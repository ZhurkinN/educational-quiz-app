package ru.lobakina.educationalquizapp.repository.question;

import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;

import java.util.List;

@Repository
public interface QuestionRepository extends GenericRepository<Question> {
    List<Question> findByTest(Test test);
}
