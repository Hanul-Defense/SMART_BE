package org.example.smart.repository;

import org.example.smart.domain.Running;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningRepository extends JpaRepository<Running, Long> {
}
