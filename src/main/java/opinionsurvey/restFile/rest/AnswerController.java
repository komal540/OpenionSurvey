package opinionsurvey.restFile.rest;

import opinionsurvey.domain.models.commands.CreateAnswersCommand;
import opinionsurvey.domain.models.entities.Answer;

import java.util.List;

public interface AnswerController {

    /**
     * Create a new answers for a survey
     * @param answersCmd the answers to be created
     * @return Number of answers created
     */
    long save(CreateAnswersCommand answersCmd);

    /**
     * Find all the answers for a given email
     * @param email the email to search for
     * @return the list of answers
     */
    List<Answer> findByEmail(String email);

    /**
     * Find all the answers
     * @return the list of answers
     */
    List<Answer> findAll();
}
