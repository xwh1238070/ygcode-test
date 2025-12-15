import { YJBusiness } from 'yjpl-core';

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
      start: {
        label: '折合系数：',
        dataType: 'combobox',
        data: [
          { id: 1, text: '1', label: '不折合' },
          { id: 100, text: '100', label: '100(百)' },
          { id: 1000, text: '1000', label: '1,000(千)' },
          { id: 10000, text: '10000', label: '10,000(万)' },
          { id: 1000000, text: '1000000', label: '1,000,000(百万)' },
          { id: 100000000, text: '100000000', label: '100,000,000(亿)' }
        ],
        filterable: true,
        allowCreate: true,
        defaultFirstOption: true,
        idField: 'id',
        textField: 'label',
        clearable: false
      },
      units: {
        name: 'units',
        dataType: 'combobox',
        label: '所属单位：',
        data: [
          { id: '1', text: '单位1' },
          { id: '2', text: '单位2' }
        ],
        idField: 'id',
        textField: 'text'
      },
      grid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'grid',
        // 表格数据
        option: {
          pager: true,
          Align: 'alClient',
          colNames: ['单位编码', '单位名称', '报表代码', '报表名称', '已审核', '审核用户'],
          colModels: [
            {
              caption: '单位编码',
              name: 'dwbm'
            },
            {
              caption: '单位名称',
              name: 'dwmc'
            },
            {
              caption: '报表代码',
              name: 'bbdm'
            },
            {
              caption: '报表名称',
              name: 'bbmc'
            },
            {
              caption: '已审核',
              name: 'ysh'
            },
            {
              caption: '审核用户',
              name: 'shyh'
            }
          ]
        }
      },
      audit: {
        text: '审核',
        icon: 'ticon iconshenhe'
      },
      cancelAudit: {
        text: '取消审核',
        icon: 'ticon iconshenhe'
      },
      export: {
        icon: 'ticon icondaochu'
      },
      print: {
        icon: 'ticon icondayin'
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

  async getGridData() {
    return [
      { dwbm: 1000, dwmc: '远光软件股份有限公司', bbdm: 'dbgs001', bbmc: '固定报表1', ysh: '是', shyh: '张三' },
      { dwbm: 1001, dwmc: '远光软件股份有限公司', bbdm: 'dbgs002', bbmc: '固定报表2', ysh: '是', shyh: '李四' },
      { dwbm: 1002, dwmc: '远光软件股份有限公司', bbdm: 'dbgs003', bbmc: '固定报表3', ysh: '是', shyh: '张三' },
      { dwbm: 1003, dwmc: '远光软件股份有限公司', bbdm: 'dbgs004', bbmc: '固定报表4', ysh: '是', shyh: '李四' },
      { dwbm: 1004, dwmc: '远光软件股份有限公司', bbdm: 'dbgs005', bbmc: '固定报表5', ysh: '是', shyh: '张三' },
      { dwbm: 1005, dwmc: '远光软件股份有限公司', bbdm: 'dbgs006', bbmc: '固定报表6', ysh: '是', shyh: '李四' },
    ];
  }

}
export default ListPageBs;