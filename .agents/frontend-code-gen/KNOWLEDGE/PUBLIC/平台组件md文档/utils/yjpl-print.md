# Print 打印工具类

## 引入方法

### 使用 npm 安装

```shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-print
```

### 2.在代码中引入

```js
import Print from 'yjpl-print';
```

## reset 刷新打印纸张

```js
import Print from 'yjpl-print';

Print.reset();
```

## setPageSettings 打印页面设置

```js
import Print from 'yjpl-print';

Print.setPageSettings(params, initDef);
```

### setPageSettings Attributes

| 参数    | 说明             | 类型    | 可选值 | 默认值 |
| ------- | ---------------- | ------- | ------ | ------ |
| params  | 配置的参数       | JSON    | —      | —      |
| initDef | 是否初始化默认值 | boolean | —      | —      |

### params

| 参数           | 说明                           | 类型    | 可选值    | 默认值 |
| -------------- | ------------------------------ | ------- | --------- | ------ |
| Paper          | 纸张                           | JSON    | —         | —      |
| Paper.Type     | 类型                           | string  | Custom/A4 |        |
| Paper.Width    | Type 是 Custom 时，纸张的宽度  | number  | —         | —      |
| Paper.Heigh    | Type 是 Custom 时，纸张的高度  | number  | —         | —      |
| Margins        | 边距 单位 mm                   | JSON    | —         | —      |
| Margins.Top    | 顶边距                         | number  | —         | —      |
| Margins.Bottom | 底边距                         | number  | —         | —      |
| Margins.Left   | 左边距                         | number  | —         | —      |
| Margins.Right  | 右边距                         | number  | —         | —      |
| LandScape      | 是否横向                       | boolean | —         | false  |
| ZoomValue      | 缩放系数 整数 如 100 表示 100% | number  | —         | —      |
| TextModel      | 超出换行, 超出缩小             | number  | 2/3       | —      |
| ShowSysWin     | 显示系统窗口                   | boolean | —         | —      |
| Copies         | 打印份数                       | number  | —         | 1      |
| pageRange      | 打印页码                       | string  | ALL/i/i-j | ALL    |

## addText 添加文本显示

```js
import Print from 'yjpl-print';

Print.addText(text, option, pnode);

//可用来设置主子表标题文字
Print.addText({
  text: '标题',
  top: 8,
  left: 10,
  align: 'Left',
  width: '100%',
  B: true,
  fontSize: '14'
});
```

### addText Attributes

| 参数   | 说明                                      | 类型          | 可选值 | 默认值 |
| ------ | ----------------------------------------- | ------------- | ------ | ------ |
| text   | 文字内容，如果传的是对象，会赋值给 option | object/string | —      | —      |
| option | 文字配置属性，可不传                      | JSON          | —      | —      |
| pnode  | 页面节点，可不传                          | any           | —      | —      |

### option

| 参数      | 说明                  | 类型          | 可选值            | 默认值 |
| --------- | --------------------- | ------------- | ----------------- | ------ |
| top       | 顶点                  | number        | —                 | —      |
| left      | 左边                  | number        | —                 | —      |
| width     | 宽度,字符串时写百分比 | number/string | —                 | —      |
| height    | 高度                  | number        | —                 | —      |
| fontSize  | 字号                  | number        | —                 | —      |
| align     | 横对齐                | string        | Left/Center/Right | —      |
| lineAlign | 纵对齐                | string        | Top/Center/Bottom | —      |
| id        | id 值                 | number        | —                 | —      |
| I         | 是否斜体              | boolean       | —                 | —      |
| B         | 是否粗体              | boolean       | —                 | —      |
| U         | 是否有下画线          | boolean       | —                 | —      |
| S         | 是否删除线            | boolean       | —                 | —      |
| relative  | 是否相对打印          | boolean       | —                 | —      |
| dataField | 数据项目              | string        | —                 | —      |

## addLine 添加画线

```js
import Print from 'yjpl-print';

Print.addLine(text, option);

Print.addLine('', {
  left: 10,
  top: 2,
  width: '100%',
  Border: '2',
  Style: 'solid',
  height: 0
});
```

### addLine Attributes

| 参数   | 说明                                      | 类型          | 可选值 | 默认值 |
| ------ | ----------------------------------------- | ------------- | ------ | ------ |
| text   | 文字内容，如果传的是对象，会赋值给 option | object/string | —      | —      |
| option | 配置属性                                  | JSON          | —      | —      |

### option

| 参数          | 说明                | 类型          | 可选值 | 默认值 |
| ------------- | ------------------- | ------------- | ------ | ------ |
| top           | 顶点                | number        | —      | —      |
| left          | 左边                | number        | —      | —      |
| width         | 宽,字符串时写百分比 | number/string | —      | —      |
| height        | 高                  | number        | —      | —      |
| border/Border | 线                  | string        | —      | —      |
| id            | id 值               | string        | —      | —      |
| color/Color   | 颜色                | string        | —      | —      |
| style/Style   | 线的类型            | string        | —      | —      |
| dataField     | 数据项目            | string        | —      | —      |

## addImage 添加图片

```js
import Print from 'yjpl-print';

Print.addImage(option, pNode);
```

### addImage Attributes

| 参数   | 说明           | 类型 | 可选值 | 默认值 |
| ------ | -------------- | ---- | ------ | ------ |
| option | 配置属性       | JSON | —      | —      |
| pNode  | 父节点，可不传 | any  | —      | —      |

### option

| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| ---- | ---- | ---- | ------ | ------ |
| top | 顶点| number | — | — |
| left| 左点 | number | — | — |
| width | 宽 | number | — | — |
| height | 高 | number | — | — |
| url | 路径 | string | — | — |
| dataField | 数据项目 | string | — | — |

## setImage 设置图片

```js
import Print from 'yjpl-print';

Print.setImage(imgNode, url, top, left, width, height);
```

