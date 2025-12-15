# Lock 令牌锁

令牌锁插件

### 基础用法

yjpl-lock 用于控制指定功能或菜单加锁。

```ts
<template>
    <page>
        <button @click="openUrl" v-ctrl-state="lockValue">{{msg}}</button><br/>
        {{tips}}
    </page>
</template>
<script>
import {YJPage} from 'yjpl-core';
import Lock from 'yjpl-lock';
export default class DoubleScreen extends YJPage {
    constructor() {
        super();
        this.lock = new Lock("jt/mapp/sample");
    }
    data() {
        return {
            lockValue: this.tleadState.DEFAULT,
            msg: '打开远光主页',
            tips: '通过令牌控制按钮的状态'
        }
    }
    mounted() {
        var lengTime = 30;
        this.lock.tryLock("006023", "a", "abcd.1234", "taskName", lengTime, true)
        .then(res => {
            if(window.console) {
                console.info(res.data);
                if(res.data === false || res.data === 'false') {
                    this.lockValue = this.tleadState.DISABLED;
                }
            }
        })
        .catch(err=> {
            if(window.console) {
                console.error(err);
            }
        });
    }
    methods() {
        return {
            openUrl() {
                if(window.console) {
                    console.info('btn click');
                }
            }
        }
    }
    destroyed() {
        this.lock.releaseLock("006023", "a", "abcd.1234");
        .then(res => {
            if(window.console) {
                console.info('解锁成功', res.data);
                if(res.data === true || res.data === 'true') {
                    this.lockValue = this.tleadState.DEFAULT;
                }
            }
        });
    }
}
</script>
<style>

</style>
```

### UI3.0用法
更新 necp.core.web jar包，js中引入ecp.lock
```javascript
require(['ecp.lock'], function (Lock) {
	var lock = Lock('/ecp/mapp/demo');
	// lock.setVipAddress('/ecp/mapp/demo'); // 动态设置vipAddress
	console.log(lock.tryLock("006023", "a", "abcd.1234", "taskName", null));
	console.log(lock.releaseLock("006023", "a", "abcd.1234"));
	console.log(lock.continueLock("006023", "a", "abcd.1234"));
});
```

### yjpl-lock Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| setVipAddress | 设置vipAddress地址 | vipAddress: string |
| tryLock | 加锁 | code: 锁码, suffix: 动态锁, securityKey: 密码, taskName: 任务名称, howLongSecond?: 时长, isContinue: 自动继锁 |
| releaseLock | 解锁 | code: 锁码, suffix: 动态锁, securityKey: 密码|
| continueLock | 继锁 | code: 锁码, suffix: 动态锁, securityKey: 密码|
| getLockInfo | 获取锁令牌 | code: 锁码, suffix: 动态锁|
