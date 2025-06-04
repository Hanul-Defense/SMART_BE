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
@Table(name = "PushUp_Feedback")
public class PushUpFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pushup_feedback_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "pushup_id", nullable = false)
	private PushUp pushUp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soldier_id", nullable = false)
	private Soldier soldier;

	@Column(name = "feedback_content", length = 500)
	private String feedbackContent;

	@Builder
	public PushUpFeedback(PushUp pushUp, Soldier soldier, String feedbackContent) {
		this.pushUp = pushUp;
		this.soldier = soldier;
		this.feedbackContent = feedbackContent;
	}
}
