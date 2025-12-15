import { YJBusiness } from 'yjpl-core';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // 表单双向绑定数据
      query: {},
      // qzz表格双向绑定数据
      grid: [],
      childGrid: [],
      list: '1',
      listData: [],
      search: ''
    };

    this.metaModel = {
      grid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'grid',
        // 表格数据
        pager: false,
        Align: 'alClient',
        multiSelect: true,
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
      },
      childGrid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'childGrid',
        // 表格数据
        pager: false,
        Align: 'alClient',
        multiSelect: true,
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
      },
      agree: {
        text: '批准',
        icon: 'icon el-icon-check',
      },
      refuse: {
        text: '拒绝',
        icon: 'icon el-icon-close'
      },
      batchAgree: {
        text: '批量批准',
        icon: 'icon el-icon-check'
      },
      batchRefuse: {
        text: '批量拒绝',
        icon: 'icon el-icon-close'
      },
      childAgree: {
        text: '批准',
        icon: 'icon el-icon-check',
      },
      childRefuse: {
        text: '拒绝',
        icon: 'icon el-icon-close'
      },
      childBatchAgree: {
        text: '批量批准',
        icon: 'icon el-icon-check'
      },
      childBatchRefuse: {
        text: '批量拒绝',
        icon: 'icon el-icon-close'
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
    return [
      { dwbm: 1000, dwmc: '远光软件股份有限公司', bbdm: 'dbgs001', bbmc: '固定报表1', ysh: '是', shyh: '张三' },
      { dwbm: 1001, dwmc: '远光软件股份有限公司', bbdm: 'dbgs002', bbmc: '固定报表2', ysh: '是', shyh: '李四' },
      { dwbm: 1002, dwmc: '远光软件股份有限公司', bbdm: 'dbgs003', bbmc: '固定报表3', ysh: '是', shyh: '张三' },
      { dwbm: 1003, dwmc: '远光软件股份有限公司', bbdm: 'dbgs004', bbmc: '固定报表4', ysh: '是', shyh: '李四' },
      { dwbm: 1004, dwmc: '远光软件股份有限公司', bbdm: 'dbgs005', bbmc: '固定报表5', ysh: '是', shyh: '张三' },
      { dwbm: 1005, dwmc: '远光软件股份有限公司', bbdm: 'dbgs006', bbmc: '固定报表6', ysh: '是', shyh: '李四' },
    ];
  }

  async getListData() {
    return [
      {
        text: '选项1',
        value: '1'
      }, {
        text: '选项2',
        value: '2'
      }, {
        text: '选项3选项3选项3选项3选项3选项3选项3选项3选项3选项3选项3选项3',
        value: '3'
      }, {
        text: '选项4',
        value: '4'
      }, {
        text: '选项5',
        value: '5'
      }, {
        text: '选项6',
        value: '6'
      }
    ]
  }

}
export default ListPageBs;