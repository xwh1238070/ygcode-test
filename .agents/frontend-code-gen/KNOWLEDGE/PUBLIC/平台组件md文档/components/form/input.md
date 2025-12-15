## Input 输入框
通过鼠标或键盘输入字符。   
基于el-input封装扩展了状态设置，前后缀标签，对齐，校验提示，格式化等能力。     
注意：格式化的相关功能只在失焦的状态下生效，聚焦的时候显示原始的值。

### 基础用法

:::demo

```html
<template>
  <tl-input class="input" v-model="input" placeholder="请输入内容"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::


### 禁用状态

:::demo 通过 `disabled` 属性指定是否禁用 input 组件
```html
<template>
  <tl-input class="input" placeholder="请输入内容" v-model="input" disabled></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 只读状态

:::demo 通过 `readonly` 属性指定是否只读 input 组件
```html
<template>
  <tl-input class="input" placeholder="请输入内容" v-model="input" readonly></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 可清空

:::demo 使用`clearable`属性即可得到一个可清空的输入框

```html
<template>
  <tl-input class="input" v-model="input" placeholder="请输入内容" clearable></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    }
  }
}
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 显示更多按钮 ^(8.5.0)

可以显示点击更多按钮

:::demo 为 `input` 添加 `show-more` 属性即可显示更多按钮。可通过 `more` 事件触发点击后事件。
```html
<template>
  <tl-input class="input" v-model="input" placeholder="请输入内容" show-more @more="handleMore"></tl-input>
</template>

<script>
  export default {
    data () {
      return {
        input: ''
      }
    },
    methods: {
      handleMore() {
        alert()
      }
    }
  }
</script>
```
:::

### 密码框

:::demo 使用`show-password`属性即可得到一个可切换显示隐藏的密码框

```html
<template>
  <tl-input class="input" v-model="input" placeholder="请输入内容" show-password></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 带 icon 的输入框

带有图标标记输入类型

:::demo 可以通过 `prefix-icon` 和 `suffix-icon` 属性在 input 组件首部和尾部增加显示图标，也可以通过 slot 来放置图标。
```html
<template>
  <div>
    <div class="demo-input-suffix">
      属性方式：
      <tl-input class="input" placeholder="请选择日期" suffix-icon="el-icon-date" v-model="input1"></tl-input>
      <tl-input class="input" placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input2"></tl-input>
    </div>
    <div class="demo-input-suffix">
      slot 方式：
      <tl-input class="input" placeholder="请选择日期" v-model="input3">
        <i slot="suffix" class="el-input__icon el-icon-date"></i>
      </tl-input>
      <tl-input class="input" placeholder="请输入内容" v-model="input4">
        <i slot="prefix" class="el-input__icon el-icon-search"></i>
      </tl-input>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: '',
      input3: '',
      input4: ''
    };
  }
};
</script>

<style>
  .demo-input-suffix{
    margin-bottom: 15px;
  }
  .demo-input-suffix .input{
    width: 200px;
  }
</style>
```
:::

### 文本域

用于输入多行文本信息，通过将 `type` 属性的值指定为 textarea。

:::demo 文本域高度可通过 `rows` 属性控制,默认是2
```html
<template>
  <tl-input class="input" type="textarea" :rows="2" v-model="input" placeholder="请输入内容"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    }
  }
};
</script>

<style>
.input {
  width: 420px;
}
</style>
```
:::

### 可自适应文本高度的文本域

通过设置 `autosize` 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 `autosize` 还可以设定为一个对象，指定最小行数和最大行数。

:::demo

```html
<template>
  <div>
    <tl-input class="input" type="textarea" autosize placeholder="请输入内容" v-model="textarea1"></tl-input>
    <div style="margin: 20px 0;"></div>
    <tl-input
      class="input"
      type="textarea"
      :autosize="{ minRows: 2, maxRows: 4}"
      placeholder="请输入内容"
      v-model="textarea2"
    ></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      textarea1: '',
      textarea2: ''
    };
  }
};
</script>

