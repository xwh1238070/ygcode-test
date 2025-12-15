import { YJBusiness } from 'yjpl-core';
import data from '../data';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // 表单双向绑定数据
      query: {},
      // qzz表格双向绑定数据
      grid: []
    };

    this.metaModel = {
      T_YWDJTYPE: {
        label: '单据名称',
        dataType: 'string',
        placeholder: '请输入'
      },
      T_TTIME: {
        label: '制单日期',
        dataType: 'date',
        type: 'daterange'
      },
      T_BILLID: {
        label: '申请编号',
        dataType: 'tl-range-number',
      },
      grid: {
        title: '详细信息',
        layout: ['title', 'button', 'search'],
        cardOption: {
          formatOption: {
            money: 'T_SUMMONEY'
          }
        },
        option: {
          suitToFit: true,
          columnSetupId: 'columnKey',
          colNames: ["单据名称", "制单日期", "总金额", "申请编号"],
          Align: 'alClient',
          titleFit: true,
          autoEcpWidth: true,
          colModels: [
            {
              caption: '单据名称',
              align: 'left',
              name: 'T_YWDJTYPE',
              showInList: true,
              contentFit: true
            },
            {
              dataType: 'date',
              editType: 'date',
              caption: '日期',
              name: 'T_TTIME',
              contentFit: true
            },
            {
              editType: 'number',
              dataType: 'number',
              caption: '总金额',
              name: 'T_SUMMONEY',
              formatType: 'money',
              defaultValue: 0,
              scale: 2,
              contentFit: true
            },
            {
              caption: '申请编号',
              name: 'T_BILLID',
              align: 'left',
              contentFit: true
            }
          ]
        }
      },
      add: {
        text: '新增',
        children: [
          {
            name: 'listAdd',
            text: '表格新增',
            action: 'add'
          },
          {
            name: 'singleAdd',
            text: '单屏新增'
          }
        ]
      },
      edit: {
        text: '修改',
      },
      delete: {
        text: '删除',
        action: 'delete'
      },
      import: {
        text: '导入',
      },
      export: {
        text: '导出',
      },
      tree: {
        locate: true,
        defaultExpandAll: true
      }
    };
    this.stateModel = {};
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
    return data;
  }
}
export default ListPageBs;