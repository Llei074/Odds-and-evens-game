package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class RobotFactory {

  /**
   * chooseDifficulty method creates an instance of the robot based on the difficulty.
   *
   * @param difficulty the difficulty of the robot
   * @return the robot instance based on the difficulty
   */
  public static Robot chooseDifficulty(Difficulty difficulty) {

    // Creates the robot based on the difficulty
    switch (difficulty) {
      case EASY:
        return new EasyBot();
      case MEDIUM:
        return new MediumBot();
      case HARD:
        return new HardBot();
    }
    return null;
  }
}
