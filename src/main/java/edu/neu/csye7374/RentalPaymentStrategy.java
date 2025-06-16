package edu.neu.csye7374;

public interface RentalPaymentStrategy {
    public double calculateBillAmount(long timestamp, double serviceFee, ParkingLine currentLine);
}
