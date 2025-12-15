# Java 后端编码智能体 - 完整指南

> 本文档为 Java 后端编码智能体提供完整的使用指南和技术规范，适用于 Context7 平台。

---

## 目录

1. [概览](#1-概览)
2. [架构设计](#2-架构设计)
3. [执行流程](#3-执行流程)
4. [编码规范](#4-编码规范)
5. [SQL 规范](#5-sql-规范)
6. [数据交互](#6-数据交互)
7. [最佳实践](#7-最佳实践)

---

# 1. 概览

## 1.1 智能体简介

**Java 后端编码智能体**是一个专业的后端代码生成工具，能够根据需求文档和详细设计文档自动生成符合公司规范的高质量 Java 后端代码。

### 核心能力

- 📋 **需求分析**: 深度解析需求文档，识别功能点和变更影响
- 🏗️ **架构设计**: 基于分层架构自动生成完整的项目结构
- 💻 **代码生成**: 生成符合规范的 Controller、Service、DAO 层代码
- 🗄️ **数据库设计**: 自动设计物理表结构和数据模型
- ✅ **单元测试**: 自动生成完整的单元测试代码
- 📝 **文档输出**: 生成详细设计文档和接口文档

### 适用场景

1. **新功能开发**: 根据需求文档快速生成完整的后端代码
2. **功能变更**: 识别历史功能影响，生成变更代码
3. **代码重构**: 按照最新规范重构现有代码
4. **接口开发**: 快速生成标准化的 RESTful API

## 1.2 技术栈

| 技术 | 版本/说明 |
|------|----------|
| **框架** | Spring Boot |
| **JDK** | 1.8 |
| **数据库** | MySQL / Oracle（兼容） |
| **依赖管理** | Maven |
| **数据访问** | JPA + 自定义 ORMap 框架 |
| **API 文档** | Swagger |

## 1.3 核心特性

### 智能需求分析

- 自动识别业务场景和功能点
- 分析新增功能与历史功能的关联
- 确定功能优先级和依赖关系
- 识别修改入口和关键信息

### 规范化代码生成

- 严格遵循公司编码规范
- 自动应用设计模式和最佳实践
- 生成符合安全规范的代码
- 支持多种业务场景（缓存、消息、调度等）

### 完整的开发流程

智能体按照以下 9 个步骤执行：

1. **准备阶段**: 读取需求文档和详细设计文档
2. **需求分析**: 深度解析功能点和影响范围
3. **规范读取**: 加载相关编码规范和样例
4. **设计阶段**: 生成详细设计文档（如不存在）
5. **编码阶段**: 生成后端代码
6. **单元测试**: 编写完整的测试用例
7. **检查阶段**: 验证代码完整性和编译状态
8. **总结阶段**: 输出总结和接口文档

### 多场景支持

根据需求场景自动加载相应规范：

- 🔄 **任务调度**: 定时任务和调度逻辑
- 💾 **缓存读写**: Redis 缓存操作
- 📨 **消息收发**: 消息队列处理
- 📁 **非结构化**: 文件存储和处理
- ⚡ **异步处理**: 异步任务执行
- 🗃️ **数据交互**: 数据库 CRUD 操作
- 📥 **导入导出**: Excel 导入导出功能

## 1.4 输入与输出

### 输入要求

1. **需求文档**（必需）
   - Markdown 格式
   - 包含完整的业务场景描述
   - 明确的功能点说明

2. **详细设计文档**（可选）
   - Markdown 格式
   - 包含数据库设计
   - 接口设计说明

### 输出内容

1. **源代码文件**
   - Controller、Service、DAO 层代码
   - 数据模型（VO、BO、PO）
   - 配置文件

2. **单元测试**
   - 正向测试用例
   - 反向测试用例
   - 边界和异常测试

3. **文档**
   - 详细设计文档（如不存在）
   - 接口文档（包含请求/响应参数）
   - 总结文档（实现功能清单）

---

# 2. 架构设计

## 2.1 分层架构

### 架构图

```
┌─────────────────────────────────────────────────────────┐
│                     Controller 层                        │
│              (HTTP 请求处理、参数校验)                    │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                      Service 层                          │
│         ┌──────────────────┬──────────────────┐         │
│         │  Application     │     Query        │         │
│         │  (业务服务)      │   (查询服务)     │         │
│         └──────────────────┴──────────────────┘         │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                       DAO 层                             │
│              (数据访问、数据库操作)                       │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                     Domain 层                            │
│         ┌────────┬────────┬────────┬──────────┐         │
│         │   VO   │   BO   │   PO   │ Transfer │         │
│         └────────┴────────┴────────┴──────────┘         │
└─────────────────────────────────────────────────────────┘
```

### 数据流向

```
请求 → Controller → Service → DAO → Database
                      ↓
                   Domain
                (VO/BO/PO/Transfer)
```

## 2.2 完整项目结构

```
项目根目录/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.ygsoft.{产品域}.{模块简称}/
│   │   │       ├── boot/                    # 应用启动
│   │   │       ├── controller/              # 控制器层
│   │   │       ├── domain/                  # 数据模型
│   │   │       │   ├── vo/                  # 视图对象
│   │   │       │   ├── bo/                  # 业务对象
│   │   │       │   ├── po/                  # 持久化对象
│   │   │       │   └── transfer/            # 模型转换
│   │   │       ├── service/                 # 服务层
│   │   │       │   ├── application/         # 业务服务
│   │   │       │   └── query/               # 查询服务
│   │   │       ├── infrastructure/          # 基础设施
│   │   │       │   ├── util/                # 工具类
│   │   │       │   ├── config/              # 配置类
│   │   │       │   └── constant/            # 常量类
│   │   │       ├── dao/                     # 数据访问层
│   │   │       ├── schedule/                # 定时任务
│   │   │       └── consumer/                # 消息消费者
│   │   └── resources/                       # 资源文件
│   │       ├── application.yml              # 主配置文件
│   │       └── META-INF/                    # 元信息配置
│   └── test/                                # 测试代码
└── pom.xml                                  # Maven 配置
```

## 2.3 各层详细说明

### Controller 层

**职责**: HTTP 请求处理

**核心功能**:
- 接收和验证 HTTP 请求参数
- 调用 Service 层处理业务逻辑
- 返回统一格式的响应结果
- 不包含业务逻辑

**命名规范**: `[业务功能] + Controller`

**URL 格式**: `{securitydomain}/{vipaddress}/{读写分离标识}/model/{modelname}/`

**安全域类型**:
- `inner`: 系统内部微服务调用（局域网 IP 限制）
- `member`: 前端应用调用
- `outer`: 不同系统间调用（应用鉴权）
- `open`: 对外开放接口（应用鉴权）

**返回值**: 统一使用 `CommonResult<T>`

### Service 层

**职责**: 业务逻辑处理

#### Application Service（业务服务）

**职责**:
- 处理复杂业务逻辑
- 事务管理
- 业务规则实现
- 数据验证和转换

**命名规范**: `[业务功能] + Service`

#### Query Service（查询服务）

**职责**:
- 专注于数据查询
- 不涉及数据修改
- 优化查询性能

**命名规范**: `[业务功能] + QueryService`

**特点**:
- 只读操作，不开启事务
- 可以直接返回 VO 对象
- 支持复杂查询和分页

### DAO 层

**职责**: 数据访问和持久化

**技术选型**:
- **JPA**: 简单的增删改查
- **ORMap 框架**: 复杂查询和动态 SQL

**命名规范**: `[实体名] + Dao`

### Domain 层

**职责**: 数据模型定义

#### VO (View Object) - 视图对象

**用途**: Controller 层与前端交互

**特点**:
- 包含前端需要的字段
- 可能包含格式化后的数据
- 用于接收请求参数和返回响应

#### BO (Business Object) - 业务对象

**用途**: Service 层内部使用

**特点**:
- 包含业务逻辑需要的字段
- 可能包含计算字段
- 用于业务处理过程

#### PO (Persistent Object) - 持久化对象

**用途**: 与数据库表对应

**特点**:
- 使用 JPA 注解
- 字段与数据库列一一对应
- 不包含业务逻辑

#### Transfer - 模型转换

**用途**: 不同模型间的转换

**特点**:
- 提供静态转换方法
- 处理字段映射和格式转换
- 避免在业务代码中进行转换

### Infrastructure 层

**职责**: 提供技术支持和通用功能

- **Util**: 工具类（DateUtil、StringUtil 等）
- **Config**: 配置类（数据源、Redis 等）
- **Constant**: 常量类（错误码、业务常量等）

### Schedule 层

**职责**: 定时任务处理

**特点**:
- 使用 `@Scheduled` 注解
- 配置执行周期
- 处理定时业务逻辑

### Consumer 层

**职责**: 消息队列消费

**特点**:
- 监听消息队列
- 处理异步消息
- 实现事件驱动

## 2.4 代码复杂度规范

### 文件级别
- 单个 Java 类文件最大长度: **1000 行**（含注释）
- 单行最大长度: **120 个字符**

### 方法级别
- 单个方法最大长度: **50 行**
- 方法参数个数: **不超过 7 个**（超过则封装为对象）
- 匿名类代码行数: **不超过 20 行**

### SQL 规范
- 不允许在 Java 代码中包含 SQL 语句
- SQL 语句必须放在配置文件中

---

# 3. 执行流程

## 3.1 完整流程图

```
第一步: 准备阶段
    ↓
第二步: 需求分析阶段
    ↓
第四步: 规范读取阶段
    ↓
第五步: 设计阶段
    ↓
第六步: 编码阶段
    ↓
第七步: 单元测试阶段
    ↓
第八步: 检查内容完整
    ↓
第九步: 总结阶段
```

## 3.2 详细步骤说明

### 第一步：准备阶段

**目标**: 收集必要的输入文档

**执行内容**:
1. 读取需求文档（MD 格式）
2. 检查需求文档是否存在
3. 读取详细设计文档（若存在，MD 格式）

**关键决策**:
- ❌ 如果需求文档不存在 → **退出智能体**
- ✅ 如果需求文档存在 → 继续执行

### 第二步：需求分析阶段

**目标**: 深度理解需求，识别关键信息

**执行内容**:

1. **分析业务场景**: 识别主要业务流程、理解业务背景和目标
2. **深度解析功能点**: 列出所有功能点、分析详细需求、识别功能点关联
3. **识别新增功能和变更点**: 区分新增和修改功能、分析变更影响
4. **分析历史功能**: 如涉及修改，分析现有代码相关功能点
5. **确定功能优先级**: 识别功能依赖关系、确定开发顺序
6. **识别修改入口**: 对于受影响的历史功能，识别修改入口
7. **分析详细设计文档**: 如存在，分析设计方案

### 第四步：规范读取阶段

**目标**: 加载相关的编码规范和样例代码

**执行内容**:

1. **加载基础规范**: `codebase.md`（后端编码规范）

2. **根据场景加载专项规范**:

| 业务场景 | 加载规范 | 包含内容 |
|---------|---------|---------|
| 涉及调度任务 | 任务调度.md + 代码样例 | 定时任务配置和实现 |
| 涉及缓存 | 缓存读写.md + 代码样例 | Redis 缓存操作 |
| 涉及消息 | 消息收发.md + 代码样例 | 消息队列处理 |
| 涉及非结构化 | 非结构化.md | 文件存储和处理 |
| 涉及异步处理 | 异步处理.md | 异步任务执行 |
| 涉及数据交互 | java代码数据库交互.md + 样例 | 数据库 CRUD 操作 |
| 涉及导入 | 导入组件.md | Excel 导入功能 |
| 涉及导出 | 导出组件.md | Excel 导出功能 |

### 第五步：设计阶段

**目标**: 生成或验证详细设计文档

**执行流程**:

1. **检查详细设计文档**: 如存在则跳过设计阶段

2. **详细设计流程**（如不存在）:

   **步骤1**: 按行业标准设计物理表结构和数据模型
   
   **步骤2**: 输出初步设计
   
   **步骤3**: ⚠️ **重要** - 调用 MCP 服务获取标准元素
   - 输入：表名（如已知）或字段中文名称
   - 输出：标准元素信息（英文名称、字段名称、数据类型、长度、精度）
   
   **步骤4**: 字段对比和更新
   - 物理表字段名 ← 标准元素的字段名称
   - 物理表字段类型 ← 标准元素的数据类型
   - 物理表字段长度和精度 ← 标准元素的长度和精度
   - 数据模型属性名 ← 标准元素的英文名称（转为小驼峰）
   
   **步骤5**: 输出最终设计（对比更新后的物理表结构和数据模型）
   
   **步骤6**: 输出设计文档（`XXXX需求详细设计文档.md`）

### 第六步：编码阶段

**目标**: 生成符合规范的后端代码

**代码生成顺序**:

1. Domain 层（数据模型）: PO → BO → VO → Transfer
2. DAO 层（数据访问）: DAO 接口 → DAO 实现 → SQL 配置文件
3. Service 层（业务逻辑）: Application Service → Query Service
4. Controller 层（接口层）: Controller 类
5. 其他组件（按需）: Schedule、Consumer、Infrastructure

**关键要求**:
- ✅ 严格遵循编码规范
- ✅ 使用规范的注解
- ✅ 统一的命名风格
- ✅ 完整的注释说明
- ✅ SQL 语句放在配置文件中

### 第七步：编写单元测试阶段

**目标**: 生成完整的单元测试代码

**测试用例类型**:

1. **正向测试**: 正常业务流程、有效输入数据、预期成功结果
2. **反向测试**: 异常业务流程、无效输入数据、预期失败结果
3. **边界测试**: 边界值测试、极限值测试、临界条件测试
4. **异常测试**: 异常情况处理、错误处理验证、异常恢复测试

**增量变更原则**:
- 如果单元测试类已存在 → 补充新的测试场景
- 如果单元测试类不存在 → 新建单元测试类
- 不删除现有的测试用例
- 保持测试用例的独立性

### 第八步：检查内容完整

**目标**: 确保代码质量和完整性

**编译检查**:
1. 第一次失败：分析错误信息，修复明显问题
2. 第二次失败：检查依赖和配置
3. 第三次失败：记录问题，继续后续检查

**内容完整性检查**:
- ✓ 所有类文件都已生成
- ✓ 文件内容没有被截断
- ✓ 方法实现完整
- ✓ 所有功能点都已实现
- ✓ 所有接口都已定义
- ✓ 所有数据模型都已创建

### 第九步：总结阶段

**目标**: 输出总结文档和接口文档

**输出内容**:

1. **总结文档**:
   - 实现功能清单
   - 新增代码文件
   - 修改代码文件
   - 配置文件变更
   - 数据库变更
   - 依赖变更

2. **接口文档**:
   - 接口地址
   - 请求方式
   - 接口功能描述
   - 请求参数
   - 响应参数
   - 请求/响应示例
   - 错误码说明

---

# 4. 编码规范

## 4.1 Controller 层规范

### 基本要求

**命名规范**: `[业务功能] + Controller`

### 完整示例

```java
import com.ygsoft.ecp.service.log.EcpLogFactory;
import com.ygsoft.ecp.service.log.IEcpLog;
import com.ygsoft.necp.core.service.dcispec.EcpPostMapping;
import com.ygsoft.jt.teng.fw.core.base.model.CommonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 * 
 * 职责：
 * 1. 参数接收和转换
 * 2. 调用业务组件
 * 3. 返回统一响应格式
 * 
 * @author developer
 * @date 2023-01-01
 */
@RestController
@Api(tags = "用户管理", description = "用户管理相关接口")
@RequestMapping({"{securitydomain}/{vipaddress}/{读写分离标识}/model/{modelname}/"})
public class UserController {
    
    public static final IEcpLog LOG = EcpLogFactory.getLog(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserQueryService userQueryService;
    
    @ApiOperation(value = "创建用户", notes = "创建新用户", 
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/user/create"), 
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userVO", 
            value = "{\"userName\": \"用户名\", \"age\": \"年龄\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @EcpPostMapping(path = "/create")
    public CommonResult<Long> createUser(@RequestBody(required = true) final UserVO userVO) {
        if(LOG.isInfoEnabled()){
            LOG.info("开始创建用户，用户名：{}", userVO.getUserName());
        }
        
        final Long userId = userService.createUser(userVO);
        
        if(LOG.isInfoEnabled()){
            LOG.info("用户创建成功，用户ID：{}", userId);
        }
        
        return CommonResult.of(userId);
    }
}
```

### 统一返回格式

**必须使用**: `CommonResult<T>`

**静态工厂方法**:

| 方法 | 说明 |
|------|------|
| `of(T value)` | 创建成功响应 |
| `of(T value, String message)` | 创建带消息的响应 |
| `of(T value, int code)` | 创建带状态码的响应 |
| `of(T value, int code, String message)` | 创建完整响应 |

### 日志规范

```java
// 日志对象定义
public static final IEcpLog LOG = EcpLogFactory.getLog(当前类.class);

// 日志使用
if(LOG.isInfoEnabled()){
    LOG.info("操作说明，参数：{}", param);
}

if(LOG.isWarnEnabled()){
    LOG.warn("警告信息：{}", message);
}

if(LOG.isErrorEnabled()){
    LOG.error("错误信息：{}", message, exception);
}
```

## 4.2 Service 层规范

### Application Service（业务服务）

```java
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    private static final IEcpLog LOG = EcpLogFactory.getLog(UserServiceImpl.class);
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserTransfer userTransfer;
    
    @Override
    public Long createUser(UserVO userVO) {
        if(LOG.isInfoEnabled()){
            LOG.info("开始创建用户，用户名：{}", userVO.getUserName());
        }
        
        // 1. 参数验证
        validateUserVO(userVO);
        
        // 2. VO 转 BO
        UserBO userBO = userTransfer.voToBo(userVO);
        
        // 3. 业务逻辑处理
        processUserCreation(userBO);
        
        // 4. BO 转 PO
        UserPO userPO = userTransfer.boToPo(userBO);
        
        // 5. 保存到数据库
        userDao.save(userPO);
        
        return userPO.getUserId();
    }
    
    private void validateUserVO(UserVO userVO) {
        if (userVO == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        if (userVO.getUserName() == null || userVO.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
    }
}
```

### Query Service（查询服务）

```java
@Service
public class UserQueryServiceImpl implements UserQueryService {
    
    private static final IEcpLog LOG = EcpLogFactory.getLog(UserQueryServiceImpl.class);
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserTransfer userTransfer;
    
    @Override
    public UserVO getUserById(Long userId) {
        if(LOG.isInfoEnabled()){
            LOG.info("查询用户信息，用户ID：{}", userId);
        }
        
        UserPO userPO = userDao.findById(userId).orElse(null);
        
        if (userPO == null) {
            return null;
        }
        
        return userTransfer.poToVo(userPO);
    }
}
```

## 4.3 DAO 层规范

### JPA 使用场景

```java
public interface UserDao extends JpaRepository<UserPO, Long> {
    
    UserPO findByUserName(String userName);
    
    List<UserPO> findByAgeBetween(Integer minAge, Integer maxAge);
    
    List<UserPO> findByUserNameLike(String userName);
}
```

### ORMap 框架使用场景

**SQL 配置文件**: `META-INF/ecp-sql/类路径.类名.xml`

```xml
<clazz id="com.ygsoft.xxx.dao.UserDao">
    <sql id="selectByCondition">
        SELECT 
            USER_ID,
            USER_NAME,
            AGE
        FROM TBL_USER
        WHERE 1=1
        <if test="userName != null and userName != ''">
            AND USER_NAME LIKE CONCAT('%', #{userName}, '%')
        </if>
        <if test="minAge != null">
            AND AGE >= #{minAge}
        </if>
        ORDER BY CREATE_TIME DESC
    </sql>
</clazz>
```

**Java 代码使用**:

```java
@Repository
public class UserDaoImpl implements UserDao {
    
    private static final String SQL_CLASS = "com.ygsoft.xxx.dao.UserDao";
    
    public List<UserPO> findByCondition(UserQueryCondition condition) {
        ISQLTemplate sqlTemplate = DBUtil.getSqlTemplate();
        
        String sql = SqlResource.getMyBatisSql(
            SQL_CLASS, 
            "selectByCondition",
            "userName", condition.getUserName(),
            "minAge", condition.getMinAge()
        );
        
        List<UserPO> result = sqlTemplate.findBySQL(sql, UserPO.class);
        
        return result;
    }
}
```

## 4.4 安全规范

### 必须避免的安全漏洞

- ✗ SQL 注入
- ✗ XPath 注入
- ✗ XML 注入
- ✗ 跨站脚本攻击（XSS）
- ✗ 命令注入

### 敏感信息保护

- ✗ 源码中不能包含明文密码（禁止关键字: pwd, password, pswd, secret, mima, 密码）
- ✗ 源码中不能包含客户信息（禁止关键字: nw, gw, nfdw, gjdw, gddw 等）

### 安全编码实践

```java
// ✓ 正确：使用 SecureRandom
SecureRandom secureRandom = new SecureRandom();
byte[] token = new byte[32];
secureRandom.nextBytes(token);

// ✗ 错误：使用 Random
Random random = new Random();
int value = random.nextInt();
```

## 4.5 性能优化规范

### 避免循环中访问数据库

```java
// ✗ 错误：循环中查询数据库
for (Long userId : userIds) {
    UserPO user = userDao.findById(userId);
    // 处理逻辑
}

// ✓ 正确：批量查询
List<UserPO> users = userDao.findAllById(userIds);
Map<Long, UserPO> userMap = users.stream()
    .collect(Collectors.toMap(UserPO::getUserId, Function.identity()));
```

---

# 5. SQL 规范

## 5.1 物理模型和数据模型设计规范

### 数据模型设计流程

1. **已知表名时**: 先调用 MCP 获取表结构信息
2. **未知表名时**: 按行业标准设计 → 输出初步设计 → 调用 MCP 获取标准元素 → 字段对比更新 → 输出最终设计

### 标准元素定义

**标准元素由 MCP 服务返回，包含**:

| 字段 | 说明 |
|------|------|
| 英文名称 | 用于数据模型属性名 |
| 中文名称 | 字段的中文描述 |
| 字段名称 | 数据库表字段名 |
| 数据类型 | 字段的数据类型 |
| 长度 | 字段长度 |
| 精度 | 数值字段的精度 |

### 物理表字段设计规范

**设计原则**:
1. 字段名来源于标准元素的字段名称
2. 字段类型来源于标准元素的数据类型
3. 字段长度和精度来源于标准元素的长度和精度
4. 无对应标准元素时，采用行业标准
5. 禁止字段名重复

### PO 类设计要求

```java
@Entity
@Table(name = "TBL_USER")
public class UserPO {
    
    @Id
    @Column(name = "C_USER_ID", length = 20)
    private Long userId;
    
    @Column(name = "C_USER_NAME", length = 100)
    private String userName;
    
    @Column(name = "C_AGE", precision = 3)
    private Integer age;
    
    // getter/setter
}
```

## 5.2 SQL 代码风格规范

### 格式与排版

```sql
-- ✓ 正确：关键字大写、使用别名、缩进规范
SELECT 
  T1.C_USER_ID,
  T1.C_USER_NAME,
  T2.C_DEPT_NAME
FROM TBL_USER T1
  LEFT JOIN TBL_DEPT T2 ON T1.C_DEPT_ID=T2.C_DEPT_ID
WHERE T1.C_STATUS='1'
  AND T1.C_AGE>=18
ORDER BY T1.C_CREATE_TIME DESC;
```

### 命名规范

- 别名不超过 2 字符
- 禁止使用数据库保留字
- 分页表以 `QT_` 开头

## 5.3 查询优化规范

### 基本优化

- ✗ 禁止使用 `SELECT *`
- ✓ 使用绑定变量
- ✓ 关联表数量不超过 5 个
- ✓ IN 列表：<10 直接用，10-100 递归子查询，>100 临时表

### 索引使用

- ✗ 索引列禁用函数
- ✗ 禁止隐式转换
- ✓ 查询必须走索引

### JOIN 优化

- ✓ 优先使用 INNER JOIN
- ✗ 禁止无效的 LEFT JOIN
- ✗ 禁止使用 FULL JOIN

## 5.4 数据操作规范

### 时间写入

```sql
-- ✓ 正确：使用数据库时间函数
-- MySQL
INSERT INTO TBL_USER (C_USER_ID, C_CREATE_TIME) VALUES (1, NOW());

-- Oracle
INSERT INTO TBL_USER (C_USER_ID, C_CREATE_TIME) VALUES (1, SYSDATE);
```

### 空值处理

```sql
-- ✓ 正确：插入 NULL
INSERT INTO TBL_USER (C_USER_ID, C_EMAIL) VALUES (1, NULL);

-- ✓ 正确：查询 NULL
SELECT * FROM TBL_USER WHERE C_EMAIL IS NULL;
```

### 禁止事项

- ✗ 禁止全量删除/更新普通表
- ✗ 禁止 TRUNCATE 普通表
- ✗ 禁止动态拼接恒等式（WHERE 1=1）

---

# 6. 数据交互

## 6.1 元数据初始化

### 初始化步骤

```java
public void initializeMetaData() {
    final EntityMetaData metaData = new EntityMetaData();
    metaData.setEntityName(UserPO.class.getName());
    
    final List<PropertyMetaData> props = new ArrayList<>();
    metaData.setColList(props);
    metaData.setTableName("TBL_USER");
    
    // userId 字段（主键）
    PropertyMetaData propUserId = new PropertyMetaData();
    propUserId.setName("userId");
    propUserId.setMapName("C_USER_ID");
    propUserId.setDataType(PropertyDataTypeEnum.LONG);
    propUserId.setPropEnum(PropertyTypeEnum.ID);
    propUserId.setLength(20);
    props.add(propUserId);
    
    // 其他字段...
    
    JtMetaDataHolder.instance.loadMetaData(UserPO.class.getName(), metaData);
}
```

## 6.2 CRUD 操作

### 新增和修改

```java
@Autowired
private IJtDataPersistService persistService;

// save 方法会根据主键自动判断插入或更新
UserPO user = new UserPO();
user.setUserId(123456L);
user.setUserName("张三");
persistService.save(user);
```

### 删除

```java
// 根据主键删除
persistService.delete(userId);
```

### 查询

```java
@Autowired
private IJtDataQueryService queryService;

// 构建查询条件
List<ItemCondition> conditions = new ArrayList<>();
conditions.add(new ItemCondition("userName", ItemConditionOp.EQ, "张三"));
conditions.add(new ItemCondition("age", ItemConditionOp.GE, 18));

// 分页查询
PageModel<?> page = queryService.findByPageWithCondition(conditions, 1, 1000);
List<UserPO> users = (List<UserPO>) page.getItems();
```

### ItemConditionOp 操作符

| 操作符 | 说明 |
|--------|------|
| EQ | 等于 (=) |
| NE | 不等于 (!=) |
| GT | 大于 (>) |
| GE | 大于等于 (>=) |
| LT | 小于 (<) |
| LE | 小于等于 (<=) |
| LIKE | 模糊匹配 |
| IN | 在列表中 |
| IS_NULL | 为空 |

---

# 7. 最佳实践

## 7.1 代码组织

### 分层清晰

```java
// ✓ 正确：职责清晰
@RestController
public class UserController {
    @Autowired
    private UserService userService;  // 只依赖 Service 层
}

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;  // 只依赖 DAO 层
}
```

### 面向接口编程

```java
// ✓ 正确：定义接口
public interface UserService {
    UserVO getUserById(Long userId);
}

// ✓ 正确：实现接口
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserVO getUserById(Long userId) {
        // 实现逻辑
    }
}
```

## 7.2 性能优化

### 使用缓存

```java
public UserVO getUserById(Long userId) {
    // 1. 先查缓存
    String cacheKey = "user:" + userId;
    UserVO cachedUser = (UserVO) redisTemplate.opsForValue().get(cacheKey);
    
    if (cachedUser != null) {
        return cachedUser;
    }
    
    // 2. 缓存未命中，查数据库
    UserPO userPO = userDao.findById(userId);
    UserVO userVO = userTransfer.poToVo(userPO);
    
    // 3. 写入缓存
    redisTemplate.opsForValue().set(cacheKey, userVO, 1, TimeUnit.HOURS);
    
    return userVO;
}
```

### 异步处理

```java
public void createOrder(OrderVO orderVO) {
    // 1. 创建订单（同步）
    Long orderId = saveOrder(orderVO);
    
    // 2. 发送通知（异步）
    mappService.doPostAsync(url, orderId, String.class, callback);
}
```

## 7.3 安全编码

### 参数验证

```java
private void validateUserVO(UserVO userVO) {
    if (userVO == null) {
        throw new IllegalArgumentException("用户信息不能为空");
    }
    if (StringUtils.isBlank(userVO.getUserName())) {
        throw new IllegalArgumentException("用户名不能为空");
    }
    if (userVO.getAge() != null && (userVO.getAge() < 0 || userVO.getAge() > 150)) {
        throw new IllegalArgumentException("年龄必须在0-150之间");
    }
}
```

### SQL 注入防护

```java
// ✓ 正确：使用绑定变量
String sql = SqlResource.getMyBatisSql(SQL_CLASS, "selectByUserName", "userName", userName);

// ✗ 错误：字符串拼接
String sql = "SELECT * FROM TBL_USER WHERE C_USER_NAME='" + userName + "'";
```

## 7.4 异常处理

### 自定义业务异常

```java
public class BusinessException extends RuntimeException {
    private String code;
    private String message;
    
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
```

### 统一异常处理

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public CommonResult<Void> handleBusinessException(BusinessException e) {
        LOG.warn("业务异常：{}", e.getMessage());
        return CommonResult.of(null, e.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handleException(Exception e) {
        LOG.error("系统异常", e);
        return CommonResult.of(null, "9999", "系统异常，请联系管理员");
    }
}
```

## 7.5 事务管理

```java
// ✓ 正确：在 Service 层使用事务
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Override
    public void createUser(UserVO userVO) {
        // 多个数据库操作在同一事务中
        userDao.save(userPO);
        userRoleDao.save(userRolePO);
    }
}
```

## 7.6 日志记录

```java
public void createUser(UserVO userVO) {
    // DEBUG：详细的调试信息
    if (LOG.isDebugEnabled()) {
        LOG.debug("开始创建用户，参数：{}", userVO);
    }
    
    // INFO：重要的业务流程信息
    if (LOG.isInfoEnabled()) {
        LOG.info("创建用户，用户名：{}", userVO.getUserName());
    }
    
    try {
        userDao.save(userPO);
        
        if (LOG.isInfoEnabled()) {
            LOG.info("用户创建成功，用户ID：{}", userPO.getUserId());
        }
    } catch (Exception e) {
        // ERROR：错误信息
        if (LOG.isErrorEnabled()) {
            LOG.error("创建用户失败", e);
        }
        throw e;
    }
}
```

## 7.7 核心原则总结

1. **代码清晰**: 命名清晰、结构清晰、逻辑清晰
2. **职责单一**: 每个类、每个方法只做一件事
3. **面向接口**: 依赖接口而不是实现
4. **异常处理**: 合理使用异常，统一异常处理
5. **性能优化**: 避免循环查询、使用缓存、异步处理
6. **安全编码**: 参数验证、防止注入、保护敏感信息
7. **日志记录**: 合理使用日志级别，记录关键信息
8. **测试覆盖**: 编写单元测试，覆盖各种场景

## 7.8 开发检查清单

```
✓ 代码符合编码规范
✓ SQL 语句放在配置文件中
✓ 使用绑定变量防止 SQL 注入
✓ 参数验证完整
✓ 异常处理合理
✓ 事务管理正确
✓ 日志记录完整
✓ 单元测试覆盖
✓ 性能考虑充分
✓ 安全问题已处理
```

---

## 附录：快速参考

### 常用注解

| 注解 | 用途 |
|------|------|
| `@RestController` | 标识 REST 控制器 |
| `@Service` | 标识服务层组件 |
| `@Repository` | 标识数据访问层组件 |
| `@Transactional` | 声明事务 |
| `@Autowired` | 依赖注入 |
| `@EcpPostMapping` | POST 请求映射 |
| `@EcpGetMapping` | GET 请求映射 |

### 常用工具类

| 工具类 | 用途 |
|--------|------|
| `DBUtil.getSqlTemplate()` | 获取 SQL 模板 |
| `SqlResource.getMyBatisSql()` | 获取 SQL 语句 |
| `EcpLogFactory.getLog()` | 获取日志对象 |
| `CommonResult.of()` | 创建统一响应 |

### 文档版本

- **版本**: 1.0.0
- **更新日期**: 2025-12-15
- **适用智能体**: Java 后端编码智能体 v1.0

---

**文档结束**
