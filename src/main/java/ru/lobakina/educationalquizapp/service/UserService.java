package ru.lobakina.educationalquizapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for all actions with users
 */
@Service
public class UserService extends GenericService<User> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor
     *
     * @param userRepository        user's repo
     * @param bCryptPasswordEncoder encoder
     */
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Creates new user
     *
     * @param model model
     * @return new user
     */
    @Override
    public User create(User model) {
        model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        model.setCreatedWhen(LocalDateTime.now());
        return userRepository.save(model);
    }

    /**
     * Searches users
     *
     * @param group    group
     * @param login    login
     * @param pageable page
     * @return page
     */
    public Page<User> findUsers(String group, String login, Pageable pageable) {
        if (!group.equals("")) {
            return userRepository.searchStudents(
                    group,
                    login,
                    pageable
            );
        }
        return userRepository.searchUsers(
                login,
                pageable
        );

    }

    /**
     * Gets all students with pages
     *
     * @param pageable page
     * @return page
     */
    public Page<User> getStudents(Pageable pageable) {
        return userRepository.findStudents(pageable);
    }

    /**
     * Gets all students
     *
     * @return page
     */
    public List<User> getAllStudents() {
        return userRepository.findStudents();
    }
}
