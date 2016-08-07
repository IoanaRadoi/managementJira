package management.repository;

import management.domain.Storypoint;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Storypoint entity.
 */
@SuppressWarnings("unused")
public interface StorypointRepository extends JpaRepository<Storypoint,Long> {

    @Query("select sum(sp.storypoint) from Storypoint sp where sp.projectreleasesprint.id = :idSprint")
    Integer countStoryPointPerSprint(@Param("idSprint") Long idSprint);

}
