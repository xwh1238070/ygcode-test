## Screen 双屏弹窗

打开新窗口，并弹出到副屏幕中。（注： 仅firefox可用）

### 基础用法

yjpl-screen 打开新窗口，并弹出到副屏幕中。（注： 仅firefox可用）

```ts
<template>
    <page>
        <button @click="openUrl">{{msg}}</button><br/>
        {{tips}}
    </page>
</template>
<script>
import {YJPage} from 'yjpl-core';
import Screen from 'yjpl-screen';
export default class DoubleScreen extends YJPage {
    constructor() {
        super();
        this.sc = new Screen("https://www.ygsoft.com", {});
    }
    data() {
        return {
            msg: '远光主页',
            tips: '只有firefox浏览器才能打开到副屏'
        }
    }
    methods() {
        return {
            openUrl() {
                this.sc.open();
            }
        }
    }
}
</script>
```

### yjpl-screen
| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| url | 打开的页面地址 | string | — | false |
| option | 打开的页面的相关属性 | object | — | {} |
