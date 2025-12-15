## WaterMark 页面水印组件

在页面内显示水印

### 基础用法

在页面内添加tl-water-mark标签
:::demo `text`是文字水印 `font-size`文字大小


```html
<template>
    <div class="mark-con">
      <tl-water-mark element="mark-con" :text="input" :font-size="fontSize" :position="position"></tl-water-mark>
    </div>
</template>

<script>

export default {
  data() {
    return {
      input: '测试水印',
      fontSize: 40,
      position: 'center',
    };
  }

}
</script>

<style>
.mark-con {
  width: 800px;
  height: 300px;
  background-color: #f5f5f5;
  margin-bottom: 24px;
  position: relative;
  margin-top: 16px;
}
</style>


```
:::

### 多水印配置

添加multiple配置多水印
:::demo `multiple`配置多水印 `density` 配置多水印密度(值越大水印越多), `water-mark-id` 配置一个页面多个水印，不同水印id


```html
<template>
    <div class="mark-con1">
      <tl-water-mark water-mark-id="water-mark-id1" element="mark-con1" :text="input" :font-size="fontSize" :position="position" :density="density" multiple></tl-water-mark>
    </div>
</template>

<script>

export default {
  data() {
    return {
      input: '测试水印',
      fontSize: 18,
      density: 1,
      position: 'center',
    };
  }

}
</script>

<style>
.mark-con1 {
  width: 800px;
  height: 300px;
  background-color: #f5f5f5;
  margin-bottom: 24px;
  position: relative;
  margin-top: 16px;
}
</style>


```
:::

### 图片水印

通过img配置图片水印
:::demo `imgSrc` 可配置图片路径或者input选中的file图片文件，`width``height` 配置图片大小，不传则默认原图大小

```html
<template>
    <div class="mark-con2">
      <tl-water-mark water-mark-id="water-mark-id2" element="mark-con2" :width="width" :height="height" :img="imgSrc"></tl-water-mark>
    </div>
</template>

<script>

export default {
  data() {
    return {
      width: 200,
      height: 15,
      imgSrc: 'http://localhost:8080/jt/mapp/tleadcpdemo/fileupload/attachment/downloadFile?resId=54580c02d226-11ed-adf1-c5a20d5fa981',
    };
  }

}
</script>

<style>
.mark-con2 {
  width: 800px;
  height: 300px;
  background-color: #f5f5f5;
  margin-bottom: 24px;
  position: relative;
  margin-top: 16px;
}
</style>


```
:::

### html水印

通过dangerously-use-html-string 开启html编译
:::demo html编译会影响浏览器性能，请谨慎使用

```html
<template>
    <div class="mark-con3">
      <tl-water-mark water-mark-id="water-mark-id3" element="mark-con3" :text="textarea" :font-size="30" dangerously-use-html-string></tl-water-mark>
    </div>
</template>

<script>

export default {
  data() {
    return {
      textarea: '<div style="color: red;">测试水印</div><div style="color: green;">+8611111111111</div>',
    };
  }

}
</script>

<style>
.mark-con3 {
  width: 800px;
  height: 300px;
  background-color: #f5f5f5;
  margin-bottom: 24px;
  position: relative;
  margin-top: 16px;
}
</style>


```
:::


### 以指令服务方式调用水印

watermark还可以以服务的方式调用。
:::demo 指令参数与props参数一致

```html
<template>
    <div>
        <tl-button @click="openMark">方法打开水印</tl-button>
        <tl-button @click="clearMark">手动销毁水印</tl-button>
    </div>
</template>

<script>

export default {
  name: 'water-mark-demo4',
  data() {
    return {
      watermark: null
    };
  },
  methods:{
    openMark() {
      this.watermark = new this.$watermark({
          waterMarkId: 'test-body-water-mark-id',
          text: '方法打开水印'
      });
    },
    clearMark() {
        this.watermark.clear();
    }
  }


}
</script>

```
:::

### Attributes

| 参数              | 说明                             | 类型            | 可选值 | 默认值 |
| ----------------- | -------------------------------- | --------------- | ------ | ------ |
| water-mark-id     | 水印id                   | string          |        |   water-mark__id     |
| text | 水印文字       | string |        |     |
| element             | 水印元素   | string |        | `body`     |
| multiple            | 多个水印控制 | boolean |        | `false`     |
| fixed            | 水印fixed浮动布局 | boolean |        | `false`     |
| rotate    | 水印旋转角度                   | number          |        |   `15`     |
| opacity | 水印透明度       | number |    `0 - 1`    |  `0.15`   |
| fontSize             | 文字水印大小   | number |        | `18`     |
| font-family            | 文字水印字体 | string |        | `微软雅黑`     |
| text-color     | 文字水印字体颜色                   | string          |        |   `#000000`     |
| distance-x |  单个水印X轴位移      | number |        |     |
| distance-y | 单个水印Y轴位移   | number |        |      |
| force-remove            | 是否监听强制移除水印 | boolean |        | `false`     |
| width     | 图片水印宽度                   | number          |        |        |
| height | 图片水印高度       | number |        |     |
| density             | 多个水印密度   | number |        | `1`     |
| img            | 图片水印图片地址或者file文件 | string | File |        |     |
| dangerously-use-html-string             | HTML水印   | boolean |        | `false`     |
| text-line            | 文字水印最大行数 | number |        | `5`     |
| position             | 单个水印位置   | string |   `center`, `topLeft`, `topCenter`, `topRight`, `leftCenter`, `rightCenter`, `bottomLeft`, `bottomCenter`, `bottomRight`     | `center`     |

