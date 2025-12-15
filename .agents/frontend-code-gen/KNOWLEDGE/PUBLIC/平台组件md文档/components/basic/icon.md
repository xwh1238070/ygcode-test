## Icon 图标

提供了一套常用的图标集合。

### YJPL 图标使用方法
直接通过设置类名为 `yj-p-success` 来使用即可。例如：

:::demo

```html
<template>
  <i class="yj-p-success"></i>
  <i class="el-icon-house"></i>
  <i class="iconfont icondayinshehzhi"></i>
  <tl-button type="primary" icon="yj-p-edit">编辑</tl-button>
</template>
```
:::

### YJPL 图标集合
:::demo 

```html
<template>
  <ul class="icon-list">
    <li v-for="icon in yjIcons" :title="icon.name" :key="icon.font_class">
      <span>
        <i :class="icon.font_class.startsWith('icon') ? `iconfont ${icon.font_class}` : icon.font_class"></i>
        <span class="icon-name">{{ icon.font_class.startsWith('icon') ? `iconfont ${icon.font_class}` : icon.font_class }}</span>
      </span>
    </li>
  </ul>
</template>

<script>
  export default {
    data() {
      return {
        yjIcons: [
          {
            "icon_id": "44341165",
            "name": "语音",
            "font_class": "yj-p-voice",
            "unicode": "e7bd",
            "unicode_decimal": 59325
          },
          {
            "icon_id": "44340830",
            "name": "提示词",
            "font_class": "yj-p-hint",
            "unicode": "e7bf",
            "unicode_decimal": 59327
          },
          {
            "icon_id": "44340829",
            "name": "发送",
            "font_class": "yj-p-sending",
            "unicode": "e7c0",
            "unicode_decimal": 59328
          },
          {
            "icon_id": "44340834",
            "name": "查询面板",
            "font_class": "yj-p-querypanel",
            "unicode": "e7bb",
            "unicode_decimal": 59323
          },
          {
            "icon_id": "44340833",
            "name": "AI",
            "font_class": "yj-p-ai",
            "unicode": "e7bc",
            "unicode_decimal": 59324
          },
          {
            "icon_id": "44340831",
            "name": "单据等",
            "font_class": "yj-p-invoices",
            "unicode": "e7be",
            "unicode_decimal": 59326
          },
          {
            "icon_id": "42930696",
            "name": "el-icon-setting",
            "font_class": "el-icon-setting",
            "unicode": "e7b3",
            "unicode_decimal": 59315
          },
          {
            "icon_id": "42930699",
            "name": "el-icon-bottom-left",
            "font_class": "el-icon-bottom-left",
            "unicode": "e7b4",
            "unicode_decimal": 59316
          },
          {
            "icon_id": "42930698",
            "name": "el-icon-bottom-right",
            "font_class": "el-icon-bottom-right",
            "unicode": "e7b5",
            "unicode_decimal": 59317
          },
          {
            "icon_id": "42930697",
            "name": "el-icon-unlock",
            "font_class": "el-icon-unlock",
            "unicode": "e7b6",
            "unicode_decimal": 59318
          },
          {
            "icon_id": "42930695",
            "name": "el-icon-link",
            "font_class": "el-icon-link",
            "unicode": "e7b7",
            "unicode_decimal": 59319
          },
          {
            "icon_id": "42930694",
            "name": "el-icon-folder-delete",
            "font_class": "el-icon-folder-delete",
            "unicode": "e7b8",
            "unicode_decimal": 59320
          },
          {
            "icon_id": "42930693",
            "name": "el-icon-d-arrow-right",
            "font_class": "el-icon-d-arrow-right",
            "unicode": "e7b9",
            "unicode_decimal": 59321
          },
          {
            "icon_id": "42930691",
            "name": "el-icon-d-arrow-left",
            "font_class": "el-icon-d-arrow-left",
            "unicode": "e7ba",
            "unicode_decimal": 59322
          },
          {
            "icon_id": "42514483",
            "name": "数智人",
            "font_class": "yj-p-digitalhumans",
            "unicode": "e7b2",
            "unicode_decimal": 59314
          },
          {
            "icon_id": "42381992",
            "name": "监测",
            "font_class": "yj-p-jiance",
            "unicode": "e7b0",
            "unicode_decimal": 59312
          },
          {
            "icon_id": "42379427",
            "name": "图片",
            "font_class": "yj-p-pic",
            "unicode": "e7b1",
            "unicode_decimal": 59313
          },
          {
            "icon_id": "42012590",
            "name": "移除",
            "font_class": "yj-p-remove",
            "unicode": "e7af",
            "unicode_decimal": 59311
          },
          {
            "icon_id": "41806885",
            "name": "错误",
            "font_class": "yj-p-error",
            "unicode": "e7ae",
            "unicode_decimal": 59310
          },
          {
            "icon_id": "41737927",
            "name": "加签2",
            "font_class": "yj-p-countersign",
            "unicode": "e7ac",
            "unicode_decimal": 59308
          },
          {
            "icon_id": "41737795",
            "name": "文档",
            "font_class": "yj-p-wendang",
            "unicode": "e7ab",
            "unicode_decimal": 59307
          },
          {
            "icon_id": "41737769",
            "name": "全清",
            "font_class": "yj-p-quanqing",
            "unicode": "e7aa",
            "unicode_decimal": 59306
          },
          {
            "icon_id": "41656883",
            "name": "会签",
            "font_class": "yj-p-hq",
            "unicode": "e6ec",
            "unicode_decimal": 59116
          },
          {
            "icon_id": "41656886",
            "name": "机器人",
            "font_class": "yj-p-robot",
            "unicode": "e6ed",
            "unicode_decimal": 59117
          },
          {
            "icon_id": "41656885",
            "name": "会签通过",
            "font_class": "yj-p-hqtongguo",
            "unicode": "e6ee",
            "unicode_decimal": 59118
          },
          {
            "icon_id": "41656884",
            "name": "计算器",
            "font_class": "yj-p-calculator",
            "unicode": "e6ef",
            "unicode_decimal": 59119
          },
          {
            "icon_id": "41656882",
            "name": "计算器-面",
            "font_class": "yj-p-calculator-filled",
            "unicode": "e6f0",
            "unicode_decimal": 59120
          },
          {
            "icon_id": "41656881",
            "name": "基础信息",
            "font_class": "yj-p-basic-information",
            "unicode": "e6f1",
            "unicode_decimal": 59121
          },
          {
            "icon_id": "41656880",
            "name": "轨迹终止",
            "font_class": "yj-p-guijizhongzhi",
            "unicode": "e6f2",
            "unicode_decimal": 59122
          },
          {
            "icon_id": "41656876",
            "name": "概览",
            "font_class": "yj-p-profiles",
            "unicode": "e6f3",
            "unicode_decimal": 59123
          },
          {
            "icon_id": "41656865",
            "name": "辅助审核",
            "font_class": "yj-p-auxiliary-rebiew",
            "unicode": "e6f4",
            "unicode_decimal": 59124
          },
          {
            "icon_id": "41656878",
            "name": "回到顶部",
            "font_class": "yj-p-huidaodingbu",
            "unicode": "e6f5",
            "unicode_decimal": 59125
          },
          {
            "icon_id": "41656872",
            "name": "固钉",
            "font_class": "yj-p-tuding",
            "unicode": "e6f6",
            "unicode_decimal": 59126
          },
          {
            "icon_id": "41656879",
            "name": "回退",
            "font_class": "yj-p-huitui1",
            "unicode": "e6f7",
            "unicode_decimal": 59127
          },
          {
            "icon_id": "41656877",
            "name": "恢复",
            "font_class": "yj-p-comn-resume",
            "unicode": "e6f8",
            "unicode_decimal": 59128
          },
          {
            "icon_id": "41656874",
            "name": "关闭",
            "font_class": "yj-p-guanbi",
            "unicode": "e6f9",
            "unicode_decimal": 59129
          },
          {
            "icon_id": "41656875",
            "name": "更多",
            "font_class": "yj-p-more",
            "unicode": "e6fa",
            "unicode_decimal": 59130
          },
          {
            "icon_id": "41656873",
            "name": "个人工作中心",
            "font_class": "yj-p-pc",
            "unicode": "e6fb",
            "unicode_decimal": 59131
          },
          {
            "icon_id": "41656871",
            "name": "复制",
            "font_class": "yj-p-copy",
            "unicode": "e6fc",
            "unicode_decimal": 59132
          },
          {
            "icon_id": "41656869",
            "name": "放大镜2",
            "font_class": "yj-p-magnifier-filled",
            "unicode": "e6fd",
            "unicode_decimal": 59133
          },
          {
            "icon_id": "41656870",
            "name": "附件",
            "font_class": "yj-p-annex",
            "unicode": "e6fe",
            "unicode_decimal": 59134
          },
          {
            "icon_id": "41656863",
            "name": "导入",
            "font_class": "yj-p-comn-import",
            "unicode": "e6ff",
            "unicode_decimal": 59135
          },
          {
            "icon_id": "41656868",
            "name": "分享",
            "font_class": "yj-p-share",
            "unicode": "e700",
            "unicode_decimal": 59136
          },
          {
            "icon_id": "41656867",
            "name": "放大镜",
            "font_class": "yj-p-magnifier",
            "unicode": "e701",
            "unicode_decimal": 59137
          },
          {
            "icon_id": "41656866",
            "name": "辅助审核2",
            "font_class": "yj-p-auxiliary-rebiew-fill",
            "unicode": "e702",
            "unicode_decimal": 59138
          },
          {
            "icon_id": "41656864",
            "name": "放大",
            "font_class": "yj-p-full-screen",
            "unicode": "e703",
            "unicode_decimal": 59139
          },
          {
            "icon_id": "41656862",
            "name": "返回",
            "font_class": "yj-p-return",
            "unicode": "e704",
            "unicode_decimal": 59140
          },
          {
            "icon_id": "41656855",
            "name": "登录",
            "font_class": "yj-p-sign-in",
            "unicode": "e705",
            "unicode_decimal": 59141
          },
          {
            "icon_id": "41656857",
            "name": "二维码-面",
            "font_class": "yj-p-qr-code-filled",
            "unicode": "e706",
            "unicode_decimal": 59142
          },
          {
            "icon_id": "41656860",
            "name": "发送",
            "font_class": "yj-p-send",
            "unicode": "e707",
            "unicode_decimal": 59143
          },
          {
            "icon_id": "41656856",
            "name": "二维码",
            "font_class": "yj-p-qr-code",
            "unicode": "e708",
            "unicode_decimal": 59144
          },
          {
            "icon_id": "41656861",
            "name": "反馈",
            "font_class": "yj-p-feedback",
            "unicode": "e709",
            "unicode_decimal": 59145
          },
          {
            "icon_id": "41656859",
            "name": "发起修改",
            "font_class": "yj-p-faqixiugai",
            "unicode": "e70a",
            "unicode_decimal": 59146
          },
          {
            "icon_id": "41656858",
            "name": "待处理",
            "font_class": "yj-p-daichuli",
            "unicode": "e70b",
            "unicode_decimal": 59147
          },
          {
            "icon_id": "41656852",
            "name": "导出",
            "font_class": "yj-p-export",
            "unicode": "e70c",
            "unicode_decimal": 59148
          },
          {
            "icon_id": "41656851",
            "name": "导航展开",
            "font_class": "yj-p-nav-unfold",
            "unicode": "e70d",
            "unicode_decimal": 59149
          },
          {
            "icon_id": "41656854",
            "name": "导航收起",
            "font_class": "yj-p-nav-fold",
            "unicode": "e70e",
            "unicode_decimal": 59150
          },
          {
            "icon_id": "41656849",
            "name": "撤回",
            "font_class": "yj-p-chehui",
            "unicode": "e70f",
            "unicode_decimal": 59151
          },
          {
            "icon_id": "41656853",
            "name": "打印",
            "font_class": "yj-p-print",
            "unicode": "e710",
            "unicode_decimal": 59152
          },
          {
            "icon_id": "41656845",
            "name": "查询设置",
            "font_class": "yj-p-query-settings",
            "unicode": "e711",
            "unicode_decimal": 59153
          },
          {
            "icon_id": "41656850",
            "name": "表格",
            "font_class": "yj-p-biaoge",
            "unicode": "e712",
            "unicode_decimal": 59154
          },
          {
            "icon_id": "41656846",
            "name": "驳回",
            "font_class": "yj-p-bohui1",
            "unicode": "e713",
            "unicode_decimal": 59155
          },
          {
            "icon_id": "41656848",
            "name": "打开",
            "font_class": "yj-p-open",
            "unicode": "e714",
            "unicode_decimal": 59156
          },
          {
            "icon_id": "41656847",
            "name": "传递",
            "font_class": "yj-p-chuandi",
            "unicode": "e715",
            "unicode_decimal": 59157
          },
          {
            "icon_id": "41656844",
            "name": "编辑",
            "font_class": "yj-p-edit",
            "unicode": "e716",
            "unicode_decimal": 59158
          },
          {
            "icon_id": "41656843",
            "name": "帮助",
            "font_class": "yj-p-help",
            "unicode": "e717",
            "unicode_decimal": 59159
          },
          {
            "icon_id": "41656842",
            "name": "按钮组下降筛选",
            "font_class": "yj-p-screen-down-filled",
            "unicode": "e718",
            "unicode_decimal": 59160
          },
          {
            "icon_id": "41656840",
            "name": "按钮组上升筛选",
            "font_class": "yj-p-screen-up-filled",
            "unicode": "e719",
            "unicode_decimal": 59161
          },
          {
            "icon_id": "41656841",
            "name": "保存",
            "font_class": "yj-p-save",
            "unicode": "e71a",
            "unicode_decimal": 59162
          },
          {
            "icon_id": "41656838",
            "name": "V字下箭头",
            "font_class": "yj-p-arrow-v-down",
            "unicode": "e71b",
            "unicode_decimal": 59163
          },
          {
            "icon_id": "41656836",
            "name": "V字上箭头",
            "font_class": "yj-p-arrow-v-up",
            "unicode": "e71c",
            "unicode_decimal": 59164
          },
          {
            "icon_id": "41656839",
            "name": "V字左箭头",
            "font_class": "yj-p-arrow-v-left",
            "unicode": "e71d",
            "unicode_decimal": 59165
          },
          {
            "icon_id": "41656837",
            "name": "V字右箭头",
            "font_class": "yj-p-arrow-v-right",
            "unicode": "e71e",
            "unicode_decimal": 59166
          },
          {
            "icon_id": "41657080",
            "name": "智慧应用中心",
            "font_class": "yj-p-smart-center",
            "unicode": "e71f",
            "unicode_decimal": 59167
          },
          {
            "icon_id": "41656931",
            "name": "停用",
            "font_class": "yj-p-stop-use",
            "unicode": "e6a5",
            "unicode_decimal": 59045
          },
          {
            "icon_id": "41656958",
            "name": "置顶",
            "font_class": "yj-p-zhiding",
            "unicode": "e6a6",
            "unicode_decimal": 59046
          },
          {
            "icon_id": "41656957",
            "name": "移动端",
            "font_class": "yj-p-mobile",
            "unicode": "e6a7",
            "unicode_decimal": 59047
          },
          {
            "icon_id": "41656955",
            "name": "置底",
            "font_class": "yj-p-bottoming",
            "unicode": "e6a8",
            "unicode_decimal": 59048
          },
          {
            "icon_id": "41656956",
            "name": "最小化",
            "font_class": "yj-p-zuixiaohua",
            "unicode": "e6a9",
            "unicode_decimal": 59049
          },
          {
            "icon_id": "41656953",
            "name": "暂停",
            "font_class": "yj-p-suspend",
            "unicode": "e6aa",
            "unicode_decimal": 59050
          },
          {
            "icon_id": "41656952",
            "name": "预览",
            "font_class": "yj-p-preview",
            "unicode": "e6ab",
            "unicode_decimal": 59051
          },
          {
            "icon_id": "41656948",
            "name": "用户名",
            "font_class": "yj-p-user-name",
            "unicode": "e6ac",
            "unicode_decimal": 59052
          },
          {
            "icon_id": "41656950",
            "name": "预警消息提醒",
            "font_class": "yj-p-tips-warning",
            "unicode": "e6ad",
            "unicode_decimal": 59053
          },
          {
            "icon_id": "41656951",
            "name": "展开",
            "font_class": "yj-p-unfold",
            "unicode": "e6ae",
            "unicode_decimal": 59054
          },
          {
            "icon_id": "41656949",
            "name": "邮件",
            "font_class": "yj-p-mail",
            "unicode": "e6af",
            "unicode_decimal": 59055
          },
          {
            "icon_id": "41656947",
            "name": "隐藏",
            "font_class": "yj-p-hidden",
            "unicode": "e6b0",
            "unicode_decimal": 59056
          },
          {
            "icon_id": "41656945",
            "name": "协办",
            "font_class": "yj-p-co-xieban1",
            "unicode": "e6b1",
            "unicode_decimal": 59057
          },
          {
            "icon_id": "41656946",
            "name": "修改",
            "font_class": "yj-p-modify",
            "unicode": "e6b2",
            "unicode_decimal": 59058
          },
          {
            "icon_id": "41656942",
            "name": "详细信息",
            "font_class": "yj-p-detailed-info",
            "unicode": "e6b3",
            "unicode_decimal": 59059
          },
          {
            "icon_id": "41656944",
            "name": "选中-成功",
            "font_class": "yj-p-select-success",
            "unicode": "e6b4",
            "unicode_decimal": 59060
          },
          {
            "icon_id": "41656941",
            "name": "新增",
            "font_class": "yj-p-add",
            "unicode": "e6b5",
            "unicode_decimal": 59061
          },
          {
            "icon_id": "41656943",
            "name": "小贝-面",
            "font_class": "yj-p-robot-filled",
            "unicode": "e6b6",
            "unicode_decimal": 59062
          },
          {
            "icon_id": "41656940",
            "name": "消息",
            "font_class": "yj-p-message",
            "unicode": "e6b7",
            "unicode_decimal": 59063
          },
          {
            "icon_id": "41656935",
            "name": "下移",
            "font_class": "yj-p-Down",
            "unicode": "e6b8",
            "unicode_decimal": 59064
          },
          {
            "icon_id": "41656939",
            "name": "下载",
            "font_class": "yj-p-download",
            "unicode": "e6b9",
            "unicode_decimal": 59065
          },
          {
            "icon_id": "41656936",
            "name": "拖拽条",
            "font_class": "yj-p-drag-bar",
            "unicode": "e6ba",
            "unicode_decimal": 59066
          },
          {
            "icon_id": "41656938",
            "name": "显示",
            "font_class": "yj-p-reveal",
            "unicode": "e6bb",
            "unicode_decimal": 59067
          },
          {
            "icon_id": "41656937",
            "name": "图表",
            "font_class": "yj-p-tubiao",
            "unicode": "e6bc",
            "unicode_decimal": 59068
          },
          {
            "icon_id": "41656934",
            "name": "退出",
            "font_class": "yj-p-out",
            "unicode": "e6bd",
            "unicode_decimal": 59069
          },
          {
            "icon_id": "41656933",
            "name": "透视图",
            "font_class": "yj-p-hanglieqiehuan",
            "unicode": "e6be",
            "unicode_decimal": 59070
          },
          {
            "icon_id": "41656927",
            "name": "树展示",
            "font_class": "yj-p-tree-display",
            "unicode": "e6bf",
            "unicode_decimal": 59071
          },
          {
            "icon_id": "41656928",
            "name": "缩小",
            "font_class": "yj-p-exit-screen",
            "unicode": "e6c0",
            "unicode_decimal": 59072
          },
          {
            "icon_id": "41656926",
            "name": "提示-成功",
            "font_class": "yj-p-success",
            "unicode": "e6c1",
            "unicode_decimal": 59073
          },
          {
            "icon_id": "41656932",
            "name": "添加",
            "font_class": "yj-p-append",
            "unicode": "e6c2",
            "unicode_decimal": 59074
          },
          {
            "icon_id": "41656930",
            "name": "提示详情",
            "font_class": "yj-p-info",
            "unicode": "e6c3",
            "unicode_decimal": 59075
          },
          {
            "icon_id": "41656929",
            "name": "提示-警告",
            "font_class": "yj-p-warning",
            "unicode": "e6c4",
            "unicode_decimal": 59076
          },
          {
            "icon_id": "41656925",
            "name": "提交",
            "font_class": "yj-p-submit",
            "unicode": "e6c5",
            "unicode_decimal": 59077
          },
          {
            "icon_id": "41656924",
            "name": "双重V字左箭头",
            "font_class": "yj-p-arrow-double-left",
            "unicode": "e6c6",
            "unicode_decimal": 59078
          },
          {
            "icon_id": "41656923",
            "name": "搜索",
            "font_class": "yj-p-search",
            "unicode": "e6c7",
            "unicode_decimal": 59079
          },
          {
            "icon_id": "41656919",
            "name": "事前交办",
            "font_class": "yj-p-shiqianjiaoban",
            "unicode": "e6c8",
            "unicode_decimal": 59080
          },
          {
            "icon_id": "41656921",
            "name": "首页",
            "font_class": "yj-p-homepage",
            "unicode": "e6c9",
            "unicode_decimal": 59081
          },
          {
            "icon_id": "41656918",
            "name": "设置",
            "font_class": "yj-p-shezhi",
            "unicode": "e6ca",
            "unicode_decimal": 59082
          },
          {
            "icon_id": "41656922",
            "name": "双重V字右箭头",
            "font_class": "yj-p-arrow-double-right",
            "unicode": "e6cb",
            "unicode_decimal": 59083
          },
          {
            "icon_id": "41656920",
            "name": "刷新",
            "font_class": "yj-p-refresh",
            "unicode": "e6cc",
            "unicode_decimal": 59084
          },
          {
            "icon_id": "41656916",
            "name": "收起",
            "font_class": "yj-p-retract",
            "unicode": "e6cd",
            "unicode_decimal": 59085
          },
          {
            "icon_id": "41656917",
            "name": "日期",
            "font_class": "yj-p-date",
            "unicode": "e6ce",
            "unicode_decimal": 59086
          },
          {
            "icon_id": "41656912",
            "name": "上移",
            "font_class": "yj-p-moveup",
            "unicode": "e6cf",
            "unicode_decimal": 59087
          },
          {
            "icon_id": "41656915",
            "name": "上传",
            "font_class": "yj-p-upload",
            "unicode": "e6d0",
            "unicode_decimal": 59088
          },
          {
            "icon_id": "41656910",
            "name": "删除-提示-错误",
            "font_class": "yj-p-delete",
            "unicode": "e6d1",
            "unicode_decimal": 59089
          },
          {
            "icon_id": "41656914",
            "name": "筛选",
            "font_class": "yj-p-filter",
            "unicode": "e6d2",
            "unicode_decimal": 59090
          },
          {
            "icon_id": "41656913",
            "name": "时间",
            "font_class": "yj-p-time",
            "unicode": "e6d3",
            "unicode_decimal": 59091
          },
          {
            "icon_id": "41656911",
            "name": "升序",
            "font_class": "yj-p-ascendingorder",
            "unicode": "e6d4",
            "unicode_decimal": 59092
          },
          {
            "icon_id": "41656909",
            "name": "审计",
            "font_class": "yj-p-audit",
            "unicode": "e6d5",
            "unicode_decimal": 59093
          },
          {
            "icon_id": "41656907",
            "name": "任务委托",
            "font_class": "yj-p-weituo",
            "unicode": "e6d6",
            "unicode_decimal": 59094
          },
          {
            "icon_id": "41656908",
            "name": "审批轨迹",
            "font_class": "yj-p-approval-track",
            "unicode": "e6d7",
            "unicode_decimal": 59095
          },
          {
            "icon_id": "41656905",
            "name": "日历",
            "font_class": "yj-p-calendar",
            "unicode": "e6d8",
            "unicode_decimal": 59096
          },
          {
            "icon_id": "41656906",
            "name": "评论",
            "font_class": "yj-p-comment",
            "unicode": "e6d9",
            "unicode_decimal": 59097
          },
          {
            "icon_id": "41656904",
            "name": "列设置",
            "font_class": "yj-p-lieshezhi",
            "unicode": "e6da",
            "unicode_decimal": 59098
          },
          {
            "icon_id": "41656903",
            "name": "全屏(还原)",
            "font_class": "yj-p-quanping",
            "unicode": "e6db",
            "unicode_decimal": 59099
          },
          {
            "icon_id": "41656902",
            "name": "面型下箭头",
            "font_class": "yj-p-arrow-down-filled",
            "unicode": "e6dc",
            "unicode_decimal": 59100
          },
          {
            "icon_id": "41656901",
            "name": "清除",
            "font_class": "yj-p-clean-up",
            "unicode": "e6dd",
            "unicode_decimal": 59101
          },
          {
            "icon_id": "41656900",
            "name": "批量",
            "font_class": "yj-p-batch",
            "unicode": "e6de",
            "unicode_decimal": 59102
          },
          {
            "icon_id": "41656897",
            "name": "默认排序",
            "font_class": "yj-p-default-sort",
            "unicode": "e6df",
            "unicode_decimal": 59103
          },
          {
            "icon_id": "41656899",
            "name": "面型左箭头",
            "font_class": "yj-p-arrow-left-filled",
            "unicode": "e6e0",
            "unicode_decimal": 59104
          },
          {
            "icon_id": "41656898",
            "name": "卡片",
            "font_class": "yj-p-kapian",
            "unicode": "e6e1",
            "unicode_decimal": 59105
          },
          {
            "icon_id": "41656896",
            "name": "面型右箭头",
            "font_class": "yj-p-arrow-right-filled",
            "unicode": "e6e2",
            "unicode_decimal": 59106
          },
          {
            "icon_id": "41656895",
            "name": "科目详情",
            "font_class": "yj-p-details",
            "unicode": "e6e3",
            "unicode_decimal": 59107
          },
          {
            "icon_id": "41656894",
            "name": "密码",
            "font_class": "yj-p-pwd",
            "unicode": "e6e4",
            "unicode_decimal": 59108
          },
          {
            "icon_id": "41656888",
            "name": "会签不通过",
            "font_class": "yj-p-hqbutongguo",
            "unicode": "e6e5",
            "unicode_decimal": 59109
          },
          {
            "icon_id": "41656893",
            "name": "面型上箭头",
            "font_class": "yj-p-arrow-up-filled",
            "unicode": "e6e6",
            "unicode_decimal": 59110
          },
          {
            "icon_id": "41656889",
            "name": "加签",
            "font_class": "yj-p-jiaqian1",
            "unicode": "e6e7",
            "unicode_decimal": 59111
          },
          {
            "icon_id": "41656890",
            "name": "垃圾桶",
            "font_class": "yj-p-dustbin",
            "unicode": "e6e8",
            "unicode_decimal": 59112
          },
          {
            "icon_id": "41656892",
            "name": "列宽自适应",
            "font_class": "yj-p-col-width-adaptive",
            "unicode": "e6e9",
            "unicode_decimal": 59113
          },
          {
            "icon_id": "41656887",
            "name": "降序",
            "font_class": "yj-p-descendingorder",
            "unicode": "e6ea",
            "unicode_decimal": 59114
          },
          {
            "icon_id": "41656891",
            "name": "列表",
            "font_class": "yj-p-liebiao",
            "unicode": "e6eb",
            "unicode_decimal": 59115
          }
        ]
      }
    }
  }
</script>

<style>
  .icon-list{
    margin: 10px 0;
    padding: 0 0 0 20px;
    font-size: 14px;
    color: #5e6d82;
    line-height: 2em;
    list-style: none;
    padding: 0!important;
    border: 1px solid #eaeefb;
    border-radius: 4px;
    overflow: hidden;
  }
  .icon-list li{
      float: left;
      width: 16.66%;
      text-align: center;
      height: 120px;
      line-height: 120px;
      color: #666;
      font-size: 13px;
      border-right: 1px solid #eee;
      border-bottom: 1px solid #eee;
      margin-right: -1px;
      margin-bottom: -1px;
  }
  .icon-list li i{
    display: block;
    font-size: 32px;
    margin-bottom: 15px;
    color: #606266;
    transition: color .15s linear;
  }
  .icon-list li span{
     line-height: normal;
     font-family: Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif;
     color: #99a9bf;
     transition: color .15s linear;
  }
  .icon-list li span i{
    display: block;
    font-size: 32px;
    margin-bottom: 15px;
    color: #606266;
    transition: color .15s linear;
  }
  .icon-list li span .icon-name{
     display: inline-block;
     padding: 0 3px;
     height: 1em;
  }
  .icon-list li span .icon-name p{
     margin: 0;
  }
  .icon-list li span .icon-name:after, .icon-list span{
    display: inline-block;
    vertical-align: middle;
  }
.icon-list li span .icon-name:hover{
    color: #5cb6ff;
  } 
</style>

:::