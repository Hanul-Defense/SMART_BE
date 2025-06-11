package org.example.smart.domain;

import java.time.LocalDateTime;

import org.example.smart.domain.enums.EvaluationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "Running")
public class Running {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "running_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soldier_id", nullable = false)
	private Soldier soldier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "standard_id", nullable = false)
	private Standard standard;

	@Column(name = "time", nullable = false)
	private Integer time;

	@Column(name = "summary", nullable = true, length = 500)
	private String summary;

	@OneToOne(mappedBy = "running", cascade = CascadeType.ALL, orphanRemoval = true)
	private RunningFeedback runningFeedback;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "evaluation_type", nullable = false)
	private EvaluationType evaluationType;

	@Column(name = "evaluation_date", nullable = false)
	private LocalDateTime evaluationDate;

	@Builder
	public Running(Soldier soldier, Standard standard, Integer time, String summary,
		EvaluationType evaluationType, LocalDateTime evaluationDate) {
		this.soldier = soldier;
		this.standard = standard;
		this.time = time;
		this.summary = summary;
		this.evaluationType = evaluationType;
		this.evaluationDate = evaluationDate;
	}
}
