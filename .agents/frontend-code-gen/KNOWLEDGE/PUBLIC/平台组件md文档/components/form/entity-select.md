# EntitySelect 通用实体选择

基于下拉选择二次封装的通用实体选择，当下拉数据量大的时候选用该组件

### 树表选择

适用广泛的基础单选和多选，默认弹出为树表
:::demo`v-model`的值为当前被选中的 value 属性值`id-field`为`data`的 id 标识`text-field`为`data`的显示标识
```html
<template>
  <div class="entity-select">
    <tl-entity-select
      v-model="value1"
      :data="data"
      :table-data="data"
      :tree-data="treeData"
      :col-data="colData"
      id-field="dxid"
      text-field="dxmc"
      tree-id-field="id"
      tree-text-field="name"
      tree-code-field="bm"
      tree-code-policy="4-4-4-4-4-4-4-4-4-4"
      :tree-field="{parent: 'bm', children: 'groupId'}"
    >
    </tl-entity-select>
    <tl-entity-select
      multiple
      v-model="value2"
      :data="data"
      :table-data="data"
      :tree-data="treeData"
      :col-data="colData"
      id-field="dxid"
      text-field="dxmc"
      tree-id-field="id"
      tree-text-field="name"
      tree-code-field="bm"
      tree-code-policy="4-4-4-4-4-4-4-4-4-4"
      :tree-field="{parent: 'bm', children: 'groupId'}"
    >
    </tl-entity-select>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        value1: '',
        value2: [],
        data: [
          { dxid: 1570001118, dxmc: '江苏电力11111111111111111111111111111111111111111111111111111111' },
          { dxid: 1570001098, dxmc: '湖北电力' },
          { dxid: 1570001113, dxmc: '大宇物流' },
          { dxid: 1570001115, dxmc: '风尖' },
          { dxid: 1570001114, dxmc: '威然' },
          { dxid: 1570001121, dxmc: '喆大电力' }
        ],
        treeData: [
          { bm: '81040001', name: '单位分类', id: 1 },
          { bm: '810400010001', name: '系统内单位', id: 2 },
          { bm: '81040002', name: '系统外单位', id: 3 }
        ],
        colData: [
          { label: 'id', prop: 'dxid' },
          { label: '名称', prop: 'dxmc', selected: true }
        ],
      }
    }
  };
</script>
<style>
  .entity-select{
    display:flex;
  }
  .entity-select .tl-entity-select{
    margin-right: 20px;
  }
</style>
```
:::

### 单树单表

当不需要树/表时，可改为单树/单表选择
:::demo `type`设为`tree`为单树选择，`table`为单表选择
```html
<template>
  <div class="entity-select">
    <tl-entity-select
      type="tree"
      v-model="value1"
      multiple
      :data="treeData"
      :tree-data="treeData"
      id-field="id"
      text-field="text"
      tree-parent-field="pid">
    </tl-entity-select>
    <tl-entity-select
      type="table"
      v-model="value2"
      :data="data"
      :table-data="data"
      :col-data="colData"
      id-field="dxid"
      text-field="dxmc">
    </tl-entity-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value1: '',
      value2: [],
      treeData: [
        { id: '1', pid: '', text: '公司本部' },
        { id: '2', pid: '1', text: 'ECP研发部' },
        { id: '3', pid: '1', text: 'DAP研发中心' },
        { id: '4', pid: '2', text: '天鹂项目组' },
        { id: '5', pid: '2', text: '天蜂项目组' },
        { id: '6', pid: '2', text: '天鹿项目组' },
        { id: '7', pid: '3', text: 'DAP研发一部' },
        { id: '8', pid: '3', text: 'DAP研发二部' },
        { id: '9', pid: '3', text: 'DAP研发三部' },
        { id: '10', pid: '3', text: 'DAP研发四部' },
        { id: '11', pid: '4', text: '打杂人员' },
        { id: '12', pid: '', text: '全资子公司' }
      ],
      data: [
        { dxid: 1, dxmc: '电力' },
        { dxid: 1570001118, dxmc: '江苏电力' },
        { dxid: 1570001098, dxmc: '湖北电力' },
        { dxid: 1570001113, dxmc: '大宇物流' },
        { dxid: 1570001115, dxmc: '风尖' },
        { dxid: 1570001114, dxmc: '威然' },
        { dxid: 1570001121, dxmc: '喆大电力' }
      ],
      colData: [
        { label: 'id', prop: 'dxid' }
      ]
    }
  }
};
</script>
```
:::

