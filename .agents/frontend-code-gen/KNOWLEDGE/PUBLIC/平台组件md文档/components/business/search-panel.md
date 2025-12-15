# SearchPanel 查询面板

查询面板为一种通用的布局控件，旨在支持各种查询类型页面。根据业务需求配置使用。

### 基本使用

`SearchPanel` 组件可以通过 slot 插入自定义内容。

组件折叠后会显示当前查询的摘要。

可以通过点击图钉按钮让面板固定展开,用户主动点击箭头控制展开缩放的优先级大于图钉。  
面板内部使用`form`，`form-item`组件可以实现内部项目自适应宽度效果，具体参阅[form](/tlead/tlead-ui/form/form.html)。

:::demo `SearchPanel` 组件可以配置 `is-fixed` 属性来控制是否随着页面滚动固定于顶部。

```html
<template>
  <div class="search-panel">
    <tl-search-panel :is-fixed="false" :sub-title="subTitle" :fixed-top="0" yw-key="20211202" user-id="118568">
      <tl-form label-suffix="：">
        <tl-form-item label="报销凭证单位">
          <tl-select
            :data="selectOptions1"
            v-model="slotValue.selectValue1"
            placeholder="请选择..."
            id-field="value"
            text-field="label"
          ></tl-select>
        </tl-form-item>
        <tl-form-item label="凭证来源">
          <tl-select
            :data="selectOptions2"
            v-model="slotValue.selectValue2"
            id-field="value"
            text-field="label"
          ></tl-select>
        </tl-form-item>
        <tl-form-item label="制证人">
          <tl-select
            :data="selectOptions3"
            v-model="slotValue.selectValue3"
            id-field="value"
            text-field="label"
          ></tl-select>
        </tl-form-item>
        <tl-form-item label="报销凭证单位">
          <tl-select
            :data="selectOptions4"
            v-model="slotValue.selectValue4"
            id-field="value"
            text-field="label"
          ></tl-select>
        </tl-form-item>
        <tl-form-item label="金额">
          <tl-input-number v-model="slotValue.inputVal1" type="number"></tl-input-number>
        </tl-form-item>
      </tl-form>
    </tl-search-panel>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // 面板内容当前值
        slotValue: {
          selectValue1: '',
          selectValue2: '',
          selectValue3: '',
          selectValue4: '',
          inputValue1: '',
          timeValue1: ''
        },
        // ---- 查询内容的选项data start ----
        selectOptions1: [
          {
            value: '国网天津电力工说',
            label: '国网天津电力工说'
          },
          {
            value: '国网天津电力工说2',
            label: '国网天津电力工说2'
          },
          {
            value: '国网天津电力工说3',
            label: '国网天津电力工说3'
          },
          {
            value: '国网天津电力工说4',
            label: '国网天津电力工说4'
          },
          {
            value: '国网天津电力工说5',
            label: '国网天津电力工说5'
          }
        ],
        selectOptions2: [
          {
            value: '来源1',
            label: '来源1'
          },
          {
            value: '来源2',
            label: '来源2'
          },
          {
            value: '来源3',
            label: '来源3'
          }
        ],
        selectOptions3: [
          {
            value: '张三',
            label: '张三'
          },
          {
            value: '李四',
            label: '李四'
          },
          {
            value: '李五',
            label: '李五'
          },
          {
            value: '张五',
            label: '张五'
          },
          {
            value: '张六',
            label: '张六'
          }
        ],
        selectOptions4: [
          {
            value: '国网公司',
            label: '国网公司'
          },
          {
            value: '国网公司2',
            label: '国网公司2'
          },
          {
            value: '国网公司3',
            label: '国网公司3'
          },
          {
            value: '国网公司4',
            label: '国网公司4'
          },
          {
            value: '国网公司5',
            label: '国网公司6'
          }
        ],
        selectOptions5: [
          {
            value: '南网公司电力',
            label: '南网公司电力'
          },
          {
            value: '南网公司电力2',
            label: '南网公司电力2'
          },
          {
            value: '南网公司电力3',
            label: '南网公司电力3'
          },
          {
            value: '南网公司电力4',
            label: '南网公司电力4'
          },
          {
            value: '南网公司电力5',
            label: '南网公司电力5'
          }
        ]
        // ---- 查询内容的选项data end ----
      };
    },
    computed: {
      subTitle() {
        let result = `查询条件：无`;
        let timeValue1 = this.slotValue.timeValue1
          ? this.slotValue.timeValue1.reduce((acc, cur, index) => {
              if (index === 0) {
                return acc + cur.toLocaleDateString();
              }
              return acc + ' - ' + cur.toLocaleDateString();
            }, '')
          : '';
        const selectArr = [
          timeValue1 ? '凭证日期：' + timeValue1 : '',
          this.slotValue.selectValue1 ? '报销凭证单位:' + this.slotValue.selectValue1 : '',
          this.slotValue.selectValue2 ? '凭证来源：' + this.slotValue.selectValue2 : '',
          this.slotValue.selectValue3 ? '制证人：' + this.slotValue.selectValue3 : '',
          this.slotValue.selectValue4 ? '报销凭证单位：' + this.slotValue.selectValue4 : '',
          this.slotValue.inputVal1 ? '金额：' + this.slotValue.inputVal1 : ''
        ];
        const activeArr = selectArr.filter(val => val !== '' && val !== undefined);
        if (activeArr.length > 0) {
          let activeStr = activeArr.reduce((acc, cur) => {
            return acc + cur + ', ';
          }, ``);
          result = `查询条件(${activeArr.length}): ${activeStr}`;
        }
        // console.log(result)
        return result;
      }
    }
  };
</script>
```

