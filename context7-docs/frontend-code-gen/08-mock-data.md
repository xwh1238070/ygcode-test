# YJPL前端代码生成智能体 - Mock数据配置

## Mock概述

Mock是一种在前后端分离开发中常用的技术，用于模拟后端API接口返回的数据。在YJPL框架中，Mock机制允许前端开发人员在后端API尚未完成或不可用的情况下，继续进行前端开发和测试工作。

### Mock的作用

- **加速开发流程**：不需要等待后端API完成，前端可以独立开发
- **提高开发效率**：避免因后端服务不稳定导致的开发中断
- **便于测试**：可以模拟各种数据场景，包括边界条件和错误情况
- **降低依赖**：减少对后端服务的依赖，提高开发灵活性

## Mock配置

### 目录结构

```
src
 |- mock      mock数据目录
 |     |- index.ts  主要的mock配置文件
```

### 基本配置

在 `src/mock/index.ts` 文件中配置mock规则：

```typescript
export default {
  doGet: {
    // GET请求拦截
  },
  
  doPost: {
    // POST请求拦截
  }
};
```

## Mock规则详解

### GET请求拦截

```javascript
doGet: {
  '/api/users': {
    data(param: any) {
      // param 包含请求参数
      return [
        { id: 1, name: '张三', email: 'zhangsan@example.com' },
        { id: 2, name: '李四', email: 'lisi@example.com' }
      ];
    }
  }
}
```

### POST请求拦截

```javascript
doPost: {
  '/api/user/add': {
    data(param: any) {
      // param 包含请求体数据
      return {
        success: true,
        message: '添加成功',
        data: {
          id: Date.now(),
          ...param
        }
      };
    }
  }
}
```

## 高级用法

### 1. 动态生成数据

```javascript
doGet: {
  '/api/users': {
    data(param: any) {
      const users = [];
      for (let i = 1; i <= 100; i++) {
        users.push({
          id: i,
          name: `用户${i}`,
          email: `user${i}@example.com`,
          age: 20 + Math.floor(Math.random() * 40),
          status: Math.random() > 0.5 ? 1 : 0
        });
      }
      return users;
    }
  }
}
```

### 2. 模拟延迟响应

```javascript
doGet: {
  '/api/slow-data': {
    data(param: any) {
      return new Promise((resolve) => {
        setTimeout(() => {
          resolve({
            message: '延迟返回的数据'
          });
        }, 2000); // 延迟2秒返回
      });
    }
  }
}
```

### 3. 根据参数返回不同数据

```javascript
doPost: {
  '/api/user/info': {
    data(param: any) {
      const { userId } = param;
      
      if (userId === '1') {
        return {
          id: '1',
          name: '管理员',
          role: 'admin',
          permissions: ['user:add', 'user:edit', 'user:delete']
        };
      } else {
        return {
          id: userId,
          name: '普通用户',
          role: 'user',
          permissions: ['user:view']
        };
      }
    }
  }
}
```

### 4. 模拟错误情况

```javascript
doGet: {
  '/api/error-demo': {
    data(param: any) {
      // 模拟随机错误
      if (Math.random() > 0.7) {
        throw new Error('服务器内部错误');
      }
      
      return {
        success: true,
        data: []
      };
    }
  }
}
```

### 5. 分页数据模拟

```javascript
doPost: {
  '/api/data/page': {
    data(param: any) {
      const { pageSize = 20, pageNum = 1, keyword = '' } = param;
      const total = 100;
      
      // 生成数据
      const allData = [];
      for (let i = 1; i <= total; i++) {
        allData.push({
          id: i,
          name: `项目${i}`,
          description: `这是项目${i}的描述`,
          status: Math.random() > 0.5 ? 1 : 0,
          createTime: new Date(Date.now() - Math.random() * 30 * 24 * 60 * 60 * 1000).toISOString()
        });
      }
      
      // 关键词过滤
      let filteredData = allData;
      if (keyword) {
        filteredData = allData.filter(item => 
          item.name.includes(keyword) || item.description.includes(keyword)
        );
      }
      
      // 分页
      const startIndex = (pageNum - 1) * pageSize;
      const endIndex = Math.min(startIndex + pageSize, filteredData.length);
      const list = filteredData.slice(startIndex, endIndex);
      
      return {
        list,
        total: filteredData.length,
        pageSize,
        pageNum,
        pages: Math.ceil(filteredData.length / pageSize)
      };
    }
  }
}
```

### 6. 文件上传模拟

