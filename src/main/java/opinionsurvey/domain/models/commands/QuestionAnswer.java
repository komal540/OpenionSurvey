package opinionsurvey.domain.models.commands;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionAnswer {
    private Long questionId;
    private List<String> answers;
}
