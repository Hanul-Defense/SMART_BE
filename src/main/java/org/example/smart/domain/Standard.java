package org.example.smart.domain;

import org.example.smart.domain.enums.StandardRank;
import org.example.smart.domain.enums.EvaluationCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Standard")
public class Standard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "standard_id", nullable = false)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "standard_rank", nullable = false)
	private StandardRank standardRank;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "evaluation_category", nullable = false)
	private EvaluationCategory evaluationCategory;

	@Column(name = "min_age", nullable = false)
	private Integer minAge;

	@Column(name = "max_age", nullable = false)
	private Integer maxAge;

	@Column(name = "min_value", nullable = false)
	private Integer minValue;

	@Column(name = "max_value", nullable = false)
	private Integer maxValue;
}
