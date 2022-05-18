package managementsystem;

import java.util.Scanner;

public class Menu {

    //prints out initial message
    void initialMessage() {
        System.out.println("\nThis program is designed to simulate an emergency room priority queue, " +
                "with patients being sorted based on an assigned numerical priority value. \nThe list can be manipulated " +
                "in a variety of ways - patients can be added, removed, reassigned a new priority, etc.\n");
    }

    void mainOptions() {

        Scanner mainOptionInt = new Scanner(System.in);
        System.out.println("MAIN MENU:\n" +
                "------------------\n" +
                "1 - List patients (ordered by priority)\n" +
                "2 - Add patients\n" +
                "3 - Remove patients\n" +
                "4 - Change priority\n" +
                "5 - Find patients\n\n" +
                "Selection (enter a number): ");

        int mainSelectionInt = mainOptionInt.nextInt();
        System.out.println("Selection: " + mainSelectionInt);

    }

    void listMenu() {

    }

    void addMenu() {

    }

    void removeMenu() {

    }

    void checkMenu() {

    }

}
