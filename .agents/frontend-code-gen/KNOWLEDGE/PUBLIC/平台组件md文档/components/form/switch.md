## Switch 开关
表示两种相互对立的状态间的切换，多用于触发「开/关」。

### 基础用法

基础的开关用法。

:::demo 绑定v-model到一个Boolean类型的变量。可以使用active-color属性与inactive-color属性来设置开关的背景色。

```html
<template>
  <tl-switch 
    v-model="value" 
    active-color="#13ce66"
    inactive-color="#ff4949">
  </tl-switch>
</template>

<script>
export default {
  name: 'Switch1',
  data() {
    return {
      value: true
    } 
  }
};
</script>
```
:::

### 开关外部带文字

开关外部自定义不同状态下的文字。

:::demo 使用active-text属性设置开关开启时文字(居右显示)，inactive-text设置开关关闭时文字(居左显示）

```html
<template>
  <tl-switch
    v-model="value"
    active-text="打开"
    inactive-text="关闭">
  </tl-switch>
</template>

<script>
export default {
  name: 'Switch2',
  data() {
    return {
      value: true
    } 
  }
};
</script>
```
:::

### 开关内部带文字

开关内部自定义不同状态下的文字。

:::demo 设置show-text-inside属性为true;当文字内容过多，宽度不够时，可设置`:width`属性调节开关的宽度（默认宽度44px）

```html
<template>
  <tl-switch
    v-model="value"
    active-text="开"
    inactive-text="关"
    :show-text-inside="true">
  </tl-switch>
</template>

<script>
export default {
  name: 'Switch3',
  data() {
    return {
      value: true
    } 
  }
};
</script>
```
:::

### 扩展的 value 类型
<br/>
  
:::demo 使用active-value设置switch打开时的值，使用inactive-value设置switch关闭时的值，接受Boolean, String或Number类型的值

```html
<template>
  <div>
    <tl-switch
      v-model="value"
      active-value="100"
      inactive-value="0">
    </tl-switch>
    <span>switch value: {{value}}</span>
  </div>
</template>

<script>
  export default {
    name: 'Switch4',
    data() {
      return {
        value: '100'
      }
    }
  };
</script>
```
:::

### 禁用状态
<br/>
  
:::demo 设置disabled属性，接受一个Boolean，设置true即可禁用。

```html
<template>
  <div>
    <tl-switch
      v-model="value1"
      active-text="开"
      disabled>
    </tl-switch>
    <tl-switch
      v-model="value2"
      inactive-text="关"
      :show-text-inside="true"
      disabled
      style="margin-left: 20px">
    </tl-switch>
  </div>
</template>

<script>
export default {
  name: 'Switch5',
  data() {
    return {
      value1: true,
      value2: false
    } 
  }
};
</script>
```
:::

### Attributes

| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| value / v-model | 绑定值 | boolean / string / number | — | — |
| disabled  | 是否禁用    | boolean   | — | false   |
| width  | switch 的宽度（像素）    | number   | — | 40 |
| active-icon-class  | switch 打开时所显示图标的类名，设置此项会忽略 `active-text`    | string   | — | — |
| inactive-icon-class  | switch 关闭时所显示图标的类名，设置此项会忽略 `inactive-text`    | string   | — | — |
| active-text  | switch 打开时的文字描述    | string   | — | — |
| inactive-text  | switch 关闭时的文字描述    | string   | — | — |
| show-text-inside  | 文字描述在switch里还是外    | boolean   | — | false |
| active-value  | switch 打开时的值    | boolean / string / number | — | true |
| inactive-value  | switch 关闭时的值    | boolean / string / number | — | false |
| active-color  | switch 打开时的背景色    | string   | — | #409EFF |
| inactive-color  | switch 关闭时的背景色    | string   | — | #C0CCDA |
| name            | switch 对应的 name 属性    | string   | — | — |
| validate-event  | 改变 switch 状态时是否触发表单的校验     | boolean   | - | true |

### Events
| 事件名称      | 说明    | 回调参数      |
|---------- |-------- |---------- |
| change  | switch 状态发生变化时的回调函数    | 新状态的值 |

### Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| focus | 使 switch 获取焦点 | - |