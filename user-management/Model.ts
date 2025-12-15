import { YJState } from 'yjpl-core';

// 表格属性公共配置
const gridOption = {
  Align: 'alClient',
  columnSetupId: 'userManagementToolbar',
  multiselect: true,
  footerrow: false,
  contentFit: true,
  shrinkToFit: true,
  suitToFit: true,
  autoEcpWidth: true,
  cellEdit: false
};

// metaModel - 页面配置（静态）
export const metaModel = {
  // 标题
  title: '用户管理',
  // form-item的标题后缀
  labelSuffix: '：',
  // 是否显示树
  showTree: false,
  // 是否可以切换成卡片样式
  showCard: true,
  // 是否可以切换成列表样式
  showList: true,
  isQueryGrid: true,
  // 查询面板的配置项
  searchPanel: {
    subTitle: '查询条件：无'
  },
  defaultSelectValue: '',
  // 树的配置项
  tree: {
    locate: true,
    idField: 'id',
    textField: 'label',
    defaultExpandAll: true
  },
  // 查询form配置项
  query: [
    {
      placeholder: '请输入用户名',
      name: 'username',
      dataType: 'string',
      label: '用户名',
      rules: [
        { required: false, message: '请输入用户名', trigger: 'blur' }
      ]
    },
    {
      placeholder: '请输入姓名',
      name: 'realName',
      dataType: 'string',
      label: '姓名',
      rules: [
        { required: false, message: '请输入姓名', trigger: 'blur' }
      ]
    },
    {
      placeholder: '请选择',
      name: 'department',
      dataType: 'combobox',
      label: '部门',
      idField: 'dxid',
      textField: 'dxmc',
      data: [
        { dxid: 'dept001', dxmc: '技术部' },
        { dxid: 'dept002', dxmc: '市场部' },
        { dxid: 'dept003', dxmc: '财务部' },
        { dxid: 'dept004', dxmc: '人力资源部' },
        { dxid: 'dept005', dxmc: '行政部' }
      ],
      rules: [
        { required: false, message: '请选择部门', trigger: 'change' }
      ]
    },
    {
      placeholder: '请选择',
      name: 'role',
      dataType: 'combobox',
      label: '角色',
      idField: 'dxid',
      textField: 'dxmc',
      data: [
        { dxid: 'admin', dxmc: '系统管理员' },
        { dxid: 'manager', dxmc: '部门经理' },
        { dxid: 'user', dxmc: '普通用户' },
        { dxid: 'guest', dxmc: '访客' }
      ],
      rules: [
        { required: false, message: '请选择角色', trigger: 'change' }
      ]
    },
    {
      placeholder: '请选择',
      name: 'status',
      dataType: 'combobox',
      label: '状态',
      idField: 'dxid',
      textField: 'dxmc',
      data: [
        { dxid: '1', dxmc: '启用' },
        { dxid: '0', dxmc: '禁用' }
      ],
      rules: [
        { required: false, message: '请选择状态', trigger: 'change' }
      ]
    },
    {
      name: 'createTime',
      dataType: 'date',
      type: 'daterange',
      label: '创建日期',
      startPlaceholder: '开始日期',
      endPlaceholder: '结束日期',
      rangeSeparator: '至',
      format: 'yyyy-MM-dd',
      valueFormat: 'yyyy-MM-dd'
    }
  ],
  // 工具栏按钮
  btns: [
    {
      name: 'add',
      text: '新增',
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
      icon: 'yj-p-delete'
    },
    {
      name: 'resetPassword',
      text: '重置密码',
      icon: 'yj-p-refresh'
    },
    {
      name: 'enable',
      text: '启用',
      icon: 'yj-p-check'
    },
    {
      name: 'disable',
      text: '禁用',
      icon: 'yj-p-close'
    },
    {
      name: 'export',
      text: '导出',
      icon: 'yj-p-export'
    },
    {
      name: 'import',
      text: '导入',
      icon: 'yj-p-import'
    }
  ],
  // qzz表格配置项
  grid: {
    ...gridOption,
    layout: ['title', 'button', 'search'],
    title: '用户列表',
    pager: true,
    // 卡片配置
    cardOption: {
      formatOption: {
        title: 'username',
        subTitle: ['realName', 'department'],
        money: '',
        description: 'email'
      }
    },
    // 表格配置
    colNames: ['用户ID', '用户名', '姓名', '部门', '角色', '手机号', '邮箱', '状态', '创建时间', '最后登录'],
    Align: 'alClient',
    autoEcpWidth: true,
    colModels: [
      {
        editType: 'string',
        caption: '用户ID',
        name: 'userId',
        width: 100,
        showInList: true,
        align: 'center'
      },
      {
        editType: 'string',
        caption: '用户名',
        name: 'username',
        width: 120,
        showInList: true
      },
      {
        editType: 'string',
        caption: '姓名',
        name: 'realName',
        width: 100,
        showInList: true
      },
      {
        editType: 'string',
        caption: '部门',
        name: 'department',
        width: 120,
        showInList: true
      },
      {
        editType: 'string',
        caption: '角色',
        name: 'role',
        width: 120,
        showInList: true
      },
      {
        editType: 'string',
        caption: '手机号',
        name: 'phone',
        width: 120,
        showInList: true
      },
      {
        editType: 'string',
        caption: '邮箱',
        name: 'email',
        width: 180,
        showInList: true
      },
      {
        editType: 'string',
        caption: '状态',
        name: 'status',
        width: 80,
        showInList: true,
        align: 'center'
      },
      {
        editType: 'string',
        caption: '创建时间',
        name: 'createTime',
        width: 150,
        showInList: true
      },
      {
        editType: 'string',
        caption: '最后登录',
        name: 'lastLoginTime',
        width: 150,
        showInList: false
      }
    ]
  }
};

// dataModel - 业务数据（动态）
export const dataModel = {
  // 查询面板双向绑定数据
  query: {},
  // 树组件双向绑定数据
  tree: [],
  // qzz表格双向绑定数据
  grid: [],
  pageTotal: 0
};

// stateModel - 状态管理（控制）
export const stateModel = {
  add: YJState.STATE.DEFAULT,
  edit: YJState.STATE.DEFAULT,
  delete: YJState.STATE.DEFAULT,
  resetPassword: YJState.STATE.DEFAULT,
  enable: YJState.STATE.DEFAULT,
  disable: YJState.STATE.DEFAULT,
  export: YJState.STATE.DEFAULT,
  import: YJState.STATE.DEFAULT
};

export default {
  metaModel,
  dataModel,
  stateModel
};
