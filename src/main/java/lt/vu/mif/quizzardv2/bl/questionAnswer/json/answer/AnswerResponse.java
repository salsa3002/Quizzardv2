package lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer;

import lombok.Builder;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;

@Builder
public class AnswerResponse {

    private Long id;
    private String answer;

    public static AnswerResponse of(Answer entity) {
        if (entity == null) {
            return null;
        }

        return AnswerResponse.builder()
                .id(entity.getId())
                .answer(entity.getAnswer())
                .build();
    }
}
