# Search 搜索框

具有搜索标识的输入框组件，可通过搜索图标和键盘事件进行搜索

### 基础用法

要使用它，只需要在`search`元素中使用`v-model`绑定变量即可，变量的初始值即为默认值。

:::demo

```html
<template>
  <tl-search v-model="search" @search="handelSearch" placeholder="请输入关键字搜索"></tl-search>
</template>
<script>
  export default {
    data() {
      return {
        search: ''
      };
    },
    methods: {
      handelSearch(val) {
        console.log(val);
      }
    }
  };
</script>
```

:::

### 定位

搜索框定位功能，通过`type="locate"`配置

:::demo

```html
<template>
  <tl-search v-model="search" type="locate" :length="locatedLength" @locate-change="handleLocate" @search="handelSearch" placeholder="请输入关键字搜索"></tl-search>
</template>
<script>
  export default {
    data() {
      return {
        search: '',
        locatedLength: 10
      };
    },
    methods: {
      handelSearch(val) {
        console.log(val);
      },
      handleLocate(index) {
        console.log(index);
      }
    }
  };
</script>
```

:::

### 右侧搜索按钮

搜索框右侧搜索按钮功能，通过`type="trigger"`配置

:::demo

```html
<template>
  <tl-search v-model="search" type="trigger" @search="handelSearch" placeholder="请输入关键字搜索"></tl-search>
</template>
<script>
  export default {
    data() {
      return {
        search: ''
      };
    },
    methods: {
      handelSearch(val) {
        console.log(val);
      }
    }
  };
</script>
```

:::

### 尺寸

搜索框三种尺寸，通过`size`配置

:::demo

```html
<template>
  <span>large：</span>
  <tl-search class="mt12" size="large" v-model="search" @search="handelSearch" placeholder="请输入关键字搜索"></tl-search>
  <span>medium：</span>
  <tl-search class="mt12" size="medium" v-model="search" @search="handelSearch" placeholder="请输入关键字搜索"></tl-search>
  <span>small：</span>
  <tl-search class="mt12" size="small" v-model="search" @search="handelSearch" placeholder="请输入关键字搜索"></tl-search>
</template>
<script>
  export default {
    data() {
      return {
        search: ''
      };
    },
    methods: {
      handelSearch(val) {
        console.log(val);
      }
    }
  };
</script>
```

:::

### 表格列搜索 ^(8.5.0)

传递`search-colums`表格列配置，输入时会弹出列搜索确认，用户选择时会返回输入值和列设置，不选默认返回输入值。

:::demo

```html
<template>
  <tl-search v-model="search" type="trigger" @search="handelSearch" placeholder="请输入关键字搜索" :search-colums="column"></tl-search>
</template>
<script>
  export default {
    data() {
      return {
        search: '',
        column: [
            {
                id: 'id',
                text: '测试表头'
            },
            {
                id: 'id1',
                text: '测试表头1'
            },
            {
                id: 'id2',
                text: '测试表头2'
            },
            {
                id: 'id3',
                text: '测试表头3'
            },
            {
                id: 'id4',
                text: '测试表头4'
            },
            {
                id: 'id5',
                text: '测试表头5'
            },
            {
                id: 'id6',
                text: '测试表头6'
            },
            {
                id: 'id7',
                text: '测试表头7'
            },
            {
                id: 'id8',
                text: '测试表头8'
            },
            {
                id: 'id9',
                text: '测试表头9'
            },
            {
                id: 'id10',
                text: '测试表头10'
            },
            {
                id: 'id11',
                text: '测试表头11'
            }
        ]
      };
    },
    methods: {
      handelSearch(val, item) {
        console.log(val, item);
      }
    }
  };
</script>
```

:::

### Attributes

| 参数              | 说明                     | 类型    | 可选值       | 默认值    |
| ----------------- | ------------------------ | ------- | ------------ | --------- |
| value / v-model   | 绑定值                   | string  | —            | ''         |
| type              | 搜索框类型               | string  | `''`/`locate`/`trigger`    | `''` |
| size              | 计数器尺寸               | string  | `large`/`medium`/`small` | `medium`        |
| placeholder       | 搜索框默认 placeholder   | string  | -            | -         |
| locatedLength       |  搜索框定位总数量  | number  | -            | 0        |
| search-colums ^(8.5.0)       |  搜索列设置  | array  | -            | `[]`       |

### ssearch-colums ^(8.5.0)
| 参数              | 说明                     | 类型    | 可选值       | 默认值    |
| ----------------- | ------------------------ | ------- | ------------ | --------- |
| id   | 列字段（key）                   | string  | —            | ''         |
| text   | 列文字显示                   | string  | —            | ''         |

### Events

| 事件名称 | 说明                        | 回调参数               |
| -------- | --------------------------- | ---------------------- |
| search   | 搜索事件（回车，点击搜索按钮）          | value |
| locate-change     | 定位事件，返回定位到第几个位置 |    val: number     |