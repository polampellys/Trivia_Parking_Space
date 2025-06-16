package edu.neu.csye7374;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class VehicleDataLoader {
    private static VehicleDataLoader instance = new VehicleDataLoader();
    private HashMap<String, Vehicle> vehicleInfo;

    private VehicleDataLoader() {
        try {
        vehicleInfo = loadVehicleData();
        } catch (CloneNotSupportedException ex){
            ex.getMessage();
        }
    }

    public HashMap<String, Vehicle> getVehicleInfo() {
        return vehicleInfo;
    }
    public static VehicleDataLoader getInstance() {
        return instance;
    }

    public String getVehicleByNumber(String vehicleNumber) {
        Vehicle vehicle = vehicleInfo.get(vehicleNumber);
        if (vehicle != null) {
            return vehicle.getVehicleNumber();
        }
        return null;
    }
    public void addVehicle(Vehicle user) {
        vehicleInfo.put(user.getVehicleNumber(), user);
        saveVehicleData();
    }
    private HashMap<String, Vehicle> loadVehicleData() throws CloneNotSupportedException{
        // Implement logic to load vehicle data from a file or database
        // For now, I'll provide a simple example using a file

        HashMap<String, Vehicle> vehicleData = new HashMap<>();
        final String VEHICLES_FILE_PATH = "REGISTERED_VEHICLES.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String userId = parts[0];
                    String vehicleNumber = parts[1];
                    String vehicleName = parts[2];
                    Vehicle vehicle = Vehicle.class.cast(Vehicle.getInstance().clone());
                    vehicle.configureObject(userId, vehicleNumber, vehicleName);
                    vehicleData.put(vehicleNumber, vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println();
        }

        return vehicleData;
    }

    private void saveVehicleData() {
        final String VEHICLES_FILE_PATH = "REGISTERED_VEHICLES.csv";
        try (FileOutputStream fos = new FileOutputStream(VEHICLES_FILE_PATH, false);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw)) {
            for (Map.Entry<String, Vehicle> user: vehicleInfo.entrySet()){
            bw.write(user.getValue().getUserId() + "," + user.getValue().getVehicleNumber() + "," + user.getValue().getVehicleName());
            bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}