package lt.vu.mif.quizzardv2.bl.question.service;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.question.json.QuestionResponse;
import lt.vu.mif.quizzardv2.bl.question.json.QuestionSearch;
import lt.vu.mif.quizzardv2.bl.question.repository.QuestionRepository;
import lt.vu.mif.quizzardv2.bl.question.specification.QuestionSpecification;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponse findById(Long id) {
        return QuestionResponse.of(questionRepository.findByIdRequired(id));
    }

    public List<QuestionResponse> findAllBySearch(QuestionSearch search) {
        return questionRepository.findAll(QuestionSpecification.build(search), QuestionResponse::of);
    }
}
