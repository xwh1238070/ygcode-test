# YJPL前端代码生成智能体 - 常见问题解决

## 编译错误

### Q1: 缺少 lang="ts" 导致的编译错误

**错误信息：**
```
Syntax Error: Unexpected token, expected ","
```

**原因：**
在 `.yjpl` 文件中使用 TypeScript 类型注解时，未在 `<script>` 标签中添加 `lang="ts"` 属性。

**解决方案：**
```xml
<!-- ✗ 错误 -->
<script>
export default class MyPage {
  handleSort(column: string, order: string) {  // 编译错误
    // ...
  }
}
</script>

<!-- ✓ 正确 -->
<script lang="ts">
export default class MyPage {
  handleSort(column: string, order: string) {  // 正确
    // ...
  }
}
</script>
```

### Q2: 模块找不到

**错误信息：**
```
Cannot find module 'yjpl-core'
```

**解决方案：**
1. 检查是否安装了依赖：`npm install`
2. 检查 package.json 中是否包含该依赖
3. 尝试重新安装：`npm install yjpl-core`
4. 清除缓存：`npm cache clean --force`

### Q3: TypeScript 类型错误

**错误信息：**
```
Property 'xxx' does not exist on type 'YYY'
```

**解决方案：**
1. 检查类型定义是否正确
2. 安装类型定义包：`npm install @types/yjpl-core`
3. 在 tsconfig.json 中配置类型路径

## 运行时错误

### Q4: this.bs is undefined

**错误信息：**
```
TypeError: Cannot read property 'getData' of undefined
```

**原因：**
Business 类未正确初始化。

**解决方案：**
```typescript
// ✓ 正确
export default class MyPage extends ListTemplate {
  constructor() {
    super();
    this.bs = new Business();  // 必须在构造函数中初始化
  }
}
```

### Q5: metaModel is undefined

**错误信息：**
```
TypeError: Cannot read property 'title' of undefined
```

**原因：**
数据未正确展开。

**解决方案：**
```typescript
// ✓ 正确
data() {
  return {
    ...this.bs.getData(),  // 使用展开运算符
    // 其他数据
  };
}
```

### Q6: 表格数据不显示

**可能原因：**
1. 数据格式不正确
2. 列配置与数据字段不匹配
3. 数据未正确加载

**解决方案：**
```typescript
// 1. 检查数据格式
console.log('表格数据:', this.dataModel.grid);

// 2. 检查列配置
console.log('列配置:', this.metaModel.grid.colModels);

// 3. 确保数据加载完成
async created() {
  this.dataModel.grid = await this.bs.getGridData();
  console.log('数据加载完成:', this.dataModel.grid);
}
```

## 组件问题

### Q7: 中台组件加载失败

**错误信息：**
```
Failed to load component
```

**解决方案：**
1. 检查包是否正确安装
2. 检查 vipAddress 配置是否正确
3. 检查 ecp 参数是否正确设置
4. 检查网络连接和API服务

```typescript
// 检查配置
<currency-select
  v-model="currencyId"
  :vip-address="/necp/mapp/metamodel"  // 确保路径正确
  :ecp="true">                          // 确保参数正确
</currency-select>
```

### Q8: QZZ表格编辑不生效

**可能原因：**
1. editType 配置错误
2. editable 设置为 false
3. 表格处于只读状态

**解决方案：**
```typescript
// 1. 检查列配置
colModels: [
  {
    name: 'amount',
    editType: 'number',  // 确保 editType 正确
    editable: true       // 确保可编辑
  }
]

// 2. 检查表格状态
const grid = this.$refs.grid.ui;
console.log('是否只读:', grid.isReadOnly());
grid.setReadOnly(false);  // 设置为可编辑
```

### Q9: 表格事件不触发

**可能原因：**
1. 事件绑定时机不对
2. 事件名称错误
3. this 指向问题

**解决方案：**
```typescript
// ✓ 正确：在 mounted 中绑定事件
mounted() {
  const grid = this.$refs.grid.ui;
  
  // 使用箭头函数保持 this 指向
  grid.bind('onAfterEdit', (dataType, fieldName, value) => {
    this.handleEdit(fieldName, value);
  });
  
  // 或使用 bind
  grid.bind('onAfterEdit', function(dataType, fieldName, value) {
    // 这里的 this 指向 grid
  });
}
```

## 性能问题

### Q10: 页面加载慢

**可能原因：**
1. 一次性加载大量数据
2. 未使用虚拟滚动
3. 过多的深度监听

**解决方案：**
```typescript
// 1. 使用分页
grid: {
  pageSize: 20,
  pager: true
}

// 2. 使用虚拟滚动
grid: {
  virtual: true,
  virtualSize: 20
}

// 3. 优化监听
watch: {
  // ✗ 避免：监听整个对象
  dataModel: {
    handler() { },
    deep: true
  },
  
  // ✓ 推荐：只监听必要的字段
  'dataModel.query': {
    handler() { },
    deep: true
  }
}
```

### Q11: 表格操作卡顿

**解决方案：**
```typescript
// 使用批量更新
const grid = this.$refs.grid.ui;

grid.beginUpdate();
// 执行多个操作
grid.setValue('field1', value1);
grid.setValue('field2', value2);
grid.setValue('field3', value3);
grid.endUpdate(true);  // 最后统一刷新
```

## Mock数据问题

