# 打包分析工具安装

 1. 使用命令进行安装
  npm i webpack-bundle-analyzer -D

 2. 在 yjpl.config.js 的 chainWebpack 方法内添加以下代码
  ```js
  config.plugin('webpack-bundle-analyzer').use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin);
  ```

 3. 执行 npm run build

* *注意： 如无需分析时请注释改代码，不然 jenkins 构建会卡住。