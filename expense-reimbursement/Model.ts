// 费用报销单数据模型
export class ExpenseReimbursementModel {
  // 元数据模型（配置信息）
  metaModel = {
    // 主表单配置
    billForm: {
      title: '报销信息',
      collapsible: true
    },
    // 费用明细配置
    billInfo: {
      title: '费用明细',
      collapsible: true
    },
    // 附件配置
    billUpload: {
      title: '附件信息',
      collapsible: true
    },
    // 审批流程配置
    billWorkflow: {
      title: '审批轨迹',
      collapsible: true
    },
    // 财务审核配置
    billFinance: {
      title: '财务审核',
      collapsible: true
    }
  };
  
  // 数据模型（业务数据）
  dataModel = {
    // 经办人信息
    billUser: {
      name: '',
      dept: '',
      operator: ''
    },
    // 主表单数据
    businessForm: {
      reimbursementNo: '', // 报销单号
      applicant: '', // 申请人
      applyDate: '', // 申请日期
      department: '', // 部门
      expenseType: '', // 费用类型
      totalAmount: 0, // 总金额
      currency: 'CNY', // 币种
      paymentMethod: '', // 支付方式
      bankAccount: '', // 银行账号
      emergencyContact: '', // 紧急联系人
      description: '', // 报销说明
      status: 'draft' // 状态：draft草稿, submitted已提交, approved已通过, rejected已拒绝
    },
    // 费用明细数据
    expenseDetails: [],
    // 审批流程数据
    workflowData: [],
    // 财务审核信息
    financeInfo: {
      approver: '', // 财务审核人
      approveTime: '', // 审核时间
      paymentAmount: 0, // 实付金额
      paymentDate: '', // 支付日期
      remarks: '' // 审核备注
    }
  };
  
  // 状态模型（控制状态）
  stateModel = {
    // 表单状态
    formState: {
      isEditing: false, // 是否编辑状态
      isReadOnly: false, // 是否只读
      canSave: true, // 是否可保存
      canSubmit: true, // 是否可提交
      canDelete: true // 是否可删除
    },
    // 费用明细状态
    gridState: {
      canAdd: true, // 是否可添加
      canEdit: true, // 是否可编辑
      canDelete: true, // 是否可删除
      canImport: true, // 是否可导入
      canExport: true // 是否可导出
    },
    // 审批状态
    workflowState: {
      canApprove: false, // 是否可审批
      canReject: false, // 是否可拒绝
      canTransfer: false, // 是否可转办
      canViewFlowChart: true // 是否可查看流程图
    }
  };
}

export default ExpenseReimbursementModel;