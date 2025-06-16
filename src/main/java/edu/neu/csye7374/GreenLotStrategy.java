package edu.neu.csye7374;

public class GreenLotStrategy implements RentalPaymentStrategy {

    @Override
    public double calculateBillAmount(long timestamp, double serviceFee, ParkingLine currentLine) {
        double bill = serviceFee;
        if (currentLine == ParkingLine.ORANGE){
            bill += (double) 70 * (System.currentTimeMillis() - timestamp)/(60 * 1000);
        } else if(currentLine == ParkingLine.GOLD) {
            bill += (double) 35 * (System.currentTimeMillis() - timestamp)/(60 * 1000);
        } else {
            bill += (double) 15 * (System.currentTimeMillis() - timestamp)/(60 * 1000);   
        }
        return bill;
    }
    
}
