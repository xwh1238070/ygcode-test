# Java 后端编码智能体 - 最佳实践

## 概述

本文档总结了 Java 后端开发的最佳实践，包括代码组织、性能优化、安全编码等方面的建议。

## 代码组织最佳实践

### 1. 分层清晰

```java
// ✓ 正确：职责清晰的分层
@RestController
public class UserController {
    @Autowired
    private UserService userService;  // 只依赖 Service 层
    
    public CommonResult<UserVO> getUser(Long userId) {
        return CommonResult.of(userService.getUserById(userId));
    }
}

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;  // 只依赖 DAO 层
    
    public UserVO getUserById(Long userId) {
        UserPO userPO = userDao.findById(userId);
        return userTransfer.poToVo(userPO);
    }
}
```

### 2. 面向接口编程

```java
// ✓ 正确：定义接口
public interface UserService {
    UserVO getUserById(Long userId);
    void createUser(UserVO userVO);
}

// ✓ 正确：实现接口
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserVO getUserById(Long userId) {
        // 实现逻辑
    }
}

// ✓ 正确：依赖接口
@RestController
public class UserController {
    @Autowired
    private UserService userService;  // 依赖接口，不是实现类
}
```

### 3. 单一职责原则

```java
// ✓ 正确：每个类只负责一个功能
public class UserService {
    // 只处理用户相关业务
}

public class OrderService {
    // 只处理订单相关业务
}

public class UserTransfer {
    // 只负责用户模型转换
}
```

### 4. 合理使用设计模式

#### 工厂模式

```java
public class ServiceFactory {
    
    public static UserService createUserService() {
        return new UserServiceImpl();
    }
}
```

#### 策略模式

```java
public interface PaymentStrategy {
    void pay(BigDecimal amount);
}

public class AlipayStrategy implements PaymentStrategy {
    @Override
    public void pay(BigDecimal amount) {
        // 支付宝支付逻辑
    }
}

public class WechatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(BigDecimal amount) {
        // 微信支付逻辑
    }
}
```

---

## 性能优化最佳实践

### 1. 避免循环中访问数据库

```java
// ✗ 错误：循环中查询数据库
public void processUsers(List<Long> userIds) {
    for (Long userId : userIds) {
        UserPO user = userDao.findById(userId);  // N次数据库查询
        // 处理逻辑
    }
}

// ✓ 正确：批量查询
public void processUsers(List<Long> userIds) {
    // 一次查询获取所有用户
    List<UserPO> users = userDao.findAllById(userIds);
    Map<Long, UserPO> userMap = users.stream()
        .collect(Collectors.toMap(UserPO::getUserId, Function.identity()));
    
    for (Long userId : userIds) {
        UserPO user = userMap.get(userId);
        // 处理逻辑
    }
}
```

### 2. 使用缓存

```java
@Service
public class UserService {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private UserDao userDao;
    
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
}
```

### 3. 分页查询优化

```java
// ✓ 正确：使用分页
public PageResult<UserVO> getUserList(int pageNo, int pageSize) {
    // 限制每页大小
    if (pageSize > 1000) {
        pageSize = 1000;
    }
    
    PageModel<UserPO> page = userDao.findByPage(pageNo, pageSize);
    
    List<UserVO> userVOList = page.getItems().stream()
        .map(userTransfer::poToVo)
        .collect(Collectors.toList());
    
    return new PageResult<>(userVOList, page.getTotalCount());
}
```

### 4. 异步处理

```java
@Service
public class OrderService {
    
    @Autowired
    private MappService mappService;
    
    /**
     * 异步发送通知
     */
    public void createOrder(OrderVO orderVO) {
        // 1. 创建订单（同步）
        Long orderId = saveOrder(orderVO);
        
        // 2. 发送通知（异步）
        sendNotificationAsync(orderId);
    }
    
    private void sendNotificationAsync(Long orderId) {
        String url = "http://notification-service/api/notify";
        
        mappService.doPostAsync(
            url,
            orderId,
            String.class,
            new IAsyncCallback() {
                @Override
                public void doCallBack(Object result, Throwable error) {
                    if (error != null) {
                        LOG.error("发送通知失败", error);
                    }
                }
            }
        );
    }
}
```

---

## 安全编码最佳实践

### 1. 参数验证

