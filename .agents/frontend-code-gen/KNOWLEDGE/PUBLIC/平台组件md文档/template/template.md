# YJPL 模板开发

YJPL 提供了一系列的基础模板，供开发继承扩展开发，仅需要少量的配置便可实现规范的 UI 界面。

## 使用方法

安装 `yjpl-template`：

```shell
npm install yjpl-template
```

## 模板基础使用方法

### 模板继承

在模板中，通过 YJPL 语法继承模板:

```html
<template>
  <page extends="yjpl-template/business/list-template.yjpl"></page>
</template>

<script>
  import ListTemplate from "yjpl-template/business/list-template.yjpl";
  import ListTemplateBs from "./list-template.ts";

  export default class ListPage extends ListTemplate {
    constructor() {
      super();
      this.bs = new ListTemplateBs();
    }

    data() {
      return {
        ...this.bs.getData(),
      };
    }
  }
</script>
```

详细可查阅[继承管理](../features/extends.md)文档。

**\*`YJPL文件`仅作视图操作，如控制界面元素、事件绑定、调用消息提示/弹窗等界面行为，所有业务运算和取数由`业务ts文件`实现。**

### 业务 ts

```typescript
import { YJBusiness, YJState } from "yjpl-core";

class ListPageBs extends YJBusiness {
  constructor() {
    super();
    this.dataModel = {};
    this.metaModel = {};
    this.stateModel = {};
    this.dataSource = {};
  }
  getData() {
    return {
      dataModel: this.dataModel,
      metaModel: this.metaModel,
      stateModel: this.stateModel,
      dataSource: this.dataSource,
    };
  }
}

export default ListPageBs;
```

**\*`业务ts文件`仅作业务操作，如业务运算和取数，所有界面操作由`YJPL文件`实现。**

### 模板 model

YJPL 模板的实现围绕着多个`model`实现，以下为各个`model`的作用。

#### dataModel 数据模型

存在于 `YJPL文件` 和 `业务ts文件`，用于数据的储存、调用和双向绑定，在 `业务ts文件` 中初始化。

#### metaModel 元模型

仅存在于 `业务ts文件`，用于存放源数据配置，如表单项、按钮、表格等等。

#### pageModel 页面模型

仅存在于 `YJPL文件`，用于描述界面区域信息和元素信息，可将元模型元素赋予至相应的区域，可以通过 `$pageModel` 操作。

##### $pageModel

在 `YJPL文件中`，通过 `this.$pageModel` 调用。

```javascript
mounted() {
  // 通过 set 方法，配置相应区域的元素
  this.$pageModel.set('form', ['name', 'text']);
}
```

| 方法名   | 说明                                                   | 参数                                                                           |
| -------- | ------------------------------------------------------ | ------------------------------------------------------------------------------ |
| set      | 将 `metaModel` 内的元素设置入 `pageModel` 内的相应区域 | (area, name \ name[]) 接收两个参数，1. 区域名称 2. `metaModel` 内元素的 name   |
| get      | 获取 `metaModel` 内相应元素的信息                      | (name) => object 接收一个参数，1. `metaModel` 内元素的 key                     |
| setAttr  | 设置相应元素的属性                                     | (name, attrName, value) 接收三个参数，1. 元素的 name 2. 属性的 key 3. 属性的值 |
| getAttr  | 获取相应元素的属性                                     | (name, attrName) 接收两个参数，1. 元素的 name 2. 属性的 key                    |
| setState | 设置相应元素的状态                                     | (name, state) 接收两个参数，1. 元素的 name 2. 状态的值                         |

#### stateModel 状态模型

存在于 `YJPL文件` 和 `业务ts文件`，用于描述界面元素的状态，如显示、隐藏、禁用、只读等，在 `业务ts文件` 中初始化，在 `YJPL文件` 中修改，可与 `v-ctrl-state` 绑定使用。

详细可查阅[状态管理](../features/state.md)文档。

#### eventsModel 事件模型

仅存在于 `YJPL文件`，用于界面元素事件绑定，可与元数据相应元素绑定，key 为对应元素的 `name`，值为事件合集对象，可传入 `methods` 内对应方法。

```javascript
data() {
  return {
    eventsModel: {
      test: {
        input: this.handleInput
      }
    }
  }
}

methods() {
  handleInput() {
    console.log('hanlde input');
  }
}
```

#### i18nModel 国际化模型

仅存在于 `YJPL文件`，用于界面国际化，可以直接通过链式调用项目内配置的国际化字段。

详细可查阅[国际化工具](../utils/i18n.md)文档。

```ts
this.i18nModel.input.placeholder;
```