### setImage Attributes

| 参数   | 说明     | 类型   | 可选值 | 默认值 |
| ------ | -------- | ------ | ------ | ------ |
| imgNod | 图片节点 | string | —      | —      |
| url    | 路径     | string | —      | —      |
| top    | 顶点     | number | —      | —      |
| left   | 左点     | number | —      | —      |
| width  | 宽       | number | —      | —      |
| height | 高       | number | —      | —      |

## setHeader/setFooter 设置页眉/页脚

```js
import Print from 'yjpl-print';

Print.setHeader(value, option);
Print.setHeader({ text: '第{0}页(共{1}页)', align: 'Center' });
Print.setFooter(value, option);
Print.setFooter({ text: '第{0}页(共{1}页)', align: 'Center' });
```

### setHeader/setFooter Attributes

| 参数   | 说明                                                                      | 类型                | 可选值 | 默认值 |
| ------ | ------------------------------------------------------------------------- | ------------------- | ------ | ------ |
| value  | 值,为对象时只用传这一个参数，相当于赋值给 option,为数组时有三个页眉文本值 | string/object/array | —      | —      |
| option | 属性                                                                      | JSON                | —      | —      |

### option

| 参数          | 说明       | 类型    | 可选值            | 默认值 |
| ------------- | ---------- | ------- | ----------------- | ------ |
| text          | 值         | string  | —                 | —      |
| align         | 横对齐     | string  | Left/Center/Right | —      |
| isShowLine    | 显示下画线 | boolean | —                 | —      |
| LineAlignment | 纵对齐     | string  | —                 | —      |
| fontName      | 字体名称   | string  | —                 | —      |
| fontSize      | 字体大小   | number  | —                 | —      |
| I             | 是否斜体   | boolean | —                 | —      |
| B             | 是否粗体   | boolean | —                 | —      |
| U             | 是否底线   | boolean | —                 | —      |
| S             | 是否删除线 | boolean | —                 | —      |

## addMainModel 添加主表块

```js
import Print from 'yjpl-print';

Print.addMainModel(option);
// {
//   colcount: 3,
//   labelWidth: 100,
//   fontSize: 12,
//   items: [
//     {
//       label: '标题A：',
//       name: 'ZDA',
//       labelAlign: 'Right'
//     },
//     {
//       label: '标题B：',
//       name: 'ZDB'
//     },
//     {
//       label: '标题C：',
//       name: 'ZDC'
//     }
//    ]
//  }
```

### addMainModel Attributes

| 参数       | 说明         | 类型   | 可选值 | 默认值 |
| ---------- | ------------ | ------ | ------ | ------ |
| colcount   | col 值       | number | —      | —      |
| labelWidth | 表内标签宽度 | number | —      | —      |
| fontSize   | 字号         | number | —      | —      |
| left       | 左边         | number | —      | —      |
| border     | 线           | number | —      | —      |
| items      | 表内 item    | array  | —      | —      |

## addGridModel 添加整个表格控件数据

```js
import Print from 'yjpl-print';

Print.addGridModel(dataGrid, option);
//添加子表
Print.addGridModel(grid, { left: 10, fontSize: 12, autoWidth: true });
```

### addGridModel Attributes

| 参数     | 说明     | 类型 | 可选值 | 默认值 |
| -------- | -------- | ---- | ------ | ------ |
| dataGrid | 表格控件 | grid | —      | —      |
| option   | 配置参数 | json | —      | —      |

## 打印参数设置

```js
import Print from 'yjpl-print';

Print.setCopies(count);//打印份数设置
Print.setLandscape(value);//是否横向
Print.setZoomValue(value);//设置缩放值
Print.setDocumentName(value);//设置文件名
Print.setRecordAPage(value);//设置每页记录数
Print.setRowCountPerPage(tab，value);//设置每页记录数
Print.setNoBorder(tab，value);//是否显示边线,true/false
Print.setPaperSize(value, width, height);//打印纸张尺寸设置
Print.setMargins(top, left, bottom, right);//页边距设置
Print.printAllPage();//打印所有页
Print.setPrintCounts(params);//设置打印次数
Print.printSomePage(fromPage, toPage);//打印开始页到结束页
Print.setPrintStyle(PrintControllerStyle, IsRepeatHeader, tableFillModel, useDefPrinter, bottomfurl);


```

### 打印参数 Attributes

| 参数                 | 说明                       | 类型    | 可选值 | 默认值 |
| -------------------- | -------------------------- | ------- | ------ | ------ |
| params               | 参数                       | JSON    | —      | 1      |
| count                | setCopies 打印页数         | number  | —      | 1      |
| value                | setLandscape 打印是否横向  | boolean | —      | false  |
| tab                  | table 节点                 | node    | —      |        |
| value                | setPaperSize 打印纸张类型  | string  | —      | —      |
| width                | setPaperSize 打印纸张长    | number  | —      | —      |
| height               | setPaperSize 打印纸张宽    | number  | —      | —      |
| top                  | 顶边距                     | number  | —      | —      |
| left                 | 左边距                     | number  | —      | —      |
| bottom               | 底边距                     | number  | —      | —      |
| right                | 右边距                     | number  | —      | —      |
| fromPage             | printSomePage 打印开始页数 | string  | —      | —      |
| toPage               | printSomePage 结束页数     | string  | —      | —      |
| PrintControllerStyle | —                          | ''      | —      |
| IsRepeatHeader       | —                          | any     | ''     | —      |
| tableFillModel       | 超出换行, 超出缩小         | number  | 2/3    | —      |
| useDefPrinter        | 是否默认打印机             | boolean | 0/1    | —      |
| bottomfurl           | 置底部分靠近元素下方的区域 | any     | —      | —      |

## addPageNode 添加页面模板

```js
import Print from 'yjpl-print';

Print.addPageNode(pageNode);
```

### addPageNode Attributes

