# Tabs 选项卡

分隔内容上有关联但属于不同类别的数据集合。

### 基础用法

基础的、简洁的标签页。

:::demo 不设置 `v-model/value` 默认选中第一个标签页，你也可以通过 `value` 属性来指定当前选中的标签页。`disabled` 属性设置选项卡禁用；当选项卡过长时会出现省略号，悬浮鼠标可查看完整内容

```html
<template>
  <tl-tabs v-model="activeName" @tab-click="handleClick">
    <tl-tab-pane label="选项卡选中" name="0">选项卡选中</tl-tab-pane>
    <tl-tab-pane label="选项卡禁用" name="1" disabled>选项卡禁用</tl-tab-pane>
    <tl-tab-pane label="选项卡未选中" name="2">选项卡未选中</tl-tab-pane>
    <tl-tab-pane label="超长选项卡超长选项卡超长选项卡" name="3">超长选项卡</tl-tab-pane>
  </tl-tabs>
</template>

<script>
export default {
  data() {
    return {
      activeName: '0'
    } 
  },
  methods: {
    handleClick(tab) {
      console.log(tab);
    }
  }
};
</script>
```
:::

### 选项卡带内容数量和角标

添加数字等标记

:::demo 提供数量、角标、红点标记，设置`count`、`badge-value`、`is-dot`可分别设置对应的值；`count`、`badge-value`最大值为999，超过显示999+

```html
<template>
  <tl-tabs>
    <tl-tab-pane label="选项卡含数量" name="0" count="8">选项卡含数量</tl-tab-pane>
    <tl-tab-pane label="选项卡含角标" name="1" badge-value="8">选项卡含角标</tl-tab-pane>
    <tl-tab-pane label="选项卡角标显示最大值" name="2" badge-value="10000">选项卡角标显示最大值</tl-tab-pane>
    <tl-tab-pane label="选项卡含角标红点" name="3" is-dot>选项卡含角标红点</tl-tab-pane>
  </tl-tabs>
</template>
```
:::

### 卡片化

卡片化的标签页。

:::demo 设置 `type` 为 border 或 border-card 改变卡片风格

```html
<template>
  <tl-tabs type="border-card">
    <tl-tab-pane label="选项卡1" name="0">选项卡1</tl-tab-pane>
    <tl-tab-pane label="选项卡2" name="1">选项卡2</tl-tab-pane>
    <tl-tab-pane label="选项卡3" name="2">选项卡3</tl-tab-pane>
    <tl-tab-pane label="选项卡4" name="3">选项卡4</tl-tab-pane>
  </tl-tabs>
</template>
```
:::

### 位置

可以通过 `tab-position` 设置标签的位置。

:::demo 标签一共有四个方向的设置 `tabPosition="left|right|top|bottom"`

```html
<template>
  <div>
    <tl-radio-group v-model="tabPosition" style="margin-bottom: 30px;">
      <tl-radio-button label="top">top</tl-radio-button>
      <tl-radio-button label="right">right</tl-radio-button>
      <tl-radio-button label="bottom">bottom</tl-radio-button>
      <tl-radio-button label="left">left</tl-radio-button>
    </tl-radio-group>

    <tl-tabs :tab-position="tabPosition">
      <tl-tab-pane label="选项卡1" name="0">选项卡1</tl-tab-pane>
      <tl-tab-pane label="选项卡2" name="1">选项卡2</tl-tab-pane>
      <tl-tab-pane label="选项卡3" name="2">选项卡3</tl-tab-pane>
      <tl-tab-pane label="选项卡4" name="3">选项卡4</tl-tab-pane>
    </tl-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tabPosition: 'left'
    } 
  }
};
</script>
```
:::

### 自定义标签页

可以通过具名 `slot` 来实现自定义标签页的内容。

:::demo

```html
<template>
  <tl-tabs type="border-card">
    <tl-tab-pane>
      <span slot="label"><i class="el-icon-date"></i> 工作安排</span>
      工作安排
    </tl-tab-pane>
    <tl-tab-pane label="选项卡2">选项卡2</tl-tab-pane>
    <tl-tab-pane label="选项卡3">选项卡3</tl-tab-pane>
    <tl-tab-pane label="选项卡4">选项卡4</tl-tab-pane>
  </tl-tabs>
</template>
```
:::

### 拖动更改标签页

设置 `draggable` 属性，拖动更改标签页,只能在选项卡样式的标签页下使用。

:::demo 拖动标签需要同步使用回调事件 `@tab-drag-end` 更改传进组件的源对象的排序，否则会出现选中条不对应的问题

```html
<template>
  <tl-tabs type="border-card" draggable @tab-drag-end="handleDragEnd">
    <tl-tab-pane
      v-for="pane in editableTabs"
      :key="pane.name"
      :label="pane.title"
      :name="pane.name">
      {{pane.content}}
    </tl-tab-pane>
  </tl-tabs>
</template>

<script>
export default {
  data() {
    return {
      editableTabs: [
        {
          title: '选项卡1',
          name: '0',
          content: '选项卡1'
        },
        {
          title: '选项卡2',
          name: '1',
          content: '选项卡2'
        },
        {
          title: '选项卡3',
          name: '2',
          content: '选项卡3'
        },
        {
          title: '选项卡4',
          name: '3',
          content: '选项卡4'
        }
      ]
    } 
  },
  methods: {
    handleDragEnd(oldIndex, newIndex) {
      const movedTab = this.editableTabs[oldIndex];
      this.editableTabs.splice(oldIndex, 1);
      this.editableTabs.splice(newIndex, 0, movedTab);
    }
  }
};
</script>
```
:::

