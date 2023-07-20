package cs3500.pa02.FileReader;

import cs3500.pa02.QuestionBlock;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file reader for spaced repetition files
 */
public class SpacedRepFileReader extends AFileReader {

  /**
   * Initialize a spaced repetition file reader
   *
   * @param paths The paths of spaced repetition files
   */
  public SpacedRepFileReader(ArrayList<Path> paths) {
    super(paths);
  }

  /**
   * Get the questions of this spaced repetition file
   *
   * @return The questions of the spaced repetition file
   */
  public ArrayList<QuestionBlock> getQuestions(){
    ArrayList<QuestionBlock> questions = new ArrayList<>();
    for (Path path : paths){
      try {
        scanner = new Scanner(path);
      } catch (IOException e) {
        e.printStackTrace();
      }
      while (scanner.hasNext()) {
        String curLine = scanner.nextLine();
        if (inQuestionFormat(curLine)) {
          QuestionBlock curQuestionBlock = getQuestionFromLine(curLine);
          questions.add(curQuestionBlock);
        }
      }
    }
    return questions;
  }

  /**
   * Get question class from a string
   *
   * @param line The string to be extracted from
   * @return The questions extracted from a string
   */
  private QuestionBlock getQuestionFromLine(String line){
    boolean difficult = false;
    if (line.endsWith("false")){
      line = line.substring(0, line.lastIndexOf(" "));
    }
    if (line.endsWith("true")) {
      line = line.substring(0, line.lastIndexOf(" "));
      difficult = true;
    }
    String question = removeColons(line, true);
    String answer = removeColons(line, false);
    return new QuestionBlock(question, answer, difficult);
  }

  /**
   * Is the string in a question format?
   *
   * @param line The string to be read
   * @return If the string is in a question format
   */
  private boolean inQuestionFormat(String line){
    return (line.contains("true") || line.contains("false"))
        && line.contains(":::")
        && (!line.contains("[[") && (!line.contains("]]")));
  }
}
