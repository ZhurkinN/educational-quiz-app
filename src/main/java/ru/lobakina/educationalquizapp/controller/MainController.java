package ru.lobakina.educationalquizapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.user.RoleRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.service.UserService;

import static ru.lobakina.educationalquizapp.model.enums.Roles.STUDENT;
import static ru.lobakina.educationalquizapp.support.constants.RoleNameConstantsKeeper.ADMIN;

/**
 * Controller for registration and authorization
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Shows main page
     *
     * @return page
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Authorizes user
     *
     * @return page
     */
    @GetMapping("/login")
    public String login() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "users/login";
    }

    /**
     * Show registration page
     *
     * @return page
     */
    @GetMapping("/register")
    public String register() {
        return "users/register";
    }

    /**
     * Registers user
     *
     * @param user          user's info
     * @param bindingResult binding for errors
     * @return page
     */
    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User user,
                               BindingResult bindingResult) {
        if (user.getLogin().equals(ADMIN.toLowerCase()) || userRepository.existsByLogin(user.getLogin())) {
            bindingResult.rejectValue("login", "error.login", "Пользователь с таким логином уже зарегистрирован");
            return "redirect:/login";
        }
        user.setRole(roleRepository.findByRoleName(STUDENT.getRoleName()));
        userService.create(user);
        return "redirect:/login";
    }
}
