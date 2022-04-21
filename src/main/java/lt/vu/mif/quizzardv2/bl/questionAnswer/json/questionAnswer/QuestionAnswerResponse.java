package lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer;

import lombok.Builder;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionResponse;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;
import lt.vu.mif.quizzardv2.model.question.entity.Question;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public class QuestionAnswerResponse {
    
    private QuestionResponse question;
    private Set<AnswerResponse> answers;
    
    public static QuestionAnswerResponse of(Question entity, Set<Answer> answers) {
        if (entity == null) {
            return null;
        }
        
        return QuestionAnswerResponse.builder()
                .question(QuestionResponse.of(entity))
                .answers(answers.stream().map(AnswerResponse::of).collect(Collectors.toSet()))
                .build();
    }
}
