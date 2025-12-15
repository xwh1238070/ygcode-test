## Select 选择器

当选项过多时，使用下拉菜单展示并选择内容。

### 基础用法

适用广泛的基础单选和多选
:::demo `v-model`的值为当前被选中的 value 属性值`id-field`为`data`的 id 标识`text-field`为`data`的显示标识
```html
<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value1"
      :data="data"
      id-field="id"
      text-field="text"
      :clearable="false"
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      multiple
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
  </tl-col>
</tl-row>

<script>
export default {
  data () {
    return {
      value1: '',
      value2: [],
      value3: [],
      data: [
        { id: 1, text: '江苏电力' },
        { id: 2, text: '湖北电力' },
        { id: 3, text: '大宇物流' },
        { id: 4, text: '风尖' },
        { id: 5, text: '威然' },
        { id: 6, text: '喆大电力' }
      ],
      data2: []
    }
  }
}
</script>
```
:::

### 禁用状态

选择器不可用状态
:::demo 为`select`设置`disabled`属性，则整个选择器不可用
```html
<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value"
      disabled
      :data="data"
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
</tl-row> 

<script>
  export default {
    data () {
      return {
        value: '',
        data: [
          { id: 1, text: '江苏电力' },
          { id: 2, text: '湖北电力' },
          { id: 3, text: '大宇物流' },
          { id: 4, text: '风尖' },
          { id: 5, text: '威然' },
          { id: 6, text: '喆大电力' }
        ]
      }
    }
  }
</script>
```
:::

### 显示更多按钮

可以显示点击更多按钮

:::demo 为 `select` 添加 `show-more` 属性即可显示更多按钮。可通过 `more` 事件触发点击后事件。
```html
<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value"
      show-more
      :data="data"
      id-field="id"
      text-field="text"
      @more="handleMore"
    >
    </tl-select>
  </tl-col>
</tl-row> 

<script>
  export default {
    data () {
      return {
        value: '',
        data: [
          { id: 1, text: '江苏电力' },
          { id: 2, text: '湖北电力' },
          { id: 3, text: '大宇物流' },
          { id: 4, text: '风尖' },
          { id: 5, text: '威然' },
          { id: 6, text: '喆大电力' }
        ]
      }
    },
    methods: {
      handleMore() {
        alert()
      }
    }
  }
</script>
```
:::

### 可搜索

可以利用搜索功能快速查找选项

:::demo 为`select`添加`filterable`属性即可启用搜索功能。默认情况下，Select 会找出所有`label`属性包含输入值的选项。如果希望使用其他的搜索逻辑，可以通过传入一个`filter-method`来实现。`filter-method`为一个`Function`，它会在输入值发生变化时调用，参数为当前输入值。
```html
<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value"
      filterable
      :data="data"
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
</tl-row> 

<script>
  export default {
    data () {
      return {
        value: '',
        data: [
          { id: 1, text: '江苏电力' },
          { id: 2, text: '湖北电力' },
          { id: 3, text: '大宇物流' },
          { id: 4, text: '风尖' },
          { id: 5, text: '威然' },
          { id: 6, text: '喆大电力' }
        ]
      }
    }
  }
</script>
```
:::

### 自定义模板

可以自定义备选项

:::demo 将自定义的 HTML 模板插入 slot 中即可。
```html
<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value"
      :data="data"
      id-field="id"
      text-field="text"
    >
      <template slot-scope="scope">
        <span style="float: left">{{ scope.data.text }}</span>
        <span style="float: right; color: ##8492a6; font-size: 13px">{{ scope.data.locate }}</span>
      </template>
    </tl-select>
  </tl-col>
</tl-row> 

<script>
export default {
  data () {
    return {
      value: '',
      data: [
        { id: 1, text: '江苏电力', locate: '江苏' },
        { id: 2, text: '湖北电力', locate: '湖北' }
      ]
    }
  }
}
</script>
```
:::

### 尺寸

