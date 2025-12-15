## @yjpl/Spread SpreadJs

葡萄城SpreadJs 第三方依赖封装

### 依赖整合

**@yjpl/spread**: yjpl封装SpreadJs主要部分,内容包括@grapecity-software/spread-excelio, @grapecity-software/spread-sheets, @grapecity-software/spread-sheets-io, @grapecity-software/spread-sheets-languagepackages, @grapecity-software/spread-sheets-resources-zh, @grapecity-software/spread-sheets-vue , **默认引入gc.spread.sheets.excel2013white.css，其他样式需从lib/styles中自行引入**

**@yjpl/spread-designer**: yjpl封装SpreadJs设计器designer部分,内容包括@grapecity-software/spread-sheets-designer, @grapecity-software/spread-sheets-designer-resources-cn, @grapecity-software/spread-sheets-designer-vue

**@yjpl/spread-pivot-addon**: yjpl封装SpreadJs透视表PivotTable部分,内容包括@grapecity-software/spread-sheets-pivot-addon, @grapecity-software/spread-sheets-slicers

**@yjpl/spread-barcode**: yjpl封装SpreadJs的二维码部分,内容包括@grapecity-software/spread-sheets-barcode

**@yjpl/spread-charts**: yjpl封装SpreadJs的图表部分,内容包括@grapecity-software/spread-sheets-charts

**@yjpl/spread-pdf**: yjpl封装SpreadJs的pdf部分,内容包括@grapecity-software/spread-sheets-pdf

**@yjpl/spread-print**: yjpl封装SpreadJs的打印部分,内容包括@grapecity-software/spread-sheets-print

**@yjpl/spread-shapes**: yjpl封装SpreadJs的图形管理部分,内容包括@grapecity-software/spread-sheets-shapes

**@yjpl/spread-tablesheet**: yjpl封装SpreadJs的工作表部分,内容包括@grapecity-software/spread-sheets-tablesheet

**@yjpl/spread-ganttsheet**: yjpl封装SpreadJs的甘特图部分,内容包括@grapecity-software/spread-sheets-tablesheet
```javascript
import { GC, Excel } from '@yjpl/spread';
// import * as Excel from '@grapecity-software/spread-excelio';
// import GC from '@grapecity-software/spread-sheets';
```

### 基础用法

初始化SpreadJS

:::demo

```html
<template>
    <div>
       <div id='ss' style='width:100%; height:400px;'></div>
    </div>
</template>

<script>

export default {
  mounted(){
    const spread = new GC.Spread.Sheets.Workbook(document.getElementById('ss'), {
      sheetCount: 1
    });
    spread.getActiveSheet().setValue(0, 0, "Hello World!");
  }

}
</script>

```
:::

### 表单

SpreadJS 包括多个表单, 表单名称标签, 滚动条等等。其核心部分是表单。

:::demo

