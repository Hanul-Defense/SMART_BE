package org.example.smart.repository;

import java.util.List;

import org.example.smart.domain.SitUp;
import org.example.smart.domain.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitUpRepository extends JpaRepository<SitUp, Long> {

	List<SitUp> getSitUpsBySoldier(Soldier soldier);

}
