import { YJState } from 'yjpl-core';

/**
 * 任务管理页面 - 数据模型
 */

// metaModel - 页面配置（静态）
export const metaModel = {
  title: '任务管理',
  
  // 查询面板配置
  query: [
    {
      name: 'taskName',
      label: '任务名称',
      dataType: 'string',
      placeholder: '请输入任务名称'
    },
    {
      name: 'status',
      label: '任务状态',
      dataType: 'combobox',
      options: [
        { label: '全部', value: '' },
        { label: '待办', value: 'pending' },
        { label: '进行中', value: 'in_progress' },
        { label: '已完成', value: 'completed' },
        { label: '已取消', value: 'cancelled' }
      ],
      clearable: true
    },
    {
      name: 'priority',
      label: '优先级',
      dataType: 'combobox',
      options: [
        { label: '全部', value: '' },
        { label: '高', value: 'high' },
        { label: '中', value: 'medium' },
        { label: '低', value: 'low' }
      ],
      clearable: true
    },
    {
      name: 'assignee',
      label: '负责人',
      dataType: 'string',
      placeholder: '请输入负责人'
    },
    {
      name: 'dateRange',
      label: '创建日期',
      dataType: 'daterange',
      format: 'yyyy-MM-dd',
      valueFormat: 'yyyy-MM-dd',
      startPlaceholder: '开始日期',
      endPlaceholder: '结束日期'
    }
  ],
  
  // 表格配置
  grid: {
    colNames: ['ID', '任务名称', '描述', '状态', '优先级', '负责人', '创建时间', '截止时间', '完成时间'],
    colModels: [
      {
        name: 'id',
        caption: 'ID',
        width: 80,
        align: 'center',
        sortable: true
      },
      {
        name: 'taskName',
        caption: '任务名称',
        width: 200,
        sortable: true
      },
      {
        name: 'description',
        caption: '描述',
        width: 250
      },
      {
        name: 'status',
        caption: '状态',
        width: 100,
        align: 'center',
        formatType: 'enum',
        enumData: {
          'pending': '待办',
          'in_progress': '进行中',
          'completed': '已完成',
          'cancelled': '已取消'
        }
      },
      {
        name: 'priority',
        caption: '优先级',
        width: 100,
        align: 'center',
        formatType: 'enum',
        enumData: {
          'high': '高',
          'medium': '中',
          'low': '低'
        }
      },
      {
        name: 'assignee',
        caption: '负责人',
        width: 120
      },
      {
        name: 'createTime',
        caption: '创建时间',
        width: 150,
        formatType: 'date',
        format: 'yyyy-MM-dd HH:mm:ss'
      },
      {
        name: 'dueDate',
        caption: '截止时间',
        width: 150,
        formatType: 'date',
        format: 'yyyy-MM-dd'
      },
      {
        name: 'completeTime',
        caption: '完成时间',
        width: 150,
        formatType: 'date',
        format: 'yyyy-MM-dd HH:mm:ss'
      }
    ],
    pageSize: 20,
    pageSizes: [10, 20, 50, 100],
    pager: true,
    showCheckbox: true,
    showIndex: true,
    height: 'auto',
    striped: true,
    border: true
  },
  
  // 按钮配置
  btns: [
    {
      name: 'add',
      text: '新增',
      type: 'primary',
      icon: 'yj-p-add'
    },
    {
      name: 'edit',
      text: '编辑',
      icon: 'yj-p-edit'
    },
    {
      name: 'delete',
      text: '删除',
      icon: 'yj-p-delete',
      type: 'danger'
    },
    {
      name: 'complete',
      text: '完成任务',
      icon: 'yj-p-ai',
      type: 'success'
    },
    {
      name: 'export',
      text: '导出',
      icon: 'yj-p-export'
    }
  ]
};

// dataModel - 业务数据（动态）
export const dataModel = {
  // 查询条件
  query: {
    taskName: '',
    status: '',
    priority: '',
    assignee: '',
    dateRange: []
  },
  
  // 表格数据
  grid: [],
  
  // 分页信息
  pageTotal: 0,
  pageSize: 20,
  pageIndex: 1
};

// stateModel - 状态管理（控制）
export const stateModel = {
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DISABLED,
  delete: YJState.STATE.DISABLED,
  complete: YJState.STATE.DISABLED,
  export: YJState.STATE.DEFAULT,
  query: YJState.STATE.DEFAULT,
  reset: YJState.STATE.DEFAULT
};

export default { metaModel, dataModel, stateModel };
