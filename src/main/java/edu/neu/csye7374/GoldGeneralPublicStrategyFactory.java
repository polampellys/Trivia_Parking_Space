package edu.neu.csye7374;

public class GoldGeneralPublicStrategyFactory implements AbstractStrategyFactory {
    private static GoldGeneralPublicStrategyFactory instance = null;
    
    private GoldGeneralPublicStrategyFactory() {
    }

    public static GoldGeneralPublicStrategyFactory getInstance(){
       if (instance == null){
            synchronized(GoldGeneralPublicStrategyFactory.class){
                if (instance == null){
                    instance = new GoldGeneralPublicStrategyFactory();
                }
            }
       }
        return instance;
    }

    @Override
    public RentalPaymentStrategy getObject() {
        return new GoldGeneralPublicStrategy();
    }
    
}
