# YJPL前端代码生成智能体 - 最佳实践

## 代码组织

### 文件结构

```
your-page/
├── index.yjpl              # 主模板文件
├── Business.ts             # 业务逻辑类
├── Model.ts                # 数据模型
├── components/             # 子组件
│   ├── CustomDialog.yjpl
│   └── CustomPanel.yjpl
└── styles/                 # 样式文件
    └── index.scss
```

### 命名规范

**文件命名：**
- 组件文件：PascalCase（如 `UserList.yjpl`）
- 业务逻辑：PascalCase（如 `Business.ts`）
- 数据模型：PascalCase（如 `Model.ts`）
- 样式文件：kebab-case（如 `user-list.scss`）

**变量命名：**
- 变量和函数：camelCase（如 `userName`, `handleClick`）
- 常量：UPPER_SNAKE_CASE（如 `MAX_COUNT`）
- 类名：PascalCase（如 `UserBusiness`）
- CSS类名：kebab-case（如 `.user-list`）

## 数据模型设计

### ✓ 推荐做法

**1. 分离配置和数据**
```typescript
// ✓ 正确
export const metaModel = { /* 静态配置 */ };
export const dataModel = { /* 动态数据 */ };
export const stateModel = { /* 状态控制 */ };
```

**2. 使用TypeScript类型**
```typescript
// ✓ 正确
interface QueryData {
  userName: string;
  status: number | null;
}

export const dataModel = {
  query: {} as QueryData
};
```

**3. 提供合理的默认值**
```typescript
// ✓ 正确
export const dataModel = {
  query: {
    userName: '',
    status: null
  },
  grid: [],
  pageSize: 20,
  pageIndex: 1
};
```

### ✗ 避免做法

**1. 混合配置和数据**
```typescript
// ✗ 错误
export const model = {
  title: '用户列表',  // 配置
  users: [],          // 数据
  addDisabled: true   // 状态
};
```

**2. 在metaModel中存储动态数据**
```typescript
// ✗ 错误
export const metaModel = {
  query: [
    {
      name: 'userName',
      value: '张三'  // 错误：应该在dataModel中
    }
  ]
};
```

## 组件使用

### ✓ 推荐做法

**1. 按需引入组件**
```javascript
// ✓ 正确
import { Button, Input, Table } from 'yjpl-ui';
```

**2. 使用组件的默认配置**
```html
<!-- ✓ 正确 -->
<button type="primary">按钮</button>
```

**3. 合理使用组件事件**
```html
<!-- ✓ 正确 -->
<table :data="tableData" @selection-change="handleSelectionChange"></table>
```

### ✗ 避免做法

**1. 过度自定义组件样式**
```css
/* ✗ 避免 */
.el-button {
  /* 大量自定义样式 */
}
```

**2. 在循环中创建大量组件实例**
```html
<!-- ✗ 避免 -->
<div v-for="item in largeList">
  <heavy-component :data="item"></heavy-component>
</div>
```

## 性能优化

### 1. 大数据列表优化

**使用虚拟滚动：**
```typescript
// ✓ 推荐
grid: {
  virtual: true,
  virtualSize: 20,
  pageSize: 20
}
```

**使用分页：**
```typescript
// ✓ 推荐
grid: {
  pageSize: 20,
  showPagination: true
}
```

### 2. 表格列优化

**指定列宽：**
```typescript
// ✓ 推荐
columns: [
  { key: 'id', label: 'ID', width: 80 },
  { key: 'name', label: '名称', width: 150 }
]
```

**使用格式化：**
```typescript
// ✓ 推荐
columns: [
  { key: 'amount', label: '金额', formatType: 'money', scale: 2 }
]
```

### 3. 事件处理优化

**使用事件委托：**
```typescript
// ✓ 推荐
eventsModel: {
  grid: {
    'row-click': (row) => this.handleRowClick(row)
  }
}
```

