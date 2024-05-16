package nz.ac.auckland.se281;

public class MediumBot implements Robot{
    Strategy strategy;

    public MediumBot(){
        this.strategy = new RandomStrategy();
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    @Override
    public String getRobotOutput() {
        return strategy.getAction();
    }

    @Override
    public String getModel() {
        return model;
    }
}
