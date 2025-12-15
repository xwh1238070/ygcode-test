import { YJBusiness, YJState } from 'yjpl-core';

class ListPageBs extends YJBusiness {
  constructor() {
    super();
    this.dataModel = {
      // 查询面板双向绑定数据
      query: {},
      // 树组件双向绑定数据
      tree: [],
      // qzz表格双向绑定数据
      grid: []
    };
    this.metaModel = {
      // 标题
      title: '归档打印查询',
      // form-item的标题后缀
      labelSuffix: '：',
      // 是否显示树
      showTree: true,
      // 是否可以切换成卡片样式
      showCard: true,
      isQueryGrid: false,
      // 查询面板的配置项,具体参考tl-searchPanel组件文档
      searchPanel: {},
      // 树的配置项，具体参考tl-tree组件文档
      tree: {
        idField: 'pid',
        textField: 'label',
        defaultExpandAll: true
      },
      // 查询form配置项
      query: [
        {
          name: 'name',
          dataType: 'string',
          label: '归档名称1111111111111111',
          rules: [
            { required: true, message: '请输入归档名称', trigger: 'blur' },
            { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
          ]
        },
        {
          name: 'makeDate',
          dataType: 'date',
          type: 'datetimerange',
          label: '制证日期',
          startPlaceholder: '开始时间',
          endPlaceholder: '结束时间',
          rangeSeparator: '至',
          format: 'yyyy-MM-dd'
        },
        {
          name: 'organization',
          dataType: 'entity-select',
          type: 'table',
          label: '协作单位',
          data: [
            { dxid: 1570001118, dxmc: '江苏电力' },
            { dxid: 1570001098, dxmc: '湖北电力' },
            { dxid: 1570001113, dxmc: '大宇物流' },
            { dxid: 1570001115, dxmc: '风尖' },
            { dxid: 1570001114, dxmc: '威然' },
            { dxid: 1570001121, dxmc: '喆大电力' }
          ],
          // qzz表格列头设置
          colData: [
            { label: 'id', prop: 'dxid' },
            { label: '名称', prop: 'dxmc', selected: true },
            { label: '是否归档', prop: 'isArchive', data: { 1: '归档', 0: '不归档' } }
          ],
          tableData: [
            { dxid: 1570001118, dxmc: '江苏电力', isArchive: 1 },
            { dxid: 1570001098, dxmc: '湖北电力', isArchive: 0 },
            { dxid: 1570001113, dxmc: '大宇物流', isArchive: 1 },
            { dxid: 1570001115, dxmc: '风尖', isArchive: 0 },
            { dxid: 1570001114, dxmc: '威然', isArchive: 1 },
            { dxid: 1570001121, dxmc: '喆大电力', isArchive: 0 }
          ],
          idField: 'dxid',
          textField: 'dxmc'
        },
        {
          name: 'sketch',
          dataType: 'tl-input',
          label: '归档简述'
        },
        {
          name: 'checked',
          label: '是否检查',
          children: [
            {
              dataType: 'checkbox',
              name: 'checked1',
              label: '选项1'
            },
            {
              dataType: 'checkbox',
              name: 'checked2',
              label: '选项2'
            }
          ]
        },
        {
          name: 'daterange',
          label: '区间选择',
          children: [
            {
              dataType: 'string',
              name: 'checked1',
              label: '选项1',
              style: {
                width: '45%',
                float: 'left'
              }
            },
            {
              dataType: 'text',
              label: '至',
              style: {
                display: 'block',
                width: '10%',
                float: 'left',
                textAlign: 'center'
              }
            },
            {
              dataType: 'string',
              name: 'checked2',
              label: '选项2',
              style: {
                width: '45%',
                float: 'left'
              }
            }
          ]
        }
      ],
      // qzz表格配置项
      grid: {
        layout: ['button', 'search'],
        title: '表格样例',
        // tab名称
        label: '表格1',
        // 表格数据
        option: {
          pager: true,
          pageSizeList: [100, 200, 500, 1000],
          colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              editType: 'number',
              caption: '费用',
              name: 'cost'
            },
            {
              editType: 'string',
              caption: '出发地',
              name: 'origin',
              showInList: true
            },
            {
              editType: 'string',
              caption: '到达地',
              name: 'destination',
              showInList: true
            },
            {
              editType: 'string',
              caption: '交通工具',
              name: 'traffic',
              showInList: true
            },
            {
              editType: 'date',
              caption: '日期',
              name: 'date'
            }
          ]
        },
        // 工具栏按钮
        btns: [
          {
            name: 'add',
            text: '新增'
          },
          {
            name: 'delete',
            text: '删除'
          }
        ]
      }
    };
    this.stateModel = {
      add: YJState.STATE.DISABLED,
      start: YJState.STATE.DEFAULT
    };
    this.dataSource = {};
  }
  getData() {
    return {
      dataModel: this.dataModel,
      metaModel: this.metaModel,
      stateModel: this.stateModel,
      dataSource: this.dataSource
    };
  }

  async getQueryData() {
    return {
      checked: false
    };
  }

  async getTreeData() {
    return [
      {
        label: '全部',
        destination: '',
        children: [
          { origin: '广州', destination: '广州', label: '广州' },
          { origin: '上海', destination: '上海', label: '上海' },
          { origin: '深圳', destination: '深圳', label: '深圳' },
          { origin: '北京', destination: '北京', label: '北京' },
          { origin: '天津', destination: '天津', label: '天津' }
        ]
      }
    ];
  }

  async getGridData() {
    return [
      { cost: 500, date: '2021-01-01', origin: '珠海', destination: '广州', traffic: '高铁' },
      { cost: 600, date: '2021-02-01', origin: '珠海', destination: '上海', traffic: '的士' },
      { cost: 900, date: '2021-03-01', origin: '珠海', destination: '北京', traffic: '火箭' },
      { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
      { cost: 800, date: '2021-05-01', origin: '珠海', destination: '天津', traffic: '飞机' }
    ];
  }
}
export default ListPageBs;
