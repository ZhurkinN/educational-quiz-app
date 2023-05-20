package ru.lobakina.educationalquizapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.controller.generic.GenericController;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.service.GroupService;
import ru.lobakina.educationalquizapp.service.generic.GenericService;
import ru.lobakina.educationalquizapp.support.dto.QuestionDTO;
import ru.lobakina.educationalquizapp.support.dto.UserDTO;

@Controller
@RequestMapping("/groups")
public class GroupController extends GenericController<Group> {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        super(groupService);
        this.groupService = groupService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         Model model) {
        Page<Group> groupPage = this.getAll(page, pageSize);
        model.addAttribute("groups", groupPage);
        return "/groups/viewGroups";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupService.delete(id);
        return "redirect:/groups";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @ModelAttribute("userSearchForm") Group group,
            Model model
    ) {
        if (!StringUtils.hasText(group.getGroupNumber()) && !StringUtils.hasLength(group.getGroupNumber())) {
            return "redirect:/groups";
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
            Page<Group> groupPage = groupService.findGroups(group.getGroupNumber(), pageRequest);
            Page<Group> finalGroupPage = new PageImpl<>(groupPage.getContent(), pageRequest, groupPage.getTotalElements());
            model.addAttribute("groups", finalGroupPage);
            return "groups/viewGroups";
        }
    }

    @GetMapping("/add")
    public String add() {
        return "groups/addGroup";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("groupForm") Group group) {
        groupService.create(group);
        return "redirect:/groups";
    }
}
