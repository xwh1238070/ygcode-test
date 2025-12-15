# Calendar 日历面板

日历面板为一种通用的日历查看及选择控件，旨在支持各种查询类型及展示类页面。根据业务需求配置使用。

### 基本使用
自动获取当前时间高亮，通过 `v-mode` 双向绑定可以进行切换日期的绑定

:::demo Calendar1

```html
<template>
  <tl-calendar v-model="date"></tl-calendar>
</template>
<script>
export default {
  data() {
    return {
      date: new Date(),
    };
  },
};
</script>
```

:::


### 通过插槽自定义
通过插槽添加自定义内容。

:::demo 可以通过插槽 `item` 添加自定义内容，插槽返回2个内容， `cell` 代表当前格子内容, `isSelected` 代表当前是否选中

```html
<template>
  <tl-calendar v-model="date">
    <template #item="{ cell, isSelected, date }">
      <div :style="{ 'background-color': isSelected ? 'skyblue' : '', 'color': isSelected ? '#fff' : '' }">
        {{cell}}<i class="el-icon-check" v-if="isSelected" />
      </div>
    </template>
  </tl-calendar>
</template>

<script>
export default {
  data() {
    return {
      date: new Date(),
    };
  },
};
</script>
```
:::

### 非棋盘格及无阴影样式
通过参数设置日历面板棋盘格样式和无阴影样式。

:::demo 通过使用参数 `stripe` 、 `shadow` 分别设置日历面板棋盘格样式和无阴影样式。

```html
<template>
  <tl-calendar v-model="date" :stripe="false"></tl-calendar>
  <tl-calendar v-model="date" :shadow="false"></tl-calendar>
</template>

<script>
export default {
  data() {
    return {
      date: new Date(),
    };
  },
};
</script>
```
:::


### 自定义字体和背景色
设置自定义日历面板的字体和背景颜色

:::demo 通过使用参数 `textColor` 、 `backgroundColor` 分别设置日历面板的字体和背景颜色

```html
<template>
  <tl-calendar
    v-model="date"
    :background-color="backgroundColor"
    :text-color="textColor"
  >
  </tl-calendar>
</template>

<script>
export default {
  data() {
    return {
      textColor: "#EDA915",
      backgroundColor: "#409EFF",
      date: new Date(),
    };
  },
};
</script>
```
:::
### Calendar Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| stripe   | 棋盘格样式          | boolean | true/false          |true     |
| shadow   | 面板是否显示阴影 | boolean | true/false              | true      |
| text-color | 高亮日期的字体颜色 | String | -                     | '#FFFFFF      |
| background-color | 高亮日期的背景颜色 | String | - | '5DB58D'     |

### Calendar Slots

| 名称     | 说明                                              |
| -------- | ------------------------------------------------- |
| item   | 自定日历面板内容插槽 |

### Calendar Attributes

| 参数         | 说明                   | 类型   | 可选值               | 默认值 |
| ------------ | ---------------------- | ------ | -------------------- | ------ |
| cell          | 日期单元格数据 | String  | —                    | ''     |
| isSelected | 当前激活的日期单元格  | Boolean | true/false    | -     |
| date | 当前日期单元格日期(day=>时间格式，month=>月份，year=>年份)  | date/number | ----    | -     |


### Calendar Events

| 事件名称      | 说明                           | 回调参数     |
| ------------- | ------------------------------ | ------------ |
| select        | 当前激活/点击的日期改变时发出的事件 | selectedDate |
