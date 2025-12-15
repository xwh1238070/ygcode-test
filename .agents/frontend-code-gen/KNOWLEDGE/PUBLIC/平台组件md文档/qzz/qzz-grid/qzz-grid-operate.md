### QzzGrid - 添加、删除、保存

:::demo Grid13

```html
<template>
  <div style="height: 500px">
    <table style="height: 500px">
      <tr>
        <td style="width:94%; height:500px;">
          <qzz-grid :option="gridOption" v-model="gridData" ref="grid" style="height:500px;"></qzz-grid>
        </td>
        <td style="width:6%;display:block;padding-left:8px;">
          <button @click="add" id="addBtn">添加</button>
          <button @click="ins" id="insBtn">插入当前行</button>
          <button @click="ins2" id="insBtn2">插入后一行</button>
          <button @click="del" id="delBtn">删除</button>
          <button @click="save" id="saveBtn">保存</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
  export default {
    name: 'Grid13',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          groups: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '停用', '金额', '内容', '默认'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true },
            { name: 'DKLY' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number', 'formatStr': '$ #{text}' },
            { name: 'CONTEXT', length: 100 },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        gridData: [
          { dxmc: '1', TJBM: '001', DKLY: '农业银行', JE: 10000, CONTEXT: '房地产建设', STOP: true },
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
          { dxmc: '3', TJBM: '003', DKLY: '交通银行', JE: 10001 },
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CON: '3', TJBM: '003', DKLY: '交通银行', JE: 10001 },
          { dxmc: '4', TJBM: '004', DKLY: '工商银行', JE: 10000, CONTEXT: '房地产建设' }
        ]
      };
    },
    methods: {
      add() {
        this.grid = this.$refs.grid.ui;
        this.grid.append();
      },
      ins() {
        this.grid = this.$refs.grid.ui;
        this.grid.insert();
      },
      ins2() {
        this.grid = this.$refs.grid.ui;
        if (this.grid.dataSet.getIndex() < this.grid.getRecordCount()) {
          this.grid.next();
          this.grid.insert();
        } else {
          this.grid.append();
        }
      },
      del() {
        this.grid = this.$refs.grid.ui;
        this.grid.delRecord();
      },
      save() {
        this.grid = this.$refs.grid.ui;
        var cs = this.grid.getChangeSet();
        if (cs.length > 0) {
          alert('有变更');
        }
      }
    }
  };
</script>
```

:::