| 参数     | 说明                             | 类型 | 可选值 | 默认值 |
| -------- | -------------------------------- | ---- | ------ | ------ |
| pageNode | 可包含三个值，节点 name,value,id | any  | —      | —      |

## addWatermarkNode 添加带水印的页面模板

```js
import Print from 'yjpl-print';

Print.addWatermarkNode(watermarkNode);
```

### addWatermarkNode Attributes

| 参数          | 说明                             | 类型 | 可选值 | 默认值 |
| ------------- | -------------------------------- | ---- | ------ | ------ |
| watermarkNode | 可包含三个值，节点 name,value,id | any  | —      | —      |

## addRow 添加行

```js
import Print from 'yjpl-print';

Print.addRow(table, option);
```

### addRow Attributes

| 参数   | 说明              | 类型   | 可选值 | 默认值 |
| ------ | ----------------- | ------ | ------ | ------ |
| table  | 表格节点          | node   | —      | —      |
| height | option 配置的高度 | number | —      | —      |

## addRows 添加行明细对象

```js
import Print from 'yjpl-print';

Print.addRows(table, datas);
```

### addRows Attributes

| 参数  | 说明     | 类型 | 可选值 | 默认值 |
| ----- | -------- | ---- | ------ | ------ |
| table | 表格对象 | node | —      | —      |
| datas | 数据包   | JSON | —      | —      |

### datas

| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| ---- | ---- | ---- | ------ | ------ |
| height | 行高 | number | — | — |
| dataFields| 表数据项 | string | — | — |
| cells | 明细数据 | array | — | — |
| cells[n].text | 文本内容 | string | — | — |
| datas.cells[n].dataField | 单元数据项 | string | — | — |
| cells[n].colSpan | 占用列数 | number | — | — |
| cells[n].rowSpan | 占用行数 | number | — | — |
| cells[n].fontSize | 字体大小 | number | — | — |
| cells[n].align | 横对齐 | string | Left/Center/Right | — |
| cells[n].lineAlign | 列对齐 | string | Top/Center/Bottom | — |

## addCell 添加单元格

```js
import Print from 'yjpl-print';

Print.addCell(row, text, option);
//返回单元格节点
```

### addCell Attributes

| 参数   | 说明                                  | 类型          | 可选值 | 默认值 |
| ------ | ------------------------------------- | ------------- | ------ | ------ |
| row    | 行节点                                | node          | —      | —      |
| text   | 内容，如果传的是对象，会赋值给 option | object/string | —      | —      |
| option | 配置属性                              | JSON          | —      | —      |

### option

| 参数      | 说明     | 类型    | 可选值            | 默认值 |
| --------- | -------- | ------- | ----------------- | ------ |
| colSpan   | 占用列数 | number  | —                 | —      |
| rowSpan   | 占用行数 | number  | —                 | —      |
| fontSize  | 字体大小 | number  | —                 | —      |
| align     | 横对齐   | string  | Left/Center/Right | —      |
| B         | 加粗     | Boolean | —                 | —      |
| U         | 下划线   | Boolean | —                 | —      |
| I         | 斜体     | Boolean | —                 | —      |
| dataField | 数据项目 | string  | —                 | —      |

## setCellWidths 设置单元格宽度

```js
import Print from 'yjpl-print';

Print.setCellWidths(table, widths);
```

### setCellWidths Attributes

| 参数   | 说明                | 类型  | 可选值 | 默认值 |
| ------ | ------------------- | ----- | ------ | ------ |
| table  | 表格对象            | node  | —      | —      |
| widths | 设置 table 各列宽度 | array | —      | —      |

## addHTMLTable 添加 HTML 的 TABLE 结构

```js
import Print from 'yjpl-print';

Print.addHTMLTable(params);
Print.addHTMLTable({ table: document.getElementById('testTab'), left: 100, width: '100%' });
```

### addHTMLTable Attributes

| 参数        | 说明         | 类型    | 可选值 | 默认值 |
| ----------- | ------------ | ------- | ------ | ------ |
| table       | 表格对象     | object  | —      | —      |
| top         | 顶点         | number  | —      | —      |
| left        | 左点         | number  | —      | —      |
| id          | id           | string  | —      | —      |
| maxColWidth | 单列大最宽度 | number  | —      | —      |
| hasBorder   | 是否有边线   | boolean | —      | —      |

## cacTableWidth 重新计算表格宽度

```js
import Print from 'yjpl-print';

Print.cacTableWidth(tdWidths, newWidth);
//返回新的tdWidths
```

### cacTableWidth Attributes

| 参数     | 说明               | 类型   | 可选值 | 默认值 |
| -------- | ------------------ | ------ | ------ | ------ |
| tdWidths | 每个单元格宽度集合 | array  | —      | —      |
| newWidth | 表格新的总宽度     | number | —      | —      |

## addArrayTable 按数组打印 table.

```js
import Print from 'yjpl-print';

Print.addArrayTable(dataAry, fontSize, top, left, autoWidth, hasNoBorder, colWidths, aligns, headRows);
```

### addArrayTable Attributes

| 参数        | 说明           | 类型   | 可选值 | 默认值 |
| ----------- | -------------- | ------ | ------ | ------ |
| dataAry     | 二维数据数组   | array  | —      | —      |
| fontSize    | 字号           | number | —      | —      |
| top         | 表格新的总宽度 | number | —      | —      |
| left        | 表格新的总宽度 | number | —      | —      |
| autoWidth   | 表格新的总宽度 | number | —      | —      |
| hasNoBorder | 表格新的总宽度 | number | —      | —      |
| colWidths   | 表格新的总宽度 | number | —      | —      |
| aligns      | 表格新的总宽度 | number | —      | —      |
| headRows    | 表格新的总宽度 | number | —      | —      |

## addTable 添加表格

```js
import Print from 'yjpl-print';

Print.addTable(option); //一般用于表格基本配置
Print.addTable({ top: 0, left: 0, id: 'main1', width: '100%', TdWidths: [162, 464, 201, 246, 214], border: 0 });
```