### 异步打开弹窗 ^(8.5.0)

当需要异步操作后再打开弹出窗
:::demo 传入`async-open`，参数为控制展开的方法。
```html
<template>
  <div class="entity-select">
    <tl-entity-select
      type="tree"
      v-model="value1"
      multiple
      :data="treeData"
      :tree-data="treeData"
      id-field="id"
      text-field="text"
      tree-parent-field="pid"
      :async-open="handleOpen">
    </tl-entity-select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value1: '',
      value2: [],
      treeData: [
        { id: '1', pid: '', text: '公司本部' },
        { id: '2', pid: '1', text: 'ECP研发部' },
        { id: '3', pid: '1', text: 'DAP研发中心' },
        { id: '4', pid: '2', text: '天鹂项目组' },
        { id: '5', pid: '2', text: '天蜂项目组' },
        { id: '6', pid: '2', text: '天鹿项目组' },
        { id: '7', pid: '3', text: 'DAP研发一部' },
        { id: '8', pid: '3', text: 'DAP研发二部' },
        { id: '9', pid: '3', text: 'DAP研发三部' },
        { id: '10', pid: '3', text: 'DAP研发四部' },
        { id: '11', pid: '4', text: '打杂人员' },
        { id: '12', pid: '', text: '全资子公司' }
      ],
      data: [
        { dxid: 1, dxmc: '电力' },
        { dxid: 1570001118, dxmc: '江苏电力' },
        { dxid: 1570001098, dxmc: '湖北电力' },
        { dxid: 1570001113, dxmc: '大宇物流' },
        { dxid: 1570001115, dxmc: '风尖' },
        { dxid: 1570001114, dxmc: '威然' },
        { dxid: 1570001121, dxmc: '喆大电力' }
      ],
      colData: [
        { label: 'id', prop: 'dxid' }
      ]
    }
  },
  methods: {
    handleOpen(show) {
      const loading = this.$loading();
      setTimeout(() => {
        show();
        loading.close();
      }, 2000);
    }
  }
};
</script>
```
:::

### 自定义表格列

表格列可以通过插槽自定义
:::demo 表格列格式可以通过插槽自定义，需要通过表格列组件 `el-table-column` 配合 `table-column` 插槽一起使用。
```html
<template>
  <tl-entity-select
    v-model="value"
    :data="data"
    :table-data="data"
    :tree-data="treeData"
    :col-data="colData"
    id-field="dxid"
    text-field="dxmc"
    tree-id-field="id"
    tree-text-field="name"
    tree-code-field="bm"
    tree-code-policy="4-4-4-4-4-4-4-4-4-4"
    :tree-field="{parent: 'bm', children: 'groupId'}"
  >
    <tl-table-column slot="table-column" label="自定义列" align="center" :width="120">
      <template slot-scope="scope">
        <tl-switch v-model="scope.row.switch"></tl-switch>
      </template>
    </tl-table-column>
  </tl-entity-select>
</template>

<script>
  export default {
    data () {
      return {
        value: '',
        data: [
          { dxid: 1570001118, dxmc: '江苏电力', switch: true },
          { dxid: 1570001098, dxmc: '湖北电力', switch: true },
          { dxid: 1570001113, dxmc: '大宇物流', switch: false },
          { dxid: 1570001115, dxmc: '风尖', switch: false },
          { dxid: 1570001114, dxmc: '威然', switch: true },
          { dxid: 1570001121, dxmc: '喆大电力', switch: false }
        ],
        treeData: [
          { bm: '81040001', name: '单位分类', id: 1 },
          { bm: '810400010001', name: '系统内单位', id: 2 },
          { bm: '81040002', name: '系统外单位', id: 3 }
        ],
        colData: [
          { label: 'id', prop: 'dxid' },
          { label: '名称', prop: 'dxmc', selected: true }
        ],
      }
    }
  };
</script>
```
:::

