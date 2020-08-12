package com.gxa.child.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxa.child.groups.Add;
import com.gxa.child.groups.Login;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Admin {

  private Long id;
  private Long loginId;
  @NotBlank(message = "管理员账户不能为空！",groups = {Login.class, Add.class})
  private String adminName;
  @NotBlank(message = "管理员密码不能为空！",groups = {Login.class, Add.class})
  private String adminPwd;
  @Min(value = 10000000000L,message = "手机号不正确")
  @NotNull(message = "手机号不能为空！",groups = {Add.class})
  private Long adminPhone;
  private Integer adminStatus;
  private Integer isDeleted;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private java.sql.Timestamp createTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private java.sql.Timestamp lastLoginTime;
  private Integer isAdmin;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getLoginId() {
    return loginId;
  }

  public void setLoginIdId(Long id) {
    this.loginId = loginId;
  }

  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

  public String getAdminPwd() {
    return adminPwd;
  }

  public void setAdminPwd(String adminPwd) {
    this.adminPwd = adminPwd;
  }


  public Long getAdminPhone() {
    return adminPhone;
  }

  public void setAdminPhone(Long adminPhone) {
    this.adminPhone = adminPhone;
  }


  public Integer getAdminStatus() {
    return adminStatus;
  }

  public void setAdminStatus(Integer adminStatus) {
    this.adminStatus = adminStatus;
  }


  public Integer getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  public Integer getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Integer isAdmin) {
    this.isAdmin = isAdmin;
  }

  @Override
  public String toString() {
    return "Admin{" +
            "id=" + id +
            ", adminName='" + adminName + '\'' +
            ", adminPwd='" + adminPwd + '\'' +
            ", adminPhone=" + adminPhone +
            ", adminStatus=" + adminStatus +
            ", isDeleted=" + isDeleted +
            ", createTime=" + createTime +
            ", lastLoginTime=" + lastLoginTime +
            ", isAdmin=" + isAdmin +
            '}';
  }
}
