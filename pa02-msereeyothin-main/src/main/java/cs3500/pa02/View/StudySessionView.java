package cs3500.pa02.View;

import cs3500.pa02.QuestionBlock;
import java.util.Scanner;

/**
 * Represent the view of a study session
 */
public class StudySessionView {
  private static Scanner scanner;

  public StudySessionView() {
    scanner = new Scanner(System.in);
  }

  /**
   * Convenience constructor made for scanning purposes
   *
   * @param scanner A scanner for testing
   */
  public StudySessionView(Scanner scanner){
    this.scanner = scanner;
  }

  /**
   * Queries the user for the path of the question bank
   *
   * @return The path of the question bank
   */
  public static String getQuestionBank() {
    System.out.println("Enter a SR question bank file: ");
    return scanner.nextLine();
  }

  /**
   * Queries the user the number of questions they wish to study
   *
   * @return The number of questions the user wishes to study
   */
  public static int getNumToPractice() {
    System.out.println("How many questions would you like to study?: ");
    return scanner.nextInt();
  }

  /**
   * Greet the user
   */
  public static void greetUser() {
    System.out.println("Welcome to a SR study session!");
  }

  /**
   * Show the question block's question
   *
   * @param question The question block to process
   */
  public static void showQuestion(QuestionBlock question) {
    System.out.println("Question: " + question.getQuestion());
  }

  /**
   * Show the question block's answer
   *
   * @param question The question block to process
   */
  public static void showAnswer(QuestionBlock question) {
    System.out.println("Press enter to show answer");
    String ke = scanner.nextLine();
    while (!ke.equals("")) {
      showAnswer(question);
    }
    System.out.println("Answer: " + question.getAnswer() + "\n");
  }

  /**
   * Query the user for the question's difficulty
   *
   * @return The user key input
   */
  public static String isHardOrEasy() {
    System.out.println("Is this question hard [h] or easy [e]");
    String ke = scanner.nextLine();
    return ke;
  }

  /**
   * Congratulate the user for finishing the study session
   */
  public static void congratulateUser() {
    System.out.println("You've made it to the end. Good Job!\n");
  }

  /**
   * Show the statistics of the study session
   *
   * @param easyToHard The amount of questions that went from easy to hard
   * @param hardToEasy The amount of questions that went from hard to easy
   */
  public static void showStats(int easyToHard, int hardToEasy) {
    System.out.println(easyToHard + " questions went from easy to hard");
    System.out.println(hardToEasy + " questions went from hard to easy\n");
  }

  /**
   * Show the easy and hard counts of the question bank
   *
   * @param easyQuestions The amount of easy questions
   * @param hardQuestions The amount of hard questions
   */
  public static void showCounts(int easyQuestions, int hardQuestions) {
    System.out.println("Current Counts in Question Bank:");
    System.out.println(hardQuestions + " Hard Questions");
    System.out.println(easyQuestions + " Easy Questions");
  }
}
