# YjContainer YJPL表单容器

业务用表单组件，可根据对象配置来配置出相应的表单。

### 基本使用
通过 `data` 属性配置表单项目。

:::demo 

```html
<template>
  <div>
    <yj-container v-model="form" :data="data" label-suffix="："></yj-container>
  </div>
  
</template>
<script>
  export default {
    data () {
      return {
        form: {
          start: '2012-2-21',
          end: '2012-2-22',
          string: '测试事项',
          textarea: '回家',
        },
        data: [
          {
            name: 'start',
            dataType: 'date',
            label: '开始时间',
            required: true
          },
          {
            name: 'end',
            dataType: 'date',
            label: '结束时间',
            required: true
          },
          {
            name: 'select',
            dataType: 'combobox',
            label: '地区',
            idField: 'id',
            textField: 'text',
            data: [
              { id: 'zh', text: '珠海'},
              { id: 'wh', text: '武汉'},
              { id: 'bj', text: '北京'}
            ],
            cols: 2,
            required: true
          },
          {
            name: 'range',
            label: '输入区间',
            cols: 4,
            children: [
              {
                dataType: 'string',
                name: 'checked1',
                class: 'range-input'
              },
              {
                dataType: 'text',
                label: '至',
                class: 'range-text'
              },
              {
                dataType: 'string',
                name: 'checked2',
                class: 'range-input'
              }
            ]
          },
          {
            name: 'textarea',
            dataType: 'textarea',
            label: '出差事由',
            resize: 'vertical',
            rows: 1,
            cols: 4,
            tooltip: '这是一个出差事由'
          }
        ]
      }
    }
  };
</script>

<style>
  .range-text {
    display: block;
    width: 10%;
    float: left;
    text-align: center;
  }
  .range-input {
    width: 45%;
    float: left;
  }
</style>
```
:::

### 设置错误信息
通过 `setErrors` 方法，给相应的表单项设置错误信息。

:::demo 

```html
<template>
  <div>
    <yj-container v-model="form" :data="data" label-suffix="：" ref='container'></yj-container>
    <tl-button @click="handleErrors">设置错误信息</tl-button>
    <tl-button @click="restErrors">重置错误信息</tl-button>
  </div>
  
</template>
<script>
  export default {
    data () {
      return {
        form: {
          start: '2012-2-21',
          end: '2012-2-22',
          string: '测试事项',
          textarea: '回家'
        },
        data: [
          {
            name: 'start',
            dataType: 'date',
            label: '开始时间',
            required: true
          },
          {
            name: 'end',
            dataType: 'date',
            label: '结束时间',
            required: true
          },
          {
            name: 'range',
            label: '输入区间',
            cols: 2,
            children: [
              {
                dataType: 'string',
                name: 'checked1',
                class: 'range-input'
              },
              {
                dataType: 'text',
                label: '至',
                class: 'range-text'
              },
              {
                dataType: 'string',
                name: 'checked2',
                class: 'range-input'
              }
            ]
          },
          {
            name: 'textarea',
            dataType: 'textarea',
            label: '出差事由',
            resize: 'vertical',
            rows: 1,
            cols: 4
          }
        ]
      }
    },
    methods: {
      handleErrors() {
        this.$refs.container.setErrors({
          start: '这是一条开始时间的错误信息',
          end: '这是一条结束时间的错误信息'
        });
      },
      restErrors() {
        this.$refs.container.clearErrors();
      }
    }
  };
</script>

<style>
  .range-text {
    display: block;
    width: 10%;
    float: left;
    text-align: center;
  }
  .range-input {
    width: 45%;
    float: left;
  }
</style>
```
:::

### 自定义表单项
当通过配置无法满足时，可通过 slot 自定义表单项，仅在 `auto-width` 模式下生效。

:::demo 

```html
<template>
  <div>
    <yj-container v-model="form" :data="data" :events="eventsModel" label-suffix="：" auto-width>
      <tl-form-item label="前置扩展" slot="prepend">这是一个前置扩展</tl-form-item>
      <tl-form-item label="自定义扩展" slot="start-prepend">扩展至start前</tl-form-item>
      <tl-form-item label="自定义扩展" slot="end-append">扩展至end后</tl-form-item>
      <tl-form-item label="后置扩展">这是一个后置扩展</tl-form-item>
    </yj-container>
  </div>
  
</template>
<script>
  export default {
    data () {
      return {
        // 通过 name 和事件名自定义事件
        eventsModel: {
          start: {
            change: (val) => {
              console.log(val)
            }
          },
          end: {
            blur: (val) => {
              console.log(val)
            }
          }
        },
        form: {
          start: '2012-2-21',
          end: '2012-2-22'
        },
        data: [
          {
            name: 'start',
            dataType: 'date',
            label: '开始时间',
            required: true,
            tooltip: '这是一个开始时间'
          },
          {
            name: 'end',
            dataType: 'date',
            label: '结束时间',
            required: true
          }
        ]
      }
    }
  };
</script>

<style>
  .range-text {
    display: block;
    width: 10%;
    float: left;
    text-align: center;
  }
  .range-input {
    width: 45%;
    float: left;
  }
</style>
```
:::

### 自定义事件
通过 `events` 属性可自定义表单事件。

:::demo 

