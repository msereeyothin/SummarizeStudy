package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests for the file tree walker
 */
class FileTreeWalkerVisitorTest {

  ArrayList<Path> examplePaths = new ArrayList<Path>();
  FileTreeWalkerVisitor exampleFileWalker = new FileTreeWalkerVisitor();

  Path examplePath;
  Path invalidPath;

  /**
   * Initialize example paths and add them to an example arraylist of paths
   */
  @Test
  void initPaths() {
    examplePath = Path.of("validmarkdownfile.md");
    invalidPath = Path.of("notamarkdownfile");
    examplePaths.add(examplePath);
  }

  /**
   * Tests visiting individual files
   */
  @Test
  void visitFile() {
    initPaths();
    exampleFileWalker.visitFile(examplePath, null);
    exampleFileWalker.visitFile(invalidPath, null);
    assertEquals(examplePath, exampleFileWalker.getPaths().get(0));
    assertEquals(1, exampleFileWalker.getPaths().size());
    assertEquals(FileVisitResult.CONTINUE, exampleFileWalker.visitFile(examplePath, null));
  }

  /**
   * Tests visiting the post directory
   */
  @Test
  void postVisitDirectory() {
    initPaths();
    assertEquals(FileVisitResult.CONTINUE, exampleFileWalker.postVisitDirectory(examplePath, null));
  }

  /**
   * Tests visiting the previous directory
   */
  @Test
  void preVisitDirectory() {
    initPaths();
    try {
      assertEquals(FileVisitResult.CONTINUE,
          exampleFileWalker.preVisitDirectory(examplePath, null));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests visiting an invalid path
   */
  @Test
  void visitFileFailed() {
    initPaths();
    assertEquals(FileVisitResult.CONTINUE,
        exampleFileWalker.visitFileFailed(examplePath, new IOException()));
  }

  /**
   * Tests getting the paths from a file visitor
   */
  @Test
  void getPaths() {
    initPaths();
    exampleFileWalker.visitFile(examplePath, null);
    assertEquals(examplePath, exampleFileWalker.getPaths().get(0));
    assertEquals(1, exampleFileWalker.getPaths().size());
  }
}