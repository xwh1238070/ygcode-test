package com.ygsoft.user.management.domain.po;

import javax.persistence.*;
import java.util.Date;

/**
 * 部门持久化对象
 * 
 * 对应数据库表：TBL_DEPARTMENT
 * 
 * @author developer
 * @date 2024-12-15
 */
@Entity
@Table(name = "TBL_DEPARTMENT")
public class DepartmentPO {
    
    /**
     * 部门ID（主键）
     */
    @Id
    @Column(name = "C_DEPT_ID", length = 20)
    private String deptId;
    
    /**
     * 部门编码（唯一）
     */
    @Column(name = "C_DEPT_CODE", length = 50, nullable = false, unique = true)
    private String deptCode;
    
    /**
     * 部门名称
     */
    @Column(name = "C_DEPT_NAME", length = 100, nullable = false)
    private String deptName;
    
    /**
     * 父部门ID
     */
    @Column(name = "C_PARENT_ID", length = 20)
    private String parentId;
    
    /**
     * 部门层级（1为顶级）
     */
    @Column(name = "C_DEPT_LEVEL", nullable = false)
    private Integer deptLevel;
    
    /**
     * 部门路径
     */
    @Column(name = "C_DEPT_PATH", length = 500)
    private String deptPath;
    
    /**
     * 排序号
     */
    @Column(name = "C_SORT_ORDER", nullable = false)
    private Integer sortOrder;
    
    /**
     * 部门负责人
     */
    @Column(name = "C_LEADER", length = 50)
    private String leader;
    
    /**
     * 联系电话
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
     * 部门描述
     */
    @Column(name = "C_DESCRIPTION", length = 500)
    private String description;
    
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
     * 创建人
     */
    @Column(name = "C_CREATOR", length = 50)
    private String creator;
    
    /**
     * 更新人
     */
    @Column(name = "C_UPDATER", length = 50)
    private String updater;
    
    public String getDeptId() {
        return deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    
    public String getDeptCode() {
        return deptCode;
    }
    
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    
    public String getDeptName() {
        return deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public Integer getDeptLevel() {
        return deptLevel;
    }
    
    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }
    
    public String getDeptPath() {
        return deptPath;
    }
    
    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }
    
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public String getLeader() {
        return leader;
    }
    
    public void setLeader(String leader) {
        this.leader = leader;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
