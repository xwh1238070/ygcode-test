# 更新日志

## 8.5.0

#### * 因性能要求，`yjpl-ui` 已移除对 `element-ui` 的依赖，并且支持所属有 `element-ui` 的导出项和组件，建议调整引入代码为 `yjpl-ui`，标签无需调整。

_2025-01_

#### 新增

- yjpl-qzz
  - qzz-grid
    - 新增 支持表格行拖拽调整顺序

#### 优化

- yjpl-ui
  - dialog
    - 优化 去除 `size` 为 `default` 的溢出隐藏
  - export
    - 优化 仅异步导出支持 `csv` 格式

#### Bug 修复

- yjpl-ui
  - select
    - 修复 下拉搜索空选项问题
  - entity-select
    - 修复 多选树无选中样式
  - search-panel
    - 修复 图标丢失问题
  - yj-dialog
    - 修复 弹出窗不关闭问题

_2024-12_

#### 新增

- @yjpl/cli-service
  - 新增 ai分析工具，联合远光小天，对编译错误进行ai分析
  - 新增 `FMP` 参数，用于构建时 `CDN` 资源添加 fmp 前缀
  - 新增 `components` 命令，用于构建项目内 `components` 目录组件库
  - 新增 `extend` 命令，用于项目内 `src/ext` 目录内的扩展打包
  - 新增 `yj-import-component-replace` loader，用于处理 `YJImportComponent` 方法

- yjpl-cli
  - 新增 `components` 命令，用于项目内初始化组件库项目

- yjpl-url
  - 新增 `svGid` 支持，兼容服务端多版本时，接口前缀的替换

- yjpl-ui
  - yj-dialog
    - 新增 `onCancel` 关闭按钮回调

#### 优化

- yjpl-core
  - 优化 `YJImportComponent` 方法支持通过组件列表和组件库名引入

- yjpl-qiankun
  - 优化 销毁后刷新处理
  - 优化 子应用刷新处理

- yjpl-extend
  - 优化 返回实例化对象

- yjpl-qzz
  - list-box
    - 优化 第一列为自然色

- yjpl-ui
  - dialog
    - 优化 `qiankun` 下的弹出窗效果
  - table-tools
    - 优化 去除区域溢出换行

#### Bug 修复

- yjpl-ui
  - entity-select
    - 修复 插槽重复问题

_2024-11_

#### 新增

- yjpl-core
  - 新增 `YJImportComponent` 方法，用于按需加载独立组件或组件包，须在 `CDN` 环境下使用

- yjpl-i18n
  - 新增 全局变量 `_jt_i18n_keys`，用于记录当前页面使用的国际化

- yjpl-qiankun
  - 新增 子应用激活状态
  - 新增 全局 `window` 路由容器激活状态
  - 新增 `router` 、 `routerPrefix` 属性，用于内置路由处理

- yjpl-utils
  - 新增 `getTenantEnable` 方法，用于页面级穿透租户

- yjpl-ui
  - select
    - 新增 `dropdown` 插槽，可配合 `tl-option` 使用
  - export
    - 新增 导出 `csv` 文件类型
  - import
    - 新增 `showImportedMessage` 参数，用于隐藏默认导入提示
    - 新增 `asyncCallback` 参数，用于异步导入后调用成功失败回调
  - qr-bar-code
    - 新增 `v-model` 支持，值改变时自动刷新
  - yj-container-card
    - 新增 卡片视图支持分页

#### 优化

- yjpl-router
  - 优化 重写 `router-view`，用于支持 `qiankun`

- yjpl-qzz
  - qzz-grid
    - 优化 斑马线样式调增为灰色开始
  - list-box
    - 优化 添加 `loading` 状态

- yjpl-ui
  - form
    - 优化 表单非空提示改为跟随国际化
  - range-number
    - 优化 为空处理
  - upload-input
    - 优化 默认 `ywkey` 取 `value`
  - yj-button-list
    - 优化 按钮支持默认悬浮文字提示，默认使用 `title` 属性
  - yj-container-card
    - 优化 视图支持分页同步
    - 优化 卡片视图支持多表头

#### Bug 修复

- yjpl-url
  - 修复 正则导致的请求类型判断错误的问题

- yjpl-ui
  - stpes
    - 修复 步骤条渲染问题
  - upload
    - 修复 上传多个文件时，未携带 `headers` 的问题
  - data-picker
    - 修复 年度切换按钮图标丢失问题
  - entity-select
    - 修复 全清不生效问题

_2024-10_