<style>
.input {
  width: 420px;
}
</style>
```
:::

### 复合型输入框

可前置或后置元素，一般为标签或按钮

:::demo 可通过 slot 来指定在 input 中前置或者后置内容。
```html
<template>
  <div class="demo-input-select">
    <tl-input placeholder="请输入内容" v-model="input1">
      <template #prepend>http://</template>
      <template #append>.com</template>
    </tl-input>
    <tl-input v-model="input2" placeholder="请输入网址" style="margin-top: 15px;">
      <template #prepend>
        <tl-select v-model="select1" placeholder="请选择">
          <tl-option label="http://" value="http://"></tl-option>
          <tl-option label="https://" value="https://"></tl-option>
        </tl-select>
      </template>
      <template #append>
        <tl-select v-model="select2" placeholder="请选择">
          <tl-option label=".com" value=".com"></tl-option>
          <tl-option label=".cn" value=".cn"></tl-option>
        </tl-select>
      </template>
    </tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: '',
      select1: 'http://',
      select2: '.com'
    };
  }
};
</script>

<style>
.demo-input-select .el-select .el-input {
  width: 200px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>
```
:::

### 尺寸

:::demo 可通过 `size` 属性指定输入框的尺寸，除了默认的大小外，还提供了 large、small 和 mini 三种尺寸。
```html
<template>
  <div>
    <tl-input class="input" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="input1"></tl-input>
    <tl-input class="input" size="large" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="input4"></tl-input>
    <tl-input class="input" size="medium" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="input2"></tl-input>
    <tl-input class="input" size="small" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="input3"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: '',
      input3: '',
      input4: ''
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 带输入建议

根据输入内容提供对应的输入建议

:::demo autocomplete 是一个可带输入建议的输入框组件，`fetch-suggestions` 是一个返回输入建议的方法属性，如 querySearch(queryString, cb)，在该方法中你可以在你的输入建议数据准备好时通过 cb(data) 返回到 autocomplete 组件中，`trigger-on-focus`可以设置激活即列出输入建议还是输入后匹配输入建议。还可以使用`scoped slot`自定义输入建议的模板。该 scope 的参数为`item`，表示当前输入建议对象。
```html
<template>
  <tl-autocomplete
    ref="autocomplete"
    v-model="input"
    :fetch-suggestions="querySearch"
    placeholder="请输入内容"
    clearable
    @clear="handleClear"
  >
    <template slot-scope="{ item }">
      <div v-html="slotHighlight(item.value)"></div>
    </template>
  </tl-autocomplete>
</template>

<script>
export default {
  data() {
    return {
      input: '',
      suggestions: [
        { value: '联想1' },
        { value: '输入建议1' },
        { value: '联想2' },
        { value: '输入建议2' },
        { value: '联想3' },
        { value: '输入建议3' },
        { value: '联想4' },
        { value: '输入建议4' },
        { value: '联想5' },
        { value: '联想6' }
      ]
    };
  },
  methods: {
    // 处理联想input的事件
    querySearch(queryString, callback) {
      const suggestions = this.suggestions;
      let results = queryString
        ? suggestions.filter(this.createFilter(queryString))
        : suggestions;
      // 调用 callback 返回建议列表的数据
      callback(results);
    },
    // 处理联想input的事件
    createFilter(queryString) {
      return data => {
        return (
          data.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1
        );
      };
    },
    // 处理联想input的事件
    handleClear() {
      this.$refs.autocomplete.activated = true;
    },
    // 处理联想input的事件
    slotHighlight(value) {
      let result = "";
      const inputValue = this.input;
      if (value.indexOf(inputValue) !== -1) {
        result = value.replace(
          inputValue,
          `<span style="color: #1687E8">${inputValue}</span>`
        );
      }
      return result;
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::




### 输入长度限制

:::demo `maxlength` 和 `minlength` 是原生属性，用来限制输入框的字符长度，其中字符长度是用 Javascript 的字符串长度统计的。对于类型为 `text` 或 `textarea` 的输入框，在使用 `maxlength` 属性限制最大输入长度的同时，可通过设置 `show-word-limit` 属性来展示字数统计。
```html
<template>
  <div>
    <tl-input class="input" placeholder="请输入内容" v-model="text" maxlength="10" show-word-limit></tl-input>
    <div style="margin: 20px 0;"></div>
    <tl-input
      class="textarea"
      type="textarea"
      placeholder="请输入内容"
      v-model="textarea"
      maxlength="1000"
      :rows="10"
      show-word-limit
    ></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      text: '',
      textarea: ''
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
.textarea {
  width: 300px;
}
</style>
```
:::

### 最大值和最小值限制

:::demo `max`和`min`属性只针对`num`, `number`, `integer`, `range`, `date`, `datetime`, `datetime-local`, `month`, `time`, `week`生效
```html
<template>
  <div>
    <span>最小值为5：</span>
    <tl-input class="input" type="num" v-model="input1" :min="5" placeholder="请输入内容"></tl-input>
    <span style="margin-left: 20px">最大值为8：</span>
    <tl-input class="input" type="num" v-model="input2" :max="8" placeholder="请输入内容"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: ''
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

<!-- ### 最大字节和最小字节限制

:::demo 规则：1个英文和数字为1个字节，1个中文为两个字节。超过最大字节的时候，限制输入。不满足最小字节的时候，失焦报错提示。
```html
<template>
  <div>
    <span>最小字节为2：</span>
    <tl-input class="input" type="num" v-model="input1" :minbyte="2" placeholder="请输入内容"></tl-input>
    <span style="margin-left: 20px">最大字节为5：</span>
    <tl-input class="input" type="num" v-model="input2" :maxbyte="5" placeholder="请输入内容"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: ''
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
::: -->

### 常用的数字类型
:::demo `number`类型会在输入框最右侧带有上下微调的小箭头; `num`类型则不会有; `integer`类型只能输入整数
```html
<template>
  <div class="demo-input-number">
    <span>number类型：</span>
    <tl-input class="input" type="number" v-model="input1" placeholder="请输入内容"></tl-input>
    <span>num类型：</span>
    <tl-input class="input" type="num" v-model="input2" placeholder="请输入内容"></tl-input>
    <span>integer类型：</span>
    <tl-input class="input" type="integer" v-model="input3" placeholder="请输入内容"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: '',
      input3: ''
    }
  }
};
</script>

