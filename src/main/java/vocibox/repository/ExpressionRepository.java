package vocibox.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vocibox.domain.Expression;

/**
 * Spring Data JPA repository for the Expression entity.
 */
public interface ExpressionRepository extends JpaRepository<Expression, Long> {

    @Query("select e from Expression e where (:marked is null or e.marked = :marked)")
    Page<Expression> search(@Param("marked") Boolean marked, Pageable pageable);


    @Query("select e from Expression e left join fetch e.tags where e.id = :id")
    Expression findOneWithEagerRelationships(@Param("id") Long id);

}
