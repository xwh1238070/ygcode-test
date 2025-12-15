import { YJBusiness } from 'yjpl-core';
import Model from './Model';

export default class UserManagementBusiness extends YJBusiness {
  constructor() {
    super();
    this.dataModel = Model.dataModel;
    this.stateModel = Model.stateModel;
  }

  /**
   * 获取初始查询数据
   * @returns 查询条件初始值
   */
  async getQueryData() {
    return {
      username: '',
      realName: '',
      department: '',
      role: '',
      status: '',
      createTime: []
    };
  }

  /**
   * 获取树形数据（如需要）
   * @returns 树形结构数据
   */
  async getTreeData() {
    return [];
  }

  /**
   * 获取表格数据
   * @param pageSize 每页条数
   * @param pageIndex 当前页码
   * @returns 用户列表数据
   */
  async getGridData(pageSize?: number, pageIndex?: number) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/list', {
    //   pageSize: pageSize || 20,
    //   pageIndex: pageIndex || 1,
    //   ...this.dataModel.query
    // });
    // return response.data;

    // Mock 数据示例
    const mockData = [
      {
        userId: 'U001',
        username: 'admin',
        realName: '张三',
        department: '技术部',
        role: '系统管理员',
        phone: '13800138001',
        email: 'zhangsan@example.com',
        status: '启用',
        createTime: '2024-01-15 10:30:00',
        lastLoginTime: '2024-12-15 09:15:00'
      },
      {
        userId: 'U002',
        username: 'lisi',
        realName: '李四',
        department: '市场部',
        role: '部门经理',
        phone: '13800138002',
        email: 'lisi@example.com',
        status: '启用',
        createTime: '2024-02-20 14:20:00',
        lastLoginTime: '2024-12-14 16:45:00'
      },
      {
        userId: 'U003',
        username: 'wangwu',
        realName: '王五',
        department: '财务部',
        role: '普通用户',
        phone: '13800138003',
        email: 'wangwu@example.com',
        status: '启用',
        createTime: '2024-03-10 09:00:00',
        lastLoginTime: '2024-12-13 11:20:00'
      },
      {
        userId: 'U004',
        username: 'zhaoliu',
        realName: '赵六',
        department: '人力资源部',
        role: '普通用户',
        phone: '13800138004',
        email: 'zhaoliu@example.com',
        status: '启用',
        createTime: '2024-04-05 15:30:00',
        lastLoginTime: '2024-12-12 14:10:00'
      },
      {
        userId: 'U005',
        username: 'sunqi',
        realName: '孙七',
        department: '行政部',
        role: '普通用户',
        phone: '13800138005',
        email: 'sunqi@example.com',
        status: '禁用',
        createTime: '2024-05-12 11:15:00',
        lastLoginTime: '2024-11-20 10:30:00'
      },
      {
        userId: 'U006',
        username: 'zhouba',
        realName: '周八',
        department: '技术部',
        role: '普通用户',
        phone: '13800138006',
        email: 'zhouba@example.com',
        status: '启用',
        createTime: '2024-06-18 13:45:00',
        lastLoginTime: '2024-12-15 08:00:00'
      },
      {
        userId: 'U007',
        username: 'wujiu',
        realName: '吴九',
        department: '市场部',
        role: '普通用户',
        phone: '13800138007',
        email: 'wujiu@example.com',
        status: '启用',
        createTime: '2024-07-22 16:20:00',
        lastLoginTime: '2024-12-14 15:30:00'
      },
      {
        userId: 'U008',
        username: 'zhengshi',
        realName: '郑十',
        department: '财务部',
        role: '部门经理',
        phone: '13800138008',
        email: 'zhengshi@example.com',
        status: '启用',
        createTime: '2024-08-30 10:00:00',
        lastLoginTime: '2024-12-15 09:45:00'
      },
      {
        userId: 'U009',
        username: 'guest001',
        realName: '访客一',
        department: '行政部',
        role: '访客',
        phone: '13800138009',
        email: 'guest001@example.com',
        status: '启用',
        createTime: '2024-09-15 14:30:00',
        lastLoginTime: '2024-12-10 10:00:00'
      },
      {
        userId: 'U010',
        username: 'test001',
        realName: '测试用户',
        department: '技术部',
        role: '普通用户',
        phone: '13800138010',
        email: 'test001@example.com',
        status: '禁用',
        createTime: '2024-10-20 09:30:00',
        lastLoginTime: '2024-11-15 16:00:00'
      }
    ];

