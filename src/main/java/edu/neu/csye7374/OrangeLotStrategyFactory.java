package edu.neu.csye7374;

public class OrangeLotStrategyFactory implements AbstractStrategyFactory {
    private static OrangeLotStrategyFactory instance = null;
    
    private OrangeLotStrategyFactory() {
    }

    public static OrangeLotStrategyFactory getInstance(){
       if (instance == null){
            synchronized(OrangeLotStrategyFactory.class){
                if (instance == null){
                    instance = new OrangeLotStrategyFactory();
                }
            }
       }
        return instance;
    }

    @Override
    public RentalPaymentStrategy getObject() {
        return new OrangeLotStrategy();
    }
    
}
