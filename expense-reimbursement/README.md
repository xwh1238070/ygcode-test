# 费用报销单页面

## 功能概述

费用报销单页面是一个基于YJPL框架开发的业务单据页面，用于员工提交费用报销申请，包括填写报销信息、添加费用明细、上传附件、查看审批流程等功能。

## 技术架构

- **框架**: YJPL 8.5.0+
- **模板类型**: 单据模板 (ywdj)
- **编程语言**: TypeScript
- **UI组件**: yjpl-ui组件库

## 文件结构

```
expense-reimbursement/
├── index.yjpl              # 主模板文件
├── Business.ts             # 业务逻辑类
├── Model.ts                # 数据模型
├── mock.js                 # Mock数据配置
└── README.md               # 使用说明
```

## 功能模块

### 1. 报销信息
- 报销单号（自动生成）
- 申请人
- 申请日期
- 部门
- 费用类型
- 总金额（自动计算）
- 币种
- 支付方式
- 银行账号
- 紧急联系人
- 报销说明
- 状态（草稿/已提交/已通过/已拒绝）

### 2. 费用明细
- 费用类别
- 费用说明
- 费用日期
- 金额
- 票据号
- 票据数量
- 备注

支持功能：
- 新增费用明细
- 编辑费用明细
- 删除费用明细
- 复制费用明细
- 导入/导出费用明细
- 表格/列表/卡片视图切换

### 3. 附件管理
- 上传报销凭证
- 查看附件列表
- 下载附件
- 删除附件

### 4. 审批轨迹
- 查看审批流程
- 查看每个节点的处理人、处理时间、处理意见
- 节点状态（待处理/通过/拒绝）

### 5. 财务审核
- 财务审核人
- 审核时间
- 实付金额
- 支付日期
- 审核备注

## 数据模型

### 元数据模型 (MetaModel)
配置各区域的显示属性，如标题、是否可折叠等。

### 数据模型 (DataModel)
存储业务数据，包括经办人信息、报销信息、费用明细、审批轨迹、财务审核信息等。

### 状态模型 (StateModel)
控制页面各组件的交互状态，如是否可编辑、是否可提交、是否可审批等。

## 主要方法

### 业务方法
- `saveReimbursement()` - 保存报销单
- `submitReimbursement()` - 提交报销单
- `deleteReimbursement()` - 删除报销单
- `calculateTotalAmount()` - 计算总金额
- `addExpenseDetail()` - 添加费用明细
- `editExpenseDetail()` - 编辑费用明细
- `deleteExpenseDetail()` - 删除费用明细
- `approveReimbursement()` - 审批报销单
- `rejectReimbursement()` - 拒绝报销单

### 视图方法
- `getBillStatusText()` - 获取单据状态文本
- `getBillStatusClass()` - 获取单据状态样式类
- `getPaymentMethodText()` - 获取支付方式文本

## Mock数据

项目提供了完整的Mock数据配置，支持：
- 获取报销单详情
- 保存报销单
- 提交报销单
- 删除报销单
- 审批/拒绝报销单
- 获取部门/用户列表
- 文件上传/下载
- 打印/导出报销单
- 获取流程图

## 使用说明

### 1. 基本使用
```typescript
// 引入页面组件
import ExpenseReimbursement from './expense-reimbursement/index.yjpl';

// 在路由中配置
{
  path: '/expense-reimbursement/:id',
  component: ExpenseReimbursement,
  meta: {
    title: '费用报销单'
  }
}
```

### 2. 参数传递
- 路由参数：`id` - 报销单ID
- 查询参数：`type` - 页面类型（view/edit/add）

### 3. 事件处理
页面中的按钮点击事件会自动调用Business类中对应的方法，如：
- 保存按钮 → `saveReimbursement()`
- 提交按钮 → `submitReimbursement()`
- 删除按钮 → `deleteReimbursement()`

### 4. 权限控制
通过stateModel控制各组件的显示和交互状态，根据用户权限设置：
- `formState.canSave` - 是否可保存
- `formState.canSubmit` - 是否可提交
- `formState.canDelete` - 是否可删除
- `workflowState.canApprove` - 是否可审批
- `workflowState.canReject` - 是否可拒绝

## 自定义扩展

### 1. 添加新字段
在pageModel.form中添加新的表单字段配置：
```typescript
{
  required: true,
  name: 'newField',
  dataType: 'text',
  label: '新字段',
  // 其他配置...
}
```

### 2. 添加新按钮
在pageModel.toolbar中添加新的工具栏按钮：
```typescript
{
  label: '新功能',
  name: 'newFeature',
  icon: 'yj-p-add',
  type: 'primary'
}
```

### 3. 添加新业务方法
在Business类中添加新的业务处理方法：
```typescript
// 新功能处理方法
handleNewFeature() {
  // 处理逻辑
  console.log('执行新功能');
  return true;
}
```

## 常见问题

### Q: 如何修改费用明细的字段？
A: 在pageModel.grid.option.colModels中修改或新增列配置。

### Q: 如何添加新的审批节点？
A: 在dataModel.workflowData中添加新的审批节点对象。

### Q: 如何自定义审批状态？
A: 在getBillStatusText和getBillStatusClass方法中添加新的状态映射。

### Q: 如何集成其他中台组件？
A: 在index.yjpl中使用plugins插入中台组件，并在Business类中处理相关业务逻辑。

## 更新日志

- v1.0.0 (2023-12-15): 初始版本，实现基本的费用报销功能
  - 报销信息填写
  - 费用明细管理
  - 附件上传
  - 审批流程
  - 财务审核