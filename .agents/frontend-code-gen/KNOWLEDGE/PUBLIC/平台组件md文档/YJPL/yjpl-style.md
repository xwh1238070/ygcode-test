# YJPL风格指南
这里是官方的 YJPL 特有代码的风格指南。如果在工程中使用 YJPL，为了回避错误、小纠结和反模式，该指南是份不错的参考。
## 规范归类
###  优先级A：必要的
这些规则会帮你规避错误，所以学习并接受它们带来的全部代价吧。这里面可能存在例外，但应该非常少，且只有你同时精通 JavaScript 和 YJPL 才可以这样做。
### 优先级B：强烈推荐
这些规则能够在绝大多数工程中改善可读性和开发体验。即使你违反了，代码还是能照常运行。
### 优先级C：推荐
当存在多个同样好的选项，选任意一个都可以确保一致性。
### 优先级D：谨慎使用
有些 YJPL 特性的存在是为了照顾极端情况。当被过度使用时，这些特性会让你的代码难于维护甚至变成 bug 的来源。

## 优先级A的规则：必要的
### 组件名为多个单词
组件名应该始终是多个单词的，根组件 App 以及`<transition>、<component>`之类的 YJPL 内置组件除外。  
这样做可以避免跟现有的以及未来的 HTML 元素相冲突，因为所有的 HTML 元素名称都是单个单词的。  

反例
``` js 
Yjpl.component('todo', {
  // ...
})
```
好例子
``` js 
Yjpl.component('todo-item', {
  // ...
})
```
### 组件数据
组件的 data 必须是一个函数。  
当在组件中使用 data property 的时候 (除了 new YJPL 外的任何地方)，它的值必须是返回一个对象的函数。  

反例
``` js 
Yjpl.component('todo', {
  data: {
    foo: 'bar'
  }
})
```
好例子
``` js 
Yjpl.component('todo-item', {
  data: function(){
    return {
      foo: 'bar'
    }
  }
})
```
### prop定义
prop 定义应该尽量详细。  
在你提交的代码中，prop 的定义应该尽量详细，至少需要指定其类型。
1. 它们写明了组件的 API，所以很容易看懂组件的用法；
2. 在开发环境下，如果向一个组件提供格式不正确的 prop，YJPL 将会告警，以帮助你捕获潜在的错误来源。  

反例：  
``` js
// 这样做只有开发原型系统时可以接受
props(){
  return ['status'];
}
```
好例子  
``` js
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
### 为 v-for 设置键值
总是用 key 配合 v-for。  
在组件上总是必须用 key 配合 v-for，以便维护内部组件及其子树的状态。甚至在元素上维护可预测的行为，比如动画中的对象固化 (object constancy)，也是一种好的做法。

### 避免 v-if 和 v-for 用在一起
当 YJPL 处理指令时，v-for 比 v-if 具有更高的优先级，永远不要把 v-if 和 v-for 同时用在同一个元素上。  
一般我们在两种常见的情况下会倾向于这样做：  
1. 为了过滤一个列表中的项目  
(比如 v-for="user in users" v-if="user.isActive")。在这种情形下，请将 users 替换为一个计算属性 (比如 activeUsers)，让其返回过滤后的列表。
2. 为了避免渲染本应该被隐藏的列表 
(比如 v-for="user in users" v-if="shouldShowUsers")。这种情形下，请将 v-if 移动至容器元素上 (比如 ul、ol)。
### 组件样式设置作用域
对于应用来说，顶级 App 组件和布局组件中的样式可以是全局的，但是其它所有组件都应该是有作用域的。  
这条规则只和单文件组件有关。你不一定要使用 scoped attribute。设置作用域也可以通过 CSS Modules，那是一个基于 class 的类似 BEM 的策略，当然你也可以使用其它的库或约定。  
不管怎样，对于组件库，我们应该更倾向于选用基于 class 的策略而不是 scoped attribute。
这让覆写内部样式更容易：使用了常人可理解的 class 名称且没有太高的选择器优先级，而且不太会导致冲突。  

反例  
``` html
<template>
  <button class="btn btn-close">X</button>
