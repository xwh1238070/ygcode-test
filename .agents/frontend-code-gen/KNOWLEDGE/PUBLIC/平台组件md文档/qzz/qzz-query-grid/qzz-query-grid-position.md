### QzzQueryGrid - 表格定位

:::demo QueryGrid18

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <input type="text" ref="locVal" />
      <button @click="locateBtn">正向定位</button>
      <button @click="locateDescBtn">反向定位</button>
      <button @click="locateAllBtn">全表定位</button>
      <button @click="locateCellBtn">定位到贷款来源</button>
      <button @click="locateIndexBtn">定位到第6条</button>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          multiSelect: true,
          rownumbers: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY', link: true },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center', editType: 'radio' },
            { name: 'STOP', editType: 'checkBox', align: 'center' }
          ]
        },
        griddata: [
          { dxmc: '1', TJBM: '001', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '2', TJBM: '002', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设', DEFAULT: true },
          { dxmc: '21', TJBM: '002001', DKLY: '农业银行', JE: 10000, KSRQ: '2015-10-10' },
          {
            dxmc: '22',
            TJBM: '002002',
            DKLY: '农业银行',
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          { dxmc: '221', TJBM: '002002001', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设', STOP: true },
          { dxmc: '3', TJBM: '003', DKLY: '交通银行', JE: 10000 },
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    },
    methods: {
      //正向定位
      locateBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.locate('dxmc', this.$refs.locVal.value);
      },
      //反向定位
      locateDescBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.locateDesc('dxmc', this.$refs.locVal.value);
      },
      //全表定位
      locateAllBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.locate('@ALL', this.$refs.locVal.value);
      },
      // 单元格定位
      locateCellBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.locateCell(null, 'DKLY');
      },
      //定位到第6条
      locateIndexBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.locate('@INDEX', 6);
      }
    }
  };
</script>
```

:::
