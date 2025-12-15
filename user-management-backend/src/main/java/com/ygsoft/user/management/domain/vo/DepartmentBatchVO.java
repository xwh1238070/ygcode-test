package com.ygsoft.user.management.domain.vo;

import java.util.List;

/**
 * 部门批量操作对象
 * 
 * 用于批量删除、启用、禁用等操作
 * 
 * @author developer
 * @date 2024-12-15
 */
public class DepartmentBatchVO {
    
    /**
     * 部门ID列表
     */
    private List<String> deptIds;
    
    public List<String> getDeptIds() {
        return deptIds;
    }
    
    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }
}
