## ErrorPage 错误页

错误页用于向用户展示一系列操作任务的处理结果

### 基本使用

:::demo 错误页组件提供标题、信息、图标属性可供扩展。

```html
<template>
  <tl-error-page :title="title" :message="message"></tl-error-page>
</template>
<script>
  export default {
    data() {
      return {
        title: '404（可以自定义）',
        message: '哎呀，404了，连不上了~哎呀，404了，连不上了~(可以自定义)'
      };
    }
  };
</script>
<style>
  .errorpage-bt {
    padding: 0;
  }
</style>
```

:::

### Attributes

| 参数    | 说明     | 类型   | 可选值 | 默认值 |
| ------- | -------- | ------ | ------ | ------ |
| title   | 标题     | string | —      | —      |
| message | 信息文字 | string |        | —      |
| icon    | 图标     | string |        | —      |
