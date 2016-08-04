package management.repository;

import management.domain.Releasejira;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Releasejira entity.
 */
@SuppressWarnings("unused")
public interface ReleasejiraRepository extends JpaRepository<Releasejira,Long> {

}
