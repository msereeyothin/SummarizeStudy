package cs3500.pa02.File;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa02.File.MarkdownFile;
import cs3500.pa02.File.MarkdownFileCollection;
import cs3500.pa02.OrderFlag;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests for a collection of markdown files
 */
class MarkdownFileCollectionTest {

  private ArrayList<String> exampleContent = new ArrayList<String>();
  private MarkdownFileCollection exampleMarkdownFileCollection = new MarkdownFileCollection();

  /**
   * Tests adding a markdown file to a collection
   */
  @Test
  void addMarkdownFile() {
    MarkdownFile exampleMarkdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.CREATED);
    assertDoesNotThrow(() -> {
      exampleMarkdownFileCollection.addMarkdownFile(exampleMarkdownFile);
    });
  }

  /**
   * Tests converting a collection of markdown files' contents to a string
   */
  @Test
  void testToString() {
    exampleContent.add("line 1");
    exampleContent.add("line 2");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("line 1").append(System.lineSeparator());
    stringBuilder.append("line 2").append(System.lineSeparator());
    stringBuilder.append("").append(System.lineSeparator());

    MarkdownFile exampleMarkdownFile = new MarkdownFile(
        "example",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.CREATED);
    exampleMarkdownFileCollection.addMarkdownFile(exampleMarkdownFile);
    assertEquals(stringBuilder.toString(), exampleMarkdownFileCollection.toString());
  }

  /**
   * Tests sorting the markdown files by creation date
   */
  @Test
  void sortCollectionByCreatedDate() {
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

    exampleMarkdownFileCollection.addMarkdownFile(may20markdownFile);
    exampleMarkdownFileCollection.addMarkdownFile(may15markdownFile);
    MarkdownFileCollection sortedMFC = new MarkdownFileCollection();
    sortedMFC.addMarkdownFile(may15markdownFile);
    sortedMFC.addMarkdownFile(may20markdownFile);
    exampleMarkdownFileCollection.sortCollection();
    assertEquals(sortedMFC.toString(), exampleMarkdownFileCollection.toString());
  }

  /**
   * Tests sorting the markdown files by name
   */
  @Test
  void sortCollectionByName() {
    MarkdownFile markdownFileA = new MarkdownFile(
        "A",
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        FileTime.fromMillis(1671619200000L), // May, 15 2023
        exampleContent,
        OrderFlag.FILENAME);
    MarkdownFile markdownFileB = new MarkdownFile(
        "B",
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        FileTime.fromMillis(1690425600000L), // May, 20 2023
        exampleContent,
        OrderFlag.FILENAME);

    exampleMarkdownFileCollection.addMarkdownFile(markdownFileB);
    exampleMarkdownFileCollection.addMarkdownFile(markdownFileA);
    MarkdownFileCollection sortedMFC = new MarkdownFileCollection();
    sortedMFC.addMarkdownFile(markdownFileA);
    sortedMFC.addMarkdownFile(markdownFileB);
    exampleMarkdownFileCollection.sortCollection();
    assertEquals(sortedMFC.toString(), exampleMarkdownFileCollection.toString());
  }

  /**
   * Tests sorting the markdown files by last modified date
   */
  @Test
  void sortCollectionByLastModified() {
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

    exampleMarkdownFileCollection.addMarkdownFile(may20markdownFile);
    exampleMarkdownFileCollection.addMarkdownFile(may15markdownFile);
    MarkdownFileCollection sortedMFC = new MarkdownFileCollection();
    sortedMFC.addMarkdownFile(may15markdownFile);
    sortedMFC.addMarkdownFile(may20markdownFile);
    exampleMarkdownFileCollection.sortCollection();
    assertEquals(sortedMFC.toString(), exampleMarkdownFileCollection.toString());
  }
}