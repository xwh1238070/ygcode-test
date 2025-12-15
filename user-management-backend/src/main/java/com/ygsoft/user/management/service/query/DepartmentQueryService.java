package com.ygsoft.user.management.service.query;

import com.ygsoft.user.management.domain.vo.DepartmentQueryVO;
import com.ygsoft.user.management.domain.vo.DepartmentTreeVO;
import com.ygsoft.user.management.domain.vo.DepartmentVO;

import java.util.List;

/**
 * 部门查询服务接口
 * 
 * 专注于数据查询，不涉及数据修改
 * 
 * @author developer
 * @date 2024-12-15
 */
public interface DepartmentQueryService {
    
    /**
     * 查询部门列表
     * 
     * @param queryVO 查询条件
     * @return 部门列表
     */
    List<DepartmentVO> getDepartmentList(DepartmentQueryVO queryVO);
    
    /**
     * 查询部门树形结构
     * 
     * @param status 状态（可选）
     * @return 部门树形结构列表
     */
    List<DepartmentTreeVO> getDepartmentTree(String status);
    
    /**
     * 获取部门总数
     * 
     * @param queryVO 查询条件
     * @return 部门总数
     */
    long getDepartmentCount(DepartmentQueryVO queryVO);
    
    /**
     * 根据ID查询部门详情
     * 
     * @param deptId 部门ID
     * @return 部门详情
     */
    DepartmentVO getDepartmentById(String deptId);
    
    /**
     * 查询子部门列表
     * 
     * @param parentId 父部门ID
     * @return 子部门列表
     */
    List<DepartmentVO> getChildDepartments(String parentId);
    
    /**
     * 统计部门用户数量
     * 
     * @param deptId 部门ID
     * @return 用户数量
     */
    int getDepartmentUserCount(String deptId);
}
