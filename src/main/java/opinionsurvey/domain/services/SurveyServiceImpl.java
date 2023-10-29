package opinionsurvey.domain.services;

import opinionsurvey.restFile.repository.SurveyRepository;
import opinionsurvey.domain.exceptions.NoResultsException;
import opinionsurvey.domain.models.entities.Survey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;

    SurveyServiceImpl(SurveyRepository surveyRepository){
        this.surveyRepository = surveyRepository;
    }
    @Override
    public List<Survey> listSurveys() {
        return this.surveyRepository.findAllEnabled();
    }

    @Override
    public Survey getSurvey(Long id) {
        return this.surveyRepository.findById(id)
                .orElseThrow(() -> new NoResultsException("No survey found for id: " + id));
    }

    @Override
    public Survey createSurvey(Survey survey) {
        return this.surveyRepository.save(survey);
    }

    @Override
    public boolean deleteSurvey(Long id) {
        var result = this.surveyRepository.updateEnabledById(id, false);
        if(result == 0){
            throw new NoResultsException("No survey found for id: " + id);
        }
        return result > 0;
    }
}
