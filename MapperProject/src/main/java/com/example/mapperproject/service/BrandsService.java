package com.example.mapperproject.service;

import com.example.mapperproject.domain.Brands;
import com.example.mapperproject.domain.BrandsRepository;
import com.example.mapperproject.dto.BrandsDto;
import com.example.mapperproject.mapper.BrandsMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandsService {

  private final BrandsRepository repository;

  public void create(BrandsDto d) {
    if (null == d) {
      return;
    }

    Brands e = BrandsMapper.INSTANCE.toEntity(d);
    repository.save(e);
  }

  public BrandsDto select(String id) {
    return BrandsMapper.INSTANCE.toDto(repository.getById(id));
  }

  public List<BrandsDto> selectAll() {

    List<Brands> brandsList = repository.findAll();
    return BrandsMapper.INSTANCE.toDtoList(brandsList);
  }

  public void update(String id, BrandsDto d) {
    if (null == d) {
      return;
    }

    Brands e = repository.getById(id);
    updateFromDto(d, e);
    repository.save(e);
  }

  public void delete(String id) {
    if (!repository.findById(id).isPresent()) {
      return;
    }

    repository.deleteById(id);
  }

  protected void updateFromDto(BrandsDto d, Brands e) {
    BrandsMapper.INSTANCE.updateFromDto(d, e);
  }
}
