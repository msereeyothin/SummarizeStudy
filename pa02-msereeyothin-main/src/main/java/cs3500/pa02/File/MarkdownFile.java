package cs3500.pa02.File;

import cs3500.pa02.OrderFlag;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

/**
 * A representation of a markdown file
 */
public class MarkdownFile implements Comparable<MarkdownFile> {
  private String name;
  private FileTime createdDate;
  private FileTime modifiedDate;
  private ArrayList<String> content;
  private OrderFlag orderFlag;

  /**
   * The constructor for the markdown file.
   *
   * @param name         the name of the file.
   * @param createdDate  the creation date of the file.
   * @param modifiedDate the last modified date of the file.
   * @param content      the content of the file.
   * @param orderFlag    a flag to specify the order to sort in.
   */
  public MarkdownFile(String name, FileTime createdDate, FileTime modifiedDate,
                      ArrayList<String> content, OrderFlag orderFlag) {
    this.name = name;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
    this.content = content;
    this.orderFlag = orderFlag;
  }

  /**
   * Returns an integer for the comparator in order to sort multiple markdown files
   *
   * @param other the object to be compared.
   * @return an integer for the comparator operator.
   */
  @Override
  public int compareTo(MarkdownFile other) {
    if (orderFlag.equals(OrderFlag.FILENAME)) {
      return name.compareTo(other.getName());
    } else if (orderFlag.equals(OrderFlag.MODIFIED)) {
      return modifiedDate.compareTo(other.getModifiedDate());
    } else {
      return createdDate.compareTo(other.getCreatedDate());
    }
  }

  /**
   * Get the name of a markdown file.
   *
   * @return The name of the markdown file.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the creation date of a markdown file.
   *
   * @return The creation date of a markdown file.
   */
  public FileTime getCreatedDate() {
    return createdDate;
  }

  /**
   * Get the last modified date of a markdown file.
   *
   * @return The last modified date of the markdown file.
   */
  public FileTime getModifiedDate() {
    return modifiedDate;
  }


  /**
   * The content of the markdown file in the form of a string.
   *
   * @return The content of the markdown file as a string.
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    for (String s : content) {
      stringBuilder.append(s).append(System.lineSeparator());
    }

    return stringBuilder.toString();
  }
}
