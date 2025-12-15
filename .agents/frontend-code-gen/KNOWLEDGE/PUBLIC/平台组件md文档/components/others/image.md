## Image 图片
图片容器，在保留原生img的特性下，支持懒加载，自定义占位、加载失败等

### 基础用法

:::demo 可通过`fit`确定图片如何适应到容器框，同原生 [object-fit](https://developer.mozilla.org/en-US/docs/Web/CSS/object-fit)。
```html
<template>
    <div class="demo-image">
    <div class="block" v-for="fit in fits" :key="fit">
        <span class="demonstration">{{ fit }}</span>
        <tl-image
        style="width: 100px; height: 100px"
        :src="url"
        :fit="fit"></tl-image>
    </div>
    </div>
</template>

<script>
  const img = require('../../../assets/images/4a731a90594a4af544c0c25941171jpeg.jpeg')
  export default {
    data() {
      return {
        fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
        url: img
      }
    }
  };
</script>
```
:::

### 占位内容

:::demo 可通过`slot = placeholder`可自定义占位内容
```html
<template>
    <div class="demo-image__placeholder">
    <div class="block">
        <span class="demonstration">默认</span>
        <tl-image :src="src"></tl-image>
    </div>
    <div class="block">
        <span class="demonstration">自定义</span>
        <tl-image :src="src">
        <div slot="placeholder" class="image-slot">
            加载中<span class="dot">...</span>
        </div>
        </tl-image>
    </div>
    </div>
</template>

<script>
  const img = require('../../../assets/images/4d3ea53c084bad6931a56d5158a48jpeg.jpeg')
  export default {
    data() {
      return {
        src: img
      }
    }
  };
</script>
```
:::

### 加载失败

:::demo 可通过`slot = error`可自定义加载失败内容
```html
<template>
    <div class="demo-image__error">
    <div class="block">
        <span class="demonstration">默认</span>
        <tl-image></tl-image>
    </div>
    <div class="block">
        <span class="demonstration">自定义</span>
        <tl-image>
        <div slot="error" class="image-slot">
            <i class="el-icon-picture-outline"></i>
        </div>
        </tl-image>
    </div>
    </div>
</template>
<style>
  .demonstration {
    display: block;
    color: #8492a6;
    font-size: 14px;
    margin-bottom: 20px;
 }
</style>

```
:::

### 懒加载

:::demo 可通过`lazy`开启懒加载功能，当图片滚动到可视范围内才会加载。可通过`scroll-container`来设置滚动容器，若未定义，则为最近一个`overflow`值为`auto`或`scroll`的父元素。
```html
<div class="demo-image__lazy">
  <tl-image v-for="url in urls" :key="url" :src="url" lazy></tl-image>
</div>

<script>
  const img = require('../../../assets/images/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg')
  const img1 = require('../../../assets/images/19aa98b1fcb2781c4fba33d850549jpeg.jpeg')
  const img2 = require('../../../assets/images/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg')
  const img3 = require('../../../assets/images/e27858e973f5d7d3904835f46abbdjpeg.jpeg')
  const img4 = require('../../../assets/images/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg')
  const img5 = require('../../../assets/images/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg')
  const img6 = require('../../../assets/images/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg')
  export default {
    data() {
      return {
        urls: [
          img,
          img1,
          img2,
          img3,
          img4,
          img5,
          img6
        ]
      }
    }
  };
</script>
```
:::

### 大图预览

:::demo 可通过 `previewSrcList` 开启预览大图的功能。
```html
<template>
    <div class="demo-image__preview">
    <tl-image 
        style="width: 100px; height: 100px"
        :src="url" 
        :preview-src-list="srcList">
    </tl-image>
    </div>
</template>

<script>
  const img = require('../../../assets/images/4a731a90594a4af544c0c25941171jpeg.jpeg')
  const img1 = require('../../../assets/images/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg')
  const img2 = require('../../../assets/images/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg')
  export default {
    data() {
      return {
        url: img,
        srcList: [
          img1,
          img2
        ]
      }
    }
  };
</script>
```
:::

### 宽度高度

:::demo 可通过`width``height`设置图片宽度高度
```html
<template>
    <div class="demo-image">
      <tl-image
      :width="100"
      :height="150"
      :src="url"
      :preview-src-list="[url]"
      fit="cover"></tl-image>
    </div>
</template>

<script>
  const img = require('../../../assets/images/4a731a90594a4af544c0c25941171jpeg.jpeg')
  export default {
    data() {
      return {
        url: img
      }
    }
  };
</script>
```
:::

### Attributes
| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| src | 图片源，同原生 | string | — | - |
| fit | 确定图片如何适应容器框，同原生 [object-fit](https://developer.mozilla.org/en-US/docs/Web/CSS/object-fit) | string | fill / contain / cover / none / scale-down | - |
| alt | 原生 alt | string | - | - |
| referrer-policy | 原生 referrerPolicy | string | - | - |
| lazy | 是否开启懒加载 | boolean | — | false |
| scroll-container | 开启懒加载后，监听 scroll 事件的容器 | string / HTMLElement | — | 最近一个 overflow 值为 auto 或 scroll 的父元素 |
| preview-src-list | 开启图片预览功能 | Array | — | - |
| z-index | 设置图片预览的 z-index | Number | — | 2000 |
| width ^(8.5.0) | 设置图片宽度 | Number | — | — |
| height ^(8.5.0) | 设置图片高度 | Number | — | — |

### Events
| 事件名称      | 说明    | 回调参数      |
|---------- |-------- |---------- |
| load | 图片加载成功触发 | (e: Event) |
| error | 图片加载失败触发 | (e: Error) |

### Slots
| 名称    | 说明         |
|---------|-------------|
| placeholder | 图片未加载的占位内容 |
| error | 加载失败的内容 |