**避免在模板中处理复杂逻辑：**
```html
<!-- ✗ 避免 -->
<button @click="complexLogic(item, index, event)">操作</button>

<!-- ✓ 推荐 -->
<button @click="handleClick(item)">操作</button>
```

### 4. 监听器优化

**深度监听必要字段：**
```typescript
// ✓ 推荐
watch: {
  'dataModel.query': {
    handler() { /* ... */ },
    deep: true
  }
}

// ✗ 避免
watch: {
  dataModel: {
    handler() { /* ... */ },
    deep: true
  }
}
```

### 5. 计算属性优化

**使用计算属性缓存：**
```typescript
// ✓ 推荐
computed: {
  filteredList() {
    return this.list.filter(item => item.status === 1);
  }
}

// ✗ 避免
methods: {
  getFilteredList() {
    return this.list.filter(item => item.status === 1);
  }
}
```

## 代码质量

### 1. 使用ESLint

**.eslintrc.js配置：**
```javascript
module.exports = {
  extends: [
    'plugin:vue/recommended',
    '@vue/typescript/recommended'
  ],
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off'
  }
};
```

### 2. 代码注释

**函数注释：**
```typescript
/**
 * 获取用户列表
 * @param pageSize 每页条数
 * @param pageIndex 页码
 * @returns 用户列表数据
 */
async getUserList(pageSize: number, pageIndex: number) {
  // 实现代码
}
```

**复杂逻辑注释：**
```typescript
// 计算订单总金额
// 1. 遍历所有订单项
// 2. 累加每项的金额
// 3. 应用折扣
const totalAmount = orderItems.reduce((sum, item) => {
  return sum + item.quantity * item.price;
}, 0) * discount;
```

### 3. 错误处理

**统一错误处理：**
```typescript
async getGridData() {
  try {
    const response = await api.getList();
    return response.data;
  } catch (error) {
    this.$message.error('获取数据失败：' + error.message);
    console.error('获取数据失败:', error);
    return [];
  }
}
```

**表单验证：**
```typescript
validateForm() {
  if (!this.dataModel.form.orderNo) {
    this.$message.warning('请输入订单号');
    return false;
  }
  
  if (!this.dataModel.form.amount || this.dataModel.form.amount <= 0) {
    this.$message.warning('请输入有效的金额');
    return false;
  }
  
  return true;
}
```

## 模板使用

### ✓ 推荐做法

**1. 优先使用模板**
```xml
<!-- ✓ 推荐 -->
<template>
  <page extends="yjpl-template/business/list-template.yjpl">
    <!-- 使用plugins扩展 -->
  </page>
</template>
```

**2. 合理使用plugins**
```xml
<!-- ✓ 推荐：只扩展必要的区域 -->
<plugins type="replace" target=".query-toolbar">
  <!-- 自定义工具栏 -->
</plugins>
```

### ✗ 避免做法

**1. 不要直接修改模板文件**
```xml
<!-- ✗ 错误：直接修改list-template.yjpl -->
```

**2. 不要过度自定义**
```xml
<!-- ✗ 错误：替换所有区域 -->
<plugins type="replace" target=".query-header"></plugins>
<plugins type="replace" target=".query-search-panel"></plugins>
<plugins type="replace" target=".query-toolbar"></plugins>
<plugins type="replace" target=".query-grid-body"></plugins>
```

## 测试

### 1. 单元测试

```typescript
import { mount } from '@vue/test-utils';
import UserList from './index.yjpl';

describe('UserList', () => {
  it('renders correctly', () => {
    const wrapper = mount(UserList);
    expect(wrapper.exists()).toBe(true);
  });
  
  it('loads data on created', async () => {
    const wrapper = mount(UserList);
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.dataModel.grid.length).toBeGreaterThan(0);
  });
});
```

### 2. 集成测试