### 动态增减标签页

增减标签页按钮只能在选项卡样式的标签页下使用。

:::demo

```html
<template>
  <tl-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
    <tl-tab-pane
      v-for="pane in editableTabs"
      :key="pane.name"
      :label="pane.title"
      :name="pane.name">
      {{pane.content}}
    </tl-tab-pane>
  </tl-tabs>
</template>

<script>
export default {
  data() {
    return {
      editableTabsValue: '2',
      tabIndex: 2,
      editableTabs: [
        {
          title: '选项卡1',
          name: '1',
          content: '选项卡1'
        },
        {
          title: '选项卡2',
          name: '2',
          content: '选项卡2'
        }
      ]
    } 
  },
  methods: {
    handleTabsEdit(targetName, action) {
      if(action === 'add') {
        const newTabName = ++this.tabIndex + '';
        this.editableTabs.push({
          title: `选项卡${newTabName}`,
          name: newTabName,
          content: `选项卡${newTabName}`
        });
        this.editableTabsValue = newTabName;
      } else if(action === 'remove') {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        if(activeName === targetName) {
          tabs.forEach((tab, index) => {
            if(tab.name === targetName) {
              const nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }

        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      }
    }
  }
};
</script>
```
:::

### 自定义增加标签页触发器
<br/>

:::demo

```html
<template>
  <div>
    <div style="margin-bottom: 20px;">
      <tl-button
        @click="addTab(editableTabsValue)"
      >add tab
      </tl-button>
    </div>

    <tl-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
      <tl-tab-pane
        v-for="pane in editableTabs"
        :key="pane.name"
        :label="pane.title"
        :name="pane.name">
        {{pane.content}}
      </tl-tab-pane>
    </tl-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      editableTabsValue: '2',
      tabIndex: 2,
      editableTabs: [
        {
          title: '选项卡1',
          name: '1',
          content: '选项卡1'
        },
        {
          title: '选项卡2',
          name: '2',
          content: '选项卡2'
        }
      ]
    } 
  },
  methods: {
    addTab(targetName) {
      const newTabName = ++ this.tabIndex + '';
      this.editableTabs.push({
        title: `选项卡${newTabName}`,
        name: newTabName,
        content: `选项卡${newTabName}`
      });
      this.editableTabsValue = newTabName;
    },

    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if(activeName === targetName) {
        tabs.forEach((tab, index) => {
          if(tab.name === targetName) {
            const nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    }
  }
};
</script>
```
:::

### Tabs Attributes
| 参数       | 说明     | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| value / v-model  | 绑定值，选中选项卡的 name  | string   |  —  |  第一个选项卡的 name |
| type     | 风格类型   | string   | card/border-card  |     —    |
| closable  | 标签是否可关闭   | boolean   | — |  false  |
| addable  | 标签是否可增加   | boolean   | — |  false  |
| editable  | 标签是否同时可增加和关闭   | boolean   | — |  false  |
| draggable  | 标签是否可拖拽   | boolean   | — |  false  |
| tab-position  | 选项卡所在位置 | string   |  top/right/bottom/left  |  top |
| stretch  | 标签的宽度是否自撑开 | boolean   |  -  |  false |
| before-leave | 切换标签之前的钩子，若返回 false 或者返回 Promise 且被 reject，则阻止切换。 | Function(activeName, oldActiveName) | — | — |

### Tab-pane Attributes
| 参数       | 说明     | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| label     | 选项卡标题   | string   | — |    —     |
| count     | 选项卡后的数字   | string / number   | — |    —     |
| badge-value     | 选项卡后的角标值   | string / number   | — |    —     |
| is-dot | 角标以红点的形式展示 | boolean | — | false |
| disabled | 是否禁用 | boolean | — | false |
| name      | 与选项卡绑定值 value 对应的标识符，表示选项卡别名 | string | — | 该选项卡在选项卡列表中的顺序值，如第一个选项卡则为'1' |
| closable  | 标签是否可关闭   | boolean   | — |  false  |
| lazy  | 标签是否延迟渲染   | boolean   | — |  false  |

### Tabs Events
| 事件名称 | 说明 | 回调参数 |
|---------- |-------- |---------- |
| tab-click  | tab 被选中时触发 | 被选中的标签 tab 实例 |
| tab-remove  | 点击 tab 移除按钮后触发  | 被删除的标签的 name |
| tab-add  | 点击 tabs 的新增按钮后触发  | — |
| edit  | 点击 tabs 的新增按钮或 tab 被关闭后触发  | (targetName, action) |
| tab-drag-end  | 拖拽选项卡到新位置后触发  | 拖动前位置和拖动后位置的index |
