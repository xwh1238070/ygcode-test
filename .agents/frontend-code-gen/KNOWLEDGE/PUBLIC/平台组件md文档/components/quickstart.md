# 快速上手
本节将介绍如何在项目中使用 yjpl-ui。

## 引入 yjpl-ui
你可以引入整个 yjpl-ui，或是根据需要仅引入部分组件。我们先介绍如何引入完整的 yjpl-ui。

## 完整引入
在 main.ts 中写入以下内容：

```typescript
import Yjpl from 'yjpl';
import YjplUI from 'yjpl-ui';
import 'yjpl-ui/lib/theme-chalk/index.css';
import App from './App.yjpl';
import i18n from './i18n';

Yjpl.use(YjplUi, {
  i18n: (key: string, opts: any) => i18n.t(key, opts)
});

new Yjpl({
  i18n,
  render: h => h(App)
}).$mount('#app');
```

以上代码便完成了 `yjpl-ui` 的引入。需要注意的是，样式文件需要单独引入。

## 按需引入
借助 [babel-plugin-component](https://www.npmjs.com/package/babel-plugin-component)，我们可以只引入需要的组件，以达到减小项目体积的目的。

首先，安装 babel-plugin-component：

```bash
npm install babel-plugin-component -D
```

然后，将 .babelrc 修改为：

```json
{
  "plugins": [
    [
      "component",
      {
        "libraryName": "yjpl-ui",
        "styleLibraryName": "theme-chalk"
      }
    ]
  ]
}
```

接下来，如果你只希望引入部分组件，比如 Button 和 Select，那么需要在 main.js 中写入以下内容：

```javascript
import Yjpl from 'yjpl';
import { Button, Select } from 'yjpl-ui';
import App from './App.yjpl';

Yjpl.component(Button.name, Button);
Yjpl.component(Select.name, Select);
/* 或写为
 * Yjpl.use(Button)
 * Yjpl.use(Select)
 */

new Yjpl({
  render: h => h(App)
}).$mount('#app');
```

完整组件列表和引入方式（完整组件列表以 components.json 为准）

```javascript
import Yjpl from 'yjpl';
import {
  Affix,
  Alert,
  Anchor,
  Button,
  ButtonGroup,
  Calendar,
  Card,
  CornerMark,
  DatePicker,
  Dialog,
  Drawer,
  EntitySelect,
  ErrorPage,
  Export,
  Foldbar,
  Folder,
  Form,
  FormItem,
  Import,
  Input,
  InputNumber,
  Menu,
  MenuItem,
  MenuItemGroup,
  MenuNest,
  PageTabs,
  Popentity,
  Popover,
  QrBarCode,
  Search,
  SearchPanel,
  SearchPanelSolution,
  Section,
  Select,
  SelectEditor,
  SelectList,
  Step,
  Steps,
  Submenu,
  Switch,
  TabPane,
  TableTools,
  Tabs,
  Tag,
  TemplateSkeleton,
  Textarea,
  Title,
  Toolbar,
  Tree,
  UploadChunk,
  UploadEditor,
  UploadInput,
  UploadManager,
  UploadPanel,
  UploadTable,
  YjButtonList,
  YjContainer,
  YjContainerCard,
  YjDialog,
  YjFilterPanel,
  YjPanel
} from 'yjpl-ui';

Yjpl.use(Affix);
Yjpl.use(Alert);
Yjpl.use(Anchor);
Yjpl.use(Button);
Yjpl.use(ButtonGroup);
Yjpl.use(Calendar);
Yjpl.use(Card);
Yjpl.use(CornerMark);
Yjpl.use(DatePicker);
Yjpl.use(Dialog);
Yjpl.use(Drawer);
Yjpl.use(EntitySelect);
Yjpl.use(ErrorPage);
Yjpl.use(Foldbar);
Yjpl.use(Folder);
Yjpl.use(Form);
Yjpl.use(FormItem);
Yjpl.use(Input);
Yjpl.use(InputNumber);
Yjpl.use(Menu);
Yjpl.use(MenuItem);
Yjpl.use(MenuItemGroup);
Yjpl.use(MenuNest);
Yjpl.use(PageTabs);
Yjpl.use(Popentity);
Yjpl.use(Popover);
Yjpl.use(QrBarCode);
Yjpl.use(Search);
Yjpl.use(SearchPanel);
Yjpl.use(SearchPanelSolution);
Yjpl.use(Section);
Yjpl.use(Select);
Yjpl.use(SelectList);
Yjpl.use(Step);
Yjpl.use(Steps);
Yjpl.use(Submenu);
Yjpl.use(Switch);
Yjpl.use(TabPane);
Yjpl.use(TableTools);
Yjpl.use(Tabs);
Yjpl.use(Tag);
Yjpl.use(TemplateSkeleton);
Yjpl.use(Textarea);
Yjpl.use(Title);
Yjpl.use(Toolbar);
Yjpl.use(Tree);
Yjpl.use(UploadChunk);
Yjpl.use(UploadInput);
Yjpl.use(UploadPanel);
Yjpl.use(UploadTable);
Yjpl.use(YjButtonList);
Yjpl.use(YjContainer);
Yjpl.use(YjContainerCard);
Yjpl.use(YjFilterPanel);
Yjpl.use(YjPanel);

Yjpl.prototype.$import = Import;
Yjpl.prototype.$export = Export;
Yjpl.prototype.$dialog = YjDialog;
```
