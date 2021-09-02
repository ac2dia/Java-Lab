package com.example.mapperproject.mapper;

import com.example.mapperproject.domain.Brands;
import com.example.mapperproject.dto.BrandsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandsMapper extends GenericMapper<BrandsDto, Brands> {

  BrandsMapper INSTANCE = Mappers.getMapper(BrandsMapper.class);

}
