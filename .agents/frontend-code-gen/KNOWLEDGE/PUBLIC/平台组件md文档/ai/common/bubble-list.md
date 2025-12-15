## BubbleList 对话气泡列表 ^(8.5.0)

用于展示多个对话气泡的列表组件，基于 `Bubble` 封装。

### 基础用法

基础的气泡列表用法。

:::demo 通过 `list` 设置气泡列表数据，自动识别 `placement` 判断是否以 Markdown 渲染。

```html
<template>
  <yj-bubble-list :list="list"></yj-bubble-list>
</template>

<script>
export default {
  data() {
    return {
      list: [
        { placement: 'start', content: '你好，有什么可以帮助你吗？' },
        { placement: 'end', content: '我想了解产品功能。' },
        { placement: 'start', content: '我们的产品提供以下功能...' }
      ]
    };
  }
};
</script>

:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| list | 对话列表 | Array | - | [] |

### list
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| placement | 位置信息 | String | start / end | start |
| content | 聊天内容 | String  | - | - |
| isMarkdown | 是否以 `markdown` 形式展示 | Boolean | - | false |

### Slots
| name | 说明 |
| - | - |
| - | 自定义气泡的内容，参数为 { data } |