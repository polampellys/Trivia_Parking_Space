package edu.neu.csye7374;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

//Singleton
class ParkingInfoLoader {
    private static ParkingInfoLoader instance = new ParkingInfoLoader();
    private HashMap<String, ParkingInfo> parkingInfo;

    private ParkingInfoLoader() {
        // Load user data from file
        parkingInfo = loadParkingInfoData();
    }

    public static ParkingInfoLoader getInstance() {
        return instance;
    }
    public HashMap<String, ParkingInfo> getUserInfo() {
        return parkingInfo;
    }

    public ParkingInfo getParkingInfoByNumber(String userId) {
        return parkingInfo.get(userId);
    }
    public void addParkingInfo(ParkingInfo user) {
        parkingInfo.put(user.getVehicleNumber(), user);
        saveParkingInfoData();
    }

    public void removeParkingInfo(String vehicleNumber){
        parkingInfo.remove(vehicleNumber);
        saveParkingInfoData();
    }

    // Other methods to interact with user data

    private HashMap<String, ParkingInfo> loadParkingInfoData() {
        HashMap<String, ParkingInfo> parkedVehiclesMap = new HashMap<>();
        final String PARKING_CSV_FILENAME = "PARKED_VEHICLES.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(PARKING_CSV_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String userId = parts[0];
                    String vehicleNumber = parts[1];
                    ParkingLine lineType = ParkingLine.valueOf(parts[2]); 
                    String timestamp = parts[3];
                    double serviceFee = Double.parseDouble(parts[4]);
                   ParkingInfo parkedVehicle = new ParkingInfo(userId, vehicleNumber, lineType, timestamp);
                   parkedVehicle.setServiceFee(serviceFee);
                   parkedVehiclesMap.put(vehicleNumber, parkedVehicle);
                   System.out.println(parkedVehicle);   
                }
                }
            }
         catch (IOException e) {
            System.out.println();
        }
        return parkedVehiclesMap;
    }

    private void saveParkingInfoData() {
        final String PARKING_CSV_FILENAME = "PARKED_VEHICLES.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(PARKING_CSV_FILENAME, false))) {
            for (ParkingInfo parkingInfo : parkingInfo.values()) {
                writer.println(parkingInfo.getUserId() + "," +
                               parkingInfo.getVehicleNumber() + "," +
                               parkingInfo.getParkingLine() + "," +
                               parkingInfo.getTimeStamp() + "," +
                               parkingInfo.getServiceFee());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
