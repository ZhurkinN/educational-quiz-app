package ru.lobakina.educationalquizapp.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.test.TestGroups;

@Repository
public interface TestGroupsRepository extends JpaRepository<TestGroups, Long> {
}
