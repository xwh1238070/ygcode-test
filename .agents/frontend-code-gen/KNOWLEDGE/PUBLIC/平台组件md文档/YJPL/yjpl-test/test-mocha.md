# mocha 框架

## 异步代码
使用mocha测试异步代码是再简单不过了。只需要在测试完成的时候调用一下回调函数即可。通过添加一个回调函数(通常命名为done)给it()方法，Mocha就会知道，它应该等这个函数被调用的时候才能完成测试。

``` js
describe('User', function() {
  describe('#save()', function() {
    it('should save without error', function() {
      var user = new User('Luna')
      user.save(function(err) {
        if(err) done(err);
        else done()
      })
    })
  })
})
```

也可以让事情变得更简单，因为done()函数接收一个err，所以，我们可以直接按照下面的使用：

``` js
describe('User', function() {
  describe('#save()', function() {
    it('should save without error', function(done) {
      var user = new User('Luna')
      user.save(done)
    })
  })
})
```

## 钩子函数
Mocha提供了一些钩子函数:before(),after(),beforeEach()和afterEach()。这些钩子函数可以用于设置测试的先决条件或者对测试进行清理。

1. before：所有测试执行前执行（只执行一次）
2. beforeEach：每个单元测试执行前执行
3. afterEach：每个单元测试执行后执行
4. after：所有单元测试执行完执行（只执行一次）
``` js 
import { expect } from 'yjpl-test';
describe('mock hooks', function () {

  // 钩子将按其定义的顺序运行，视情况而定; 
  // 所有before()钩子运行（一次），然后任何beforeEach()钩子，测试，任何afterEach()钩子，最后after()钩子（一次）。

  before(function() {
    // 在这个区块内的所有测试之前运行
  })
  after(function () {
    // 在这个区块内的所有测试之后运行
  })
  beforeEach(function () {
    // 在这个区块内的每个测试运行之前运行
  })
  afterEach(function () {
    // 在这个区块内的每个测试之后运行
  })
});
```