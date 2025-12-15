/**
 * 任务管理页面 - Mock数据配置
 * 用于开发阶段模拟后端API
 */

// 生成模拟任务数据
function generateMockTasks(count = 50) {
  const tasks = [];
  const statuses = ['pending', 'in_progress', 'completed', 'cancelled'];
  const priorities = ['high', 'medium', 'low'];
  const assignees = ['张三', '李四', '王五', '赵六', '钱七', '孙八', '周九', '吴十'];
  
  for (let i = 1; i <= count; i++) {
    const status = statuses[Math.floor(Math.random() * statuses.length)];
    const createTime = new Date(Date.now() - Math.random() * 30 * 24 * 60 * 60 * 1000);
    const dueDate = new Date(createTime.getTime() + Math.random() * 15 * 24 * 60 * 60 * 1000);
    
    tasks.push({
      id: i,
      taskName: `任务${i}`,
      description: `这是任务${i}的详细描述，包含了任务的具体内容和要求。`,
      status: status,
      priority: priorities[Math.floor(Math.random() * priorities.length)],
      assignee: assignees[Math.floor(Math.random() * assignees.length)],
      createTime: createTime.toISOString(),
      dueDate: dueDate.toISOString().split('T')[0],
      completeTime: status === 'completed' 
        ? new Date(createTime.getTime() + Math.random() * 10 * 24 * 60 * 60 * 1000).toISOString()
        : null
    });
  }
  
  return tasks;
}

// 全局任务数据
let mockTasksData = generateMockTasks(50);

export default {
  doGet: {
    // 获取任务列表
    '/api/task/list': {
      data(param) {
        const {
          pageSize = 20,
          pageIndex = 1,
          taskName = '',
          status = '',
          priority = '',
          assignee = '',
          startDate = '',
          endDate = ''
        } = param;
        
        // 过滤数据
        let filteredTasks = mockTasksData;
        
        // 按任务名称过滤
        if (taskName) {
          filteredTasks = filteredTasks.filter(task =>
            task.taskName.includes(taskName) || task.description.includes(taskName)
          );
        }
        
        // 按状态过滤
        if (status) {
          filteredTasks = filteredTasks.filter(task => task.status === status);
        }
        
        // 按优先级过滤
        if (priority) {
          filteredTasks = filteredTasks.filter(task => task.priority === priority);
        }
        
        // 按负责人过滤
        if (assignee) {
          filteredTasks = filteredTasks.filter(task => task.assignee.includes(assignee));
        }
        
        // 按日期范围过滤
        if (startDate && endDate) {
          filteredTasks = filteredTasks.filter(task => {
            const taskDate = new Date(task.createTime).getTime();
            const start = new Date(startDate).getTime();
            const end = new Date(endDate).getTime();
            return taskDate >= start && taskDate <= end;
          });
        }
        
        // 分页
        const total = filteredTasks.length;
        const startIndex = (pageIndex - 1) * pageSize;
        const endIndex = Math.min(startIndex + pageSize, total);
        const list = filteredTasks.slice(startIndex, endIndex);
        
        return {
          list,
          total,
          pageSize,
          pageIndex,
          pages: Math.ceil(total / pageSize)
        };
      }
    }
  },
  
  doPost: {
    // 新增任务
    '/api/task/add': {
      data(param) {
        const newTask = {
          id: mockTasksData.length + 1,
          taskName: param.taskName,
          description: param.description || '',
          status: 'pending',
          priority: param.priority || 'medium',
          assignee: param.assignee || '',
          createTime: new Date().toISOString(),
          dueDate: param.dueDate || null,
          completeTime: null
        };
        
        mockTasksData.push(newTask);
        
        return {
          success: true,
          message: '新增任务成功',
          data: newTask
        };
      }
    },
    
    // 更新任务
    '/api/task/update': {
      data(param) {
        const index = mockTasksData.findIndex(task => task.id === param.id);
        
        if (index === -1) {
          throw new Error('任务不存在');
        }
        
        mockTasksData[index] = {
          ...mockTasksData[index],
          ...param,
          id: mockTasksData[index].id, // 保持ID不变
          createTime: mockTasksData[index].createTime // 保持创建时间不变
        };
        
        return {
          success: true,
          message: '更新任务成功',
          data: mockTasksData[index]
        };
      }
    },
    
    // 删除任务
    '/api/task/delete': {
      data(param) {
        const { ids } = param;
        
        if (!ids || !Array.isArray(ids) || ids.length === 0) {
          throw new Error('请提供要删除的任务ID');
        }
        
        mockTasksData = mockTasksData.filter(task => !ids.includes(task.id));
        
        return {
          success: true,
          message: `成功删除 ${ids.length} 条任务`
        };
      }
    },
    
    // 完成任务
    '/api/task/complete': {
      data(param) {
        const { ids } = param;
        
        if (!ids || !Array.isArray(ids) || ids.length === 0) {
          throw new Error('请提供要完成的任务ID');
        }
        
        const completeTime = new Date().toISOString();
        let completedCount = 0;
        
        mockTasksData = mockTasksData.map(task => {
          if (ids.includes(task.id) && task.status !== 'completed' && task.status !== 'cancelled') {
            completedCount++;
            return {
              ...task,
              status: 'completed',
              completeTime: completeTime
            };
          }
          return task;
        });
        
        return {
          success: true,
          message: `成功完成 ${completedCount} 条任务`
        };
      }
    },
    
    // 导出任务
    '/api/task/export': {
      data(param) {
        // 实际项目中这里会生成Excel文件并返回下载链接
        console.log('导出任务参数:', param);
        
        return {
          success: true,
          message: '导出成功',
          data: {
            fileUrl: 'https://example.com/exports/tasks_' + Date.now() + '.xlsx',
            fileName: '任务列表_' + new Date().toISOString().split('T')[0] + '.xlsx'
          }
        };
      }
    }
  }
};
