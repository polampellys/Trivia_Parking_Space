package edu.neu.csye7374;

public class GoldLotStrategy implements RentalPaymentStrategy {

    @Override
    public double calculateBillAmount(long timestamp, double serviceFee, ParkingLine currentLine) {
        double bill = serviceFee;
        if (currentLine == ParkingLine.ORANGE){
            bill += (double) 60 * (System.currentTimeMillis() - timestamp)/(60 * 1000);
        } else {
            bill += (double) 25 * (System.currentTimeMillis() - timestamp)/(60 * 1000);   
        }
        return bill;
    }
    
}
