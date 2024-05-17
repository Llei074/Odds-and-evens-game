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

  /**
   * Starts a new game with the user inputted options. If a game is already running, it will discard
   * the current game and start a new one.
   *
   * @param difficulty The difficulty setting of the robot
   * @param choice The choice for the player to win
   * @param options The name of the player
   */
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

  /** Plays a round of the game. */
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

  /** Ends the game and prints the results of the game. */
  public void endGame() {
    // Check if the game is running
    if (!gameRunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // End the game
    gameRunning = false;
    printPlayerWins();
  }

  /** Prints the current stats in an active game. */
  public void showStats() {
    if (!gameRunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    printPlayerWins();
  }

  /**
   * Asks the player for their round input and returns only when a valid input is given.
   *
   * @return the player's input between 0 and 5 inclusive
   */
  public String askPlayerInput() {
    while (true) {
      MessageCli.ASK_INPUT.printMessage();
      String input = Utils.scanner.nextLine();
      // Check if the input is between 0 and 5 inclusive
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

  /**
   * Determines the outcome of the round based on the player's choice.
   *
   * @param sumOfValues the sum of the player and robot's inputs
   * @return the outcome of the round
   */
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

  /**
   * Prints the wins and losses of each player and the game ending promt if the current game has
   * finished.
   */
  public void printPlayerWins() {

    // Recored number of robot wins
    int robotWins = 0;
    for (String outcome : this.robotWins) {
      if (outcome.equals("win")) {
        robotWins++;
      }
    }

    // Player wins can be calculated by subtracting robot wins from the total number of rounds
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        player, Integer.toString(roundNumber - robotWins), Integer.toString(robotWins));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        robot.getModel(), Integer.toString(robotWins), Integer.toString(roundNumber - robotWins));

    // If the game has ended, print the end game message
    if (!gameRunning) {
      if (robotWins > roundNumber - robotWins) {
        MessageCli.PRINT_END_GAME.printMessage(robot.getModel());
      } else if (robotWins < roundNumber - robotWins) {
        MessageCli.PRINT_END_GAME.printMessage(player);
      } else {
        MessageCli.PRINT_END_GAME_TIE.printMessage("Draw");
      }
    }
  }
}
