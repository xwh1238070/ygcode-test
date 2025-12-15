## Page-tabs 窗口内嵌页签

页面内嵌的多功能页签，方便快速的生成页面页签栏。

### 基础用法

标签页过多溢出时可以点击左右按钮进行前换，也可以在右侧下拉栏中直接选择对应的页签项
:::demo `v-model`为当前选中的标签`id`，组件根据`tabs-data`的内容变化而改变，用户可以监听`tab:click`与`tab:close`事件做出业务端的响应

```html
<template>
  <tl-page-tabs
    v-model="activeTabId"
    :tabs-data="tabsData"
    @tab-click="tabClick"
    @tab-close="closeClick"
  ></tl-page-tabs>
</template>

<script>
  export default {
    data() {
      return {
        activeTabId: 'yedx',
        tabsData: [
          {
            name: '余额抵消',
            id: 'yedx'
          },
          {
            name: '集团对账',
            id: 'jtdz'
          },
          {
            name: '抵销凭证输入',
            id: 'dxpzsr'
          },
          {
            name: '抵销凭证输入2',
            id: 'dxpzsr2'
          },
          {
            name: '抵销凭证输入3',
            id: 'dxpzsr3'
          },
          {
            name: '抵销凭证输入4',
            id: 'dxpzsr4'
          },
          {
            name: '抵销凭证输入5',
            id: 'dxpzsr5'
          },
          {
            name: '抵销凭证输入6',
            id: 'dxpzsr6'
          },
          {
            name: '抵销凭证输入7',
            id: 'dxpzsr7'
          },
          {
            name: '抵销凭证输入8',
            id: 'dxpzsr8'
          }
        ]
      };
    },
    watch: {
      activeTabId(newVal) {
        console.log(newVal);
      }
    },
    mounted() {},
    methods: {
      tabClick(val) {
        console.log(`tabClick in demo`, val);
        this.$message({
          message: `name: ${val.name}`,
          type: 'info',
          duration: 1500
        });
      },
      closeClick(val) {
        console.log(`closeClick in demo`, val);

        // 业务端的删除逻辑
        if (this.activeTabId === val.id) {
          const tabIndex = this.tabsData.findIndex(item => item.id === val.id);
          if (tabIndex || tabIndex === 0) {
            this.activeTabId =
              tabIndex > 0 ? this.tabsData[tabIndex - 1].id : this.tabsData.length > 1 ? this.tabsData[1].id : '';
          }
        }
        this.tabsData = this.tabsData.filter(item => item.id !== val.id);
      }
    }
  };
</script>
```

:::

### Page-tabs Attributes

| 参数     | 说明                         | 类型   | 可选值 | 默认值 |
| -------- | ---------------------------- | ------ | ------ | ------ |
| v-model  | 当前选中页签项的 id          | string | —      | —      |
| tabsData | 页签数据，必传项，具体看下表 | array  | —      | -      |
| home     | 点击主页按钮时事件反馈的数据 | object | —      | -      |

### tabsData

| 参数 | 说明           | 类型   | 可选值 | 默认值 |
| ---- | -------------- | ------ | ------ | ------ |
| name | 页签显示的文字 | string | —      | -      |
| id   | 页签项的 id    | string | —      | -      |

### Page-tabs Events

| 事件名称  | 说明                     | 回调参数         |
| --------- | ------------------------ | ---------------- |
| tab-click | 页签被点击时触发         | (tab, index) |
| tab-contextmenu | 页签右键点击时出发 | (event, tab, index)   |
| tab-close | 页签关闭按钮被点击时触发 | (tab, index)   |
