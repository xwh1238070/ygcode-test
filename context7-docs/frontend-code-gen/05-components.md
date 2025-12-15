# YJPL前端代码生成智能体 - 组件库完整参考

## 组件库概述

YJPL UI组件库包含68个组件，涵盖基础组件、表单组件、数据展示、业务组件、容器组件、反馈组件、导航组件等多个类别。

## 组件分类索引

### 基础组件 (6个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Button | 按钮组件 | 触发操作、提交表单 |
| Icon | 图标组件 | 显示图标、装饰 |
| Link | 链接组件 | 页面跳转、外链 |
| Layout | 布局组件 | 页面布局、栅格系统 |
| Container | 容器组件 | 内容容器、区域划分 |
| Transition | 过渡动画组件 | 动画效果、过渡 |

### 表单组件 (16个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Input | 输入框 | 文本输入 |
| InputNumber | 数字输入框 | 数字输入、计数器 |
| Select | 选择器 | 下拉选择 |
| Cascader | 级联选择器 | 多级联动选择 |
| DatePicker | 日期选择器 | 日期选择 |
| TimePicker | 时间选择器 | 时间选择 |
| Checkbox | 复选框 | 多选 |
| Radio | 单选框 | 单选 |
| Switch | 开关 | 开关切换 |
| Slider | 滑块 | 数值范围选择 |
| ColorPicker | 颜色选择器 | 颜色选择 |
| Upload | 文件上传 | 文件上传 |
| Form | 表单容器 | 表单布局、验证 |
| EntitySelect | 实体选择器 | 实体数据选择 |
| RangeNumber | 范围数字输入 | 数值范围输入 |
| YjCondition | 条件组件 | 条件筛选 |

### 数据展示 (8个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Table | 数据表格 | 数据列表展示 |
| Tree | 树形组件 | 树形结构展示 |
| Pagination | 分页器 | 分页导航 |
| Avatar | 头像组件 | 用户头像 |
| Tag | 标签组件 | 标签展示 |
| Badge | 徽章组件 | 消息提示、状态标记 |
| Timeline | 时间线组件 | 时间轴展示 |
| SelectList | 选择列表 | 列表选择 |

### 业务组件 (14个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Export | 导出功能 | 数据导出 |
| Import | 导入功能 | 数据导入 |
| SearchPanel | 搜索面板 | 查询条件面板 |
| TableTools | 表格工具栏 | 表格操作工具 |
| UploadPanel | 上传面板 | 文件上传管理 |
| YjButtonList | 按钮列表 | 按钮组 |
| YjContainerCard | 容器卡片 | 卡片容器 |
| YjContainer | 容器组件 | 表单容器 |
| YjTransfer | 穿梭框 | 数据穿梭选择 |
| Calculator | 计算器 | 数值计算 |
| Magnifier | 放大镜 | 图片放大 |
| QrBarCode | 二维码/条形码 | 码生成展示 |
| WaterMark | 水印 | 页面水印 |
| ErrorPage | 错误页面 | 错误提示页 |

### 容器组件 (11个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Dialog | 对话框 | 弹窗、模态框 |
| Drawer | 抽屉 | 侧边抽屉 |
| Card | 卡片 | 内容卡片 |
| Tabs | 标签页 | 标签切换 |
| Steps | 步骤条 | 流程步骤 |
| Collapse | 折叠面板 | 内容折叠 |
| Section | 分区组件 | 内容分区 |
| Anchor | 锚点 | 页面锚点导航 |
| Title | 标题 | 标题展示 |
| FoldBar | 折叠栏 | 可折叠区域 |
| YjDialog | 增强对话框 | 增强弹窗 |

### 反馈组件 (11个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Message | 消息提示 | 操作反馈 |
| MessageBox | 消息框 | 确认对话框 |
| Notification | 通知 | 系统通知 |
| Alert | 警告框 | 警告提示 |
| Loading | 加载动画 | 加载状态 |
| Progress | 进度条 | 进度展示 |
| Tooltip | 工具提示 | 提示信息 |
| Popover | 弹出框 | 气泡提示 |
| PopConfirm | 确认弹框 | 操作确认 |
| CornerMark | 角标 | 角标提示 |
| YjMessage | 增强消息提示 | 增强反馈 |

### 导航组件 (5个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Menu | 菜单 | 导航菜单 |
| Breadcrumb | 面包屑 | 路径导航 |
| PageTabs | 页面标签 | 页面标签切换 |
| DropDown | 下拉菜单 | 下拉操作 |
| PageHeader | 页面头部 | 页面头部信息 |

### 其他组件 (6个)

| 组件名 | 说明 | 常用场景 |
|--------|------|---------|
| Carousel | 轮播图 | 图片轮播 |
| Image | 图片 | 图片展示 |
| Divider | 分割线 | 内容分割 |
| BackTop | 返回顶部 | 页面回顶 |
| InfiniteScroll | 无限滚动 | 滚动加载 |
| SpreadJS | 电子表格 | 表格编辑 |

