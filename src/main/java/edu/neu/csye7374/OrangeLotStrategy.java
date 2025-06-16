package edu.neu.csye7374;

public class OrangeLotStrategy implements RentalPaymentStrategy {

    @Override
    public double calculateBillAmount(long timestamp, double serviceFee, ParkingLine currentLine) {
        double bill = serviceFee;
        bill += (double) 50 * (System.currentTimeMillis() - timestamp)/(60 * 1000);
        return bill;
    }
    
}
