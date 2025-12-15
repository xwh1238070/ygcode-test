# 用户管理页面

基于 YJPL 框架的用户管理查询界面，支持用户的增删改查、启用禁用、密码重置、数据导入导出等功能。

## 📁 文件结构

```
user-management/
├── index.yjpl              # 主模板文件
├── Business.ts             # 业务逻辑类
├── Model.ts                # 数据模型（metaModel、dataModel、stateModel）
├── mock.js                 # Mock数据配置
└── README.md               # 使用文档
```

## 🎯 功能特性

### 查询功能
- ✅ 用户名查询
- ✅ 姓名查询
- ✅ 部门筛选
- ✅ 角色筛选
- ✅ 状态筛选（启用/禁用）
- ✅ 创建日期范围查询

### 表格展示
- ✅ 用户ID
- ✅ 用户名
- ✅ 姓名
- ✅ 部门
- ✅ 角色
- ✅ 手机号
- ✅ 邮箱
- ✅ 状态
- ✅ 创建时间
- ✅ 最后登录时间

### 操作功能
- ✅ 新增用户
- ✅ 编辑用户
- ✅ 删除用户（支持批量）
- ✅ 重置密码（支持批量）
- ✅ 启用用户（支持批量）
- ✅ 禁用用户（支持批量）
- ✅ 导出数据
- ✅ 导入数据

### 视图切换
- ✅ 表格视图
- ✅ 卡片视图
- ✅ 列表视图

## 🚀 快速开始

### 1. 集成到项目

将 `user-management` 目录复制到项目的页面目录中：

```bash
cp -r user-management /your-project/src/pages/
```

### 2. 配置路由

在路由配置文件中添加路由：

```javascript
{
  path: '/user-management',
  name: 'UserManagement',
  component: () => import('@/pages/user-management/index.yjpl'),
  meta: {
    title: '用户管理',
    icon: 'yj-p-user'
  }
}
```

### 3. 配置 Mock 数据（可选）

如果需要使用 Mock 数据进行前端独立开发，在项目的 `src/mock/index.ts` 中引入：

```javascript
import userManagementMock from '@/pages/user-management/mock.js';

export default {
  doGet: {
    ...userManagementMock.doGet
  },
  doPost: {
    ...userManagementMock.doPost
  }
};
```

### 4. 替换 API 接口

在 `Business.ts` 中，将 Mock 数据替换为实际的 API 调用：

```typescript
// 获取表格数据
async getGridData(pageSize?: number, pageIndex?: number) {
  const response = await this.$http.post('/api/user/list', {
    pageSize: pageSize || 20,
    pageIndex: pageIndex || 1,
    ...this.dataModel.query
  });
  return response.data;
}

// 获取总记录数
async getTotalRecord() {
  const response = await this.$http.post('/api/user/count', {
    ...this.dataModel.query
  });
  return response.data;
}
```

## 📋 API 接口说明

### 查询接口

#### 获取用户列表
```
POST /api/user/list
```

**请求参数：**
```json
{
  "pageSize": 20,
  "pageIndex": 1,
  "username": "admin",
  "realName": "张三",
  "department": "技术部",
  "role": "系统管理员",
  "status": "1",
  "createTime": ["2024-01-01", "2024-12-31"]
}
```

**返回数据：**
```json
[
  {
    "userId": "U001",
    "username": "admin",
    "realName": "张三",
    "department": "技术部",
    "role": "系统管理员",
    "phone": "13800138001",
    "email": "zhangsan@example.com",
    "status": "启用",
    "createTime": "2024-01-15 10:30:00",
    "lastLoginTime": "2024-12-15 09:15:00"
  }
]
```

#### 获取用户总数
```
POST /api/user/count
```

**请求参数：** 同查询条件

**返回数据：**
```json
100
```

### 操作接口

#### 新增用户
```
POST /api/user/add
```

**请求参数：**
```json
{
  "username": "newuser",
  "realName": "新用户",
  "department": "技术部",
  "role": "普通用户",
  "phone": "13800138888",
  "email": "newuser@example.com"
}
```

#### 编辑用户
```
POST /api/user/edit
```

**请求参数：**
```json
{
  "userId": "U001",
  "realName": "张三",
  "department": "技术部",
  "role": "系统管理员",
  "phone": "13800138001",
  "email": "zhangsan@example.com"
}
```

#### 删除用户
```
POST /api/user/delete
```

**请求参数：**
```json
{
  "userIds": ["U001", "U002"]
}
```

