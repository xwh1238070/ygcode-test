### QzzQueryGrid - 服务端二次搜索

:::demo QueryServerFilter

```html
<template>
  <tl-row>
    <tl-col :span=6>
        <tl-radio v-model="radio" label="1">少于2000</tl-radio>
        <tl-radio v-model="radio" label="2">多于2000</tl-radio>
    </tl-col>
    <tl-col :span=15>
        <tl-button @click="doQuery">查询</tl-button>
    </tl-col>
    <tl-col :span=3>
        <tl-input v-model="searchVal"/>
    </tl-col>
  </tl-row>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"
        @oncollectfilter="onCollectFilter"
        @onpagechanged="onPageChanged"></qzz-query-grid>
    </tl-col>
  </tl-row>
</template>

<script>

  //-------------------------------------------------Server类为服务端模拟代码--------------------------------------------------
  class Server {
    constructor(datas) {
        if(datas) {
		    this.datas = datas;
		}
    }
    filterData(filters, sorts, searchVal) {
			console.info(sorts);
			var datas = this.datas.slice();
			if(sorts && sorts.sortState && sorts.sortState !== '' && sorts.sortState !== 'none') {
			    var dataType = sorts.dataType;
			    var sortType = sorts.sortState;
				var fieldName = sorts.fieldName;
				datas = datas.sort(function(a, b) {
					var res = 0;
					if(sortType === 'up') {
						if(dataType === 'number') {
							res = +a[fieldName] - +b[fieldName];
						} else {
							res = a[fieldName] - b[fieldName];
						}
					} else {
						if(dataType === 'number') {
							res = +b[fieldName] - +a[fieldName];
						} else {
							res = b[fieldName] - a[fieldName];
						}
					}
					return res;
				});
			}
			datas = datas.filter((item) => {
				var res = true;
				if(filters != null && filters.length > 0) {
					for(var i = 0, ilen = filters.length; i < ilen; i++) {
						var cfilter = filters[i];
						var fieldName = cfilter.fieldName;
						res = res && cfilter.sel[item[fieldName]] === 1;
					}
				}
				if(res === true && searchVal != null && searchVal !== '') {
				    var has = false;
					for(var ckey in item) {
						var cval = item[ckey];
						if((cval + '').indexOf(searchVal) >= 0) {
						    has = true;
							break;
						}
					}
					res = res && has;
				}
				return res;
			});
			return datas
		}

        getData (pc, ps, pi, filters, sorts, searchVal) {
			var _this = this;
			return new Promise(function(resolve, reject) {
				var startIndex = (pi - 1) * ps;
				var endIndex = startIndex + ps;
				var cdatas = _this.filterData(filters, sorts, searchVal);
				var res = cdatas.slice(startIndex, endIndex);
				resolve({
					datas: res,
					count: cdatas.length
				});
			})
		}

        getFilterData (filterStack, fieldName, searchVal) {
			var list = [];
			var count = {};
			var datas = this.filterData(filterStack, searchVal);
			for(var i = 0, ilen = datas.length; i < ilen; i++) {
				var cdata = datas[i];
				var cval = cdata[fieldName];
				if(cval == null) {
				   cval = '(空值)'
				}
				if(count[cval] == null) {
					count[cval] = 1
				} else {
					count[cval] += 1;
				}
			}
			for(var ckey in count) {
				list.push({
					id: ckey,
					text: ckey,
					count: count[ckey]
				})
			}
			return new Promise(function(resolve) {
				resolve({datas:list});
			})
		}
  }
  
  let datas1 = [];
  for(let i = 0; i < 1500; i++) {
    datas1.push({'gid': i+1, 'dxmc':i+1,'TJBM':'001','DKLY':'农业银行','JE':10000,'TEL': '13149316032', 'CONTEXT':'房地产  建设1','STOP':true,'DEFAULT':false, 'SEX': 1})
  }
  let cser = new Server(datas1);

  let datas2 = [];
  for(let i = 0; i < 4000; i++) {
    datas2.push({'gid': i+1, 'dxmc':i+1,'TJBM':'001','DKLY':'农业银行','JE':10000,'TEL': '13149316032', 'CONTEXT':'房地产  建设1','STOP':true,'DEFAULT':false, 'SEX': 1})
  }
  let cser2 = new Server(datas2);

  var mser = cser;

  // ------------------------------------------------------------------以上为服务端模板代码， 请仅需参考下面的代码即可---------------------------------------------------

  export default {
    data() {
      return {
        radio: '1',
        searchVal: '',
        gridoption: {
          Align: 'alClient',
          maxFilterCount: 2000,
          pager: true,
          colNames: ['对象名称', '统计编码', '开始日期', '结束日期', '贷款来源', '金额', '内容', '停用', '默认'],
          colModels: [
            { name: 'dxmc', frozen: true },
            { name: 'TJBM' },
            { name: 'KSRQ', dataType: 'date' },
            { name: 'JSRQ', dataType: 'date', showTime: true, width: 150 },
            { name: 'DKLY' },
            { name: 'JE', scale: 2, sum: true, dataType: 'number' },
            { name: 'CONTEXT', length: 100 },
            { name: 'STOP', editType: 'onoff', align: 'center' },
            { name: 'DEFAULT', align: 'center' }
          ]
        },
        griddata: []
      };
    },
    watch: {
        radio(val) {
            if(val === '1') {
                mser = cser;
            } else {
                mser = cser2;
            }
        }
    },
    methods: {
        onCollectFilter(obj, filterStack, fieldName, callBack) {
            console.info('arguments1', arguments);
            mser.getFilterData(filterStack, fieldName, this.searchVal)
			.then(res => {
			    callBack(res.datas);
			});
        },
        onPageChanged(obj, pc, ps, pi, filters, sorts) {
            console.info('arguments2', arguments);
            var grid = obj.target;
		    mser.getData(pc, ps, pi, filters, sorts, this.searchVal)
		    .then(res => {
               this.griddata = res.datas;
			   grid.setTotalRecord(res.count, false);
		    })
        },
        doQuery() {
			this.$refs.grid.ui.loading();
			// 为了看加载动画，故意延迟1秒钟初始化数据。
			setTimeout(() => {
                this.$refs.grid.ui.firstPage(true);
			}, 1000);
        }
    }
  };
</script>
```

:::
