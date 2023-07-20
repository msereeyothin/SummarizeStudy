package cs3500.pa02;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Write new markdown files
 */
public class FileWriter {
  private java.io.FileWriter output;
  private String content;
  private Path outputPath;

  /**
   * Constructor for MarkdownFileWriter.
   *
   * @param outputPath The output path for the markdown file.
   * @param content    The content within the markdown file.
   */
  public FileWriter(String outputPath, String content) {
    this.outputPath = Path.of(outputPath);
    this.content = content;
  }

  /**
   * Create a new markdown file.
   *
   * @throws IOException Throw an exception if the output path is invalid.
   */
  public void createFile(String extension) throws IOException {
    File parentFile = outputPath.toFile().getParentFile();
    String childWithExtension = (outputPath.getFileName().toString());
    String childWithoutExtension =
        childWithExtension.substring(0, childWithExtension.lastIndexOf('.'));
    output = new java.io.FileWriter(new File(parentFile, childWithoutExtension + extension));
    output.write(content);
    output.close();
  }

}
