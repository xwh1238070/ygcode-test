# YJPL前端代码生成智能体 - 精简指南

## 1. 快速概览

### 核心特性
- **三文件架构**：index.yjpl（视图）+ Business.ts（逻辑）+ Model.ts（数据模型）
- **模板继承**：list-template（列表查询）、ywdj（业务单据）
- **三层数据模型**：metaModel（配置）、dataModel（数据）、stateModel（状态）
- **中台组件**：币种、枚举、管理对象等选择控件
- **Mock支持**：GET/POST请求拦截、动态数据生成

### 技术栈
- YJPL 8.5.0+ / Vue2 / TypeScript
- yjpl-ui组件库（68个组件）
- yjpl-qzz表格组件

## 2. 核心架构

### 三层数据模型

```typescript
// Model.ts
export const metaModel = {  // 静态配置
  title: '页面标题',
  query: [...],  // 查询字段
  grid: {...},   // 表格配置
  btns: [...]    // 按钮配置
};

export const dataModel = {  // 动态数据
  query: {},     // 查询条件
  grid: [],      // 表格数据
  pageTotal: 0   // 分页信息
};

export const stateModel = {  // 状态控制
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DISABLED
};
```

### Business类结构

```typescript
// Business.ts
import { YJBusiness } from 'yjpl-core';
import Model from './Model';

export default class YourBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  async getQueryData() { return {}; }
  async getGridData(pageSize?, pageIndex?) { return []; }
  async getTotalRecord() { return 0; }
  
  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }
}
```

### index.yjpl结构

```xml
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
    <!-- plugins扩展 -->
  </page>
</template>

<script lang="ts">  <!-- ⚠️ 必须添加 lang="ts" -->
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
        add: { click: this.handleAdd }
      }
    };
  }

  async created() {
    this.dataModel.grid = await this.bs.getGridData();
  }

  methods() {
    return {
      handleAdd() { /* ... */ }
    };
  }
}
</script>
```

## 3. 模板系统

### list-template（列表查询）

**适用场景**：数据列表、查询筛选、多视图切换、树形导航

**核心区域**：
- `.query-search-panel` - 查询面板
- `.query-toolbar` - 工具栏
- `.query-grid-body` - 表格区域
- `.query-tree` - 树形导航

**配置示例**：
```typescript
metaModel: {
  showTree: true,  // 显示树形导航
  query: [
    { name: 'userName', label: '用户名', dataType: 'string' }
  ],
  grid: {
    colNames: ['ID', '用户名', '邮箱'],
    colModels: [
      { name: 'id', width: 80 },
      { name: 'userName', width: 150 }
    ],
    pageSize: 20,
    pager: true
  },
  btns: [
    { name: 'add', text: '新增', type: 'primary', icon: 'yj-p-add' }
  ]
}
```

### ywdj（业务单据）

**适用场景**：单据详情、表单+子表、审批流程、附件管理

**核心区域**：
- `.bill-form` - 表单区域
- `.bill-info` - 详情区域
- `.bill-upload` - 附件区域
- `.bill-workflow` - 流程区域

**配置示例**：
```typescript
metaModel: {
  cols: 4,  // 表单列数
  form: [
    { name: 'orderNo', label: '订单号', dataType: 'string', required: true, span: 2 }
  ],
  grid: [
    { title: '订单明细', columns: [...] }
  ],
  toolbar: [
    { label: '保存', type: 'primary', name: 'save' }
  ]
}
```

### plugins扩展

```xml
<!-- 替换 -->
<plugins type="replace" target=".query-toolbar">
  <div slot="toolbar">自定义工具栏</div>
</plugins>

<!-- 追加 -->
<plugins type="append" target=".query-body">
  <custom-component></custom-component>
</plugins>

<!-- 移除 -->
<plugins type="remove" target=".query-search-panel"></plugins>
```

## 4. 数据模型配置

### 查询字段类型

