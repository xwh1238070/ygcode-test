## Container 布局容器
用于布局的容器组件，方便快速搭建页面的基本结构：

`<tl-container>`：外层容器。当子元素中包含 `<tl-header>` 或 `<tl-footer>` 时，全部子元素会垂直上下排列，否则会水平左右排列。

`<tl-header>`：顶栏容器。

`<tl-aside>`：侧边栏容器。

`<tl-main>`：主要区域容器。

`<tl-footer>`：底栏容器。

:::tip
以上组件采用了 flex 布局，使用前请确定目标浏览器是否兼容。此外，`<tl-container>` 的子元素只能是后四者，后四者的父元素也只能是 `<tl-container>`。
:::

### 常见页面布局

:::demo
```html
<tl-container>
  <tl-header>Header</tl-header>
  <tl-main>Main</tl-main>
</tl-container>

<tl-container>
  <tl-header>Header</tl-header>
  <tl-main>Main</tl-main>
  <tl-footer>Footer</tl-footer>
</tl-container>

<tl-container>
  <tl-aside width="200px">Aside</tl-aside>
  <tl-main>Main</tl-main>
</tl-container>

<tl-container>
  <tl-header>Header</tl-header>
  <tl-container>
    <tl-aside width="200px">Aside</tl-aside>
    <tl-main>Main</tl-main>
  </tl-container>
</tl-container>

<tl-container>
  <tl-header>Header</tl-header>
  <tl-container>
    <tl-aside width="200px">Aside</tl-aside>
    <tl-container>
      <tl-main>Main</tl-main>
      <tl-footer>Footer</tl-footer>
    </tl-container>
  </tl-container>
</tl-container>

<tl-container>
  <tl-aside width="200px">Aside</tl-aside>
  <tl-container>
    <tl-header>Header</tl-header>
    <tl-main>Main</tl-main>
  </tl-container>
</tl-container>

<tl-container>
  <tl-aside width="200px">Aside</tl-aside>
  <tl-container>
    <tl-header>Header</tl-header>
    <tl-main>Main</tl-main>
    <tl-footer>Footer</tl-footer>
  </tl-container>
</tl-container>
```
:::

### 实例

:::demo
```html
<div class="custom-container">
  <tl-container style="height: 500px; border: 1px solid #eee">
    <tl-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <tl-menu :default-openeds="['1', '3']">
        <tl-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>导航一</template>
          <tl-menu-item-group>
            <template slot="title">分组一</template>
            <tl-menu-item index="1-1">选项1</tl-menu-item>
            <tl-menu-item index="1-2">选项2</tl-menu-item>
          </tl-menu-item-group>
          <tl-menu-item-group title="分组2">
            <tl-menu-item index="1-3">选项3</tl-menu-item>
          </tl-menu-item-group>
          <tl-submenu index="1-4">
            <template slot="title">选项4</template>
            <tl-menu-item index="1-4-1">选项4-1</tl-menu-item>
          </tl-submenu>
        </tl-submenu>
        <tl-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>导航二</template>
          <tl-menu-item-group>
            <template slot="title">分组一</template>
            <tl-menu-item index="2-1">选项1</tl-menu-item>
            <tl-menu-item index="2-2">选项2</tl-menu-item>
          </tl-menu-item-group>
          <tl-menu-item-group title="分组2">
            <tl-menu-item index="2-3">选项3</tl-menu-item>
          </tl-menu-item-group>
          <tl-submenu index="2-4">
            <template slot="title">选项4</template>
            <tl-menu-item index="2-4-1">选项4-1</tl-menu-item>
          </tl-submenu>
        </tl-submenu>
        <tl-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>导航三</template>
          <tl-menu-item-group>
            <template slot="title">分组一</template>
            <tl-menu-item index="3-1">选项1</tl-menu-item>
            <tl-menu-item index="3-2">选项2</tl-menu-item>
          </tl-menu-item-group>
          <tl-menu-item-group title="分组2">
            <tl-menu-item index="3-3">选项3</tl-menu-item>
          </tl-menu-item-group>
          <tl-submenu index="3-4">
            <template slot="title">选项4</template>
            <tl-menu-item index="3-4-1">选项4-1</tl-menu-item>
          </tl-submenu>
        </tl-submenu>
      </tl-menu>
    </tl-aside>
    
    <tl-container>
      <tl-header style="text-align: right; font-size: 12px">
        <tl-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <tl-dropdown-menu slot="dropdown">
            <tl-dropdown-item>查看</tl-dropdown-item>
            <tl-dropdown-item>新增</tl-dropdown-item>
            <tl-dropdown-item>删除</tl-dropdown-item>
          </tl-dropdown-menu>
        </tl-dropdown>
        <span>王小虎</span>
      </tl-header>
      
      <tl-main>
        <tl-table :data="tableData">
          <tl-table-column prop="date" label="日期" width="140">
          </tl-table-column>
          <tl-table-column prop="name" label="姓名" width="120">
          </tl-table-column>
          <tl-table-column prop="address" label="地址">
          </tl-table-column>
        </tl-table>
      </tl-main>
    </tl-container>
  </tl-container>
</div>

<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item)
      }
    }
  };
</script>
```
:::

### Container Attributes
| 参数    | 说明     | 类型    | 可选值      | 默认值 |
|---------|----------|---------|-------------|--------|
| direction | 子元素的排列方向 | string | horizontal / vertical | 子元素中有 `el-header` 或 `el-footer` 时为 vertical，否则为 horizontal |

### Header Attributes
| 参数    | 说明     | 类型    | 可选值      | 默认值 |
|---------|----------|---------|-------------|--------|
| height | 顶栏高度 | string | — | 60px |

### Aside Attributes
| 参数    | 说明     | 类型    | 可选值      | 默认值 |
|---------|----------|---------|-------------|--------|
| width | 侧边栏宽度 | string | — | 300px |

### Footer Attributes
| 参数    | 说明     | 类型    | 可选值      | 默认值 |
|---------|----------|---------|-------------|--------|
| height | 底栏高度 | string | — | 60px |