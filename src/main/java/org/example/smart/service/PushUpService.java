package org.example.smart.service;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.Standard;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.PushUpRepository;
import org.example.smart.repository.SoldierRepository;
import org.example.smart.repository.StandardRepository;
import org.example.smart.service.spec.EstimationService;
import org.example.smart.util.BirthUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service(value = "pushUpService")
@RequiredArgsConstructor
public class PushUpService implements EstimationService {
	private final SoldierRepository soldierRepository;
	private final PushUpRepository pushUpRepository;
	private final StandardRepository standardRepository;

	@Override
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		Integer age = BirthUtil.getAgeByBirth(soldier.getBirth());
		Standard standard = standardRepository.findByAgeAndCount(age, postEstimationDto.count())
			.orElseThrow();

		try {
			PushUp pushUp = PushUp.builder()
				.soldier(soldier)
				.count(postEstimationDto.count())
				.standard(standard)
				.evaluationType(postEstimationDto.evaluationType())
				.evaluationDate(postEstimationDto.evaluationDate())
				.summary(postEstimationDto.summary()) // TODO
				.contentUrl(null)
				.build();

			pushUpRepository.save(pushUp);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}
}
