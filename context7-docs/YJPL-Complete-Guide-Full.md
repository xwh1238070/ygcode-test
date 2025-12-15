# YJPL前端代码生成智能体 - 概述

## 简介

YJPL前端代码生成智能体是一个功能强大的AI助手，专门用于从需求文档生成高质量的UI4.0页面代码。它基于YJPL框架，支持中台组件集成和Mock数据生成。

## 核心特性

### 1. UI4.0页面开发
- **index.yjpl** - 页面视图文件（模板继承）
- **Business.ts** - 业务逻辑文件
- **Model.ts** - 数据模型文件（包含metaModel、dataModel、stateModel）

### 2. 中台组件集成
- 待办组件（待办数量统计、待办列表）
- 用户中心组件（组织选择、用户选择）
- 主数据选择控件（币种、枚举、科目、计量单位、组织）
- 业务中台组件（yjpl-template、yjpl-ui、yjpl-qzz等）

### 3. Mock数据生成
- GET/POST请求拦截
- 动态数据生成
- 延迟响应模拟
- 错误场景模拟
- 参数化数据生成

## 技术栈

- **YJPL框架** 8.5.0+
- **Vue2**
- **TypeScript**
- **yjpl-ui组件库**（68个组件）
- **中台组件库**
- **Mock数据支持**

## 快速开始

### 基本工作流程

```
1. 分析需求 → 2. 选择模板 → 3. 生成代码 → 4. 测试验证
```

### 模板类型

#### list-template（列表查询模板）
适用于：
- 数据列表展示
- 查询筛选功能
- 多视图切换（表格、列表、卡片）
- 树形导航

#### ywdj（业务单据模板）
适用于：
- 业务单据详情
- 表单+子表组合
- 审批流程展示
- 附件管理

## 文件结构

```
your-page/
├── index.yjpl              # 主模板文件（extends 模板）
├── Business.ts             # 业务逻辑类
├── Model.ts                # 数据模型（包含 metaModel、dataModel、stateModel）
└── components/             # 子组件（可选）
    ├── component1.yjpl
    └── component2.yjpl
```

## 核心概念

### 三层数据模型

1. **metaModel（页面配置）- 静态**
   - 页面结构、字段定义、按钮配置等
   - 不会在运行时改变

2. **dataModel（业务数据）- 动态**
   - 实际的业务数据、查询条件、表格数据等
   - 会根据用户操作改变

3. **stateModel（状态管理）- 控制**
   - 组件状态（禁用、加载、隐藏等）
   - 使用 YJState.STATE 枚举值

### 模板扩展系统（plugins）

使用 `<plugins>` 标签进行模板扩展，支持四种操作：

- **replace** - 完全替换目标内容
- **append** - 在目标后追加内容
- **insert** - 在目标前插入内容
- **remove** - 移除目标元素

## 版本信息

- **智能体版本**：3.0.0
- **支持的YJPL版本**：8.5.0+
- **支持的UI4.0版本**：9.5.0+
- **最后更新**：2025-12-04

## 下一步

- 查看 [架构和核心概念](./02-architecture.md)
- 了解 [模板系统详解](./03-templates.md)
- 学习 [数据模型设计](./04-data-models.md)
- 浏览 [组件库参考](./05-components.md)
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
# YJPL前端代码生成智能体 - 模板系统详解

## 模板选择策略

### 决策流程图

```
分析页面需求
    ↓
是否有查询+列表展示？
    ↓ 是
使用 list-template
    ↓ 否
是否有表单+子表+审批？
    ↓ 是
使用 ywdj 模板
    ↓ 否
自定义开发
```

## list-template（列表查询模板）

### 适用场景

**✓ 适合使用的场景：**
- 需要展示数据列表的页面
- 需要查询/筛选功能的页面
- 需要多视图切换的页面（表格、列表、卡片、图表）
- 需要树形导航的页面
- 数据管理类页面

**特征识别清单：**
```
✓ 有查询面板
✓ 有表格/列表展示
✓ 有工具栏按钮
✓ 可能有树形导航
✓ 支持多种视图
✓ 需要分页功能
```

### 模板结构

```xml
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
    <!-- 可扩展区域 -->
  </page>
</template>
```

### 核心区域

| 区域选择器 | 说明 | 用途 |
|-----------|------|------|
| `.query-header` | 头部区域 | 页面标题、面包屑 |
| `.query-search-panel` | 查询面板 | 查询条件输入 |
| `.query-toolbar` | 工具栏 | 功能按钮 |
| `.query-grid-body` | 表格区域 | 数据展示 |
| `.query-body` | 主体区域 | 整体内容区 |
| `.query-tree` | 树形导航 | 左侧树形结构 |

### 完整示例

```xml
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
  </page>
</template>

<script lang="ts">
import ListTemplate from 'yjpl-template/business/list-template.yjpl';
import Business from './Business';

export default class UserListPage extends ListTemplate {
  constructor() {
    super();
    this.bs = new Business();
  }

  data() {
    return {
      ...this.bs.getData(),
      eventsModel: {
        add: { click: this.handleAdd },
        edit: { click: this.handleEdit },
        delete: { click: this.handleDelete },
        query: { click: this.handleQuery },
        reset: { click: this.handleReset }
      }
    };
  }

  async created() {
    // 初始化查询数据
    this.dataModel.query = await this.bs.getQueryData();
    
    // 加载树形数据（如果需要）
    if (this.metaModel.showTree) {
      this.dataModel.tree = await this.bs.getTreeData();
    }
    
    // 加载表格数据
    this.dataModel.grid = await this.bs.getGridData();
    this.dataModel.pageTotal = await this.bs.getTotalRecord();
  }

  mounted() {
    // 设置分页总数
    if (this.$refs.grid && this.$refs.grid.ui) {
      this.$refs.grid.ui.setTotalRecord(this.dataModel.pageTotal);
    }
  }

  methods() {
    return {
      async handleQuery() {
        this.dataModel.grid = await this.bs.getGridData();
        this.$refs.grid.ui.refresh();
      },
      
      handleReset() {
        this.dataModel.query = {};
      },
      
      handleAdd() {
        // 新增逻辑
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
        this.$confirm('确定要删除选中的数据吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          // 删除逻辑
          this.$message.success('删除成功');
          this.handleQuery();
        });
      }
    };
  }

  watch() {
    return {
      'dataModel.query': {
        handler() {
          // 查询条件变化时自动查询
          this.handleQuery();
        },
        deep: true
      }
    };
  }
}
</script>

<style lang="scss" scoped>
// 自定义样式
</style>
```

### 配置选项

```typescript
// Model.ts
export const metaModel = {
  title: '用户列表',
  
  // 是否显示树形导航
  showTree: true,
  
  // 树形配置
  tree: {
    nodeKey: 'id',
    label: 'name',
    children: 'children',
    defaultExpandAll: true
  },
  
  // 查询面板配置
  query: [
    {
      name: 'userName',
      label: '用户名',
      dataType: 'string',
      placeholder: '请输入用户名'
    }
  ],
  
  // 表格配置
  grid: {
    // 默认视图
    defaultShow: 'grid',
    
    // 显示的视图类型
    showGrid: true,    // 表格视图
    showList: true,    // 列表视图
    showCard: true,    // 卡片视图
    showChart: false,  // 图表视图
    
    // 列配置
    colNames: ['ID', '用户名', '邮箱'],
    colModels: [
      { name: 'id', caption: 'ID', width: 80 },
      { name: 'userName', caption: '用户名', width: 150 },
      { name: 'email', caption: '邮箱', width: 200 }
    ],
    
    // 分页配置
    pageSize: 20,
    pager: true,
    
    // 卡片视图配置
    cardOption: {
      formatOption: {
        title: 'userName',
        subTitle: 'email',
        description: 'createTime'
      }
    }
  },
  
  // 按钮配置
  btns: [
    { name: 'add', text: '新增', type: 'primary', icon: 'yj-p-add' },
    { name: 'edit', text: '编辑', icon: 'yj-p-edit' },
    { name: 'delete', text: '删除', icon: 'yj-p-delete' }
  ]
};
```

## ywdj（业务单据模板）

### 适用场景

**✓ 适合使用的场景：**
- 业务单据详情页面
- 需要表单 + 表格组合的页面
- 需要审批流程展示的页面
- 需要附件管理的页面
- 单据编辑类页面

**特征识别清单：**
```
✓ 有表单区域
✓ 有子表格
✓ 有审批/流程信息
✓ 有附件区域
✓ 有侧边栏
✓ 有锚点导航
```

### 模板结构

```xml
<template>
  <page extends="yjpl-template/page/ywdj/index.yjpl">
    <!-- 可扩展区域 -->
  </page>
</template>
```

### 核心区域

| 区域选择器 | 说明 | 用途 |
|-----------|------|------|
| `.bill-title` | 标题区域 | 单据标题、编号 |
| `.bill-form` | 表单区域 | 基础信息表单 |
| `.bill-info` | 详情区域 | 子表、详细信息 |
| `.bill-upload` | 附件区域 | 附件上传管理 |
| `.bill-workflow` | 流程区域 | 审批流程展示 |
| `.bill-footer` | 底部工具栏 | 保存、提交等按钮 |
| `.bill-aside` | 侧边栏 | 快捷操作、相关信息 |
| `.bill-leader` | 审批人区域 | 审批人信息 |
| `.bill-user` | 制单人区域 | 制单人信息 |

### 完整示例

```xml
<template>
  <page extends="yjpl-template/page/ywdj/index.yjpl">
    <!-- 自定义侧边栏 -->
    <plugins type="replace" target=".bill-aside">
      <div class="custom-aside">
        <h3>快捷操作</h3>
        <button @click="handleQuickAction">快捷操作</button>
      </div>
    </plugins>
  </page>
</template>

<script lang="ts">
import YwdjTemplate from 'yjpl-template/page/ywdj/index.yjpl';
import Business from './Business';

export default class OrderDetailPage extends YwdjTemplate {
  constructor() {
    super();
    this.bs = new Business();
  }

  data() {
    return {
      ...this.bs.getData(),
      eventsModel: {
        save: { click: this.handleSave },
        submit: { click: this.handleSubmit },
        cancel: { click: this.handleCancel }
      }
    };
  }

  async created() {
    // 获取单据ID
    const billId = this.$route.query.id;
    
    if (billId) {
      // 编辑模式：加载单据数据
      const billData = await this.bs.getBillData(billId);
      this.dataModel.form = billData.form;
      this.dataModel.grid = billData.grid;
      this.dataModel.workflow = billData.workflow;
    } else {
      // 新增模式：初始化空数据
      this.dataModel.form = await this.bs.getInitFormData();
    }
  }

  methods() {
    return {
      async handleSave() {
        // 验证表单
        if (!this.validateForm()) {
          return;
        }
        
        try {
          await this.bs.saveBill(this.dataModel);
          this.$message.success('保存成功');
        } catch (error) {
          this.$message.error('保存失败：' + error.message);
        }
      },
      
      async handleSubmit() {
        // 验证表单
        if (!this.validateForm()) {
          return;
        }
        
        this.$confirm('确定要提交吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          try {
            await this.bs.submitBill(this.dataModel);
            this.$message.success('提交成功');
            this.$router.back();
          } catch (error) {
            this.$message.error('提交失败：' + error.message);
          }
        });
      },
      
      handleCancel() {
        this.$router.back();
      },
      
      validateForm() {
        // 表单验证逻辑
        if (!this.dataModel.form.orderNo) {
          this.$message.warning('请输入订单号');
          return false;
        }
        return true;
      },
      
      handleQuickAction() {
        // 快捷操作逻辑
      }
    };
  }
}
</script>

<style lang="scss" scoped>
.custom-aside {
  padding: 20px;
  
  h3 {
    margin-bottom: 10px;
  }
}
</style>
```

### 配置选项

```typescript
// Model.ts
export const metaModel = {
  title: '订单详情',
  code: 'ORD-2024-001',
  date: '2024-12-04',
  
  // 表单列数
  cols: 4,
  
  // 表单字段配置
  form: [
    {
      name: 'orderNo',
      label: '订单号',
      dataType: 'string',
      required: true,
      span: 2
    },
    {
      name: 'customerName',
      label: '客户名称',
      dataType: 'string',
      required: true,
      span: 2
    },
    {
      name: 'orderDate',
      label: '订单日期',
      dataType: 'date',
      required: true
    },
    {
      name: 'amount',
      label: '订单金额',
      dataType: 'number',
      scale: 2
    }
  ],
  
  // 子表配置（支持多个子表）
  grid: [
    {
      title: '订单明细',
      columns: [
        { key: 'productName', label: '产品名称', width: 150 },
        { key: 'quantity', label: '数量', width: 80 },
        { key: 'price', label: '单价', width: 100 },
        { key: 'amount', label: '金额', width: 100 }
      ]
    }
  ],
  
  // 工具栏按钮
  toolbar: [
    { label: '保存', type: 'primary', name: 'save' },
    { label: '提交', type: 'primary', name: 'submit' },
    { label: '取消', name: 'cancel' }
  ],
  
  // 锚点导航
  anchor: [
    { title: '基础信息', id: 'form' },
    { title: '订单明细', id: 'info' },
    { title: '附件文档', id: 'upload' },
    { title: '审批流程', id: 'workflow' }
  ]
};
```

## 模板扩展技巧

### plugins 系统

#### 1. replace（替换）

完全替换目标区域的内容：

```xml
<plugins type="replace" target=".query-toolbar">
  <div slot="toolbar" class="query-toolbar">
    <button v-for="item of metaModel.functionButton" 
            :key="item.id"
            @click="handleClick(item)">
      {{item.title}}
    </button>
  </div>
</plugins>
```

#### 2. append（追加）

在目标区域后追加内容：

```xml
<plugins type="append" target=".query-body">
  <div class="custom-section">
    <h3>自定义区域</h3>
    <custom-component></custom-component>
  </div>
</plugins>
```

#### 3. insert（插入）

在目标区域前插入内容：

```xml
<plugins type="insert" target=".query-grid">
  <div class="tips">
    <i class="el-icon-info"></i>
    <span>提示信息</span>
  </div>
</plugins>
```