    // 根据查询条件过滤数据
    let filteredData = mockData;
    
    if (this.dataModel.query.username) {
      filteredData = filteredData.filter(item => 
        item.username.includes(this.dataModel.query.username)
      );
    }
    
    if (this.dataModel.query.realName) {
      filteredData = filteredData.filter(item => 
        item.realName.includes(this.dataModel.query.realName)
      );
    }
    
    if (this.dataModel.query.department) {
      filteredData = filteredData.filter(item => 
        item.department === this.dataModel.query.department
      );
    }
    
    if (this.dataModel.query.role) {
      filteredData = filteredData.filter(item => 
        item.role === this.dataModel.query.role
      );
    }
    
    if (this.dataModel.query.status) {
      filteredData = filteredData.filter(item => 
        item.status === (this.dataModel.query.status === '1' ? '启用' : '禁用')
      );
    }

    // 分页处理
    const start = ((pageIndex || 1) - 1) * (pageSize || 20);
    const end = start + (pageSize || 20);
    
    return filteredData.slice(start, end);
  }

  /**
   * 获取总记录数
   * @returns 总记录数
   */
  async getTotalRecord() {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/count', {
    //   ...this.dataModel.query
    // });
    // return response.data;

    // Mock 数据示例
    return 10;
  }

  /**
   * 获取所有数据供 index.yjpl 使用
   * @returns 包含所有模型的对象
   */
  getData() {
    return {
      metaModel: Model.metaModel,
      dataModel: this.dataModel,
      stateModel: this.stateModel
    };
  }

  /**
   * 新增用户
   * @param userData 用户数据
   */
  async addUser(userData: any) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/add', userData);
    // return response;
    
    console.log('新增用户:', userData);
    return { success: true, message: '新增成功' };
  }

  /**
   * 编辑用户
   * @param userId 用户ID
   * @param userData 用户数据
   */
  async editUser(userId: string, userData: any) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/edit', {
    //   userId,
    //   ...userData
    // });
    // return response;
    
    console.log('编辑用户:', userId, userData);
    return { success: true, message: '编辑成功' };
  }

  /**
   * 删除用户
   * @param userIds 用户ID数组
   */
  async deleteUser(userIds: string[]) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/delete', {
    //   userIds
    // });
    // return response;
    
    console.log('删除用户:', userIds);
    return { success: true, message: '删除成功' };
  }

  /**
   * 重置密码
   * @param userIds 用户ID数组
   */
  async resetPassword(userIds: string[]) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/resetPassword', {
    //   userIds
    // });
    // return response;
    
    console.log('重置密码:', userIds);
    return { success: true, message: '密码已重置为默认密码' };
  }

  /**
   * 启用用户
   * @param userIds 用户ID数组
   */
  async enableUser(userIds: string[]) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/enable', {
    //   userIds
    // });
    // return response;
    
    console.log('启用用户:', userIds);
    return { success: true, message: '启用成功' };
  }

  /**
   * 禁用用户
   * @param userIds 用户ID数组
   */
  async disableUser(userIds: string[]) {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/disable', {
    //   userIds
    // });
    // return response;
    
    console.log('禁用用户:', userIds);
    return { success: true, message: '禁用成功' };
  }

  /**
   * 导出用户数据
   */
  async exportData() {
    // 实际项目中调用后端 API
    // const response = await this.$http.post('/api/user/export', {
    //   ...this.dataModel.query
    // });
    // return response;
    
    console.log('导出数据');
    return { success: true, message: '导出成功' };
  }

  /**
   * 导入用户数据
   * @param file 文件对象
   */
  async importData(file: any) {
    // 实际项目中调用后端 API
    // const formData = new FormData();
    // formData.append('file', file);
    // const response = await this.$http.post('/api/user/import', formData);
    // return response;
    
    console.log('导入数据:', file);
    return { success: true, message: '导入成功' };
  }
}
