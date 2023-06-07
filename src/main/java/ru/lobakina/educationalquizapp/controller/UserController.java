package ru.lobakina.educationalquizapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lobakina.educationalquizapp.controller.generic.GenericController;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.user.GroupRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.service.GroupService;
import ru.lobakina.educationalquizapp.service.UserService;
import ru.lobakina.educationalquizapp.support.dto.UserDTO;
import ru.lobakina.educationalquizapp.support.helper.BubbleSorter;
import ru.lobakina.educationalquizapp.support.mapper.UserMapper;

import java.util.List;
import java.util.Objects;

import static ru.lobakina.educationalquizapp.support.constants.RoleNameConstantsKeeper.ADMIN;

/**
 * Controller for all actions with users
 */
@Controller
@RequestMapping("/users")
public class UserController extends GenericController<User> {

    private final UserService userService;
    private final GroupService groupService;
    private final UserMapper userMapper;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userService     user's service
     * @param groupService    group's service
     * @param userMapper      user's mapper
     * @param groupRepository group's repo
     * @param userRepository  user's repo
     */
    public UserController(UserService userService,
                          GroupService groupService,
                          UserMapper userMapper,
                          GroupRepository groupRepository,
                          UserRepository userRepository) {
        super(userService);
        this.userService = userService;
        this.groupService = groupService;
        this.userMapper = userMapper;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    /**
     * Shows all users
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
        Page<User> userPage = this.getAll(page, pageSize);
        model.addAttribute("users", userPage);
        return "/users/viewUsers";
    }

    /**
     * Sorts users by FIO
     *
     * @param page     page
     * @param pageSize size of page
     * @param model    model
     * @return page
     */
    @GetMapping("/sorted")
    public String getAllSorted(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               Model model) {
        List<User> users = BubbleSorter.sortUsers(userService.getAll().toArray(new User[0]));
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "firstName"));
        Page<User> userPage = new PageImpl<>(users, pageRequest, users.size());
        model.addAttribute("users", userPage);
        return "/users/viewSortedUsers";
    }

    /**
     * Shows page for adding users
     *
     * @param model model
     * @return page
     */
    @GetMapping("/add")
    public String addUser(Model model) {
        List<String> groups = groupService.getAll()
                .stream()
                .map(Group::getGroupNumber)
                .toList();
        model.addAttribute("groups", groups);
        return "users/addUser";
    }

    /**
     * Adds new user
     *
     * @param userDto       user's info
     * @param bindingResult binding for errors
     * @return page
     */
    @PostMapping("/add")
    public String addUser(@ModelAttribute("userForm") UserDTO userDto,
                          BindingResult bindingResult) {
        if (userDto.getLogin().equals(ADMIN.toLowerCase()) || userRepository.existsByLogin(userDto.getLogin())) {
            bindingResult.rejectValue("login", "error.login", "Пользователь с таким логином уже зарегистрирован");
            return "redirect:/users";
        }
        userService.create(userMapper.toEntity(userDto));
        return "redirect:/users";
    }

    /**
     * Deletes user
     *
     * @param id user's id
     * @return page
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    /**
     * Shows page for updating users
     *
     * @param id    user's id
     * @param model model
     * @return page
     */
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        List<String> groups = groupService.getAll()
                .stream()
                .map(Group::getGroupNumber)
                .toList();
        model.addAttribute("groups", groups);
        model.addAttribute("user", userService.getById(id));
        return "users/updateUser";
    }

    /**
     * Updates user
     *
     * @param userDto       user's info
     * @param bindingResult binding for errors
     * @return page
     */
    @PostMapping("/update")
    public String update(@ModelAttribute("userForm") UserDTO userDto,
                         BindingResult bindingResult) {
        if (userDto.getLogin().equals(ADMIN.toLowerCase())) {
            bindingResult.rejectValue("login", "error.login", "Пользователь с таким логином уже зарегистрирован");
            return "redirect:/users";
        }
        User user = userService.getById(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        if (!Objects.isNull(userDto.getGroup())) {
            user.setGroup(groupRepository.findByGroupNumber(userDto.getGroup()));
        }
        userService.update(user);
        return "redirect:/users";
    }

    /**
     * Seaches users
     *
     * @param page    page
     * @param size    size of page
     * @param userDTO search details
     * @param model   model
     * @return page
     */
    @PostMapping("/search")
    public String searchUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @ModelAttribute("userSearchForm") UserDTO userDTO,
            Model model
    ) {
        if (!StringUtils.hasText(userDTO.getGroup()) && !StringUtils.hasText(userDTO.getLogin())) {
            return "redirect:/";
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
            Page<User> userPage = userService.findUsers(userDTO.getGroup(), userDTO.getLogin(), pageRequest);
            Page<User> finalUserPage = new PageImpl<>(userPage.getContent(), pageRequest, userPage.getTotalElements());
            model.addAttribute("users", finalUserPage);
            return "users/viewUsers";
        }
    }

    /**
     * Shows all students
     *
     * @param page     page
     * @param pageSize size of page
     * @param model    model
     * @return page
     */
    @GetMapping("/students")
    public String getStudents(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "10") int pageSize,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> modelPage = userService.getStudents(pageRequest);
        Page<User> studentsPage = new PageImpl<>(modelPage.getContent(), pageRequest, modelPage.getTotalElements());
        model.addAttribute("users", studentsPage);
        return "/users/viewStudents";
    }

}
