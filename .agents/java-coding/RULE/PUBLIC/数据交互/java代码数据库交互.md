# Java数据库交互指南

## 1. 概述
本文档介绍如何在Java代码中与数据库进行交互，包括元数据初始化和基本的CRUD（创建、读取、更新、删除）操作。

## 2. 元数据初始化
在进行数据库操作前，需要先初始化实体类的元数据，建立Java对象与数据库表之间的映射关系。

### 2.1 元数据初始化步骤
1. 创建`EntityMetaData`对象
2. 设置实体类名称
3. 创建属性列表
4. 设置表名
5. 为每个字段创建并配置`PropertyMetaData`对象
6. 加载元数据到`JtMetaDataHolder`

### 2.2 元数据初始化示例代码
```
/**
 * 初始化元数据
 */
private void initializeMetaData() {
    final EntityMetaData metaData = new EntityMetaData();
    metaData.setEntityName(Person.class.getName());

    final List<PropertyMetaData> props = new ArrayList<>();
    metaData.setColList(props);
    metaData.setTableName("TBL_PERSON_NEW");

    // status字段
    PropertyMetaData prop0 = new PropertyMetaData();
    prop0.setName("status");
    prop0.setMapName("STATUS");
    prop0.setLength(50);
    prop0.setDataType(PropertyDataTypeEnum.STRING);
    props.add(prop0);

    // userId字段（主键）
    PropertyMetaData prop1 = new PropertyMetaData();
    prop1.setName("userId");
    prop1.setMapName("USER_ID");
    prop1.setDataType(PropertyDataTypeEnum.INTEGER);
    prop1.setPropEnum(PropertyTypeEnum.ID);
    prop1.setLength(8);
    props.add(prop1);

    // userName字段
    PropertyMetaData prop2 = new PropertyMetaData();
    prop2.setName("userName");
    prop2.setMapName("USER_NAME");
    prop2.setLength(50);
    prop2.setDataType(PropertyDataTypeEnum.STRING);
    props.add(prop2);

    JtMetaDataHolder.instance.loadMetaData(Person.class.getName(), metaData);
}
```

## 3. 数据库CRUD操作

### 3.1 新增和修改操作
使用`IJtDataPersistService`的`save`方法可以实现对象的新增或更新。系统会根据主键自动判断是执行插入还是更新操作。

```
private IJtDataPersistService persistService;
final int id = 123231;
final Person user = new Person();
user.setUserId(id);
user.setUserName("test11");
user.setCondition("abc11");
persistService.save(user);
```

### 3.2 删除操作
使用`IJtDataPersistService`的`delete`方法可以根据主键删除记录。

```
//IJtDataPersistService 中的接口信息
public void delete(Serializable primaryKey) {
   this.delete(this.getEntityName(), primaryKey);
}
```

### 3.3 查询操作
使用`IJtDataQueryService`的`findByPageWithCondition`方法可以根据条件进行分页查询。

```
private IJtDataQueryService queryService;
List<ItemCondition> conditions = new ArrayList<>();
ItemCondition condition = new ItemCondition("name", ItemConditionOp.EQ, "test");
conditions.add(condition);

PageModel<?> page = queryService.findByPageWithCondition(conditions, 1, 1000);
```

## 4. 注意事项
- 在使用数据库操作前，必须先初始化元数据
- 查询条件使用`ItemCondition`类构建，支持多种操作符（如EQ、GT、LT等）
- 分页查询时，页码从1开始计数，示例中的"1"表示第一页
- 分页大小表示每页返回的记录数，示例中的"1000"表示每页最多返回1000条记录
- `save`方法既可以用于新增也可以用于更新，系统会根据主键自动判断
