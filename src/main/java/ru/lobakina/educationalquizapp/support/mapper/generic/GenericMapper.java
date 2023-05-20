package ru.lobakina.educationalquizapp.support.mapper.generic;

import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.support.dto.generic.GenericDTO;

import java.util.List;

public abstract class GenericMapper<M extends GenericModel, D extends GenericDTO> {

    public abstract M toEntity(D dto);

    public List<M> toEntities(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public abstract D toDto(M entity);

    public List<D> toDtos(List<M> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    protected M setGenericFields(D dto, M entity) {
        entity.setId(dto.getId());
        entity.setCreatedWhen(dto.getCreatedWhen());

        return entity;
    }
}
