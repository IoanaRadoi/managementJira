package management.repository;

import management.domain.Manageraccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Manageraccount entity.
 */
@SuppressWarnings("unused")
public interface ManageraccountRepository extends JpaRepository<Manageraccount,Long> {

    @Query("select distinct manageraccount from Manageraccount manageraccount left join fetch manageraccount.projects")
    List<Manageraccount> findAllWithEagerRelationships();

    @Query("select manageraccount from Manageraccount manageraccount left join fetch manageraccount.projects where manageraccount.id =:id")
    Manageraccount findOneWithEagerRelationships(@Param("id") Long id);

}
