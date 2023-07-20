package cs3500.pa02.FileReader;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To represent a file reader
 */
abstract class AFileReader {

  protected ArrayList<Path> paths = new ArrayList<>();
  protected Scanner scanner = null;

  /**
   * Initialize a file reader
   *
   * @param paths The lists paths to read
   */
  public AFileReader(ArrayList<Path> paths) {
    this.paths = paths;
  }

  static String removeColons(String input, boolean before) {
    String withoutBrackets = input.replaceAll("\\[\\[", "").replaceAll("\\]\\]", "");

    // Extract the string before the triple colon (:::)
    int colonIndex = withoutBrackets.indexOf(":::");
    if (colonIndex != -1) {
      String result;
      if (before) {
        result = withoutBrackets.substring(0, colonIndex);
      } else {
        result = withoutBrackets.substring(colonIndex + 3);
      }
      return result.trim();
    }
    return input;
  }

  /**
   * Organizes incomplete brackets.
   *
   * @param input An ArrayList of strings which represents the content of a markdown file.
   * @return A version of the content with organized brackets.
   */
  static ArrayList<String> formatBrackets(ArrayList<String> input) {
    for (int i = 0; i < input.size(); i++) {
      String currentLine = input.get(i);
      StringBuilder str = new StringBuilder();

      if (currentLine.contains("[[") && !currentLine.contains("]]")) {
        int curIndex = i;
        while (!input.get(curIndex).contains("]]")) {
          str.append(input.get(curIndex) + " ");
          curIndex++;
        }
        str.append(input.get(curIndex).trim());
        input.set(i, str.toString());
      }

      if (stringAfterBrackets(currentLine).contains("[[")) {
        str.append(stringAfterBrackets(currentLine) + " ");
        int curIndex = i + 1;
        while (!input.get(curIndex).contains("]]")) {
          str.append(input.get(curIndex).trim());
          curIndex++;
        }
        str.append(input.get(curIndex).trim());
        input.set(curIndex, str.toString());
      }

    }
    return input;
  }

  /**
   * Returns the rest of a string after the last complete bracket pair.
   *
   * @param input A string with an incomplete bracket pair.
   * @return A string after the last complete bracket pair.
   */
  private static String stringAfterBrackets(String input) {
    int lastIndex = input.lastIndexOf("]]");
    if (lastIndex != -1) {
      return input.substring(lastIndex + 2);
    } else {
      return "";
    }
  }

}
