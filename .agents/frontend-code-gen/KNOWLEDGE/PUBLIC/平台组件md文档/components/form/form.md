# Form 表单
为网站提供导航功能的菜单。

### 典型表单
包括各种表单项，比如输入框、选择器、开关、单选框、多选框等。自带宽度自适应响应。

:::demo 在 Form 组件中，每一个表单域由一个 Form-Item 组件构成，表单域中可以放置各种类型的表单控件，包括 Input、Select、Checkbox、Radio、Switch、DatePicker、TimePicker。表单组件默认自带响应式，当组件的宽度达到最小宽度，则标签从右侧布局改为上方布局，可以防止 label 占用的位置过多，组件显示不全。

```html
<tl-form>
  <tl-row :gutter="20">
    <tl-col :span="12">
      <tl-form-item label="文本标签1：">
        <tl-input v-model="value1"></tl-input>
      </tl-form-item>
    </tl-col>
    <tl-col :span="12">
      <tl-form-item label="文本标签2：">
        <tl-input v-model="value2"></tl-input>
      </tl-form-item>
    </tl-col>
  </tl-row>
  <tl-row :gutter="20">
    <tl-col :span="12">
      <tl-form-item label="文本标签3：">
        <tl-input v-model="value3"></tl-input>
      </tl-form-item>
    </tl-col>
    <tl-col :span="12">
      <tl-form-item label="文本标签4：">
        <tl-input v-model="value4"></tl-input>
      </tl-form-item>
    </tl-col>
  </tl-row>
</tl-form>
<script>

export default {
  data () {
    return {
      value1: '',
      value2: '',
      value3: '',
      value4: ''
    }
  }
}
</script>
```
:::

### 表单验证

在防止用户犯错的前提下，尽可能让用户更早地发现并纠正错误。

