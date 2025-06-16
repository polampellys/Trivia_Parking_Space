package edu.neu.csye7374;

public class VehicleWashDecorator implements MovingVehicle{

    private MovingVehicle vehicle;
    
    public VehicleWashDecorator(MovingVehicle vehicle){
        this.vehicle = vehicle;
    }
    @Override
    public double getCost() {
        return 15 + vehicle.getCost();
    }
    
}
