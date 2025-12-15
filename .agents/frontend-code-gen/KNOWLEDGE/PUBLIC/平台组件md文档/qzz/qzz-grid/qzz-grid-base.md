### QzzGrid - 编辑表格

:::demo

```html
<template>
  <div style="height: 300px">
    <qzz-grid :option="gridOption" v-model="gridData" ref="grid"></qzz-grid>
  </div>
</template>

<script>
  export default {
    name: 'Grid1',
    data() {
      return {
        gridOption: {
          Align: 'alClient',
          treeId: 'TJBM',
          height: 300,
          multiselect: true,
          footerrow: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '停用', '默认'],
          colModels: [
            { name: 'dxmc' },
            { name: 'TJBM' },
            { name: 'KSRQ', editType: 'date', validate: 'notempty', validateMessage: '开始日期不能为空。' },
            { name: 'JSRQ', editType: 'date', showTime: true, width: 150 },
            {
              name: 'DKLY',
              editType: 'comboBox',
              data: [
                { id: 'red', text: '红' },
                { id: 'green', text: '绿' },
                { id: 'blue', text: '蓝' },
                { id: 'yellow', text: '黄' }
              ]
            },
            { name: 'JE', editType: 'number', scale: 2, dataType: 'number', sum: true },
            { name: 'CONTEXT', length: 100, editType: 'buttonEdit' },
            { name: 'STOP', editType: 'checkBox', align: 'center' },
            { name: 'DEFAULT', editType: 'radio', align: 'center' }
          ]
        },
        gridData: [
          { dxmc: '明细1' },
          { dxmc: '明细2' },
          { dxmc: '明细3', DKLY: 'red' },
          { dxmc: '明细3', DKLY: 'yellow' }
        ]
      };
    },
    mounted() {
      const grid = this.$refs.grid.ui;
      grid.bind('onCanEditing', function (ds, fn, state) {
        if (ds.getValue('STOP') === true) {
          return false;
        } else {
          return state;
        }
      });
      grid.bind('onCheckChanged', function (cNode, checkState, fn) {
        grid.refresh(true);
      });
    }
  };
</script>
```

:::

### Attributes

