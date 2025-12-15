# YJPL使用

YJPL完全兼容VUE的语法，唯一不同的写法是单文件组件script的写法，具体可见
<!-- <a :href="$withBase('#单文件组件')">单文件组件</a>。 -->

## 数据绑定

YJPL框架很核心的功能就是双向的数据绑定。 双向是指：HTML标签数据 绑定到YJPL对象，另外反方向数据也是绑定的。通俗点说就是，YJPL对象的改变会直接影响到HTML的标签的变化，而且标签的变化也会反过来影响YJPL对象的属性的变化。  
这样以来，就彻底变革了之前Dom的开发方式，之前Dom驱动的开发方式尤其是以jQuery为主的开发时代，都是dom变化后，触发js事件，然后在事件中通过js代码取得标签的变化，再跟后台进行交互，然后根据后台返回的结果再更新HTML标签，异常的繁琐。有了YJPL这种双向绑定，让开发人员只需要关心json数据的变化即可，YJPL自动映射到HTML上，而且HTML的变化也会映射回js对象上，开发方式直接变革成了前端由数据驱动的 开发时代，远远抛弃了Dom开发主导的时代了。  
<!-- <img :src="$withBase('/yjpl/bind.png')">   -->

### 绑定文本

数据绑定最常见的形式就是使用 “Mustache” 语法（双大括号）的文本插值，比如模板引擎：handlebars中就是用的{{}}.  
创建的YJPL对象中的data属性就是用来绑定数据到HTML的。参考如下代码：

``` html
<template>
 <page class="index">
   <!-- 使用 “Mustache” 语法（双大括号） -->
   Message: {{msg}}
   <!-- 绑定数据中使用JavaScript表达式 -->
   <span>Message: {{ msg + ' - ' + name }}</span>
   <!-- 支持表达中的任何计算、函数处理 -->
   <p>{{ isOk ? '123' : '456' }}<p>
   <p>我的年龄是： {{ age * 2 }}</p>
 </page>
</template>
<script>
import {
 YJPage,
} from 'yjpl-core';
export default class YwdjView extends YJPage {
  data() {
    return {
      msg: 'Hello',
      name: 'YJPL',
      isOk: true,
      age: 18
    }
  }
}
</script>
```

### 属性绑定

``` html
<!-- 
  不能直接使用{{ expression }} 语法进行绑定html的标签，而是用它特有的v-bind指令。  
  绑定的语法结构：
  <标签 v-bind:属性名="要绑定的YJPL对象的data里的属性名"></标签>例如:
 -->
<span v-bind:id="menuId"></span>
<!-- 由于v-bind 使用非常频繁，所以YJPL提供了简单的写法，可以去掉v-bind直接使用:即可 -->
<span :id="menuId"></span>
```
### 样式绑定

#### 绑定样式对象

对于普通的属性的绑定，只能用上面的讲的绑定属性的方式。而YJPL专门加强了class和style的属性的绑定。可以有复杂的对象绑定、数组绑定样式和类。

``` html
<!-- 绑定样式对象 -->
<div v-bind:class="{ active: isActive }"></div>
<!-- 
解释：
当 isActive为 true时， div就会具有了active样式类，如果 isActive为false，那么div就去掉active样式类。
 -->
```
#### 混合普通的HTML标签样式类及绑定样式对象

v-bind:class 指令可以与普通的 class 属性共存。

``` html
<!-- 绑定样式对象 -->
<div class="static"
     :class="{ active: isActive, 'text-danger': hasError }">
</div>
<!-- 
解释：
当 isActive为 true时，hasError为true时。等价于
<div class="static active text-danger"></div>
 -->
```

#### 绑定样式数组

绑定数组，就是绑定样式对象的延续

``` html
<template>
 <page class="index">
  <div :class="[activeClass, errorClass]">
 </page>
</template>

<script>
  import { YJPage } from 'yjpl-core';
  export default class YwdjView extends YJPage {
    data(){
      return {
        activeClass: 'active',
        errorClass: 'text-danger'
      }
    }
  }
</script>
```

#### 内联样式绑定

内联样式的绑定，非常类似于样式类的操作。v-bind:style 的对象语法十分直观——看着非常像 CSS ，其实它是一个 JavaScript 对象。 CSS属性名可以用驼峰式（camelCase）或短横分隔命名（kebab-case）。

``` html
<template>
 <page class="index">
  <div :style="{fontSize: size + 'px', backgroundColor: bgcolor, width: width}">
    YJPL 入门系列教程
  </div>
 </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      size: 19,
      width: 200,
      bgcolor: 'red'
    }
  }
}
</script>
```

### 计算属性

