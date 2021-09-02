package com.example.mapperproject.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface GenericMapper<D, E> {

  D toDto(E e);

  E toEntity(D d);

  List<D> toDtoList(List<E> es);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateFromDto(D dto, @MappingTarget E entity);
}