#### 4. remove（移除）

移除目标区域：

```xml
<plugins type="remove" target=".query-search-panel"></plugins>
```

### 常用扩展场景

#### 场景1：自定义工具栏

```xml
<plugins type="replace" target=".query-toolbar">
  <div slot="toolbar" class="query-toolbar">
    <div class="left-buttons">
      <button type="primary" @click="handleAdd">新增</button>
      <button @click="handleEdit">编辑</button>
      <button @click="handleDelete">删除</button>
    </div>
    <div class="right-buttons">
      <button @click="handleExport">导出</button>
      <button @click="handleImport">导入</button>
    </div>
  </div>
</plugins>
```

#### 场景2：添加统计信息

```xml
<plugins type="append" target=".query-toolbar">
  <div class="statistics">
    <span>总计：{{dataModel.pageTotal}} 条</span>
    <span>已选：{{selectedCount}} 条</span>
  </div>
</plugins>
```

#### 场景3：自定义查询面板

```xml
<plugins type="replace" target=".query-search-panel">
  <div class="custom-search-panel">
    <yj-container v-model="dataModel.query" :data="metaModel.query">
      <template #suffix>
        <button type="primary" @click="handleQuery">查询</button>
        <button @click="handleReset">重置</button>
        <button @click="handleAdvancedSearch">高级查询</button>
      </template>
    </yj-container>
  </div>
</plugins>
```

#### 场景4：添加侧边栏

```xml
<plugins type="replace" target=".bill-aside">
  <div class="custom-aside">
    <section class="aside-section">
      <h3>相关单据</h3>
      <ul>
        <li v-for="item in relatedBills" :key="item.id">
          <a @click="viewBill(item)">{{item.title}}</a>
        </li>
      </ul>
    </section>
    
    <section class="aside-section">
      <h3>操作历史</h3>
      <timeline :data="operationHistory"></timeline>
    </section>
  </div>
</plugins>
```

#### 场景5：自定义表格操作列

```xml
<plugins type="append" target=".query-grid-body">
  <template #operation="{ row }">
    <button size="small" @click="handleView(row)">查看</button>
    <button size="small" @click="handleEdit(row)">编辑</button>
    <button size="small" type="danger" @click="handleDelete(row)">删除</button>
  </template>
</plugins>
```

## 模板选择决策表

| 页面特征 | list-template | ywdj | 自定义 |
|---------|--------------|------|--------|
| 数据列表展示 | ✓ | ✗ | ✓ |
| 查询筛选 | ✓ | ✗ | ✓ |
| 多视图切换 | ✓ | ✗ | ✓ |
| 树形导航 | ✓ | ✗ | ✓ |
| 表单编辑 | ✗ | ✓ | ✓ |
| 子表管理 | ✗ | ✓ | ✓ |
| 审批流程 | ✗ | ✓ | ✓ |
| 附件管理 | ✗ | ✓ | ✓ |
| 完全自定义布局 | ✗ | ✗ | ✓ |

## 最佳实践

### ✓ 推荐做法

1. **优先使用模板**
   - 能用模板就不要自定义
   - 使用 plugins 进行扩展
   - 保持代码的可维护性

2. **合理使用 plugins**
   - 只扩展必要的区域
   - 避免过度自定义
   - 保持模板的优势

3. **遵循命名规范**
   - 使用语义化的类名
   - 遵循 BEM 命名规范
   - 保持代码的可读性

### ✗ 避免做法

1. **不要直接修改模板文件**
   ```xml
   <!-- ✗ 错误：直接修改模板 -->
   <!-- 修改 list-template.yjpl 文件 -->
   
   <!-- ✓ 正确：使用 plugins 扩展 -->
   <plugins type="replace" target=".query-toolbar">
     <!-- 自定义内容 -->
   </plugins>
   ```

2. **不要过度自定义**
   ```xml
   <!-- ✗ 错误：替换所有区域 -->
   <plugins type="replace" target=".query-header"></plugins>
   <plugins type="replace" target=".query-search-panel"></plugins>
   <plugins type="replace" target=".query-toolbar"></plugins>
   <plugins type="replace" target=".query-grid-body"></plugins>
   
   <!-- ✓ 正确：只扩展必要的区域 -->
   <plugins type="replace" target=".query-toolbar">
     <!-- 只自定义工具栏 -->
   </plugins>
   ```

3. **不要忽视模板提供的功能**
   ```typescript
   // ✗ 错误：重新实现分页
   methods() {
     return {
       handlePageChange(page) {
         // 自己实现分页逻辑
       }
     };
   }
   
   // ✓ 正确：使用模板的分页功能
   grid: {
     pageSize: 20,
     pager: true
   }
   ```

## 下一步

- 学习 [数据模型设计](./04-data-models.md)
- 查看 [组件库参考](./05-components.md)
- 了解 [QZZ表格组件](./06-qzz-grid.md)
# YJPL前端代码生成智能体 - 数据模型设计

## 数据模型概述

YJPL框架采用三层数据模型设计，每层都有明确的职责和用途：

```
metaModel (配置层) → 定义页面结构
dataModel (数据层) → 存储业务数据
stateModel (状态层) → 控制组件状态
```

## metaModel（页面配置）

### 基本结构

```typescript
export const metaModel = {
  // 页面基本信息
  title: string,
  code?: string,
  date?: string,
  
  // 查询面板配置
  query?: Array<QueryField>,
  
  // 表格配置
  grid?: GridConfig,
  
  // 按钮配置
  btns?: Array<ButtonConfig>,
  
  // 树形配置
  tree?: TreeConfig,
  
  // 表单配置
  form?: Array<FormField>
};
```

### 查询字段配置

#### 基本字段类型

```typescript
// 文本输入
{
  name: 'userName',
  label: '用户名',
  dataType: 'string',
  placeholder: '请输入用户名',
  required: false,
  disabled: false,
  visible: true,
  defaultValue: ''
}

// 数字输入
{
  name: 'age',
  label: '年龄',
  dataType: 'number',
  min: 0,
  max: 150,
  step: 1,
  precision: 0
}

// 日期选择
{
  name: 'createDate',
  label: '创建日期',
  dataType: 'date',
  format: 'yyyy-MM-dd',
  valueFormat: 'yyyy-MM-dd',
  placeholder: '请选择日期'
}

// 日期时间选择
{
  name: 'createTime',
  label: '创建时间',
  dataType: 'datetime',
  format: 'yyyy-MM-dd HH:mm:ss',
  valueFormat: 'yyyy-MM-dd HH:mm:ss'
}

// 下拉选择
{
  name: 'status',
  label: '状态',
  dataType: 'combobox',
  options: [
    { label: '启用', value: 1 },
    { label: '禁用', value: 0 }
  ],
  clearable: true,
  filterable: true
}

// 多选下拉
{
  name: 'roles',
  label: '角色',
  dataType: 'combobox',
  multiple: true,
  options: [
    { label: '管理员', value: 'admin' },
    { label: '用户', value: 'user' }
  ]
}

// 级联选择
{
  name: 'region',
  label: '地区',
  dataType: 'cascader',
  options: [
    {
      label: '浙江',
      value: 'zhejiang',
      children: [
        { label: '杭州', value: 'hangzhou' },
        { label: '宁波', value: 'ningbo' }
      ]
    }
  ]
}

// 开关
{
  name: 'enabled',
  label: '启用',
  dataType: 'switch',
  activeValue: 1,
  inactiveValue: 0
}

// 多行文本
{
  name: 'description',
  label: '描述',
  dataType: 'textarea',
  rows: 4,
  maxlength: 500
}
```

#### 字段属性完整列表

| 属性 | 类型 | 说明 | 默认值 |
|------|------|------|--------|
| name | string | 字段名（必填） | - |
| label | string | 显示标签 | - |
| dataType | string | 数据类型 | 'string' |
| placeholder | string | 占位符 | - |
| required | boolean | 是否必填 | false |
| disabled | boolean | 是否禁用 | false |
| visible | boolean | 是否可见 | true |
| defaultValue | any | 默认值 | - |
| options | Array | 选项列表 | [] |
| clearable | boolean | 是否可清空 | true |
| filterable | boolean | 是否可搜索 | false |
| multiple | boolean | 是否多选 | false |
| format | string | 日期格式 | - |
| valueFormat | string | 值格式 | - |
| min | number | 最小值 | - |
| max | number | 最大值 | - |
| step | number | 步长 | 1 |
| precision | number | 精度 | - |
| rows | number | 文本行数 | 2 |
| maxlength | number | 最大长度 | - |
| span | number | 栅格占位 | 1 |

### 表格配置

#### 基本配置

```typescript
grid: {
  // 列标题
  colNames: ['ID', '用户名', '邮箱', '状态', '创建时间'],
  
  // 列配置
  colModels: [
    {
      name: 'id',
      caption: 'ID',
      width: 80,
      align: 'center',
      sortable: true
    },
    {
      name: 'userName',
      caption: '用户名',
      width: 150,
      sortable: true
    },
    {
      name: 'email',
      caption: '邮箱',
      width: 200
    },
    {
      name: 'status',
      caption: '状态',
      width: 100,
      formatType: 'enum',
      enumData: {
        1: '启用',
        0: '禁用'
      }
    },
    {
      name: 'createTime',
      caption: '创建时间',
      width: 150,
      formatType: 'date',
      format: 'yyyy-MM-dd HH:mm:ss'
    }
  ],
  
  // 分页配置
  pageSize: 20,
  pageSizes: [10, 20, 50, 100],
  pager: true,
  
  // 其他配置
  showCheckbox: true,
  showIndex: true,
  height: 'auto',
  striped: true,
  border: true
}
```

#### 列属性完整列表

| 属性 | 类型 | 说明 | 默认值 |
|------|------|------|--------|
| name | string | 字段名 | - |
| caption | string | 列标题 | - |
| width | number | 列宽 | - |
| align | string | 对齐方式 | 'left' |
| sortable | boolean | 是否可排序 | false |
| filterable | boolean | 是否可筛选 | false |
| formatType | string | 格式化类型 | - |
| format | string | 格式化字符串 | - |
| hidden | boolean | 是否隐藏 | false |
| frozen | boolean | 是否冻结 | false |
| editable | boolean | 是否可编辑 | false |
| required | boolean | 是否必填 | false |
| scale | number | 小数位数 | - |
| sum | boolean | 是否合计 | false |

#### 格式化类型

```typescript
// 金额格式化
{
  name: 'amount',
  caption: '金额',
  formatType: 'money',
  scale: 2
}

// 百分比格式化
{
  name: 'rate',
  caption: '比率',
  formatType: 'percent',
  scale: 2
}

// 日期格式化
{
  name: 'date',
  caption: '日期',
  formatType: 'date',
  format: 'yyyy-MM-dd'
}

// 枚举格式化
{
  name: 'status',
  caption: '状态',
  formatType: 'enum',
  enumData: {
    1: '启用',
    0: '禁用'
  }
}
```

#### 多视图配置

```typescript
grid: {
  // 默认视图
  defaultShow: 'grid',
  
  // 显示的视图类型
  showGrid: true,    // 表格视图
  showList: true,    // 列表视图
  showCard: true,    // 卡片视图
  showChart: false,  // 图表视图
  
  // 卡片视图配置
  cardOption: {
    formatOption: {
      title: 'userName',        // 标题字段
      subTitle: 'email',        // 副标题字段
      money: 'amount',          // 金额字段
      description: 'desc',      // 描述字段
      image: 'avatar'           // 图片字段
    }
  },
  
  // 列表视图配置
  listOption: {
    formatOption: {
      title: 'userName',
      subTitle: 'email',
      description: 'desc'
    }
  }
}
```

### 按钮配置

```typescript
btns: [
  {
    name: 'add',
    text: '新增',
    type: 'primary',
    icon: 'yj-p-add',
    disabled: false,
    visible: true,
    action: 'add'
  },
  {
    name: 'edit',
    text: '编辑',
    icon: 'yj-p-edit',
    disabled: false,
    visible: true,
    action: 'edit'
  },
  {
    name: 'delete',
    text: '删除',
    icon: 'yj-p-delete',
    type: 'danger',
    disabled: false,
    visible: true,
    action: 'delete'
  },
  {
    name: 'export',
    text: '导出',
    icon: 'yj-p-export',
    action: 'export'
  },
  {
    name: 'import',
    text: '导入',
    icon: 'yj-p-import',
    action: 'import'
  }
]
```

#### 按钮类型

| type | 说明 | 样式 |
|------|------|------|
| primary | 主要按钮 | 蓝色 |
| success | 成功按钮 | 绿色 |
| warning | 警告按钮 | 橙色 |
| danger | 危险按钮 | 红色 |
| info | 信息按钮 | 灰色 |
| text | 文本按钮 | 无背景 |

#### 常用图标

| 图标 | 说明 |
|------|------|
| yj-p-add | 新增 |
| yj-p-edit | 编辑 |
| yj-p-delete | 删除 |
| yj-p-save | 保存 |
| yj-p-print | 打印 |
| yj-p-export | 导出 |
| yj-p-import | 导入 |
| yj-p-search | 搜索 |
| yj-p-refresh | 刷新 |
| yj-p-ai | AI |

### 树形配置

```typescript
tree: {
  nodeKey: 'id',
  label: 'name',
  children: 'children',
  defaultExpandAll: true,
  expandOnClickNode: false,
  checkOnClickNode: false,
  showCheckbox: false,
  accordion: false,
  indent: 16,
  iconClass: 'el-icon-folder'
}
```

### 表单配置

```typescript
form: [
  {
    name: 'orderNo',
    label: '订单号',
    dataType: 'string',
    required: true,
    span: 2,
    disabled: false
  },
  {
    name: 'customerName',
    label: '客户名称',
    dataType: 'string',
    required: true,
    span: 2
  },
  {
    name: 'orderDate',
    label: '订单日期',
    dataType: 'date',
    required: true,
    span: 1
  },
  {
    name: 'amount',
    label: '订单金额',
    dataType: 'number',
    scale: 2,
    span: 1
  },
  {
    name: 'description',
    label: '备注',
    dataType: 'textarea',
    span: 4,
    rows: 4
  }
]
```

