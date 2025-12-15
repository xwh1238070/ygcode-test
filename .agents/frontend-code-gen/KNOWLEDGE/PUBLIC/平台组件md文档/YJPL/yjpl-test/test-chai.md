# chai 断言库
## not
对之后的断言取反
``` js
expect(TEST_Data.foo).to.not.equal('bar');
expect(TEST_Data).to.have.property('foo')
  .and.not.equal('bar');
```
## deep
设置deep标记，然后使用equal和property断言。该标记可以让其后的断言不是比较对象本身，而是递归比较对象的键值对
``` js
expect(TEST_Data).to.deep.equal({ bar: 'baz' });
expect({ foo: { bar: { baz: 'quux' } } })
  .to.have.deep.property('foo.bar.baz', 'quux');

// deep.property中的特殊符号可以使用双反斜杠进行转义（第一个反斜杠是在字符串参数中对第二个反斜杠进行转义，第二个反斜杠用于在property中进行转义）
let deepCss = { '.link': { '[target]': 42 } };
expect(deepCss).to.have.deep.property('\\.link.\\[target\\]', 42);
```
## any
在keys断言之前使用any标记（与all相反）
``` js
expect(TEST_Data).to.have.any.keys('bar', 'baz');
```
## all
在keys断言之前使用all标记（与any相反）
``` js
expect(TEST_Data).to.have.all.keys('bar', 'baz');
```
## .a(type) / .an(type)
a和an断言即可作为语言链又可作为断言使用
``` js
// 类型断言
expect('test').to.be.a('string');
expect({ foo: 'bar' }).to.be.an('object');
expect(null).to.be.a('null');
expect(undefined).to.be.an('undefined');
expect(new Error).to.be.an('error');
expect(new Promise(function (resolve) {resolve(2);})).to.be.a('promise');
expect(new Float32Array()).to.be.a('float32array');
expect(Symbol()).to.be.a('symbol');

// es6 overrides
expect({ [Symbol.toStringTag]: () => 'foo' }).to.be.a('foo');

// language chain
expect(new Date()).to.be.an.instanceof(Date);
```
## .include(value) / contains(value)
include()和contains()即可作为属性类断言前缀语言链又可作为作为判断数组、字符串是否包含某值的断言使用。当作为语言链使用时，常用于key()断言之前
``` js
expect([1, 2, 3]).to.include(2);
expect('foobar').to.include('bar');
expect({ foo: 'bar', hello: 'universe' }).to.include.keys('foo');
```
## ok
断言目标为真值
``` js
expect('everything').to.be.ok;
expect(1).to.be.ok;
expect(false).to.not.be.ok;
expect(null).to.not.be.ok;
```
## true
断言目标为true，注意，这里与ok的区别是不进行类型转换，只能为true才能通过断言
``` js
expect(true).to.be.true;
expect(1).to.not.be.true;
```
## false
断言目标为false
``` js
expect(false).to.be.false;
expect(0).to.not.be.false;
```
## null
断言目标为null
``` js
expect(null).to.be.null;
expect(undefined).to.not.be.null;
```
## undefined
断言目标为undefined
``` js
expect(undefined).to.be.undefined;
expect(null).to.not.be.undefined;
```
## NaN
断言目标为NaN
``` js
expect(Number('foo')).to.be.NaN;
expect(4).to.not.be.NaN;
```
## exist
断言目标存在，即非null也非undefined
``` js
let foo = 'hi',
    bar = null,
    baz;

expect(foo).to.exist;
expect(bar).to.not.exist;
expect(baz).to.not.exist;
```
## empty
断言目标的长度为0。对于数组和字符串，它检查length属性，对于对象，它检查可枚举属性的数量
``` js
expect([]).to.be.empty;
expect('').to.be.empty;
expect({}).to.be.empty;
```
## arguments
断言目标是一个参数对象arguments
``` js
expect(getArgments(1,2,3)).to.be.arguments;
function getArgments () {
  return arguments;
}
```
## equal
断言目标严格等于(===)value。另外，如果设置了deep标记，则断言目标深度等于value
``` js
expect('hello').to.equal('hello');
expect(42).to.equal(42);
expect(1).to.not.equal(true);
expect({ foo: 'bar' }).to.not.equal({ foo: 'bar' });
expect({ foo: 'bar' }).to.deep.equal({ foo: 'bar' });
```
## eql
断言目标深度等于value，相当于deep.equal(value)的简写
``` js
expect({ foo: 'bar' }).to.eql({ foo: 'bar' });
expect([1, 2, 3]).to.eql([1, 2, 3]);
```
## above
断言目标大于（超过）value， 也可接在length后来断言一个最小的长度。相比直接提供长度的好处是提供了更详细的错误消息
``` js
expect(10).to.be.above(5);
expect('foo').to.have.length.above(2);
expect([1, 2, 3]).to.have.length.above(2);
```
## least
断言目标不小于（大于或等于）value， 也可接在length后来断言一个最小的长度。相比直接提供长度的好处是提供了更详细的错误消息
``` js
expect(10).to.be.at.least(10);
expect('foo').to.have.length.of.at.least(3);
expect([1, 2, 3]).to.have.length.of.at.least(3);
```
## below
断言目标小于value， 也可接在length后来断言一个最大的长度。相比直接提供长度的好处是提供了更详细的错误消息
``` js
expect('foo').to.have.length.below(4);
expect([1, 2, 3]).to.have.length.below(4);
```
## most
断言目标不大于（小于或等于）value， 也可接在length后来断言一个最大的长度。相比直接提供长度的好处是提供了更详细的错误消息
``` js
expect('foo').to.have.length.of.at.most(4);
expect([1, 2, 3]).to.have.length.of.at.most(3);
```
## within
断言目标在某个区间内
``` js
expect(7).to.be.within(5, 10);
```
## instanceof(constructor)
断言目标是构造函数constructor的一个实例
``` js
let Tea = function (name) { this.name = name; },
      Chai = new Tea('chai');

expect(Chai).to.be.an.instanceof(Tea);
expect([1, 2, 3]).to.be.an.instanceof(Array);
```
## property
断言目标是否拥有某个名为name的属性，可选地如果提供了value则该属性值还需要严格等于（===）value。如果设置了deep标记，则可以使用点.和中括号[]来指向对象和数组中的深层属性
``` js
// 简单引用
let obj = { foo: 'bar' };
expect(obj).to.have.property('foo');
expect(obj).to.have.property('foo', 'bar');

// 深层引用
let deepObj = {
  green: { tea: 'matcha' },
  tea: ['Chai', 'matcha', { tea: 'konacha' }]
};

expect(deepObj).to.have.deep.property('green.tea', 'matcha');
expect(deepObj).to.have.deep.property('teas[1]', 'matcha');
expect(deepObj).to.have.deep.property('teas[2].tea', 'konacha');
// 如果目标是一个数组，还可以直接使用一个或多个数组下标作为name来在嵌套数组中断言deep.property
let arr = [
  ['chai', 'matcha', 'konacha'],
  [{ tea: 'chai' },
  { tea: 'matcha' },
  { tea: 'konacha' }
  ]
];

expect(arr).to.have.deep.property('[0][1]', 'matcha');
expect(arr).to.have.deep.property('[1][2].tea', 'konacha');

// 此外，property把断言的主语（subject）从原来的对象变为当前属性的值，使得可以在其后进一步衔接其它链式断言（来针对这个属性值进行测试）
expect(obj).to.have.property('foo')
  .that.is.a('string');
expect(deepObj).to.have.property('green')
  .that.is.an('object')
  .that.deep.equals({ tea: 'matcha' });
expect(deepObj).to.have.property('teas')
  .that.is.an('array')
  .with.deep.property('[2]')
  .that.deep.equals({ tea: 'konacha' });

// 注意，只有当设置了deep标记的时候，在property() name中的点（.）和中括号（[]）才必须使用双反斜杠\进行转义（为什么是双反斜杠，在前文有提及），当没有设置deep标记的时候，是不能进行转义的
// 简单指向
let css = { '.link[target]': 42 };
expect(css).to.have.property('.link[target]', 42);

//深度指向
let deepCss = { 'link': { '[target]': 42 } };
expect(deepCss).to.have.deep.property('\\.link\\.[target]', 42);
```
## ownProperty
断言目标拥有名为name的自有属性
``` js
expect('test').to.have.ownProperty('length');
```
## ownPropertyDescription
断言目标的某个自有属性存在描述符对象，如果给定了descroptor描述符对象，则该属性的描述符对象必须与其相匹配
``` js
expect('test').to.have.ownPropertyDescriptor('length');
expect('test').to.have.ownPropertyDescriptor('length', {
  enumerable: false,
  configrable: false,
  writeable: false,
  value: 4
});
expect('test').not.to.have.ownPropertyDescriptor('length', {
  enumerable: false,
  configurable: false,
  writeable: false,
  value: 3
});
// 将断言的主语改为了属性描述符对象
expect('test').to.have.ownPropertyDescriptor('length')
  .to.have.property('enumerable', false);
expect('test').to.have.ownPropertyDescriptor('length')
  .to.have.keys('value');
```
## length
设置.have.length标记作为比较length属性值的前缀
``` js
expect('foo').to.have.length.above(2);
expect([1, 2, 3]).to.have.length.within(2, 4);
```
## lengthOf
断言目标的length属性为期望的值
``` js
expect([1, 2, 3]).to.have.lengthOf(3);
expect('foobar').to.have.lengthOf(6);
```
## match
断言目标匹配到一个正则表达式
``` js
expect('foobar').to.match(/^foo/);
```
## string

