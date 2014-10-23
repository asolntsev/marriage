package ee.marriage.web;

public class ValidationError extends RuntimeException {
  public ValidationError(String message) {
    super(message);
  }
}
