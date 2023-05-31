package ru.lobakina.educationalquizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.controller.generic.GenericController;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.service.QuestionService;
import ru.lobakina.educationalquizapp.support.dto.QuestionDTO;
import ru.lobakina.educationalquizapp.support.mapper.QuestionMapper;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController extends GenericController<Question> {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        super(questionService);
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,
                          Model model) {
        List<Question> questions = questionService.findQuestionsByTest(id);
        model.addAttribute("questions", questions);
        model.addAttribute("testId", id);
        return "/questions/viewQuestions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Question question = questionService.getById(id);
        questionService.delete(id);
        return "redirect:/questions/" + question.getTest().getId();
    }

    @GetMapping("/add/{id}")
    public String add(Model model,
                      @PathVariable Long id) {
        model.addAttribute("testId", id);
        return "questions/addQuestion";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("questionForm") QuestionDTO questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        questionService.create(question);
        return "redirect:/questions/" + question.getTest().getId();
    }
}
