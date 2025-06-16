package edu.neu.csye7374;

public class ParkingSystemFacade {

    private UserDataLoader userDataLoader;
    private VehicleDataLoader vehicleDataLoader;
    private ParkingInfoLoader parkingInfoLoader;
    private ParkingLotLoader parkingLotLoader;

    public ParkingSystemFacade() {
        userDataLoader = UserDataLoader.getInstance();
        vehicleDataLoader = VehicleDataLoader.getInstance();
        parkingInfoLoader = ParkingInfoLoader.getInstance();
        parkingLotLoader = ParkingLotLoader.getInstance();
        parkingLotLoader.addObserversOnLoad(userDataLoader);
    }


    public boolean checkIfEntityPresent(String key, String mapper){
        if (mapper == "USER") return (userDataLoader.getUserInfo().get(key) != null);
        else if (mapper == "VEHICLE") return (vehicleDataLoader.getVehicleInfo().get(key) != null);
        else System.out.println("Passes Mapper not supported");
        return false;
    }

    public void signUp(String userId, UserType userType, Double balance, ParkingLine parkingLine, int noOfVehicles,
            String[] vehicleNumbers, String[] vehicleNames) throws CloneNotSupportedException{
                 // Validate UserId from existing Hashmap to see if it already exists.
         if (userDataLoader.getUserById(userId) != null) {
            System.out.println("User with Id: " + userId + " already exists!");
            return;
        }
        User user = User.class.cast(User.getInstance().clone());
        user.configureObject(userId, parkingLine, noOfVehicles, userType, balance);
        ParkingLot userParkingLot = parkingLotLoader.getUserById(parkingLine);
        userParkingLot.addObserver(user);
        parkingLotLoader.addParkingLot(userParkingLot);
        userDataLoader.addUser(user);   
        for (int i = 0; i < noOfVehicles; i++) {
            Vehicle vehicle = Vehicle.class.cast(Vehicle.getInstance().clone());
            vehicle.configureObject(userId, vehicleNumbers[i], vehicleNames[i]);
            vehicleDataLoader.addVehicle(vehicle);
        }
    }

    public void parkVehicle(String userId, String vehicleNumber, ParkingLine parkedLine) throws CloneNotSupportedException {
        if (parkingInfoLoader.getParkingInfoByNumber(vehicleNumber) != null) {
            System.out.println("Vehicle is already Parked in the estate! Please try again!");
            return;
        }
        ParkingLot parkedLot = parkingLotLoader.getUserById(parkedLine);
        if (parkedLot.getState() == ParkingLotStatus.FUll){
            ParkingLotState state = new FullState();
            parkedLot.setState(state.parkVehicle(parkedLot, userId, vehicleNumber, parkedLine));
            return;
        }
        ParkingLotState state = new AvailableState(parkingInfoLoader, userDataLoader, vehicleDataLoader);
        ParkingLotStatus status = state.parkVehicle(parkedLot, userId, vehicleNumber, parkedLine);
        parkedLot.setState(status);
        parkingLotLoader.addParkingLot(parkedLot);
    }

    public void exitVehicle(String vehicleNumber){
        ParkingInfo exitingVehicle = parkingInfoLoader.getParkingInfoByNumber(vehicleNumber);
        if (exitingVehicle == null) {
            System.out.println("The entered vehicle is not parked in the estate currently");
            return;
        } else {
            ParkingLot parkedLot = parkingLotLoader.getUserById(exitingVehicle.getParkingLine());
            AvailableState state = new AvailableState(parkingInfoLoader, userDataLoader, vehicleDataLoader);
            ParkingLotStatus status = state.exitVehicle(parkedLot, vehicleNumber);
            parkedLot.setState(status);
        //    parkingLotLoader.put(currentLine, parkedLot);
            parkingLotLoader.addParkingLot(parkedLot);
        }
}
}

