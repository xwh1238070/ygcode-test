import { YJBusiness } from 'yjpl-core';
class SinglePageBs extends YJBusiness {
  constructor() {
    super();
    this.dataModel = {
      // businessForm双向绑定数据
      businessForm: {
        start: undefined,
        end: undefined,
        reimbursement: undefined,
        reason: undefined,
      },
      // visitingForm双向绑定数据
      visitingForm: {
        visitingStartDate: undefined,
        visitingEndDate: undefined,
        reimburseAmount: undefined
      },
      // qzz双向绑定数据
      grid: [
        { cost: 500, date: '2021-01-01', origin: '珠海', destination: '广州', traffic: '高铁' },
        { cost: 600, date: '2021-02-01', origin: '珠海', destination: '上海', traffic: '的士' },
        { cost: 900, date: '2021-03-01', origin: '珠海', destination: '北京', traffic: '火箭' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 700, date: '2021-04-01', origin: '珠海', destination: '深圳', traffic: '轮渡' },
        { cost: 800, date: '2021-05-01', origin: '最后一条', destination: '天津', traffic: '飞机' }
      ]
    };
    this.metaModel = {
      // 模板头部二维码内容
      qrcode: 'hello world',
      // 模板标题
      title: '个人单据',
      // 模板头部编号
      code: '996711',
      // 模板头部制单日期
      date: '20230421',
      // 模板头部按钮组
      titleBtnList: [
        {
          text: '导入',
          // element-ui图标使用方式
          icon: 'el-icon-folder-add'
        },
        {
          text: '导出',
          // 天鹂图标使用方式
          icon: 'iconfont frontEnd'
          // icon: 'iconfont icondaochu'
        }
      ],
      // 分组1的标题和折叠属性
      billForm: {
        title: '我的单据信息',
        collapsible: true
      },
      // 分组2的标题和折叠属性
      billInfo: {
        title: '我的费用信息',
        collapsible: true
      },
      // 分组3的标题和折叠属性
      billUpload: {
        title: '我的附件文档',
        collapsible: true
      },
      // 分组4的标题和折叠属性
      billWorkflow: {
        title: '我的审批轨迹',
        collapsible: true
      },
      // businessForm配置信息
      businessForm: [
        {
          name: 'start',
          dataType: 'date',
          label: '开始时间'
        },
        {
          name: 'end',
          dataType: 'date',
          label: '结束时间'
        },
        {
          name: 'reimbursement',
          dataType: 'string',
          label: '报销事项',
          rules: [{
            required: true,
            message: '此项不能为空'
          }]
        },
        {
          name: 'reason',
          dataType: 'textarea',
          label: '出差事由',
          resize: 'vertical',
          col: 4
        }
      ],
      // visitingForm配置信息
      visitingForm: [
        {
          name: 'visitingStartDate',            
          dataType: 'date',
          label: '开始时间'
        },
        {
          name: 'visitingEndDate',
          dataType:'date',
          label: '结束时间',
        },
        {
          name: 'reimburseAmount',
          dataType: 'string',
          type: 'number',
          label: '车费报销',
          format: {
            key: 'currency',
            decimal: 2,
            currencySign: '$',
            options: {
              separation: true,
              bracketabled: false
            }
          }
        }
      ],
      // qzz表格配置项
      grid: {
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
            caption: '出发地',
            name: 'origin',
            showInList: true
          },
          {
            caption: '到达地',
            name: 'destination',
            showInList: true
          },
          {
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
      // qzz表格顶部右侧编辑按钮组
      gridBtnList: [
        {
          text: '增加',
          icon: 'el-icon-circle-plus-outline'
        },
        {
          text: '编辑',
          icon: 'el-icon-edit'
        },
        {
          text: '删除',
          icon: 'el-icon-remove-outline'
        }
      ],
      // 底部工具栏按钮组
      footerBtnList: [
        {
          text: '取消',
        },
        {
          text: '传递',
          type: 'primary',
          // eslint-disable-next-line
          callback: () =>{}
        }
      ]
    };
  }
  getData() {
    return {
      dataModel: this.dataModel,
      metaModel: this.metaModel
    };
  }
}
export default SinglePageBs;