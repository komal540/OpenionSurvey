package opinionsurvey.domain.services;

import opinionsurvey.domain.models.commands.CreateAnswersCommand;
import opinionsurvey.domain.models.entities.Answer;

import java.util.List;

public interface AnswerService {

    /**
     * Save a list of answers
     * @param answersCmd the list of answers to save
     * @return Number of answers saved
     */
    long save(CreateAnswersCommand answersCmd);

    /**
     * Find all answers by email
     * @param email the email to search
     * @return a list of answers
     */
    List<Answer> findByEmail(String email);

    /**
     * Find all answers
     * @return a list of answers
     */
    List<Answer> findAll();

}
