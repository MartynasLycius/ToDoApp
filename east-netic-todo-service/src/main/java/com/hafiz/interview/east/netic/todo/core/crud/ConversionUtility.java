package com.hafiz.interview.east.netic.todo.core.crud;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ConversionUtility<E extends BaseEntity, D> {
    private final ModelMapper mapper;
    private final Type entityType;
    private final Type dtoType;

    public Optional<E> buildEntityForCreate(Optional<D> s) {
        return Optional.of(mapper.map(s.get(), entityType));
    }

    public Optional<E> buildEntityForUpdate(Optional<D> reqBody, E entity) {
        BeanUtils.copyProperties(reqBody.get(),entity, getNullPropertyNames(reqBody.get()));
        return Optional.of(entity);
    }

    public Optional<D> getDto(Optional<E> entity) {
        return entity.<Optional<D>>map(value -> Optional.of(mapper.map(value, dtoType))).orElse(null);
    }

    public Page<D> getDtoList(Page<E> entities) {
        List<D> dtos = entities.getContent().stream().map(Optional::of).map(this::getDto).map(Optional::get).collect(Collectors.toList());
        final Page<D> page = new PageImpl<D>(dtos);
        return page;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
