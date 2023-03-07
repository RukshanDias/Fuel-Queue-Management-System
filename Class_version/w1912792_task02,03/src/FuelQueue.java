import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FuelQueue extends passenger{
    //class attributes
    private static int fuelStock=6600;
    private static int [] FuelIncome=new int[5];
    private static FuelQueue fuelQueue[][]=new FuelQueue[5][6];

    //class methods

    //constructor
    public FuelQueue(String fName, String sName, String vehicalNo, int liters){
        super(fName,sName,vehicalNo,liters);
    }

    //FuelQueue array getter
    public static FuelQueue[][] getFuelQueue() {
        return fuelQueue;
    }

    //100 or VFQ: View all Fuel Queues
    public static void view_queues(){
        FuelQueue queue[][]=getFuelQueue();
        FuelQueue PassesngerQueue;
        for (int i=0;i<queue.length;i++){
            for (int j=0;j< queue[i].length;j++){
                PassesngerQueue=queue[i][j];
                if ((queue[i][j])==null){
                    System.out.println("queue "+(i+1)+": place "+(j+1)+" -> Empty");
                }else {
                    System.out.println("queue "+(i+1)+": place "+(j+1)+" -> "+PassesngerQueue.getFirstName());
                }
            }
            System.out.println("\n");
        }

    }

    //101 or VEQ: View all Empty Queues
    public static void view_empty_queues(){
        FuelQueue queue[][]=getFuelQueue();
        for (int row=0;row<queue.length;row++){
            System.out.println("\nQueue No: "+(row+1));
            for (int col=0;col<queue[row].length;col++){
                if (queue[row][col]==null){
                    System.out.println("Queue no, "+(row+1)+" location no, "+(col+1)+" --> Empty");
                }
            }

        }
    }

    //102 or ACQ: Add customer to a Queue.
    public static void add_customer(){
        FuelQueue queue[][]=getFuelQueue();
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter first name:");
        String Fname = sc.next();
        System.out.print("Enter second name:");
        String Sname = sc.next();
        System.out.print("Enter vehicle number:");
        String VehicleNo = sc.next();
        int liters;
        while (true){
            try {
                System.out.print("Enter liters required:");
                liters = sc.nextInt();
                break;
            }catch (Exception e){
                System.out.println("Invalid input. Enter a number..");
                sc.nextLine(); //clear input
                continue;
            }

        }

        int num=ShortestQueue(queue);//check the shortest queue

        for (int col=0;col<queue[num].length;col++) {
            int lastVal = queue[num].length - 1;
            if (queue[num][lastVal]==null){
                if (queue[num][col] == null) {
                    queue[num][col] =new FuelQueue(Fname,Sname,VehicleNo,liters);
                    System.out.println(Fname+" added to queue number "+(num+1));
                    fuelStock -= liters;
                    setFuelIncome(num,liters);
                    break;
                }
            }else {
                System.out.println("Fuel Queues are full now.");
                System.out.println("You will be added to a waiting list.");
                WaitingList.enqueue(Fname,Sname,VehicleNo,liters);
                break;
            }
        }
    }

    //Fuel income array
    public static void setFuelIncome(int index,int liters) {
        int income=liters*430;
        FuelIncome[index]+=income;
    }

    //108 STK - fuel stock Getter
    public static int getFuelStock() {
        return fuelStock;
    }

    //find the shortest queue
    public static int ShortestQueue(FuelQueue [][] queue){
        int shortestIndex=0;
        int NullCount=0;
        int temp=0;
        for (int row=0;row<queue.length;row++){
            for (int col=0;col<queue[row].length;col++){
                if (queue[row][col]==null){
                    NullCount+=1;
                }
            }
            if (NullCount>temp){
                shortestIndex=row;
            }
            temp=NullCount;
            NullCount=0;

        }
        return shortestIndex;
    }

    //103 or RCQ: Remove a customer from a Queue
    public static void remove_customer(){
        FuelQueue queue[][]=getFuelQueue();
        Scanner sc=new Scanner(System.in);
        int queue_no;
        int location;
        while (true){
            try {
                System.out.print("Enter queue number:");
                queue_no=sc.nextInt();
                if (queue_no<1 || queue_no>5){
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
        passenger Passenger;
        Passenger=queue[queue_no-1][location-1];
        if (queue[queue_no-1][location-1]!=null){
            System.out.println(Passenger.getFirstName()+" removed.");
            FuelQueue.setFuelStock(Passenger.getLitersRequired());

            queue[queue_no-1][location-1]=null;

            queue=customers_moveFront(queue,queue_no); //moving customers to front
        }else {
            System.out.println("location "+location+" is Empty. pls try again with correct location.");
        }

    }

    //move customer front
    private static FuelQueue[][] customers_moveFront(FuelQueue[][]queue,int queue_no){
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

    //setter for fuel stock
    public static void setFuelStock(int liters) {
        FuelQueue.fuelStock += liters;
    }

    //104 or PCQ: Remove a served customer.
    public static void remove_served_customer(){
        FuelQueue queue[][]=getFuelQueue();
        boolean isNameEqual=false;
        int col;
        int row;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter name: ");
        String customer_name=sc.nextLine();
        try {
            for (row=0;row< queue.length;row++){
                for ( col=0;col< queue[row].length;col++){
                    if (queue[row][col]!=null && ((queue[row][col].getFirstName()).equalsIgnoreCase(customer_name))){
                        System.out.println(queue[row][col].getFirstName()+" removed");
                        queue[row][col]=null;
                        isNameEqual=true;
                        break;
                    }
                }
                if (isNameEqual==true){
                    break;
                }
            }customers_moveFront(queue,row+1);
            int lastVal=queue[row].length-1;
            queue[row][lastVal]=WaitingList.dequeue();
            System.out.println(queue[row][lastVal].getFirstName()+" has been added to Fuel queue "+(row+1)+", from waiting queue");

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Entered name can't be found in fuel queue..");
        }

    }

    //105 or VCS: View Customers Sorted in alphabetical order
    public static void sort_queue(){
        FuelQueue queue[][]=getFuelQueue();
        FuelQueue[][] copyArr=new FuelQueue[5][6]; //copying queue to a new array
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
                        if (copyArr[i][k].getFirstName().compareTo(copyArr[i][k + 1].getFirstName()) >0) {

                            // swapping elements
                            FuelQueue t = copyArr[i][k];
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
                    System.out.print(copyArr[i][j].getFirstName() + ", ");
                }

            System.out.println();
        }
    }

    //106 or SPD: Store Program Data into file.
    public static void store_data_toFile(){
        FuelQueue queue[][]=getFuelQueue();
        try {
            FileWriter data_file= new FileWriter("data.txt");
            for (int row=0;row<queue.length;row++){
                data_file.write("Queue "+(row+1)+"\n");
                for (int col=0;col<queue[row].length;col++){
                    data_file.write("Queue no, "+(row+1)+" location "+(col+1)+":\n");
                    if (queue[row][col]!=null){
                        data_file.write("\tFirst Name: "+queue[row][col].getFirstName()+"\n\tSecond Name: "+queue[row][col].getSecondName()+"\n\tVehicle No: "+queue[row][col].getVehicleNo()+"\n\tLiters requied: "+queue[row][col].getLitersRequired()+"\n");
                    }else {
                        data_file.write("\tEmpty space\n");
                    }
                }
                data_file.write("\n");
            }
            data_file.close();
            System.out.println("wrote to file..");
        }catch (IOException e){
            System.out.println("error occurred..");
        }
    }

    //107 or LPD: Load Program Data from file.
    public static void show_data_fromFile(){
        try {
            File dataFile=new File("data.txt");
            Scanner readFile=new Scanner(dataFile);
            String fileLine;
            while (readFile.hasNext()){
                fileLine=readFile.nextLine();
                System.out.println(fileLine);
            }
        }catch (IOException e){
            System.out.println("Error");
        }
    }

    //for 109 or AFS: Add Fuel Stock.
    public static void add_fuel_stock() {
        Scanner sc = new Scanner(System.in);
        int new_fuel_count;
        while (true) {
            try {
                System.out.print("Enter fuel amount to add: ");
                new_fuel_count = sc.nextInt();
                if (new_fuel_count < 0) {
                    System.out.println("invalid, should be grater than 0.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter a valid amount.");
                sc.nextLine();//clear input
                continue;
            }
        }
        setFuelStock(new_fuel_count);
    }

    //110 or IFQ: display fuel income
    public static void DisplayFuelIncome(){
        for(int i=0;i<FuelIncome.length;i++){
            System.out.println("Fuel queue "+(i+1)+" income: "+FuelIncome[i]);
        }
    }

    //fuel income array Getter -for GUI
    public static int[] getFuelIncome() {
        return FuelIncome;
    }
}