</template>

<style>
.btn-close {
  background-color: red;
}
</style>
```
好例子
``` html
<template>
  <button class="button button-close">X</button>
</template>

<!-- 使用 `scoped` attribute -->
<style scoped>
.button {
  border: none;
  border-radius: 2px;
}

.button-close {
  background-color: red;
}
</style>
```
``` html
<template>
  <button class="c-Button c-Button--close">X</button>
</template>

<!-- 使用 BEM 约定 -->
<style>
.c-Button {
  border: none;
  border-radius: 2px;
}

.c-Button--close {
  background-color: red;
}
</style>
```
## 优先级B的规则：强烈推荐
### 组件文件
只要有能够拼接文件的构建系统，就把每个组件单独分成文件。  
当你需要编辑一个组件或查阅一个组件的用法时，可以更快速的找到它。  

反例  
``` js
Yjpl.component('TodoList', {
  // ...
})

Yjpl.component('TodoItem', {
  // ...
})
``` 
好例子  
    components  
    |- TodoList.yjpl  
    |- TodoItem.yjpl  

### 单文件组件文件的大小写
单文件组件的文件名应该要么始终是单词大写开头 (PascalCase)，要么始终是横线连接 (kebab-case)。    
单词大写开头对于代码编辑器的自动补全最为友好，因为这使得我们在 JS(X) 和模板中引用组件的方式尽可能的一致。然而，混用文件命名方式有的时候会导致大小写不敏感的文件系统的问题，这也是横线连接命名同样完全可取的原因。  
反例  
components  
    |- mycomponent.yjpl  
    |- myComponent.yjpl   
好例子  
components  
    |- MyComponent.yjpl  
    |- my-component.yjpl  
  
### 紧密耦合的组件名
和父组件紧密耦合的子组件应该以父组件名作为前缀命名。  
如果一个组件只在某个父组件的场景下有意义，这层关系应该体现在其名字上。因为编辑器通常会按字母顺序组织文件，所以这样做可以把相关联的文件排在一起。
反例  
components  
    |- TodoList.yjpl  
    |- TodoItem.yjpl  
    |- TodoButton.yjpl   

components  
    |- SearchSidebar.yjpl  
    |- NavigationForSearchSidebar.yjpl    

好例子  
components  
    |- TodoList.yjpl  
    |- TodoListItem.yjpl  
    |- TodoListItemButton.yjpl   

components  
    |- SearchSidebar.yjpl  
    |- SearchSidebarNavigation.yjpl    

### prop 名大小写
在声明 prop 的时候，其命名应该始终使用 camelCase，而在模板和 JSX 中应该始终使用 kebab-case。  
我们单纯的遵循每个语言的约定。在 JavaScript 中更自然的是 camelCase。而在 HTML 中则是 kebab-case。  
反例  
``` html
<welcome-message greetingText="hi"></welcome-message>

props(){
  return  {
    'greeting-text': String
  }
}
```  
好例子  
``` html
<welcome-message greeting-text="hi"></welcome-message>

