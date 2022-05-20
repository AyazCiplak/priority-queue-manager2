package managementsystem;

import java.util.Scanner;

public class Menu {

    PriorityQueue queue = new PriorityQueue();

    void initialMessage() {
        System.out.println("\nThis program is designed to simulate an emergency room priority queue, " +
                "with patients being sorted based on an assigned numerical priority value. \nThe list can be manipulated " +
                "in a variety of ways - patients can be added, removed, reassigned a new priority, etc.\n" +
                "Note: A lower priority means a more urgent patient.\n");
    }

    void mainOptions() {

        Scanner mainOptionInt = new Scanner(System.in);
        System.out.println("MAIN MENU:\n" +
                "------------------\n" +
                "1 - Add Menu\n" +
                "2 - Remove Menu\n" +
                "3 - Find Menu\n" +
                "4 - Change a patient's priority\n" +
                "5 - List patients (ordered by priority) \n" +
                "6 - Exit program\n\n" +
                "Selection (enter a number): ");

        int mainSelectionInt = mainOptionInt.nextInt();

        switch(mainSelectionInt) {
            case 1:
                this.addMenu();
                break;
            case 2:
                this.removeMenu();
                break;
            case 3:
                this.findMenu();
                break;
            case 4:
                this.priorityChange();
                break;
            case 5:
                this.listPatients();
                break;
            case 6:
                System.out.println("Program exited.");
                break;
            default:
                System.out.println("Invalid selection\n");
                this.mainOptions();
                break;
        }

    }



    void addMenu() {

        Scanner addOptionInt = new Scanner(System.in);
        System.out.println("ADD MENU:\n" +
                "------------------\n" +
                "1 - Add patient and assign priority\n" +
                "2 - Add patient to end of queue\n" +
                "3 - Go back\n\n" +
                "Selection (enter a number): ");

        int addSelectionInt = addOptionInt.nextInt();

        switch(addSelectionInt) {
            case 1:
                Scanner addName = new Scanner(System.in);
                Scanner addPriority = new Scanner(System.in);

                System.out.println("Name: ");
                String name = addName.next();

                System.out.println("Priority Value: ");
                int priority = addPriority.nextInt();

                queue.add(name, priority);
                this.addSuccess();
                break;
            case 2:
                Scanner addJustName = new Scanner(System.in);

                System.out.println("Name: ");
                String justName = addJustName.next();

                queue.add(justName);
                this.addSuccess();
                break;
            case 3:
                this.mainOptions();
                break;
            default:
                System.out.println("Invalid selection\n");
                this.mainOptions();
                break;
        }
    }

    void addSuccess(){
        System.out.println("Patient added successfully.\n");
        this.mainOptions();
    }

    void removeMenu() {

        Scanner removeOptionInt = new Scanner(System.in);
        System.out.println("REMOVE MENU:\n" +
                "------------------\n" +
                "1 - Remove patient by name\n" +
                "2 - Remove highest priority patient\n" +
                "3 - Remove all patients more urgent than a certain priority value\n" +
                "4 - Remove all patients less urgent than a certain priority value\n" +
                "5 - Go back\n\n" +
                "Selection (enter a number): ");

        int removeSelectionInt = removeOptionInt.nextInt();

        switch (removeSelectionInt) {
            case 1:
                //remove patient by name function
                break;
            case 2:
                //remove highest priority patient function
                break;
            case 3:
                //remove all patients more urgent than a certain priority value function
                break;
            case 4:
                //remove all patients less urgent than a certain priority value function
                break;
            case 5:
                this.mainOptions();
                break;
            default:
                System.out.println("Invalid selection\n");
                this.mainOptions();
                break;
        }
    }

    void findMenu() {

        Scanner findOptionInt = new Scanner(System.in);
        System.out.println("FIND MENU:\n" +
                "------------------\n" +
                "1 - Check if patient is in the line\n" +
                "2 - Find patient's priority\n" +
                "3 - Find most urgent patient in line\n" +
                "4 - Go back\n\n" +
                "Selection (enter a number): ");

        int findSelectionInt = findOptionInt.nextInt();

        switch(findSelectionInt) {
            case 1:
                //check if patient is in the line function
                break;
            case 2:
                //find patient's priority function
                break;
            case 3:
                //find most urgent patient in line function
                break;
            case 4:
                this.mainOptions();
                break;
            default:
                System.out.println("Invalid selection\n");
                this.mainOptions();
                break;
        }
    }

    void priorityChange() {
        //change a patient's priority function

    }

    void listPatients() {

        //list patients by priority (also display total patients in queue)
    }

}
