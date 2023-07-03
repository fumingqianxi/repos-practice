package com.itheima.springinaction.spittr.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@NoArgsConstructor
@Table(name = "spittle")
public class Spittle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String message;

  @Column(name = "posted_time")
  private Date time;

  public Spittle(String message, Date time) {
    this(null, message, time);
  }

  public Spittle(Long id, String message, Date time) {
    this.id = id;
    this.message = message;
    this.time = time;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTime(Date time) {
    this.time = time;
  }


  public String getMessage() {
    return message;
  }

  public Date getTime() {
    return time;
  }

  @Override
  public boolean equals(Object that) {
    return EqualsBuilder.reflectionEquals(this, that, "id", "time");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "id", "time");
  }
}
