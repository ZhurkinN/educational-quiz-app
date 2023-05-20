package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.repository.test.TestStudentsRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class TestStudentsService {

    private final TestStudentsRepository testStudentsRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;

    public TestStudents assignTest(Long studentId, Long testId) {
        User student = userRepository.findById(studentId).orElseThrow();
        Test test = testRepository.findById(testId).orElseThrow();
        TestStudents testStudents = new TestStudents()
                .setTest(test)
                .setStudent(student);
        return testStudentsRepository.save(testStudents);
    }
}
