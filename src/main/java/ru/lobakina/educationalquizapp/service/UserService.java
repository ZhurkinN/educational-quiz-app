package ru.lobakina.educationalquizapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

import java.time.LocalDateTime;

@Service
public class UserService extends GenericService<User> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User create(User model) {
        model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        model.setCreatedWhen(LocalDateTime.now());
        return userRepository.save(model);
    }

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

    public Page<User> getStudents(Pageable pageable) {
        return userRepository.findStudents(pageable);
    }
}
