package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;
import ru.lobakina.educationalquizapp.repository.user.GroupRepository;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

@Service
public class GroupService extends GenericService<Group> {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        super(groupRepository);
        this.groupRepository = groupRepository;
    }

    public Page<Group> findGroups(String groupNumber,
                                  Pageable pageable) {
        return groupRepository.findByGroupNumberContainsIgnoreCase(groupNumber, pageable);
    }
}
