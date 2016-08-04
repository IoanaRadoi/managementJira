package management.repository;

import management.domain.Storypoint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Storypoint entity.
 */
@SuppressWarnings("unused")
public interface StorypointRepository extends JpaRepository<Storypoint,Long> {

}
