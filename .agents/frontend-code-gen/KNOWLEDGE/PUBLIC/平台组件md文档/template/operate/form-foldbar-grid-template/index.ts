import { YJBusiness } from 'yjpl-core';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // 表单双向绑定数据
      query: {},
      // qzz表格双向绑定数据
      grid: [],
      form: {
        work: '1',
        date: '2023-03-02'
      },
      isCollapse: false,
      tree: [],
      work: '1',
      workData: []
    };

    this.metaModel = {
      grid: {
        // 对应的dataModel内配置的数据和ref指向
        name: 'grid',
        option: {
          // 表格数据
          pager: true,
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
        }
      },
      tree: {
        border: true,
        locate: true,
        'default-expand-all': true,
        'show-checkbox': true
      },
      publish: {
        text: '发布',
        icon: 'ticon iconfabu',
        children: [
          {
            label: '增加',
            icon: 'el-icon-circle-plus-outline',
            type: 'primary'
          },
          {
            label: '编辑',
            icon: 'el-icon-edit'
          },
          {
            name: 'aaaa',
            label: '删除',
            icon: 'el-icon-remove-outline'
          }
        ]
      },
      cancelPublish: {
        text: '取消发布',
        icon: 'ticon iconquxiaofabu',
        children: [
          {
            label: '增加',
            icon: 'el-icon-circle-plus-outline',
            type: 'primary'
          },
          {
            label: '编辑',
            icon: 'el-icon-edit'
          },
          {
            name: 'aaaa',
            label: '删除',
            icon: 'el-icon-remove-outline'
          }
        ]
      },
      changePublish: {
        text: '调整发布状态',
        icon: 'icon el-icon-set-up'
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

  async getWorkData() {
    return [
      {
        id: '1',
        text: '2022财务快报'
      },
      {
        id: '2',
        text: '2023财务快报'
      }
    ]
  }

}
export default ListPageBs;