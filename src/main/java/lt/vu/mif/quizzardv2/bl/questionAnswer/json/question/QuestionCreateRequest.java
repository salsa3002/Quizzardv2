package lt.vu.mif.quizzardv2.bl.questionAnswer.json.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.vu.mif.quizzardv2.bl.utils.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class QuestionCreateRequest {

    @NotBlank
    @Size(max = Constants.MAX_LENGTH_QUESTION)
    private String question;
}
