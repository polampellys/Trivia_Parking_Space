package edu.neu.csye7374;

public class GoldGeneralPublicStrategy implements RentalPaymentStrategy{

    @Override
    public double calculateBillAmount(long timestamp, double serviceFee, ParkingLine currentLine) {
        double bill = serviceFee + 40.0;
        bill += (double) 25 * (System.currentTimeMillis() - timestamp)/(60 * 1000);
        return bill;
    }
    
}