### 使用 QZZ 表格

将表格替换为 `QZZ` 表格
:::demo 允许将表格配置为 `QZZ` 表格，配置可以保持不变，也可以通过 `table-options` 配置 `QZZ` 表格属性。
```html
<template>
  <tl-entity-select
    v-model="value1"
    use-qzz-grid
    :data="data"
    :table-data="data"
    :tree-data="treeData"
    :col-data="colData"
    id-field="dxid"
    text-field="dxmc"
    tree-id-field="id"
    tree-text-field="name"
    tree-code-field="bm"
    tree-code-policy="4-4-4-4-4-4-4-4-4-4"
    :tree-field="{parent: 'bm', children: 'groupId'}"
    @popentity-qzz-loaded="handleQzzLoaded"
  >
  </tl-entity-select>
</template>

<script>
  export default {
    data () {
      return {
        value1: '',
        data: [
          { dxid: 1570001118, dxmc: '江苏电力', switch: true },
          { dxid: 1570001098, dxmc: '湖北电力', switch: true },
          { dxid: 1570001113, dxmc: '大宇物流', switch: false },
          { dxid: 1570001115, dxmc: '风尖', switch: false },
          { dxid: 1570001114, dxmc: '威然', switch: true },
          { dxid: 1570001121, dxmc: '喆大电力', switch: false }
        ],
        treeData: [
          { bm: '81040001', name: '单位分类', id: 1 },
          { bm: '810400010001', name: '系统内单位', id: 2 },
          { bm: '81040002', name: '系统外单位', id: 3 }
        ],
        colData: [
          { label: 'id', prop: 'dxid' },
          { label: '名称', prop: 'dxmc', selected: true }
        ],
      }
    },
    methods: {
      handleQzzLoaded(qzz) {
        console.log(qzz);
      }
    }
  };
</script>
```
:::

### 标题切换

可以将标题配为对象，实现标题切换功能，用 `title-change` 事件接收选中标题。
:::demo 可用于通用实体选择的数据切换。
```html
<template>
  <tl-entity-select
    v-model="value"
    :data="data"
    :table-data="data"
    :tree-data="treeData"
    :col-data="colData"
    id-field="dxid"
    text-field="dxmc"
    tree-id-field="id"
    tree-text-field="name"
    tree-code-field="bm"
    tree-code-policy="4-4-4-4-4-4-4-4-4-4"
    :tree-field="{parent: 'bm', children: 'groupId'}"
    :dialog-title="dialogTitle"
    @title-change="handleTitleChange"
  >
  </tl-entity-select>
</template>

<script>
  export default {
    data () {
      return {
        value: '',
        data: [
          { dxid: 1570001118, dxmc: '江苏电力11111111111111111111111111111111' },
          { dxid: 1570001098, dxmc: '湖北电力' },
          { dxid: 1570001113, dxmc: '大宇物流' },
          { dxid: 1570001115, dxmc: '风尖' },
          { dxid: 1570001114, dxmc: '威然' },
          { dxid: 1570001121, dxmc: '喆大电力' }
        ],
        treeData: [
          { bm: '81040001', name: '单位分类', id: 1 },
          { bm: '810400010001', name: '系统内单位', id: 2 },
          { bm: '81040002', name: '系统外单位', id: 3 }
        ],
        colData: [
          { label: 'id', prop: 'dxid' },
          { label: '名称', prop: 'dxmc', selected: true }
        ],
        dialogTitle: {
          one: {
            text: '选项一',
          },
          two: {
            text: '选项二'
          },
          three: {
            text: '选项三',
            disabled: true
          }
        }
      }
    },
    methods: {
      handleTitleChange(name, item) {
        alert(`切换至${item.text}！`);
        console.log(name, item);
      }
    }
  };
</script>
```
:::

### 高级查询

