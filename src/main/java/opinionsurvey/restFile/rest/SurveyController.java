package opinionsurvey.restFile.rest;

import opinionsurvey.domain.models.entities.Survey;

import java.util.List;

public interface SurveyController {

    /**
     * List all surveys
     *
     * @return List of surveys
     */
    List<Survey> listSurveys();

    /**
     * Get a survey by id
     *
     * @param id Id of the survey
     * @return Survey found
     */
    Survey getSurvey(Long id);

    /**
     * Create a survey
     *
     * @param survey Survey to create
     * @return Survey created
     */
    Survey createSurvey(Survey survey);

    /**
     * Update a survey to logic delete
     *
     * @param id Id of the survey to delete
     * @return Survey deleted
     */
    boolean deleteSurvey(Long id);


}