:::

### 高级查询

可以配合 `Popover` 组件，实现高级查询功能。

:::demo `SearchPanel` 组件可以配置 `is-fixed` 属性来控制是否随着页面滚动固定于顶部。

```html
<template>
  <tl-search-panel>
    <yj-container v-model="form" :data="formItem">
      <template slot="button">
        <tl-button type="primary">查询</tl-button>
        <tl-popover trigger="click">
          <tl-button slot="reference">高级</tl-button>
          <yj-container
            auto-width
            v-model="form"
            slot="content"
            :data="formItem">
          </yj-container>
        </tl-popover>
        <tl-button>重置</tl-button>
      </template>
    </yj-container>
  </tl-search-panel>
</template>

<script>
  export default {
    data() {
      return {
        // 面板内容当前值
        form: {
          coefficient: '',
          units: '',
          name: '',
          money: '',
          number: ''
        },
        formItem: 
        [{
          label: '折合系数',
          name: 'coefficient',
          dataType: 'string',
        },
        {
          label: '所属单位',
          name: 'units',
          dataType: 'string',
        },
        {
          label: '人员名称',
          name: 'name',
          dataType: 'string',
        },
        {
          label: '金额',
          name: 'money',
          dataType: 'string',
        },
        {
          label: '数量',
          name: 'name',
          dataType: 'number',
        }]
      };
    }
  };
</script>
```

:::

### 查询面板方案配置

通过使用 TlSearchPanelSolution 插入 slot=solution 的插槽，可以使用查询方案名称组件。 TlSearchPanelSolution 需要传
vip-address 以确定服务地址,可以通过设置 fixedTop 属性规定 fixed 时面板的 top 值

:::demo SearchPanelSolution 组件内容通过 form-value 监听数据，包含设置默认和取消默认事件。

