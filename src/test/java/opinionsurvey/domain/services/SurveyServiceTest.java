package opinionsurvey.domain.services;

import opinionsurvey.restFile.repository.SurveyRepository;
import opinionsurvey.domain.exceptions.NoResultsException;
import opinionsurvey.domain.models.entities.Question;
import opinionsurvey.domain.models.entities.Survey;
import opinionsurvey.domain.models.enums.QuestionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @BeforeEach
    void setUp() {
        this.surveyService = new SurveyServiceImpl(this.surveyRepository);
    }

    @Test
    void GetListSurveysHasResults() {
        List<Survey> surveys = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        questions.add(Question.builder()
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build());
        questions.add(Question.builder()
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build());

        surveys.add(Survey.builder()
                .name("clinic plus")
                .enabled(true)
                .questions(questions)
                .build());

        when(surveyRepository.findAllEnabled()).thenReturn(surveys);


        List<Survey> result = surveyService.listSurveys();
        assert(result.size() == surveys.size());
        assert(result.get(0).getQuestions().size() == surveys.get(0).getQuestions().size());
        verify(surveyRepository).findAllEnabled();
    }

    @Test
    void GetListSurveysHasNoResults() {
        List<Survey> surveys = new ArrayList<>();
        when(surveyRepository.findAllEnabled()).thenReturn(surveys);

        List<Survey> result = surveyService.listSurveys();
        assert(result.size() == surveys.size());
        verify(surveyRepository).findAllEnabled();
    }

    @Test
    void GetSurveyByIdHasResult(){
        List<Survey> surveys = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        questions.add(Question.builder()
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build());
        questions.add(Question.builder()
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build());

        surveys.add(Survey.builder()
                .name("clinic plus")
                .enabled(true)
                .questions(questions)
                .build());

        when(surveyRepository.findById(1L)).thenReturn(java.util.Optional.of(surveys.get(0)));

        Survey result = surveyService.getSurvey(1L);

        assert(result.getName().equals(surveys.get(0).getName()));
        assert(result.getQuestions().size() == surveys.get(0).getQuestions().size());
        verify(surveyRepository).findById(1L);
    }

    @Test
    void GetSurveyByIdHasNoResult(){
        when(surveyRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(NoResultsException.class, () -> {
            surveyService.getSurvey(1L);
        });
    }

    @Test
    void CreateSurvey(){
        List<Question> questions = new ArrayList<>();

        questions.add(Question.builder()
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build());
        questions.add(Question.builder()
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build());

        Survey survey = Survey.builder()
                .name("clinic plus")
                .enabled(true)
                .questions(questions)
                .build();

        surveyService.createSurvey(survey);

        verify(surveyRepository).save(survey);
    }

    @Test
    void DeleteSurvey(){
        when(surveyRepository.updateEnabledById(any(Long.class), any(Boolean.class))).thenReturn(1);

        surveyService.deleteSurvey(1L);

        verify(surveyRepository).updateEnabledById(1L, false);
    }

    @Test
    void DeleteSurveyHasNoResult(){
        when(surveyRepository.updateEnabledById(any(Long.class), any(Boolean.class))).thenReturn(0);

        assertThrows(NoResultsException.class, () -> {
            surveyService.deleteSurvey(1L);
        });
    }

}
