## Backtop 回到顶部

返回页面顶部的操作按钮

### 基础用法

滑动页面即可看到右下方的按钮。
:::demo


```html
<template>
  <div>
    Scroll down to see the bottom-right button.
    <tl-backtop target=".app-main" :bottom="60" :right="30"></tl-backtop>
  </div>
</template>

```

:::

### 自定义显示内容

显示区域被固定为 40px \* 40px 的区域, 其中的内容可支持自定义。
:::demo 


```html
<template>
  Scroll down to see the bottom-right button.
  <tl-backtop target=".app-main" :bottom="60" :right="100">
    <div>
      UP
    </div>
  </tl-backtop>
</template>
```

:::

### Attributes

| 参数              | 说明                             | 类型            | 可选值 | 默认值 |
| ----------------- | -------------------------------- | --------------- | ------ | ------ |
| target            | 触发滚动的对象                   | string          |        |        |
| visibility-height | 滚动高度达到此参数值才出现       | number |        | 200    |
| right             | 控制其显示位置, 距离页面右边距   | number |        | 40     |
| bottom            | 控制其显示位置, 距离页面底部距离 | number |        | 40     |

### Events

| 事件名 | 说明               | 回调参数 |
| ------ | ------------------ | -------- |
| click  | 点击按钮触发的事件 | 点击事件 |