## dataModel（业务数据）

### 基本结构

```typescript
export const dataModel = {
  // 查询条件
  query: {},
  
  // 表格数据
  grid: [],
  
  // 树形数据
  tree: [],
  
  // 表单数据
  form: {},
  
  // 分页信息
  pageTotal: 0,
  pageSize: 20,
  pageIndex: 1,
  
  // 选中数据
  selectedRows: [],
  
  // 其他业务数据
  ...
};
```

### 查询数据

```typescript
// 简单查询
query: {
  userName: '',
  status: null,
  startDate: '',
  endDate: ''
}

// 复杂查询
query: {
  // 基本条件
  userName: '',
  status: null,
  
  // 日期范围
  dateRange: ['2024-01-01', '2024-12-31'],
  
  // 多选条件
  roles: [],
  
  // 级联选择
  region: [],
  
  // 自定义条件
  customCondition: {
    field: 'amount',
    operator: 'gt',
    value: 1000
  }
}
```

### 表格数据

```typescript
// 简单列表
grid: [
  { id: 1, userName: '张三', email: 'zhangsan@example.com', status: 1 },
  { id: 2, userName: '李四', email: 'lisi@example.com', status: 1 }
]

// 树形表格
grid: [
  {
    id: 1,
    name: '部门1',
    children: [
      { id: 11, name: '子部门1-1' },
      { id: 12, name: '子部门1-2' }
    ]
  },
  {
    id: 2,
    name: '部门2',
    children: [
      { id: 21, name: '子部门2-1' }
    ]
  }
]
```

### 树形数据

```typescript
tree: [
  {
    id: 1,
    name: '节点1',
    children: [
      {
        id: 11,
        name: '子节点1-1',
        children: [
          { id: 111, name: '子节点1-1-1' }
        ]
      },
      { id: 12, name: '子节点1-2' }
    ]
  },
  {
    id: 2,
    name: '节点2',
    children: [
      { id: 21, name: '子节点2-1' }
    ]
  }
]
```

### 表单数据

```typescript
// 简单表单
form: {
  orderNo: 'ORD-2024-001',
  customerName: '客户A',
  orderDate: '2024-12-04',
  amount: 10000,
  description: '订单备注'
}

// 复杂表单（包含子表）
form: {
  // 主表数据
  orderNo: 'ORD-2024-001',
  customerName: '客户A',
  orderDate: '2024-12-04',
  
  // 子表数据
  details: [
    { productName: '产品A', quantity: 10, price: 100, amount: 1000 },
    { productName: '产品B', quantity: 20, price: 200, amount: 4000 }
  ],
  
  // 附件数据
  attachments: [
    { name: '附件1.pdf', url: 'http://example.com/file1.pdf' }
  ]
}
```

## stateModel（状态管理）

### 基本结构

```typescript
import { YJState } from 'yjpl-core';

export const stateModel = {
  // 按钮状态
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DISABLED,
  delete: YJState.STATE.DISABLED,
  save: YJState.STATE.DEFAULT,
  submit: YJState.STATE.DEFAULT,
  
  // 字段状态
  orderNo: YJState.STATE.DEFAULT,
  customerName: YJState.STATE.DEFAULT,
  
  // 区域状态
  queryPanel: YJState.STATE.DEFAULT,
  gridArea: YJState.STATE.DEFAULT
};
```

### 状态枚举值

| 状态 | 值 | 说明 |
|------|---|------|
| DEFAULT | 0 | 默认状态（可用） |
| DISABLED | 1 | 禁用状态 |
| LOADING | 2 | 加载状态 |
| HIDDEN | 3 | 隐藏状态 |

### 状态控制示例

```typescript
// 根据选中行控制按钮状态
methods() {
  return {
    handleSelectionChange(selection) {
      if (selection.length === 0) {
        this.stateModel.edit = YJState.STATE.DISABLED;
        this.stateModel.delete = YJState.STATE.DISABLED;
      } else if (selection.length === 1) {
        this.stateModel.edit = YJState.STATE.DEFAULT;
        this.stateModel.delete = YJState.STATE.DEFAULT;
      } else {
        this.stateModel.edit = YJState.STATE.DISABLED;
        this.stateModel.delete = YJState.STATE.DEFAULT;
      }
    }
  };
}

// 根据表单状态控制字段
methods() {
  return {
    handleModeChange(mode) {
      if (mode === 'view') {
        // 查看模式：所有字段禁用
        Object.keys(this.stateModel).forEach(key => {
          if (key.startsWith('field_')) {
            this.stateModel[key] = YJState.STATE.DISABLED;
          }
        });
      } else if (mode === 'edit') {
        // 编辑模式：部分字段可编辑
        this.stateModel.field_orderNo = YJState.STATE.DISABLED;
        this.stateModel.field_customerName = YJState.STATE.DEFAULT;
      }
    }
  };
}
```

## 数据模型最佳实践

### ✓ 推荐做法

1. **分离配置和数据**
   ```typescript
   // ✓ 正确：分离配置和数据
   export const metaModel = { /* 配置 */ };
   export const dataModel = { /* 数据 */ };
   export const stateModel = { /* 状态 */ };
   ```

2. **使用类型定义**
   ```typescript
   // ✓ 正确：使用 TypeScript 类型
   interface QueryData {
     userName: string;
     status: number | null;
   }
   
   export const dataModel = {
     query: {} as QueryData
   };
   ```

3. **合理的默认值**
   ```typescript
   // ✓ 正确：提供合理的默认值
   export const dataModel = {
     query: {
       userName: '',
       status: null,
       pageSize: 20,
       pageIndex: 1
     }
   };
   ```

### ✗ 避免做法

1. **不要混合配置和数据**
   ```typescript
   // ✗ 错误：混合配置和数据
   export const model = {
     title: '用户列表',  // 配置
     users: [],          // 数据
     addDisabled: true   // 状态
   };
   ```

2. **不要在 metaModel 中存储动态数据**
   ```typescript
   // ✗ 错误：在配置中存储数据
   export const metaModel = {
     query: [
       {
         name: 'userName',
         value: '张三'  // 错误：这应该在 dataModel 中
       }
     ]
   };
   ```

3. **不要直接修改 metaModel**
   ```typescript
   // ✗ 错误：运行时修改配置
   this.metaModel.query.push({ /* ... */ });
   
   // ✓ 正确：配置应该是静态的
   // 如果需要动态字段，应该在 dataModel 中处理
   ```

## 下一步

- 查看 [组件库参考](./05-components.md)
- 了解 [QZZ表格组件](./06-qzz-grid.md)
- 学习 [中台组件集成](./07-middleware.md)
# YJPL前端代码生成智能体 - 组件库完整参考

## 组件库概述

YJPL UI组件库包含68个组件，涵盖基础组件、表单组件、数据展示、业务组件、容器组件、反馈组件、导航组件等多个类别。

## 组件分类索引

### 基础组件 (6个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Button | 按钮组件 | 触发操作、提交表单 |
| Icon | 图标组件 | 显示图标、装饰 |
| Link | 链接组件 | 页面跳转、外链 |
| Layout | 布局组件 | 页面布局、栅格系统 |
| Container | 容器组件 | 内容容器、区域划分 |
| Transition | 过渡动画组件 | 动画效果、过渡 |

### 表单组件 (16个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Input | 输入框 | 文本输入 |
| InputNumber | 数字输入框 | 数字输入、计数器 |
| Select | 选择器 | 下拉选择 |
| Cascader | 级联选择器 | 多级联动选择 |
| DatePicker | 日期选择器 | 日期选择 |
| TimePicker | 时间选择器 | 时间选择 |
| Checkbox | 复选框 | 多选 |
| Radio | 单选框 | 单选 |
| Switch | 开关 | 开关切换 |
| Slider | 滑块 | 数值范围选择 |
| ColorPicker | 颜色选择器 | 颜色选择 |
| Upload | 文件上传 | 文件上传 |
| Form | 表单容器 | 表单布局、验证 |
| EntitySelect | 实体选择器 | 实体数据选择 |
| RangeNumber | 范围数字输入 | 数值范围输入 |
| YjCondition | 条件组件 | 条件筛选 |

### 数据展示 (8个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Table | 数据表格 | 数据列表展示 |
| Tree | 树形组件 | 树形结构展示 |
| Pagination | 分页器 | 分页导航 |
| Avatar | 头像组件 | 用户头像 |
| Tag | 标签组件 | 标签展示 |
| Badge | 徽章组件 | 消息提示、状态标记 |
| Timeline | 时间线组件 | 时间轴展示 |
| SelectList | 选择列表 | 列表选择 |

### 业务组件 (14个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Export | 导出功能 | 数据导出 |
| Import | 导入功能 | 数据导入 |
| SearchPanel | 搜索面板 | 查询条件面板 |
| TableTools | 表格工具栏 | 表格操作工具 |
| UploadPanel | 上传面板 | 文件上传管理 |
| YjButtonList | 按钮列表 | 按钮组 |
| YjContainerCard | 容器卡片 | 卡片容器 |
| YjContainer | 容器组件 | 表单容器 |
| YjTransfer | 穿梭框 | 数据穿梭选择 |
| Calculator | 计算器 | 数值计算 |
| Magnifier | 放大镜 | 图片放大 |
| QrBarCode | 二维码/条形码 | 码生成展示 |
| WaterMark | 水印 | 页面水印 |
| ErrorPage | 错误页面 | 错误提示页 |

### 容器组件 (11个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Dialog | 对话框 | 弹窗、模态框 |
| Drawer | 抽屉 | 侧边抽屉 |
| Card | 卡片 | 内容卡片 |
| Tabs | 标签页 | 标签切换 |
| Steps | 步骤条 | 流程步骤 |
| Collapse | 折叠面板 | 内容折叠 |
| Section | 分区组件 | 内容分区 |
| Anchor | 锚点 | 页面锚点导航 |
| Title | 标题 | 标题展示 |
| FoldBar | 折叠栏 | 可折叠区域 |
| YjDialog | 增强对话框 | 增强弹窗 |

### 反馈组件 (11个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Message | 消息提示 | 操作反馈 |
| MessageBox | 消息框 | 确认对话框 |
| Notification | 通知 | 系统通知 |
| Alert | 警告框 | 警告提示 |
| Loading | 加载动画 | 加载状态 |
| Progress | 进度条 | 进度展示 |
| Tooltip | 工具提示 | 提示信息 |
| Popover | 弹出框 | 气泡提示 |
| PopConfirm | 确认弹框 | 操作确认 |
| CornerMark | 角标 | 角标提示 |
| YjMessage | 增强消息提示 | 增强反馈 |

### 导航组件 (5个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Menu | 菜单 | 导航菜单 |
| Breadcrumb | 面包屑 | 路径导航 |
| PageTabs | 页面标签 | 页面标签切换 |
| DropDown | 下拉菜单 | 下拉操作 |
| PageHeader | 页面头部 | 页面头部信息 |

### 其他组件 (6个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Carousel | 轮播图 | 图片轮播 |
| Image | 图片 | 图片展示 |
| Divider | 分割线 | 内容分割 |
| BackTop | 返回顶部 | 页面回顶 |
| InfiniteScroll | 无限滚动 | 滚动加载 |
| SpreadJS | 电子表格 | 表格编辑 |

## 常用组件详解

### Button（按钮）

**基本用法：**
```html
<button type="primary">主要按钮</button>
<button type="success">成功按钮</button>
<button type="warning">警告按钮</button>
<button type="danger">危险按钮</button>
<button type="info">信息按钮</button>
<button type="text">文本按钮</button>
```

**带图标：**
```html
<button type="primary" icon="yj-p-add">新增</button>
<button type="danger" icon="yj-p-delete">删除</button>
```

**按钮尺寸：**
```html
<button size="large">大按钮</button>
<button size="medium">中按钮</button>
<button size="small">小按钮</button>
<button size="mini">迷你按钮</button>
```

**按钮状态：**
```html
<button :disabled="true">禁用按钮</button>
<button :loading="true">加载中</button>
```

### Input（输入框）

**基本用法：**
```html
<input v-model="value" placeholder="请输入内容"></input>
```

**带图标：**
```html
<input v-model="value" prefix-icon="el-icon-search"></input>
<input v-model="value" suffix-icon="el-icon-date"></input>
```

**可清空：**
```html
<input v-model="value" clearable></input>
```

**密码框：**
```html
<input v-model="password" type="password" show-password></input>
```

**文本域：**
```html
<input v-model="text" type="textarea" :rows="4"></input>
```

### Select（选择器）

**基本用法：**
```html
<select v-model="value" placeholder="请选择">
  <option label="选项1" value="1"></option>
  <option label="选项2" value="2"></option>
</select>
```

**多选：**
```html
<select v-model="values" multiple placeholder="请选择">
  <option label="选项1" value="1"></option>
  <option label="选项2" value="2"></option>
</select>
```

**可搜索：**
```html
<select v-model="value" filterable placeholder="请选择">
  <option label="选项1" value="1"></option>
</select>
```

### DatePicker（日期选择器）

**日期选择：**
```html
<date-picker v-model="date" type="date" placeholder="选择日期"></date-picker>
```

**日期时间选择：**
```html
<date-picker v-model="datetime" type="datetime" placeholder="选择日期时间"></date-picker>
```

**日期范围：**
```html
<date-picker v-model="dateRange" type="daterange" 
             range-separator="至"
             start-placeholder="开始日期"
             end-placeholder="结束日期">
</date-picker>
```

### Table（表格）

**基本用法：**
```html
<table :data="tableData">
  <table-column prop="id" label="ID" width="80"></table-column>
  <table-column prop="name" label="姓名" width="120"></table-column>
  <table-column prop="email" label="邮箱"></table-column>
</table>
```

**带操作列：**
```html
<table :data="tableData">
  <table-column prop="name" label="姓名"></table-column>
  <table-column label="操作" width="180">
    <template slot-scope="scope">
      <button size="small" @click="handleEdit(scope.row)">编辑</button>
      <button size="small" type="danger" @click="handleDelete(scope.row)">删除</button>
    </template>
  </table-column>
</table>
```

