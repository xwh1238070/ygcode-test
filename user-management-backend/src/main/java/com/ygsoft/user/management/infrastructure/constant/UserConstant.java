package com.ygsoft.user.management.infrastructure.constant;

/**
 * 用户常量类
 * 
 * 定义用户管理相关的常量
 * 
 * @author developer
 * @date 2024-12-15
 */
public class UserConstant {
    
    /**
     * 用户状态：启用
     */
    public static final String STATUS_ENABLED = "1";
    
    /**
     * 用户状态：禁用
     */
    public static final String STATUS_DISABLED = "0";
    
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    
    /**
     * 用户ID前缀
     */
    public static final String USER_ID_PREFIX = "U";
    
    /**
     * 批量操作最大数量
     */
    public static final int MAX_BATCH_SIZE = 100;
    
    /**
     * 用户名最小长度
     */
    public static final int USERNAME_MIN_LENGTH = 3;
    
    /**
     * 用户名最大长度
     */
    public static final int USERNAME_MAX_LENGTH = 50;
    
    /**
     * 用户名正则表达式（字母、数字、下划线）
     */
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]+$";
}
