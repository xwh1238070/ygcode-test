# Magnifier 放大镜

放大镜可用于整个页面的放大

### 基本使用
使用 `this.$magnifier`,传入参数isTrigger的值为字符串1的时候可触发放大镜，鼠标单击右键和键盘上按下esc按键放大镜消失。


:::demo 

```html
<template>
    <tl-button  @click="handleMagnifier">放大镜</tl-button>
</template>

<script>
export default {
   methods: {  
        handleMagnifier() {
            this.$magnifier({
                isTrigger:'1',
            })
        },           
    },
}
</script>
```

:::
### Magnifier Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| isTrigger | 是否触发放大镜 | String | 1 |      |
