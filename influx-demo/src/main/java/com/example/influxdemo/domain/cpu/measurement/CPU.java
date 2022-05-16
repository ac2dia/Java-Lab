package com.example.influxdemo.domain.cpu.measurement;

import java.time.Instant;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

@Data
@Measurement(name = "cpu")
public class CPU {

  @TimeColumn
  @Column(name = "time")
  private Instant time;

  @Column(name = "version")
  private String version;

  @Column(name = "use")
  private double use;

  @Column(name = "idle")
  private double idle;
}
