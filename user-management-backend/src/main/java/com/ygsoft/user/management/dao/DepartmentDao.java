package com.ygsoft.user.management.dao;

import com.ygsoft.user.management.domain.po.DepartmentPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门数据访问接口
 * 
 * 使用JPA进行数据访问
 * 
 * @author developer
 * @date 2024-12-15
 */
@Repository
public interface DepartmentDao extends JpaRepository<DepartmentPO, String>, JpaSpecificationExecutor<DepartmentPO> {
    
    /**
     * 根据部门编码查询部门
     * 
     * @param deptCode 部门编码
     * @return 部门对象
     */
    DepartmentPO findByDeptCode(String deptCode);
    
    /**
     * 根据父部门ID查询子部门列表
     * 
     * @param parentId 父部门ID
     * @return 子部门列表
     */
    List<DepartmentPO> findByParentIdOrderBySortOrderAsc(String parentId);
    
    /**
     * 根据父部门ID和状态查询子部门列表
     * 
     * @param parentId 父部门ID
     * @param status 状态
     * @return 子部门列表
     */
    List<DepartmentPO> findByParentIdAndStatusOrderBySortOrderAsc(String parentId, String status);
    
    /**
     * 根据状态查询所有部门
     * 
     * @param status 状态
     * @return 部门列表
     */
    List<DepartmentPO> findByStatusOrderByDeptLevelAscSortOrderAsc(String status);
    
    /**
     * 查询所有部门（按层级和排序号排序）
     * 
     * @return 部门列表
     */
    List<DepartmentPO> findAllByOrderByDeptLevelAscSortOrderAsc();
    
    /**
     * 统计指定父部门下的子部门数量
     * 
     * @param parentId 父部门ID
     * @return 子部门数量
     */
    long countByParentId(String parentId);
    
    /**
     * 根据部门ID列表查询部门
     * 
     * @param deptIds 部门ID列表
     * @return 部门列表
     */
    List<DepartmentPO> findByDeptIdIn(List<String> deptIds);
}
