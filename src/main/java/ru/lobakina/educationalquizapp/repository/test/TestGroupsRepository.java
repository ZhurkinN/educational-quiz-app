package ru.lobakina.educationalquizapp.repository.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.test.TestGroups;

@Repository
public interface TestGroupsRepository extends JpaRepository<TestGroups, Long> {

    @Query(nativeQuery = true,
            value = "select tg.* " +
                    "from test_groups tg " +
                    "join test t on t.id = tg.test_id " +
                    "where t.teacher_id = ?1 ")
    Page<TestGroups> findByTeacher(Long teacherId, Pageable pageable);
}