可以通过配置参数 `show-advanced-query` 开启高级查询，通过 `advanced-query-col-data` 配置高级查询可选列，通过 `popentity-advanced-query` 配置高级查询事件，高级查询需结合服务端查询使用。
:::demo 高级查询需结合服务端查询使用，所以请通过 `popentity-advanced-query` 事件自行取数，可通过 `default-advanced-query-col` 设置默认筛选列。
```html
<template>
  <tl-entity-select
    multiple
    show-advanced-query
    :advanced-query-col-data="[
      { prop:'option1', label: '选项1' },
      { prop:'option2', label: '选项2' },
      { prop:'option3', label: '选项3' },
      { prop:'option4', label: '选项4' }
    ]"
    :default-advanced-query-col="[
      { prop:'option1', label: '选项1' },
      { prop:'option2', label: '选项2' }
    ]"
    v-model="value"
    use-qzz-grid
    :table-options="{
      pager: true
    }"
    :data="data"
    :table-data="data"
    :tree-data="treeData"
    :col-data="colData"
    id-field="dxid"
    text-field="dxmc"
    tree-id-field="id"
    tree-text-field="name"
    tree-code-field="bm"
    tree-code-policy="4-4-4-4-4-4-4-4-4-4"
    :tree-field="{parent: 'bm', children: 'groupId'}"
    @popentity-advanced-query="handleAdvancedQuery"
  >
  </tl-entity-select>
</template>

<script>
  export default {
    data () {
      return {
        value: '',
        data: [
          { dxid: 1570001118, dxmc: '江苏电力11111111111111111111111111111111' },
          { dxid: 1570001098, dxmc: '湖北电力' },
          { dxid: 1570001113, dxmc: '大宇物流' },
          { dxid: 1570001115, dxmc: '风尖' },
          { dxid: 1570001114, dxmc: '威然' },
          { dxid: 1570001121, dxmc: '喆大电力' }
        ],
        treeData: [
          { bm: '81040001', name: '单位分类', id: 1 },
          { bm: '810400010001', name: '系统内单位', id: 2 },
          { bm: '81040002', name: '系统外单位', id: 3 }
        ],
        colData: [
          { label: 'id', prop: 'dxid' },
          { label: '名称', prop: 'dxmc', selected: true }
        ]
      }
    },
    methods: {
      handleAdvancedQuery(val) {
        console.log(val);
      }
    }
  };
</script>
```
:::

### 在表格中使用
EntitySelect 还可以以服务的方式调用。放入 EntitySelect 组件在表格列的 `editType` 中：
```javascript
import { SelectEditor } from 'yjpl-ui';
```
组件属性在列设置中传入。


:::tip
该组件基于
<router-link :to="{ path: '/components/form/select' }">tl-select</router-link>
二次封装，属性事件用法相同，下方只列举该组件特有属性。

:::

