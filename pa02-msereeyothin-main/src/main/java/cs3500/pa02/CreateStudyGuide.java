package cs3500.pa02;

import cs3500.pa02.File.MarkdownFileCollection;
import cs3500.pa02.File.SpacedRepFile;
import cs3500.pa02.FileReader.MarkdownFileReader;
import cs3500.pa02.FileReader.MarkdownQuestionReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Representing the process of making a study guide
 */
public class CreateStudyGuide {

  private static String stringPath;
  private static OrderFlag orderFlag;
  private static String stringOutput;

  /**
   * Initialize the process of making a study guide
   *
   * @param args The command line arguments
   */
  public CreateStudyGuide(String[] args) {
    try {
      stringPath = args[0];

      orderFlag = null;

      switch (args[1]) {
        case "filename" -> orderFlag = OrderFlag.FILENAME;
        case "modified" -> orderFlag = OrderFlag.MODIFIED;
        case "created" -> orderFlag = OrderFlag.CREATED;
      }

      stringOutput = args[2];
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Generate the study guides and save them as files
   *
   * @throws IOException
   */
  public static void generateGuides() throws IOException {

    Path startingDirectory = Path.of(stringPath);
    FileTreeWalkerVisitor pf = new FileTreeWalkerVisitor();
    MarkdownFileCollection mfC = new MarkdownFileCollection();
    Files.walkFileTree(startingDirectory, pf);


    // Handling markdown files
    MarkdownFileReader fr = new MarkdownFileReader(pf.getPaths(), orderFlag);
    fr.addToMarkdownFileCollection(mfC);
    mfC.sortCollection();

    // Handling spaced repetition files
    MarkdownQuestionReader spfr = new MarkdownQuestionReader(pf.getPaths());
    SpacedRepFile spf = spfr.getSpacedRepFile();

    // Handling the creation of markdown files
    FileWriter mfw = new FileWriter(stringOutput, mfC.toString());
    mfw.createFile(".md");

    // Handling the creation of sr files
    FileWriter srfw = new FileWriter(stringOutput, spf.toString());
    srfw.createFile(".sr");
  }
}
