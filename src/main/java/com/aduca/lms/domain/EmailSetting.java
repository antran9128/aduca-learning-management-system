package com.aduca.lms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_settings")
public class EmailSetting extends IdBasedEntity{
  private String host;
  private int port;
  private String username;
  private String password;
  private boolean smtpAuth = true;
  private boolean starttlsEnable = true;
  private boolean starttlsRequired = true;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
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

  public boolean isSmtpAuth() {
    return smtpAuth;
  }

  public void setSmtpAuth(boolean smtpAuth) {
    this.smtpAuth = smtpAuth;
  }

  public boolean isStarttlsEnable() {
    return starttlsEnable;
  }

  public void setStarttlsEnable(boolean starttlsEnable) {
    this.starttlsEnable = starttlsEnable;
  }

  public boolean isStarttlsRequired() {
    return starttlsRequired;
  }

  public void setStarttlsRequired(boolean starttlsRequired) {
    this.starttlsRequired = starttlsRequired;
  }
}
