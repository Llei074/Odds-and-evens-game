package nz.ac.auckland.se281;

public class EasyBot implements Robot {
  Strategy strategy = new RandomStrategy();

  @Override
  public String getRobotOutput() {
    return strategy.getAction();
  }

  @Override
  public String getModel() {
    return model;
  }
}