:::demo Form 组件提供了表单验证的功能，只需要通过 `rules` 属性传入约定的验证规则，并将 Form-Item 的 `prop` 属性设置为需校验的字段名即可。校验规则参见 [async-validator](https://github.com/yiminghe/async-validator)
```html
<template>
  <tl-form :model="ruleForm" :rules="rules" label-suffix="：" ref="ruleForm">
    <tl-form-item label="活动名称" prop="name">
      <tl-input v-model="ruleForm.name"></tl-input>
    </tl-form-item>
    <tl-form-item label="活动区域" prop="region">
      <tl-select
        v-model="ruleForm.region"
        placeholder="请选择活动区域"
        id-field="id"
        text-field="text"
        :data="[
          { id: 'zh', text: '珠海'},
          { id: 'wh', text: '武汉'},
          { id: 'bj', text: '北京'}
        ]"
      >
      </tl-select>
    </tl-form-item>
    <tl-form-item label="活动时间" required>
      <tl-col :span="11">
        <tl-form-item prop="date1">
          <tl-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></tl-date-picker>
        </tl-form-item>
      </tl-col>
      <tl-col class="line" :span="2">-</tl-col>
      <tl-col :span="11">
        <tl-form-item prop="date2">
          <tl-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></tl-time-picker>
        </tl-form-item>
      </tl-col>
    </tl-form-item>
    <tl-form-item label="即时配送" prop="delivery">
      <tl-switch v-model="ruleForm.delivery"></tl-switch>
    </tl-form-item>
    <tl-form-item label="活动性质" prop="type">
      <tl-checkbox-group v-model="ruleForm.type">
        <tl-checkbox label="美食/餐厅线上活动" name="type"></tl-checkbox>
        <tl-checkbox label="地推活动" name="type"></tl-checkbox>
        <tl-checkbox label="线下主题活动" name="type"></tl-checkbox>
        <tl-checkbox label="单纯品牌曝光" name="type"></tl-checkbox>
      </tl-checkbox-group>
    </tl-form-item>
    <tl-form-item label="特殊资源" prop="resource">
      <tl-radio-group v-model="ruleForm.resource">
        <tl-radio label="线上品牌商赞助"></tl-radio>
        <tl-radio label="线下场地免费"></tl-radio>
      </tl-radio-group>
    </tl-form-item>
    <tl-form-item label="活动形式" prop="desc">
      <tl-input type="textarea" v-model="ruleForm.desc"></tl-input>
    </tl-form-item>
    <tl-form-item>
      <tl-button type="primary" @click="submitForm('ruleForm')">立即创建</tl-button>
      <tl-button @click="resetForm('ruleForm')">重置</tl-button>
    </tl-form-item>
  </tl-form>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择活动区域', trigger: 'change' }
          ],
          date1: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ],
          resource: [
            { required: true, message: '请选择活动资源', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>
  .line {
    text-align: center;
  }
</style>
```
:::

### 自定义校验规则

这个例子中展示了如何使用自定义验证规则来完成密码的二次验证。

:::demo 本例还使用`status-icon`属性为输入框添加了表示校验结果的反馈图标。
```html
<tl-form :model="ruleForm" status-icon :rules="rules" label-suffix="：" ref="ruleForm">
  <tl-form-item label="密码" prop="pass">
    <tl-input v-model="ruleForm.pass" show-password autocomplete="off"></tl-input>
  </tl-form-item>
  <tl-form-item label="确认密码" prop="checkPass">
    <tl-input v-model="ruleForm.checkPass" show-password autocomplete="off"></tl-input>
  </tl-form-item>
  <tl-form-item label="年龄" prop="age">
    <tl-input v-model.number="ruleForm.age"></tl-input>
  </tl-form-item>
  <tl-form-item>
    <tl-button type="primary" @click="submitForm('ruleForm')">提交</tl-button>
    <tl-button @click="resetForm('ruleForm')">重置</tl-button>
  </tl-form-item>
</tl-form>

<script>
  export default {
    data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('年龄不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          pass: '',
          checkPass: '',
          age: ''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          age: [
            { validator: checkAge, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
```
:::tip
自定义校验 callback 必须被调用。 更多高级用法可参考 [async-validator](https://github.com/yiminghe/async-validator)。
:::

### 在查询面板中使用

这个例子中展示了如何在查询面板中使用，表单会自带宽度自适应响应。

:::demo 在查询面板中使用时会自动生效，亦可通过`auto-width`属性主动使用，请勿再内部使用layout布局。
```html
<tl-search-panel>
  <tl-form ref="form" label-suffix="：">
    <tl-form-item label="活动名称">
      <tl-input v-model="form.name"></tl-input>
    </tl-form-item>
    <tl-form-item label="活动区域">
      <tl-select v-model="form.region" placeholder="请选择活动区域"></tl-select>
    </tl-form-item>
    <tl-form-item label="活动时间">
      <tl-date-picker v-model="form.date" type="date" placeholder="选择日期" style="width: 100%;"></tl-date-picker>
    </tl-form-item>
    <tl-form-item label="活动信息">
      <tl-input v-model="form.info"></tl-input>
    </tl-form-item>
    <tl-form-item button>
      <tl-button>重置</tl-button>
      <tl-button type="primary">查询</tl-button>
    </tl-form-item>
  </tl-form>
</tl-search-panel>

<script>
  export default {
    data() {
      return {
        form: {
          name: '',
          region: '',
          date: '',
          info: ''
        }
      }
    }
  }
</script>
```
:::

### Form Attributes

| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| model   | 表单数据对象 | object      |                  —                |  — |
| rules    | 表单验证规则 | object | — | — |
| inline    | 行内表单模式 | boolean | — | false |
| auto-width    | 表单宽度自适应模式，可参考在查询面板中使用的样例 | boolean | — | false |
| auto-width    | 表单宽度自适应模式，可参考在查询面板中使用的样例 | boolean | — | false |
| max-width    | 表单宽度自适应的最大宽度，如超过这个宽度则会发生变化 | number / string | — | 384 |
| min-width    | 表单宽度自适应的最小宽度，如超过这个宽度则会发生变化 | number / string | — | 258 |
| label-position | 表单域标签的位置，如果值为 left 或者 right 时，则需要设置 `label-width`。默认值为 `right` 时，表单适用自适应规则。 | string |  right/left/top            | right |
| label-width | 表单域标签的宽度，例如 '50px'。作为 Form 直接子元素的 form-item 会继承该值。支持 `auto`，当值为 `auto` 时，表单适用自适应规则。 | string | — | auto |
| label-suffix | 表单域标签的后缀 | string | — | — |
| hide-required-asterisk | 是否显示必填字段的标签旁边的红色星号 | boolean | — | false |
| show-message  | 是否显示校验错误信息 | boolean | — | true |
| inline-message  | 是否以行内形式展示校验信息 | boolean | — | false |
| status-icon  | 是否在输入框中显示校验结果反馈图标 | boolean | — | false |
| validate-on-rule-change  | 是否在 `rules` 属性改变后立即触发一次验证 | boolean | — | true |
| size  | 用于控制该表单内组件的尺寸 | string | medium / small / mini | — |
| disabled | 是否禁用该表单内的所有组件。若设置为 true，则表单内组件上的 disabled 属性不再生效 | boolean | — | false |

### Form Methods

| 方法名      | 说明          | 参数
|---------- |-------------- | --------------
| validate | 对整个表单进行校验的方法，参数为一个回调函数。该回调函数会在校验结束后被调用，并传入两个参数：是否校验成功和未通过校验的字段。若不传入回调函数，则会返回一个 promise | Function(callback: Function(boolean, object))
| validateField | 对部分表单字段进行校验的方法 | Function(props: array \| string, callback: Function(errorMessage: string))
| resetFields | 对整个表单进行重置，将所有字段值重置为初始值并移除校验结果 | —
| clearValidate | 移除表单项的校验结果。传入待移除的表单项的 prop 属性或者 prop 组成的数组，如不传则移除整个表单的校验结果 | Function(props: array \| string)

### Form Events
| 事件名称 | 说明    | 回调参数  |
|--------- |-------- |---------- |
| validate | 任一表单项被校验后触发 | 被校验的表单项 prop 值，校验是否通过，错误消息（如果存在） |

### Form-Item Attributes

| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| prop    | 表单域 model 字段，在使用 validate、resetFields 方法的情况下，该属性是必填的 | string    | 传入 Form 组件的 `model` 中的字段 | — |
| label | 标签文本 | string | — | — |
| label-width | 表单域标签的宽度，例如 '50px'。作为 Form 直接子元素的 form-item 会继承该值。支持 `auto`，当值为 `auto` 时，表单适用自适应规则。 | string |       —       | auto |
| required | 是否必填，如不设置，则会根据校验规则自动生成 | boolean | — | false |
| rules    | 表单验证规则 | object | — | — |
| button | 查询面板中使用时，按钮区域请将改属性设为true。 | boolean | — | false |
| error    | 表单域验证错误信息, 设置该值会使表单验证状态变为`error`，并显示该错误信息 | string | — | — |
| show-message  | 是否显示校验错误信息 | boolean | — | true |
| inline-message  | 以行内形式展示校验信息 | boolean | — | false |
| size  | 用于控制该表单域下组件的尺寸 | string | medium / small / mini | - |

### Form-Item Slot
| name | 说明 |
|------|--------|
| — | Form Item 的内容 |
| label | 标签文本的内容 |

### Form-Item Scoped Slot
|  name  |   说明  |
|--------|--------|
|  error | 自定义表单校验信息的显示方式，参数为 { error } |

### Form-Item Methods

| 方法名      | 说明          | 参数
|---------- |-------------- | --------------
| resetField | 对该表单项进行重置，将其值重置为初始值并移除校验结果 | -
| clearValidate | 移除该表单项的校验结果 | -
