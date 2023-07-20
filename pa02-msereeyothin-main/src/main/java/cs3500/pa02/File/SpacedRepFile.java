package cs3500.pa02.File;

import cs3500.pa02.QuestionBlock;
import java.util.ArrayList;

/**
 * Represent a spaced repetition file
 */
public class SpacedRepFile {
  private ArrayList<QuestionBlock> questions = new ArrayList<>();

  /**
   * Initialize a spaced repetition file
   *
   * @param content The questions within a spaced repetition file
   */
  public SpacedRepFile(ArrayList<QuestionBlock> content) {
    this.questions = content;
  }

  /**
   * Get the string version of this SpacedRepFile
   *
   * @return A string version of the SpacedRepFile
   */
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (QuestionBlock q : questions) {
      String qAndA = q.toString();
      stringBuilder.append(qAndA).append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }
}
