# NavMenu 导航菜单
为网站提供导航功能的菜单。

### 带有子菜单的导航
适用广泛的基础用法

:::demo 导航菜单默认为垂直模式，通过`mode`属性可以使导航菜单变更为水平模式。另外，在菜单中通过`submenu`组件可以生成二级菜单。Menu 还提供了`background-color`、`text-color`和`active-text-color`，分别用于设置菜单的背景色、菜单的文字颜色和当前激活菜单的文字颜色。

```html
<template>
  <tl-menu
    mode="horizontal"
    default-active="1"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    @select="handleSelect"
    class="menu-demo"
  >
    <tl-menu-item index="1">工作中心</tl-menu-item>
    <tl-submenu index="2" popper-class="menu-demo">
      <template #title>应用中心</template>
      <tl-menu-item index="2-1">选项2-1</tl-menu-item>
      <tl-menu-item index="2-2">选项2-2</tl-menu-item>
      <tl-menu-item index="2-3">选项2-3</tl-menu-item>
      <tl-submenu index="2-4">
        <template #title>选项2-4</template>
        <tl-menu-item index="2-4-1">选项2-4-1</tl-menu-item>
        <tl-menu-item index="2-4-2">选项2-4-2</tl-menu-item>
        <tl-menu-item index="2-4-3">选项2-4-3</tl-menu-item>
      </tl-submenu>
    </tl-submenu>
    <tl-menu-item index="3" disabled>资金管理</tl-menu-item>
    <tl-menu-item index="4"><a href="https://xdeer.ygsoft.com/" target="_blank">天鹿官网</a></tl-menu-item>
  </tl-menu>
</template>

<script>
export default {
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>
<style>
.menu-demo .tl-menu .el-menu .tl-menu__nav--scroll .tl-menu__nav--inner--scroll .el-menu-item:hover,
.menu-demo .tl-menu .el-menu .tl-menu__nav--scroll .tl-menu__nav--inner--scroll .el-submenu__title:hover{
  color: #ffffff;
  background-color: rgb(67, 74, 80);
}
.menu-demo .el-menu--horizontal .el-menu .el-menu-item:hover{
  background-color: rgb(67, 74, 80);
}
.menu-demo .tl-menu .el-menu .tl-menu__nav--scroll .tl-menu__nav--inner--scroll .el-menu-item:hover.is-active, .menu-demo .tl-menu .el-menu .tl-menu__nav--scroll .tl-menu__nav--inner--scroll .el-submenu__title:hover.is-active{
  background-color: rgb(67, 74, 80);
}
.menu-demo.el-menu--horizontal .el-menu .el-submenu__title:hover, .menu-demo.el-menu--horizontal .el-menu .el-menu-item:hover{
  color: #ffffff;
  background-color: rgb(67, 74, 80);
}
</style>
```
:::

### 导航支持搜索
<br/>

:::demo `searchable`配置搜索属性

```html
<template>
  <tl-menu
    default-active="1"
    @select="handleSelect"
    mode="horizontal"
    searchable
  >
    <tl-menu-item index="1">工作中心</tl-menu-item>
    <tl-submenu index="2">
      <template #title>应用中心</template>
      <tl-menu-item index="2-1">选项2-1</tl-menu-item>
      <tl-menu-item index="2-2">选项2-2</tl-menu-item>
      <tl-menu-item index="2-3">选项2-3</tl-menu-item>
      <tl-submenu index="2-4">
        <template #title>选项2-4</template>
        <tl-menu-item index="2-4-1">选项2-4-1</tl-menu-item>
        <tl-menu-item index="2-4-2">选项2-4-2</tl-menu-item>
        <tl-menu-item index="2-4-3">选项2-4-3</tl-menu-item>
      </tl-submenu>
    </tl-submenu>
    <tl-menu-item index="3" disabled>资金管理</tl-menu-item>
    <tl-menu-item index="4"><a href="https://xdeer.ygsoft.com/" target="_blank">天鹿官网</a></tl-menu-item>
  </tl-menu>
</template>

<script>
export default {
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>
```
:::

### 导航栏左右区域可以添加自定义内容
适用左右需要加扩展元素的情况

:::demo 左右两侧可设置显示具名slot分发，同时也是可选的。`menu-item`设置`closeabled`属性可以进行移除操作。

