package management.repository;

import management.domain.Sprint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Sprint entity.
 */
@SuppressWarnings("unused")
public interface SprintRepository extends JpaRepository<Sprint,Long> {

}
