package cs3500.pa02.Model;

import cs3500.pa02.File.SpacedRepFile;
import cs3500.pa02.FileReader.SpacedRepFileReader;
import cs3500.pa02.FileWriter;
import cs3500.pa02.QuestionBlock;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the model for a study session
 */
public class StudyQuestionsModel {

  private static int easyToHard = 0;
  private static int hardToEasy = 0;

  /**
   * Get the questions from the question bank
   *
   * @param stringPath The path of the question bank (sr file)
   * @return The ArrayList of questions
   */
  public static ArrayList<QuestionBlock> getQuestionsFromPath(String stringPath) {
    ArrayList<QuestionBlock> questions = new ArrayList<>();
    ArrayList<Path> paths = new ArrayList<>();
    paths.add(Path.of(stringPath));
    SpacedRepFileReader srfr = new SpacedRepFileReader(paths);
    questions = srfr.getQuestions();

    return questions;
  }

  /**
   * Randomize and reduce the questions based on user input
   *
   * @param questions  An ArrayList of questions
   * @param numToStudy The number of questions the user wants to study
   * @return The new randomized and reduced list of questions
   */
  public static ArrayList<QuestionBlock> reduceAndRandomizeQuestions(
      ArrayList<QuestionBlock> questions, int numToStudy) {
    Collections.shuffle(questions);

    if (numToStudy < questions.size()) {
      List<QuestionBlock> sublist = questions.subList(0, numToStudy);
      ArrayList<QuestionBlock> reducedQuestions = new ArrayList<>(sublist);
      return reducedQuestions;
    }
    return questions;
  }

  /**
   * Change the given questions difficulty to easy
   *
   * @param question The question to modify
   */
  public static void changeToEasy(QuestionBlock question) {
    if (question.getDifficulty()) {
      hardToEasy++;
      question.changeDifficulty();
    }
  }

  /**
   * Change the given questions difficulty to hard
   *
   * @param question The question to modify
   */
  public static void changeToHard(QuestionBlock question) {
    if (!question.getDifficulty()) {
      easyToHard++;
      question.changeDifficulty();
    }
  }


  /**
   * Get the amount of questions that went from hard to easy
   *
   * @return the amount of questions that went from hard to easy
   */
  public static int getEasyToHard() {
    return easyToHard;
  }

  /**
   * Get the amount of questions that went from easy to hard
   *
   * @return the amount of questions that went from easy to hard
   */
  public static int getHardToEasy() {
    return hardToEasy;
  }

  /**
   * Get the amount of hard questions in an ArrayList of questions
   *
   * @param questions The ArrayList of questions to count from
   * @return The amount of hard questions
   */
  public static int getHardCount(ArrayList<QuestionBlock> questions) {
    int hards = 0;
    for (QuestionBlock question : questions) {
      if (question.getDifficulty()) {
        hards++;
      }
    }
    return hards;
  }

  /**
   * Get the amount of easy questions in an ArrayList of questions
   *
   * @param questions The ArrayList of questions to count from
   * @return The amount of easy questions
   */
  public static int getEasyCount(ArrayList<QuestionBlock> questions) {
    int easys = 0;
    for (QuestionBlock question : questions) {
      if (!question.getDifficulty()) {
        easys++;
      }
    }
    return easys;
  }

  /**
   * Update the question bank
   *
   * @param questions  The updated ArrayList of questions
   * @param stringPath The path to write the new file
   *                   (generally the same as the input question bank)
   * @throws IOException
   */
  public static void updateFile(ArrayList<QuestionBlock> questions, String stringPath)
      throws IOException {
    SpacedRepFile updatedSpacedRepFile = new SpacedRepFile(questions);
    String content = updatedSpacedRepFile.toString();
    FileWriter updatedFile = new FileWriter(stringPath, content);
    updatedFile.createFile(".sr");
  }
}
