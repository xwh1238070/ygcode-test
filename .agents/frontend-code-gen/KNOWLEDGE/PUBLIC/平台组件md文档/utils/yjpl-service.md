# Service 请求工具类

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-service
```

### 2.在代码中引入

``` js
import Service from 'yjpl-service';
```

## 使用样例

### restful get请求

``` js
import Service from 'yjpl-service';

async getData(url, params, dataType, headers) => {
  let res = await Service.doGet(url, params, dataType, headers);
  return res.data;
}
```

| 参数      | 说明          | 类型      | 可选值  | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | 请求地址 | string | — | — |
| params | 请求的数据 | object | — | — |
| dataType | 返回的数据类型 | string | arraybuffer \| blob \| document \| json \| text \| stream | json |
| headers | 自定义请求头 | object | — | — |

### restful post请求

``` js
import Service from 'yjpl-service';

async getData(url, params, dataType, headers) => {
  let res = await Service.doPost(url, params, dataType, headers);
  return res.data;
}
```

| 参数      | 说明          | 类型      | 可选值   | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | 请求地址 | string | — | — |
| params | 请求参数 | object | — | — |
| dataType | 返回的数据类型 | string | arraybuffer \| blob \| document \| json \| text \| stream | json |
| headers | 自定义请求头 | object | — | — |

### osgi get请求

``` js
import Service from 'yjpl-service';

async getData(serviceName, methodName, params, dataType, headers) => {
  let res = await Service.doGet(serviceName, methodName, params, dataType, headers);
  return res.data;
}
```

| 参数      | 说明          | 类型      | 可选值   | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| serviceName | 服务名 | string | — | — |
| methodName | 方法名 | string | - | - |
| params | 请求的数据 | object | — | — |
| dataType | 返回的数据类型 | string | arraybuffer \| blob \| document \| json \| text \| stream | json |
| headers | 自定义请求头 | object | — | — |

### osgi post请求

``` js
import Service from 'yjpl-service';

async getData(serviceName, methodName, params, dataType, headers) => {
  let res = await Service.doPost(serviceName, methodName, params, dataType, headers);
  return res.data;
}
```

| 参数      | 说明          | 类型      | 可选值    | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| serviceName | 服务名 | string | — | — |
| methodName | 方法名 | string | - | - |
| params | 请求的数据 | object | — | — |
| dataType | 返回的数据类型 | string | arraybuffer \| blob \| document \| json \| text \| stream | json |
| headers | 自定义请求头 | object | — | — |

### 统一请求方法

``` js
import Service from 'yjpl-service';

Service.request({
  url: '/test',
  method: 'post',
  params: {},
  responseType: 'json',
  headers: {}
}).then((res) => {
  console.log(res.data);
});
```

| 参数      | 说明          | 类型      | 可选值   | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | 请求地址 | string | — | — |
| method | 请求类型 | string | — | get \| post |
| serviceName | 服务名 | string | — | — |
| methodName | 方法名 | string | - | - |
| params | 请求的数据 | object | — | — |
| responseType | 返回的数据类型 | string | arraybuffer \| blob \| document \| json \| text \| stream | json |
| headers | 自定义请求头 | object | — | — |

### headers 预设参数

| 参数      | 说明          | 类型      | 可选值   | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| rjm | 是否开启国密加密返回数据 | boolean | — | false |
| ecpEncrypt | 是否开启 ecp 加密 | boolean | — | true |
| encryptMode | 是否开启gw加密 | boolean | — | true |
| debug | 是否开启调试 | boolean | — | false |
| vipAddress | osgi模式下，请求添加 vipAddress | string | - | - |

### getSecurity 获取加密状态

获取加密状态，需要在 yjpl 页面加载前调用，请在 `main.ts` 中添加。

```ts
import config from './config/config.ts';
import Service from 'yjpl-service';

const render = () => {
  new Yjpl({
    skin,
    i18n,
    router,
    store,
    render: h => h(App)
  }).$mount('#app');
};

// 获取安全加密需要在页面加载前，否则会有请求不能正确开关安全加密
// vipAdress 替换为项目 vipAdress，不要以 / 开头
Service.getSecurity('vipAdress').finally(() => {
  render();
});
```

| 参数      | 说明          | 类型      | 可选值   | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| vipAdress | 项目的 `vipAdress` | string | — | — |

## 微化开关

当需要调用微化服务时，请在 `main.ts` 内开启微化开关。
```js
window._ecp_remote_service_micro = true;
```

## 请求开启国密4加密(Sm4-cbc)

通过设置RJM参数，开启请求返回数据加密，拦截器解密后返回数据

全局开启

```ts
window._ecp_remote_service_rjm = '1';
```

单个请求开启，修改headers配置

```ts
res = await Service.doGet(url, method, params, dataType, {'rjm': '1'});
res = await Service.doPost(url, method, params, dataType, {'rjm': '1'});
```
