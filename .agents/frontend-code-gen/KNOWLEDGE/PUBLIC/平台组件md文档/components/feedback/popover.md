## Popover 浮动窗

浮动窗用于鼠标 hover 或点击时展示提示信息。

### 基本使用

`Popover` 的属性与 `Tooltip` 很类似，它们都是基于 `Vue-popper` 开发的，因此对于重复属性，请参考 `Tooltip` 的文档，在此文档中不做详尽解释。

::: demo `trigger` 属性用于设置何时触发 `Popover` ，支持四种触发方式： `hover` ， `click` ， `focus` 和 `manual` 。对于触发 `Popover` 的元素，有两种写法：使用 `slot="reference"` 的具名插槽，或使用自定义指令 `v-popover` 指向 `Popover` 的索引 `ref` 。

```html
<template>
  <div>
    <tl-popover
      placement="top-start"
      title="标题"
      width="200px"
      trigger="hover"
      content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
    >
      <tl-button slot="reference">hover 激活</tl-button>
    </tl-popover>

    <tl-popover
      placement="bottom"
      title="标题"
      width="200px"
      trigger="click"
      content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
    >
      <tl-button slot="reference">click 激活</tl-button>
    </tl-popover>

    <tl-popover
      ref="popover"
      placement="right"
      title="标题"
      width="200px"
      trigger="focus"
      content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
    >
    </tl-popover>
    <tl-button v-popover:popover>focus 激活</tl-button>

    <tl-popover
      placement="bottom"
      title="标题"
      width="200px"
      trigger="manual"
      content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
      v-model="visible"
    >
      <tl-button slot="reference" @click="visible = !visible">手动激活</tl-button>
    </tl-popover>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        visible: false
      };
    }
  };
</script>
```

:::

### 嵌套信息

可以在 `Popover` 中嵌套多种类型信息，以下为嵌套表格的例子。

::: demo

```html
<template>
  <div>
    <tl-popover placement="right" width="400px" trigger="click">
      <template slot="content">
        <tl-form>
          <tl-row :gutter="20">
            <tl-col :span="12">
              <tl-form-item label="文本标签1：">
                <tl-input v-model="value1"></tl-input>
              </tl-form-item>
            </tl-col>
            <tl-col :span="12">
              <tl-form-item label="文本标签2：">
                <tl-input v-model="value2"></tl-input>
              </tl-form-item>
            </tl-col>
          </tl-row>
          <tl-row :gutter="20">
            <tl-col :span="12">
              <tl-form-item label="文本标签3：">
                <tl-input v-model="value3"></tl-input>
              </tl-form-item>
            </tl-col>
            <tl-col :span="12">
              <tl-form-item label="文本标签4：">
                <tl-input v-model="value4"></tl-input>
              </tl-form-item>
            </tl-col>
          </tl-row>
        </tl-form>
      </template>
      <tl-button slot="reference">click 激活</tl-button>
    </tl-popover>
  </div>
</template>

<script>
  export default {
    name: 'Popover2',
    data() {
      return {
        gridData: [
          {
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }
        ]
      };
    }
  };
</script>
```

:::

### 嵌套操作

当然，你还可以嵌套操作，这相比 `Dialog` 更为轻量：

::: demo

```html
<template>
  <tl-popover placement="top" width="200px" v-model="visible">
    <p slot="content">这是一段内容这是一段内容确定删除吗？</p>
    <template slot="footer">
      <tl-button @click="visible = false">取消</tl-button>
      <tl-button type="primary" @click="visible = false">确定</tl-button>
    </template>
    <tl-button slot="reference">删除</tl-button>
  </tl-popover>
</template>

<script>
  export default {
    data() {
      return {
        visible: false
      };
    }
  };
</script>
```

:::

### 内容插槽

你可以使用插槽来定制自定义标题、内容、按钮。

::: demo

```html
<template>
  <tl-popover placement="top" width="200px" v-model="visible">
    <p slot="title" style="margin: 0; padding-bottom: 10px; color: blue">我是自定义标题</p>
    <p slot="content" style="margin: 0; padding-bottom: 10px; color: red">这是一段内容这是一段内容确定删除吗？</p>
    <template slot="footer">
      <tl-button @click="visible = false">取消</tl-button>
      <tl-button type="primary" @click="visible = false">确定</tl-button>
    </template>
    <tl-button slot="reference">自定义标题/内容/按钮插槽</tl-button>
  </tl-popover>
</template>

<script>
  export default {
    data() {
      return {
        visible: false
      };
    }
  };
</script>
```

:::

### Attributes

| 参数 | 说明 | 类型    | 可选值 | 默认值 |
| - | - | - | - | - |
| rigger | 触发方式 | String  | click/focus/hover/manual | click |
| title | 标题 | String  | —  | — |
| content | 显示的内容，也可以通过 slot 传入 DOM | String | — | — |
| width | 宽度 | String | — | 最小宽度 150px |
| placement | 出现位置 | String | top/top-start/top-end/bottom/bottom-start/bottom-end/left/left-start/left-end/right/right-start/right-end | bottom |
| disabled | Popover 是否可用| Boolean | — | false|
| value / v-model | 状态是否可见 | Boolean | — | false|
| offset | 出现位置的偏移量 | Number | — | 0 |
| transition | 定义渐变动画 | String | — | fade-in-linear |
| visible-arrow | 是否显示 Tooltip 箭头，更多参数可见 Vue-popper | Boolean | — | true |
| popper-options | popper.js 的参数 | Object | 参考 popper.js 文档| { boundariesElement: 'body', gpuAcceleration: false } |
| popper-class | 为 popper 添加类名 | String | — | —|
| open-delay | 触发方式为 hover 时的显示延迟，单位为毫秒 | Number | — | — |
| close-delay | 触发方式为 hover 时的隐藏延迟，单位为毫秒 | number | — | 200 |
| tabindex | Popover 组件的 tabindex | number | — | 0 |

### Slots

| 名称      | 说明                          |
| --------- | ----------------------------- |
| reference | 触发 Popover 显示的 HTML 元素 |
| title     | Popover 显示的 标题元素       |
| content   | Popover 显示的 内容元素       |
| footer    | Popover 显示的 底部容器元素   |

### Events

| 事件名称    | 说明                   | 回调参数 |
| ----------- | ---------------------- | -------- |
| show        | 显示时触发             | -        |
| after-enter | 显示动画播放完毕后触发 | -        |
| hide        | 隐藏时触发             | -        |
| after-leave | 隐藏动画播放完毕后触发 | -        |
