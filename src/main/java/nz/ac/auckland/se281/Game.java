package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
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
  private List<Choice> history;
  private List<String> robotWins;
  private boolean gameRunning = false;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {

    // Set the game to running
    gameRunning = true;

    // Initialize the game
    history = new ArrayList<>();
    robotWins = new ArrayList<>();
    roundNumber = 0;

    // Welcome the player
    player = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(player);

    // Create the robot based on chosen difficulty
    robot = RobotFactory.chooseDifficulty(difficulty);

    // Save the determining factor for the player to win
    this.choice = choice;
  }

  public void play() {

    if (!gameRunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    int sumOfValues;

    roundNumber++;
    // Start the round
    MessageCli.START_ROUND.printMessage(Integer.toString(roundNumber));

    // Ask for input and repeat until valid input is given
    playerFingers = askPlayerInput();
    MessageCli.PRINT_INFO_HAND.printMessage(player, playerFingers);

    // Check difficulty of robot and change strategy if needed
    if (robot instanceof MediumBot && roundNumber == 4) {

      // For medium difficulty we change the strategy to TopStrategy after 3 rounds
      ((MediumBot) robot).setStrategy(new TopStrategy(history, choice));

    } else if (robot instanceof HardBot && roundNumber > 3) {

      // For hard difficulty we change the strategy based on the outcome of the previous round
      if (robotWins.get(robotWins.size() - 1).equals("Lose")) {
        // Check what strategy the robot is using and change it to the opposite
        if (((HardBot) robot).getStrategy() instanceof TopStrategy) {
          ((HardBot) robot).setStrategy(new RandomStrategy());
        } else if (((HardBot) robot).getStrategy() instanceof RandomStrategy) {
          ((HardBot) robot).setStrategy(new TopStrategy(history, choice));
        }
      }
    }

    // Request for robot's input
    robotFingers = robot.getRobotOutput();
    MessageCli.PRINT_INFO_HAND.printMessage(robot.getModel(), robotFingers);

    // Save the player choice for TOP strategy
    if (Utils.isEven(Integer.parseInt(playerFingers))) {
      history.add(Choice.EVEN);
    } else {
      history.add(Choice.ODD);
    }

    // Check the outcome of the round
    sumOfValues = Integer.parseInt(playerFingers) + Integer.parseInt(robotFingers);
    robotWins.add(roundOutcome(sumOfValues));
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

  public String roundOutcome(int sumOfValues) {
    switch (choice) {
      case EVEN:
        // If sum is even, player wins
        if (Utils.isEven(sumOfValues)) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(
              Integer.toString(sumOfValues), "EVEN", player);
          return "Lose";
        } else {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(
              Integer.toString(sumOfValues), "ODD", robot.getModel());
          return "win";
        }

      case ODD:
        // If sum is odd, player wins
        if (Utils.isOdd(sumOfValues)) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(Integer.toString(sumOfValues), "ODD", player);
          return "Lose";
        } else {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage(
              Integer.toString(sumOfValues), "EVEN", robot.getModel());
          return "win";
        }
      default:
        // Inaccessable code
        return "Draw";
    }
  }
}