```typescript
describe('User Management', () => {
  it('should add user successfully', async () => {
    // 1. 打开新增对话框
    await wrapper.find('.btn-add').trigger('click');
    
    // 2. 填写表单
    await wrapper.find('input[name="username"]').setValue('testuser');
    await wrapper.find('input[name="email"]').setValue('test@example.com');
    
    // 3. 提交表单
    await wrapper.find('.btn-submit').trigger('click');
    
    // 4. 验证结果
    expect(wrapper.vm.dataModel.grid).toContainEqual(
      expect.objectContaining({ username: 'testuser' })
    );
  });
});
```

## 文档

### 1. README文档

```markdown
# 用户管理模块

## 功能说明
- 用户列表查询
- 用户新增/编辑/删除
- 用户状态管理

## 技术栈
- YJPL 8.5.0+
- Vue 2.x
- TypeScript

## 使用方法
1. 安装依赖：`npm install`
2. 启动开发：`npm run dev`
3. 构建生产：`npm run build`

## API接口
- GET /api/user/list - 获取用户列表
- POST /api/user/add - 添加用户
- POST /api/user/update - 更新用户
- POST /api/user/delete - 删除用户
```

### 2. 代码文档

```typescript
/**
 * 用户管理业务类
 * @class UserBusiness
 * @extends YJBusiness
 */
export default class UserBusiness extends YJBusiness {
  /**
   * 获取用户列表
   * @param {number} pageSize - 每页条数
   * @param {number} pageIndex - 页码
   * @returns {Promise<Array>} 用户列表
   */
  async getUserList(pageSize: number, pageIndex: number): Promise<any[]> {
    // 实现代码
  }
}
```

## 安全

### 1. XSS防护

```typescript
// ✓ 推荐：使用v-text而不是v-html
<div v-text="userInput"></div>

// ✗ 避免：直接使用v-html
<div v-html="userInput"></div>
```

### 2. CSRF防护

```typescript
// 在请求头中添加CSRF token
axios.defaults.headers.common['X-CSRF-TOKEN'] = getCsrfToken();
```

### 3. 权限控制

```typescript
// 使用指令控制权限
<button v-permission="'user:add'">新增</button>

// 使用计算属性
computed: {
  canAdd() {
    return this.$store.getters.hasPermission('user:add');
  }
}
```

## 部署

### 1. 环境配置

```javascript
// .env.development
VUE_APP_API_BASE_URL=http://localhost:8080/api
VUE_APP_USE_MOCK=true

// .env.production
VUE_APP_API_BASE_URL=https://api.example.com
VUE_APP_USE_MOCK=false
```

### 2. 构建优化

```javascript
// vue.config.js
module.exports = {
  productionSourceMap: false,
  
  configureWebpack: {
    optimization: {
      splitChunks: {
        chunks: 'all',
        cacheGroups: {
          vendor: {
            test: /[\\/]node_modules[\\/]/,
            name: 'vendors',
            priority: 10
          }
        }
      }
    }
  }
};
```

## 监控

### 1. 错误监控

```typescript
// 全局错误处理
Vue.config.errorHandler = (err, vm, info) => {
  console.error('Error:', err);
  console.error('Component:', vm);
  console.error('Info:', info);
  
  // 上报错误
  reportError({
    error: err.message,
    stack: err.stack,
    component: vm.$options.name,
    info
  });
};
```

### 2. 性能监控

```typescript
// 页面加载时间
window.addEventListener('load', () => {
  const timing = performance.timing;
  const loadTime = timing.loadEventEnd - timing.navigationStart;
  console.log('Page load time:', loadTime);
});
```

## 总结

遵循这些最佳实践可以：
1. 提高代码质量和可维护性
2. 提升应用性能
3. 减少bug和错误
4. 提高开发效率
5. 便于团队协作

## 下一步

- 查看 [实战示例](./10-examples.md)
- 学习 [常见问题解决](./11-troubleshooting.md)
