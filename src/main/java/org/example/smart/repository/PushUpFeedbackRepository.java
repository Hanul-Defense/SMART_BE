package org.example.smart.repository;

import org.example.smart.domain.PushUpFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushUpFeedbackRepository extends JpaRepository<PushUpFeedback, Long> {
}
