## Dialog 对话框

在保留当前页面状态的情况下，告知用户并承载相关操作。

### 基本用法

Dialog 弹出一个对话框，适合需要定制性更大的场景。

:::demo 需要设置`visible`属性，它接收`Boolean`，当为`true`时显示 Dialog。Dialog 分为两个部分：`body`和`footer`，`footer`需要具名为`footer`的`slot`。`title`属性用于定义标题，它是可选的，默认值为空。最后，本例还展示了`before-close`的用法。

```html
<template>
  <tl-button type="text" @click="dialogVisible = true">点击打开 Dialog</tl-button>
  <tl-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <span>这是一段信息</span>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="dialogVisible = false">取 消</tl-button>
      <tl-button type="primary" @click="dialogVisible = false">确 定</tl-button>
    </span>
  </tl-dialog>
</template>
<script>
  export default {
    data() {
      return {
        dialogVisible: false
      };
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
    }
  };
</script>
```

:::

### 自定义内容

Dialog 组件的内容可以是任意的，甚至可以是表格或表单，下面是应用了 Element Table 和 Form 组件的两个样例。

:::demo Dialog2

```html
<!-- Table -->
<template>
  <tl-button type="text" @click="dialogTableVisible = true">打开嵌套表格的 Dialog</tl-button>
  <tl-dialog title="收货地址" :visible.sync="dialogTableVisible">
    <tl-table :data="gridData">
      <tl-table-column property="date" label="日期" width="150"></tl-table-column>
      <tl-table-column property="name" label="姓名" width="200"></tl-table-column>
      <tl-table-column property="address" label="地址"></tl-table-column>
    </tl-table>
  </tl-dialog>
  <!-- Form -->
  <tl-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</tl-button>

  <tl-dialog title="收货地址" :visible.sync="dialogFormVisible">
    <tl-form :model="form">
      <tl-form-item label="活动名称" :label-width="formLabelWidth">
        <tl-input v-model="form.name" autocomplete="off"></tl-input>
      </tl-form-item>
      <tl-form-item label="活动区域" :label-width="formLabelWidth">
        <tl-select v-model="form.region" placeholder="请选择活动区域"></tl-select>
      </tl-form-item>
    </tl-form>
    <div slot="footer" class="dialog-footer">
      <tl-button @click="dialogFormVisible = false">取 消</tl-button>
      <tl-button type="primary" @click="dialogFormVisible = false">确 定</tl-button>
    </div>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        gridData: [
          {
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }
        ],
        dialogTableVisible: false,
        dialogFormVisible: false,
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px'
      };
    }
  };
</script>
```

:::

### 嵌套的 Dialog
如果需要在一个 Dialog 内部嵌套另一个 Dialog，需要使用 `append-to-body` 属性。
:::demo 正常情况下，我们不建议使用嵌套的 Dialog，如果需要在页面上同时显示多个 Dialog，可以将它们平级放置。对于确实需要嵌套 Dialog 的场景，我们提供了`append-to-body`属性。将内层 Dialog 的该属性设置为 true，它就会插入至 body 元素上，从而保证内外层 Dialog 和遮罩层级关系的正确。
```html
<template>
  <tl-button type="text" @click="outerVisible = true">点击打开外层 Dialog</tl-button>

  <tl-dialog title="外层 Dialog" :visible.sync="outerVisible">
    <tl-dialog width="30%" title="内层 Dialog" :visible.sync="innerVisible" append-to-body></tl-dialog>
    <div slot="footer" class="dialog-footer">
      <tl-button @click="outerVisible = false">取 消</tl-button>
      <tl-button type="primary" @click="innerVisible = true">打开内层 Dialog</tl-button>
    </div>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        outerVisible: false,
        innerVisible: false
      };
    }
  };
</script>
```
:::

### 居中布局

标题和底部可水平居中

:::demo 将`center`设置为`true`即可使标题和底部居中。`center`仅影响标题和底部区域。Dialog 的内容是任意的，在一些情况下，内容并不适合居中布局。如果需要内容也水平居中，请自行为其添加 CSS。

```html
<template>
  <tl-button type="text" @click="centerDialogVisible = true">点击打开 Dialog</tl-button>
  <tl-dialog title="提示" :visible.sync="centerDialogVisible" width="30%" center>
    <span>需要注意的是内容是默认不居中的</span>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="centerDialogVisible = false">取 消</tl-button>
      <tl-button type="primary" @click="centerDialogVisible = false">确 定</tl-button>
    </span>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        centerDialogVisible: false
      };
    }
  };
</script>
```
:::

### 全屏显示

通过按钮控制全屏显示

:::demo 将 `show-fullscreen` 设置为 `true` 即可显示全屏切换按钮。

```html
<template>
  <tl-button type="text" @click="dialogTableVisible = true">打开可全屏的 Dialog</tl-button>
  <tl-dialog title="收货地址" show-fullscreen :visible.sync="dialogTableVisible">
    <tl-table :data="gridData">
      <tl-table-column property="date" label="日期" width="150"></tl-table-column>
      <tl-table-column property="name" label="姓名" width="200"></tl-table-column>
      <tl-table-column property="address" label="地址"></tl-table-column>
    </tl-table>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        gridData: [
          {
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }
        ],
        dialogTableVisible: false
      };
    }
  };
</script>
```

