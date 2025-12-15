# 继承扩展

在编译期支持业务功能、模板及子组件的继承关系，通过继承可以快速实现与基业务功能类似的子功能。

1. 基于父级业务功能，通过新增、删除、修改等，实现新的业务及模板功能。
2. 基于父级的业务逻辑代码进行继承，实现面向业务和模板的开发能力。

## 视图扩展规则

YJPL 有以下三种扩展规则

### 指定模板扩展

通过根节点 page 的 extends 指定继承的模板

```HTML
<!-- 根节点page继承template.yjpl模板 -->
<page extends="../template.yjpl">…</page>
```

### 基于属性扩展

通过标签的 plugins 属性指定扩展

```HTML
<!-- 将div以append(新增)的方式扩展到名为page-content的标签 -->
<div plugins="{type: append, target: page-content}">内容</div>

<!-- 实际效果 -->
<page-content>
  <div>内容</div>
</page-content>
```

### 基于标签扩展

通过 plugins 标签指定扩展方式

```HTML
<!-- 将plugins标签中的内容以append(新增)的方式扩展到名为page-content的标签 -->
<plugins type="append" target="page-content">
  插入的内容
</plugins>

<!-- 实际效果 -->
<page-content>
  插入的内容
</page-content>
```

## Plugins Attributes

<table>
  <tr>
    <th>参数</th>
    <th>说明</th>
    <th>类型</th>
    <th>可选值</th>
  </tr>
  <tr>
    <td>type</td>
    <td>指定扩展类型</td>
    <td>string</td>
    <td>insert(插入)、replace(替换)、remove(移除)、prepend(向前追加)、append(向后追加)</td>
  </tr>
  <tr>
    <td rowspan="2">position</td>
    <td>指定插入的位置(仅在 type 取值 insert 时生效)</td>
    <td>string</td>
    <td>before(目标前)、after(目标后)</td>
  </tr>
  <tr>
    <td>指定插入的位置(仅在 type 取值 append 时生效，用于指定插入成为第几个子节点)</td>
    <td>number</td>
    <td>-</td>
  </tr>
  <tr>
    <td>target</td>
    <td>指定扩展的目标</td>
    <td>string</td>
    <td>
      <a href="#targetRules">Target取值规则</a>
    </td>
  </tr>
    <tr>
    <td>attrs</td>
    <td>给指定目标元素添加属性</td>
    <td>string</td>
    <td>-</td>
  </tr>
</table>

### 不同 Type 示例

```HTML
<!-- 1.insert 插入至目标元素-->
<plugins type="insert" target="page-content">内容</plugins>
<!-- 实际效果 -->
<page-content>内容</page-content>

<!-- 2.replace 替换目标元素-->
<plugins type="insert" target="page-content">
  <div>内容</div>
</plugins>
<!-- 实际效果 -->
<div>内容</div>

<!-- 3.remove 移除目标元素-->
<plugins type="remove" target="page-content"></plugins>
<!-- 实际效果 -->
<!-- 目标元素被移除 -->


<!-- 4.append 新增至目标元素-->
<plugins type="append" target="page-content">内容</plugins>
<!-- 实际效果 -->
<page-content>内容</page-content>
```

### 不同 Position 示例

1. type="append"

```HTML
<plugins type="append" target="page-content" position="2">
  <div>2.1</div>
  <div>2.2</div>
</plugins>
<plugins type="append" target="page-content" position="3">
  <div>3</div>
</plugins>
<plugins type="append" target="page-content" position="1">
  <div>1</div>
</plugins>
<plugins type="append" target="page-content" position="0">
  <div>0</div>
</plugins>

<!-- 实际效果 -->
<page-content>
  <div>0</div>
  <div>2.1</div>
  <div>2.2</div>
  <div>1</div>
  <div>3</div>
</page-content>

<!-- 1. 扩展会以从上到下的顺序加载，当position的值大于等于目标元素子元素个数时自动排到末尾
     2. 同一个plugins标签中的多个元素在按照position排序时默认作为一个整体 -->
```

2. type="insert"

```HTML
<plugins type="insert" target="page-content" position="after">
  <div>2.1</div>
  <div>2.2</div>
</plugins>
<plugins type="insert" target="page-content" position="before">
  <div>3</div>
</plugins>

<!-- 实际效果 -->

<div>3</div>
<page-content></page-content>
<div>2.1</div>
<div>2.2</div>
```

<span id="targetRules"> </span>

## Target 取值规则

1. 基于组件 ID + 序号，例如：“#demo@0”，表示第一个 id 为 demo 的节点
2. 基于组件 Class + 序号，例如：“.demo@1”，表示第二个 class 为 demo 的节点
3. 基于元素 + 序号，例如：“demo@2”，表示第三个标签名为 demo 的节点
4. 基于组件area属性，例如“$area1”,表示标签area属性值为area1的节点

## 示例

template.yjpl

```HTML
<template>
  <page class="standard-layout-template v-flex scroll" ref="page">
    <page-header class="flex-none head">

    </page-header>
    <page-content class="flex-auto-size body">

    </page-content>
    <page-footer class="footer">

    </page-footer>
  </page>
</template>
```

页面

```HTML
<template>
  <page extends="../template.yjpl">
   <div  plugins="{type:'append',target:'page-header@0'}">添加内容</div>
		<plugins type="append" target="page-content@0">
			<div>新增</div>
    </plugins>
  </page>
</template>
```

继承模板的页面等价于

```HTML
<template>
  <page class="standard-layout-template v-flex scroll" ref="page">
    <page-header class="flex-none head">
      <div  plugins="{type:'append',target:'page-header@0'}">添加内容</div>
    </page-header>
    <page-content class="flex-auto-size body">
      <div>新增</div>
    </page-content>
    <page-footer class="footer">

    </page-footer>
  </page>
</template>
```
### attrs自定义属性示例

template.yjpl

```HTML
<template>
  <page class="standard-layout-template v-flex scroll" ref="page">
    <page-header class="flex-none head">

    </page-header>
    <page-content class="flex-auto-size body">

    </page-content>
    <page-footer class="footer">

    </page-footer>
  </page>
</template>
```
页面

```HTML
<template>
  <page extends="../template.yjpl">
    <plugins type="append" target="page-content@0" 
     attrs="{:userData=userData, customattr2=456,@click=trigger}">
    </plugins>
  </page>
</template>
```
继承模板的页面等价于

```HTML
<template>
  <page class="standard-layout-template v-flex scroll" ref="page">
    <page-header class="flex-none head">
    
    </page-header>
    <page-content class="flex-auto-size body" :userData="userData" customattr2= "456" @click = "trigger">
      
    </page-content>
    <page-footer class="footer">

    </page-footer>
  </page>
</template>
```
