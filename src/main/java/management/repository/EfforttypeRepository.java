package management.repository;

import management.domain.Efforttype;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Efforttype entity.
 */
@SuppressWarnings("unused")
public interface EfforttypeRepository extends JpaRepository<Efforttype,Long> {

}
