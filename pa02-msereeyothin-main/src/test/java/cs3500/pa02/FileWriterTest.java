package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Tests for markdown file writer
 */
class FileWriterTest {

  FileWriter example;

  /**
   * Test the file writer constructor
   */
  @Test
  void initFiles() {
    assertDoesNotThrow(() -> {
      example = new FileWriter("" +
          "src/test/resources/ExampleOutput/output.md",
          "example content");
    });

  }

  /**
   * Test the creation of a new markdown file
   */
  @Test
  void createFile() {
    initFiles();
    assertDoesNotThrow(() -> {
      example.createFile(".md");
    });
  }
}