``` js
expect('foobar').to.have.string('bar');
```
## keys
断言目标包含传入的属性名
``` js
// 结合any使用，无论是使用have还是使用contains前缀，目标必须至少存在一个传入的属性名才能通过测试。注意，any或者all应当至少使用一个，否则默认为all
expect({ foo: 1, bar: 2, baz: 3 }).to.have.any.keys('foo', 'bar');
expect({ foo: 1, bar: 2, baz: 3 }).to.contains.any.keys('foo', 'bar');

  // 当结合all和have使用时，目标对象必须且仅能拥有全部传入的属性名
expect({ foo: 1, bar: 2, baz: 3 }).to.have.all.keys('foo', 'bar', 'baz');
// 结合all和contains使用， 目标对象必须至少拥有全部传入的属性名，但是它也可以拥有其它属性名
expect({ foo: 1, bar: 2, baz: 3 }).to.contains.all.keys('foo', 'bar');

// 传入string
expect({ foo: 1, bar: 2, baz: 3 }).to.have.any.keys('foo');
// 传入Array
expect({ foo: 1, bar: 2, baz: 3 }).to.have.all.keys(['foo', 'bar', 'baz']);
// 传入Object
expect({ foo: 1, bar: 2, baz: 3 }).to.have.any.keys({ bar: 2, foo: 1 });
```
## throw
断言目标函数会抛出一个指定错误或错误类型（使用instanceOf计算），也可使用正则表达式或者字符串来检测错误消息
``` js
let err = new ReferenceError('this is a bad function');
let fn = function () { throw err; };

expect(fn).to.throw(ReferenceError);
expect(fn).to.throw(Error);
expect(fn).to.throw(/bad function/);
expect(fn).to.not.throw('good function');
expect(fn).to.throw(err);
// 注意，当一个抛错断言被否定了（前面有.not），那么它会从Error构造函数开始依次检查各个可能传入的参数。检查一个只是消息类型不匹配但是已知的错误，合理的方式是先断言该错误存在，然后使用.and后断言错误消息不匹配
expect(fn).to.throw(ReferenceError)
  .and.not.throw(/good function/);
```
## respondTo
断言目标类或对象会响应一个方法
``` js
Date.prototype.bar = function () { return 1;};
const obj = new Date();
expect(Date).to.respondTo('bar');
expect(obj).to.respondTo('bar');

// 如果需要检查一个构造函数是否会响应一个静态方法（挂载在构造函数本身的方法），请查看itself标记
Date.baz = function () { return 1;};
expect(Date).itself.to.respondTo('baz');
```
## itself
设置itself标记，然后使用respondTo断言
``` js
function Foo () {this.name = '1';}
Foo.bar = function () {this.name = '1';};
Foo.prototype.baz = function () { this.name = '1';};

expect(Foo).itself.to.respondTo('bar');
expect(Foo).itself.not.to.respondTo('baz');
```
## satisfy
断言目标值能够让给定的测试器返回真值
``` js
expect(1).to.satisfy(function (num) { return num > 0; });
```
## closeTo
断言目标数字等于expected，或在期望值的+/-delta范围内
``` js
expect(1.5).to.be.closeTo(1, 0.5);
```
## members
断言目标字符串包含另一个字符串
``` js
expect([1, 2, 3]).to.include.members([3, 2]);
expect([1, 2, 3]).to.not.include.members([3, 2, 8]);

expect([4, 2]).to.have.members([2, 4]);
expect([5, 2]).to.not.have.members([5, 2, 1]);

expect([{ id: 1 }]).to.deep.include.members([{ id: 1 }]);
```
## oneOf
断言目标值出现在list数组的某个顶层位置（直接子元素，严格相等）
``` js
expect('a').to.be.oneOf(['a', 'b', 'c']);
expect(9).to.not.be.oneOf(['z']);

// 严格相等，所以对象类的值必须为同一个引用才能被判定为相等
let three = [3];
expect([3]).to.not.be.oneOf([1, 2, [3]]);
expect(three).to.not.be.oneOf([1, 2, [3]]);
expect(three).to.be.oneOf([1, 2, three]);
```
## frozen
断言目标对象是冻结的（无法添加新的属性并且存在的属性不能被删除和修改）
``` js
let frozenObject = Object.freeze({});
expect(frozenObject).to.be.frozen;
expect({}).to.not.be.frozen;
```

## sealed
断言目标对象是封闭的（无法添加新的属性并且存在的属性不能被删除但可以被修改）
``` js
let sealedObject = Object.seal({});
let frozenObject = Object.freeze({});

expect(sealedObject).to.be.sealed;
expect(frozenObject).to.be.sealed;
expect({}).to.not.be.sealed;
```

## extensible
断言目标对象是可扩展的(可以添加新的属性）
``` js
let nonExtensibleObject = Object.preventExtensions({});
let sealedObject = Object.seal({});
let frozenObject = Object.freeze({});

expect({}).to.be.extensible;
expect(nonExtensibleObject).to.not.be.extensible;
expect(sealedObject).to.not.be.extensible;
expect(frozenObject).to.not.be.extensible;
```