#### 新增

- @yjpl/spread
  - 新增 发布 `CDN` 资源

- yjpl
  - 新增 因公司出于代码安全考量，生产环境添加无限 `debugger`，可通过 ` ctrl + shift + z` 快捷键关闭

- yjpl-interceptor
  - 新增 `registerOpenWindow` 的拦截器，用于拦截 `openWindow` 方法

- yjpl-template
  - list-template
    - 新增 查询模板支持树收起功能

- yjpl-ui
  - export
    - 新增 `dataAction` 参数，用于指定访问主库还是从库
  - entity-select
    - 新增 `table-toolbar` 插槽，用于扩展表格上方的工具栏
  - yj-button-list
    - 新增 新增 `divided` 属性，支持配置分割线

#### 优化

- yjpl-utils
  - 优化 `windowOpen` 方法支持携带组织相关参数打开
  - 优化 `getAllArgument` 方法支持 `qiankun` 环境处理

- yjpl-ui
  - table-tools
    - 优化 标题支持省略
  - yj-button-list
    - 优化 更多按钮改为图标

#### Bug 修复

- yjpl-utils
  - 修复 跨单位时 `cck` 获取 `ecpDataContext.tokenId` 错误的问题

- yjpl-ui
  - entity-select
    - 修复 搜索框多余的清除图标
  - input
    - 修复 `title` 导致的 `formatting` 执行两次的问题
  - yj-container-card
    - 修复 切换到卡片视图导致卡死的问题

_2024-09_

#### 新增

- yjpl-indexdb
  - 新增 `indexDB` 插件，统一封装 `indexDB` 工具类

- yjpl-qiankun
  - 新增 `YJQiankun` 实例化方法
  - 新增 `destroyed` 回调，用于处理销毁事件

- yjpl-template
  - bill-template
    - 新增 S00342194 支持收纳条
  - query-template
    - 新增 低代码查询模板支持隐藏树

- yjpl-ui
  - anchor
    - 新增 图标类型
  - export
    - 新增 pdf文件类型
    - 新增 支持设置单元格宽度
    - 新增 `beforeData` 和 `afterData` 属性的支持

#### 优化

- yjpl-ui
  - export
    - 优化 导出界面
  - yj-container-card
    - 优化 表头切换支持多表头
    - 优化 卡片视图格式化调用表格的格式化，保证一致性
    - 优化 扩展视图在加载一次后，再次选中后不会再重复加载

#### Bug 修复

- yjpl-tlead-loader
  - 修复 通过 `npm link` 时，找不到模板的情况

- yjpl-utils
  - 修复 科学计数方法精度问题

- yjpl-template
  - 修复 各个模板事件绑定不全的情况

- yjpl-ui
  - entity-select
    - 修复 搜索框多余的清除图标
    - 修复 已选数据返回类型错误，导致的报错问题
  - select
    - 修复 显示更多时，显示箭头的问题
  - search-panel
    - 修复 查询面板重复动画问题
  - yj-container-card
    - 修复 扩展视图显示列设置按钮的问题

_2024-08_

#### 新增

- yjpl-template
  - bill-template
    - 新增 支持分栏分区

- yjpl-ui
  - export
    - 新增 导出 `pdf` 类型文件
    - 新增 `beforeData` 和 `afterData` 属性，用于前置和后置数据扩展
  - message-box
    - 新增 图标提示类型
  - search
    - 新增 `search-colums` 属性，用于列搜索配置
  - search-panel-setting
    - 新增 查询设置组件
  - yj-container-card
    - 新增 `card-format` 属性，用于对卡片内容进行格式化

#### 优化

- yjpl
  - 优化 所有依赖包的打包改为 `rollup`，优化打包资源大小

- yjpl-qzz
  - qzz-grid
    - 优化 筛选面板样式调整

- yjpl-ui
  - form
    - 优化 再次按设计规范，`auto-width` 时，放开输入框只有一行时，按钮区域贴近输入框
  - dialog
    - 优化 详细信息样式
  - export
    - 优化 导出界面调整
    - 优化 异步导出管理界面添加十秒自动刷新功能
  - import
    - 优化 异步导入管理进度条改为真实进度
    - 优化 调整提示界面
  - select-list
    - 优化 使用方式

#### Bug 修复

- yjpl-extend
  - 修复 `prepend` 不生效的问题

- yjpl-ui
  - form
    - 修复 `auto-width` 判断条件错误的情况
  - yj-container-card
    - 修复 B00302728 全屏模式下未自适应高度的问题

