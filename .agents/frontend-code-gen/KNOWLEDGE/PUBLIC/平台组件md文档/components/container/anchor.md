## Anchor 锚点

返回页面内对应锚点操作按钮

### 基础用法

通过data传递锚点数据，通过element传递外层容器;
注意：页面的对应位置的必须有id,且一定要和data中传递的对应的id名字保持一致;
如果点击定位和回到顶部不生效，需要检查当前页面动的滚动条是否绑定到了element上；
<!--传入element这个元素的外层最好再加一层设置 overflow: hidden来把要操作的容器和项目外面的元素隔开，以免影响滚动条的绑定-->

:::demo

```html
<template> 
  <div class="tl-anchor-outerBox" >
    <div class="tl-anchor-container">
      <h1>锚点测试</h1>
      <div id="anchor0">    
        <h3>基本信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor1">   
        <h3>单据信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor2">     
        <h3>单据详情</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor3">     
        <h3>最后一行</h3>
        <div class="anchor__part"></div>
      </div>
      <tl-anchor :data="anchorDatas" :element="anchorEl"/>
    </div>
  </div>
</template>
<script>

export default {
  data(){
    return{
        anchorEl:'.tl-anchor-container',
        anchorDatas:[
          {
            title:'基本信息',
            id:'anchor0'
          },
          {
            title:'单据信息',
            id:'anchor1'
          },
          {
            title:'单据详情',
            id:'anchor2'
          },
          {
            title:'最后一行',
            id:'anchor3'
          }
        ],
    };
  }
}
</script>

<style>
.tl-anchor-outerBox{
  height: 400px;
  overflow-y: auto;
  transform: matrix(1, 0, 0, 1, 0, 0);
}
.tl-anchor-container{
  height:100%;
  overflow-y: auto;
  scrollbar-width:none;
}
.anchor__part{
  width:300px;
  height:300px;
}
</style>

```

:::


### 横向锚点 ^(8.5.0)

通过`type`设置锚点`horizontal`水平对齐或者`vertical`垂直对齐

:::demo

```html
<template> 
  <div class="tl-anchor-outerBox" >
    <div class="tl-anchor-container1">
      <h1>锚点测试</h1>
      <div id="anchor4">    
        <h3>基本信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor5">   
        <h3>单据信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor6">     
        <h3>单据详情</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor7">     
        <h3>最后一行</h3>
        <div class="anchor__part"></div>
      </div>
      <tl-anchor type="horizontal" :data="anchorDatas" :element="anchorEl"/>
    </div>
  </div>
</template>
<script>

export default {
  data(){
    return{
        anchorEl:'.tl-anchor-container1',
        anchorDatas:[
          {
            title:'基本信息',
            id:'anchor4'
          },
          {
            title:'单据信息',
            id:'anchor5'
          },
          {
            title:'单据详情',
            id:'anchor6'
          },
          {
            title:'最后一行',
            id:'anchor7'
          }
        ],
    };
  }
}
</script>

<style>
.tl-anchor-outerBox{
  height: 400px;
  overflow-y: auto;
  transform: matrix(1, 0, 0, 1, 0, 0);
}
.tl-anchor-container1{
  height:100%;
  overflow-y: auto;
  scrollbar-width:none;
}
.anchor__part{
  width:300px;
  height:300px;
}
</style>

```

:::

### 图标格式锚点 ^(8.5.0)

通过`icon`设置锚点使用默认图标方式展示，若data数据中传递`icon`参数，则以自定义传递的`icon`为主，默认在右下方

:::demo

```html
<template> 
  <div class="tl-anchor-outerBox" >
    <div class="tl-anchor-container2">
      <h1>锚点测试</h1>
      <div id="anchor8">    
        <h3>基本信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor9">   
        <h3>单据信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor10">     
        <h3>单据详情</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor11">     
        <h3>最后一行</h3>
        <div class="anchor__part"></div>
      </div>
      <tl-anchor icon :data="anchorDatas" :element="anchorEl"/>
    </div>
  </div>
</template>
<script>

export default {
  data(){
    return{
        anchorEl:'.tl-anchor-container2',
        anchorDatas:[
          {
            title:'基本信息',
            id:'anchor8'
          },
          {
            title:'单据信息',
            id:'anchor9'
          },
          {
            title:'单据详情',
            id:'anchor10'
          },
          {
            title:'最后一行',
            id:'anchor11'
          }
        ],
    };
  }
}
</script>

<style>
.tl-anchor-outerBox{
  height: 400px;
  overflow-y: auto;
  transform: matrix(1, 0, 0, 1, 0, 0);
}
.tl-anchor-container2{
  height:100%;
  overflow-y: auto;
  scrollbar-width:none;
}
.anchor__part{
  width:300px;
  height:300px;
}
</style>

```

:::

### 横向图标格式锚点 ^(8.5.0)

横向图标锚点默认在左下方

:::demo

```html
<template> 
  <div class="tl-anchor-outerBox" >
    <div class="tl-anchor-container3">
      <h1>锚点测试</h1>
      <div id="anchor12">    
        <h3>基本信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor13">   
        <h3>单据信息</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor14">     
        <h3>单据详情</h3>
        <div class="anchor__part"></div>
      </div>
      <div id="anchor15">     
        <h3>最后一行</h3>
        <div class="anchor__part"></div>
      </div>
      <tl-anchor type="horizontal" icon :data="anchorDatas" :element="anchorEl"/>
    </div>
  </div>
</template>
<script>

export default {
  data(){
    return{
        anchorEl:'.tl-anchor-container3',
        anchorDatas:[
          {
            title:'基本信息',
            id:'anchor8'
          },
          {
            title:'单据信息',
            id:'anchor9'
          },
          {
            title:'单据详情',
            id:'anchor10'
          },
          {
            title:'最后一行',
            id:'anchor11'
          }
        ],
    };
  }
}
</script>

<style>
.tl-anchor-outerBox{
  height: 400px;
  overflow-y: auto;
  transform: matrix(1, 0, 0, 1, 0, 0);
}
.tl-anchor-container3{
  height:100%;
  overflow-y: auto;
  scrollbar-width:none;
}
.anchor__part{
  width:300px;
  height:300px;
}
</style>

```

:::

### Attributes

回到顶部按钮开放了可自定义图标的功能，有两种方式1.把原来的隐藏，再锚点标签中插入新的图标；2.直接通过icon 属性传图标的类名，类名可在文档中图标中找

| 参数              | 说明                             | 类型            | 可选值 | 默认值 |
| ----------------- | -------------------------------- | --------------- | ------ | ------ |
| data            | 锚点数据                   | array          |        | []       |
| element | 用于外层容器 ，可以传选择器/DOM元素      | string/HTMLElement |        | -    |
| type ^(8.5.0) | 设置锚点对齐方式   | string |    `horizontal`/`vertical`    |  `vertical`   |
| icon ^(8.5.0) | 图标模式（默认图标）   | boolean |    `true`/`false`    |  `false`   |

### data

| 参数 | 说明               | 类型 | 可选值 | 默认值 |
| ------ | ------------ | -------- | ---- | --- |
| title  | 锚点标题 | string | - | - |
| id  | 锚点id | string | - | - |
| icon ^(8.5.0)  | 锚点图标 | string | - | - |

### Events

| 事件名称   | 说明                        | 回调参数 |
| ---------- | --------------------------- | -------- |
| back-to-top | 回到顶部的回调           | —        |
| click     | 点击锚点的回调 | —        |  传入的对应锚点的信息 |
| scroll      | 滚动当前容器的回调           | —        |
