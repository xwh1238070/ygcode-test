### QzzQueryGrid - 数据行移动

:::demo QueryGrid4

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid" @onaftersort="onAfterSort"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <button @click="firstBtn()" :disabled="upbtnState">置顶</button>
      <button @click="upBtn()" :disabled="upbtnState">上移</button>
      <button @click="downBtn()" :disabled="upbtnState">下移</button>
      <button @click="lastBtn()" :disabled="upbtnState">置底</button>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        upbtnState: false,
        gridoption: {
          Align: 'alClient',
          rownumbers: false,
          footerrow: false,
          colNames: [
            '序号',
            '对象名称',
            '统计编码',
            '开始日期',
            '结束日期',
            '贷款来源',
            '金额',
            '内容',
            '停用',
            '默认'
          ],
          colModels: [
            { name: 'rn', dataType: 'number', align: 'center' },
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
          { dxmc: '1', TJBM: '001', DKLY: 1, JE: 10000, CONTEXT: '房地产建设', rn: '1' },
          { dxmc: '2', TJBM: '002', DKLY: 1, JE: 10000, CONTEXT: '房地产建设', DEFAULT: true, rn: '2' },
          { dxmc: '21', TJBM: '002001', DKLY: 6, JE: 10000, KSRQ: '2015-10-10', rn: '3' },
          {
            dxmc: '22',
            TJBM: '002002',
            DKLY: 2,
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true,
            rn: '4'
          },
          { dxmc: '221', TJBM: '002002001', DKLY: 3, JE: 10000, CONTEXT: '房地产建设', STOP: true, rn: '5' },
          { dxmc: '3', TJBM: '003', DKLY: 2, JE: 10000, rn: '6' },
          { dxmc: '4', TJBM: '004', DKLY: 3, JE: 10000, CONTEXT: '房地产建设', rn: '7' }
        ]
      };
    },
    methods: {
      //同步序号
      updateIndex() {
        this.grid = this.$refs.grid.ui;
        this.grid.each(function (node) {
          this.setValue('rn', this.getIndex());
        });
      },
      firstBtn() {
        this.grid = this.$refs.grid.ui;
        var c = this.grid.getRecordCount() * -1;
        this.grid.moveRecord(c);
        this.updateIndex();
      },
      upBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.moveRecord(-1);
        this.updateIndex();
      },
      downBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.moveRecord(1);
        this.updateIndex();
      },
      lastBtn() {
        this.grid = this.$refs.grid.ui;
        var c = this.grid.getRecordCount();
        this.grid.moveRecord(c);
        this.updateIndex();
      },
      onAfterSort(evt, fieldName, sortState) {
        if(sortState !== 'none') {
          this.upbtnState = true;
        } else {
          this.upbtnState = false;
        }
      }
    },
    mounted() {
      // 第一行高亮
      this.grid = this.$refs.grid.ui;
      var curNode = this.grid.dataSet.getCurNode();
      this.grid.setFocus();
      this.grid.locate('rn', curNode.data.flmc, false, null);
    }
  };
</script>
```

:::
