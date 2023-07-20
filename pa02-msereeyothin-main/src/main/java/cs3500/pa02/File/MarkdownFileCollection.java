package cs3500.pa02.File;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A representation of multiple markdown files.
 */
public class MarkdownFileCollection {
  private ArrayList<MarkdownFile> markdownFileCollection = new ArrayList<MarkdownFile>();

  /**
   * Add a markdown file to the collection.
   *
   * @param mdFile The markdown file to be added.
   */
  public void addMarkdownFile(MarkdownFile mdFile) {
    markdownFileCollection.add(mdFile);
  }

  /**
   * Get the content of all the markdown files as a string.
   *
   * @return The content of the collection of markdown files as a string.
   */
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    for (MarkdownFile file : markdownFileCollection) {
      stringBuilder.append(file.toString()).append(System.lineSeparator());
    }

    return stringBuilder.toString();
  }

  /**
   * Sort the collection of markdown files based on the ordering flag
   */
  public void sortCollection() {
    Collections.sort(markdownFileCollection);
  }

}