## 常用组件详解

### Button（按钮）

**基本用法：**
```html
<button type="primary">主要按钮</button>
<button type="success">成功按钮</button>
<button type="warning">警告按钮</button>
<button type="danger">危险按钮</button>
<button type="info">信息按钮</button>
<button type="text">文本按钮</button>
```

**带图标：**
```html
<button type="primary" icon="yj-p-add">新增</button>
<button type="danger" icon="yj-p-delete">删除</button>
```

**按钮尺寸：**
```html
<button size="large">大按钮</button>
<button size="medium">中按钮</button>
<button size="small">小按钮</button>
<button size="mini">迷你按钮</button>
```

**按钮状态：**
```html
<button :disabled="true">禁用按钮</button>
<button :loading="true">加载中</button>
```

### Input（输入框）

**基本用法：**
```html
<input v-model="value" placeholder="请输入内容"></input>
```

**带图标：**
```html
<input v-model="value" prefix-icon="el-icon-search"></input>
<input v-model="value" suffix-icon="el-icon-date"></input>
```

**可清空：**
```html
<input v-model="value" clearable></input>
```

**密码框：**
```html
<input v-model="password" type="password" show-password></input>
```

**文本域：**
```html
<input v-model="text" type="textarea" :rows="4"></input>
```

### Select（选择器）

**基本用法：**
```html
<select v-model="value" placeholder="请选择">
  <option label="选项1" value="1"></option>
  <option label="选项2" value="2"></option>
</select>
```

**多选：**
```html
<select v-model="values" multiple placeholder="请选择">
  <option label="选项1" value="1"></option>
  <option label="选项2" value="2"></option>
</select>
```

**可搜索：**
```html
<select v-model="value" filterable placeholder="请选择">
  <option label="选项1" value="1"></option>
</select>
```

### DatePicker（日期选择器）

**日期选择：**
```html
<date-picker v-model="date" type="date" placeholder="选择日期"></date-picker>
```

**日期时间选择：**
```html
<date-picker v-model="datetime" type="datetime" placeholder="选择日期时间"></date-picker>
```

**日期范围：**
```html
<date-picker v-model="dateRange" type="daterange" 
             range-separator="至"
             start-placeholder="开始日期"
             end-placeholder="结束日期">
</date-picker>
```

### Table（表格）

**基本用法：**
```html
<table :data="tableData">
  <table-column prop="id" label="ID" width="80"></table-column>
  <table-column prop="name" label="姓名" width="120"></table-column>
  <table-column prop="email" label="邮箱"></table-column>
</table>
```

**带操作列：**
```html
<table :data="tableData">
  <table-column prop="name" label="姓名"></table-column>
  <table-column label="操作" width="180">
    <template slot-scope="scope">
      <button size="small" @click="handleEdit(scope.row)">编辑</button>
      <button size="small" type="danger" @click="handleDelete(scope.row)">删除</button>
    </template>
  </table-column>
</table>
```

**带分页：**
```html
<table :data="tableData" @selection-change="handleSelectionChange">
  <table-column type="selection" width="55"></table-column>
  <table-column prop="name" label="姓名"></table-column>
</table>
<pagination
  :current-page="currentPage"
  :page-size="pageSize"
  :total="total"
  @current-change="handlePageChange">
</pagination>
```

### Dialog（对话框）

**基本用法：**
```html
<dialog title="提示" :visible.sync="dialogVisible" width="30%">
  <span>这是一段信息</span>
  <span slot="footer" class="dialog-footer">
    <button @click="dialogVisible = false">取消</button>
    <button type="primary" @click="dialogVisible = false">确定</button>
  </span>
</dialog>
```

**表单对话框：**
```html
<dialog title="编辑" :visible.sync="dialogVisible">
  <form :model="form" :rules="rules" ref="form">
    <form-item label="名称" prop="name">
      <input v-model="form.name"></input>
    </form-item>
    <form-item label="描述" prop="desc">
      <input v-model="form.desc" type="textarea"></input>
    </form-item>
  </form>
  <span slot="footer">
    <button @click="dialogVisible = false">取消</button>
    <button type="primary" @click="submitForm">确定</button>
  </span>
</dialog>
```

### Message（消息提示）

**基本用法：**
```javascript
// 成功提示
this.$message.success('操作成功');

// 警告提示
this.$message.warning('警告信息');

// 错误提示
this.$message.error('操作失败');

// 普通提示
this.$message.info('提示信息');
```

**可关闭的消息：**
```javascript
this.$message({
  message: '这是一条消息',
  type: 'success',
  showClose: true,
  duration: 3000
});
```

### MessageBox（消息框）

