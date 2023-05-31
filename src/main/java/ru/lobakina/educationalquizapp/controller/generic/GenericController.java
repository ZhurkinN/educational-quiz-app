package ru.lobakina.educationalquizapp.controller.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.service.generic.GenericService;

@RequiredArgsConstructor
public abstract class GenericController<M extends GenericModel> {

    private final GenericService<M> genericService;

    public Page<M> getAll(int page,
                          int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        Page<M> modelPage = genericService.getAll(pageRequest);
        return new PageImpl<>(modelPage.getContent(), pageRequest, modelPage.getTotalElements());
    }
}
