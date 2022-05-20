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
                double priority = addPriority.nextDouble();

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

        //to add: cases when there are no patients / no existing patients with the name inputted
        switch (removeSelectionInt) {
            case 1:
                Scanner removeName = new Scanner(System.in);

                System.out.println("Name: ");
                String name = removeName.next();

                queue.remove(name);
                this.removeSuccess();
                break;
            case 2:
                queue.removeMin();
                this.removeSuccess();
                break;
            case 3:
                Scanner removeMinPriority = new Scanner(System.in);

                System.out.println("Threshold priority value: ");
                double minPriority = removeMinPriority.nextDouble();

                queue.removeUrgentPatients(minPriority);
                this.removeSuccess();
                break;
            case 4:
                Scanner removeMaxPriority = new Scanner(System.in);

                System.out.println("Threshold priority value: ");
                double maxPriority = removeMaxPriority.nextDouble();

                queue.removeNonUrgentPatients(maxPriority);
                this.removeSuccess();
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

    void removeSuccess() {
        System.out.println("Patient removed successfully.\n");
        this.mainOptions();
    }

    //to add: cases where the patient cannot be found (in later options especially)
    void findMenu() {

        Scanner findOptionInt = new Scanner(System.in);
        System.out.println("FIND MENU:\n" +
                "------------------\n" +
                "1 - Check if patient is in the queue\n" +
                "2 - Find patient's priority\n" +
                "3 - Find most urgent patient in queue\n" +
                "4 - Go back\n\n" +
                "Selection (enter a number): ");

        int findSelectionInt = findOptionInt.nextInt();

        switch(findSelectionInt) {
            case 1:
                Scanner findName = new Scanner(System.in);

                System.out.println("Name: ");
                String name = findName.next();

                if(queue.contains(name)){
                    System.out.println("Patient is in queue.");
                } else {
                    System.out.println("Patient is not in queue.");
                }

                this.mainOptions();
                break;
            case 2:

                Scanner findNamesPriority = new Scanner(System.in);

                System.out.println("Name: ");
                String namePriority = findNamesPriority.next();

                if(queue.contains(namePriority)){
                    System.out.println("Patient's priority is " + queue.getPriority(namePriority));
                } else {
                    System.out.println("Error: Patient does not exist");
                }

                this.mainOptions();
                break;
            case 3:
                //add case where no patients exist in the line
                System.out.println("Highest priority patient is " + queue.peekMin() + ", with priority value " + queue.getMinPriority());

                this.mainOptions();
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
