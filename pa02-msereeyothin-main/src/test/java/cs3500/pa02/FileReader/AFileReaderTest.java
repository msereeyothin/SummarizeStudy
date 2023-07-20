package cs3500.pa02.FileReader;

import static cs3500.pa02.FileReader.AFileReader.formatBrackets;
import static cs3500.pa02.FileReader.AFileReader.removeColons;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the abstract file reader class
 */
class AFileReaderTest {
  /**
   * Testing removing colons from a string
   *
   * @throws IOException
   */
  @Test
  void testRemoveColons() throws IOException {
    String testString = "before:::after";
    String actual = removeColons(testString, true);
    assertEquals("before", actual);
    actual = removeColons(testString, false);
    assertEquals("after", actual);
    assertEquals("", removeColons("", true));
  }

  /**
   * Testing formatting brackets
   *
   * @throws IOException
   */
  @Test
  void testFormatBrackets() throws IOException {
    ArrayList<String> input = new ArrayList<>();
    input.add("Example [[line");
    input.add("to be concatenated until");
    input.add("closing brackets]] are found");
    String expected = "Example [[line to be concatenated until closing brackets]] are found";
    assertEquals(expected, formatBrackets(input).get(0));
    input.clear();

    input.add("[[Full line then]] Example [[line");
    input.add("to be concatenated until");
    input.add("closing brackets]] are found");
    expected = " Example [[line to be concatenated untilclosing brackets]] are found";
    assertEquals(expected, formatBrackets(input).get(2));


  }
}