在做数据的绑定的时候,数据要进行处理之后才能展示到html页面上，虽然YJPL提供了非常好的表达式绑定的方法，但是只能应对低强度的需求。比如： 把一个日期按照规定格式进行输出，可能就需要我们对日期对象做一些格式化的出来，表达式可能就捉襟见肘了。  

YJPL对象提供的computed属性，可以让我们开发者在里面可以放置一些方法，协助我们绑定数据操作，这些方法可以跟data中的属性一样用，注意这些方法用的时候不要加()。 例子来了：

``` html
<template>
 <page class="index">
  <table>
    <tr>
      <!-- computed里面的函数可以直接当成data里面的属性用，非常方便，注意没有括号！！！-->
      <td>生日</td><td>{{ getBirthday }}</td>
    </tr>
    <tr>
      <td>年龄</td><td>{{ age }}</td>
    </tr>      
    <tr>
      <td>地址</td><td>{{ address }}</td>
    </tr>
  </table>
 </page>
</template>


<script>
import {
 YJPage,
} from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      birthday: 914228510514,     // 这是一个日期对象的值：1998年11月1日
      age: 19,
      address: 'xxxx'
    };
  }
  computed(){
    return {
      // 把日期换成 常见规格格式的字符串。
      getBirthday: function () {
        var m = new Date(this.birthday);
        return m.getFullYear() + '年' + m.getMonth() +'月'+ m.getDay()+'日';
      }
    };
  }
}
</script>
```

### 绑定的数据过滤器

过滤器本质就是数据在呈现之前先进行过滤和筛选  
YJPL允许自定义过滤器，被用作一些常见的文本格式化。过滤器应该被添加在 mustache 插值的尾部，由“管道符”指示：

``` html
<template>
 <page class="index">
    <!-- 
      过滤器可以串联：
      {{ message | filterA | filterB }}
      过滤器是 JavaScript 函数，因此可以接受参数：
      {{ message | filterA('arg1', arg2) }}
      这里，字符串 'arg1' 将传给过滤器作为第二个参数， arg2 表达式的值将被求值然后传给过滤器作为第三个参数。
    -->
    <!-- in mustaches -->
    {{ message | capitalize }}
    <!-- in v-bind -->
    <div :id="message | capitalize"></div> 
 </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      message: 'ssssssssss'
    };
  }
  filters(){
    return {
      capitalize: function (value) {
        if (!value) return '';
        value = value.toString();
        return value.charAt(0).toUpperCase() + value.slice(1);
      }
    };
  }
}
</script>
```

### 双向数据绑定

v-model用于双向数据的绑定。

``` html
<template>
 <page class="index">
    <input type="text" name="txt" v-model="msg">
    <p>您输入的信息是：{{ msg }}</p>
 </page>
</template>

<script>
import {
 YJPage,
} from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      msg: '双向数据绑定的例子'
    };
  }
}
</script>
```

改变input文本框的内容的时候，p标签中的内容会跟着进行改变，哇是不是很神奇呢...

## 列表渲染及条件渲染

### 条件渲染

时候我们要根据数据的情况，决定标签是否进行显示或者有其他动作。最常见的就是，表格渲染的时候，如果表格没有数据，就显示无数据。如果有数据就显示表格数据。 YJPL帮我们提供了一个v-if的指令，帮助我们完成判断的模板处理。  

``` html
<template>
 <page class="index">
  <h1 v-if="ok">Yes</h1>
  <h1 v-else>No</h1>  
 </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      ok: true
    };
  }
}
</script>
```

### 列表渲染

#### 基本v-for循环渲染标签

模板引擎都会提供循环的支持。YJPL也不例外，YJPL是提供了一个v-for指令。基本的用法类似于foreach的用法。

``` html
<template>
 <page class="index">
   <ul>
    <li v-for="item in userList" :key="item.name"></li>
   </ul>
 </page>
</template>
<script>
import {
 YJPage,
} from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      userList: [
        {'name': 'zs', 'age': 18},
        {'name': 'ls', 'age': 22},
        {'name': 'ww', 'age': 25}
      ]
    };
  }
}
</script>
```

#### Template循环渲染多标签

上面的例子，我们演示的是 每次循环输出一个li标签。如果我们希望每次循环生成两个li标签呢？如果还有生成其他的标签呢？  
YJPL给我们提供了template标签，供我们用于v-for循环中进行处理。

``` html
<ul>
  <!-- 通过template标签，可以一次循环，输出两个li标签 -->
  <template v-for="item in items" :key="item.id">
    <li></li>
    <li class="divider"></li>
  </template>
</ul>
```

#### 关于v-for对应的数组的更新

