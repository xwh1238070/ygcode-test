### QzzQueryGrid - 列格式化显示、双击事件、 标注测试、添加链接、

:::demo QueryGrid17

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
          pager: true,
          Align: 'alClient',
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM', link: true },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true },
            { name: 'DKLY' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' },
            { name: 'STOP', align: 'center' }
          ]
        },
        griddata: []
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      var _this = this;
      //初始化数据
      this.grid.bind('onPageChanged', function (pageCount, pageSize, pageIndex) {
        //此处可以是跟据pageSize, pageIndex 从服务端取回的分页数据包。
        _this.grid.value([
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
        ]);
      });
      //双击事件
      this.grid.bind('onDBClick', function () {
        alert('您双击了对象名称为' + this.getValue('dxmc') + '的明细');
      });
      //先配置列属性link:true, 再绑定链接事件，
      this.grid.bind('onLinkClick', function (grd, fieldName, node) {
        alert(_this.grid.getValue(fieldName));
      });
      //格式化列数据
      this.grid.dataSet.bind('onFormat_DEFAULT', function (node, fieldName, dataType, value, metaItem) {
        if (this.getValue(fieldName) === true) {
          return '是';
        } else {
          return '否';
        }
      });
      //格式化显示成图片
      this.grid.dataSet.bind('onFormat_STOP', function (node, fieldName, dataType, value, metaItem) {
        if (this.getValue(fieldName) == true) {
          return '<img src="/ecp/webcore/themes/default/qzz/images/filter.jpg">';
        } else {
          return '<img src="/ecp/webcore/themes/default/qzz/images/up_bt.png">';
        }
      });
      this.grid.setTotalRecord(100);
      //添加标注
      this.grid.dataSet.getOption('dxmc').title = '标注测试';
      this.grid.refresh(true);
    }
  };
</script>
```

:::