```html
<template>
    <div class="sample-tutorial">
       <div id='ss1' class='sample-spreadsheets'></div>
       <div class="options-container">
            <div class="option-row">
                <label>Use the below buttons to add, remove or clear all sheets from the current workbook.</label>
            </div>
            <div class="option-row">
                <input type="button" value="Add Sheet" id="btnAddSheet" />
                <input type="button" value="Remove Sheet" id="btnRemoveSheet" />
                <input type="button" value="Clear Sheets" id="btnClearSheets" />
            </div>
            <div class="option-row">
                <label>ActiveSheetIndex:</label>
                <input type="text" id="activeSheetIndex" value="0" />
                <input type="button" id="btnSetActiveSheetIndex" value="Set" />
            </div>
            <div class="option-row">
                <label>This switches the currently active sheet to the sheet at the specified index.</label>
            </div>
            <div class="option-row">
                <label>ChangeSheetIndex</label>
                <label>Sheet Name:</label>
                <input type="text" id="changeSheetIndexName" value="Sheet1" />
                <label>Target Index:</label>
                <input type="text" id="changeSheetIndexTargetIndex" value="2" />
                <input type="button" id="btnChangeSheetIndex" value="Set" />
            </div>
        </div>
    </div>
</template>

<script>

export default {
  mounted(){
    const spread = new GC.Spread.Sheets.Workbook(document.getElementById('ss1'));
    const spreadNS = GC.Spread.Sheets;
    spread.setSheetCount(3);
    spread.getActiveSheet().setValue(0, 0, "Hello World!");

    spread.bind(spreadNS.Events.ActiveSheetChanged, function(e, args) {
      document.getElementById('activeSheetIndex').value = spread.getActiveSheetIndex();
      document.getElementById('changeSheetIndexName').value = spread.getActiveSheet().name();
    });

    document.getElementById('btnAddSheet').addEventListener('click',function() {
      const activeIndex = spread.getActiveSheetIndex(); 
      if (activeIndex >= 0) {
      spread.addSheet(activeIndex+1);
      spread.setActiveSheetIndex(activeIndex+1);
    }
      else{
        spread.addSheet(0);
        spread.setActiveSheetIndex(0);
      }
    });

    document.getElementById('btnRemoveSheet').addEventListener('click',function() {
      const activeIndex = spread.getActiveSheetIndex();
      if (activeIndex >= 0) {
        spread.removeSheet(activeIndex);
        spread.setActiveSheetIndex(activeIndex);
      }
    });

    document.getElementById('btnClearSheets').addEventListener('click',function() {
      spread.clearSheets();
    });

    document.getElementById('btnSetActiveSheetIndex').addEventListener('click',function() {
      let index = document.getElementById('activeSheetIndex').value;
      if (!isNaN(index)) {
        index = parseInt(index);
        if (0 <= index && index < spread.getSheetCount()) {
          spread.setActiveSheetIndex(index);
        }
      }
    });
    document.getElementById('btnChangeSheetIndex').addEventListener('click',function() {
      const sheetName = document.getElementById('changeSheetIndexName').value;
      let targetIndex = document.getElementById('changeSheetIndexTargetIndex').value;
      if (!isNaN(targetIndex)) {
        targetIndex = parseInt(targetIndex);
        if (0 <= targetIndex && targetIndex <= spread.getSheetCount()) {
          spread.changeSheetIndex(sheetName, targetIndex);
        }
      }
    });
  }

}
</script>

<style>
.sample-tutorial {
  position: relative;
  height: 400px;
  overflow: hidden;
}

.sample-spreadsheets {
    width: calc(100% - 280px);
    height: 100%;
    overflow: hidden;
    float: left;
}

.options-container {
    float: right;
    width: 280px;
    padding: 12px;
    height: 100%;
    box-sizing: border-box;
    background: #fbfbfb;
    overflow: auto;
}

.option-row {
    font-size: 14px;
    padding: 5px;
    margin-top: 10px;
}

.sample-tutorial label {
    display: block;
    margin-bottom: 3px;
    margin-top: 3px;
}

.sample-tutorial input {
    padding: 4px 6px;
}

.sample-tutorial input[type=button] {
    margin-top: 6px;
    display: block;
    width: 100%;
    text-align: center;
}
.sample-tutorial input[type=text] {
    width: 230px;
}
</style>

```
:::

### 导入导出

SpreadJS 支持 JSON 格式的序列化与反序列化。你可以将当前 spread 保存为 JSON 格式的数据进行存储, 也可以导入这些 JSON 格式数据到 spread 中来进行场景恢复。当你想要把整个Spread或者Sheet存储在数据库中或者从数据库中恢复，这个功能就很实用。

:::demo

