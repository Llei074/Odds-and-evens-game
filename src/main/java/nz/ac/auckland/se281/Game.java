package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {

  private Integer roundNumber;
  private String player;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    player = options[0];
    roundNumber = 1;

    MessageCli.WELCOME_PLAYER.printMessage(player);
  }

  public void play() {
    String input;

    // Start the round 
    MessageCli.START_ROUND.printMessage(roundNumber.toString());

    // Ask for input
    MessageCli.ASK_INPUT.printMessage();
    input = Utils.scanner.nextLine();
    switch (input) {
      case "0":
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
        MessageCli.PRINT_INFO_HAND.printMessage(player, input);
        break;
      default:
        MessageCli.INVALID_INPUT.printMessage();
        break;
    }
  }

  public void endGame() {}

  public void showStats() {}
}