列属性
| 参数      | 说明       | 类型    | 描述                               | 缺省值 | 备选值 | 是否常用 | 是否可选择 |
| --------- | --------- | ------- | ---------------------------------- | ----- | ------ | ------- | --------- |
| ng-model | 数据源     | String  | ngModel                            | 空     | -      | 0       | 可选      |
| align | 列对齐 | Array | 列对齐属性,left,right,center | left  | left:居左对齐,right:居右对齐,center:居中对齐 | 0 | 可选 |
| color    | 字体颜色   | Color   | 树节点，单元格字体颜色               | 空     | -      | 0       | 可选      |
| backgroundColor | 背景颜色 | Color   | 树节点，单元格背景颜色          | 空     | -      | 0       | 可选      |
| selectAll | 显示全选    | Boolean | 列标上显示全选框，                | false  | -     | 0       | 可选       |
| multiSelect | 多选状态 | Boolean | 列多选,比如下拉选择，选多个值       | false  | -     | 1       | 可选       |
| link      | 链接列      | Boolean | 是否链接字段。必须是只读列才起作用。| false  | -     | 0       | 可选       |
| maxDate   | 最大日期    | Date    | 最大日期                          | 空    | -     | 0       | 可选       |
| name      | 字段名称    | String  | 字段名称                          | null  | -     | 0       | 可选       |
| showTime  | 显示时间    | Boolean | 日期列是否显示到时分秒             | false| -     | 0       | 可选       |
| hidden    | 列隐藏      | Boolean | 列是否隐藏                        | false | -     | 0       | 可选       |
| numUnit   | 显示单位    | Array   | 数值列的显示单位，如万元为单位。    | 1     | -     | 0.001:千分之几,0.01:百分之几,10:十为单位,100:百为单位,1000:千为单位,10000:万为单位 | 1 | 可选       |
| required  | 是否必填     | Boolean | 是否必填                          | false | -     | 1       | 可选       |
| disInput  | 编辑禁止输入 | Boolean | 下拉框、弹出框不能编辑输入，只能选择 | false | -     | 1       | 可选      |
| defaultValue | 默认显示 | Number  | 默认值，当值为空时显示默认值        | ''    | -     | 0       | 可选       |
| viewType  | 下拉图片    | Boolean | 下拉时显示图片                     | false | -     | 0       | 可选       |
| data | 下拉数据 | Array | 列下拉选择数据列表&nbsp;[{&apos;id&apos;:&apos;1&apos;,&apos;text&apos;:&apos;txt&apos;}] | 空 | -     | 0       | 可选       |
| dataType  | 数据类型   | Array   | 数据类型,string,number，date,enum等 | 空    | string:文本,number:数值,boolean:布尔类型,int:整型 | 1    | 可选     |
| width | 列宽 | Number | 列宽度                                       | 空     | -     | 0       | 可选       |
| frozen    | 冻结列      | Boolean | 冻结列配置，必须在最前面才起作用。  | false | -     | 0       | 可选       |
| contentFit | 内容自适应 | Boolean | 内容宽度自适应                     | false | -     | 1       | 可选       |
| defWidth  | 默认宽度    | Number  | 配置默认宽度                      | 空    | -     | 0       | 可选       |
| search    | 下拉时显示过滤 | Boolean | 下拉时显示查找框                | false | -     | 1       | 可选       |
| minValue  | 数值最小值  | Number | 数值时最小值                       | 空    | -     | 0       | 可选       |
| length    | 输入长度    | Number  | 长度                             | 15     | -     | 0       | 可选      |
| titleFit  | 标题自适应   | Boolean | 自适应标题的宽度。                | false | -     | 0       | 可选       |
| ellipsis | 超出省略 | Boolean | 内容超长时是否显示省略号                | true | -     | 0       | 可选       |
| hideZero  | 隐藏0值     | Boolean | 隐藏0值                          | false| -     | 0       | 可选       |
| editType | 编辑类型 | Array | 编辑方式comboBox:下拉编辑,date:日期编辑,buttonEdit:弹出编辑,number:数值编辑,text:文本编辑，checkBox:为复选择列 | text | comboBox:下拉选择,date:日期编辑,buttonEdit:弹出编辑,number:数值编辑,text:文本 | 1       | 可选       |
| showHint  | 显示提示    | Boolean | 是否显示内容提示                  | false | -     | 0       | 可选       |
| levelBorderOnly | 隐藏竖线 | Boolean | 只显示横线                     | 空    | -     | 0       | 可选       |
| groupsEmpty | 合并空单元格 | Boolean | 是否合并空单元格。              | false | -     | 0       | 可选       |
| transKey  | 名称转换字段 | String | 名称转换对应的字段                 | 空    | -     | 0       | 可选       |
| minWidth  | 最小列宽    | Number  | 最小列宽度                        | 空    | -     | 0       | 可选       |
| capkey    | 名称字段    | String  | 名称转换字段                      | 空    | -     | 0       | 可选       |
| scale     | 小数精度    | Number  | 数据精度,即小数位数               | 0     | -     | 0       | 可选       |
| editable  | 是否编辑    | Boolean | 列是否可以编辑                    | true  | -     | 1       | 可选       |
| post      | 提交数据    | Boolean | 下拉控件选择后马上提交             | false | -     | 1       | 可选       |
| more      | 下拉时显示更多 | Boolean | 下拉时显示更多                  | false | -     | 1       | 可选       |
| filter    | 列是否过滤  | Boolean | 是否可以过滤，不配置默认为可以过滤  | true | -      | 1      | 可选       |
| minDate   | 最小日期    | Date    | 最小日期                          | 空   | -     | 1       | 可选       |
| validate | 内容效验 | String | 列输入内容效验，比如email,phone等。        | 空 | -     | 0       | 可选       |
| format    | 格式化值    | Boolean | 是否格式化，默认格式化             | false | -     | 0       | 可选       |
| dataScale | 数值精度    | Number  | 数据精度                          | 0    | -     | 0       | 可选       |
| maxValue  | 数值最大值  | Number  | 数值时最大值                       | null | -     | 0       | 可选       |
| groups    | 合并单元格  | Boolean | 当前列是否合并单元格               | false | -     | 0       | 可选       |
| isCheck | 选择状态值 | Boolean | 当editType为checkBox时，处于选择状态的值 | true | -   | 0       | 可选       |
| lockWidth | 锁定宽度 | Boolean | 锁定宽度                             | false | -   | 0       | 可选       |
| sum       | 列合计      | Boolean | 顶层是否合计                      | false| -     | 0       | 可选       |
| cyctype | 日期周期 | Array | 日期显示到月配置值month，支持"year"、"month"、"day"等 | 空 | year:年,season:季,month:月,day:日,hour:时,minute:分,second:秒 | 0  | 可选 |
| id       | 控件id     | String  | 控件id                             | 空    | -     | 0       | 可选       |
| unCheck   | 取消选择    | Boolean | 当editType为checkBox时，处于不选择状态的值 | false | -     | 0       | 可选       |


