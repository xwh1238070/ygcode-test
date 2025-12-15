package com.ygsoft.user.management.service.query;

import com.ygsoft.user.management.domain.vo.UserQueryVO;
import com.ygsoft.user.management.domain.vo.UserVO;

import java.util.List;

/**
 * 用户查询服务接口
 * 
 * 专注于用户数据查询，不涉及数据修改
 * 
 * @author developer
 * @date 2024-12-15
 */
public interface UserQueryService {
    
    /**
     * 根据ID查询用户详情
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVO getUserById(String userId);
    
    /**
     * 分页查询用户列表
     * 
     * @param queryVO 查询条件
     * @return 用户列表
     */
    List<UserVO> getUserList(UserQueryVO queryVO);
    
    /**
     * 查询用户总数
     * 
     * @param queryVO 查询条件
     * @return 用户总数
     */
    long getUserCount(UserQueryVO queryVO);
}
