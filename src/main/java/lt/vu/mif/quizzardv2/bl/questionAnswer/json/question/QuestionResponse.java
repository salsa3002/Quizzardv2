package lt.vu.mif.quizzardv2.bl.questionAnswer.json.question;

import lombok.Builder;
import lt.vu.mif.quizzardv2.model.question.entity.Question;

@Builder
public class QuestionResponse {

    private Long id;
    private String question;

    public static QuestionResponse of(Question entity) {
        if (entity == null) {
            return null;
        }

        return QuestionResponse.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .build();
    }
}
