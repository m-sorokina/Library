package menu;

import exceptions.CommandCancelException;
import library.ItemsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public final static int WRONG_VALUE = -1;
    public final static int COMMAND_CANCEL = -2;

    public final static String separatorLine = "-".repeat(30);

    static Scanner in = new Scanner(System.in);


    static public class Item {
        private final int itemNumber;
        private final String item;
        private IMenuCommand command;

        public Item(String item, int itemNumber, IMenuCommand command) {
            this.item = item;
            this.itemNumber = itemNumber;
            this.command = command;
        }

        public int getItemNumber() {
            return itemNumber;
        }

        public IMenuCommand getCommand() {
            return command;
        }

        public void setCommand(IMenuCommand value) {
            command = value;
        }

        public String toString() {
            return String.format("%d. %s", itemNumber, item);
        }
    }

    private final List<Item> menuItems;
    private String title;

    public Menu() {
        menuItems = new ArrayList<>();
        title = "";
    }

    public Menu(String title) {
        menuItems = new ArrayList<>();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void add(String item, int itemNumber, IMenuCommand command) {
        menuItems.add(new Item(item, itemNumber, command));
    }

    public void add(String item, IMenuCommand command) {
        int itemNumber = (menuItems.isEmpty()) ? 0 : menuItems.getLast().getItemNumber() + 1;
        menuItems.add(new Item(item, itemNumber, command));
    }

    public void run() {
        boolean runResult = true;
        do {
            try {
                runResult = getCommand();
            } catch (CommandCancelException e) {
                runResult = false;
            } catch (Exception e) {
                System.out.println("Wrong command!");
                System.out.println(e.getMessage());
            }
        }
        while (runResult);
    }

    public boolean getCommand() {
        System.out.println(separatorLine);
        if (getTitle() != null && !getTitle().trim().isEmpty()) {
            println(menuItems, getTitle());
        } else {
            println(menuItems);
        }
        int commandNumber = enterInt("Enter a command number: ");

        Item command = menuItems.stream()
                .filter(item -> item.itemNumber == commandNumber)
                .findFirst()
                .get();
        return command.command != null && command.command.get();

    }

    public static <T> void println(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Not found");
            return;
        }
        list.forEach(System.out::println);
    }

    public static <T> void println(List<T> list, String title) {
        if (list == null || list.isEmpty()) {
            System.out.println("Not found");
            return;
        }
        System.out.println(title);
        System.out.println(separatorLine);
        list.forEach(System.out::println);
    }


    static public Integer enterInt(String prompt) {
        String s = enterString(prompt);
        int value = tryEnterInt(s);
        if (value == WRONG_VALUE) {
            value = enterInt("The entered value is wrong, please repeat: ");
        }
        return value;
    }

    static public Integer updateInt(String prompt, Integer value) {
        return tryEnterInt(updateString(prompt, String.valueOf(value)));
    }

    static private Integer tryEnterInt(String text) {
        try {
            int value = Integer.parseInt(text);
            if (value < 0) throw new Exception();
            return value;
        } catch (Exception e) {
            return WRONG_VALUE;
        }
    }

    static public String enterString(String prompt) {
        System.out.print(prompt);
        String s = in.nextLine();
        if (s.isEmpty()) throw new CommandCancelException();
        return s;
    }

    static public String updateString(String prompt, String value) {
        System.out.printf("%s%s%nNew value: ", prompt, value);
        String newValue = in.nextLine();
        return (newValue.isEmpty() || newValue.isBlank()) ? value : newValue;
    }

    public static <T> T find(ItemsList<T> list, String prompt) {
        T item = list.find(enterInt(prompt));
        return (item == null) ? find(list, "Wrong id, please repeat, 'Enter' to exit: ") : item;
    }

}
