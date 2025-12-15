package com.ygsoft.user.management.infrastructure.constant;

/**
 * 部门常量类
 * 
 * 定义部门管理相关的常量
 * 
 * @author developer
 * @date 2024-12-15
 */
public class DepartmentConstant {
    
    /**
     * 状态：启用
     */
    public static final String STATUS_ENABLED = "1";
    
    /**
     * 状态：禁用
     */
    public static final String STATUS_DISABLED = "0";
    
    /**
     * 最大层级限制
     */
    public static final int MAX_DEPT_LEVEL = 5;
    
    /**
     * 批量操作最大数量
     */
    public static final int MAX_BATCH_SIZE = 100;
    
    /**
     * 部门编码最小长度
     */
    public static final int DEPT_CODE_MIN_LENGTH = 3;
    
    /**
     * 部门编码最大长度
     */
    public static final int DEPT_CODE_MAX_LENGTH = 50;
    
    /**
     * 部门编码正则表达式（字母、数字、下划线）
     */
    public static final String DEPT_CODE_PATTERN = "^[a-zA-Z0-9_]+$";
    
    /**
     * 默认排序号
     */
    public static final int DEFAULT_SORT_ORDER = 0;
    
    /**
     * 顶级部门层级
     */
    public static final int TOP_DEPT_LEVEL = 1;
    
    /**
     * 部门ID前缀
     */
    public static final String DEPT_ID_PREFIX = "D";
    
    /**
     * 部门路径分隔符
     */
    public static final String DEPT_PATH_SEPARATOR = "/";
}
