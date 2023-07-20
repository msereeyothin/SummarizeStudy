package cs3500.pa02.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.File.MarkdownFileCollection;
import cs3500.pa02.OrderFlag;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a markdown file reader
 */
public class MarkdownFileReaderTest {

  private final MarkdownFileCollection EXAMPLE_MDFCOLLECTION = new MarkdownFileCollection();
  private final ArrayList<Path> EXAMPLE_PATHS = new ArrayList<Path>();

  private MarkdownFileReader exampleMarkdownFileReader = null;

  /**
   * Tests the initialization of a markdown file reader
   *
   * @throws IOException
   */
  @Test
  void testInit() throws IOException {
    EXAMPLE_PATHS.add(Path.of("src/test/resources/ExampleMDFiles/testing.md"));
    exampleMarkdownFileReader = new MarkdownFileReader(EXAMPLE_PATHS, OrderFlag.FILENAME);
  }

  /**
   * Tests adding a markdown file to a markdown file collection
   *
   * @throws IOException
   */
  @Test
  void addToMarkdownFileCollection() throws IOException {
    testInit();
    exampleMarkdownFileReader.addToMarkdownFileCollection(EXAMPLE_MDFCOLLECTION);
    StringBuilder str = new StringBuilder();
    str.append("# Just a test file").append(System.lineSeparator());
    str.append("- Nothing to see here").append(System.lineSeparator());
    str.append("- enclosed string").append(System.lineSeparator());
    str.append(System.lineSeparator());
    str.append("## Another line").append(System.lineSeparator()).append(System.lineSeparator());
    String expectedValue = str.toString();
    String actualValue = EXAMPLE_MDFCOLLECTION.toString();
    assertEquals(expectedValue, actualValue);
  }

  /**
   * Tests a failed initialization
   */
  @Test
  void failInitialization() throws IOException {
    EXAMPLE_PATHS.add(Path.of("invalid paths"));
    exampleMarkdownFileReader = new MarkdownFileReader(EXAMPLE_PATHS, OrderFlag.FILENAME);
    assertThrows(NullPointerException.class, () -> {
      exampleMarkdownFileReader.addToMarkdownFileCollection(EXAMPLE_MDFCOLLECTION);
    });

    EXAMPLE_PATHS.remove(0);
  }
}
