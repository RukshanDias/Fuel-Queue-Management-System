package com.example.w1912792_task04;

public class passenger {
    //class attributes
    private String FirstName;
    private String SecondName;
    private String VehicleNo;
    private int LitersRequired;
    private static int passengerCount=0;

    //class methods
    public passenger(String fName, String sName, String vehicleNo, int liters){
        this.FirstName=fName;
        this.SecondName=sName;
        this.VehicleNo =vehicleNo;
        this.LitersRequired=liters;
        passengerCount++;
    }

    //getters
    public String getFirstName() {
        return FirstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public int getLitersRequired() {
        return LitersRequired;
    }

}
