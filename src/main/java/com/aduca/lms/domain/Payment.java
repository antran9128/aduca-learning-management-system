package com.aduca.lms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends IdBasedEntity{
  private String name;
  private String email;
  private String phone;
  private String address;
  private String cashDelivery;
  private String totalAmount;
  private String paymentType;
  private String invoiceNo;
  private String orderDate;
  private String orderMonth;
  private String orderYear;
  private String status;

  public Payment() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getCashDelivery() {
    return cashDelivery;
  }

  public void setCashDelivery(String cashDelivery) {
    this.cashDelivery = cashDelivery;
  }

  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public String getOrderMonth() {
    return orderMonth;
  }

  public void setOrderMonth(String orderMonth) {
    this.orderMonth = orderMonth;
  }

  public String getOrderYear() {
    return orderYear;
  }

  public void setOrderYear(String orderYear) {
    this.orderYear = orderYear;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
