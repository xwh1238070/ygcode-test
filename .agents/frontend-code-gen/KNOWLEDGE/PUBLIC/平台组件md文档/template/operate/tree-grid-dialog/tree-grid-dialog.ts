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
      grid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'grid',
        option: {
          // 表格 数据
        multiSelect: true,
        colNames: ['任务编号','单位名称'],
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
      tree: {
        border: true,
        locate: true,
        'default-expand-all': true
      },
      set: {
        text: '设置',
        icon: 'el-icon-plus',
        type: 'primary'
      },
      print: {
        text: '打印',
        icon: 'el-icon-edit'
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

  async getTreeData() {
    return [
      {
        label: '全部',
        destination: '',
        children: [
          {
            label: '集团本部标准报表',
            children:[
              {label: '管理报表'},
              {label: '选中状态'},
              {label: '业务报表'}
            ] 
          },
          {
            label: '苏州分公司报表',
            children:[
              {label: '管理报表'},
              {label: '选中状态'},
            ] 
          },
          {
            label: '广州分公司报表',
            children:[
              {label: '管理报表'},
              {label: '选中状态'},
            ] 
          },
          {
            label: '地方汇总报表' ,
            children:[
              {label: '月度汇总报表'},
              {label: '汇总报表'},
            ]
          },
        ]
      }
    ];
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