import { YJBusiness } from 'yjpl-core';
import Model from './Model';

export default class OrderQueryBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  // 获取初始查询数据
  async getQueryData() {
    return {};
  }

  // 获取树形数据
  async getTreeData() {
    // 返回树形结构数据
    return [];
  }

  // 获取表格数据
  async getGridData(pageSize?: number, pageIndex?: number) {
    // 实际项目中调用后端 API
    // const response = await api.getOrderList({
    //   pageSize,
    //   pageIndex,
    //   ...this.dataModel.query
    // });
    // return response.data;

    // Mock 数据示例
    return [
      {
        orderNo: 'ORD-2024-001',
        customerName: '张三',
        amount: 5000.00,
        status: 1,
        createTime: '2024-12-01 10:30:00'
      },
      {
        orderNo: 'ORD-2024-002',
        customerName: '李四',
        amount: 8000.00,
        status: 2,
        createTime: '2024-12-02 14:15:00'
      },
      {
        orderNo: 'ORD-2024-003',
        customerName: '王五',
        amount: 3500.00,
        status: 0,
        createTime: '2024-12-03 09:45:00'
      },
      {
        orderNo: 'ORD-2024-004',
        customerName: '赵六',
        amount: 12000.00,
        status: 3,
        createTime: '2024-12-04 16:20:00'
      },
      {
        orderNo: 'ORD-2024-005',
        customerName: '孙七',
        amount: 6500.00,
        status: 1,
        createTime: '2024-12-04 11:00:00'
      }
    ];
  }

  // 获取总记录数
  async getTotalRecord() {
    return 5;
  }

  // 获取所有数据供 index.yjpl 使用
  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }
}
