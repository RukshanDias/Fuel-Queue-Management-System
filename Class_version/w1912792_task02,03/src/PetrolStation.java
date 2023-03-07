import java.util.Scanner;

public class PetrolStation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //initialising variables
        char replay='y';

        System.out.println("Hello Welcome to Fuel Queue Management System.");

        while (replay=='y') {
            //Printing menu
            System.out.println("100 or VFQ: View all Fuel Queues.\n" +
                    "101 or VEQ: View all Empty Queues.\n" +
                    "102 or ACQ: Add customer to a Queue.\n" +
                    "103 or RCQ: Remove a customer from a Queue.\n" +
                    "104 or PCQ: Remove a served customer.\n" +
                    "105 or VCS: View Customers Sorted in alphabetical order\n" +
                    "106 or SPD: Store Program Data into file.\n" +
                    "107 or LPD: Load Program Data from file.\n" +
                    "108 or STK: View Remaining Fuel Stock.\n" +
                    "109 or AFS: Add Fuel Stock.\n" +
                    "110 or IFQ: income of each Fuel queue.\n"+
                    "999 or EXT: Exit the Program.\n");

            while (true) {
                System.out.print("Enter a option:");
                String option = sc.next().toUpperCase(); //getting user input

                switch (option) {
                    case "100", "VFQ":
                        //fuelQueue[0][1]=new FuelQueue("Shan","Dias","we1232",10);
                        //fuelQueue[0][5]=new FuelQueue("Rukshan","Dias","we1232",10);

                        FuelQueue.view_queues();

                        System.out.println("--------------------------------------------------\n");
                        break;

                    case "101", "VEQ":
                        System.out.println("View empty queues");
                        FuelQueue.view_empty_queues();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "102", "ACQ":
                        FuelQueue.add_customer();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "103", "RCQ":
                        FuelQueue.remove_customer();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "104", "PCQ":
                        System.out.println("Remove a served customer.");
                        FuelQueue.remove_served_customer();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "105", "VCS":
                        FuelQueue.sort_queue();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "106", "SPD":
                        FuelQueue.store_data_toFile();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "107", "LPD":
                        FuelQueue.show_data_fromFile();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "108", "STK":
                        System.out.println("Remaining fuel stock: "+FuelQueue.getFuelStock()+"L");
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "109", "AFS":
                        FuelQueue.add_fuel_stock();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "110", "IFQ":
                        FuelQueue.DisplayFuelIncome();
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "999", "EXT":
                        replay='n';
                        System.out.println("Exit from the program.");
                        break;
                    default:
                        System.out.println("invalid input..pls try again.");
                        continue;

                }
                break;
            }
        }

    }
}


