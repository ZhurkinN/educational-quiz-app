package ru.lobakina.educationalquizapp.repository.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.question.QuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
}