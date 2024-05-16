package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {
  public String getAction() {
    return Integer.toString(Utils.getRandomNumberRange(0, 5));
  }
}