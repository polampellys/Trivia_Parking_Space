package edu.neu.csye7374;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

//Singleton
class UserDataLoader {
    private static UserDataLoader instance = new UserDataLoader();
    private HashMap<String, User> userInfo;

    private UserDataLoader() {
        // Load user data from file
        try {
            userInfo = loadUserData();
        } catch (CloneNotSupportedException ex){
            ex.getMessage();
        }
    }

    public static UserDataLoader getInstance() {
        return instance;
    }
    public HashMap<String, User> getUserInfo() {
        return userInfo;
    }

    public User getUserById(String userId) {
        return userInfo.get(userId);
    }
    public void addUser(User user) {
        userInfo.put(user.getUserId(), user);
        saveUserData();
    }

    // Other methods to interact with user data

    private HashMap<String, User> loadUserData() throws CloneNotSupportedException{
        HashMap<String, User> usersMap = new HashMap<>();
        final String USER_FILE_PATH = "REGISTERED_USERS.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String userId = parts[0];
                    UserType userType = UserType.valueOf(parts[1]);
                    double balance = Double.parseDouble(parts[2]);
                    ParkingLine parkingLine = ParkingLine.valueOf(parts[3]);
                    int totalVehicles = Integer.parseInt(parts[4]);
                    User user = User.class.cast(User.getInstance().clone());
                    user.configureObject(userId, parkingLine, totalVehicles, userType, balance);
                   usersMap.put(userId, user);
                   System.out.println(user);   
                }
                }
            }
         catch (IOException e) {
            System.out.println();
        }
        return usersMap;
    }

    private void saveUserData() {
        final String USER_FILE_PATH = "REGISTERED_USERS.csv";
        
        try (FileOutputStream fos = new FileOutputStream(USER_FILE_PATH, false);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw)) {
            for (Map.Entry<String, User> user: userInfo.entrySet()){
            bw.write(user.getKey() + "," + user.getValue().getUserType() + "," + user.getValue().getBalance() + "," + user.getValue().getParkingLine() + "," + user.getValue().getTotalVehicles());
            bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("User data saved successfully.");
    }
}
