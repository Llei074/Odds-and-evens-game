package nz.ac.auckland.se281;

public class EasyBot implements Robot {

  @Override
  public String getRobotOutput() {
    return Integer.toString(Utils.getRandomNumberRange(0, 5));
  }

  @Override
  public String getModel() {
    return model;
  }
}
