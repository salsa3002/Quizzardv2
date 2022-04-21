package lt.vu.mif.quizzardv2.backend;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer.QuestionAnswerCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer.QuestionAnswerResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.questionAnswer.QuestionAnswerUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.service.QuestionAnswerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questions_answers")
public class QuestionAnswerController {

    private final QuestionAnswerService questionAnswerService;

    @GetMapping("/{id}")
    public QuestionAnswerResponse readById(@PathVariable("id") Long id) {
        return questionAnswerService.readById(id);
    }

    @GetMapping
    public List<QuestionAnswerResponse> readAll() {
        return questionAnswerService.readAll();
    }

    @PostMapping
    public Long create(@Valid @RequestBody QuestionAnswerCreateRequest request) {
        return questionAnswerService.create(request);
    }

    @PutMapping
    public void update(@Valid @RequestBody QuestionAnswerUpdateRequest request) {
        questionAnswerService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        questionAnswerService.delete(id);
    }
}
