# 用户管理后端项目

## 项目概述

基于 Spring Boot 的用户管理后端服务，提供用户的增删改查、状态管理、密码管理以及数据导入导出功能。

## 技术栈

- **框架**: Spring Boot 2.x
- **JDK**: 1.8
- **数据库**: MySQL 5.7+
- **数据访问**: JPA + ORMap 框架
- **API 文档**: Swagger
- **构建工具**: Maven

## 项目结构

```
user-management-backend/
├── docs/                                           # 文档目录
│   └── 用户管理需求详细设计文档.md                  # 详细设计文档
├── src/
│   └── main/
│       ├── java/
│       │   └── com/ygsoft/user/management/
│       │       ├── domain/                         # 数据模型层
│       │       │   ├── po/                         # 持久化对象
│       │       │   │   └── UserPO.java
│       │       │   ├── bo/                         # 业务对象
│       │       │   │   └── UserBO.java
│       │       │   ├── vo/                         # 视图对象
│       │       │   │   ├── UserVO.java
│       │       │   │   ├── UserQueryVO.java
│       │       │   │   └── UserBatchVO.java
│       │       │   └── transfer/                   # 模型转换
│       │       │       └── UserTransfer.java
│       │       ├── infrastructure/                 # 基础设施层
│       │       │   ├── constant/                   # 常量类
│       │       │   │   ├── UserConstant.java
│       │       │   │   └── ErrorCode.java
│       │       │   └── util/                       # 工具类
│       │       │       └── PasswordUtil.java
│       │       ├── dao/                            # 数据访问层（待实现）
│       │       ├── service/                        # 服务层（待实现）
│       │       ├── controller/                     # 控制器层（待实现）
│       │       └── boot/                           # 启动类（待实现）
│       └── resources/                              # 资源文件（待实现）
└── README.md                                       # 项目说明
```

## 已完成的工作

### 1. 需求分析与设计 ✅

- [x] 需求文档分析
- [x] 数据库设计
- [x] 接口设计
- [x] 详细设计文档输出

### 2. Domain 层 ✅

#### 持久化对象（PO）
- [x] `UserPO.java` - 用户持久化对象，对应数据库表 TBL_USER

#### 业务对象（BO）
- [x] `UserBO.java` - 用户业务对象，用于 Service 层内部处理

#### 视图对象（VO）
- [x] `UserVO.java` - 用户视图对象，用于前端交互
- [x] `UserQueryVO.java` - 查询条件对象
- [x] `UserBatchVO.java` - 批量操作对象

#### 模型转换（Transfer）
- [x] `UserTransfer.java` - 提供 PO、BO、VO 之间的转换方法

### 3. Infrastructure 层 ✅

#### 常量类
- [x] `UserConstant.java` - 用户相关常量（状态、默认密码等）
- [x] `ErrorCode.java` - 错误码定义

#### 工具类
- [x] `PasswordUtil.java` - 密码加密和验证工具

## 待实现的功能

### 1. DAO 层
- [ ] `UserDao.java` - JPA 数据访问接口
- [ ] `UserDaoImpl.java` - 复杂查询实现
- [ ] SQL 配置文件

### 2. Service 层
- [ ] `UserService.java` - 业务服务接口
- [ ] `UserServiceImpl.java` - 业务服务实现
- [ ] `UserQueryService.java` - 查询服务接口
- [ ] `UserQueryServiceImpl.java` - 查询服务实现

### 3. Controller 层
- [ ] `UserController.java` - 用户管理控制器

### 4. 配置文件
- [ ] `application.yml` - 应用配置
- [ ] `pom.xml` - Maven 依赖配置

### 5. 启动类
- [ ] `UserManagementApplication.java` - Spring Boot 启动类

### 6. 单元测试
- [ ] Controller 层测试
- [ ] Service 层测试
- [ ] DAO 层测试

## 核心功能

### 查询功能
- 用户列表查询（分页、多条件）
- 用户总数查询
- 用户详情查询

### 数据管理
- 新增用户
- 编辑用户
- 删除用户（支持批量）

### 用户操作
- 重置密码（支持批量）
- 启用用户（支持批量）
- 禁用用户（支持批量）

### 数据交换
- 导出用户数据（Excel）
- 导入用户数据（Excel）

## API 接口

### 基础路径
```
{securitydomain}/{vipaddress}/{读写分离标识}/model/user/
```

### 接口列表

