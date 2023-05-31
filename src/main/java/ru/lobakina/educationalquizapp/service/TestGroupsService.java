package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.test.TestGroups;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.test.TestGroupsRepository;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.repository.test.TestStudentsRepository;
import ru.lobakina.educationalquizapp.repository.user.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestGroupsService {

    private final TestGroupsRepository testGroupsRepository;
    private final TestStudentsRepository testStudentsRepository;
    private final GroupRepository groupRepository;
    private final TestRepository testRepository;

    public Page<TestGroups> findGroupTestsByTeacher(Long id, PageRequest pageRequest) {
        return testGroupsRepository.findByTeacher(id, pageRequest);
    }

    public Page<TestStudents> getTestStudentsByTestGroupsId(Long id, Pageable pageable) {
        TestGroups testGroups = testGroupsRepository.findById(id).orElseThrow();
        return testStudentsRepository.findByTestGroups(testGroups, pageable);
    }

    @Transactional
    public TestGroups assignTest(Long groupId, Long testId) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        Test test = testRepository.findById(testId).orElseThrow();
        if (group.getGroupStudents().size() == 0) {
            return new TestGroups().setTest(test);
        }
        TestGroups testGroups = new TestGroups()
                .setIsDone(false)
                .setTest(test)
                .setGroup(group)
                .setStudentsCompleted(0);
        testGroups = testGroupsRepository.save(testGroups);
        List<TestStudents> testStudentsList = new ArrayList<>();
        for (User student : group.getGroupStudents()) {
            TestStudents testStudents = new TestStudents()
                    .setTest(test)
                    .setStudent(student)
                    .setIsDone(false)
                    .setTestGroups(testGroups);
            testStudentsList.add(testStudents);
        }
        testStudentsRepository.saveAll(testStudentsList);
        return testGroups;
    }

    public TestGroups getById(Long id) {
        return testGroupsRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void delete(Long id) {
        TestGroups testGroups = testGroupsRepository.findById(id).orElseThrow();
        testStudentsRepository.deleteAll(testGroups.getTestStudents());
        testGroupsRepository.deleteById(id);
    }
}
