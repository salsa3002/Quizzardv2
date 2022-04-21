package lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer;

import lombok.Builder;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;

@Builder
public class AnswerIsCorrectResponse {

    private Long id;
    private boolean correct;

    public static AnswerIsCorrectResponse of(Answer entity) {
        if (entity == null) {
            return null;
        }

        return AnswerIsCorrectResponse.builder()
                .id(entity.getId())
                .correct(entity.isCorrect())
                .build();
    }
}