<style>
.input {
  width: 200px;
  margin-bottom: 10px;
}
.demo-input-number span:not(:first-child) {
  margin-left: 20px;
  margin-bottom: 10px;
}
</style>
```
:::

### 文字对齐方式
:::demo `align`设置为`left`、`center`、`right`可分别使内容居左、居中、居右显示，默认是left
```html
<template>
  <div>
    <div>
      <span>始终右对齐：</span>
      <tl-input class="input" align="right" v-model="input1" placeholder="请输入内容"></tl-input>
    </div>
    <div style="margin-top: 20px">
      <span>失焦右对齐：</span>
      <tl-input
        class="input"
        :align="align"
        v-model="input2"
        @focus="handleFocus"
        @blur="handleBlur"
        placeholder="请输入内容"
      ></tl-input>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input1: '',
      input2: '',
      align: null
    };
  },
  methods: {
    handleFocus() {
      this.align = 'left';
    },
    handleBlur() {
      if(this.input2 && this.input2 !== '') this.align = 'right';
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 自动填充
:::demo `preset-suffix`为预置字段，当输入框有值且聚焦的时候按下enter键可自动填充该预置字段
```html
<template>
  <tl-input class="input" v-model="input" preset-suffix="@ygsoft.com" placeholder="请输入内容"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: ''
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-小数位
:::demo 在input失焦的时候进行格式化，聚焦的时候显示真实的数据。`precision`设置格式化小数位位数,为`null`或者`undefined`则不设置小数位
```html
<template>
  <tl-input class="input" type="num" precision="2" v-model="input" placeholder="请输入内容"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: 1.2345
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-负数显示带颜色
:::demo 在input失焦的时候进行格式化，聚焦的时候显示真实的数据。`negative-color`设置字体颜色，当且仅当值为负数时才生效
```html
<template>
  <tl-input class="input" type="num" negative-color="red" v-model="input" placeholder="请输入内容"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '-123'
    }
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-负数显示括号
:::demo 在input失焦的时候进行格式化，聚焦的时候显示真实的数据。`bracketabled`设置字体为负数时是否显示括号，当且仅当值为负数时才生效
```html
<template>
  <tl-input class="input" type="num" bracketabled v-model="input" placeholder="请输入内容"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '-123'
    }
  }
}
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-分隔符
:::demo 分隔符设置`type`：`separation`，还可以用`separation`指定分隔符的字符，默认是逗号
```html
<template>
  <div>
    <tl-input class="input" :format="format1" v-model="input"></tl-input>
    <tl-input class="input" :format="format2" v-model="input"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '123456',
      format1: {
        type: 'separation'
      },
      format2: {
        type: 'separation',
        separation: '-'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-折合
:::demo 折合设置`type`：`convert`，`numUnit`：折合单位。`numUnit`可选值：`perthousand`(0.001)、`percent`(0.01)、`ten`(十)、`hundred`(百)、`thousand`(千)、`tenThousand`(万)、`hundredThousand`(十万)、`million`(百万)、`tenMillion`(千万)、`hundredMillion`(亿)、`billion`(十亿)
```html
<template>
  <div>
    <span>折合成万：</span>
    <tl-input class="input" :format="format" v-model="input"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '10000',
      format: {
        type: 'convert',
        numUnit: 'tenThousand'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-百分比
:::demo 百分比设置`type`：`percentage`，`direct`：遵循3.0规则，设置为`true`时，0.3会转成0.3%，为`false`时，0.3会转成30%，默认为`false`
```html
<template>
  <div>
    <tl-input class="input" :format="format1" v-model="input"></tl-input>
    <tl-input class="input" :format="format2" v-model="input"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '0.3',
      format1: {
        type: 'percentage',
      },
      format2: {
        type: 'percentage',
        direct: true
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-货币
:::demo 货币设置`type`：`currency`，`symbol`：货币前面的货币符号
```html
<template>
  <tl-input class="input" :format="format" v-model="input"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '123',
      format: {
        type: 'currency',
        symbol: '￥'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-科学计数
:::demo 科学计数设置`type`：`scientificCount`
```html
<template>
  <tl-input class="input" :format="format" v-model="input"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '123',
      format: {
        type: 'scientificCount'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-中文大小写
:::demo 中文大小写设置`type`：`toChinese`，`zhType`：要转换的中文类型。包含`Lower`(中文小写)、`Upper`(中文大写)、`MoneyLower`(金额小写)、`MoneyUpper`(金额大写)，默认为`Lower`
```html
<template>
  <tl-input class="input" :format="format" v-model="input"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '123',
      format: {
        type: 'toChinese',
        zhType: 'Lower'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-时间转换
:::demo 时间转换设置`type`：`date`，支持传入时间戳或其他格式的时间。`format`：要转换的时间类型。包含`yyyy/MM/dd HH:mm:ss`、`yyyy年MM月dd日`等。`isFillZero`: 单位数月份会在月份前补0,`isCHN`: 转成中文、`isENGMonth`: 月份为英文
```html
<template>
  <div>
    <tl-input class="input" :format="format1" v-model="input"></tl-input>
    <tl-input class="input" :format="format2" v-model="input"></tl-input>
    <tl-input class="input" :format="format3" v-model="input"></tl-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '1612163663000',
      format1: {
        type: 'date',
        isFillZero: true
      },
      format2: {
        type: 'date',
        isCHN: true
      },
      format3: {
        type: 'date',
        isENGMonth: true
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-掩码
:::demo 掩码设置`type`：`secret`，`secret`: 要转成掩码的字符,默认是*。static： 保留字段，即该字段不会被转成掩码
```html
<template>
  <tl-input class="input" :format="format" v-model="input"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '1aa2bb3cc4dd',
      format: {
        type: 'secret',
        secret: '*',
        static: 'bb'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-银行卡
:::demo银行卡设置 `type`：`bankCard`，会按照4位数字分隔显示
```html
<template>
  <tl-input class="input" type="num" :format="format" v-model="input"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '2898956129090121',
      format: {
        type: 'bankCard'
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::

### 格式化-手机号
:::demo 手机号设置`type`：`mobile`，会按照特定格式显示
```html
<template>
  <tl-input class="input" :format="format" v-model="input"></tl-input>
</template>

<script>
export default {
  data() {
    return {
      input: '13808436869',
      format: {
        type: 'mobile',
        secret: true,
      }
    };
  }
};
</script>

<style>
.input {
  width: 200px;
}
</style>
```
:::
<!-- 身份证 -->
### 格式化-身份证
:::demo 身份证设置`type`：`idCard`，`secret`: 是否加密，默认为false。`secretChar`: 加密字符，默认为*。`secretStart`: 加密开始位置，默认为3。`secretLength`: 加密长度，默认为11。
```html
<template>
  <tl-input class="input" :format="format" v-model="input"></tl-input>
</template>
<script>
export default {
  data() {
    return {
      input: '110101199003077890',
      format: {
        type: 'idCard',
        // 是否加密
        secret: true,
        // 加密字符
        secretChar: '*',
        // 加密开始位置
        secretStart: 3,
        // 加密长度
        secretLength: 11
      }
    };
  }
};
</script>
<style>
.input {
  width: 200px;
}
</style>
```
:::

### 特殊字符输入限制
:::demo 通过配置`limit-chars`进入输入限制，禁止输入配置中的字符
```html
<template>
  <div>
    <span>有特殊字符限制(&*@#$)：</span>
    <tl-input
      class="input"
      limit-chars="&,*,@,#,$"
      placeholder="请输入内容"
      v-model="input"
    ></tl-input>
  </div>
</template>
<script>
export default {
  data() {
    return {
      input: ''
    };
  }
};
</script>
<style>
.input {
  width: 200px;
}
</style>
```
:::






### Input Attributes

| 参数          | 说明            | 类型            | 可选值                 | 默认值   |
|-------------  |---------------- |---------------- |---------------------- |-------- |
| type         | 类型   | string  | text，textarea 和其他 [原生 input 的 type 值](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#Form_%3Cinput%3E_types) | text |
| value / v-model | 绑定值           | string / number  | — | — |
| maxlength     | 原生属性，最大输入长度      | number          |  —  | — |
| minlength     | 原生属性，最小输入长度      | number          | — | — |
| maxbyte     |  最大输入字节      | string / number          |  —  | — |
| minbyte     | 最小输入字节      | string / numbe          | — | — |
| show-word-limit | 是否显示输入字数统计，只在 `type = "text"` 或 `type = "textarea"` 时有效 | boolean    |  `true`/`false`  | `false` |
| placeholder   | 输入框占位文本    | string          | — | — |
| clearable     | 是否可清空        | boolean         | `true`/`false` | `false` |
| show-password | 是否显示切换密码图标| boolean         | `true`/`false` | `false` |
| disabled      | 禁用            | boolean         | `true`/`false` | `false`   |
| size          | 输入框尺寸，只在 `type!="textarea"` 时有效      | string          | medium / small / mini  | — |
| prefix-icon   | 输入框头部图标    | string          | — | — |
| suffix-icon   | 输入框尾部图标    | string          | — | — |
| rows          | 输入框行数，只对 `type="textarea"` 有效  |  number | — |  2   |
| autosize      | 自适应内容高度，只对 `type="textarea"` 有效，可传入对象，如，{ minRows: 2, maxRows: 6 }  |  boolean / object | — |  `false`   |
| autocomplete | 原生属性，自动补全 | string | on, off | off |
| auto-complete | 下个主版本弃用 | string | on, off | off |
| name | 原生属性 | string | — | — |
| readonly | 原生属性，是否只读 | boolean | `true`/`false` | `false` |
| max | 原生属性，设置最大值(只对数值类型生效) | — | — | — |
| min | 原生属性，设置最小值(只对数值类型生效) | — | — | — |
| step | 原生属性，设置输入字段的合法数字间隔 | — | — | — |
| resize | 控制是否能被用户缩放 | string | none, both, horizontal, vertical | — |
| autofocus | 原生属性，自动获取焦点 | boolean | `true`/`false` | `false` |
| form | 原生属性 | string | — | — |
| label | 输入框关联的label文字 | string | — | — |
| tabindex | 输入框的tabindex | string | - | - |
| validate-event | 输入时是否触发表单的校验 | boolean | `true`/`false` | `true` |
| align | 文字对齐方式 | string | left, right, center | left |
| precision | 小数位 | string | number | — |
| preset-suffix | 预置尾缀字段(按enter键自动填充) | string | — | — |
| format | 格式化参数，详见样例 | object | — | — |
| negative-color | 负数的字体颜色（仅当负数生效） | string | — | — |
| bracketabled | 负数是否用括号表示（仅当负数生效） | boolean | `true`/`false` | `false` |
| limit-chars | 特殊字符输入限制 | string | - | '' |
| show-more ^(8.5.0) | 显示更多按钮 | boolean | `true`/`false` | `false` |
| show-minus ^(8.5.0) | 负数格式是否显示负号（仅当负数生效） | boolean | `true`/`false` | `true` |

### Input Slots
| name | 说明 |
|------|--------|
| prefix | 输入框头部内容，只对 `type="text"` 有效 |
| suffix | 输入框尾部内容，只对 `type="text"` 有效 |
| prepend | 输入框前置内容，只对 `type="text"` 有效 |
| append | 输入框后置内容，只对 `type="text"` 有效 |

### Input Events
| 事件名称 | 说明 | 回调参数 |
|---------|--------|---------|
| blur | 在 Input 失去焦点时触发 | (event: Event) |
| focus | 在 Input 获得焦点时触发 | (event: Event) |
| change | 仅在输入框失去焦点或用户按下回车时触发 | (value: string \| number) |
| input | 在 Input 值改变时触发 | (value: string \| number) |
| clear | 在点击由 `clearable` 属性生成的清空按钮时触发 | — |
| more ^(8.5.0) | 点击更多按钮时触发 | 目前的输入值 |

### Input Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| focus | 使 input 获取焦点 | — |
| blur | 使 input 失去焦点 | — |
| select | 选中 input 中的文字 | — |

### Autocomplete Attributes

| 参数          | 说明            | 类型            | 可选值                 | 默认值   |
|-------------  |---------------- |---------------- |---------------------- |-------- |
| placeholder   | 输入框占位文本   | string          | — | — |
| disabled      | 禁用            | boolean         | — | false   |
| value-key | 输入建议对象中用于显示的键名 | string | — | value |
| value         | 必填值，输入绑定值   | string  | — | — |
| debounce      | 获取输入建议的去抖延时 | number         | — | 300 |
| placement     | 菜单弹出位置 | string         | top / top-start / top-end / bottom / bottom-start / bottom-end | bottom-start |
| fetch-suggestions | 返回输入建议的方法，仅当你的输入建议数据 resolve 时，通过调用 callback(data:[]) 来返回它  | Function(queryString, callback)  | — | — |
| popper-class | Autocomplete 下拉列表的类名 | string | — | — |
| trigger-on-focus | 是否在输入框 focus 时显示建议列表 | boolean | — | true |
| name | 原生属性 | string | — | — |
| select-when-unmatched | 在输入没有任何匹配建议的情况下，按下回车是否触发 `select` 事件 | boolean | — | false |
| label | 输入框关联的label文字 | string | — | — |
| prefix-icon | 输入框头部图标 | string | — | — |
| suffix-icon | 输入框尾部图标 | string | — | — |
| hide-loading | 是否隐藏远程加载时的加载图标 | boolean | — | false |
| popper-append-to-body | 是否将下拉列表插入至 body 元素。在下拉列表的定位出现问题时，可将该属性设置为 false | boolean | - | true |
| highlight-first-item | 是否默认突出显示远程搜索建议中的第一项 | boolean | — | false |

### Autocomplete Slots
| name | 说明 |
|------|--------|
| prefix | 输入框头部内容 |
| suffix | 输入框尾部内容 |
| prepend | 输入框前置内容 |
| append | 输入框后置内容 |

### Autocomplete Scoped Slot
| name | 说明 |
|------|--------|
| — | 自定义输入建议，参数为 { item } |

### Autocomplete Events
| 事件名称 | 说明 | 回调参数 |
|---------|--------|---------|
| select | 点击选中建议项时触发 | 选中建议项 |
| change | 在 Input 值改变时触发 | (value: string \| number) |

### Autocomplete Methods
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| focus | 使 input 获取焦点 | - |
