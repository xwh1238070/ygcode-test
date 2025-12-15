# TableTools 局部工具栏

局部工具栏用来承载表格、树、列表等控件的标题、功能按钮等内容。

### 基本布局

<br/>

:::demo 默认右侧布局

```html
<template>
  <div class="table-tools-demo">
    <tl-table-tools :layout="layout" @search-data="onSearch">
      <template #button>
        <tl-button v-for="item of functionButton" :key="item.title" :type="item.type" :icon="item.icon"
          >{{item.title}}
        </tl-button>

        <tl-button class="tl-icon" icon="iconfont icondaochu" id="up_button" title="上传"> </tl-button>

        <tl-dropdown placement="bottom-start" style="margin-left: 16px;">
          <tl-button class="tl-icon" icon="iconfont icondaoru">
            <i class="el-icon-arrow-down el-icon--right"></i>
          </tl-button>
          <tl-dropdown-menu slot="dropdown">
            <tl-dropdown-item :command="'导出本页'">导出本页</tl-dropdown-item>
            <tl-dropdown-item :command="'导出全部'">导出全部</tl-dropdown-item>
          </tl-dropdown-menu>
        </tl-dropdown>
      </template>
      <template #view-switch>
        <tl-radio-group v-model="viewswitch" @input="onSwitch">
          <tl-radio-button :label="0">列表</tl-radio-button>
          <tl-radio-button :label="1">图表</tl-radio-button>
        </tl-radio-group>
      </template>
    </tl-table-tools>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        layout: ['button', 'search', 'view-switch'],
        functionButton: [
          {
            //保存和新增按钮
            type: 'primary',
            title: '保存',
            icon: 'iconfont iconbaocun'
          },
          {
            type: 'default',
            title: '新增',
            icon: 'el-icon-circle-plus-outline'
          }
        ],
        viewswitch: 0
      };
    },
    methods: {
      onSearch(val) {
        this.$message(`搜索内容：${val}`);
      },
      onSwitch(val) {
        if (val === 0) {
          this.$message('列表');
        }
        if (val === 1) {
          this.$message('图表');
        }
      }
    }
  };
</script>
```

:::

### 左侧布局标题

:::demo 通过 `->` 区分左右布局

```html
<template>
  <div class="table-tools-demo">
    <tl-table-tools :layout="layout" @search-data="onSearch">
      <template #title>
        <div>左侧布局标题</div>
      </template>
      <template #content-switch>
        <tl-radio-group v-model="contentswitch" @input="onSwitch">
          <tl-radio-button :label="0">全部(10)</tl-radio-button>
          <tl-radio-button :label="1">已完成(5)</tl-radio-button>
        </tl-radio-group>
      </template>
      <template #content>
        <div>内容说明区域</div>
      </template>
    </tl-table-tools>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        layout: ['title', 'content-switch', 'content', '->'],
        contentswitch: 0
      };
    },
    methods: {
      onSearch(val) {
        this.$message(`搜索内容：${val}`);
      },
      onSwitch(val) {
        if (val === 0) {
          this.$message('全部');
        }
        if (val === 1) {
          this.$message('已完成');
        }
      }
    }
  };
</script>
```

:::

### 其它排列布局

:::demo 通过切换`layout` 中布局位置，实现不同位置排列布局

