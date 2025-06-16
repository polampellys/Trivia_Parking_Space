package edu.neu.csye7374;

public interface ParkingLotState {
    ParkingLotStatus parkVehicle(ParkingLot parkingLot, String userId, String vehicleNumber, ParkingLine parkedLine) throws CloneNotSupportedException;
}