package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.test.Subject;
import ru.lobakina.educationalquizapp.repository.test.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject create(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow();
    }

    public Subject update(Subject subject) {
        subjectRepository.findById(subject.getId())
                .orElseThrow();
        return subjectRepository.save(subject);
    }
}