```html
<template>
  <div class="section">
    <div class="example">
      <tl-search-panel :fixed-top="60">
        <template v-slot:solution>
          <tl-search-panel-solution
            :vip-address="'/jt/mapp/sample'"
            :data="slotValue"
            @set-default="defaultSelectValue"
            yw-key="20211202"
            user-id="118568"
          ></tl-search-panel-solution>
        </template>
        <template slot="default">
          <tl-form :model="slotValue" label-suffix="：">
            <tl-form-item label="报销凭证单位">
              <tl-select
                :data="selectOptions1"
                v-model="slotValue.selectValue1"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="凭证来源">
              <tl-select
                :data="selectOptions2"
                v-model="slotValue.selectValue2"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="制证人">
              <tl-select
                :data="selectOptions3"
                v-model="slotValue.selectValue3"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="报销凭证单位">
              <tl-select
                :data="selectOptions4"
                v-model="slotValue.selectValue4"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="金额">
              <tl-input-number v-model="slotValue.inputVal1" type="number"></tl-input-number>
            </tl-form-item>
            <tl-form-item button>
              <tl-button type="primary">查询</tl-button>
              <tl-button>重置</tl-button>
            </tl-form-item>
          </tl-form>
        </template>
      </tl-search-panel>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // 面板内容当前值
        slotValue: {
          selectValue1: '',
          selectValue2: '',
          selectValue3: '',
          selectValue4: '',
          inputValue1: '',
          timeValue1: ''
        },
        // 查询方案的保存值
        slotValueGroup: {
          方案一: {
            selectValue1: '国网天津电力工说',
            selectValue2: '来源1',
            selectValue3: '张三',
            selectValue4: '国网公司',
            inputValue1: 1024,
            timeValue1: ''
          },
          方案二: {
            selectValue1: '国网天津电力工说2',
            selectValue2: '来源2',
            selectValue3: '李四',
            selectValue4: '国网公司2',
            inputValue1: 2048,
            timeValue1: ''
          }
        },
        // ---- 查询内容的选项data start ----
        selectOptions1: [
          {
            value: '国网天津电力工说',
            label: '国网天津电力工说'
          },
          {
            value: '国网天津电力工说2',
            label: '国网天津电力工说2'
          },
          {
            value: '国网天津电力工说3',
            label: '国网天津电力工说3'
          },
          {
            value: '国网天津电力工说4',
            label: '国网天津电力工说4'
          },
          {
            value: '国网天津电力工说5',
            label: '国网天津电力工说5'
          }
        ],
        selectOptions2: [
          {
            value: '来源1',
            label: '来源1'
          },
          {
            value: '来源2',
            label: '来源2'
          },
          {
            value: '来源3',
            label: '来源3'
          }
        ],
        selectOptions3: [
          {
            value: '张三',
            label: '张三'
          },
          {
            value: '李四',
            label: '李四'
          },
          {
            value: '李五',
            label: '李五'
          },
          {
            value: '张五',
            label: '张五'
          },
          {
            value: '张六',
            label: '张六'
          }
        ],
        selectOptions4: [
          {
            value: '国网公司',
            label: '国网公司'
          },
          {
            value: '国网公司2',
            label: '国网公司2'
          },
          {
            value: '国网公司3',
            label: '国网公司3'
          },
          {
            value: '国网公司4',
            label: '国网公司4'
          },
          {
            value: '国网公司5',
            label: '国网公司6'
          }
        ],
        selectOptions5: [
          {
            value: '南网公司电力',
            label: '南网公司电力'
          },
          {
            value: '南网公司电力2',
            label: '南网公司电力2'
          },
          {
            value: '南网公司电力3',
            label: '南网公司电力3'
          },
          {
            value: '南网公司电力4',
            label: '南网公司电力4'
          },
          {
            value: '南网公司电力5',
            label: '南网公司电力5'
          }
        ]
        // ---- 查询内容的选项data end ----
      };
    },
    methods: {
      defaultSelectValue(value) {
        this.slotValue = value;
      },
      cancelDefaultSelectValue(val) {
        this.slotValue = val;
      }
    }
  };
</script>
```

:::

### 查询面板业务 key 配置

通过使用 `SearchPanelSolution` 插入 `solution` 的插槽，可以使用查询方案名称组件。

:::demo `SearchPanelSolution` 传入不同的`yw-key`

