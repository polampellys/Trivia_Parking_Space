package edu.neu.csye7374;

import java.util.Scanner;

public class TriviaRentalManager {
    private static Scanner sc = new Scanner(System.in);

    public static void startApplication(){
        ParkingSystemFacade facade = new ParkingSystemFacade();
        initiateProcess(facade);
    }
    
    public static void initiateProcess(ParkingSystemFacade facade) {
        System.out.println("Please select an option from below");
        System.out.println("1. Sign Up        2. Proceed to Parking Lot");
        int entrySelected = sc.nextInt();
        if (entrySelected == 1) {
            proceedToSignUp(facade);
        } else if (entrySelected == 2) {
            proceedToParkingLot(facade);
        } else {
            System.out.println("Unsupported operation!");
            initiateProcess(facade);
        }
    }

    public static void proceedToSignUp(ParkingSystemFacade facade) {

        System.out.println("Enter a Unique UserId:");
        String userId = sc.next();
        if(userId != null && facade.checkIfEntityPresent(userId, "USER")) {
            System.out.println("User " + userId + " is already registered. Please try a Unique Id!");
            initiateProcess(facade);
            return;
        }
        System.out.println("Select User Type:");
        System.out.println("1. STUDENT          2. FACULTY");
        UserType userType = sc.nextInt() == 1 ? UserType.STUDENT : UserType.FACULTY;
        System.out.println("Enter the Wallet Balance ($) to be stored:");
        Double balance = sc.nextDouble();
        System.out.println("Select one of the ParkingLine below:");
        System.out.println();
        System.out.println("1. Green       2. Gold         3. Orange");
        int type = sc.nextInt();
        ParkingLine parkingLine = type == 1 ? ParkingLine.GREEN : (type == 2 ? ParkingLine.GOLD : ParkingLine.ORANGE);
        System.out.println("Enter the number of vehicles to be registered:");
        int noOfVehicles = sc.nextInt();
        String[] vehicleNumbers = new String[noOfVehicles];
        String[] vehicleNames = new String[noOfVehicles];
        for (int i = 0; i < noOfVehicles; i++) {
            System.out.println("Enter #" + (i + 1) + " Vehicle Registered Number:");
            String number = sc.next();
            if (number != null && facade.checkIfEntityPresent(number, "VEHICLE")){
                System.out.println("Vehicle " + number + " is already registered. Please try a Unique Number!");
                initiateProcess(facade);
                return;
            }
            vehicleNumbers[i] = number;
            System.out.println("Enter #" + (i + 1) + " Vehicle Name:");
            vehicleNames[i] = sc.next();
        }
        try {
            facade.signUp(userId, userType, balance, parkingLine, noOfVehicles, vehicleNumbers, vehicleNames);
        } catch (CloneNotSupportedException ex) {
            ex.getMessage();
        }
        System.out.println("User with Id: " + userId + " registered successfully!");
        proceedToParkingLot(facade);
    }

    public static void proceedToParkingLot(ParkingSystemFacade facade) {

        System.out.println("Pick anyone of the options below:");
        System.out.println("1. Park Vehicle         2. Exit Vehicle from Parking Space");
        int option = sc.nextInt();
        if (option == 1) {
            // Display available spaces in each lot
            // displayAvailablityStats();
            parkVehicle(facade);
        } else if (option == 2) {
            exitVehicle(facade);
        } else {
            System.out.println("Unsupported Operation!");
        }
    }

    public static void parkVehicle(ParkingSystemFacade facade) {
        ParkingLotLoader parkingLotLoader = ParkingLotLoader.getInstance();
        for (ParkingLot lot : parkingLotLoader.getUserInfo().values()) {
            System.out.println(lot.toString());
        }
        System.out.println("Select an option from below:");
        System.out.println("1. Park with UserId           2. Proceed without UserId");
        int opted = sc.nextInt();
        if (opted == 1) {
            System.out.println("Enter the Id:");
            String userId = sc.next();
            if(userId != null && !facade.checkIfEntityPresent(userId, "USER")) {
                System.out.println("User " + userId + " is not registered. Please try again!");
                parkVehicle(facade);
                return;
            }
            proceedToPark(userId, facade);
        } else {
            proceedToPark("GENERAL", facade);
        }
    }

    public static void exitVehicle(ParkingSystemFacade facade) {
        // check the parked state Hashmap for the vehicle
        // generateBill();
        System.out.println("Enter the Registered Vehicle Number:");
        String vehicleNumber = sc.next();
        facade.exitVehicle(vehicleNumber);
        initiateProcess(facade);
    }

    public static void proceedToPark(String userId, ParkingSystemFacade facade) {
        System.out.println("Enter Vehicle Number:");
        String vehicleNumber = sc.next();

        System.out.println("Enter about to Park Line: 1. GOLD, 2. GREEN, 3. ORANGE");
        int parkedLane = sc.nextInt();
        ParkingLine parkedLine = parkedLane == 1 ? ParkingLine.GOLD
                : (parkedLane == 2 ? ParkingLine.GREEN : ParkingLine.ORANGE);
        try {
            facade.parkVehicle(userId, vehicleNumber, parkedLine);
        } catch (CloneNotSupportedException ex) {
            ex.getMessage();
        }
        initiateProcess(facade);
    }

}
