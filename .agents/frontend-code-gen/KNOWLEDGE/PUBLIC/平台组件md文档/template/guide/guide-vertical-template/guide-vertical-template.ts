import { YJBusiness } from 'yjpl-core';

class GuideDialogTemplateBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // 表单双向绑定数据
      query: {},
      // qzz表格双向绑定数据
      grid: []
    };

    this.metaModel = {
      show: false,
      steps: {
        active: 1,
        stepData: [
          {
            title: '基本信息',
          },
          {
            title: '选择单位',
          },
          {
            title: '选择报表',
          },
        ],
      },
      grid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'grid',
        title: '表格标题',
        // 表格数据
        multiSelect: true,
        pager: true,
        colNames: ['单位编码', '单位名称', '报表代码', '报表名称', '已审核', '审核用户', '审核单位'],
        Align: 'alClient',
        autoEcpWidth: true,
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
          },
          {
            caption: '审核单位',
            name: 'shdw'
          }
        ]
      },
      moveUp: {
        text: '上移',
        icon: 'el-icon-top'
      },
      moveDown: {
        text: '下移',
        icon: 'el-icon-bottom'
      },
      previous: {
        text: '上一步',
      },
      next: {
        text: '下一步',
        type: 'primary'
      },
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

export default GuideDialogTemplateBs;
