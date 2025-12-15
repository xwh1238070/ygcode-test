### QzzGrid - 内容格式化、动态设置输入控件

:::demo Grid10

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid10',
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
            { name: 'DEFAULT', align: 'center', dataType: 'number' },
            { name: 'STOP', editType: 'checkBox', align: 'center' }
          ]
        },
        gridData: [
          { dxmc: '明细1', TJBM: '01', DEFAULT: 1 },
          { dxmc: '明细2', TJBM: '02', DEFAULT: 0 },
          { dxmc: '明细2', TJBM: '03' },
          { dxmc: '明细2', TJBM: '04' }
        ]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      this.grid.addTransIDName('DEFAULT', { 0: '空', 1: '默认' });
      //内容格式化
      this.grid.dataSet.bind('onFormat_CONTEXT', function (node, fieldName, dataType, value, metaItem) {
        if (this.getValue('TJBM') == '01') {
          if (value === '0') {
            return '未设置';
          } else if (value === '1') {
            return '已设置';
          }
        }
        return value;
      });
      //绑定编辑事件
      this.grid.bind('onBeforeEdit', function (dataType, fieldName, value, text, cm, cn) {
        if (fieldName === 'CONTEXT') {
          if (this.getValue('TJBM') === '01') {
            return {
              editType: 'comboBox',
              data: [
                { id: '0', text: '未设置' },
                { id: '1', text: '已设置' }
              ]
            };
          } else if (this.getValue('TJBM') === '02') {
            return {
              editType: 'date'
            };
          } else if (this.getValue('TJBM') === '03') {
            return {
              editType: 'updown'
            };
          } else {
            return {
              editType: 'text'
            };
          }
        }
      });
    }
  };
</script>
```

:::
