package org.example.smart.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.enums.EvaluationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PushUpRepository extends JpaRepository<PushUp, Long> {
	List<PushUp> getPushUpsBySoldier(Soldier soldier);

	@Query("select p from PushUp p where p.soldier=:soldier and p.evaluationType=:evaluationType and function('DATE',p.evaluationDate)=:date")
	Optional<PushUp> findPushUpBySoldierAndEvaluationTypeAndEvaluationDate(Soldier soldier, EvaluationType evaluationType, LocalDate date);

}
