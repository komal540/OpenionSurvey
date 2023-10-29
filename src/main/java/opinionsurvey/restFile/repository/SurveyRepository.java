package opinionsurvey.restFile.repository;

import opinionsurvey.domain.models.entities.Survey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Modifying
    @Query("update Survey s SET s.enabled = :enabled WHERE s.id = :id")
    int updateEnabledById(@Param("id") Long id, @Param("enabled") boolean b);

    @Query("SELECT s FROM Survey s WHERE s.enabled = 1")
    List<Survey> findAllEnabled();

    Optional<Survey> findByIdAndEnabledTrue(Long id);
}
