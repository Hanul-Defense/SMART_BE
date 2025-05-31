package org.example.smart.domain.enums;

import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvaluationCategory {
	PUSH_UP("푸시업"), SIT_UP("싯업"), RUNNING("3km"),
	;

	private final String categoryName;

	public static EvaluationCategory getEvaluationCategoryByCategoryName(String categoryName) {
		switch (categoryName) {
			case "푸시업" -> {
				return EvaluationCategory.PUSH_UP;
			}
			case "싯업"->{
				return EvaluationCategory.SIT_UP;
			}
			case "3km"->{
				return EvaluationCategory.RUNNING;
			}
		}
		throw new GlobalException(ErrorCode.BAD_REQUEST);
	}
}