**带分页：**
```html
<table :data="tableData" @selection-change="handleSelectionChange">
  <table-column type="selection" width="55"></table-column>
  <table-column prop="name" label="姓名"></table-column>
</table>
<pagination
  :current-page="currentPage"
  :page-size="pageSize"
  :total="total"
  @current-change="handlePageChange">
</pagination>
```

### Dialog（对话框）

**基本用法：**
```html
<dialog title="提示" :visible.sync="dialogVisible" width="30%">
  <span>这是一段信息</span>
  <span slot="footer" class="dialog-footer">
    <button @click="dialogVisible = false">取消</button>
    <button type="primary" @click="dialogVisible = false">确定</button>
  </span>
</dialog>
```

**表单对话框：**
```html
<dialog title="编辑" :visible.sync="dialogVisible">
  <form :model="form" :rules="rules" ref="form">
    <form-item label="名称" prop="name">
      <input v-model="form.name"></input>
    </form-item>
    <form-item label="描述" prop="desc">
      <input v-model="form.desc" type="textarea"></input>
    </form-item>
  </form>
  <span slot="footer">
    <button @click="dialogVisible = false">取消</button>
    <button type="primary" @click="submitForm">确定</button>
  </span>
</dialog>
```

### Message（消息提示）

**基本用法：**
```javascript
// 成功提示
this.$message.success('操作成功');

// 警告提示
this.$message.warning('警告信息');

// 错误提示
this.$message.error('操作失败');

// 普通提示
this.$message.info('提示信息');
```

**可关闭的消息：**
```javascript
this.$message({
  message: '这是一条消息',
  type: 'success',
  showClose: true,
  duration: 3000
});
```

### MessageBox（消息框）

**确认框：**
```javascript
this.$confirm('确定要删除吗？', '提示', {
  confirmButtonText: '确定',
  cancelButtonText: '取消',
  type: 'warning'
}).then(() => {
  // 确定操作
  this.$message.success('删除成功');
}).catch(() => {
  // 取消操作
  this.$message.info('已取消删除');
});
```

**提示框：**
```javascript
this.$alert('这是一条提示信息', '提示', {
  confirmButtonText: '确定'
});
```

**输入框：**
```javascript
this.$prompt('请输入名称', '提示', {
  confirmButtonText: '确定',
  cancelButtonText: '取消'
}).then(({ value }) => {
  this.$message.success('输入的内容是：' + value);
});
```

### Form（表单）

**基本用法：**
```html
<form :model="form" :rules="rules" ref="form" label-width="100px">
  <form-item label="用户名" prop="username">
    <input v-model="form.username"></input>
  </form-item>
  <form-item label="密码" prop="password">
    <input v-model="form.password" type="password"></input>
  </form-item>
  <form-item label="邮箱" prop="email">
    <input v-model="form.email"></input>
  </form-item>
  <form-item>
    <button type="primary" @click="submitForm">提交</button>
    <button @click="resetForm">重置</button>
  </form-item>
</form>
```

**验证规则：**
```javascript
data() {
  return {
    form: {
      username: '',
      password: '',
      email: ''
    },
    rules: {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, message: '密码长度不能少于6位', trigger: 'blur' }
      ],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
      ]
    }
  };
},
methods: {
  submitForm() {
    this.$refs.form.validate((valid) => {
      if (valid) {
        // 提交表单
        console.log('提交表单', this.form);
      } else {
        return false;
      }
    });
  },
  resetForm() {
    this.$refs.form.resetFields();
  }
}
```

### Tree（树形组件）

**基本用法：**
```html
<tree :data="treeData" :props="defaultProps" @node-click="handleNodeClick"></tree>
```

```javascript
data() {
  return {
    treeData: [
      {
        id: 1,
        label: '一级 1',
        children: [
          {
            id: 11,
            label: '二级 1-1',
            children: [
              { id: 111, label: '三级 1-1-1' }
            ]
          }
        ]
      },
      {
        id: 2,
        label: '一级 2',
        children: [
          { id: 21, label: '二级 2-1' }
        ]
      }
    ],
    defaultProps: {
      children: 'children',
      label: 'label'
    }
  };
},
methods: {
  handleNodeClick(data) {
    console.log(data);
  }
}
```

**可选择的树：**
```html
<tree :data="treeData" 
      :props="defaultProps"
      show-checkbox
      node-key="id"
      :default-checked-keys="[11, 21]">
</tree>
```

### Upload（文件上传）

**基本用法：**
```html
<upload
  action="/api/upload"
  :on-success="handleSuccess"
  :on-error="handleError"
  :before-upload="beforeUpload">
  <button size="small" type="primary">点击上传</button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</upload>
```

**图片上传：**
```html
<upload
  action="/api/upload"
  list-type="picture-card"
  :on-preview="handlePreview"
  :on-remove="handleRemove">
  <i class="el-icon-plus"></i>
</upload>
```

**拖拽上传：**
```html
<upload
  drag
  action="/api/upload"
  :on-success="handleSuccess">
  <i class="el-icon-upload"></i>
  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
</upload>
```

## 组件使用最佳实践

### ✓ 推荐做法

1. **按需引入组件**
   ```javascript
   import { Button, Input, Table } from 'yjpl-ui';
   ```

2. **使用组件的默认配置**
   ```html
   <button type="primary">按钮</button>
   ```

3. **合理使用组件事件**
   ```html
   <table :data="tableData" @selection-change="handleSelectionChange"></table>
   ```

### ✗ 避免做法

1. **不要过度自定义组件样式**
   ```css
   /* ✗ 避免 */
   .el-button {
     /* 大量自定义样式 */
   }
   ```

2. **不要在循环中创建大量组件实例**
   ```html
   <!-- ✗ 避免 -->
   <div v-for="item in largeList">
     <heavy-component :data="item"></heavy-component>
   </div>
   ```

3. **不要忽视组件的性能优化**
   ```html
   <!-- ✗ 避免 -->
   <table :data="allData"></table>
   
   <!-- ✓ 推荐：使用分页 -->
   <table :data="pageData"></table>
   <pagination></pagination>
   ```

## 组件查询指南

当需要了解某个组件的详细用法时：

1. **查看本文档** - 快速了解组件基本用法
2. **查看平台组件md文档** - 获取详细的API文档
3. **参考示例代码** - 查看实际使用案例

## 下一步

- 了解 [QZZ表格组件](./06-qzz-grid.md)
- 学习 [中台组件集成](./07-middleware.md)
- 查看 [Mock数据配置](./08-mock-data.md)
# YJPL前端代码生成智能体 - QZZ表格组件使用指南

## QZZ表格组件概述

QZZ是一套功能强大的表格组件库，包含 **QzzGrid**（编辑表格）和 **QzzQueryGrid**（查询表格）两个核心组件，提供丰富的数据展示、编辑、查询等功能。

## 组件对比

| 特性 | QzzGrid | QzzQueryGrid |
|------|---------|--------------|
| 主要用途 | 数据编辑 | 数据查询展示 |
| 编辑功能 | ✓ 支持 | ✗ 不支持 |
| 性能 | 适中 | 高性能 |
| 适用场景 | 表单子表、数据录入 | 列表查询、数据展示 |

## QzzGrid（编辑表格）

### 快速开始

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      gridOption: {
        Align: 'alClient',
        height: 300,
        multiselect: true,
        colNames: ['名称', '编码', '日期', '金额'],
        colModels: [
          { name: 'name' },
          { name: 'code' },
          { name: 'date', editType: 'date' },
          { name: 'amount', editType: 'number', scale: 2, sum: true }
        ]
      },
      gridData: [
        { name: '项目1', code: '001', amount: 10000 },
        { name: '项目2', code: '002', amount: 20000 }
      ]
    };
  }
}
</script>
```

### 列配置（colModels）

#### 基本属性

| 属性 | 说明 | 类型 | 常用值 |
|------|------|------|--------|
| name | 字段名称 | String | - |
| editType | 编辑类型 | String | text, number, date, comboBox, checkBox, buttonEdit |
| dataType | 数据类型 | String | string, number, date, boolean |
| width | 列宽 | Number | - |
| hidden | 是否隐藏 | Boolean | true, false |
| editable | 是否可编辑 | Boolean | true, false |
| required | 是否必填 | Boolean | true, false |
| frozen | 是否冻结 | Boolean | true, false |
| align | 对齐方式 | String | left, center, right |
| sum | 是否合计 | Boolean | true, false |
| scale | 小数精度 | Number | 如 2 表示两位小数 |

#### 编辑类型详解

**1. 文本编辑（text）**
```javascript
{
  name: 'name',
  editType: 'text',
  width: 150,
  required: true,
  length: 50
}
```

**2. 数字编辑（number）**
```javascript
{
  name: 'amount',
  editType: 'number',
  width: 120,
  scale: 2,
  minValue: 0,
  maxValue: 999999,
  sum: true
}
```

**3. 日期编辑（date）**
```javascript
{
  name: 'date',
  editType: 'date',
  width: 150,
  showTime: false,
  format: 'yyyy-MM-dd'
}
```

**4. 下拉编辑（comboBox）**
```javascript
{
  name: 'status',
  editType: 'comboBox',
  width: 100,
  data: [
    { id: '1', text: '启用' },
    { id: '0', text: '禁用' }
  ]
}
```

**5. 复选框编辑（checkBox）**
```javascript
{
  name: 'enabled',
  editType: 'checkBox',
  width: 80,
  align: 'center'
}
```

**6. 按钮编辑（buttonEdit）**
```javascript
{
  name: 'product',
  editType: 'buttonEdit',
  width: 150,
  buttonText: '选择产品'
}
```

### 表格配置（option）

#### 基本配置

```javascript
gridOption: {
  // 对齐方式
  Align: 'alClient',  // 自适应容器
  
  // 表格尺寸
  height: 300,
  width: 800,
  
  // 列配置
  colNames: ['列1', '列2'],
  colModels: [
    { name: 'field1', caption: '列1' },
    { name: 'field2', caption: '列2' }
  ],
  
  // 功能开关
  multiselect: true,    // 多选
  rownumbers: true,     // 序号列
  pager: false,         // 分页
  total: false,         // 合计行
  subtotal: false,      // 小计行
  sort: true,           // 排序
  filter: true,         // 过滤
  cellEdit: true,       // 单元格编辑
  hidePopMenu: false,   // 隐藏右键菜单
  colDesign: true       // 列设置
}
```

#### 高级配置

```javascript
gridOption: {
  // 树表配置
  treeGrid: true,
  treeGridModel: 'adjacency',
  ExpandColumn: 'name',
  
  // 虚拟滚动（大数据）
  virtual: true,
  virtualSize: 50,
  
  // 分页配置
  pager: true,
  pageSize: 20,
  
  // 样式配置
  striped: true,        // 斑马纹
  border: true,         // 边框
  hoverRows: true       // 悬停高亮
}
```

### 常用方法

#### 数据操作

```javascript
const grid = this.$refs.grid.ui;

// 获取表格数据
const data = grid.value();

// 设置表格数据
grid.value([
  { name: '项目1', amount: 1000 },
  { name: '项目2', amount: 2000 }
]);

// 添加行
grid.append();

// 插入行（在当前行前插入）
grid.insert();

// 删除当前行
grid.delRecord();

// 删除所有数据
grid.deleteAll();

// 获取变更集（新增、修改、删除的数据）
const changeSet = grid.getChangeSet();
console.log('新增:', changeSet.addList);
console.log('修改:', changeSet.editList);
console.log('删除:', changeSet.deleteList);

// 获取选中行数据
const selectedData = grid.getSelectedRowData();
```

#### 行列操作

```javascript
// 获取当前行的字段值
const value = grid.getValue('fieldName');

// 设置当前行的字段值
grid.setValue('fieldName', 'value');

// 设置列只读
grid.setColReadOnly('fieldName', true);

// 隐藏列
grid.hideCol(['fieldName1', 'fieldName2']);

// 显示列
grid.showCol(['fieldName1', 'fieldName2']);

// 设置列对齐
grid.setColAlign('fieldName', 'center');

// 获取列配置
const colModel = grid.getColModel('fieldName');

// 设置列宽
grid.setColWidth('fieldName', 150);
```

#### 表格状态

```javascript
// 设置只读
grid.setReadOnly(true);

// 获取只读状态
const isReadOnly = grid.isReadOnly();

// 刷新表格
grid.refresh();

// 设置表格高度
grid.setHeight(500);

// 设置表格宽度
grid.setWidth(800);

// 获取数据条数
const count = grid.getRecordCount();

// 全选
grid.selectAll();

// 取消全选
grid.cancelSelectRow();

// 开始批量更新
grid.beginUpdate();

// 结束批量更新
grid.endUpdate(true);  // true表示刷新
```

#### 分页操作

```javascript
// 设置每页条数
grid.setPageSize(20);

// 获取每页条数
const pageSize = grid.getPageSize();

// 设置页码
grid.setPageIndex(2);

// 获取页码
const pageIndex = grid.getPageIndex();

// 获取页数
const pageCount = grid.getPageCount();

// 第一页
grid.firstPage();

// 最后一页
grid.lastPage();

// 上一页
grid.priorPage();

