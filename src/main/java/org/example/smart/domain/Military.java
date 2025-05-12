package org.example.smart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.example.smart.domain.enums.MilitaryBranch;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Military")
public class Military {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "military_id", nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "military_branch", nullable = false)
	private MilitaryBranch militaryBranch;

	@Column(name = "military_name", nullable = false, length = 30)
	private String militaryName;

	@Builder
	public Military(MilitaryBranch militaryBranch, String militaryName) {
		this.militaryBranch = militaryBranch;
		this.militaryName = militaryName;
	}
}
