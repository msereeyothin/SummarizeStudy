package cs3500.pa02;

/**
 * Represent a question and answer block
 */
public class QuestionBlock {
  private String question;
  private String answer;
  private boolean isHard;

  /**
   * Instantiate a new question and answer block
   *
   * @param question the question
   * @param answer   the answer
   * @param isHard   the difficulty level represented as a boolean
   *                 true -> hard , false -> easy
   */
  public QuestionBlock(String question, String answer, boolean isHard) {
    this.question = question;
    this.answer = answer;
    this.isHard = isHard;
  }

  /**
   * Change the difficulty level of the question
   */
  public void changeDifficulty() {
    isHard = !isHard;
  }

  /**
   * Get the question of the question block
   *
   * @return The question of the question block
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Get the answer of the question block
   *
   * @return The answer of the question block
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Get the question difficulty
   *
   * @return The difficulty of the question
   */
  public Boolean getDifficulty() {
    return isHard;
  }


  /**
   * Get the question in string form.
   *
   * @return The question in the form of a string
   */
  public String toString() {
    return question + ":::" + answer + " " + isHard;
  }
}
