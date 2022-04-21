package lt.vu.mif.quizzardv2.backend;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.question.QuestionUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.service.QuestionService;
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
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public QuestionResponse readById(@PathVariable("id") Long id) {
        return questionService.readById(id);
    }

    @GetMapping
    public List<QuestionResponse> readAll() {
        return questionService.readAll();
    }

    @PostMapping
    public Long create(@Valid @RequestBody QuestionCreateRequest request) {
        return questionService.create(request);
    }

    @PutMapping
    public void update(@Valid @RequestBody QuestionUpdateRequest request) {
        questionService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        questionService.delete(id);
    }
}