配置选择框尺寸
:::demo `v-model`的值为当前被选中的 value 属性值`id-field`为`data`的 id 标识`text-field`为`data`的显示标识
```html
<tl-row :gutter="20">
  <tl-col :span="3">
    <tl-select
      v-model="value1"
      :data="data"
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      size="large"
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      size="medium"
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      size="small"
      id-field="id"
      text-field="text"
    >
    </tl-select>
  </tl-col>
</tl-row>


<script>
export default {
  data () {
    return {
      value1: '',
      value2: [],
      data: [
        { id: 1, text: '江苏电力江苏电力江苏电力江苏电力江苏电力江苏电力' },
        { id: 2, text: '湖北电力' },
        { id: 3, text: '大宇物流' },
        { id: 4, text: '风尖' },
        { id: 5, text: '威然' },
        { id: 6, text: '喆大电力' }
      ]
    }
  }
}
</script>
```
:::

### 创建条目
可以创建并选中选项中不存在的条目
:::demo 使用`allow-create`属性即可通过在输入框中输入文字来创建新的条目。注意此时`filterable`必须为真。本例还使用了`default-first-option`属性，在该属性打开的情况下，按下回车就可以选中当前选项列表中的第一个选项，无需使用鼠标或键盘方向键进行定位。
```html
<template>
  <tl-select
    v-model="value"
    multiple
    filterable
    allow-create
    default-first-option
    placeholder="请选择文章标签"
    :data="options"
    id-field="value"
    text-field="label"
    @input="handleInput"
    >
  </tl-select>
</template>

<script>
  export default {
    data() {
      return {
        options: [{
          value: 'HTML',
          label: 'HTML'
        }, {
          value: 'CSS',
          label: 'CSS'
        }, {
          value: 'JavaScript',
          label: 'JavaScript'
        }],
        value: []
      }
    },
    methods: {
      handleInput(val) {
        console.log(val);
      }
    }
  }
</script>
```
:::

### 多种形态

为了丰富界面元素，下拉选择也提供了各种丰富的样式形态。
:::demo 可以通过属性开启各种形态。`show-avatar` 开启后，会以选项末尾字符作为头像显示。`show-option-list` 开启后会显示项目符号，可设置选项的 `color` 修改其颜色。`show-icon` 开启后可显示图标，需要设置选项的 `icon` 为显示图标。设置 `use-tag-option` 后，可以 `tag` 的形式显示选项，可设置 `color` 为其主色。
```html
<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value1"
      :data="data"
      id-field="id"
      text-field="text"
      :clearable="false"
      show-avatar
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      multiple
      id-field="id"
      text-field="text"
      show-avatar
    >
    </tl-select>
  </tl-col>
</tl-row>

<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value1"
      :data="data"
      id-field="id"
      text-field="text"
      :clearable="false"
      show-option-list
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      multiple
      id-field="id"
      text-field="text"
      show-option-list
    >
    </tl-select>
  </tl-col>
</tl-row>

<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value1"
      :data="data"
      id-field="id"
      text-field="text"
      :clearable="false"
      show-icon
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      multiple
      id-field="id"
      text-field="text"
      show-icon
    >
    </tl-select>
  </tl-col>
</tl-row>

<tl-row :gutter="20">
  <tl-col :span="6">
    <tl-select
      v-model="value1"
      :data="data"
      id-field="id"
      text-field="text"
      :clearable="false"
      use-tag-option
    >
    </tl-select>
  </tl-col>
  <tl-col :span="6">
    <tl-select
      v-model="value2"
      :data="data"
      multiple
      id-field="id"
      text-field="text"
      use-tag-option
    >
    </tl-select>
  </tl-col>
</tl-row>

<script>
export default {
  data () {
    return {
      value1: '',
      value2: [],
      value3: [],
      data: [
        { id: 1, text: '江苏电力', color: '#6831D8', icon: 'yj-p-wendang' },
        { id: 2, text: '湖北电力', color: '#06778D', icon: 'yj-p-calculator' },
        { id: 3, text: '大宇物流', color: '#E89B26', icon: 'yj-p-pc' },
        { id: 4, text: '风尖', color: '#BE418C', icon: 'yj-p-detailed-info' },
        { id: 5, text: '威然', color: '#1875F0', icon: 'yj-p-calendar' },
        { id: 6, text: '喆大电力' }
      ],
      data2: []
    }
  }
}
</script>

<style>
  .el-row + .el-row {
    margin-top: 8px;
  }
</style>
```
:::

