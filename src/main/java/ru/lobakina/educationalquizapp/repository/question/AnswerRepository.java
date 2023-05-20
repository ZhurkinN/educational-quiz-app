package ru.lobakina.educationalquizapp.repository.question;

import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.question.Answer;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;

import java.util.List;

@Repository
public interface AnswerRepository extends GenericRepository<Answer> {
    List<Answer> findByQuestion(Question question);
}
