package edu.neu.csye7374;

public class OrangeGeneralPublicStrategyFactory implements AbstractStrategyFactory {
    private static OrangeGeneralPublicStrategyFactory instance = null;
    
    private OrangeGeneralPublicStrategyFactory() {
    }

    public static OrangeGeneralPublicStrategyFactory getInstance(){
       if (instance == null){
            synchronized(OrangeGeneralPublicStrategyFactory.class){
                if (instance == null){
                    instance = new OrangeGeneralPublicStrategyFactory();
                }
            }
       }
        return instance;
    }

    @Override
    public RentalPaymentStrategy getObject() {
        return new OrangeGeneralPublicStrategy();
    }
    
}
