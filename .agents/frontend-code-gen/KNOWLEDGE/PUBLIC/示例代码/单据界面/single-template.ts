import { YJBusiness } from 'yjpl-core';

class SinglePageBs extends YJBusiness {
  constructor() {
    super();
    this.dataModel = {
      // 经办人信息
      billUser: {
        name: '张晓玲',
        dept: 'DAP研发中心-产品管理部',
        operator: '张小玲，远光软件股份有限公司 DAP研发中心-产品管理部'
      },
      // 主表单数据
      businessForm: {
        label1: undefined,
        label2: undefined,
        label3: undefined,
        label4: undefined,
        label5: undefined,
        label6: undefined,
        label7: undefined,
        label8: undefined,
        label9: undefined,
      },
      // 审批流程数据
      workflowData: [
        {
          status: 'wait',
          finishTime: '2018-12-23 03:23:21',
          organizationName: '远光软件股份有限公司',
          rule: '天骆角色001',
          person: '新伟01',
          comment: '',
          waitTime: '停留1.9天'
        },
        {
          status: 'pass',
          finishTime: '2018-12-23 03:23:21',
          organizationName: '远光软件股份有限公司',
          rule: '普通职员',
          person: '李梅',
          comment: '请审批',
          waitTime: '停留1.9天'
        }
      ],
    };
    this.metaModel = {
      // 主表单配置
      billForm: {
        title: '基础信息',
        collapsible: true
      },
      // 子表配置
      billInfo: {
        title: '详情信息',
        collapsible: true
      },
      // 附件配置
      billUpload: {
        title: '附件信息',
        collapsible: true
      },
      // 审批流程配置
      billWorkflow: {
        title: '审批轨迹',
        collapsible: true
      },
    };
    this.pageModel = {
      title: '单据录入',
      cols: 3,
      date: '2023-04-21',
      qrcode: '1111',
      code: '114514',
      area: {
        upload: {
          title: '附件信息',
        },
      },
      searchPanel: {},
      // 主表单字段配置
      form: [
        {
          required: true,
          name: 'jbr',
          dataType: 'string',
          label: '经办人经办人经办人'
        },
        {
          required: true,
          name: 'jbbm',
          dataType: 'string',
          label: '经办部门经办部门经办部门经办部门经办部门'
        },
        {
          name: 'start',
          dataType: 'date',
          label: '开始时间开始时间开始时间开始时间开始时间开始时间开始时间开始时间开始时间'
        },
        {
          name: 'end',
          dataType: 'date',
          label: '结束时间'
        },
        {
          name: 'textarea',
          dataType: 'textarea',
          label: '备注',
          cols: 4
        }
      ],
      // 子表配置
      grid: {
        title: '子表表格标题',
        defaultShow: 'grid',
        showGrid: true,
        showList: true,
        showCard: true,
        isQueryGrid: false,
        height: 'auto',
        cardOption: {
          formatOption: {
            title: 'origin',
            subTitle: 'date',
            money: 'cost',
            description: 'destination'
          }
        },
        option: {
          colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
          autoEcpWidth: true,
          absolute: false,
          suitHeight: true,
          Align: 'alTop',
          maxRow: 20,
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
        }
      },
      // 标题栏按钮
      titleBtns: [
        {
          label: '导出'
        },
        {
          label: '打印',
        },
        {
          label: '流程图',
        },
        {
          label: '流程轨迹'
        },
      ],
      // 工具栏按钮
      toolbar: [
        {
          label: '传递',
          type: 'primary'
        },
        {
          label: '保存'
        },
        {
          label: '关闭'
        },
        {
          label: '1关闭'
        },
        {
          label: '2关闭'
        },
        {
          label: '3关闭'
        },
        {
          label: '4关闭'
        },
        {
          label: '5关闭'
        },
        {
          label: '6关闭'
        },
        {
          label: '6关闭'
        },
        {
          label: '7关闭'
        }
      ],
      // 子表操作按钮
      btns: [
        {
          label: '新增',
          name: 'add'
        },
        {
          label: '修改',
          name: 'edit'
        },
        {
          label: '复制'
        },
        {
          label: '删除',
          action: 'delete'
        },
        {
          label: '导入'
        },
        {
          label: '导出'
        }
      ],
      // 锚点配置
      anchor: [
        {
          title: '基础信息',
          id: 'anchorA'
        },
        {
          title: '子表信息',
          id: 'anchorB'
        },
        {
          title: '附件信息',
          id: 'anchorC'
        },
        {
          title: '审批轨迹',
          id: 'anchorD'
        },
      ]
    };
  }

  getData() {
    return {
      dataModel: this.dataModel,
      metaModel: this.metaModel,
      pageModel: this.pageModel,
    };
  }
}

export default SinglePageBs;
