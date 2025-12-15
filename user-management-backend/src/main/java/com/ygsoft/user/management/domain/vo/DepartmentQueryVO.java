package com.ygsoft.user.management.domain.vo;

/**
 * 部门查询条件对象
 * 
 * 用于接收查询条件参数
 * 
 * @author developer
 * @date 2024-12-15
 */
public class DepartmentQueryVO {
    
    /**
     * 部门名称（模糊查询）
     */
    private String deptName;
    
    /**
     * 部门编码（精确查询）
     */
    private String deptCode;
    
    /**
     * 父部门ID
     */
    private String parentId;
    
    /**
     * 状态（1-启用，0-禁用）
     */
    private String status;
    
    /**
     * 部门层级
     */
    private Integer deptLevel;
    
    /**
     * 每页大小
     */
    private Integer pageSize;
    
    /**
     * 页码
     */
    private Integer pageIndex;
    
    public String getDeptName() {
        return deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public String getDeptCode() {
        return deptCode;
    }
    
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getDeptLevel() {
        return deptLevel;
    }
    
    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public Integer getPageIndex() {
        return pageIndex;
    }
    
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
