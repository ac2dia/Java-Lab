package com.example.influxdemo.domain.cpu.service;

import com.example.influxdemo.domain.cpu.measurement.CPU;

public interface InfluxService {

  void write(CPU cpu);
}