_2024-07_

#### 新增

- yjpl-core
  - 新增 `YJImport` 方法，用于动态引入扩展和 `CDN`

- yjpl-cache
  - 新增 统一缓存管理工具

- yjpl-cryp
  - 新增 `SM2`、`SM4` 加密模式

- yjpl-qiankun
  - 新增 封装统一实现方法

- yjpl-template
  - bill-template
    - 新增 模板标题扩展位
  - query-template
    - 新增 标题按钮和底部按钮区域

- yjpl-utils
  - 新增 `openWindow` 方法，用于统一打开新窗口

- yjpl-ui
  - anchor
    - 新增 `type` 属性，用于方向配置
  - entity-select
    - 新增 `use-selected-list` 属性，用于开启关闭已选列表
  - export
    - 新增 异步导出批量清理功能
  - import
    - 新增 异步导入批量清理功能
  - upload
    - 新增 `on-accept-error` 事件，用于处理文件类型不一致文件
  - yj-button-list
    - 新增 `overflow` 属性，用于溢出状态配置
  - yj-container-card
    - 新增 卡片设置功能

#### 优化

- yjpl-url
  - 优化 兼容不规范型 `-web` 结尾的 `vipAddress`，仅为兼容，如果有这种不规范的命名，建议调整

- yjpl-ui
  - button
    - 优化 按钮添加按钮延迟功能，防止重复点击
  - foldbar
    - 优化 样式调整
  - title
    - 优化 二维码支持鼠标悬停时放大

#### Bug 修复

- yjpl-ui
  - form
    - 修复 B00227297 `label` 多行时，必填标识不居中问题
  - yj-container
    - 修复 B00289338 非 `auto-width` 时，样式错乱问题

_2024-06_

#### 新增

- yjpl-qzz
  - qzz-grid
    - 新增 B00250267 表格格式化序号方法

- yjpl-ui
  - entity-select
    - 新增 `async-open` 属性，可用于需要异步操作后再打开弹出窗
  - dialog
    - 新增 `cross-modal` 属性，用于操作可穿透遮罩层，需配合关闭 `modal` 使用
  - input
    - 新增 更多功能

#### 优化

- yjpl-test
  - 优化 线程数，避免构建任务占用过高

- yjpl-ui
  - 去除 `element-ui` 的依赖，优化资源大小
  - entity-select
    - 优化 已选功能不再显示曾未选项
    - 优化 S00269436 支持 `qzz` 表格双击选中
    - 优化 S00268609 树表支持拖拽
  - import
    - 优化 异步导入支持自定义参数配置
  - table-tools
    - 优化 搜索框调整为手动触发
  - yj-container
    - 优化 `text` 类型添加 `text` 属性支持字符自定义

#### Bug 修复

- yjpl-ui
  - entity-select
    - 修复 B00263153 拷贝导致的已选数据问题
  - import
    - 修复 B00247477 导入失败不打开导入记录弹窗
  - tabs
    - 修复 B00237303 修复间距问题
  - form
    - 修复 表单在查询面板的 `dialog` 中，被设为 `auto-width` 的问题
  - upload-panel
    - 修复 B00266107 文件名过长的问题


## 8.2.0

_2024-05_

#### 新增

- @yjpl/spread
  - 新增 `@yjpl/spread` 系列组件

- yjpl-lock
  - 新增 令牌锁插件

- yjpl-template
  - query-template
    - 新增 列表模板支持子表

- yjpl-ui
  - date-picker
    - 新增 B00219550 `secret-value` 属性，用于值加密
    - 新增 S00245236 区间新增添加结束标签
  - tree
    - 新增 S00250551 `context-menu` 属性，用于开启树右键功能
  - yj-message
    - 新增 统一消息提示组件，集成 `message`、`message-box`、`notification` 和 `loading`

#### 优化

- yjpl-skin
  - 优化 `config.json` 支持缓存不走请求

- yjpl-ui
  - 补充所有组件的 `typescript` 描述
  - form
    - 优化 S00250513 `label` 的 `tooltip` 样式
  - input
    - 优化 B00237318 `title` 显示格式化后的

#### Bug 修复

- yjpl-extend
  - 修复 B00227383 `:` 丢失问题

- yjpl-url
  - 修复 B00241621 添加 `member` 错误的问题

- yjpl-utils
  - 修复 B00229274 `numberToCurrency` 方法，缺少参数报错的问题

