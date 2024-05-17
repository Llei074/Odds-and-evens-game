package nz.ac.auckland.se281;

public class MediumBot implements Robot {
  private Strategy strategy;

  /** Initializes the class with the Random Strategy */
  public MediumBot() {
    this.strategy = new RandomStrategy();
  }

  /**
   * Sets the strategy for the robot
   *
   * @param strategy the desired strategy
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  /** Returns the output of the robot based on the strategy */
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
