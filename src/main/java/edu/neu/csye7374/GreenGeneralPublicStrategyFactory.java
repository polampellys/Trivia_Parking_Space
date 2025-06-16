package edu.neu.csye7374;

public class GreenGeneralPublicStrategyFactory implements AbstractStrategyFactory {
    private static GreenGeneralPublicStrategyFactory instance = null;
    
    private GreenGeneralPublicStrategyFactory() {
    }

    public static GreenGeneralPublicStrategyFactory getInstance(){
       if (instance == null){
            synchronized(GreenGeneralPublicStrategyFactory.class){
                if (instance == null){
                    instance = new GreenGeneralPublicStrategyFactory();
                }
            }
       }
        return instance;
    }

    @Override
    public RentalPaymentStrategy getObject() {
        return new GreenGeneralPublicStrategy();
    }
    
}
