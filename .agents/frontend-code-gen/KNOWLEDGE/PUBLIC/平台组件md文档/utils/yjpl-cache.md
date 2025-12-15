# Cache 统一缓存工具类

提供了包括本地存储缓存（localStorage、sessionStorage、cookie）和内存缓存的增删查操作在内的工具类方法，对浏览器的localStorage和sessionStorage缓存值进行加密处理，当缓存超出浏览器大小限制时会将优先级低的缓存清理出去而保留优先级高的缓存。

### 基础用法

在使用统一缓存工具时需要引入yjpl-cache组件

```ts
<template>
    <page>
        <button @click="setCache">设置localStorage缓存</button><br/>
    </page>
</template>
<script>
import CacheUtil from 'yjpl-cache';
export default class YjplCacheDemo extends YJPage {
    
    data() {
        return {
            btnName: '设置localStorage缓存'
        }
    }
    methods() {
        return {
            setCache() {
                const key = 'userId';
                const value = 'zhangsan@ygsoft.com'
                CacheUtil.setGlobalCache(key, value);
            }
        }
    }
}
</script>
```

### yjpl-cache Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| setGlobalCache | 设置全局缓存的方法 |<b>key（键）</b>: 必填，字符串类型；<br><b>value（值）</b>：必填，字符串类型；<br><b>priority（优先级）</b>：可选，分三级：1,、2、3，默认为1；<br><b>storageType（缓存类型）</b>：可选，字符串类型，值有local和session，默认为local；<br><b>expires（过期时间）</b>：可选，数值类型，值为时间戳，比如new Date().getTime()|
| getGlobalCache | 获取全局缓存的方法 | <b>key（键）</b>: 必填，字符串类型；<br><b>storageType（缓存类型）</b>：可选，字符串类型，值有local和session，默认为local。同时兼容了原生storage缓存获取方法。|
| getAllGlobalCaches | 获取全部全局缓存的方法 | <b>storageType（缓存类型）</b>：可选，字符串类型，值有local和session，默认为local |
| removeGlobalCache | 删除全局缓存的方法 |<b>key（键）</b>: 必填，字符串类型；<br><b>storageType（缓存类型）</b>：可选，字符串类型，值有local和session，默认为local |
| removeAllGlobalCache | 清除所有全局缓存 | <b>storageType（缓存类型）</b>：可选，字符串类型，值有local和session，默认为local |
| setPageCache | 设置页面缓存的方法 | <b>key（键）</b>: 必填，字符串类型；<br><b>value（值）</b>：必填，字符串类型 |
| getPageCache | 获取页面缓存的方法 | <b>key（键）</b>: 必填，字符串类型 |
| removeAllPageCache | 清除所有页面级缓存 | void |
| setCookie | 设置cookie的方法 | <b>name（名称）</b>: 必填，字符串类型；<br><b>value（值）</b>：必填，字符串类型；<b>days（过期天数）</b>：可选，数值类型 |
| getCookie | 获取cookie的方法 | <b>name（名称）</b>: 必填，字符串类型 |
| getAllCookie | 获取所有cookie的方法 |  |
| removeCookie | 删除cookie的方法 | <b>name（名称）</b>: 必填，字符串类型 |
| subscribe | 订阅事件 | <b>key（键）</b>: 必填，字符串类型；<br><b>callback（回调函数）</b>：必填，函数 |
| publish | 发布事件 | 参数与setGlobalCache保持一致，如果需要触发订阅事件，使用publish设置缓存 |

