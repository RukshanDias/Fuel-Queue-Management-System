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


    public static void enqueue(String Fname,String Sname, String VehicleNo,int liters) {
        if(isFull()) {
            System.out.println("Queue is full can't insert");
            return;
        }
        rear = (rear + 1) % capacity;
        waiting_list[rear] = new FuelQueue(Fname,Sname,VehicleNo,liters);
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
        return data;
    }


    public static boolean isFull() {
        return size == capacity;
    }

    public static boolean isEmpty() {
        return size == 0;
    }
}
