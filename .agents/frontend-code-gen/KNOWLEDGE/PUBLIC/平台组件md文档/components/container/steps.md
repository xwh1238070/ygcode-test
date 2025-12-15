# Steps 步骤条

步骤条是一种展示类组件，旨在用于步骤跳转和步骤查看等展示类页面。根据业务需求配置使用。

该组件分2个组件配合使用，分别为 `Steps` 容器组件和 `Step` 内部 `ITEM` 组件，请查阅参数配置。

### 基础用法

简单的步骤条。

:::demo 设置 `active` 属性，接受一个 `Number` ，表明步骤的 `index` ，从 0 开始。需要定宽的步骤条时，设置 `space` 属性即可，它接受 `Number` ，单位为 `px` ，如果不设置，则为自适应。设置 `finish-status` 属性可以改变已经完成的步骤的状态。

```html
<template>
  <div>
    <tl-steps :active="active">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>

    <tl-button style="margin-top: 12px" @click="next">下一步</tl-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
        },
        {
          title: 'STEP2',
        },
        {
          title: 'STEP3',
        },
        {
          title: 'STEP4',
        },
      ],
    };
  },
  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
  },
};
</script>
```

:::

### 文字居中显示

设置属性控制文字显示的位置

:::demo 设置 `align-center` 属性， `true` 则居中， `false` 居左

```html
<template>
  <div>
    <tl-steps :active="active" align-center>
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
        },
        {
          title: 'STEP2',
        },
        {
          title: 'STEP3',
        },
        {
          title: 'STEP4',
        },
      ],
    };
  }
};
</script>
```

:::

### 含状态步骤条

每一步骤显示出该步骤的状态。

:::demo 也可以使用 `title` 具名分发，可以用 `slot` 的方式来取代属性的设置，在本文档最后的列表中有所有的 `slot name` 可供参考。

```html
<template>
  <div>
    <tl-steps :active="active" finish-status="success">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
        },
        {
          title: 'STEP2',
        },
        {
          title: 'STEP3',
        },
        {
          title: 'STEP4',
        },
      ],
    };
  }
};
</script>
```
:::

### 有描述的步骤条

每个步骤有其对应的步骤状态描述。

:::demo

```html
<template>
  <div>
    <tl-steps :active="active">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP2',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP3',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP4',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
      ],
    };
  },
};
</script>
```
:::


### 可以点击跳转的步骤条

点击可以跳转到相关的步骤。

:::demo

```html
<template>
  <div>
    <tl-steps :active="active">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP2',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP3',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP4',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
      ],
    };
  },
};
</script>
```
:::


### 自定义图标的步骤条

透过插槽传入图标。

:::demo 只需要在 `step` 元素中设置 `icon` 插槽即可插入图标。

```html
<template>
  <div>
    <tl-steps :active="active">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          icon: 'el-icon-edit',
          title: '自定义图标',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          icon: 'el-icon-s-tools',
          title: 'STEP2',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          icon: 'el-icon-star-on',
          title: 'STEP3',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          icon: 'el-icon-s-help',
          title: 'STEP4',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
      ],
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
  },
};
</script>
```
:::

### 迷你的步骤条

透过参数 `point` 调整步骤条尺寸。

:::demo 只需要在 `steps` 元素中设置 `point` 属性为 `true` 即可。

```html
<template>
  <div>
    <tl-steps :active="active" point>
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          icon: 'el-icon-edit',
          title: '自定义图标',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          icon: 'el-icon-s-tools',
          title: 'STEP2',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          icon: 'el-icon-star-on',
          title: 'STEP3',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          icon: 'el-icon-s-help',
          title: 'STEP4',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
      ],
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
  },
};
</script>
```
:::


### 竖向的步骤条

透过参数 `direction` 调整步骤条方向。

:::demo 只需要在 `steps` 元素中设置 `direction` 属性为 `vertical` 即可。

```html
<template>
  <div>
    <tl-steps :active="active" direction="vertical">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP2',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP3',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP4',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
      ],
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
  },
};
</script>
```
:::

### 带按钮的步骤条

透过参数 `show-button` 来显示切换按钮。

:::demo 当设置 `show-button` 时, `active` 需要带上 `sync` 修饰符，否则会不生效。

```html
<template>
  <div>
    <tl-steps :active.sync="active" show-button>
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
        <template #icon v-if="item.icon">
          <i :class="item.icon"></i>
        </template>
      </tl-step>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP2',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP3',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
        {
          title: 'STEP4',
          description: '我是描述描述描述描述描述描述描述描述描述描述描述文字',
        },
      ],
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
  },
};
</script>
```
:::

### 带切换内容的步骤条

可以切换下方内容的步骤条，需要配合 `content` 插槽和 `step-content` 组件使用。

:::demo 需要配合 `content` 插槽和 `step-content` 组件使用。

```html
<template>
  <div>
    <tl-steps :active="active">
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      >
      </tl-step>

      <template slot="content">
        <tl-step-content v-for="(item, key) in stepDatas" :key="key">
          {{ item.title }}
        </tl-step-content>
      </template>
    </tl-steps>
  </div>
</template>

<script>
export default {
  data () {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
        },
        {
          title: 'STEP2',
        },
        {
          title: 'STEP3',
        },
        {
          title: 'STEP4',
        },
      ],
    };
  },
};
</script>
```
:::

### 简洁风格步骤条

:::demo 设置 simple 可应用简洁风格，该条件下 align-center / description / direction / space 都将失效。

```html
<template>
  <div>
    <tl-steps :active="active" simple>
      <tl-step
        :key="key"
        :title="item.title"
        :description="item.description"
        v-for="(item, key) in stepDatas"
        @click.native="active = key"
      />
    </tl-steps>
  </div>
</template>

<script>
export default {
  data () {
    return {
      active: 0,
      stepDatas: [
        {
          title: 'STEP1',
        },
        {
          title: 'STEP2',
        },
        {
          title: 'STEP3',
        },
        {
          title: 'STEP4',
        },
      ],
    };
  },
};
</script>
```
:::

### Steps Attributes

| 参数      | 说明    | 类型      | 可选值       | 默认值   |
|---------- |-------- |---------- |-------------  |-------- |
| space | 每个 step 的间距，不填写将自适应间距。支持百分比。 | number / string | — | — |
| direction | 显示方向 | string | vertical/horizontal | horizontal |
| active | 设置当前激活步骤  | number | — | 0 |
| process-status | 设置当前步骤的状态 | string | wait / process / finish / error / success | process |
| finish-status | 设置结束步骤的状态 | string | wait / process / finish / error / success | finish |
| align-center | 进行居中对齐 | boolean | - | false |
| simple | 是否应用简洁风格 | boolean | - | false |
| point	| 是否使用迷你布局 | boolean |	-	| false |

### Steps Slots

| 名称     | 说明                                              |
| -------- | ------------------------------------------------- |
|content|	切换内容，需配合 `step-content` 组件使用|

### Step Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| title   | 标题                | string | -                      |-     |
| description   | 描述性文字     | string | -                         | -      |
| icon | 图标 | 传入 icon 的 class 全名来自定义 icon，也支持 slot 方式写入 |String| -  |
| status | 设置当前步骤的状态，不设置则根据 steps 确定状态 | wait / process / finish / error / success | - | - |

### Step Slots

| 名称     | 说明                                              |
| -------- | ------------------------------------------------- |
|icon|	自定义图标|
|title|	自定义标题|
|description|	自定义描述性文字|
