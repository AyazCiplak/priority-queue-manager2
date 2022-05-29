package managementsystem;

import java.util.Scanner;

public class Menu {

    PriorityQueue queue = new PriorityQueue();

    void initialMessage() {
        System.out.println("""
                This program is designed to simulate an emergency room priority queue, with patients being sorted based on an assigned numerical priority value.\s
                The list can be manipulated in a variety of ways - patients can be added, removed, reassigned a new priority, etc.
                Note: A lower priority means a more urgent patient.
                """);
    }

    void mainOptions() {

        Scanner mainOptionInt = new Scanner(System.in);
        System.out.println("""
                MAIN MENU:
                ------------------
                1 - Add Menu
                2 - Remove Menu
                3 - Find Menu
                4 - Change a patient's priority
                5 - List patients (ordered by priority)\s
                6 - Exit program

                Selection (enter a number):\s""");

        int mainSelectionInt = mainOptionInt.nextInt();

        switch (mainSelectionInt) {
            case 1 -> this.addMenu();
            case 2 -> this.removeMenu();
            case 3 -> this.findMenu();
            case 4 -> this.priorityChange();
            case 5 -> this.listPatients();
            case 6 -> System.out.println("Program exited.");
            default -> {
                System.out.println("Invalid selection\n");
                this.mainOptions();
            }
        }
    }

    void addMenu() {

        Scanner addOptionInt = new Scanner(System.in);
        System.out.println("""
                ADD MENU:
                ------------------
                1 - Add patient and assign priority
                2 - Add patient to end of queue
                3 - Go back

                Selection (enter a number):\s""");

        int addSelectionInt = addOptionInt.nextInt();

        switch (addSelectionInt) {
            case 1 -> {
                Scanner addName = new Scanner(System.in);
                Scanner addPriority = new Scanner(System.in);
                System.out.println("Name: ");
                String name = addName.next();

                if (queue.contains(name)) {
                    this.addFailure();
                } else {

                    System.out.println("Priority Value: ");
                    double priority = addPriority.nextDouble();

                    queue.add(name, priority);
                    this.addSuccess();
                }
            }
            case 2 -> {
                Scanner addJustName = new Scanner(System.in);
                System.out.println("Name: ");
                String justName = addJustName.next();
                if (queue.contains(justName)) {
                    this.addFailure();
                } else {
                    queue.add(justName);
                    this.addSuccess();
                }
            }
            case 3 -> this.mainOptions();
            default -> {
                System.out.println("Invalid selection\n");
                this.mainOptions();
            }
        }
    }

    void addSuccess(){
        System.out.println("Patient added successfully.\n");
        this.mainOptions();
    }

    void addFailure() {
        System.out.println("Error: Patient already exists in queue.\n");
        this.mainOptions();
    }

    void removeMenu() {

        Scanner removeOptionInt = new Scanner(System.in);
        System.out.println("""
                REMOVE MENU:
                ------------------
                1 - Remove patient by name
                2 - Remove highest priority patient
                3 - Remove all patients more urgent than a certain priority value
                4 - Remove all patients less urgent than a certain priority value
                5 - Go back

                Selection (enter a number):\s""");

        int removeSelectionInt = removeOptionInt.nextInt();

        switch (removeSelectionInt) {
            case 1 -> {
                Scanner removeName = new Scanner(System.in);
                System.out.println("Name: ");
                String name = removeName.next();
                if (queue.contains(name)) {
                    queue.remove(name);
                    this.removeSuccess();
                } else {
                    this.removeFailure();
                }
            }
            case 2 -> {
                if(queue.patients.size()<=1){
                    this.removeFailure();
                } else {
                    queue.removeMin();
                    this.removeSuccess();
                }
            }
            case 3 -> {
                Scanner removeMinPriority = new Scanner(System.in);
                System.out.println("Threshold priority value: ");
                double minPriority = removeMinPriority.nextDouble();
                queue.removeUrgentPatients(minPriority);
                this.removeSuccess();
            }
            case 4 -> {
                Scanner removeMaxPriority = new Scanner(System.in);
                System.out.println("Threshold priority value: ");
                double maxPriority = removeMaxPriority.nextDouble();
                queue.removeNonUrgentPatients(maxPriority);
                this.removeSuccess();
            }
            case 5 -> this.mainOptions();
            default -> {
                System.out.println("Invalid selection\n");
                this.mainOptions();
            }
        }
    }

    void removeSuccess() {
        System.out.println("Patient removed successfully.\n");
        this.mainOptions();
    }

    void removeFailure() {
        System.out.println("Error: Patient does not exist in queue.\n");
        this.mainOptions();
    }


    void findMenu() {

        Scanner findOptionInt = new Scanner(System.in);
        System.out.println("""
                FIND MENU:
                ------------------
                1 - Check if patient is in the queue
                2 - Find patient's priority
                3 - Find most urgent patient in queue
                4 - Go back

                Selection (enter a number):\s""");

        int findSelectionInt = findOptionInt.nextInt();

        switch (findSelectionInt) {
            case 1 -> {
                Scanner findName = new Scanner(System.in);
                System.out.println("Name: ");
                String name = findName.next();
                if (queue.contains(name)) {
                    System.out.println("Patient is in queue.");
                } else {
                    System.out.println("Patient is not in queue.");
                }
                this.mainOptions();
            }
            case 2 -> {
                Scanner findNamesPriority = new Scanner(System.in);
                System.out.println("Name: ");
                String namePriority = findNamesPriority.next();
                if (queue.contains(namePriority)) {
                    System.out.println("Patient's priority is " + queue.getPriority(namePriority));
                } else {
                    System.out.println("Error: Patient does not exist in queue.");
                }
                this.mainOptions();
            }
            case 3 -> {
                if(queue.patients.size() > 1) {
                    System.out.println("Highest priority patient is " + queue.peekMin() + ", with priority value " + queue.getMinPriority());
                } else {
                    System.out.println("Error: There are no patients in queue.");
                }
                this.mainOptions();
            }
            case 4 -> this.mainOptions();
            default -> {
                System.out.println("Invalid selection\n");
                this.mainOptions();
            }
        }
    }


    void priorityChange() {

        Scanner switchName = new Scanner(System.in);
        Scanner switchPriority = new Scanner(System.in);

        System.out.println("Name: ");
        String name = switchName.next();

        if(queue.contains(name)) {

            System.out.println("New priority value: ");
            double priority = switchPriority.nextDouble();

            queue.changePriority(name, priority);

            System.out.println("Patient priority switched successfully. \n");
            this.mainOptions();

        } else {
            System.out.println("Error: Patient does not exist in queue.");
            this.mainOptions();
        }
    }

    void listPatients() {
        System.out.println(queue.nameToIndex);
        this.mainOptions();
    }
}
