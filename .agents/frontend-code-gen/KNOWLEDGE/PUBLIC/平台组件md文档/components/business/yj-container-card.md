# YjContainerCard YJPL 表格容器

业务用表格组件，聚合组件自带工具栏组件和视图切换，为 `tl-table-tools` 、 `qzz-grid` 等组件封装。

### 基本使用

通过 `option` 属性配置表格，通过 `data` 配置数据。

:::demo 表格内置为 `qzz-grid`，具体配置方法可以参考 `qzz-grid` 文档。

```html
<template>
  <yj-container-card :option="gridOption" :data="data" height="300px"></yj-container-card>
</template>
<script>
  export default {
    data() {
      return {
        gridOption: {
          colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              editType: 'number',
              caption: '费用',
              name: 'cost'
            },
            {
              caption: '出发地',
              name: 'origin',
              showInList: true
            },
            {
              caption: '到达地',
              name: 'destination',
              showInList: true
            },
            {
              caption: '交通工具',
              name: 'traffic',
              showInList: true
            },
            {
              editType: 'date',
              caption: '日期',
              name: 'date'
            }
          ]
        },
        data: [
          { cost: 500, date: '2021-01-01', origin: '珠海', destination: '广州', traffic: '高铁' },
          { cost: 600, date: '2021-02-01', origin: '珠海', destination: '上海', traffic: '的士' },
          { cost: 900, date: '2021-03-01', origin: '珠海', destination: '北京', traffic: '火箭' },
          { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
          { cost: 800, date: '2021-05-01', origin: '珠海', destination: '天津', traffic: '飞机' }
        ]
      };
    }
  };
</script>
```

:::

### 视图切换

通过配置 `show-card` 和 `show-list` 属性，来显示卡片和列表视图。

:::demo 表格内置为 `qzz-grid`，具体配置方法可以参考 `qzz-grid` 文档。

```html
<template>
  <yj-container-card show-card show-list :option="gridOption" :data="data" height="300px">
    <div slot="card" slot-scope="scope">
      <span style="color:#162B41;font-size:16px;font-weight:bold;"> {{ scope.data.traffic }} </span><br />
      <p style="color:#162B41;">{{ scope.data.origin }} - {{ scope.data.destination }}</p>
      <p style="color:#F0760C;font-size:22px;font-weight:bold;">
        <font style="font-size:12px;">￥</font>{{ scope.data.cost }}
      </p>
      <span style="color:#162B41;"> {{ scope.data.date }} </span>
    </div>
  </yj-container-card>
</template>
<script>
  export default {
    data() {
      return {
        gridOption: {
          colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              editType: 'number',
              caption: '费用',
              name: 'cost'
            },
            {
              caption: '出发地',
              name: 'origin',
              showInList: true
            },
            {
              caption: '到达地',
              name: 'destination',
              showInList: true
            },
            {
              caption: '交通工具',
              name: 'traffic',
              showInList: true
            },
            {
              editType: 'date',
              caption: '日期',
              name: 'date'
            }
          ]
        },
        data: [
          { cost: 500, date: '2021-01-01', origin: '珠海', destination: '广州', traffic: '高铁' },
          { cost: 600, date: '2021-02-01', origin: '珠海', destination: '上海', traffic: '的士' },
          { cost: 900, date: '2021-03-01', origin: '珠海', destination: '北京', traffic: '火箭' },
          { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
          { cost: 800, date: '2021-05-01', origin: '珠海', destination: '天津', traffic: '飞机' }
        ]
      };
    }
  };
</script>
```

:::

### 表头切换配置

可在工具栏按钮切换表头显示。

:::demo 可通过配置 `mutiple-column-options` 属性，以数组形式配置 `qzz` 表格的 `colNames` 和 `colModels` 属性。

