package cs3500.pa02.FileReader;

import cs3500.pa02.File.MarkdownFile;
import cs3500.pa02.File.MarkdownFileCollection;
import cs3500.pa02.OrderFlag;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create MarkdownFile class from reading given files.
 */
public class MarkdownFileReader extends AFileReader {
  private BasicFileAttributes attr;
  private final ArrayList<Path> paths;
  private OrderFlag flag;

  /**
   * Constructor for FileReader.
   *
   * @param paths The paths of the given markdown file.
   * @param flag  The ordering flag of a markdown file.
   * @throws IOException Throw an exception given an invalid path.
   */
  public MarkdownFileReader(ArrayList<Path> paths, OrderFlag flag) throws IOException {
    super(paths);
    this.paths = paths;
    this.flag = flag;
  }

  /**
   * Add markdown files to a collection of markdown files.
   *
   * @param mdfC The collection of markdown files to add to.
   */
  public void addToMarkdownFileCollection(MarkdownFileCollection mdfC) {
    for (Path path : paths) {
      try {
        scanner = new Scanner(path);
        attr = Files.readAttributes(path, BasicFileAttributes.class);
      } catch (IOException e) {
        e.printStackTrace();
      }
      ArrayList<String> content = new ArrayList<String>();
      while (scanner.hasNext()) {
        content.add(scanner.nextLine());
      }
      mdfC.addMarkdownFile(new MarkdownFile(path.getFileName().toString(), attr.creationTime(),
          attr.lastModifiedTime(), formatMarkdownContent(content), this.flag));
    }

  }

  /**
   * Formats the markdown content.
   *
   * @param input An ArrayList of strings which represents the content of a markdown file.
   * @return The formatted version of the input list.
   */
  private static ArrayList<String> formatMarkdownContent(ArrayList<String> input) {
    ArrayList<String> output = new ArrayList<String>();
    input = formatBrackets(input);
    input = removeQuestions(input);

    for (int i = 0; i < input.size(); i++) {
      String currentLine = input.get(i);
      if (currentLine.startsWith("#")) {
        if (i != 0) {
          output.add("");
        }
        output.add(currentLine);
      }
      if (!currentLine.startsWith("#") && !getEnclosedString(currentLine).equals("")) {
        output.add("- " + getEnclosedString(currentLine));
      }
    }

    return output;
  }

  /**
   * Remove the question marked lines from the content
   *
   * @param input An ArrayList of strings
   * @return An ArrayList of strings without questions
   */
  private static ArrayList<String> removeQuestions(ArrayList<String> input) {
    ArrayList<String> output = new ArrayList<>();
    for (String line : input) {
      if (!line.contains(":::")) {
        output.add(line);
      }
    }
    return output;
  }

  /**
   * Returns the string inside a bracket pair.
   *
   * @param input A string with a complete bracket pair.
   * @return The string within the bracket pair.
   */
  private static String getEnclosedString(String input) {
    int start = input.indexOf("[[");
    int end = input.indexOf("]]");
    if (start != -1 && end != -1 && end > start) {
      return input.substring(start + 2, end);
    }
    return "";
  }

}