- yjpl-ui
  - rate
    - 修复 B00175500 图标缺少问题
  - input
    - 修复 B00201969 数值精度超出JS兼容长度的问题
  - import
    - 修复 B00236921 异步导入重复点击问题
  - yj-container-card
    - 修复 B00236975 分组后列设置失效的问题

## 8.2.0

_2024-04_

#### 新增

- @yjpl/cli-service
  - 新增 `yjpl-cryp` 的CDN加载

- yjpl-ui
  - input
    - 新增 输入框特殊字符过滤配置
  - yj-condition
    - 新增 S00225743 条件比较组件

#### 优化

-  yjpl-cryp
  - 优化 合并 4a 加密工具

- yjpl-extend
  - 优化 S00184095 重构代码，优化资源大小

- yjpl-service
  - 优化 B00173649 请求 `headers` 中增加 `orgCode0`

- yjpl-skin
  - 优化 `loadSkinCss` 方法改为公共方法

- yjpl-ui
  - 优化 样式打包速度
  - export
    - 优化 导出文件名由后端返回
  - icon
    - 优化 图标资源合并
  - side
    - 优化 S00184095 优化选中样式

#### Bug 修复

- yjpl-utils
  - 修复 `isNumber` 兼容类型判断 

- yjpl-ui
  - form
    - 修复 `label` 宽度带px导致的宽度计算问题
  - entity-select
    - 修复 异步传入 `col-data` 时，初始选中问题
  - search
    - 修复 搜索框不支持 `size` 的问题
  - steps
    - 修复 步骤条 `point` 不生效的问题
  - yj-button-list
    - 修复 按钮列表子按钮不受状态控制的问题

_2024-03_

#### 新增

- @yjpl/cli-service
  - 新增 动态按需加载 `CDN` 支持

- yjpl-ui
  - sidebar
    - 新增 S00214939 侧边条组件

#### 优化

- yjpl-extend
  - 优化 S00184095 重构代码，优化资源大小

- yjpl-ui
  - menu
    - 优化 B00158198 搜索框会随着收缩隐藏

#### Bug 修复

- yjpl-ui
  - upload-panel
    - 修复 B00174429 出现两个滚动条的问题

_2024-02_

#### 新增

- yjpl-template
  - 新增 列表弹出窗模板

- yjpl-ui
  - import
    - 新增 S00183587 异步导入与导入记录功能
  - search-panel
    - 新增 S00109799 `show-footer` 属性，用于隐藏按钮区域

#### 优化

- yjpl-ui
  - yj-container-card
    - 优化 B00167116 列分组调整为通过显示隐藏实现

#### Bug 修复

- yjpl-utils
  - 修复 B00166472 `formartToZh` 方法，转换大写时生成多余的零和位

_2024-01_

#### 新增

- yjpl-ui
  - export
    - 新增 S00180517 `watermark` 属性，用于配置导出水印
  - search-panel-solution
    - 新增 S00183587 其他数据保存方法
  - yj-container-card
    - 新增 `view-switch` 事件
    - 新增 `card-page-change` 事件

#### 优化

- yjpl-url
  - 优化 获取 `vipaddress` 时将最后的 `web` 去除
  - 优化 B00163088 `mappList` 改为存储于 `sessionStorage`

- yjpl-statistics
  - 优化 添加超时时间，防止服务器无法调通时，编译时间长的问题

- yjpl-ui
  - entity-select
    - 优化 查询区域样式
  - import
    - 优化 B00154649 调整输入框宽度
  - yj-container-card
    - 优化 S00171850 放大时，无论什么表格配置都能撑满

#### Bug 修复

- yjpl-ui
  - from
    - 修复 B00150216 多余 `label-suffix` 问题
    - 修复 `label` 不居右的问题
  - entity-select
    - 修复 B00144552 `qzz` 模式下全选全取消不生效的问题
    - 修复 切换分页时没有选中的问题
  - yj-container-card
    - 修复 B00152443 未传 `title` 仍占位的问题

_2023-12_

#### 新增

- yjpl-url

  - 新增 S00152055 动态获取服务名进行拼接

- yjpl-extend

  - 新增 `mode` 属性，支持直接实例化

- yjpl-ui
  - import
    - 新增 底部扩展功能
    - 新增 上传事件添加参数可供修改
  - page-tabs
    - 新增 右键点击事件 `tab-contextmenu`
    - 新增 页签未超出隐藏左右按钮
  - yj-container-card
    - 新增 S00164770 多表头配置切换功能

#### Bug 修复