```html
<template>
  <yj-container-card
    :option="gridOption"
    :mutiple-column-options="mutipleOptions"
    :data="data"
    height="300px"
  ></yj-container-card>
</template>
<script>
  export default {
    data() {
      return {
        mutipleOptions: [
          {
            text: '默认',
            colNames: ['cost', 'origin', 'destination', 'traffic', 'date']
          },
          {
            text: '三列配置',
            colNames: [ 'cost', 'traffic', 'date']
          },
          {
            text: '四列配置',
            colNames: ['cost', 'origin', 'destination', 'traffic']
          }
        ],
        gridOption: {
          colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              editType: 'number',
              caption: '费用',
              name: 'cost'
            },
            {
              caption: '出发地',
              name: 'origin',
              showInList: true
            },
            {
              caption: '到达地',
              name: 'destination',
              showInList: true
            },
            {
              caption: '交通工具',
              name: 'traffic',
              showInList: true
            },
            {
              editType: 'date',
              caption: '日期',
              name: 'date'
            }
          ]
        },
        data: [
          { cost: 500, date: '2021-01-01', origin: '珠海', destination: '广州', traffic: '高铁' },
          { cost: 600, date: '2021-02-01', origin: '珠海', destination: '上海', traffic: '的士' },
          { cost: 900, date: '2021-03-01', origin: '珠海', destination: '北京', traffic: '火箭' },
          { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
          { cost: 800, date: '2021-05-01', origin: '珠海', destination: '天津', traffic: '飞机' }
        ]
      };
    }
  };
</script>
```

:::

### 配置简易切换区域

通过 `content-switch-data` 属性可配置简易的切换区域组件，如果不满足，自行通过 `content-switch` 插槽实现。

:::demo 配置 `data` 为数据，可以通过 `content-switch` 事件接受切换后的值。

```html
<template>
  <yj-container-card 
    :option="gridOption"
    :data="data"
    height="100px"
    :content-switch-data="checkboxData"
    @content-switch="handleContentSwitch"
  ></yj-container-card>
  <yj-container-card 
    :option="gridOption"
    :data="data"
    height="100px"
    :content-switch-data="radioData"
    @content-switch="handleContentSwitch"
  ></yj-container-card>
  <yj-container-card
    :option="gridOption"
    :data="data"
    height="100px"
    :content-switch-data="selectData"
    @content-switch="handleContentSwitch"
  ></yj-container-card>
</template>
<script>
  export default {
    data() {
      return {
        gridOption: {
          colNames: ['费用', '出发地', '到达地'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              editType: 'number',
              caption: '费用',
              name: 'cost'
            },
            {
              caption: '出发地',
              name: 'origin',
              showInList: true
            },
            {
              caption: '到达地',
              name: 'destination',
              showInList: true
            }
          ]
        },
        data: [
          { cost: 500, origin: '珠海', destination: '广州' }
        ],
        checkboxData: {
          dataType: 'checkbox',
          defaultValue: true,
          label: '是否显示：',
          text: '单位'
        },
        radioData: {
          dataType: 'radio',
          defaultValue: 'zh',
          data: [
            { text: '珠海', value: 'zh' },
            { text: '北京', value: 'bj' },
            { text: '武汉', value: 'wh' }
          ]
        },
        selectData: {
          dataType: 'select',
          defaultValue: 'zh',
          label: '目的地：',
          data: [
            { text: '珠海', value: 'zh' },
            { text: '北京', value: 'bj' },
            { text: '武汉', value: 'wh' }
          ]
        }
      };
    },
    methods: {
      handleContentSwitch(data) {
        console.log(data);
      }
    }
  };
</script>
```

:::

### 视图切换扩展

当视图切换的类型不满足时，可以通过扩展视图切换来实现效果。

:::demo 可通过配置 `view-switch-extend` 属性，以数组形式配置视图切换，通过 `default-show` 配置默认显示视图，可以通过配置 `events` 配置相应视图切换区域的事件，通过 `getRef` 方法可以获取到相应组件的 `ref` 实例。

