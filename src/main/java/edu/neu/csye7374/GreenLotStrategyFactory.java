package edu.neu.csye7374;

public class GreenLotStrategyFactory implements AbstractStrategyFactory {
    private static GreenLotStrategyFactory instance = null;
    
    private GreenLotStrategyFactory() {
    }

    public static GreenLotStrategyFactory getInstance(){
       if (instance == null){
            synchronized(GreenLotStrategyFactory.class){
                if (instance == null){
                    instance = new GreenLotStrategyFactory();
                }
            }
       }
        return instance;
    }

    @Override
    public RentalPaymentStrategy getObject() {
        return new GreenLotStrategy();
    }
    
}
