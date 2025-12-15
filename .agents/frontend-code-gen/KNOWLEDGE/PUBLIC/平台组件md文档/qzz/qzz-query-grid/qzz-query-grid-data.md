### QzzQueryGrid - 主从表样例-更新数据方式

:::demo

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="maingridoption" v-model="maingriddata" ref="maingrid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="subgridoption" v-model="subgriddata" ref="subgrid"></qzz-query-grid>
    </tl-col>
  </tl-row>
</template>
<script>
  export default {
    name: 'QueryGrid1',
    data() {
      return {
        maingridoption: {
          Align: 'alClient',
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '停用', '金额', '内容', '默认'],
          colModels: [
            { name: 'name' },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true },
            { name: 'DKLY' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'JE', editType: 'checkBox', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        subgridoption: {
          Align: 'alClient',
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '停用', '金额', '内容', '默认'],
          colModels: [
            { name: 'name' },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true },
            { name: 'DKLY' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'JE', editType: 'checkBox', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        maingriddata: [
          { dxmc: '1', TJBM: '001', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设', STOP: true },
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
          { dxmc: '3', TJBM: '003', DKLY: '交通银行', JE: 10001 },
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CON: '3', TJBM: '003', DKLY: '交通银行', JE: 10001 },
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' }
        ],
        subgriddata: []
      };
    },
    mounted() {
      this.maingrid = this.$refs.maingrid.ui;
      this.subgrid = this.$refs.subgrid.ui;
      var _this = this;
      this.maingrid.bind('onRowChanged', function () {
        if (this.getValue('TJBM') === '001') {
          _this.subgrid.value([
            { dxmc: '1', TJBM: '001', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设', STOP: true }
          ]);
        } else if (this.getValue('TJBM') === '002') {
          _this.subgrid.value([
            { dxmc: '2', TJBM: '002', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设', STOP: true }
          ]);
        } else {
          _this.subgrid.value([]);
        }
      });
    }
  };
</script>
```

:::
