package management.repository;

import management.domain.Projectrelease;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Projectrelease entity.
 */
@SuppressWarnings("unused")
public interface ProjectreleaseRepository extends JpaRepository<Projectrelease,Long> {

}
