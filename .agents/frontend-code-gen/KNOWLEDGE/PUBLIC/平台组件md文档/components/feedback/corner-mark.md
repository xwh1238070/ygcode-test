## CornerMark 角标
列表或表格中局部需要就近提示时使用。因为需要考虑尽量不影响原有内容的布局，一般采用渐进式的交互方式呈现更详细的提示内容。

### 横向角标

样式为长方形，位于列表四个边角的位置，宽度根据内容而定。

:::demo 设置`placement`可调整角标的位置，有效值为`top left`、`top right`、`bottom left`、`bottom right`

```html

<template>
  <div class="corner-mark">
    <tl-corner-mark text="回退" class="left-one">
      <div class="table">列表1</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" class="right-two" placement="top right" style="margin-left: 20px">
      <div class="table">列表2</div>
    </tl-corner-mark>
  </div>
</template>

<style>
  .corner-mark .left-one{
    display:inline-block
  }
  .corner-mark .right-two{
    display:inline-block
  }
  .corner-mark .table {
    width: 400px;
    height: 104px;
    display:flex;
    background: #f7f9fb;
    justify-content: center;
    align-items: center;
  }
</style>
```
:::

### 纵向角标

:::demo 设置`direction`为`vertical`，可呈现纵向角标，默认`horizontal`

```html
<template>
  <tl-corner-mark text="回退" direction="vertical">
    <div class="table">列表1</div>
  </tl-corner-mark>
</template>

<style>
.table {
  width: 400px;
  height: 104px;
  background: #f7f9fb;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
```
:::

### 带图标的角标

横向角标中图标位于文字前方位置，纵向角标中图标位于文字上方位置

:::demo `icon`设置图标类型

```html
<template>
  <div class="corner-mark">
    <tl-corner-mark text="回退" icon="el-icon-refresh-left" class="left-one">
      <div class="table">列表1</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" direction="vertical" icon="el-icon-refresh-left" class="right-two" style="margin-left: 20px">
      <div class="table">列表2</div>
    </tl-corner-mark>
  </div>
</template>

<!-- <style lang='scss' scoped>
.corner-mark {
  display: flex;
  .table {
    width: 400px;
    height: 104px;
    background: #f7f9fb;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style> -->
```
:::

### 三角形角标

三角形角标通常位于列表的表头位置，醒目、识别度高，常起到警示和提醒的作用

:::demo `shape`值为：`triangle`时表现为单层三角形角标，`overlapTriangle`时表现为叠层三角形角标。默认值为长方形`rectangular`角标。单层角标时`text`为角标内的值,叠层角标时顶部的小三角文字通过`text`设置,大三角形文字通过`extraText`设置

```html
  <div class="corner-mark">
    <tl-corner-mark text="回退" shape="triangle" class='left-one'>
      <div class="table">列表1</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" extra-text="等待处理" shape="overlapTriangle" class="right-two" style="margin-left: 20px">
      <div class="table">列表2</div>
    </tl-corner-mark>
  </div>

<style lang='scss' scoped>
.corner-mark {
  display: flex;
  .table {
    width: 400px;
    height: 104px;
    background: #f7f9fb;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>
```
:::

### 角标内置建议颜色

角标内置了几种高饱和度的常用颜色，也可以自己去复写css自定义颜色

:::demo 内置颜色值可分为：`primary`、`success`、`info`、`warning`、`danger`，长方形和单层三角形下默认值为`danger`，叠层三角形时，叠层角标时顶部的小三角颜色通过`type`设置,默认为`danger`;大三角形文字通过`extra-type`设置，默认为`warning`

```html
  <div class="corner-mark">
    <tl-corner-mark text="回退" type="primary">
      <div class="table">列表1</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" type="success">
      <div class="table">列表2</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" type="warning" shape="triangle">
      <div class="table">列表3</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" extra-text="等待处理" type="info" shape="overlapTriangle">
      <div class="table">列表4</div>
    </tl-corner-mark>
    <tl-corner-mark text="回退" extra-text="等待处理" type="success" extra-type="primary" shape="overlapTriangle">
      <div class="table">列表5</div>
    </tl-corner-mark>
  </div>

<style lang='scss' scoped>
.corner-mark {
  display: flex;
  justify-content: space-between;
  .table {
    width: 130px;
    height: 104px;
    background: #f7f9fb;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>
```
:::


### Attributes
参数 | 说明 | 类型 | 可选值 | 默认值
|------------- |---------------- |---------------- |---------------------- |-------- |
shape | 角标形状 | String | rectangular(长方形)/triangle(三角形)overlap-triangle(叠层三角形) | rectangular
direction | 分水平或垂直模式，只在shape='rectangular'下生效 | String | horizontal/vertical | horizontal
placement | 角标处于边角的位置 | String | top left/top right/bottom left/bottom right | top left
triangle-width | 长方形、单层三角形以及叠层时小三角形的角标宽度 | String/Number | - | 48
extra-triangle-width | 叠层时大三角形的角标宽度 | String/Number | - | 78
type | 长方形、单层三角形以及叠层时小三角形的角标类型 | String | primary/success/info/warning/danger | danger
extra-type | 叠层时大三角形的角标类型 | String | primary/success/info/warning/danger | warning
text | 长方形、单层三角形以及叠层时小三角形的文字 | String | - | -
extra-text | 叠层时大三角形的角标文字 | String | - | -
icon | 角标里面的图标，只在shape='rectangular'下生效 | String | - | -

### Slot
参数 | 说明 | 类型 | 可选值 | 默认值
|------------- |---------------- |---------------- |---------------------- |-------- |
Slot | 非具名插槽，可直接自定义长方形角标内容 | - | - | -
