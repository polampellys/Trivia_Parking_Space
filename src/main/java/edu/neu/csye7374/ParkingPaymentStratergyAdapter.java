package edu.neu.csye7374;

public class ParkingPaymentStratergyAdapter implements PaymentStrategyAdapter {
    private RentalPaymentStrategy strategy;

    public  ParkingPaymentStratergyAdapter(RentalPaymentStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double calculateBillAmount(ParkingInfo parkingInfo) {
        return strategy.calculateBillAmount(Long.parseLong(parkingInfo.getTimeStamp()), parkingInfo.getServiceFee(), parkingInfo.getParkingLine());
    }
}
