### QzzGrid - 显示小计与合计

:::demo

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid2',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          treeId: 'TJBM',
          height: 300,
          total: true,
          subtotal: true,
          totalRever: false,
          multiselect: true,
          colNames: [
            '对象名称',
            '统计编码',
            '开始日期',
            '结束日期',
            '贷款来源',
            '树下拉选择',
            '数量',
            '金额',
            '内容',
            '停用',
            '停用2',
            '默认'
          ],
          colModels: [
            { name: 'dxmc' },
            { name: 'TJBM' },
            { name: 'KSRQ', editType: 'date' },
            { name: 'JSRQ', editType: 'date', showTime: true, width: 100 },
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
            {
              name: 'TREE',
              editType: 'comboTree',
              idkey: 'id',
              pidkey: 'pid',
              width: 70,
              data: [
                { id: '0', text: '农业银行' },
                { id: '1', pid: '0', text: '农业银行XX支行' },
                { id: '2', pid: '1', text: '农业银行XX支行分行' },
                { id: '3', pid: '0', text: '农业银行YY支行' }
              ]
            },
            { name: 'SL', editType: 'updown', minValue: 0 },
            { name: 'JE', editType: 'number', scale: 2, dataType: 'number', sum: true },
            { name: 'CONTEXT', length: 100, editType: 'buttonEdit' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'STOP', editType: 'onoff', align: 'center' },
            { name: 'DEFAULT', editType: 'radio', align: 'center' }
          ]
        },
        gridData: [{ dxmc: '明细1' }, { dxmc: '明细2' }, { dxmc: '明细3' }, { dxmc: '明细4' }, { dxmc: '明细5' }, { dxmc: '明细6' }, { dxmc: '明细7' }, { dxmc: '明细8' }, { dxmc: '明细9' }, { dxmc: '明细10' }, { dxmc: '明细11' },
		    { dxmc: '明细12' }, { dxmc: '明细13' }, { dxmc: '明细14' }, { dxmc: '明细15' }, { dxmc: '明细16' }, { dxmc: '明细17' }, { dxmc: '明细18' }, { dxmc: '明细19' }, { dxmc: '明细20' }
		]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      this.grid.bind('onButtonEdit', function (input, fieldName, dataType) {
        alert(this.dataSet.getIndex() + fieldName);
      });
      this.grid.setTotalValue('JE', 10000);
    }
  };
</script>
```

:::
