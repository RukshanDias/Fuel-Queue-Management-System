public class passenger {
    //class attributes
    private String FirstName;
    private String SecondName;
    private String VehicleNo;
    private int LitersRequired;

    //class methods
    public passenger(String fName, String sName, String vehicleNo, int liters){
        this.FirstName=fName;
        this.SecondName=sName;
        this.VehicleNo =vehicleNo;
        this.LitersRequired=liters;
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
