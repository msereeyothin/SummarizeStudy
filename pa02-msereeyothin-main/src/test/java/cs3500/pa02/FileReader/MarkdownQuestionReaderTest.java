package cs3500.pa02.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.File.SpacedRepFile;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests the markdown question reader
 */
class MarkdownQuestionReaderTest {

  private final String SAMPLE_MD_FILES =
      "src/test/resources/ExampleMDFileWithQuestions/MarkdownWithQuestion";
  static final ArrayList<Path> examplePaths = new ArrayList<>();

  MarkdownQuestionReader exampleMarkdownQuestionReader = null;

  /**
   * Initialize the markdown question reader
   */
  @Test
  void initMarkdownQuestionReader() {
    examplePaths.add(Path.of(SAMPLE_MD_FILES));
    exampleMarkdownQuestionReader = new MarkdownQuestionReader(examplePaths);
  }

  /**
   * Tests getting a spaced repetition file
   */
  @Test
  void getSpacedRepFile() {
    initMarkdownQuestionReader();
    SpacedRepFile exampleSpacedRepFile = exampleMarkdownQuestionReader.getSpacedRepFile();
    String actualContent = exampleSpacedRepFile.toString();

    StringBuilder str = new StringBuilder();
    str.append("Example text without:::separators true").append(System.lineSeparator());
    str.append("What is the capital of Canada?:::The capital is Ottawa. true")
        .append(System.lineSeparator());
    str.append("Which country is known as the Land of the Rising Sun?:::Japan. true")
        .append(System.lineSeparator());
    str.append("Example text:::enclosed in brackets true").append(System.lineSeparator());

    String expectedContent = str.toString();
    assertEquals(expectedContent, actualContent);

  }

  /**
   * Tests a failed initialization
   */
  @Test
  void failInitialization() {
    examplePaths.add(Path.of("invalid paths"));
    exampleMarkdownQuestionReader = new MarkdownQuestionReader(examplePaths);
    assertThrows(NullPointerException.class, () -> {
      exampleMarkdownQuestionReader.getSpacedRepFile();
    });

    examplePaths.remove(0);
  }
}