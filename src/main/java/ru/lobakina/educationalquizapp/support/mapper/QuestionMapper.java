package ru.lobakina.educationalquizapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.repository.question.QuestionTypeRepository;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.support.dto.QuestionDTO;
import ru.lobakina.educationalquizapp.support.mapper.generic.GenericMapper;

@Component
@RequiredArgsConstructor
public class QuestionMapper extends GenericMapper<Question, QuestionDTO> {

    private final QuestionTypeRepository questionTypeRepository;
    private final TestRepository testRepository;

    @Override
    public Question toEntity(QuestionDTO dto) {
        Question question = new Question()
                .setText(dto.getText())
                .setQuestionType(questionTypeRepository.findById(dto.getQuestionTypeId()).orElseThrow())
                .setTest(testRepository.findById(dto.getTestId()).orElseThrow());
        question.setId(dto.getId());
        return question;
    }

    @Override
    public QuestionDTO toDto(Question entity) {
        return null;
    }
}
