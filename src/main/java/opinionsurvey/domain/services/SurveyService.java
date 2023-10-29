package opinionsurvey.domain.services;

import opinionsurvey.domain.exceptions.NoResultsException;
import opinionsurvey.domain.models.entities.Survey;

import java.util.List;

public interface SurveyService {

    /**
     * List all surveys
     * @return List of surveys
     */
    List<Survey> listSurveys();

    /**
     * Get a survey by id
     * @param id Id of the survey
     * @return Survey found
     * @throws NoResultsException if no survey is found
     */
    Survey getSurvey(Long id);

    /**
     * Create a survey
     * @param survey Survey to create
     * @return Survey created
     */
    Survey createSurvey(Survey survey);

    /**
     * Update a survey to logic delete
     * @param id Id of the survey to delete
     * @return Survey deleted
     * @throws NoResultsException if no survey found
     */
    boolean deleteSurvey(Long id);
}
