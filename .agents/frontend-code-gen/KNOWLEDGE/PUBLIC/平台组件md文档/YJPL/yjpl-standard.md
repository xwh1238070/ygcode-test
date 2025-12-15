# YJPL 开发规范

## 概述

为了更好的规范 YJPL 开发模式和资源、组件等进行管理，以确保一个稳定、高效的平台持续发展，为提高团队协作效率，便于后台人员添加功能及前端后期优化维护，输出高质量的文档。现发布 YJPL 开发规范。本规范主要对基于 YJPL 进行开发的编码规范、开发模式、资源管理、样式管理等方面进行标准制定。

## 命名规范

为了让各事业部书写可维护的代码，而不是一次性的代码，让团队当中其他人看你的代码能一目了然，甚至一段时间时候后你再看你某个时候写的代码也能看明白。

### 普通变量命名规范

命名方法：驼峰命名法  
命名规范：

1. 命名必须是跟需求的内容相关的词，比如说我想申明一个变量，用来表示我的项目，那么我们可以这样定义 const myProject = "我的项目"。
2. 命名是复数的时候需要加 s，比如说我想申明一个数组，表示很多人的名字，那么我们可以这样定义 const names = new Array();

### 常量

命名方法：全部大写
命名规范：使用大写字母和下划线来组合命名，下划线用以分割单词。

```js
const MAX_COUNT = 10,
  URL = 'https://www.ygsoft.com/';
```

### 组件命名规范

官方文档推荐及使用遵循规则：  
PascalCase (单词首字母大写命名)是最通用的声明约定；  
kebab-case (短横线分隔命名) 是最通用的使用约定。

1. 组件名为多个单词  
   组件名应该始终是多个单词的，根组件 App 以及 `<transition>、<component>` 之类的 YJPL 内置组件除外。  
   这样做可以避免跟现有的以及未来的 HTML 元素相冲突，因为所有的 HTML 元素名称都是单个单词的。  
   反例：todo  
   好例子：todo-item
2. 有意义的名词、简短、具有可读性
3. 命名遵循 PascalCase 约定
4. 使用遵循 kebab-case 约定
5. 在页面中使用组件需要前后闭合，并以短线分隔，如（`<abcd-date-picker></abcd-date-picker>，<abcd-table></abcd-table>`）
6. 导入及注册组件时，遵循 PascalCase 约定
7. 同时还需要注意：必须符合自定义元素规范: 切勿使用保留字。

### method 方法命名规范

1. 驼峰式命名，统一使用动词或者动词+名词形式  
   反例  
   go、nextPage、show、open、login  
   好的例子  
   jumpPage、openCarInfoDialog
2. 请求数据方法，以 data 结尾  
   反例  
   takeData、confirmData、getList、postForm  
   好的例子  
   getListData、postFormData
3. init、refresh 单词除外
4. 尽量使用常用单词开头（set、get、go、can、has、is）

附：函数方法常用的动词:

<!-- <img :src="$withBase('/yjpl/methods.png')">     -->

### pages 下文件命名

1. 只有一个文件的情况下不会出现文件夹，而是直接放在 pages 目录下面，如 index.yjpl
2. 尽量是名词,且使用驼峰命名法
3. 开头的单词就是所属模块名字（workbenchIndex、workbenchList、workbenchEdit）
4. 名字至少两个单词（good: workbenchIndex）（bad:workbench）

### 其他

1. 作用域不大临时变量可以简写，比如：str，num，bol，obj，fun，arr。
2. 循环变量可以简写，比如：i、j、k 等。

## 注释规范

### 务必添加注释列表

1. 公共组件使用说明
2. 各组件中重要函数或者类说明
3. 复杂的业务逻辑处理说明
4. 特殊情况的代码处理说明,对于代码中特殊用途的变量、存在临界值、函数中使用的 hack、使用了某种算法或思路等需要进行注释描述
5. 多重 if 判断语句
6. 注释块必须以`/**（至少两个星号）开头**/`
7. 单行注释使用//

### 单行注释

注释单独一行，不要在代码后的同一行内加注释。例如：  
反例

```js
const name = 'abc'; // 姓名
```

好的例子

```js
// 姓名
const name = 'abc';
```

### 多行注释

组件使用说明，和调用说明

```js
/**
 * 组件名称
 * @module 组件存放位置
 * @desc组件描述
 * @author 组件作者
 * @date 2017年12月05日17:22:43
 * @param {Object} [title]    - 参数说明
 * @param {String} [columns] - 参数说明
 * @example 调用示例
 *  <hbTable :title="title" :columns="columns" :tableData="tableData"></hbTable>
 **/
```