由于YJPL的机制就是检测数据的变化，自动跟新HTML。数组的变化，YJPL之检测部分函数，检测的函数执行时才会触发视图更新。这些方法如下：
1. push()
2. pop()
3. shift()
4. unshift()
5. splice()
6. sort()
7. reverse()

## 事件处理

### 监听事件

YJPL提供了协助我们为标签绑定时间的方法，当然我们可以直接用dom原生的方式去绑定事件。YJPL提供的指令进行绑定也是非常方便，而且能让ViewModel更简洁，逻辑更彻底。所以还是推荐大家使用的。
YJPL提供了v-on指令帮助我们进行事件的绑定

``` html
<template>
 <page class="index">
  <!-- 为按钮绑定点击事件，执行counter += 1的任务。 -->
  <button v-on:click="counter += 1">增加 1</button>
  <button v-on:click="update">增加 1</button>
  <p>这个按钮被点击了 {{ counter }} 次。</p>
 </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YwdjView extends YJPage {
  data(){
    return {
      counter: 0
    };
  }
  methods(){
    return {
      update(){
        this.counter ++;
      }
    };
  }
}
</script>
```

### 事件描述符

在事件处理程序中调用 event.preventDefault() 或 event.stopPropagation() 是非常常见的需求。尽管我们可以在方法中轻松实现这点，但更好的方式是：方法只有纯粹的数据逻辑，而不是去处理 DOM 事件细节。  
为了解决这个问题，YJPL为 v-on 提供了事件修饰符。之前提过，修饰符是由点开头的指令后缀来表示的。
1. .stop
2. .prevent
3. .capture
4. .self
5. .once
6. .passive
``` html
<!-- 阻止单击事件继续传播 -->
<a v-on:click.stop="doThis"></a>
<!-- 提交事件不再重载页面 -->
<form v-on:submit.prevent="onSubmit"></form>
<!-- 修饰符可以串联 -->
<a v-on:click.stop.prevent="doThat"></a>
<!-- 只有修饰符 -->
<form v-on:submit.prevent></form>
<!-- 添加事件监听器时使用事件捕获模式 -->
<!-- 即内部元素触发的事件先在此处理，然后才交由内部元素进行处理 -->
<div v-on:click.capture="doThis">...</div>
<!-- 只当在 event.target 是当前元素自身时触发处理函数 -->
<!-- 即事件不是从内部元素触发的 -->
<div v-on:click.self="doThat">...</div>
```

使用修饰符时，顺序很重要；相应的代码会以同样的顺序产生。因此，用 v-on:click.prevent.self 会阻止所有的点击，而 v-on:click.self.prevent 只会阻止对元素自身的点击。

### 事件绑定的简写

YJPL中属性的绑定的简写直接是: === 'v-bind:'
而事件的缩写是直接变成@. 也就是说： v-on: === @ 看下面的例子：
``` html

<!-- 完整语法 -->
<a v-on:click="doSomething"></a>
<!-- 缩写 -->
<a @click="doSomething"></a>
```

## YJPL实例与生命周期

### data对象

数据绑定离不开data里面的数据。也是YJPL的核心属性。 它是YJPL绑定数据到HTML标签的数据源泉，另外YJPL框架会自动监视data里面的数据变化，自动更新数据到HTML标签上去。  
本质原理是：YJPL会自动将data里面的数据进行递归抓换成getter和setter，然后就可以自动更新HTML标签了，当然用getter和setter所以老的浏览器YJPL支持的不够好。  
data对象的类型：  
1. 类型是Object或者Function
2. 在单文件组件中，data必须是Function类型

``` js
// 创建普通的YJPL实例
const vm = new Yjpl({
  data: data
})

// 组件定义【后面会详细讲的】
// YJPL.extend() 中 data 必须是函数
var Component = Yjpl.extend({
  data: function () {   //这里必须是函数！！！！
    return { a: 1 }
  }
})
```

### computed

YJPL的计算属性(computed)的属性会自动混入YJPL的实例中。所有 getter 和 setter 的 this 上下文自动地绑定为 YJPL 实例。

``` js
/**
 * 类型
 * { 键：函数} { [key: string]: Function | { get: Function, set: Function } } 当然，可以省略setter,如果省略了setter，那么值就可以是普通函数，但是必须有返回值。
 **/
const vm = new Yjpl({
data: { a: 1 },
computed: {
  // 仅读取，值只须为函数
  aDouble: function () {
    return this.a * 2
  },
  // 读取和设置
  aPlus: {
    get: function () {
      return this.a + 1
    },
    set: function (v) {
      this.a = v - 1
    }
  }
}
})
vm.aPlus   // -> 2
vm.aPlus = 3
vm.a       // -> 2
vm.aDouble // -> 4
```

### methods 

