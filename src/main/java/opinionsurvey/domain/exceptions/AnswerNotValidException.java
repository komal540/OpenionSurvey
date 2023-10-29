package opinionsurvey.domain.exceptions;

public class AnswerNotValidException extends RuntimeException {
    public AnswerNotValidException(String message) {
        super(message);
    }
}
