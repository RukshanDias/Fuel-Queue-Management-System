package com.example.w1912792_task04;
/*
referred from below sources:
https://www.programiz.com/dsa/circular-queue
https://github.com/im-mani-teckieshare/data-structure-algorithm-java/blob/main/data-structure/src/circularqueue/CircularQueue.java
*/

public class WaitingList {
    //class attributes
    private static int capacity=6;
    private static int front = 0;
    private static int rear = -1;
    private static int size = 0;
    private static FuelQueue waiting_list[] = new FuelQueue[capacity];

    //class methods
    public WaitingList(){

    }

    public static void enqueue(String Fname,String Sname, String VehicleNo,int liters) {
        if(isFull()) {
            System.out.println("Waiting Queue is full can't insert");
            return;
        }
        rear = (rear + 1) % capacity;
        waiting_list[rear] = new FuelQueue(Fname,Sname,VehicleNo,liters);//add passenger to waiting list
        System.out.println(Fname+" has been added to waiting queue.");
        size++;
    }

    public static FuelQueue dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        FuelQueue data = waiting_list[front];
        front = (front + 1) % capacity;
        size--;
        return data;//return the first element of waiting queue
    }

    public static boolean isFull() {
        return size == capacity;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    //display waiting queue: for javafx
    public static String DisplayWaitingQueue(){
        String WaitingQueueDetails="";
        for (int i=0;i< waiting_list.length;i++){
            if (waiting_list[i]!=null) {
                WaitingQueueDetails += "\n" + (i + 1) + ")";
                WaitingQueueDetails += ("\n\tFirst name: " + waiting_list[i].getFirstName());
                WaitingQueueDetails += ("\n\tSecond name: " + waiting_list[i].getSecondName());
                WaitingQueueDetails += ("\n\tVehicle No: " + waiting_list[i].getVehicleNo());
                WaitingQueueDetails += ("\n\tLiters required: " + waiting_list[i].getLitersRequired() + "\n");
            }
        }
        return WaitingQueueDetails;
    }

    public static FuelQueue[] getWaiting_list() {
        return waiting_list;
    }
}
