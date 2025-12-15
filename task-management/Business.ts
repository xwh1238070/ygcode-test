import { YJBusiness } from 'yjpl-core';
import Model from './Model';

/**
 * 任务管理页面 - 业务逻辑类
 */
export default class TaskManagementBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  /**
   * 获取查询面板初始数据
   */
  async getQueryData() {
    return {
      taskName: '',
      status: '',
      priority: '',
      assignee: '',
      dateRange: []
    };
  }

  /**
   * 获取表格数据
   * @param pageSize 每页条数
   * @param pageIndex 页码
   */
  async getGridData(pageSize?: number, pageIndex?: number) {
    try {
      // 构建查询参数
      const params = {
        pageSize: pageSize || this.dataModel.pageSize,
        pageIndex: pageIndex || this.dataModel.pageIndex,
        taskName: this.dataModel.query.taskName,
        status: this.dataModel.query.status,
        priority: this.dataModel.query.priority,
        assignee: this.dataModel.query.assignee,
        startDate: this.dataModel.query.dateRange?.[0] || '',
        endDate: this.dataModel.query.dateRange?.[1] || ''
      };

      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.getTaskList(params);
      // return response.data.list;

      // 开发阶段使用Mock数据
      console.log('查询参数:', params);
      return [];
    } catch (error) {
      console.error('获取任务列表失败:', error);
      this.$message?.error('获取任务列表失败：' + error.message);
      return [];
    }
  }

  /**
   * 获取总记录数
   */
  async getTotalRecord() {
    try {
      // 构建查询参数
      const params = {
        taskName: this.dataModel.query.taskName,
        status: this.dataModel.query.status,
        priority: this.dataModel.query.priority,
        assignee: this.dataModel.query.assignee,
        startDate: this.dataModel.query.dateRange?.[0] || '',
        endDate: this.dataModel.query.dateRange?.[1] || ''
      };

      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.getTaskList({ ...params, pageSize: 1, pageIndex: 1 });
      // return response.data.total;

      // 开发阶段返回0
      return 0;
    } catch (error) {
      console.error('获取总记录数失败:', error);
      return 0;
    }
  }

  /**
   * 新增任务
   * @param taskData 任务数据
   */
  async addTask(taskData: any) {
    try {
      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.addTask(taskData);
      // return response.data;

      console.log('新增任务:', taskData);
      return { success: true, message: '新增成功' };
    } catch (error) {
      console.error('新增任务失败:', error);
      throw error;
    }
  }

  /**
   * 更新任务
   * @param taskData 任务数据
   */
  async updateTask(taskData: any) {
    try {
      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.updateTask(taskData);
      // return response.data;

      console.log('更新任务:', taskData);
      return { success: true, message: '更新成功' };
    } catch (error) {
      console.error('更新任务失败:', error);
      throw error;
    }
  }

  /**
   * 删除任务
   * @param ids 任务ID数组
   */
  async deleteTask(ids: number[]) {
    try {
      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.deleteTask(ids);
      // return response.data;

      console.log('删除任务:', ids);
      return { success: true, message: '删除成功' };
    } catch (error) {
      console.error('删除任务失败:', error);
      throw error;
    }
  }

  /**
   * 完成任务
   * @param ids 任务ID数组
   */
  async completeTask(ids: number[]) {
    try {
      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.completeTask(ids);
      // return response.data;

      console.log('完成任务:', ids);
      return { success: true, message: '任务已完成' };
    } catch (error) {
      console.error('完成任务失败:', error);
      throw error;
    }
  }

  /**
   * 导出任务列表
   */
  async exportTasks() {
    try {
      // 构建查询参数
      const params = {
        taskName: this.dataModel.query.taskName,
        status: this.dataModel.query.status,
        priority: this.dataModel.query.priority,
        assignee: this.dataModel.query.assignee,
        startDate: this.dataModel.query.dateRange?.[0] || '',
        endDate: this.dataModel.query.dateRange?.[1] || ''
      };

      // 调用后端API（实际项目中替换为真实API）
      // const response = await api.exportTasks(params);
      // 下载文件逻辑

      console.log('导出任务:', params);
      return { success: true, message: '导出成功' };
    } catch (error) {
      console.error('导出任务失败:', error);
      throw error;
    }
  }

  /**
   * 获取所有数据（供 index.yjpl 使用）
   */
  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }
}
