# 任务管理页面

基于 YJPL 框架开发的任务管理页面，提供任务的增删改查、状态管理、优先级设置等功能。

## 📋 功能特性

### 核心功能
- ✅ **任务列表展示** - 分页展示所有任务信息
- ✅ **多条件查询** - 支持按任务名称、状态、优先级、负责人、日期范围筛选
- ✅ **任务操作** - 新增、编辑、删除、完成任务
- ✅ **状态管理** - 待办、进行中、已完成、已取消四种状态
- ✅ **优先级标识** - 高、中、低三级优先级，带颜色区分
- ✅ **批量操作** - 支持批量删除、批量完成任务
- ✅ **数据导出** - 导出任务列表为 Excel 文件
- ✅ **实时统计** - 显示总任务数和已选任务数

### 交互优化
- 🎯 双击行快速编辑
- 🎯 智能按钮状态控制（根据选中行数量自动启用/禁用）
- 🎯 操作确认提示（删除、完成等危险操作）
- 🎯 状态和优先级彩色标签显示
- 🎯 友好的错误提示和成功反馈

## 🏗️ 技术架构

### 技术栈
- **YJPL 框架** 8.5.0+
- **Vue 2.x**
- **TypeScript**
- **yjpl-ui 组件库**
- **list-template 模板**

### 文件结构
```
task-management/
├── index.yjpl          # 主视图文件（继承 list-template）
├── Business.ts         # 业务逻辑类
├── Model.ts            # 数据模型（metaModel、dataModel、stateModel）
├── mock.js             # Mock 数据配置
└── README.md           # 使用说明文档
```

### 架构设计
```
┌─────────────────────────────────────┐
│         index.yjpl (视图层)          │
│    - 模板继承 list-template          │
│    - UI 渲染和事件绑定               │
│    - 状态和优先级格式化               │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│       Business.ts (业务逻辑层)        │
│    - 数据获取和处理                   │
│    - API 调用封装                    │
│    - 业务规则实现                     │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│        Model.ts (数据模型层)          │
│    - metaModel (页面配置)            │
│    - dataModel (业务数据)            │
│    - stateModel (状态管理)           │
└─────────────────────────────────────┘
```

## 📊 数据模型

### metaModel（页面配置）
定义页面的静态配置，包括：
- **查询字段**：任务名称、状态、优先级、负责人、创建日期
- **表格列**：ID、任务名称、描述、状态、优先级、负责人、时间信息
- **按钮**：新增、编辑、删除、完成、导出

### dataModel（业务数据）
存储页面的动态数据，包括：
- **查询条件**：用户输入的筛选条件
- **表格数据**：任务列表数据
- **分页信息**：总数、页码、每页条数

### stateModel（状态管理）
控制组件的状态，包括：
- **按钮状态**：根据选中行数量动态控制按钮的启用/禁用
- 使用 `YJState.STATE` 枚举值（DEFAULT、DISABLED、LOADING、HIDDEN）

## 🎨 状态和优先级

### 任务状态
| 状态 | 值 | 颜色 | 说明 |
|------|---|------|------|
| 待办 | pending | 蓝色 | 新创建的任务 |
| 进行中 | in_progress | 橙色 | 正在执行的任务 |
| 已完成 | completed | 绿色 | 已完成的任务 |
| 已取消 | cancelled | 灰色 | 已取消的任务 |

### 优先级
| 优先级 | 值 | 颜色 | 图标 |
|--------|---|------|------|
| 高 | high | 红色 | ⬆ |
| 中 | medium | 黄色 | ➡ |
| 低 | low | 绿色 | ⬇ |

## 🔌 API 接口

### GET 接口

#### 获取任务列表
```
GET /api/task/list
```

**请求参数：**
```typescript
{
  pageSize: number,      // 每页条数
  pageIndex: number,     // 页码
  taskName?: string,     // 任务名称（模糊查询）
  status?: string,       // 状态
  priority?: string,     // 优先级
  assignee?: string,     // 负责人
  startDate?: string,    // 开始日期
  endDate?: string       // 结束日期
}
```

**响应数据：**
```typescript
{
  list: Array<Task>,     // 任务列表
  total: number,         // 总记录数
  pageSize: number,      // 每页条数
  pageIndex: number,     // 当前页码
  pages: number          // 总页数
}
```

### POST 接口

#### 新增任务
```
POST /api/task/add
```

**请求参数：**
```typescript
{
  taskName: string,      // 任务名称（必填）
  description?: string,  // 描述
  priority?: string,     // 优先级
  assignee?: string,     // 负责人
  dueDate?: string       // 截止日期
}
```

#### 更新任务
```
POST /api/task/update
```

