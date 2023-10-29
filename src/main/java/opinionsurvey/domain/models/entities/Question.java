package opinionsurvey.domain.models.entities;

import opinionsurvey.domain.models.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private QuestionType type;

    @Column(nullable = false)
    private List<String> options;


}