### addTable Attributes

| 参数 | 说明     | 类型   | 可选值 | 默认值 |
| ---- | -------- | ------ | ------ | ------ |
| top  | 表格顶点 | number | —      | —      |
| left | 表格左点 | number | —      | —      |
| id   | 表格 id  | string | —      | —      |

## addTables 添加整个表格

```js
import Print from 'yjpl-print';

Print.addTables(option);
//表格更详细的配置
```

### addTables Attributes

| 参数                       | 说明       | 类型   | 可选值            | 默认值 |
| -------------------------- | ---------- | ------ | ----------------- | ------ |
| top                        | 表格顶点   | number | —                 | —      |
| left                       | 表格左点   | number | —                 | —      |
| id                         | 表格 id    | string | —                 | —      |
| rows                       | 行数据包   | array  | —                 | —      |
| rows[n].height             | 行高       | number | —                 | —      |
| rows[n].dataFields         | 表数据项   | string | —                 | —      |
| rows[n].cells              | 明细数据   | array  | —                 | —      |
| rows[n].cells[n].text      | 文本内容   | string | —                 | —      |
| rows[n].cells[n].dataField | 单元数据项 | string | —                 | —      |
| rows[n].cells[n].colSpan   | 占用列数   | number | —                 | —      |
| rows[n].cells[n].rowSpan   | 占用行数   | number | —                 | —      |
| rows[n].cells[n].fontSize  | 字体大小   | number | —                 | —      |
| rows[n].cells[n].align     | 横对齐     | string | Left/Center/Right | —      |
| rows[n].cells[n].lineAlign | 列对齐     | string | Top/Center/Bottom | —      |

## setHeadCount/getHeadCount 配置/获取表头行数

```js
import Print from 'yjpl-print';

Print.setHeadCount(table, rowIndexs, count);
Print.getHeadCount(tabNode);
//返回count
```

### setHeadCount/getHeadCount Attributes

| 参数      | 说明             | 类型   | 可选值 | 默认值 |
| --------- | ---------------- | ------ | ------ | ------ |
| table     | table            | node   | —      | —      |
| rowIndexs | 表头行数下标集合 | string | —      | —      |
| count     | 表头行数         | number | —      | —      |
| tabNode   | table            | node   | —      | —      |

## setFixColumn 配置冻结列数量

```js
import Print from 'yjpl-print';

Print.setFixColumn(table, count);
```

### setFixColumnt Attributes

| 参数  | 说明       | 类型   | 可选值 | 默认值 |
| ----- | ---------- | ------ | ------ | ------ |
| table | table      | node   | —      | —      |
| count | 冻结列数量 | number | —      | —      |

## getTableById 获得 tabNode

```js
import Print from 'yjpl-print';

Print.getTableById(id);
//返回tabNode
```

### getTableById Attributes

| 参数 | 说明 | 类型   | 可选值 | 默认值 |
| ---- | ---- | ------ | ------ | ------ |
| id   | id   | string | —      | —      |

## getTextById 获得文本节点

```js
import Print from 'yjpl-print';

Print.getTextById(id);
//返回text节点
```

### getTextById Attributes

| 参数 | 说明 | 类型   | 可选值 | 默认值 |
| ---- | ---- | ------ | ------ | ------ |
| id   | id   | string | —      | —      |

## 节点 top/left 属性获取与配置

```js
import Print from 'yjpl-print';

Print.setTop(node, top); //设置top值
Print.getTop(node); //获取top值
Print.setLeft(node, left); //设置left值
Print.getLeft(node); //获取top值
```

### Attributes

| 参数 | 说明 | 类型   | 可选值 | 默认值 |
| ---- | ---- | ------ | ------ | ------ |
| node | node | node   | —      | —      |
| top  | 顶   | number | —      | —      |
| left | 左   | number | —      | —      |

## DataField 数据项目的配置使用

```js
import Print from 'yjpl-print';

Print.setDataField(obj, value, dfNode); //配置数据项目dataField值
Print.setDataFields(obj, value); //给表(行)配置数据项目dataField值值
Print.getDataFields(); //获得所有表数据项目，返回值为数组
Print.getDataField(dataField); //获得对应dataField的数据项目
obj.Print.updateDataField(dataField, value); //把value值更新到对应的dataField的值上
Print.updateDataFields(dataFields, dataJson, pnode); //把一行的数据更新到设置了行数据项目(dataFields)的明细上
Print.appendDataFields(dataFields); //添加一行行数据项目node
Print.hasDataFields(dataFields); //判断是否有设置了dataFields的行数据项目
```

### Attributes

| 参数       | 说明                        | 类型   | 可选值 | 默认值 |
| ---------- | --------------------------- | ------ | ------ | ------ |
| obj        | setDataField 配置的单个节点 | node   | —      | —      |
| value      | setDataField 配置的值       | string | —      | —      |
| dfNode     | dataField 数据节点，可不传  | node   | —      | —      |
| obj        | setDataField 配置的节点     | node   | —      | —      |
| value      | dataFields 值               | string | —      | —      |
| dataField  | dataField 值                | string | —      | —      |
| value      | updateDataField 更新的值    | string | —      | —      |
| dataFields | 给行的 dataFields 值        | string | —      | —      |
| dataJson   | 新的一行数据                | JSON   | —      | —      |
| pnode      | —                           | node   | —      | —      |

## 配置打印机/获得打印机

```js
import Print from 'yjpl-print';

Print.setPrinter(value);
Print.getPrinter(); //value
Print.getDefPrinter(); //获得默认打印机
Print.getSysPrinter(printType, callBack); //获得所有打印机列表
```

### Attributes

