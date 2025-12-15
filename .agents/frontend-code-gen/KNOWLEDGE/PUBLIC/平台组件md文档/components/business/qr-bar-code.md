## QrCodeBar 条码

将字符串转成对应的条形码或者二维码。

### 基础二维码

字符串转换成二维码。

:::demo 传入`text`值，将值转换成二维码。为避免渲染异常，应该给每个二维码设置一个唯一的`ref-id`

```html
<template>
  <tl-qr-bar-code ref-id="qrcode1" text="hello world"></tl-qr-bar-code>
</template>
```
:::

### 二维码可设置宽高，背景色和条纹颜色

:::demo `color-dark`设置二维码深色条纹的颜色，`color-light`设置二维码浅色条纹的颜色
```html
<template>
  <div class="qrcode-demo">
    <tl-qr-bar-code
      ref-id="qrcode2-1"
      text="hello code"
      width="50"
      height="50"
      color-dark="#aeb654"
    >
    </tl-qr-bar-code>
    <tl-qr-bar-code
      class="light-qrcode"
      ref-id="qrcode2-2"
      text="hello code"
      width="50"
      height="50"
      color-light="#aeb654"
    >
    </tl-qr-bar-code>
  </div>
</template>

<style>
  .qrcode-demo {
    display: flex;
  }
  .qrcode-demo .light-qrcode {
      margin-left: 100px;
    }
</style>

```
:::


### 基础条形码

字符串转换成条形码。

:::demo QrBarCode 设置`type`为barcode即可设置成条形码；传入`text`值，将值转换成条形码；设置`format`参数即可设置条形码类型；为避免渲染异常，应该给每个条形码设置一个唯一的`ref-id`。

```html
<template>
  <div>
    <tl-qr-bar-code
      type="barcode"
      ref-id="brcode3-1"
      text="hello world"
      format="CODE39"
    >
    </tl-qr-bar-code>
    <tl-qr-bar-code
      type="barcode"
      ref-id="brcode3-2"
      text="hello world"
      format="CODE128"
    >
    </tl-qr-bar-code>
  </div>
</template>

```
:::

### 条形码样式

更改条纹的宽高

:::demo `width`改变条纹的宽度，`height`改变条纹的高度

```html
<template>
  <tl-qr-bar-code
    type="barcode"
    ref-id="brcode4"
    text="hello world"
    width="4"
    heigth="10"
  >
  </tl-qr-bar-code>
</template>


```
:::

### 条形码可设置背景色和条纹颜色

字符串转换成二维码。

::: demo 传入text值，将值转换成二维码。为避免渲染异常，应该给每个二维码设置一个唯一的ref-id

```html
<template>
  <tl-qr-bar-code type="barcode" ref-id="qrcode2" text="hello world" background="#aeb654" lineColor="#ffffff"></tl-qr-bar-code>
</template>
```
:::

### 条形码带文字

条形码可设置文字标题并可以自定义文字属性

::: demo `display-value`为true时显示文字，`title`设置文字，`font-size`设置文字大小，`text-align`设置文本的水平对齐方式，`text-position`设置文本的垂直对齐方式，`text-margin`设置条形码和文本之间的间距，`font-options`配置文字加粗体或变斜体等属性

```html
<template>
  <tl-qr-bar-code
    type="barcode"
    ref-id="brcode6"
    text="hello world"
    :display-value="true"
    title="hello world"
    :font-size="16"
    text-align="left"
    text-position="bottom"
    text-margin="5"
    font-options="bold italic"
  >
  </tl-qr-bar-code>
</template>
```
:::

### Attributes
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
type | 条码类型，二维码或条形码 | String | qrcode / barcode | qrcode

### QRCode Attributes
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
ref-id | 避免渲染异常，传入的唯一标识符 | String | - | tlqrcode
text | 二维码的内容（链接或字符串） | String | - | -
width | 二维码的宽度 | String / Number | - | 150
height | 二维码的高度 | String / Number | - | 150
color-dark | 深色条纹的颜色 | String | - | #000
color-light | 浅色条纹的颜色 | String | - | #fff
background | 背景色 | String | - | #fff

### BarCode Attributes
参数 | 说明 | 类型 | 可选值 | 默认值
| --------------- | -------- | -------- | --------------- | ------ |
ref-id | 避免渲染异常，传入的唯一标识符 | String | - | tlbarcode
text | 条形码的内容 | String | - | hello world
format | 条形码类型，如：CODE39、CODE128等 | String | - | CODE39
width | 条纹块宽度 | String / Number | - | 150
height | 条纹块高度 | String / Number | - | 150
line-color | 条码颜色 | String | - | #000
background | 背景色 | String | - | #fff
margin | 条码周围的空白边距 | String / Number | - | 5
display-value | 条形码下方是否显示文字 | Boolean | - | false
title | 条形码下方显示的文字 | String | - | hello world
font | 条形码下方显示的文字字体 | String | - | Microsoft YaHei
font-size | 条形码下方显示的文字字体大小(font必须要设置值，此属性才生效) | String / Number | - | 14
text-align | 文本的水平对齐方式 | String | left / right | left
text-position | 文本的垂直对齐方式 | String | top / bottom | bottom
text-margin | 条形码和文本之间的间距 | String / Number | - | 5
font-options | 文字加粗体或变斜体等属性配置,如：'bold italic' | String | - | bold