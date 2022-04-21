package lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionUpdateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@AllArgsConstructor
public class QuestionAnswerUpdateRequest {

    @NotNull
    @Valid
    private QuestionUpdateRequest question;

    @NotNull
    @Valid
    private Set<AnswerUpdateRequest> answers;
}
