### QzzQueryGrid - 单元格添加多个链接,多个按钮

:::demo QueryGrid20

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100, width: 50 },
            { name: 'DEFAULT', align: 'center', editType: 'radio' },
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
    mounted() {
      this.grid = this.$refs.grid.ui;
      //绑定draw事件
      this.grid.bind('onFormat_TJBM', function (dataSet, fieldName, text, value) {
        return '<a id="link1" href="#">链接1</a> <a id="link2" href="#">链接2</a>';
      });

      this.grid.bind('onFormat_CONTEXT', function (ds, fieldName, text, value) {
        return '<input type="button" id="btn1" value="按钮1"> <input type="button"  id="btn2" value="按钮2">';
      });

      this.grid.bind('onClick', function (node, fieldName, ev) {
        if (ev.target.id == 'link1') {
          alert('您点击了第' + this.dataSet.getIndex() + '行的链接1。');
        } else if (ev.target.id == 'link2') {
          alert('您点击了第' + this.dataSet.getIndex() + '行链接2。');
        } else if (ev.target.id == 'btn1') {
          alert('您点击了第' + this.dataSet.getIndex() + '行按钮1。');
        } else if (ev.target.id == 'btn2') {
          alert('您点击了第' + this.dataSet.getIndex() + '行按钮2。');
        }
      });
    }
  };
</script>
```

:::
