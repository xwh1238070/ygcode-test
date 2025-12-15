# Java 后端编码智能体 - 编码规范

## 概述

本文档详细说明 Java 后端开发的编码规范，包括 Controller、Service、DAO 各层的规范要求。

## Controller 层规范

### 基本要求

**命名规范**: `[业务功能] + Controller`

**示例**: `UserController`, `OrderController`, `ProductController`

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
 * 注意：不包含业务逻辑
 * 
 * @author developer
 * @date 2023-01-01
 */
@RestController
@Api(tags = "用户管理", description = "用户管理相关接口")
@RequestMapping({"{securitydomain}/{vipaddress}/{读写分离标识}/model/{modelname}/"})
public class UserController {
    
    /**
     * 日志对象
     */
    public static final IEcpLog LOG = EcpLogFactory.getLog(UserController.class);
    
    /**
     * 依赖注入业务组件（面向接口编程）
     */
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserQueryService userQueryService;
    
    /**
     * 创建用户
     * 
     * @param userVO 用户信息
     * @return 创建结果
     */
    @ApiOperation(value = "创建用户", notes = "创建新用户", 
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = "/user/create"), 
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userVO", 
            value = "{\"userName\": \"用户名\", \"age\": \"年龄\", \"email\": \"邮箱\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @EcpPostMapping(path = "/create")
    public CommonResult<Long> createUser(@RequestBody(required = true) final UserVO userVO) {
        if(LOG.isInfoEnabled()){
            LOG.info("开始创建用户，用户名：{}", userVO.getUserName());
        }
        
        // 调用业务组件
        final Long userId = userService.createUser(userVO);
        
        if(LOG.isInfoEnabled()){
            LOG.info("用户创建成功，用户ID：{}", userId);
        }
        
        // 返回结果包装
        return CommonResult.of(userId);
    }
    
    /**
     * 查询用户详情
     * 
     * @param userId 用户ID
     * @return 用户详情
     */
    @ApiOperation(value = "查询用户详情", notes = "根据用户ID查询用户详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", 
            paramType = "query", dataType = "Long", required = true) })
    @EcpGetMapping(path = "/detail")
    public CommonResult<UserVO> getUserDetail(@RequestParam("userId") final Long userId) {
        if(LOG.isInfoEnabled()){
            LOG.info("查询用户详情，用户ID：{}", userId);
        }
        
        final UserVO userVO = userQueryService.getUserById(userId);
        
        return CommonResult.of(userVO);
    }
}
```

### URL 格式规范

```
{securitydomain}/{vipaddress}/{读写分离标识}/model/{modelname}/
```

**参数说明**:

| 参数 | 说明 | 可选值 |
|------|------|--------|
| securitydomain | 安全域 | inner, member, outer, open |
| vipaddress | VIP地址 | 实际配置的地址 |
| 读写分离标识 | 操作类型 | service（写）, query（读） |
| modelname | 模型名称 | 动态服务标识 |

**安全域说明**:

- `inner`: 系统内部微服务调用，局域网 IP 限制
- `member`: 前端应用调用
- `outer`: 不同系统间调用，应用鉴权
- `open`: 对外开放接口，应用鉴权

### 注解规范

#### 必需注解

```java
@RestController              // 标识为 REST 控制器
@Api                        // Swagger 接口类说明
@RequestMapping             // 请求路径映射
@ApiOperation              // 接口方法说明
@ApiImplicitParams         // 参数说明
@EcpPostMapping            // POST 请求映射（自定义）
@EcpGetMapping             // GET 请求映射（自定义）
```

#### 注解示例

```java
@ApiOperation(
    value = "接口简要说明", 
    notes = "接口详细说明",
    extensions = @Extension(name = "relation", properties = {
        @ExtensionProperty(name = "fullPath", value = "/完整路径"),
        @ExtensionProperty(name = "advice", value = "1")
    })
)
@ApiImplicitParams({
    @ApiImplicitParam(
        name = "参数名",
        value = "参数说明（JSON格式示例）",
        paramType = "body",  // body, query, path, header, form
        dataType = "Object",
        required = true
    )
})
```

### 统一返回格式

**必须使用**: `CommonResult<T>`

**类定义**:
```java
package com.ygsoft.jt.teng.fw.core.base.model;

