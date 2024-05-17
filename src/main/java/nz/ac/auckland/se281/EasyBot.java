package nz.ac.auckland.se281;

public class EasyBot implements Robot {
  private Strategy strategy;

  /** Initializes the class with the Random Strategy */
  public EasyBot() {
    this.strategy = new RandomStrategy();
  }

  /** Returns the current strategy used by the robot */
  @Override
  public String getRobotOutput() {
    return strategy.getAction();
  }

  /** Returns the model name of the robot */
  @Override
  public String getModel() {
    return model;
  }
}
