package menu;

import commands.MenuCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Menu {

    static Scanner in = new Scanner(System.in);


    static public class Item {
        private final int itemNumber;
        private final String item;
        private Supplier<Boolean> command;

        public Item(String item, int itemNumber, Supplier<Boolean> command) {
            this.item = item;
            this.itemNumber = itemNumber;
            this.command = command;
        }

        public int getItemNumber() {
            return itemNumber;
        }

        public Supplier<Boolean> getCommand() {
            return command;
        }

        public void setCommand(Supplier<Boolean> value) {
            command = value;
        }

        public String toString() {
            return String.format("%d. %s", itemNumber, item);
        }
    }

    private final List<Item> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
    }

    public void add(String item, int itemNumber, Supplier<Boolean> command) {
        menuItems.add(new Item(item, itemNumber, command));
    }

    public void add(String item, Supplier<Boolean> command) {
        int itemNumber = (menuItems.isEmpty()) ? 0 : menuItems.getLast().getItemNumber() + 1;
        menuItems.add(new Item(item, itemNumber, command));
    }

    public void run() {
        boolean runResult = true;
        do {
            try {
                runResult = getCommand();
            } catch (Exception e) {
                System.out.println("Wrong command!");
            }
        }
        while (runResult);
    }

    public boolean getCommand() {
        System.out.println("-".repeat(30));
        println(menuItems);

        try {
            int commandNumber = enterInt("Enter a command number: ");

            Item command = menuItems.stream().
                    filter(item -> item.itemNumber == commandNumber).findFirst().get();
            return command.command != null && command.command.get();
        } catch (RuntimeException e) {
            return true;
        }

    }

    public static <T> void println(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Not found");
            return;
        }
        list.forEach(System.out::println);
    }

    static public Integer enterInt(String prompt) {
        System.out.print(prompt);
        int value = tryEnterInt();
        if (value == MenuCommands.COMMAND_CANCEL) throw new RuntimeException();
        while (value == -1) {
            value = tryEnterInt();
        }
        return value;
    }

    static public Integer updateInt(String prompt, Integer value) {
        System.out.printf("%s%s%n", prompt, value);
        String newValue = in.nextLine();
        return (newValue.isEmpty() || newValue.isBlank()) ? value : tryEnterInt(newValue);
    }

    static private Integer tryEnterInt() {
        try {
            String s = in.nextLine();
            if (s.isEmpty()) return MenuCommands.COMMAND_CANCEL;
            int value = Integer.parseInt(s);
            if (value < 0) throw new Exception();
            return value;
        } catch (Exception e) {
            System.out.print("The entered value is wrong, please repeat: ");
            return -1;
        }
    }

    static private Integer tryEnterInt(String text) {
        try {
            int value = Integer.parseInt(text);
            if (value < 0) throw new Exception();
            return value;
        } catch (Exception e) {
            System.out.print("The entered value is wrong, please repeat: ");
            return -1;
        }
    }

    static public String enterString(String prompt) {
        System.out.print(prompt);
        String s = in.nextLine();
        if (s.isEmpty()) throw new RuntimeException();
        return s;
    }

    static public String updateString(String prompt, String value) {
        System.out.printf("%s%s%n", prompt, value);
        String newValue = in.nextLine();
        return (newValue.isEmpty() || newValue.isBlank()) ? value : newValue;
    }

}