- yjpl-ui
  - yj-button-list
    - 修复 B00139081 子按钮 `type` 不生效的问题

_2023-11_

#### 新增

- yjpl-ui
  - from
    - 新增 S00146964 `tooltip` 支持
  - export
    - 新增 `exportValidation` 参数，支持枚举单元格数据有效性
  - yj-container
    - 新增 `error` 参数， `setErrors`，设置错误信息

#### 优化

- yjpl-template

  - bill-template
    - 优化 支持侧边栏左右配置

- yjpl-ui
  - export
    - 优化 `header` 参数支持多表头

#### Bug 修复

- yjpl-ui
  - entity-select
    - 修复 `qzz` 模式下，已选失效的问题
    - 修复 `qzz` 模式下，已选数据错误问题
  - input
    - 修复 B00112245 `change` 和 `input` 返回值不同的问题
    - 修复 最大最小值不生效问题
  - title
    - 修复 `logo`区域多余宽度的问题
  - tree
    - 修复 B00118839 `disabled` 样式丢失问题
  - dialog
    - 修复 遮罩层级问题
  - yj-container-card
    - 修复 B00114722 修复全屏不响应的问题

_2023-10_

#### 优化

- yjpl-template
  - bill-template
    - 优化 单据模板添加锚点组件

#### Bug 修复

- yjpl-tlead-loader

  - 修复 继承模板 class 丢失问题

- yjpl-ui
  - entity-select
    - 修复 `qzz` 模式下不会自动选中的问题

_2023-09_

#### 新增

- yjpl-skin

  - 新增 S00090143 区域图标切换功能

- yjpl-ui
  - tree
    - 新增 S00095475 树切换功能
  - entity-select
    - 新增 S00095475 高级查询功能
    - 新增 S00095475 标题切换功能
  - search-panel
    - 新增 S00104585 `hide-fold` 参数用于隐藏折叠按钮

#### 优化

- yjpl-skin

  - 优化 支持 `fmp` 路径

- yjpl-url

  - 优化 `fmpList` 改为非必填参数

- yjpl-ui
  - select
    - 优化 S00095475 下拉显示规则，最大宽度为 `400px`，否则按最长选项宽度
  - foldbar
    - 优化 B00078047 侧边栏宽度计算方式
  - export
    - 优化 异步导出添加分页
    - 优化 B00075256 导出模板不走异步导出
  - entity-select
    - 优化 添加 `link` 类型的支持
    - 优化 支持 `qzz` 模式下的已选
  - upload-input
    - 优化 上传输入框排序刷新文字修改
  - yj-container
    - 优化 只有表单点击 `enter` 会刷新的问题

#### Bug 修复

- yjpl-ui
  - entity-select
    - 修复 B00074432 选择为空时无法关闭窗口
  - tabs
    - 修复 超长收起显示错误的问题
  - import
    - 修复 导入时返回结果未解密

_2023-08-31_

#### 新增

- yjpl-template

  - list-template
    - 查询模板树添加功能按钮区域

- yjpl-ui
  - select
    - 新增 下拉菜单反方向省略
  - upload-panel
    - 新增 S00074628 拖拽排序功能
    - 新增 S00074628 图片预览切换功能

#### 优化

- yjpl-tlead-loader

  - 优化 多重继承优化

- yjpl-utils

  - 优化 `getAllArgument` 获取 url 参数添加 `qiankun` 环境处理

- yjpl-ui
  - search
    - 优化 添加默认 `placeholder`
  - tree
    - 优化 节点添加 `title`
  - import
    - 优化 错误提示改为服务端返回
  - upload-panel
    - 优化 B00063204 针对上传文件名称特殊字符校验
  - yj-container-card
    - 优化 表格放大后自适应

#### Bug 修复

- yjpl-ui
  - export
    - 修复 国际化导致的标题缺失问题
  - yj-container
    - 修复 表单容器状态切换时，属性顺序错乱的问题
    - 修复 B00063972 深度监听失败的问题
  - yj-container-card
    - 修复 B00054906 表格容器未配置标题区域，显示错位的问题

_2023-08-11_

#### 新增

- yjpl-charts

  - 新增 图表组件

- yjpl-ui
  - dialog
    - 新增 `default` 尺寸
  - popentity
    - 新增 查看已选功能
  - select
    - 新增 下拉支持历史记录
  - import
    - 新增 回调 `onCatchFile`，支持自定义删除文件
  - upload-panel
    - 新增 `word` 和 `excel` 预览功能

_2023-07-28_

#### 新增

