package dev.sayem.todoapp.domains.common.base.models.mappers;

import dev.sayem.todoapp.domains.common.base.models.dtos.BaseDto;
import dev.sayem.todoapp.domains.common.base.models.entities.BaseEntity;

public interface BaseMapper<T extends BaseEntity, S extends BaseDto> {
    S map(T entity);

    T map(S dto, T exEntity);
}