| 接口 | 路径 | 方法 | 说明 |
|------|------|------|------|
| 查询用户列表 | /list | POST | 分页查询 |
| 获取用户总数 | /count | POST | 统计总数 |
| 获取用户详情 | /detail | POST | 查询详情 |
| 新增用户 | /add | POST | 创建用户 |
| 编辑用户 | /edit | POST | 更新信息 |
| 删除用户 | /delete | POST | 删除用户 |
| 重置密码 | /resetPassword | POST | 重置密码 |
| 启用用户 | /enable | POST | 启用用户 |
| 禁用用户 | /disable | POST | 禁用用户 |
| 导出数据 | /export | POST | 导出Excel |
| 导入数据 | /import | POST | 导入Excel |

## 数据库设计

### 用户表（TBL_USER）

| 字段名 | 类型 | 长度 | 说明 |
|--------|------|------|------|
| C_USER_ID | VARCHAR | 20 | 用户ID（主键） |
| C_USERNAME | VARCHAR | 50 | 用户名（唯一） |
| C_PASSWORD | VARCHAR | 200 | 密码（加密） |
| C_REAL_NAME | VARCHAR | 50 | 真实姓名 |
| C_DEPARTMENT_ID | VARCHAR | 20 | 部门ID |
| C_DEPARTMENT | VARCHAR | 100 | 部门名称 |
| C_ROLE_ID | VARCHAR | 20 | 角色ID |
| C_ROLE | VARCHAR | 50 | 角色名称 |
| C_PHONE | VARCHAR | 20 | 手机号 |
| C_EMAIL | VARCHAR | 100 | 邮箱 |
| C_STATUS | VARCHAR | 1 | 状态（1-启用，0-禁用） |
| C_CREATE_TIME | DATETIME | - | 创建时间 |
| C_UPDATE_TIME | DATETIME | - | 更新时间 |
| C_LAST_LOGIN_TIME | DATETIME | - | 最后登录时间 |
| C_CREATOR | VARCHAR | 50 | 创建人 |
| C_UPDATER | VARCHAR | 50 | 更新人 |

## 业务规则

### 用户名规则
- 长度：3-50 个字符
- 格式：字母、数字、下划线
- 唯一性：系统内唯一

### 密码规则
- 默认密码：`123456`
- 加密方式：BCrypt（建议）
- 重置密码：重置为默认密码

### 状态规则
- `1`：启用
- `0`：禁用
- 新增用户默认启用

### 批量操作规则
- 最大批量数：100
- 失败策略：部分失败继续执行

## 错误码

| 错误码 | 说明 |
|--------|------|
| 0 | 成功 |
| 1001 | 用户名已存在 |
| 1002 | 用户不存在 |
| 1003 | 用户名格式不正确 |
| 1004 | 必填字段为空 |
| 1005 | 批量操作数量超限 |
| 1006 | 导入文件格式错误 |
| 9999 | 系统异常 |

## 开发规范

本项目严格遵循《Java 后端编码规范》，包括：

- 分层架构设计
- 命名规范
- 代码注释规范
- 日志记录规范
- 异常处理规范
- SQL 编写规范
- 安全编码规范

## 下一步工作

1. **完成 DAO 层实现**
   - 创建 JPA Repository 接口
   - 实现复杂查询逻辑
   - 编写 SQL 配置文件

2. **完成 Service 层实现**
   - 实现业务逻辑
   - 添加事务管理
   - 实现数据验证

3. **完成 Controller 层实现**
   - 实现 RESTful API
   - 添加参数验证
   - 配置 Swagger 文档

4. **配置文件和启动类**
   - 配置数据源
   - 配置日志
   - 创建启动类

5. **单元测试**
   - 编写测试用例
   - 测试覆盖率达到 80%+

6. **文档完善**
   - API 接口文档
   - 部署文档
   - 运维文档

## 注意事项

1. **包结构问题**：当前代码文件的包声明与实际目录结构不匹配，需要调整项目结构或配置 Maven 的 source 目录。

2. **依赖管理**：需要创建 `pom.xml` 文件，添加必要的依赖：
   - Spring Boot Starter Web
   - Spring Boot Starter Data JPA
   - MySQL Connector
   - Swagger
   - 其他必要依赖

3. **密码加密**：当前 `PasswordUtil` 使用简单示例实现，实际项目中应使用 BCrypt 等成熟的加密算法。

4. **MCP 服务调用**：在数据库设计阶段，应调用 MCP 服务获取标准元素，确保字段命名符合企业规范。

## 联系方式

如有问题或建议，请联系开发团队。

---

**文档版本**: v1.0  
**更新日期**: 2024-12-15  
**项目状态**: 开发中