```html
<template>
    <div>
        <div class="sample-tutorial">
          <div class="sample-spreadsheets" style="overflow: auto;">
              <label style="font:bold 10pt arial">ToJson:</label>
              <div id="ss2" style="height: 260px"></div>
              <br />
              <label style="font:bold 10pt arial">FromJson:</label>
              <div id="ss3" style="height: 260px"></div>
          </div>
          <div class="options-container">
              <div style="width:290px">
                  <label>This serializes the first Spread instance to a JSON object, and then deserializes that object
                      into the second Spread instance.</label>
                  <div class="option-row">
                      <input type="button" value="Json Serialize" id="fromtoJsonBtn" />
                  </div>
                  <div>
                      <div class="container">
                          <div class="row" style="margin-top: 10px">
                              <div class="col-xs-12">
                                  <label>FromJSON Options:</label>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="import_noFormula" />
                                      <label style="text-align: left" for="import_noFormula">Ignore Formula</label>
                                      <input type="checkbox" id="import_noStyle" />
                                      <label style="text-align: left" for="import_noStyle">Ignore Style</label>
                                  </div>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="import_rowHeaders" />
                                      <label style="text-align: left" for="import_rowHeaders">Treat the frozen columns as
                                          row headers</label>
                                  </div>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="import_columnHeaders" />
                                      <label style="text-align: left" for="import_columnHeaders">Treat the frozen rows as
                                          column headers</label>
                                  </div>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="import_donotrecalculateafterload" />
                                      <label style="text-align: left" for="import_donotrecalculateafterload">Avoid
                                          recalculation after load</label>
                                  </div>
                              </div>
                          </div>
                          <div class="row" style="margin-top: 20px">
                              <div class="col-xs-12">
                                  <label>ToJSON Options:</label>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="noFormula" />
                                      <label style="text-align: left" for="noFormula">Ignore Formula</label>
                                      <input type="checkbox" id="noStyle" />
                                      <label style="text-align: left" for="noStyle">Ignore Style</label>
                                  </div>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="SaveCustomRowHeaders" />
                                      <label style="text-align: left" for="SaveCustomRowHeaders">Treat the row headers as
                                          frozen columns</label>
                                  </div>
                                  <div style="margin-top: 10px">
                                      <input type="checkbox" id="SaveCustomColumnHeaders" />
                                      <label style="text-align: left" for="SaveCustomColumnHeaders">Treat the column
                                          headers as frozen rows</label>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
    </div>
</template>

<script>

export default {
  mounted(){
    const spread = new GC.Spread.Sheets.Workbook(this._getElementById('ss3'), { sheetCount: 3 });
    const spread2 = new GC.Spread.Sheets.Workbook(this._getElementById('ss2'), { sheetCount: 1 });
    this.initSpread(spread);
  },
  methods: {
    initSpread(spread){
      var sheet = spread.getSheet(0);
      this.fillStyle(sheet);
      var pivotSourceSheet = spread.getSheet(1);
      pivotSourceSheet.name("PivotSourceData");
      var pivotSourceTableName = this.fillPivotSourceData(pivotSourceSheet);
      var pivotTableSheet = spread.getSheet(2);
      pivotTableSheet.name("PivotTable");
      this.fillPivotTable(pivotTableSheet, pivotSourceTableName);

      this._getElementById('fromtoJsonBtn').addEventListener('click', function() {
        var jsonOptions = {
          ignoreFormula: !!this._getElementById('import_noFormula').checked,
          ignoreStyle: !!this._getElementById('import_noStyle').checked,
          frozenColumnsAsRowHeaders: !!this._getElementById('import_rowHeaders').checked,
          frozenRowsAsColumnHeaders: !!this._getElementById('import_columnHeaders').checked,
          doNotRecalculateAfterLoad: !!this._getElementById('import_donotrecalculateafterload').checked
        };
        var serializationOption = {
          ignoreFormula: !!this._getElementById('noFormula').checked,
          ignoreStyle: !!this._getElementById('noStyle').checked,
          rowHeadersAsFrozenColumns: !!this._getElementById('SaveCustomRowHeaders').checked,
          columnHeadersAsFrozenRows: !!this._getElementById('SaveCustomColumnHeaders').checked
        };
        //ToJson
        var spread1 = GC.Spread.Sheets.findControl(document.getElementById('ss'));
        var jsonStr = JSON.stringify(spread1.toJSON(serializationOption));

        //FromJson
        var spread2 = GC.Spread.Sheets.findControl(document.getElementById('ss1'));;
        spread2.fromJSON(JSON.parse(jsonStr), jsonOptions);
      });
    },
    fillStyle(sheet) {
      var spreadNS = GC.Spread.Sheets;
      sheet.suspendPaint();

      sheet.frozenRowCount(4);
      sheet.frozenColumnCount(4);

      sheet.addSpan(1, 1, 1, 3);
      sheet.setValue(1, 1, 'Store');
      sheet.addSpan(1, 4, 1, 7);
      sheet.setValue(1, 4, 'Goods');
      sheet.addSpan(2, 1, 1, 2);
      sheet.setValue(2, 1, 'Area');
      sheet.addSpan(2, 3, 2, 1);
      sheet.setValue(2, 3, 'ID');
      sheet.addSpan(2, 4, 1, 2);
      sheet.setValue(2, 4, 'Fruits');
      sheet.addSpan(2, 6, 1, 2);
      sheet.setValue(2, 6, 'Vegetables');
      sheet.addSpan(2, 8, 1, 2);
      sheet.setValue(2, 8, 'Foods');
      sheet.addSpan(2, 10, 2, 1);
      sheet.setValue(2, 10, 'Total');
      sheet.setValue(3, 1, 'State');
      sheet.setValue(3, 2, 'City');
      sheet.setValue(3, 4, 'Grape');
      sheet.setValue(3, 5, 'Apple');
      sheet.setValue(3, 6, 'Potato');
      sheet.setValue(3, 7, 'Tomato');
      sheet.setValue(3, 8, 'SandWich');
      sheet.setValue(3, 9, 'Hamburger');

      sheet.addSpan(4, 1, 7, 1);
      sheet.addSpan(4, 2, 3, 1);
      sheet.addSpan(7, 2, 3, 1);
      sheet.addSpan(10, 2, 1, 2);
      sheet.setValue(10, 2, 'Sub Total:');
      sheet.addSpan(11, 1, 7, 1);
      sheet.addSpan(11, 2, 3, 1);
      sheet.addSpan(14, 2, 3, 1);
      sheet.addSpan(17, 2, 1, 2);
      sheet.setValue(17, 2, 'Sub Total:');
      sheet.addSpan(18, 1, 1, 3);
      sheet.setValue(18, 1, 'Total:');

      sheet.setValue(4, 1, 'NC');
      sheet.setValue(4, 2, 'Raleigh');
      sheet.setValue(7, 2, 'Charlotte');
      sheet.setValue(4, 3, '001');
      sheet.setValue(5, 3, '002');
      sheet.setValue(6, 3, '003');
      sheet.setValue(7, 3, '004');
      sheet.setValue(8, 3, '005');
      sheet.setValue(9, 3, '006');
      sheet.setValue(11, 1, 'PA');
      sheet.setValue(11, 2, 'Philadelphia');
      sheet.setValue(14, 2, 'Pittsburgh');
      sheet.setValue(11, 3, '007');
      sheet.setValue(12, 3, '008');
      sheet.setValue(13, 3, '009');
      sheet.setValue(14, 3, '010');
      sheet.setValue(15, 3, '011');
      sheet.setValue(16, 3, '012');

      sheet.setFormula(10, 4, '=SUM(E5:E10)');
      sheet.setFormula(10, 5, '=SUM(F5:F10)');
      sheet.setFormula(10, 6, '=SUM(G5:G10)');
      sheet.setFormula(10, 7, '=SUM(H5:H10)');
      sheet.setFormula(10, 8, '=SUM(I5:I10)');
      sheet.setFormula(10, 9, '=SUM(J5:J10)');

      sheet.setFormula(17, 4, '=SUM(E12:E17)');
      sheet.setFormula(17, 5, '=SUM(F12:F17)');
      sheet.setFormula(17, 6, '=SUM(G12:G17)');
      sheet.setFormula(17, 7, '=SUM(H12:H17)');
      sheet.setFormula(17, 8, '=SUM(I12:I17)');
      sheet.setFormula(17, 9, '=SUM(J12:J17)');

      for (var i = 0; i < 14; i++) {
        sheet.setFormula(4 + i, 10, '=SUM(E' + (5 + i).toString() + ':J' + (5 + i).toString() + ')');
      }

      sheet.setFormula(18, 4, '=E11+E18');
      sheet.setFormula(18, 5, '=F11+F18');
      sheet.setFormula(18, 6, '=G11+G18');
      sheet.setFormula(18, 7, '=H11+H18');
      sheet.setFormula(18, 8, '=I11+I18');
      sheet.setFormula(18, 9, '=J11+J18');
      sheet.setFormula(18, 10, '=K11+K18');

      sheet.getRange(1, 1, 3, 10).backColor('#F2F2F2');
      sheet.getRange(4, 1, 15, 3).backColor('#CFCFCF');
      sheet.getRange(1, 1, 3, 10).hAlign(spreadNS.HorizontalAlign.center);

      sheet.getRange(1, 1, 18, 10).setBorder(new spreadNS.LineBorder('Black', spreadNS.LineStyle.thin), { all: true });
      sheet.getRange(4, 4, 3, 6).setBorder(new spreadNS.LineBorder('Black', spreadNS.LineStyle.dotted), { inside: true });
      sheet.getRange(7, 4, 3, 6).setBorder(new spreadNS.LineBorder('Black', spreadNS.LineStyle.dotted), { inside: true });
      sheet
        .getRange(11, 4, 3, 6)
        .setBorder(new spreadNS.LineBorder('Black', spreadNS.LineStyle.dotted), { inside: true });
      sheet
        .getRange(14, 4, 3, 6)
        .setBorder(new spreadNS.LineBorder('Black', spreadNS.LineStyle.dotted), { inside: true });

      this.fillSampleData(sheet, new spreadNS.Range(4, 4, 6, 6));
      this.fillSampleData(sheet, new spreadNS.Range(11, 4, 6, 6));

      sheet.resumePaint();
    },
    fillSampleData(sheet, range) {
      for (var i = 0; i < range.rowCount; i++) {
        for (var j = 0; j < range.colCount; j++) {
          sheet.setValue(range.row + i, range.col + j, Math.ceil(Math.random() * 300));
        }
      }
    },
    fillPivotSourceData(sheet) {
      sheet.setRowCount(117);
      sheet.getCell(-1, 0).formatter("YYYY-mm-DD");
      sheet.getRange(-1,4,0,2).formatter("$ #,##0");
      let table = sheet.tables.add('table', 0, 0, 117, 6);
      for(let i=2;i<=117;i++)
      {
        sheet.setFormula(i-1,5,'=D'+i+'*E'+i)
      }
      table.style(GC.Spread.Sheets.Tables.TableThemes["none"]);
      sheet.setArray(0, 0, pivotSales);
      return table.name();
    },
    fillPivotTable (sheet, tableName) {
      sheet.setRowCount(1000);
      let pivotTableOptions = {bandRows:true,bandColumns:true};
      let pivotTable = sheet.pivotTables.add("PivotTable", tableName, 1, 1, GC.Spread.Pivot.PivotTableLayoutType.outline, GC.Spread.Pivot.PivotTableThemes.medium1, pivotTableOptions);
      pivotTable.suspendLayout();
      pivotTable.add("salesperson", "Salesperson", GC.Spread.Pivot.PivotTableFieldType.rowField);
      pivotTable.add("car", "Cars", GC.Spread.Pivot.PivotTableFieldType.rowField);
      pivotTable.add("date", "Date", GC.Spread.Pivot.PivotTableFieldType.columnField);
      let groupInfo = { originFieldName: "date", dateGroups: [{ by: GC.Pivot.DateGroupType.quarters }] };
      pivotTable.group(groupInfo);
      pivotTable.add("total", "Totals", GC.Spread.Pivot.PivotTableFieldType.valueField, GC.Pivot.SubtotalType.sum);
      let style = new GC.Spread.Sheets.Style();
      style.formatter = "$ #,##0";
      pivotTable.setStyle({dataOnly: true}, style);
      pivotTable.resumeLayout();
      pivotTable.autoFitColumn();
    },
    _getElementById(id){
        return document.getElementById(id);
    } 
  }
}
</script>

```
:::