## 样式规范

### 通用规范

1. 统一使用"-"连字符
2. 省略值为 0 时的单位, 非 0 时一定要添加单位
3. 如果 CSS 可以做到，就不要使用 JS
4. 建议并适当缩写值，提高可读性，特殊情况除外  
   “建议并适当”是因为缩写总是会包含一系列的值，而有时候我们并不希望设置某一值，反而造成了麻烦，那么这时候你可以不缩写，而是分开写。  
   当然，在一切可以缩写的情况下，请务必缩写，它最大的好处就是节省了字节，便于维护，并使阅读更加一目了然。

```css
/* bad */
.box {
  border-top-style: none;
  font-family: palatino, georgia, serif;
  font-size: 100%;
  line-height: 1.6;
  padding-bottom: 2em;
  padding-left: 1em;
  padding-right: 1em;
  padding-top: 0;
}
/* good */
.box {
  border-top: 0;
  font: 100%/1.6 palatino, georgia, serif;
  padding: 0 1em 2em;
}
```

5. 元素选择器应该避免在 scoped 中出现（在 scoped 样式中，类选择器比元素选择器更好，因为大量使用元素选择器是很慢的）
6. 声明应该按照下表的顺序  
左到右，从上到下
<!-- <img :src="$withBase('/yjpl/cssAttr.png')">     -->

### sass 规范

SCSS 块应具有顺序， 如下：

1. 当前选择器的样式属性
2. 父级选择器的伪类选择器 (:first-letter, :hover, :active etc)
3. 伪类元素 (:before and :after)
4. 父级选择器的声明样式 (.selected, .active, .enlarged etc.)
5. 用 Sass 的上下文媒体查询
6. 子选择器作为最后的部分

```css
.product-teaser {
  /* 1. 当前选择器的样式属性 */
  display: inline-block;
  padding: 1rem;
  background-color: whitesmoke;
  color: grey;

  /* 2. 父级选择器的伪类选择器 */
  &:hover {
    color: black;
  }

  /* 3. 伪类元素 */
  &:before {
    content: '';
    display: block;
    border-top: 1px solid grey;
  }

  &:after {
    content: '';
    display: block;
    border-top: 1px solid grey;
  }

  /* 4. 父级选择器的声明样式  */
  &.active {
    background-color: pink;
    color: red;

    &:hover {
      color: darkred;
    }
  }

  /* 5. 用 Sass 的上下文媒体查询 */
  @media screen and (max-width: 640px) {
    display: block;
    font-size: 2em;
  }

  /* 6. 子选择器作为最后的部分 */
  > .content > .title {
    font-size: 1.2em;

    @media screen and (max-width: 640px) {
      letter-spacing: 0.2em;
      text-transform: uppercase;
    }
  }
}
```

### 特殊规范

1. 用页面级组件样式，应该是有作用域的
2. 对于公用组件或者全局组件库，应该更倾向于选用基于 class 的 BEM 策略

```js
<!-- bad -->
<style lang='scss'></style>

<!-- good -->
<!--使用 scoped 作用域 -->
<style lang='scss' scoped></style>

<!-- good -->
<!--使用 BEM 约定 -->
<style>
  .c-Button {
    border: none;
    border-radius: 2px;
  }
</style>
```

## 编码规范

### 源码风格

使用 ES6 风格编码

1. 定义变量使用 let ,定义常量使用 const
2. 静态字符串一律使用单引号或反引号，动态字符串使用反引号
3. 数组成员对变量赋值时，优先使用解构赋值

```js
// 数组解构赋值
const arr = [1, 2, 3, 4];
//  bad
const first = arr[0];
const second = arr[1];

// good
const [first, second] = arr;
```

4. 函数的参数如果是对象的成员，优先使用解构赋值

```js
// bad
function getFullName(user) {
  constfirstName = user.firstName;
  constlastName = user.lastName;
}

// good
function getFullName(obj) {
  const { firstName, lastName } = obj;
}
// best
function getFullName({ firstName, lastName }) {}
```

5. 使用扩展运算符（...）拷贝数组

```js
const items = [1, 2, 3, 4, 5];

// bad
const constitemsCopy = items;

// good
const constitemsCopy = [...items];
```

6. 需要使用函数表达式的场合，尽量用箭头函数代替。因为这样更简洁，而且绑定了 this
7. 如果模块只有一个输出值，就使用 export default，如果模块有多个输出值，就不使用 export default，export default 与普通的 export 不要同时使用
8. 如果模块默认输出一个函数，函数名的首字母应该小写
9. 如果模块默认输出一个对象，对象名的首字母应该大写

### 指令规范

