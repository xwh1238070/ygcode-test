## Import 导入组件

### 基础用法

使用 `this.$import` 进行导入。

:::demo

```html
<template>
    <tl-button @click="handleImport">导入</tl-button>
</template>

<script>
  export default {
    name: 'Import1',
    methods: {
      handleImport() {
        this.$import({
          vipAddress: '/jt/mapp/sample',
          colData: [
            {
              sheetName: 'sheet1',
              colName: ['name', 'key', 'what', 'erver', 'something']
            },
            {
              sheetName: 'sheet2',
              colName: ['id', 'pid', 'code', 'text', 'more']
            }
          ],
          beforeUpload(file) {
            if(file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' ||
              file.type === 'application/vnd.ms-excel'
            ){
              return true;
            } else {
              const name = file.name.split('.');
              if(name[name.length - 1] === 'et'){
                return true;
              }
            }
            this.$message({
              message: '当前只支持xls,xlsx和et格式导入',
              type: 'warning'
            });
            return false;
          },
          onSuccess: (resData, file) => {
            console.log(resData)
          }
        });
      }
    }
  };
</script>


```
:::

### 导入方案

使用 `importScheme`为 `true` 进行开启方案选择。通过`schemeData`传递表格表头信息，`name`是字段，`title`是列名称。

:::demo

```html
<template>
    <tl-button @click="handleImport">方案导入</tl-button>

    <tl-import
      vip-address="/jt/mapp/sample"
      :visible.sync="visible"
      :schemeVO="schemeVO"
      :scheme-data="schemeData"
      :import-scheme="true"
      :before-upload="beforeUpload"
      :advanced-data="advancedData"
      :on-mate-key="handelMetaKey"
      :before-scheme-save="handelBeforeSave">
      <template slot="scheme-form">
        <tl-form ref="form" label-suffix="：">
          <tl-form-item label="表单字段">
            <tl-select
              v-model="form.name"
              id-field="id"
              text-field="name"
              :data="metaKeyData"
              placeholder="请选择导入字段">
            </tl-select>
          </tl-form-item>
        </tl-form>
      </template>
    </tl-import>

    <tl-button @click="visible = !visible">打开方案导入弹窗</tl-button>
</template>

<script>
  export default {
    name: 'Import1',
    data: ()=>{
      return {
        visible: false,
        form: {
          name: ''
        },
        schemeVO: {
          classId: '123',
          typeId: '456'
        },
        metaKeyData: [],
        advancedData: [
          {
            name: 'TBBM',
            title: '填报部门',
            input: '对象类型'
          },
          {
            name: 'TBBM',
            title: '管理对象名称',
            input: '对象类型'
          }
        ],
        schemeData: [
          {
            name: 'WLDWQC',
            title: '往来单位全称'
          },
          {
            name: 'JCZZBM',
            title: '基础组织编码'
          },
          {
            name: 'GSDJH',
            title: '工商登记号'
          },
          {
            name: 'SWDJH',
            title: '税务登记号'
          },
          {
            name: 'DX',
            title: '是否抵销'
          },
          {
            name: 'WLDWFL',
            title: '往来单位分类'
          },
          {
            name: 'TXDZ',
            title: '通讯地址'
          },
          {
            name: 'LXDZYB',
            title: '联系地址邮编'
          },
          {
            name: 'GSDH',
            title: '公司电话'
          },
          {
            name: 'CZ',
            title: '联系人传真'
          },
          {
            name: 'email',
            title: '联系人电子邮箱'
          },
          {
            name: 'user',
            title: '联系人'
          },
          {
            name: 'phone',
            title: '联系人电话'
          },
          {
            name: 'DW',
            title: '填报单位'
          },
          {
            name: 'BZ',
            title: '备注'
          }
        ]
      }
    },
    methods: {
      handleImport() {
        this.$import({
          vipAddress: '/jt/mapp/sample',
          colData: [
            {
              sheetName: 'sheet1',
              colName: ['name', 'key', 'what', 'erver', 'something'],
            },
            {
              sheetName: 'sheet2',
              colName: ['id', 'pid', 'code', 'text', 'more'],
            },
          ],
          schemeData: this.schemeData,
          importScheme: true,
          beforeUpload: this.beforeUpload,
          onSuccess: (resData, file, schemeData) => {
            console.log(resData, file, schemeData);
          },
          onSub:(data)=>{
            console.log('选中的数据', data);
          },
          onAdd:(data)=>{
            console.log('新增的数据', data);
          },
          onEdit:(data)=>{
            console.log('编辑的数据', data);
          },
          onDel:(data)=>{
            console.log('删除的数据', data);
            if(data.name === '测试方案1'){
              this.$message('测试方案1数据不可删除');
            }
            return false;
          }
        });
      },
      beforeUpload(file) {
        if(file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' ||
          file.type === 'application/vnd.ms-excel'
        ){
          return true;
        } else {
          const name = file.name.split('.');
          if(name[name.length - 1] === 'et'){
            return true;
          }
        }
        this.$message({
          message: '当前只支持xls,xlsx和et格式导入',
          type: 'warning'
        });
        return false;
      },
      handelMetaKey(data) {
        this.metaKeyData = data;
        this.form.name = '1';
      },
      handelBeforeSave(data) {
        console.log(data);
      }
    }
  };
</script>


```
:::