- yjpl-service

  - 新增 `cros` 跨 `iframe` 字段处理

- yjpl-gwsecurity

  - 新增 `sm4-cbc` 解密

- yjpl-ui
  - export
    - 新增 异步导出功能
  - popentity
    - 新增 支持树表定位
  - search-panel-solution
    - 新增 查询方案自定义设置
  - magnifier
    - 新增 S00041806 放大器组件
  - yj-container-card
    - 新增 表格放大功能

#### 优化

- yjpl-extend

  - 优化 支持传入 `data`、`propsData` 等参数

- yjpl-gwsecurity

  - 优化 B00040559 gw 安全模式下 `get` 请求添加密签名处理

- yjpl-service

  - 优化 `getSecurity` 的 `orgCode ` 通过 `cookie` 获取
  - 优化 `getSecurity` 没有 `tokenId` 时，不添加 `orgCode`

- yjpl-ui

  - 优化 打包后体积

- yjpl-qzz
  - 优化 B00042413 表格添加列属性 `filterFieldNames` 配置联合筛选
  - 优化 B00043992 过滤添加了 `onRowChanged` 事件
  - 优化 B00042413 复制行的时候获取不到 `dataFields` 的问题

#### Bug 修复

- yjpl-des

  - 修复 与 `yjpl-utils` 循环引用导致的报错问题

- yjpl-ui

  - range-input
    - 修复 区间组件输入框高度的问题
    - 修复 区间组件清除事件错误的问题
  - popentity
    - 修复 B00031483 配置 `return-string` 时，打开未选中的问题
    - 修复 树 `id-field` 获取错误的问题
    - 修复 `close` 事件绑定错误的问题
  - tree
    - 修复 子节点宽度不铺满的问题
  - time-picker
    - 修复 B00012822 时间选择超出屏幕问题
  - import
    - 修复 B00044821 导入方案输入特殊字符报错的问题
  - upload-panel
    - 修复 B00032972 图片因安全问题无法显示和预览的问题

- yjpl-qzz
  - 修复 B00046955 复制行的时候获取不到 `dataFields` 的问题
  - 修复 B00052709 全键盘操作复制后，粘贴内容不全的问题

_2023-07-14_

#### 新增

- yjpl-template

  - query-template
    - 新增 底代码列表模板支持高级查询功能

- yjpl-service
  - 新增 S00060816 请求支持 `sm4-cbc` 解密

#### 优化

- yjpl-ui
  - tree
    - 优化插槽渲染
  - yj-container-card
    - S00023433 现在工具栏区域自带 `tl-table-tools` 组件
    - T00055795 优化卡片、列表配置，新增 `card-option`、`list-option`、`grid-option`，上述属性优先于 `option`
  - yj-dialog
    - 支持通过 `iframe` 打开，当 `url` 为 `string` 类型时

#### Bug 修复

- yjpl-template
  - list-template
    - 修复 按钮未国际化的问题

_2023-06-30_

#### 新增

- yjpl-extend

  - 动态扩展组件，可在运行期扩展页面

- yjpl-ui
  - YjIcon
    - 新增 `yjpl` 图标
  - Calculator
    - 新增 S00041871 计算器组件
  - YjGridEditor
    - 新增 S00042496 表格单行编辑组件

#### 优化

- yjpl-qzz

  - QzzGrid
  - S00042476 表格支持快捷键操作
  - S00042482 表格筛选过滤支持服务端扩展

- yjpl-ui
  - SearchPanel
    - 查询面板组件新增动画完成事件
  - EntitySelect
    - S00011221 通用实体选择支持 QZZ 表格
  - YjContainerCard
    - 优化表单容器卡片、列表配置，支持默认卡片格式

_2023-06-16_

#### 新增

- yjpl-template
  - 新增 表格模板
  - 新增 操作模板
  - 新增 操作树表模板
  - 新增 向导模板
  - 新增 分组表单模板

#### 优化

- yjpl-url

  - 真实环境下去除 `fmp-grm` 前缀路径

- yjpl-ui
  - Dialog
    - 添加大、中、小尺寸
  - SearchPanel
    - 调整按钮间隔
  - Popentity
    - 添加取消事件

_2023-06-02_

#### 新增

- yjpl-ui
  - RangeNumber
    - 新增 S00036603 区间输入组件

#### 优化

- yjpl

  - 添加全局 `i18nModel`

- yjpl-ui4.0-loader

  - 完善代码错误提示

- yjpl-service

  - 优化获取安全开关，默认取 `cookie` 内属性

