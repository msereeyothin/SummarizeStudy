package cs3500.pa02;

import static cs3500.pa02.Driver.main;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tests the driver
 */
class DriverTest {


  /**
   * Tests initialization of the program
   *
   * @throws IOException
   */
  @Test
  public void mainTest() {
    String[] noArgs = new String[] {};
    String[] args1 = new String[] {"src/test/resources/ExampleMDFiles", "filename",
        "src/test/resources/ExampleOutput/output.md"};
    String[] args2 = new String[] {"src/test/resources/ExampleMDFiles", "modified",
        "src/test/resources/ExampleOutput/output.md"};
    String[] args3 = new String[] {"src/test/resources/ExampleMDFiles", "created",
        "src/test/resources/ExampleOutput/output.md"};
    String[] invalidArgs1 = new String[] {"src/test/resources/ExampleMDFiles", "invalid",
        "src/test/resources/ExampleOutput/output.md"};
    String[] invalidArgs2 =
        new String[] {"invalid path", "invalid", "invalid path", "too many args"};

    assertDoesNotThrow(() -> {
      Driver example = new Driver();
    });
    assertDoesNotThrow(() -> {
      main(args1);
      main(args2);
      main(args3);
      main(invalidArgs1);
      main(invalidArgs2);
    });
    assertThrows(NoSuchElementException.class, () -> {
      main(noArgs);
    });
  }
}