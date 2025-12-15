### QzzGrid - 行只读、列只读、单元格只读

:::demo Grid9

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid9',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          treeId: 'TJBM',
          height: 100,
          post: true,
          multiselect: true,
          footerrow: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', editType: 'date' },
            { name: 'JSRQ', editType: 'date', showTime: true, width: 150 },
            {
              name: 'DKLY',
              editType: 'comboBox',
              data: [
                { id: '0', text: '农业银行' },
                { id: '1', text: '商业银行' },
                { id: '2', text: '工商银行' },
                { id: '3', text: '交通银行' }
              ]
            },
            { name: 'JE', editType: 'number', scale: 2, dataType: 'number', sum: true },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', editType: 'radio', align: 'center' },
            { name: 'STOP', editType: 'onoff', align: 'center' }
          ]
        },
        gridData: [
          { dxmc: '明细1', TJBM: '01' },
          { dxmc: '明细2', TJBM: '02' },
          { dxmc: '明细2', TJBM: '03' },
          { dxmc: '明细2', TJBM: '04' }
        ]
      };
    },
    mounted() {
      const grid = this.$refs.grid.ui;
      grid.bind('onCanEditing', function (grd, fieldName, canEdit) {
        //行只读
        if (grd.getValue('TJBM') === '02') {
          return false;
        }
        //列只读
        if (fieldName == 'dxmc') {
          return false;
        }
        //单元格只读
        if (fieldName == 'TJBM' && grid.getValue('DKLY') === '1') {
          return false;
        }
        if (fieldName == 'KSRQ' && this.getValue('STOP') === true) {
          return false;
        }
        return canEdit;
      });
      grid.bind('onAfterEdit', function (dataType, fieldName, value, text, cm, node, oldValue) {
        if (fieldName == 'DKLY') {
          grid.refresh(true);
        }
      });
      grid.bind('onCheckChanged', function (node, state, fieldName) {
        if (fieldName == 'STOP') {
          this.dataSet.getCellOption('KSRQ', node).changed = true;
          this.refresh();
        }
      });
    }
  };
</script>
```

:::
