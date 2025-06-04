package org.example.smart.util;

import java.time.LocalDate;

import org.example.smart.domain.Soldier;

public class SoldierUtil {
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

	public static String getNameWithRank(Soldier soldier) {
		if (soldier != null) {
			return soldier.getName() + " " + soldier.getMilitaryRank().getRank();
		}
		return null;
	}
}
