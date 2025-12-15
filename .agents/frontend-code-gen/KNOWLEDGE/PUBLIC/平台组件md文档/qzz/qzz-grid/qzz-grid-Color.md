### QzzGrid - 控制单元格、行、列的背景色

:::demo Grid16

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid16',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          treeId: 'TJBM',
          height: 100,
          multiselect: true,
          footerrow: true,
          colNames: [
            '对象名称',
            '统计编码',
            '开始日期',
            '结束日期',
            '贷款来源',
            '金额',
            '内容',
            '停用',
            '停用2',
            '默认'
          ],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', editType: 'date' },
            { name: 'JSRQ', editType: 'date', showTime: true, width: 150 },
            {
              name: 'DKLY',
              editType: 'comboBox',
              data: [
                { id: 'red', text: '红' },
                { id: 'green', text: '绿' },
                { id: 'blue', text: '蓝' },
                { id: 'yellow', text: '黄' }
              ]
            },
            { name: 'JE', editType: 'number', scale: 2, dataType: 'number', sum: true },
            { name: 'CONTEXT', length: 100, editType: 'buttonEdit' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'STOP', editType: 'onoff', align: 'center' },
            { name: 'DEFAULT', editType: 'radio', align: 'center' }
          ]
        },
        gridData: [
          { dxmc: '明细1' },
          { dxmc: '明细2' },
          { dxmc: '明细3', DKLY: 'red' },
          { dxmc: '明细3', DKLY: 'yellow' }
        ]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //弹出选择
      this.grid.bind('onButtonEdit', function (input, fieldName, dataType) {
        alert(this.dataSet.getIndex() + fieldName);
      });
      //根据当前值渲染颜色
      this.grid.bind('onCellDraw_DKLY', function (ds, fn, col) {
        col.style.backgroundColor = this.getValue(fn);
      });
      //整行、整列背景颜色控制。
      this.grid.onCellDraw = function (ds, fn, col) {
        col.style.backgroundColor = '';
        if (fn == 'DEFAULT') {
          col.style.backgroundColor = 'green';
        }
        if (ds.getValue('dxmc') == '明细2') {
          col.style.backgroundColor = 'blue';
        }
      };
      //因为表格刷新是局部刷新，
      this.grid.bind('onAfterEdit', function (dt, fieldName, vl, value, cm, ccn) {
        if (fieldName === 'dxmc') {
          this.grid.refresh(true);
        }
      });
    }
  };
</script>
```

:::