```html
<template>
  <div>
    <yj-container v-model="form" :data="data" :events="eventsModel" label-suffix="："></yj-container>
  </div>
  
</template>
<script>
  export default {
    data () {
      return {
        // 通过 name 和事件名自定义事件
        eventsModel: {
          start: {
            change: (val) => {
              console.log(val)
            }
          },
          end: {
            blur: (val) => {
              console.log(val)
            }
          }
        },
        form: {
          start: '2012-2-21',
          end: '2012-2-22',
          string: '测试事项',
          textarea: '回家',
        },
        data: [
          {
            name: 'start',
            dataType: 'date',
            label: '开始时间',
            required: true,
            tooltip: '这是一个开始时间'
          },
          {
            name: 'end',
            dataType: 'date',
            label: '结束时间',
            required: true
          },
          {
            name: 'range',
            label: '输入区间',
            cols: 2,
            children: [
              {
                dataType: 'string',
                name: 'checked1',
                class: 'range-input'
              },
              {
                dataType: 'text',
                label: '至',
                class: 'range-text'
              },
              {
                dataType: 'string',
                name: 'checked2',
                class: 'range-input'
              }
            ]
          },
          {
            name: 'textarea',
            dataType: 'textarea',
            label: '出差事由',
            resize: 'vertical',
            rows: 1,
            cols: 4
          }
        ]
      }
    }
  };
</script>

<style>
  .range-text {
    display: block;
    width: 10%;
    float: left;
    text-align: center;
  }
  .range-input {
    width: 45%;
    float: left;
  }
</style>
```
:::

:::tip
该组件基于
<router-link :to="{ path: '/components/form/form' }">tl-form</router-link>
二次封装，属性事件用法相同，下方只列举该组件特有属性。

:::

### YjContainer Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| value   | 绑定的值，为对象，每个表单元素的 `name` 属性做为key值          | object | - | - |
| data   | 表单渲染数据，详细见下表          | array | - | - |
| value-key | 实际 value 的 key 值，如果 `value` 的子项是对象，请配置该值 | string | - | - |
| state | 状态管理属性，如需实现全局状态管理，请将 `stateModel` 传入该属性 | object | -  | - |
| auto-width    | 表单列数和宽度自适应模式，可参考在查询面板中使用的样例 | boolean | — | false |
| max-width    | 表单宽度自适应的最大宽度，如超过这个宽度则会发生变化 | number / string | — | 384 |
| min-width    | 表单宽度自适应的最小宽度，如超过这个宽度则会发生变化 | number / string | — | 258 |
| cols    | 每行放置的表单元素个数，设置 `auto-width` 时不生效 | number | — | 4 |
| gutter    | 表单元素之间的间距 | number | — | 20 |
| events | 自定义组件事件，根据相应的 `name` 配置相应组件的事件 | object | - |

### data

| 参数         | 说明                   | 类型   | 可选值               | 默认值 |
| ------------ | ---------------------- | ------ | -------------------- | ------ |
| name | 按钮标识，与 `value` 内的值对应，可视为id，当绑定 `stateModel` 时，该属性作为 `stateModel` 内对应的值 | string  | — | - |
| laebl | label字符 | string  | — | - |
| dataType | 表单类型，详细见下表 | function(data)  | — | - |
| required | 是否必填 | boolean  | - | false |
| cols     | 占的列数 | number | - | — |
| 其他     | 其他为别的组件自身的属性 | - | - | — |

### dataType

> `dataType` 基于传入名称进行渲染组件，如果下列类型中无满足需求的，可以自行将组件注册至全局，然后将标签名传入到 `dataType` 中。

| 名称         | 对应组件 |
| ------------ | ----- |
| string | tl-input |
| num | tl-input-number |
| switch | tl-switch |
| combobox | tl-select |
| entity-select | tl-entity-select |
| file | tl-upload-input |
| date | tl-date-picker |
| time | el-time-picker |

## YjContainer Methods

| 方法名      | 说明          | 参数
|---------- |-------------- | --------------
| validate | 对整个表单进行校验的方法，参数为一个回调函数。该回调函数会在校验结束后被调用，并传入两个参数：是否校验成功和未通过校验的字段。若不传入回调函数，则会返回一个 promise | Function(callback: Function(boolean, object))
| validateField | 对部分表单字段进行校验的方法 | Function(props: array \| string, callback: Function(errorMessage: string))
| resetFields | 对整个表单进行重置，将所有字段值重置为初始值并移除校验结果 | —
| clearValidate | 移除表单项的校验结果。传入待移除的表单项的 prop 属性或者 prop 组成的数组，如不传则移除整个表单的校验结果 | Function(props: array \| string)
| setErrors | 给相应的表单项设置错误信息 | Function(errors: object)
| clearErrors | 移除已设置的错误信息 | -

## YjContainer Events
| 事件名称 | 说明    | 回调参数  |
|--------- |-------- |---------- |
| validate | 任一表单项被校验后触发 | 被校验的表单项 prop 值，校验是否通过，错误消息（如果存在） |

## YjContainer Slots
|   name  | 说明     |
|---------|---------|
|    —    | 表单元素 |
| prepend  | 往前插入表单元素 |
| {name}-append  | 往指定表单项后插入表单元素 |
| {name}-prepend | 往指定表单项元素前插入表单元素 |
| button | 按钮区域，仅在 `auto-width` 时生效 |