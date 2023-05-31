package ru.lobakina.educationalquizapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.service.TestService;
import ru.lobakina.educationalquizapp.service.TestStudentsService;
import ru.lobakina.educationalquizapp.service.UserService;
import ru.lobakina.educationalquizapp.support.dto.TestStudentsDTO;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/test-students")
@RequiredArgsConstructor
public class TestStudentsController {

    private final TestStudentsService testStudentsService;
    private final TestService testService;
    private final UserService userService;

    @GetMapping("/active/{id}")
    public String getTeachersActiveTests(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                         Model model,
                                         @PathVariable Long id) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<TestStudents> testPage = testStudentsService.findActiveTestsByTeacher(id, pageRequest);
        Page<TestStudents> finalTestPage = new PageImpl<>(testPage.getContent(), pageRequest, testPage.getTotalElements());
        model.addAttribute("records", finalTestPage);
        return "/test-students/viewActive";
    }

    @GetMapping("/archive/{id}")
    public String getTeachersArchiveTests(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                          Model model,
                                          @PathVariable Long id) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<TestStudents> testPage = testStudentsService.findArchiveTestsByTeacher(id, pageRequest);
        Page<TestStudents> finalTestPage = new PageImpl<>(testPage.getContent(), pageRequest, testPage.getTotalElements());
        model.addAttribute("records", finalTestPage);
        return "/test-students/viewArchive";
    }

    @GetMapping("/start/{id}")
    public String start(Model model,
                        @PathVariable Long id) {
        List<Test> tests = testService.getTeachersTests(id);
        List<User> students = userService.getAllStudents();
        model.addAttribute("tests", tests);
        model.addAttribute("students", students);
        return "test-students/assignTest";
    }

    @PostMapping("/start")
    public String start(@ModelAttribute("recordForm") TestStudentsDTO dto) {
        Test test = testService.getById(dto.getTestId());
        if (test.getQuestions().size() < 3) {
            return "redirect:/test-students/assignTest";
        }
        TestStudents testStudents = testStudentsService.assignTest(dto.getStudentId(), dto.getTestId());
        return "redirect:/test-students/active/" + testStudents.getTest().getTeacher().getId();
    }

    @GetMapping("/return/{id}")
    public String delete(@PathVariable Long id) {
        TestStudents testStudents = testStudentsService.getById(id);
        testStudentsService.returnTest(id);
        return "redirect:/test-students/archive/" + testStudents.getTest().getTeacher().getId();
    }

    @GetMapping("/given/{id}")
    public String getPersonalStudentsTests(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                           Model model,
                                           @PathVariable Long id) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<TestStudents> testPage = testStudentsService.findTestsByStudent(id, pageRequest);
        Page<TestStudents> finalTestPage = new PageImpl<>(testPage.getContent(), pageRequest, testPage.getTotalElements());
        model.addAttribute("records", finalTestPage);
        return "/test-students/viewStudentTests";
    }

    @GetMapping("/perform/{id}")
    public String performTest(@PathVariable Long id,
                              Model model) {
        TestStudents testStudents = testStudentsService.getById(id);
        Test test = testStudents.getTest();
        Set<Question> questions = test.getQuestions();

        model.addAttribute("questions", questions);
        model.addAttribute("test", test);
        return "tests/performTest";
    }

}
