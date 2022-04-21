package lt.vu.mif.quizzardv2.bl.questionAnswer.service;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.repository.QuestionRepository;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponse readById(Long id) {
        return QuestionResponse.of(questionRepository.findByIdRequired(id));
    }

    public List<QuestionResponse> readAll() {
        return questionRepository.findAll().stream().map(QuestionResponse::of).collect(Collectors.toList());
    }

    public Long create(QuestionCreateRequest request) {
        return questionRepository.save(
                Question.builder()
                        .question(request.getQuestion())
                        .build())
                .getId();
    }

    public void update(QuestionUpdateRequest request) {
        Question entity = questionRepository.findByIdRequired(request.getId());
        entity.setQuestion(request.getQuestion());
        questionRepository.save(entity);
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
