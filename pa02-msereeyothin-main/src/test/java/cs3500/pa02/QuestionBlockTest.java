package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for a question block
 */
class QuestionBlockTest {

  /**
   * Tests the constructor and getter methods in a question block
   */
  @Test
  void testConstructorAndGetters() {
    String question = "What is the capital of France?";
    String answer = "Paris";
    boolean isHard = true;

    QuestionBlock questionBlock = new QuestionBlock(question, answer, isHard);

    assertEquals(question, questionBlock.getQuestion());
    assertEquals(answer, questionBlock.getAnswer());
    assertEquals(isHard, questionBlock.getDifficulty());
  }

  /**
   * Tests changing the difficulty of a question block
   */
  @Test
  void testChangeDifficulty() {
    String question = "What is the capital of Germany?";
    String answer = "Berlin";
    boolean isHard = false;

    QuestionBlock questionBlock = new QuestionBlock(question, answer, isHard);
    assertFalse(questionBlock.getDifficulty());
    questionBlock.changeDifficulty();
    assertTrue(questionBlock.getDifficulty());
    questionBlock.changeDifficulty();
    assertFalse(questionBlock.getDifficulty());
  }

  /**
   * Tests converting the question block to a string
   */
  @Test
  void testToString() {
    String question = "What is the capital of Italy?";
    String answer = "Rome";
    boolean isHard = true;

    QuestionBlock questionBlock = new QuestionBlock(question, answer, isHard);

    String expectedString = question + ":::" + answer + " " + isHard;
    assertEquals(expectedString, questionBlock.toString());
  }
}
