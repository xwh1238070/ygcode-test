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
