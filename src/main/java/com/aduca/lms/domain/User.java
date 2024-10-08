package com.aduca.lms.domain;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User extends IdBasedEntity {

    @Column(length = 255, nullable = false)
    @Size(min = 3, message = "Name must have a minimum of 3 characters")
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    @Size(min = 3, message = "Username must have a minimum of 3 characters")
    private String username;

    @NotNull(message = "Email field is required!")
    @Email(message = "Email is invalid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 255)
    private String photo;

    private String phone;

    @Column(length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private boolean status = true;
    private String lastSeen;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    @Transient
    private String joinedTime;
    public User(Long id) {
        this.id = id;
    }

  public User() {

  }

  public String getJoinedTime() {
    return joinedTime;
  }

  public void setJoinedTime(String joinedTime) {
    this.joinedTime = joinedTime;
  }

  public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", username=" + username + ", email=" + email + ", password=" + password
                + ", photo=" + photo + ", phone=" + phone + ", address=" + address + ", role=" + role + ", status="
                + status + ", lastSeen=" + lastSeen + ", rememberToken=" + rememberToken + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }

}