| 参数      | 说明                                                               | 类型            | 可选值           | 默认值 |
| --------- | ------------------------------------------------------------------ | --------------- | ---------------- | ------ |
| value     | 打印机 name 属性值                                                 | string          | —                | —      |
| printType | 打印类型，如果是一个 function,赋值给 callback,打印类型默认为 Print | string/function | Print/Preview/'' | —      |
| callBack  | 返回打印列表                                                       | array           | —                | —      |

## setPrintAllTable/getPrintAllTable 设置/获取是否打印所有表格

```js
import Print from 'yjpl-print';

Print.setPrintAllTable(value);
Print.getPrintAllTable(); //true/false
```

### setPrintAllTable Attributes

| 参数  | 说明             | 类型    | 可选值 | 默认值 |
| ----- | ---------------- | ------- | ------ | ------ |
| value | 是否打印所有表格 | boolean | —      | —      |

## groupSort 分组排序

```js
import Print from 'yjpl-print';

Print.groupSort(datas, fieldName, sumdf);
//返回{ 'datas': array, 'sums': array }
```

### groupSort Attributes

| 参数      | 说明           | 类型   | 可选值 | 默认值 |
| --------- | -------------- | ------ | ------ | ------ |
| datas     | 所有的数据明细 | array  | —      | —      |
| fieldName | 分组用的字段   | string | —      | —      |
| sumdf     | 合计列的字段   | array  | —      | —      |

## 打印 url 路径

```js
import Print from 'yjpl-print';

Print.getContextPath(); //返回context路径（天擎路径结构）
Print.getServletUrl(servicePath); //服务端打印接口
```

### getServletUrl Attributes

| 参数        | 说明       | 类型   | 可选值 | 默认值 |
| ----------- | ---------- | ------ | ------ | ------ |
| servicePath | vipAddress | string | —      | —      |

## mergeGridData 生成合并数据

```js
import Print from 'yjpl-print';

Print.mergeGridData(formatXml, items, sums, dataField, sumDataField);
```

### mergeGridData Attributes

| 参数         | 说明           | 类型       | 可选值 | 默认值 |
| ------------ | -------------- | ---------- | ------ | ------ |
| formatXml    |                | xml 字符串 | —      | —      |
| items        | 数据包         | array      | —      | —      |
| sums         | 合计明细       | array      |        | —      |
| dataField    | dataField 字段 | array      | —      | —      |
| sumDataField | 合计字段       | array      | —      | —      |

## getPrintXmlData 获得打印 XML 数据

```js
import Print from 'yjpl-print';

Print.getPrintXmlData(formatXml, datas, callBack); //模板 + 数据包合并
```

### getPrintXmlData Attributes

| 参数      | 说明       | 类型     | 可选值 | 默认值 |
| --------- | ---------- | -------- | ------ | ------ |
| formatXml | xml 字符串 | string   | —      | —      |
| datas     | 数据       | object   | —      | —      |
| callBack  | —          | function | —      | —      |

## pdf 打印或预览

```js
import Print from 'yjpl-print';

Print.hasPdfPlugins(); //浏览器是否有PDF内置插件
//true/false
Print.previewToNvPdf(params); //预览到浏览器PDF

Print.printToFoxiPdf(printType, params, printer); //打印到福昕的pdf插件。
```

### Attributes

| 参数      | 说明   | 类型   | 可选值 | 默认值 |
| --------- | ------ | ------ | ------ | ------ |
| params    | params | JSON   | —      | —      |
| printType | —      | string | —      | —      |
| printer   | 打印机 | JSON   | String | —      |

## initData 初始化数据包

```js
import Print from 'yjpl-print';

Print.initData(params, callBack);
```

### initData Attributes

| 参数     | 说明 | 类型     | 可选值 | 默认值 |
| -------- | ---- | -------- | ------ | ------ |
| params   | —    | JSON     | —      | —      |
| callBack | —    | function | —      | —      |

## printData 执行打印数据(入口)

```js
import Print from 'yjpl-print';

Print.printData(printType, params, printer, callBack);
```

### printData Attributes

| 参数      | 说明       | 类型     | 可选值        | 默认值 |
| --------- | ---------- | -------- | ------------- | ------ |
| printType | 打印方式   | string   | Preview/Print | —      |
| params    | 全局的参数 | JSON     | —             | —      |
| printer   | printer    | JSON     | String        | —      |
| callBack  | 回调函数   | function | —             | —      |

## afterPrint 打印后的事件

```js
import Print from 'yjpl-print';

Print.afterPrint(params, prms, printState, callBack);
```

### afterPrint Attributes

| 参数       | 说明         | 类型     | 可选值 | 默认值 |
| ---------- | ------------ | -------- | ------ | ------ |
| params     | 全局的参数   | JSON     | —      | —      |
| prms       | 整理后的参数 | JSON     | —      | —      |
| printState | 打印是否成功 | boolean  | —      | —      |
| callBack   | 回调函数     | function | —      | —      |

## print 打印单据配置通用接口

```js
import printEcpBean from 'yjpl-print';

printEcpBean.print(printType, option, callBack);

//默认调用
obj.print("Preview", {gridid:"gridid", data: ''}));
//指定表格调用
obj.print("Print", {gridid:"gridid", data: ''}));



```

### print Attributes

| 参数      | 说明     | 类型     | 可选值        | 默认值 |
| --------- | -------- | -------- | ------------- | ------ |
| printType | 打印类型 | string   | Print/Preview | —      |
| option    | 配置属性 | object   | —             | —      |
| callBack  | callBack | function | —             | —      |

### option

