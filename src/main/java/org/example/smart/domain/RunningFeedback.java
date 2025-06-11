package org.example.smart.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Running_Feedback")
public class RunningFeedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "running_feedback_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "running_id", nullable = false)
	private Running running;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soldier_id", nullable = false)
	private Soldier soldier;

	@Column(name = "feedback_content", length = 500)
	private String feedbackContent;

	@Builder
	public RunningFeedback(Running running, Soldier soldier, String feedbackContent) {
		this.running = running;
		this.soldier = soldier;
		this.feedbackContent = feedbackContent;
	}
}
