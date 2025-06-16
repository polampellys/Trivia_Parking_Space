package edu.neu.csye7374;

enum ParkingLine {
    GOLD, ORANGE, GREEN
}

enum UserType {
    STUDENT, FACULTY
}

// User class and its subclasses
class User implements Cloneable, Observer {
    private String userId;
    private int totalVehicles;
    private UserType userType;
    private ParkingLine parkingLine;
    private double balance;

    private static User instance = new User();
    
    private User() {
    }

    public static User getInstance(){
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(int totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ParkingLine getParkingLine() {
        return parkingLine;
    }

    public void setParkingLine(ParkingLine parkingline) {
        this.parkingLine = parkingline;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void configureObject(String userId, ParkingLine parkingLine, int totalVehicles, UserType userType, double amount) {
        this.userId = userId;
        this.totalVehicles = totalVehicles;
        this.userType = userType;
        this.balance = amount;
        this.parkingLine = parkingLine;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", totalVehicles=" + totalVehicles + ", userType=" + userType
                + ", parkingLine=" + parkingLine + ", balance=" + balance + "]";
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void notify(ParkingLot parkingLot) {
        System.out.println("Hello " + getUserId() + "! The available parking spaces in " +
                           parkingLot.getLotName() + " lot have changed to " + parkingLot.getTotalSpaces());
    }
   
}
