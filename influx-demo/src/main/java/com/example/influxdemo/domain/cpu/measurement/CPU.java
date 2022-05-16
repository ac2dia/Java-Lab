package com.example.influxdemo.domain.cpu.measurement;

import java.time.Instant;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

@Measurement(name = "cpu")
public class CPU {

  @TimeColumn
  @Column(name = "time")
  private Instant time;

  @Column(name = "version")
  private String version;

  @Column(name = "use")
  private String use;

  @Column(name = "idle")
  private String idle;

  public Instant getTime() {
    return time;
  }

  public void setTime(Instant time) {
    this.time = time;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getUse() {
    return use;
  }

  public void setUse(String use) {
    this.use = use;
  }

  public String getIdle() {
    return idle;
  }

  public void setIdle(String idle) {
    this.idle = idle;
  }

  @Override
  public String toString() {
    return "CPU{" +
        "time=" + time +
        ", version='" + version + '\'' +
        ", use='" + use + '\'' +
        ", idle='" + idle + '\'' +
        '}';
  }
}
