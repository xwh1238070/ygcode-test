# 组件库搭建 ^(8.1.0)

## 通过脚手架工具创建
使用 `yjpl-cli` 在 `现有的应用项目` 内创建组件库项目。

```shell
npm i yjpl-cli@8.1.0-SP -g
yjpl components
```

通过命令行工具，回答相应的问题初始化组件库工程。
成功后，会在项目的 `src` 同级目录下，生成 `components` 目录。

## 组件库打包发布
在项目中添加脚本命令。

```json
{
  "scripts": {
    "build:comp": "yjpl-cli-service components",
    "build": "npm run build:comp && yjpl-cli-service build"
  }
}
```

执行 `npm run build:comp` 命令后，组件库会打包到 `public` 目录下，执行 `npm run build` 命令进行打包，组件库会打包到 `dist` 目录下，随应用静态资源一起上传到服务器。

如果需要发布 `npm` 包，可以进入 `components` 目录下执行 `npm publish` 进行发布。

## 组件库目录规范
组件目录请严格按以下结构进行开发，组件库编译会与目录结构相关。 

```html
|- public                       公共资源
|- components                   组件目录
|     |- ...                    单个组件文件夹
|     |     |- src              组件存放目录
|     |     |     |- 组件yjpl    组件存放目录
|     |     |- index.ts         组件入口文件
|     |- package.json           组件库信息文件
|- src                          项目源码目录
|- yjpl.config.js               项目编译配置文件
|- package.json                 项目信息文件
```

## 组件规范
每个组件放在一个单独的文件夹内，组件统一放在文件夹内的 `src` 目录内，由统一的 `index.ts` 进行导出。

### 组件和文件夹名称
组件名称统一为业务前缀加组件，例如：`yj-button`、`zt-input`。

### 组件规范样例
组件：

```html
<template>
... html 实现
<template>

<script>
import { YJPage } from 'yjpl-core';

// class名称统一为组件名的大驼峰格式
export default class 组件大驼峰名称 extends YJPage {
... 实现代码
}
</script>
```

`index.ts` 入口文件：

```typescript
import YjButton from './src/yj-button.yjpl';

YjButton.install = (Yjpl: any) => {
  Yjpl.component(YjButton.name, YjButton);
};

export default YjButton;

```

### 组件依赖规范
组件仅允许引入和使用已发布 `CDN` 的依赖包和组件包内的文件，一旦引入非上述依赖和文件，编译时会报错提示，请按照提示进行调整。

### 组件内依赖非本包组件
框架支持通过外链的形式引入组件，可以通过使用 `yjpl-core` 内的 `YJImportComponent` 方法进行调用。

```html
<template>
  <!-- 引入成功后，渲染组件 -->
  <demo-test v-if="renderComp"></demo-test>
<template>

<script>
import { YJPage, YJImportComponent } from 'yjpl-core';

export default class Test extends YJPage {
  data() {
    return {
      renderComp: false
    }
  }

  created() {
    // 引入组件
    YJImportComponent('组件资源路径url', 'DemoTest', this).then(() => {
      // 引入成功后，渲染组件
      this.renderComp = true;
    });
  }
}
</script>
```

### 组件静态资源大小规范
单个组件静态资源 `gzip` 后不得超过 `50kb`，如果有超过大小的，编译会进行错误提示，并给出过大的提示，请按照提示调整依赖。