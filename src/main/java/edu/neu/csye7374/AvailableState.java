package edu.neu.csye7374;

public class AvailableState implements ParkingLotState {
    private ParkingInfoLoader parkingInfoLoader;
    private UserDataLoader userDataLoader;
    private VehicleDataLoader vehicleDataLoader;

    public AvailableState(ParkingInfoLoader parkingInfoLoader, UserDataLoader userDataLoader,VehicleDataLoader vehicleDataLoader) {
        this.parkingInfoLoader = parkingInfoLoader;
        this.userDataLoader=userDataLoader;
        this.vehicleDataLoader=vehicleDataLoader;
    }

    @Override
    public ParkingLotStatus parkVehicle(ParkingLot parkedLot, String userId, String vehicleNumber, ParkingLine parkedLine) throws CloneNotSupportedException{
        long timeStamp = System.currentTimeMillis();
        MovingVehicle vehicle;
        if (parkedLine == ParkingLine.ORANGE){
            Vehicle baseVehicle = Vehicle.class.cast(Vehicle.getInstance().clone());
            baseVehicle.configureObject(userId, vehicleNumber, "GENERAL");
            vehicle = new VehicleSafetyCheckDecorator(baseVehicle);
        } else if (parkedLine == ParkingLine.GOLD){
            Vehicle baseVehicle = Vehicle.class.cast(Vehicle.getInstance().clone());
            baseVehicle.configureObject(userId, vehicleNumber, "GENERAL");
            vehicle = new VehicleWashDecorator(baseVehicle);
        } else {
            Vehicle baseVehicle = Vehicle.class.cast(Vehicle.getInstance().clone());
            baseVehicle.configureObject(userId, vehicleNumber, "GENERAL");
            vehicle = baseVehicle;
        }
        ParkingInfo parkedVehicle = new ParkingInfo(userId, vehicleNumber, parkedLine, String.valueOf(timeStamp));
        parkedVehicle.setServiceFee(vehicle.getCost());
        parkingInfoLoader.addParkingInfo(parkedVehicle);
        System.out.println("Vehicle " + vehicleNumber + " successfully parked in parkingLine " + parkedLine);
        parkedLot.setTotalSpaces(parkedLot.getTotalSpaces()-1);
        if (parkedLot.getTotalSpaces() == 0) return ParkingLotStatus.FUll;
        return ParkingLotStatus.AVAILABLE;
    }

    public ParkingLotStatus exitVehicle(ParkingLot parkedLot, String vehicleNumber) {
        ParkingInfo exitingVehicle = parkingInfoLoader.getParkingInfoByNumber(vehicleNumber);
        if (exitingVehicle != null) {
            String userId = exitingVehicle.getUserId();
            double amount = 0.0;
            ParkingLine currentLine = exitingVehicle.getParkingLine();
            if(userDataLoader.getUserById(userId) == null || vehicleDataLoader.getVehicleByNumber(exitingVehicle.getVehicleNumber()) == null) {
                if (currentLine == ParkingLine.ORANGE){
                    exitingVehicle.setBillStrategyAdapter(OrangeGeneralPublicStrategyFactory.getInstance().getObject());
                    amount = exitingVehicle.generateBill();
                } else if(currentLine == ParkingLine.GOLD){
                    exitingVehicle.setBillStrategyAdapter(GoldGeneralPublicStrategyFactory.getInstance().getObject());
                    amount = exitingVehicle.generateBill();
                } else {
                    exitingVehicle.setBillStrategyAdapter(GreenGeneralPublicStrategyFactory.getInstance().getObject());
                    amount = exitingVehicle.generateBill();
                }
            }  else {
                // Authenticated registered strategy
                User user = userDataLoader.getUserById(userId);
                ParkingLine registeredLine = user.getParkingLine();
                if (registeredLine == ParkingLine.ORANGE){
                    exitingVehicle.setBillStrategyAdapter(OrangeLotStrategyFactory.getInstance().getObject());
                    amount = exitingVehicle.generateBill();
                } else if (registeredLine == ParkingLine.GOLD){
                    exitingVehicle.setBillStrategyAdapter(GoldLotStrategyFactory.getInstance().getObject());
                    amount = exitingVehicle.generateBill();
                } else {
                    exitingVehicle.setBillStrategyAdapter(GreenLotStrategyFactory.getInstance().getObject());
                    amount = exitingVehicle.generateBill();
                }
                user.deductBalance(amount);
                userDataLoader.addUser(user);
                // userInfo.put(userId, user);
            }
            // saveUserDatatoCSV(userInfo, false);
            parkingInfoLoader.removeParkingInfo(exitingVehicle.getVehicleNumber());

            // Increase available spaces and notify observers
            parkedLot.setTotalSpaces(parkedLot.getTotalSpaces() + 1);
            System.out.println("Bill Amount deducted: $" + amount);
            System.out.println("Vehicle " + vehicleNumber + " successfully exited!");
        }
        if (parkedLot.getTotalSpaces() == 0) return ParkingLotStatus.FUll;
        return ParkingLotStatus.AVAILABLE;
    }
}

