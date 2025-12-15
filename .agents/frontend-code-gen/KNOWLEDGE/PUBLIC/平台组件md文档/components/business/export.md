# Export 导出组件

导出excel表格数据，需要引入天擎后端包。

### 基础用法

使用 `this.$export` 进行导出。

:::demo

```html
<tl-button @click="handleExport">导出</tl-button>

<script>
  export default {
    methods: {
      handleExport() {
        this.$export({
          vipAddress: '/jt/mapp/sample',
          fileName: '员工',
          data: [
            {
              tabName: 'sheet1',
              header: ['姓名', '年龄', '性别'],
              /**
               * 单元格格式配置
               * 目前相关文档仅有以下属性，详细需要咨询后端同事
               * @property {number} rowFrom  行起始位置
               * @property {number} colFrom  行结束位置
               * @property {number} rowTo  列起始位置
               * @property {number} colTo  列结束位置
               * @property {string} d  文本信息text
               * @property {string} s  样式style
               * @property {string} dt  类型dataType
               * @property {string} df  字段项目datafield
               * @property {string} width  宽度
               * @property {number} colspan  跨列
               * @property {number} rowspan  跨行
               */
              userData: []
            }
          ],
          exportTemplate: true,
          templateData: [
            {
              tabName: 'sheet1',
              header: ['姓名', '年龄', '性别']
            }
          ]
        });
      }
    }
  };
</script>
```
:::

### 导出范围

可以根据范围导出。

:::demo

```html
<tl-button @click="handleExport">导出</tl-button>

<script>
  export default {
    methods: {
      handleExport() {
        this.$export({
          vipAddress: '/jt/mapp/sample',
          fileName: '测试用例',
          exportRange: true,
          exportTemplate: true,
          templateData: [
            {
              tabName: 'sheet1',
              header: ['姓名', '年龄', '性别']
            }
          ],
          data: [
            /**
               * 返回参数
               * @param {String} pages  行起始位置 0: 全部 1: 当前页 其他:自定义
               * @param {String} exportMode 导出模式
               * @param {String} fileType  导出文件类型
               */
            (pages, exportMode, fileType) => {
              // 根据返回的参数处理分页数据
              return {
                tabName: 'sheet1',
                header: ['姓名', '年龄', '性别'],
                userData: [
                  [{d: '张三', s: 'b'}, '18', '男'],
                  ['李四', '1', '女'],
                  ['王五', '99', '未知']
                ]
              };
            },
            {
              tabName: 'sheet2',
              header: ['姓名', '年龄', '性别'],
              userData: [
                ['张三', '18', '男'],
                ['李四', '1', '女'],
                ['王五', '99', '未知']
              ]
            }
          ]
        })
      }
    }
  };
</script>
```
:::

### 扩展选项

可以使用标签模式使用组件，里用 `slot` 扩展选项，通过 `beforeExport` 回调修改导出数据。

:::demo

```html
<tl-button @click="handleExport">导出</tl-button>

<tl-export
  export-template
  vip-address="/jt/mapp/sample"
  fileName="测试用例"
  :visible.sync="visible"
  :data="data"
  :template-data="templateData"
  :before-export="handleBeforeExport"
>
  <tl-radio v-model="extend" label="1">扩展选项1</tl-radio>
  <tl-radio v-model="extend" label="2">扩展选项2</tl-radio>
</tl-export>

<script>
  export default {
    data() {
      return {
        visible: false,
        extend: '1',
        templateData: [
          {
            tabName: 'sheet1',
            header: ['姓名', '年龄', '性别']
          }
        ],
        data: [
          {
            tabName: 'sheet1',
            header: ['姓名', '年龄', '性别'],
            userData: [
              ['张三', '18', '男'],
              ['李四', '1', '女'],
              ['王五', '99', '未知']
            ]
          }
        ]
      }
    },
    methods: {
      handleExport() {
        this.visible = true;
      },
      handleBeforeExport(exportContent) {
        exportContent.extend = this.extend;
        console.log(exportContent);
      }
    }
  };
</script>
```
:::

### 服务端导出

可以根据服务端导出，绕过前端数据的获取步骤。

:::demo

