package org.example.smart.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.SitUp;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.enums.EvaluationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SitUpRepository extends JpaRepository<SitUp, Long> {

	List<SitUp> getSitUpsBySoldier(Soldier soldier);

	@Query("select s from SitUp s where s.soldier=:soldier and s.evaluationType=:evaluationType and function('DATE',s.evaluationDate)=:date")
	Optional<SitUp> findSitUpBySoldierAndEvaluationTypeAndEvaluationDate(Soldier soldier, EvaluationType evaluationType, LocalDate date);
}