### Select Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| value / v-model | 绑定值 | boolean / string / number | — | — |
| data | 下拉数据，必传项，具体看下表 | array | — | - |
| id-field | 作为id的字段 | string | — | - |
| text-field | 作为显示的字段 | string | — | - |
| return-type | 返回类型，object 为整条数据，string 为间隔符字符串 | boolean | object | string | - |
| show-more | 显示更多按钮 | boolean | — | false |
| multiple | 是否多选 | boolean | — | false |
| disabled | 是否禁用 | boolean | — | false |
| hide-picker | 隐藏下拉框 | boolean | — | false |
| history | 开启历史记录模式，隐藏 `data` 内真实数据，显示 `history-data` 内数据 | boolean | — | false |
| history-data | 配合历史记录使用 | array | — | [] |
| option-reverse-ellipsis | 下拉选项反向省略 | boolean | — | false |
| size | 输入框尺寸 | string | medium/small/mini | — |
| clearable | 是否可以清空选项 | boolean | — | true |
| multiple-limit | 多选时用户最多可以选择的项目数，为 0 则不限制 | number | — | 0 |
| name | select input 的 name 属性 | string | — | — |
| autocomplete | select input 的 autocomplete 属性 | string | — | off |
| placeholder | 占位符 | string | — | 请选择 |
| filterable | 是否可搜索 | boolean | — | false |
| allow-create | 是否允许用户创建新条目，需配合 `filterable` 使用 | boolean | — | false |
| filter-method | 自定义搜索方法 | function | — | — |
| remote | 是否为远程搜索 | boolean | — | false |
| remote-method | 远程搜索方法 | function | — | — |
| loading | 是否正在从远程获取数据 | boolean | — | false |
| loading-text | 远程加载时显示的文字 | string | — | 加载中 |
| no-match-text | 搜索条件无匹配时显示的文字，也可以使用`slot="empty"`设置 | string | — | 无匹配数据 |
| no-data-text | 选项为空时显示的文字，也可以使用`slot="empty"`设置 | string | — | 无数据 |
| popper-class | Select 下拉框的类名 | string | — | — |
| reserve-keyword | 多选且可搜索时，是否在选中一个选项后保留当前的搜索关键词 | boolean | — | false |
| default-first-option | 在输入框按下回车，选择第一个匹配项。需配合 `filterable` 或 `remote` 使用 | boolean | - | false |
| popper-append-to-body | 是否将弹出框插入至 body 元素。在弹出框的定位出现问题时，可将该属性设置为 false | boolean | - | true |
| automatic-dropdown | 对于不可搜索的 Select，是否在输入框获得焦点后自动弹出选项菜单 | boolean | - | false |

### data
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| disabled | 是否禁用 | boolean | — | false |
| color | 文字/图标颜色 | string | — | - |
| icon | 图标 | string | — | - |

### Select Events
| 事件名称 | 说明 | 回调参数 |
|---------|---------|---------|
| change | 选中值发生变化时触发 | 目前的选中值 |
| visible-change | 下拉框出现/隐藏时触发 | 出现则为 true，隐藏则为 false |
| remove-tag | 多选模式下移除tag时触发 | 移除的tag值 |
| clear | 可清空的单选模式下用户点击清空按钮时触发 | — |
| blur | 当 input 失去焦点时触发 | (event: Event) |
| focus | 当 input 获得焦点时触发 | (event: Event) |
| more | 点击更多按钮时触发 | 目前的选中值 |

### Select Slots
|   name  | 说明     |
|---------|---------|
| - | 下拉数据，配合 `option` 使用 |
| option | 下拉列表格式化 |
| prefix  | Select 组件头部内容 |
| empty | 无选项时的列表 |


### Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| focus | 使 input 获取焦点 | - |
| blur | 使 input 失去焦点，并隐藏下拉框 | - |
