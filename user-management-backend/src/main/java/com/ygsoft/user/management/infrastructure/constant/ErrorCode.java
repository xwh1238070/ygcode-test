package com.ygsoft.user.management.infrastructure.constant;

/**
 * 错误码常量类
 * 
 * 定义用户管理相关的错误码
 * 
 * @author developer
 * @date 2024-12-15
 */
public class ErrorCode {
    
    /**
     * 成功
     */
    public static final String SUCCESS = "0";
    
    /**
     * 用户名已存在
     */
    public static final String USERNAME_EXISTS = "1001";
    
    /**
     * 用户不存在
     */
    public static final String USER_NOT_FOUND = "1002";
    
    /**
     * 用户名格式不正确
     */
    public static final String USERNAME_INVALID = "1003";
    
    /**
     * 必填字段为空
     */
    public static final String REQUIRED_FIELD_EMPTY = "1004";
    
    /**
     * 批量操作数量超限
     */
    public static final String BATCH_SIZE_EXCEEDED = "1005";
    
    /**
     * 导入文件格式错误
     */
    public static final String IMPORT_FILE_ERROR = "1006";
    
    /**
     * 系统异常
     */
    public static final String SYSTEM_ERROR = "9999";
}