```javascript
doPost: {
  '/api/upload': {
    data(param: any) {
      const { fileName, fileSize } = param;
      
      return {
        success: true,
        data: {
          fileId: 'mock_file_' + Date.now(),
          fileName: fileName || 'unknown.file',
          fileSize: fileSize || 0,
          fileUrl: `https://example.com/files/${Date.now()}.jpg`,
          uploadTime: new Date().toISOString()
        }
      };
    }
  }
}
```

### 7. 登录认证模拟

```javascript
doPost: {
  '/api/login': {
    data(param: any) {
      const { username, password } = param;
      
      // 模拟用户数据库
      const users = {
        'admin': { password: 'admin123', role: 'admin', name: '管理员' },
        'user': { password: 'user123', role: 'user', name: '普通用户' }
      };
      
      const user = users[username];
      
      if (!user) {
        throw new Error('用户不存在');
      }
      
      if (user.password !== password) {
        throw new Error('密码错误');
      }
      
      return {
        success: true,
        data: {
          token: 'mock_token_' + Date.now(),
          userInfo: {
            id: username,
            username: username,
            name: user.name,
            role: user.role
          }
        }
      };
    }
  }
}
```

## 实战示例

### 示例1：用户管理Mock

```javascript
// src/mock/user.ts
export default {
  doGet: {
    // 获取用户列表
    '/api/user/list': {
      data(param: any) {
        const { pageSize = 20, pageNum = 1, keyword = '', status } = param;
        
        // 模拟用户数据
        const users = [];
        for (let i = 1; i <= 100; i++) {
          users.push({
            id: i,
            username: `user${i}`,
            name: `用户${i}`,
            email: `user${i}@example.com`,
            phone: `138${String(i).padStart(8, '0')}`,
            status: Math.random() > 0.5 ? 1 : 0,
            createTime: new Date(Date.now() - Math.random() * 365 * 24 * 60 * 60 * 1000).toISOString()
          });
        }
        
        // 过滤
        let filteredUsers = users;
        if (keyword) {
          filteredUsers = users.filter(u => 
            u.username.includes(keyword) || 
            u.name.includes(keyword) || 
            u.email.includes(keyword)
          );
        }
        if (status !== undefined && status !== null) {
          filteredUsers = filteredUsers.filter(u => u.status === status);
        }
        
        // 分页
        const start = (pageNum - 1) * pageSize;
        const end = start + pageSize;
        
        return {
          list: filteredUsers.slice(start, end),
          total: filteredUsers.length,
          pageSize,
          pageNum
        };
      }
    },
    
    // 获取用户详情
    '/api/user/detail': {
      data(param: any) {
        const { id } = param;
        return {
          id,
          username: `user${id}`,
          name: `用户${id}`,
          email: `user${id}@example.com`,
          phone: `138${String(id).padStart(8, '0')}`,
          status: 1,
          roles: ['user'],
          createTime: new Date().toISOString()
        };
      }
    }
  },
  
  doPost: {
    // 添加用户
    '/api/user/add': {
      data(param: any) {
        return {
          success: true,
          message: '添加成功',
          data: {
            id: Date.now(),
            ...param,
            createTime: new Date().toISOString()
          }
        };
      }
    },
    
    // 更新用户
    '/api/user/update': {
      data(param: any) {
        return {
          success: true,
          message: '更新成功',
          data: param
        };
      }
    },
    
    // 删除用户
    '/api/user/delete': {
      data(param: any) {
        return {
          success: true,
          message: '删除成功'
        };
      }
    }
  }
};
```

### 示例2：订单管理Mock

```javascript
// src/mock/order.ts
export default {
  doGet: {
    // 获取订单列表
    '/api/order/list': {
      data(param: any) {
        const { pageSize = 20, pageNum = 1 } = param;
        
        const orders = [];
        for (let i = 1; i <= 50; i++) {
          orders.push({
            id: i,
            orderNo: `ORD${Date.now()}${i}`,
            customerName: `客户${i}`,
            amount: Math.floor(Math.random() * 100000) / 100,
            status: ['pending', 'processing', 'completed', 'cancelled'][Math.floor(Math.random() * 4)],
            createTime: new Date(Date.now() - Math.random() * 30 * 24 * 60 * 60 * 1000).toISOString()
          });
        }
        
        const start = (pageNum - 1) * pageSize;
        const end = start + pageSize;
        
        return {
          list: orders.slice(start, end),
          total: orders.length,
          pageSize,
          pageNum
        };
      }
    },
    
    // 获取订单详情
    '/api/order/detail': {
      data(param: any) {
        const { id } = param;
        return {
          id,
          orderNo: `ORD${Date.now()}${id}`,
          customerName: `客户${id}`,
          customerPhone: '13800138000',
          amount: 10000,
          status: 'processing',
          createTime: new Date().toISOString(),
          items: [
            {
              id: 1,
              productName: '产品A',
              quantity: 10,
              price: 500,
              amount: 5000
            },
            {
              id: 2,
              productName: '产品B',
              quantity: 20,
              price: 250,
              amount: 5000
            }
          ]
        };
      }
    }
  },
  
  doPost: {
    // 创建订单
    '/api/order/create': {
      data(param: any) {
        return {
          success: true,
          message: '订单创建成功',
          data: {
            id: Date.now(),
            orderNo: `ORD${Date.now()}`,
            ...param,
            status: 'pending',
            createTime: new Date().toISOString()
          }
        };
      }
    },
    
    // 更新订单状态
    '/api/order/updateStatus': {
      data(param: any) {
        return {
          success: true,
          message: '状态更新成功'
        };
      }
    }
  }
};
```

### 示例3：组合Mock配置

```javascript
// src/mock/index.ts
import userMock from './user';
import orderMock from './order';
import productMock from './product';

