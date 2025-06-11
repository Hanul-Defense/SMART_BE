package org.example.smart.repository;

import org.example.smart.domain.RunningFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningFeedbackRepository extends JpaRepository<RunningFeedback, Long> {
}
