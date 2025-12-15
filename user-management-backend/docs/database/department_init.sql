-- =============================================
-- 部门管理数据库初始化脚本
-- 版本: v1.0
-- 日期: 2024-12-15
-- 说明: 创建部门表并插入测试数据
-- =============================================

-- 1. 创建部门表
CREATE TABLE IF NOT EXISTS TBL_DEPARTMENT (
    C_DEPT_ID VARCHAR(20) NOT NULL COMMENT '部门ID',
    C_DEPT_CODE VARCHAR(50) NOT NULL COMMENT '部门编码',
    C_DEPT_NAME VARCHAR(100) NOT NULL COMMENT '部门名称',
    C_PARENT_ID VARCHAR(20) COMMENT '父部门ID',
    C_DEPT_LEVEL INT NOT NULL DEFAULT 1 COMMENT '部门层级',
    C_DEPT_PATH VARCHAR(500) COMMENT '部门路径',
    C_SORT_ORDER INT NOT NULL DEFAULT 0 COMMENT '排序号',
    C_LEADER VARCHAR(50) COMMENT '部门负责人',
    C_PHONE VARCHAR(20) COMMENT '联系电话',
    C_EMAIL VARCHAR(100) COMMENT '邮箱',
    C_STATUS VARCHAR(1) NOT NULL DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
    C_DESCRIPTION VARCHAR(500) COMMENT '部门描述',
    C_CREATE_TIME DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    C_UPDATE_TIME DATETIME COMMENT '更新时间',
    C_CREATOR VARCHAR(50) COMMENT '创建人',
    C_UPDATER VARCHAR(50) COMMENT '更新人',
    PRIMARY KEY (C_DEPT_ID),
    UNIQUE KEY UK_DEPT_CODE (C_DEPT_CODE),
    KEY IDX_PARENT_ID (C_PARENT_ID),
    KEY IDX_STATUS (C_STATUS),
    KEY IDX_DEPT_LEVEL (C_DEPT_LEVEL)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 2. 插入测试数据
INSERT INTO TBL_DEPARTMENT (C_DEPT_ID, C_DEPT_CODE, C_DEPT_NAME, C_PARENT_ID, C_DEPT_LEVEL, C_DEPT_PATH, C_SORT_ORDER, C_LEADER, C_PHONE, C_EMAIL, C_STATUS, C_DESCRIPTION, C_CREATE_TIME, C_CREATOR) VALUES
('D001', 'DEPT001', '总公司', NULL, 1, '/D001', 1, '张总', '13800000001', 'ceo@company.com', '1', '公司总部', NOW(), 'system'),
('D002', 'DEPT002', '技术部', 'D001', 2, '/D001/D002', 1, '李经理', '13800000002', 'tech@company.com', '1', '技术研发部门', NOW(), 'system'),
('D003', 'DEPT003', '市场部', 'D001', 2, '/D001/D003', 2, '王经理', '13800000003', 'market@company.com', '1', '市场营销部门', NOW(), 'system'),
('D004', 'DEPT004', '研发一组', 'D002', 3, '/D001/D002/D004', 1, '赵组长', '13800000004', 'dev1@company.com', '1', '研发第一组', NOW(), 'system'),
('D005', 'DEPT005', '研发二组', 'D002', 3, '/D001/D002/D005', 2, '钱组长', '13800000005', 'dev2@company.com', '1', '研发第二组', NOW(), 'system');

-- 3. 更新用户表，添加部门关联（如果用户表已存在）
-- 注意：这个脚本假设用户表已经存在，如果不存在请先创建用户表
UPDATE TBL_USER SET C_DEPARTMENT_ID = 'D002', C_DEPARTMENT = '技术部' WHERE C_USERNAME = 'admin';
UPDATE TBL_USER SET C_DEPARTMENT_ID = 'D004', C_DEPARTMENT = '研发一组' WHERE C_USERNAME = 'user1';
UPDATE TBL_USER SET C_DEPARTMENT_ID = 'D004', C_DEPARTMENT = '研发一组' WHERE C_USERNAME = 'user2';
UPDATE TBL_USER SET C_DEPARTMENT_ID = 'D005', C_DEPARTMENT = '研发二组' WHERE C_USERNAME = 'user3';
UPDATE TBL_USER SET C_DEPARTMENT_ID = 'D003', C_DEPARTMENT = '市场部' WHERE C_USERNAME = 'user4';

-- 4. 验证数据
SELECT '部门表数据统计' AS INFO;
SELECT 
    C_DEPT_LEVEL AS '层级',
    COUNT(*) AS '部门数量'
FROM TBL_DEPARTMENT
GROUP BY C_DEPT_LEVEL
ORDER BY C_DEPT_LEVEL;

SELECT '部门树形结构' AS INFO;
SELECT 
    C_DEPT_ID AS '部门ID',
    C_DEPT_CODE AS '部门编码',
    C_DEPT_NAME AS '部门名称',
    C_PARENT_ID AS '父部门ID',
    C_DEPT_LEVEL AS '层级',
    C_DEPT_PATH AS '路径',
    C_STATUS AS '状态'
FROM TBL_DEPARTMENT
ORDER BY C_DEPT_LEVEL, C_SORT_ORDER;

-- =============================================
-- 脚本执行完成
-- =============================================