```html
<template>
  <tl-menu
    class="menu-demo1"
    :default-active="activeIndex"
    background-color="#02356d"
    text-color="#fff"
    active-text-color="#fff"
    hoverBackgroundColor="#1687e8"
    menu-trigger="click"
    mode="horizontal"
    @select="handleSelect"
    @menu-remove="handleRemove"
    searchable>
    <template #logo>
      <div class="slot-logo">LOGO </div>
    </template>
    <tl-menu-item 
      v-for="item in menuItems" 
      :key="item.index"
      :index="item.index"
      :closable="item.closable"
      :disabled="item.disabled">{{item.name}}
    </tl-menu-item>
    <template #toolbar>
      <div class="slot-toolbar">Toolbar</div>
    </template>
  </tl-menu>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: '0',
      menuItems: [
        {
          name: '工作中心',
          index: '0',
        },
        {
          name: '应用中心',
          index: '1',
        },
        {
          name: '安全管理',
          index: '2',
          disabled: true
        },
        {
          name: '影像管理',
          index: '3',
          closable: true
        },
        {
          name: '核算管理',
          index: '4',
          closable: true
        },
        {
          name: '电子档案管理',
          index: '5',
        },
        {
          name: '综合管理',
          index: '6',
        }
      ]
    }
  },
  methods: {
    handleSelect(index, indexPath, item) {
      this.activeIndex = index;
    },
    handleRemove(index, indexPath, item) {
      let menuItems = this.menuItems;
      if(index === this.activeIndex) {
        const nextMenuItemIndex = index + 1 || index - 1;
        if(nextMenuItemIndex > -1) {
          this.activeIndex = nextMenuItemIndex;
        }
      }
      this.menuItems = menuItems.filter(menu => menu.index !== index);
    }
  }
};
</script>

<style>
  .slot-logo,
  .slot-toolbar {
    font-size: 20px;
    color: white;
    font-family: Source Han Sans CN;
    font-weight: bold;
  }
  .slot-logo {
    margin-left: 30px;
    margin-right: 60px;
  }
  .slot-toolbar {
    margin-right: 30px;
  }
  .menu-demo1 .tl-menu .el-menu .tl-menu__nav--scroll .tl-menu__nav--inner--scroll .el-menu-item:hover, .menu-demo1 .tl-menu .el-menu .tl-menu__nav--scroll .tl-menu__nav--inner--scroll .el-submenu__title:hover{
    color: #ffffff;
  }
</style>
```
:::

### 侧边导航

垂直菜单，可内嵌子菜单

:::demo 

```html
<template>
  <div class="container">
    <div class="menu__box">
      <h5>默认颜色</h5>
      <tl-menu
        mode="vertical"
        default-active="1"
        @submenu-open="handleOpen"
        @submenu-close="handleClose"
        searchable
      >
        <tl-menu-item index="1">
          <i class="el-icon-location"></i>
          <span slot="title">工作中心</span>
        </tl-menu-item>
        <tl-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>应用中心</span>
          </template>
          <tl-menu-item-group>
            <template slot="title">分组一</template>
            <tl-menu-item index="2-1">选项2-1</tl-menu-item>
            <tl-menu-item index="2-2">选项2-2</tl-menu-item>
          </tl-menu-item-group>
          <tl-menu-item-group title="分组二">
            <tl-menu-item index="2-3">选项2-3</tl-menu-item>
            <tl-submenu index="2-4">
              <template slot="title">选项2-4</template>
              <tl-menu-item index="2-4-1">选项2-4-1</tl-menu-item>
              <tl-menu-item index="2-4-2">选项2-4-2</tl-menu-item>
              <tl-menu-item index="2-4-3">选项2-4-3</tl-menu-item>
            </tl-submenu>
          </tl-menu-item-group>
        </tl-submenu>
        <tl-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <span slot="title">资金管理</span>
        </tl-menu-item>
        <tl-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title"><a href="https://xdeer.ygsoft.com/" target="_blank">天鹿官网</a></span>
        </tl-menu-item>
      </tl-menu>
    </div>

    <div class="menu__box custom-menu" style="border: none">
      <h5>自定义颜色</h5>
      <tl-menu
        mode="vertical"
        default-active="1"
        @submenu-open="handleOpen"
        @submenu-close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <tl-menu-item index="1">
          <i class="el-icon-location"></i>
          <span slot="title">工作中心</span>
        </tl-menu-item>
        <tl-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>应用中心</span>
          </template>
          <tl-menu-item index="2-1">选项2-1</tl-menu-item>
          <tl-menu-item index="2-2">选项2-2</tl-menu-item>
          <tl-menu-item index="2-3">选项2-3</tl-menu-item>
          <tl-submenu index="2-4">
            <template slot="title">选项2-4</template>
            <tl-menu-item index="2-4-1">选项2-4-1</tl-menu-item>
            <tl-menu-item index="2-4-2">选项2-4-2</tl-menu-item>
            <tl-menu-item index="2-4-3">选项2-4-3</tl-menu-item>
          </tl-submenu>
        </tl-submenu>
        <tl-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <span slot="title">资金管理</span>
        </tl-menu-item>
        <tl-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title"><a href="https://xdeer.ygsoft.com/" target="_blank">天鹿官网</a></span>
        </tl-menu-item>
      </tl-menu>
    </div>
  </div>
</template>

<script>

export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  }
}
</script>

<style>

.container{
  display: flex;
  justify-content: space-around;
}
.container .menu__box{
  width: 240px;
}
.container h5{
  font-size: 14px;
    color: #8492a6;
    margin-top: 10px;
}
.custom-menu .tl-menu .el-menu .el-menu-item.is-active i{
  color: #ffd04b;
}
.custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-menu-item:hover, .custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-submenu__title:hover{
  color: #ffffff;
  background-color: #434a50;
}
.custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-menu-item:hover i, .custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-submenu__title:hover i{
  color: #ffffff;
}
.custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-menu-item.is-active, .custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-submenu__title.is-active{
  background-color: transparent;
}
.custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-menu-item:hover.is-active, .custom-menu .tl-menu .el-menu .tl-menu__nav--scroll .el-submenu__title:hover.is-active{
  background-color: #434a50;
}
</style>
```
:::

