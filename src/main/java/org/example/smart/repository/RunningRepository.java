package org.example.smart.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.Running;
import org.example.smart.domain.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningRepository extends JpaRepository<Running, Long> {

	@Query("select r from Running r where r.soldier=:soldier and function('DATE',r.evaluationDate)=:date")
	Optional<Running> findRunningBySoldierAndEvaluationDate(Soldier soldier, LocalDate date);

	List<Running> findRunningsBySoldier(Soldier soldier);

}
