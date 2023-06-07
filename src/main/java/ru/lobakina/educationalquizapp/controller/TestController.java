package ru.lobakina.educationalquizapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.controller.generic.GenericController;
import ru.lobakina.educationalquizapp.model.test.Subject;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.service.SubjectService;
import ru.lobakina.educationalquizapp.service.TestService;
import ru.lobakina.educationalquizapp.support.dto.TestDTO;
import ru.lobakina.educationalquizapp.support.mapper.TestMapper;

import java.util.List;

/**
 * Controller for all actions with tests
 */
@Controller
@RequestMapping("/tests")
public class TestController extends GenericController<Test> {

    private final TestService testService;
    private final TestMapper testMapper;
    private final SubjectService subjectService;


    /**
     * Constructor
     *
     * @param testService    service
     * @param testMapper     mapper
     * @param subjectService subject service
     */
    public TestController(TestService testService, TestMapper testMapper, SubjectService subjectService) {
        super(testService);
        this.testService = testService;
        this.testMapper = testMapper;
        this.subjectService = subjectService;
    }

    /**
     * Shows all created tests
     *
     * @param page     page
     * @param pageSize size of page
     * @param model    model
     * @return page
     */
    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         Model model) {
        Page<Test> testPage = this.getAll(page, pageSize);
        model.addAttribute("tests", testPage);
        return "/tests/viewTests";
    }

    /**
     * Shows all teacher's created tests
     *
     * @param page     page
     * @param pageSize size of page
     * @param id       teacher id
     * @param model    model
     */
    @GetMapping("/history/{id}")
    public String getById(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "10") int pageSize,
                          @PathVariable Long id,
                          Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Test> testPage = testService.findByTeacher(id, pageRequest);
        Page<Test> finalTestPage = new PageImpl<>(testPage.getContent(), pageRequest, testPage.getTotalElements());
        model.addAttribute("tests", finalTestPage);
        return "/tests/viewTests";
    }

    /**
     * Shows page for adding new test
     *
     * @param model model
     * @return page
     */
    @GetMapping("/add")
    public String addTest(Model model) {
        List<String> subjects = subjectService.getAll()
                .stream()
                .map(Subject::getTitle)
                .toList();
        model.addAttribute("subjects", subjects);
        return "tests/addTest";
    }

    /**
     * Adds new test
     *
     * @param testDTO test info
     * @return page
     */
    @PostMapping("/add")
    public String addTest(@ModelAttribute("testForm") TestDTO testDTO) {
        testService.create(testMapper.toEntity(testDTO));
        return "redirect:/tests/history/" + testDTO.getTeacher();
    }

    /**
     * Deletes test
     *
     * @param id tests id
     * @return page
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        testService.delete(id);
        return "redirect:/";
    }

    /**
     * Shows page for updating test
     *
     * @param id    test id
     * @param model model
     * @return page
     */
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        List<String> subjects = subjectService.getAll()
                .stream()
                .map(Subject::getTitle)
                .toList();
        model.addAttribute("subjects", subjects);
        model.addAttribute("test", testService.getById(id));
        return "tests/updateTest";
    }

    /**
     * Updates test
     *
     * @param testDTO test's new info
     * @return page
     */
    @PostMapping("/update")
    public String updateTest(@ModelAttribute("testForm") TestDTO testDTO) {
        testService.update(testMapper.toEntity(testDTO));
        return "redirect:/tests/history/" + testDTO.getTeacher();
    }

}
