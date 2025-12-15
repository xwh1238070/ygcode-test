## 微前端
    1. 微前端是什么？
     > 微前端是一种多个团队通过独立发布功能的方式来共同构建现代化 web 应用的技术手段及方法策略，简单来说，微前端就是不同项目独立开发的集合.

## 为什么不使用iframe?
    1. iframe 最大的特性就是提供了浏览器原生的硬隔离方案，不论是样式隔离、js 隔离这类问题统统都能被完美解决。但他的最大问题也在于他的隔离性无法被突破，导致应用间上下文无法被共享，随之带来的开发体验、产品体验的问题。
        * url 不同步。浏览器刷新 iframe url 状态丢失、后退前进按钮无法使用。
        * UI 不同步，DOM 结构不共享。想象一下屏幕右下角 1/4 的 iframe 里来一个带遮罩层的弹框，同时我们要求这个弹框要浏览器居中显示，还要浏览器 resize 时自动居中.
        * 全局上下文完全隔离，内存变量不共享。iframe 内外系统的通信、数据同步等需求，主应用的 cookie 要透传到根域名都不同的子应用中实现免登效果
        * 慢。每次子应用进入都是一次浏览器上下文重建、资源重新加载的过程
## 微前端方案
    1. 由single-spa发起的微前端潮流，single-spa 仅仅是一个子应用生命周期的调度者，需要开发者在加载自己子 App 的时候实现的，要不就是通过一些第三方工具实现。
    2. qiankun，基于single-spa的一个实现库，类似jquery一样的js库，目的就是为了帮助实现微前端的架构，里面仅包含几个方法，包括注册子应用，加载子应用，子应用生命周期和动态加载子应用
    3. 现在一般使用qiankun作为微前端方案，毕竟阿里已经做了一系列操作完善qiankun，作为现在最容易对接和扩展的方案。

## qiankun
    1. qiankun的核心理念
    由于主应用微应用都能做到技术栈无关，qiankun 对于用户而言只是一个类似 jQuery 的库，你需要调用几个 qiankun 的 API 即可完成应用的微前端改造。同时由于 qiankun 的 HTML entry 及沙箱的设计，使得微应用的接入像使用 iframe 一样简单。
    2. 微前端的核心目标是将巨石应用拆解成若干可以自治的松耦合微应用，而 qiankun 的诸多设计均是秉持这一原则，如 HTML entry、沙箱、应用间通信等。这样才能确保微应用真正具备 独立开发、独立运行 的能力。

## qiankun集成
##### 1. 首先在主应用中安装qiankun
> yarn add qiankun 或者 npm i qiankun -s

##### 2. 在主应用中注册微应用

```javascript
import { registerMicroApps, start } from 'qiankun';

registerMicroApps([
  {
    name: 'yjplApp1', // name相当于key值，
    entry: process.env.NODE_ENV === 'production' ? '/demo1' : `//localhost:8081`, // entry 相当于引入项目的路径， 在这里'/demo1'是打包配置后，正式环境访问地址，这个地址需与activeRule区别开，不然刷新后访问的就是子项目了
    container: '#container', // container 就是引入项目的标签
    activeRule: '/yjplApp1', // activeRule 就是引入项目的路径，当什么路径引入什么项目
  },
  {
    name: 'yjplApp2',
    entry: process.env.NODE_ENV === 'production' ? '/demo2' : `//localhost:8082`,
    container: '#container',
    activeRule: '/yjplApp2',
  }
]);
```

#### 子应用配置
##### 1. 首先配置生命周期
```js
    /**
 * bootstrap 只会在微应用初始化的时候调用一次，下次微应用重新进入时会直接调用 mount 钩子，不会再重复触发 bootstrap。
 * 通常我们可以在这里做一些全局变量的初始化，比如不会在 unmount 阶段被销毁的应用级别的缓存等。
 */


if ((window as any).__POWERED_BY_QIANKUN__) {  // 方便独立开发，配置publicpath
  __webpack_public_path__ = (window as any).__INJECTED_PUBLIC_PATH_BY_QIANKUN__;
}
if (!(window as any).__POWERED_BY_QIANKUN__) { // 在有qiankun的集成下，不render。没有qiankun的时候，使用rendere。便于独立开发
  Service.getSecurity('vipadress').then((res: any) => {
    (window as any)._ecp_remote_security_state = res.data;
  }).finally(() => {
    render();
  });
}

// bootstrap 只会在微应用初始化的时候调用一次，下次微应用重新进入时会直接调用 mount 钩子，不会再重复触发 bootstrap。通常我们可以在这里做一些全局变量的初始化，比如不会在 unmount 阶段被销毁的应用级别的缓存等。
export async function bootstrap() {
  console.log('bootstrap');
}

/**
 * 应用每次进入都会调用 mount 方法，通常我们在这里触发应用的渲染方法
 * @param props props就是主项目传过来的数据
 */