:::

### 拖拽和缩放

弹出框头部区域拖拽，弹出框右下角缩放

:::demo 将`draggable`设置为`true`即可使头部区域拖拽。将`zoom`设置为`true`即可使右下角使用缩放。Dialog 的内容是任意的，在一些情况下，内容并不适合缩放布局。如果需要内容也随缩放变化，请自行为其添加 CSS。

```html
<template>
  <tl-button type="text" @click="centerDialogVisible = true">点击打开 Dialog</tl-button>
  <tl-dialog title="提示" :visible.sync="centerDialogVisible" width="30%" center draggable zoom>
    <span>需要注意的是内容是默认高度，不随缩放变化</span>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="centerDialogVisible = false">取 消</tl-button>
      <tl-button type="primary" @click="centerDialogVisible = false">确 定</tl-button>
    </span>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        centerDialogVisible: false
      };
    }
  };
</script>
```
:::

### 显示详细信息

弹出框头部区域拖拽，弹出框右下角缩放

:::demo 将 `show-detail` 设置为 `true` 即可显示详细区域，可通过具名为 `detail` 的 `slot` 去扩展详细信息。

```html
<template>
  <tl-button type="text" @click="centerDialogVisible = true">点击打开 Dialog</tl-button>
  <tl-dialog title="提示" :visible.sync="centerDialogVisible" show-detail>
    这是一个具有详细信息的 Dialog
    <template slot="detail">
      这是一段展开信息。
    </template>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        centerDialogVisible: false
      };
    }
  };
</script>
```

:::

### 最小宽度和最小高度

弹出框缩放时最小高度和最小宽度

:::demo `min-width` 设置最小宽度 `min-height` 设置最小高度

```html
<template>
  <tl-button type="text" @click="centerDialogVisible = true">点击打开 Dialog</tl-button>
  <tl-dialog
    title="提示"
    :visible.sync="centerDialogVisible"
    width="30%"
    draggable
    zoom
    :min-width="400"
    :min-height="159"
  >
    <div style="height: 100%;">
      <span>缩放最小高度为159px，最小宽度为400px</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="centerDialogVisible = false">取消</tl-button>
      <tl-button type="primary" @click="centerDialogVisible = false">确定</tl-button>
    </span>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        centerDialogVisible: false
      };
    }
  };
</script>
```
:::

### 弹出框尺寸

设置弹出框的尺寸

:::demo 通过`size` 设置弹出框的尺寸，设置size后，width就不生效了，默认使用样式的宽度

```html
<template>
  <tl-button type="text" @click="openDialog('large')">点击打开 大尺寸Dialog</tl-button>
  <tl-button type="text" @click="openDialog('medium')">点击打开 中尺寸Dialog</tl-button>
  <tl-button type="text" @click="openDialog('small')">点击打开 小尺寸Dialog</tl-button>
  <tl-dialog
    title="提示"
    :visible.sync="visible"
    draggable
    zoom
    :size="size"
  >
    <div style="height: 100%;">
      <span>中尺寸弹窗内容</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="visible = false">取 消</tl-button>
      <tl-button type="primary" @click="visible = false">确 定</tl-button>
    </span>
  </tl-dialog>
  <tl-dialog
    title="提示"
    :visible.sync="visibleLarge"
    draggable
    zoom
    :size="sizeLarge"
  >
    <div style="height: 100%;">
      <span>大尺寸弹窗内容</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="visibleLarge = false">取 消</tl-button>
      <tl-button type="primary" @click="visibleLarge = false">确 定</tl-button>
    </span>
  </tl-dialog>
  <tl-dialog
    title="提示"
    :visible.sync="visibleSmall"
    draggable
    zoom
    :size="sizeSmall"
  >
    <div style="height: 100%;">
      <span>小尺寸弹窗内容</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="visibleSmall = false">取 消</tl-button>
      <tl-button type="primary" @click="visibleSmall = false">确 定</tl-button>
    </span>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        visible: false,
        visibleLarge: false,
        visibleSmall: false,
        size: 'medium',
        sizeLarge: 'large',
        sizeSmall: 'small'
      };
    },
    methods: {
      openDialog(type){
        if(type === 'medium'){
          this.visible = true;
        }
        if(type === 'large'){
          this.visibleLarge = true;
        }
        if(type === 'small'){
          this.visibleSmall = true;
        }
      }
    }
  };
</script>
```
:::

### 弹出框关闭遮罩层

弹出框关闭遮罩层

:::demo 弹出框关闭遮罩层

