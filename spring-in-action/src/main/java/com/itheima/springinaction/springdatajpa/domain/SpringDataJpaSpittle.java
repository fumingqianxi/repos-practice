package com.itheima.springinaction.springdatajpa.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spittle")
@NoArgsConstructor
public class SpringDataJpaSpittle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "spitter")
  private SpringDataJpaSpitter spitter;

  @Column
  private String message;

  @Column(name = "posted_time")
  private Date postedTime;

  public SpringDataJpaSpittle(Long id, SpringDataJpaSpitter spitter, String message, Date postedTime) {
    this.id = id;
    this.spitter = spitter;
    this.message = message;
    this.postedTime = postedTime;
  }

  public Long getId() {
    return this.id;
  }

  public String getMessage() {
    return this.message;
  }

  public Date getPostedTime() {
    return this.postedTime;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSpitter(SpringDataJpaSpitter spitter) {
    this.spitter = spitter;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setPostedTime(Date postedTime) {
    this.postedTime = postedTime;
  }

  public SpringDataJpaSpitter getSpitter() {
    return this.spitter;
  }

  @Override
  public String toString() {
    return "JpaSpittle{" +
        "id=" + id +
        ", spitter=" + spitter +
        ", message='" + message + '\'' +
        ", postedTime=" + postedTime +
        '}';
  }
}
