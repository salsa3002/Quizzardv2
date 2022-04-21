package lt.vu.mif.quizzardv2.bl.questionAnswer.service;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer.QuestionAnswerCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer.QuestionAnswerResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer.QuestionAnswerUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.repository.AnswerRepository;
import lt.vu.mif.quizzardv2.bl.questionAnswer.repository.QuestionRepository;
import lt.vu.mif.quizzardv2.bl.questionAnswer.specification.AnswerSpecification;
import lt.vu.mif.quizzardv2.model.answer.entity.Answer;
import lt.vu.mif.quizzardv2.model.question.entity.Question;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionAnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionAnswerResponse readById(Long id) {
        return QuestionAnswerResponse.of(
                questionRepository.findByIdRequired(id),
                new HashSet<>(answerRepository.findAll(AnswerSpecification.build(id))));
    }

    public List<QuestionAnswerResponse> readAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(question -> QuestionAnswerResponse.of(question, question.getAnswers())).collect(Collectors.toList());
    }

    public Long create(QuestionAnswerCreateRequest request) {

        // TODO - Validate that at least 1 answer is selected as correct

        Question question = questionRepository.save(Question.builder()
                .question(request.getQuestion().getQuestion())
                .build());

        answerRepository.saveAll(request.getAnswers().stream().map(answer -> Answer.builder()
                .answer(answer.getAnswer())
                .correct(answer.isCorrect())
                .question(question)
                .build()).collect(Collectors.toList()));

        return question.getId();
    }

    public void update(QuestionAnswerUpdateRequest request) {

        // TODO - Validate that at least 1 answer is selected as correct

        Question question = questionRepository.findByIdRequired(request.getQuestion().getId());
        question.setQuestion(request.getQuestion().getQuestion());

        List<Answer> answers = answerRepository.findAllById(request.getAnswers().stream().map(AnswerUpdateRequest::getId).collect(Collectors.toList()));
        answers.forEach(entity -> request.getAnswers().forEach(answer -> {
            if (Objects.equals(entity.getId(), answer.getId())) {
                entity.setAnswer(answer.getAnswer());
                entity.setCorrect(answer.isCorrect());
            }
        }));
        answerRepository.saveAll(answers);
    }

    public void delete(Long id) {
        answerRepository.deleteAll(answerRepository.findAll(AnswerSpecification.build(id)));
        questionRepository.deleteById(id);
    }
}
