package lt.vu.mif.quizzardv2.bl.question.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import lt.vu.mif.quizzardv2.model.question.enums.CategoryEnum;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionResponse {

    Long id;
    String question;
    CategoryEnum category;

    public static QuestionResponse of(Question entity) {
        if (entity == null) {
            return null;
        }

        return QuestionResponse.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .category(entity.getCategory())
                .build();
    }
}