```java
@Service
public class UserService {
    
    public void createUser(UserVO userVO) {
        // 参数验证
        validateUserVO(userVO);
        
        // 业务逻辑
        // ...
    }
    
    private void validateUserVO(UserVO userVO) {
        if (userVO == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        
        if (StringUtils.isBlank(userVO.getUserName())) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        
        if (userVO.getUserName().length() > 50) {
            throw new IllegalArgumentException("用户名长度不能超过50");
        }
        
        if (userVO.getAge() != null && (userVO.getAge() < 0 || userVO.getAge() > 150)) {
            throw new IllegalArgumentException("年龄必须在0-150之间");
        }
        
        if (StringUtils.isNotBlank(userVO.getEmail())) {
            if (!isValidEmail(userVO.getEmail())) {
                throw new IllegalArgumentException("邮箱格式不正确");
            }
        }
    }
    
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
}
```

### 2. SQL 注入防护

```java
// ✓ 正确：使用绑定变量
String sql = SqlResource.getMyBatisSql(
    SQL_CLASS,
    "selectByUserName",
    "userName", userName  // 使用绑定变量
);

// ✗ 错误：字符串拼接
String sql = "SELECT * FROM TBL_USER WHERE C_USER_NAME='" + userName + "'";
```

### 3. 敏感信息保护

```java
// ✓ 正确：不在代码中存储敏感信息
@Configuration
public class DataSourceConfig {
    
    @Value("${db.password}")  // 从配置文件读取
    private String password;
    
    @Bean
    public DataSource dataSource() {
        // 使用配置的密码
    }
}

// ✗ 错误：硬编码密码
public class DataSourceConfig {
    private static final String PASSWORD = "123456";  // 禁止
}
```

### 4. 使用安全的随机数

```java
// ✓ 正确：使用 SecureRandom
SecureRandom secureRandom = new SecureRandom();
byte[] token = new byte[32];
secureRandom.nextBytes(token);

// ✗ 错误：使用 Random
Random random = new Random();
int value = random.nextInt();
```

---

## 异常处理最佳实践

### 1. 自定义业务异常

```java
/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    
    private String code;
    private String message;
    
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    // getter/setter
}
```

### 2. 统一异常处理

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final IEcpLog LOG = EcpLogFactory.getLog(GlobalExceptionHandler.class);
    
    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public CommonResult<Void> handleBusinessException(BusinessException e) {
        LOG.warn("业务异常：{}", e.getMessage());
        return CommonResult.of(null, e.getCode(), e.getMessage());
    }
    
    /**
     * 处理参数验证异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        LOG.warn("参数验证失败：{}", e.getMessage());
        return CommonResult.of(null, "1002", e.getMessage());
    }
    
    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handleException(Exception e) {
        LOG.error("系统异常", e);
        return CommonResult.of(null, "9999", "系统异常，请联系管理员");
    }
}
```

### 3. 异常使用规范

```java
// ✓ 正确：抛出具体的异常
public UserVO getUserById(Long userId) {
    UserPO user = userDao.findById(userId);
    
    if (user == null) {
        throw new BusinessException("1001", "用户不存在");
    }
    
    return userTransfer.poToVo(user);
}

// ✓ 正确：记录异常日志
try {
    // 业务逻辑
} catch (Exception e) {
    LOG.error("处理失败", e);
    throw new BusinessException("1003", "处理失败");
}

// ✗ 错误：吞掉异常
try {
    // 业务逻辑
} catch (Exception e) {
    // 什么都不做
}
```

---

## 事务管理最佳实践

### 1. 事务注解使用

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

// ✓ 正确：只读事务
@Service
public class UserQueryServiceImpl implements UserQueryService {
    
    @Transactional(readOnly = true)
    public UserVO getUserById(Long userId) {
        // 只读操作
    }
}
```

### 2. 事务传播级别

```java
@Service
public class OrderService {
    
    @Autowired
    private LogService logService;
    
    /**
     * 创建订单（需要事务）
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderVO orderVO) {
        // 保存订单
        orderDao.save(orderPO);
        
        // 记录日志（独立事务）
        logService.saveLog("创建订单", orderVO.getOrderId());
    }
}

@Service
public class LogService {
    
    /**
     * 记录日志（独立事务，不受外部事务影响）
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(String action, Long orderId) {
        LogPO log = new LogPO();
        log.setAction(action);
        log.setOrderId(orderId);
        logDao.save(log);
    }
}
```

---

## 日志记录最佳实践

### 1. 日志级别使用

```java
public class UserService {
    
    private static final IEcpLog LOG = EcpLogFactory.getLog(UserService.class);
    
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
            // 业务逻辑
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
}
```

### 2. 日志格式规范

```java
// ✓ 正确：使用占位符
LOG.info("用户登录，用户ID：{}，IP：{}", userId, ip);

// ✗ 错误：字符串拼接
LOG.info("用户登录，用户ID：" + userId + "，IP：" + ip);

// ✓ 正确：检查日志级别
if (LOG.isDebugEnabled()) {
    LOG.debug("详细信息：{}", expensiveOperation());
}

// ✗ 错误：不检查级别
LOG.debug("详细信息：{}", expensiveOperation());  // 即使不输出也会执行
```

---

## 代码质量最佳实践

### 1. 代码注释

```java
/**
 * 用户服务实现类
 * 
 * 提供用户管理相关的业务功能，包括：
 * - 用户创建
 * - 用户更新
 * - 用户查询
 * - 用户删除
 * 
 * @author developer
 * @date 2023-01-01
 */
