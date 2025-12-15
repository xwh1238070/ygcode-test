# Dialog 通过JS弹框

将yjpl页面放在弹框中展示，仅提供简单的展示场景交互，如果有复杂的交互逻辑，建议使用 `Dialog` 组件。

## 基本用法

```ts
<!------------------被放到dialog中展示的yjpl页面---------------------------->
<template>
  <page class="container">
    <h1>{{text}}</h1>
    <span>这是测试页</span>
  </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
export default class YjDialogPageDemo extends YJPage {
  constructor() {
    super();
  }
  // 接收new Dialog()时的option传参
  props() {
    return ['text'];
  }
  methods() {
    return {
      // 可以通过 confirm 方法返回值
      confirm() {
        return 'test';
      }
    };
  }
}
</script>


<!------------------使用dialog的yjpl页面---------------------------->
<template>
  <page class="container">
    <tl-button @click="handleClick">点击打开 Dialog</tl-button>
  </page>
</template>

<script>
import { YJPage } from 'yjpl-core';
import { YjDialog } from 'yjpl-ui';
import TestPage from './test.yjpl';

export default class YjDialogDemo extends YJPage {
  constructor() {
    super();
  }
  methods() {
    return {
      handleClick() {
        new YjDialog({
          title: '提示',
          url: TestPage,
          width: '150px',
          option: {
            text: 'hello dialog!'
          },
          // 第一个参数为页面的 confirm 方法返回的值，第二个参数是页面对象
          onConfirm(res: any, ref: any) {
            console.info('confirm.....', res, ref);
          },
          onClose(res: any) {
            console.info('close.....', res);
          }
        });
      }
    };
  }
}
</script>
```
:::tip
该组件基于
<router-link :to="{ path: '../../yjpl/docs/container/dialog' }">tl-dialog</router-link>
二次封装，属性事件用法相同，下方只列举该组件特有属性。

:::

### Attributes
| 参数 | 说明 | 类型 | 可选值 | 默认值
| - | - | - | - | -
| title | 弹框标题 | string | - | -
| url | 要在弹框中展示的页面的路径，为 `string` 时通过 `iframe` 打开 | string | yjpl | - | -
| option | 页面props里接收的参数 | object | - | -
| hideConfirm | 隐藏确认按钮 | boolean | - | -
| hideCancel | 隐藏取消按钮 | boolean | - | -
| buttonList | 额外按钮配置，配置方式与 `yj-button-list` 组件相同 | Array | - | -

### Events
| 事件名称 | 说明 | 回调参数
| - | - | - |
| onConfirm | 点击确认时的回调函数 | -
| onClose | 点击取消时的回调函数 | -
