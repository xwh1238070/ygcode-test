# I18n 国际化工具类
默认使用中文，若希望使用其他语言，则需要进行多语言设置。

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-i18n
```

### 项目引入
在 src/main.ts中引入

```js
import Yjpl from 'yjpl';
import i18n from './i18n'; //引入i18n

new Yjpl({ 
     i18n, 
     render: h => h(App)
}).$mount('#app');
```
## 目录结构

      |- src          开发源码目录
      |     |- i18n  国际化资源
      |     |        |- index.ts  国际化统一资源入口
      |     |        |- plugin.ts 载入YJPL原型中，导出i18n实例
      |     |        |- langs 语言包文件
      |     |        |        |- en-us.ts 英文
      |     |        |        |- zh-cn.ts 中文      
      |     |        |        |- index.ts  语言包资源入口

## 使用国际化
使用方法与vue-i18n一致
定义国际化资源文件
``` js
  // en-us.ts
  export default {
    welcome: 'welcome',
    english: 'english',
    chinese: 'chinese'
  };
  // zh-cn.ts
  export default {
    welcome: '欢迎',
    english: '英文',
    chinese: '中文'
  };
```
在yjpl页面使用，切换国际化语言，有以下两种方式：

1.  刷新页面

``` js
  import Utils from 'yjpl-utils';
  import config from '@config/config';

  Utils.setCookie(config.i18nCookieId, 'xxx', null, null);
  //  刷新页面的方式
  setTimeout(() => {
    window.location.reload();
  }, 500);
```
2. 不刷新页面
``` js
  /**
   * 将this.$t() 写到了data属性里，切换语言不起作用data是一次性生产的
   * 这么写只能是在 data 初始化的时候拿到这些被国际化的值，并不能响应变化
   * 解决办法是，将表达式写到computed属性里，不要写到data里
   * 
  **/
  import Utils from 'yjpl-utils';
  import config from '@/config/config';

  Utils.setCookie(config.i18nCookieId, 'xxx', null, null);

  //方法一
  import i18n from '../../i18n'; // 引入i18n全局实例（通常在非YJPL文件使用）
  i18n.locale = 'xxx'

  //方法二
  this.$i18n.locale = 'xxx'

```

3. 通用使用方法 （适用非YJPL文件）
``` js
  //采用引入全局I18N实例
  import i18n from '../../i18n';
  
  //更改cookie不变，和以前一致
  import Utils from 'yjpl-utils';
  import config from '@/config/config';
  Utils.setCookie(config.i18nCookieId, 'xxx', null, null);
  
  //操作的时候直接使用i18n实例直接操作，此方法等同 this.$i18n.locale = 'xxx'
  i18n.locale = 'xxx'

  // data内部
  data(){
    return {
      title: i18n.t('title') // 此方法等同 this.$t('title')
    }
  }
```
``` html
<template>
 <page class="index">
   <!-- 刷新页面生效 -->
  <div>{{$t('welcome')}}</div>
  <!-- 不刷新页面生效 -->
  <div>{{tip}}</div>
  <!-- 通过 i18nModel 使用 -->
  <div>{{i18nModel.welcome}}</div>
  <tl-select v-model="dataModel.i18n"  
    :data="dataModel.i18nData" 
    id-field="id" 
    text-field="text" 
    @change="changeI18n"/>
 </page>
</template>
<script lang="ts">
import {
 YJPage,
} from 'yjpl-core';
import Utils from 'yjpl-utils';
import config from '@/config/config';
export default class YwdjView extends YJPage {
  data() {
    return {
      dataModel: {
        // 需要刷新页面，才能生效
        i18nData: [{
          id: 'zh-cn',
          text: this.$t('chinese')
        }, {
          id: 'en-us',
          text: this.$t('english')
        }],
        i18n: this.$i18n.locale
      }
    };
  }

  computed(){
    return {
      tip(){
        return this.$t('welcome');
      }
    }
  }

  methods() {
    return {
      changeI18n() {
        // 通过loadSkin切换主题
        if (this.$i18n.locale !== this.dataModel.i18n) {
          Utils.setCookie(config.i18nCookieId, this.dataModel.i18n, null, null);
          //  刷新页面的方式
          // setTimeout(() => {
          //   window.location.reload();
          // }, 500);
          // 不刷新页面的方式
          this.$i18n.locale = this.dataModel.i18n
        };
      }
    };
  }
}
</script>
```

4. 新增兼容组件内部国际化
``` js
// 以下为i18n目录中的plugin.ts
import Utils from 'yjpl-utils';
import config from '../config/config';
import YjplI18n from 'yjpl-i18n';
import langs from './langs';

// + 新增引入组件库国际化语言包
import en from 'yjpl-ui/lib/locale/lang/en-US.js';
import zh from 'yjpl-ui/lib/locale/lang/zh-CN.js';
// + 采用混入的形式添加语言包
const message = {
    'zh-cn': { ...zh, ...langs['zh-cn'] },
    'en-us': { ...en, ...langs['en-us'] }
}

const i18nKey: any = Utils.getCookie(config.i18nCookieId) || 'zh-cn';
export default () => new YjplI18n(i18nKey, message);
```
``` js
// 以下为项目主入口main.ts，并省略了无关代码
import App from './App.yjpl';
import router from './router';
import store from './store';
import yjplUi from 'yjpl-ui';
import Yjpl from 'yjpl';
import 'yjpl-ui/lib/theme-chalk/index.css';
import i18n from './i18n';

// + 新增UI组件库嵌入i18n实例
Yjpl.use(yjplUi, {
  i18n: (key: any, value: any) => i18n.t(key, value)
});

new Yjpl({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount('#app');
```