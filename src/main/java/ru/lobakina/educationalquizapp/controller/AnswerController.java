package ru.lobakina.educationalquizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.controller.generic.GenericController;
import ru.lobakina.educationalquizapp.model.question.Answer;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.service.AnswerService;
import ru.lobakina.educationalquizapp.service.QuestionService;
import ru.lobakina.educationalquizapp.support.dto.AnswerDTO;
import ru.lobakina.educationalquizapp.support.dto.QuestionDTO;
import ru.lobakina.educationalquizapp.support.mapper.AnswerMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/answers")
public class AnswerController extends GenericController<Answer> {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final QuestionService questionService;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper, QuestionService questionService) {
        super(answerService);
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public String getAll(Model model,
                         @PathVariable Long id) {
        List<Answer> answers = answerService.getByQuestion(id);
        model.addAttribute("answers", answers);
        model.addAttribute("question", questionService.getById(id));
        model.addAttribute("right", questionService.getById(id)
                .getQuestionAnswers()
                .stream()
                .filter(Answer::getIsRight)
                .toList()
                .size());
        return "/answers/viewAnswers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Answer answer = answerService.getById(id);
        answerService.delete(id);
        return "redirect:/answers/" + answer.getQuestion().getId();
    }

    @GetMapping("/add/{id}")
    public String add(Model model,
                      @PathVariable Long id) {
        model.addAttribute("questionId", id);
        return "answers/addAnswer";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("answerForm") AnswerDTO answerDTO) {
        Answer answer = answerMapper.toEntity(answerDTO);
        answer.setIsRight(true);
        answerService.create(answer);
        return "redirect:/answers/" + answer.getQuestion().getId();
    }

    @GetMapping("/add-variant/{id}")
    public String addVariant(Model model,
                      @PathVariable Long id) {
        model.addAttribute("questionId", id);
        return "answers/addVariant";
    }

    @PostMapping("/add-variant")
    public String addVariant(@ModelAttribute("answerForm") AnswerDTO answerDTO) {
        Answer answer = answerMapper.toEntity(answerDTO);
        answer.setIsRight(false);
        answerService.create(answer);
        return "redirect:/answers/" + answer.getQuestion().getId();
    }
}