export async function mount(props: any) {
  Service.getSecurity('vipadress').then((res: any) => {
    (window as any)._ecp_remote_security_state = res.data;
  }).finally(() => {
    render();
  });
}

/**
 * 应用每次 切出/卸载 会调用的方法，通常在这里我们会卸载微应用的应用实例
 */
export async function unmount() {
  // instance就是yjpl实例（项目实例）
  // instance = new Yjpl({
  //   skin,
  //   i18n,
  //   router,
  //   store,
  //   render: h => h(App)
  // }).$mount('#demo1');
  instance.$destroy();
}

/**
 * 子应用更新
 */
export async function update(props: any) {
  console.log('update props', props);
}
```
##### 2. 配置打包方式
```ts
    const packageName = require('./package.json').name; // 取项目名称，可自定义
    configureWebpack:{
        output: {
            library: `${packageName}-[name]`, // 设置打包文件，便于主应用调用
            libraryTarget: 'umd',
            jsonpFunction: `webpackJsonp_${packageName}`,
        },
    },
```

如果持续报错`You need to export the functional lifecycles in xxx entry`，检查主应用和微应用是否使用了 AMD 或 CommonJS 模块化。检查方法：单独运行微应用和主应用，在控制台输入如下代码：(typeof exports === 'object' && typeof module === 'object') || (typeof define === 'function' && define.amd) || typeof exports === 'object'，如果返回 true，则说明是这种情况，主要有以下解决办法
```ts
const packageName = require('./package.json').name;
module.exports = {
  output: {
    library: `${packageName}-[name]`,
-    libraryTarget: 'umd',
+    libraryTarget: 'window',
    jsonpFunction: `webpackJsonp_${packageName}`,
  },
};
```

##### 3. router的mode影响base，Hash路径模式不用处理
```ts
// 需要区分基本路径，使用qiankun的情况下需加上registerMicroApps中的activeRule
router = new YjplRouter({
  mode: 'history',
  base: (window as any).__POWERED_BY_QIANKUN__ ? '/yjplApp1/' : '/',
  routes
});
```
##### 4. 子项目在局部加载另外一个子项目
```ts
// 子项目需安装qiankun，直接loadMicroApp就行，另外一个子项目需自行配置路由，因为加载是从根路劲加载
this.microApp = loadMicroApp({
  name: 'yjplApp1',
  entry: process.env.NODE_ENV === 'production' ? '/demo1' : `//localhost:8081`,
  container: '#appContainerDemo1', // load另外一个子项目到什么位置
  props: { data : { defaultPath: '/about' } } // 可传值到另外一个子项目，另外一个子项目接收并作特殊处理
});
```
##### 5. yjpl和vue不使用浏览器路径展示页面（处理子项目加载另外一个子项目，因为浏览器路径只有一个，不能匹配多个，只能特殊处理另外一个子项目的路由）
```ts
// router mode使用abstract，用法与history一致，需处理基本路径
router = new YjplRouter({
  mode: 'abstract',
  base: (window as any).__POWERED_BY_QIANKUN__ ? '/yjplApp1/' : '/',
  routes
});
```

##### 6. 提取公共资源
qiankun现在提取公共资源有两种配置
###### 1. 使用CDN方式，浏览器同一个路径链接资源会有缓存，不会重复加载
###### 2. 在主项目里面定义公共资源，子项目使用定义的这一份公共资源，这种方式下的子项目生产环境不能单独访问
```ts
// 在主项目中定义资源名称，子应用直接使用
import * as YjplUi from 'yjpl-ui';
import * as Yjpl from 'yjpl';
import * as  YjplRouter from 'yjpl-router';

function loadCommonFile() {
    (window as any).Yjpl = Yjpl;
    (window as any).YjplRouter = YjplRouter;
    (window as any).YjplUi = YjplUi;
}

// 子应用 需配置打包externals
// externals会把 import Yjpl from 'yjpl';  这种写法转成var yjpl = window.Yjpl
config.externals({
  'yjpl': 'Yjpl', // 后面的value值，需要根主项目的资源名称对应
  'yjpl-router': 'YjplRouter',
  'yjpl-ui': 'YjplUi'
});
```

#### qiankun方法
##### 1. registerMicroApps 主应用注册子应用
```ts
import { registerMicroApps } from 'qiankun';

