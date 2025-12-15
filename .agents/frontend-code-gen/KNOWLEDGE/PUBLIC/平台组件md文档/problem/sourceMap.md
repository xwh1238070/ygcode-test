# 如何在生产环境进行源码调试？

**请勿将 `.map` 文件部署至生产环境!!!**

## 配置source-map打包路径

**source-map文件与压缩混淆代码分离，方便保存一份源码，以便后面生产环境使用。**
文件名可自己设置规则，与js文件命名规则一致。

```js
  // yjpl.config.js中添加代码
  module.exports = {
    productionSourceMap: true
  }
```

## 打包目录

**打包后得目录中将会含有`maps`文件夹, 其中包含css和js得map源码，只需将`maps`保存并移除，其他文件不变，更新到服务器即可。**

（注：如未产生 `maps` 文件夹，请将 `@yjpl/cli-service` 更新至最新版本。）

下面是打包出来得目录结构

<!-- <img :src="$withBase('/yjpl/maps.jpg')">   -->

```js

dist
├─ css
|  ├─ app.css
|  ├─ ...
├─ fonts
├─ img
├─ js
|  ├─ app.js
|  ├─ ...
|  ├─ css
|  |   ├─ app.css.map
|  |   ├─ ...
|  ├─ js
|  |   ├─ app.js.map
|  |   ├─ ...
├─ favicon.ico
├─ index.html
maps('重点')
```

## 生产环境使用方式（谷歌浏览器）

* 首先查看报错文件，或者是知道要查看源码得文件（一般是js文件），需要知道js文件名
* 谷歌浏览器控制台中，**`Sources`（源码）菜单（左下）**中有一个**`FileSystem`（文件系统）**，在文件系统中上传maps文件夹（也可以只上传需要的源码文件）
<!-- <img :src="$withBase('/yjpl/sources.jpg')">   -->

* **上传时谷歌浏览器会在上方提示信息，同意即可**
* 上传完成，FileSystem（文件系统）中会显示目录文件，**找到相应map文件，右键**，会有一个 **`Copy link address`（复制文件地址）菜单，点击复制地址**
<!-- <img :src="$withBase('/yjpl/copyAddress.jpg')">   -->

* 在 **`Sources`中间代码区域，右键，会有一个Add source map...菜单，点击添加url，添加完成即可查看源码。** 添加完成后错误信息直接会转换为源码地址，无需刷新。
<!-- <img :src="$withBase('/yjpl/addSourceMap.jpg')">  -->
<!-- <img :src="$withBase('/yjpl/addSourcesMapUrl.jpg')">  -->
