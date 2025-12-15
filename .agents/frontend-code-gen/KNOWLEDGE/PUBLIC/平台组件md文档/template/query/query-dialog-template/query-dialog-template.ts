import { YJBusiness } from 'yjpl-core';
import data from '../data';
import userData from '../user';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // 表单双向绑定数据
      query: {
        T_TTIME: []
      },
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
        type: 'daterange',
        placeholder: '请选择'
      },
      T_BILLID: {
        label: '申请编号',
        dataType: 'tl-range-number'
      },
      T_FISTYHDM: {
        label: '申请人',
        dataType: 'entity-select',
        idField: 'xsmc',
        textField: 'xsmc',
        data: userData,
        tableData: userData,
        type: 'table',
        multiple: true,
        dialogTitle: '申请人',
        colData: [{
          label: '用户名称',
          prop: 'yhmc'
        }, {
          label: '显示名称',
          prop: 'xsmc',
          selected: true
        }]
      },
      grid: {
        // 是否显示卡片
        showCard: true,
        // 是否显示列表
        showList: true,
        useQueryGrid: true,
        title: '详细信息',
        
        layout: ['title', 'button', 'search'],
        
        cardOption: {
          formatOption: {
            title: 'T_YWDJTYPE',
            subTitle: 'T_FISTYHDM',
            money: 'T_SUMMONEY',
            description: 'T_BZWC'
          }
        },
        option: {
          suitToFit: true,
          columnSetupId: 'columnKey',
          colNames: ["所属组织", "单据名称", "制单日期", "总金额", "申请编号", "申请人", "待处理角色", "待处理人", "完成状态"],
          Align: 'alClient',
          titleFit: true,
          autoEcpWidth: true,
          colModels: [
            {
              caption: '所属组织',
              name: 'T_COMPID',
              align: 'left',
              showInList: true,
              contentFit: true
            },
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
              contentFit: true,
              link: true
            }
            ,{
              caption: '申请人',
              name: 'T_FISTYHDM',
              align: 'center',
              contentFit: true
            },
            {
              caption: '待处理角色',
              name: 'T_NEXTPOSTID',
              align: 'center',
              contentFit: true
            },
            {
              caption: '待处理人',
              name: 'T_NEXTYHDM',
              align: 'center',
              contentFit: true
            },
            {
              caption: '完成状态',
              name: 'T_BZWC',
              align: 'center',
              contentFit: true
            }
          ]
        }
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

  getTreeData() {
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

  getGridData(filter = {}) {
    const res = data.filter((item) => {
      if (!item.T_YWDJTYPE.includes((filter as any).T_YWDJTYPE || '')) return false;
      // if (Utils.strToTimeMillis(Utils.ecpDateToDate(item.T_TTIME)) < Utils.strToTimeMillis((filter as any).T_TTIME[0])) return false;
      // if (Utils.strToTimeMillis(Utils.ecpDateToDate(item.T_TTIME)) > Utils.strToTimeMillis((filter as any).T_TTIME[1])) return false;
      // if ((filter as any).T_FISTYHDM.length > 0 && !(filter as any).T_FISTYHDM.includes(item.T_FISTYHDM || '')) return false;
      return true;
    });
    return res;
  }
}
export default ListPageBs;