registerMicroApps(
  [
    {
      name: 'app1',
      entry: '//localhost:8080', // 加载项目的地址，不可与activeRule相同
      container: '#container',
      activeRule: '/demo1', // 激活项目的路由，当主项目处于什么路由就加载对应的项目
      loader: (loading: boolean)=>{}, // loading状态发生改变时调用
      props: { // 传数据
        name: 'test',
      },
    },
  ],
  { // 生命周期
    beforeLoad: (app) => console.log('before load', app.name), // 加载之前
    beforeMount: [(app) => console.log('before mount', app.name)], // 载入之前
    afterMount: [(app) => console.log('after mount', app.name)], // 载入之后
    beforeUnmount: [(app) => console.log('before unmount', app.name)], // 卸载之前
    afterUnmount: [(app) => console.log('after unmount', app.name)] // 卸载之后
  },
);
```

##### 2. start qiankun
```ts
// 搭配预加载使用
import { start } from 'qiankun';
opt:{
  prefetch, // true 配置为 true 则会在第一个微应用 mount 完成后开始预加载其他微应用的静态资源
  // 配置为 'all' 则主应用 start 后即开始预加载所有微应用静态资源
  // 配置为 string[] 则会在第一个微应用 mounted 后开始加载数组内的微应用资源
  // 配置为 function 则可完全自定义应用的资源加载时机 (首屏应用及次屏应用)
  sandbox, // 是否开启沙箱模式，默认为true
  // { strictStyleIsolation?: boolean, experimentalStyleIsolation?: boolean }
  // 默认情况下沙箱可以确保单实例场景子应用之间的样式隔离，但是无法确保主应用跟子应用、或者多实例场景的子应用样式隔离。当配置为 { strictStyleIsolation: true } 时表示开启严格的样式隔离模式。这种模式下 qiankun 会为每个微应用的容器包裹上一个 shadow dom 节点，从而确保微应用的样式不会对全局造成影响
  singular, // 是否为单实例场景，单实例指的是同一时间只会渲染一个微应用。默认为 true
  // 可传方法 ((app: RegistrableApp<any>) => Promise<boolean>); 手动控制场景
  fetch, // 自定义的 fetch 方法
  getPublicPath, // (entry: Entry) => string - 参数是微应用的 entry 值。
  getTemplate, // (tpl: string) => string
  excludeAssetFilter, // (assetUrl: string) => boolean - 指定部分特殊的动态加载的微应用资源（css/js) 不被 qiankun 劫持处理。
}
start(opt);
```

##### 3.setDefaultMountApp 设置主应用启动后默认进入的微应用
```ts
import { setDefaultMountApp } from 'qiankun';

setDefaultMountApp('/demo1');
```

##### 4. runAfterFirstMounted 第一个微应用 mount 后需要调用的方法，比如开启一些监控或者埋点脚本
```ts
import { runAfterFirstMounted } from 'qiankun';

runAfterFirstMounted(() => startMonitor());
```

##### 5. loadMicroApp 手动加载子应用
```ts
// loadMicroApp(app, configuration?) // configuration与start的opt一致，没有预加载配置
import { loadMicroApp } from 'qiankun';
import React from 'react';

class App extends React.Component {
  containerRef = React.createRef();
  microApp = null;

  componentDidMount() {
    this.microApp = loadMicroApp({
      name: 'app1',
      entry: '//localhost:1234',
      container: this.containerRef.current,
      props: { brand: 'qiankun' },
    });
  }

  componentWillUnmount() {
    this.microApp.unmount();
  }

  componentDidUpdate() {
    this.microApp.update({ name: 'kuitos' });
  }

  render() {
    return <div ref={this.containerRef}></div>;
  }
}
```

##### 6. prefetchApps 预加载配置
手动预加载指定的微应用静态资源。仅手动加载微应用场景需要，基于路由自动激活场景直接配置 prefetch 属性即可。

```ts
import { prefetchApps } from 'qiankun';

prefetchApps([
  { name: 'app1', entry: '//localhost:7001' },
  { name: 'app2', entry: '//localhost:7002' },
]);
```

##### 7. addErrorHandler/removeErrorHandler, 异常处理
```ts
import { addGlobalUncaughtErrorHandler, removeGlobalUncaughtErrorHandler } from 'qiankun';

addGlobalUncaughtErrorHandler((event) => console.log(event));
removeGlobalUncaughtErrorHandler(handler);

```

##### 8. initGlobalState 定义全局状态
定义全局状态，并返回通信方法，建议在主应用使用，微应用通过 props 获取通信方法。
```ts
// 主应用
import { initGlobalState, MicroAppStateActions } from 'qiankun';

// 初始化 state
const actions: MicroAppStateActions = initGlobalState(state);

actions.onGlobalStateChange((state, prev) => {
  // state: 变更后的状态; prev 变更前的状态
  console.log(state, prev);
});
actions.setGlobalState(state);
actions.offGlobalStateChange();

// 子应用
// 从生命周期 mount 中获取通信方法，使用方式和 主应用 一致
export function mount(props) {
  props.onGlobalStateChange((state, prev) => {
    // state: 变更后的状态; prev 变更前的状态
    console.log(state, prev);
  });

  props.setGlobalState(state);
}
```

#### qiankun总结
qinkun是一个js库，无关框架语言，处理好加载路劲和激活路径就行，二者不可一样
尽量区分开发环境和生产环境配置，尽量支持独立开发，最后整合到主项目之下运行

