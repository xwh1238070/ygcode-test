# 兼容性处理

## yjpl版本
更新yjpl依赖至7.2.0/8.1.0/8.2.0/8.5.0及以上版本

## browserslistrc
检查项目中是否存在 .browserslistrc 文件，如果不存在请新建同名文件，并将下列代码复制进去
```
> 1%
last 2 versions
not ie <= 8
```

## babel.config.js
检查项目中是否存在babel.config.js，**兼容性配置不能使用.babelrc文件，只能用babel.config.js**，如果不存在请新建同名文件，并将下列代码复制进去
```js
module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  env: {
    test: {
      plugins: ['istanbul']
    }
  }
};

```

## babel-polyfill
检查 main.ts 内是否引入 babel-polyfill,**并且一定要在最上面引入**
```ts
import 'babel-polyfill';
```

## transpileDependencies
`transpileDependencies`是webpack对node_modules中依赖进行打包的配置，会根据browserslistrc中的配置，打包成低版本浏览器兼容的写法
在 yjpl.config.js 文件中配置 transpileDependencies 按下列配置，配置下该属性
```js
module.exports = {
  ...
  // 打包时 会将node_modules中的'yjpl-ui4.0-loader', 'yjpl-template'进行二次编译打包
  transpileDependencies: ['yjpl-ui4.0-loader', 'yjpl-template'],
  ...
}

```

## 查找问题
* 完成上述配置后执行打包
* 打包完成后使用编辑器单独打开 dist 文件夹，并全局搜索 const ,let , => , await等es6关键字（关键字后加空格）
<!-- <img :src="$withBase('/yjpl/compatibility.png')">   -->

* 如搜到相关代码，在node_modules里面搜索对应有特征的代码，查看该代码在哪个依赖内，并将依赖名称添加至 transpileDependencies 属性数组中。
* 添加完成后再次执行打包，并再次从查找问题开始操作，直到不再出现问题。
