## Calculator 计算器

计算器用于运算数值

### 基本使用

:::demo 默认不用配置参数

```html
<template>
  <tl-calculator></tl-calculator>
</template>
```

:::

### 弹窗方式打开

:::demo 通过指令方式打开，参数是 tl-dialog 和 tl-calculator 的集合

```html
<template>
  <tl-button @click="open">指令方式弹出计算器</tl-button>
</template>

<script>
  export default {
    name: 'Service',
    methods: {
      open() {
        this.$calculator({
          title: '计算器' // 弹出框的标题
        });
      }
    }
  };
</script>
```

:::

### Attributes

| 参数   | 说明         | 类型   | 可选值 | 默认值 |
| ------ | ------------ | ------ | ------ | ------ |
| errMsg | 错误信息展示 | string | — | — |

### Events

| 事件名称 | 说明         | 回调参数 |
| -------- | ------------ | -------- |
| onError  | 计算错误事件 | - |
| on-error | 计算错误事件 | - |
