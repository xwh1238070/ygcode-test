## RangeNumber 数字区间输入框
通过鼠标或键盘输入字符。   
基于tl-input封装,数据类型为数字，且第第一个输入框数值不能大于第二个输入框数值，不符合条件默认为空

### 基础用法

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number  :range-separator="rangeSeparator" v-model="datas" :placeholder="placeText"></tl-range-number>
  </div>
 
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[],
      placeText:'请输入数值'
    }
  },
  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::


### 带图标用法

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" suffix-icon="el-icon-date"  prefix-icon="el-icon-search"></tl-range-number>
  </div>
</template>

<script>
export default {
  data() {
    return {
      datas:[]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 禁用

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" disabled ></tl-range-number>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[]
    }
  },  
};
</script>

```
:::


### 只读

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas3" :range-separator="rangeSeparator" readonly ></tl-range-number>
  </div> 
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas3:[1,2]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::


### 可清空

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas3" :range-separator="rangeSeparator" clearable ></tl-range-number>
  </div>  
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas3:[1,2]
    }
  },  
};
</script>
<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 最大值最小值

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" :max="30" :min="0"></tl-range-number>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 设置小数位

:::demo 在input失焦的时候进行格式化，聚焦的时候显示真实的数据。`precision`设置格式化小数位位数,为`null`或者`undefined`则不设置小数位

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" precision="2"></tl-range-number>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 格式化-科学计数

:::demo 科学计数设置`type`：`scientificCount`

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" :format="format1"></tl-range-number>
  </div> 
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[],
      format1: {
          type: 'scientificCount'
      }
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 格式化-折合

:::demo 折合设置`type`：`convert`，`numUnit`：折合单位。`numUnit`可选值：`perthousand`(0.001)、`percent`(0.01)、`ten`(十)、`hundred`(百)、`thousand`(千)、`tenThousand`(万)、`hundredThousand`(十万)、`million`(百万)、`tenMillion`(千万)、`hundredMillion`(亿)、`billion`(十亿)
 
```html
<template>
  <div class="outer">
     <tl-range-number v-model="datas" :range-separator="rangeSeparator" :format="format2"></tl-range-number>
  </div> 
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[],
      format2: {
        type: 'convert',
        numUnit: 'tenThousand'
      }
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 格式化-百分比

:::demo 百分比设置`type`：`percentage`，`direct`：遵循3.0规则，设置为`true`时，0.3会转成0.3%，为`false`时，0.3会转成30%，默认为`false`

```html
<template>
  <div class="outer"> 
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" :format="format1"></tl-range-number>
    </tl-range-number>
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" :format="format2"></tl-range-number>
  </div>
   
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[0.3,0.3],
      format1: {
        type: 'percentage',
      },
      format2: {
        type: 'percentage',
        direct: true
      }
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 格式化-货币

:::demo 货币设置`type`：`currency`，`symbol`：货币前面的货币符号

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" :format="format"></tl-range-number>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[10,20],
      format: {
        type: 'currency',
        symbol: '￥'
      }
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 格式化-负数显示带颜色

:::demo 在input失焦的时候进行格式化，聚焦的时候显示真实的数据。`negative-color`设置字体颜色，当且仅当值为负数时才生效

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" negative-color="red"></tl-range-number>
  </div>  
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 格式化-负数显示带括号

:::demo 在input失焦的时候进行格式化，聚焦的时候显示真实的数据。`bracketabled`设置字体为负数时是否显示括号，当且仅当值为负数时才生效

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" bracketabled></tl-range-number>
  </div>  
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::

### 限制最大最小长度

:::demo

```html
<template>
  <div class="outer">
    <tl-range-number v-model="datas" :range-separator="rangeSeparator" maxlength="10" minlength="4" ></tl-range-number>
  </div>  
</template>

<script>
export default {
  data() {
    return {
      rangeSeparator: '至',
      datas:[]
    }
  },  
};
</script>

<style>
  .outer{
    width:400px;
  }
</style>

```
:::




### Input Attributes

| 参数          | 说明            | 类型            | 可选值                 | 默认值   |
|-------------  |---------------- |---------------- |---------------------- |-------- |
| value / v-model | 绑定值           | array  | — | — |
| maxlength     | 原生属性，最大输入长度      | number          |  —  | — |
| minlength     | 原生属性，最小输入长度      | number          | — | — |
| placeholder   | 输入框占位文本    | string          | — | — |
| rangeSeparator| 连接输入框的字    | string          | — | 至 |
| clearable     | 是否可清空        | boolean         | — | false |
| disabled      | 禁用            | boolean         | — | false   |
| step | 原生属性，设置输入字段的合法数字间隔 | — | — | — |
| prefix-icon   | 第一个输入框图标    | string          | — | — |
| suffix-icon   | 第二个输入框图标    | string          | — | — |
| readonly | 原生属性，是否只读 | boolean | — | false |
| max | 原生属性，设置最大值(只对数值类型生效) | — | — | — |
| min | 原生属性，设置最小值（只对数值类型生效) | — | — | — |
| align | 文字对齐方式 | string | left, right, center | left |
| precision | 小数位 | string | number | — |
| format | 格式化参数，同 `tl-input` | object | — | — |
| negative-color | 负数的字体颜色（仅当负数生效） | string | — | — |
| bracketabled | 负数是否用括号表示（仅当负数生效） | boolean | true, false | false |

### format Attributes

| 参数          | 说明            | 类型            | 可选值                 | 默认值   |
|-------------  |---------------- |---------------- |---------------------- |-------- |
| type     | 格式化类型     | string |  scientificCount， convert， percentage， currency | — |



### Input Events



| 事件名称 | 说明 | 回调参数 |
|---------|--------|---------|
| blur | 在 Input 失去焦点时触发 | (event: Event) |
| focus | 在 Input 获得焦点时触发 | (event: Event) |
| change | 仅在输入框失去焦点或用户按下回车时触发 | (value: array) |
| input | 在 Input 值改变时触发 | (value: array)|


