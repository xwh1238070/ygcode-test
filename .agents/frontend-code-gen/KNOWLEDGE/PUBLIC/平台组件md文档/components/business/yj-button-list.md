## YjButtonList YJPL按钮组

业务用按钮组组件，可根据对象配置来配置出相应的按钮。

### 基本使用
通过 `data` 属性配置显示按钮。

:::demo

```html
<template>
   <yj-button-list :data="data"></yj-button-list>
</template>

<script>
  export default {
    data () {
      return {
        data: [
          {
            label: '增加',
            icon: 'el-icon-circle-plus-outline',
            type: 'primary'
          },
          {
            label: '编辑',
            icon: 'el-icon-edit'
          },
          {
            name: 'aaaa',
            label: '删除',
            icon: 'el-icon-remove-outline'
          }
        ]
      }
    }
  };
</script>
```
:::

### 下拉子按钮
通过 `data` 内的 `children` 属性可配置下拉子按钮。

:::demo

```html
<template>
 <yj-button-list :data="data"></yj-button-list>
</template>

<script>
  export default {
    data () {
      return {
        data: [
          {
            label: '更多',
            icon: 'el-icon-circle-plus-outline',
            children: [
              {
                label: '增加',
                icon: 'el-icon-circle-plus-outline',
                type: 'primary'
              },
              {
                label: '编辑',
                icon: 'el-icon-edit'
              }
            ]
          }
        ]
      }
    }
  };
</script>
```
:::

### 浮动窗式下拉
通过 `popover` 属性，可将下拉改为浮动窗。

:::demo

```html
<template>
    <yj-button-list popover :data="data"></yj-button-list>
</template>
<script>
  export default {
    data () {
      return {
        data: [
          {
            label: '更多',
            icon: 'el-icon-circle-plus-outline',
            children: [
              {
                label: '增加',
                icon: 'el-icon-circle-plus-outline',
                type: 'primary'
              },
              {
                label: '编辑',
                icon: 'el-icon-edit'
              }
            ]
          }
        ]
      }
    }
  };
</script>
```
:::

### 分割线
通过 `data` 内的 `divided` 属性可配置按钮分割线。

:::demo

```html
<template>
 <yj-button-list :data="data"></yj-button-list>
</template>

<script>
  export default {
    data () {
      return {
        data: [
          {
            label: '新增'
          },
          {
            label: '修改',
            divided: true
          },
          {
            label: '删除',
            divided: true
          },
          {
            label: '更多',
            children: [
              {
                label: '增加',
                type: 'primary'
              },
              {
                label: '编辑',
                divided: true
              },
              {
                name: 'aaaa',
                label: '删除',
                divided: true
              }
            ]
          }
        ]
      }
    }
  };
</script>
```
:::

### YjButtonList Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| data   | 按钮组渲染数据，详细见下表          | array | - | - |
| popover | 子按钮是否以 popover 的形式显示 | boolean | true/false | false      |
| state | 状态管理属性，如需实现全局状态管理，请将 `stateModel` 传入该属性 | object | -  | - |

### YjButtonList  Events

| 事件名称      | 说明                           | 回调参数     |
| ------------- | ------------------------------ | ------------ |
| click        |  点击事件                      | function(data)  |

### data

| 参数         | 说明                   | 类型   | 可选值               | 默认值 |
| ------------ | ---------------------- | ------ | -------------------- | ------ |
| name | 按钮标识，可视为id，当绑定 `stateModel` 时，该属性作为 `stateModel` 内对应的值 | string  | — | - |
| text | 按钮文字 | string  | — | - |
| children | 子按钮属性，格式与data一致 | array  | — | - |
| callback | 按钮点击回调 | function(data)  | — | - |
| size     | 尺寸   | string  |   medium / small / mini            |    —     |
| type     | 类型   | string    |   primary / success / warning / danger / info / text |     —    |
| plain     | 是否朴素按钮   | boolean    | — | false   |
| round     | 是否圆角按钮   | boolean    | — | false   |
| circle     | 是否圆形按钮   | boolean    | — | false   |
| loading     | 是否加载中状态   | boolean    | — | false   |
| icon  | 图标类名 | string   |  —  |  —  |
| autofocus  | 是否默认聚焦 | boolean   |  —  |  false  |
| native-type | 原生 type 属性 | string | button / submit / reset | button |
| overflow | 溢出状态 | string | `auto` / `hidden` / `none` | - |
| divided  | 显示分割线	 | boolean   |  —  |  false  |