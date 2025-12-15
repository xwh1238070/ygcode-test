### QzzGrid - 运算表达式设置

:::demo Grid8

```html
<template>
  <div>
    <div style="height: 300px">
      <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
    </div>
    <div id="exp"></div>
  </div>
</template>

<script>
  export default {
    name: 'Grid8',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          footerrow: true,
          colNames: [
            '对象名称',
            '统计编码',
            '开始日期',
            '结束日期',
            '贷款来源',
            '金额',
            '数量',
            '单价',
            '内容',
            '停用',
            '默认'
          ],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number', groups: false },
            { name: 'SL', scale: 0, sum: true, dataType: 'number', groups: false },
            { name: 'DJ', scale: 2, sum: true, dataType: 'number', groups: false },
            { name: 'CONTEXT', length: 100 },
            { name: 'STOP', editType: 'checkBox', align: 'center', selectAll: true },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        //初始化数据
        gridData: [
          { dxmc: '1', TJBM: '001', DKLY: 1, JE: 10000, CONTEXT: '房地产建设' },
          { dxmc: '2', TJBM: '002', DKLY: 1, JE: 10000, CONTEXT: '房地产建设', DEFAULT: true },
          { dxmc: '21', TJBM: '002001', DKLY: 6, JE: 10000, KSRQ: '2015-10-10' },
          {
            dxmc: '22',
            TJBM: '002002',
            DKLY: 2,
            JE: 10000,
            CONTEXT: '房地产建设',
            JSRQ: '2015-10-10 09:14:00',
            STOP: true
          },
          { dxmc: '221', TJBM: '002002001', DKLY: 3, JE: 10000, CONTEXT: '房地产建设', STOP: true },
          { dxmc: '3', TJBM: '003', DKLY: 2, JE: 10000 },
          { dxmc: '4', TJBM: '004', DKLY: 3, JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //设置id名称转换列表，此方法会覆盖原来的列表数据
      this.grid.setTransIDName('DKLY', { 1: '农业银行', 2: '工商银行', 3: '交通银行' });
      //添加id名称转换列表 此方法会附加到原来的列表上，可以多次调用。
      this.grid.addTransIDName('DKLY', { 4: '建设银行', 5: '广发银行', 6: '中国银行' });
      // 给指定单元格添加运算表达式。
      // #{ } 表示单元格对象， SL 是列字段, dxmc 是索引字段, 2是索引主键的值; 主要用于定位指定单元格。
      this.grid.addExpress('JE', 'dxmc', '22', {
        exp: '#{SL~~dxmc_2}*#{DJ~~dxmc_1}',
        caption: 'dxmc等于2的数量乘以dxmc等于1的单价'
      });
      //执行全量运算
      this.grid.caculate(true);
      //设置表名
      this.grid.setName('mygrid');
      //定位
      this.grid.locate('dxmc', '21');
      //获取单元格公式结构
      var obj = this.grid.getCellExpObject('dxmc', 'SL');
      //显示
      document.querySelector('#exp').innerHTML = JSON.stringify(obj);
    }
  };
</script>
```

:::