```html
<template>
  <div class="section">
    <div class="example">
      <tl-search-panel :fixed-top="60">
        <template v-slot:solution>
          <tl-search-panel-solution
            :vip-address="'/jt/mapp/sample/'"
            :data="slotValue"
            @set-default="defaultSelectValue"
            yw-key="20230320"
            user-id="132325"
          ></tl-search-panel-solution>
        </template>
        <template slot="default">
          <tl-form :model="slotValue" label-suffix="：">
            <tl-form-item label="报销凭证单位">
              <tl-select
                :data="selectOptions1"
                v-model="slotValue.selectValue1"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="凭证来源">
              <tl-select
                :data="selectOptions2"
                v-model="slotValue.selectValue2"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="制证人">
              <tl-select
                :data="selectOptions3"
                v-model="slotValue.selectValue3"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="报销凭证单位">
              <tl-select
                :data="selectOptions4"
                v-model="slotValue.selectValue4"
                placeholder="请选择..."
                id-field="value"
                text-field="label"
              ></tl-select>
            </tl-form-item>
            <tl-form-item label="金额">
              <tl-input-number v-model="slotValue.inputVal1" type="number"></tl-input-number>
            </tl-form-item>
            <tl-form-item button>
              <tl-button>重置</tl-button>
              <tl-button type="primary">查询</tl-button>
            </tl-form-item>
          </tl-form>
        </template>
      </tl-search-panel>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // 面板内容当前值
        slotValue: {
          selectValue1: '',
          selectValue2: '',
          selectValue3: '',
          selectValue4: '',
          inputValue1: '',
          timeValue1: ''
        },
        // 查询方案的保存值
        slotValueGroup: {
          方案一: {
            selectValue1: '国网天津电力工说',
            selectValue2: '来源1',
            selectValue3: '张三',
            selectValue4: '国网公司',
            inputValue1: 1024,
            timeValue1: ''
          },
          方案二: {
            selectValue1: '国网天津电力工说2',
            selectValue2: '来源2',
            selectValue3: '李四',
            selectValue4: '国网公司2',
            inputValue1: 2048,
            timeValue1: ''
          }
        },
        // ---- 查询内容的选项data start ----
        selectOptions1: [
          {
            value: '国网天津电力工说',
            label: '国网天津电力工说'
          },
          {
            value: '国网天津电力工说2',
            label: '国网天津电力工说2'
          },
          {
            value: '国网天津电力工说3',
            label: '国网天津电力工说3'
          },
          {
            value: '国网天津电力工说4',
            label: '国网天津电力工说4'
          },
          {
            value: '国网天津电力工说5',
            label: '国网天津电力工说5'
          }
        ],
        selectOptions2: [
          {
            value: '来源1',
            label: '来源1'
          },
          {
            value: '来源2',
            label: '来源2'
          },
          {
            value: '来源3',
            label: '来源3'
          }
        ],
        selectOptions3: [
          {
            value: '张三',
            label: '张三'
          },
          {
            value: '李四',
            label: '李四'
          },
          {
            value: '李五',
            label: '李五'
          },
          {
            value: '张五',
            label: '张五'
          },
          {
            value: '张六',
            label: '张六'
          }
        ],
        selectOptions4: [
          {
            value: '国网公司',
            label: '国网公司'
          },
          {
            value: '国网公司2',
            label: '国网公司2'
          },
          {
            value: '国网公司3',
            label: '国网公司3'
          },
          {
            value: '国网公司4',
            label: '国网公司4'
          },
          {
            value: '国网公司5',
            label: '国网公司6'
          }
        ],
        selectOptions5: [
          {
            value: '南网公司电力',
            label: '南网公司电力'
          },
          {
            value: '南网公司电力2',
            label: '南网公司电力2'
          },
          {
            value: '南网公司电力3',
            label: '南网公司电力3'
          },
          {
            value: '南网公司电力4',
            label: '南网公司电力4'
          },
          {
            value: '南网公司电力5',
            label: '南网公司电力5'
          }
        ]
        // ---- 查询内容的选项data end ----
      };
    },
    methods: {
      defaultSelectValue(value) {
        this.slotValue = value;
      },
      cancelDefaultSelectValue(val) {
        this.slotValue = val;
      }
    }
  };
</script>
```

:::


### 查询面板查询方案与查询设置搭配使用

通过使用 `SearchPanelSolution` 插入 `solution` 的插槽，可以使用查询方案名称组件。通过使用`show-setting`配置显示查询条件设置，通过使用`setSolution`动态设置查询数据和查询条件

:::demo `SearchPanelSolution` 传入不同的`yw-key`

