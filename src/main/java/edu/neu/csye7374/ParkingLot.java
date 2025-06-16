package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

enum ParkingLotStatus {
    FUll, AVAILABLE
}

// ParkingLot class and related
class ParkingLot {
    private ParkingLine lotName;
    private int totalSpaces;
    private List<Observer> observers = new ArrayList<>();
    private ParkingLotStatus state;

    public void setState(ParkingLotStatus state){
        this.state = state;
    }

    public ParkingLotStatus getState(){
        return this.state;
    }
    
    public void addObserver(User observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }

    public ParkingLine getLotName() {
        return lotName;
    }

    public void setLotName(ParkingLine lotName) {
        this.lotName = lotName;
    }

    public int getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
        notifyObservers();
    }


    @Override
    public String toString() {
        return "ParkingLot [lotName=" + lotName + ", totalSpaces=" + totalSpaces + ", state=" + state + "]";
    }

    public ParkingLot(ParkingLine lotName, int totalSpaces) {
        this.lotName = lotName;
        this.totalSpaces = totalSpaces;
        this.state = ParkingLotStatus.AVAILABLE;
    }

    
    
    
}
