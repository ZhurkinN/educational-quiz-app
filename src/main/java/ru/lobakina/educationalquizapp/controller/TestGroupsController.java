package ru.lobakina.educationalquizapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.test.TestGroups;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.service.GroupService;
import ru.lobakina.educationalquizapp.service.TestGroupsService;
import ru.lobakina.educationalquizapp.service.TestService;
import ru.lobakina.educationalquizapp.support.dto.TestGroupsDTO;
import ru.lobakina.educationalquizapp.support.dto.TestStudentsDTO;

import java.util.List;

@Controller
@RequestMapping("/test-groups")
@RequiredArgsConstructor
public class TestGroupsController {

    private final TestGroupsService testGroupsService;
    private final TestService testService;
    private final GroupService groupService;

    @GetMapping("/{id}")
    public String getTeachersActiveGroupTests(@RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                              Model model,
                                              @PathVariable Long id) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<TestGroups> testPage = testGroupsService.findGroupTestsByTeacher(id, pageRequest);
        Page<TestGroups> finalTestPage = new PageImpl<>(testPage.getContent(), pageRequest, testPage.getTotalElements());
        model.addAttribute("records", finalTestPage);
        return "/test-groups/viewTests";
    }

    @GetMapping("/start/{id}")
    public String start(Model model,
                        @PathVariable Long id) {
        List<Test> tests = testService.getTeachersTests(id);
        List<Group> groups = groupService.getAll();
        model.addAttribute("tests", tests);
        model.addAttribute("groups", groups);
        model.addAttribute("teacherId", id);
        return "test-groups/assignTest";
    }

    @PostMapping("/start")
    public String start(@ModelAttribute("recordForm") TestGroupsDTO dto) {
        TestGroups testGroups = testGroupsService.assignTest(dto.getGroupId(), dto.getTestId());
        return "redirect:/test-groups/" + testGroups.getTest().getTeacher().getId();
    }

    @GetMapping("/results/{id}")
    public String getResults(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int pageSize,
                             Model model,
                             @PathVariable Long id) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<TestStudents> testPage = testGroupsService.getTestStudentsByTestGroupsId(id, pageRequest);
        Page<TestStudents> finalTestPage = new PageImpl<>(testPage.getContent(), pageRequest, testPage.getTotalElements());
        model.addAttribute("records", finalTestPage);
        return "/test-groups/viewResults";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        TestGroups testGroups = testGroupsService.getById(id);
        testGroupsService.delete(id);
        return "redirect:/test-groups/" + testGroups.getTest().getTeacher().getId();
    }
}
