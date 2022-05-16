package com.example.influxdemo.domain.cpu.service;

import com.example.influxdemo.domain.cpu.measurement.CPU;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class InfluxServiceImpl implements InfluxService {

  private final InfluxDBTemplate<Point> influxDBTemplate;

  @PostConstruct
  public void init() {
    influxDBTemplate.createDatabase();
  }

  @Override
  public void write(CPU cpu) {
    log.info(cpu.toString());

    influxDBTemplate.write(Point.measurementByPOJO(CPU.class).addFieldsFromPOJO(cpu).build());
  }
}
