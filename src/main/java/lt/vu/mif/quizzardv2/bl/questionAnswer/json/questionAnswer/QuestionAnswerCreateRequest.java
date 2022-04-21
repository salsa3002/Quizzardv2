package lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionCreateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@AllArgsConstructor
public class QuestionAnswerCreateRequest {

    @NotNull
    @Valid
    private QuestionCreateRequest question;

    @NotNull
    @Valid
    private Set<AnswerCreateRequest> answers;
}