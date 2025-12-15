# YJPL前端代码生成智能体 - 架构和核心概念

## 架构概览

YJPL框架采用分层架构设计，将页面开发分为三个核心层次：

```
┌─────────────────────────────────────┐
│         index.yjpl (视图层)          │
│    - 模板继承                         │
│    - UI渲染                          │
│    - 事件绑定                         │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│       Business.ts (业务逻辑层)        │
│    - 数据获取                         │
│    - 业务处理                         │
│    - 状态管理                         │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│        Model.ts (数据模型层)          │
│    - metaModel (页面配置)            │
│    - dataModel (业务数据)            │
│    - stateModel (状态管理)           │
└─────────────────────────────────────┘
```

## 三层数据模型详解

### 1. metaModel（页面配置层）

**特点**：静态配置，定义页面结构

**包含内容**：
- 页面标题和基本信息
- 查询字段配置
- 表格列配置
- 按钮配置
- 树形结构配置
- 表单字段配置

**示例**：
```typescript
export const metaModel = {
  title: '用户管理',
  
  // 查询面板配置
  query: [
    {
      name: 'userName',
      label: '用户名',
      dataType: 'string',
      placeholder: '请输入用户名'
    },
    {
      name: 'status',
      label: '状态',
      dataType: 'combobox',
      options: [
        { label: '启用', value: 1 },
        { label: '禁用', value: 0 }
      ]
    }
  ],
  
  // 表格列配置
  grid: {
    colNames: ['ID', '用户名', '邮箱', '状态', '创建时间'],
    colModels: [
      { name: 'id', caption: 'ID', width: 80 },
      { name: 'userName', caption: '用户名', width: 150 },
      { name: 'email', caption: '邮箱', width: 200 },
      { name: 'status', caption: '状态', width: 100 },
      { name: 'createTime', caption: '创建时间', width: 150 }
    ],
    pageSize: 20,
    pager: true
  },
  
  // 按钮配置
  btns: [
    { name: 'add', text: '新增', type: 'primary', icon: 'yj-p-add' },
    { name: 'edit', text: '编辑', icon: 'yj-p-edit' },
    { name: 'delete', text: '删除', icon: 'yj-p-delete' }
  ]
};
```

### 2. dataModel（业务数据层）

**特点**：动态数据，随用户操作变化

**包含内容**：
- 查询条件数据
- 表格数据
- 树形数据
- 表单数据
- 分页信息

**示例**：
```typescript
export const dataModel = {
  // 查询条件
  query: {
    userName: '',
    status: null
  },
  
  // 表格数据
  grid: [
    { id: 1, userName: '张三', email: 'zhangsan@example.com', status: 1 },
    { id: 2, userName: '李四', email: 'lisi@example.com', status: 1 }
  ],
  
  // 树形数据
  tree: [],
  
  // 分页信息
  pageTotal: 0,
  pageSize: 20,
  pageIndex: 1
};
```

### 3. stateModel（状态管理层）

**特点**：控制组件状态

**状态枚举值**：
- `YJState.STATE.DEFAULT` - 默认状态（可用）
- `YJState.STATE.DISABLED` - 禁用状态
- `YJState.STATE.LOADING` - 加载状态
- `YJState.STATE.HIDDEN` - 隐藏状态

**示例**：
```typescript
import { YJState } from 'yjpl-core';

export const stateModel = {
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DISABLED,
  delete: YJState.STATE.DISABLED,
  query: YJState.STATE.DEFAULT,
  reset: YJState.STATE.DEFAULT
};
```

## Business类架构

### 基本结构

```typescript
import { YJBusiness } from 'yjpl-core';
import Model from './Model';

export default class YourPageBusiness extends YJBusiness {
  constructor() {
    super();
    // 从 Model.ts 中引入数据模型
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  // 获取初始查询数据
  async getQueryData() {
    return {};
  }

  // 获取树形数据
  async getTreeData() {
    return [];
  }

  // 获取表格数据
  async getGridData(pageSize?: number, pageIndex?: number) {
    // 调用后端 API
    return [];
  }

  // 获取总记录数
  async getTotalRecord() {
    return 0;
  }

  // 获取所有数据（供 index.yjpl 使用）
  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }
}
```

### 核心方法说明

| 方法 | 说明 | 返回值 |
|------|------|--------|
| `getQueryData()` | 获取查询面板初始数据 | Promise<Object> |
| `getTreeData()` | 获取树形结构数据 | Promise<Array> |
| `getGridData()` | 获取表格数据 | Promise<Array> |
| `getTotalRecord()` | 获取分页总数 | Promise<Number> |
| `getData()` | 获取所有模型数据 | Object |

## index.yjpl架构

### 基本结构

```xml
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
    <!-- 使用 plugins 进行模板扩展 -->
  </page>
</template>

<script lang="ts">
import ListTemplate from 'yjpl-template/business/list-template.yjpl';
import Business from './Business';

export default class MyPage extends ListTemplate {
  constructor() {
    super();
    this.bs = new Business();
  }

  data() {
    return {
      ...this.bs.getData(),
      eventsModel: {
        // 事件绑定配置
      }
    };
  }

  async created() {
    // 初始化数据
  }

  methods() {
    return {
      // 事件处理方法
    };
  }

  watch() {
    return {
      // 监听器配置
    };
  }
}
</script>

<style lang="scss" scoped>
/* 样式 */
</style>
```