```html
<tl-button @click="handleExport">导出</tl-button>

<script>
  export default {
    methods: {
      handleExport() {
        this.$export({
          vipAddress: '/jt/mapp/sample',
          fileName: '测试用例',
          // 异步导出
          async: true,
          // 业务领域标识
          fromFunName: '测试用例',
          data: [
            {
              tabName: 'sheet3',
              header: ['业务领域', '类名称', '创建时间'],
              serverDataConfig: {
                beanId: '/jt/mapp/sample/export/where',
                //调用接口时的参数在这里定义
                params: {
                  name: 'hy',
                  pageRange: {
                    begin: 1,
                    end: 1
                  },
                  pageSize: '1000'
                },
                //表示是URL导出
                resultType: 'URL',
                colModel: [
                  {
                    name: 'biz_domain',
                    align: 'center',
                    metaItem: {
                      dataType: 'string',
                    }
                  },
                  {
                    name: 'clazz_name',
                    align: 'right',
                    metaItem: {
                      dataType: 'string',
                    }
                  },
                  {
                    name: 'cre_time',
                    align: 'center',
                    metaItem: {
                      dataType: 'string',
                    }
                  }
                ]
              }
            }
          ]
        })
      }
    }
  };
</script>
```
:::

### 导出添加水印

可通过 `watermark` 参数添加水印。

:::demo

```html
<tl-button @click="handleExport">导出</tl-button>

<script>
  export default {
    methods: {
      handleExport() {
        this.$export({
          vipAddress: '/jt/mapp/sample',
          fileName: '员工',
          data: [
            {
              tabName: 'sheet1',
              header: ['姓名', '年龄', '性别'],
              userData: [
                [{ d:'名单', s: 'h|c', colspan:3 }],
                ['张三', { d:'18', dt: 'number' }, '男'],
                ['李四', { d:'19', dt: 'number' }, '女'],
                ['王五', { d:'999', dt: 'number' }, '未知']
              ]
            }
          ],
          watermark: {
            // 水印文本列表，一个元素代表水印一行
            text: ['这是一个水印', new Date().toString()],
            // 水印的颜色和透明度，默认为浅灰色不透明
            color: '#1687e8',
            // 水印的旋转角度
            rotationAngle: -30
          }
        });
      }
    }
  };
</script>
```
:::

### 批量导出

可通过 `batch` 参数使用批量导出。

:::demo

```html
<tl-button @click="handleExport">批量导出</tl-button>

<script>
  export default {
    methods: {
      handleExport() {
        this.$export({
          batch: true, // 开启批量导出
          vipAddress: '/jt/mapp/sample/',
          async: true, // 异步
          batchData: [{ // 批量导出表格数据
              fileName: '测试用例8',
              fromFunName: '导出功能8',
              data: [{
                sheetName: '人员名单',
                userData: [
                  [{d:'概要',s: 'h|c',colspan:2},{d:'文件名',s: 'h|c',rowspan:2},{d:'文件类型',s: 'h|c',rowspan:2},{d:'详情',s: 'h|c',colspan:3}],
                  [{d:'业务key',s: 'h|c'},{d:'资源id',s: 'h|c'},{d:'文件大小',s: 'h|c'},{d:'上传时间',s: 'h|c'},{d:'文件所在位置',s: 'h|c'}]
                ],
                serverDataConfig: {
                  'beanId': '/jt/mapp/sample/export/before',
                  'translate': '/jt/mapp/sample/export/translate',
                  'after': '/jt/mapp/sample/export/after',
                  // 'dataTenant': 9916,
                  'params': {
                    'name': 'hy',
                    'table': 'attachment_summary',
                    'pageRange': {
                      'begin': 1,
                      'end': 31
                    },
                    'pageSize': '3000'
                  },
                  'resultType': 'SQL',
                  'colModel': [{
                      'name': 'ywkey',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'resid',
                      'align': 'right',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'title',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'btype',
                      'align': 'left',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'bsize',
                      'align': 'left',
                      'metaItem': {
                        'dataType': 'currency'
                      }
                    },
                    {
                      'name': 'addtime',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'storageaddress',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    }
                  ]
                }
              }]
            },
            {
              fileName: '测试用例9',
              fromFunName: '导出功能9',
              data: [{
                sheetName: '人员名单1',
                userData: [
                  [{d:'概要',s: 'h|c',colspan:2},{d:'文件名',s: 'h|c',rowspan:2},{d:'文件类型',s: 'h|c',rowspan:2},{d:'详情',s: 'h|c',colspan:3}],
                  [{d:'业务key',s: 'h|c'},{d:'资源id',s: 'h|c'},{d:'文件大小',s: 'h|c'},{d:'上传时间',s: 'h|c'},{d:'文件所在位置',s: 'h|c'}]
                ],
                serverDataConfig: {
                  'beanId': '/jt/mapp/sample/export/before',
                  'translate': '/jt/mapp/sample/export/translate',
                  'after': '/jt/mapp/sample/export/after',
                  // 'dataTenant': 9916,
                  'params': {
                    'name': 'hy',
                    'table': 'attachment_summary',
                    'pageRange': {
                      'begin': 1,
                      'end': 31
                    },
                    'pageSize': '3000'
                  },
                  'resultType': 'SQL',
                  'colModel': [{
                      'name': 'ywkey',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'resid',
                      'align': 'right',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'title',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'btype',
                      'align': 'left',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'bsize',
                      'align': 'left',
                      'metaItem': {
                        'dataType': 'currency'
                      }
                    },
                    {
                      'name': 'addtime',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    },
                    {
                      'name': 'storageaddress',
                      'align': 'center',
                      'metaItem': {
                        'dataType': 'string'
                      }
                    }
                  ]
                }
              }]
            }
          ]
        });
      }
    }
  };
</script>
```
:::

