package com.ygsoft.user.management.domain.vo;

/**
 * 用户查询条件对象
 * 
 * 用于接收前端查询参数
 * 
 * @author developer
 * @date 2024-12-15
 */
public class UserQueryVO {
    
    /**
     * 用户名（模糊查询）
     */
    private String username;
    
    /**
     * 真实姓名（模糊查询）
     */
    private String realName;
    
    /**
     * 部门名称
     */
    private String department;
    
    /**
     * 角色名称
     */
    private String role;
    
    /**
     * 状态：1-启用，0-禁用
     */
    private String status;
    
    /**
     * 创建日期范围 [开始日期, 结束日期]
     */
    private String[] createTime;
    
    /**
     * 每页大小
     */
    private Integer pageSize;
    
    /**
     * 页码（从1开始）
     */
    private Integer pageIndex;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String[] getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String[] createTime) {
        this.createTime = createTime;
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
