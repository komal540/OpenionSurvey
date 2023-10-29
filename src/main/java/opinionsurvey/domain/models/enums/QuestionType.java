package opinionsurvey.domain.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum QuestionType {
    TEXT("TEXT"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    CHECKBOX("CHECKBOX");
    @Getter private String value;
}
