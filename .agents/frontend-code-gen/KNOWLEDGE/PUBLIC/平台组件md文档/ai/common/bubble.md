## Bubble 对话气泡 ^(8.5.0)
用于聊天的对话气泡。

### 基础用法

基础的气泡用法。

:::demo 通过 `avatar` 设置自定义头像，通过 `placement` 设置位置，提供了 `start`、`end` 两个选项。

```html
<template>
  <yj-bubble placement="end">你好！</yj-bubble>
  <yj-bubble placement="start">你也好！</yj-bubble>
</template>
```
:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| placement | 位置信息 | String | start / end | start |
| content | 聊天内容 | String  | - | - |
| isMarkdown | 是否以 `markdown` 形式展示 | Boolean | - | false |