```html
<template>
  <yj-container-card
    :option="gridOption"
    :view-switch-extend="viewSwitchExtend"
    default-show="grid"
    :events="eventsModel"
    :data="data"
    height="700px"
    ref="container"
  ></yj-container-card>
</template>
<script>
  export default {
    data() {
      return {
        viewSwitchExtend: [
          {
            // 自定义视图id
            name: 'test',
            // 图表配置
            icon: 'yj-icon yj-p-tubiao',
            // 组件标签或实例
            dataType: 'yj-pivot-table',
            // 绑定组件的属性
            option: {
              data: {
                dataType: '2',
                data: [
                  { cost: 100, date: '2021-01-01', origin: '珠海', destination: '珠海', traffic: '高铁' },
                  { cost: 200, date: '2021-02-01', origin: '珠海', destination: '珠海', traffic: '的士' },
                  { cost: 300, date: '2021-03-01', origin: '北京', destination: '北京', traffic: '火箭' },
                  { cost: 400, date: '2021-04-01', origin: '北京', destination: '深圳', traffic: '轮渡' },
                  { cost: 500, date: '2021-05-01', origin: '北京', destination: '天津', traffic: '飞机' }
                ]
                // 根据origin，destination分组，合计cost
              },
              options: {
                colModels: [
                  {
                    editType: 'number',
                    caption: '费用',
                    name: 'cost',
                    format: '[green]#0.00'
                  },
                  {
                    caption: '出发地',
                    name: 'origin',
                    showInList: true
                  },
                  {
                    caption: '到达地',
                    name: 'destination',
                    showInList: true,
                    format: '[red]@'
                  },
                  {
                    caption: '交通工具',
                    name: 'traffic',
                    showInList: true
                  },
                  {
                    editType: 'date',
                    caption: '日期',
                    name: 'date'
                  }
                ],
                total: 5,
                fieldsInfo: [[], [], [], []]
              }
            }
          }
        ],
        eventsModel: {
          test: {
            pageCurrentChange: data => {
              console.log('扩展组件事件', data);
            },
            pageSizeChange: data => {
              console.log('扩展组件事件pageSizeChange', data);
            },
            // 透视表配置拖动事件
            filterChange: (data, spread) => {
              console.log('扩展组件事件filterChange', data, spread);
            },
            updateClick: data => {
              console.log('扩展组件事件updateClick', data);
            },
            cellDoubleClick: (data, callback) => {
              console.log('扩展组件事件cellDoubleClick', data);
              setTimeout(() => {
                callback(
                  [{ cost: 800, date: '2021-01-01', origin: '珠海', destination: '珠海', traffic: '高铁' }],
                  300
                );
              }, 3000);
            },
            detailPagesChange: (data, callback) => {
              console.log('扩展组件事件detailOptionsChange', data);
              setTimeout(() => {
                callback(
                  [{ cost: 900, date: '2021-01-01', origin: '珠海', destination: '珠海', traffic: '高铁' }],
                  500
                );
              }, 3000);
            },
            sheetInitSuccess: data => {
              console.log('扩展组件事件sheetInitSuccess', data);
              setTimeout(() => {
                this.viewSwitchExtend[0].option.data.dataType = '1';
                this.viewSwitchExtend[0].option.options.total = 300;
                this.viewSwitchExtend[0].option.options.fieldsInfo = [
                  [],
                  [
                    {
                      name: 'origin',
                      displayName: '出发地',
                      sourceName: '出发地'
                    }
                  ],
                  [
                    {
                      name: 'destination',
                      displayName: '到达地',
                      sourceName: '到达地'
                    }
                  ],
                  [
                    {
                      name: 'cost',
                      displayName: '费用',
                      sourceName: '费用'
                    }
                  ]
                ];
                for (let i = 0; i < 300; i++) {
                  this.viewSwitchExtend[0].option.data.data.push({
                    cost: 50 + i,
                    date: '2021-01-01',
                    origin: '珠海',
                    destination: '广州',
                    traffic: '高铁'
                  });
                }
              }, 3000);
            }
          }
        },
        gridOption: {
          colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              editType: 'number',
              caption: '费用',
              name: 'cost'
            },
            {
              caption: '出发地',
              name: 'origin',
              showInList: true
            },
            {
              caption: '到达地',
              name: 'destination',
              showInList: true
            },
            {
              caption: '交通工具',
              name: 'traffic',
              showInList: true
            },
            {
              editType: 'date',
              caption: '日期',
              name: 'date'
            }
          ]
        },
        data: [
          { cost: 500, date: '2021-01-01', origin: '珠海', destination: '广州', traffic: '高铁' },
          { cost: 600, date: '2021-02-01', origin: '珠海', destination: '上海', traffic: '的士' },
          { cost: 900, date: '2021-03-01', origin: '珠海', destination: '北京', traffic: '火箭' },
          { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
          { cost: 800, date: '2021-05-01', origin: '珠海', destination: '天津', traffic: '飞机' }
        ]
      };
    }
  };
</script>
```

