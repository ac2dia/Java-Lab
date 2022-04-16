package com.example.usecasewithmapstruct.global.model.mapper;

import java.util.List;

public interface GenericMapper<D, E> {

  D toDto(E e);

  E toEntity(D d);

  List<D> toDtoList(List<E> es);

  List<E> toEntityList(List<D> ds);
}