表属性
| 参数      | 说明       | 类型    | 描述                               | 缺省值 | 备选值 | 是否常用 | 是否可选择 |
| --------- | --------- | ------- | ---------------------------------- | ----- | ----- | -------- | --------- |
| width     | 宽度       | int     | 表格宽度                           | 空    | -     | 1        | 必选      |
| rownumbers  | 显示序号 | boolean | 显示序号列                         | true  | -     | 1        | 可选      |
| pager     | 显示分页   | boolean | 显示服务端分页面板                  | false | -     | 0        | 可选      |
| pageSize  | 每页条数   | Number  | 分页状态下每页显示多少条数据         | 10    | -     | 1        | 可选      |
| total     | 显示合计   | boolean | 显示合计行                         | false | -     | 1        | 可选      |
| subtotal  | 显示小计   | boolean | 显示合计的小计行                    | false | -     | 1        | 可选      |
| sort      | 显示排序   | boolean | 显示排序功能                       | true  | -     | 1        | 可选      |
| filter    | 支持过滤   | boolean | 显示排序及筛选框                    | true  | -     | 1        | 可选      |
| suitToFit | 表宽自适应 | boolean | 列宽适应表宽                        | false | -     | 0        | 可选      |
| multiSelect | 复选标志 | boolean | 是否复选择                         | false | -     | 1        | 可选      |
| multiFirst | 首列复选列 | boolean | 复选择框列显示在第一列              | true  | -     | 1        | 可选      |
| virtual   | 懒加载     | boolean | 表格懒加载属性                      | false | -     | 1        | 可选      |
| treeGrid  | 是否树表   | boolean | 表格是否树表                        | false | -     | 1        | 可选      |
| colDesign | 显示列设置 | boolean | 显示列设置                          | true  | -     | 1        | 可选      |
| isExpanded| 默认展开   | boolean | 树节点，是否默认展开                | true  | -     | 0        | 可选      |
| colModels | 列属性列表 | Array   | 表格的表头列数据模型                 | null  | -     | 1        | 必选      |
| height    | 高度       | int     | 表格高度                           | 空    | -     | 1        | 必选      |
| checkFlag | 自动选择   | Array   | 自动选择上/下级标志                 | 空    | p:自动选择上级,s:自动选择下级,b:自动选择同级,ps:自动选择上下级,sb:自动选择同、下级,psb:自动选择上、下及同级 | 0        | 可选      |
| initSort  | 初始化排序 | boolean | 初始化时对数据进行排序               | false | -     | 0        | 可选      |
| unCheckFlag| 自动取消  | Array   | 自动取消选择上/下级标志           | 空 | p:自动取消选择上级,s:自动取消选择下级,b:自动取消选择同级,ps:自动取消选择上下级,sb:自动取消选择同、下级,psb:自动取消选择上、下及同级 | 0        | 可选      |
| sumAllLevel | 全部合计 | boolean | 合计所有层                         | false | -     | 0        | 可选      |
| cellEdit  | 列可编辑   | boolean | 表格只读属性                        | true  | -     | 0        | 可选      |
| cacheChangeSet| 缓存变更集 | boolean | 分页状态下，缓存其它页的脏数据。 | false | -     | 0        | 可选      |
| idkey     | 主键字段   | string  | id字段                             | gid   | -     | 0        | 可选      |
| colNames  | 列名列表   | Array   | 表格的表头配置信息                  | null  | -     | 1        | 必选      |
| reverGJ   | 反向勾稽   | boolean | 单元格勾稽运算时是否反向判断         | false | -     | 0        | 可选      |
| autoGroups| 自动合并   | boolean | 插入自动合并单元格。                | false | -     | 1        | 可选      |
| pageSizeList | 每页条数列表 | Array | 分页时每页显示多少条数据          | [20,50,100,200] | -  | 0 | 可选      |
| totalRever | 合计对换位置 | boolean | 小计与合计对换                   | false | -     | 0        | 可选      |
| treeKey   | 树结构字段 | string  | 树结构所在的字段                    | 空    | -     | 0        | 可选      |
| rowHeight | 行高       | int    | 设置表格单行的高度                   | 36    | -     | 0        | 可选      |
| primaryKey| 主键       | Array   | 表格的主键配置                     | null  | -     | 0        | 可选      |
| pidkey    | 父级字段   | string  | pid字段                            | pid    | -     | 0        | 可选      |
| EnterDown | 回车方向   | boolean | 编辑时，按回车焦点是否向下移         | false | -     | 0        | 可选      |
| hidePopMenu | 隐藏右键菜单 | boolean | 是否隐藏右键菜单                | false | -     | 0        | 可选      |
| shrinkToFit | 填满表宽 | boolean | 表格各列宽度拉伸填满整个表格         | true  | -     | 0        | 可选      |

