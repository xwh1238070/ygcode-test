### QzzGrid - 下拉控件的过滤事件

:::demo Grid11

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid11',
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
            '树下拉选择',
            '数量',
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
            { name: 'JSRQ', editType: 'date', showTime: true, width: 100 },
            {
              name: 'DKLY',
              editType: 'comboBox',
              search: true,
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
        gridData: [{ dxmc: '明细1' }, { dxmc: '明细2' }]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //过滤事件
      this.grid.bind('onComboBoxFilter', function (input, fieldName, text, state) {
        if (fieldName == 'DKLY') {
          if (text != '') {
            input.loadData([{ id: 1, text: '1111' }]);
          } else {
            var cm = this.getColModel(fieldName);
            input.loadData(cm.data);
          }
        }
      });

      this.grid.bind('onButtonEdit', function (input, fieldName, dataType) {
        alert(this.dataSet.getIndex() + fieldName);
      });
    }
  };
</script>
```

:::
