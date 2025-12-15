# 单元测试
## 测试框架介绍
- jest
  基于node.js的javascript测试运行环境
- chai
  是一个针对 node.js 和浏览器的行为驱动测试和测试驱动测试的诊断库，可与任何 JavaScript 测试框架集成。


## 环境搭建
执行下载命令引入单元测试相关依赖

``` shell
npm install yjpl-test@8.5.0 @vue/cli-plugin-unit-jest@5.0.8 -D
```
配置文件

在项目根目录下创建 `jest.config.js`，并将一下代码添加至文件内。

``` javascript
const config = require('yjpl-test/config/jest.config');

module.exports = config;
```

在package.json的script中配置运行命令

``` json
{
  "scripts": {
    "test": "yjpl-cli-service test:unit --coverage"
  }
}
```

执行 `npm run test` 运行单元测试。