// 下一页
grid.nextPage();
```

### 常用事件

#### 行事件

```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 行变更事件
  grid.bind('onRowChanged', function() {
    console.log('当前行:', this.getValue('name'));
  });
  
  // 行选择前事件
  grid.bind('beforeSelectRow', function(oldNode, newNode) {
    // 返回false可阻止选择
    return true;
  });
  
  // 行选择事件
  grid.bind('onSelectRow', function(cNode, selectState, fieldName) {
    console.log('选择状态:', selectState);
  });
}
```

#### 编辑事件

```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 编辑前事件
  grid.bind('onBeforeEdit', function(dataType, fieldName, value, text, colModel, node) {
    // 返回false可阻止编辑
    if (fieldName === 'code' && node.status === 'approved') {
      return false;  // 已审批的不允许编辑编码
    }
    return true;
  });
  
  // 编辑后事件
  grid.bind('onAfterEdit', function(dataType, fieldName, value, text, colModel, cNode) {
    console.log('编辑字段:', fieldName, '新值:', value);
    
    // 联动计算
    if (fieldName === 'quantity' || fieldName === 'price') {
      const quantity = this.getValue('quantity') || 0;
      const price = this.getValue('price') || 0;
      this.setValue('amount', quantity * price);
    }
  });
  
  // 编辑中事件
  grid.bind('onEditing', function(colModel, oldValue, newValue, cellOption, cNode) {
    return true;
  });
}
```

### 高级用法

#### 1. 单元格格式化

```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 格式化金额列
  grid.bind('onFormat_amount', function(node, fieldName, dataType, value, colModel) {
    if (value) {
      return '¥' + parseFloat(value).toFixed(2);
    }
    return '';
  });
  
  // 格式化状态列
  grid.bind('onFormat_status', function(node, fieldName, dataType, value, colModel) {
    const statusMap = {
      '1': '<span style="color: green;">启用</span>',
      '0': '<span style="color: red;">禁用</span>'
    };
    return statusMap[value] || '';
  });
}
```

#### 2. 条件编辑控制

```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 控制单元格是否可编辑
  grid.bind('onCanEditing', function(ds, fn, state) {
    // 如果状态为禁用，则不允许编辑
    if (ds.getValue('status') === '0') {
      return false;
    }
    
    // 如果是编码字段且已有值，不允许修改
    if (fn === 'code' && ds.getValue('code')) {
      return false;
    }
    
    return state;
  });
}
```

#### 3. 表达式运算

```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 添加单元格运算表达式（合计 = 数量 × 单价）
  grid.addExpress('amount', 'id', '', {
    exp: 'quantity*price',
    caption: '金额'
  });
  
  // 执行计算
  grid.caculate();
}
```

#### 4. 自定义按钮编辑

```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 按钮点击事件
  grid.bind('onButtonEdit', function(fieldName, node) {
    if (fieldName === 'product') {
      // 打开产品选择对话框
      this.openProductDialog(node);
    }
  });
}

methods: {
  openProductDialog(node) {
    // 显示产品选择对话框
    this.productDialogVisible = true;
    this.currentNode = node;
  },
  
  selectProduct(product) {
    const grid = this.$refs.grid.ui;
    // 设置选中的产品
    grid.setValue('productId', product.id);
    grid.setValue('productName', product.name);
    grid.setValue('price', product.price);
    this.productDialogVisible = false;
  }
}
```

## QzzQueryGrid（查询表格）

### 快速开始

```html
<template>
  <div style="height: 300px">
    <qzz-query-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-query-grid>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      gridOption: {
        Align: 'alClient',
        height: 300,
        colNames: ['名称', '编码', '日期', '金额'],
        colModels: [
          { name: 'name' },
          { name: 'code' },
          { name: 'date', dataType: 'date' },
          { name: 'amount', dataType: 'number', scale: 2, sum: true }
        ]
      },
      gridData: []
    };
  },
  
  async created() {
    // 加载数据
    this.gridData = await this.loadData();
  },
  
  methods: {
    async loadData() {
      // 调用API获取数据
      return [];
    }
  }
}
</script>
```

### 与QzzGrid的区别

| 特性 | QzzGrid | QzzQueryGrid |
|------|---------|--------------|
| 列配置属性 | editType | dataType |
| 编辑功能 | 支持 | 不支持 |
| 性能 | 适中 | 更高 |
| 适用场景 | 数据录入 | 数据展示 |

## 性能优化建议

### 1. 大数据量使用虚拟滚动

```javascript
gridOption: {
  virtual: true,      // 启用懒加载
  virtualSize: 50,    // 虚拟行数
  pageSize: 50        // 合理设置每页条数
}
```

### 2. 避免频繁刷新

```javascript
const grid = this.$refs.grid.ui;

// ✗ 错误：每次操作都刷新
grid.setValue('field1', value1);
grid.refresh();
grid.setValue('field2', value2);
grid.refresh();

// ✓ 正确：批量操作后统一刷新
grid.beginUpdate();
grid.setValue('field1', value1);
grid.setValue('field2', value2);
grid.endUpdate(true);  // 最后统一刷新
```

### 3. 合理使用冻结列

```javascript
colModels: [
  { name: 'id', frozen: true },      // 只冻结必要的列
  { name: 'name', frozen: true },
  { name: 'other1' },
  { name: 'other2' }
]
```

### 4. 按需加载数据

```javascript
// ✗ 错误：一次性加载所有数据
gridData: allData  // 数据过多导致性能问题

// ✓ 正确：使用分页加载
gridOption: {
  pager: true,
  pageSize: 20
}
```

## 实战示例

### 示例1：订单明细表

```html
<template>
  <div style="height: 400px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
    <div class="toolbar">
      <button @click="addRow">添加行</button>
      <button @click="deleteRow">删除行</button>
      <button @click="calculate">计算合计</button>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      gridOption: {
        Align: 'alClient',
        height: 350,
        multiselect: true,
        colNames: ['产品名称', '数量', '单价', '金额', '备注'],
        colModels: [
          {
            name: 'productName',
            editType: 'buttonEdit',
            width: 150,
            required: true,
            buttonText: '选择'
          },
          {
            name: 'quantity',
            editType: 'number',
            width: 100,
            scale: 0,
            minValue: 1,
            required: true
          },
          {
            name: 'price',
            editType: 'number',
            width: 120,
            scale: 2,
            minValue: 0,
            required: true
          },
          {
            name: 'amount',
            editType: 'number',
            width: 120,
            scale: 2,
            editable: false,
            sum: true
          },
          {
            name: 'remark',
            editType: 'text',
            width: 200
          }
        ]
      },
      gridData: []
    };
  },
  
  mounted() {
    const grid = this.$refs.grid.ui;
    
    // 编辑后自动计算金额
    grid.bind('onAfterEdit', function(dataType, fieldName, value, text, colModel, cNode) {
      if (fieldName === 'quantity' || fieldName === 'price') {
        const quantity = this.getValue('quantity') || 0;
        const price = this.getValue('price') || 0;
        this.setValue('amount', quantity * price);
      }
    });
    
    // 按钮编辑事件
    grid.bind('onButtonEdit', (fieldName, node) => {
      if (fieldName === 'productName') {
        this.selectProduct(node);
      }
    });
  },
  
  methods: {
    addRow() {
      this.$refs.grid.ui.append();
    },
    
    deleteRow() {
      const selectedRows = this.$refs.grid.ui.getSelectedRowData();
      if (selectedRows.length === 0) {
        this.$message.warning('请选择要删除的行');
        return;
      }
      this.$refs.grid.ui.delRecord();
    },
    
    calculate() {
      this.$refs.grid.ui.caculate();
    },
    
    selectProduct(node) {
      // 打开产品选择对话框
      // ...
    }
  }
}
</script>
```

### 示例2：可编辑的数据列表

```html
<template>
  <div>
    <div class="toolbar">
      <button type="primary" @click="save">保存</button>
      <button @click="cancel">取消</button>
    </div>
    <div style="height: 500px">
      <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      gridOption: {
        Align: 'alClient',
        height: 500,
        multiselect: true,
        colNames: ['姓名', '性别', '年龄', '部门', '入职日期', '状态'],
        colModels: [
          {
            name: 'name',
            editType: 'text',
            width: 120,
            required: true
          },
          {
            name: 'gender',
            editType: 'comboBox',
            width: 80,
            data: [
              { id: 'M', text: '男' },
              { id: 'F', text: '女' }
            ]
          },
          {
            name: 'age',
            editType: 'number',
            width: 80,
            scale: 0,
            minValue: 18,
            maxValue: 65
          },
          {
            name: 'department',
            editType: 'text',
            width: 150
          },
          {
            name: 'hireDate',
            editType: 'date',
            width: 120
          },
          {
            name: 'status',
            editType: 'comboBox',
            width: 100,
            data: [
              { id: '1', text: '在职' },
              { id: '0', text: '离职' }
            ]
          }
        ]
      },
      gridData: []
    };
  },
  
  async created() {
    await this.loadData();
  },
  
  methods: {
    async loadData() {
      // 加载数据
      this.gridData = await api.getEmployees();
    },
    
    async save() {
      const grid = this.$refs.grid.ui;
      const changeSet = grid.getChangeSet();
      
      if (changeSet.addList.length === 0 && 
          changeSet.editList.length === 0 && 
          changeSet.deleteList.length === 0) {
        this.$message.info('没有数据变更');
        return;
      }
      
      try {
        await api.saveEmployees(changeSet);
        this.$message.success('保存成功');
        await this.loadData();
      } catch (error) {
        this.$message.error('保存失败：' + error.message);
      }
    },
    
    cancel() {
      this.$router.back();
    }
  }
}
</script>
```

## 常见问题

### Q1: 如何设置某一行不可编辑？

```javascript
grid.bind('onCanEditing', function(ds, fn, state) {
  // 根据行数据判断
  if (ds.getValue('locked') === true) {
    return false;
  }
  return state;
});
```

### Q2: 如何实现主从表联动？

```javascript
// 主表行选择事件
mainGrid.bind('onSelectRow', async function(cNode) {
  const masterId = cNode.id;
  // 加载从表数据
  const detailData = await api.getDetails(masterId);
  detailGrid.value(detailData);
});
```

### Q3: 如何导出表格数据？

```javascript
methods: {
  exportData() {
    const grid = this.$refs.grid.ui;
    const data = grid.value();
    // 转换为Excel格式并下载
    this.downloadExcel(data);
  }
}
```

## 下一步

- 学习 [中台组件集成](./07-middleware.md)
- 查看 [Mock数据配置](./08-mock-data.md)
- 了解 [最佳实践](./09-best-practices.md)
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
# YJPL前端代码生成智能体 - Mock数据配置

## Mock概述

Mock是一种在前后端分离开发中常用的技术，用于模拟后端API接口返回的数据。在YJPL框架中，Mock机制允许前端开发人员在后端API尚未完成或不可用的情况下，继续进行前端开发和测试工作。

### Mock的作用

- **加速开发流程**：不需要等待后端API完成，前端可以独立开发
- **提高开发效率**：避免因后端服务不稳定导致的开发中断
- **便于测试**：可以模拟各种数据场景，包括边界条件和错误情况
- **降低依赖**：减少对后端服务的依赖，提高开发灵活性

## Mock配置

### 目录结构

```
src
 |- mock      mock数据目录
 |     |- index.ts  主要的mock配置文件
