// 费用报销单Mock数据

// 获取报销单详情
Mock.mock('/api/expense-reimbursement/detail', 'get', {
  'code': 200,
  'message': '获取成功',
  'data': {
    // 经办人信息
    'billUser': {
      'name': '张晓玲',
      'dept': 'DAP研发中心-产品管理部',
      'operator': '张小玲，远光软件股份有限公司 DAP研发中心-产品管理部'
    },
    // 主表单数据
    'businessForm': {
      'reimbursementNo': 'BX20231215001',
      'applicant': '张晓玲',
      'applyDate': '2023-12-15',
      'department': 'DAP研发中心-产品管理部',
      'expenseType': '差旅费',
      'totalAmount': 933.00,
      'currency': 'CNY',
      'paymentMethod': 'bank',
      'bankAccount': '6225881234567890',
      'emergencyContact': '李明 13800138000',
      'description': '上海出差费用报销',
      'status': 'submitted'
    },
    // 费用明细数据
    'expenseDetails|2-5': [{
      'id|+1': 1,
      'expenseCategory': '@pick(["交通费", "住宿费", "餐费", "办公费", "通讯费", "业务招待费", "培训费", "其他"])',
      'expenseDescription': '@cparagraph(1, 3)',
      'expenseDate': '@date("yyyy-MM-dd")',
      'amount|100-1000.2': 2,
      'receiptNo': '@string("upper", 10)',
      'receiptCount|1-5': 1,
      'remark': '@cparagraph(1, 2)'
    }],
    // 审批流程数据
    'workflowData|2-4': [{
      'status|1': ['pass', 'wait', 'refuse'],
      'finishTime': function() {
        return this.status === 'wait' ? '' : '@datetime("yyyy-MM-dd HH:mm:ss")';
      },
      'organizationName': '远光软件股份有限公司',
      'rule': '@pick(["部门经理", "财务审核", "总经理", "董事长"])',
      'person': function() {
        return this.status === 'wait' ? '待处理' : '@cname';
      },
      'comment': function() {
        return this.status === 'wait' ? '' : '@cparagraph(1, 2)';
      },
      'waitTime': '@pick(["停留0.5天", "停留1天", "停留2天", "等待处理"])'
    }],
    // 财务审核信息
    'financeInfo': {
      'approver': '',
      'approveTime': '',
      'paymentAmount': 0,
      'paymentDate': '',
      'remarks': ''
    }
  }
});

// 保存报销单
Mock.mock('/api/expense-reimbursement/save', 'post', {
  'code': 200,
  'message': '保存成功',
  'data': {
    'reimbursementNo': '@string("BX", 15)',
    'status': 'draft'
  }
});

// 提交报销单
Mock.mock('/api/expense-reimbursement/submit', 'post', {
  'code': 200,
  'message': '提交成功',
  'data': {
    'reimbursementNo': '@string("BX", 15)',
    'status': 'submitted'
  }
});

// 删除报销单
Mock.mock(/\/api\/expense-reimbursement\/delete\?id=.*/, 'delete', {
  'code': 200,
  'message': '删除成功',
  'data': null
});

// 审批报销单
Mock.mock('/api/expense-reimbursement/approve', 'post', {
  'code': 200,
  'message': '审批成功',
  'data': {
    'status': 'approved'
  }
});

// 拒绝报销单
Mock.mock('/api/expense-reimbursement/reject', 'post', {
  'code': 200,
  'message': '操作成功',
  'data': {
    'status': 'rejected'
  }
});

// 获取部门列表
Mock.mock('/api/org/department/list', 'get', {
  'code': 200,
  'message': '获取成功',
  'data|5-10': [{
    'id|+1': 1,
    'name': '@cword(5, 10)',
    'code': '@string("upper", 5)'
  }]
});

// 获取用户列表
Mock.mock('/api/org/user/list', 'get', {
  'code': 200,
  'message': '获取成功',
  'data|10-20': [{
    'id|+1': 1,
    'name': '@cname',
    'code': '@string("upper", 8)',
    'deptId': '@integer(1, 10)',
    'deptName': '@cword(5, 10)',
    'phone': '@string("1", 11)'
  }]
});

// 文件上传
Mock.mock('/api/file/upload', 'post', {
  'code': 200,
  'message': '上传成功',
  'data': {
    'fileId': '@guid',
    'fileName': '@cword(5, 10).@pick(["jpg", "pdf", "doc", "docx", "xls", "xlsx"])',
    'fileSize': '@integer(1000, 100000)',
    'filePath': '/upload/@date("yyyyMMdd")/@guid.@pick(["jpg", "pdf", "doc", "docx", "xls", "xlsx"])',
    'uploadTime': '@datetime("yyyy-MM-dd HH:mm:ss")'
  }
});

// 获取附件列表
Mock.mock('/api/file/list', 'get', {
  'code': 200,
  'message': '获取成功',
  'data|2-5': [{
    'id|+1': 1,
    'fileId': '@guid',
    'fileName': '@cword(5, 10).@pick(["jpg", "pdf", "doc", "docx", "xls", "xlsx"])',
    'fileSize': '@integer(1000, 100000)',
    'filePath': '/upload/@date("yyyyMMdd")/@guid.@pick(["jpg", "pdf", "doc", "docx", "xls", "xlsx"])',
    'uploadTime': '@datetime("yyyy-MM-dd HH:mm:ss")',
    'uploader': '@cname'
  }]
});

// 删除附件
Mock.mock(/\/api\/file\/delete\?id=.*/, 'delete', {
  'code': 200,
  'message': '删除成功',
  'data': null
});

// 打印报销单
Mock.mock('/api/expense-reimbursement/print', 'get', {
  'code': 200,
  'message': '打印成功',
  'data': {
    'printUrl': '/api/file/download?fileId=@guid'
  }
});

// 导出报销单
Mock.mock('/api/expense-reimbursement/export', 'get', {
  'code': 200,
  'message': '导出成功',
  'data': {
    'exportUrl': '/api/file/download?fileId=@guid'
  }
});

// 获取流程图
Mock.mock('/api/expense-reimbursement/flowchart', 'get', {
  'code': 200,
  'message': '获取成功',
  'data': {
    'flowchartUrl': '/api/file/download?fileId=@guid'
  }
});