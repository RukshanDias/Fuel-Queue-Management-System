import java.io.*;
import java.util.*;

public class fuelManagementSystem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //initialising variables
        char replay='y';
        String[][] queue=new String[3][6];
        int fuel_stock=6600;

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
                    "999 or EXT: Exit the Program.\n");

            while (true) {
                System.out.print("Enter a option:");
                String option = sc.next().toUpperCase(); //getting user input

                switch (option) {
                    case "100", "VFQ":
                        view_queues("1", queue[0]);
                        view_queues("2", queue[1]);
                        view_queues("3", queue[2]);
                        System.out.println("--------------------------------------------------\n");
                        break;

                    case "101", "VEQ":
                        System.out.println("View empty queues");
                        view_empty_queues(queue);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "102", "ACQ":
                        fuel_stock=add_customer(queue,fuel_stock);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "103", "RCQ":
                        fuel_stock=remove_customer(queue,fuel_stock);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "104", "PCQ":
                        System.out.println("Remove a served customer.");
                        remove_served_customer(queue);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "105", "VCS":
                        sort_queue(queue);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "106", "SPD":
                        //store_data_toFile(queue);
                        store_data_toFile(queue,fuel_stock);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "107", "LPD":
                        show_data_fromFile(queue,fuel_stock);
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "108", "STK":
                        System.out.println("Remaining fuel stock: "+fuel_stock+"L");
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "109", "AFS":
                        fuel_stock+=add_fuel_stock(fuel_stock);
                        System.out.println("New fuel stock is, "+fuel_stock+"L");
                        System.out.println("--------------------------------------------------\n");
                        break;
                    case "999", "EXT":
                        replay='n';
                        System.out.println("Exit from the program.\nThank you for using Fuel Management System!");
                        break;
                    default:
                        System.out.println("invalid input..pls try again.");
                        continue;

                }
                break;
            }
        }
    }
    //------------------------------Methods---------------------------------

    //for 100 or VFQ: View all Fuel Queues.
    private static void view_queues(String type,String [] queue){
        for (int i=0;i<queue.length;i++){
            if ((queue[i])==null){
                System.out.println("queue "+type+": place "+(i+1)+" -> Empty");
            }else {
                System.out.println("queue "+type+": place "+(i+1)+" -> "+queue[i]);
            }
        }
        System.out.println("\n");
    }

    // for 102 or ACQ: Add customer to a Queue.
    private static int add_customer(String[][]queue,int fuel){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter name:");
        String name = sc.next().toLowerCase();
        int queue_no;
        while (true){
            try {
                System.out.print("Enter queue: ");
                queue_no = sc.nextInt();
                if (queue_no>3 || queue_no<1){
                    System.out.println("Invalid queue number.");
                    continue;
                }
                break;
            }catch (Exception e){
                System.out.println("Invalid input. enter a number.");
                sc.nextLine(); //clear input
                continue;
            }
        }
        int num=queue_no-1;
        for (int col=0;col<queue[num].length;col++) {
            int lastVal = queue[num].length - 1;
            if (queue[num][lastVal]==null){
            if (queue[num][col] == null) {
                queue[num][col] = name;
                System.out.println(name+" added to queue "+queue_no);
                fuel -= 10;
                break;
            }
            }else {
                System.out.println("Queue "+queue_no+" is full now. check another queue or try again later.");
                break;
            }
        }
        return fuel;
    }

    // for 103 or RCQ: Remove a customer from a Queue.
    private static int remove_customer(String [][] queue,int fuel){
        Scanner sc=new Scanner(System.in);
        int queue_no;
        int location;
        while (true){
            try {
                System.out.print("Enter queue number:");
                queue_no=sc.nextInt();
                if (queue_no<1 || queue_no>3){
                    System.out.println("Enter a valid queue number.");
                    continue;
                }
                System.out.print("Enter location:");
                location=sc.nextInt();
                if (location<1 || location>6){
                    System.out.println("Enter a valid queue number.");
                    sc.nextLine();//clear input
                    continue;
                }
                break;
            }catch (Exception e){
                System.out.println("Enter a number not a letter.");
                sc.nextLine();//clear input
                continue;
            }

        }
        if (queue[queue_no-1][location-1]!=null){
            System.out.println(queue[queue_no-1][location-1]+" removed.");
            queue[queue_no-1][location-1]=null;
            fuel+=10;
            queue=customers_moveFront(queue,queue_no); //moving customers to front
        }else {
            System.out.println("location "+location+" is Empty. pls try again with correct location.");
        }

        return fuel;
    }

    private static String[][] customers_moveFront(String[][]queue,int queue_no){
        //reordering queue
        for (int i=0;i<queue[queue_no-1].length;i++){
            if (queue[queue_no-1][i]==null){
                for (int k=i+1; k<queue[queue_no-1].length; k++){
                    queue[queue_no-1][k-1] = queue[queue_no-1][k];
                }
                int lastVal=queue[queue_no-1].length-1;
                queue[queue_no-1][lastVal]=null;
                break;
            }
        }return queue;
    }

    // for 101 or VEQ: View all Empty Queues.
    private static void view_empty_queues(String [][] queue){
        for (int row=0;row<queue.length;row++){
            System.out.println("\nQueue No: "+(row+1));
            for (int col=0;col<queue[row].length;col++){
                if (queue[row][col]==null){
                    System.out.println("Queue no, "+(row+1)+" location no, "+(col+1)+" --> Empty");
                }
            }

        }
        System.out.println("--------------------------------------------------");
    }

    //for 104 or PCQ: Remove a served customer.
    private static void remove_served_customer(String [][] queue){
        Scanner sc=new Scanner(System.in);
        int queue_no;
        String customer_name;
        System.out.print("Enter name: ");
        customer_name=sc.nextLine();
        while (true){
            try {
                System.out.print("Enter his/her queue no: ");
                queue_no=sc.nextInt();
                if (queue_no<1 || queue_no>3) {
                    System.out.println("Enter a valid queue number.");
                    continue;
                }
                break;
            }catch (Exception e){
                System.out.println("Enter a number not a letter.");
                sc.nextLine();
                continue;
            }
        }
        //remove part
        int index=0;
        boolean isNameEqual=false;
        for (String i : queue[queue_no-1]){
            if (i!=null && i.equalsIgnoreCase(customer_name)){
                System.out.println(i+" removed");
                queue[queue_no-1][index]=null;
                isNameEqual=true;
                break;
            }index++;
        }
        if (!isNameEqual){//if isNameEqual is false
            System.out.println("There's no passenger called, "+customer_name+" in queue "+queue_no);
        }
        queue=customers_moveFront(queue,queue_no); //moving customers to front
    }

    //for 109 or AFS: Add Fuel Stock.
    private static int add_fuel_stock(int fuel) {
        Scanner sc = new Scanner(System.in);
        int new_fuel_count;
        while (true) {
            try {
                System.out.print("Enter fuel amount to add: ");
                new_fuel_count = sc.nextInt();//get input
                if (new_fuel_count < 0) {
                    System.out.println("invalid, fuel amount should be greater than 0.");
                    //sc.nextLine();//clear input
                    continue;
                }
                System.out.println(new_fuel_count+"L has been added to fuel stock.");
                break;
            } catch (Exception e) {
                System.out.println("Enter a valid amount.");
                sc.nextLine();//clear input
                continue;
            }
        }
        return new_fuel_count;
    }
    //for 107 or LPD: Load Program Data from file.

    //for 106 or SPD: Store Program Data into file.
    private static void store_data_toFile(String[][] queue_list,int fuelStock){
        try {
            FileWriter dataFile=new FileWriter("Task01_data.txt");
            for (int row=0;row< queue_list.length;row++){
                for (int col=0;col<queue_list[row].length;col++){
                    dataFile.write(queue_list[row][col]+"\n");
                }
            }
            dataFile.write(String.valueOf(fuelStock));
            dataFile.close();
            System.out.println("Data stored in Task01_data.txt file..");
        }catch (IOException e){
            System.out.println("error when writing file..");
        }
    }

    //107 or LPD: Load Program Data from file.
    private static void show_data_fromFile(String[][] queue_list,int fuelStock){
        try{
            File loadedFile=new File("Task01_data.txt");
            Scanner readFile=new Scanner(loadedFile);
            while (readFile.hasNext()){
                for (int row=0;row< queue_list.length;row++){
                    for (int col=0;col<queue_list[row].length;col++){
                        queue_list[row][col]=readFile.nextLine();
                    }
                }
                fuelStock=readFile.nextInt();
            }
            readFile.close();
            System.out.println("Data has been loaded");
        }catch (IOException e){
            System.out.println("error occur");
        }
    }


    //for 105 or VCS: View Customers Sorted in alphabetical order
    //https://www.geeksforgeeks.org/row-wise-sorting-2d-array/
    private static void sort_queue(String [][]queue){
        String[][] copyArr=new String[3][6]; //copying queue to a new array
        //copying queue to new array
        for (int row=0;row< queue.length;row++){
            for (int col=0;col< queue[row].length;col++){
                copyArr[row][col]=queue[row][col];
            }
        }

        // loop for rows of matrix
        for (int i = 0; i < copyArr.length; i++) {

            // loop for column of matrix
            for (int j = 0; j < copyArr[i].length; j++) {

                // loop for comparison and swapping
                for (int k = 0; k < copyArr[i].length - j - 1; k++) {
                    try {
                        if (Str_compare(copyArr[i][k],copyArr[i][k + 1])  >0) {

                            // swapping elements
                            String t = copyArr[i][k];
                            copyArr[i][k] = copyArr[i][k + 1];
                            copyArr[i][k + 1] = t;
                        }
                    }catch (NullPointerException n){
                        continue;
                    }

                }
            }
        }

        // printing the sorted queue
        for (int i = 0; i < copyArr.length; i++) {
            System.out.print("Queue "+(i+1)+" --> ");
            for (int j = 0; j < copyArr[i].length; j++)
                if (copyArr[i][j]!=null){
                    System.out.print(copyArr[i][j] + ", ");
                }

            System.out.println();
        }
    }

    //String comparison
    private static int Str_compare(String s1, String s2){
        int len1=s1.length();
        int len2=s2.length();

        int min;
        //finding min value
        if (len1>len2){
            min =len2;
        }else {
            min=len1;
        }
         char[] ch1=s1.toCharArray(); //convert string to letter by letter array
         char[] ch2=s2.toCharArray();

         for (int i=0;i<min;i++){
             char letter1=ch1[i];
             char letter2=ch2[i];
             if (letter1!=letter2){
                 int val=letter1-letter2;
                 return val;
             }
         }
    return 0;
    }
}
