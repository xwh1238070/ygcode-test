# demo 样例

## 组件单元测试编写规范
单元测试必须放置在 `tests/specs` 目录内，且命名必须为 `{单元测试名称}.specs.ts`。

## 组件单元测试样例

``` js
// 通过 yjpl-test 引入 chai 的 expect，和 mock 数据使用的 sinon
import { expect, sinon } from 'yjpl-test';
import { YJService } from 'yjpl-core';
// 需要针对编写的业务类
import AccBookSetYw from '@/pages/accBookSet/AccBookSetYw';

// 能帮我针对这个ts生成一个karma单元测试吗，需要每个方法和每个逻辑分支的测试，mock数据使用 sinon
describe('AccBookSetYw', () => {
  let accBookSetYw: AccBookSetYw;
  let svr: YJService;
  let context: any;

  beforeEach(async () => {
    // 实例化业务类
    accBookSetYw = new AccBookSetYw();
    // 实例话 service
    svr = new YJService();
    // mock 掉 业务类中的 servcie
    sinon.stub(accBookSetYw, 'svr').value(svr);
  });

  afterEach(() => {
    sinon.restore();
  });

  // 能帮我针对 loadTopAndCurDldwData 编写一个单元测试吗
  describe('loadTopAndCurDldwData', () => {
    it('是否加载数据成功', async () => {
      console.log(context, 1111);
      const mockResult = {
        data: {
          errMsg: '',
          topDldw: 'topDldw',
          bookData: [],
        },
      };
      const doGetStub = sinon.stub(svr, 'doGet').resolves(mockResult);
      await accBookSetYw.loadTopAndCurDldwData();
      expect(doGetStub.calledOnce).to.be.true;
      expect(accBookSetYw.dataModel.TopDldw).to.equal(mockResult.data.topDldw);
      expect(accBookSetYw.dataModel.bookData).to.equal(mockResult.data.bookData);
    });

    it('测试返回是失败场景', async () => {
      const mockResult = {
        data: {
          errMsg: '加载账簿数据失败！',
        },
      };
      const doGetStub = sinon.stub(svr, 'doGet').resolves(mockResult);
      await accBookSetYw.loadTopAndCurDldwData();
      expect(doGetStub.calledOnce).to.be.true;
      expect(accBookSetYw.dataModel.messageShow).to.equal('加载账簿数据失败！');
      expect(accBookSetYw.dataModel.messageIndex).to.be.true;
    });
  });

  // 能帮我针对 queryMoneyInfo 编写一个单元测试吗
  describe('queryMoneyInfo', () => {
    it('查询币种成功', async () => {
      const mockResult = {
        data: [
          { moneydm: '1', moneymc: 'USD' },
          { moneydm: '2', moneymc: 'CNY' },
        ],
      };
      const doGetStub = sinon.stub(svr, 'doGet').resolves(mockResult);
      const result = await accBookSetYw.queryMoneyInfo();
      expect(doGetStub.calledOnce).to.be.true;
      expect(result).to.deep.equal([
        { id: '1', text: 'USD' },
        { id: '2', text: 'CNY' },
      ]);
    });

    it('查询币种失败判断', async () => {
      const mockResult = {
        data: {
          errMsg: 'Query money info failed',
        },
      };
      const doGetStub = sinon.stub(svr, 'doGet').resolves(mockResult);
      try {
        await accBookSetYw.queryMoneyInfo();
      } catch (error) {
        expect(doGetStub.calledOnce).to.be.true;
        expect(error).to.equal('Query money info failed');
      }
    });
  });

  // 能帮我针对 addData 编写一个单元测试吗
  describe('addData', () => {
    it('添加 data 是否成功', async () => {
      const mockGrid = {
        append: sinon.stub()
      };
      const mockResult = {
        data: {
          errMsg: ''
        }
      };
      const doGetStub = sinon.stub(svr, 'doGet').resolves(mockResult);
      await accBookSetYw.addData(mockGrid);
      expect(doGetStub.calledOnce).to.be.true;
      expect(mockGrid.append.calledOnce).to.be.true;
    });

    it('添加失败时处理', async () => {
      const mockGrid = {
        append: sinon.stub()
      };
      const mockResult = {
        data: {
          errMsg: '添加失败'
        }
      };
      const doGetStub = sinon.stub(svr, 'doGet').resolves(mockResult);
      try {
        await accBookSetYw.addData(mockGrid);
      } catch (error) {
        expect(doGetStub.calledOnce).to.be.true;
        expect(error).to.equal('添加失败');
      }
    });
  });

  // 能帮我针对 gridData2JavaObject 编写一个单元测试吗
  describe('gridData2JavaObject', () => {
    it('能通过方法将表格数据转为 java 对象', () => {
      const mockGridData = [
        { field1: 'value1', field2: 'value2' },
        { field1: 'value3', field2: 'value4' },
      ];
      const expectedJavaObject = [
        { field1: 'value1', field2: 'value2' },
        { field1: 'value3', field2: 'value4' },
      ];
      const result = accBookSetYw.gridData2JavaObject(mockGridData);
      expect(result).to.deep.equal(expectedJavaObject);
    });
  });

  // 能帮我针对 getGridData 编写一个单元测试吗
  describe('getGridData', () => {
    it('should get grid data correctly', () => {
      const mockGridData = [
        { field1: 'value1', field2: 'value2' },
        { field1: 'value3', field2: 'value4' },
      ];
      const grid = {
        getData: sinon.stub().returns(mockGridData),
      };
      const result = accBookSetYw.getGridData();
      expect(grid.getData.calledOnce).to.be.true;
      expect(result).to.deep.equal(mockGridData);
    });
  });
});

```