### QzzQueryGrid - 复选、默认选择、禁用复选

:::demo QueryGrid25

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <button @click="singleBtn">单选</button>
      <button @click="multiBtn">复选</button>
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
          rownumbers: false,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY', link: true },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' },
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
      singleBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.setMultiSelect(false);
      },
      multiBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.setMultiSelect(true);
      }
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //关联node与cell的关系
      this.grid.bind('onCellDraw_DKLY', function (dataSet, fieldName, cell) {
        dataSet.getCurNode()._cell = cell;
      });
      //弹出自定义的下拉窗口
      this.grid.bind('onLinkClick', function (grid, fieldName, node) {
        var win = new qzz.ui.drop.popwindow({ width: 100, height: 100 });
        win.setParent(document.body);
        var rect = node._cell.getBoundingClientRect();
        win.execute(rect.top + grid.rowHeight - 4, rect.left);
      });
    }
  };
</script>
```

:::
