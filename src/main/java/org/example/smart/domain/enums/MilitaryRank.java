package org.example.smart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MilitaryRank {
	PVT("이등병"), PFC("일병"), CPL("상병"), SGT("병장"),
	SSG("하사"), PSG("중사"), MSG("상사"), FSG("일등상사"), SGM("원사"), CSM("주임원사"),
	CWO1("준위 1호봉"), CWO2("준위 2호봉"), CWO3("준위 3호봉"), CWO4("준위 4호봉"), CWO5("준위 5호봉"),
	ST("소위"), LT("중위"), CPT("대위"),
	MAJ("소령"), LCL("중령"), COL("대령"),
	BG("준장"), MG("소장"), LG("중장"), GEN("대장"),
	GA("원수"),
	;

	private final String rank;

	public static MilitaryRank getMilitaryRankByRank(String rank) {
		return switch (rank) {
			case "이등병" -> PVT;
			case "일병" -> PFC;
			case "상병" -> CPL;
			case "병장" -> SGT;
			case "하사" -> SSG;
			case "중사" -> PSG;
			case "상사" -> MSG;
			case "일등상사" -> FSG;
			case "원사" -> SGM;
			case "주임원사" -> CSM;
			case "준위 1호봉" -> CWO1;
			case "준위 2호봉" -> CWO2;
			case "준위 3호봉" -> CWO3;
			case "준위 4호봉" -> CWO4;
			case "준위 5호봉" -> CWO5;
			case "소위" -> ST;
			case "중위" -> LT;
			case "대위" -> CPT;
			case "소령" -> MAJ;
			case "중령" -> LCL;
			case "대령" -> COL;
			case "준장" -> BG;
			case "소장" -> MG;
			case "중장" -> LG;
			case "대장" -> GEN;
			case "원수" -> GA;
			default -> null;
		};
	}
}