export default {
  doGet: {
    ...userMock.doGet,
    ...orderMock.doGet,
    ...productMock.doGet
  },
  doPost: {
    ...userMock.doPost,
    ...orderMock.doPost,
    ...productMock.doPost
  }
};
```

## 最佳实践

### 1. 组织Mock数据

对于复杂项目，建议将mock数据按模块分离：

```
src/mock/
  ├── index.ts       # 主配置文件
  ├── user.ts        # 用户模块
  ├── order.ts       # 订单模块
  ├── product.ts     # 产品模块
  └── common.ts      # 公共数据
```

### 2. 保持数据结构一致

Mock数据的结构应与实际API返回的数据结构保持一致：

```javascript
// ✓ 正确：与实际API结构一致
{
  success: true,
  data: {
    list: [],
    total: 0
  },
  message: ''
}

// ✗ 错误：结构不一致
{
  items: [],
  count: 0
}
```

### 3. 使用真实的数据格式

```javascript
// ✓ 正确：使用真实的日期格式
createTime: new Date().toISOString()

// ✗ 错误：使用简化的格式
createTime: '2024-12-04'
```

### 4. 模拟真实的业务逻辑

```javascript
doPost: {
  '/api/user/add': {
    data(param: any) {
      // 验证必填字段
      if (!param.username) {
        throw new Error('用户名不能为空');
      }
      
      if (!param.email) {
        throw new Error('邮箱不能为空');
      }
      
      // 验证邮箱格式
      const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailReg.test(param.email)) {
        throw new Error('邮箱格式不正确');
      }
      
      return {
        success: true,
        data: {
          id: Date.now(),
          ...param
        }
      };
    }
  }
}
```

### 5. 及时更新Mock数据

当后端API发生变化时，应及时更新对应的Mock数据。

### 6. 使用环境变量控制

```javascript
// 根据环境决定是否使用Mock
if (process.env.NODE_ENV === 'development') {
  // 使用Mock数据
  import('./mock');
}
```

## 注意事项

1. **不要在Mock数据中包含敏感信息**
2. **Mock数据只是开发阶段的辅助工具**
3. **最终产品应该与实际API进行充分测试**
4. **注意Mock数据的性能影响**
5. **保持Mock数据的可维护性**

## 常见问题

### Q1: Mock数据不生效？

**解决方案：**
1. 检查API路径是否正确
2. 检查请求方法（GET/POST）是否匹配
3. 检查Mock配置是否正确导入
4. 检查浏览器控制台是否有错误

### Q2: 如何切换到真实API？

```javascript
// 方法1：使用环境变量
const API_BASE_URL = process.env.VUE_APP_USE_MOCK === 'true' 
  ? '/mock-api' 
  : '/real-api';

// 方法2：配置开关
const useMock = false;
if (useMock) {
  import('./mock');
}
```

### Q3: 如何模拟复杂的业务场景？

使用状态管理和条件判断：

```javascript
let orderStatus = 'pending';

doPost: {
  '/api/order/submit': {
    data(param: any) {
      orderStatus = 'processing';
      return { success: true };
    }
  },
  
  '/api/order/status': {
    data(param: any) {
      return { status: orderStatus };
    }
  }
}
```

## 下一步

- 了解 [最佳实践](./09-best-practices.md)
- 查看 [实战示例](./10-examples.md)
- 学习 [常见问题解决](./11-troubleshooting.md)