### 大数据导入

配置 `optimize` 进行导入。

:::demo

```html
<template>
  <div>
    <tl-button @click="handleImport">数据优化导入(同步)</tl-button>
    <tl-button @click="handleImport1">数据优化导入(异步)</tl-button>
  </div>
</template>

<script>
  export default {
    name: 'Import',
    methods: {
      handleImport() {
        this.$import({
          vipAddress: '/jt/mapp/sample',
          optimize: true,
          functionPoints: '异步导入mapp样例-同步',
          headRowNum: 2,
          beanId: 'demoJtImportDataContext',
          importParams: JSON.stringify({ id: '123' }), // 字符串  JSON字符串
          beforeUpload(file) {
            if(file.raw.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' ||
              file.raw.type === 'application/vnd.ms-excel'
            ){
              return true;
            } else {
              const name = file.raw.name.split('.');
              if(name[name.length - 1] === 'et'){
                return true;
              }
            }
            this.$message({
              message: '当前只支持xls,xlsx和et格式导入',
              type: 'warning'
            });
            return false;
          },
          onError: (res, file) => {
            console.log('onError::::::::::', res, file);
          },
          onSuccess: (resData, file) => {
            console.log(resData);
            console.log(file);
          },
        });
      },
      handleImport1() {
        this.$import({
          vipAddress: '/jt/mapp/sample',
          optimize: true,
          async: true,
          functionPoints: '异步导入mapp样例-异步',
          headRowNum: 2,
          beanId: 'demoJtImportDataContext',
          beforeUpload(file) {
            if(file.raw.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' ||
              file.raw.type === 'application/vnd.ms-excel'
            ){
              return true;
            } else {
              const name = file.raw.name.split('.');
              if(name[name.length - 1] === 'et'){
                return true;
              }
            }
            this.$message({
              message: '当前只支持xls,xlsx和et格式导入',
              type: 'warning'
            });
            return false;
          },
          onError: (res, file) => {
            console.log('onError::::::::::', res, file);
          },
          onSuccess: (res, file) => {
            console.log('onSuccess::::::::::', res, file);
            console.log(res);
            console.log(file);
          },
        });
      },
    }
  };
</script>


```
:::

### 异步导入执行回调

配置 `asyncCallback` 进行配置异步导入后是否执行成功失败回调。组件开启异步执行回调后，需要在beforeDestroy事件中手动销毁组件实例

:::demo

```html
<template>
  <div>
    <tl-button @click="handleImport">异步导入</tl-button>
  </div>
</template>

<script>
  export default {
    name: 'Import',
    data() {
      return {
        importRef: null
      }
    },
    methods: {
      handleImport() {
        this.importRef = this.$import({
          vipAddress: '/jt/mapp/sample',
          optimize: true,
          async: true,
          functionPoints: '异步导入mapp样例-异步',
          headRowNum: 2,
          beanId: 'demoJtImportDataContext',
          asyncCallback: true,
          beforeUpload(file) {
            if(file.raw.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' ||
              file.raw.type === 'application/vnd.ms-excel'
            ){
              return true;
            } else {
              const name = file.raw.name.split('.');
              if(name[name.length - 1] === 'et'){
                return true;
              }
            }
            this.$message({
              message: '当前只支持xls,xlsx和et格式导入',
              type: 'warning'
            });
            return false;
          },
          onError: (res, file) => {
            console.log('onError::::::::::', res, file);
            alert('失败回调');
          },
          onSuccess: (res, file) => {
            console.log('onSuccess::::::::::', res, file);
            alert('成功回调');
          },
        });
      },
      handleOpenResult() {
        if(this.importRef) {
          this.importRef.showImportResult({
            type: 'result',
            resultArr: [{sheetName: '第一个表', rowIndex: '第一行', colIndex: '第一列', message: '错误信息'}]
          });
        }
      }
    },
    beforeDestroy() {
      // 组件开启异步执行回调后，需要手动销毁
      if(this.importRef && this.importRef.destroyed) this.importRef.destroyed();
    }
  };
</script>


```
:::

### 单独引用

单独引入 `Import`：

```javascript
import { Import } from 'yjpl-ui';
```

此时调用方法为 `Import(options)`。使用方法与基础用法一致。

### 后端包依赖包

九天需要依赖以下后端包

