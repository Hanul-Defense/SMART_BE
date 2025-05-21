package org.example.smart.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseUnitNameListDto(
	@JsonProperty(value = "name_list")
	List<String> unitName
) {
}
