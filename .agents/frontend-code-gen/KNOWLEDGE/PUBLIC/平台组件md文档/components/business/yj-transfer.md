# YjTransfer YJPL 穿梭框容器

业务穿梭框组件。

# 基本使用

设置`type` 属性值是`grid`为表格穿梭框，配置列名 `col-names`,配置 `col-models` 表格列绑定字段；`type`值是`list` 为列表穿
梭框，`pageSize`属性 pageSize 大于 0，数据是要分页； `titles` 属性;`search-key`属性值：绑定的数据中字段中数据不重复的字
段 ;searchable 属性表示的是否显示搜索框。

:::demo`type`的值为`grid`，类型为表格时，配置列名 `col-names`,配置 `col-models` 表格列绑定字段

```html
<template>
  <yj-transfer
    :type="conmpoentType"
    :searchable="true"
    :titles="['目标内容','展示标题']"
    :col-names="colNames"
    :col-models="colModels"
    :data="dataSource"
    :search-key="searchKey"
    :page-size="pageSize"
  ></yj-transfer>
</template>

<script>
  export default {
    name: 'YjTransferGrid',
    data() {
      return {
        currentPageNo: 1,
        pageSize: 10,
        // grid表头
        colNames: ['对象名称', '统计编码', '开始日期', '结束日期'],
        colModels: [
          {
            name: 'dxmc',
            width: '80px'
          },
          {
            name: 'TJBM'
          },
          {
            name: 'KSRQ',
            dataType: 'date'
          },
          {
            name: 'JSRQ',
            dataType: 'date'
          }
        ],
        conmpoentType: 'grid',
        searchKey: 'dxmc',
        // 数据源
        dataSource: [
          {
            dxmc: '1',
            TJBM: '001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '2',
            TJBM: '002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            DEFAULT: true
          },
          {
            dxmc: '3',
            TJBM: '002001',
            DKLY: '农业银行',
            JE: 10000,
            KSRQ: '2015-10-10'
          },
          {
            dxmc: '4',
            TJBM: '002002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          {
            dxmc: '5',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '6',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '7',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '8',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '9',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '10',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '11',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '12',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '13',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          }
        ]
      };
    }
  };
</script>
```

:::

# 初始化展示的数据

设置属性`right-data`的值可以初始化展示标题内容

:::demo

```html
<template>
  <yj-transfer
    :type="conmpoentType"
    :searchable="true"
    :titles="['目标内容','展示标题']"
    :col-names="colNames"
    :col-models="colModels"
    :data="dataSource"
    :right-data="initRightData"
    :search-key="searchKey"
    :page-size="pageSize"
  ></yj-transfer>
</template>

<script>
  export default {
    name: 'YjTransferGrid',
    data() {
      return {
        currentPageNo: 1,
        pageSize: 10,
        // grid表头
        colNames: ['对象名称', '统计编码', '开始日期', '结束日期'],
        colModels: [
          {
            name: 'dxmc',
            width: '80px'
          },
          {
            name: 'TJBM'
          },
          {
            name: 'KSRQ',
            dataType: 'date'
          },
          {
            name: 'JSRQ',
            dataType: 'date'
          }
        ],
        conmpoentType: 'grid',
        searchKey: 'dxmc',
        // 数据源
        dataSource: [
          {
            dxmc: '1',
            TJBM: '001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '2',
            TJBM: '002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            DEFAULT: true
          },
          {
            dxmc: '3',
            TJBM: '002001',
            DKLY: '农业银行',
            JE: 10000,
            KSRQ: '2015-10-10'
          },
          {
            dxmc: '4',
            TJBM: '002002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          {
            dxmc: '5',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '6',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '7',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '8',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '9',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '10',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '11',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '12',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '13',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          }
        ],
        initRightData: [
          {
            dxmc: '14',
            TJBM: '0014',
            DKLY: '中国银行',
            JE: 140000,
            CONTEXT: '远光地产建设'
          },
          {
            dxmc: '15',
            TJBM: '002',
            DKLY: '民生银行',
            JE: 150000,
            CONTEXT: '远光地产建设',
            DEFAULT: true
          },
          {
            dxmc: '16',
            TJBM: '002001',
            DKLY: '广大银行',
            JE: 10000,
            KSRQ: '2015-10-10'
          }
        ]
      };
    }
  };
</script>
```