### 单独引用

单独引入 `Export`：

```javascript
import { Export } from 'yjpl-ui';
```

此时调用方法为 `Export(options)`。使用方法与基础用法一致。

### 后端包依赖包

九天需要依赖以下后端包

```xml
<groupId>com.ygsoft.jt.teng</groupId>
<artifactId>teng.cp.file.fileupload</artifactId>

<groupId>com.ygsoft.jt.teng</groupId>
<artifactId>teng.cp.file.export</artifactId>
```

NECP需要依赖以下后端包

```xml
<groupId>com.ygsoft.necp.component</groupId>
<artifactId>necp.component.export</artifactId>

```

### Export Attributes
| 参数      | 说明          | 类型      | 可选值 | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| data / dataList | 导出表格属性，具体配置看下表 | array | — | — |
| templateData | 导出模板属性，具体配置看下表 | array | — | — |
| exportTemplate | 是否使用导出模板 | boolean | — | false |
| exportRange | 是否使用范围 | boolean | — | false |
| exportTypes | 可导出文件类型 | Array | ['xlsx', 'xls', 'et', 'pdf', 'csv', 'docx', 'doc'] | - |
| fileName | 导出的文件名 | string | — | - |
| vipAddress | vipAddress | string | — | - |
| modal-append-to-body | 遮罩层是否插入至 body 元素上，若为 false，则遮罩层会插入至 Dialog 的父元素上   | boolean   | — | true |
| append-to-body | Dialog 自身是否插入至 body 元素上。嵌套的 Dialog 必须指定该属性并赋值为 true   | boolean   | — | false |
| servlet | 是否使用 servlet 请求   | boolean   | — | false |
| async | 开启异步导出 | boolean   | — | false |
| fromFunName | 配合异步导出使用，用于标记业务领域 | string | - | - |
| exportValidation | 枚举单元格数据有效性 | boolean | - | false |
| dataAction ^(8.5.0) | 指定访问主库还是从库 | string | 'edit' \| 'read0' | 'edit' |
| batch ^(8.5.0) | 开启批量导出 | boolean | - | false |
| batchData ^(8.5.0) | 批量导出表格数据 | array | - | - |
| beforeExport ^(8.5.0) | 导出前回调，可用于修改导出数据 | function(content) | - | - |

:::tip
`data / dataList` 可为对象或回调，但是回调必须返回的数据并且格式须于下表保持一致，主要用于导出范围使用，详细可查看导出范围样例。
:::

### data / dataList / templateData / batchData
| 参数       | 说明                | 类型     | 可选值  | 默认值  |
| -------- | ----------------- | ------ | ---- | ---- |
| tabName    | 指定节点标签为节点对象的某个属性值 | string | —    | —    |
| header    | 表格表头 | array | —    | —    |
| userData | 表格数据 | array | —    | —    |
| afterData | 表格前数据 | array | —    | —    |
| beforeData | 表格前数据 | array | —    | —    |
| serverDataConfig | 服务端导出配置数据 | array | —    | —    |

### Export Slots
| name | 说明 |
|------|--------|
| - ^(8.5.0) | 用于扩展导出选项 |
| button ^(8.5.0) | 用于扩展底部按钮 |