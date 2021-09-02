package com.example.mapperproject.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.mapperproject.domain.Brands;
import com.example.mapperproject.dto.BrandsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BrandsMapperTest {

  @Test
  @DisplayName("Entity to Dto")
  void test_entity_to_dto() {
    // when
    final Brands brands = Brands.builder()
        .name("samsung")
        .email("lee@samsung.com")
        .est(1938)
        .build();

    // then
    final BrandsDto brandsDto = BrandsMapper.INSTANCE.toDto(brands);

    // given
    assertNotNull(brandsDto);
    assertEquals(brandsDto.getName(), "samsung");
    assertEquals(brandsDto.getEmail(), "lee@samsung.com");
    assertEquals(brandsDto.getEst(), 1938);
  }

  @DisplayName("DTO to Entity")
  @Test
  void test_dto_to_entity() {
    // when
    final BrandsDto brandsDto = BrandsDto.builder()
        .name("samsung")
        .email("lee@samsung.com")
        .est(1938)
        .build();

    // then
    final Brands brands = BrandsMapper.INSTANCE.toEntity(brandsDto);

    // given
    assertNotNull(brands);
    assertEquals(brands.getName(), "samsung");
    assertEquals(brands.getEmail(), "lee@samsung.com");
    assertEquals(brands.getEst(), 1938);
  }

}