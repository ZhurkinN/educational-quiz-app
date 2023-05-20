package ru.lobakina.educationalquizapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

import java.time.LocalDateTime;

@Service
public class TestService extends GenericService<Test> {

    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public TestService(TestRepository testRepository, UserRepository userRepository) {
        super(testRepository);
        this.testRepository = testRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Test create(Test model) {
        model.setCreatedWhen(LocalDateTime.now());
        model.setQuestionQuantity(0);
        return testRepository.save(model);
    }

    public Page<Test> findByTeacher(Long id,
                                    Pageable pageable) {

        User teacher = userRepository.findById(id)
                .orElseThrow();
        return testRepository.findByTeacher(teacher, pageable);
    }
}
