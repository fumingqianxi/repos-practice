package com.itheima.spring实战.jpa.domain;

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
public class JpaSpittle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "spitter")
  private JpaSpitter spitter;

  @Column
  private String message;

  @Column(name = "posted_time")
  private Date postedTime;

  public JpaSpittle(Long id, JpaSpitter spitter, String message, Date postedTime) {
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

  public JpaSpitter getSpitter() {
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