public class CommonResult<T> implements java.io.Serializable {
    private T data;                          // 结果数据
    private String message = "success";      // 响应消息
    private String code = findNormalCode();  // 状态码
    
    // getter/setter 省略
}
```

**静态工厂方法**:

| 方法 | 说明 | 使用场景 |
|------|------|---------|
| `of(T value)` | 创建成功响应 | 只返回数据 |
| `of(T value, String message)` | 创建带消息的响应 | 返回数据和自定义消息 |
| `of(T value, int code)` | 创建带状态码的响应 | 返回数据和状态码 |
| `of(T value, int code, String message)` | 创建完整响应 | 返回数据、状态码和消息 |
| `of(T value, String code, String message)` | 创建字符串状态码响应 | 使用字符串状态码 |

**使用示例**:

```java
// 1. 只返回数据
return CommonResult.of(userId);

// 2. 返回数据和自定义消息
return CommonResult.of(userId, "用户创建成功");

// 3. 返回数据和状态码
return CommonResult.of(userId, 200);

// 4. 返回完整信息
return CommonResult.of(userId, 200, "用户创建成功");

// 5. 返回错误
return CommonResult.of(null, 1001, "用户名已存在");
```

### 日志规范

**日志对象定义**:
```java
public static final IEcpLog LOG = EcpLogFactory.getLog(当前类.class);
```

**日志使用**:
```java
// 信息日志
if(LOG.isInfoEnabled()){
    LOG.info("操作说明，参数：{}", param);
}

// 警告日志
if(LOG.isWarnEnabled()){
    LOG.warn("警告信息：{}", message);
}

// 错误日志
if(LOG.isErrorEnabled()){
    LOG.error("错误信息：{}", message, exception);
}
```

---

## Service 层规范

### Application Service（业务服务）

**职责**: 处理复杂业务逻辑、事务管理、业务规则实现

**命名规范**: `[业务功能] + Service` / `[业务功能] + ServiceImpl`

#### 接口定义

```java
package com.ygsoft.xxx.service.application;

/**
 * 用户业务服务接口
 * 
 * @author developer
 * @date 2023-01-01
 */
public interface UserService {
    
    /**
     * 创建用户
     * 
     * @param userVO 用户信息
     * @return 用户ID
     */
    Long createUser(UserVO userVO);
    
    /**
     * 更新用户信息
     * 
     * @param userVO 用户信息
     */
    void updateUser(UserVO userVO);
    
    /**
     * 删除用户
     * 
     * @param userId 用户ID
     */
    void deleteUser(Long userId);
}
```

#### 实现类

```java
package com.ygsoft.xxx.service.application.impl;

