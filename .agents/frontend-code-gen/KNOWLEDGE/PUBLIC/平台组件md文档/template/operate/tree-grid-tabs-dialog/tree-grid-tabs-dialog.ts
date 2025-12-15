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
          pager: true,
          // 表格数据
          colNames: ['微服务', '服务名称', '引用', '描述'],
          Align: 'alClient',
          autoEcpWidth: true,
          colModels: [
            {
              caption: '微服务',
              name: 'wff'
            },
            {
              caption: '服务名称',
              name: 'ffmc'
            },
            {
              caption: '引用',
              name: 'yy',
              editType: 'onoff',
            },
            {
              caption: '描述',
              name: 'description'
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
            label: '云管理平台',
            children:[
              {label: '机器人管理'},
              {label: '我的管理'},
              {label: '我的机器人'}
            ] 
          },
          {
            label: '知识图谱应用平台',
            children:[
              {label: '管理报表'},
              {label: '选中状态'},
            ] 
          },
          {
            label: '电子档案',
            children:[
              {label: '档案安全'},
              {label: '配置管理'},
            ] 
          },
        ]
      }
    ];
  }

  async getGridData() {
    return [
      {wff:'p.rpadcloud',ffmc:'最近运行任务明细',description:''},
      {wff:'p.rpadcloud',ffmc:'任务运行数据概览',description:''},
      {wff:'p.rpadcloud',ffmc:'最近运行任务明细',description:''},
      {wff:'p.rpadcloud',ffmc:'任务运行数据概览',description:''},
      {wff:'p.rpadcloud',ffmc:'最近运行任务明细',description:''},
      {wff:'p.rpadcloud',ffmc:'任务运行数据概览',yy:true,description:''},
      {wff:'p.rpadcloud',ffmc:'最近运行任务明细',description:''},
      {wff:'p.rpadcloud',ffmc:'任务运行数据概览',description:''},

    ];
  }

}
export default ListPageBs;