package org.example.smart.domain.enums;

import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvaluationCategory {
	PUSH_UP("푸시업"), SIT_UP("윗몸일으키기"), RUNNING("3km 뜀걸음"),
	;

	private final String categoryName;

	public static EvaluationCategory getEvaluationCategoryByCategoryName(String categoryName) {
		switch (categoryName) {
			case "푸시업" -> {
				return EvaluationCategory.PUSH_UP;
			}
			case "윗몸일으키기"->{
				return EvaluationCategory.SIT_UP;
			}
			case "3km 뜀걸음"->{
				return EvaluationCategory.RUNNING;
			}
		}
		throw new GlobalException(ErrorCode.BAD_REQUEST);
	}
}
