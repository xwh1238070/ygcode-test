### QzzGrid - 根据复选择框控制行只读

:::demo

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid1',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          treeId: 'TJBM',
          height: 300,
          multiselect: true,
          footerrow: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '停用', '默认'],
          colModels: [
            { name: 'dxmc' },
            { name: 'TJBM' },
            { name: 'KSRQ', editType: 'date', validate: 'notempty', validateMessage: '开始日期不能为空。' },
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
      const grid = this.$refs.grid.ui;
      grid.bind('onCanEditing', function (ds, fn, state) {
        if (ds.getValue('STOP') === true) {
          return false;
        } else {
          return state;
        }
      });
      grid.bind('onCheckChanged', function (cNode, checkState, fn) {
        grid.refresh(true);
      });
    }
  };
</script>
```

:::
