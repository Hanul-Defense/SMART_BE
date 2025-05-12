package org.example.smart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvaluationCategory {
	PUSH_UP("푸시업"), SIT_UP("윗몸일으키기"), RUNNING("3km 뜀걸음"),
	;

	private final String categoryName;
}
