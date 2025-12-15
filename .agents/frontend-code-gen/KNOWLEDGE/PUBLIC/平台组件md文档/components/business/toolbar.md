## Toolbar 页面工具栏
常用的页面工具栏。

### 基础用法

基础的页面工具栏用法。

:::demo 设置`is-save`显示保存按钮，回调事件：`save`。默认是一个空的插槽，可自由放置任意元素

```html
<template>
  <tl-toolbar is-save @save="doSave"></tl-toolbar>
</template>

<script>
  export default {
    methods: {
      doSave() {
        console.log('保存成功...');
      }
    }
  };
</script>
```
:::

### 含取消按钮和保存按钮
<br/>

:::demo 设置`is-cancel`可以显示取消按钮，对应的事件是`@cancel` 。

```html
<template>
  <tl-toolbar is-cancel @cancel="doCancel"></tl-toolbar>
</template>

<script>
export default {
  methods: {
    doCancel() {
      console.log('doCancel....');
    }
  }
};
</script>
```
:::

### 已选信息纯展示

可用于计算选中条数和计算合计金额

:::demo 你可以使用直接使用插槽自定义。

```html
<template>
  <tl-toolbar class="toolbar-demo" is-save>
    <div class="pay-info">
      <span class="pay-title">本次支付合计：</span>
      <span class="pay-number">￥453.00</span>
    </div>
    <div class="selected-count">已选条数：2</div>
    <tl-checkbox class="check-all" v-model="checkAll">全选</tl-checkbox>
  </tl-toolbar>
</template>

<script>
export default {
  data() {
    return {
      checkAll: false
    };
  }
};
</script>

<style>
.toolbar-demo{
  margin-bottom: 30px;
  display: flex;
  flex-direction: row-reverse;
}
.toolbar-demo .pay-info {
  font-size: 14px;
  margin-right: 19px;
}
.toolbar-demo .pay-title {
  color: #162b41;
}
.toolbar-demo .pay-number {
  font-family: Source Han Sans SC;
  color: #f0760c;
  font-weight: 500;
}
.toolbar-demo .selected-count{
  margin-right: 30px;
  font-weight: bold;
  font-family: Source Han Sans CN;
  font-size: 14px;
  color: #f0760c;
}
.toolbar-demo .el-icon-caret-top{
  cursor: pointer;
  font-size: 16px;
  color: #7b8ea2;
  margin-right: 19px;
  margin-left: -19px;
}
.toolbar-demo .check-all{
  margin-right: auto;
  font-size: 14px;
  color: #162b41;
}
</style>
```
:::

### 浮动展示已选信息条目

已选信息折叠，鼠标悬浮展开查看

:::demo 你可以使用直接使用插槽自定义。

```html
<template>
  <tl-toolbar class="toolbar-demo" is-save>
    <tl-popover placement="top-start" width="300" trigger="hover">
      <div class="pop-content">
        <div class="pop-item" v-for="(item, index) in popList" :key="index">{{ item }}</div>
      </div>
      <i slot="reference" class="el-icon-caret-top"></i>
    </tl-popover>
    <div class="selected-count">
      <span style="color: #162b41">已选条数：</span>
      <span>2</span>
    </div>
  </tl-toolbar>
</template>

<script>
export default {
  data() {
    return {
      popList: [
        "差旅费",
        "办公费",
        // "通讯费",
        // "业务招待费",
        // "机维修费",
        // "差旅费",
        // "办公费",
        // "通讯费",
        // "业务招待费",
        // "机维修费"
      ]
    };
  }
};
</script>

<style>
.pop-content{
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}
.pop-content .pop-item{
  font-size: 12px;
  font-family: Source Han Sans CN;
  font-weight: 400;
  color: #162b41;
  padding: 2px 6px;
  border: 1px solid #dce1ea;
  box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.08);
  margin: 0 7px 7px 0;
}
.pop-content .pop-item::after{
  content: "";
  width: 20px;
}
.toolbar-demo{
  margin-bottom: 30px;
  display: flex;
  flex-direction: row-reverse;
}
.toolbar-demo .pay-info {
  font-size: 14px;
  margin-right: 19px;
}
.toolbar-demo .pay-title {
  color: #162b41;
}
.toolbar-demo .pay-number {
  font-family: Source Han Sans SC;
  color: #f0760c;
  font-weight: 500;
}
.toolbar-demo .selected-count{
  margin-right: 30px;
  font-weight: bold;
  font-family: Source Han Sans CN;
  font-size: 14px;
  color: #f0760c;
}
.toolbar-demo .el-icon-caret-top{
  cursor: pointer;
  font-size: 16px;
  color: #7b8ea2;
  margin-right: 19px;
  margin-left: -19px;
}
</style>
```
:::


### Attributes
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
is-save | 是否显示保存按钮 | Boolean | true/false | false
is-cancel | 是否显示取消按钮 | Boolean | true/false | false
save-text | 保存按钮的文字 | String | - | 保存
cancel-text | 取消按钮的文字 | String | - | 取消
is-fixed | 是否固定在底部 | Boolean | true/false | false

### Slot
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
Slot | 非具名插槽，可直接自定义工具栏的内容(注：内容默认从右向左布局) | - | - | - |

### Events
事件名称 | 说明 | 回调参数
| --------------- | -------- | -------- | 
save | 未定义插槽时，点击保存按钮触发 | - |