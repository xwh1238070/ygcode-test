# YjCondition 条件选择

针对表单控件进行条件处理

### 基础用法

使用`v-model`传递值，`option`中的`dataType` 数据类型用于控制条件选择对应的控件。

:::demo 使用数组传递数据，[ operator, value ] operator 对应条件选择，value 对应条件选择的值。

```html
<template>
  <yj-condition v-model="value" :option="option" @change="handleChange"></yj-condition>
</template>
<script>
  export default {
    data() {
      return {
        value: '', // 可传数组 [ operator, value ]
        option: {
          dataType: 'string'
        }
      };
    },
    methods: {
      handleChange(value) {
        console.log(value);
      }
    }
  };
</script>
```

:::

### 默认类型

默认提供四种数据类型，分别对应字符、日期、数值和自定义类型。

:::demo

```html
<template>
  <div>
    <p>字符类型：</p>
    <yj-condition v-model="string" :option="option" @change="handleChange"></yj-condition>
    <p>日期类型：</p>
    <yj-condition v-model="date" :option="option1" @change="handleChange1"></yj-condition>
    <p>数值类型：</p>
    <yj-condition v-model="num" :option="option2" @change="handleChange2"></yj-condition>
    <p>自定义类型：</p>
    <yj-condition v-model="cust" :option="option3" @change="handleChange3"></yj-condition>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        string: '',
        option: {
          dataType: 'string'
        },
        date: '',
        option1: {
          dataType: 'date'
        },
        num: '',
        option2: {
          dataType: 'num'
        },
        cust: ['0', 1],
        option3: {
          dataType: 'combobox',
          data: [
            { id: 1, text: '数据1' },
            { id: 2, text: '数据2' }
          ]
        }
      };
    },
    methods: {
      handleChange(value) {
        console.log(value);
      },
      handleChange1(value) {
        console.log(value);
      },
      handleChange2(value) {
        console.log(value);
      },
      handleChange3(value) {
        console.log(value);
      }
    }
  };
</script>
```

:::

### 自定义条件数据

可以通过`option`中的`NumberCompareData` `DateCompareData` `ByteCompareData` `OtersCompareData`属性自定义条件数据。

:::demo `NumberCompareData` 对应数值类型条件 `DateCompareData` 对应日期类型条件 `ByteCompareData` 对应字符类型条件 `OtersCompareData` 对应自定义类型条件。使用`id` `text`对应下拉数据 key

```html
<template>
  <div>
    <p>字符类型：</p>
    <yj-condition v-model="string" :option="option" @change="handleChange"></yj-condition>
    <p>日期类型：</p>
    <yj-condition v-model="date" :option="option1" @change="handleChange1"></yj-condition>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        string: '0',
        option: {
          dataType: 'string',
          ByteCompareData: [
            {
              id: '0',
              text: '测试条件'
            },
            {
              id: '1',
              text: '测试条件1'
            }
          ]
        },
        date: '0',
        option1: {
          dataType: 'tl-date-picker',
          type: 'daterange',
          OtersCompareData: [
            {
              id: '0',
              text: '测试条件在范围'
            },
            {
              id: '1',
              text: '测试条件'
            }
          ]
        }
      };
    },
    methods: {
      handleChange(value) {
        console.log(value);
      },
      handleChange1(value) {
        console.log(value);
        if (value && value[0]) {
          if (value[0] === '1') {
            this.option1 = {
              dataType: 'tl-date-picker',
              type: 'date',
              OtersCompareData: [
                {
                  id: '0',
                  text: '测试条件在范围'
                },
                {
                  id: '1',
                  text: '测试条件'
                }
              ]
            };
          } else {
            this.option1 = {
              dataType: 'tl-date-picker',
              type: 'daterange',
              OtersCompareData: [
                {
                  id: '0',
                  text: '测试条件在范围'
                },
                {
                  id: '1',
                  text: '测试条件'
                }
              ]
            };
          }
          if(this.date && this.date[0] && this.date[0] !== value[0]) {
            this.date = value && value[0] ? [ value[0] ] : [];
          }
        }
      }
    }
  };
</script>
```

:::

### Attributes

| 参数            | 说明                             | 类型   | 可选值      | 默认值 |
| --------------- | -------------------------------- | ------ | ----------- | ------ |
| value / v-model | 绑定值                           | array  | `''` / `[]` | `''`   |
| option          | 条件对应控件参数（查看下方配置） | object | —           | —      |

### option

| 参数              | 说明                                  | 类型   | 可选值 | 默认值 |
| ----------------- | ------------------------------------- | ------ | ------ | ------ |
| dataType          | 数据类型(对应 yjpl 表单容器 dataType) | string | —      | 0      |
| ByteCompareData   | 字符类型条件数据                      | array  | —      | —      |
| DateCompareData   | 日期类型条件数据                      | array  | —      | —      |
| NumberCompareData | 数值类型条件数据                      | array  | —      | —      |
| OtersCompareData  | 自定义类型条件数据                    | array  | —      | —      |

### Events

| 事件名称 | 说明               | 回调参数        |
| -------- | ------------------ | --------------- |
| change   | 绑定值被改变时触发 | operator, value |
