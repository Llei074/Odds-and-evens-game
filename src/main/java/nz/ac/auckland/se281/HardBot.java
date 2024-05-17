package nz.ac.auckland.se281;

public class HardBot implements Robot{
    private Strategy strategy;

    public HardBot() {
        this.strategy = new RandomStrategy();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
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
