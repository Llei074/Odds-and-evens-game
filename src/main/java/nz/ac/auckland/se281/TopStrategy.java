package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

public class TopStrategy implements Strategy {

  List<Choice> history;
  Choice winningChoice;

  public TopStrategy(List<Choice> history, Choice choice) {
    this.history = history;
    winningChoice = choice;
  }

  public String getAction() {
    int evenChoiceCount = 0;
    int oddChoiceCount = 0;

    // Check through history and note the number of even and odd choices
    for (Choice choice : history) {
      if (choice == Choice.EVEN) {
        evenChoiceCount++;
      } else {
        oddChoiceCount++;
      }
    }

    switch (winningChoice) {
      // For player to win they must have an Even outcome
      case EVEN:
        // If we predict the player will choose an Odd number
        // We will choose an Even number
        if (oddChoiceCount > evenChoiceCount) {
          return Integer.toString(Utils.getRandomEvenNumber());
        } else {
          return Integer.toString(Utils.getRandomOddNumber());
        }
      // For player to win they must have an Odd outcome
      case ODD:
        // If we predict the player will choose an Odd number
        // We will choose an Odd number
        if (oddChoiceCount > evenChoiceCount) {
          return Integer.toString(Utils.getRandomOddNumber());
        } else {
          return Integer.toString(Utils.getRandomEvenNumber());
        }
      default:
        return Integer.toString(Utils.getRandomNumberRange(0, 5));
    }
  }
}