:::

# 方法使用

获取过滤后的数据：`change-target`方法获取穿梭搜目标内容的数据；`change-filter`方法获取穿梭后展示数据,通
过`target-refs`,`filter-refs`方法获取表格或列表的 ref。

:::demo

```html
<template>
  <yj-transfer
    :type="conmpoentType"
    :titles="['目标内容','展示标题']"
    :searchable="true"
    :col-names="colNames"
    :col-models="colModels"
    :data="dataSource"
    :search-key="searchKey"
    :page-size="pageSize"
    @change-target="changeTarget"
    @change-filter="changeFilter"
    @target-refs="getTargetContent"
    @filter-refs="getFilterContent"
  ></yj-transfer>
</template>
<script>
  export default {
    name: 'YjTransferGrid1',
    data() {
      return {
        currentPageNo: 1,
        pageSize: 5,
        initGridData: [
          {
            dxmc: '1',
            TJBM: '001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '2',
            TJBM: '002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            DEFAULT: true
          },
          {
            dxmc: '3',
            TJBM: '002001',
            DKLY: '农业银行',
            JE: 10000,
            KSRQ: '2015-10-10'
          },
          {
            dxmc: '4',
            TJBM: '002002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          {
            dxmc: '5',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '6',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '7',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '8',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '9',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '10',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '11',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '12',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '13',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          }
        ],
        // grid表头
        colNames: ['对象名称', '统计编码', '开始日期', '结束日期'],
        colModels: [
          {
            name: 'dxmc',
            width: '80px'
          },
          {
            name: 'TJBM'
          },
          {
            name: 'KSRQ',
            dataType: 'date'
          },
          {
            name: 'JSRQ',
            dataType: 'date'
          }
        ],
        conmpoentType: 'grid',
        searchKey: 'dxmc',
        // 数据源
        dataSource: []
      };
    },
    created() {
      this.dataSource = this.initGridData;
    },
    methods: {
      changeFilter(value) {
        console.log('展示表格的数据', value);
      },
      changeTarget(value) {
        console.log('目标表格的数据', value);
      },
      // 获取ref
      getTargetContent(refVal) {
        console.log('展示表格ref', refVal);
      },
      getFilterContent(refVal) {
        console.log('目标表格ref', refVal);
      }
    }
  };
</script>
```

:::

# slot

通过`slot` 属性插入对表格数据的控制

:::demo