import com.ygsoft.ecp.service.log.EcpLogFactory;
import com.ygsoft.ecp.service.log.IEcpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务服务实现
 * 
 * @author developer
 * @date 2023-01-01
 */
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
        
        if(LOG.isInfoEnabled()){
            LOG.info("用户创建成功，用户ID：{}", userPO.getUserId());
        }
        
        return userPO.getUserId();
    }
    
    /**
     * 验证用户信息
     */
    private void validateUserVO(UserVO userVO) {
        if (userVO == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        if (userVO.getUserName() == null || userVO.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        // 其他验证逻辑
    }
    
    /**
     * 处理用户创建业务逻辑
     */
    private void processUserCreation(UserBO userBO) {
        // 业务规则处理
        // 例如：设置默认值、计算字段等
    }
}
```

### Query Service（查询服务）

**职责**: 专注于数据查询，不涉及数据修改

**命名规范**: `[业务功能] + QueryService` / `[业务功能] + QueryServiceImpl`

#### 接口定义

```java
package com.ygsoft.xxx.service.query;

import java.util.List;

/**
 * 用户查询服务接口
 * 
 * @author developer
 * @date 2023-01-01
 */
public interface UserQueryService {
    
    /**
     * 根据ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVO getUserById(Long userId);
    
    /**
     * 查询用户列表
     * 
     * @param condition 查询条件
     * @return 用户列表
     */
    List<UserVO> getUserList(UserQueryCondition condition);
    
    /**
     * 分页查询用户
     * 
     * @param condition 查询条件
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    PageResult<UserVO> getUserPage(UserQueryCondition condition, int pageNo, int pageSize);
}
```

#### 实现类

```java
package com.ygsoft.xxx.service.query.impl;

import com.ygsoft.ecp.service.log.EcpLogFactory;
import com.ygsoft.ecp.service.log.IEcpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户查询服务实现
 * 
 * 注意：查询服务不开启事务
 * 
 * @author developer
 * @date 2023-01-01
 */
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
        
        // 1. 查询数据库
        UserPO userPO = userDao.findById(userId).orElse(null);
        
        if (userPO == null) {
            if(LOG.isWarnEnabled()){
                LOG.warn("用户不存在，用户ID：{}", userId);
            }
            return null;
        }
        
        // 2. PO 转 VO
        UserVO userVO = userTransfer.poToVo(userPO);
        
        return userVO;
    }
    
    @Override
    public List<UserVO> getUserList(UserQueryCondition condition) {
        // 查询逻辑
        List<UserPO> userPOList = userDao.findByCondition(condition);
        
        // 批量转换
        List<UserVO> userVOList = userPOList.stream()
            .map(userTransfer::poToVo)
            .collect(Collectors.toList());
        
        return userVOList;
    }
}
```

### Service 层规范要点

1. **面向接口编程**: 定义接口，实现类放在 impl 包下
2. **事务管理**: Application Service 使用 `@Transactional`，Query Service 不使用
3. **异常处理**: 抛出业务异常，由统一异常处理器处理
4. **日志记录**: 关键操作记录日志
5. **参数验证**: 在 Service 层进行业务参数验证
6. **模型转换**: 使用 Transfer 类进行模型转换

---

## DAO 层规范

### 技术选型

#### JPA 使用场景

```java
/**
 * 用户 DAO 接口
 * 
 * 继承 JpaRepository 获得基本 CRUD 功能
 */
public interface UserDao extends JpaRepository<UserPO, Long> {
    
    /**
     * 根据用户名查询
     * 
     * 方法名查询，无需编写 SQL
     */
    UserPO findByUserName(String userName);
    
    /**
     * 根据年龄范围查询
     */
    List<UserPO> findByAgeBetween(Integer minAge, Integer maxAge);
    
    /**
     * 根据用户名模糊查询
     */
    List<UserPO> findByUserNameLike(String userName);
}
```

#### ORMap 框架使用场景

**适用于**: 复杂查询、动态 SQL、多表关联

**SQL 配置文件位置**: `META-INF/ecp-sql/类路径.类名.xml`

**XML 配置示例**:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<clazz id="com.ygsoft.xxx.dao.UserDao">
    
    <!-- 根据条件查询用户 -->
    <sql id="selectByCondition">
        SELECT 
            USER_ID,
            USER_NAME,
            AGE,
            EMAIL,
            CREATE_TIME
        FROM TBL_USER
        WHERE 1=1
        <if test="userName != null and userName != ''">
            AND USER_NAME LIKE CONCAT('%', #{userName}, '%')
        </if>
        <if test="minAge != null">
            AND AGE >= #{minAge}
        </if>
        <if test="maxAge != null">
            AND AGE <= #{maxAge}
        </if>
        ORDER BY CREATE_TIME DESC
    </sql>
    
    <!-- 统计用户数量 -->
    <sql id="countByCondition">
        SELECT COUNT(1)
        FROM TBL_USER
        WHERE 1=1
        <if test="userName != null and userName != ''">
            AND USER_NAME LIKE CONCAT('%', #{userName}, '%')
        </if>
    </sql>
    
    <!-- 批量插入 -->
    <sql id="batchInsert">
        INSERT INTO TBL_USER (USER_ID, USER_NAME, AGE, EMAIL, CREATE_TIME)
        VALUES
        <foreach collection="list" item="item" separator=",">
            (#{item.userId}, #{item.userName}, #{item.age}, #{item.email}, #{item.createTime})
        </foreach>
    </sql>
    
</clazz>
```

