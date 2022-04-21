package lt.vu.mif.quizzardv2.backend;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerIsCorrectResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerCreateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerResponse;
import lt.vu.mif.quizzardv2.bl.questionAnswer.json.answer.AnswerUpdateRequest;
import lt.vu.mif.quizzardv2.bl.questionAnswer.service.AnswerService;
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
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public AnswerResponse readById(@PathVariable("id") Long id) {
        return answerService.readById(id);
    }

    @GetMapping
    public List<AnswerResponse> readAll() {
        return answerService.readAll();
    }

    @PostMapping
    public Long create(@Valid @RequestBody AnswerCreateRequest request) {
        return answerService.create(request);
    }

    @PutMapping
    public void update(@Valid @RequestBody AnswerUpdateRequest request) {
        answerService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        answerService.delete(id);
    }

    @GetMapping("/check/{id}")
    public AnswerIsCorrectResponse checkCorrectById(@PathVariable("id") Long id) {
        return answerService.checkIfCorrectById(id);
    }
}
