package com.ygsoft.user.management.domain.vo;

import java.util.List;

/**
 * 用户批量操作对象
 * 
 * 用于批量删除、启用、禁用、重置密码等操作
 * 
 * @author developer
 * @date 2024-12-15
 */
public class UserBatchVO {
    
    /**
     * 用户ID列表
     */
    private List<String> userIds;
    
    public List<String> getUserIds() {
        return userIds;
    }
    
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
