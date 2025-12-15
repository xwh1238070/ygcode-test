package com.ygsoft.user.management.dao;

import com.ygsoft.user.management.domain.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用户数据访问接口
 * 
 * 使用 JPA 提供基础的 CRUD 操作
 * 
 * @author developer
 * @date 2024-12-15
 */
@Repository
public interface UserDao extends JpaRepository<UserPO, String> {
    
    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    Optional<UserPO> findByUsername(String username);
    
    /**
     * 根据用户名判断是否存在
     * 
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);
    
    /**
     * 根据状态查询用户列表
     * 
     * @param status 状态
     * @return 用户列表
     */
    List<UserPO> findByStatus(String status);
    
    /**
     * 根据部门ID查询用户列表
     * 
     * @param departmentId 部门ID
     * @return 用户列表
     */
    List<UserPO> findByDepartmentId(String departmentId);
    
    /**
     * 根据角色ID查询用户列表
     * 
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<UserPO> findByRoleId(String roleId);
    
    /**
     * 根据用户ID列表批量查询
     * 
     * @param userIds 用户ID列表
     * @return 用户列表
     */
    List<UserPO> findByUserIdIn(List<String> userIds);
    
    /**
     * 根据用户ID列表批量删除
     * 
     * @param userIds 用户ID列表
     * @return 删除数量
     */
    int deleteByUserIdIn(List<String> userIds);
}
