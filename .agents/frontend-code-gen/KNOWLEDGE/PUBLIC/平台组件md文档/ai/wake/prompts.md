## Prompts 提示集 ^(8.5.0)

用于显示一组与当前上下文文相关的预定义的问题和建议。

### 基础用法

基础的提示集用法。

:::demo 通过 `items` 设置提示集数据。

```html
<template>
  <yj-prompts
    title="推荐问题"
    icon="yj-p-hint"
    :cols="2"
    :items="promptItems"
    @item-click="handlePromptClick"
  ></yj-prompts>
</template>

<script>
export default {
  data() {
    return {
      promptItems: [
        { label: '问题1', description: '描述信息1', icon: 'yj-p-invoices' },
        { label: '问题2', description: '描述信息2', icon: 'yj-p-pic' }
      ]
    };
  },
  methods: {
    handlePromptClick(item) {
      console.log('选中项:', item);
    }
  }
};
</script>
```
:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| title | 列表标题 | String | - | - |
| icon | 列表图标 | String | - | - |
| cols | 列数 | Number | - | 1 |
| items | 列表数据 | Array | - | [] |

### items
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| label | 列表项标题 | String | - | - |
| icon | 列表项图标 | String  | - | - |
| description | 列表项描述信息 | String  | - | - |

### Events
| 事件名称 | 说明 | 回调参数 |
|- | - | - |
| item-click | 点击提示项时触发 | (item) |