package cs3500.pa02.Controller;

import cs3500.pa02.Model.StudyQuestionsModel;
import cs3500.pa02.QuestionBlock;
import cs3500.pa02.View.StudySessionView;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a controller for a study session
 */
public class StudySessionController {
  /**
   * Begin the study session
   *
   * @throws IOException
   */
  public static void start() throws IOException {
    StudySessionView view = new StudySessionView();
    StudyQuestionsModel model = new StudyQuestionsModel();

    view.greetUser();

    String questionBankPath = view.getQuestionBank();
    int numToPractice = view.getNumToPractice();

    ArrayList<QuestionBlock> questions = model.getQuestionsFromPath(questionBankPath);
    ArrayList<QuestionBlock> questionsToStudy =
        model.reduceAndRandomizeQuestions(questions, numToPractice);

    for (QuestionBlock question : questionsToStudy) {
      view.showQuestion(question);
      String ke = "";
      while (!ke.equals("e") && !ke.equals("h")) {
        ke = view.isHardOrEasy();
        if (ke.equals("e")) {
          model.changeToEasy(question);
        } else if (ke.equals("h")) {
          model.changeToHard(question);
        } else {
          System.out.println("invalid input! please enter [e] or [h] only");
        }
      }
      view.showAnswer(question);
    }

    int easyToHard = model.getEasyToHard();
    int hardToEasy = model.getHardToEasy();
    int hardCount = model.getHardCount(questions);
    int easyCount = model.getEasyCount(questions);

    view.congratulateUser();
    view.showStats(easyToHard, hardToEasy);
    view.showCounts(easyCount, hardCount);

    model.updateFile(questions, questionBankPath);

  }
}