1. 指令有缩写一律采用缩写形式

```js
// bad
v-bind:class="{'show-left'：true}"
v-on:click="getListData"

// good
:class="{'show-left'：true}"
@click="getListData"
```

2. v-for 循环必须加上 key 属性，在整个 for 循环中 key 需要唯一
3. 避免 v-if 和 v-for 同时用在一个元素上（性能问题）  
   将数据替换为一个计算属性，让其返回过滤后的列表

```js
<!-- bad -->
<ul>
  <li v-for="user in users" v-if="user.isActive" :key="user.id"></li>
</ul>

<!-- good -->
<ul>
  <li v-for="user in activeUsers" :key="user.id"></li>
</ul>
<script>
  computed(){
    return {
      activeUsers: function () {
        return this.users.filter(function (user) {
          return user.isActive
        })
      }
    }
  }
</script>
```

将 v-if 移动至容器元素上 (比如 ul, ol)

```js
<!-- bad -->
<ul>
  <li v-for="user in users" v-if="shouldShowUsers" :key="user.id"></li>
</ul>

<!-- good -->
<ul v-if="shouldShowUsers">
  <li v-for="user in users" :key="user.id"></li>
</ul>
```

### 组件规范

#### prop 定义应该尽量详细。

在你提交的代码中，prop 的定义应该尽量详细，至少需要指定其类型。

1. 它们写明了组件的 API，所以很容易看懂组件的用法；
2. 在开发环境下，如果向一个组件提供格式不正确的 prop，YJPL 将会告警，以帮助你捕获潜在的错误来源。

反例：

```js
// 这样做只有开发原型系统时可以接受
props(){
  return ['status'];
}
```

好例子

```js
// 这样做只有开发原型系统时可以接受
props(){
  return {
    status: String
  }
}
// 更好的做法
props(){
  return {
    status: {
      type: String,
      required: true,
      validator: function (value) {
        return [
          'syncing',
          'synced',
          'version-conflict',
          'error'
        ].indexOf(value) !== -1
      }
    }
  }
}
```

#### 组件的 data 必须是一个函数。

当在组件中使用 data property 的时候 (除了 new YJPL 外的任何地方)，它的值必须是返回一个对象的函数。

反例

```js
Yjpl.component('todo', {
  data: {
    foo: 'bar'
  }
});
```

好例子

```js
Yjpl.component('todo-item', {
  data: function () {
    return {
      foo: 'bar'
    };
  }
});
```

#### 组件定义必须使用 \*.yjpl 文件

#### 如果一个组件只在某个父组件场景下才能使用，则子组件名必须以父组件名作为前缀命名

#### 组件名应该以高级别的单词开头， 以描述性的修饰词结尾

#### 模板中只能输出和绑定变量，不能包含复杂的运算表达式，所有的运算表达式必须放到 computed 作用域中

#### 子组件返回数据给父子组件应该使用$emit进行， 不能用 this.$parent 或改变 prop 值

### 视图逻辑分离规范

## 继承规范

yjpl 与 ts 分开两个文件存放，如：

```js
<!-- demo.yjpl -->
<template>
  <page class="yj-container"></page>
</templace>
<script>
  import { YJPage } from 'yjpl/lib';
  import Yw from ‘./yw.ts’
  export default class YwdjView extends YJPage {
    constructor () {
      super();
      this.yw = new yw();
    }
    data() {
      return Yw.getData();
    }
  }
</script>
```

```js
// yw.ts
import { YJBusiness } from 'yjpl/lib';
class Yw extends YJBusiness {
  methods() {
    return {
      getData() {
        return {
          show: true
        };
      }
    };
  }
}
```

### 其他

1. 所有的 yjpl 上的标题文本，都必须定义到 data 中便于国际化，如：

```js
<!-- bad -->
<div title=”标题”></div>

<!-- good -->
<div :title=”captions.title”></div>
data() {
  return {
    captions: {title: '标题'}
  };
}
```

2. computed 区域的运算属性的定义, 命名应该以和 data 类似，不允许添加类似 get/set 的前缀
3. 避免 this.$parent、this.$ref 等来直接操作 dom 元素
4. 调试信息 console.log() debugger 使用完及时删除
5. 除了三目运算，if,else 等禁止简写

## 异常处理

1. 不可以直接吃掉异常

```js
//bad
try {
  //代码
} catch (e) {}
```

2. 异常输出信息要有真实的意义

```js
try {
  //代码
} catch (e) {
  if (window.console) {
    window.console.error('错误信息: 获取城市信息数据失败，' + e);
  }
}
```

## 代码提交规范

提交的代码必须满足 ESLint 的检查。
