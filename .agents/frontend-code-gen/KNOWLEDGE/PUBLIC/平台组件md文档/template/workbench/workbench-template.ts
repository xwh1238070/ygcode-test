import { YJBusiness } from 'yjpl-core';
export default class WorkbenchBs extends YJBusiness {
  constructor() {
    super();
    this.metaModel = {
      commonFunction: {
        title: '常用功能',
        addText: '新增',
        add: () => {
          console.log('新增');
        },
        editText: '编辑',
        edit: () => {
          console.log('编辑');
        },
        carouselList: []
      },
      calendar: {
        calendarDate: new Date(),
        toDoSummary: '待办事项汇总',
        date: '2023年4月23日',
        templatePulish: '模板发布',
        templatePulishNum: 1,
        templateUnit: '张',
        templateApproval: '模板审批',
        templateApprovalNum: 100
      },
      notice: {
        title: '业务公告',
        moreTitle: '查看更多',
        openMoreInfo: () => {},
        infoRow: 6,
        noticeList: [
          {
            id: '001',
            bgColor: 'rgba(177, 207, 251, 0.49)',
            title: '报表模板',
            content: '报表新模板正式发布，欢迎使用',
            time: '2小时'
          },
          {
            id: '002',
            bgColor: '#f0f3f6',
            title: '业务公告',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '2小时'
          },
          {
            id: '003',
            bgColor: '#f0f3f6',
            title: '业务公告',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '2小时'
          },
          {
            id: '004',
            bgColor: '#b0e8d1',
            title: '报表模板',
            content: '报表新模板正式发布，欢迎使用',
            time: '2小时'
          },
          {
            id: '005',
            bgColor: '#f0f3f6',
            title: '业务公告',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '3天前'
          },
          {
            id: '006',
            title: '业务公告',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '1月前'
          },
          {
            id: '007',
            title: '业务公告',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '3月前'
          },
          {
            id: '008',
            title: '报表模板',
            content: '报表新模板正式发布，欢迎使用',
            time: '2小时'
          },
          {
            id: '009',
            title: '业务公告',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '3天前'
          },
          {
            id: '010',
            title: '业务公告1',
            content: '报表新模板正式发布，欢迎使用报表新模板正式发布，欢迎使用',
            time: '1月前'
          },
          {
            id: '011',
            title: '业务公告1',
            content: '最后一条，欢迎使用报表新模板正式发布，欢迎使用',
            time: '3月前'
          }
        ]
      },

      directoryNav: {
        navTitle: '模板汇总数据',
        reportImgBg: require('./img/wb-banner1.png'),
        directoryList: [
          {
            url: require('./img/data-undo.png'),
            directoryName: '报表分类',
            directoryNum: 24
          },
          {
            url: require('./img/data-format.png'),
            directoryName: '报表格式',
            directoryNum: 67
          },
          {
            url: require('./img/data-template.png'),
            directoryName: '已经发布',
            directoryNum: 39
          },
          {
            url: require('./img/data-task.png'),
            directoryName: '任务完成',
            directoryNum: 3
          }
        ]
      }
    };
    this.pageModel = {
      tabActiveName: '0',
      tabList: [
        {
          id: '001',
          tabId: '0',
          tabName: '我的代办',
          // 下拉过去的数据
          filterList: [
            {
              selectedVal: 1,
              idField: 'id',
              textField: 'text',
              select: () => {},
              data: [
                {
                  id: 1,
                  text: '近一个月'
                },
                {
                  id: 2,
                  text: '近两个月'
                }
              ]
            },
            {
              selectedVal: 2,
              idField: 'id',
              textField: 'text',
              select: () => {},
              data: [
                {
                  id: 1,
                  text: '正常'
                },
                {
                  id: 2,
                  text: '异常'
                }
              ]
            },
            {
              selectedVal: 1,
              idField: 'id',
              textField: 'text',
              select: () => {},
              data: [
                {
                  id: 1,
                  text: '业务类型'
                },
                {
                  id: 2,
                  text: '运维类型'
                }
              ]
            }
          ],
          // 排序的数据
          sortBtnList: [
            {
              label: '到达时间',
              icon: 'el-icon-sort-down',
              callback: () => {}
            },
            {
              label: '业务类型',
              icon: '',
              callback: () => {}
            },
            {
              label: '金额',
              icon: '',
              callback: () => {}
            }
          ],
          // 搜索的数据
          operateBtnList: {
            searchVal: '',
            btnList: [
              {
                label: '批量审批',
                icon: 'el-icon-s-check',
                callback: () => {}
              },
              {
                label: '导出',
                icon: 'el-icon-download',
                callback: () => {}
              },
              {
                label: '',
                icon: 'el-icon-refresh',
                callback: () => {}
              }
            ],
            search: () => {}
          },
          //折叠列表的数据
          listFold: {}
        },
        {
          id: '002',
          tabId: '1',
          tabName: '我的已办',
          filterList: [],
          sortBtnList: [],
          operateBtnList: {
            searchVal: '',
            btnList: []
          },
          listFold: {}
        }
      ]
    };
  }
  // 走马灯的数据
  getCarouselItemData() {
    return {
      carouselItemOne: [
        {
          id: 1,
          icon: 'el-icon-document',
          name: '报表编辑'
        },
        {
          id: 2,
          icon: 'el-icon-set-up',
          name: '配置中心'
        },
        {
          id: 3,
          icon: 'el-icon-chat-dot-square',
          name: '报表数据分析'
        },
        {
          id: 4,
          icon: 'el-icon-help',
          name: '协同任务管理'
        },
        {
          id: 5,
          icon: 'el-icon-folder-opened',
          name: '报表服务目录'
        },
        {
          id: 6,
          icon: 'el-icon-chat-dot-square',
          name: '自助分析'
        },
        {
          id: 7,
          icon: 'el-icon-download',
          name: '资源下载'
        },
        {
          id: 8,
          icon: 'el-icon-s-check',
          name: '审批记录'
        }
      ],
      carouselItemTwo: [
        {
          id: 7,
          name: '报表编辑'
        }
      ]
    };
  }