### 可折叠的侧边导航
垂直菜单，可内嵌子菜单
:::demo 

```html
<template>
  <div>
    <tl-radio-group v-model="isCollapse" style="margin-bottom: 20px">
      <tl-radio-button :label="false">展开</tl-radio-button>
      <tl-radio-button :label="true">收起</tl-radio-button>
    </tl-radio-group>
    <div class="tl-menu-vertical-demo">
      <tl-menu
        default-active="1"
        :collapse="isCollapse"
      >
        <tl-menu-item index="1">
          <i class="el-icon-location"></i>
          <span slot="title">工作中心</span>
        </tl-menu-item>
        <tl-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>应用中心</span>
          </template>
          <tl-menu-item index="2-1">选项2-1</tl-menu-item>
          <tl-menu-item index="2-2">选项2-2</tl-menu-item>
          <tl-menu-item index="2-3">选项2-3</tl-menu-item>
          <tl-submenu index="2-4">
            <template slot="title">选项2-4</template>
            <tl-menu-item index="2-4-1">选项2-4-1</tl-menu-item>
            <tl-menu-item index="2-4-2">选项2-4-2</tl-menu-item>
            <tl-menu-item index="2-4-3">选项2-4-3</tl-menu-item>
          </tl-submenu>
        </tl-submenu>
        <tl-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <span slot="title">资金管理</span>
        </tl-menu-item>
        <tl-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title"><a href="https://xdeer.ygsoft.com/" target="_blank">天鹿官网</a></span>
        </tl-menu-item>
      </tl-menu>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: true,
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style>
  .tl-menu-vertical-demo {
    width: 200px;
  }
</style>
```
:::

### 横向嵌套导航
根据数据结构里的`children`属性自动生成嵌套导航，目前只支持横向一级导航嵌套二级导航
:::demo

```html
<template>
  <div class="menuDemo">
    <tl-menu-nest
      mode="horizontal"
      moreable
      :menu-data="menuData" 
      :default-active="activeIndex"    
      background-color="#02356d"
      text-color="#fff"
      active-text-color="#fff"
      hover-text-color="#fff"
      hoverBackgroundColor="#1687e8"
      @select="handleSelect"
      @remove="handleRemove">
    </tl-menu-nest>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: '0',
      menuData: [
        {
          text: '工作中心',
          index: '0'
        },
        {
          text: '应用中心',
          index: '1',
          children: [
            {
              text: '二级导航1',
              index: '1-1',
              children: [{
                text: '三级导航1',
                index: '1-1-1'
              }]
            },
            {
              text: '二级导航2',
              index: '1-2',
              children: [
                {
                  text: '三级导航1',
                  index: '1-2-1',
                  children: [
                    {
                      text: '四级导航1',
                      index: '1-2-1-1',
                    },
                    {
                      text: '四级导航2',
                      index: '1-2-1-2',
                    }
                  ]
                },
                {
                  text: '三级导航2',
                  index: '4-2-2'
                }
              ]
            },
            {
              text: '二级导航3',
              index: '1-3',
              children: [{
                text: '三级导航1',
                index: '1-3-1'
              }]
            },
            {
              text: '超长的二级导航4超长的二级导航4超长的二级导航4',
              index: '1-4',
              children: [{
                text: '三级导航1',
                index: '1-4-1'
              }]
            },
            {
              text: '二级导航5',
              index: '1-5'
            },
            {
              text: '二级导航6',
              index: '1-6'
            },
            {
              text: '二级导航7',
              index: '1-7'
            },
            {
              text: '二级导航8',
              index: '1-8'
            }
          ]
        },
        {
          text: '资料管理',
          index: '2'
        },
        {
          text: '影响管理',
          index: '3',
          closable: true
        },
        {
          text: '核算管理',
          index: '4'
        },
        {
          text: '电子档案管理',
          index: '5'
        },
        {
          text: '综合管理',
          index: '6'
        }
      ]
    } 
  },
  methods: {
    handleSelect(key, keyPath) {
      // key是元素当前的index值，keyPath会追溯它的上一级，直至顶级
      console.log(`demo4....key=${key}...keyPath=${keyPath}`);
    },
    handleRemove(targetIndex, indexPath) {
      let menusItems = this.menuData; 
      let activeIndex = this.activeIndex;
      if(activeIndex === targetIndex) {
        menusItems.map((menuItem, index) => {
          if(menuItem.index === targetIndex) {
            let nextMenuItem = menusItems[index + 1] || menusItems[index - 1];
            if(nextMenuItem) {
              activeIndex = nextMenuItem.index;
            }
          }
        });
      }
      this.activeIndex = activeIndex;
      this.menuData = menusItems.filter(menu => menu.index !== targetIndex);
    }
  }
};
</script>

```
:::

