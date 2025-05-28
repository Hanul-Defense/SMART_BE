package org.example.smart.repository;

import java.util.Optional;

import org.example.smart.domain.Standard;
import org.example.smart.domain.enums.EvaluationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<Standard, Long> {

	@Query("""
		select s from Standard s
		where s.evaluationCategory=:evaluationCategory
		and s.minAge<=:age and s.maxAge>=:age
		and s.minValue<=:count and s.maxValue>=:count
		""")
	Optional<Standard> findByAgeAndCountAndEvaluationCategory(@Param("age") Integer age, @Param("count") Integer count,
		EvaluationCategory evaluationCategory);
}