```

### 基本配置

在 `src/mock/index.ts` 文件中配置mock规则：

```typescript
export default {
  doGet: {
    // GET请求拦截
  },
  
  doPost: {
    // POST请求拦截
  }
};
```

## Mock规则详解

### GET请求拦截

```javascript
doGet: {
  '/api/users': {
    data(param: any) {
      // param 包含请求参数
      return [
        { id: 1, name: '张三', email: 'zhangsan@example.com' },
        { id: 2, name: '李四', email: 'lisi@example.com' }
      ];
    }
  }
}
```

### POST请求拦截

```javascript
doPost: {
  '/api/user/add': {
    data(param: any) {
      // param 包含请求体数据
      return {
        success: true,
        message: '添加成功',
        data: {
          id: Date.now(),
          ...param
        }
      };
    }
  }
}
```

## 高级用法

### 1. 动态生成数据

```javascript
doGet: {
  '/api/users': {
    data(param: any) {
      const users = [];
      for (let i = 1; i <= 100; i++) {
        users.push({
          id: i,
          name: `用户${i}`,
          email: `user${i}@example.com`,
          age: 20 + Math.floor(Math.random() * 40),
          status: Math.random() > 0.5 ? 1 : 0
        });
      }
      return users;
    }
  }
}
```

### 2. 模拟延迟响应

```javascript
doGet: {
  '/api/slow-data': {
    data(param: any) {
      return new Promise((resolve) => {
        setTimeout(() => {
          resolve({
            message: '延迟返回的数据'
          });
        }, 2000); // 延迟2秒返回
      });
    }
  }
}
```

### 3. 根据参数返回不同数据

```javascript
doPost: {
  '/api/user/info': {
    data(param: any) {
      const { userId } = param;
      
      if (userId === '1') {
        return {
          id: '1',
          name: '管理员',
          role: 'admin',
          permissions: ['user:add', 'user:edit', 'user:delete']
        };
      } else {
        return {
          id: userId,
          name: '普通用户',
          role: 'user',
          permissions: ['user:view']
        };
      }
    }
  }
}
```

### 4. 模拟错误情况

```javascript
doGet: {
  '/api/error-demo': {
    data(param: any) {
      // 模拟随机错误
      if (Math.random() > 0.7) {
        throw new Error('服务器内部错误');
      }
      
      return {
        success: true,
        data: []
      };
    }
  }
}
```

### 5. 分页数据模拟

```javascript
doPost: {
  '/api/data/page': {
    data(param: any) {
      const { pageSize = 20, pageNum = 1, keyword = '' } = param;
      const total = 100;
      
      // 生成数据
      const allData = [];
      for (let i = 1; i <= total; i++) {
        allData.push({
          id: i,
          name: `项目${i}`,
          description: `这是项目${i}的描述`,
          status: Math.random() > 0.5 ? 1 : 0,
          createTime: new Date(Date.now() - Math.random() * 30 * 24 * 60 * 60 * 1000).toISOString()
        });
      }
      
      // 关键词过滤
      let filteredData = allData;
      if (keyword) {
        filteredData = allData.filter(item => 
          item.name.includes(keyword) || item.description.includes(keyword)
        );
      }
      
      // 分页
      const startIndex = (pageNum - 1) * pageSize;
      const endIndex = Math.min(startIndex + pageSize, filteredData.length);
      const list = filteredData.slice(startIndex, endIndex);
      
      return {
        list,
        total: filteredData.length,
        pageSize,
        pageNum,
        pages: Math.ceil(filteredData.length / pageSize)
      };
    }
  }
}
```

### 6. 文件上传模拟

```javascript
doPost: {
  '/api/upload': {
    data(param: any) {
      const { fileName, fileSize } = param;
      
      return {
        success: true,
        data: {
          fileId: 'mock_file_' + Date.now(),
          fileName: fileName || 'unknown.file',
          fileSize: fileSize || 0,
          fileUrl: `https://example.com/files/${Date.now()}.jpg`,
          uploadTime: new Date().toISOString()
        }
      };
    }
  }
}
```

### 7. 登录认证模拟

```javascript
doPost: {
  '/api/login': {
    data(param: any) {
      const { username, password } = param;
      
      // 模拟用户数据库
      const users = {
        'admin': { password: 'admin123', role: 'admin', name: '管理员' },
        'user': { password: 'user123', role: 'user', name: '普通用户' }
      };
      
      const user = users[username];
      
      if (!user) {
        throw new Error('用户不存在');
      }
      
      if (user.password !== password) {
        throw new Error('密码错误');
      }
      
      return {
        success: true,
        data: {
          token: 'mock_token_' + Date.now(),
          userInfo: {
            id: username,
            username: username,
            name: user.name,
            role: user.role
          }
        }
      };
    }
  }
}
```

## 实战示例

### 示例1：用户管理Mock

```javascript
// src/mock/user.ts
export default {
  doGet: {
    // 获取用户列表
    '/api/user/list': {
      data(param: any) {
        const { pageSize = 20, pageNum = 1, keyword = '', status } = param;
        
        // 模拟用户数据
        const users = [];
        for (let i = 1; i <= 100; i++) {
          users.push({
            id: i,
            username: `user${i}`,
            name: `用户${i}`,
            email: `user${i}@example.com`,
            phone: `138${String(i).padStart(8, '0')}`,
            status: Math.random() > 0.5 ? 1 : 0,
            createTime: new Date(Date.now() - Math.random() * 365 * 24 * 60 * 60 * 1000).toISOString()
          });
        }
        
        // 过滤
        let filteredUsers = users;
        if (keyword) {
          filteredUsers = users.filter(u => 
            u.username.includes(keyword) || 
            u.name.includes(keyword) || 
            u.email.includes(keyword)
          );
        }
        if (status !== undefined && status !== null) {
          filteredUsers = filteredUsers.filter(u => u.status === status);
        }
        
        // 分页
        const start = (pageNum - 1) * pageSize;
        const end = start + pageSize;
        
        return {
          list: filteredUsers.slice(start, end),
          total: filteredUsers.length,
          pageSize,
          pageNum
        };
      }
    },
    
    // 获取用户详情
    '/api/user/detail': {
      data(param: any) {
        const { id } = param;
        return {
          id,
          username: `user${id}`,
          name: `用户${id}`,
          email: `user${id}@example.com`,
          phone: `138${String(id).padStart(8, '0')}`,
          status: 1,
          roles: ['user'],
          createTime: new Date().toISOString()
        };
      }
    }
  },
  
  doPost: {
    // 添加用户
    '/api/user/add': {
      data(param: any) {
        return {
          success: true,
          message: '添加成功',
          data: {
            id: Date.now(),
            ...param,
            createTime: new Date().toISOString()
          }
        };
      }
    },
    
    // 更新用户
    '/api/user/update': {
      data(param: any) {
        return {
          success: true,
          message: '更新成功',
          data: param
        };
      }
    },
    
    // 删除用户
    '/api/user/delete': {
      data(param: any) {
        return {
          success: true,
          message: '删除成功'
        };
      }
    }
  }
};
```

### 示例2：订单管理Mock

```javascript
// src/mock/order.ts
export default {
  doGet: {
    // 获取订单列表
    '/api/order/list': {
      data(param: any) {
        const { pageSize = 20, pageNum = 1 } = param;
        
        const orders = [];
        for (let i = 1; i <= 50; i++) {
          orders.push({
            id: i,
            orderNo: `ORD${Date.now()}${i}`,
            customerName: `客户${i}`,
            amount: Math.floor(Math.random() * 100000) / 100,
            status: ['pending', 'processing', 'completed', 'cancelled'][Math.floor(Math.random() * 4)],
            createTime: new Date(Date.now() - Math.random() * 30 * 24 * 60 * 60 * 1000).toISOString()
          });
        }
        
        const start = (pageNum - 1) * pageSize;
        const end = start + pageSize;
        
        return {
          list: orders.slice(start, end),
          total: orders.length,
          pageSize,
          pageNum
        };
      }
    },
    
    // 获取订单详情
    '/api/order/detail': {
      data(param: any) {
        const { id } = param;
        return {
          id,
          orderNo: `ORD${Date.now()}${id}`,
          customerName: `客户${id}`,
          customerPhone: '13800138000',
          amount: 10000,
          status: 'processing',
          createTime: new Date().toISOString(),
          items: [
            {
              id: 1,
              productName: '产品A',
              quantity: 10,
              price: 500,
              amount: 5000
            },
            {
              id: 2,
              productName: '产品B',
              quantity: 20,
              price: 250,
              amount: 5000
            }
          ]
        };
      }
    }
  },
  
  doPost: {
    // 创建订单
    '/api/order/create': {
      data(param: any) {
        return {
          success: true,
          message: '订单创建成功',
          data: {
            id: Date.now(),
            orderNo: `ORD${Date.now()}`,
            ...param,
            status: 'pending',
            createTime: new Date().toISOString()
          }
        };
      }
    },
    
    // 更新订单状态
    '/api/order/updateStatus': {
      data(param: any) {
        return {
          success: true,
          message: '状态更新成功'
        };
      }
    }
  }
};
```

### 示例3：组合Mock配置

```javascript
// src/mock/index.ts
import userMock from './user';
import orderMock from './order';
import productMock from './product';

export default {
  doGet: {
    ...userMock.doGet,
    ...orderMock.doGet,
    ...productMock.doGet
  },
  doPost: {
    ...userMock.doPost,
    ...orderMock.doPost,
    ...productMock.doPost
  }
};
```

## 最佳实践

### 1. 组织Mock数据

对于复杂项目，建议将mock数据按模块分离：

```
src/mock/
  ├── index.ts       # 主配置文件
  ├── user.ts        # 用户模块
  ├── order.ts       # 订单模块
  ├── product.ts     # 产品模块
  └── common.ts      # 公共数据
```

### 2. 保持数据结构一致

Mock数据的结构应与实际API返回的数据结构保持一致：

```javascript
// ✓ 正确：与实际API结构一致
{
  success: true,
  data: {
    list: [],
    total: 0
  },
  message: ''
}

// ✗ 错误：结构不一致
{
  items: [],
  count: 0
}
```

### 3. 使用真实的数据格式

```javascript
// ✓ 正确：使用真实的日期格式
createTime: new Date().toISOString()

// ✗ 错误：使用简化的格式
createTime: '2024-12-04'
```

### 4. 模拟真实的业务逻辑

```javascript
doPost: {
  '/api/user/add': {
    data(param: any) {
      // 验证必填字段
      if (!param.username) {
        throw new Error('用户名不能为空');
      }
      
      if (!param.email) {
        throw new Error('邮箱不能为空');
      }
      
      // 验证邮箱格式
      const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailReg.test(param.email)) {
        throw new Error('邮箱格式不正确');
      }
      
      return {
        success: true,
        data: {
          id: Date.now(),
          ...param
        }
      };
    }
  }
}
```

### 5. 及时更新Mock数据

当后端API发生变化时，应及时更新对应的Mock数据。

### 6. 使用环境变量控制

```javascript
// 根据环境决定是否使用Mock
if (process.env.NODE_ENV === 'development') {
  // 使用Mock数据
  import('./mock');
}
```

## 注意事项

1. **不要在Mock数据中包含敏感信息**
2. **Mock数据只是开发阶段的辅助工具**
3. **最终产品应该与实际API进行充分测试**
4. **注意Mock数据的性能影响**
5. **保持Mock数据的可维护性**

## 常见问题

### Q1: Mock数据不生效？

**解决方案：**
1. 检查API路径是否正确
2. 检查请求方法（GET/POST）是否匹配
3. 检查Mock配置是否正确导入
4. 检查浏览器控制台是否有错误

### Q2: 如何切换到真实API？

```javascript
// 方法1：使用环境变量
const API_BASE_URL = process.env.VUE_APP_USE_MOCK === 'true' 
  ? '/mock-api' 
  : '/real-api';

// 方法2：配置开关
const useMock = false;
if (useMock) {
  import('./mock');
}
```

### Q3: 如何模拟复杂的业务场景？

使用状态管理和条件判断：

```javascript
let orderStatus = 'pending';

doPost: {
  '/api/order/submit': {
    data(param: any) {
      orderStatus = 'processing';
      return { success: true };
    }
  },
  
  '/api/order/status': {
    data(param: any) {
      return { status: orderStatus };
    }
  }
}
```

## 下一步

- 了解 [最佳实践](./09-best-practices.md)
- 查看 [实战示例](./10-examples.md)
- 学习 [常见问题解决](./11-troubleshooting.md)
# YJPL前端代码生成智能体 - 最佳实践

## 代码组织

### 文件结构

```
your-page/
├── index.yjpl              # 主模板文件
├── Business.ts             # 业务逻辑类
├── Model.ts                # 数据模型
├── components/             # 子组件
│   ├── CustomDialog.yjpl
│   └── CustomPanel.yjpl
└── styles/                 # 样式文件
    └── index.scss
```

### 命名规范

**文件命名：**
- 组件文件：PascalCase（如 `UserList.yjpl`）
- 业务逻辑：PascalCase（如 `Business.ts`）
- 数据模型：PascalCase（如 `Model.ts`）
- 样式文件：kebab-case（如 `user-list.scss`）

**变量命名：**
- 变量和函数：camelCase（如 `userName`, `handleClick`）
- 常量：UPPER_SNAKE_CASE（如 `MAX_COUNT`）
- 类名：PascalCase（如 `UserBusiness`）
- CSS类名：kebab-case（如 `.user-list`）

## 数据模型设计

### ✓ 推荐做法

**1. 分离配置和数据**
```typescript
// ✓ 正确
export const metaModel = { /* 静态配置 */ };
export const dataModel = { /* 动态数据 */ };
export const stateModel = { /* 状态控制 */ };
```

**2. 使用TypeScript类型**
```typescript
// ✓ 正确
interface QueryData {
  userName: string;
  status: number | null;
}

export const dataModel = {
  query: {} as QueryData
};
```

**3. 提供合理的默认值**
```typescript
// ✓ 正确
export const dataModel = {
  query: {
    userName: '',
    status: null
  },
  grid: [],
  pageSize: 20,
  pageIndex: 1
};
```

### ✗ 避免做法

**1. 混合配置和数据**
```typescript
// ✗ 错误
export const model = {
  title: '用户列表',  // 配置
  users: [],          // 数据
  addDisabled: true   // 状态
};
```

**2. 在metaModel中存储动态数据**
```typescript
// ✗ 错误
export const metaModel = {
  query: [
    {
      name: 'userName',
      value: '张三'  // 错误：应该在dataModel中
    }
  ]
};
```

## 组件使用

### ✓ 推荐做法

**1. 按需引入组件**
```javascript
// ✓ 正确
import { Button, Input, Table } from 'yjpl-ui';
```

**2. 使用组件的默认配置**
```html
<!-- ✓ 正确 -->
<button type="primary">按钮</button>
```

**3. 合理使用组件事件**
```html
<!-- ✓ 正确 -->
<table :data="tableData" @selection-change="handleSelectionChange"></table>
```

### ✗ 避免做法

**1. 过度自定义组件样式**
```css
/* ✗ 避免 */
.el-button {
  /* 大量自定义样式 */
}
```

**2. 在循环中创建大量组件实例**
```html
<!-- ✗ 避免 -->
<div v-for="item in largeList">
  <heavy-component :data="item"></heavy-component>
</div>
```

## 性能优化

### 1. 大数据列表优化

**使用虚拟滚动：**
```typescript
// ✓ 推荐
grid: {
  virtual: true,
  virtualSize: 20,
  pageSize: 20
}
```

**使用分页：**
```typescript
// ✓ 推荐
grid: {
  pageSize: 20,
  showPagination: true
}
```

### 2. 表格列优化

**指定列宽：**
```typescript
// ✓ 推荐
columns: [
  { key: 'id', label: 'ID', width: 80 },
  { key: 'name', label: '名称', width: 150 }
]
```

**使用格式化：**
```typescript
// ✓ 推荐
columns: [
  { key: 'amount', label: '金额', formatType: 'money', scale: 2 }
]
```

### 3. 事件处理优化

**使用事件委托：**
```typescript
// ✓ 推荐
eventsModel: {
  grid: {
    'row-click': (row) => this.handleRowClick(row)
  }
}
```

**避免在模板中处理复杂逻辑：**
```html
<!-- ✗ 避免 -->
<button @click="complexLogic(item, index, event)">操作</button>

<!-- ✓ 推荐 -->
<button @click="handleClick(item)">操作</button>
```

### 4. 监听器优化

**深度监听必要字段：**
```typescript
// ✓ 推荐
watch: {
  'dataModel.query': {
    handler() { /* ... */ },
    deep: true
  }
}

// ✗ 避免
watch: {
  dataModel: {
    handler() { /* ... */ },
    deep: true
  }
}
```

### 5. 计算属性优化

**使用计算属性缓存：**
```typescript
// ✓ 推荐
computed: {
  filteredList() {
    return this.list.filter(item => item.status === 1);
  }
}

// ✗ 避免
methods: {
  getFilteredList() {
    return this.list.filter(item => item.status === 1);
  }
}
```

## 代码质量

### 1. 使用ESLint

**.eslintrc.js配置：**
```javascript
module.exports = {
  extends: [
    'plugin:vue/recommended',
    '@vue/typescript/recommended'
  ],
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off'
  }
};
```

### 2. 代码注释

**函数注释：**
```typescript
/**
 * 获取用户列表
 * @param pageSize 每页条数
 * @param pageIndex 页码
 * @returns 用户列表数据
 */
