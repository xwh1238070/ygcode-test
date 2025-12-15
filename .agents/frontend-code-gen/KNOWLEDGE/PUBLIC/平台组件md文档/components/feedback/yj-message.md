## YjMessage 消息提示集合

由于平台需要统一消息提示组件，以不同的状态码对应不同组件类型状态，常用于接口反馈。

### 基础用法

yjMessage 使用其他提示类组件配置，通过`messageType`用以区分提示组件类型，所以配置部分在此不做详尽解释。

:::demo 

```html
<template>
  <tl-button :plain="true" @click="open">打开浮动类提示</tl-button>
  <tl-button :plain="true" @click="open1">打开浮动类不关闭提示</tl-button>
  <tl-button :plain="true" @click="open2">打开通知类提示</tl-button>
  <tl-button :plain="true" @click="open3">打开弹框类提示</tl-button>
</template>

<script>
  export default {
    methods: {
      open() {
        this.$yjMessage({
          messageType: 'message',
          message: '这是浮动类提示'
        });
      },
      open1() {
        this.$yjMessage({
          messageType: 'message',
          message: '这是浮动类不关闭提示',
          showClose: true,
          duration: 0
        });
      },
      open2() {
        this.$yjMessage({
          messageType: 'notify',
          message: '这是通知类提示',
          title: '通知'
        });
      },
      open3() {
        this.$yjMessage({
          messageType: 'prompt',
          message: '请输入邮箱',
          title: '提示',
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '邮箱格式不正确'
        }).then(({ value })=>{
          console.log('prompt::::::::::', value);
        });
      }
    }
  };
</script>
```
:::

### 弹窗提示更多消息

yjMessage 

:::demo 

```html
<template>
  <tl-button :plain="true" @click="open">弹窗提示更多消息</tl-button>
    <tl-button :plain="true" @click="open1">弹窗提示更多消息html</tl-button>
</template>

<script>
  export default {
    methods: {
      open() {
        this.$yjMessage({
          title: '标题名称',
          message: '这是一段内容',
          messageType: 'alert',
          confirmButtonText: '确定',
          showDetail: true,
          detail: '这个是详细信息'
        });
      },
      open1() {
        this.$yjMessage({
          title: '标题名称',
          message: '这是一段内容',
          messageType: 'alert',
          confirmButtonText: '确定',
          showDetail: true,
          dangerouslyUseHTMLString: true,
          detail: '这个是详细信息<a>a标签</a>'
        });
      }
    }
  };
</script>
```
:::

### 请求反馈

当不传递`messageType`配置时，则以code作为组件依据，yjMessage会通过code自主进行处理（必须遵守平台code规则，否则不处理）。

:::demo 

```html
<template>
  <tl-button :plain="true" @click="open">打开成功提示</tl-button>
  <tl-button :plain="true" @click="open1">打开普通提示</tl-button>
  <tl-button :plain="true" @click="open2">打开警告提示</tl-button>
  <tl-button :plain="true" @click="open3">打开错误提示</tl-button>
</template>

<script>
  export default {
    methods: {
      open() {
        this.$yjMessage({
          code: '6100089',
          message: '这是成功提示'
        });
      },
      open1() {
        this.$yjMessage({
          code: '6100095',
          message: '这是普通提示'
        });
      },
      open2() {
        this.$yjMessage({
          code: '6100011',
          message: '这是警告提示'
        });
      },
      open3() {
        this.$yjMessage({
          code: '6100099',
          message: '这是错误提示'
        });
      }
    }
  };
</script>
```
:::


### 可关闭浮动弹窗

可关闭浮动弹窗默认关闭时间是`5`秒

:::demo 

```html
<template>
  <tl-button @click="open">普通浮动提示</tl-button>
  <tl-button @click="open1">可关闭浮动提示</tl-button>
  <tl-button @click="open2">错误浮动提示</tl-button>
  <tl-button @click="open3">可关闭错误浮动提示</tl-button>
  <tl-button @click="open4">警告浮动提示</tl-button>
  <tl-button @click="open5">可关闭警告浮动提示</tl-button>
  <tl-button @click="open6">设置10秒关闭浮动提示</tl-button>
</template>

<script>
  export default {
    methods: {
      open() {
        this.$yjMessage({
          messageType: 'message',
          message: '普通浮动提示（默认3秒）'
        });
      },
      open1() {
        this.$yjMessage({
          messageType: 'message',
          message: '可关闭浮动提示（默认5秒）',
          showClose: true
        });
      },
      open2() {
        this.$yjMessage({
          messageType: 'message',
          message: '错误浮动提示（默认3秒）',
          type: 'error'
        });
      },
      open3() {
        this.$yjMessage({
          messageType: 'message',
          message: '可关闭错误浮动提示（默认5秒）',
          showClose: true,
          type: 'error'
        });
      },
      open4() {
        this.$yjMessage({
          messageType: 'message',
          message: '警告浮动提示（默认3秒）',
          type: 'warning'
        });
      },
      open5() {
        this.$yjMessage({
          messageType: 'message',
          message: '可关闭警告浮动提示（默认5秒）',
          showClose: true,
          type: 'warning'
        });
      },
      open6() {
        this.$yjMessage({
          messageType: 'message',
          message: '设置10秒关闭浮动提示',
          showClose: true,
          duration: 10000,
          type: 'warning'
        });
      }
    }
  };
</script>
```
:::

### Yjpl开启全局请求反馈
yjMessage 支持通过配置，绑定到service中，开启请求结果拦截。会默认根据请求返回的**标准结果**进行消息提示。
也可以通过配置请求头**headers**中`message: false`来控制一个请求不需要消息提示。
```javascript
// 在 Yjpl.use(YjplUi) 之前添加
Yjpl.config.message = true;

// headers 控制单个请求
// { message: false }
```

### Yjpl请求开启loading

Yjpl支持通过配置，绑定到service中，开启请求拦截。会根据配置展示loading。
也可以通过配置请求头**headers**中`loading: false`来控制一个请求不需要消息提示。

```javascript

// 在 Yjpl.use(YjplUi) 之前添加
Yjpl.config.loading = true;

// headers 控制单个请求
// { loading: true }

```