```html
<template>
  <yj-transfer
    :type="type"
    :titles="['目标内容','展示内容']"
    :searchable="true"
    :col-names="colNames"
    :col-models="colModels"
    :data="dataSource"
    :search-key="searchKey"
    :page-size="pageSize"
    @change-target="onChangeTarget"
    @change-filter="onChangeFilter"
    @target-refs="getTargetContent"
    @filter-refs="getFilterContent"
  >
    <template v-slot:leftbuttonlist>
      <tl-button size="mini">保存</tl-button>
      <tl-button size="mini" icon="iconfont ticon iconshangyi" @click="upBtn(targetQueryContent.ui)"></tl-button>
      <tl-button size="mini" icon="iconfont ticon iconxiayi" @click="downBtn(targetQueryContent.ui)"></tl-button>
      <tl-button size="mini" icon="el-icon-refresh"></tl-button>
    </template>
    <template v-slot:rightbuttonlist>
      <tl-button size="mini">保存</tl-button>
      <tl-button size="mini" icon="iconfont ticon iconshangyi" @click="upBtn(filterQueryContent.ui)"></tl-button>
      <tl-button size="mini" icon="iconfont ticon iconxiayi" @click="downBtn(filterQueryContent.ui)"></tl-button>
      <tl-button size="mini" icon="el-icon-refresh"></tl-button>
    </template>
  </yj-transfer>
</template>

<script>
  export default {
    name: 'YjTransferGrid8',
    data() {
      return {
        type: 'grid',
        searchKey: 'dxmc',
        pageSize: 5,
        targetQueryContent: '',
        filterQueryContent: '',
        targetQueryData: [],
        filterQueryData: [],
        colNames: ['对象名称', '统计编码', '开始日期', '结束日期'],
        colModels: [
          {
            name: 'dxmc',
            width: '80px'
          },
          {
            name: 'TJBM'
          },
          {
            name: 'KSRQ',
            dataType: 'date'
          },
          {
            name: 'JSRQ',
            dataType: 'date'
          }
        ],
        dataSource: [
          {
            dxmc: '1',
            TJBM: '001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '2',
            TJBM: '002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            DEFAULT: true
          },
          {
            dxmc: '3',
            TJBM: '002001',
            DKLY: '农业银行',
            JE: 10000,
            KSRQ: '2015-10-10'
          },
          {
            dxmc: '4',
            TJBM: '002002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          {
            dxmc: '5',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '6',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '7',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '8',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '9',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '10',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          },
          {
            dxmc: '11',
            TJBM: '002002001',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            STOP: true
          },
          {
            dxmc: '12',
            TJBM: '003',
            DKLY: '交通银行',
            JE: 10000
          },
          {
            dxmc: '13',
            TJBM: '004',
            DKLY: '工商银行',
            JE: 10000,
            CONTEXT: '房地产建设'
          }
        ]
      };
    },
    methods: {
      onChangeFilter(value) {
        this.targetQueryData = value;
      },
      onChangeTarget(value) {
        this.filterQueryData = value;
      },
      getTargetContent(val) {
        this.targetQueryContent = val;
      },
      getFilterContent(val) {
        this.filterQueryContent = val;
      },
      save() {
        this.$message('保存信息');
      },
      updateIndex(uiEle) {
        uiEle.each(function () {
          this.setValue('rn', this.getIndex());
        });
      },
      upBtn(ui) {
        let data = ui.value();
        if (data.length) {
          ui.moveRecord(-1);
          this.updateIndex(ui);
        }
      },
      downBtn(ui) {
        let data = ui.value();
        if (data.length) {
          ui.moveRecord(1);
          this.updateIndex(ui);
        }
      },
      refresh() {
        this.$message('刷新数据');
      }
    }
  };
</script>
<style>
  .save-btn {
    width: 50px;
  }
</style>
```

:::

### YjTransfer Attributes

| 参数       | 说明                               | 类型    | 可选值              | 默认值                  |
| ---------- | ---------------------------------- | ------- | ------------------- | ----------------------- |
| data       | 组件渲染原始数据                   | Array   | -                   | -                       |
| rightData  | 组件渲染右侧初始化数据             | Array   | -                   | -                       |
| type       | 类型                               | String  | list/grid(详见下表) | -                       |
| pageSize   | 每页的 pageSize，值为 0 表示不分页 | Number  | -                   | 0                       |
| searchable | 搜索框的占位符                     | Boolean | false/true          | false                   |
| titles     | 左右的容器的 title                 | Array   | -                   | ['目标内容','展示内容'] |
| searchKey  | 绑定数据中一个唯一的字段           | String  | —                   | -                       |

### YjTransfer Event

| 事件名称      | 说明                      | 回调参数 |
| ------------- | ------------------------- | -------- |
| change-target | 获取穿梭后的数据(右侧)    | value    |
| change-filter | 获取穿梭后的数据(左侧)    | value    |
| target-refs   | 获取左侧的 ref 内容(左侧) | refVal   |
| filter-refs   | 获取右侧的 ref 内容(右侧) | refVal   |

### YjTransfer Grid Attributes

| 参数        | 说明           | 类型  | 可选值 | 默认值 |
| ----------- | -------------- | ----- | ------ | ------ |
| column-name | 表头的名称     | Array | -      | -      |
| col-models  | 表头绑定的字段 | Array | -      | -      |

### YjTransfer list Attributes

| 参数 | 说明           | 类型  | 可选值 | 默认值                    |
| ---- | -------------- | ----- | ------ | ------------------------- |
| data | 传入的数据格式 | Array | -      | {value:'1',text:'选项 1'} |

### YjTransfer slot

| 参数            | 说明                 |
| --------------- | -------------------- |
| leftbuttonlist  | 左侧插入的自定义按钮 |
| rightbuttonlist | 右侧插入的自定义按钮 |
