package opinionsurvey.restFile.controllers;

import opinionsurvey.restFile.rest.AnswerControllerImpl;
import opinionsurvey.domain.models.commands.CreateAnswersCommand;
import opinionsurvey.domain.models.commands.QuestionAnswer;
import opinionsurvey.domain.models.entities.Answer;
import opinionsurvey.domain.models.entities.Question;
import opinionsurvey.domain.models.entities.Survey;
import opinionsurvey.domain.models.enums.QuestionType;
import opinionsurvey.domain.services.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnswersControllerTest {
    @Mock
    private AnswerService answerService;

    @InjectMocks
    private AnswerControllerImpl answerController;

    @BeforeEach
    void setUp() {
        this.answerController = new AnswerControllerImpl(this.answerService);
    }

    @Test
    void GetListAnswersHasResults() {
        Question question = Question.builder()
                .id(1L)
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build();


        List<Answer> answers = List.of(Answer.builder()
                .id(1L)
                .email("example@mail.com")
                .question(question)
                .answers(List.of("Re"))
                .build());

        when(answerService.findAll()).thenReturn(answers);

        List<Answer> answersResult  = answerController.findAll();

        assert(answersResult.size() == answers.size());
    }

    @Test
    void GetListAnswersByEmailHasResults() {
        Question question = Question.builder()
                .id(1L)
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build();


        List<Answer> answers = List.of(Answer.builder()
                .id(1L)
                .email("example@mail.com")
                .question(question)
                .answers(List.of("Re"))
                .build());

        when(answerService.findByEmail(anyString())).thenReturn(answers);

        List<Answer> answersResult  = answerController.findByEmail("example@mail.com");

        assert(answersResult.size() == answers.size());
    }

    @Test
    void SaveAnswersHasResults() {
        Question question = Question.builder()
                .id(1L)
                .question("client question")
                .type(QuestionType.CHECKBOX)
                .options(List.of("Re", "No"))
                .build();

        Survey survey = Survey.builder()
                .id(1L)
                .name("clinic plus")
                .questions(List.of(question))
                .build();

        CreateAnswersCommand answersCmd = CreateAnswersCommand.builder()
                .email("user@mail.com")
                .surveyId(1L)
                .questionAnswers(List.of(
                        QuestionAnswer.builder().
                                questionId(1L)
                                .answers(List.of("Re"))
                                .build()
                ))
                .build();

        when(answerService.save(any(CreateAnswersCommand.class))).thenReturn((long) answersCmd.getQuestionAnswers().size());

        long inserted = answerController.save(answersCmd);

        assert(inserted == answersCmd.getQuestionAnswers().size());
    }
}
