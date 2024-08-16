package com.aduca.lms.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ResetPassword {

    private Long id;

    @NotEmpty(message = "The old password field is required")
    private String oldPassword;

    @Size(min = 6, message = "The new password must have at least 6 characters")
    private String newPassword;

    @NotEmpty(message = "The confirm new password field is required")
    private String confirmPassword;

    public ResetPassword() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
