package opinionsurvey.restFile.rest;

import opinionsurvey.domain.models.commands.CreateAnswersCommand;
import opinionsurvey.domain.models.entities.Answer;
import opinionsurvey.domain.services.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("opinion/answers")
public class AnswerControllerImpl implements AnswerController {

    private final AnswerService answerService;

    public AnswerControllerImpl(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    @PostMapping(produces = "application/json", consumes = "application/json")
    public long save(@RequestBody CreateAnswersCommand answersCmd) {
        return this.answerService.save(answersCmd);
    }

    @Override
    @GetMapping(value = "/{email}", produces = "application/json")
    public List<Answer> findByEmail(@PathVariable String email) {
        return this.answerService.findByEmail(email);
    }

    @Override
    @GetMapping(produces = "application/json")
    public List<Answer> findAll() {
        return this.answerService.findAll();
    }
}
