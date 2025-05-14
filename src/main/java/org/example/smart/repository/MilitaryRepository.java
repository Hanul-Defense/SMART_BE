package org.example.smart.repository;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.example.smart.domain.Military;
import org.example.smart.domain.enums.MilitaryBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MilitaryRepository extends JpaRepository<Military, Long> {
	// @Query("select Military m where ")
	Optional<Military> findByMilitaryBranchAndMilitaryNameAndCompanyAndPlatoon(
		MilitaryBranch militaryBranch,
		String militaryName,
		Integer company,
		Integer platoon
	);
}
