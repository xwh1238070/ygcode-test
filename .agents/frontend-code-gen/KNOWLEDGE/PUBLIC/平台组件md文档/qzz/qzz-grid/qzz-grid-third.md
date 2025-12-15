### QzzGrid - 嵌入外部输入控件

:::demo

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  //  import { DatePicker } from 'yjpl-ui';

  export default {
    name: 'Grid14',
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
            //方式1，通过require引入控件模块"qzz.idatepicker",
            //然后把对应引入的 myDatePicker对象设置到editType上，如下
            { name: 'KSRQ', editType: '' },
            { name: 'JSRQ', editType: 'date', showTime: true, width: 150 },
            //方式２，直接把嵌入的控件的引入模块直接配置到editType中，如下
            {
              name: 'DKLY',
              editType: 'ecp.component.comboBox',
              data: [
                { id: '0', text: '农业银行' },
                { id: '1', text: '商业银行' },
                { id: '2', text: '工商银行' },
                { id: '3', text: '交通银行' }
              ]
            },
            { name: 'JE', editType: 'number', scale: 2, dataType: 'number', sum: true },
            { name: 'CONTEXT', length: 100, dataType: 'number' },
            { name: 'DEFAULT', editType: 'radio', align: 'center' },
            { name: 'STOP', editType: 'checkBox', align: 'center' }
          ]
        },
        gridData: [
          { dxmc: '明细1', TJBM: '01' },
          { dxmc: '明细2', TJBM: '02' },
          { dxmc: '明细2', TJBM: '03' },
          { dxmc: '明细2', TJBM: '04' },
          { dxmc: '明细2', TJBM: '03' },
          { dxmc: '明细2', TJBM: '04' }
        ]
      };
    }
  };
</script>
```

:::
