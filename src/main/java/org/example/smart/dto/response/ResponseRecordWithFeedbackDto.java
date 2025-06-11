package org.example.smart.dto.response;

import java.time.LocalDateTime;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.PushUpFeedback;
import org.example.smart.domain.SitUp;
import org.example.smart.domain.SitUpFeedback;
import org.example.smart.domain.enums.EvaluationCategory;
import org.example.smart.domain.enums.EvaluationType;
import org.example.smart.util.SoldierUtil;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ResponseRecordWithFeedbackDto(

	@JsonProperty(value = "category_name")
	String categoryName,

	@JsonProperty(value = "record")
	Integer count,

	@JsonProperty(value = "rank")
	String rank,

	@JsonProperty(value = "summary")
	String summary,

	@JsonProperty(value = "feedback_writer")
	String feedbackWriter,

	@JsonProperty(value = "feedback_content")
	String feedbackContent,

	@JsonProperty(value = "evaluation_type")
	EvaluationType evaluationType,

	@JsonProperty(value = "evaluation_date")
	LocalDateTime evaluationDate
) {
	public static ResponseRecordWithFeedbackDto fromPushUp(PushUp pushUp) {
		PushUpFeedback feedback = pushUp.getPushUpFeedback();

		return ResponseRecordWithFeedbackDto.builder()
			.categoryName(EvaluationCategory.PUSH_UP.getCategoryName())
			.count(pushUp.getCount())
			.rank(pushUp.getStandard().getStandardRank().getRankName())
			.summary(pushUp.getSummary())
			.feedbackWriter(feedback != null ? SoldierUtil.getNameWithRank(feedback.getSoldier()) : null)
			.feedbackContent(feedback != null ? feedback.getFeedbackContent() : null)
			.evaluationType(pushUp.getEvaluationType())
			.evaluationDate(pushUp.getEvaluationDate())
			.build();
	}

	public static ResponseRecordWithFeedbackDto fromSitUp(SitUp sitUp) {
		SitUpFeedback feedback = sitUp.getSitUpFeedback();

		return ResponseRecordWithFeedbackDto.builder()
			.categoryName(EvaluationCategory.SIT_UP.getCategoryName())
			.count(sitUp.getCount())
			.rank(sitUp.getStandard().getStandardRank().getRankName())
			.summary(sitUp.getSummary())
			.feedbackWriter(feedback != null ? SoldierUtil.getNameWithRank(feedback.getSoldier()) : null)
			.feedbackContent(feedback != null ? feedback.getFeedbackContent() : null)
			.evaluationType(sitUp.getEvaluationType())
			.evaluationDate(sitUp.getEvaluationDate())
			.build();
	}
}
