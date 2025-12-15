### QzzQueryGrid - 列合并单元格

:::demo QueryGrid3

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <button @click="appendBtn">添加</button>
      <button @click="insertBtn">插入</button>
      <button @click="deleteBtn">删除</button>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          footerrow: true,
          groups: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '停用', '默认'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY', groups: true },
            { name: 'JE', scale: 2, sum: true, dataType: 'number', groups: true },
            { name: 'CONTEXT', length: 100, groups: true },
            { name: 'STOP', editType: 'checkBox', align: 'center', groups: true, selectAll: true },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        griddata: [
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
    methods: {
      appendBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.append();
      },
      insertBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.insert();
      },
      deleteBtn() {
        this.grid = this.$refs.grid.ui;
        this.grid.delRecord();
      }
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      this.grid.setTransIDName('DKLY', { 1: '农业银行', 2: '工商银行', 3: '交通银行' });
      this.grid.addTransIDName('DKLY', { 4: '建设银行', 5: '广发银行', 6: '中国银行' });
      this.grid.bind('onBeforeGroups', function (colModel, priorNode, curNode, ismerge) {
        if (colModel.name == 'JE' || colModel.name == 'STOP') {
          if (this.getValue('CONTEXT', priorNode) == this.getValue('CONTEXT', curNode)) {
            return true;
          } else {
            return false;
          }
        }
      });
    }
  };
</script>
```

:::

### 在指定的列显示“合计”

:::demo QueryGrid8

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
          total: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '默认', '停用'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY' },
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
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    },
    mounted() {
      this.grid = this.$refs.grid.ui;
      //清除掉原来的合计
      this.grid.summaryText = '';
      //指定某列设置合计值
      this.grid.dataSet.setSummary('dxmc', '合计:');
      //通过绘画事件定义对齐方式
      this.grid.bind('onSummaryDraw', function (dataSet, fieldName, node) {
        if (fieldName == 'dxmc') {
          return { align: 'center' };
        }
      });
    }
  };
</script>
```

:::

### 表格列设置保存

:::demo QueryGrid9

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
    <tl-col :span="24" style="height: 20px;margin:8px;">
      <input type="text" value="col123456789" id="colSet" />
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
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    }
  };
</script>
```

:::