```html
<template>
  <div class="table-tools-demo">
    <div class="mt10">
      <tl-table-tools :layout="layout" @search-data="onSearch">
        <template #title>
          <div>标题1</div>
        </template>
        <template #content-switch>
          <tl-radio-group v-model="contentswitch" @input="onContentSwitch">
            <tl-radio-button :label="0">全部(10)</tl-radio-button>
            <tl-radio-button :label="1">已完成(5)</tl-radio-button>
          </tl-radio-group>
        </template>
        <template #content>
          <div>内容说明</div>
        </template>
        <template #button>
          <tl-button v-for="item of functionButton" :key="item.title" :type="item.type" :icon="item.icon"
            >{{item.title}}
          </tl-button>

          <tl-button class="tl-icon" icon="iconfont icondaochu" id="up_button" title="上传"> </tl-button>

          <tl-dropdown placement="bottom-start" style="margin-left: 16px;">
            <tl-button class="tl-icon" icon="iconfont icondaoru">
              <i class="el-icon-arrow-down el-icon--right"></i>
            </tl-button>
            <tl-dropdown-menu slot="dropdown">
              <tl-dropdown-item :command="'导出本页'">导出本页</tl-dropdown-item>
              <tl-dropdown-item :command="'导出全部'">导出全部</tl-dropdown-item>
            </tl-dropdown-menu>
          </tl-dropdown>
        </template>
        <template #view-switch>
          <tl-radio-group v-model="viewswitch" @input="onViewSwitch">
            <tl-radio-button :label="0">列表</tl-radio-button>
            <tl-radio-button :label="1">图表</tl-radio-button>
          </tl-radio-group>
        </template>
      </tl-table-tools>
    </div>

    <div class="mt10">
      <tl-table-tools :layout="layout1" @search-data="onSearch">
        <template #title>
          <div>标题2</div>
        </template>
        <template #content-switch>
          <tl-radio-group v-model="contentswitch" @input="onContentSwitch">
            <tl-radio-button :label="0">全部(10)</tl-radio-button>
            <tl-radio-button :label="1">已完成(5)</tl-radio-button>
          </tl-radio-group>
        </template>
        <template #content>
          <div>内容说明</div>
        </template>
        <template #button>
          <tl-button v-for="item of functionButton" :key="item.title" :type="item.type" :icon="item.icon"
            >{{item.title}}
          </tl-button>

          <tl-button class="tl-icon" icon="iconfont icondaochu" id="up_button" title="上传"> </tl-button>

          <tl-dropdown placement="bottom-start" style="margin-left: 16px;">
            <tl-button class="tl-icon" icon="iconfont icondaoru">
              <i class="el-icon-arrow-down el-icon--right"></i>
            </tl-button>
            <tl-dropdown-menu slot="dropdown">
              <tl-dropdown-item :command="'导出本页'">导出本页</tl-dropdown-item>
              <tl-dropdown-item :command="'导出全部'">导出全部</tl-dropdown-item>
            </tl-dropdown-menu>
          </tl-dropdown>
        </template>
        <template #view-switch>
          <tl-radio-group v-model="viewswitch" @input="onViewSwitch">
            <tl-radio-button :label="0">列表</tl-radio-button>
            <tl-radio-button :label="1">图表</tl-radio-button>
          </tl-radio-group>
        </template>
      </tl-table-tools>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        layout: ['title', 'content-switch', 'content', '->', 'button', 'search', 'view-switch'],
        layout1: ['title', 'button', '->', 'content-switch', 'content', 'search', 'view-switch'],
        functionButton: [
          {
            type: 'primary',
            title: '保存',
            icon: 'iconfont iconbaocun'
          },
          {
            type: 'default',
            title: '新增',
            icon: 'el-icon-circle-plus-outline'
          }
        ],
        viewswitch: 0,
        contentswitch: 0
      };
    },
    methods: {
      onSearch(val) {
        this.$message(`搜索内容：${val}`);
      },
      onContentSwitch(val) {
        if (val === 0) {
          this.$message('全部');
        }
        if (val === 1) {
          this.$message('已完成');
        }
      },
      onViewSwitch(val) {
        if (val === 0) {
          this.$message('列表');
        }
        if (val === 1) {
          this.$message('图表');
        }
      }
    }
  };
</script>

<style>
  .table-tools-demo .mt10 {
    margin-top: 10px;
  }
</style>
```

:::

### Attributes

| 参数   | 说明             | 类型  | 可选值 | 默认值                                                                   |
| ------ | ---------------- | ----- | ------ | ------------------------------------------------------------------------ |
| layout | 模块布局排列顺序 | array | -      | ['title', 'content-switch', 'content', 'view-switch', 'search', 'button'] |

### Slot

| name           | 说明             |
| -------------- | ---------------- |
| title          | 标题插槽         |
| content-switch | 内容切换模块插槽 |
| content        | 内容说明模块插槽 |
| button         | 功能按钮模块插槽 |
| search         | 搜索模块插槽     |
| view-switch    | 视图切换模块插槽 |

### Events

| 事件名称    | 说明               | 回调参数 |
| ----------- | ------------------ | -------- |
| search-data | 搜索框回车触发     | -        |
| blur-data   | 搜索框失去焦点触发 | -        |
| focus-data  | 搜索框聚焦时触发   | -        |
| input-data  | 搜索框值改变时触发 | -        |