### 增量加载

SpreadJS fromJSON函数支持增量加载，该加载将在后台加载值和公式时快速显示数据。当导入大文件时建议使用增量加载以帮助优化初始加载时间。

:::demo

```html
<template>
    <div>
        <div class="sample-tutorial1">
          <div class="sample-container">
              <div id="ss4" class="sample-spreadsheets1"></div>
              <div id="statusBar"></div>
          </div>
          <div class="sample-options">
              <div class="options-container1">
                  <p class="summary">
                      You can load the sample json or import a file to see the difference.
                  </p>


                  <div class="option-row1">
                      <input type="checkbox" id="incremental" checked />
                      <label for="incremental">Incremental Loading</label>
                  </div>
                  <div class="option-row1">
                      <div class="inputContainer">
                          <input type="button" id="loadSample" value="Load Sample JSON" class="button">

                      </div>
                  </div>
                  <div class="option-row1">
                      <div class="inputContainer">
                          <input type="file" id="fileDemo" class="input">
                          <input type="button" id="loadFile" value="Load File" class="button">
                      </div>
                  </div>
              </div>
          </div>
      </div>
    </div>
</template>

<script>

function LoadingStatus(name, options) {
	GC.Spread.Sheets.StatusBar.StatusItem.call(this, name, options);
}
LoadingStatus.prototype = new GC.Spread.Sheets.StatusBar.StatusItem();
LoadingStatus.prototype.onCreateItemView = function (container) {
	var statusBarDiv = this.contentDiv = document.createElement('div');
	statusBarDiv.innerHTML = `<span>Ready</span>
					<span style="width: 150px;height: 9px;border: solid 1px white;display: none;margin-left: 10px; line-height: 0px;">
						<span style="width: 93px;height: 9px;background-color: white;display: inline-block;"></span>
					</span>`;
	statusBarDiv.style.padding = "0 3px";
	container.appendChild(statusBarDiv);
};
LoadingStatus.prototype.updateProgress = function (progress, args) {
	progress = progress * 100;
	this.contentDiv.children[0].innerText = "Loading: " + progress.toFixed(2) + "%, "+ args.sheet.name();
	this.contentDiv.children[1].style.display = "inline-block";
	this.contentDiv.children[1].children[0].style.width = progress * 1.5 + "px";
};
LoadingStatus.prototype.updateText = function (text) {
	this.contentDiv.children[0].innerText = text;
	this.contentDiv.children[1].style.display = "none";
};

export default {
  mounted(){
    var spread = new GC.Spread.Sheets.Workbook(document.getElementById('ss4'));

    var statusBar = new GC.Spread.Sheets.StatusBar.StatusBar(document.getElementById('statusBar'));
    statusBar.bind(spread);
    statusBar.remove("cellMode");
    var loadingStatus = new LoadingStatus('LoadingStatus', { tipText: 'LoadingStatus' });
    statusBar.add(loadingStatus);

    var incrementalEle = document.getElementById("incremental");
    function fromJSON(json) {
      if (incrementalEle.checked) {
        spread.fromJSON(json, {
                  doNotRecalculateAfterLoad: true,
          incrementalLoading: {
            loading: function (progress, args) {
              loadingStatus.updateProgress(progress, args);
            },
            loaded: function () {
              loadingStatus.updateText("Ready");
            }
          }
        });
      } else {
        spread.fromJSON(json);
        loadingStatus.updateText("Ready");
      }
    }

    document.getElementById('loadSample').onclick = function () {
      fromJSON(ssjson);
    }

    document.getElementById('loadFile').onclick = function () {
      var file = document.getElementById("fileDemo").files[0];
      if (file) {
        loadingStatus.updateText("Reading file");
        var fileName = file.name;
        var suffix = fileName.substr(fileName.lastIndexOf('.'));
        if (suffix === '.xlsx') {
          var excelIo = new Excel.IO();
          // here is excel IO API
          excelIo.open(file, function (json) {
            fromJSON(json);
          }, function (e) {
            console.log(e);
          });
        } else if (suffix === '.ssjson' || suffix === '.json') {
          var reader = new FileReader();
          reader.onload = function () {
            var json = JSON.parse(this.result);
            fromJSON(json);
          };
          reader.readAsText(file);
        }
      }
    };
  }
}
</script>
<style>
  @media only screen and (max-width: 768px) {
    .options-toggle {
        display: block !important;
        top: 20px;
        left: -30px;
        width: 30px;
        height: 30px;
        position: absolute;
        background-color: #86bd00;
        line-height: 30px;
        text-align: center;
        padding: 4px;
        box-sizing: border-box;
        color: #fff;
    }

    .options-container1 {
        width: 280px !important;
        overflow: auto;
        height: 100%;
        padding: 22px;
        box-sizing: border-box;
    }
    .sample-container {
        width: 100% !important;
        height: 100%;
        overflow: hidden;
        float: left;
    }
    .sample-options {
        right: 0;
        padding: 0 !important;
        overflow: visible !important;
        position: absolute;
        box-shadow: 0 0 3px 0 rgba(0, 0, 0, .25);
        transition: right .25s ease-in-out;
        z-index: 1000;
    }
    .sample-options.hidden {
        right: -280px;
    }

    #statusBar {
        bottom: 0;
        height: 25px;
        width: 100%;
        position: relative;
        float: left;
    }
}

.sample-tutorial1 {
    position: relative;
    height: 400px;
    overflow: hidden;
}

.sample-container {
    width: calc(100% - 280px);
    height: 100%;
    float: left;
}

.sample-spreadsheets1 {
    width: 100%;
    height: calc(100% - 25px);
    overflow: hidden;
}

#statusBar {
    bottom: 0;
    height: 25px;
    width: 100%;
    position: relative;
}

#incremental {
    padding: 4px 6px;
    width: auto;
}

.options-container1 {
    float: right;
    width: 280px;
    padding: 12px;
    height: 100%;
    box-sizing: border-box;
    background: #fbfbfb;
}

.option-row1 {
    font-size: 14px;
    padding: 16px 8px;
    margin-top: 10px;
}
</style>
```
:::