- yjpl-qzz

  - QzzGrid
    - S00042465 表格支持自定义格式属性
    - U00003992 表格外部插件支持复制粘贴能力

- yjpl-ui
  - SearchPanel
    - 查询面板组件新增动画完成事件
  - EntitySelect
    - S00011221 通用实体选择支持 QZZ 表格

#### Bug 修复

- yjpl-ui
  - EntitySelect
    - 修复 B00025259 修复树型选择时 `clearSelection` 方法报错的问题
  - Select
    - 修复 B00025124 修复高度抖动问题
  - YjButtonList
    - 修复 B00025036 调整按钮间距不统一问题

_2023-05-19_

#### 优化

- yjpl

  - 新增属性扩展

- yjpl-ui
  - Dialog
    - 优化拖拽布局
  - EntitySelect
    - 支持响应式布局和拖拽大小

#### Bug 修复

- yjpl-ui
  - Select
    - 修复 B00006519 修复不显示 `title` 的问题

_2023-05-12_

#### 优化

- yjpl-service

  - 优化获取加密开关的逻辑，现在会从 `cookie` 中获取缓存

- yjpl-print

  - 优化打印接口，改为 `ajax` 调用接口

- yjpl-ui
  - DatePicker
    - 优化 S00017238 新增季度选择类型

#### Bug 修复

- yjpl-ui
  - YjButtonList
    - 修复 B00010486 按钮列表更多时无法控制状态

_2023-04-21_

#### Bug 修复

- yjpl-url
  - 修复 `fmp` 替换，无映射时错误的问题

#### 优化

- yjpl-ui4.0-loader

  - 通过 `babel` 重构，新增模板标签属性添加功能

- yjpl-template

  - U00002891 调整查询模板按钮位置

- yjpl-ui
  - EntitySelect
    - 添加自定义列插槽

_2023-04-07_

#### 新增

- yjpl-ui
  - ImportScheme
    - 新增 S00012099 导入方案组件
  - Layout
    - 新增 S00017124 布局组件
  - YjScheme
    - 新增 S00012099 方案组件

#### Bug 修复

- yjpl-ui
  - DatePicker
    - 修复日期组件判断区间不包含月份的问题
  - Dialog
    - 修复拖拽导致的全局事件失效问题
  - YjButtonList
    - 修复按钮文字出现多余空隙问题
  - YjBusinessContainer
    - 兼容天鸿日期区间格式

#### 优化

- yjpl-service

  - 新增 request、get、post 方法

- yjpl-ui
  - Dialog
    - 调整默认为可拖拽
  - TableTool
    - S00011218 表格工具栏兼容旧版本优化
  - YjDialog
    - 新增隐藏原始按钮属性，支持自定义按钮列表

_2023-03-24_

#### Bug 修复

- yjpl-ui
  - Import
    - 修复上传组件导致无法导入的问题

#### 优化

- yjpl-ui
  - Dialog
    - 调整默认为可拖拽
  - Select
    - 优化现有逻辑，并添加已选 title 文字提示

_2023-03-10_

#### 新增

- yjpl-ui
  - YjTransfer
    - 新增 S00011220 穿梭框组件

#### 优化

- yjpl-ui
  - TableTools
    - S00011218 表格工具栏组件重构

_2023-02-14_

#### 优化

- yjpl-ui
  - Dialog
    - S00011219 弹出框组件支持拖拽和缩放
  - Export
    - S00011216 导出组件支持导出范围，并将导出接口合并为一个
  - Form
    - T60015139 优化表单在查询面板内的按钮自适应规则

_2023-02-10_

#### Bug 修复

- yjpl-ui
  - 修复通过 js 调用 extend 生成的组件组件未注册报错的问题

#### 优化

- yjpl-ui

  - Export
    - S00008970 导出组件支持服务端导出
  - Form
    - T60015139 优化表单在查询面板内的自适应规则
  - Select
    - S00000945 新增 `hide-picker` 属性，支持禁用下拉
  - SearchPanelSolution
    - S00000947 查询方案组件重构
  - TableTools

    - T00008598 表格工具栏组件重构

    ```html
    <!-- 对 slot 名称进行了调整优化，layout 属性相同 -->

    <!-- <template slot="title-area"></template> -->
    <template slot="title"></template>

    <!-- <template slot="contentexplain-area"></template> -->
    <template slot="content"></template>

    <!-- <template slot="button-area"></template> -->
    <template slot="button"></template>

    <!-- <template slot="search-area"></template> -->
    <template slot="search"></template>

    <!-- <template slot="contentswitch-area"></template> -->
    <template slot="content-switch"></template>

    <!-- <template slot="viewswitch-area"></template> -->
    <template slot="view-switch"></template>
    ```