:::

### YjContainerCard Attributes

| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| title | 标题 | object  | - | - |
| option | 表格属性配置 | object  | - | - |
| grid-option | 表格属性配置，优先取该配置，否则取 `option` | object | - | - |
| list-option | 列表属性配置，优先取该配置，否则取 `option` | object | - | - |
| card-option | 卡片属性配置 | object  | - | - |
| data | 表格数据 | array   | - | -      |
| height | 容器高度 | string  | - | 'auto' |
| default-show | 默认显示类型 | string  | 'grid'/'card'/'list'/自定义 | 'grid' |
| show-grid | 是否显示表格 | boolean | - | true   |
| show-card | 是否显示卡片 | boolean | - | false  |
| show-list | 是否显示列表 | boolean | - | false  |
| is-query-grid | 是否使用查询表格 | boolean | - | false  |
| mutiple-column-options | 表头切换配置 | array   | - |
| view-switch-extend | 视图切换扩展属性，可自定义扩展视图切换类型，详见下表 | array | - |
| events | 自定义视图组件事件，根据相应的 `name` 配置相应组件的事件 | object | - |
| layout | `tl-table-tools` 模块布局排列顺序 | array | - | ['title', 'content-switch', 'content', 'view-switch', 'search', 'button'] |

### card-option

| 参数         | 说明           | 类型   | 可选值 | 默认值 |
| ------------ | -------------- | ------ | ------ | ------ |
| formatOption | 卡片格式化属性 | object | -      | -      |

### formatOption

| 参数        | 说明                 | 类型   | 可选值 | 默认值 |
| ----------- | -------------------- | ------ | ------ | ------ |
| title       | 卡片标题对应的 `key` | string | -      | -      |
| subTitle    | 副标题对应的 `key`   | string | -      | -      |
| money       | 金额对应的 `key`     | string | -      | -      |
| description | 描述对应的 `key`     | string | -      | -      |

### mutiple-column-options

| 参数      | 说明                          | 类型   | 可选值 | 默认值 |
| --------- | ----------------------------- | ------ | ------ | ------ |
| text      | 按钮的问题                    | string | -      | -      |
| colNames  | 列名列表，同 `qzz` 表格属性   | array  | -      | -      |
| colModels | 列属性列表，同 `qzz` 表格属性 | object | -      | -      |

### view-switch-extend

| 参数     | 说明                                   | 类型            | 可选值 | 默认值 |
| -------- | -------------------------------------- | --------------- | ------ | ------ |
| name     | 视图切换的 id                          | string          | -      | -      |
| icon     | 视图切换按钮的图表                     | string          | -      | -      |
| dataType | 视图切换的自定义组件，标签名或组件实例 | string / object | -      | -      |
| option   | 传入到组件内的属性                     | object          | -      | -      |

### YjContainerCard Refs

| name | 说明         |
| ---- | ------------ |
| ui   | 表格 ui 对象 |
| grid | 表格对象     |
| list | 列表对象     |

### YjContainerCard Slots

| name    | 说明             |
| ------- | ---------------- |
| toolbar | 工具栏插槽       |
| prepend | 往前插入表单元素 |
| button  | 按钮区域         |
| title | 标题插槽         |
| content-switch | 内容切换模块插槽 |
| content | 内容说明模块插槽 |
| button | 功能按钮模块插槽 |
| search  | 搜索模块插槽     |
| view-switch | 视图切换模块插槽 |

### YjContainerCard Events

| 事件名称         | 说明             | 回调参数            |
| ---------------- | ---------------- | ------------------- |
| fullscreen       | 全屏事件         | 是否全屏            |
| card-click       | 卡片点击事件     | (data, selected)    |
| card-dblclick    | 卡片双击事件     | (data)              |
| card-page-change | 卡片分页切换事件 | (page, size, index) |
| card-size-change | 卡片分页条数切换事件 | (page, size, index) |

### YjContainerCard Methods

| 方法名 | 说明                            | 参数 |
| ------ | ------------------------------- | ---- |
| getRef | 获取自定义视图组件的 `ref` 实例 | name |
