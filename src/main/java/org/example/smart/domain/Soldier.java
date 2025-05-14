package org.example.smart.domain;

import java.time.LocalDate;

import org.example.smart.domain.enums.MilitaryRank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Soldier")
public class Soldier {
	@Id
	@Column(name = "soldier_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "military_id", nullable = false)
	private Military military;

	@Column(name = "soldier_name", nullable = false, length = 10)
	private String name;

	@Column(name = "service_number", nullable = false, length = 11)
	private String serviceNumber;

	@Column(name = "password", nullable = false, length = 20)
	private String password;

	@Column(name = "military_rank", nullable = false)
	private MilitaryRank militaryRank;

	@Column(name = "enlistment_date", nullable = false)
	private LocalDate enlistmentDate;

	@Builder
	public Soldier(Military military, String name, String serviceNumber, String password, MilitaryRank militaryRank,
		LocalDate enlistmentDate) {
		this.military = military;
		this.name = name;
		this.serviceNumber = serviceNumber;
		this.password = password;
		this.militaryRank = militaryRank;
		this.enlistmentDate = enlistmentDate;
	}
}
