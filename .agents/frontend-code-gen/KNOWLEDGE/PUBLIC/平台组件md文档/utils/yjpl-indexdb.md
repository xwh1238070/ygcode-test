# indexedDB 缓存工具类

提供indexedDB缓存API，实现对indexedDB本地数据库表记录的增加、删除、修改等操作。
indexedDB相比较于storage，存储容量更大（理论上没有限制），同时具有异步非阻塞读写的特点。
关闭浏览器，存储在indexedDB中的数据不会丢失，除非手工清楚。
数据存储层次： 数据库(DB)-表(STORE)-记录(RECORD),这点与关系式数据库类似，API操作的默认数据库名为ecp_default。
但要注意，每个表必需设置唯一性主键，每条记录的数据以对象的形式整体存储在表中。
本API对被存储数据data进行了统一对象封装如下：
```ts
   {
    gid,       // 唯一性主键ID
    data,      // 数据
    data_size, // 数据字节数
    hit,       // 访问次数
    lasttime   // 最后存取时间
    }
```
特别注意：
（1）使用该API前必须先检查浏览器是否支持(window.indexedDB != null)。
（2）目前，ecp_default数据库中已经存在两张表，用于两种专门数据的本地缓存：
    qzzcolumnsettings：用于保存qzz表格的列设置
    genentitylastselect：用于保存通用实体最近选择过的选项
    就像不是谁都可以在数据库中执行脚本一样，indexedDB的数据库及表的维护与升级只能由平台完成。
    <b>也就是说，</b>如果需要新的存储表，请向平台部申请。实际上，在平台的代码级别需要做：
    增加indexedDB的版本号（目前为2），并增加申请的表名。并且，UI2.0，3.0，4.0需要同步升级。

### 基础用法

在使用indexedDB缓存工具时需要导入yjpl-indexdb组件。举例如下：

```ts
... ...
<script>
import IndexDB from 'yjpl-indexdb';
export default class YjplIndexDbDemo extends YJPage {

    ... ...

    methods() {
        return {
            doSomething() {
               const storeName = '要操作的表名'; 
               const gid = '主键值';
               IndexDB.getById(storeName, gid, (record)=>{
                  // 这里对读到的记录record进行处理，真实数据保存在record.data中
               });
            }
        }
    }
}
</script>
```

### 通用方法 （IndexDB.方法）
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| getById | 获取indexedDB缓存的方法 |<b>storeName（表名）</b>: 必填，字符串类型；<br><b>gid（主键值）</b>：必填，字符串类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性已经解密，注意对象记录可能不存在，即可能为null）|
| setById | 设置indexedDB缓存的方法 |<b>storeName（表名）</b>: 必填，字符串类型；<br><b>gid（主键值）</b>：必填，字符串类型；<br><b>data（数据）</b>: 必填，任意类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性为明文）|
| delById | 删除indexedDB缓存的方法 | <b>storeName（表名）</b>: 必填，字符串类型；<br><b>gid（主键值）</b>：必填，字符串类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性已经解密，注意对象记录可能不存在，即可能为null）|
### qzzColumnSettings 方法 （IndexDB.qzzColumnSettings.方法）
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| getById | 获取qzz表格列设置缓存的方法 |<b>gid（主键值）</b>：必填，字符串类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性已经解密，注意对象记录可能不存在，即可能为null）|
| setById | 设置qzz表格列设置缓存的方法 |<b>gid（主键值）</b>：必填，字符串类型；<br><b>data（数据）</b>: 必填，任意类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性为明文）|
| delById | 删除qzz表格列设置缓存的方法 |<b>gid（主键值）</b>：必填，字符串类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性已经解密，注意对象记录可能不存在，即可能为null）|
### genentitylastselect 方法 （IndexDB.genentitylastselect.方法）
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| getById | 获取通用实体最近选择项缓存的方法 |<b>gid（主键值）</b>：必填，字符串类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性已经解密，注意对象记录可能不存在，即可能为null）|
| setById | 设置通用实体最近选择项缓存的方法 |<b>gid（主键值）</b>：必填，字符串类型；<br><b>data（数据）</b>: 必填，任意类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性为明文）|
| delById | 删除通用实体最近选择项缓存的方法 |<b>gid（主键值）</b>：必填，字符串类型；<br><b>callback（回调函数）</b>：可选，将整个对象记录作为参数传入（其中data属性已经解密，注意对象记录可能不存在，即可能为null）|
