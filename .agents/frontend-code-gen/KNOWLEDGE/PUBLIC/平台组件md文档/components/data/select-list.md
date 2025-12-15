## SelectList 选择列表

用于展示可以选择的数据列表。

### 单选列表

搜索控件作为列表属性，可以自己选择有无。

:::demo Select-list 组件提供三种类型，由`type`属性指定，默认值为`radio`。

```html
<template>
  <tl-select-list
    v-model="data1.value"
    :search-bar="data1.searchBar"
    :data="data1.data"
  ></tl-select-list>
</template>

<script>
  export default {
    data() {
      return {
        data1: {
          searchBar: true,
          value: 1,
          data: [
            {
              text: "选项1",
              value: 1,
              disable:true
            },
            {
              text: "选项2",
              value: 2
            },
            {
              text:
                "选项3选项3选项3选项3选项3选项3选项3选项3选项3选项3选项3选项3",
              value: 3
            },
            {
              text: "选项4",
              value: 4
            },
            {
              text: "选项5",
              value: 5
            },
            {
              text: "选项6",
              value: 6
            }
          ]
        }
      };
    }
  };
</script>
```

:::

### 多选列表

select-list 组件提供了多选列表。

:::demo 通过设置`type`属性和传入对应的`data`来使用多选列表。

```html
<template>
  <tl-select-list
    v-model="data2.value"
    :data="data2.data"
    :type="data2.type"
    :search-bar="data2.searchBar"
  ></tl-select-list>
</template>

<script>
  export default {
    data() {
      return {
        data2: {
          searchBar: true,
          value: [1],
          type: "checkbox",
          data: [
            {
              text: "选项1",
              value: 1
            },
            {
              text: "选项2选项2选项2选项2选项2选项2选项2选项2选项2",
              value: 2
            },
            {
              text: "选项3",
              value: 3
            },
            {
              text: "选项4",
              value: 4
            },
            {
              text: "选项5",
              value: 5
            },
            {
              text: "选项6",
              value: 6
            }
          ]
        }
      };
    }
  };
</script>
```

:::

### 层级列表

Select-list 组件提供带层级的选择列表。

1. 该控件仅适用于两层列表的场景
2. 内容较多、层级复杂、有多选操作、并有层级增多的可能，请使用树控件

:::demo 通过设置`type`属性和传入对应的`data`来使用层级列表。

```html
<template>
  <tl-select-list
    v-model="data3.value"
    :search-bar="data3.searchBar"
    :data="data3.data"
    :type="data3.type"
  ></tl-select-list>
</template>
<script>
  
  export default {
    data() {
      return {
        data3: {
          searchBar: true,
          value: 1.1,
          type: "group",
          data: [
            {
              text: "选项1",
              value: 1,
              isExpanded: true,
              children: [
                {
                  value: 1.1,
                  text: "选项1-1"
                },
                {
                  value: 1.2,
                  text:
                    "选项1-2选项1-2选项1-2选项1-2选项1-2选项1-2选项1-2选项1-2"
                }
              ]
            },
            {
              text: "选项2",
              value: 2
            },
            {
              text: "选项3",
              value: 3,
              children: [
                {
                  value: 3.1,
                  text: "选项3-1"
                },
                {
                  value: 3.2,
                  text: "选项3-2"
                }
              ]
            },
            {
              text: "选项4",
              value: 4
            },
            {
              text: "选项5",
              value: 5
            }
          ]
        }
      };
    }
  };
</script>
```

:::

### 层级多选列表

Select-list 组件提供带层级的多选选择列表。

1. 该控件仅适用于两层列表的场景
2. 内容较多、层级复杂、有多选操作、并有层级增多的可能，请使用树控件

:::demo 通过设置`type`属性和传入对应的`data`来使用层级列表。

```html
<template>
  <tl-select-list
    v-model="data4.value"
    :search-bar="data4.searchBar"
    :data="data4.data"
    :type="data4.type"
  ></tl-select-list>
</template>

<script>
  export default {
    data() {
      return {
        data4: {
          searchBar: true,
          value: [1.1],
          type: "group_checkbox",
          data: [
            {
              text: "选项1",
              value: 1,
              isExpanded: true,
              children: [
                {
                  value: 1.1,
                  text: "选项1-1"
                },
                {
                  value: 1.2,
                  text:
                    "选项1-2选项1-2选项1-2选项1-2选项1-2选项1-2选项1-2选项1-2"
                }
              ]
            },
            {
              text: "选项2",
              value: 2
            },
            {
              text: "选项3",
              value: 3,
              children: [
                {
                  value: 3.1,
                  text: "选项3-1"
                },
                {
                  value: 3.2,
                  text: "选项3-2"
                }
              ]
            },
            {
              text: "选项4",
              value: 4
            },
            {
              text: "选项5",
              value: 5
            }
          ]
        }
      };
    }
  };
</script>
```

:::

### Attributes

| 参数      | 说明       | 类型                  | 可选值                              | 默认值 |
| --------- | ---------- | --------------------- | ----------------------------------- | ------ |
| value     | 选中的值   | String, Number, Array | —                                   | -      |
| type      | 类型       | String                | radio/checkbox/group/group_checkbox | radio  |
| search-bar | 搜索栏开关 | Boolean               | —                                   | -      |
| data      | 列表内容   | Array                 | —                                   | []     |
| width     | 宽         | Number/String         | —                                   | --     |
| height    | 高         | Number/String         | —                                   | -      |

### Events

| 事件名称   | 说明                  | 回调参数                                               |
| ---------- | --------------------- | ------------------------------------------------------ |
| change     | 当 value 值改变时触发 | value 值                                               |
| click-list | 列表项被点击时触发    | {event: 原生事件通知对象, item: 被点击的列表项的 data} |

