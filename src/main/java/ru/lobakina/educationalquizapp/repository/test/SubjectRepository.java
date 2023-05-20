package ru.lobakina.educationalquizapp.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.test.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByTitle(String subject);
}
