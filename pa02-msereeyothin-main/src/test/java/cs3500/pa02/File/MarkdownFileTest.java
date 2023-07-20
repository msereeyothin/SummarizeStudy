package cs3500.pa02.File;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa02.OrderFlag;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class MarkdownFileTest {
  private ArrayList<String> exampleContent = new ArrayList<String>();

  @Test
  void initMarkdownFiles() {
    exampleContent.add("testing adding content to markdown file");
    exampleContent.add("updated version of markdown file does not");
    exampleContent.add("parse its given content");
    assertDoesNotThrow(() -> {
      MarkdownFile exampleMarkdownFile = new MarkdownFile(
          "example",
          FileTime.fromMillis(1671619200000L), // May, 15 2023
          FileTime.fromMillis(1690425600000L), // May, 20 2023
          exampleContent,
          OrderFlag.CREATED);
    });
  }

  /**
   * Testing comparator integer for created date comparison
   */
  @Test
  void compareToCreatedDate() {
    MarkdownFile may15markdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        exampleContent,
        OrderFlag.CREATED);
    MarkdownFile may20markdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.CREATED);
    assertEquals(-1, may15markdownFile.compareTo(may20markdownFile));
  }

  /**
   * Testing comparator integer for last modified date comparison
   */
  @Test
  void compareToModifiedDate() {
    MarkdownFile may15markdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        exampleContent,
        OrderFlag.MODIFIED);
    MarkdownFile may20markdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.MODIFIED);
    assertEquals(-1, may15markdownFile.compareTo(may20markdownFile));
  }

  /**
   * Testing comparator integer for name comparison
   */
  @Test
  void compareToName() {
    MarkdownFile markdownFileA = new MarkdownFile(
        "A",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        exampleContent,
        OrderFlag.FILENAME);
    assertEquals(0, markdownFileA.compareTo(markdownFileA));
  }

  @Test
  void getName() {
    MarkdownFile exampleMarkdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.CREATED);
    assertEquals("example", exampleMarkdownFile.getName());
  }

  /**
   * Test converting the markdown file's contents to a string
   */
  @Test
  void testToString() {
    exampleContent.add("line 1");
    exampleContent.add("line 2");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("line 1").append(System.lineSeparator());
    stringBuilder.append("line 2").append(System.lineSeparator());

    MarkdownFile exampleMarkdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.CREATED);
    assertEquals(stringBuilder.toString(), exampleMarkdownFile.toString());
  }
}