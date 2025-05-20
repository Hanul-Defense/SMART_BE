package org.example.smart.repository;

import java.util.Optional;

import org.example.smart.domain.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {
	Optional<Soldier> findByServiceNumber(String serviceNumber);
}
