package com.hafiz.interview.east.netic.todo.core.crud;

import com.hafiz.interview.east.netic.todo.core.constants.ErrorMessage;
import com.hafiz.interview.east.netic.todo.exceptions.ExceptionHolders;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public abstract class CrudService<E extends BaseEntity> implements ICrudService<E> {

    private static Logger logger = LoggerFactory.getLogger(CrudService.class);

    private final ICrudRepository<E, UUID> repository;

    @Override
    public Page<E> getList(Long page, Long size) {
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue());
        return repository.findAll(pageable);
    }

    @Override
    public Optional<E> getById(UUID id) {
        Optional<E> entity = repository.findById(id);
        if(!entity.isPresent()) {
            StringBuilder error = new StringBuilder("entity with id " + id + " of " +  getEntityClass().getSimpleName() + " is not found ");
            logger.error(error.toString());
            throw new ExceptionHolders.ResourceNotFoundException(error.toString());
        }
        return entity;
    }

    @Override
    @Transactional
    public E create(Optional<E> entity) {
        if(!entity.isPresent()) return entity.get();
        E createdEntity = null;
        entity.ifPresent(e -> e.setId(null));
        if(entity.isPresent()) {
            createdEntity = repository.save(entity.get());
        }
        return createdEntity;
    }

    @Override
    @Transactional
    public List<E> createAll(List<E> entities) {
        entities = entities
            .stream()
            .peek(e -> e.setId(null))
            .collect(Collectors.toList());
        return repository.saveAll(entities);
    }

    @Override
    @Transactional
    public E update(Optional<E> entity) {
        E updatedEntity = null;
        if(entity.isPresent()) {
            if(entity.get().getId() == null) {
                logger.info("entity with null id is not updateable for " + entity.getClass().getName());
                throw new ExceptionHolders.GeneralServerException(ErrorMessage.GENERAL_SERVER_PROBLEM);
            }
            updatedEntity = repository.save(entity.get());
        }
        assert updatedEntity != null;
        return updatedEntity;
    }

    @Override
    @Transactional
    public void deleteById(Optional<UUID> id) {
        id.ifPresent(repository::deleteById);
    }

    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
