package ru.lobakina.educationalquizapp.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.model.user.Role;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;

@Repository
public interface GroupRepository extends GenericRepository<Group> {
    Group findByGroupNumber(String groupNumber);

    Page<Group> findByGroupNumberContainsIgnoreCase(String groupNumber,
                                                    Pageable pageable);
}