### EntitySelect Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| value / v-model | 绑定值 | boolean / string / number | — | — |
| data | 下拉数据，必传项，具体看下表 | array | — | - |
| tree-data | 树数据 | array | — | - |
| table-data | 表格数据 | array | — | - |
| use-qzz-grid | 是否使用 `qzz` 表格 | boolean | — | false |
| use-selected-list | 是否使用已选列表，当数据为异步取时，表格数据不一定包含已选数据，请按情况关闭 | boolean | — | true |
| col-data | 表格列属性，详细参见下表 | array | — | - |
| tree-options | 树属性配置，详细参考 `tl-tree` 属性配置 | object | — | - |
| table-options | 表格属性配置，如果开启 `use-qzz-grid`，则为 `QZZ` 表格配置，否则参考 `el-table` 属性配置 | object | — | - |
| select-parent | 树是否可选择父节点 | boolean | — | true |
| return-type | 返回类型，object 为整条数据，string 为间隔符字符串 | boolean | object | string | - |
| multiple | 是否多选 | boolean | — | false |
| dialog-title | 弹出窗标题 | string | — | - |
| id-field | 作为id的字段 | string | — | - |
| text-field | 作为显示的字段 | string | — | - |
| tree-id-field | 树的id标识 | string | — | - |
| tree-text-field | 树的显示文字标识 | string | — | - |
| tree-code-field | 树结构化标识 | string | — | - |
| tree-code-policy | 树结构化编码 例： 4-4-4-4-4-4-4 | string | — | - |
| tree-field | 构建树表关联时使用，详细参见下表 | object | boolea— | - |
| tree-default-expand-all | 是否默认展开所有节点 | boolean | — | false |
| search | 是否前端搜索，服务端搜索时请禁用 | boolean | — | true |
| tree-locate | 是否使用树定位，需要类型为 `tree` | boolean | - | false |
| grid-locate | 是否使用表格定位，需要类型为 `table` 或 `treeTable`，且开启 `use-qzz-grid` | boolean | - | false |
| page-sizes | 每页行数数组 | array | — | [100, 200, 300, 400] |
| page-size | 每页行数 | number | — | 100 |
| total | 总页数 | number | — | - |
| async-open ^(8.5.0) | 异步打开弹窗回调，参数为控制弹窗显示的方法 | function(show) | — | - |
| on-confirm-close | 点击确定后的钩子，如果返回 false 则不会关闭 | function(data) | — | - |
| show-advanced-query | 是否使用高级查询 | boolean | — | false |
| advanced-query-col-data | 高级查询列筛选设置，与 `col-data` 相同，详细参见下表 | array | — | - |
| default-advanced-query-col | 默认显示筛选列，值为 `advanced-query-col-data` 中的 `prop` 属性合集 | array | — | - |

### data
| 参数      | 说明          | 类型      | 可选值 | 默认值  |
|---------- |-------------- |---------- |-  |-------- |
| disabled | 是否禁用 | boolean | — | false |

### col-data / advanced-query-col-data
| 参数      | 说明          | 类型      | 可选值 | 默认值  |
|---------- |-------------- |---------- |-  |-------- |
| label | 列名 | string | — | - |
| prop | 对应数据的id | string | — | - |
| width | 表格列宽度 | string | — | auto |
| selected | 上方筛选条件使用，筛选列是否默认选中该列 | boolean | — | - |
| filter | 上方筛选条件使用，是否出现在筛选条件内 | boolean | — | true |
| data | 上方筛选条件使用，id名称转换的数据 | string | — | - |
| link | 是否显示为链接类型 | boolean | — | false |
| format | 格式化方法，返回的值为单元格渲染内容 | function(data) | — | - |

### tree-field
| 参数      | 说明          | 类型      | 可选值 | 默认值  |
|---------- |-------------- |---------- |-  |- |
| parent | 树关联表的id | string | — | - |
| children | 表关联树的id | string | — | - |

### EntitySelect Events
| 事件名称 | 说明 | 回调参数 |
|---------|---------|---------|
| tree-select | 树选中值发生变化时触发 | treeNode, tableFilter, pageInfo |
| page-change | 分页发生变化时触发 | treeNode, tableFilter, pageInfo |
| size-change | pageSize 改变时会触发 | treeNode, tableFilter, pageInfo |
| table-link-click | 单元格为 `link` 时点击会触发 | row |
| popentity-search | 弹出窗表格搜索时触发 | treeNode, tableFilter, pageInfo |
| popentity-confirm | 弹出窗点击确定时触发 | 目前的选中值 |
| popentity-cancel | 弹出窗点击取消时触发 | - |
| popentity-opened | 弹出窗开启触发 | popentity |
| popentity-close | 弹出窗关闭时触发 | - |
| popentity-qzz-loaded | 弹出窗内 `qzz` 渲染完毕触发 | qzz |
| popentity-advanced-query | 高级查询触发事件 | form |

### EntitySelect Slots
|   name  | 说明     |
|---------|---------|
|    —    | 下拉列表格式化 |
| title   | 弹出窗标题 |
| prefix  | Select 组件头部内容 |
| empty   | 无选项时的列表 |
| tree-search | 树搜索区域 |
| table-search | 表格搜索区域 |
| table-column | 表格自定义列 |
