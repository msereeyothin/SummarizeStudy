package cs3500.pa02;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * To obtain every file in a given path
 */
public class FileTreeWalkerVisitor implements FileVisitor<Path> {
  private final ArrayList<Path> paths = new ArrayList<Path>();

  /**
   * Visit the folder of the associated path
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return Signify continuation of transversal
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (file.getFileName().toString().endsWith(".md")) {
      paths.add(file);
    }

    return CONTINUE;
  }

  /**
   * Obtain the finishing directory
   *
   * @param dir  a reference to the directory
   * @param exec {@code null} if the iteration of the directory completes without
   *             an error; otherwise the I/O exception that caused the iteration
   *             of the directory to complete prematurely
   * @return Signify continuation of transversal
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    // System.out.format("Finishing Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * Visit the starting directory
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return Signify continuation of transversal
   * @throws IOException Throw an exception given an invalid starting directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    // System.out.format("Starting Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * Exception handling for a visited invalid file.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return Signify continuation of transversal
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println(exc);
    return CONTINUE;
  }

  /**
   * Return the list of paths
   *
   * @return The list of markdown file paths
   */
  public ArrayList<Path> getPaths() {
    return paths;
  }


}
