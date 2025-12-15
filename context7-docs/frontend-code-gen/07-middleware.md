# YJPL前端代码生成智能体 - 中台组件集成

## 中台组件概述

中台组件是企业级应用中的核心组件库，提供统一的业务流程处理能力，包括待办管理、用户中心、主数据选择等功能。

## 中台组件分类

### 1. 业务中台组件

#### 核心模块
- **yjpl-template**: 模板系统（单据、查询、列表、操作等）
- **yjpl-ui**: UI组件库（表单、表格、对话框等）
- **yjpl-qzz**: 高性能表格组件
- **yjpl-core**: 核心框架
- **yjpl-utils**: 工具类库

#### 完整引入

```typescript
import Yjpl from 'yjpl';
import YjplUI from 'yjpl-ui';
import 'yjpl-ui/lib/theme-chalk/index.css';
import App from './App.yjpl';
import i18n from './i18n';

Yjpl.use(YjplUi, {
  i18n: (key: string, opts: any) => i18n.t(key, opts)
});

new Yjpl({
  i18n,
  render: h => h(App)
}).$mount('#app');
```

#### 按需引入

```bash
npm install babel-plugin-component -D
```

配置 .babelrc:
```json
{
  "plugins": [
    [
      "component",
      {
        "libraryName": "yjpl-ui",
        "styleLibraryName": "theme-chalk"
      }
    ]
  ]
}
```

### 2. 中台主数据选择控件

#### 通用使用模式

所有中台选择控件遵循统一的使用模式：

**组件引入模式：**
```javascript
// 1. 在 index.yjpl 中引入组件
import ComponentName from 'package-path/component.yjpl';

// 2. 在组件中注册
components() {
  return {
    'component-name': ComponentName
  };
}

// 3. 表格编辑器引入（在 Business.ts 中）
import ComponentEdit from 'package-path/component.grid.edit.js';
```

**在 MetaModel 中使用：**
```javascript
query: [
  {
    name: 'fieldName',
    dataType: 'component-type',
    label: '字段标签',
    value: [],
    multiple: true,
    vipAddress: '/necp/mapp/metamodel'
  }
]
```

**在 QZZ 表格中使用：**
```javascript
grid: {
  colModels: [
    {
      name: 'fieldName',
      index: 'fieldName',
      width: 150,
      editType: ComponentEdit,
      ecp: true
    }
  ]
}
```

#### 常用中台选择控件

| 控件名称 | 功能描述 | 包名 |
|---------|---------|------|
| 币种选择 | 选择币种信息 | jt.tswan.components |
| 对象分类 | 选择对象分类 | jt.tswan.cp.dcc.biz.web |
| 枚举选择 | 选择枚举值 | jt.tswan.components |
| 管理对象 | 选择管理对象 | jt.tswan.cp.dcc.biz.web |
| 地区选择 | 选择地区信息 | jt.tswan.components |
| 业务分类 | 选择业务分类 | jt.tswan.cp.dcc.biz.web |
| 单位选择 | 选择组织单位 | jt.tswan.components |
| 用户选择 | 选择用户信息 | jt.tswan.components |

#### 通用参数

| 参数 | 说明 | 类型 | 默认值 |
|------|------|------|--------|
| v-model | 绑定值 | string / Array | - |
| multiple | 是否多选 | boolean | false |
| disabled | 是否禁用 | boolean | false |
| readonly | 是否只读 | boolean | true |
| vipAddress | 项目组地址 | String | '' |
| ecp | 是否走ECP服务 | boolean | false |
| option | 配置选项 | Object | - |
| filterOption | 过滤条件 | Object | - |

#### 通用事件

```javascript
@input="handleInput"           // 返回所选值
@input:data="handleInputData"  // 返回所选值对应的对象数据
@change="handleChange"         // 值发生变化时触发
@clear="handleClear"           // 清空按钮点击时触发
@blur="handleBlur"             // 失去焦点时触发
@focus="handleFocus"           // 获得焦点时触发
```

## 具体组件使用

### 1. 币种选择控件（currency-select）

**安装：**
```bash
npm install jt.tswan.components@XX
```