```html
<template>
  <div class="section">
    <p>查询面板设置</p>
    <div class="example">
      <tl-search-panel :is-fixed="false">
        <template v-slot:solution>
          <tl-search-panel-solution ref="searchPanelSolution" show-solution show-setting :setting-data="data" :state="state" @state-change="onStateChange" :vip-address="'/jt/mapp/sample'" :data="slotValue" yw-key="202300223" user-id="118568" @set-default="defaultSelectValue">
          </tl-search-panel-solution>
        </template>
        <template slot="default">
          <yj-container v-model="slotValue" :data="data" label-suffix="：" :state="state" :cols="3">
            <template slot="button">
              <tl-button>重置</tl-button>
              <tl-button type="primary">查询</tl-button>
            </template>
          </yj-container>
        </template>
      </tl-search-panel>
    </div>
    <tl-button @click="setSolution">设置方案</tl-button>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // 面板内容当前值
        slotValue: {
          selectValue1: '',
          selectValue2: '',
          selectValue3: '',
          selectValue4: '',
          inputValue1: '',
          timeValue1: ''
        },
        data: [{
          name: 'selectValue1',
          dataType: 'tl-select',
          label: '报销凭证单位',
          data: [{
            value: '国网天津电力工说',
            label: '国网天津电力工说'
          }, {
            value: '国网天津电力工说2',
            label: '国网天津电力工说2'
          }, {
            value: '国网天津电力工说3',
            label: '国网天津电力工说3'
          }, {
            value: '国网天津电力工说4',
            label: '国网天津电力工说4'
          }, {
            value: '国网天津电力工说5',
            label: '国网天津电力工说5'
          }],
          'id-field': 'value',
          "text-field": "label"
        },
        {
          name: 'selectValue2',
          dataType: 'tl-select',
          label: '凭证来源',
          data: [{
            value: '来源1',
            label: '来源1'
          }, {
            value: '来源2',
            label: '来源2'
          }, {
            value: '来源3',
            label: '来源3'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue3',
          dataType: 'tl-select',
          label: '制证人',
          data: [{
            value: '张三',
            label: '张三'
          }, {
            value: '李四',
            label: '李四'
          }, {
            value: '李五',
            label: '李五'
          }, {
            value: '张五',
            label: '张五'
          }, {
            value: '张六',
            label: '张六'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue4',
          dataType: 'tl-select',
          label: '报销凭证单位',
          data: [{
            value: '国网公司',
            label: '国网公司'
          }, {
            value: '国网公司2',
            label: '国网公司2'
          }, {
            value: '国网公司3',
            label: '国网公司3'
          }, {
            value: '国网公司4',
            label: '国网公司4'
          }, {
            value: '国网公司5',
            label: '国网公司6'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'inputValue1',
          dataType: 'string',
          label: '金额',
        }
      ],
      state: {
        selectValue1: this.tleadState.SHOW,
        selectValue2: this.tleadState.DISABLED,
        selectValue3: this.tleadState.SHOW,
        selectValue4: this.tleadState.SHOW,
        inputValue1: this.tleadState.SHOW,
      }
      };
    },
    methods: {
      defaultSelectValue(value) {
        this.slotValue = value;
      },
      cancelDefaultSelectValue(val) {
        this.slotValue = val;
      },
      onStateChange(val) {
        this.state = val;
      },
      setSolution() {
        this.$refs.searchPanelSolution.setSolution("8a5c1e102c51-11ee-be95-431de9ac57c6", true);
      }
    }
  };
</script>
```

:::



### 查询面板查询方案存储自定义数据

通过使用 `SearchPanelSolution` 插入 `solution` 的插槽，可以使用查询方案名称组件。传递`set-others-data`方法，返回数据进行设置数据，传递`get-others-data`进行获取方案数据

:::demo

```html
<template>
  <div class="section">
    <div style="display: flex; align-items: center;margin-bottom: 16px;">
      <span>选择分页数量：</span>
      <tl-select
        style="width: 200px;"
        v-model="select"
        :data="selectData"
        id-field="id"
        text-field="text"
        :clearable="false"
      >
      </tl-select>
    </div>
    <div class="example">
      <tl-search-panel :is-fixed="false">
        <template v-slot:solution>
          <tl-search-panel-solution ref="searchPanelSolution" :setOthersData="setGridData" :getOthersData="getGridData" show-solution show-setting :setting-data="data" :state="state" @state-change="onStateChange" :vip-address="'/jt/mapp/sample'" :data="slotValue" yw-key="20240221" user-id="118568" @set-default="defaultSelectValue">
          </tl-search-panel-solution>
        </template>
        <template slot="default">
          <yj-container v-model="slotValue" :data="data" label-suffix="：" :state="state" :cols="3">
            <template slot="button">
              <tl-button>重置</tl-button>
              <tl-button type="primary">查询</tl-button>
            </template>
          </yj-container>
        </template>
      </tl-search-panel>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Others',
  components: {
  },
  data() {
    return {
      slotValue: {
        selectValue1: '',
        selectValue2: '',
        selectValue3: '',
        selectValue4: '',
        inputValue1: '',
        timeValue1: ''
      },
      data: [{
          name: 'selectValue1',
          dataType: 'tl-select',
          label: '报销凭证单位',
          data: [{
            value: '国网天津电力工说',
            label: '国网天津电力工说'
          }, {
            value: '国网天津电力工说2',
            label: '国网天津电力工说2'
          }, {
            value: '国网天津电力工说3',
            label: '国网天津电力工说3'
          }, {
            value: '国网天津电力工说4',
            label: '国网天津电力工说4'
          }, {
            value: '国网天津电力工说5',
            label: '国网天津电力工说5'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue2',
          dataType: 'tl-select',
          label: '凭证来源',
          data: [{
            value: '来源1',
            label: '来源1'
          }, {
            value: '来源2',
            label: '来源2'
          }, {
            value: '来源3',
            label: '来源3'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue3',
          dataType: 'tl-select',
          label: '制证人',
          data: [{
            value: '张三',
            label: '张三'
          }, {
            value: '李四',
            label: '李四'
          }, {
            value: '李五',
            label: '李五'
          }, {
            value: '张五',
            label: '张五'
          }, {
            value: '张六',
            label: '张六'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue4',
          dataType: 'tl-select',
          label: '报销凭证单位',
          data: [{
            value: '国网公司',
            label: '国网公司'
          }, {
            value: '国网公司2',
            label: '国网公司2'
          }, {
            value: '国网公司3',
            label: '国网公司3'
          }, {
            value: '国网公司4',
            label: '国网公司4'
          }, {
            value: '国网公司5',
            label: '国网公司6'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'inputValue1',
          dataType: 'num',
          label: '金额',
          step: 0.01,
          'controls-position': 'right'
        }
      ],
      state: {
        selectValue1: this.tleadState.SHOW,
        selectValue2: this.tleadState.DISABLED,
        selectValue3: this.tleadState.SHOW,
        selectValue4: this.tleadState.SHOW,
        inputValue1: this.tleadState.SHOW,
      },
      select: 50,
      selectData: [
        { id: 50, text: '50条/页' },
        { id: 100, text: '100条/页' },
        { id: 150, text: '150条/页' },
        { id: 200, text: '200条/页' },
      ]
    };
  },
  methods: {
    defaultSelectValue(value) {
      this.slotValue = value;
    },
    cancelDefaultSelectValue(val) {
      this.slotValue = val;
    },
    onStateChange(val) {
      this.state = val;
    },
    async setGridData(){
      return await this.getData();
    },
    getData(){
      // return new Promise((resolve, reject) => {
      //   setTimeout(() => {
      //     resolve([{id: 1, text: '测试'}]);
      //   }, 2000);
      // });
      return { pageSize2: this.select };
    },
    getGridData(data){
      if(data && data.pageSize2){
        this.select = data.pageSize2;
      }
    }
  }
};
</script>


```

