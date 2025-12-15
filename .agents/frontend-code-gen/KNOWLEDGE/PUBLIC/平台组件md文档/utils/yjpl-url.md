# Url 路径工具类

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-url
```

### 2.在代码中引入

``` js
import Url from 'yjpl-url';
```

## FmpPath 全局替换 fmp 地址方法

``` js
// 在 main 中使用
import Yjpl from 'yjpl';
import { FmpPath } from 'yjpl-url';

Yjpl.use(FmpPath, {
  // fmpList: {
  //   '/necp/mapp/demo': '/fmp-grm/mapp/demo'
  // }
});
```

### FmpPath Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| config | 项目的 config，参数为下列属性 | object | — | — |
| config.startString | 起始字符串，默认为 `/fmp-grm` | string | — | '/fmp-grm' |
| config.fmpList | fmp 映射列表，可自行扩展，需要时再进行配置 | object | — | - |
| config.addMember | 链接是否带 `member` | object | — | - |
| config.isStatic | 是否静态资源/url地址，是的话按照静态资源规则进行替换 | boolean | — | false |
| config.isStaticResource | 即使非fmp环境也进行替换（用于测试） | boolean | — | false |

## getFmpPath 获取fmp替换后请求

``` js
import { getFmpPath } from 'yjpl-url';

console.log(getFmpPath('/grm/necp/mapp/demo/test'));
// '/fmp-grmnecp.mapp.demo/member/test'
```

### replaceFmpPath Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | 需要替换的 `url` | string | — | — |
| config | 项目的 config，参数为下列属性 | object | — | — |
| config.startString | 起始字符串，默认为 `/fmp-grm` | string | — | '/fmp-grm' |
| config.fmpList | fmp 映射列表，可自行扩展，需要时再进行配置 | object | — | - |
| config.addMember | 链接是否带 `member` | object | — | - |
| config.isStatic | 是否静态资源/url地址，是的话按照静态资源规则进行替换 | boolean | — | false |
| config.isStaticResource | 即使非fmp环境也进行替换（用于测试） | boolean | — | false |

<!-- ## getVipAddres 获取 vipaddress

``` js
import { getVipAddres } from 'yjpl-url';

console.log(getVipAddres('/grm/necp/mapp/dqd/test'));
// '/necp/mapp/dqd/'
```

### getVipAddres Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | `url` 地址 | object | — | — | -->

<!-- ## getContextPah 获取contextpath

``` js
import { getContextPah } from 'yjpl-url';

console.log(getContextPah('/grm/necp/mapp/dqd/test'));
// '/grm'
```

### getContextPah Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | `url` 地址，可不传，不传则自动获取 | string | — | — | -->