| 参数                     | 说明                                                                          | 类型    | 可选值    | 默认值 |
| ------------------------ | ----------------------------------------------------------------------------- | ------- | --------- | ------ |
| control                  | 页面控制器                                                                    | object  | —         | —      |
| classid                  | 大类 ID                                                                       | string  | —         | —      |
| typeid                   | 小类 ID                                                                       | string  | —         | —      |
| formatId                 | 指定格式 id                                                                   | string  | —         | —      |
| gid                      | 指定打印表格的 id                                                             | string  | —         | —      |
| yhdm                     | 用户代码                                                                      | string  | —         | —      |
| unitCode                 | 单位代码                                                                      | string  | —         | —      |
| level                    | 是否横向                                                                      | boolean | —         | —      |
| xmlStr                   | 打印模板                                                                      | string  | —         | —      |
| data                     | 打印数据包                                                                    | string  | —         | —      |
| reload                   | 重加载模板                                                                    | boolean | —         | —      |
| printerOption            | 打印机属性                                                                    | object  | —         | —      |
| printerOption.dialog     | 是否显示打印机设置面板                                                        | boolean | —         | —      |
| printerOption.autoRotate | 是否旋转                                                                      | boolean | —         | —      |
| printerOption.autoCenter | 是否居中                                                                      | boolean | —         | —      |
| findServerData           | 获取服务端数据接口，是个函数                                                  | string  | —         | —      |
| isPageRange              | 是否需要显示页面范围                                                          | boolean | —         | —      |
| noSpwinShow              | 是否弹出选择打印方案窗口 （批量打印时会弹出多次选择方案窗口） true 表示不弹出 | boolean | —         | —      |
| scale                    | 缩放                                                                          | number  | 100/90/80 | —      |
| range                    | 打印当前窗口数据                                                              | string  | current   | —      |
| xmlUrl                   | 打印方案路径                                                                  | string  | —         | —      |

## 模板打印

把列表和单屏模板的打印数据传到服务端，将生成 pdf 再返回给客户端打开，并用 pdfview 打开进行打印。

### 一、列表模板

#### 引入打印组件

```js
import { printTemplate } from 'yjpl-print';
```

#### 打印方法

打印方法要传入三个参数 pageModel、metaModel、dataModel

```js
printTemplate(pageModel, metaModel, dataModel);
```

#### 在 qzz 表格按钮中调用打印方法

```js
{
  btns: [
    {
      icon: 'el-icon-printer',
      text: '打印',
      callback: () => this.pt.printTemplate(this.printPageModel, this.metaModel, this.dataModel)
    }
  ]
}
```

#### 模板

##### 单列表模板

模板页面  
在 mounted 中定义子表

```html
<template>
  <page extends="yjpl-template/business/list-template.yjpl"> </page>
</template>

<script>
  import QueryTemplate from 'yjpl-template/business/list-template.yjpl';
  import PrintTemBs from './printOneListTemplateDemobs';
  export default class QueryPage extends QueryTemplate {
    constructor() {
      super();
      this.bs = new PrintTemBs();
    }
    data() {
      return this.bs.getData();
    }
    mounted() {
      if (this.isTabs) {
        this.metaModel.test1 = this.$refs.test1[0].ui;
        this.metaModel.test2 = this.$refs.test2[0].ui;
        this.metaModel.isTabs = this.isTabs;
      } else {
        this.metaModel.gridui = this.$refs.grid.ui;
        this.metaModel.isTabs = this.isTabs;
      }
    }
  }
</script>
```

主表、子表模型 pageModel

```js
this.printPageModel = [
  {
    children: [
      {
        title: '时间',
        name: 'query',
        type: 'main'
      }
    ]
  },
  {
    children: [
      {
        name: 'gridui'
      }
    ]
  }
];
```

主表  
title 主表标题  
name 主表名称  
type 类型，判断是否为主表

```js
{
  children: [
    {
      title: '时间',
      name: 'query',
      type: 'main'
    }
  ]
}
```

子表  
title 子表标题  
name 子表名称

```js
{
  children: [
    {
      title: 'table',
      name: 'gridui'
    }
  ]
}
```

metaModel

```js
this.metaModel = {
  // 树
  tree: false,
  // 查询表单
  query: [
    {
      name: 'start',
      dataType: 'date',
      label: '开始时间',
      required: true
    },
    {
      name: 'end',
      dataType: 'date',
      label: '结束时间',
      required: true
    }
  ],
  // 表格配置
  grid: {
    pager: true,
    colNames: ['费用', '出发地', '到达地'],
    Align: 'alClient',
    autoEcpWidth: true,
    colModels: [
      {
        editType: 'number',
        caption: '费用',
        name: 'fy'
      },
      {
        caption: '出发地',
        name: 'cfd',
        showInList: true
      },
      {
        caption: '到达地',
        name: 'ddd',
        showInList: true
      }
    ]
  },
  // 工具栏按钮
  btns: [
    'add',
    'insert',
    'delete',
    'moveUp',
    'moveDown',
    'save',
    {
      icon: 'el-icon-printer',
      text: '打印',
      callback: () => this.pt.printTemplate(this.printPageModel, this.metaModel, this.dataModel)
    }
  ]
};
```

dataModel  
需要配置打印类型 printType，打印标题 title，主表单元格名称

```js
this.dataModel = {
  grid: [
    { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '广州', jtgj: '高铁' },
    { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '上海', jtgj: '的士' }
  ],
  tree: [
    {
      label: '全部',
      ddd: '',
      children: [
        { label: '广州', ddd: '广州' },
        { label: '上海', ddd: '上海' }
      ]
    }
  ],
  printType: 'Preview',
  title: '查询样例',
  start: '',
  end: '',
  query: {
    start: '2021-01-01',
    end: '2021-12-31'
  }
};
```

##### 页签列表模板

模板页面  
在 mounted 中定义子表

```html
<template>
  <page extends="yjpl-template/business/list-template.yjpl"></page>
</template>

<script>
  import QueryTemplate from 'yjpl-template/business/list-template.yjpl';
  import PrintTemBs from './printTabListTemplateDemobs';
  export default class QueryPage extends QueryTemplate {
    constructor() {
      super();
      this.bs = new PrintTemBs();
    }
    data() {
      return this.bs.getData();
    }
    mounted() {
      if (this.isTabs) {
        this.metaModel.test1 = this.$refs.test1[0].ui;
        this.metaModel.test2 = this.$refs.test2[0].ui;
        this.metaModel.isTabs = this.isTabs;
      } else {
        this.metaModel.gridui = this.$refs.grid.ui;
        this.metaModel.isTabs = this.isTabs;
      }
    }
  }
</script>
```

