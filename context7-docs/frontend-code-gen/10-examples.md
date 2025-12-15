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
