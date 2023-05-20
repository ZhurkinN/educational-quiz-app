package ru.lobakina.educationalquizapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.repository.question.QuestionRepository;
import ru.lobakina.educationalquizapp.repository.test.TestRepository;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

import java.util.List;

@Service
public class QuestionService extends GenericService<Question> {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public QuestionService(QuestionRepository questionRepository, TestRepository testRepository) {
        super(questionRepository);
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    public List<Question> findQuestionsByTest(Long id) {
        Test test = testRepository.findById(id)
                .orElseThrow();
        return questionRepository.findByTest(test);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Question question = questionRepository.findById(id).orElseThrow();
        Test test = question.getTest();
        test.setQuestionQuantity(test.getQuestionQuantity() - 1);
        testRepository.save(test);
        super.delete(id);
    }

    @Override
    @Transactional
    public Question create(Question model) {
        Question question =  super.create(model);
        Test test = question.getTest();
        test.setQuestionQuantity(test.getQuestionQuantity() + 1);
        testRepository.save(test);
        return question;
    }
}
