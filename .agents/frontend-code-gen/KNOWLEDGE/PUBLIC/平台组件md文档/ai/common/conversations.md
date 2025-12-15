## Conversations 会话管理 ^(8.5.0)

用于承载用户侧发送的历史对话列表。

### 基础用法

基础的会话管理用法。

:::demo 通过 `items` 属性传入对话记录数据，支持按时间自动分组展示。

```html
<template>
  <yj-conversations
    v-model="selectedId"
    :items="conversationList"
    @create="handleCreate"
    @delete="handleDelete"
  />
</template>

<script>
export default {
  data() {
    return {
      selectedId: '',
      conversationList: [
        { id: '1', label: '对话1', date: '2024-03-25' },
        { id: '2', label: '对话2', date: '2024-03-28' },
        { id: '3', label: '对话3', date: '2024-04-01' }
      ]
    };
  },
  methods: {
    handleCreate() {
      console.log('新建对话');
    },
    handleDelete(item) {
      console.log('删除对话:', item);
    }
  }
};
</script>
```
:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| value | 当前选中项的标识字段值 | String | - | - |
| title | 列表标题 | String | - | - |
| items | 对话记录数据数组 | Array | - | [] |
| rowKey | 每条记录的唯一标识字段名 | String | - | id |
| labelKey | 每条记录显示名称字段名 | String | - | label |


### items
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| id | 唯一标识 | String | - | - |
| label | 显示名称 | String  | - | - |
| date | 创建日期 | String / Date | - | - |

### Events
| 事件名称 | 说明 | 回调参数 |
|- | - | - |
| create | 新建对话事件 | - |
| delete | 删除对话事件 | (item) |