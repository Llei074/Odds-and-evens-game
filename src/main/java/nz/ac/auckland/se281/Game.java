package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {

  private Integer roundNumber;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    roundNumber = 1;
  }

  public void play() {


    MessageCli.START_ROUND.printMessage(roundNumber.toString());
    MessageCli.ASK_INPUT.printMessage();
    String input = Utils.scanner.nextLine();
    switch (input) {
      case "0":
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
        break;
      default:
        MessageCli.INVALID_INPUT.printMessage();
        break;
    }
  }

  public void endGame() {}

  public void showStats() {}
}
