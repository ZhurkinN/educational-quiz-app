package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.model.test.StudentAnswer;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.StudentAnswerRepository;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.repository.test.TestStudentsRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestStudentsService {

    private final TestStudentsRepository testStudentsRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final StudentAnswerRepository studentAnswerRepository;

    public TestStudents assignTest(Long studentId,
                                   Long testId) {
        User student = userRepository.findById(studentId).orElseThrow();
        Test test = testRepository.findById(testId).orElseThrow();
        TestStudents testStudents = new TestStudents()
                .setTest(test)
                .setStudent(student)
                .setIsDone(false);
        return testStudentsRepository.save(testStudents);
    }

    public Page<TestStudents> findActiveTestsByTeacher(Long id, Pageable pageable) {
        List<TestStudents> studentsTests = testStudentsRepository.findActiveTestsByTeacher(id);
        checkDeadline(studentsTests);
        return testStudentsRepository.findActiveTestsByTeacher(id, pageable);
    }

    public Page<TestStudents> findArchiveTestsByTeacher(Long id, Pageable pageable) {
        return testStudentsRepository.findArchiveTestsByTeacher(id, pageable);
    }

    public TestStudents getById(Long id) {
        return testStudentsRepository.findById(id).orElseThrow();
    }

    public void returnTest(Long id) {
        TestStudents testStudents = testStudentsRepository.findById(id)
                .orElseThrow();
        testStudents
                .setScore(0)
                .setTestDate(null)
                .setIsDone(false);
        testStudentsRepository.save(testStudents);
    }

    public Page<TestStudents> findTestsByStudent(Long id, Pageable pageRequest) {
        User student = userRepository.findById(id).orElseThrow();
        List<TestStudents> studentsTests = testStudentsRepository.findByStudent(student);
        checkDeadline(studentsTests);
        return testStudentsRepository.findByStudent(student, pageRequest);
    }

    private void checkDeadline(List<TestStudents> studentsTests) {

        for (TestStudents testStudents : studentsTests) {
            Date deadline = testStudents.getTest().getDeadline();

            if (!testStudents.getIsDone() && deadline.before(new Date(System.currentTimeMillis()))) {
                testStudents.setScore(0)
                        .setIsDone(true)
                        .setTestDate(deadline);
                testStudentsRepository.save(testStudents);
                for (Question question : testStudents.getTest().getQuestions()) {
                    StudentAnswer studentAnswer = new StudentAnswer()
                            .setAnswer("")
                            .setTest(testStudents)
                            .setIsRight(false)
                            .setQuestion(question);
                    studentAnswerRepository.save(studentAnswer);
                }
            }
        }
    }
}
