package lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.vu.mif.quizzardv2.bl.utils.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class AnswerCreateRequest {

    @NotBlank
    @Size(max = Constants.MAX_LENGTH_ANSWER)
    private String answer;

    private boolean correct;
}
