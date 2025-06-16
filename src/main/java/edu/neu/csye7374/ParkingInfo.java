package edu.neu.csye7374;



public class ParkingInfo {
    private String userId;
    private PaymentStrategyAdapter billStrategyAdapter;


    @Override
    public String toString() {
        return "ParkingInfo [userId=" + userId + ", vehicleNumber=" + vehicleNumber + ", parkingLine=" + parkingLine 
        + ", timeStamp=" + timeStamp +  ", serviceFee=" + serviceFee + "]";
    }

    private String vehicleNumber;
    private ParkingLine parkingLine;
    private String timeStamp;
    private double serviceFee;

    
    public String getUserId() {
        return userId;
    }

    public void setBillStrategyAdapter(RentalPaymentStrategy strategy) {
        this.billStrategyAdapter = new ParkingPaymentStratergyAdapter (strategy);
    }

    public double generateBill() {
        if (billStrategyAdapter == null) {
            throw new IllegalStateException("Bill strategy adapter is not set.");
        }
        return this.billStrategyAdapter.calculateBillAmount(this);
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public ParkingLine getParkingLine() {
        return parkingLine;
    }

    public void setParkingLine(ParkingLine parkingLine) {
        this.parkingLine = parkingLine;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    

    public ParkingInfo(String userId, String vehicleNumber, ParkingLine parkingLine, String timeStamp) {
        this.userId = userId;
        this.vehicleNumber = vehicleNumber;
        this.parkingLine = parkingLine;
        this.timeStamp = timeStamp;
        this.serviceFee = 0.0;
        this.billStrategyAdapter = new ParkingPaymentStratergyAdapter(new GreenGeneralPublicStrategy());
    }

}

