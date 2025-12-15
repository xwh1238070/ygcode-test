package com.ygsoft.user.management.domain.vo;

import java.util.List;

/**
 * 部门树形结构对象
 * 
 * 用于返回部门树形结构数据
 * 
 * @author developer
 * @date 2024-12-15
 */
public class DepartmentTreeVO {
    
    /**
     * 部门ID
     */
    private String deptId;
    
    /**
     * 部门编码
     */
    private String deptCode;
    
    /**
     * 部门名称
     */
    private String deptName;
    
    /**
     * 父部门ID
     */
    private String parentId;
    
    /**
     * 部门层级
     */
    private Integer deptLevel;
    
    /**
     * 排序号
     */
    private Integer sortOrder;
    
    /**
     * 状态（1-启用，0-禁用）
     */
    private String status;
    
    /**
     * 部门用户数量
     */
    private Integer userCount;
    
    /**
     * 子部门列表
     */
    private List<DepartmentTreeVO> children;
    
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
    
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getUserCount() {
        return userCount;
    }
    
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
    
    public List<DepartmentTreeVO> getChildren() {
        return children;
    }
    
    public void setChildren(List<DepartmentTreeVO> children) {
        this.children = children;
    }
}
