## Welcome 欢迎 ^(8.5.0)

清晰传达给用户可实现的意图范围和预期功能。

### 基础用法

基础的欢迎用法。

:::demo 通过 `icon` 设置欢迎图标，可以为字符图标也可以为图片。

```html
<template>
  <yj-welcome
    class="welcome-demo"
    icon="yj-p-robot"
    title="欢迎来到天鸿智能查询系统，您的智能数据分析伙伴！"
    description="我们致力于通过智能化、可视化的方式简化您的数据探索过程，帮助您快速准确地获取所需信息，支持业务决策。我们的系统集成了先进的人工智能技术，提供了前所未有的便捷查询体验。">
  </yj-welcome>
</template>

<style>
  .welcome-demo .yj-p-robot {
    text-shadow: 2px 2px 1px #409eff;
  }
</style>
```
:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| title | 欢迎标题 | String | - | - |
| icon | 欢迎图标 | String | - | - |
| description | 欢迎描述信息 | String | - | - |