**基本使用：**
```html
<template>
  <div class="currency-select">
    <currency-select
      v-model="currencyId"
      :vip-address="vipAddress"
      :ecp="true"
      @input:data="handleCurrencyChange">
    </currency-select>
  </div>
</template>

<script lang="ts">
import CurrencySelect from 'jt.tswan.components/packages/currency-select/currency.yjpl';

export default {
  components: {
    'currency-select': CurrencySelect
  },
  
  data() {
    return {
      currencyId: '',
      vipAddress: '/necp/mapp/metamodel'
    };
  },
  
  methods: {
    handleCurrencyChange(data) {
      console.log('选中的币种:', data);
    }
  }
}
</script>
```

**在表格中使用：**
```javascript
import CurrencyEdit from 'jt.tswan.components/packages/currency-select/currency.grid.edit.js';

// 在 Model.ts 中
grid: {
  colModels: [
    {
      name: 'currency',
      index: 'currency',
      width: 150,
      editType: CurrencyEdit,
      ecp: true,
      vipAddress: '/necp/mapp/metamodel'
    }
  ]
}
```

### 2. 枚举选择控件（enum-select）

**基本使用：**
```html
<template>
  <enum-select
    v-model="enumValue"
    :entity-type-id="enumTypeId"
    :multiple="false"
    :vip-address="vipAddress">
  </enum-select>
</template>

<script lang="ts">
import EnumSelect from 'jt.tswan.components/packages/enum-select/enum.yjpl';

export default {
  components: {
    'enum-select': EnumSelect
  },
  
  data() {
    return {
      enumValue: '',
      enumTypeId: 'STATUS_TYPE',
      vipAddress: '/necp/mapp/metamodel'
    };
  }
}
</script>
```

**主要参数：**
| 参数 | 说明 | 类型 | 默认值 |
|------|------|------|--------|
| entityTypeId | 枚举类型ID | String | - |
| enumType | 枚举类型ID | String | - |
| type | 枚举系统 | String | 'enum' |
| multiple | 是否支持多选 | boolean | false |
| collapseTags | 多选标签展示行为 | boolean | true |
| defaultId | 默认选中的id | String | '' |
| qzzSelect | 多选时是否需要qzz下拉 | boolean | true |

### 3. 管理对象选择控件（gldx-select）

**基本使用：**
```html
<template>
  <gldx-select
    v-model="gldxValue"
    :dxlx-id="dxlxId"
    :vip-address="vipAddress"
    :multiple="true"
    :is-show-ty-check-box="true">
  </gldx-select>
</template>

<script lang="ts">
import GldxSelect from 'jt.tswan.cp.dcc.biz.web/packages/bizcontrol/gldx-select/gldx.yjpl';

export default {
  components: {
    'gldx-select': GldxSelect
  },
  
  data() {
    return {
      gldxValue: [],
      dxlxId: '5001',
      vipAddress: '/necp/mapp/metamodel'
    };
  }
}
</script>
```

**在 YJPL 模板中使用：**
```javascript
// MetaModel.ts
query: [
  {
    name: 'gldx',
    dataType: 'gldx',
    label: '管理对象',
    value: [],
    dxlxId: '5001',
    multiple: true,
    vipAddress: '/necp/mapp/metamodel'
  }
]
```

**在 QZZ 表格中使用：**
```javascript
import gldxEdit from 'jt.tswan.cp.dcc.biz.web/packages/bizcontrol/gldx-select/gldx.grid.edit.js';

grid: {
  colModels: [
    {
      name: 'gldx',
      index: 'gldx',
      width: 150,
      dataType: 'gldx',
      editType: gldxEdit,
      dxlxId: '5001',
      ecp: true
    }
  ]
}
```

**主要参数：**
| 参数 | 说明 | 类型 | 默认值 |
|------|------|------|--------|
| dxlxId | 管理对象类型ID | String | - |
| multiple | 是否多选 | boolean | false |
| filterOption | 过滤条件配置 | Object | - |
| filterTy | 是否默认勾选显示停用 | boolean | false |
| isShowTyCheckBox | 是否显示停用复选框 | boolean | false |

