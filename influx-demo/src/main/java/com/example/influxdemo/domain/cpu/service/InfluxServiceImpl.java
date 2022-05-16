package com.example.influxdemo.domain.cpu.service;

import com.example.influxdemo.domain.cpu.measurement.CPU;
import javax.annotation.PostConstruct;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

@Service
public class InfluxServiceImpl implements InfluxService {

  private final InfluxDBTemplate<Point> influxDBTemplate;

  public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate) {
    this.influxDBTemplate = influxDBTemplate;
  }

  @PostConstruct
  public void init() {
    influxDBTemplate.createDatabase();
  }

  @Override
  public void write(CPU cpu) {
    influxDBTemplate.write(Point.measurementByPOJO(CPU.class).addFieldsFromPOJO(cpu).build());

    System.out.println(cpu.toString());
  }
}