### Menu Attribute
| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| mode     | 模式   | string  |   horizontal / vertical   | vertical |
| collapse  | 是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）| boolean  |   —   | false |
| background-color  | 菜单的背景色（仅支持 hex 格式） | string |   —   | #ffffff |
| hover-background-color  | 鼠标悬浮时背景色（仅支持 hex 格式） | string |   —   | #ffffff |
| text-color  | 菜单的文字颜色（仅支持 hex 格式） | string |   —   | #303133 |
| active-text-color  | 当前激活菜单的文字颜色（仅支持 hex 格式） | string |   —   | #409EFF |
| active-bar-color  | 选中条颜色（仅支持 hex 格式） | string |   —   | #409EFF |
| default-active | 当前激活菜单的 index | string    | — | — |
| default-openeds | 当前打开的 sub-menu 的 index 的数组 | Array    | — | — |
| unique-opened  | 是否只保持一个子菜单的展开 | boolean   | — | false   |
| menu-trigger  | 子菜单打开的触发方式(只在 mode 为 horizontal 时有效) | string   | hover / click | hover |
| router  | 是否使用 vue-router 的模式，启用该模式会在激活导航时以 index 作为 path 进行路由跳转 | boolean   | — | false   |
| searchable  | 是否显示搜索框 | boolean   | — | false   |


### SunMenu Methods
| 方法名称      | 说明    | 参数      |
|---------- |-------- |---------- |
| open  | 展开指定的 sub-menu | index: 需要打开的 sub-menu 的 index |
| close  | 收起指定的 sub-menu | index: 需要收起的 sub-menu 的 index |

### Menu Events
| 事件名称      | 说明    | 回调参数      |
|---------- |-------- |---------- |
| select  | 菜单激活回调 | index: 选中菜单项的 index, indexPath: 选中菜单项的 index path  |
| open | sub-menu 展开的回调 | index: 打开的 sub-menu 的 index， indexPath: 打开的 sub-menu 的 index path  |
| close  | sub-menu 收起的回调 | index: 收起的 sub-menu 的 index， indexPath: 收起的 sub-menu 的 index path  |
| remove  | 一级菜单下菜单项被移除时的回调 | index: 移除的 menuItem 的 index， indexPath: 移除的 menuItem 的 index path  |

### SubMenu Attribute
| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| index     | 唯一标志   | string/null  | — | null |
| popper-class | 弹出菜单的自定义类名 | string | — | — |
| show-timeout | 展开 sub-menu 的延时 | number | — | 300 |
| hide-timeout | 收起 sub-menu 的延时 | number | — | 300 |
| disabled  | 是否禁用 | boolean | — | false |
| closable  | 是否可删除 | boolean | — | false |
| popper-append-to-body | 是否将弹出菜单插入至 body 元素。在菜单的定位出现问题时，可尝试修改该属性 | boolean | — | 一级子菜单：true / 非一级子菜单：false |

### Menu-Item Attribute
| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| index     | 唯一标志   | string  | — | — |
| route     | Vue Router 路径对象 | Object | — | — |
| disabled  | 是否禁用 | boolean | — | false |
| closable  | 是否可删除 | boolean | — | false |

### Menu-Group Attribute
| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| title     | 分组标题   | string  | — | — |

### Menu Slot
参数 | 说明 | 类型 | 可选值 | 默认值
- | - | - | - | -
logo | 设置居左的logo区域(横向下)，也可以通过 slot#logo 传入 DOM | string | - | -
toolbar | 设置居右的工具栏区域(横向下)，也可以通过 slot#toolbar 传入 DOM | string | - | -