**Java 代码使用**:

```java
import com.ygsoft.ecp.service.dataaccess.ISQLTemplate;
import com.ygsoft.ecp.service.dataaccess.DBUtil;
import com.ygsoft.ecp.service.dataaccess.SqlResource;

@Repository
public class UserDaoImpl implements UserDao {
    
    private static final String SQL_CLASS = "com.ygsoft.xxx.dao.UserDao";
    
    /**
     * 根据条件查询用户
     */
    public List<UserPO> findByCondition(UserQueryCondition condition) {
        // 1. 获取 SQL 模板
        ISQLTemplate sqlTemplate = DBUtil.getSqlTemplate();
        
        // 2. 获取 SQL 语句
        String sql = SqlResource.getMyBatisSql(
            SQL_CLASS, 
            "selectByCondition",
            "userName", condition.getUserName(),
            "minAge", condition.getMinAge(),
            "maxAge", condition.getMaxAge()
        );
        
        // 3. 执行查询
        List<UserPO> result = sqlTemplate.findBySQL(sql, UserPO.class);
        
        return result;
    }
    
    /**
     * 分页查询
     */
    public PageResult<UserPO> findByPage(UserQueryCondition condition, int pageNo, int pageSize) {
        ISQLTemplate sqlTemplate = DBUtil.getSqlTemplate();
        
        // 构建参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("userName", condition.getUserName());
        params.put("minAge", condition.getMinAge());
        params.put("maxAge", condition.getMaxAge());
        
        // 获取 SQL
        String sql = SqlResource.getMyBatisSql(SQL_CLASS, "selectByCondition", params);
        
        // 分页查询（内部自动 count 和分页）
        List<UserPO> result = sqlTemplate.findBySQL(sql, pageNo, pageSize, UserPO.class);
        
        return new PageResult<>(result, pageNo, pageSize);
    }
}
```

### ISQLTemplate 接口说明

**常用方法**:

```java
// 查询列表（限制2000行）
<T> List<T> findBySQL(String sql, Class<T> entity)

// 分页查询
<T> List<T> findBySQL(String sql, int pageNo, int pageSize, Class<T> entity)

// 带参数查询
<T> List<T> findBySQL(String sql, Map<String, ?> params, int pageNo, int pageSize, Class<T> entity)

// 查询列表（无行数限制）
<T> List<T> findBySQLWithoutRowNumLimit(String sql, Class<T> entity)

// 执行更新
int executeSQL(String sql)
int executeSQL(String sql, Map<String, ?> params)

// 批量执行
int executeBatchSQL(String sql, List<Object[]> params)

// 执行存储过程
boolean executeProcedure(String procedureName, Object... paramList)
```

---

## 代码复杂度规范

### 文件级别

```
✓ 单个 Java 类文件最大长度: 1000 行（含注释）
✓ 单行最大长度: 120 个字符
```

### 方法级别

```
✓ 单个方法最大长度: 50 行
✓ 方法参数个数: 不超过 7 个
  - 超过 7 个参数时，封装为对象
✓ 匿名类代码行数: 不超过 20 行
```

### SQL 规范

```
✗ 不允许在 Java 代码中包含 SQL 语句
✓ SQL 语句必须放在配置文件中
```

---

## 安全规范

### 必须避免的安全漏洞

```
✗ SQL 注入
✗ XPath 注入
✗ XML 注入
✗ 跨站脚本攻击（XSS）
✗ 命令注入
✗ 资源释放问题
✗ DNS 欺骗
```

### 敏感信息保护

```
✗ 源码中不能包含明文密码
  - 禁止关键字: pwd, password, pswd, secret, mima, 密码
  
✗ 源码中不能包含客户信息
  - 禁止关键字: nw, gw, nfdw, gjdw, gddw 等
```

### 安全编码实践

