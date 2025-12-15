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