props(){
  return  {
    greetingText: String
  }
}
```
## 优先级C的规则：推荐

### 组件/实例的选项的顺序
组件/实例的选项应该有统一的顺序。  
这是我们推荐的组件选项默认顺序。  
``` html 
<template>
<page></page>
</template>
<script lang="ts">
import {
  YJPage,
} from 'yjpl-core';
export default class YwdjView extends YJPage {
  components(){
    return {
      'my-comp': MyComp
    }
  }
  directives(){
    return {
      focus: {
        // 指令的定义
        inserted: function (el) {
          el.focus()
        }
      }
    };
  }
  filters(){
    return {
      capitalize: function (value) {
        if (!value) {
          return '';
        };
        value = value.toString();
        return value.charAt(0).toUpperCase() + value.slice(1);
      }
    };
  }
  props(){
    return {
      name: {
        type: String
      }
    }
  }
  data(){
    return {
      hi: 'hi'
    };
  }
  computed(){
    return {
      tip(){
        return this.hi + this.name;
      }
    };
  }
  watch(){
    return {
      hi(){
        console.log('hi变量发生变化');
      }
    };
  }
  created(){}
  beforeMount(){}
  mounted(){}
  beforeDestroy(){}
  destroyed(){}
  methods(){
    return {
      sayHi(){
        consolo.log(this.hi);
      }
    };
  }
}
</script>
```
### 元素 attribute 的顺序  
元素 (包括组件) 的 attribute 应该有统一的顺序。  
这是我们为组件选项推荐的默认顺序。它们被划分为几大类，所以你也能知道新添加的自定义 attribute 和指令应该放到哪里。  
1. 定义 (提供组件的选项)
  is
2. 列表渲染 (创建多个变化的相同元素)
  v-for
3. 条件渲染 (元素是否渲染/显示)  
  v-if  
  v-else-if  
  v-else  
  v-show  
  v-cloak  
4. 渲染方式   
  v-pre  
  v-once    
5. 全局感知 (需要超越组件的知识)
  id
6. 唯一的 attribute (需要唯一值的 attribute)  
  ref  
  key  
7. 双向绑定 (把绑定和事件结合起来)  
  v-model
8. 其它 attribute (所有普通的绑定或未绑定的 attribute)  
9. 事件 (组件事件监听器)  
  v-on
10. 内容 (覆写元素的内容)  
  v-html  
  v-text

## 优先级D的规则：谨慎使用
### 没有在 v-if/v-else-if/v-else 中使用 key
如果一组 v-if + v-else 的元素类型相同，最好使用 key (比如两个 `<div>` 元素)。  
默认情况下，YJPL 会尽可能高效的更新 DOM。这意味着其在相同类型的元素之间切换时，会修补已存在的元素，而不是将旧的元素移除然后在同一位置添加一个新元素。如果本不相同的元素被识别为相同，则会出现意料之外的结果。  
反例  
``` html 
<div v-if="error">
  错误：{{ error }}
</div>
<div v-else>
  {{ results }}
</div>
```
好例子  
``` html 
<div
  v-if="error"
  key="search-status"
>
  错误：{{ error }}
</div>
<div
  v-else
  key="search-results"
>
  {{ results }}
</div>
```  
### scoped 中的元素选择器  
元素选择器应该避免在 scoped 中出现。  
在 scoped 样式中，类选择器比元素选择器更好，因为大量使用元素选择器是很慢的。   

为了给样式设置作用域，YJPL 会为元素添加一个独一无二的 attribute，例如 data-v-f3f3eg9。然后修改选择器，使得在匹配选择器的元素中，只有带这个 attribute 才会真正生效 (比如 button[data-v-f3f3eg9])。
问题在于大量的元素和 attribute 组合的选择器 (比如 button[data-v-f3f3eg9]) 会比类和 attribute 组合的选择器慢，所以应该尽可能选用类选择器。    
反例  
``` html
<template>
  <button>X</button>
</template>

<style scoped>
button {
  background-color: red;
}
</style>
```  
好例子  
``` html
<template>
  <button class="btn btn-close">X</button>
</template>

<style scoped>
.btn-close {
  background-color: red;
}
</style>
```
### 隐性的父子组件通信  
应该优先通过 prop 和事件进行父子组件之间的通信，而不是 this.$parent 或变更 prop。  
一个理想的 YJPL 应用是 prop 向下传递，事件向上传递的。遵循这一约定会让你的组件更易于理解。然而，在一些边界情况下 prop 的变更或 this.$parent 能够简化两个深度耦合的组件。  
问题在于，这种做法在很多简单的场景下可能会更方便。但请当心，不要为了一时方便 (少写代码) 而牺牲数据流向的简洁性 (易于理解)。  
