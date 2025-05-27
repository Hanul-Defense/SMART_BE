package org.example.smart.util;

import java.time.LocalDate;

import lombok.experimental.UtilityClass;

public class BirthUtil {
	public static Integer getAgeByBirth(LocalDate birth) {
		LocalDate now = LocalDate.now();
		Integer age = now.getYear() - birth.getYear();
		if (now.getMonthValue() > birth.getMonthValue()) {
			age++;
		} else if (now.getMonthValue() == birth.getMonthValue()) {
			age = (now.getDayOfMonth() >= birth.getDayOfMonth()) ? age + 1 : age;
		}
		return age;
	}
}
