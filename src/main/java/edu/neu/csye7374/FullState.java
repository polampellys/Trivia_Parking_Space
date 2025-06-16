package edu.neu.csye7374;

public class FullState implements ParkingLotState {

    @Override
    public ParkingLotStatus parkVehicle(ParkingLot parkingLot, String userId, String vehicleNumber, ParkingLine parkedLine) {
        System.out.println("Hello! " + userId + ", vehicle " + vehicleNumber + " cannot be parked at this time as selected " + parkingLot.getLotName() + " Lot is already full! Please try again.");
        return ParkingLotStatus.FUll;
    }
}