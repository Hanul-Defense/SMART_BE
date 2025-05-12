package org.example.smart.domain.enums;

import lombok.AllArgsConstructor;

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
}
