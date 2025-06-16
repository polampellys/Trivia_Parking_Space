package edu.neu.csye7374;

public class GreenGeneralPublicStrategy implements RentalPaymentStrategy{

    @Override
    public double calculateBillAmount(long timestamp, double serviceFee, ParkingLine currentLine) {
        double bill = serviceFee + 40.0;
        bill += (double) 15 * (System.currentTimeMillis() - timestamp)/(60 * 1000);
        return bill;
    }
    
}
