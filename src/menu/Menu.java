package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Menu {

    static Scanner in = new Scanner(System.in);

    static public class Item {
        private final int itemNumber;
        private final String item;
        private final Supplier<Boolean> command;

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

        int commandNumber = enterInt("Enter a command number: ");

        Item command = menuItems.stream().
                filter(item -> item.itemNumber == commandNumber).findFirst().get();
        return command.command != null && command.command.get();

    }

    public static <T> void println(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Not found");
            return;
        }
        list.forEach(System.out::println);
    }

    static public int enterInt(String prompt) {
        System.out.print(prompt);
        int value = tryEnterInt();
        while (value == -1) {
            value = tryEnterInt();
        }
        return value;
    }

    static private int tryEnterInt() {
        try {
            int value = Integer.parseInt(in.nextLine());
            if (value < 0) throw new Exception();
            return value;
        } catch (Exception e) {
            System.out.print("The entered value is wrong, please repeat: ");
            return -1;
        }
    }

    static public String enterString(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }

}