### Methods
| 方法名称         | 中文名   | 参数                                          | 描述               | 返回值   |
| --------------- | -------- | -------------------------------------------- | ------------------ | ------- |
| setReadOnly     | 设置只读  | value:true/false,只读状态                     | 设置只读状态       | void     |
| setSubtotalValue | 设置小计值 | fieldName:字段名称，value:值                | 设置小计值          | void    |
| setColAlign     | 设置列对齐 | fieldName：字段名,align:对齐方式              | 设置列对象方式     | void     |
| setHeight       | 设置高度  | height:表格高度                               | 设置表格高度       | void     |
| getReadOnly     | 获取只读  | .                                            | 获取只读状态        | Boolean |
| dataSet.setUnSelect | 取消选择   | node:节点对象，为空时默认指当前节点 | [模型方法]复选择时，取消选择 | void    |
| validateAll     | 全部效验  | callBack:回调函数                         | 效验表格配置的所有效验列 | void    |
| insert          | 插入明细  | index:序号,item:数据包                        | 插入数据明细，     | void     |
| setWidth        | 设置宽度  | width:表格宽度                                | 设置表格宽度        | void    |
| hideTitle       | 隐藏标题  | .                                            | 隐藏标题              | void   |
| caculateGJ      | 执行勾稽  | full：true全运算/false增量运算                | 执行勾稽运算        | void    |
| isReadOnly      | 是否只读  | .                                            | 是否只读             | Boolean |
| setTotalValue   | 设置合计值 | fieldName:字段名称，value:值                  | 设置合计值          | void    |
| showCol         | 显示列    | fieldAry：字段数组                            | 显示列             | void    |
| prior           | 上一行    | .                                            | 移动到上一行        | void    |
| showTitle       | 显示标题  | .                                            | 显示标题            | void    |
| getPageData     | 获取当页数据 | .                                         | 获取当前分页的数据   | 数据包   |
| saveChangeSet   | 保存变更  | key:保存的主键                       | 根据给定的key缓存当前变量集。| JSON     |
| checkAll        | 全选   | fieldName:字段,titleState:标题状态,callBack:回调 | 全选               | void     |
| scrollToFocus   | 滚动到焦点行 | .                                     | 滚动到焦点行所在的位置。 | Boolean |
| setPageSize     | 设置每页条数 | size:每页条数                               | 设置页面大小        | void    |
| refreshTitle    | 重置表头   | titleObject:表头包括colNames和colModels       | 重构表头结构.       | void    |
| isShow          | 显示状态  | .                                         | 表格是否生于显示的状态  | Boolean |
| dataSet.gotoChild | 获得子节点 | index:子节点序号                | [模型方法]移动到第index个子节点 | void    |
| delRecord       | 删除明细  | node:节点对象，为空时默认指当前节点             | 删除行数据            | void   |
| resetColWidth   | 重置列宽  | .                                             | 重置列宽          | void     |
| dataSet.unSelectAll | 取消全选择 | node:节点对象，为空时默认指根节点,callBack:回调函数 | [模型方法]取消选择某节点下的所有节点 | void    |
| refreshHiddenState | 刷新隐藏列  | .                                | 刷新隐藏列的状态。          | void     |
| getChangeSet    | 获取变更集 | single:是否只获取当前页                   | 获取变量集。 | 数据包{Array|Object} |
| dataSet.lastNode | 最后节点 | node:节点对象，为空时默认指当前节点  | [模型方法]移动到以node节点为根的最后一行 | void     |
| last            | 最后一行  | .                                            | 最后一行              | void   |
| loadChangeSet   | 加载变量  | key:加载的主键值，callBack:回调函数         | 根据给定的key加载变量集 | void    |
| colDatasToColNames | 数据包转换成表头 | headVals:表头数据包        | 数据包转换成表头  | 数据包{Array|Object}  |
| setFilterMark   | 设置过滤结构   | res:过滤结构,callBack:回调函数            | 设置过滤结构       | void     |
| isColHidden     | 列是否隐藏 | fieldName:列字段                             | 列是否隐藏。          | Boolean |
| dataSet.getChildNode | 获取子节点  | index:子节点序号,pNode:父节点对象，为空时默认指当前节点       | [模型方法]获取子节点 | Node |
| isColReadOnly   | 是否列只读 | fieldName:列字段                             | 列只读               | Boolean |
| setTitleChecked | 设置标题复选 | fieldName:字段名,val：true/false状态        | 标题行复选框选择    | void    |
| setTreeGrid | 设置树表状态 | types:是否树结构,idkey:id字段,pidkey:父id字段 | 设置树表格状态        | Boolean |
| mergeChangeLog  | 合并变更集 | ids:id列表                                  | 合并变更集          | void    |
| getCellExpObject | 获取单元格公式结构 | keyFieldName:主键字段,captionFieldName:显示字段 | 获取单元格公式结构    | JSON |
| getPageIndex    | 获取页码  | .                                            | 获取分页页码。        | int     |
| setContentFit   | 内容自应  | fieldNames:字段列表,col:列对象(标题cell)，可不传,callBack:回调 | 计算内容自适应   | void |
| dataSet.getCurNode | 获取行节点 | .                                   | [模型方法]获取当前行节点 | Node     |
| selectAll       | 全选      | .        | fieldName：字段名，不传则指默认复选择列,callBack:回调函数 | void    |
| doClearData     | 清除数据  | .                                        | 清除区域选择的可编辑内容 | void    |
| isChecked       | 是否选择  | node:节点对象                                 | 是否选择。         | Boolean  |
| getPageSize     | 获取条数  | .                                            | 获取分页每页条数     | int     |
| getFieldName    | 获取当前列字段 | index:表格列序号                          | 获取字段名         | 列字段名 |
| isCellReadOnly  | 是否单元格只读 | fieldName:列节点,node:行节点              | 是否单元格只读        | Boolean |
| refreshValidate | 刷新效验  | colModel:列属性对象,node:行节点对象,value:单元格的值,text:单元格显示的文本,col:单元格dom元素 | 刷新某单元格的效验状态 | void   |
| getDisplayAsJson | 获取json数据 | fieldNames:字段列表(数组）             | 获取显示数据列表， | 返回json数组 |
| getIndex        | 获取序号   | .                                            | 获取当前节点的序号   | number  |
| setTotalRecord  | 设置分页条数 | count:明细总数                             | 设置分页明细总条数   | void    |
| reverCheckRow   | 反选      | fieldName:字段，callBack:回调                 | 反选              | void     |
| shrinkToFit     | 宽度填满  | state：填满状态，suit:拉伸状态         | 列宽填满整个表格。或拉伸表格。| void    |
| getSubtotalValue | 获取小计值 | fieldName:字段名                            | 获取小计值          | string |
| reverSelectRow  | 反选      | fieldName：字段名，不传则指默认复选择列,callBack:回调函数 | 反选     | void    |
| resetPageState  | 重置分页状态 | .                                          | 重置分页状态        | void     |
| setFiltered     | 执行过滤  | filtered:true/false,callBack:回调函数         | 执行过滤          | void     |
| getPrintData    | 获取打印数据包 | callBack(datas):回调函数                  | 读取打印数据包，   | void     |
| dataSet.gotoParent | 移到上级   | level:移到某父层,不传表示上级父节点     | [模型方法]移动到上父节点行 | void   |
| enable          | 控件可用   | .                                           | 表格可用           | void     |
| beginUpdate     | 开始刷新  | .                                            | 开始锁定刷新        | void    |
| priorPage       | 上一页    | .                                            | 上一页                | void   |
| getCopyData     | 获取复制数据 | .                                        | 获取区域选择的内容   | data数据包 |
| getHeader       | 获取表头 | hidden：过滤隐藏,numberRow：是否获取序号列       | 获取表头结构数组     | 表头数据 |
| setPageIndex    | 设置页码  | index:页码                                   | 设置第几页            | void   |
| hideGridHeader  | 隐藏表头  | .                                            | 隐藏表头            | void    |
| caculateSum     | 计算合计  | full：true全运算/false增量运算                 | 执行顶层合计运算    | void    |
| cancelSelectRow | 取消全选  | fieldName：字段名，不传则指默认复选择列,callBack:回调函数 | 全取消选择 | void    |
| isColShow       | 列是否显示 | fieldName:列字段                             | 列是否显示          | Boolean |
| setReadOnly     | 设置只读  | state:true/false,可选状态，node:节点对象，为空时默认指当前节点 | 设置行只读状态 | void |
| dataSet.getSummary | 设置合计行 | fieldName:字段名                     | [模型方法]获取合计行的值 | value    |
| post            | 提交数据  | .                                            | 确认并提交编辑数据，   | void   |
| dataSet.setEnableSelect | 设置节点是否可选择  | state:true/false,可选状态，node:节点对象，为空时默认指当前节点 | [模型方法]设置节点是否可复选. | void |
| getTitleName    | 获取标题  | index:表格列序号                              | 获取表头名称           | string|
| showGridHeader  | 显示表头  | .                                            | 显示表头              | void   |
| deleteAll       | 删除所有数据  | .                                        | 删除全部数据         | void    |
| getPageCount    | 获取页数  | .                                            | 获取分页页数。      | int     |
| setRowReadOnly  | 设置行只读 | value:true/false,行只读状态，node：行节点，为空时默认指当前行 | 设置行只读状态  | void     |
| refresh         | 刷新表格  | refreshAll:刷新全部单元格     | 刷新表格显示，默认只刷新变动的单元格。 | void    |
| select          | 选择节点  | node：子节点对象                               | 选择某节点          | void   |
| moveRecord      | 移动行   | step:步长,callBack:回调函数                   | 移动行               | void   |
| dataSet.getNode | 获取子节点 | index: 子节点序号, pNode:父节点对象，为空时默认指当前节点, focus:设置为焦点行 | [模型方法]获取节点 | Node |
| addGJExpress    | 添加勾稽  | keyFieldName:行定位字段,expJson：表达式        | 添加单元格勾稽表达式  | void   |
| dataSet.setSummary | 设置合计值 | fieldName:字段名,value:值               | [模型方法]设置合计行的值 | void   |
| dataSet.getRecNo | 获取行编号 | .                               | [模型方法]获取当前节点的唯一编号 | number  |
| setTitleCaption | 设置标题名称 | startColName:开始字段,newLabel:新名称,level:标题行数,isHtml:是否html | 修改标题名称。 | void |
| getCellReadOnly | 是否单元格只读 | fieldName:列节点,node:行节点              | 是否单元格只读      | Boolean |
| colNamesToColDatas | 获取表头数据包 | colModels:列配置,colNames:标题 | 表头转换成数据包 | 数据包{Array|Object} |
| appendChild     | 添加子节点 | item:数据包,pNode:父级节点对象                | 添加子节点          | void    |
| getTransIDName  | 获取名称转换列表 | .                          | 获取名称转换列表    | 数据包{Array|Object} |
| setSort         | 设置排序  | fieldName:字段名,sortState：up/down/none排序状态,refresh：是否直接刷新,callBack：回调函数 | 设置排序 | void |
| setTransIDName  | 设置名称转换 | fieldName:字段名，idNameJson:名称转换列表   | 设置名称转换列表     | void    |
| addTransIDName  | 添加转换列表 | fieldName:字段名，idNameJson:名称转换列表 | 添加名称转换列表，idNameJson结构类似{name:value} | void |
| addExpress      | 添加表达式 | fieldName：列字段,&nbsp;keyFieldName:定位行用的字段,keyValue：定位行用的值,&nbsp;expJson：表达式&nbsp;{&apos;exp&apos;:&apos;&apos;,&nbsp;&apos;caption&apos;:&apos;&apos;} | 添加单元格运算表达式 | void     |
| dataSet.setSelect | 选择行  | node:节点对象，为空时默认指当前节点       | [模型方法]复选择时，执行选择 | void   |
| dataSet.getSelectedData | 获取选择数据 | .                              | [模型方法]返回选择了的节 | void   |
| getFilterMark   | 获取过滤结构 | .                                    | 获取过滤结构  | 数据包{Array|Object} |
| addTreeNode     | 添加树节点 | data:节点数据json,nodeFlag:节点类型同级/下级   | 给树当前节点添加节点，同级时nodeFlag=brother,下级时nodeFlag=child | void |
| dataSet.getOption | 获取单元格属性  | fieldName:字段名,node:节点对象，为空时默认指当前节点 | [模型方法]获取节点属性,如果不传参数则获取dataSet属性。 | Json |
| each            | 遍历      | node:节点对象,可不传,eachFunc:循环函数,desc:是否反向遍历,callBack:回调函数 | [模型方法]循环遍历所有节点 | void   |
| caculate        | 执行计算  | full：true全运算/false增量运算                | 执行表达式运算       | void    |
| firstPage       | 第一页    | .                                            | 第一页             | void    |
| isRowReadOnly   | 是否行只读 | node:行只读                                  | 是否行只读           | Boolean |
| delEcpRowData   | 删除行     | rowIds:行主键ID,不传就删除当前行              | 删除行             | void     |
| addEcpRowData   | 添加行    | datas：数据包（JSON数组）                     | 添加数据            | void    |
| hideCol         | 隐藏列    | fieldAry：字段数组                            | 隐藏列              | void    |
| moveRecord      | 行移动    | step：负数为向上移，正为向下移step行            | 移动行            | void     |
| getRecordCount() | 获取数据数量 | .                                        | 获取数据条数        | number   |
| dataSet.getFilterState | 获取过滤状态 | fieldName:字段名                  | [模型方法]获取过滤状态 | Boolean |
| getCellReadOnly | 单元格只读 | fieldName:字段名,node：行节点，为空时默认指当前行 | 获取单元格只读状态 | Boolean |
| refreshSortState | 刷新排序状态 | fieldName:列字段名称                   | 刷新表格排序的状态     | void     |
| getEcpChangeSet | 获取ECP变更集 | single:是否只获取当前页        | 获取ECP变更集      | 数据包{Array|Object} |
| getRangeData    | 获取数据  | range：分页范围,callback：回调函数  | 通过分页范围获取数据 | 数据返回到回调事件里 |
| endUpdate       | 开始刷新  | refreshState:是否刷新表格                     | 结束锁定刷新        | void    |
| getSelectedRowData | 获取选择数据 | curOnly:true/false复选表格时，如果只想选择当前行的data则传入true | 获取选择行，复选时返回多行 | 选择的数据包   |
| scrollToIndex   | 还原滚动条 | index:滚动条位置序号                        | 还原滚动条到某个状态。 | void    |
| nextPage        | 翻页      | .                                            | 下一页             | void    |
| refreshScrollBar | 刷新纵向滚动条 | .                                       | 刷新纵向滚动条        | void    |
| dataSet.getEnableSelect | 是否可选  | node:节点对象，为空时默认指当前节点  | [模型方法]获取节点的复选择是否可编辑 | Boolean |
| locateCell      | 定位到单元格 | rowId:行主键ID（暂时元效）,fieldName:字段名 | 定位以单元格         | void    |
| scrollToFocus   | 滚动到焦点行 | .                                          | 滚动到选择行        | Boolean |
| isSelected      | 是否选择  | node：行节点，为空时默认指当前行          | 复选择时，是否处于选择状态 | Boolean |
| setSelection    | 执行选择  | rowId：主键id,fieldName:列名,onselect:是否触发事件,highLight | 定位选择中行 | void |
| getDisplayAsArray | 获取显示数据 | fieldNames:字段列表(数组）               | 获取显示数据列表，   | 二维数组 |
| value | 读/写表数据 | datas:数据包（JSON数组）   | 初始化数据,同步初始化到数据模型，或不传参数为获取表格数据 | 数据包{Array|Object} |
| last            | 移到最后  | .                                            | 移动到最后一行       | void    |
| getValue        | 获取值    | fieldName:字段名,node:节点对象                | 获取当前行某字段的数据 | value |
| getColAlign     | 获取列对齐 | fieldName:列字段                   | 返回某列字段内容的对齐方式。   | string   |
| setName         | 设置名称  | .                                            | 设置表格对象名称。   | void    |
| getDisplayData | 获得显示数据 | type:默认'array',可传入'json',callBack:回调函数 | 返回格式化后的数据   | 数据包 |
| lastPage        | 最后一页  | .                                            | 最后一页            | void    |
| multiSelectRows | 选择行    | status：状态true/false,fieldName：字段名，不传则指默认复选择列,callBack:回调函数 | 表格全选/取消全选方法 | void |
| append          | 增加行    | records:行数据包，pNode：父节点对象，为空时默认指当前节点,callBack:回调函数 | 添加行数据 | void    |
| setMultiSelect  | 设置复选  | value:true/false                             | 是否复选择            | void   |
| disable         | 禁用控件  | .                                            | 表格不可用            | void   |
| first           | 第一行    | .                                            | 第一行              | void    |
| cancelCheckRow  | 取消选择  | fieldName:字段,titleState:标题状态,callBack:回调 | 取消选择         | void    |
| setValue        | 设置值    | fieldName:字段名，value:值                    | 设置当前行某字段的数据 | Boolean |
| setColReadOnly  | 设置列只读 | fieldName:列字段,readOnly:只读状态。          | 设置列只读。        | void    |
| mulMoveRecord   | 多行移动  | step:步长,callBack:回调函数 | 多行移动接口，用于复选表格，step向前移一步则传入-1，多步则传入类似-3的值，向后移一步则传入1，多步则传入类似3的值。 | void    |
| setHeaderLabel  | 设置标题名称 | startColName:开始字段,newLabel:新名称,level:标题行数,isHtml:是否html | 修改标题名称。 | void   |
| getColModel     | 获取列属性 | fieldName:字段名                           | 获取列配置信息      | 列属性Json |
| getScrollIndex  | 获取滚动序号 | .                                         | 获取滚动条所在的位置序号 | int  |
| dataSet.isExpand | 是否展开  | node：节点对象，为空时默认指当前节点           | [模型方法]是否展开  | Boolean  |
| next            | 下一行    | state:是否遍历隐藏行                          | 下一行                | void   |
| dataSet.getAlign | 获取对齐方式 | fieldName:字段名                      | [模型方法]获取列对齐方式 | void    |
| dataSet.getCount | 获取子节点数量  | node：节点对象，为空时默认指当前节点 | [模型方法]获取子节点数量 | number  |
| isSelected     | 是否选择  | node:节点对象，为空时默认指当前节点      | 复选择时，是否处于选择状态 | Boolean   |
| dataSet.getFieldOption | 获取列属性 | fieldName:字段名                      | [模型方法]获取列属性 | void    |
| getTotalValue   | 获取合计值 | fieldName:字段名称                           | 获取合计值         | string   |
| showGridFooterrow | 显示合计行 | .                                      | 显示合计行        | void         |
| doResize        | 大小调整  | .                          | 根据配置的属性，调整表格的宽高          | void    |

### Events

| 事件名称         | 中文名   | 参数                                          | 描述               | 返回值   |
| --------------- | -------- | -------------------------------------------- | ------------------ | ------- |
| onMoreClick     | 下拉选择更多事件 | input,fieldName                     | 下拉选择时选择更多事件 | void     |
| onButtonEdit    | 弹出选择事件 | input,fieldName,dataType                  | 单元格弹出选择事件    | void   |
| onCellSelect    | 单元格选择 | pKeyName,colIndex,grid                      | 单元格选择事件      | void     |
| onSortCol       | 列排序    | priorColIndex,colIndex,state                 | 列排序事件          | void    |
| onLinkClick     | 链接点击  | grid,fieldName,node                          | 链接点击事件        | Boolean |
| onCheckChanged  | 复选框变化 | cNode,selectState,fieldName                 | 复选择框选择事件    | void     |
| beforeSelectRow | 行变更前  | oldNode,newNode                              | 行变更事件          | Boolean |
| onCellDraw_[fieldName] | 单元格渲染 | dataSet,fieldName,col                | 单元格渲染事件      | void     |
| onScrollEnd     | 滚动结束  | .                                            | 滚动到最后一条数据事件 | void   |
| onDBClick       | 双击事件  | grid                                         | 双击事件            | void     |
| onScrollBegin   | 滚动起始  | .                                            | 滚动到第一条数据事件 | void    |
| onAfterLoad     | 数据加载后 | .                                           | 数据加载后事件       | void     |
| onSelectAll     | 全选      | checked,fieldName                            | 全选择事件         | void     |
| loadError       | 数据加载错误 | null,e,message               | 单元格绘画事件,[fieldName]为字段名 | void     |
| onHeaderClick   | 标题点击  | .                                            | 表头点击事件        | void     |
| onSummaryDraw   | 合计行绘画 | dataSet,fieldName,node                      | 合计行渲染事件      | void     |
| onFormat_[fieldName] | 格式化 | node,fieldName,dataType,value,colModel     | [模型事件]显示格式化事件,fieldName为格式化的字段 | void     |
| onSelectRow     | 行选择    | cNode,selectState,fieldName                  | 选择行事件，复选事件 | void     |
| onFilter        | 过滤      | dataSet,filterState                         | [模型事件]过滤事件    | Boolean  |
| onFiltered      | 过滤后    | dataSet                                     | 过滤后事件           | void     |
| onAfterEdit     | 编辑后    | dataType,fieldName,value,text,colModel,cNode | 编辑后事件          | void    |
| onRowChanged    | 行变更    | dataSet                                      | 行变更事件          | void     |
| onFormat_[fieldName] | 格式化 | dataSet,fieldName,text,value               | 单元格格式化事件     |string   |
| onPopMenuExt    | 菜单扩展  | grid,popMenu                                 | 菜单扩展事件        | void     |
| onEditing       | 编辑中    | colModel,oldValue,newValue,cellOption,cNode  | 单元格编辑中事件     | Boolean  |
| onClick         | 单击事件  | node,FieldName                               | 单击事件            | void     |
| onExpand        | 展开事件  | node                                         | 展开事件            | void    |
| onPageChanged   | 分页变更  | pageCount,pageSize,pageIndex                 | 面页变化事件        | void     |
| onBeforeGroups  | 单元格合并前 | colModel,pNode,cNode,isMerge              | 单元格合并前事件     | JSON     |
| onSort          | 排序事件  | fieldName,sortState                          | 排序事件            | void     |
| onCellClear_[fieldName] | 清除事件 | dataSet,fieldName,col                 | 单元格清除事件      | void     |
| onCanEditing     | 可编辑控制事件 | grid,fieldName,editSta     | 用于控制表格单元格是否可编辑的事件 | Boolean  |
| onComboBoxSelect | 下拉选择事件 | input,fieldName,index,item                | 下拉选择时的事件    | void     |
| afterPasteData   | 粘贴后事件 | grid,oldDatas,newDatas                      | 粘贴后事件         | void     |
| onSetText_[fieldName]   | 输入文本事件 | fieldName,node,text         | 输入文件事件，用于名称转ID | void     |
| onBeforeEdit | 编辑前事件 | dataType,fieldName,value,text,colModel,node     | 编辑前事件         | JSON     |
| onShowHint  | 显示hint事件 | hintStr, fieldName, cnode                          | 显示hint的事件     | string   |
| afterClearData   | 清除数据事件 | grid                                       | 清除事件          | void     |