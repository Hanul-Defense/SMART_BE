package org.example.smart.dto.response;

import org.example.smart.domain.Soldier;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ResponseSoldierDto(
	@JsonProperty(value = "name")
	String name,

	@JsonProperty(value = "rank")
	String rank
) {
	public static ResponseSoldierDto from(Soldier soldier) {
		return ResponseSoldierDto.builder()
			.name(soldier.getName())
			.rank(soldier.getMilitaryRank().getRank())
			.build();
	}
}
