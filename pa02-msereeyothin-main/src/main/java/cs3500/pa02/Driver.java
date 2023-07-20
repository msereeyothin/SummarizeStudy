package cs3500.pa02;

import cs3500.pa02.Controller.StudySessionController;
import java.io.IOException;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - either none or three command line arguments are required
   *             no command line arguments will initialize a study session
   *             three command line arguments will create a study guide:
   *             argument 1 - The path of the markdown files to be read
   *             argument 2 - The ordering flag to sort the markdown file content
   *             argument 3 - The output of the study guide
   */
  public static void main(String[] args) throws IOException {

    if (args.length == 0) {
      StudySessionController study = new StudySessionController();
      study.start();
    }

    if (args.length == 3) {
      CreateStudyGuide guide = new CreateStudyGuide(args);
      guide.generateGuides();
    }
  }
}