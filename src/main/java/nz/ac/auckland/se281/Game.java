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
    Boolean loop = true;

    // Start the round
    MessageCli.START_ROUND.printMessage(roundNumber.toString());

    // Ask for input and repeat until valid input is given
    while (loop) {
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
          roundNumber++;
          loop = false;
          break;
        default:
          MessageCli.INVALID_INPUT.printMessage();
          break;
      }
    }
  }

  public void endGame() {}

  public void showStats() {}
}