```java
// ✗ 错误：使用不安全的随机函数
Random random = new Random();

// ✓ 正确：使用安全的随机函数
SecureRandom secureRandom = new SecureRandom();
```

---

## 性能优化规范

### 数据库访问优化

```
✗ 避免在循环中访问数据库
✓ 批量操作代替循环操作
✓ 能提前预处理的提前预处理
```

**错误示例**:
```java
// ✗ 错误：循环中访问数据库
for (Long userId : userIds) {
    UserPO user = userDao.findById(userId).orElse(null);
    // 处理逻辑
}
```

**正确示例**:
```java
// ✓ 正确：批量查询
List<UserPO> users = userDao.findAllById(userIds);
Map<Long, UserPO> userMap = users.stream()
    .collect(Collectors.toMap(UserPO::getUserId, Function.identity()));

for (Long userId : userIds) {
    UserPO user = userMap.get(userId);
    // 处理逻辑
}
```

---

## 服务调用规范

### 使用 MappService

**禁止**: 直接使用原生 RestTemplate

**必须**: 使用自定义工具类 `com.ygsoft.jt.teng.fw.core.mapp.MappService`

### 同步调用

```java
@Autowired
private MappService mappService;

// GET 请求
public UserVO getUser(Long userId) {
    String url = "http://user-service/api/user/{userId}";
    UserVO user = mappService.doGet(url, UserVO.class, userId);
    return user;
}

// POST 请求
public Long createUser(UserVO userVO) {
    String url = "http://user-service/api/user/create";
    Long userId = mappService.doPost(url, userVO, Long.class);
    return userId;
}

// 返回列表
public List<UserVO> getUserList() {
    String url = "http://user-service/api/user/list";
    List<UserVO> users = mappService.doGetReturnList(url, UserVO.class);
    return users;
}
```

### 异步调用

```java
// 异步 GET 请求
public void getUserAsync(Long userId) {
    String url = "http://user-service/api/user/{userId}";
    
    IAsyncResult<UserVO> asyncResult = mappService.doGetAsync(
        url, 
        UserVO.class,
        new IAsyncCallback() {
            @Override
            public void doCallBack(Object result, Throwable error) {
                if (error != null) {
                    LOG.error("查询用户失败", error);
                } else {
                    UserVO user = (UserVO) result;
                    // 处理结果
                }
            }
        },
        userId
    );
    
    // 可以选择等待结果
    // UserVO user = asyncResult.getResult();
}
```

---

## 最佳实践

### 1. 依赖注入

```java
// ✓ 推荐：使用 @Autowired 注入
@Autowired
private UserService userService;

// ✓ 更推荐：构造器注入
private final UserService userService;

@Autowired
public UserController(UserService userService) {
    this.userService = userService;
}
```

### 2. 异常处理

```java
// 定义业务异常
public class BusinessException extends RuntimeException {
    private String code;
    private String message;
    
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

// 使用业务异常
if (user == null) {
    throw new BusinessException("1001", "用户不存在");
}
```

### 3. 参数验证

```java
// Service 层验证
private void validateUser(UserVO userVO) {
    if (userVO == null) {
        throw new IllegalArgumentException("用户信息不能为空");
    }
    if (StringUtils.isBlank(userVO.getUserName())) {
        throw new IllegalArgumentException("用户名不能为空");
    }
    if (userVO.getAge() != null && userVO.getAge() < 0) {
        throw new IllegalArgumentException("年龄不能为负数");
    }
}
```

### 4. 日志规范

```java
// ✓ 正确：使用占位符
LOG.info("用户创建成功，用户ID：{}", userId);

// ✗ 错误：使用字符串拼接
LOG.info("用户创建成功，用户ID：" + userId);

// ✓ 正确：检查日志级别
if(LOG.isDebugEnabled()){
    LOG.debug("详细信息：{}", detailInfo);
}
```

## 下一步

- 🗄️ 学习 [SQL 规范](./05-sql-standards.md) 了解数据库设计规范
- 💾 查看 [数据交互](./06-data-interaction.md) 了解数据库操作方法
- 🔧 参考 [专项功能](./07-special-features.md) 了解缓存、消息等功能
