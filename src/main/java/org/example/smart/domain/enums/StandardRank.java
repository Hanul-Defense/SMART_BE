package org.example.smart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StandardRank {
	SPECIAL("특급"),

	FIRST("1급"),

	SECOND("2급"),

	THIRD("3급"),
	;

	private final String rankName;
}
