### QzzQueryGrid - 给单元格添加气泡（hint）提示

:::demo QueryGrid21

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
          multiSelect: false,
          rownumbers: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY', link: true },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' },
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
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '6', TJBM: '006', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '7', TJBM: '007', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '8', TJBM: '008', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '9', TJBM: '009', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '10', TJBM: '010', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '11', TJBM: '011', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '12', TJBM: '012', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '13', TJBM: '013', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '14', TJBM: '014', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '15', TJBM: '015', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      var _this = this;
      //局域选择时合计数据，
      var wins = null;
      this.grid.bind('onCellDraw_JE', function (dataSet, fieldName, cell) {
        var bn = _this.grid.selRect.beginRowNode;
        var en = _this.grid.selRect.endRowNode;
        if (bn != en) {
          //计算合计
          var ds = _this.grid.dataSet;
          var on = ds.getCurNode();
          ds.beginUpdate();
          ds.select(bn);
          var sum = 0,
            count = 0;
          while (ds.getCurNode().recNo != en.recNo) {
            sum += ds.getValue('JE');
            count++;
            ds.next();
          }
          if (ds.getCurNode().recNo == en.recNo) {
            sum += ds.getValue('JE');
          }
          ds.select(on);
          ds.endUpdate(false);
          //显示窗口
          if (wins == null) {
            wins = new qzz.ui.drop.popbubble({ width: 100, height: 30, color: 'red', borderColor: 'red' });
            wins.setParent(document.body);
          }
          wins.setText(sum);
          wins.execute('right', this.grid.curCell);
        }
      });
      //划过显示浮动窗口
      this.grid.bind('onCellDraw_DKLY', function (dataSet, fieldName, cell) {
        cell._cnode = dataSet.getCurNode();
        cell.onmouseover = function () {
          if (dataSet.getValue(fieldName, this._cnode) !== '') {
            if (this._win == null) {
              this._win = new qzz.ui.drop.popbubble({ width: 100, height: 100 });
              this._win.setParent(document.body);
            }
            this._win.setText(this._cnode.scrIndex);
            this._win.execute('right', this);
          }
        };
        cell.onmouseout = function () {
          if (this._win) {
            this._win.hide();
          }
        };
      });
    }
  };
</script>
```

:::
