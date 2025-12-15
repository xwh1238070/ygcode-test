## Textarea 文本域

文本域用于输入多行文本信息。

### 基本使用
<br/>

:::demo Textarea 组件提供多行输入框，可通过属性配置禁用/只读/提示文案等属性。
```html
<template>
  <tl-textarea
    v-model="inputEcp"
    :row="params.inputEcp_rows"
    :disabled="params.inputEcp_disabled"
    :readonly="params.inputEcp_readonly"
    :placeholder="params.inputEcp_placeholder"
  ></tl-textarea>
</template>
<script>

export default {
  name: 'Textarea1',
  data () {
    return {
      inputEcp: '',
      params: {
        // inputEcp
        inputinputEcp_rows: 2,
        inputEcp_readonly: false,
        inputEcp_disabled: false,
        inputEcp_placeholder: '提示文案',
      }
    }
  },
}
</script>
```
:::

### 禁用和只读
<br/>

:::demo
```html
<template>
  <div>
    <tl-textarea
      v-model="inputEcp"
      :disabled="params.inputEcp_disabled"
      :readonly="params.inputEcp_readonly"
      :placeholder="params.inputEcp_placeholder"
    ></tl-textarea>
    <hr /> 
    <tl-textarea
      v-model="inputEcp2"
      :disabled="params2.inputEcp_disabled"
      :readonly="params2.inputEcp_readonly"
      :placeholder="params2.inputEcp_placeholder"
    ></tl-textarea>
  </div>
</template>
<script>

export default {
  name: 'Textarea1',
  data () {
    return {
      inputEcp: 'this is disabled',
      inputEcp2: 'this is readonly',
      params: {
        // inputEcp
        inputEcp_readonly: false,
        inputEcp_disabled: true,
        inputEcp_placeholder: '提示文案',
      },
      params2: {
        // inputEcp
        inputEcp_readonly: true,
        inputEcp_disabled: false,
        inputEcp_placeholder: '提示文案2',
      },
    }
  },
}
</script>
```
:::

### 文字长度限制和计数器

Textarea 组件可以通过`maxlength`属性调节字数限制，通过`show-word-limit`属性调整是否显示字数统计。

:::demo 通过设置`maxlength`属性和`show-word-limit`属性来使用字数长度限制和计数器。
```html
<template>
   <tl-textarea
      type="textarea"
      v-model="inputEcp2"
      :show-word-limit="params.inputEcp2_showWordLimit"
      :maxlength="Number(params.inputEcp2_maxlength)"
      :maxWidth="params.inputEcp2_maxWidth"
      :maxHeight="params.inputEcp2_maxHeight"
    ></tl-textarea>
</template>

<script>
export default {
  name: 'Textarea3',
  data () {
    return {
      inputEcp2: '',
      params: {
        // inputEcp2
        inputEcp2_maxlength: 50,
        inputEcp2_showWordLimit: true,
        inputEcp2_maxWidth: '',
        inputEcp2_maxHeight: ''
      }
    }
  },
}
</script>
```
:::



### 文本域框大小调节

通过设置`resize`属性和设置输入框的最大/最小宽高来启用文本域大小调节。  
_不支持IE浏览器_

:::demo 通过设置`resize`属性和设置输入框的最大/最小宽高来启用文本域大小调节。**不支持IE浏览器**

```html
<template>
   <tl-textarea
      type="textarea"
      v-model="inputEcp2"
      :resize="params.inputEcp2_resize"
      :maxWidth="params.inputEcp2_maxWidth"
      :maxHeight="params.inputEcp2_maxHeight"
    ></tl-textarea>
</template>

<script>
export default {
  name: 'Textarea4',
  data () {
    return {
      inputEcp2: '',
      params: {
        // inputEcp2
        inputEcp2_resize: 'vertical',
        inputEcp2_maxWidth: 900,
        inputEcp2_maxHeight: 400
      }
    }
  },
}
</script>
```
:::


### Attributes


| 参数            | 说明                                                   | 类型             | 可选值                           | 默认值 |
| --------------- | ------------------------------------------------------ | ---------------- | -------------------------------- | ------ |
| maxlength       | 最大字数                                               | number           | —                                | —      |
| minlength       | 最小字数                                               | number           |                                  | —      |
| value           | 当前值                                                 | string           | —                                | —      |
| placeholder     | 输入框占位文本                                         | string           | —                                | -      |
| readonly        | 原生属性，是否只读                                     | boolean          | —                                | false  |
| resize          | 控制是否能被用户缩放                                   | string           | none, both, horizontal, vertical | -      |
| show-word-limit | 是否显示输入字数统计                                   | boolean          | —                                | false  |
| minWidth        | 最小宽度                                               | Number/String    | —                                | 150    |
| maxWidth        | 最大宽度                                               | Number/String    | —                                | 1024   |
| minHeight       | 最小高度                                               | Number/String    | —                                | 46     |
| maxHeight       | 最大高度                                               | Number/String    | —                                | 1024   |
| disabled        | 禁用                                                   | boolean          | —                                | —      |
| rows            | 输入框行数                                             | number           | —                                | 2      |
| autosize        | 自适应内容高度，可传入对象如{ minRows: 2, maxRows: 6 } | boolean / object | —                                | false  |


### Events
| 事件名称 | 说明                                          | 回调参数       |
| -------- | --------------------------------------------- | -------------- |
| blur     | 在 Input 失去焦点时触发                       | (event: Event) |
| focus    | 在 Input 获得焦点时触发                       | (event: Event) |
| change   | 仅在输入框失去焦点或用户按下回车时触发        | (value: string | number) |
| input    | 在 Input 值改变时触发                         | (value: string | number) |
| clear    | 在点击由 `clearable` 属性生成的清空按钮时触发 | -              |
