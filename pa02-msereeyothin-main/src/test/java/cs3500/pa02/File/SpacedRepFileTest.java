package cs3500.pa02.File;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa02.QuestionBlock;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a spaced repetition file
 */
class SpacedRepFileTest {

  private ArrayList<QuestionBlock> questions = new ArrayList<>();
  private SpacedRepFile exampleSpacedRepFile = null;

  /**
   * Tests the initialization of a spaced repetition file
   */
  @Test
  void initSpacedRepFile() {
    QuestionBlock hardQuestion = new QuestionBlock("example question",
        "example answer", true);
    QuestionBlock easyQuestion = new QuestionBlock("example question",
        "example answer", false);
    questions.add(hardQuestion);
    questions.add(easyQuestion);

    exampleSpacedRepFile = new SpacedRepFile(questions);
  }

  /**
   * Tests the toString method
   */
  @Test
  void testToString() {
    initSpacedRepFile();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("example question:::example answer true").append(System.lineSeparator());
    stringBuilder.append("example question:::example answer false").append(System.lineSeparator());
    String expected = stringBuilder.toString();
    assertEquals(expected, exampleSpacedRepFile.toString());
  }
}