:::

### 查询面板查询设置

通过使用 `tl-search-panel-setting`组件，

:::demo

```html
<template>
  <div class="section">
    <p>查询面板设置</p>
    <div class="example">
      <tl-search-panel :is-fixed="false">
        <template v-slot:solution>
          <tl-search-panel-setting ref="searchPanelSolution" show-solution show-setting :setting-data="data" :state="state" @state-change="onStateChange" :vip-address="'/jt/mapp/sample'" :data="slotValue" yw-key="20240810" user-id="118568" @set-default="defaultSelectValue">
          </tl-search-panel-setting>
        </template>
        <template slot="default">
          <yj-container v-model="slotValue" :data="data" label-suffix="：" :state="state" :cols="3">
            <template slot="button">
              <tl-button>重置</tl-button>
              <tl-button type="primary">查询</tl-button>
            </template>
          </yj-container>
        </template>
      </tl-search-panel>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Setting',
  data() {
    return {
      // 面板内容当前值
      slotValue: {
        selectValue1: '',
        selectValue2: '',
        selectValue3: '',
        selectValue4: '',
        inputValue1: '',
        timeValue1: ''
      },
      data: [{
          name: 'selectValue1',
          dataType: 'tl-select',
          label: '报销凭证单位',
          data: [{
            value: '国网天津电力工说',
            label: '国网天津电力工说'
          }, {
            value: '国网天津电力工说2',
            label: '国网天津电力工说2'
          }, {
            value: '国网天津电力工说3',
            label: '国网天津电力工说3'
          }, {
            value: '国网天津电力工说4',
            label: '国网天津电力工说4'
          }, {
            value: '国网天津电力工说5',
            label: '国网天津电力工说5'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue2',
          dataType: 'tl-select',
          label: '凭证来源',
          data: [{
            value: '来源1',
            label: '来源1'
          }, {
            value: '来源2',
            label: '来源2'
          }, {
            value: '来源3',
            label: '来源3'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue3',
          dataType: 'tl-select',
          label: '制证人',
          data: [{
            value: '张三',
            label: '张三'
          }, {
            value: '李四',
            label: '李四'
          }, {
            value: '李五',
            label: '李五'
          }, {
            value: '张五',
            label: '张五'
          }, {
            value: '张六',
            label: '张六'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'selectValue4',
          dataType: 'tl-select',
          label: '报销凭证单位',
          data: [{
            value: '国网公司',
            label: '国网公司'
          }, {
            value: '国网公司2',
            label: '国网公司2'
          }, {
            value: '国网公司3',
            label: '国网公司3'
          }, {
            value: '国网公司4',
            label: '国网公司4'
          }, {
            value: '国网公司5',
            label: '国网公司6'
          }],
          "id-field": "value",
          "text-field": "label"
        },
        {
          name: 'inputValue1',
          dataType: 'num',
          label: '金额',
          step: 0.01,
          'controls-position': 'right'
        }
      ],
      state: {
        selectValue1: this.tleadState.SHOW,
        selectValue2: this.tleadState.DISABLED,
        selectValue3: this.tleadState.SHOW,
        selectValue4: this.tleadState.SHOW,
        inputValue1: this.tleadState.SHOW,
      }
    };
  },
  methods: {
    defaultSelectValue(value) {
      console.log(value);
      this.slotValue = value;
    },
    cancelDefaultSelectValue(val) {
      this.slotValue = val;
    },
    onStateChange(val) {
      this.state = val;
    }
  }
};
</script>


```

