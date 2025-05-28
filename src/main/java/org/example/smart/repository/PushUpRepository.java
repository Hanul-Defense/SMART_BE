package org.example.smart.repository;

import java.util.List;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushUpRepository extends JpaRepository<PushUp, Long> {
	List<PushUp> getPushUpsBySoldier(Soldier soldier);
}
