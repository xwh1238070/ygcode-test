### Avatar 头像

用图标、图片或者字符的形式展示用户或事物信息。

### 基本用法

通过 `shape` 和 `size` 设置头像的形状和大小。

:::demo Avatar1

```html
<template>
  <tl-row class="demo-avatar demo-basic">
    <tl-col :span="12">
      <div class="sub-title">circle</div>
      <div class="demo-basic--circle">
        <div class="block">
          <tl-avatar :size="50" :src="circleUrl"></tl-avatar>
        </div>
        <div class="block" v-for="size in sizeList" :key="size">
          <tl-avatar :size="size" :src="circleUrl"></tl-avatar>
        </div>
      </div>
    </tl-col>  
    <tl-col :span="12">
      <div class="sub-title">square</div>
      <div class="demo-basic--circle">
        <div class="block">
          <tl-avatar shape="square" :size="50" :src="squareUrl"></tl-avatar></div>
        <div class="block" v-for="size in sizeList" :key="size">
          <tl-avatar shape="square" :size="size" :src="squareUrl"></tl-avatar>
        </div>
      </div>
    </tl-col> 
  </tl-row>
</template>
<script>
  const circleUrl = require('../../../assets/images/3ea6beec64369c2642b92c6726f1epng.png')
  const squareUrl = require('../../../assets/images/f0ee8a3c7c9638a54940382568c9dpng.png')
  export default {
    data() {
      return {
        circleUrl: circleUrl,
        squareUrl: squareUrl,
        sizeList: ["large", "medium", "small"]
      };
    }
  };
</script>

```
:::

### 展示类型

支持三种类型：图标、图片和字符

:::demo Avatar2

```html
<template>
  <div class="demo-type">
    <div>
      <tl-avatar icon="el-icon-user-solid"></tl-avatar>
    </div>
    <div>
      <tl-avatar :src="src"></tl-avatar>
    </div>
    <div>
      <tl-avatar> user </tl-avatar>
    </div>
  </div>
</template>
<script>
  const img = require('../../../assets/images/03b0d39583f48206768a7534e55bcpng.png')
  export default {
    name:'Avatar2',
    data() {
      return {
        src: img
      };
    }
  }
</script>
```
:::

### 图片加载失败的 fallback 行为

当展示类型为图片的时候，图片加载失败的 fallback 行为

:::demo Avatar3

```html
<template>
  <div class="demo-type">
    <tl-avatar :size="60" src="https://empty" @error="errorHandler">
      <img :src="src"/>
    </tl-avatar>
  </div>
</template>
<script>
  const img = require('../../../assets/images/0fc7d20532fdaf769a25683617711png.png')
  export default {
    name:'Avatar3',
    data() {
      return {
        src: img
      };
    },
    methods: {
      errorHandler() {
        return true
      }
    }
  }
</script>

```
:::

### 图片如何适应容器框

当展示类型为图片的时候，使用 `fit` 属性定义图片如何适应容器框，同原生 [object-fit](https://developer.mozilla.org/en-US/docs/Web/CSS/object-fit)。

:::demo Avatar4

```html
<template>
  <div class="demo-fit">
    <div class="block" v-for="fit in fits" :key="fit">
        <span class="title">{{ fit }}</span>
        <tl-avatar shape="square" :size="100" :fit="fit" :src="url"></tl-avatar>
    </div>
  </div>
</template>
<script>
  const img = require('../../../assets/images/4a731a90594a4af544c0c25941171jpeg.jpeg')
  export default {
    name:'Avatar4',
    data() {
      return {
        fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
        url: img
      }
    }
  }
</script>

```
:::

### Attributes

| 参数              | 说明                             | 类型            | 可选值 | 默认值 |
| ----------------- | -------------------------------- | --------------- | ------ | ------ |
| icon              | 设置头像的图标类型，参考 Icon 组件   | string          |        |        |
| size              | 设置头像的大小                     | number/string | number / large / medium / small | large  |
| shape             | 设置头像的形状  | string |    circle / square     |   circle  |
| src               | 图片头像的资源地址 | string |        |      |
| srcSet            | 以逗号分隔的一个或多个字符串列表表明一系列用户代理使用的可能的图像 | string |        |      |
| alt               | 描述图像的替换文本 | string |        |      |
| fit               | 当展示类型为图片的时候，设置图片如何适应容器框 | string |    fill / contain / cover / none / scale-down    |   cover   |


### Events

| 事件名 | 说明               | 回调参数 |
| ------ | ------------------ | -------- |
| error  | 图片类头像加载失败的回调， 返回 false 会关闭组件默认的 fallback 行为 |(e: Event)  |

### Slot

| 名称	 | 说明               |  
| ------ | ------------------ | 
| default  | 自定义头像展示内容 |
