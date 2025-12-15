# 升级 yjpl 版本

在 `8.1.0` 版本后，框架进行了调整，删除了部分包和调整了部分依赖，请按以下方式调整。

```typescript
  // 如需升级 8.1.0/8.2.0/8.5.0 使用，请按以下调整使用
  // 1.移除tlead-ui，tlead-ui正式弃用，无需再次引入
  // 2.样式引入调整
  import 'yjpl-ui/lib/theme-chalk/src/index.scss';
  // 改为
  import 'yjpl-ui/lib/theme-chalk/index.css';
  // 3.因 tlead-ui 正式移除，下列JS内使用的组件更改为从 yjpl-ui 引入
  import { SelectEditor, UploadEditor, UploadManager, Export, Import  } from 'tlead-ui';
  // 改为
  import { SelectEditor, UploadEditor, UploadManager, Export, Import  } from 'yjpl-ui';
  // 4.因 yjpl-ui 内的js调用 Dialog 与 标签型组件 Dialog 重名，现改为 YjDialog
  import { Dialog } from 'yjpl-ui';
  // 改为
  import { YjDialog } from 'yjpl-ui';

  // qzz系列组件正式抽离出 yjpl-ui 组件库，独立为 yjpl-qzz，请按以下方式引入
  import Yjpl from 'yjpl';
  import Qzz from 'yjpl-qzz';
  import 'yjpl-qzz/lib/theme/ecp.qzz.css';
  Yjpl.use(Qzz);

  // 所有 ecp.tlead.xxx 格式的前端依赖弃用，调整为 yjpl-xxx
  import Utils from 'ecp.tlead.utils';
  // 改为
  import Utils from 'yjpl-utils';
```