package com.ygsoft.user.management.domain.po;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户持久化对象
 * 
 * 对应数据库表：TBL_USER
 * 
 * @author developer
 * @date 2024-12-15
 */
@Entity
@Table(name = "TBL_USER")
public class UserPO {
    
    /**
     * 用户ID（主键）
     */
    @Id
    @Column(name = "C_USER_ID", length = 20)
    private String userId;
    
    /**
     * 用户名（唯一）
     */
    @Column(name = "C_USERNAME", length = 50, nullable = false, unique = true)
    private String username;
    
    /**
     * 密码（加密存储）
     */
    @Column(name = "C_PASSWORD", length = 200, nullable = false)
    private String password;
    
    /**
     * 真实姓名
     */
    @Column(name = "C_REAL_NAME", length = 50, nullable = false)
    private String realName;
    
    /**
     * 部门ID
     */
    @Column(name = "C_DEPARTMENT_ID", length = 20)
    private String departmentId;
    
    /**
     * 部门名称
     */
    @Column(name = "C_DEPARTMENT", length = 100)
    private String department;
    
    /**
     * 角色ID
     */
    @Column(name = "C_ROLE_ID", length = 20)
    private String roleId;
    
    /**
     * 角色名称
     */
    @Column(name = "C_ROLE", length = 50)
    private String role;
    
    /**
     * 手机号
     */
    @Column(name = "C_PHONE", length = 20)
    private String phone;
    
    /**
     * 邮箱
     */
    @Column(name = "C_EMAIL", length = 100)
    private String email;
    
    /**
     * 状态：1-启用，0-禁用
     */
    @Column(name = "C_STATUS", length = 1, nullable = false)
    private String status;
    
    /**
     * 创建时间
     */
    @Column(name = "C_CREATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    /**
     * 更新时间
     */
    @Column(name = "C_UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    /**
     * 最后登录时间
     */
    @Column(name = "C_LAST_LOGIN_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;
    
    /**
     * 创建人
     */
    @Column(name = "C_CREATOR", length = 50)
    private String creator;
    
    /**
     * 更新人
     */
    @Column(name = "C_UPDATER", length = 50)
    private String updater;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getUpdater() {
        return updater;
    }
    
    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
