# 1. 概述
本文档旨在为后端开发提供详细的开发规范和指导。


# 2. 技术栈
| 技术 | 说明 |
|------|------|
| Spring boot | 数据驱动的前端框架 |
| jdk | 使用1.8 |
| 数据库 | 兼容MYSQL、Oracle数据库 |
| 依赖管理   | Maven项目 |



# 3. 项目架构规范

## 3.1 分层架构
```
Controller层 → Service层 → Dao层 → domain层
```

## 3.2 项目结构及各层作用

```
项目根目录
├── src/                                # 源代码目录
│   ├── main/                           # 主要代码目录
│   │   ├── java/                       # Java源代码目录
│   │   │   └── com.ygsoft.{产品域}.{模块简称}/  # 项目基础包路径
│   │   │       ├── boot/                # 项目入口类，包含应用启动类和配置初始化
│   │   │       ├── controller/          # 控制器层：处理HTTP请求，参数校验，调用服务层，返回响应
│   │   │       ├── domain/               # 数据模型：定义各类数据结构
│   │   │       │   ├── vo/             # 视图对象(View Object)：用于控制器层与前端交互的数据模型
│   │   │       │   ├── bo/             # 业务对象(Business Object)：服务层内部使用的业务数据模型
│   │   │       │   ├── po/             # 持久化对象(Persistent Object)：与数据库表结构对应的实体类
│   │   │       │   └── transfer/       # 模型转换：负责不同模型之间的转换逻辑(VO/BO/PO)
│   │   │       ├── service/             # 服务层：实现核心业务逻辑
│   │   │       │   ├── application/    # 业务服务：处理复杂业务逻辑，事务管理，业务规则实现
│   │   │       │   └── query/          # 查询服务：专注于数据查询相关的业务逻辑
│   │   │       ├── infrastructure/      # 基础设施：提供技术支持和通用功能
│   │   │       │   ├── util/           # 工具类：提供通用工具方法
│   │   │       │   ├── config/         # 配置类：应用配置、Bean配置等
│   │   │       │   └── constant/       # 常量类：定义系统常量和枚举值
│   │   │       ├── dao/                # 数据访问：处理与数据库的交互
│   │   │       ├── schedule/            # 定时任务：处理系统定时执行的任务
│   │   │       └── consumer/            # 消息消费者：处理消息队列中的消息
│   │   └── resources/                   # 配置文件目录：存放应用配置和资源文件
│   │       ├── application.yml          # 应用主配置文件：核心配置信息
│   │       └── META-INF/                # 元信息配置目录：存放元数据和特定配置
│   │           ├── config/              # 配置文件目录：存放各类配置文件
│   │           │   ├── app/            # 应用信息配置：应用基本信息配置
│   │           │   ├── class/          # 类别配置：类别相关配置
│   │           │   ├── domain/         # 领域配置：领域模型相关配置
│   │           │   ├── enum/           # 枚举配置：枚举值定义配置
│   │           │   ├── featureobj/     # 模型特性配置：模型特性相关配置
│   │           │   ├── home/           # 应用门户配置：门户界面配置
│   │           │   ├── inputermeta/    # 模型输入器配置：输入控件配置
│   │           │   ├── login/          # 门户登录配置：登录相关配置
│   │           │   ├── metamodel/      # 元模型配置：元数据模型配置
│   │           │   ├── process/        # 流程配置：业务流程相关配置
│   │           │   ├── rule/           # 规则特性配置：业务规则配置
│   │           │   └── scene/          # 场景配置：业务场景配置
│   │           ├── ecp-login/          # 登录配置：认证授权相关配置
│   │           ├── ecp-sql/            # SQL配置：SQL语句和数据库相关配置
│   │           ├── ecp-security/       # 安全配置：安全控制相关配置
│   │           ├── ecp-cache/          # 缓存配置：缓存策略和实现配置
│   │           ├── ecp-i18n/           # 国际化文件：多语言支持配置
│   │           ├── ecp-mapp/           # 映射配置：对象映射相关配置
│   │           ├── ecp-scheduler/      # 调度配置：任务调度相关配置
│   │           ├── ecp-spring/         # Spring配置：Spring框架相关配置
│   │           ├── ecp-audit/          # 审计配置：操作审计相关配置
│   │           └── jt-tflow-processmodel/ # 流程目录：工作流相关配置
├── pom.xml                              # Maven项目配置：依赖管理和构建配置
```