**请求参数：**
```typescript
{
  id: number,            // 任务ID（必填）
  taskName?: string,     // 任务名称
  description?: string,  // 描述
  status?: string,       // 状态
  priority?: string,     // 优先级
  assignee?: string,     // 负责人
  dueDate?: string       // 截止日期
}
```

#### 删除任务
```
POST /api/task/delete
```

**请求参数：**
```typescript
{
  ids: number[]          // 任务ID数组
}
```

#### 完成任务
```
POST /api/task/complete
```

**请求参数：**
```typescript
{
  ids: number[]          // 任务ID数组
}
```

#### 导出任务
```
POST /api/task/export
```

**请求参数：**
```typescript
{
  taskName?: string,     // 任务名称
  status?: string,       // 状态
  priority?: string,     // 优先级
  assignee?: string,     // 负责人
  startDate?: string,    // 开始日期
  endDate?: string       // 结束日期
}
```

## 🚀 使用说明

### 开发环境

1. **安装依赖**
```bash
npm install
```

2. **配置 Mock 数据**
在 `src/mock/index.ts` 中引入 mock 配置：
```typescript
import taskMock from '@/pages/task-management/mock.js';

export default {
  doGet: {
    ...taskMock.doGet
  },
  doPost: {
    ...taskMock.doPost
  }
};
```

3. **启动开发服务器**
```bash
npm run dev
```

### 生产环境

1. **替换 API 调用**
在 `Business.ts` 中，将 Mock 数据调用替换为真实 API：
```typescript
// 开发阶段（使用 Mock）
console.log('查询参数:', params);
return [];

// 生产环境（调用真实 API）
const response = await api.getTaskList(params);
return response.data.list;
```

2. **配置 API 地址**
在项目配置文件中设置 API 基础地址。

3. **构建生产版本**
```bash
npm run build
```

## 📝 代码示例

### 查询任务
```typescript
// 设置查询条件
this.dataModel.query = {
  taskName: '项目',
  status: 'pending',
  priority: 'high'
};

// 执行查询
await this.handleQuery();
```

### 新增任务
```typescript
const taskData = {
  taskName: '新任务',
  description: '任务描述',
  priority: 'high',
  assignee: '张三',
  dueDate: '2024-12-31'
};

await this.bs.addTask(taskData);
```

### 批量完成任务
```typescript
const selectedRows = this.$refs.grid.ui.getSelectedRowData();
const ids = selectedRows.map(row => row.id);
await this.bs.completeTask(ids);
```

## 🎯 最佳实践

### 1. 状态控制
根据选中行数量智能控制按钮状态：
- 未选中：编辑、删除、完成按钮禁用
- 选中一条：所有操作按钮启用
- 选中多条：编辑按钮禁用，删除和完成按钮启用

### 2. 数据验证
在执行操作前进行数据验证：
```typescript
// 完成任务前检查状态
const invalidTasks = selectedRows.filter(
  row => row.status === 'completed' || row.status === 'cancelled'
);

if (invalidTasks.length > 0) {
  this.$message.warning('选中的任务中包含已完成或已取消的任务');
  return;
}
```

### 3. 错误处理
使用 try-catch 捕获异常并给出友好提示：
```typescript
try {
  await this.bs.deleteTask(ids);
  this.$message.success('删除成功');
  await this.loadData();
} catch (error) {
  this.$message.error('删除失败：' + error.message);
}
```

### 4. 用户体验
- 危险操作使用确认对话框
- 操作成功后自动刷新数据
- 提供实时统计信息
- 使用彩色标签增强可读性

## 🔧 扩展功能

### 可扩展的功能点

1. **任务详情对话框**
   - 创建独立的任务编辑对话框组件
   - 支持更多字段（标签、附件、评论等）

2. **高级筛选**
   - 添加更多筛选条件
   - 保存常用筛选条件

3. **任务分类**
   - 添加任务分类/标签功能
   - 支持按分类筛选

4. **权限控制**
   - 根据用户角色控制操作权限
   - 只能编辑/删除自己创建的任务

5. **任务协作**
   - 任务评论功能
   - 任务进度跟踪
   - 任务提醒通知

## 📚 参考文档

- [YJPL 框架文档](../context7-docs/YJPL-Complete-Guide-Full.md)
- [list-template 模板说明](../context7-docs/03-templates.md)
- [数据模型设计](../context7-docs/04-data-models.md)
- [组件库参考](../context7-docs/05-components.md)

## 📄 许可证

本项目仅供学习和参考使用。

## 👥 贡献者

- 开发者：YJPL 代码生成智能体
- 创建时间：2025-12-15

---

**注意**：本页面使用 Mock 数据进行开发，实际使用时需要替换为真实的 API 接口。