### Q12: Mock数据不生效

**可能原因：**
1. API路径不匹配
2. 请求方法不匹配
3. Mock配置未正确导入

**解决方案：**
```typescript
// 1. 检查API路径
doGet: {
  '/api/users': {  // 确保路径完全匹配
    data(param: any) {
      return [];
    }
  }
}

// 2. 检查请求方法
// 如果后端是POST，Mock也要用doPost

// 3. 确保Mock配置被导入
// main.ts
if (process.env.NODE_ENV === 'development') {
  import('./mock');
}
```

### Q13: Mock数据返回格式错误

**解决方案：**
```typescript
// ✓ 正确：直接返回业务数据
doGet: {
  '/api/users': {
    data(param: any) {
      return [
        { id: 1, name: '张三' },
        { id: 2, name: '李四' }
      ];
    }
  }
}

// ✗ 错误：不要包装成响应对象
doGet: {
  '/api/users': {
    data(param: any) {
      return {
        success: true,
        data: [...]  // 错误：多余的包装
      };
    }
  }
}
```

## 样式问题

### Q14: 样式不生效

**可能原因：**
1. 样式作用域问题
2. 样式优先级问题
3. 样式未正确引入

**解决方案：**
```vue
<!-- 1. 使用 scoped 避免样式污染 -->
<style lang="scss" scoped>
.my-class {
  color: red;
}
</style>

<!-- 2. 使用深度选择器修改组件样式 -->
<style lang="scss" scoped>
::v-deep .el-button {
  color: red;
}
</style>

<!-- 3. 提高样式优先级 -->
<style lang="scss" scoped>
.my-page .el-button {
  color: red !important;
}
</style>
```

### Q15: 布局错乱

**解决方案：**
```css
/* 1. 检查容器高度 */
.page-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

/* 2. 检查表格高度 */
.grid-container {
  flex: 1;
  overflow: hidden;
}

/* 3. 使用 Flex 布局 */
.layout {
  display: flex;
  height: 100vh;
}
```

## 路由问题

### Q16: 路由跳转失败

**解决方案：**
```typescript
// 1. 使用编程式导航
this.$router.push('/user/list');

// 2. 带参数跳转
this.$router.push({
  path: '/user/detail',
  query: { id: '123' }
});

// 3. 新窗口打开
const route = this.$router.resolve({
  path: '/user/detail',
  query: { id: '123' }
});
window.open(route.href, '_blank');
```

### Q17: 路由参数获取不到

**解决方案：**
```typescript
// 1. 在 created 中获取
async created() {
  const id = this.$route.query.id;
  if (id) {
    await this.loadData(id);
  }
}

// 2. 监听路由变化
watch: {
  '$route'(to, from) {
    if (to.query.id !== from.query.id) {
      this.loadData(to.query.id);
    }
  }
}
```

## 数据问题

### Q18: 数据更新后视图不更新

**原因：**
Vue 无法检测到数组或对象的某些变化。

**解决方案：**
```typescript
// ✗ 错误：直接修改数组索引
this.dataModel.grid[0] = newItem;

// ✓ 正确：使用 Vue.set
this.$set(this.dataModel.grid, 0, newItem);

// ✓ 正确：使用数组方法
this.dataModel.grid.splice(0, 1, newItem);

// ✗ 错误：直接添加对象属性
this.dataModel.newProp = value;

// ✓ 正确：使用 Vue.set
this.$set(this.dataModel, 'newProp', value);
```

### Q19: 表单验证不通过

**解决方案：**
```typescript
// 1. 检查验证规则
rules: {
  userName: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ]
}

// 2. 手动触发验证
this.$refs.form.validate((valid) => {
  if (valid) {
    // 验证通过
  } else {
    console.log('验证失败');
    return false;
  }
});

// 3. 清除验证
this.$refs.form.clearValidate();
```

## 调试技巧

### 使用Vue DevTools

1. 安装 Vue DevTools 浏览器扩展
2. 在开发环境中打开
3. 查看组件树和数据状态

### 使用console.log

```typescript
// 1. 查看数据
console.log('数据:', this.dataModel);

// 2. 查看方法调用
methods() {
  return {
    handleClick() {
      console.log('handleClick 被调用');
      // ...
    }
  };
}

// 3. 查看生命周期
created() {
  console.log('created');
}

mounted() {
  console.log('mounted');
}
```

### 使用debugger

```typescript
methods() {
  return {
    handleClick() {
      debugger;  // 代码会在这里暂停
      // ...
    }
  };
}
```

## 获取帮助

### 1. 查看文档
- [概述](./01-overview.md)
- [架构和核心概念](./02-architecture.md)
- [模板系统详解](./03-templates.md)

### 2. 查看示例
- [实战示例](./10-examples.md)

### 3. 搜索问题
- 在项目文档中搜索关键词
- 查看相关组件的API文档

### 4. 联系支持
- 提交问题到项目仓库
- 联系技术支持团队

## 总结

遇到问题时的解决步骤：
1. 查看错误信息，确定问题类型
2. 检查代码配置是否正确
3. 查看浏览器控制台的错误信息
4. 使用调试工具定位问题
5. 查阅相关文档
6. 搜索类似问题的解决方案
7. 寻求技术支持

## 返回

- 返回 [概述](./01-overview.md)
- 查看 [最佳实践](./09-best-practices.md)
