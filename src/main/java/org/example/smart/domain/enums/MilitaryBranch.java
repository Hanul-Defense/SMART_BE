package org.example.smart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MilitaryBranch {
    ARMY("육군"), AIR("공군"), NAVY("해군"), MARINE("해병대"),
    ;
    private final String name;
}
