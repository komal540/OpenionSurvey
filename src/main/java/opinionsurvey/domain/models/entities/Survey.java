package opinionsurvey.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String campaignCode;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id")
    private List<Question> questions;

}
