package opinionsurvey.restFile.rest;

import opinionsurvey.domain.exceptions.AnswerNotValidException;
import opinionsurvey.domain.exceptions.NoResultsException;
import opinionsurvey.domain.exceptions.QuestionNotFoundException;
import opinionsurvey.domain.exceptions.SurveyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({NoResultsException.class})
    public ResponseEntity<String> handleNoResultsException(NoResultsException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AnswerNotValidException.class, QuestionNotFoundException.class, SurveyNotFoundException.class})
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
