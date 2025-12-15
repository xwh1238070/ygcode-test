# Skin 主题工具类
天鹂提供主题管理模块yjpl-skin，用于主题切换。

## 引入方法
### 使用npm安装
``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-i18n
```

## 运行期(npm run serve)时进行主题配置
### 初始化主题加载配置    
在根目录下找到config/config.ts 文件，进行配置：
```typescript
import skinConfig from '../../public/assets/themes/skin/default/config.json';

export default {
  skinOption: {
    // 选配(默认值_sk)，主题切换时需要依赖的cookie名称
    cookieId: '_sk',
    // 选配(默认值default)，初始化加载的主题类，可选：default(ECP默认)、dapdefault(DAP默认)、gw(国网)、nw(南网)、ngw(南国网)、nyh(南云化)
    defaultSkinName: 'default',
    // 选配(默认值为空)，只加载线上或本地，可选online或local
    loadType: 'online',
    // 选配(默认值为空)，要加载的服务端主题样式列表，为空加载服务端所有的样式
    includeOnline: ['index.css'],
    // 选配(默认值为空)，要加载的本地主题样式列表，为空加载本地所有的样式
    includeLocal: ['index.css'],
    // 选配(默认值为空)，排除要加载的线上主题样式，为空不做排除
    excludeOnline: [],
    // 选配(默认值为空)，排除要加载的本地主题样式，为空不做排除
    excludeLocal: [],
    // 选配(默认值为空)，本地主题资源路径，为空加载public下的assets/themes/skin/
    localPath: process.env.BASE_URL + 'assets/themes/skin/',
    // 选配(默认值为空)，loadType 为 online 时生效，根据后端服务配置
    vipAddress: '',
    // 选配(默认值为空)，个性化配置
    config: skinConfig
  }
};
```

### 跨域代理配置(只在npm run serve时生效，打包后配置会失效)    
在根目录下找到yjpl.config.js文件，在devServer中配置proxy：
```js
举例：前端项目中的contextPath = /web/contextPath (这里以自己项目中的contextPath为准)

devServer: {
    proxy: {
      // 获取线上的主题列表
      '/web/contextPath/necp/themes': {
        target: 'http://10.51.149.20:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/web/contextPath/necp/themes': '/necp/themes'
        }
      },
      // 获取线上的所有css文件
      '/web/contextPath/assets': {
        target: 'http://10.51.149.20:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/web/contextPath/assets': '/assets'
        }
      }
    }
  }
```

## nginx项目
1、如果是在nginx部署的项目中，需要做如下配置(类比开发期的代理配置)：  
找到nginx的nginx.conf文件
```js
  location server端contextpath/necp/themes/ {
    proxy_pass   'http://10.51.149.20:8090/necp/themes/'; # 主题list接口 IP:port
  }
  location server端contextpath/assets/ {
    proxy_pass   'http://10.51.149.20:8090/assets/'; # 主题css接口 IP:port
  }
```
例如：server端contextpath = necp/mapp
```js
  location necp/mapp/necp/themes/ {
    proxy_pass   'http://10.51.149.20:8090/necp/themes/'; # 主题list接口 IP:port
  }

  location necp/mapp/assets/ {
    proxy_pass   'http://10.51.149.20:8090/assets/'; # 主题css接口 IP:port
  }
```

## 注意点
### 1、如果需要配置本地css主题样式，建议本地存放css文件的文件结构(和线上css文件结构尽可能一致)：  

      public/assets/themes/skin
          |-- dapdefault
          |    |- layout        通用布局样式
          |        |- index.css 
          |    |- component     组件样式
          |        |- index.css 
          |    |- bill          单据样式
          |        |- index.css 
          |-- default
          |    |- layout        通用布局样式
          |        |- index.css 
          |    |- component     组件样式
          |        |- index.css 
          |    |- bill          单据样式
          |        |- index.css 
          ...

###  2、切换主题
如果不需要切换主题，使用初始化配置即可，如果需要动态切换主题，则可进行如下配置：
1. 通过getSkinName查询当前主题
2. 通过getSkinList查询主题列表
3. 通过loadSkin切换主题
``` html
<template>
 <page>
    <tl-select 
      v-model="dataModel.skin" 
      :data="dataModel.skinData" 
      id-field="id" 
      text-field="text"
      @change="changeSkin"  />
 </page>
</template>

<script lang="ts">
import {
 YJPage,
} from 'yjpl-core';
export default class YwdjView extends YJPage {
  data() {
    return {
      dataModel: {
        // 通过getSkinName查询当前主题
        skin: skin: this.tleadSkin.getSkinName(),
        skinData: []
      }
    };
  }

  created() {
    // 通过getSkinList查询主题列表
    this.tleadSkin.getSkinList().then((res) => {
      this.dataModel.skinData = res;
    });
  }

  methods() {
    return {
      changeSkin() {
        // 通过loadSkin切换主题
        this.tleadSkin.loadSkin(this.dataModel.skin);
      }
    };
  }
}
</script>
```

### 3、加载个性化配置
yjpl-skin提供了加载个性化主题配置，每个主题可以通过配置来指定某些组件的样式。

配置过程如下：

1.配置 `src/config/config.ts` 文件。
[参考上文](/yjpl/utils/skin.html#初始化主题加载配置)

2.个性化配置：

`public/assets/skin/你的主题名(如gw)/config.json` 在这个配置文件中做以下配置：

```js
{
  // 主题名
  "name": "gw",
  // 表单的label对齐方式
  "formLabelPosition": "right",
  // 表单的label的宽度
  "formLabelWidth": "120px",
  // yj-container-card 默认显示类型
  "listShowType": "grid",
  // tabs 默认显示类型
  "tabsType": ""
}
```

3.在main.ts中加载个性化配置

```js
skin.loadConfig().finally(() => {
  new Yjpl({
    skin,
    router,
    store,
    render: h => h(App)
  }).$mount('#app');
});
```

4.切换主题到你配置了个性化的主题，可以看到个性化配置在yjpl组件中生效了