package ru.lobakina.educationalquizapp.repository.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.test.TestGroups;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.model.user.User;

import java.util.List;

@Repository
public interface TestStudentsRepository extends JpaRepository<TestStudents, Long> {

    @Query(nativeQuery = true,
            value = "select ts.* " +
                    "from test_students ts " +
                    "join test t on t.id = ts.test_id " +
                    "where t.teacher_id = ?1 and ts.is_done = false ")
    Page<TestStudents> findActiveTestsByTeacher(Long teacherId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select ts.* " +
                    "from test_students ts " +
                    "join test t on t.id = ts.test_id " +
                    "where t.teacher_id = ?1 and ts.is_done = false ")
    List<TestStudents> findActiveTestsByTeacher(Long teacherId);

    @Query(nativeQuery = true,
            value = "select ts.* " +
                    "from test_students ts " +
                    "inner join test t on t.id = ts.test_id " +
                    "where t.teacher_id = ?1 and ts.is_done = true ")
    Page<TestStudents> findArchiveTestsByTeacher(Long id, Pageable pageable);

    Page<TestStudents> findByTestGroups(TestGroups testGroups, Pageable pageable);

    Page<TestStudents> findByStudent(User student, Pageable pageable);

    List<TestStudents> findByStudent(User student);

}