  getCarouselData() {
    const { carouselList } = this.metaModel.commonFunction;
    let carouselItem = this.getCarouselItemData();
    let carouselItemValList = Object.values(carouselItem);
    carouselItemValList.forEach(child => carouselList.push(child));
  }
  // 折叠列表的数据
  async getListFold() {
    return {
      tabActiveName: '0',
      currentPage: 1,
      pageSize: 10,
      pageSizes: [10, 20, 50, 100],
      layout: 'total, sizes, prev, pager, next, jumper',
      contentList: [
        ,
        {
          foldTitleDay: '今天',
          foldTitleDate: '2023-04-01',
          foldTitleSeparator: '|',
          foldTitleTotalRecord: '共 3条记录',
          isFold: true,
          list: [
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '报表审核申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            },
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '模板发布申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              isFold: true,
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            }
          ]
        },
        {
          foldTitleDay: '今天',
          foldTitleDate: '2023-04-02',
          foldTitleSeparator: '|',
          foldTitleTotalRecord: '共 4条记录',
          isFold: true,
          list: [
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '报表审核申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            },
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '模板发布申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            }
          ]
        },
        {
          foldTitleDay: '今天',
          foldTitleDate: '2023-04-02',
          foldTitleSeparator: '|',
          foldTitleTotalRecord: '共 4条记录',
          isFold: true,
          list: [
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '报表审核申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            },
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '模板发布申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            }
          ]
        },
        {
          foldTitleDay: '今天',
          foldTitleDate: '2023-04-03',
          foldTitleSeparator: '|',
          foldTitleTotalRecord: '共 4条记录',
          isFold: true,
          list: [
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '报表审核申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            },
            {
              listImage: require('./img/data-undo.png'),
              listTitle: '模板发布申请单',
              listNumber: '2023040101',
              listContent: '由某集团某公司业务用户陈雨若  于2023年04月1日传递，停留1天',
              listOperateBtnList: [
                {
                  label: '传递',
                  callback: () => {}
                },
                {
                  label: '作废',
                  callback: () => {}
                },
                {
                  label: '查看详情',
                  callback: () => {}
                },
                {
                  label: '查看流程',
                  callback: () => {}
                }
              ]
            }
          ]
        }
      ]
    };
  }
  //添加listFold数据到tab的中
  async addDataToTab() {
    let listFold = await this.getListFold();
    const { tabList } = this.pageModel;
    tabList.forEach((child: any, idx: number) => {
      child.listFold = idx < 1 ? listFold : child.listFold;
    });
  }

  getData() {
    this.getCarouselData();
    this.addDataToTab();
    return {
      metaModel: this.metaModel,
      pageModel: this.pageModel
    };
  }
}
