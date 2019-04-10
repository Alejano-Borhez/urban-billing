package com.urban.billingapi.service.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.urban.billingapi.model.EntityExample;

public interface EntityToVOMapper<ID, E extends EntityExample<E, ID>, V>
{
    V toVO( E entity );

    E toEntity( V vo );

    default List<V> toVOs( List<E> entities )
    {
        return Optional.ofNullable( entities ).orElse( Collections.emptyList() ).stream().map( this::toVO ).collect( Collectors.toList() );
    }

    default List<E> toEntities( List<V> vos )
    {
        return Optional.ofNullable( vos ).orElse( Collections.emptyList() ).stream().map( this::toEntity ).collect( Collectors.toList() );
    }
}
