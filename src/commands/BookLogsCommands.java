package commands;

import exceptions.CommandCancelException;
import library.*;
import menu.Main;
import menu.Menu;
import org.joda.time.LocalDate;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

public class BookLogsCommands extends Menu {

    public BookLogsCommands() {
        super();
        add("Print log", 1, this::printAll);
        add("Print all readers", new ReaderCommands()::printAll);
        add("Print all books", new BookCommands()::printAll);
        add("Take book", this::takeBook);
        add("Return book", this::returnBook);
        add("Show all lent books by reader", this::showAllLentBookByReader);
        setTitle("Log's entry:");
    }


    public ItemsList<BookLogsRecord> getList() {
        return Main.lib.getBookLogsRecords();
    }

    public Boolean printAll() {
        try {
            System.out.println("-".repeat(30));
            if (getList().getTitle() != null && !getList().getTitle().trim().isEmpty()) {
                println(getList().getListOf(), getList().getTitle());
            } else {
                println(getList().getListOf());
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    public Boolean takeBook() {
        try {
            Reader reader = Main.lib.getReaders().find(enterInt("Enter reader id: "));
            System.out.println("Add books to lending, press 'Enter' to exit");
            takeOneBook(reader);
        } catch (Exception ignored) {
        }
        return true;
    }

    public void takeOneBook(Reader reader) {
        try {
            Book book = find(Main.lib.getBooks(), enterInt("Enter book id: "));
            System.out.println(book);

            int copyNumber = enterInt("Enter book copy number: ");
            if (!isCopyNumberOnHand(copyNumber)) {
                try {
                    BookCopy bookCopy = new BookCopy().findCopyByBookAndCopyNumber(book, copyNumber);
                    LocalDate currentDate = new LocalDate();
                    BookLogsRecord bookLogsRecord = new BookLogsRecord(reader, bookCopy, currentDate, BookLogsRecord.ActionTypes.TAKE);
                    getList().add(bookLogsRecord);
                } catch (CommandCancelException ignored) {
                }
            } else {
                System.out.println("Copy number is not available");
            }
            takeOneBook(reader);
        } catch (CommandCancelException e) {
            return;
        }
    }

    public Boolean returnBook() {
        try {
            Reader reader = Main.lib.getReaders().find(enterInt("Enter reader id: "));
            System.out.println("Add books to return, press 'Enter' to exit");
            returnOneBook(reader);
        } catch (Exception ignored) {
        }
        return true;
    }

    public void returnOneBook(Reader reader) {
        try {
            Book book = find(Main.lib.getBooks(), enterInt("Enter book id: "));
            System.out.println(book);

            int copyNumber = enterInt("Enter book copy number: ");
            if (isCopyNumberOnHand(copyNumber)) {
                try {
                    BookCopy bookCopy = new BookCopy().findCopyByBookAndCopyNumber(book, copyNumber);
                    LocalDate currentDate = new LocalDate();
                    BookLogsRecord bookLogsRecord = new BookLogsRecord(reader, bookCopy, currentDate, BookLogsRecord.ActionTypes.RETURN);
                    getList().add(bookLogsRecord);
                } catch (CommandCancelException ignored) {
                }
            } else {
                System.out.println("Copy number is not lent");
            }
            takeOneBook(reader);
        } catch (CommandCancelException e) {
            return;
        }
    }


    public Boolean showAllLentBookByReader() {
        try {
            Reader reader = Main.lib.getReaders().find(enterInt("Enter reader id: "));

            getList().getListOf()
                    .stream()
                    .filter(bookLogsRecord
                            -> bookLogsRecord.getReader().getId() == reader.getId())
                    .collect(groupingBy(bookLogsRecord -> bookLogsRecord.getBookCopy().getBook().getId(),
                            maxBy(Comparator.comparing(BookLogsRecord::getDateOfAction))))
                    .values()
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(bookLogsRecord -> bookLogsRecord.getAction().equals(BookLogsRecord.ActionTypes.TAKE))
                    .toList()
                    .forEach(System.out::println);

//            System.out.println("===Map with keys===");
//
//            List<BookLogsRecord> records = new ArrayList<>();
//            for (BookLogsRecord record : getList().getListOf()) {
//                if (record.getReader().getId() == reader.getId()) {
//                    records.add(record);
//                }
//            }
//            Map<Integer, List<BookLogsRecord>> mapRecords = new HashMap<>();
//            for (BookLogsRecord record : records) {
//                if (!mapRecords.containsKey(record.getBookCopy().getBook().getId())) {
//                    mapRecords.put(record.getBookCopy().getBook().getId(), new ArrayList<>());
//                }
//                mapRecords.get(record.getBookCopy().getBook().getId()).add(record);
//            }
//            List<BookLogsRecord> recordsBooksOnHands = new ArrayList<>();
//
//            mapRecords.forEach((key, bookRecords)
//                    -> {
//                bookRecords.sort(Comparator.comparing(BookLogsRecord::getDateOfAction));
//                if (bookRecords.getLast().getAction() == BookLogsRecord.ActionTypes.TAKE) {
//                    recordsBooksOnHands.add(bookRecords.getLast());
//                }
//            });
//
//            recordsBooksOnHands.forEach(System.out::println);
//
//            System.out.println("===Map without keys===");
//
//            List<BookLogsRecord> recordsBooksOnHands2 = new ArrayList<>();
//
//            mapRecords.values().stream().forEach(bookRecords
//                    -> {
//                bookRecords.sort(Comparator.comparing(BookLogsRecord::getDateOfAction));
//                if (bookRecords.getLast().getAction() == BookLogsRecord.ActionTypes.TAKE) {
//                    recordsBooksOnHands2.add(bookRecords.getLast());
//                }
//            });
//
//            recordsBooksOnHands2.forEach(System.out::println);
//
//            System.out.println("end");


        } catch (Exception ignored) {
        }
        return true;
    }

    public List<Integer> bookCopiesOnHand() {
        return getList().getListOf()
                .stream()
                .collect(groupingBy(bookLogsRecord -> bookLogsRecord.getBookCopy().getBook().getId(),
                        maxBy(Comparator.comparing(BookLogsRecord::getDateOfAction))))
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(bookLogsRecord -> bookLogsRecord.getAction() == BookLogsRecord.ActionTypes.TAKE)
                .map(bookLogsRecord -> bookLogsRecord.getBookCopy().getCopyNumber())
                .toList();
    }

    public List<BookCopy> bookCopiesRead() {
        Set<Integer> setOfCopyNumbersOnHand = new HashSet<>(bookCopiesOnHand());

        return getList().getListOf().stream()
                .map(bookLogsRecord -> bookLogsRecord.getBookCopy())
                .filter(bookCopy -> !setOfCopyNumbersOnHand.contains(bookCopy.getCopyNumber()))
                .toList();
    }

    public List<BookCopy> bookCopiesAvailable() {
        Set<Integer> setOfCopyNumbersOnHand = new HashSet<>(bookCopiesOnHand());

        return Main.lib.getBookCopies().getListOf().stream()
                .filter(bookCopy -> !setOfCopyNumbersOnHand.contains(bookCopy.getCopyNumber()))
                .toList();
    }

    public Boolean isCopyNumberOnHand(int copyNumber) {
        return bookCopiesOnHand().contains(copyNumber);
    }


}
