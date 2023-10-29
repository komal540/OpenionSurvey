package opinionsurvey.domain.services;

import opinionsurvey.restFile.repository.AnswerRepository;
import opinionsurvey.restFile.repository.QuestionRepository;
import opinionsurvey.restFile.repository.SurveyRepository;
import opinionsurvey.domain.exceptions.AnswerNotValidException;
import opinionsurvey.domain.exceptions.NoResultsException;
import opinionsurvey.domain.exceptions.QuestionNotFoundException;
import opinionsurvey.domain.exceptions.SurveyNotFoundException;
import opinionsurvey.domain.models.commands.CreateAnswersCommand;
import opinionsurvey.domain.models.commands.QuestionAnswer;
import opinionsurvey.domain.models.entities.Answer;
import opinionsurvey.domain.models.entities.Question;
import opinionsurvey.domain.models.entities.Survey;
import opinionsurvey.domain.models.enums.QuestionType;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public long save(CreateAnswersCommand answersCmd) {
        Survey currentSurvey = this.getSurvey(answersCmd.getSurveyId());
        List<Answer> toInsert = answersCmd.getQuestionAnswers().stream().map(questionAnswer -> {
            Question currentQuestion = this.getQuestion(questionAnswer.getQuestionId());
            this.validateAnswers(currentQuestion, questionAnswer);
            Answer answer = Answer.builder()
                    .survey(currentSurvey)
                    .email(answersCmd.getEmail())
                    .question(currentQuestion)
                    .answers(questionAnswer.getAnswers())
                    .build();
            return answer;
        }).toList();

        List<Answer> inserted = toInsert.stream().map(answerRepository::save).toList();

        return inserted.size() == answersCmd.getQuestionAnswers().size() ? inserted.size() : 0 ;
    }

    @Override
    public List<Answer> findByEmail(String email) {
        List<Answer> results = this.answerRepository.findByEmail(email);
        if(results.isEmpty()){
            throw new NoResultsException("No answers found for email: " + email);
        }
        return results;
    }

    @Override
    public List<Answer> findAll() {
        return this.answerRepository.findAll();
    }

    /**
     * Get the survey by id
     * @param surveyId the survey id
     * @return the survey
     * @throws SurveyNotFoundException if the survey is not found
     */
    private Survey getSurvey(Long surveyId) {
        return surveyRepository.findByIdAndEnabledTrue(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(String.format("Survey %d is not available", surveyId)));
    }

    /**
     * Get the question by id
     * @param questionId the question id
     * @return the question
     * @throws QuestionNotFoundException if the question is not found
     */
    private Question getQuestion(Long questionId){
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(String.format("Question %d is not available", questionId)));
    }

    /**
     * Check if the answers are valid
     * @param currentQuestion the question to check
     * @param questionAnswer the answers to check
     * @throws AnswerNotValidException if the answers are not valid
     */
    private void validateAnswers(Question currentQuestion, QuestionAnswer questionAnswer) {
        if (!currentQuestion.getQuestion().equals(QuestionType.TEXT)) {
            questionAnswer.getAnswers().stream().forEach(option -> {
                        if (!currentQuestion.getOptions().contains(option)) {
                            throw new AnswerNotValidException(String.format("Answer - %s - is not valid for question %d", option, questionAnswer.getQuestionId()));
                        }
                    }
            );
        }
    }

}
