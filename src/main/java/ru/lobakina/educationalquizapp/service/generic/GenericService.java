package ru.lobakina.educationalquizapp.service.generic;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.lobakina.educationalquizapp.support.constants.ErrorMessagesKeeper.NOT_FOUND;


@RequiredArgsConstructor
public abstract class GenericService<M extends GenericModel> {

    private final GenericRepository<M> genericRepository;

    public List<M> getAll() {
        return genericRepository.findAll();
    }

    public Page<M> getAll(Pageable pageable) {
        return genericRepository.findAll(pageable);
    }

    public M getById(Long id) {
        return genericRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
    }

    public M create(M model) {
        model.setCreatedWhen(LocalDateTime.now());
        return genericRepository.save(model);
    }

    public M update(M model) {
        genericRepository.findById(model.getId())
                .orElseThrow();
        return genericRepository.save(model);
    }

    public void delete(Long id) {
        genericRepository.deleteById(id);
    }

}
