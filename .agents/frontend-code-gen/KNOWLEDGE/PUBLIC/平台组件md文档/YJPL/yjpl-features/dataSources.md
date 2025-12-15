# 数据源
为了简化从服务端获取数据的步骤，YJPL提供了数据源的功能，通过配置的方式，自动获取数据并绑定。
## 数据源定义
通过在业务基类（YJBusiness）中定义dataSources来配置数据接口信息。    
1. 定义数据源dataSources方法用于定义接口信息
2. 定义dataSource用于接收接口数据
3. 定义getData函数， 返回dataModel和dataSource，用于YJPL页面。  
比如在dataSources中定义了form1接口，获取的数据会自动绑定到this.dataSource.form1.data上。
``` js
import { YJBusiness } from 'yjpl-core';
class PageBase extends YJBusiness {
  constructor(){
    super();
    this.dataSource = {
      form1: {
        data: []
      },
      form2: {
        data: []
      }
    };
    this.dataModel = {
      abcd: {},
    };
  }
  dataSources() {
    return {
      form1: {
        url: '/model/form1',
        method: 'post',
        params: {},
        //params: 可以是数组或者JSON，其中的变量要用${}这种形式
        /*
          params: ['100', '${dataModel.grid}'],
          params: {
              'grid': '${dataModel.grid}'
          },
        */
        // wait属性，可以配置是否自动触发
        // wait: true,
        // renameKey属性，可以配置查询回来的结果存放的路径
        // renameKey: {
        //     'abcd': 'dataModel.abcd', // 默认取res.data
        //     '@': 'dataModel.abcd', // 整个节点挂过去
        // },
      },
      form2: {
        url: '/model/form2',
        params: {},
        method: 'get',
        // 请求依赖项，依赖深度最深3重。
        // deps: ['form1'],
      }
    };
  }
  getData() {
    return {
      dataModel: this.dataModel,
      dataSource: this.dataSource
    };
  }
  sendRequestManually() {
    // 手动触发dataSources中的请求
    // this.trigger 方法继承自 YJBusiness 类
    this.trigger('form1');
  }
}
export default PageBase;
```
## 数据源数据绑定
``` html
<template>
<page>
  <yj-panel>
    <!-- 通过dataSource.form1.data绑定数据源form1接口数据 -->
    <yj-container v-model="dataModel" :data="dataSource.form1.data" label-suffix="：" :col="4"></yj-container>
  </yj-panel>

</page>
</template>
<script lang="ts">
import {
  YJPage,
} from 'yjpl-core';
// 数据源定义中的业务类
import Yw from './js/index';
export default class YwdjView extends YJPage {
  constructor() {
    super();
    this.yw = new Yw();
  }
  data() {
    return this.yw.getData();
  }
}
</script>
```