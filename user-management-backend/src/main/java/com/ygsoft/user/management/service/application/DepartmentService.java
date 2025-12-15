package com.ygsoft.user.management.service.application;

import com.ygsoft.user.management.domain.vo.DepartmentBatchVO;
import com.ygsoft.user.management.domain.vo.DepartmentVO;

/**
 * 部门业务服务接口
 * 
 * 处理部门相关的业务逻辑
 * 
 * @author developer
 * @date 2024-12-15
 */
public interface DepartmentService {
    
    /**
     * 创建部门
     * 
     * @param departmentVO 部门信息
     * @return 部门ID
     */
    String createDepartment(DepartmentVO departmentVO);
    
    /**
     * 更新部门信息
     * 
     * @param departmentVO 部门信息
     * @return 是否成功
     */
    boolean updateDepartment(DepartmentVO departmentVO);
    
    /**
     * 删除部门
     * 
     * @param batchVO 批量操作对象
     * @return 删除数量
     */
    int deleteDepartments(DepartmentBatchVO batchVO);
    
    /**
     * 启用部门
     * 
     * @param batchVO 批量操作对象
     * @return 启用数量
     */
    int enableDepartments(DepartmentBatchVO batchVO);
    
    /**
     * 禁用部门
     * 
     * @param batchVO 批量操作对象
     * @return 禁用数量
     */
    int disableDepartments(DepartmentBatchVO batchVO);
}