**确认框：**
```javascript
this.$confirm('确定要删除吗？', '提示', {
  confirmButtonText: '确定',
  cancelButtonText: '取消',
  type: 'warning'
}).then(() => {
  // 确定操作
  this.$message.success('删除成功');
}).catch(() => {
  // 取消操作
  this.$message.info('已取消删除');
});
```

**提示框：**
```javascript
this.$alert('这是一条提示信息', '提示', {
  confirmButtonText: '确定'
});
```

**输入框：**
```javascript
this.$prompt('请输入名称', '提示', {
  confirmButtonText: '确定',
  cancelButtonText: '取消'
}).then(({ value }) => {
  this.$message.success('输入的内容是：' + value);
});
```

### Form（表单）

**基本用法：**
```html
<form :model="form" :rules="rules" ref="form" label-width="100px">
  <form-item label="用户名" prop="username">
    <input v-model="form.username"></input>
  </form-item>
  <form-item label="密码" prop="password">
    <input v-model="form.password" type="password"></input>
  </form-item>
  <form-item label="邮箱" prop="email">
    <input v-model="form.email"></input>
  </form-item>
  <form-item>
    <button type="primary" @click="submitForm">提交</button>
    <button @click="resetForm">重置</button>
  </form-item>
</form>
```

**验证规则：**
```javascript
data() {
  return {
    form: {
      username: '',
      password: '',
      email: ''
    },
    rules: {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, message: '密码长度不能少于6位', trigger: 'blur' }
      ],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
      ]
    }
  };
},
methods: {
  submitForm() {
    this.$refs.form.validate((valid) => {
      if (valid) {
        // 提交表单
        console.log('提交表单', this.form);
      } else {
        return false;
      }
    });
  },
  resetForm() {
    this.$refs.form.resetFields();
  }
}
```

### Tree（树形组件）

**基本用法：**
```html
<tree :data="treeData" :props="defaultProps" @node-click="handleNodeClick"></tree>
```

```javascript
data() {
  return {
    treeData: [
      {
        id: 1,
        label: '一级 1',
        children: [
          {
            id: 11,
            label: '二级 1-1',
            children: [
              { id: 111, label: '三级 1-1-1' }
            ]
          }
        ]
      },
      {
        id: 2,
        label: '一级 2',
        children: [
          { id: 21, label: '二级 2-1' }
        ]
      }
    ],
    defaultProps: {
      children: 'children',
      label: 'label'
    }
  };
},
methods: {
  handleNodeClick(data) {
    console.log(data);
  }
}
```

**可选择的树：**
```html
<tree :data="treeData" 
      :props="defaultProps"
      show-checkbox
      node-key="id"
      :default-checked-keys="[11, 21]">
</tree>
```

### Upload（文件上传）

**基本用法：**
```html
<upload
  action="/api/upload"
  :on-success="handleSuccess"
  :on-error="handleError"
  :before-upload="beforeUpload">
  <button size="small" type="primary">点击上传</button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</upload>
```

**图片上传：**
```html
<upload
  action="/api/upload"
  list-type="picture-card"
  :on-preview="handlePreview"
  :on-remove="handleRemove">
  <i class="el-icon-plus"></i>
</upload>
```

**拖拽上传：**
```html
<upload
  drag
  action="/api/upload"
  :on-success="handleSuccess">
  <i class="el-icon-upload"></i>
  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
</upload>
```

## 组件使用最佳实践

### ✓ 推荐做法

1. **按需引入组件**
   ```javascript
   import { Button, Input, Table } from 'yjpl-ui';
   ```

2. **使用组件的默认配置**
   ```html
   <button type="primary">按钮</button>
   ```

3. **合理使用组件事件**
   ```html
   <table :data="tableData" @selection-change="handleSelectionChange"></table>
   ```

### ✗ 避免做法

1. **不要过度自定义组件样式**
   ```css
   /* ✗ 避免 */
   .el-button {
     /* 大量自定义样式 */
   }
   ```

2. **不要在循环中创建大量组件实例**
   ```html
   <!-- ✗ 避免 -->
   <div v-for="item in largeList">
     <heavy-component :data="item"></heavy-component>
   </div>
   ```

3. **不要忽视组件的性能优化**
   ```html
   <!-- ✗ 避免 -->
   <table :data="allData"></table>
   
   <!-- ✓ 推荐：使用分页 -->
   <table :data="pageData"></table>
   <pagination></pagination>
   ```

## 组件查询指南

当需要了解某个组件的详细用法时：

1. **查看本文档** - 快速了解组件基本用法
2. **查看平台组件md文档** - 获取详细的API文档
3. **参考示例代码** - 查看实际使用案例

## 下一步

- 了解 [QZZ表格组件](./06-qzz-grid.md)
- 学习 [中台组件集成](./07-middleware.md)
- 查看 [Mock数据配置](./08-mock-data.md)
