package ru.lobakina.educationalquizapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.support.dto.TestStudentsDTO;

@Component
@RequiredArgsConstructor
public class TestStudentsMapper {

    private final UserRepository userRepository;
    private final TestRepository testRepository;

    public TestStudents toEntity(TestStudentsDTO dto) {
        return new TestStudents()
                .setId(dto.getId())
                .setTestDate(dto.getTestDate())
                .setScore(dto.getScore())
                .setIsDone(dto.getIsDone())
                .setStudent(userRepository.findById(dto.getStudentId()).orElseThrow())
                .setTest(testRepository.findById(dto.getTestId()).orElseThrow());
    }
}
