package com.hafiz.interview.east.netic.todo.core.crud;

import com.hafiz.interview.east.netic.todo.validatorgroup.CreateValidatorGroup;
import com.hafiz.interview.east.netic.todo.validatorgroup.UpdateValidatorGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class CrudController<E extends BaseEntity, D extends IdHolder> {

    private final CrudService<E> service;
    private final ConversionUtility<E, D> conversionUtility;
    private static final long MAXVALUE = 10000L;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<D> getList(@RequestParam(value = "page", required = false, defaultValue = "0") Long page,
                           @RequestParam(value = "size", required = false, defaultValue = MAXVALUE + "") Long size) {
        return conversionUtility.getDtoList(service.getList(page, size));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public D getById(@PathVariable UUID id){
        return conversionUtility.getDto(service.getById(id)).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public D create(@RequestBody @Validated({CreateValidatorGroup.class}) D reqBody) {
        return conversionUtility.getDto(
                Optional.ofNullable(service.create(
                        conversionUtility.buildEntityForCreate(Optional.of(reqBody))))
        ).orElse(null);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public D update(@RequestBody @Validated({UpdateValidatorGroup.class}) D reqBody, @PathVariable UUID id) {
        Optional<E> e = conversionUtility.buildEntityForUpdate(Optional.of(reqBody), service.getById(id).get());
        return conversionUtility.getDto(Optional.of(service.update(e))).orElse(null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteById(Optional.of(id));
    }
}