async getUserList(pageSize: number, pageIndex: number) {
  // 实现代码
}
```

**复杂逻辑注释：**
```typescript
// 计算订单总金额
// 1. 遍历所有订单项
// 2. 累加每项的金额
// 3. 应用折扣
const totalAmount = orderItems.reduce((sum, item) => {
  return sum + item.quantity * item.price;
}, 0) * discount;
```

### 3. 错误处理

**统一错误处理：**
```typescript
async getGridData() {
  try {
    const response = await api.getList();
    return response.data;
  } catch (error) {
    this.$message.error('获取数据失败：' + error.message);
    console.error('获取数据失败:', error);
    return [];
  }
}
```

**表单验证：**
```typescript
validateForm() {
  if (!this.dataModel.form.orderNo) {
    this.$message.warning('请输入订单号');
    return false;
  }
  
  if (!this.dataModel.form.amount || this.dataModel.form.amount <= 0) {
    this.$message.warning('请输入有效的金额');
    return false;
  }
  
  return true;
}
```

## 模板使用

### ✓ 推荐做法

**1. 优先使用模板**
```xml
<!-- ✓ 推荐 -->
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
    <!-- 使用plugins扩展 -->
  </page>
</template>
```

**2. 合理使用plugins**
```xml
<!-- ✓ 推荐：只扩展必要的区域 -->
<plugins type="replace" target=".query-toolbar">
  <!-- 自定义工具栏 -->
</plugins>
```

### ✗ 避免做法

**1. 不要直接修改模板文件**
```xml
<!-- ✗ 错误：直接修改list-template.yjpl -->
```

**2. 不要过度自定义**
```xml
<!-- ✗ 错误：替换所有区域 -->
<plugins type="replace" target=".query-header"></plugins>
<plugins type="replace" target=".query-search-panel"></plugins>
<plugins type="replace" target=".query-toolbar"></plugins>
<plugins type="replace" target=".query-grid-body"></plugins>
```

## 测试

### 1. 单元测试

```typescript
import { mount } from '@vue/test-utils';
import UserList from './index.yjpl';

describe('UserList', () => {
  it('renders correctly', () => {
    const wrapper = mount(UserList);
    expect(wrapper.exists()).toBe(true);
  });
  
  it('loads data on created', async () => {
    const wrapper = mount(UserList);
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.dataModel.grid.length).toBeGreaterThan(0);
  });
});
```

### 2. 集成测试

```typescript
describe('User Management', () => {
  it('should add user successfully', async () => {
    // 1. 打开新增对话框
    await wrapper.find('.btn-add').trigger('click');
    
    // 2. 填写表单
    await wrapper.find('input[name="username"]').setValue('testuser');
    await wrapper.find('input[name="email"]').setValue('test@example.com');
    
    // 3. 提交表单
    await wrapper.find('.btn-submit').trigger('click');
    
    // 4. 验证结果
    expect(wrapper.vm.dataModel.grid).toContainEqual(
      expect.objectContaining({ username: 'testuser' })
    );
  });
});
```

## 文档

### 1. README文档

```markdown
# 用户管理模块

## 功能说明
- 用户列表查询
- 用户新增/编辑/删除
- 用户状态管理

## 技术栈
- YJPL 8.5.0+
- Vue 2.x
- TypeScript

## 使用方法
1. 安装依赖：`npm install`
2. 启动开发：`npm run dev`
3. 构建生产：`npm run build`

## API接口
- GET /api/user/list - 获取用户列表
- POST /api/user/add - 添加用户
- POST /api/user/update - 更新用户
- POST /api/user/delete - 删除用户
```

### 2. 代码文档

```typescript
/**
 * 用户管理业务类
 * @class UserBusiness
 * @extends YJBusiness
 */
export default class UserBusiness extends YJBusiness {
  /**
   * 获取用户列表
   * @param {number} pageSize - 每页条数
   * @param {number} pageIndex - 页码
   * @returns {Promise<Array>} 用户列表
   */
  async getUserList(pageSize: number, pageIndex: number): Promise<any[]> {
    // 实现代码
  }
}
```

## 安全

### 1. XSS防护

```typescript
// ✓ 推荐：使用v-text而不是v-html
<div v-text="userInput"></div>

// ✗ 避免：直接使用v-html
<div v-html="userInput"></div>
```

### 2. CSRF防护

```typescript
// 在请求头中添加CSRF token
axios.defaults.headers.common['X-CSRF-TOKEN'] = getCsrfToken();
```

### 3. 权限控制

```typescript
// 使用指令控制权限
<button v-permission="'user:add'">新增</button>

// 使用计算属性
computed: {
  canAdd() {
    return this.$store.getters.hasPermission('user:add');
  }
}
```

## 部署

### 1. 环境配置

```javascript
// .env.development
VUE_APP_API_BASE_URL=http://localhost:8080/api
VUE_APP_USE_MOCK=true

// .env.production
VUE_APP_API_BASE_URL=https://api.example.com
VUE_APP_USE_MOCK=false
```

### 2. 构建优化

```javascript
// vue.config.js
module.exports = {
  productionSourceMap: false,
  
  configureWebpack: {
    optimization: {
      splitChunks: {
        chunks: 'all',
        cacheGroups: {
          vendor: {
            test: /[\\/]node_modules[\\/]/,
            name: 'vendors',
            priority: 10
          }
        }
      }
    }
  }
};
```

## 监控

### 1. 错误监控

```typescript
// 全局错误处理
Vue.config.errorHandler = (err, vm, info) => {
  console.error('Error:', err);
  console.error('Component:', vm);
  console.error('Info:', info);
  
  // 上报错误
  reportError({
    error: err.message,
    stack: err.stack,
    component: vm.$options.name,
    info
  });
};
```

### 2. 性能监控

```typescript
// 页面加载时间
window.addEventListener('load', () => {
  const timing = performance.timing;
  const loadTime = timing.loadEventEnd - timing.navigationStart;
  console.log('Page load time:', loadTime);
});
```

## 总结

遵循这些最佳实践可以：
1. 提高代码质量和可维护性
2. 提升应用性能
3. 减少bug和错误
4. 提高开发效率
5. 便于团队协作

## 下一步

- 查看 [实战示例](./10-examples.md)
- 学习 [常见问题解决](./11-troubleshooting.md)
# YJPL前端代码生成智能体 - 实战示例

## 示例1：用户管理页面

### 需求描述
实现一个用户管理页面，包含用户列表查询、新增、编辑、删除功能。

### 实现步骤

#### 1. 创建Model.ts

```typescript
import { YJState } from 'yjpl-core';

export const metaModel = {
  title: '用户管理',
  
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
        { label: '全部', value: '' },
        { label: '启用', value: 1 },
        { label: '禁用', value: 0 }
      ]
    }
  ],
  
  grid: {
    colNames: ['ID', '用户名', '姓名', '邮箱', '状态', '创建时间'],
    colModels: [
      { name: 'id', caption: 'ID', width: 80 },
      { name: 'userName', caption: '用户名', width: 120 },
      { name: 'name', caption: '姓名', width: 120 },
      { name: 'email', caption: '邮箱', width: 200 },
      { name: 'status', caption: '状态', width: 100, formatType: 'enum', enumData: { 1: '启用', 0: '禁用' } },
      { name: 'createTime', caption: '创建时间', width: 150, formatType: 'date' }
    ],
    pageSize: 20,
    pager: true
  },
  
  btns: [
    { name: 'add', text: '新增', type: 'primary', icon: 'yj-p-add' },
    { name: 'edit', text: '编辑', icon: 'yj-p-edit' },
    { name: 'delete', text: '删除', icon: 'yj-p-delete', type: 'danger' }
  ]
};

export const dataModel = {
  query: {
    userName: '',
    status: ''
  },
  grid: [],
  pageTotal: 0
};

export const stateModel = {
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DISABLED,
  delete: YJState.STATE.DISABLED
};

export default { metaModel, dataModel, stateModel };
```

#### 2. 创建Business.ts

```typescript
import { YJBusiness } from 'yjpl-core';
import Model from './Model';
import api from '@/api/user';

export default class UserBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  async getQueryData() {
    return {
      userName: '',
      status: ''
    };
  }

  async getGridData(pageSize?: number, pageIndex?: number) {
    try {
      const response = await api.getUserList({
        pageSize: pageSize || 20,
        pageIndex: pageIndex || 1,
        ...this.dataModel.query
      });
      return response.data.list;
    } catch (error) {
      console.error('获取用户列表失败:', error);
      return [];
    }
  }

  async getTotalRecord() {
    try {
      const response = await api.getUserList({
        pageSize: 1,
        pageIndex: 1,
        ...this.dataModel.query
      });
      return response.data.total;
    } catch (error) {
      console.error('获取总数失败:', error);
      return 0;
    }
  }

  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }
}
```

#### 3. 创建index.yjpl

```xml
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
  </page>
</template>

<script lang="ts">
import ListTemplate from 'yjpl-template/business/list-template.yjpl';
import Business from './Business';
import UserDialog from './components/UserDialog.yjpl';

export default class UserListPage extends ListTemplate {
  constructor() {
    super();
    this.bs = new Business();
  }

  data() {
    return {
      ...this.bs.getData(),
      eventsModel: {
        add: { click: this.handleAdd },
        edit: { click: this.handleEdit },
        delete: { click: this.handleDelete },
        query: { click: this.handleQuery },
        reset: { click: this.handleReset }
      },
      dialogVisible: false,
      dialogMode: 'add',
      currentUser: null
    };
  }

  components() {
    return {
      'user-dialog': UserDialog
    };
  }

  async created() {
    this.dataModel.query = await this.bs.getQueryData();
    await this.loadData();
  }

  mounted() {
    if (this.$refs.grid && this.$refs.grid.ui) {
      this.$refs.grid.ui.setTotalRecord(this.dataModel.pageTotal);
    }
  }

  methods() {
    return {
      async loadData() {
        this.dataModel.grid = await this.bs.getGridData();
        this.dataModel.pageTotal = await this.bs.getTotalRecord();
        if (this.$refs.grid && this.$refs.grid.ui) {
          this.$refs.grid.ui.setTotalRecord(this.dataModel.pageTotal);
        }
      },
      
      async handleQuery() {
        await this.loadData();
        this.$refs.grid.ui.refresh();
      },
      
      handleReset() {
        this.dataModel.query = {
          userName: '',
          status: ''
        };
      },
      
      handleAdd() {
        this.dialogMode = 'add';
        this.currentUser = null;
        this.dialogVisible = true;
      },
      
      handleEdit() {
        const selectedRows = this.$refs.grid.ui.getSelectedRowData();
        if (selectedRows.length === 0) {
          this.$message.warning('请选择要编辑的用户');
          return;
        }
        if (selectedRows.length > 1) {
          this.$message.warning('只能选择一个用户进行编辑');
          return;
        }
        this.dialogMode = 'edit';
        this.currentUser = selectedRows[0];
        this.dialogVisible = true;
      },
      
      handleDelete() {
        const selectedRows = this.$refs.grid.ui.getSelectedRowData();
        if (selectedRows.length === 0) {
          this.$message.warning('请选择要删除的用户');
          return;
        }
        
        this.$confirm(`确定要删除选中的${selectedRows.length}个用户吗？`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          try {
            const ids = selectedRows.map(row => row.id);
            await api.deleteUsers(ids);
            this.$message.success('删除成功');
            await this.loadData();
          } catch (error) {
            this.$message.error('删除失败：' + error.message);
          }
        });
      },
      
      async handleDialogSuccess() {
        this.dialogVisible = false;
        await this.loadData();
      }
    };
  }

  watch() {
    return {
      'dataModel.grid': {
        handler(newVal) {
          // 根据选中行控制按钮状态
          const selectedRows = this.$refs.grid?.ui?.getSelectedRowData() || [];
          if (selectedRows.length === 0) {
            this.stateModel.edit = YJState.STATE.DISABLED;
            this.stateModel.delete = YJState.STATE.DISABLED;
          } else if (selectedRows.length === 1) {
            this.stateModel.edit = YJState.STATE.DEFAULT;
            this.stateModel.delete = YJState.STATE.DEFAULT;
          } else {
            this.stateModel.edit = YJState.STATE.DISABLED;
            this.stateModel.delete = YJState.STATE.DEFAULT;
          }
        }
      }
    };
  }
}
</script>

<style lang="scss" scoped>
// 自定义样式
</style>
```

## 示例2：订单详情页面

### 需求描述
实现一个订单详情页面，包含订单基本信息、订单明细、审批流程等。

### 实现步骤

#### 1. 创建Model.ts

```typescript
export const metaModel = {
  title: '订单详情',
  code: '',
  date: '',
  cols: 4,
  
  form: [
    {
      name: 'orderNo',
      label: '订单号',
      dataType: 'string',
      required: true,
      span: 2,
      disabled: true
    },
    {
      name: 'customerName',
      label: '客户名称',
      dataType: 'string',
      required: true,
      span: 2
    },
    {
      name: 'orderDate',
      label: '订单日期',
      dataType: 'date',
      required: true
    },
    {
      name: 'deliveryDate',
      label: '交货日期',
      dataType: 'date',
      required: true
    },
    {
      name: 'amount',
      label: '订单金额',
      dataType: 'number',
      scale: 2,
      disabled: true
    },
    {
      name: 'status',
      label: '订单状态',
      dataType: 'combobox',
      options: [
        { label: '待审批', value: 'pending' },
        { label: '已审批', value: 'approved' },
        { label: '已完成', value: 'completed' }
      ],
      disabled: true
    },
    {
      name: 'remark',
      label: '备注',
      dataType: 'textarea',
      span: 4,
      rows: 4
    }
  ],
  
  grid: [
    {
      title: '订单明细',
      columns: [
        { key: 'productName', label: '产品名称', width: 150 },
        { key: 'quantity', label: '数量', width: 100 },
        { key: 'price', label: '单价', width: 120 },
        { key: 'amount', label: '金额', width: 120 },
        { key: 'remark', label: '备注', width: 200 }
      ]
    }
  ],
  
  toolbar: [
    { label: '保存', type: 'primary', name: 'save' },
    { label: '提交', type: 'primary', name: 'submit' },
    { label: '取消', name: 'cancel' }
  ],
  
  anchor: [
    { title: '基础信息', id: 'form' },
    { title: '订单明细', id: 'info' },
    { title: '附件文档', id: 'upload' },
    { title: '审批流程', id: 'workflow' }
  ]
};

