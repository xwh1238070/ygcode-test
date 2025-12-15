### QzzGrid - 弹出选择

:::demo Grid5

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid5',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          treeId: 'TJBM',
          height: 100,
          multiselect: true,
          footerrow: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true, editType: 'buttonEdit' },
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
            { name: 'STOP', editType: 'checkBox', align: 'center' }
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
      this.grid = this.$refs.grid.ui;
      this.grid.bind('onButtonEdit', function (input, fieldName, dataType) {
        alert('弹出选择测试');
        input.setValue('弹出测试' + this.dataSet.getIndex());
      });
    }
  };
</script>
```

:::
