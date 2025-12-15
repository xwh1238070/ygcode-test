## Alert 警告

用于页面中展示重要的提示信息。

### 基本用法

页面中的非浮层元素，不会自动消失。

:::demo 组件提供四种主题，由`type`属性指定，默认值为`info`。
```html
<template>
  <tl-alert
    title="成功提示的文案"
    type="success">
  </tl-alert>
  <tl-alert
    title="消息提示的文案"
    type="info">
  </tl-alert>
  <tl-alert
    title="警告提示的文案"
    type="warning">
  </tl-alert>
  <tl-alert
    title="错误提示的文案"
    type="error">
  </tl-alert>
</template>
```
:::

### 主题

Alert 组件提供了两个不同的主题：`light`和`dark`。

:::demo 通过设置`effect`属性来改变主题，默认为`light`。
```html
<template>
  <tl-alert
    title="成功提示的文案"
    type="success"
    effect="dark">
  </tl-alert>
  <tl-alert
    title="消息提示的文案"
    type="info"
    effect="dark">
  </tl-alert>
  <tl-alert
    title="警告提示的文案"
    type="warning"
    effect="dark">
  </tl-alert>
  <tl-alert
    title="错误提示的文案"
    type="error"
    effect="dark">
  </tl-alert>
</template>
```
:::



### 自定义关闭按钮

自定义关闭按钮为文字或其他符号。

:::demo在 Alert 组件中，你可以设置是否可关闭，关闭按钮的文本以及关闭时的回调函数。`closable`属性决定是否可关闭，接受`boolean`，默认为`true`。你可以设置`close-text`属性来代替右侧的关闭图标，注意：`close-text`必须为文本。设置`close`事件来设置关闭时的回调。
```html
<template>
  <tl-alert
    title="不可关闭的 alert"
    type="success"
    :closable="false">
  </tl-alert>
  <tl-alert
    title="自定义 close-text"
    type="info"
    close-text="知道了">
  </tl-alert>
  <tl-alert
    title="设置了回调的 alert"
    type="warning"
    @close="hello">
  </tl-alert>
</template>

<script>
  export default {
    methods: {
      hello() {
        alert('Hello World!');
      }
    }
  }
</script>
```
:::

### 带有 icon

表示某种状态时提升可读性。

:::demo 通过设置`show-icon`属性来显示 Alert 的 icon，这能更有效地向用户展示你的显示意图。
```html
<template>
  <tl-alert
    title="成功提示的文案"
    type="success"
    show-icon>
  </tl-alert>
  <tl-alert
    title="消息提示的文案"
    type="info"
    show-icon>
  </tl-alert>
  <tl-alert
    title="警告提示的文案"
    type="warning"
    show-icon>
  </tl-alert>
  <tl-alert
    title="错误提示的文案"
    type="error"
    show-icon>
  </tl-alert>
  </template>
```
:::

### Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|----------|-------------- |---------- |--------------------------------  |-------- |
| title     | 内容          | string | — | — |
| width     | 宽度          | string | — | 100% |
| type | 主题 | string | success/warning/info/error | info |
| closable | 是否可关闭 | boolean | — | true |
| center | 文字是否居中 | boolean | — | true |
| close-text | 关闭按钮自定义文本 | string | — | — |
| show-icon | 是否显示图标 | boolean | — | false |
| effect | 选择提供的主题 | string | light/dark | light |

### Events
| 事件名称 | 说明 | 回调参数 |
|---------- |-------- |---------- |
| close | 关闭alert时触发的事件 | — |
