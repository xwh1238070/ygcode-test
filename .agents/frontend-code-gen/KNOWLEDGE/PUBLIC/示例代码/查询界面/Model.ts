import { YJState } from 'yjpl-core';

// 表格属性公共配置
const gridOption = {
  Align: 'alClient',
  columnSetupId: 'orderQueryToolbar',
  multiselect: false,
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
  title: '订单查询',
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
  // 多视图扩展配置
  viewSwitchExtend: [
    {
      name: 'yfxPivot',
      icon: 'yj-icon yj-p-hanglieqiehuan',
      dataType: 'yfx-pivot',
      option: {
        params: {
          metaData: [
            {
              fieldDataLengh: 0,
              fieldName: 'orderNo',
              fieldClazz: '',
              dispFieldName: '订单号',
              fieldDataType: 0,
              fieldDataPrecision: 0
            },
            {
              dispFieldName: '客户名称',
              fieldDataPrecision: 0,
              fieldDataLengh: 0,
              fieldDataType: 0,
              fieldName: 'customerName',
              fieldClazz: ''
            },
            {
              dispFieldName: '订单金额',
              fieldDataType: 1,
              fieldDataPrecision: 2,
              fieldDataLengh: 0,
              fieldName: 'amount',
              fieldClazz: ''
            },
            {
              fieldDataLengh: 0,
              dispFieldName: '订单状态',
              fieldDataPrecision: 0,
              fieldClazz: '',
              fieldDataType: 0,
              fieldName: 'status'
            }
          ],
          fullData: [
            ['ORD-2024-001', '张三', 5000, '已审核'],
            ['ORD-2024-002', '李四', 8000, '已发货'],
            ['ORD-2024-003', '王五', 3500, '待审核'],
            ['ORD-2024-004', '赵六', 12000, '已完成'],
            ['ORD-2024-005', '孙七', 6500, '已审核']
          ],
          moduleCode: 'order',
          sceneId: 'orderQuery',
          userId: '132325',
          srvVipAddress: ''
        }
      }
    }
  ],
  pivotRef: null,
  showView: null,
  // 查询form配置项
  query: [
    {
      placeholder: '请输入',
      name: 'orderNo',
      dataType: 'string',
      label: '订单号',
      rules: [
        { required: false, message: '请输入订单号', trigger: 'blur' }
      ]
    },
    {
      placeholder: '请输入',
      name: 'customerName',
      dataType: 'string',
      label: '客户名称',
      rules: [
        { required: false, message: '请输入客户名称', trigger: 'blur' }
      ]
    },
    {
      name: 'orderDate',
      dataType: 'date',
      type: 'daterange',
      label: '订单日期',
      startPlaceholder: '开始日期',
      endPlaceholder: '结束日期',
      rangeSeparator: '至',
      format: 'yyyy-MM-dd',
      valueFormat: 'yyyy-MM-dd'
    },
    {
      name: 'status',
      dataType: 'combobox',
      label: '订单状态',
      options: [
        { label: '待审核', value: 0 },
        { label: '已审核', value: 1 },
        { label: '已发货', value: 2 },
        { label: '已完成', value: 3 }
      ]
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
      name: 'export',
      text: '导出',
      icon: 'yj-p-export'
    },
    {
      name: 'import',
      text: '导入',
      icon: 'yj-p-import'
    },
    {
      name: 'printBtn',
      icon: 'yj-p-print',
      tips: '打印'
    }
  ],
  // qzz表格配置项
  grid: {
    ...gridOption,
    layout: ['title', 'button', 'search'],
    title: '订单列表',
    pager: true,
    // 卡片配置
    cardOption: {
      formatOption: {
        title: 'orderNo',
        subTitle: ['customerName', 'status'],
        money: 'amount',
        description: 'createTime'
      }
    },
    // 表格配置
    colNames: ['订单号', '客户名称', '订单金额', '订单状态', '创建时间'],
    Align: 'alClient',
    autoEcpWidth: true,
    colModels: [
      {
        editType: 'string',
        caption: '订单号',
        name: 'orderNo',
        showInList: true
      },
      {
        editType: 'string',
        caption: '客户名称',
        name: 'customerName',
        showInList: true
      },
      {
        editType: 'number',
        caption: '订单金额',
        name: 'amount',
        scale: 2,
        showInList: true
      },
      {
        editType: 'string',
        caption: '订单状态',
        name: 'status',
        showInList: true
      },
      {
        editType: 'string',
        caption: '创建时间',
        name: 'createTime',
        showInList: true
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
  export: YJState.STATE.DEFAULT,
  import: YJState.STATE.DEFAULT,
  printBtn: YJState.STATE.DEFAULT
};

export default {
  metaModel,
  dataModel,
  stateModel
};
