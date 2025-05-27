package org.example.smart.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostSignUpDto(
	@JsonProperty(value = "soldier_name", required = true)
	String soldierName,

	@JsonProperty(value = "birth", required = true)
	LocalDate birth,

	@JsonProperty(value = "service_number", required = true)
	String serviceNumber,

	@JsonProperty(value = "password", required = true)
	String password,

	@JsonProperty(value = "military_branch", required = true)
	String militaryBranch,

	@JsonProperty(value = "military_name", required = true)
	String militaryName,

	@JsonProperty(value = "company", required = true)
	Integer company,

	@JsonProperty(value = "platoon", required = true)
	Integer platoon,

	@JsonProperty(value = "military_rank", required = true)
	String militaryRank,

	@JsonProperty(value = "enlistment_date", required = true)
	LocalDate enlistmentDate
) {
}
