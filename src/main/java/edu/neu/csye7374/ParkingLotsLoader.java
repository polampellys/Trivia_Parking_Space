package edu.neu.csye7374;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

//Singleton
class ParkingLotLoader {
    private static ParkingLotLoader instance = new ParkingLotLoader();
    private HashMap<ParkingLine, ParkingLot> parkingLotInfo;

    private ParkingLotLoader () {
        // Load user data from file
        parkingLotInfo = loadUserData();
    }

    public static ParkingLotLoader  getInstance() {
        return instance;
    }
    public HashMap<ParkingLine, ParkingLot> getUserInfo() {
        return parkingLotInfo;
    }

    public ParkingLot getUserById(ParkingLine userId) {
        return parkingLotInfo.get(userId);
    }
    public void addParkingLot(ParkingLot user) {
       parkingLotInfo.put(user.getLotName(), user);
        saveUserData();
    }

    public void addObserversOnLoad(UserDataLoader loader){
        for (User user: loader.getUserInfo().values()){
            if (user.getParkingLine() == ParkingLine.GOLD){
                ParkingLot lot = parkingLotInfo.get(ParkingLine.GOLD);
                lot.addObserver(user);
            } else if (user.getParkingLine() == ParkingLine.GREEN){
                ParkingLot lot = parkingLotInfo.get(ParkingLine.GREEN);
                lot.addObserver(user);
            } else {
                ParkingLot lot = parkingLotInfo.get(ParkingLine.ORANGE);
                lot.addObserver(user);
            }
        }
    }

    // Other methods to interact with user data

    private HashMap<ParkingLine, ParkingLot> loadUserData() {
        HashMap<ParkingLine, ParkingLot> parkingLots = new HashMap<>();
         final String PARKING_LOTS_CSV_FILENAME = "PARKING_LOTS.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(PARKING_LOTS_CSV_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    ParkingLine lotName = ParkingLine.valueOf(parts[0]);
                    int totalSpaces = Integer.parseInt(parts[1]);
                    
                    ParkingLot parkingLot = new ParkingLot(lotName, totalSpaces);
                    if (totalSpaces == 0) parkingLot.setState(ParkingLotStatus.FUll);
                   parkingLots.put(lotName, parkingLot);
                   System.out.println(parkingLot);   
                }
                }
            }
         catch (IOException e) {
            ParkingLot greenLot = new ParkingLot(ParkingLine.GREEN, 150);
            ParkingLot goldLot = new ParkingLot(ParkingLine.GOLD, 100);
            ParkingLot orangeLot = new ParkingLot(ParkingLine.ORANGE, 40);
            parkingLots.put(greenLot.getLotName(), greenLot);
            parkingLots.put(goldLot.getLotName(), goldLot);
            parkingLots.put(orangeLot.getLotName(), orangeLot);
        }
        return parkingLots;
    }

    

    private void saveUserData() {
        final String PARKING_LOTS_CSV_FILENAME = "PARKING_LOTS.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(PARKING_LOTS_CSV_FILENAME, false))) {
            for (ParkingLot parkingLot : parkingLotInfo.values()) {
                writer.println(parkingLot.getLotName() + "," +
                               parkingLot.getTotalSpaces());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
