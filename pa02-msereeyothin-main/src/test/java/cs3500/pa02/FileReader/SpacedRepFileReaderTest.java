package cs3500.pa02.FileReader;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa02.QuestionBlock;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the spaced rep file reader
 */
class SpacedRepFileReaderTest {

  static final String SAMPLE_SR_FILES = "src/test/resources/ExampleSRFiles/ExampleQuestions.sr";
  static final ArrayList<Path> examplePaths = new ArrayList<>();

  SpacedRepFileReader exampleFileReader = null;

  /**
   * Initialize the file reader and test constructor
   */
  @Test
  void initFileReader() {
    examplePaths.add(Path.of(SAMPLE_SR_FILES));
    exampleFileReader = new SpacedRepFileReader(examplePaths);
  }

  /**
   * Tests getting the questions and helper methods
   */
  @Test
  void getQuestions() {
    initFileReader();
    ArrayList<QuestionBlock> testQuestions = exampleFileReader.getQuestions();
    QuestionBlock question1 = testQuestions.get(0);
    QuestionBlock question2 = testQuestions.get(1);

    assertEquals("This is an", question1.getQuestion());
    assertEquals("example of a question.", question1.getAnswer());
    assertEquals(false, question1.getDifficulty());
    assertEquals(true, question2.getDifficulty());
  }

  /**
   * Tests a failed initialization
   */
  @Test
  void failInitialization() {
    examplePaths.add(Path.of("invalid paths"));
    exampleFileReader = new SpacedRepFileReader(examplePaths);
    assertThrows(NullPointerException.class, () -> {
      exampleFileReader.getQuestions();
    });

    examplePaths.remove(0);
  }
}