```html
<template>
  <tl-button type="text" @click="openDialog()">普通弹出框无遮罩层</tl-button>
  <tl-button type="text" @click="openDialog('drag')">拖拽缩放弹出框无遮罩层</tl-button>
  <tl-button type="text" @click="openDialog('medium')">中尺寸弹出框无遮罩层</tl-button>
  <tl-dialog
    title="普通弹出框"
    :visible.sync="visible"
    :modal="false"
    cross-modal
  >
    <div style="height: 100%;">
      <span>普通弹出框无遮罩层</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="visible = false">取 消</tl-button>
      <tl-button type="primary" @click="visible = false">确 定</tl-button>
    </span>
  </tl-dialog>
  <tl-dialog
    title="拖拽缩放弹出框"
    :visible.sync="visibleLarge"
    draggable
    zoom
    :modal="false"
  >
    <div style="height: 100%;">
      <span>拖拽缩放弹出框无遮罩层</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="visibleLarge = false">取 消</tl-button>
      <tl-button type="primary" @click="visibleLarge = false">确 定</tl-button>
    </span>
  </tl-dialog>
  <tl-dialog
    title="中尺寸弹出框"
    :visible.sync="visibleSmall"
    :size="size"
    :modal="false"
  >
    <div style="height: 100%;">
      <span>中尺寸弹出框无遮罩层</span>
    </div>
    <span slot="footer" class="dialog-footer">
      <tl-button @click="visibleSmall = false">取 消</tl-button>
      <tl-button type="primary" @click="visibleSmall = false">确 定</tl-button>
    </span>
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        visible: false,
        visibleLarge: false,
        visibleSmall: false,
        size: 'medium'
      };
    },
    methods: {
      openDialog(type){
        if(!type){
          this.visible = true;
        }
        if(type === 'drag'){
          this.visibleLarge = true;
        }
        if(type === 'medium'){
          this.visibleSmall = true;
        }
      }
    }
  };
</script>
```
:::

:::tip
如果 `visible` 属性绑定的变量位于 Vuex 的 store 内，那么 `.sync` 不会正常工作。此时需要去除 `.sync` 修饰符，同时监听 Dialog 的 `open` 和 `close` 事件，在事件回调中执行 Vuex 中对应的 mutation 更新 `visible` 属性绑定的变量的值。
:::

### Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| visible   | 是否显示 Dialog，支持 .sync 修饰符 | boolean | — | false |
| title     | Dialog 的标题，也可通过具名 slot （见下表）传入 | string    | — | — |
| width     | Dialog 的宽度 | string    | — | 50% |
| fullscreen     | 是否为全屏 Dialog | boolean    | — | false |
| show-fullscreen     | 显示全屏 Dialog 按钮 | boolean    | — | false |
| show-detail     | 显示详细信息 | boolean    | — | false |
| top       | Dialog CSS 中的 margin-top 值 | string | — | 15vh |
| modal     | 是否需要遮罩层   | boolean   | — | true |
| cross-modal ^(8.5.0) | 可穿透点击 | boolean | - | false |
| modal-append-to-body     | 遮罩层是否插入至 body 元素上，若为 false，则遮罩层会插入至 Dialog 的父元素上   | boolean   | — | true |
| append-to-body     | Dialog 自身是否插入至 body 元素上。嵌套的 Dialog 必须指定该属性并赋值为 true   | boolean   | — | false |
| lock-scroll | 是否在 Dialog 出现时将 body 滚动锁定 | boolean | — | true |
| custom-class      | Dialog 的自定义类名 | string    | — | — |
| close-on-click-modal | 是否可以通过点击 modal 关闭 Dialog | boolean    | — | true |
| close-on-press-escape | 是否可以通过按下 ESC 关闭 Dialog | boolean    | — | true |
| show-close | 是否显示关闭按钮 | boolean    | — | true |
| before-close | 关闭前的回调，会暂停 Dialog 的关闭 | function(done)，done 用于关闭 Dialog | — | — |
| center | 是否对头部和底部采用居中布局 | boolean | — | false |
| destroy-on-close | 关闭时销毁 Dialog 中的元素 | boolean | — | false |
| draggable | 开启 Dialog Header 区域拖拽 | boolean | — | false |
| zoom | 开启 Dialog 右下角缩放 | boolean | — | false |
| min-width | Dialog 缩放最小宽度 | number | — | 200 |
| min-height | Dialog 缩放最小高度 | number | — | 200 |
| size | Dialog 尺寸 | string | `large` / `medium` / `small` | — |

### Slot

| name   | 说明                    |
| ------ | ----------------------- |
| —      | Dialog 的内容           |
| title  | Dialog 标题区的内容     |
| footer | Dialog 按钮操作区的内容 |
| detail | 详细信息，配置 `show-detail` 时生效 |

### Events

| 事件名称   | 说明                        | 回调参数 |
| ---------- | --------------------------- | -------- |
| open       | Dialog 打开的回调           | —        |
| opened     | Dialog 打开动画结束时的回调 | —        |
| close      | Dialog 关闭的回调           | —        |
| closed     | Dialog 关闭动画结束时的回调 | —        |
| drag-start | Dialog 拖拽开始事件         | —        |
| drag-move  | Dialog 拖拽拖动中事件       | —        |
| drag-end   | Dialog 拖拽结束事件         | —        |
| zoom-start | Dialog 缩放开始事件         | —        |
| zoom-move  | Dialog 缩放拖动中事件       | —        |
| zoom-end   | Dialog 缩放结束事件         | —        |
| fullscreen-change | Dialog 全屏事件   | —        |
