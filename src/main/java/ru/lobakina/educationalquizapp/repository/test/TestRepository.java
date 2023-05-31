package ru.lobakina.educationalquizapp.repository.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;

import java.util.List;

@Repository
public interface TestRepository extends GenericRepository<Test> {

    Page<Test> findByTeacher(User user, Pageable pageable);

    List<Test> findByTeacher(User user);
}