#### 重置密码
```
POST /api/user/resetPassword
```

**请求参数：**
```json
{
  "userIds": ["U001", "U002"]
}
```

#### 启用用户
```
POST /api/user/enable
```

**请求参数：**
```json
{
  "userIds": ["U001", "U002"]
}
```

#### 禁用用户
```
POST /api/user/disable
```

**请求参数：**
```json
{
  "userIds": ["U001", "U002"]
}
```

#### 导出数据
```
POST /api/user/export
```

**请求参数：** 同查询条件

**返回数据：**
```json
{
  "success": true,
  "message": "导出成功",
  "data": {
    "fileUrl": "/download/users_export_xxx.xlsx"
  }
}
```

#### 导入数据
```
POST /api/user/import
```

**请求参数：** FormData（包含文件）

**返回数据：**
```json
{
  "success": true,
  "message": "导入成功",
  "data": {
    "successCount": 10,
    "failCount": 0
  }
}
```

## 🔧 自定义配置

### 修改查询字段

在 `Model.ts` 的 `metaModel.query` 中添加或修改查询字段：

```typescript
query: [
  {
    placeholder: '请输入',
    name: 'fieldName',
    dataType: 'string',
    label: '字段标签',
    rules: [
      { required: false, message: '请输入', trigger: 'blur' }
    ]
  }
]
```

### 修改表格列

在 `Model.ts` 的 `metaModel.grid.colModels` 中添加或修改列：

```typescript
colModels: [
  {
    editType: 'string',
    caption: '列标题',
    name: 'fieldName',
    width: 120,
    showInList: true,
    align: 'center'
  }
]
```

### 添加工具栏按钮

在 `Model.ts` 的 `metaModel.btns` 中添加按钮：

```typescript
btns: [
  {
    name: 'customBtn',
    text: '自定义按钮',
    icon: 'yj-p-custom'
  }
]
```

在 `index.yjpl` 的 `eventsModel` 中绑定事件：

```typescript
eventsModel: {
  customBtn: {
    click: this.handleCustom
  }
}
```

在 `methods()` 中实现处理方法：

```typescript
methods() {
  return {
    handleCustom() {
      // 自定义逻辑
    }
  };
}
```

## 📊 数据模型说明

### metaModel（页面配置）
- `title` - 页面标题
- `query` - 查询字段配置
- `grid` - 表格配置
- `btns` - 工具栏按钮配置
- `showCard` - 是否支持卡片视图
- `showList` - 是否支持列表视图

### dataModel（业务数据）
- `query` - 查询条件数据
- `grid` - 表格数据
- `pageTotal` - 分页总数

### stateModel（状态管理）
- 按钮状态控制（DEFAULT、DISABLED、HIDDEN、LOADING）

## ⚠️ 注意事项

1. **TypeScript 类型**：确保在 `<script>` 标签中添加 `lang="ts"` 属性
2. **依赖版本**：需要 YJPL 框架 8.5.0+ 版本
3. **API 接口**：实际项目中需要替换 Mock 数据为真实 API
4. **权限控制**：根据实际需求添加按钮权限控制
5. **数据验证**：在新增/编辑时添加表单验证规则

## 🐛 常见问题

### Q1: 表格数据不显示？
**A:** 检查以下几点：
- Business.ts 中的 `getGridData()` 方法是否正确返回数据
- 数据格式是否与 colModels 中的字段名匹配
- 是否在 created() 中正确初始化数据

### Q2: 按钮点击无反应？
**A:** 检查以下几点：
- eventsModel 中是否正确绑定事件
- methods() 中是否实现了对应的处理方法
- stateModel 中按钮状态是否为 DISABLED

### Q3: 查询条件不生效？
**A:** 检查以下几点：
- dataModel.query 是否正确绑定
- Business.ts 中是否使用了查询条件
- 后端 API 是否正确处理查询参数

### Q4: 分页不正常？
**A:** 检查以下几点：
- getTotalRecord() 是否返回正确的总数
- mounted() 中是否调用了 setTotalRecord()
- getGridData() 是否正确处理 pageSize 和 pageIndex

## 📝 更新日志

### v1.0.0 (2024-12-15)
- ✨ 初始版本发布
- ✅ 完整的用户管理功能
- ✅ 支持多视图切换
- ✅ 提供 Mock 数据支持

## 📞 技术支持

如有问题或建议，请联系前端开发团队。

## 📄 许可证

本项目遵循企业内部开发规范。
