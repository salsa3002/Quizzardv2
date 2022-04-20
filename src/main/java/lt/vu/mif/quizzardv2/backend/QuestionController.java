package lt.vu.mif.quizzardv2.backend;

import lombok.RequiredArgsConstructor;
import lt.vu.mif.quizzardv2.bl.question.json.QuestionResponse;
import lt.vu.mif.quizzardv2.bl.question.json.QuestionSearch;
import lt.vu.mif.quizzardv2.bl.question.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public QuestionResponse findById(@PathVariable("id") Long id) {
        return questionService.findById(id);
    }

    @GetMapping
    public List<QuestionResponse> findAllBySearch(QuestionSearch search) {
        return questionService.findAllBySearch(search);
    }
}
