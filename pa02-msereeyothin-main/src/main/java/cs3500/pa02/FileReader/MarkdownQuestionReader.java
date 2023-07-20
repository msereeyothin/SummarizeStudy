package cs3500.pa02.FileReader;

import cs3500.pa02.File.SpacedRepFile;
import cs3500.pa02.QuestionBlock;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A file reader class to parse questions from markdown files
 */
public class MarkdownQuestionReader extends AFileReader {

  /**
   * Initialize the file reader
   *
   * @param paths The paths of files to read from
   */
  public MarkdownQuestionReader(ArrayList<Path> paths) {
    super(paths);
  }

  /**
   * Get the spaced repetition file from the questions parsed from the markdown files
   *
   * @return The spaced repetition file
   */
  public SpacedRepFile getSpacedRepFile() {
    ArrayList<String> content = new ArrayList<String>();
    for (Path path : paths) {
      try {
        scanner = new Scanner(path);
      } catch (IOException e) {
        e.printStackTrace();
      }
      while (scanner.hasNext()) {
        content.add(scanner.nextLine());
      }
    }
    return new SpacedRepFile(parseQuestions(content));
  }

  /**
   * Extract the questions from the given content
   *
   * @param content The ArrayList of content to extract the questions from
   * @return The ArrayList of extracted questions
   */
  private ArrayList<QuestionBlock> parseQuestions(ArrayList<String> content) {
    ArrayList<QuestionBlock> questions = new ArrayList<>();
    content = formatBrackets(content);
    for (String line : content) {
      if (containsQuestion(line)) {
        String question = removeColons(line, true);
        String answer = removeColons(line, false);
        QuestionBlock curQuestion = new QuestionBlock(question, answer, true);
        questions.add(curQuestion);
      }
    }
    return questions;
  }

  /**
   * Does this line contain a question?
   *
   * @param line The given string to determine if there is a questions
   * @return If the line contains a question
   */
  private boolean containsQuestion(String line) {
    return (line.contains("[[") && line.contains(":::") && line.contains("]]"));
  }
}
