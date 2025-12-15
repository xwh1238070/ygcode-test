/**
 * 用户管理页面 Mock 数据配置
 * 用于前端独立开发和测试
 */

export default {
  // GET 请求拦截
  doGet: {
    // 获取用户列表
    '/api/user/list': {
      data(param) {
        const mockUsers = [
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

        // 根据查询条件过滤
        let filteredData = mockUsers;

        if (param.username) {
          filteredData = filteredData.filter(user =>
            user.username.includes(param.username)
          );
        }

        if (param.realName) {
          filteredData = filteredData.filter(user =>
            user.realName.includes(param.realName)
          );
        }

        if (param.department) {
          filteredData = filteredData.filter(user =>
            user.department === param.department
          );
        }

        if (param.role) {
          filteredData = filteredData.filter(user =>
            user.role === param.role
          );
        }

        if (param.status) {
          const statusText = param.status === '1' ? '启用' : '禁用';
          filteredData = filteredData.filter(user =>
            user.status === statusText
          );
        }

        // 分页处理
        const pageSize = param.pageSize || 20;
        const pageIndex = param.pageIndex || 1;
        const start = (pageIndex - 1) * pageSize;
        const end = start + pageSize;

        return filteredData.slice(start, end);
      }
    },

    // 获取用户总数
    '/api/user/count': {
      data(param) {
        // 实际应根据查询条件返回对应的总数
        return 10;
      }
    }
  },

  // POST 请求拦截
  doPost: {
    // 新增用户
    '/api/user/add': {
      data(param) {
        console.log('Mock: 新增用户', param);
        return {
          success: true,
          message: '新增用户成功',
          data: {
            userId: 'U' + String(Date.now()).slice(-3)
          }
        };
      }
    },

    // 编辑用户
    '/api/user/edit': {
      data(param) {
        console.log('Mock: 编辑用户', param);
        return {
          success: true,
          message: '编辑用户成功'
        };
      }
    },

    // 删除用户
    '/api/user/delete': {
      data(param) {
        console.log('Mock: 删除用户', param.userIds);
        return {
          success: true,
          message: `成功删除 ${param.userIds.length} 个用户`
        };
      }
    },

    // 重置密码
    '/api/user/resetPassword': {
      data(param) {
        console.log('Mock: 重置密码', param.userIds);
        return {
          success: true,
          message: `成功重置 ${param.userIds.length} 个用户的密码`
        };
      }
    },

    // 启用用户
    '/api/user/enable': {
      data(param) {
        console.log('Mock: 启用用户', param.userIds);
        return {
          success: true,
          message: `成功启用 ${param.userIds.length} 个用户`
        };
      }
    },

    // 禁用用户
    '/api/user/disable': {
      data(param) {
        console.log('Mock: 禁用用户', param.userIds);
        return {
          success: true,
          message: `成功禁用 ${param.userIds.length} 个用户`
        };
      }
    },

    // 导出用户数据
    '/api/user/export': {
      data(param) {
        console.log('Mock: 导出用户数据', param);
        return {
          success: true,
          message: '导出成功',
          data: {
            fileUrl: '/download/users_export_' + Date.now() + '.xlsx'
          }
        };
      }
    },

    // 导入用户数据
    '/api/user/import': {
      data(param) {
        console.log('Mock: 导入用户数据', param);
        return {
          success: true,
          message: '导入成功',
          data: {
            successCount: 10,
            failCount: 0
          }
        };
      }
    }
  }
};
