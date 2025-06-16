package edu.neu.csye7374;

public class GoldLotStrategyFactory implements AbstractStrategyFactory {
    private static GoldLotStrategyFactory instance = null;
    
    private GoldLotStrategyFactory() {
    }

    public static GoldLotStrategyFactory getInstance(){
       if (instance == null){
            synchronized(GoldLotStrategyFactory.class){
                if (instance == null){
                    instance = new GoldLotStrategyFactory();
                }
            }
       }
        return instance;
    }

    @Override
    public RentalPaymentStrategy getObject() {
        return new GoldLotStrategy();
    }
    
}
