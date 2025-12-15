-- ============================================
-- 用户管理系统 - 数据库初始化脚本
-- 数据库类型: MySQL 5.7+
-- 创建日期: 2024-12-15
-- ============================================

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS user_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE user_management;

-- ============================================
-- 1. 创建用户表
-- ============================================

DROP TABLE IF EXISTS TBL_USER;

CREATE TABLE TBL_USER (
    C_USER_ID VARCHAR(20) NOT NULL COMMENT '用户ID',
    C_USERNAME VARCHAR(50) NOT NULL COMMENT '用户名',
    C_PASSWORD VARCHAR(200) NOT NULL COMMENT '密码',
    C_REAL_NAME VARCHAR(50) NOT NULL COMMENT '真实姓名',
    C_DEPARTMENT_ID VARCHAR(20) COMMENT '部门ID',
    C_DEPARTMENT VARCHAR(100) COMMENT '部门名称',
    C_ROLE_ID VARCHAR(20) COMMENT '角色ID',
    C_ROLE VARCHAR(50) COMMENT '角色名称',
    C_PHONE VARCHAR(20) COMMENT '手机号',
    C_EMAIL VARCHAR(100) COMMENT '邮箱',
    C_STATUS VARCHAR(1) NOT NULL DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
    C_CREATE_TIME DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    C_UPDATE_TIME DATETIME COMMENT '更新时间',
    C_LAST_LOGIN_TIME DATETIME COMMENT '最后登录时间',
    C_CREATOR VARCHAR(50) COMMENT '创建人',
    C_UPDATER VARCHAR(50) COMMENT '更新人',
    PRIMARY KEY (C_USER_ID),
    UNIQUE KEY UK_USERNAME (C_USERNAME),
    KEY IDX_DEPARTMENT (C_DEPARTMENT_ID),
    KEY IDX_ROLE (C_ROLE_ID),
    KEY IDX_STATUS (C_STATUS),
    KEY IDX_CREATE_TIME (C_CREATE_TIME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ============================================
-- 2. 插入初始数据
-- ============================================

-- 插入管理员账号
-- 密码: 123456 (实际项目中应使用加密后的密码)
INSERT INTO TBL_USER (
    C_USER_ID,
    C_USERNAME,
    C_PASSWORD,
    C_REAL_NAME,
    C_DEPARTMENT_ID,
    C_DEPARTMENT,
    C_ROLE_ID,
    C_ROLE,
    C_PHONE,
    C_EMAIL,
    C_STATUS,
    C_CREATE_TIME,
    C_CREATOR
) VALUES (
    'U1702627200001',
    'admin',
    'encrypted_123456_1702627200001',
    '系统管理员',
    'dept001',
    '技术部',
    'admin',
    '系统管理员',
    '13800138000',
    'admin@example.com',
    '1',
    NOW(),
    'system'
);

-- 插入测试用户数据
INSERT INTO TBL_USER (
    C_USER_ID,
    C_USERNAME,
    C_PASSWORD,
    C_REAL_NAME,
    C_DEPARTMENT_ID,
    C_DEPARTMENT,
    C_ROLE_ID,
    C_ROLE,
    C_PHONE,
    C_EMAIL,
    C_STATUS,
    C_CREATE_TIME,
    C_CREATOR
) VALUES 
(
    'U1702627200002',
    'zhangsan',
    'encrypted_123456_1702627200002',
    '张三',
    'dept001',
    '技术部',
    'manager',
    '部门经理',
    '13800138001',
    'zhangsan@example.com',
    '1',
    NOW(),
    'admin'
),
(
    'U1702627200003',
    'lisi',
    'encrypted_123456_1702627200003',
    '李四',
    'dept002',
    '市场部',
    'user',
    '普通用户',
    '13800138002',
    'lisi@example.com',
    '1',
    NOW(),
    'admin'
),
(
    'U1702627200004',
    'wangwu',
    'encrypted_123456_1702627200004',
    '王五',
    'dept003',
    '财务部',
    'user',
    '普通用户',
    '13800138003',
    'wangwu@example.com',
    '1',
    NOW(),
    'admin'
),
(
    'U1702627200005',
    'zhaoliu',
    'encrypted_123456_1702627200005',
    '赵六',
    'dept004',
    '人力资源部',
    'user',
    '普通用户',
    '13800138004',
    'zhaoliu@example.com',
    '0',
    NOW(),
    'admin'
);

-- ============================================
-- 3. 查询验证
-- ============================================

-- 查询所有用户
SELECT 
    C_USER_ID AS '用户ID',
    C_USERNAME AS '用户名',
    C_REAL_NAME AS '姓名',
    C_DEPARTMENT AS '部门',
    C_ROLE AS '角色',
    C_PHONE AS '手机号',
    C_EMAIL AS '邮箱',
    CASE C_STATUS 
        WHEN '1' THEN '启用'
        WHEN '0' THEN '禁用'
        ELSE '未知'
    END AS '状态',
    C_CREATE_TIME AS '创建时间'
FROM TBL_USER
ORDER BY C_CREATE_TIME DESC;

-- 统计用户数量
SELECT 
    COUNT(*) AS '总用户数',
    SUM(CASE WHEN C_STATUS = '1' THEN 1 ELSE 0 END) AS '启用用户数',
    SUM(CASE WHEN C_STATUS = '0' THEN 1 ELSE 0 END) AS '禁用用户数'
FROM TBL_USER;

-- ============================================
-- 4. 常用查询示例
-- ============================================

-- 按部门统计用户数
SELECT 
    C_DEPARTMENT AS '部门',
    COUNT(*) AS '用户数'
FROM TBL_USER
GROUP BY C_DEPARTMENT
ORDER BY COUNT(*) DESC;

-- 按角色统计用户数
SELECT 
    C_ROLE AS '角色',
    COUNT(*) AS '用户数'
FROM TBL_USER
GROUP BY C_ROLE
ORDER BY COUNT(*) DESC;

-- 查询最近创建的用户
SELECT 
    C_USERNAME AS '用户名',
    C_REAL_NAME AS '姓名',
    C_DEPARTMENT AS '部门',
    C_CREATE_TIME AS '创建时间'
FROM TBL_USER
ORDER BY C_CREATE_TIME DESC
LIMIT 10;

-- ============================================
-- 5. 性能优化建议
-- ============================================

-- 分析表
ANALYZE TABLE TBL_USER;

-- 查看索引使用情况
SHOW INDEX FROM TBL_USER;

-- ============================================
-- 脚本执行完成
-- ============================================

SELECT '数据库初始化完成！' AS '状态';
