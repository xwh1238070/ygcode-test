### QzzQueryGrid - 重新设置表头

:::demo QueryGrid26

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <button @click="resetHeadBtn">重置表头</button>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          colName: ['默认'],
          colModels: [{ name: 'gid' }]
        },
        griddata: []
      };
    },
    methods: {
      resetHeadBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.refreshTitle({
          colNames: ['对象名称', '统计编码', '贷款来源'],
          colModels: [{ name: 'dxmc', frozen: true }, { name: 'TJBM' }, { name: 'DKLY' }]
        });
        this.grid.value([
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
        ]);
      }
    }
  };
</script>
```

:::