主表、子表模型 pageModel

```js
this.printPageModel = [
  {
    children: [
      {
        title: '时间',
        name: 'query',
        type: 'main'
      }
    ]
  },
  {
    children: [
      {
        title: 'tab1',
        name: 'test1'
      },
      {
        title: 'tab2',
        name: 'test2'
      }
    ]
  }
];
```

主表  
title 主表标题  
name 主表名称  
type 类型，判断是否为主表

```js
children: [
  {
    title: '时间',
    name: 'query',
    type: 'main'
  }
];
```

子表  
title 子表标题  
name 子表名称

```js
{
  children: [
    {
      title: 'tab1',
      name: 'test1'
    },
    {
      title: 'tab2',
      name: 'test2'
    }
  ];
}
```

metaModel

```js
this.metaModel = {
  // 树
  tree: false,
  // 查询表单
  query: [
    {
      name: 'start',
      dataType: 'date',
      label: '开始时间',
      required: true
    },
    {
      name: 'end',
      dataType: 'date',
      label: '结束时间',
      required: true
    }
  ],
  // 表格配置
  grid: [
    {
      // 表格标题
      title: '表格样例',
      // 对应的dataModel内数据，和 ref
      name: 'test1',
      // tab名称
      label: 'tab1',
      // 工具栏按钮
      btns: [
        'add',
        'insert',
        'delete',
        'moveUp',
        'moveDown',
        'save',
        {
          icon: 'el-icon-printer',
          text: '打印',
          callback: () => this.pt.printTemplate(this.printPageModel, this.metaModel, this.dataModel)
        }
      ],
      // 表格数据
      option: {
        height: 300,
        pager: true,
        colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
        Align: 'alClient',
        autoEcpWidth: true,
        colModels: [
          {
            editType: 'number',
            caption: '费用',
            name: 'fy'
          },
          {
            caption: '出发地',
            name: 'cfd',
            showInList: true
          },
          {
            caption: '到达地',
            name: 'ddd',
            showInList: true
          },
          {
            caption: '交通工具',
            name: 'jtgj',
            showInList: true
          },
          {
            editType: 'date',
            caption: '日期',
            name: 'rq'
          }
        ]
      }
    },
    {
      // 表格标题
      title: '表格样例',
      // 对应的dataModel内数据，和 ref
      name: 'test2',
      // tab名称
      label: 'tab2',
      // 工具栏按钮
      btns: [
        'add',
        'insert',
        'delete',
        'moveUp',
        'moveDown',
        'save',
        {
          icon: 'el-icon-printer',
          text: '打印',
          callback: () => this.pt.printTemplate(this.printPageModel, this.metaModel, this.dataModel)
        }
      ],
      // 表格数据
      option: {
        height: 300,
        pager: true,
        colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
        Align: 'alClient',
        autoEcpWidth: true,
        colModels: [
          {
            editType: 'number',
            caption: '费用',
            name: 'fy'
          },
          {
            caption: '出发地',
            name: 'cfd',
            showInList: true
          },
          {
            caption: '到达地',
            name: 'ddd',
            showInList: true
          },
          {
            editType: 'string',
            caption: '交通工具',
            name: 'jtgj',
            showInList: true
          },
          {
            editType: 'date',
            caption: '日期',
            name: 'rq'
          }
        ]
      }
    }
  ],
  // 工具栏按钮
  btns: [
    'add',
    'insert',
    'delete',
    'moveUp',
    'moveDown',
    'save',
    {
      icon: 'el-icon-printer',
      text: '打印',
      callback: () => this.pt.printTemplate(this.printPageModel, this.metaModel, this.dataModel)
    }
  ]
};
```

dataModel  
需要配置打印类型 printType，打印标题 title，主表单元格名称

```js
this.dataModel = {
  grid: {
    test1: [
      { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '广州', jtgj: '高铁' },
      { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '上海', jtgj: '的士' }
    ],
    test2: [
      { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '广州', jtgj: '高铁' },
      { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '上海', jtgj: '的士' }
    ]
  },
  tree: [
    {
      label: '全部',
      ddd: '',
      children: [
        { label: '广州', ddd: '广州' },
        { label: '上海', ddd: '上海' }
      ]
    }
  ],
  printType: 'Preview',
  title: '查询样例',
  start: '',
  end: '',
  query: {
    start: '2021-01-01',
    end: '2021-12-31'
  }
};
```

#### 单屏模板

模板页面  
在 mounted 中定义子表

