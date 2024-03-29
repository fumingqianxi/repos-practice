package com.itheima.springinaction.springdatajpa.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spitter")
@NoArgsConstructor
public class SpringDataJpaSpitter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String username;

  @Column
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "email")
  private String email;

  @Column(name = "update_by_email")
  private boolean updateByEmail;

  @Column(name="status")
  private String status;

  @OneToMany(targetEntity=SpringDataJpaSpittle.class, fetch= FetchType.EAGER, mappedBy="spitter")
  private List<SpringDataJpaSpittle> spittles;

  public SpringDataJpaSpitter(Long id, String username, String password, String fullName, String email, boolean updateByEmail) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.updateByEmail = updateByEmail;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUpdateByEmail(boolean updateByEmail) {
    this.updateByEmail = updateByEmail;
  }

  public String getEmail() {
    return email;
  }

  public boolean isUpdateByEmail() {
    return updateByEmail;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setSpittles(
      List<SpringDataJpaSpittle> spittles) {
    this.spittles = spittles;
  }

  public List<SpringDataJpaSpittle> getSpittles() {
    return spittles;
  }

  @Override
  public String toString() {
    return "Spitter{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", fullName='" + fullName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
