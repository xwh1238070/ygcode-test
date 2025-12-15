# Security 安全工具类

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-security
```

### 2.在代码中引入

``` js
import Security from 'yjpl-security';
```

## isSecurity 获取安全加密开关状态

``` js
import Security from 'yjpl-security';

const isSecurity = Security.isSecurity();
// 返回布尔值。如果启用了安全加密，返回true,否则返回false

```

## ecm.sign 获取CM签名

``` js
import Security from 'yjpl-security';

const isSecurity = Security.ecm.sign();
// 返回CM签名字符串

```

## eaz.sign 获取加密后的数据

``` js
import Security from 'yjpl-security';

const isSecurity = Security.eaz.sign({id: 123, name: 'test', data: [1, 2, 3, 4]}, 'post');
// 返回加密后的数据对象
//  {
//      // MD5，可以验证数据有没有被篡改
//      AZ: 'ewcd732211bf3ba41348c28b34a2b4dc25c6ixm8',
//      RequestURI: '',
//      // 加密的参数
//      encryptedParam: {id: '#3#21#@3', name: '#stet#@4', data: ',#2#,#,#143#@7'}
//  }

```
### eaz.sign Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| params    | 请求参数 | any | — | — |
  method    | 请求方法   | string  | get, post, put, delete... | — |