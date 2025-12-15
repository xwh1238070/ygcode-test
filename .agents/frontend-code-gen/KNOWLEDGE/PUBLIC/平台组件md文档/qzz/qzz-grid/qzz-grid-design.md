### QzzGrid - 表格设计器

:::demo Grid15

```html
<template>
  <div>
    <tl-button @click="design" id="designBtn">表格设计</tl-button>
    <tl-button @click="add" id="addBtn">添加明细</tl-button>
    <tl-button @click="addChild" id="addChildBtn">添加下级明细</tl-button>
    <div style="height:500px;margin-top: 10px">
      <qzz-grid :option="gridOption" v-model="gridData" ref="grid" style="height:500px;"></qzz-grid>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Grid15',
    data() {
      return {
        cpid: 5,
        gridOption: {
          Align: 'alTop',
          treeGrid: true,
          height: 500,
          suitToFit: true,
          checkFlag: 'ps',
          unCheckFlag: 'ps',
          multiSelect: true,
          idkey: 'cid',
          pidkey: 'cpid',
          //"idkey":"bm",
          //modelData:obj,
          colNames: [
            [
              { caption: 'test2', startIndex: 0, colSpan: 4 },
              { caption: 'test3', startIndex: 4, colSpan: 2 }
            ],
            [{ caption: 'test1', startIndex: 1, colSpan: 3 }],
            ['classid', 'typeid', 'gid', 'billid', 'caption', 'formatid', 'cid', 'cpid', 'bm']
          ],
          colModels: [
            { name: 'classId' },
            { name: 'typeid' },
            { name: 'gid' },
            { name: 'billid' },
            { name: 'caption' },
            { name: 'formatid' },
            { name: 'cid' },
            { name: 'cpid' },
            { name: 'bm' }
          ]
        },
        gridData: [
          { classId: 'ywdj.gldx', typeid: '011243', cid: 1, cpid: 0, bm: '0001' },
          { classId: 'ywdj.gldx', typeid: '011244', cid: 2, cpid: 1, bm: '00010001' },
          { classId: 'ywdj.gldx', typeid: '011244', cid: 3, cpid: 2, bm: '00010002' },
          { classId: 'ywdj.gldx', typeid: '011244', cid: 4, cpid: 2, bm: '000100020001' }
        ]
      };
    },
    methods: {
      design() {
        const grid = this.$refs.grid.ui;
        new design({ option: grid.getOption(), index: this.grid.index }, function (opt) {
          //alert("ok");
          grid.refreshTitle(opt);
          grid.value(datas);
        });
      },
      add() {
        const grid = this.$refs.grid.ui;
        if (grid.getOption().treeGrid === true) {
          var citem = {};
          // citem[grid.getOption().pidkey] = this.cpid;
          grid.appendChild(citem);
          this.cpid++;
        }
      },
      addChild() {
        const grid = this.$refs.grid.ui;
        var curNode = grid.getCurNode();
        var pNode = curNode.pNode;
        var citem = {};
        citem[grid.getOption().pidkey] = grid.getValue(grid.getOption().idkey, pNode);
        grid.append(citem);
      }
    }
  };
</script>
```

:::
