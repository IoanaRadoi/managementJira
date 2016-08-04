package management.repository;

import management.domain.Projectreleasesprint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Projectreleasesprint entity.
 */
@SuppressWarnings("unused")
public interface ProjectreleasesprintRepository extends JpaRepository<Projectreleasesprint,Long> {

}
