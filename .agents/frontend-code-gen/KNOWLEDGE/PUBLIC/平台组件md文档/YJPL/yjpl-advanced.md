# YJPL 进阶

## 数据初始化

在 YJPL 中 data 内定义的数据是双向绑定的

1. 如果不需要双向绑定的数据不要在 data 内定义。
2. 如果是双向绑定的，需要在 data 内定义。

## 长列表性能优化

YJPL 会通过 Object.defineProperty 对数据进行劫持，来实现视图响应数据的变化，有些时候我们的组件就是纯粹的数据展示，不会有任何改变，我们就不需要 YJPL 来劫持我们的数据，在大量数据展示的情况下，这能够很明显的减少组件初始化的时间。
当组件就是纯粹的数据展示，不会有任何改变，不需要被监测，可以通过 Object.freeze 方法来冻结一个对象，来减少组件初始化的时间。

```html
<template>
 <page class="index">
  <ul>
    <option v-for="(item, index) in users" :key="item.id"></option>
  </ul>
 </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YwdjView extends YJPage {
  data() {
    return {
      users: []
    }
  }
  async created(){
    const users = await this.tleadService.doGet('/api/users');
    this.users = Object.freeze(users);
  }
}
</script>
```

## v-if/v-show

v-if 是真正的条件渲染，因为它会确保在切换过程中条件块内的事件监听器和子组件适当地被销毁和重建；也是惰性的：如果在初始渲染时条件为假，则什么也不做——直到条件第一次变为真时，才会开始渲染条件块。  
v-show 不管初始条件是什么，元素总是会被渲染，并且只是简单地基于 CSS 的 display 属性进行切换。

v-show: 需要频繁切换视图的情况下使用。  
v-if: 很少切换的情况下使用。

## v-for

1. v-for 不能和 v-if 在同一标签下使用。  
   v-for 比 v-if 优先级高，如果每一次都需要遍历整个数组，将会影响速度，尤其是当之需要渲染很小一部分的时候，必要情况下应该替换成 computed 属性。
2. v-for 循环需设置 key 属性，且值最好不要用 index。  
   在列表数据进行遍历渲染时，需要为每一项 item 设置唯一 key 值，方便 YJPL 内部机制精准找到该条列表数据。当更新时，新的状态值和旧的状态值对比，较快地定位到 diff 。  
   推荐使用

```html
<template>
<page>
  <ul>
    <li v-for="user in activeUsers" :key="user.id"></li>
  </ul>
</page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      data: []
    }
  }
  computed() {
    return {
      activeUsers: function () {
        return this.users.filter(function (user) {
          return user.isActive
        });
      }
    }
  }
}
</script>
```

不推荐使用

```html
<template>
  <page>
    <ul>
      <li v-for="user in users" v-if="user.isActive" :key="user.id"></li>
    </ul>
  </page>
</template>
```

## computed/watch

1. computed： 计算属性，依赖其它属性值，并且 computed 的值有缓存，只有它依赖的属性值发生改变，下一次获取 computed 的值时才会重新计算 computed 的值。
2. watch： 更多的是「观察」的作用，类似于某些数据的监听回调 ，每当监听的数据变化时都会执行回调进行后续操作。  
   运用场景：

当我们需要进行数值计算，并且依赖于其它数据时，应该使用 computed，因为可以利用 computed 的缓存特性，避免每次获取值时，都要重新计算。  
当我们需要在数据变化时执行异步或开销较大的操作时，应该使用 watch，使用 watch 选项允许我们执行异步操作 ( 访问一个 API )，限制我们执行该操作的频率，并在我们得到最终结果前，设置中间状态。这些都是计算属性无法做到的。

## watch

场景还原：组件创建的时候我们获取一次列表，同时监听 input 框，每当发生变化的时候重新获取一次。

```javascript
created(){
  this.getData();
}
watch() {
  return {
    searchInputValue(){
      this.getData()
    }
  };
}
```

等价于（推荐使用）

```js
watch() {
  return {
    searchInputValue: {
      handler: 'getData',
      immediate: true
    }
  };
}

```

## $attrs/$listeners

$attrs/$listeners 主要解决多级组件嵌套的情况。  
场景：C 组件要获取 A 组件传的属性和事件，需要经常 B 组件中转。

```html
A组件：
<b :age="12" name="tom" sex="男" @input="handleInput"></b>

B组件:
<C :age="age" :name="name" :sex="sex" @input="$emit('input', $event.target.value)"></C>

props(){ return ['age', 'name', 'sex']; } C组件： props(){ return ['age', 'name', 'sex']; }
```

等价于（推荐使用）

```html
A组件：
<b :age="12" name="“tom”sex" ="“男”" @input="handleInput"></b>

B组件:
<C v-bind="$attrs" v-on="$listeners"> </C>

C组件： props(){ return ['age', 'name', 'sex']; }
```

## yjpl.config.ts 配置

### 浏览器兼容性

为了提高编译效率与速度，YJPL 编译服务默认只会转换本项目的 es6 代码为 es5，不会转换 node_modules 内的代码。为了兼容部分三方模块的代码不是 es6 的情况，提供 transpileDependencies 配置，针对特定三方模块进行 es6 转 es5 处理。
配置方式如下：

```js
// yjpl.config.js
const path = require('path');
module.exports = {
  filenameHashing: false,
  productionSourceMap: false,
  publicPath: process.env.BASE_URL,
  transpileDependencies: ['yjpl-umc-components'], // 将要es6转es5处理的模块名放入数组中
  chainWebpack: config => {
    config.resolve.alias.set('@', path.resolve(__dirname, 'src'));
    config.resolve.alias.set('@types', path.resolve(__dirname, 'src/@types'));
  }
};
```
