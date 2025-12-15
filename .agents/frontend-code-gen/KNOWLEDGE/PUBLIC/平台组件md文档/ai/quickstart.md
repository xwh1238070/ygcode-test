# 快速上手
本节将介绍如何在项目中使用 yjpl-ui-x。

## 引入 yjpl-ui
你可以引入整个 yjpl-ui-x，或是根据需要仅引入部分组件。我们先介绍如何引入完整的 yjpl-ui-x。

## 完整引入
在 main.ts 中写入以下内容：

```typescript
import Yjpl from 'yjpl';
import YjplUiX from 'yjpl-ui-x';
import 'yjpl-uiui-x/lib/theme-chalk/index.css';
import App from './App.yjpl';
import i18n from './i18n';

Yjpl.use(YjplUiX, {
  i18n: (key: string, opts: any) => i18n.t(key, opts)
});

new Yjpl({
  i18n,
  render: h => h(App)
}).$mount('#app');
```

以上代码便完成了 `yjpl-ui-x` 的引入。需要注意的是，样式文件需要单独引入。