## 8.1.0

_2022-12-31_

#### 新增

- Docs

  - 新增 <router-link :to="{path: '../yjpl/utils/security'}">Security 安全工具类</router-link>
  - 新增 <router-link :to="{path: '../yjpl/utils/skin'}">Skin 主题工具类文档</router-link>
  - 新增 <router-link :to="{path: '../yjpl/utils/print'}">Print 打印工具类文档</router-link>
  - 新增 <router-link :to="{path: '../yjpl/utils/i18n'}">I18n 国际化工具文档</router-link>

- <router-link :to="{path: '../yjpl/utils/url'}">yjpl-url</router-link>

  - 新增 `url` 插件，可用于替换 `fmp` 链接，获取 `vipAddress`、`contextPath`

- yjpl-ui
  - 新增 [组件库支持按需加载](yjpl-ui/quickstart.html#按需引入)
  - Anchor
    - 新增 60010414 纵向锚点组件
  - WaterMark
    - 新增 S00000320 水印组件
  - Upload
    - 新增 60012162 上传组件，内置上传接口
  - YjListPanel
    - 新增 60010487 列表标签筛选组件

#### Bug 修复

- yjpl-ui
  - Input
    - 修复 2202744 数值输入框处理负号
  - Import
    - 修复 组件内 `radio` 失效的问题
  - YjDialog
    - 修复 多次调用时模态窗内页面不刷新的问题

#### 优化

- yjpl-ui

  - 优化 合并 tlead-ui 和 yjpl-ui
    ```typescript
    // 如需升级 8.1.0-M.1 使用，请按以下调整使用
    // 1.移除tlead-ui，tlead-ui正式弃用，无需再次引入
    // 2.样式引入调整
    import 'yjpl-ui/lib/theme-chalk/src/index.scss';
    // 改为
    import 'yjpl-ui/lib/theme-chalk/index.css';
    // 3.因 tlead-ui 正式移除，下列JS内使用的组件更改为从 yjpl-ui 引入
    import { SelectEditor, UploadEditor, UploadManager, Export, Import } from 'tlead-ui';
    // 改为
    import { SelectEditor, UploadEditor, UploadManager, Export, Import } from 'yjpl-ui';
    // 4.因 yjpl-ui 内的js调用 Dialog 与 标签型组件 Dialog 重名，现改为 YjDialog
    import { Dialog } from 'yjpl-ui';
    // 改为
    import { YjDialog } from 'yjpl-ui';
    ```
  - 新的样式规范
  - Dialog
    - 新增 60009636 对话框添加详细信息功能
  - EntitySelect
    - 新增 60008569 通用实体选择控件的表格支持格式化显示
  - Folder
    - 新增 60009638 优化侧边栏面板，新增多种样式
  - Input
    - 新增 2290843 新增 `num-unit` 属性，数值型时生效，返回值会根据该属性调整
  - Menu
    - 新增 60009639 侧边导航支持溢出后缩进
  - SearchPanel
    - 重构 60010489 查询面板组件，优化了折叠动画
  - UploadPanel
    - 新增 60012162 支持图片预览放大、缩小、旋转
  - YjButtonList
    - 新增 60010488 按钮列表支持溢出后缩进更多
  - YjContainer
    - 新增 S00000060 支持项目动态 `slot`
    - 新增 S00000060 支持单项目多组件格式
  - YjListPanel
    - 新增 60010487 列表标签筛选组件

- yjpl-qzz

  - 优化 将 qzz 系列组件抽取出 yjpl-ui，独立为 `yjpl-qzz`

  ```typescript
  // qzz系列组件正式抽离出 yjpl-ui 组件库，独立为 yjpl-qzz，请按以下方式引入
  import Yjpl from 'yjpl';
  import Qzz from 'yjpl-qzz';
  import 'yjpl-qzz/lib/theme/ecp.qzz.css';
  Yjpl.use(Qzz);
  ```

- yjpl-template

  - list-template
    - 新增 S00000065 新增 `eventsModel` ，用于业务分离时事件绑定
  - bill-template
    - 新增 子表支持 tabs 显示

- Other
  - 优化 ecp.tlead.xxx 系列工具库统一改名为 yjpl-xxx，如需更新请调整
