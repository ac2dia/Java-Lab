package com.example.influxdemo.domain.cpu.controller;

import com.example.influxdemo.domain.cpu.measurement.CPU;
import com.example.influxdemo.domain.cpu.service.InfluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CpuController {

  private final InfluxService influxService;

  @PostMapping("/cpu")
  public void writeCpuData(@RequestBody CPU cpu) {
//    cpu.setVersion("Intel(R) Core(TM) i5-1038NG7 CPU @ 2.00GHz");
//    cpu.setUse(13.66);
//    cpu.setIdle(66.88);

    influxService.write(cpu);
  }
}
