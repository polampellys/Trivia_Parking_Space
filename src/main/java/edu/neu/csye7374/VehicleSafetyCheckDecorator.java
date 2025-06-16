package edu.neu.csye7374;

public class VehicleSafetyCheckDecorator implements MovingVehicle {

    private MovingVehicle vehicle;
    
    public VehicleSafetyCheckDecorator(MovingVehicle vehicle){
        this.vehicle = vehicle;
    }
    @Override
    public double getCost() {
        return 25 + vehicle.getCost();
    }
    
}
