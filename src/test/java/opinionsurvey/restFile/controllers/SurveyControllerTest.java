package opinionsurvey.restFile.controllers;

import opinionsurvey.restFile.rest.SurveyControllerImpl;
import opinionsurvey.domain.models.entities.Question;
import opinionsurvey.domain.models.entities.Survey;
import opinionsurvey.domain.models.enums.QuestionType;
import opinionsurvey.domain.services.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyControllerTest {

    @Mock
    private SurveyService surveyService;

    @InjectMocks
    private SurveyControllerImpl surveyController;

    @BeforeEach
    void setUp() {
        this.surveyController = new SurveyControllerImpl(this.surveyService);
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

        when(surveyService.listSurveys()).thenReturn(surveys);

        assert(surveyController.listSurveys().size() == surveys.size());
    }

    @Test
    void GetSurveyByIdHasResult(){
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

        when(surveyService.getSurvey(any(Long.class))).thenReturn(survey);


        assert(surveyController.getSurvey(1L).getName().equals(survey.getName()));
    }

    @Test
    void CreateSurveyHasResult(){
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

        when(surveyService.createSurvey(any(Survey.class))).thenReturn(survey);

        assert(surveyController.createSurvey(survey).getName().equals(survey.getName()));
    }

    @Test
    void deleteSurveyHasResult(){
        when(surveyService.deleteSurvey(any(Long.class))).thenReturn(true);

        assert(surveyController.deleteSurvey(1L));
    }
}
