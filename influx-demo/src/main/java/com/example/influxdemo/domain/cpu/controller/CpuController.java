package com.example.influxdemo.domain.cpu.controller;

import com.example.influxdemo.domain.cpu.measurement.CPU;
import com.example.influxdemo.domain.cpu.service.InfluxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CpuController {

  private final InfluxService influxService;

  public CpuController(InfluxService influxService) {
    this.influxService = influxService;
  }

  @GetMapping("/")
  public void writeCpuData() {
    CPU cpu = new CPU();
    cpu.setVersion("1");
    cpu.setUse("2");
    cpu.setIdle("3");

    influxService.write(cpu);
  }
}