:::

### 查询面板查询条件设置

通过使用 `tl-search-panel-setting`组件，

:::demo

```html
<template>
  <div class="section">
    <p>查询面板开启条件设置</p>
    <div class="example">
      <tl-search-panel :is-fixed="false">
        <template v-slot:solution>
          <tl-search-panel-setting ref="searchPanelSolution" show-solution :setting-data="data" :state="state" @state-change="onStateChange" :vip-address="'/jt/mapp/sample'" :data="slotValue" yw-key="20240809" user-id="118568" @set-default="defaultSelectValue" show-condition>
          </tl-search-panel-setting>
        </template>
        <template slot="default">
          <yj-business-container v-model="slotValue" :data="data" label-suffix="：" :state="state" :cols="3">
            <template slot="button">
              <tl-button>重置</tl-button>
              <tl-button type="primary">查询</tl-button>
            </template>
          </yj-business-container>
        </template>
      </tl-search-panel>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Condition',
  data() {
    return {
      // 面板内容当前值
      slotValue: {
        selectValue1: {
          operator: '0',
          value: ''
        },
        selectValue2: {
          operator: '0',
          value: ''
        },
        selectValue3: {
          operator: '0',
          value: ''
        },
        selectValue4: {
          operator: '0',
          value: ''
        },
        inputValue1: '',
        timeValue1: {
          operator: '0',
          value: ''
        }
      },
      data: [{
          name: 'selectValue1',
          dataType: 'condition',
          rangeValue: 'selectValue1.operator,selectValue1.value',
          label: '报销凭证单位',
          option: {
            dataType: 'tl-select',
            data: [{
              value: '国网天津电力工说',
              label: '国网天津电力工说'
            }, {
              value: '国网天津电力工说2',
              label: '国网天津电力工说2'
            }, {
              value: '国网天津电力工说3',
              label: '国网天津电力工说3'
            }, {
              value: '国网天津电力工说4',
              label: '国网天津电力工说4'
            }, {
              value: '国网天津电力工说5',
              label: '国网天津电力工说5'
            }],
            "id-field": "value",
            "text-field": "label"
            }
        },
        {
          name: 'selectValue2',
          dataType: 'condition',
          rangeValue: 'selectValue2.operator,selectValue2.value',
          label: '凭证来源',
          option: {
            dataType: 'tl-select',
            data: [{
              value: '来源1',
              label: '来源1'
            }, {
              value: '来源2',
              label: '来源2'
            }, {
              value: '来源3',
              label: '来源3'
            }],
            "id-field": "value",
            "text-field": "label"
          }
        },
        {
          name: 'selectValue3',
          dataType: 'condition',
          rangeValue: 'selectValue3.operator,selectValue3.value',
          label: '制证人',
          option: {
            dataType: 'string',
          }
        },
        {
          name: 'selectValue4',
          dataType: 'condition',
          rangeValue: 'selectValue4.operator,selectValue4.value',
          label: '报销凭证单位',
          option: {
            dataType: 'tl-select',
            data: [{
              value: '国网公司',
              label: '国网公司'
            }, {
              value: '国网公司2',
              label: '国网公司2'
            }, {
              value: '国网公司3',
              label: '国网公司3'
            }, {
              value: '国网公司4',
              label: '国网公司4'
            }, {
              value: '国网公司5',
              label: '国网公司6'
            }],
            "id-field": "value",
            "text-field": "label"
          }
        },
        {
          name: 'inputValue1',
          // dataType: 'condition',
          // rangeValue: 'inputValue1.operator,inputValue1.value',
          label: '金额',
          dataType: 'num',
          step: 0.01,
          'controls-position': 'right'
        }
      ],
      state: {
        selectValue1: this.tleadState.SHOW,
        selectValue2: this.tleadState.DISABLED,
        selectValue3: this.tleadState.SHOW,
        selectValue4: this.tleadState.SHOW,
        inputValue1: this.tleadState.SHOW,
      }
    };
  },
  methods: {
    defaultSelectValue(value) {
      this.slotValue = value;
    },
    cancelDefaultSelectValue(val) {
      this.slotValue = val;
    },
    onStateChange(val) {
      this.state = val;
    }
  }
};
</script>


```