```typescript
// 文本
{ name: 'userName', label: '用户名', dataType: 'string' }

// 数字
{ name: 'age', dataType: 'number', min: 0, max: 150 }

// 日期
{ name: 'date', dataType: 'date', format: 'yyyy-MM-dd' }

// 下拉
{ name: 'status', dataType: 'combobox', options: [
  { label: '启用', value: 1 }
]}

// 多选
{ name: 'roles', dataType: 'combobox', multiple: true, options: [...] }
```

### 表格列配置

```typescript
grid: {
  colModels: [
    { name: 'id', caption: 'ID', width: 80, align: 'center', sortable: true },
    { name: 'amount', caption: '金额', formatType: 'money', scale: 2 },
    { name: 'date', caption: '日期', formatType: 'date', format: 'yyyy-MM-dd' },
    { name: 'status', caption: '状态', formatType: 'enum', enumData: {
      1: '启用', 0: '禁用'
    }}
  ]
}
```

### 状态控制

```typescript
import { YJState } from 'yjpl-core';

stateModel: {
  add: YJState.STATE.DEFAULT,    // 可用
  edit: YJState.STATE.DISABLED,  // 禁用
  delete: YJState.STATE.LOADING, // 加载中
  query: YJState.STATE.HIDDEN    // 隐藏
}
```

## 5. QZZ表格组件

### QzzGrid（编辑表格）

```typescript
gridOption: {
  colModels: [
    { name: 'name', editType: 'text', width: 150, required: true },
    { name: 'amount', editType: 'number', scale: 2, sum: true },
    { name: 'date', editType: 'date' },
    { name: 'status', editType: 'comboBox', data: [
      { id: '1', text: '启用' }
    ]},
    { name: 'product', editType: 'buttonEdit', buttonText: '选择' }
  ]
}
```

**常用方法**：
```javascript
const grid = this.$refs.grid.ui;

grid.value();              // 获取数据
grid.append();             // 添加行
grid.delRecord();          // 删除行
grid.getChangeSet();       // 获取变更集
grid.setValue('field', v); // 设置值
grid.refresh();            // 刷新
```

**常用事件**：
```javascript
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 编辑后事件
  grid.bind('onAfterEdit', function(dataType, fieldName, value) {
    if (fieldName === 'quantity' || fieldName === 'price') {
      this.setValue('amount', this.getValue('quantity') * this.getValue('price'));
    }
  });
}
```

## 6. 中台组件集成

### 通用使用模式

```javascript
// 1. 引入组件
import CurrencySelect from 'jt.tswan.components/packages/currency-select/currency.yjpl';

// 2. 注册
components() {
  return { 'currency-select': CurrencySelect };
}

// 3. 使用
<currency-select
  v-model="currencyId"
  :vip-address="/necp/mapp/metamodel"
  :ecp="true">
</currency-select>
```

### 常用控件

| 控件 | 包名 | 用途 |
|------|------|------|
| 币种选择 | jt.tswan.components | 选择币种 |
| 枚举选择 | jt.tswan.components | 选择枚举值 |
| 管理对象 | jt.tswan.cp.dcc.biz.web | 选择管理对象 |
| 用户选择 | jt.tswan.components | 选择用户 |
| 组织选择 | jt.tswan.components | 选择组织 |

## 7. Mock数据配置

```typescript
// src/mock/index.ts
export default {
  doGet: {
    '/api/users': {
      data(param: any) {
        return [
          { id: 1, name: '张三' },
          { id: 2, name: '李四' }
        ];
      }
    }
  },
  
  doPost: {
    '/api/user/add': {
      data(param: any) {
        return {
          success: true,
          data: { id: Date.now(), ...param }
        };
      }
    }
  }
};
```

**分页数据模拟**：
```javascript
data(param: any) {
  const { pageSize = 20, pageNum = 1 } = param;
  const allData = [...]; // 生成数据
  const start = (pageNum - 1) * pageSize;
  return {
    list: allData.slice(start, start + pageSize),
    total: allData.length
  };
}
```

## 8. 最佳实践

### ✓ 推荐做法

1. **分离配置和数据**
```typescript
export const metaModel = { /* 配置 */ };
export const dataModel = { /* 数据 */ };
export const stateModel = { /* 状态 */ };
```

