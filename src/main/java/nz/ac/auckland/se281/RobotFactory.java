package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class RobotFactory {

  public static Robot chooseDifficulty(Difficulty difficulty) {

    switch (difficulty) {
      case EASY:
        return new EasyBot();
      case MEDIUM:
        return new MediumBot();
      case HARD:
         return new HardBot();
    }
    // If possible change later
    return null;
  }
}
