import { YJBusiness } from 'yjpl-core';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      // 表单双向绑定数据
      query: {},
      // qzz表格双向绑定数据
      grid: []
    };

    this.metaModel = {
      start: {
        label: '报表任务：',
        dataType: 'combobox',
        data: [
          { id: 1, text: '1', label: '任务1' },
          { id: 2, text: '2', label: '任务2' },
        ],
        filterable: true,
        allowCreate: true,
        defaultFirstOption: true,
        idField: 'id',
        textField: 'label',
        clearable: false
      },
      units: {
        name: 'units',
        dataType: 'combobox',
        label: '所属单位：',
        data: [
          { id: '1', text: '单位1' },
          { id: '2', text: '单位2' }
        ],
        idField: 'id',
        textField: 'text'
      },
    };
    this.stateModel = {};
    this.dataSource = {};
  }
  getData() {
    return {
      dataModel: this.dataModel,
      metaModel: this.metaModel,
      stateModel: this.stateModel,
      dataSource: this.dataSource
    };
  }
}
export default ListPageBs;