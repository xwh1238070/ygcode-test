package com.ygsoft.user.management.service.application;

import com.ygsoft.user.management.domain.vo.UserVO;
import com.ygsoft.user.management.domain.vo.UserBatchVO;

/**
 * 用户业务服务接口
 * 
 * 处理用户相关的业务逻辑
 * 
 * @author developer
 * @date 2024-12-15
 */
public interface UserService {
    
    /**
     * 创建用户
     * 
     * @param userVO 用户信息
     * @return 用户ID
     */
    String createUser(UserVO userVO);
    
    /**
     * 更新用户信息
     * 
     * @param userVO 用户信息
     * @return 是否成功
     */
    boolean updateUser(UserVO userVO);
    
    /**
     * 删除用户
     * 
     * @param batchVO 批量操作对象
     * @return 删除数量
     */
    int deleteUsers(UserBatchVO batchVO);
    
    /**
     * 重置密码
     * 
     * @param batchVO 批量操作对象
     * @return 重置数量
     */
    int resetPassword(UserBatchVO batchVO);
    
    /**
     * 启用用户
     * 
     * @param batchVO 批量操作对象
     * @return 启用数量
     */
    int enableUsers(UserBatchVO batchVO);
    
    /**
     * 禁用用户
     * 
     * @param batchVO 批量操作对象
     * @return 禁用数量
     */
    int disableUsers(UserBatchVO batchVO);
}