### 4. 用户选择控件（user-select）

**基本使用：**
```html
<template>
  <user-select
    v-model="userId"
    :multiple="false"
    :option="userOption">
  </user-select>
</template>

<script lang="ts">
import UserSelect from 'jt.tswan.components/packages/user-select/user.yjpl';

export default {
  components: {
    'user-select': UserSelect
  },
  
  data() {
    return {
      userId: '',
      userOption: {
        // 用户选择配置
      }
    };
  }
}
</script>
```

### 5. 组织选择控件（org-select）

**基本使用：**
```html
<template>
  <org-select
    v-model="orgId"
    :multiple="false"
    :fin-org-option="finOrgOption">
  </org-select>
</template>

<script lang="ts">
import OrgSelect from 'jt.tswan.components/packages/org-select/org.yjpl';

export default {
  components: {
    'org-select': OrgSelect
  },
  
  data() {
    return {
      orgId: '',
      finOrgOption: {
        // 组织选择配置
      }
    };
  }
}
</script>
```

## 集成最佳实践

### 1. 统一管理中台组件

创建一个统一的中台组件注册文件：

```typescript
// src/middleware/index.ts
import CurrencySelect from 'jt.tswan.components/packages/currency-select/currency.yjpl';
import EnumSelect from 'jt.tswan.components/packages/enum-select/enum.yjpl';
import GldxSelect from 'jt.tswan.cp.dcc.biz.web/packages/bizcontrol/gldx-select/gldx.yjpl';
import UserSelect from 'jt.tswan.components/packages/user-select/user.yjpl';
import OrgSelect from 'jt.tswan.components/packages/org-select/org.yjpl';

export default {
  'currency-select': CurrencySelect,
  'enum-select': EnumSelect,
  'gldx-select': GldxSelect,
  'user-select': UserSelect,
  'org-select': OrgSelect
};
```

在页面中使用：
```typescript
import MiddlewareComponents from '@/middleware';

export default {
  components: {
    ...MiddlewareComponents
  }
}
```

### 2. 封装通用配置

```typescript
// src/config/middleware.ts
export const middlewareConfig = {
  vipAddress: '/necp/mapp/metamodel',
  ecp: true,
  
  // 管理对象类型映射
  gldxTypes: {
    customer: '5001',
    supplier: '5002',
    employee: '5003'
  },
  
  // 枚举类型映射
  enumTypes: {
    status: 'STATUS_TYPE',
    category: 'CATEGORY_TYPE'
  }
};
```

### 3. 统一事件处理

```typescript
// src/mixins/middleware.ts
export default {
  methods: {
    handleMiddlewareChange(componentName, value, data) {
      console.log(`${componentName} changed:`, value, data);
      // 统一的变更处理逻辑
    },
    
    handleMiddlewareError(componentName, error) {
      console.error(`${componentName} error:`, error);
      this.$message.error(`${componentName}加载失败`);
    }
  }
};
```

## 常见问题

### Q1: 中台组件加载失败？

**解决方案：**
1. 检查包是否正确安装
2. 检查 vipAddress 配置是否正确
3. 检查 ecp 参数是否正确设置
4. 检查网络连接和API服务

### Q2: 如何处理中台组件的权限控制？

```javascript
computed: {
  canSelectUser() {
    return this.$store.getters.hasPermission('user:select');
  }
}
```

```html
<user-select v-if="canSelectUser" v-model="userId"></user-select>
```

### Q3: 如何自定义中台组件的显示格式？

```javascript
// 使用 text 属性自定义显示
<currency-select
  v-model="currencyId"
  :text="(data) => `${data.code} - ${data.name}`">
</currency-select>
```

### Q4: 如何在表格中使用中台组件？

参考各组件的"在QZZ表格中使用"部分，需要：
1. 引入对应的 grid.edit.js 文件
2. 在 colModels 中配置 editType
3. 设置必要的参数（如 dxlxId、ecp 等）

## 下一步

- 查看 [Mock数据配置](./08-mock-data.md)
- 了解 [最佳实践](./09-best-practices.md)
- 查看 [实战示例](./10-examples.md)
