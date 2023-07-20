package cs3500.pa02.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.QuestionBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Tests the study mode model
 */
class StudyQuestionsModelTest {
  private static final String TEST_FILE_PATH = "testFile.sr";

  /**
   * Create an example file
   *
   * @throws IOException
   */
  @BeforeEach
  void setUp() throws IOException {
    // Create a test spaced repetition file
    String fileContent = "Question 1:::Answer 1 true\n" + "Question 2:::Answer 2 false\n" +
        "Question 3:::Answer 3 true\n" + "Question 4:::Answer 4 false\n" +
        "Question 5:::Answer 5 true\n";
    Files.write(Path.of(TEST_FILE_PATH), fileContent.getBytes());
  }

  /**
   * Tests getting the questions from a path
   */
  @Test
  void testGetQuestionsFromPath() {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    assertEquals(5, questions.size());
  }

  /**
   * Tests reducing and randomizing an arraylist of questions
   */
  @Test
  void testReduceAndRandomizeQuestions() {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    int numToStudy = 3;
    ArrayList<QuestionBlock> reducedQuestions =
        StudyQuestionsModel.reduceAndRandomizeQuestions(questions, numToStudy);
    assertEquals(numToStudy, reducedQuestions.size());
    numToStudy = 100;
    reducedQuestions = StudyQuestionsModel.reduceAndRandomizeQuestions(questions, numToStudy);
    assertEquals(questions.size(), reducedQuestions.size());
  }

  /**
   * Tests changing a question to hard
   */
  @Test
  void testChangeToHard() {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    QuestionBlock question = questions.get(1);
    StudyQuestionsModel.changeToHard(question);
    assertTrue(question.getDifficulty());
    StudyQuestionsModel.changeToHard(question);
    assertTrue(question.getDifficulty());
    assertEquals(1, StudyQuestionsModel.getHardToEasy());
  }

  /**
   * Tests changing a question to easy
   */
  @Test
  void testChangeToEasy() {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    QuestionBlock question = questions.get(0);
    StudyQuestionsModel.changeToEasy(question);
    assertFalse(question.getDifficulty());
    StudyQuestionsModel.changeToEasy(question);
    assertFalse(question.getDifficulty());
    assertEquals(0, StudyQuestionsModel.getEasyToHard());
  }

  /**
   * Test getting the hard question count in the question bank
   */
  @Test
  void testGetHardCount() {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    int hardCount = StudyQuestionsModel.getHardCount(questions);
    assertEquals(3, hardCount);
  }


  /**
   * Test getting the easy question count in the question bank
   */
  @Test
  void testGetEasyCount() {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    int easyCount = StudyQuestionsModel.getEasyCount(questions);
    assertEquals(2, easyCount);
  }

  /**
   * Tests updating a spaced repetition file
   *
   * @throws IOException
   */
  @Test
  void testUpdateFile() throws IOException {
    ArrayList<QuestionBlock> questions = StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    QuestionBlock newQuestion = new QuestionBlock("Question 6", "Answer 6", false);
    questions.add(newQuestion);

    StudyQuestionsModel.updateFile(questions, TEST_FILE_PATH);

    ArrayList<QuestionBlock> updatedQuestions =
        StudyQuestionsModel.getQuestionsFromPath(TEST_FILE_PATH);
    assertEquals(6, updatedQuestions.size());
  }
}

