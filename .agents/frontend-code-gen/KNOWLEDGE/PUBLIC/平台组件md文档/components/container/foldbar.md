## Foldbar 侧边栏

侧边栏是一种页面内可展开可收缩的侧边布局，旨在用于可收缩，可拖拽布局等页面。

该组件并没有包括外层容器，请先确认使用场景，再进行样式定义。


### 基础用法

可调整尺寸可以拖拽，需设置侧边栏默认宽度。

:::demo 

```html
<template>
  <div class="demo-foldbar section">
    <div class="example">
      <div class="main">
        <tl-foldbar ref="fold" title="内容标题" :width="300">
          <tl-select-list
            v-model="value"
            search-bar
            type="checkbox"
            :data="data.data"
          ></tl-select-list>
        </tl-foldbar>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        value: 1,
        data: [
          {
            text: "选项1",
            value: 1
          },
          {
            text: "选项2选项2选项2选项2选项2选项2选项2选项2选项2",
            value: 2
          },
          {
            text: "选项3",
            value: 3
          },
          {
            text: "选项4",
            value: 4
          },
          {
            text: "选项5",
            value: 5
          },
          {
            text: "选项6",
            value: 6
          }
        ]
      };
    }
  };
</script>

<style>
  .section .example {
    height: 300px;
  }

  .main {
    display: flex;
    width: 100%;
    height: 100%;
    justify-content: flex-end;
  }
</style>
```

:::

### 固定尺寸

固定尺寸不能拖拽，需设置侧边栏默认宽度。

:::demo 

```html
<template>
  <div class="demo-foldbar section">
    <div class="example">
      <div class="main">
        <div class="content">
          <tl-button @click="openFlodBar">打开/关闭侧边栏</tl-button>
        </div>
        <tl-foldbar
          ref="fold"
          title="内容标题"
          out-close
          show-close
          :collapse.sync="isCollapse"
          :draggable="false"
          :width="300">
          <tl-select-list
            v-model="value"
            search-bar
            type="checkbox"
            :data="data.data"
          ></tl-select-list>
        </tl-foldbar>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        isCollapse: true,
        value: 1,
        data: [
          {
            text: "选项1",
            value: 1
          },
          {
            text: "选项2选项2选项2选项2选项2选项2选项2选项2选项2",
            value: 2
          },
          {
            text: "选项3",
            value: 3
          },
          {
            text: "选项4",
            value: 4
          },
          {
            text: "选项5",
            value: 5
          },
          {
            text: "选项6",
            value: 6
          }
        ]
      };
    },
    methods: {
      openFlodBar() {
        this.isCollapse = !this.isCollapse;
      }
    }
  };
</script>

<style>
  .section .example {
    height: 300px;
  }

  .main {
    display: flex;
    width: 100%;
    height: 100%;
  }

  .demo-foldbar .tl-select-list-container {
    width: 100%;
  }
  
</style>
```

:::

### Attributes

| 参数        | 说明                     | 类型          | 可选值     | 默认值 |
| ----------- | ------------------------ | ------------- | ---------- | ------ |
| title       | 侧边栏标题               | string        | -          | -      |
| position    | 侧边栏位置               | string        | left/right | right  |
| collapse    | 侧边栏是否展开           | boolean       | -          | false  |
| width       | 侧边栏默认宽度           | number/string | -          | 20%    |
| show-close  | 侧边栏右上角关闭按钮     | boolean       | -          | false  |
| out-close   | 侧边栏外置的展开收缩按钮 | boolean       | -          | false  |
| inner-close | 侧边栏内置的展开收缩按钮 | boolean       | -          | false  |
| draggable   | 侧边栏是否可拖拽         | boolean       | -          | true   |

### slot

| 参数   | 说明               |
| ------ | ------------------ |
|        | 侧边栏内容区域     |
| action  | 侧边栏标题区域     |
| title | 侧边栏头部操作区域 |
| footer | 侧边栏底部区域     |

### Events

| 事件名称   | 说明               | 回调参数 |
| ---------- | ------------------ | -------- |
| open       | 侧边栏展开事件     | -        |
| close      | 侧边栏关闭事件     | -        |
| drag-start | 侧边栏开始拖拽事件 | —        |
| drag-move  | 侧边栏拖拽移动事件 | -        |
| drag-end   | 侧边栏拖拽结束事件 | -        |
