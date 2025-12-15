### QzzQueryGrid - 空表提示词

:::demo QueryGrid24

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
  </tl-row>
  <tl-row>
    <tl-col :span="12" style="height: 20px;margin:8px;">
      <input v-model="emptyText">
    </tl-col>
    <tl-col :span="12">
      <button @click="showStopBtn">自定义设置</button>
      <button @click="newBtn">新增模式</button>
      <button @click="queryBtn">查询模式</button>
      <button @click="defBtn">默认模式</button>
      <button @click="addBtn">新增</button>
      <button @click="delBtn">删除</button>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        emptyText: '',
        gridoption: {
          Align: 'alClient',
          emptyMode: 'new',
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '停用', '金额', '内容', '默认'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM', validate:"notEmpty", validateMessage: '不能为空' , required: true},
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true },
            { name: 'DKLY' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        griddata: []
      };
    },
    methods: {
      showStopBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.setEmptyText(this.emptyText);
      },
      queryBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.setEmptyMode("query");
      },
      newBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.setEmptyMode('new');
      },
      defBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.setEmptyMode("default");
      },
      addBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.append();
      },
      delBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.delRecord();
      }
    }
  };
</script>
```

:::
