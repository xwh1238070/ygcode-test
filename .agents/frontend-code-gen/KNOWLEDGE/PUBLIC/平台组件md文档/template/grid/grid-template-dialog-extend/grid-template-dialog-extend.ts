import { YJBusiness } from 'yjpl-core';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // qzz表格双向绑定数据
      grid: []
    };

    this.metaModel = {
       grid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'grid',
        option: {
          // 表格数据
          multiSelect: true,
          colNames: ['任务编号', '单位名称'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              caption: '任务编号',
              name: 'bbdm'
            },
            {
              caption: '单位名称',
              name: 'dwmc'
            },
          ]
        }
      },
      moveup: {
        text: '上移',
        icon: 'el-icon-top'
      },
      movedown: {
        text: '下移',
        icon: 'el-icon-bottom'
      },
      up: {
        text: '升序',
        icon: 'ticon iconshengxu'
      },
      down: {
        text: '降序',
        icon: 'ticon iconjiangxu'
      },
      more: {
        icon: 'el-icon-more-outline'
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
      { dwmc: '资产处报表', bbdm: 'dbgs001' },
      { dwmc: '月度固定报表', bbdm: 'dbgs002'},
      { dwmc: '月度卡片报表', bbdm: 'dbgs003' },
      { dwmc: '司库报表', bbdm: 'dbgs004'},
      { dwmc: '资产处报表', bbdm: 'dbgs005'},
      { dwmc: '月度固定报表', bbdm: 'dbgs006'},
      { dwmc: '月度卡片报表', bbdm: 'dbgs007'},
      { dwmc: '司库报表', bbdm: 'dbgs008'},
      { dwmc: '司库报表', bbdm: 'dbgs009'},
      { dwmc: '资产处报表', bbdm: 'dbgs0010'},
    ];
  }

}
export default ListPageBs;