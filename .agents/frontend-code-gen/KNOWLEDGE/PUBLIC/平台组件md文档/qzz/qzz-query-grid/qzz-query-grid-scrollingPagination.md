### QzzQueryGrid - 滚动分页

:::demo QueryGrid10

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          footerrow: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '停用', '默认'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'STOP', editType: 'onoff', align: 'center' },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        griddata: [
          { dxmc: '1', TJBM: '001', DKLY: 1, JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '2', TJBM: '002', DKLY: 1, JE: 10000, CONTEXT: '房地产建设', DEFAULT: true },
          { dxmc: '21', TJBM: '002001', DKLY: 6, JE: 10000, KSRQ: '2015-10-10' },
          {
            dxmc: '22',
            TJBM: '002002',
            DKLY: 2,
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          { dxmc: '221', TJBM: '002002001', DKLY: 3, JE: 10000, CONTEXT: '房地产建设', STOP: true },
          { dxmc: '3', TJBM: '003', DKLY: 2, JE: 10000 },
          { dxmc: '4', TJBM: '004', DKLY: 3, JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '11', TJBM: '011', DKLY: 1, JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '12', TJBM: '012', DKLY: 1, JE: 10000, CONTEXT: '房地产建设', DEFAULT: true },
          { dxmc: '121', TJBM: '012001', DKLY: 6, JE: 10000, KSRQ: '2015-10-10' },
          {
            dxmc: '122',
            TJBM: '012002',
            DKLY: 2,
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          { dxmc: '1221', TJBM: '012002001', DKLY: 3, JE: 10000, CONTEXT: '房地产建设', STOP: true },
          { dxmc: '13', TJBM: '013', DKLY: 2, JE: 10000 },
          { dxmc: '14', TJBM: '014', DKLY: 3, JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //设置id名称转换列表，此方法会覆盖原来的列表数据
      this.grid.setTransIDName('DKLY', { 1: '农业银行', 2: '工商银行', 3: '交通银行' });
      //添加id名称转换列表 此方法会附加到原来的列表上，可以多次调用。
      this.grid.addTransIDName('DKLY', { 4: '建设银行', 5: '广发银行', 6: '中国银行' });
      //滚动到低事件。
      this.grid.bind('onScrollEnd', function () {
        //此部分数据可以从服务端取数，建议一至少次50条。
        this.grid.addEcpRowData(this.griddata);
      });
    }
  };
</script>
```

:::
