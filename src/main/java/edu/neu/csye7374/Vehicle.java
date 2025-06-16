package edu.neu.csye7374;

// Vehicle class
class Vehicle implements MovingVehicle, Cloneable{
    private String vehicleNumber;
    private String userId;
    private String vehicleName;


    private static Vehicle instance = new Vehicle();
    
    private Vehicle() {
    }

    public static Vehicle getInstance(){
        return instance;
    }

    public void configureObject(String userId, String vehicleNum, String vehicleName){
        this.userId = userId;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNum;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
    @Override
    public String toString() {
        return "Vehicle [vehicleNumber=" + vehicleNumber + ", userId=" + userId + ", vehicleName=" + vehicleName + "]";
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getVehicleName() {
        return vehicleName;
    }
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    public double getCost() {
        return 0.0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
      
}
