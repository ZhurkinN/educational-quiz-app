package ru.lobakina.educationalquizapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.model.test.Subject;
import ru.lobakina.educationalquizapp.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public String getAll(Model model) {
        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("subjects", subjects);
        return "/subjects/viewSubjects";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "subjects/addSubject";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("subjectForm") Subject subject) {
        subjectService.create(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        subjectService.delete(id);
        return "redirect:/subjects";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable Long id) {
        model.addAttribute("subject", subjectService.getById(id));
        return "subjects/updateSubject";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("subjectForm") Subject subject) {
        subjectService.update(subject);
        return "redirect:/subjects";
    }
}