:::tip 

```html
<groupId>com.ygsoft.jt.teng</groupId>
<artifactId>teng.cp.file.fileupload</artifactId>

<groupId>com.ygsoft.jt.teng</groupId>
<artifactId>teng.cp.file.import</artifactId>


```
:::

NECP需要依赖以下后端包

:::tip 

```html
<dependency>
  <groupId>com.ygsoft.necp.component</groupId>
  <artifactId>necp.component.fileupload</artifactId>
  <version>${ecp.version}</version>
</dependency>

<groupId>com.ygsoft.necp.component</groupId>
<artifactId>necp.component.import</artifactId>

```
:::


### Import Attributes
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | -------------- | ------ |
colData	| 导入表格属性，如果不传入则返回原始数据，具体配置看下表 |	array | — | — |
vipAddress |vipAddress |	string | — |- |
title | Dialog | 标题 | string | — | 导入数据 |
beforeUpload |	导入前回调 |	function(file) | — |- |
onSuccess |	导入成功回调 |	function(data, file) |	— |	- |
onError	| 导入失败回调 |  function(res, file) | — |	- |
onChange |	文件变更回调 |	function(file)	| -	| -
onClose	| Dialog 关闭事件 |	function |—	|- |
onCatchFile	| 文件导入后删除文件回调，可自行删除文件, 调用 `delFile` 方法删除 |	function(resId, delFile) |—	|- |
message |	消息文字 |	string / VNode |	— |	- |
messageUseHtml | 是否将 message 属性作为 HTML 片段处理 | boolean |	— |	false |
modal-append-to-body |	遮罩层是否插入至 body 元素上，若为 false，则遮罩层会插入至 Dialog 的父元素上 |	boolean | 	— |	true |
append-to-body| Dialog 自身是否插入至 body 元素上。嵌套的 Dialog 必须指定该属性并赋值为 true |	boolean |	— |	false |
schemeVO |	格式化对象？传至天擎后端处理，天擎无具体文档 |	object |	— |	- |
importScheme |	开启导入方案 |	boolean |	`true` / `false` |	false |
schemeData |	方案内表格表头数据 |	array	| - |	- |
schemeListDialogTitle |	方案列表标题 |	array	| - |	- |
schemeDialogTitle |	方案设置标题 |	array	| - |	- |
maxLine |	方案数据最大行数 |	number	| - |	9999 |
optimize |	开启优化导入（大数据导入） |	boolean	| - |	false |
async |	优化导入的异步导入 |	boolean	| - |	false |
headRowNum |	优化导入的文件的标题行数 |	number	| - |	1 |
beanId |	优化导入的业务组实现的beanId |	string	| - |	'' |
functionPoints |	优化导入的功能点名称 |	string	| - |	'' |
importParams |	优化导入的自定义字符串（JSON字符串） |	string | - |	'' |
showImportedMessage ^(8.5.0) |	显示导入后的消息提示（非大数据导入） |	boolean | `true` / `false` |	true |
asyncCallback ^(8.5.0) |	异步导入后调用成功失败回调 |	boolean | `true` / `false` |	false |
advancedData |	方案内高级设置表格数据 |	array	| - |	- |
on-mate-key |	方案内表头匹配数据时回调 |	function(data)	| - |	- |
before-scheme-save |	方案保存之前处理数据回调 |	function(data)	| - |	- |
executorBeanId |	方案走自定义服务BeanId |	string	| - |	'' |

:::tip 

```html
WARNING

message 属性虽然支持传入 HTML 片段，但是在网站上动态渲染任意 HTML 是非常危险的，因为容易导致 XSS 攻击 (opens new window)。因此

在 messageUseHtml 打开的情况下，请确保 message 的内容是可信的，永远不要将用户提交的内容赋值给 message 属性。

```
:::

### colData
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
sheetName |	指定节点标签为节点对象的某个属性值 | string	| —	| — |
colName	| 列名字段，数组值的顺序与表格列对应| string| — | — |

### schemeData
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
title |	表格表头中的列key值 | string	| —	| — |
name	| 表格表头中的列名称| string| — | — |

### Events
事件名称 | 说明 | 回调参数
| --------------- | -------- | -------- | 
onSuccess | 导入成功事件 | (resData, file, schemeData) => void |
onSub | 选中导入方案事件 | (schemeData) => void |
onAdd | 新增导入方案成功事件 | (schemeData) => void |
onEdit | 编辑导入方案成功事件 | (schemeData) => void |
onDel | 删除导入方案前事件（返回false则是中断删除） | (schemeData) => return true |

### Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| showImportResult ^(8.5.0) | 导入结果弹窗，用于提示导入文件错误信息 | { type: 'result', resultArr: [{ sheetName: '表名', rowIndex: '行数', colIndex: '列数', message: '错误信息' }] } |