2. **使用TypeScript类型**
```typescript
interface QueryData {
  userName: string;
  status: number | null;
}
```

3. **性能优化**
```typescript
// 虚拟滚动
grid: { virtual: true, virtualSize: 20 }

// 批量更新
grid.beginUpdate();
// 多个操作
grid.endUpdate(true);
```

### ✗ 避免做法

1. **不要混合配置和数据**
2. **不要在metaModel中存储动态数据**
3. **不要直接修改模板文件**
4. **不要过度自定义**

## 9. 常见问题

### Q1: 缺少 lang="ts" 导致编译错误
```xml
<!-- ✓ 正确 -->
<script lang="ts">
export default class MyPage {
  handleSort(column: string, order: string) { }
}
</script>
```

### Q2: this.bs is undefined
```typescript
// ✓ 在构造函数中初始化
constructor() {
  super();
  this.bs = new Business();
}
```

### Q3: 表格数据不显示
```typescript
// 检查数据格式和列配置
console.log('数据:', this.dataModel.grid);
console.log('列配置:', this.metaModel.grid.colModels);
```

### Q4: Mock数据不生效
```typescript
// 检查路径和方法是否匹配
doGet: {
  '/api/users': { /* ... */ }  // 确保路径完全匹配
}
```

## 10. 完整示例

### 用户管理页面

**Model.ts**：
```typescript
import { YJState } from 'yjpl-core';

export const metaModel = {
  title: '用户管理',
  query: [
    { name: 'userName', label: '用户名', dataType: 'string' }
  ],
  grid: {
    colNames: ['ID', '用户名', '邮箱', '状态'],
    colModels: [
      { name: 'id', width: 80 },
      { name: 'userName', width: 150 },
      { name: 'email', width: 200 },
      { name: 'status', width: 100, formatType: 'enum', enumData: {
        1: '启用', 0: '禁用'
      }}
    ],
    pageSize: 20,
    pager: true
  },
  btns: [
    { name: 'add', text: '新增', type: 'primary', icon: 'yj-p-add' }
  ]
};

export const dataModel = {
  query: { userName: '' },
  grid: [],
  pageTotal: 0
};

export const stateModel = {
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DISABLED
};

export default { metaModel, dataModel, stateModel };
```

**Business.ts**：
```typescript
import { YJBusiness } from 'yjpl-core';
import Model from './Model';

export default class UserBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  async getGridData(pageSize = 20, pageIndex = 1) {
    // 调用API
    return [];
  }

  async getTotalRecord() {
    return 0;
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

**index.yjpl**：
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
        query: { click: this.handleQuery }
      }
    };
  }

  async created() {
    this.dataModel.grid = await this.bs.getGridData();
    this.dataModel.pageTotal = await this.bs.getTotalRecord();
  }

  methods() {
    return {
      handleAdd() {
        this.$message('新增功能');
      },
      async handleQuery() {
        this.dataModel.grid = await this.bs.getGridData();
        this.$refs.grid.ui.refresh();
      }
    };
  }
}
</script>
```

## 11. 快速参考

### 文件结构
```
your-page/
├── index.yjpl      # 视图（extends模板）
├── Business.ts     # 业务逻辑
└── Model.ts        # 数据模型（metaModel + dataModel + stateModel）
```

### 开发流程
```
1. 分析需求 → 选择模板
2. 创建Model.ts → 定义数据模型
3. 创建Business.ts → 实现业务逻辑
4. 创建index.yjpl → 继承模板 + plugins扩展
5. 配置Mock数据 → 测试验证
```

### 关键点检查清单
- [ ] `<script lang="ts">` 已添加
- [ ] Business类已在constructor中初始化
- [ ] 数据模型已正确展开 `...this.bs.getData()`
- [ ] 事件已绑定到eventsModel
- [ ] Mock数据路径和方法已匹配
- [ ] 表格列配置与数据字段一致

---

**版本信息**：
- 智能体版本：3.0.0
- YJPL版本：8.5.0+
- 最后更新：2025-12-04

**获取完整文档**：查看 `YJPL-Complete-Guide-Full.md`
