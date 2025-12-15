# Interceptor 拦截器工具类

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-interceptor
```

### 2.在代码中引入

``` js
import yjplInterceptor from 'yjpl-interceptor';
```

## 使用样例

``` js
import Interceptor from 'yjpl-interceptor';

const it = new Interceptor();

/*
* 注册请求后拦截处理
* return result: 请求返回的结果
*/
it.register((result: any) => {

});

```
## 常用事件

```js
// 注册请求后拦截处理
it.register((result: any) => {
    // result 是接口的信息，加上接口返回的数据， data + headers + request + status
});

// 注册请求前拦截处理
it.registerBefore((result: any) => {
    // result 是发起请求前传入的数据
});

// 注册异常拦截处理
it.registerCatch((err: any) => {
    // err 是请求的错误信息
});

// 清空已注册处理
it.clearAll();

```
## 所有事件



| 事件名称      | 说明          | 参数      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| register | 注册请求后拦截处理 | Funtion | — | — |
| registerBefore | 注册请求前拦截处理 | Funtion | — | — |
| registerCatch | 注册异常拦截处理 | Funtion | — | — |
| clearAll | 清空已注册处理 | Funtion | — | — |
| registerEncrypt | 注册加密拦截处理 | Funtion | — | — |
| registerDecrypt | 注册解密拦截处理 | Funtion | — | — |
| registerBeforeExt | 注册请求前数据拦截处理(返回请求的数据) | Funtion | — | — |









