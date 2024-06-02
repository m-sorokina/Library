package commands;

import library.BookLogsRecord;
import library.ItemsList;
import menu.Main;
import menu.Menu;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Comparator;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

public class ReportCommands extends Menu {

    private final int MAX_DAY_LEND = 1;
    public ReportCommands(){
        super();
        add("Show all readers with overdue", 1, this::allReadersOverdue);
        setTitle("Report entry:");
    }

    public ItemsList<BookLogsRecord> getList() {
        return Main.lib.getBookLogsRecords();
    }

    public Boolean allReadersOverdue(){
        try {
            getList().getListOf().stream()
                    .collect(groupingBy(bookLogsRecord -> bookLogsRecord.getBookCopy().getBook().getId(),
                            maxBy(Comparator.comparing(BookLogsRecord::getDateOfAction))))
                    .values()
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(bookLogsRecord -> bookLogsRecord.getAction() == BookLogsRecord.ActionTypes.TAKE)
                    .filter(bookLogsRecord
                            -> (Days.daysBetween(bookLogsRecord.getDateOfAction(), new LocalDate()).getDays() > MAX_DAY_LEND))
                    .sorted(Comparator.comparing(BookLogsRecord::getDateOfAction)
                            .thenComparing(BookLogsRecord::getReaderName))
                    .forEach(System.out::println);
        } catch (Exception ignored) {
        }
        return true;
    }
}
