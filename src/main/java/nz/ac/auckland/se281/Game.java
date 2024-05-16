package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {

  private int roundNumber;
  private String playerFingers;
  private String robotFingers;

  private String player;
  private Robot robot;
  private Choice choice;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {

    roundNumber = 0;

    player = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(player);

    // Create the robot based on chosen difficulty
    robot = RobotFactory.chooseDifficulty(difficulty);

    this.choice = choice;
  }

  public void play() {
    int sum;
    roundNumber++;
    // Start the round
    MessageCli.START_ROUND.printMessage(Integer.toString(roundNumber));

    // Ask for input and repeat until valid input is given
    playerFingers = askPlayerInput();
    MessageCli.PRINT_INFO_HAND.printMessage(player, playerFingers);

    // Request for robot's input
    robotFingers = robot.getRobotOutput();
    MessageCli.PRINT_INFO_HAND.printMessage(robot.getModel(), robotFingers);

    // Check the outcome of the round
    sum = Integer.parseInt(playerFingers) + Integer.parseInt(robotFingers);
    switch (choice) {
      case EVEN:
        // If sum is even, player wins
        if (Utils.isEven(sum)) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(Integer.toString(sum), "EVEN", player);
        } else {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(
              Integer.toString(sum), "ODD", robot.getModel());
        }
        break;

      case ODD:
        // If sum is odd, player wins
        if (Utils.isOdd(sum)) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(Integer.toString(sum), "ODD", player);
        } else {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(
              Integer.toString(sum), "EVEN", robot.getModel());
        }
        break;
    }
  }

  public void endGame() {}

  public void showStats() {}

  public String askPlayerInput() {
    while (true) {
      MessageCli.ASK_INPUT.printMessage();
      String input = Utils.scanner.nextLine();
      switch (input) {
        case "0":
        case "1":
        case "2":
        case "3":
        case "4":
        case "5":
          return input;
        default:
          MessageCli.INVALID_INPUT.printMessage();
          break;
      }
    }
  }
}