### 生命周期

```
constructor() → data() → created() → mounted() → updated() → destroyed()
```

### 重要注意事项

**⚠️ 必须在 `<script>` 标签中添加 `lang="ts"` 属性**

当在 `.yjpl` 文件中使用 TypeScript 类型注解时，必须声明 `lang="ts"`：

```xml
<!-- ✅ 正确 -->
<script lang="ts">
export default class MyPage {
  handleSort(column: string, order: string) {
    // ...
  }
}
</script>

<!-- ❌ 错误 - 会导致编译错误 -->
<script>
export default class MyPage {
  handleSort(column: string, order: string) {  // 编译错误
    // ...
  }
}
</script>
```

## 事件处理架构

### eventsModel配置

```typescript
data() {
  return {
    ...this.bs.getData(),
    eventsModel: {
      // 按钮事件
      add: {
        click: this.handleAdd
      },
      edit: {
        click: this.handleEdit
      },
      delete: {
        click: this.handleDelete
      },
      
      // 表格事件
      grid: {
        'row-click': this.handleRowClick,
        'selection-change': this.handleSelectionChange
      },
      
      // 查询事件
      query: {
        click: this.handleQuery
      },
      reset: {
        click: this.handleReset
      }
    }
  };
}
```

### 事件处理方法

```typescript
methods() {
  return {
    handleAdd() {
      this.$message('新增功能');
    },
    
    handleEdit() {
      const selectedRows = this.$refs.grid.ui.getSelectedRowData();
      if (selectedRows.length === 0) {
        this.$message.warning('请选择要编辑的数据');
        return;
      }
      // 编辑逻辑
    },
    
    handleDelete() {
      const selectedRows = this.$refs.grid.ui.getSelectedRowData();
      if (selectedRows.length === 0) {
        this.$message.warning('请选择要删除的数据');
        return;
      }
      // 删除逻辑
    },
    
    handleQuery() {
      this.$refs.grid.ui.refresh();
    },
    
    handleReset() {
      this.dataModel.query = {};
    },
    
    handleRowClick(row) {
      console.log('点击行:', row);
    },
    
    handleSelectionChange(selection) {
      console.log('选中行:', selection);
    }
  };
}
```

## 数据流架构

```
用户操作
   ↓
事件触发 (eventsModel)
   ↓
方法调用 (methods)
   ↓
Business处理 (Business.ts)
   ↓
数据更新 (dataModel)
   ↓
视图更新 (index.yjpl)
```

## 模板继承架构

### 继承关系

```
index.yjpl
   ↓ extends
list-template.yjpl / ywdj/index.yjpl
   ↓ 包含
基础组件 (yjpl-ui)
```

### 扩展方式

使用 `<plugins>` 标签进行扩展：

```xml
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
    <!-- 替换工具栏 -->
    <plugins type="replace" target=".query-toolbar">
      <div slot="toolbar" class="query-toolbar">
        <!-- 自定义工具栏内容 -->
      </div>
    </plugins>
    
    <!-- 追加自定义组件 -->
    <plugins type="append" target=".query-body">
      <custom-component></custom-component>
    </plugins>
    
    <!-- 移除不需要的区域 -->
    <plugins type="remove" target=".query-search-panel"></plugins>
  </page>
</template>
```

## 组件通信架构

### 父子组件通信

```typescript
// 父组件传递数据
<child-component :data="parentData" @change="handleChange"></child-component>

// 子组件接收和触发
props: ['data'],
methods: {
  handleClick() {
    this.$emit('change', newValue);
  }
}
```

### 兄弟组件通信

```typescript
// 使用事件总线
import { EventBus } from 'yjpl-core';

// 组件A发送
EventBus.$emit('event-name', data);

// 组件B接收
EventBus.$on('event-name', (data) => {
  // 处理数据
});
```

## 性能优化架构

### 1. 懒加载

```typescript
// 组件懒加载
components: {
  'heavy-component': () => import('./HeavyComponent.yjpl')
}
```

### 2. 虚拟滚动

```typescript
grid: {
  virtual: true,
  virtualSize: 20
}
```

### 3. 防抖节流

```typescript
import { debounce, throttle } from 'yjpl-utils';

methods() {
  return {
    handleSearch: debounce(function() {
      // 搜索逻辑
    }, 300),
    
    handleScroll: throttle(function() {
      // 滚动逻辑
    }, 100)
  };
}
```

## 错误处理架构

### 全局错误处理

```typescript
// 在 Business.ts 中
async getGridData() {
  try {
    const response = await api.getList();
    return response.data;
  } catch (error) {
    this.$message.error('获取数据失败：' + error.message);
    return [];
  }
}
```

### 表单验证

```typescript
// 在 metaModel 中配置验证规则
query: [
  {
    name: 'email',
    label: '邮箱',
    dataType: 'string',
    required: true,
    validate: 'email'
  }
]
```

## 下一步

- 了解 [模板系统详解](./03-templates.md)
- 学习 [数据模型设计](./04-data-models.md)
- 查看 [组件库参考](./05-components.md)
