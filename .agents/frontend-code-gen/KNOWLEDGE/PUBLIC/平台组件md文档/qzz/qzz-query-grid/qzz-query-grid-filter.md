### QzzQueryGrid - 数据过滤

:::demo QueryGrid24

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <button @click="hideStopBtn">隐藏停用</button>
      <button @click="showStopBtn">显示停用</button>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '停用', '金额', '内容', '默认'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true },
            { name: 'DKLY' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        griddata: [
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
        ]
      };
    },
    methods: {
      hideStopBtn() {
        this.grid = this.$refs.grid.ui;
        //执行过滤
        this.grid.dataSet.setFiltered(true);
        //定位到第一条明细（重要）
        this.grid.first();
      },
      showStopBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.dataSet.setFiltered(false);
        this.grid.first();
      }
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //绑定过滤事件，
      this.grid.dataSet.bind('onFilter', function (dataSet, state) {
        if (dataSet.getValue('STOP') === true) {
          //需要过滤的数据返回true
          return true;
        } else {
          //不需要过滤的数据返回false
          return false;
        }
      });
    }
  };
</script>
```

:::
