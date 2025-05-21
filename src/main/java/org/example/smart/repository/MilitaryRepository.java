package org.example.smart.repository;

import java.util.List;
import java.util.Optional;

import org.example.smart.domain.Military;
import org.example.smart.domain.enums.MilitaryBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MilitaryRepository extends JpaRepository<Military, Long> {

	Optional<Military> findByMilitaryBranchAndMilitaryNameAndCompanyAndPlatoon(
		MilitaryBranch militaryBranch,
		String militaryName,
		Integer company,
		Integer platoon
	);

	@Query("select m from Military m where m.militaryName like %:keyword%")
	List<Military> findMilitariesByKeyword(@Param("keyword") String keyword);
}
