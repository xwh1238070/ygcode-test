## Card 卡片

"卡片"是一种交互设计模块，把相关信息集合在一个尺寸灵活的容器里，视觉上看起来像一张卡牌。

### 基础用法

包含标题、内容、操作按钮

:::demo Card 组件包括`header`和`body`两部分，`header`需要有显式具名 slot 分发，同时也是可选的

```html
<template>
  <tl-card class="box-card">
    <template #header>
      <div class="header">
        <span class="title">标题</span>
        <tl-button type="text">操作按钮</tl-button>
      </div>
    </template>
    <div class="content">
      <div v-for="item in 4" :key="item" class="card-item">{{`列表内容${item}`}}</div>
    </div>
  </tl-card>
</template>

<style>
  .box-card .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 8px;
  }
  .box-card .title {
    font-size: 16px;
  }
  .box-card .content {
    background: #f7f9fb;
    padding: 18px;
  }
  .box-card .content .card-item {
    margin-bottom: 18px;
    font-size: 14px;
  }
  .box-card .content .card-item:last-child {
    margin-bottom: 0;
  }
</style>
```

:::

### 只有内容区域

:::demo 内容区域为默认插槽，不设置`header`具名插槽即可

```html
<template>
  <tl-card class="box-card">
    <div class="content">
      <div v-for="item in 4" :key="item" class="card-item">{{`列表内容${item}`}}</div>
    </div>
  </tl-card>
</template>

<style>
  .box-card .content {
    background: #f7f9fb;
    padding: 18px;
  }
  
  .box-card .card-item {
    margin-bottom: 18px;
    font-size: 14px;
  }

  .box-card .card-item:last-child {
    margin-bottom: 0;
  }
</style>
```

:::

### 带选项卡

:::demo 包含选项卡、内容和操作按钮。操作按钮部分支持显式具名`slot`分发，内容区支持显式具名`slot`分发

```html
<template>
  <tl-card class="box-card">
    <template #header>
      <div class="header">
        <tl-tabs v-model="activeName" class="header--tabs">
          <tl-tab-pane label="待办事项" name="待办事项"></tl-tab-pane>
          <tl-tab-pane label="已办事项" name="已办事项"></tl-tab-pane>
        </tl-tabs>
        <tl-button type="text">操作按钮</tl-button>
      </div>
    </template>
    <div class="content">
      <div v-for="item in 4" :key="item" class="card-item">{{`${activeName}列表内容${item}`}}</div>
    </div>
  </tl-card>
</template>

<script>
  export default {
    data() {
      return {
        activeName: '待办事项'
      };
    }
  };
</script>

<style>
  .box-card {
    width: 80%;
  }

  .box-card .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    margin-bottom: 8px;
  }
  
  .box-card .header .header--tabs {
    margin-right: 16px;
    flex: auto;
  }

  .box-card .content {
    background: #f7f9fb;
    padding: 18px;
  }

  .box-card .content .card-item {
    margin-bottom: 18px;
    font-size: 14px;
  }

  .box-card .content .card-item:last-child {
    margin-bottom: 0;
  }
</style>
```

:::

### 带图片

可配置定义更丰富的内容展示。

:::demo `body`部分设置为图片,可以通过`padding`属性设置卡片内容间距

```html
<template>
  <tl-row>
    <tl-col :span="10" v-for="(item, index) in 2" :key="item" :offset="index > 0 ? 2 : 0">
      <tl-card :padding="index > 0 ? 12 : 0" class="box-card">
        <img src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg" class="card-image" />
        <div class="card-bottom" :style="{'padding': (index > 0 ? '15px 0 0 0' : '15px')}">
          <span class="bottom__title" title="标题名称标题名称标题名称标题名称">标题名称标题名称标题名称标题名称</span>
          <div class="bottom--right">
            <i class="el-icon-view"></i>
            <span>260</span>
          </div>
        </div>
      </tl-card>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {};
</script>

<style>
  .box-card img {
    width: 100%;
  }
</style>
```

:::

### 卡片阴影

可对阴影的显示时机进行配置。

:::demo 通过 shadow 属性设置卡片阴影出现的时机：`always`、`hover`或`never`。

```html
<template>
  <tl-row :gutter="12">
    <tl-col :span="8">
      <tl-card shadow="always" border="1px solid #EBEEF5">总是显示</tl-card>
    </tl-col>
    <tl-col :span="8">
      <tl-card shadow="hover" border="1px solid #EBEEF5">鼠标悬浮时显示</tl-card>
    </tl-col>
    <tl-col :span="8">
      <tl-card shadow="never" border="1px solid #EBEEF5">从不显示</tl-card>
    </tl-col>
  </tl-row>
</template>
```

:::

### Attributes

| 参数       | 说明             | 类型   | 可选值                 | 默认值 |
| ---------- | ---------------- | ------ | ---------------------- | ------ |
| shadow     | 设置阴影显示时机 | string | always / hover / never | always |
| body-style | 设置 body 的样式 | object | -                      | -      |

### Slot

| 参数   | 说明                                         | 类型   | 可选值 | 默认值 |
| ------ | -------------------------------------------- | ------ | ------ | ------ |
| header | 设置 header，也可以通过 slot#header 传入 DOM | string | -      | -      |
