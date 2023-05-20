package ru.lobakina.educationalquizapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lobakina.educationalquizapp.model.question.Answer;
import ru.lobakina.educationalquizapp.repository.question.QuestionRepository;
import ru.lobakina.educationalquizapp.support.dto.AnswerDTO;
import ru.lobakina.educationalquizapp.support.mapper.generic.GenericMapper;

@Component
@RequiredArgsConstructor
public class AnswerMapper extends GenericMapper<Answer, AnswerDTO> {

    private final QuestionRepository questionRepository;

    @Override
    public Answer toEntity(AnswerDTO dto) {
        Answer answer = new Answer()
                .setText(dto.getText())
                .setIsRight(dto.getIsRight())
                .setQuestion(questionRepository.findById(dto.getQuestionId()).orElseThrow());
        answer.setId(dto.getId());
        answer.setCreatedWhen(dto.getCreatedWhen());
        return answer;
    }

    @Override
    public AnswerDTO toDto(Answer entity) {
        return null;
    }
}
