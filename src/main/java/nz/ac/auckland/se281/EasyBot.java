package nz.ac.auckland.se281;

public class EasyBot implements Robot {

  private String model = "HAL-9000";

  @Override
  public String getRobotOutput() {
    return Integer.toString(Utils.getRandomNumberRange(0, 5));
  }

  @Override
  public String getModel() {
    return model;
  }
}
