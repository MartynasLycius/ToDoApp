package com.mir00r.todoapp.domains.common.models.mappers;

import com.mir00r.todoapp.domains.common.models.dtos.BaseDto;
import com.mir00r.todoapp.domains.common.models.entities.base.BaseEntity;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
public interface BaseMapper<T extends BaseEntity, S extends BaseDto> {

    S map(T entity);

    T map(S dto, T exEntity);
}
