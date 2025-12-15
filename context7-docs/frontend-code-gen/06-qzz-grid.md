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
