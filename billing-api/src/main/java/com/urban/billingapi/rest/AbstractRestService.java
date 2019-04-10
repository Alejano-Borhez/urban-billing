package com.urban.billingapi.rest;

import static java.util.Optional.ofNullable;

import com.urban.billingapi.annotation.ApiPageable;
import com.urban.billingapi.model.EntityExample;
import com.urban.billingapi.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class AbstractRestService<ID, E extends EntityExample<E, ID>, V>
{
    abstract CrudService<ID, E, V> getService();

    @GetMapping
    @ApiPageable
    public Page<V> findAll( @PageableDefault Pageable pageable )
    {
        return getService().get( pageable );
    }

    @PostMapping( "/find" )
    @ApiPageable
    public Page<V> find( @RequestBody E example, @PageableDefault Pageable pageable )
    {
        return ofNullable(example)
                .map(example::createExample)
                .map(ex -> getService().find(pageable, ex))
                .orElse(Page.empty());
    }

    @GetMapping( "/{id}" )
    public V get( @PathVariable ID id )
    {
        return getService().get( id );
    }

    @PostMapping
    public V create( @RequestBody @Valid V vo )
    {
        return getService().create( vo );
    }

    @PutMapping
    public V update( @RequestBody @Valid V vo )
    {
        return getService().update( vo );
    }
}
