# 状态管理

提供状态管理功能， 用于控制页面或者组件的状态（只读、禁用、显示、隐藏）。  
目前内置的状态常量有：

1. `DEFAULT`:  默认值
2. `SHOW`:  显示
3. `HIDE`:  隐藏
4. `READONLY`:  只读
5. `DISABLED`:  禁用
6. `HIDE_DISABLED`:  隐藏并禁用
7. `HIDE_READONLY`:  隐藏并只读
8. `READONLY_DISABLED`:  只读并禁用
9. `HIDE_READONLY_READONLY`:  隐藏、禁用并只读

### 基础使用方法

通过 `v-ctrl-state` 给组件绑定状态（组件内部需要支持 disabled、readonly）属性。

```html
<template>
  <page>
    <!-- 设置默认值 -->
    <input v-ctrl-state="tleadState.DEFAULT" placeholder="默认" />
    <!-- 设置显示 -->
    <input v-ctrl-state="tleadState.SHOW" placeholder="显示" />
    <!-- 设置隐藏 -->
    <input v-ctrl-state="tleadState.HIDE" />
    <!-- 设置只读 -->
    <input v-ctrl-state="tleadState.READONLY" placeholder="只读" />
    <!-- 设置禁用 -->
    <input v-ctrl-state="tleadState.DISABLED" placeholder="禁用" />
    <!-- 设置隐藏并禁用 -->
    <input v-ctrl-state="tleadState.HIDE_DISABLED" />
    <!-- 设置隐藏并只读 -->
    <input v-ctrl-state="tleadState.HIDE_READONLY" />
    <!-- 设置只读并禁用 -->
    <input
      v-ctrl-state="tleadState.READONLY_DISABLED"
      placeholder="只读并禁用"
    />
    <!-- 设置隐藏、只读并禁用 -->
    <input v-ctrl-state="tleadState.HIDE_READONLY_READONLY" />
    <!-- 使用变量的方式设置 -->
    <input v-ctrl-state="stateModel.input" />
  </page>
</template>

<script>
  import { YJPage } from "yjpl-core";
  export default class Demo extends YJPage {
    data() {
      return {
        stateModel: {
          input: this.tleadState.READONLY,
        },
      };
    }
  }
</script>
```

### 状态方法使用

状态管理提供以下方法：

1. `applyReadonlyTheme` 用于设置所有输入控件为只读状态
2. `applyEditTheme` 用于设置所有输入控件可编辑
3. `applyDefaultTheme` 用于设置所有输入控件为默认状态
4. `applyTheme` 用于设置自定义状态


设置所有输入控件为 只读 / 可编辑 / 默认状态：

```html
<template>
  <page>
    <yj-container
      v-model="dataModel"
      :data="metaModel.form"
      :state="stateModel"
      label-suffix="："
      :col="4"
    ></yj-container>
    <button @click="tleadState.applyReadonlyTheme()">全局只读</button>
    <button @click="tleadState.applyDefaultTheme()">默认状态</button>
    <button @click="tleadState.applyEditTheme()">全局可编辑</button>
  </page>
</template>

<script lang="ts">
  import { YJPage } from "yjpl-core";
  export default class YwdjView extends YJPage {
    data() {
      return {
        stateModel: {
          start: this.tleadState.SHOW,
          end: this.tleadState.SHOW,
          string: this.tleadState.SHOW,
          number: this.tleadState.SHOW,
          textarea: this.tleadState.SHOW,
        },
        dataModel: {},
        metaModel: {
          form: [
            {
              name: "start",
              dataType: "date",
              label: "开始时间",
              required: true,
            },
            {
              name: "end",
              dataType: "date",
              label: "结束时间",
              required: true,
            },
            {
              name: "string",
              dataType: "string",
              label: "报销事项",
            },
            {
              name: "number",
              dataType: "number",
              label: "票据张数",
            },
            {
              name: "textarea",
              dataType: "textarea",
              label: "出差事由",
              resize: "vertical",
              col: 4,
            },
          ],
        },
      };
    }
  }
</script>
```

### 自定义状态主题

可通过 `applyTheme` 方法实现自定义状态主题。

```html
<template>
  <page>
    <yj-container
      v-model="dataModel"
      :data="metaModel.form"
      :state="stateModel"
      label-suffix="："
      :col="4"
    ></yj-container>
    <button @click="applyTheme">自定义主题</button>
  </page>
</template>

<script lang="ts">
  import { YJPage } from "yjpl-core";
  export default class YwdjView extends YJPage {
    data() {
      return {
        stateModel: {
          start: this.tleadState.SHOW,
          end: this.tleadState.SHOW,
          string: this.tleadState.SHOW,
          number: this.tleadState.SHOW,
          textarea: this.tleadState.SHOW,
        },
        dataModel: {},
        metaModel: {
          form: [
            {
              name: "start",
              dataType: "date",
              label: "开始时间",
              required: true,
            },
            {
              name: "end",
              dataType: "date",
              label: "结束时间",
              required: true,
            },
            {
              name: "string",
              dataType: "string",
              label: "报销事项",
            },
            {
              name: "number",
              dataType: "number",
              label: "票据张数",
            },
            {
              name: "textarea",
              dataType: "textarea",
              label: "出差事由",
              resize: "vertical",
              col: 4,
            },
          ],
        },
      };
    }
    created() {
      this.initCustomTheme(); // 初始化自定义状态
    }
    methods() {
      return {
        initCustomTheme() {
          // 通过 init() 方法 添加自定义状态，可定义多个
          this.tleadState.init({
            theme1: {
              start: this.tleadState.SHOW,
              end: this.tleadState.SHOW,
              string: this.tleadState.DISABLED,
              number: this.tleadState.SHOW,
              textarea: this.tleadState.DISABLED,
            },
          });
          // 通过 addTheme() 方法添加自定义状态
          this.tleadState.addTheme("theme2", {
            start: this.tleadState.DISABLED,
            end: this.tleadState.READONLY_DISABLED,
            string: this.tleadState.SHOW,
            number: this.tleadState.SHOW,
            textarea: this.tleadState.SHOW,
          });
        },
        applyTheme() {
          // 通过 applyTheme() 方法应用自定义状态
          this.tleadState.applyTheme("theme2");
        },
      };
    }
  }
</script>
```

### 通过引入使用

可以通过从 `yjpl-core` 中，引入 `YJState` 进行使用。

```typescript
import { YJState } from 'yjpl-core';

const stateModel = {
  'name': YJState.STATE.DEFAULT,
  'start': YJState.STATE.DEFAULT
};
```