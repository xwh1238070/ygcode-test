import { YJBusiness } from 'yjpl-core';

class ListPageBs extends YJBusiness {
  constructor() {
    super();

    this.dataModel = {
      
    };

    this.metaModel = {   
      form:[
        {
          title:'分组标签A',
          isCollapse:true,
          collapsible:true,
          children:[
            {
              name: 'lableType2',
              dataType: 'string',
              label: '标签名称：',
            },
            {
              name: 'sort2',
              dataType: 'entity-select',
              label: '项目分类：',
              data: [
                { dxid: 1570001118, dxmc: '江苏电力' },
                { dxid: 1570001098, dxmc: '湖北电力' },
                { dxid: 1570001113, dxmc: '大宇物流' },
                { dxid: 1570001115, dxmc: '风尖' },
                { dxid: 1570001114, dxmc: '威然' },
                { dxid: 1570001121, dxmc: '喆大电力' }
              ],
              // qzz表格列头设置
              colData: [
                { label: 'id', prop: 'dxid' },
                { label: '名称', prop: 'dxmc', selected: true },
                { label: '是否归档', prop: 'isArchive', data: { 1: '归档', 0: '不归档' } }
              ],
              tableData: [
                { dxid: 1570001118, dxmc: '江苏电力', isArchive: 1 },
                { dxid: 1570001098, dxmc: '湖北电力', isArchive: 0 },
                { dxid: 1570001113, dxmc: '大宇物流', isArchive: 1 },
                { dxid: 1570001115, dxmc: '风尖', isArchive: 0 },
                { dxid: 1570001114, dxmc: '威然', isArchive: 1 },
                { dxid: 1570001121, dxmc: '喆大电力', isArchive: 0 }
              ],
              idField: 'dxid',
              textField: 'dxmc',
            },
            {
              name: 'number',
              dataType: 'num',
              label: '标签数量：',
            },
            {
              name: 'checkboxlabel',
              label: '多选标签：',
              cols:2,
                children: [
                  {

                    dataType: 'checkbox',
                    name: 'checked1',
                    label: '选项A'
                  },
                  {
                    dataType: 'checkbox',
                    name: 'checked2',
                    label: '选项B'
                  },
                  {
                    dataType: 'checkbox',
                    name: 'checked3',
                    label: '选项F'
                  }
                ]
            },
            {
              name: 'radiolabel',
              label: '单选标签：',
              cols:2,
                children: [
                  {
                    dataType: 'radio',
                    name: 'radio',
                    label: '选项C',
                  },
                  {
                    dataType: 'radio',
                    name: 'radio',
                    label: '选项D',
                  },
                ]
            },
          ]
        },       
        {
          title:'分组标签B',
          isCollapse:true,
          collapsible:true,
          children:[
            {
              name: 'labelName',
              dataType: 'combobox',
              label: '标签名称：',
            },
            {
              name: 'formLable',
              label: '多选平铺：',
              cols:2,
               children: [
                  {
                    dataType: 'checkbox',
                    name: 'checked1',
                    label: '备选项1'
                  },
                  {
                    dataType: 'checkbox',
                    name: 'checked2',
                    label: '备选项2'
                  },
                  {
                    dataType: 'checkbox',
                    name: 'checked3',
                    label: '备选项3'
                  },
                  {
                    dataType: 'checkbox',
                    name: 'checked4',
                    label: '备选项4'
                  },
                ]
            },
            {
              name: 'makeDate1',
              dataType: 'string',
              type: 'combobox',
              label: '标签名称：',
            },
            {
              name: 'sort1',
              dataType: 'entity-select',
              label: '项目分类：',
              data: [
                { dxid: 1570001118, dxmc: '江苏电力' },
                { dxid: 1570001098, dxmc: '湖北电力' },
                { dxid: 1570001113, dxmc: '大宇物流' },
                { dxid: 1570001115, dxmc: '风尖' },
                { dxid: 1570001114, dxmc: '威然' },
                { dxid: 1570001121, dxmc: '喆大电力' }
              ],
              // qzz表格列头设置
              colData: [
                { label: 'id', prop: 'dxid' },
                { label: '名称', prop: 'dxmc', selected: true },
                { label: '是否归档', prop: 'isArchive', data: { 1: '归档', 0: '不归档' } }
              ],
              tableData: [
                { dxid: 1570001118, dxmc: '江苏电力', isArchive: 1 },
                { dxid: 1570001098, dxmc: '湖北电力', isArchive: 0 },
                { dxid: 1570001113, dxmc: '大宇物流', isArchive: 1 },
                { dxid: 1570001115, dxmc: '风尖', isArchive: 0 },
                { dxid: 1570001114, dxmc: '威然', isArchive: 1 },
                { dxid: 1570001121, dxmc: '喆大电力', isArchive: 0 }
              ],
              idField: 'dxid',
              textField: 'dxmc',
            },
            {
              name: 'lableType2',
              dataType: 'combobox',
              label: '标签类型：',
            },
          ]
        }
      ],     
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
  };
  getFormData(){
    return this.metaModel.form
  }
 
}
export default ListPageBs;