@Service
public class UserServiceImpl implements UserService {
    
    /**
     * 创建用户
     * 
     * 业务规则：
     * 1. 用户名不能重复
     * 2. 年龄必须在0-150之间
     * 3. 邮箱格式必须正确
     * 
     * @param userVO 用户信息
     * @return 用户ID
     * @throws BusinessException 当用户名重复时抛出
     */
    @Override
    public Long createUser(UserVO userVO) {
        // 实现逻辑
    }
}
```

### 2. 命名规范

```java
// ✓ 正确：清晰的命名
public class UserService {
    public UserVO getUserById(Long userId) { }
    public List<UserVO> getUserList(UserQueryCondition condition) { }
    public void createUser(UserVO userVO) { }
    public void updateUser(UserVO userVO) { }
    public void deleteUser(Long userId) { }
}

// ✗ 错误：模糊的命名
public class UserService {
    public UserVO get(Long id) { }
    public List<UserVO> list(Object obj) { }
    public void add(UserVO vo) { }
    public void modify(UserVO vo) { }
    public void remove(Long id) { }
}
```

### 3. 代码复用

```java
// ✓ 正确：提取公共方法
public class UserService {
    
    public void createUser(UserVO userVO) {
        validateUserVO(userVO);  // 复用验证逻辑
        // 创建逻辑
    }
    
    public void updateUser(UserVO userVO) {
        validateUserVO(userVO);  // 复用验证逻辑
        // 更新逻辑
    }
    
    private void validateUserVO(UserVO userVO) {
        // 验证逻辑
    }
}
```

---

## 测试最佳实践

### 1. 单元测试

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    /**
     * 测试创建用户 - 正常情况
     */
    @Test
    public void testCreateUser_Success() {
        // 准备测试数据
        UserVO userVO = new UserVO();
        userVO.setUserName("测试用户");
        userVO.setAge(25);
        userVO.setEmail("test@example.com");
        
        // 执行测试
        Long userId = userService.createUser(userVO);
        
        // 验证结果
        assertNotNull(userId);
        assertTrue(userId > 0);
    }
    
    /**
     * 测试创建用户 - 用户名为空
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUser_NullUserName() {
        UserVO userVO = new UserVO();
        userVO.setUserName(null);
        
        userService.createUser(userVO);
    }
    
    /**
     * 测试创建用户 - 年龄超出范围
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUser_InvalidAge() {
        UserVO userVO = new UserVO();
        userVO.setUserName("测试用户");
        userVO.setAge(200);  // 超出范围
        
        userService.createUser(userVO);
    }
}
```

---

## 配置管理最佳实践

### 1. 使用配置文件

```yaml
# application.yml
spring:
  datasource:
    url: ${DB_URL:jdbc:mysql://localhost:3306/test}
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:password}
  
  redis:
    host: ${REDIS_HOST:localhost}
    port: ${REDIS_PORT:6379}

app:
  name: user-service
  version: 1.0.0
```

### 2. 配置类

```java
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    
    private String name;
    private String version;
    
    // getter/setter
}

@Service
public class UserService {
    
    @Autowired
    private AppConfig appConfig;
    
    public void doSomething() {
        String appName = appConfig.getName();
        // 使用配置
    }
}
```

---

## 总结

### 核心原则

1. **代码清晰**: 命名清晰、结构清晰、逻辑清晰
2. **职责单一**: 每个类、每个方法只做一件事
3. **面向接口**: 依赖接口而不是实现
4. **异常处理**: 合理使用异常，统一异常处理
5. **性能优化**: 避免循环查询、使用缓存、异步处理
6. **安全编码**: 参数验证、防止注入、保护敏感信息
7. **日志记录**: 合理使用日志级别，记录关键信息
8. **测试覆盖**: 编写单元测试，覆盖各种场景

### 开发检查清单

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

## 参考文档

- 📖 [概览文档](./01-overview.md) - 了解智能体功能
- 🏗️ [架构文档](./02-architecture.md) - 了解项目结构
- 🔄 [执行流程](./03-workflow.md) - 了解开发流程
- 📝 [编码规范](./04-coding-standards.md) - 了解代码规范
- 🗄️ [SQL 规范](./05-sql-standards.md) - 了解数据库规范
- 💾 [数据交互](./06-data-interaction.md) - 了解数据库操作