```html
<template>
  <page extends="yjpl-template/business/single-template.yjpl">
    <plugins type="append" target=".bill-title">
      <yj-button-list :data="metaModel.titleBtnList"></yj-button-list>
    </plugins>

    <plugins type="append" target=".bill-form">
      <yj-container v-model="dataModel" :data="metaModel.form1" label-suffix="：" :col="4"></yj-container>
      <h3>探亲资格时间</h3>
      <yj-container v-model="dataModel" :data="metaModel.form2" label-suffix="：" :col="4"></yj-container>
    </plugins>

    <plugins type="append" target=".bill-info">
      <tabs type="card">
        <tab-pane label="费用明细">
          <yj-container-card ref="fymxGrid" :option="metaModel.grid" :data="dataModel.grid" height="300px">
            <yj-button-list slot="button" :data="metaModel.gridBtnList" @edit="handleEdit"></yj-button-list>
          </yj-container-card>
        </tab-pane>
        <tl-tab-pane label="借款核销">
          <yj-container-card ref="jkhxGrid" :option="metaModel.grid" :data="dataModel.grid" height="300px">
            <yj-button-list slot="button" :data="metaModel.gridBtnList"></yj-button-list>
          </yj-container-card>
        </tl-tab-pane>
        <tl-tab-pane label="发票明细">
          <yj-container-card ref="fpmxGrid" :option="metaModel.grid" :data="dataModel.grid" height="300px">
            <yj-button-list slot="button" :data="metaModel.gridBtnList"></yj-button-list>
          </yj-container-card>
        </tl-tab-pane>
        <tl-tab-pane label="费用分摊表">
          <yj-container-card ref="fyftbGrid" :option="metaModel.grid" :data="dataModel.grid" height="300px">
            <yj-button-list slot="button" :data="metaModel.gridBtnList"></yj-button-list>
          </yj-container-card>
        </tl-tab-pane>
      </tabs>
    </plugins>

    <plugins type="append" target=".bill-upload">
      <upload-panel></upload-panel>
    </plugins>

    <plugins type="append" target=".bill-footer">
      <yj-button-list :data="metaModel.footerBillBtnList"></yj-button-list>
    </plugins>

    <plugins type="remove" target=".bill-aside"></plugins>
    <plugins type="remove" target=".bill-user"></plugins>
    <plugins type="remove" target=".bill-leader"></plugins>
    <plugins type="remove" target=".bill-workflow"></plugins>
  </page>
</template>
<script>
  mounted() {
    this.metaModel.fymxGrid = this.$refs.fymxGrid.ui;
    this.metaModel.jkhxGrid = this.$refs.jkhxGrid.ui;
    this.metaModel.fpmxGrid = this.$refs.fpmxGrid.ui;
    this.metaModel.fyftbGrid = this.$refs.fyftbGrid.ui;
  }
</script>
```

主表、子表模型 pageModel

```js
this.printPageModel = [
  {
    children: [
      {
        title: '单据信息',
        name: 'form1',
        type: 'main'
      },
      {
        title: '探亲资格时间',
        name: 'form2',
        type: 'main'
      }
    ]
  },
  {
    title: '费用信息',
    children: [
      {
        title: '费用明细',
        name: 'fymxGrid'
      },
      {
        title: '借款核销',
        name: 'jkhxGrid'
      },
      {
        title: '发票明细',
        name: 'fpmxGrid'
      },
      {
        title: '费用分摊表',
        name: 'fyftbGrid'
      }
    ]
  }
];
```

主表  
title 主表标题  
name 主表名称  
type 类型，判断是否为主表

```js
{
  children: [
    {
      title: '单据信息',
      name: 'form1',
      type: 'main'
    },
    {
      title: '探亲资格时间',
      name: 'form2',
      type: 'main'
    }
  ];
}
```

子表  
title 子表标题  
name 子表名称  
子表外层 title 是这个子表的标题

```js
{
  'title': '费用信息',
  'children': [
    {
      'title': '费用明细',
      'name': 'fymxGrid'
    },
    {
      'title': '借款核销',
      'name': 'jkhxGrid'
    },
    {
      'title': '发票明细',
      'name': 'fpmxGrid'
    },
    {
      'title': '费用分摊表',
      'name': 'fyftbGrid'
    }
  ]
}
```

metaModel

```js
this.metaModel = {
  titleBtnList: [
    {
      label: '复制'
    },
    {
      label: '撤销'
    },
    {
      label: '导入'
    },
    {
      label: '导出'
    },
    {
      label: '打印',
      text: '打印',
      callback: () => this.pt.printTemplate(this.printPageModel, this.metaModel, this.dataModel)
    }
  ],
  gridBtnList: [
    {
      label: '增加',
      icon: 'el-icon-circle-plus-outline'
    },
    {
      label: '编辑',
      icon: 'el-icon-edit'
    },
    {
      label: '删除',
      icon: 'el-icon-remove-outline'
    }
  ],
  footerBtnList: [
    {
      label: '保存'
    },
    {
      label: '提交',
      type: 'primary'
    }
  ],
  form1: [
    {
      name: 'start',
      dataType: 'date',
      label: '开始时间',
      required: true
    },
    {
      name: 'end',
      dataType: 'date',
      label: '结束时间',
      required: true
    },
    {
      name: 'string',
      dataType: 'string',
      label: '报销事项'
    },
    {
      name: 'number',
      dataType: 'number',
      label: '原始票据粘贴单张数'
    },
    {
      name: 'textarea',
      dataType: 'textarea',
      label: '出差事由',
      resize: 'vertical',
      col: 4
    }
  ],
  form2: [
    {
      name: 'tqstart',
      dataType: 'date',
      label: '开始时间',
      required: true
    },
    {
      name: 'tqend',
      dataType: 'date',
      label: '结束时间',
      required: true
    }
  ],
  grid: {
    height: 300,
    pager: true,
    cellEdit: false,
    colNames: ['费用', '出发地', '到达地', '交通工具', '日期'],
    Align: 'alClient',
    autoEcpWidth: true,
    shrinkToFit: true,
    colModels: [
      {
        editType: 'number',
        caption: '费用',
        name: 'fy'
      },
      {
        caption: '出发地',
        name: 'cfd'
      },
      {
        caption: '到达地',
        name: 'ddd'
      },
      {
        caption: '交通工具',
        name: 'jtgj'
      },
      {
        editType: 'date',
        caption: '日期',
        name: 'rq'
      }
    ]
  }
};
```

dataModel  
需要配置打印类型 printType，打印标题 title，主表单元格名称

```js
this.dataModel = {
  grid: [
    { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '广州', jtgj: '高铁' },
    { fy: 100, rq: '2021-01-01', cfd: '珠海', ddd: '上海', jtgj: '的士' }
  ],
  printType: 'Preview',
  title: '差旅费报销单',
  start: '',
  end: '',
  string: '',
  number: '',
  textarea: '',
  tqstart: '',
  tqend: ''
};
```
