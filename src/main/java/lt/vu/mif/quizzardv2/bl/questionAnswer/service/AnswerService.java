package lt.vu.mif.quizzardv2.bl.questionAnswer.service;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerIsCorrectResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.repository.AnswerRepository;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerResponse readById(Long id) {
        return AnswerResponse.of(answerRepository.findByIdRequired(id));
    }

    public List<AnswerResponse> readAll() {
        return answerRepository.findAll().stream().map(AnswerResponse::of).collect(Collectors.toList());
    }

    public Long create(AnswerCreateRequest request) {
        return answerRepository.save(
                Answer.builder()
                        .answer(request.getAnswer())
                        .correct(request.isCorrect())
                        .build())
                .getId();
    }

    public void update(AnswerUpdateRequest request) {
        Answer entity = answerRepository.findByIdRequired(request.getId());
        entity.setAnswer(request.getAnswer());
        entity.setCorrect(request.isCorrect());
        answerRepository.save(entity);
    }

    public void delete(Long id) {
        answerRepository.deleteById(id);
    }

    public AnswerIsCorrectResponse checkIfCorrectById(Long id) {
        return AnswerIsCorrectResponse.of(answerRepository.findByIdRequired(id));
    }
}