### 3.2.1 基于业界标准的架构层次扩展内容


1. **boot**
   - boot类需继承SpringBootServletInitializer

2. **Service层的细分**
   - 分为**application(业务服务)**和**query(查询服务)**两类
   - application服务处理复杂业务逻辑、事务管理、业务规则实现
   - query服务专注于数据查询相关的业务逻辑

3. **domain层中的transfer组件**
   - 负责不同模型间的转换(VO/BO/PO)

4. **Infrastructure层**
   - 提供与业务无关的技术支持和通用功能
   - 包含**util(工具类)**、**config(配置类)**、**constant(常量类)**等组件
   - 作为独立架构层而非散布在各个模块中
   - 为整个应用提供技术支持

5. **Schedule层**
   - 处理定时任务和调度逻辑

6. **Consumer层**
   - 处理消息队列中的消息

7. **pom.xml**
   - 只能添加新增的jar依赖，其他的不允许修改

## 3.3 Controller规范

### 3.3.1 Controller设计规范
```java
import com.ygsoft.ecp.service.log.EcpLogFactory;
import com.ygsoft.ecp.service.log.IEcpLog;
import com.ygsoft.necp.core.service.dcispec.EcpPostMapping;

/**
 * Controller设计规范示例
 * 
 * 1. 命名规范：[业务功能]+Controller
 * 2. 职责：参数接收、转换、调用业务组件（不包含业务逻辑）
 * 3.securitydomain有inner、member、outer、open 四种类型：
 * inner：适用于系统内部微服务之间调用的接口，局域网 IP 限制。
 * member：适用于前端应用调用的接口。
 * outer：适用于不同系统之间调用的服务端接口，应用鉴权。
 * open：适用于对外开放的服务端接口，应用鉴权。
 * 4、读写分离标识：管理类使用service、查询类使用query
 * 5、modelname：动态服务标识，通过指定具体访问模型动态服务
 * 
 * @author developer
 * @date 2023-01-01
 */
@RestController
@Api(tags = "接口类描述", description = "接口类描述")
@RequestMapping({"{securitydomain}/{vipaddress}/{读写分离标识}/model/{modelname}/"})
public class ExampleController {
    
    /**
     * 依赖注入业务组件（面向接口编程）
     */
    @Autowired
    private AccBookQueryService accBookQueryService;

    /**
     * 日志对象
     */
    public static final IEcpLog LOG = EcpLogFactory.getLog(ExampleController.class);

    /**
     * API方法设计规范
     * 1. 注解：@ApiOperation + @ApiImplicitParams + @EcpPostMapping/@EcpGetMapping
     * 4. 返回值：CommonResult<T>
     * 
     * @param paramMap 请求参数
     * @return 处理结果
     */
    @ApiOperation(value = "", notes = "", 
            extensions = @Extension(name = "relation", properties = {
            @ExtensionProperty(name = "fullPath", value = ""), 
            @ExtensionProperty(name = "advice", value = "1") }))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accBookVO", 
            value = "{\"paramKey1\": \"参数说明1\", \"paramKey2\": \"参数说明2\"}", 
            paramType = "body", dataType = "Object", required = true) })
    @EcpPostMapping(path = "/aipName")
    public CommonResult<String> aipName(@RequestBody(required = true) final AccBookVO accBookVO) {
        // 1. 调用业务组件（不包含业务逻辑）
        final AccBookBO accBookBO = accBookQueryService.getAccBookById(accBookVO.getAccBook());
        if(LOG.isInfoEnabled()){
            LOG.info("操作结束。");
        }
        // 2. 返回结果包装
        return  CommonResult.of(accBookBO);
    }
}
 ```
 **规范要点**:
1、必须使用swagger进行接口注释
2、统一返回CommonResult
3、必须使用@EcpPostMapping/@EcpGetMapping
4、日志使用平台的日志
  
### 3.3.2 统一返回CommonResult

Controller层必须返回统一的响应格式CommonResult，使用框架提供的公共类：`com.ygsoft.jt.teng.fw.core.base.model.CommonResult`。

该类的基本结构如下：

```java
package com.ygsoft.jt.teng.fw.core.base.model;

public class CommonResult<T> implements java.io.Serializable {
    private T data;            // 结果值，泛型设计支持各种数据类型
    private String message = "success";    // 响应消息，默认值为"success"
    private String code = findNormalCode();      // 状态码，默认值通过findNormalCode()方法获取
    
    // 构造方法、静态工厂方法、getter和setter方法省略
}
```

**规范要点**：
1. 请直接使用框架提供的CommonResult类，不要自行创建新的响应类，以保持系统响应格式的一致性。
2. CommonResult类的构造方法是protected的，应使用静态工厂方法of()来创建实例。

### 3.3.3 静态工厂方法

CommonResult类提供了多种静态工厂方法用于创建实例：

| 方法 | 说明 |
|------|------|
| of(T value) | 创建只包含数据的成功响应 |
| of(T value, String message) | 创建包含数据和自定义消息的成功响应 |
| of(T value, int code) | 创建包含数据和状态码的响应，消息会根据状态码自动获取 |
| of(T value, int code, String... keys) | 创建响应，支持国际化消息模板参数 |
| of(T value, int code, String message) | 创建包含数据、状态码和自定义消息的响应 |
| of(T value, String code, String message) | 创建包含数据、字符串状态码和自定义消息的响应 |
| of(T value, String code, String flag, String message) | 创建包含标志的响应（扩展用途） |

## 3.4 代码复杂度规范
   单个JAVA类文件的最大长度不能超过1000行（含注释部分）。
1. JAVA类文件中单行长度不能超过120个字符。
2. JAVA类文件中每个方法的最大长度不超过50行。
3. JAVA类文件中方法或者构造器的参数个数不能超过7个，如果超过，则将多个参数封装成一个对象。
4. JAVA类文件中匿名类的代码行数最大不能超过20行。
5. JAVA类文件中不允许包含SQL语句，SQL语句应放置在统一配置文件中处理。

# 4. 组件规范

## 4.1 数据访问
公司混合使用多种二次封装的数据访问框架，包括类似mybatis的ORMap框架、JPA.
不需要定义@Query注解或@native注解的增删改及简单查询语句时使用jpa，其他的情况使用类似mybatisORMap框架。
SQL语句应放在相应的xml配置文件中

#### 二次封装的mybatisORMap框架的使用方法
 - 先在ecp-sql目录下新建xml文件，命名方式:类路径.类名.xml，如果已存在相应的xml文件，无需新增，把语句写在这个文件即可
 - 再在该xml文件内定义如下格式的sql脚本
 ```xml
 <clazz id="当前类路径">
    <sql id="sqlid?">
	   ...mybatis script...(其中dbtype为内置变量以兼容mysql及oracle等主流数据库类型， 可引用其它sql节点)	   
	</sql>
 </class>
 ```
 - java代码中获取sql语句
 ```java
   final String SQL_CLASS = 当前类的类路径
   final String sql... = SqlResource.getMyBatisSql(SQL_CLASS，sqlid?,  参数名1，参数值1，参数名2，参数值2, ...)
 ```   
 - 执行sql的工具类
  com.ygsoft.ecp.service.dataaccess.ISQLTemplate sqlTemplate = DBUtil.getSqlTemplate();
  
#### JPA的使用方法
 - 基本与jpa的用法与业界保持一致
 - 具有自定义实现的Dao的父类为com.ygsoft.ecp.framework.dao.CommonRepositoryDAO<T extends Serializable, ID extends Serializable>, 存放路径在dao接口所在package下的.impl.dao
 
