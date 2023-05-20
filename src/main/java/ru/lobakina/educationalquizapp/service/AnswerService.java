package ru.lobakina.educationalquizapp.service;

import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.question.Answer;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.repository.question.AnswerRepository;
import ru.lobakina.educationalquizapp.repository.question.QuestionRepository;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

import java.util.List;

@Service
public class AnswerService extends GenericService<Answer> {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        super(answerRepository);
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public List<Answer> getByQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow();
        return answerRepository.findByQuestion(question);
    }
}
