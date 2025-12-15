# Section 分组色块

作用于分组，添加间隔，以产生明显的区块

### 基础用法

基础的分组色块用法。

:::demo 通过`title`设置分组标题; 也可以使用`slot='title'`自定义标题

```html
<template>
  <div>
    <tl-section class="gap-bottom"></tl-section>
    <tl-section title="我是通过传参设置的标题" class="gap-bottom"></tl-section>
    <tl-section>
      <template #title>我是通过slot设置的标题</template>
    </tl-section>
  </div>
</template>

<style>
  .gap-bottom {
    margin-bottom: 20px;
  }
</style>
```

:::

### 分组色块可以通过匿名插槽展示内容区域

:::demo

```html
<template>
  <tl-section title="标题">
    <span class="content">默认不具备展开和收缩的能力</span>
  </tl-section>
</template>

<style>
  .content {
    margin-top: 10px;
  }
</style>
```

:::

### 通过属性设置是否具备展开和收缩的能力、是否默认展开还是收缩

:::demo 通过`collapsible`设置是否具备展开收缩的能力，默认为 false ,通过`is-collapse`设置是否默认展开，默认为 true

```html
<template>
  <tl-section title="可自定义展开内容" :collapsible="collapsible" :is-collapse="isCollapse">
    <div class="section-detail">
      <tl-date-picker
        v-model="timeValue"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
      ></tl-date-picker>
      <tl-select v-model="selectValue" placeholder="请选择..." class="select">
        <tl-option v-for="item in selectOptions" :key="item.value" :label="item.label" :value="item.value"></tl-option>
      </tl-select>
    </div>
  </tl-section>
</template>

<script>
  export default {
    data() {
      return {
        collapsible: true,
        isCollapse: false,
        timeValue: '',
        selectValue: '',
        selectOptions: [
          {
            label: '选项1',
            value: '选项1'
          },
          {
            label: '选项2',
            value: '选项2'
          },
          {
            label: '选项3',
            value: '选项3'
          }
        ]
      };
    }
  };
</script>

<style>
  .section-detail {
    margin-top: 10px;
  }
  .section-detail .tl-date-picker {
    width: 350px;
  }
  .select {
    display: block;
    margin-top: 10px;
    width: 350px;
  }
</style>
```

:::

### Attributes

| 参数        | 说明                   | 类型    | 可选值        | 默认值 |
| ----------- | ---------------------- | ------- | ------------- | ------ |
| title       | 标题                   | String  | -             | -      |
| collapsible | 是否有展开和收缩的能力 | Boolean | true \| false | false  |
| is-collapse | 是否默认展开           | Boolean | true \| false | true   |

### Events

| 事件名称        | 说明               | 回调参数                   |
| --------------- | ------------------ | -------------------------- |
| collapse-change | 点击收缩按钮时触发 | 收缩还是显示状态的 bool 值 |

### Slots

| 名称    | 描述       |
| ------- | ---------- |
| default | 内容       |
| title   | 标题的内容 |
