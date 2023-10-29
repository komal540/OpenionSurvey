package opinionsurvey.domain.models.commands;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateAnswersCommand {

    private String email;
    private Long surveyId;
    private List<QuestionAnswer> questionAnswers;
}
