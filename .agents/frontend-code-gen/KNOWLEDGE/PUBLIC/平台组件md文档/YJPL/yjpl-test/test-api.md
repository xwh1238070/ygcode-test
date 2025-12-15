# api 接口

## 引入方法

### 1.使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-test
```

## mount()

- **参数：**

  - `{Component} component`
  - `{Object} options`

- **返回值：** `{Wrapper}`

- **选项：**

移步 `选项``

- **用法：**

创建一个包含被挂载和渲染的 YJPL 组件的 `Wrapper`。

**Without options:**

```js
import { mount } from 'yjpl-test'
import Foo from './Foo.yjpl'
describe('Foo', () => {
  it('renders a div', () => {
    const wrapper = mount(Foo)
    expect(wrapper.contains('div')).toBe(true)
  })
})
```

**使用 YJPL 选项：**

```js
import { mount } from 'yjpl-test'
import Foo from './Foo.yjpl'
describe('Foo', () => {
  it('renders a div', () => {
    const wrapper = mount(Foo, {
      propsData: {
        color: 'red'
      }
    })
    expect(wrapper.props().color).toBe('red')
  })
})
```

**固定在 DOM 上：**

```js
import { mount } from 'yjpl-test'
import Foo from './Foo.yjpl'
describe('Foo', () => {
  it('renders a div', () => {
    const div = document.createElement('div')
    document.body.appendChild(div)
    const wrapper = mount(Foo, {
      attachTo: true
    })
    expect(wrapper.contains('div')).toBe(true)
    wrapper.destroy()
  })
})
```

**默认插槽和具名插槽：**

```js
import { mount } from 'yjpl-test'
import Foo from './Foo.yjpl'
import Bar from './Bar.yjpl'
import FooBar from './FooBar.yjpl'
describe('Foo', () => {
  it('renders a div', () => {
    const wrapper = mount(Foo, {
      slots: {
        default: [Bar, FooBar],
        fooBar: FooBar, // 将匹配 `<slot name="FooBar" />`。
        foo: '<div />'
      }
    })
    expect(wrapper.contains('div')).toBe(true)
  })
})
```

**将全局属性存根：**

```js
import { mount } from 'yjpl-test'
import Foo from './Foo.yjpl'
describe('Foo', () => {
  it('renders a div', () => {
    const $route = { path: 'http://www.example-path.com' }
    const wrapper = mount(Foo, {
      mocks: {
        $route
      }
    })
    expect(wrapper.vm.$route.path).toBe($route.path)
  })
})
```

**将组件存根：**

```js
import { mount } from 'yjpl-test'
import Foo from './Foo.yjpl'
import Bar from './Bar.yjpl'
import Faz from './Faz.yjpl'
describe('Foo', () => {
  it('renders a div', () => {
    const wrapper = mount(Foo, {
      stubs: {
        BarFoo: true,ils
        FooBar: Faz,
        Bar: { template: '<div class="stubbed" />' }
      }
    })
    expect(wrapper.contains('.stubbed')).toBe(true)
    expect(wrapper.contains(Bar)).toBe(true)
  })
})
```

##   createLocalYjpl()

- **参数：**

  - `{Object} options`
    - `{Function} errorHandler`

- **返回值：**

  - `{Component}`

- **用法：**

`  createLocalYjpl` 返回一个 YJPL 的类供你添加组件、混入和安装插件而不会污染全局的 YJPL 类。

在组件渲染功能和观察者期间，`errorHandler` 选项可用于处理未捕获的错误。

可通过 `options.localYjpl` 来使用：

**Without options:**

```js
import { createLocalYjpl, shallowMount } from 'yjpl-test'
import YjplUI from 'yjpl-ui'
import Foo from './Foo.yjpl'
const localYjpl =   createLocalYjpl()
localYjpl.use(YjplUI)

const wrapper = shallowMount(Foo, {
  localYjpl,
  mocks: { foo: true }
})
expect(wrapper.vm.foo).toBe(true)
const freshWrapper = shallowMount(Foo)
expect(freshWrapper.vm.foo).toBe(false)
```

**使用 `errorHandler` 选项:**

```js
import {   createLocalYjpl, shallowMount } from 'yjpl-test'
import Foo from './Foo.yjpl'
const errorHandler = (err, vm, info) => {
  expect(err).toBeInstanceOf(Error)
}
const localYjpl =   createLocalYjpl({
  errorHandler
})
// Foo在生命周期挂钩中引发错误
const wrapper = shallowMount(Foo, {
  localYjpl
})
```