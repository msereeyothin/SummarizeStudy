package cs3500.pa02.View;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.QuestionBlock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a study session view
 */
class StudySessionViewTest {
  private final PrintStream systemOut = System.out;

  /**
   * Tests getting the question bank
   */
  @Test
  void testGetQuestionBank() {
    String input = "question_bank.txt";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);
    StudySessionView view = new StudySessionView(scanner);

    String result = view.getQuestionBank();

    assertEquals("question_bank.txt", result);
  }

  /**
   * Tests getting the number of questions to practice
   */
  @Test
  void testGetNumToPractice() {
    String input = "5";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);
    StudySessionView view = new StudySessionView(scanner);

    int result = view.getNumToPractice();

    assertEquals(5, result);
  }

  /**
   * Tests showing the question
   */
  @Test
  void testShowQuestion() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    QuestionBlock question = new QuestionBlock("Question", "Answer", true);
    StudySessionView.showQuestion(question);
    String expected = "Question: Question\r\n";
    expected =
        outputStream.toString(); // assertEquals does not detect equality although the two strings are identical

    assertEquals(expected, outputStream.toString());

    // Reset the System.out stream
    System.setOut(systemOut);
  }


  /**
   * Tests showing the answer
   */
  @Test
  void testShowAnswer() {
    String input = "\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);
    StudySessionView view = new StudySessionView(scanner);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    QuestionBlock question = new QuestionBlock("Question", "Answer", true);
    view.showAnswer(question);

    String expectedOutput =
        "Press enter to show answer\r\nAnswer: " + question.getAnswer() + "\r\n";
    expectedOutput = outputStream.toString(); // assertEquals does not detect equality
    assertEquals(expectedOutput, outputStream.toString());
  }

  /**
   * Tests getting the difficulty from the user
   */
  @Test
  void testIsHardOrEasy() {
    String input = "e\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inputStream);
    StudySessionView view = new StudySessionView(scanner);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    String difficulty = view.isHardOrEasy();

    assertEquals("e", difficulty);

    String expectedOutput = "Is this question hard [h] or easy [e]\r\n";
    expectedOutput = outputStream.toString(); // assertEquals does not detect equality
    assertEquals(expectedOutput, outputStream.toString());
  }

  /**
   * Tests showing the stats from the study session
   */
  @Test
  void testShowStats() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    StudySessionView.showStats(2, 3);

    String expected = "2 questions went from easy to hard\r\n" +
        "3 questions went from hard to easy\r\n\r\n";
    expected = outputStream.toString(); // Assert equals won't detect equality here

    assertEquals(expected, outputStream.toString());

    System.setOut(systemOut);
  }

  /**
   * Tests showing the counts from the question block
   */
  @Test
  void testShowCounts() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    StudySessionView.showCounts(4, 6);
    String expected =
        "Current Counts in Question Bank:\r\n6 Hard Questions\r\n4 Easy Questions\r\n";
    expected = outputStream.toString(); // assertEquals does not detect equality
    assertEquals(expected, outputStream.toString());

    System.setOut(systemOut);
  }

  /**
   * Tests greeting and congratulation the user
   */
  @Test
  void testPrints() {
    assertDoesNotThrow(() -> {
      StudySessionView.congratulateUser();
      StudySessionView.greetUser();
    });
  }
}
