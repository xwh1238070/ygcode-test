import { YJBusiness } from 'yjpl-core';

class ExpenseReimbursementBs extends YJBusiness {
  constructor() {
    super();
    this.dataModel = {
      // 经办人信息
      billUser: {
        name: '张晓玲',
        dept: 'DAP研发中心-产品管理部',
        operator: '张小玲，远光软件股份有限公司 DAP研发中心-产品管理部'
      },
      // 主表单数据
      businessForm: {
        reimbursementNo: 'BX20231215001', // 报销单号
        applicant: '', // 申请人
        applyDate: new Date().toISOString().split('T')[0], // 申请日期
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
      expenseDetails: [
        {
          id: 1,
          expenseCategory: '交通费', // 费用类别
          expenseDescription: '北京至上海高铁票', // 费用说明
          expenseDate: '2023-12-10', // 费用日期
          amount: 553.00, // 金额
          receiptNo: 'TX123456', // 票据号
          receiptCount: 1, // 票据数量
          remark: '商务出差' // 备注
        },
        {
          id: 2,
          expenseCategory: '住宿费',
          expenseDescription: '上海宾馆住宿',
          expenseDate: '2023-12-11',
          amount: 380.00,
          receiptNo: 'ZD789012',
          receiptCount: 1,
          remark: '商务出差住宿'
        }
      ],
      // 审批流程数据
      workflowData: [
        {
          status: 'pass',
          finishTime: '2023-12-15 09:30:00',
          organizationName: '远光软件股份有限公司',
          rule: '部门经理',
          person: '王经理',
          comment: '同意报销',
          waitTime: '停留0.5天'
        },
        {
          status: 'wait',
          finishTime: '',
          organizationName: '远光软件股份有限公司',
          rule: '财务审核',
          person: '待处理',
          comment: '',
          waitTime: '等待处理'
        }
      ],
      // 财务审核信息
      financeInfo: {
        approver: '', // 财务审核人
        approveTime: '', // 审核时间
        paymentAmount: 0, // 实付金额
        paymentDate: '', // 支付日期
        remarks: '' // 审核备注
      }
    };
    
    this.metaModel = {
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
    
    this.stateModel = {
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
    
    this.pageModel = {
      title: '费用报销单',
      cols: 3,
      date: new Date().toISOString().split('T')[0],
      qrcode: 'BX20231215001',
      code: 'BX20231215001',
      area: {
        upload: {
          title: '附件信息',
        },
      },
      searchPanel: {},
      // 主表单字段配置
      form: [
        {
          required: true,
          name: 'reimbursementNo',
          dataType: 'text',
          label: '报销单号',
          readonly: true
        },
        {
          required: true,
          name: 'applicant',
          dataType: 'entity-select',
          label: '申请人',
          options: {
            entityType: 'user',
            entityName: '申请人'
          }
        },
        {
          required: true,
          name: 'applyDate',
          dataType: 'date',
          label: '申请日期'
        },
        {
          required: true,
          name: 'department',
          dataType: 'entity-select',
          label: '部门',
          options: {
            entityType: 'department',
            entityName: '部门'
          }
        },
        {
          required: true,
          name: 'expenseType',
          dataType: 'combobox',
          label: '费用类型',
          options: [
            { value: 'business', label: '差旅费' },
            { value: 'office', label: '办公费' },
            { value: 'communication', label: '通讯费' },
            { value: 'transport', label: '交通费' },
            { value: 'entertainment', label: '业务招待费' },
            { value: 'training', label: '培训费' },
            { value: 'other', label: '其他' }
          ]
        },
        {
          required: true,
          name: 'totalAmount',
          dataType: 'number',
          label: '总金额',
          readonly: true
        },
        {
          required: true,
          name: 'currency',
          dataType: 'combobox',
          label: '币种',
          options: [
            { value: 'CNY', label: '人民币' },
            { value: 'USD', label: '美元' },
            { value: 'EUR', label: '欧元' }
          ]
        },
        {
          required: true,
          name: 'paymentMethod',
          dataType: 'combobox',
          label: '支付方式',
          options: [
            { value: 'cash', label: '现金' },
            { value: 'bank', label: '银行转账' },
            { value: 'check', label: '支票' }
          ]
        },
        {
          required: false,
          name: 'bankAccount',
          dataType: 'text',
          label: '银行账号',
          visible: { field: 'paymentMethod', value: 'bank' }
        },
        {
          required: false,
          name: 'emergencyContact',
          dataType: 'text',
          label: '紧急联系人'
        },
        {
          required: false,
          name: 'description',
          dataType: 'textarea',
          label: '报销说明',
          cols: 3
        }
      ],
      // 费用明细表格配置
      grid: {
        title: '费用明细',
        defaultShow: 'grid',
        showGrid: true,
        showList: true,
        showCard: true,
        isQueryGrid: false,
        height: 'auto',
        cardOption: {
          formatOption: {
            title: 'expenseCategory',
            subTitle: 'expenseDescription',
            money: 'amount',
            date: 'expenseDate'
          }
        },
        option: {
          colNames: ['费用类别', '费用说明', '费用日期', '金额', '票据号', '票据数量', '备注'],
          autoEcpWidth: true,
          absolute: false,
          suitHeight: true,
          Align: 'alTop',
          maxRow: 20,
          colModels: [
            {
              editType: 'combobox',
              caption: '费用类别',
              name: 'expenseCategory',
              required: true,
              options: [
                { value: '交通费', label: '交通费' },
                { value: '住宿费', label: '住宿费' },
                { value: '餐费', label: '餐费' },
                { value: '办公费', label: '办公费' },
                { value: '通讯费', label: '通讯费' },
                { value: '业务招待费', label: '业务招待费' },
                { value: '培训费', label: '培训费' },
                { value: '其他', label: '其他' }
              ]
            },
            {
              editType: 'text',
              caption: '费用说明',
              name: 'expenseDescription',
              required: true,
              width: 200
            },
            {
              editType: 'date',
              caption: '费用日期',
              name: 'expenseDate',
              required: true,
              width: 120
            },
            {
              editType: 'number',
              caption: '金额',
              name: 'amount',
              required: true,
              width: 100,
              formatter: 'currency'
            },
            {
              editType: 'text',
              caption: '票据号',
              name: 'receiptNo',
              width: 120
            },
            {
              editType: 'number',
              caption: '票据数量',
              name: 'receiptCount',
              width: 80
            },
            {
              editType: 'text',
              caption: '备注',
              name: 'remark',
              width: 200
            }
          ]
        }
      },
      // 标题栏按钮
      titleBtns: [
        {
          label: '导出',
          name: 'export',
          icon: 'yj-p-export'
        },
        {
          label: '打印',
          name: 'print',
          icon: 'yj-p-print'
        },
        {
          label: '流程图',
          name: 'flowchart',
          icon: 'yj-p-search'
        },
        {
          label: '流程轨迹',
          name: 'workflow',
          icon: 'yj-p-search'
        }
      ],
      // 工具栏按钮
      toolbar: [
        {
          label: '保存',
          name: 'save',
          type: 'primary',
          icon: 'yj-p-save'
        },
        {
          label: '提交',
          name: 'submit',
          type: 'success',
          icon: 'yj-p-search'
        },
        {
          label: '删除',
          name: 'delete',
          type: 'danger',
          icon: 'yj-p-delete'
        },
        {
          label: '关闭',
          name: 'close',
          icon: 'yj-p-search'
        }
      ],
      // 费用明细操作按钮
      btns: [
        {
          label: '新增',
          name: 'add',
          icon: 'yj-p-add'
        },
        {
          label: '修改',
          name: 'edit',
          icon: 'yj-p-edit'
        },
        {
          label: '复制',
          name: 'copy',
          icon: 'yj-p-search'
        },
        {
          label: '删除',
          name: 'delete',
          action: 'delete',
          icon: 'yj-p-delete'
        },
        {
          label: '导入',
          name: 'import',
          icon: 'yj-p-import'
        },
        {
          label: '导出',
          name: 'export',
          icon: 'yj-p-export'
        }
      ],
      // 锚点配置
      anchor: [
        {
          title: '报销信息',
          id: 'anchorA'
        },
        {
          title: '费用明细',
          id: 'anchorB'
        },
        {
          title: '附件信息',
          id: 'anchorC'
        },
        {
          title: '审批轨迹',
          id: 'anchorD'
        },
        {
          title: '财务审核',
          id: 'anchorE'
        }
      ]
    };
  }

  getData() {
    return {
      dataModel: this.dataModel,
      metaModel: this.metaModel,
      stateModel: this.stateModel,
      pageModel: this.pageModel
    };
  }

  // 业务方法
  // 保存报销单
  saveReimbursement() {
    // 计算总金额
    this.calculateTotalAmount();
    // 保存逻辑
    console.log('保存报销单');
    return true;
  }

  // 提交报销单
  submitReimbursement() {
    // 计算总金额
    this.calculateTotalAmount();
    // 设置状态为已提交
    this.dataModel.businessForm.status = 'submitted';
    // 提交逻辑
    console.log('提交报销单');
    return true;
  }

  // 删除报销单
  deleteReimbursement() {
    // 删除逻辑
    console.log('删除报销单');
    return true;
  }

  // 计算总金额
  calculateTotalAmount() {
    let total = 0;
    this.dataModel.expenseDetails.forEach(item => {
      total += parseFloat(item.amount) || 0;
    });
    this.dataModel.businessForm.totalAmount = total;
    return total;
  }

  // 添加费用明细
  addExpenseDetail() {
    const newDetail = {
      id: Date.now(),
      expenseCategory: '',
      expenseDescription: '',
      expenseDate: new Date().toISOString().split('T')[0],
      amount: 0,
      receiptNo: '',
      receiptCount: 1,
      remark: ''
    };
    this.dataModel.expenseDetails.push(newDetail);
    return newDetail;
  }

  // 编辑费用明细
  editExpenseDetail(id, data) {
    const index = this.dataModel.expenseDetails.findIndex(item => item.id === id);
    if (index !== -1) {
      this.dataModel.expenseDetails[index] = { ...this.dataModel.expenseDetails[index], ...data };
      return true;
    }
    return false;
  }

  // 删除费用明细
  deleteExpenseDetail(id) {
    const index = this.dataModel.expenseDetails.findIndex(item => item.id === id);
    if (index !== -1) {
      this.dataModel.expenseDetails.splice(index, 1);
      this.calculateTotalAmount(); // 重新计算总金额
      return true;
    }
    return false;
  }

  // 审批报销单
  approveReimbursement(comment) {
    // 添加审批记录
    const workflow = {
      status: 'pass',
      finishTime: new Date().toLocaleString(),
      organizationName: '远光软件股份有限公司',
      rule: '财务审核',
      person: '李会计',
      comment: comment || '同意报销',
      waitTime: '停留0.1天'
    };
    this.dataModel.workflowData.unshift(workflow);
    // 更新状态
    this.dataModel.businessForm.status = 'approved';
    return true;
  }

  // 拒绝报销单
  rejectReimbursement(comment) {
    // 添加拒绝记录
    const workflow = {
      status: 'refuse',
      finishTime: new Date().toLocaleString(),
      organizationName: '远光软件股份有限公司',
      rule: '财务审核',
      person: '李会计',
      comment: comment || '报销信息不完整',
      waitTime: '停留0.1天'
    };
    this.dataModel.workflowData.unshift(workflow);
    // 更新状态
    this.dataModel.businessForm.status = 'rejected';
    return true;
  }
}

export default ExpenseReimbursementBs;