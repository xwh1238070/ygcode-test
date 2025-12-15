## Sender 输入框 ^(8.5.0)

用于聊天的输入框组件。

### 基础用法

基础的输入框用法。

:::demo 支持文本输入和语音识别，通过 `send` 事件提交内容。

```html
<template>
  <yj-sender @send="handleSend"></yj-sender>
</template>

<script>
export default {
  methods: {
    handleSend(content, type) {
      console.log('发送内容:', content);
      console.log('发送类型:', type); // write（文本）或 voice（语音）
    }
  }
};
</script>
```
:::

:::tip 

```html
WARNING

使用语音功能需要浏览器支持 Web Speech API（如 Chrome、Edge），且具有网络权限。首次使用语音识别时需授权麦克风访问权限。若无网络或权限被拒绝，会提示错误信息。

```
:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| - | - | - | - | - |
| loading | 是否加载中状态 | Boolean | - | false |


### Events
| 事件名称 | 说明 | 回调参数 |
|- | - | - |
| send | 点击发送按钮或按下回车时触发 | (value, type:  write \| voice) |