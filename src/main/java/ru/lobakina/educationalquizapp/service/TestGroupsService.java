package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.repository.test.TestGroupsRepository;

@Service
@RequiredArgsConstructor
public class TestGroupsService {

    private final TestGroupsRepository testGroupsRepository;
}
