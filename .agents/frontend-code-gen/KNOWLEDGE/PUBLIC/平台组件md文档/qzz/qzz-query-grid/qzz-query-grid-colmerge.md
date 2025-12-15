### QzzQueryGrid - 表头斜线分隔标题，表头单元格合并， 明细行，列单元格合并，合半行单元格合并。

:::demo QueryGrid8

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 500px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid" @onafterload="onAfterLoad"></qzz-query-grid>
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
          subtotal: true,
          exttotal: true,
          'colNames':[
            [{'caption': '合并', 'startIndex': '1', 'colSpan': 1}, {'caption':'合并2', "startIndex": 2, "colSpan": 3}],
            [
              // 斜线分界线，多添加 captionExt横向标题， 表头夸列
              {'caption':'对象名称','captionExt': '年月', 'align':'left', colSpan: 2},
              // 表头夸列
              {caption: '开始日期',colSpan: 2},
              {'caption':'贷款来源','align':'left'},
              '停用', '金额', '电话', '默认',
              {'caption':'内容','align':'left', 'hideTopBorder': true},
              '税率', '函数测试', '操作'
            ]
				  ],
          'colModels':[
            {'name':'dxmc','frozen':true, link: true, editable: false, width: 100, lockWidth: true,'groups': true,
            showHint: true, // formatStr: '##*',
            hintFontSize: 20},
            {'name':'TJBM','showHint':true,'link':true, editable: false, showHint: true, width: 100, lockWidth: true, 'frozen':false},
            {'name':'KSRQ','dataType':'date','notnull':false, 'validate':'notempty','validateMessage':'开始日期不能为空。',width: 200, lockWidth: true,'frozen':false,
              validateStrong: true, formatStr: 'yyyy年MM月dd日', groups: true},  //formatStr: 'yyyy年MM月dd日', numUpper: 'Lower'},
            {'name':'JSRQ','dataType':'date','showTime':true, formatStr: 'yyyy年MM月dd日 h点mm分ss秒', numUpper: 'Lower'},
            {'name':'DKLY','editType':'comboBox', "idkey":"flgid", 
              disInput:true ,capkey: "caption",showCount: 6, 
              multiSelect:false, "rowHeight":17,
              escape: false, align: 'center',
            'data': [{'flgid':'#ff0000','caption':'建设银行'}, {'flgid': '2', 'caption': '中国工商银行'}, {'flgid': '3', 'caption': '农业银行'}, {'flgid': '4', 'caption': '建设银行'}],
            'search':true, 'notnull':true, 'filterTextOnly':true, showHint:true},
            {'name':'STOP', 'editType':'checkBox','align':'center', 'editable': true, selectAll:true, groups: true, showable:false},
            {'name':'JE', 'scale':2,'sum':true, 'dataType':'number', exponential: false, color: 'red',// numUpper: 'Lower', //'numUnit':100, 
            'editType':'number','step':0.1, 'showHint': true, hintFontSize: 20, groups: false},
            {'name': 'TEL'},
            {'name':'DEFAULT','align':'center', 'editType': 'radio', selectAll:true, 'rfrozen': false, hidden: true},
            {'name':'CONTEXT','length':100, 'rfrozen': false, secretChar: '*', staticChar: '建', hidden: false, showable: false},
            {'name':'TAX','length':10, 'rfrozen': false, dataType: 'number', numUnit: 0.01, scale: 4, hidden: false, sum:true},
            {'name': 'func', 'rfrozen': false, editType: function() {}},
            {'name': 'todo', 'rfrozen': true, 'groups': true, 'readOnly': true}
          ]
        },
        griddata: [
          {'dxmc':'1','TJBM':'001','DKLY':'农业银行','JE':123456,'TEL': '13149316032', 'CONTEXT':'房地产  建设1','STOP':true,'DEFAULT':false},
          {'dxmc':'2','TJBM':'002','DKLY':'农业银行','JE':10000,'TEL': '445632199568621236', 'CONTEXT':'房地产建设','DEFAULT':false, TAX: 0.562315},
          {'dxmc':'21','TJBM':'002001','DKLY':'农业银行','JE':10000,'TEL': '7777586912365486','KSRQ':'2015-10-10','DEFAULT':false},
          {'dxmc':'211','TJBM':'002001001','DKLY':'农业银行','JE':10000,'KSRQ':'2015-10-10','DEFAULT':false},
          {'dxmc':'22','TJBM':'002002','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','JSRQ':'2015-10-10 09:14:00','STOP':true,'DEFAULT':false},
          {'dxmc':'221','TJBM':'002002001','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','STOP':true,'DEFAULT':false},
          {'dxmc':'3','TJBM':'003','DKLY':'交通银行','JE':10001,'DEFAULT':false},
          {'dxmc':'4','TJBM':'004','CON':'3','DKLY':'交通银行','JE':10001,'DEFAULT':false, TAX: 110},
          {'dxmc':'4','TJBM':'005','DKLY':'工商银行','JE':10001,'CONTEXT':'房地产建设','DEFAULT':false},
          {'dxmc':'4','TJBM':'006','CON':'3','DKLY':'交通银行','JE':10001,'DEFAULT':false},
					 
          {'dxmc':'4','TJBM':'007','KSRQ':'2015-10-10','DKLY':'工商银行','JE':10000,'CONTEXT':'房地产建设','DEFAULT':false},
          {'dxmc':'1','TJBM':'011','KSRQ':'2015-10-10','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','STOP':true,'DEFAULT':false},
          {'dxmc':'2','TJBM':'012','DKLY':'农业银行','JE':100000000,'CONTEXT':'房地产建设','DEFAULT':true},
          {'dxmc':'21','TJBM':'012001','DKLY':'农业银行','JE':10000,'KSRQ':'2015-10-10','DEFAULT':false},
          {'dxmc':'22','TJBM':'012002','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','JSRQ':'2015-10-10 09:14:00','STOP':true,'DEFAULT':false},
          {'dxmc':'221','TJBM':'012002001','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','STOP':true,'DEFAULT':false},
          {'dxmc':'3','TJBM':'013','DKLY':'交通银行','JE':10001},
          {'dxmc':'4','TJBM':'014','CON':'3','DKLY':'交通银行','JE':10001,'DEFAULT':false},
          {'dxmc':'4','TJBM':'015','DKLY':'工商银行','JE':10000,'CONTEXT':'房地产建设','DEFAULT':false},
          {'dxmc':'4','TJBM':'016','CON':'3','DKLY':'交通银行','JE':10001,'DEFAULT':false},
					 
          {'dxmc':'4','TJBM':'017','DKLY':'工商银行','JE':10000,'CONTEXT':'房地产建设','DEFAULT':false},
          {'dxmc':'1','TJBM':'021','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','STOP':true,'DEFAULT':false},
          {'dxmc':'2','TJBM':'022','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','DEFAULT':false, 'STOP2':true, TAX: 110},
          {'dxmc':'21','TJBM':'022001','DKLY':'农业银行','JE':10000,'KSRQ':'2015-10-10','DEFAULT':false},
          {'dxmc':'22','TJBM':'022002','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','JSRQ':'2015-10-10 09:14:00','STOP':true,'DEFAULT':false},
          {'dxmc':'221','TJBM':'022002001','DKLY':'农业银行','JE':10000,'CONTEXT':'房地产建设','STOP':true,'DEFAULT':false},
          {'dxmc':'3','TJBM':'023','DKLY':'交通银行','JE':10001,'DEFAULT':false},
          {'dxmc':'4','TJBM':'024','CON':'3','DKLY':'交通银行','JE':10001,'DEFAULT':false},
          {'dxmc':'4','TJBM':'025','DKLY':'工商银行','JE':10000,'CONTEXT':'房地产建设','DEFAULT':false},
          {'dxmc':'4','TJBM':'026','CON':'3','DKLY':'交通银行','JE':10001,'DEFAULT':false},
					
          {'dxmc':'4','TJBM':'027','DKLY':'工商银行','JE':10000,'CONTEXT':'房地产建设','DEFAULT':false} //,
        ]
      };
    },
    mounted() {
      var gridMain = this.$refs.grid.ui;
      // 行合并单元格，dxmc上下行值一样的，合并。
      gridMain.bind('onBeforeGroups', function(cm, pcn, ccn, ismerge) {
        if(cm.name === 'todo') {
          if(this.getValue('KSRQ', ccn) !== '' && this.getValue('KSRQ', pcn) === this.getValue('KSRQ', ccn)) {
            return true;
          }
        } else if(cm.name === "dxmc") {
          if(this.getValue("dxmc", ccn) === this.getValue("dxmc", pcn)) {
            return true;
          }
        } else if(cm.name === "KSRQ") {
          if(this.getValue("dxmc", ccn) === this.getValue("dxmc", pcn) && this.getDisplay("KSRQ", ccn) === this.getDisplay("KSRQ", pcn)) {
            return true;
          } else {
            return false;
          }
        }
        return ismerge;
      });
      
    },
    methods: {
      onAfterLoad() {
        var gridMain = this.$refs.grid.ui;
        // 明细数据，列合并单元格。
        gridMain.each(function(cnode) {
          // 对象名称==4的，都合并dxmc和tjbm两列。 结合上面的行合并，组合成多行多列单元格合并
          if(this.getValue("dxmc", cnode) === '4' || this.getValue("dxmc", cnode) === "3") {
            gridMain.setColMerge("dxmc,TJBM", cnode, true);
          }
        })
        // 合计行数据对齐方式
        gridMain.setTotalAlign("JE", "center");
        // 合计行，列合并单元格。
        gridMain.setTotalColMerge(["JE", "TEL"]);
        gridMain.setTotalColMerge("dxmc,TJBM");
        gridMain.setTotalValue("JE", "123456");

        // 合计行 行合并
        gridMain.setTotalRowMerge("KSRQ", ["subtotal", "total"]);
        gridMain.setSubtotalValue("KSRQ", "2024-12-12");

        gridMain.setTotalRowMerge("JSRQ", ["exttotal", "subtotal", 'total']);
        gridMain.setExttotalValue("JSRQ", "2024-12-13");

        gridMain.setTotalRowMerge("DKLY", ["exttotal", 'subtotal']);
        gridMain.setExttotalValue("DKLY", "农业银行");

        // 
		    gridMain.setTotalColMerge("DKLY,STOP");

      }
    }
  };
</script>
```

:::
