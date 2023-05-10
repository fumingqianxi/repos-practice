package com.itheima.spring实战.spittr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "spitter")
@NoArgsConstructor
public class Spitter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Size(min=5, max=16, message="{username.size}")
  @Column
  private String username;

  @NotNull
  @Size(min=5, max=25, message="{password.size}")
  @Column
  private String password;
  
  @NotNull
  @Size(min=2, max=30, message="{fullName.size}")
  @Column(name = "full_name")
  private String fullName;
  
  @NotNull
  @Email
  @Column
  private String email;

  @Column(name = "update_by_email")
  private boolean updateByEmail = false;

  public Spitter(String username, String password, String fullName, String email) {
    this(null, username, password, fullName, email);
  }

  public Spitter(Long id, String username, String password, String fullName, String email) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public boolean isUpdateByEmail() {
    return updateByEmail;
  }

  public void setUpdateByEmail(boolean updateByEmail) {
    this.updateByEmail = updateByEmail;
  }

  @Override
  public boolean equals(Object that) {
    return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
  }
  
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
  }

}