:::

### SearchPanel Attributes

| 参数        | 说明                        | 类型    | 可选值 | 默认值 |
| ----------- | --------------------------- | ------- | ------ | ------ |
| z-index     | 面板的 z-index 值           | Number  | -      | 99     |
| is-fixed    | 滚动出屏幕时是否 fix 到顶部 | Boolean | -      | true   |
| fixed-top   | fix 时的 top 值             | Number  | -      | 0      |
| show-title  | 显示头部标题                | Boolean | —      | true   |
| show-footer | 是否显示面板按钮区域          | Boolean | —      | true   |
| show-shadow | 是否显示面板的阴影          | Boolean | —      | true   |
| show-border | 是否显示面板的边框          | Boolean | -      | true   |
| init-pin    | 是否初始化时就激活固钉      | Boolean | -      | false  |
| sub-title   | 折叠后查询摘要内容          | String  | -      | ''     |
| offset-top  | 自定义的触发收缩补偿值      | Number  | —      | 0      |
| hide-pin    | 是否隐藏固钉按钮                | Boolean | -      | false  |
| hide-fold    | 是否隐藏折叠按钮                | Boolean | -      | false  |
| user-id ^(8.2.0)    | 用户id，用于缓存用户固钉操作                | String | -      | ''  |

### SearchPanel Slots

| 名称     | 说明                                              |
| -------- | ------------------------------------------------- |
| -        | 自定义查询面板内容插槽                            |
| solution | 查询面板方案插槽，配合 TlSearchPanelSolution 使用 |

### SearchPanel Methods

| 方法名     | 说明                                 | 参数    | 默认值 |
| ---------- | ------------------------------------ | ------- | ------ |
| changeShow | 控制面板展开或者关闭，优先级低于固钉 | Boolean | false  |

### SearchPanel Events

| 事件名称        | 说明                             | 回调参数          |
| --------------- | -------------------------------- | ----------------- |
| change-show     | 控制面板展开或者关闭事件         | (val: bolean)=>{} |
| change-complete | 控制面板展开或者关闭动画完成事件 | (val: bolean)=>{} |

### SearchPanelSolution Attributes

| 参数          | 说明                   | 类型   | 可选值               | 默认值 |
| ------------- | ---------------------- | ------ | -------------------- | ------ |
| data        | 查询条件，必填       | Object | {}     | {}     |
| ywKey       | 业务 key，必填       | String | -      | -      |
| userId      | 功能模块 id，必填    | String | -      | -      |
| vip-address | 模块的服务地址，必填 | String | -      | -      |
| solution-list | 查询方案名称列表，下详 | Array  | —                    | []     |
| selected-name | 当前激活 list 的 name  | String | radio/checkbox/group | ''     |
| show-setting        | 显示查询条件设置按钮       | Boolean | -     | false     |
| show-solution       | 显示查询方案设置按钮      | Boolean | -      | true      |
| set-others-data    | 设置方案其他数据，传递方法返回数据                | Function | ()=>{ return {} }      | -  |
| get-others-data    | 获取方案其他数据，参数是方案数据                | Function | (data)=>{  }      | -  |
| org-code      | 查询方案中 组织字段（开启组件默认方案隔离）    | String | -      | -     |
| operator-field       | 查询设置中作为条件逻辑的字段       | String | -      | 'operator'     |
| value-field      | 查询设置中作为值的字段    | String | -      | 'value'     |
| show-condition       | 查询设置中显示条件比较      | Boolean | -      | false      |

### SearchPanelSolution Methods

| 方法名     | 说明                                 | 参数    | 默认值 |
| ---------- | ------------------------------------ | ------- | ------ |
| setSolution | 动态设置查询方案数据和查询条件 | Boolean | false  |

### SearchPanelSolution Events

| 事件名称       | 说明             | 回调参数 |
| -------------- | ---------------- | -------- |
| delete         | 删除方案事件     | name     |
| set-default    | 设定默认方案事件 | name     |
| state-change | 查询条件改变事件 | obj     |