#### ISqlTemplate接口
 以下以WithoutRowNumLimit结尾的接口返回结果记录数没有限制，其它接口内部已限制只返回2000行，以下接口中的entity参数为映射实体类PO或集合类
 - public <T> List<T> findBySQL(String sql, Class<T> entity)
 - public <T> List<T> findBySQL(String sql, int pageNo, int pageSize, Class<T> entity)
 - public <T> List<T> findBySQL(String sql, String paramName, Object value, int pageNo, int pageSize, Class<T> entity)
 - public <T> List<T> findBySQL(String sql, Map<String, ? extends Object> params, int pageNo, int pageSize, Class<T> entity) 
 - public <T> List<T> findBySQLWithoutRowNumLimit(String sql, Class<T> entity)
 - public <T> List<T> findBySQLWithoutRowNumLimit(String sql, String paramName, Object value, Class<T> entity)
 - public <T> List<T> findBySQLWithoutRowNumLimit(String sql, Map<String, ? extends Object> params, Class<T> entity)
 - public int executeSQL(String sql)
 - public int executeSQL(String sql, String paramName, Object value)
 - public int executeSQL(String sql, Map<String, ? extends Object> params) 
 - public int executeBatchSQL(String sql, List<Object[]> params) 
 - public int executeBatchSql(final String[] sqls)
 - public boolean executeProcedure(String procedureName, Object... paramList)
 - public Map<?, ?> executeProcedure(Map<String, Object> mapIn, Map<String, Object> mapOut, String procedureName)
 - 需要分页时须使用以上带pageNo和pageSize参数的方法，这些方法内部已内置count并分页取数的机制


## 4.2 服务调用
本文档为后端开发提服务供详细指导。需使用自定义工具类com.ygsoft.jt.teng.fw.core.mapp.MappService进行服务调用，不允许直接使用原生的restTemplate。

#### MappService接口定义
 - public <T> T doGet(String url, Class<T> responseType, Object... uriVariables)
 - public <T> List<T> doGetReturnList(String url, Class<T> responseType, Object... uriVariables)
 - public <T> IAsyncResult<T> doGetAsync(String url, Class<T> responseType, IAsyncCallback callBack, Object... uriVariables)
 - public <T> IAsyncResult<List<T>> doGetAsyncReturnList(String url, Class<T> responseType, IAsyncCallback callBack, Object... uriVariables)
 - public <T> T doPost(String url, Object request, Class<T> responseType, Object... uriVariables)
 - public <T> List<T> doPostReturnList(String url, Object request, Class<T> responseType, Object... uriVariables)
 - public <T> IAsyncResult<T> doPostAsync(String url, Object request, Class<T> responseType, IAsyncCallback callBack, Object... uriVariables)
 - public <T> IAsyncResult<List<T>> doPostAsyncReturnList(String url, Object request, Class<T> responseType, IAsyncCallback callBack, Object... uriVariables)

#### com.ygsoft.ecp.service.async.IAsyncResult的接口定义
	 public interface IAsyncResult<T> {
		T getResult() throws com.ygsoft.jt.teng.fw.core.base.exception.JtException;

		T getResult(long var1) throws JtException;

		boolean isCompleted();
	}
	
#### com.ygsoft.jt.teng.fw.core.service.async.IAsyncCallback的接口定义	
	public interface IAsyncCallback {
		void doCallBack(Object var1, Throwable var2);
	}

# 5. 安全规范
- 需避免以下安全漏洞：SQL注入、XPath注入、XML注入、跨站脚本攻击、命令注入、资源释放、DNS欺骗
- 源码中不能包含pwd,password,pswd,secret,mima,密码等关键字的明文value值
- 源码中不能包含客户信息相关的代码，如nw、gw、nfdw、gjdw、gddw等
- 使用安全的随机函数 SecureRandom 代替不安全的随机函数 Random

# 6. 其他规范
- 强调：避免在循环中访问数据库
- 循环时能提前预处理的提前预处理，减少循环提升性能