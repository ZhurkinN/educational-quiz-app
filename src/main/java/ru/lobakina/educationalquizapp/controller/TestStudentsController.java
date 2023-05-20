package ru.lobakina.educationalquizapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lobakina.educationalquizapp.service.TestStudentsService;

@Controller
@RequestMapping("/test-students")
@RequiredArgsConstructor
public class TestStudentsController {

    private final TestStudentsService testStudentsService;

    @GetMapping("/active/{id}")
    public String getTeachersTests(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                   Model model,
                                   @PathVariable Long id) {
        return "/test-students/startTest";
    }
}
