package ru.lobakina.educationalquizapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.repository.test.SubjectRepository;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.support.dto.TestDTO;
import ru.lobakina.educationalquizapp.support.mapper.generic.GenericMapper;

@Component
@RequiredArgsConstructor
public class TestMapper extends GenericMapper<Test, TestDTO> {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Test toEntity(TestDTO dto) {
        Test test = new Test()
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setDeadline(dto.getDeadline())
                .setDuration(dto.getDuration())
                .setQuestionQuantity(dto.getQuestionQuantity())
                .setSubject(subjectRepository.findByTitle(dto.getSubject()))
                .setTeacher(userRepository.findById(dto.getTeacher()).orElseThrow());
        test.setCreatedWhen(dto.getCreatedWhen());
        test.setId(dto.getId());
        return test;
    }

    @Override
    public TestDTO toDto(Test entity) {
        return null;
    }
}
