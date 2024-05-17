package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {

  /**
   * getAction returns a random number between 0 and 5 inclusive.
   *
   * @return a random number between 0 and 5
   */
  public String getAction() {
    return Integer.toString(Utils.getRandomNumberRange(0, 5));
  }
}