export const dataModel = {
  form: {
    orderNo: '',
    customerName: '',
    orderDate: '',
    deliveryDate: '',
    amount: 0,
    status: 'pending',
    remark: ''
  },
  grid: [],
  attachments: [],
  workflow: []
};

export const stateModel = {
  save: YJState.STATE.DEFAULT,
  submit: YJState.STATE.DEFAULT,
  cancel: YJState.STATE.DEFAULT
};

export default { metaModel, dataModel, stateModel };
```

#### 2. 创建Business.ts

```typescript
import { YJBusiness } from 'yjpl-core';
import Model from './Model';
import api from '@/api/order';

export default class OrderBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  async getOrderData(orderId: string) {
    try {
      const response = await api.getOrderDetail(orderId);
      return response.data;
    } catch (error) {
      console.error('获取订单详情失败:', error);
      throw error;
    }
  }

  async saveOrder(data: any) {
    try {
      const response = await api.saveOrder(data);
      return response.data;
    } catch (error) {
      console.error('保存订单失败:', error);
      throw error;
    }
  }

  async submitOrder(data: any) {
    try {
      const response = await api.submitOrder(data);
      return response.data;
    } catch (error) {
      console.error('提交订单失败:', error);
      throw error;
    }
  }

  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }
}
```

## 示例3：带树形导航的列表页面

### 实现代码

```typescript
// Model.ts
export const metaModel = {
  title: '部门用户管理',
  showTree: true,
  
  tree: {
    nodeKey: 'id',
    label: 'name',
    children: 'children',
    defaultExpandAll: false
  },
  
  query: [
    {
      name: 'userName',
      label: '用户名',
      dataType: 'string'
    }
  ],
  
  grid: {
    colNames: ['用户名', '姓名', '职位', '状态'],
    colModels: [
      { name: 'userName', caption: '用户名', width: 120 },
      { name: 'name', caption: '姓名', width: 120 },
      { name: 'position', caption: '职位', width: 150 },
      { name: 'status', caption: '状态', width: 100 }
    ]
  }
};
```

```xml
<!-- index.yjpl -->
<script lang="ts">
export default class DeptUserPage extends ListTemplate {
  methods() {
    return {
      async handleTreeSelect(node) {
        // 树节点选择事件
        this.dataModel.query.deptId = node.id;
        await this.loadData();
      }
    };
  }
}
</script>
```

## 示例4：表格内编辑

### 实现代码

```typescript
// Model.ts
export const metaModel = {
  grid: {
    colNames: ['产品名称', '数量', '单价', '金额', '备注'],
    colModels: [
      {
        name: 'productName',
        editType: 'buttonEdit',
        width: 150,
        required: true,
        buttonText: '选择'
      },
      {
        name: 'quantity',
        editType: 'number',
        width: 100,
        scale: 0,
        minValue: 1,
        required: true
      },
      {
        name: 'price',
        editType: 'number',
        width: 120,
        scale: 2,
        minValue: 0,
        required: true
      },
      {
        name: 'amount',
        editType: 'number',
        width: 120,
        scale: 2,
        editable: false,
        sum: true
      },
      {
        name: 'remark',
        editType: 'text',
        width: 200
      }
    ]
  }
};
```

```typescript
// index.yjpl
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 编辑后自动计算金额
  grid.bind('onAfterEdit', function(dataType, fieldName, value, text, colModel, cNode) {
    if (fieldName === 'quantity' || fieldName === 'price') {
      const quantity = this.getValue('quantity') || 0;
      const price = this.getValue('price') || 0;
      this.setValue('amount', quantity * price);
    }
  });
}
```

## 总结

这些示例展示了：
1. 标准列表查询页面的实现
2. 业务单据详情页面的实现
3. 带树形导航的列表页面
4. 表格内编辑功能

通过这些示例，您可以快速上手YJPL框架的开发。

## 下一步

- 学习 [常见问题解决](./11-troubleshooting.md)
- 返回 [概述](./01-overview.md)
# YJPL前端代码生成智能体 - 常见问题解决

## 编译错误

### Q1: 缺少 lang="ts" 导致的编译错误

**错误信息：**
```
Syntax Error: Unexpected token, expected ","
```

**原因：**
在 `.yjpl` 文件中使用 TypeScript 类型注解时，未在 `<script>` 标签中添加 `lang="ts"` 属性。

**解决方案：**
```xml
<!-- ✗ 错误 -->
<script>
export default class MyPage {
  handleSort(column: string, order: string) {  // 编译错误
    // ...
  }
}
</script>

<!-- ✓ 正确 -->
<script lang="ts">
export default class MyPage {
  handleSort(column: string, order: string) {  // 正确
    // ...
  }
}
</script>
```

### Q2: 模块找不到

**错误信息：**
```
Cannot find module 'yjpl-core'
```

**解决方案：**
1. 检查是否安装了依赖：`npm install`
2. 检查 package.json 中是否包含该依赖
3. 尝试重新安装：`npm install yjpl-core`
4. 清除缓存：`npm cache clean --force`

### Q3: TypeScript 类型错误

**错误信息：**
```
Property 'xxx' does not exist on type 'YYY'
```

**解决方案：**
1. 检查类型定义是否正确
2. 安装类型定义包：`npm install @types/yjpl-core`
3. 在 tsconfig.json 中配置类型路径

## 运行时错误

### Q4: this.bs is undefined

**错误信息：**
```
TypeError: Cannot read property 'getData' of undefined
```

**原因：**
Business 类未正确初始化。

**解决方案：**
```typescript
// ✓ 正确
export default class MyPage extends ListTemplate {
  constructor() {
    super();
    this.bs = new Business();  // 必须在构造函数中初始化
  }
}
```

### Q5: metaModel is undefined

**错误信息：**
```
TypeError: Cannot read property 'title' of undefined
```

**原因：**
数据未正确展开。

**解决方案：**
```typescript
// ✓ 正确
data() {
  return {
    ...this.bs.getData(),  // 使用展开运算符
    // 其他数据
  };
}
```

### Q6: 表格数据不显示

**可能原因：**
1. 数据格式不正确
2. 列配置与数据字段不匹配
3. 数据未正确加载

**解决方案：**
```typescript
// 1. 检查数据格式
console.log('表格数据:', this.dataModel.grid);

// 2. 检查列配置
console.log('列配置:', this.metaModel.grid.colModels);

// 3. 确保数据加载完成
async created() {
  this.dataModel.grid = await this.bs.getGridData();
  console.log('数据加载完成:', this.dataModel.grid);
}
```

## 组件问题

### Q7: 中台组件加载失败

**错误信息：**
```
Failed to load component
```

**解决方案：**
1. 检查包是否正确安装
2. 检查 vipAddress 配置是否正确
3. 检查 ecp 参数是否正确设置
4. 检查网络连接和API服务

```typescript
// 检查配置
<currency-select
  v-model="currencyId"
  :vip-address="/necp/mapp/metamodel"  // 确保路径正确
  :ecp="true">                          // 确保参数正确
</currency-select>
```

### Q8: QZZ表格编辑不生效

**可能原因：**
1. editType 配置错误
2. editable 设置为 false
3. 表格处于只读状态

**解决方案：**
```typescript
// 1. 检查列配置
colModels: [
  {
    name: 'amount',
    editType: 'number',  // 确保 editType 正确
    editable: true       // 确保可编辑
  }
]

// 2. 检查表格状态
const grid = this.$refs.grid.ui;
console.log('是否只读:', grid.isReadOnly());
grid.setReadOnly(false);  // 设置为可编辑
```

### Q9: 表格事件不触发

**可能原因：**
1. 事件绑定时机不对
2. 事件名称错误
3. this 指向问题

**解决方案：**
```typescript
// ✓ 正确：在 mounted 中绑定事件
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 使用箭头函数保持 this 指向
  grid.bind('onAfterEdit', (dataType, fieldName, value) => {
    this.handleEdit(fieldName, value);
  });
  
  // 或使用 bind
  grid.bind('onAfterEdit', function(dataType, fieldName, value) {
    // 这里的 this 指向 grid
  });
}
```

## 性能问题

### Q10: 页面加载慢

**可能原因：**
1. 一次性加载大量数据
2. 未使用虚拟滚动
3. 过多的深度监听

**解决方案：**
```typescript
// 1. 使用分页
grid: {
  pageSize: 20,
  pager: true
}

// 2. 使用虚拟滚动
grid: {
  virtual: true,
  virtualSize: 20
}

// 3. 优化监听
watch: {
  // ✗ 避免：监听整个对象
  dataModel: {
    handler() { },
    deep: true
  },
  
  // ✓ 推荐：只监听必要的字段
  'dataModel.query': {
    handler() { },
    deep: true
  }
}
```

### Q11: 表格操作卡顿

**解决方案：**
```typescript
// 使用批量更新
const grid = this.$refs.grid.ui;

grid.beginUpdate();
// 执行多个操作
grid.setValue('field1', value1);
grid.setValue('field2', value2);
grid.setValue('field3', value3);
grid.endUpdate(true);  // 最后统一刷新
```

## Mock数据问题

### Q12: Mock数据不生效

**可能原因：**
1. API路径不匹配
2. 请求方法不匹配
3. Mock配置未正确导入

**解决方案：**
```typescript
// 1. 检查API路径
doGet: {
  '/api/users': {  // 确保路径完全匹配
    data(param: any) {
      return [];
    }
  }
}

// 2. 检查请求方法
// 如果后端是POST，Mock也要用doPost

// 3. 确保Mock配置被导入
// main.ts
if (process.env.NODE_ENV === 'development') {
  import('./mock');
}
```

### Q13: Mock数据返回格式错误

**解决方案：**
```typescript
// ✓ 正确：直接返回业务数据
doGet: {
  '/api/users': {
    data(param: any) {
      return [
        { id: 1, name: '张三' },
        { id: 2, name: '李四' }
      ];
    }
  }
}

// ✗ 错误：不要包装成响应对象
doGet: {
  '/api/users': {
    data(param: any) {
      return {
        success: true,
        data: [...]  // 错误：多余的包装
      };
    }
  }
}
```

## 样式问题

### Q14: 样式不生效

**可能原因：**
1. 样式作用域问题
2. 样式优先级问题
3. 样式未正确引入

**解决方案：**
```vue
<!-- 1. 使用 scoped 避免样式污染 -->
<style lang="scss" scoped>
.my-class {
  color: red;
}
</style>

<!-- 2. 使用深度选择器修改组件样式 -->
<style lang="scss" scoped>
::v-deep .el-button {
  color: red;
}
</style>

<!-- 3. 提高样式优先级 -->
<style lang="scss" scoped>
.my-page .el-button {
  color: red !important;
}
</style>
```

### Q15: 布局错乱

**解决方案：**
```css
/* 1. 检查容器高度 */
.page-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

/* 2. 检查表格高度 */
.grid-container {
  flex: 1;
  overflow: hidden;
}

/* 3. 使用 Flex 布局 */
.layout {
  display: flex;
  height: 100vh;
}
```

## 路由问题

### Q16: 路由跳转失败

**解决方案：**
```typescript
// 1. 使用编程式导航
this.$router.push('/user/list');

// 2. 带参数跳转
this.$router.push({
  path: '/user/detail',
  query: { id: '123' }
});

// 3. 新窗口打开
const route = this.$router.resolve({
  path: '/user/detail',
  query: { id: '123' }
});
window.open(route.href, '_blank');
```

### Q17: 路由参数获取不到

**解决方案：**
```typescript
// 1. 在 created 中获取
async created() {
  const id = this.$route.query.id;
  if (id) {
    await this.loadData(id);
  }
}

// 2. 监听路由变化
watch: {
  '$route'(to, from) {
    if (to.query.id !== from.query.id) {
      this.loadData(to.query.id);
    }
  }
}
```

## 数据问题

### Q18: 数据更新后视图不更新

**原因：**
Vue 无法检测到数组或对象的某些变化。

**解决方案：**
```typescript
// ✗ 错误：直接修改数组索引
this.dataModel.grid[0] = newItem;

// ✓ 正确：使用 Vue.set
this.$set(this.dataModel.grid, 0, newItem);

// ✓ 正确：使用数组方法
this.dataModel.grid.splice(0, 1, newItem);

// ✗ 错误：直接添加对象属性
this.dataModel.newProp = value;

// ✓ 正确：使用 Vue.set
this.$set(this.dataModel, 'newProp', value);
```

### Q19: 表单验证不通过

**解决方案：**
```typescript
// 1. 检查验证规则
rules: {
  userName: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ]
}

// 2. 手动触发验证
this.$refs.form.validate((valid) => {
  if (valid) {
    // 验证通过
  } else {
    console.log('验证失败');
    return false;
  }
});

// 3. 清除验证
this.$refs.form.clearValidate();
```

## 调试技巧

### 使用Vue DevTools

1. 安装 Vue DevTools 浏览器扩展
2. 在开发环境中打开
3. 查看组件树和数据状态

### 使用console.log

```typescript
// 1. 查看数据
console.log('数据:', this.dataModel);

// 2. 查看方法调用
methods() {
  return {
    handleClick() {
      console.log('handleClick 被调用');
      // ...
    }
  };
}

// 3. 查看生命周期
created() {
  console.log('created');
}

mounted() {
  console.log('mounted');
}
```

### 使用debugger

```typescript
methods() {
  return {
    handleClick() {
      debugger;  // 代码会在这里暂停
      // ...
    }
  };
}
```

## 获取帮助

### 1. 查看文档
- [概述](./01-overview.md)
- [架构和核心概念](./02-architecture.md)
- [模板系统详解](./03-templates.md)

### 2. 查看示例
- [实战示例](./10-examples.md)

### 3. 搜索问题
- 在项目文档中搜索关键词
- 查看相关组件的API文档

### 4. 联系支持
- 提交问题到项目仓库
- 联系技术支持团队

## 总结

遇到问题时的解决步骤：
1. 查看错误信息，确定问题类型
2. 检查代码配置是否正确
3. 查看浏览器控制台的错误信息
4. 使用调试工具定位问题
5. 查阅相关文档
6. 搜索类似问题的解决方案
7. 寻求技术支持

## 返回

- 返回 [概述](./01-overview.md)
- 查看 [最佳实践](./09-best-practices.md)
