## Layout 上下左右布局


容器中的内容的具体布局根据插槽slot的值来决定


### 上下基础布局

上下布局基础用法
上下布局有3个值可配置，top:上部分可配置高度，percent：上部分可配置高度，值为百分比，bottom:下可配置高度
拖拽为默认，若不需要，需要设置为false
:::demo

```html
<template>
  <div class="layoutDoc">
    <tl-layout :direction="direction" :type="type"  :height="height" :is-drag="isDrag">
      <div slot="top" class="layoutTop">top</div>
      <!-- <div slot="topPercent" class="layoutTop">topPercent</div>      -->
      <div slot="main" class="layoutMain">main</div>
  </tl-layout>
  </div>
   
</template>

<script>
export default {
 
  data(){
    return{
        direction:'vertical',
        type:'top',
        height:60,
        isDrag:false,
        //type:'percent',
       // height:'50%'//type为percent时高度为百分比              
    };
  }
};
</script>

<style>
.layoutDoc{
  height:300px;
}
.layoutTop{
  height:100%;
  background-color:#d3dce6;
}
.layoutMain{
  height:100%;
  background-color:#e5e9f2;
}


</style>

```
:::

### 上下布局可拖拽和收缩展开


上下布局配置可拖拽和收缩按钮用法

可拖拽或者有收缩按钮后 ，可配置最小值,如果不配置最小值，最小默认为0

:::demo

```html
<template>
  <div class="layoutDoc">
    <tl-layout :direction="direction" :type="type" :is-contractbtn="isContractbtn" :height="height" :min-height="minHeight">
      <!-- <div slot="top">top</div> -->    
      <div slot="main" class="layoutMain">main</div> 
      <div slot="bottom" class="layoutBottom">bottom</div>                     
  </tl-layout>
  </div>
   
</template>

<script>
export default{
  data(){
    return{
        direction:'vertical',
        type:'bottom',
        isContractbtn:true,
        minHeight:20,
        height:60,             
    };
  }
};
</script>

<style>
.layoutDoc{
  height:300px;
}
.layoutBottom{
  height:100%;
  background-color:#d3dce6;
}
.layoutMain{
  height:100%;
  background-color:#e5e9f2;
}

</style>

```
:::

### 左右基础布局

左右布局基础用法
左右布局有3个值可配置，left:左边宽度可配置，percent：左边宽度可配置，值为百分比，right:右边宽度可配置
:::demo

```html
<template>
  <div class="layoutDoc">
    <tl-layout :direction="direction" :type="type" :width="width" :is-drag="isDrag">
      <div slot="left" class="layoutLeft">left</div>
      <!-- <div slot="leftPercent">leftPercent</div> -->
      <div slot="main" class="layoutMain">main</div>
      <!-- <div slot="right">right</div> -->
  </tl-layout>
  </div>   
</template>

<script>
export default {
  data(){
    return{
        direction:'horizon',
        type:'left',
        width:300,
        isDrag:false,
        //type:percent,
        //width:30%,type为percent时宽度为百分比              
    };
  }
};
</script>

<style>
.layoutDoc{
  height:300px;
}
.layoutLeft{
  height:100%;
  background-color:#d3dce6;
}
.layoutMain{
  height:100%;
  background-color:#e5e9f2;
}

</style>

```
:::
### 左右布局可拖拽和收缩展开


左右布局配置可拖拽和收缩按钮用法

配置可拖拽或者收缩按钮后 ，可配置最小值,如果不配置最小值，最小默认为0

:::demo

```html
<template>
  <div class="layoutDoc">
    <tl-layout :direction="direction" :type="type" :is-contractbtn="isContractbtn" :width="width">    
      <div slot="main" class="layoutMain">main</div>
      <div slot="right" class="layoutRight">right</div>
    </tl-layout>
  </div>
   
</template>

<script>
export default {
  data(){
    return{
        direction:'horizon',
        type:'right',
        isContractbtn:true,
        width:300,             
    };
  }
};
</script>

<style>
.layoutDoc{
  height:300px;
}
.layoutRight{
  height:100%;
  background-color:#d3dce6;
}
.layoutMain{
  height:100%;
  background-color:#e5e9f2;
}

</style>

```
:::

### 左右布局嵌套可拖拽和收缩展开


左右布局嵌套可拖拽和收缩展开用法

嵌套的时候必须每个容器配置唯一的drag-id拖拽才能正常使用

:::demo

```html
<template>
  <div class="layoutDoc">
    <tl-layout :direction="direction" :type="typeR" :is-contractbtn="isContractbtn" :width="width" :drag-id="dragOne">    
      <div slot="main" class="layoutMain">
        <tl-layout :direction="direction" :type="typeL" :is-contractbtn="isContractbtn" :width="width" :drag-id="dragTwo">
          <div slot="left" class="layoutLeft">内层left</div>    
          <div slot="main" class="layoutMain">main</div>                       
        </tl-layout>
      </div>
      <div slot="right" class="layoutRight">外层right</div>
    </tl-layout>
  </div>
   
</template>

<script>
export default {
  data(){
    return{
        dragOne:'1',
        dragTwo:'2',
        direction:'horizon',
        typeR:'right',
        typeL:'left',
        isContractbtn:true,
        width:300,             
    };
  }
};
</script>

<style>
.layoutDoc{
  height:300px;
}
.layoutRight{
  height:100%;
  background-color:#d3dce6;
}
.layoutLeft{
  height:100%;
  background-color:#d3dce6;
}
.layoutMain{
  height:100%;
  background-color:#e5e9f2;
}

</style>

```

:::


### Attributes

拖拽默认值是true,如果想取消要配置为false
收起展开按钮如需要展示，需要配置值为true
若没有配置参数，默认为上下布局，布局类型为left
若有配置布局方向和布局类型，没有配置宽度或者高度，默认宽为300，高度为60，布局类型为percent的时候，宽度或者高度若没配置默认值为30%

| 参数              | 说明                             | 类型            | 可选值 | 默认值 |
| ----------------- | -------------------------------- | --------------- | ------ | ------ |
| direction | 布局方向      | string |    vertical/horizon   | -    |
| type | 布局类型 ,值为percent的时候为上或者左边可配置高度或者宽度，值为百分比    | string |    left/right/top/bottom/percent   | -    |
| isContractbtn | 是否配置收起展开按钮      | boolean |   true/false   | -    |
| isDrag | 是否可拖拽      | boolean |   true/false   |  true    |
| width | 可配置宽度      | string/number |   -   | -    |
| height | 可配置高度      | string/number |   -   | -    |
| minHeight | 最小收缩高度      | string/number |   -   | -    |
| minWidth | 最小收缩宽度      | string/number |   -   | -    |
| dragId | 被拖拽元素的唯一标识，当容器用于多层嵌套的时候需要配置这个属性      | string/number |   -   | 1    |

### slot

| 参数 | 说明               | 类型 | 可选值 | 默认值 |
| ------ | ------------ | -------- | ---- | --- |
| top  | 放置内容插槽名字 | string | - | - |
| topPercent  | 放置内容插槽名字 | string | - | - |
| left  | 放置内容插槽名字 | string | - | - |
| leftPercent  | 放置内容插槽名字 | string | - | - |
| main  | 放置内容插槽名字 | string | - | - |
| bottom  | 放置内容插槽名字 | string | - | - |
| right  | 放置内容插槽名字 | string | - | - |