methods 将被混入到 YJPL 实例中。可以直接通过 VM 实例访问这些方法，或者在指令表达式中使用。方法中的 this 自动绑定为 YJPL 实例。  
注意，不应该使用箭头函数来定义 method 函数 (例如 plus: () => this.a++)。理由是箭头函数绑定了父级作用域的上下文，所以 this 将不会按照期望指向 YJPL 实例，this.a 将是 undefined。 

``` js
const vm = new Yjpl({
  data: { a: 1 },
  methods: {
    plus: function () {
      this.a++
    }
  }
})
vm.plus()
vm.a // 2
```

### watch

一个对象，键是需要观察的表达式，值是对应回调函数。值也可以是方法名，或者包含选项的对象。

``` js
const vm = new Yjpl({
  data: {
    a: 1,
    b: 2,
    c: 3
  },
  watch: {
    // 监控a变量变化的时候，自动执行此函数
    a: function (val, oldVal) {
      console.log('new: %s, old: %s', val, oldVal)
    },
    // 深度 watcher
    c: {
      handler: function (val, oldVal) { /* ... */ },
      deep: true
    }
  }
})
vm.a = 2 // -> new: 2, old: 1
```

### 生命周期

YJPL实例有一个完整的生命周期，也就是从开始创建、初始化数据、编译模板、挂载Dom、渲染→更新→渲染、卸载等一系列过程，我们称这是YJPL的生命周期。通俗说就是YJPL实例从创建到销毁的过程，就是生命周期。

在YJPL的整个生命周期中，它提供了一系列的事件，可以让我们注册js方法，可以让我们达到控制整个过程的目的地。值得注意的是，在这些事件响应方法中的this直接指向的是YJPL的实例。
YJPL提供的可以注册的钩子如下：  

beforeCreate  
  在实例初始化之后，数据观测(data observer) 和 event/watcher 事件配置之前被调用。  

created  
  实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见。  

beforeMount  
  在挂载开始之前被调用：相关的 render 函数首次被调用。  

mounted  
el 被新创建的 vm.$el 替换，并挂载到实例上去之后调用该钩子。如果 root 实例挂载了一个文档内元素，当 mounted 被调用时 vm.$el 也在文档内。  

beforeUpdate  
数据更新时调用，发生在虚拟 DOM 重新渲染和打补丁之前。 你可以在这个钩子中进一步地更改状态，这不会触发附加的重渲染过程。  

updated  
由于数据更改导致的虚拟 DOM 重新渲染和打补丁，在这之后会调用该钩子。  
当这个钩子被调用时，组件 DOM 已经更新，所以你现在可以执行依赖于 DOM 的操作。然而在大多数情况下，你应该避免在此期间更改状态，因为这可能会导致更新无限循环。  
该钩子在服务器端渲染期间不被调用。  

beforeDestroy  
实例销毁之前调用。在这一步，实例仍然完全可用。  

destroyed  
  YJPL 实例销毁后调用。调用后，YJPL 实例指示的所有东西都会解绑定，所有的事件监听器会被移除，所有的子实例也会被销毁。 该钩子在服务器端渲染期间不被调用。  

### 生命周期执行顺序

加载渲染过程  
父beforeCreate->父created->父beforeMount->子beforeCreate->子created->子beforeMount->子mounted->父mounted

更新过程  
父beforeUpdate->子beforeUpdate->子updated->父updated  

销毁过程  
父beforeDestroy->子beforeDestroy->子destroyed->父destroyed

## 单文件组件

下面介绍如何在单文件组件中绑定components、props、data、computed、methods、watch、filters、directives

``` html
<template>
 <page class="index">
  {{hi}}
  <my-comp></my-comp>
 </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
import MyComp from './myComp.yjpl'; 
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
    }
  }
  watch(){
    return {
      hi(){
        console.log('hi变量发生变化');
      }
    }
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

在Vue中的写法如下：

``` html
<template>
 <page class="index">
  {{hi}}
  <my-comp></my-comp>
 </page>
</template>

<script>
export default {
  components: {
    'my-comp': MyComp
  },
  directives: {
    focus: {
      // 指令的定义
      inserted: function (el) {
        el.focus()
      }
    }
  },
  filters: {
    capitalize: function (value) {
      if (!value) {
        return '';
      };
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    }
  },
  props:{
    name: {
      type: String
    }
  },
  data(){
    return {
      hi: 'hi'
    };
  },
  computed: {
    tip(){
      return this.hi + this.name;
    }
  },
  watch: {
    hi(){
      console.log('hi变量发生变化');
    }
  },
  methods: {
    sayHi(){
      consolo.log(this.hi);
    }
  },
  created(){},
  beforeMount(){},
  mounted(){},
  beforeDestroy(){},
  destroyed(){}
}
</script>
```