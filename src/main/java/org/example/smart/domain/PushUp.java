package org.example.smart.domain;

import java.time.LocalDateTime;

import org.example.smart.domain.enums.EvaluationType;
import org.example.smart.dto.request.PostEstimationDto;

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
@Table(name = "PushUp")
public class PushUp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pushup_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soldier_id", nullable = false)
	private Soldier soldier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "standard_id", nullable = false)
	private Standard standard;

	@Column(name = "count", nullable = false)
	private Integer count;

	@Column(name = "summary", nullable = true, length = 500)
	private String summary;

	@OneToOne(mappedBy = "pushUp", cascade = CascadeType.ALL, orphanRemoval = true)
	private PushUpFeedback pushUpFeedback;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "evaluation_type", nullable = false)
	private EvaluationType evaluationType;

	@Column(name = "evaluation_date", nullable = false)
	private LocalDateTime evaluationDate;

	@Builder
	public PushUp(Soldier soldier, Standard standard, Integer count, String summary,
		EvaluationType evaluationType, LocalDateTime evaluationDate) {
		this.soldier = soldier;
		this.standard = standard;
		this.count = count;
		this.summary = summary;
		this.evaluationType = evaluationType;
		this.evaluationDate = evaluationDate;
	}

	public void updateRecord(PostEstimationDto postEstimationDto) {
		if (this.count < postEstimationDto.count()) {
			this.count = postEstimationDto.count();
			this.summary = postEstimationDto.summary();
			this.evaluationDate = postEstimationDto.evaluationDate();
			this.pushUpFeedback = null;
		}
	}
}
