# Utils 工具类

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-utils
```

### 2.在代码中引入

``` js
import Utils from 'yjpl-utils';
```

## isArray 判断对象是不是数组

``` js
import Utils from 'yjpl-utils';

Utils.isArray([]);
// true

Utils.isArray('');
// false

```

### isArray Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| obj | 传入值，将被用来判断是否为数组 | any | — | — |


## isNumber 判断是否为number类型

``` js
import Utils from 'yjpl-utils';

Utils.isNumber(1);
// true

Utils.isNumber('a');
// false

```

### isNumber Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| t | 传入值，将被用来是否为number类型 | any | — | — |


## isSafeNumber 判断是否是安全的数字

``` js
import Utils from 'yjpl-utils';

// JavaScript中的基本数据类Number是双精度浮点数
// 最大安全范围是正负9007199254740991，也就是2的53次方减一

Utils.isSafeNumber(42);
// true

Utils.isSafeNumber(9007199254740992);
// false

Utils.isSafeNumber('a');
// false

```

### isSafeNumber Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| num | 传入值，将被用来是否是安全的数字 | any | — | — |


## isObject 判断是否为Object类型

``` js
import Utils from 'yjpl-utils';

Utils.isObject({});
// true

Utils.isObject([]);
// false

```

### isObject Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| t | 传入值，将被用来是否为Object类型 | any | — | — |


## isNotEmpty 判断对象是否不为空

``` js
import Utils from 'yjpl-utils';

Utils.isNotEmpty(); // false
Utils.isNotEmpty({}); // false
Utils.isNotEmpty([]); // false
Utils.isNotEmpty(''); // false

```

### isNotEmpty Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要判断的值 | any | — | — |


## isNullOrEmpty 判断是否为空

``` js
import Utils from 'yjpl-utils';

Utils.isNullOrEmpty();
// true
Utils.isNullOrEmpty(null);
// true
Utils.isNullOrEmpty(undefined);
// true
Utils.isNullOrEmpty('');
// true
Utils.isNullOrEmpty([]);
// true
Utils.isNullOrEmpty({});
// false
Utils.isNullOrEmpty(0);
// true
Utils.isNullOrEmpty(false);
// true

```

### isNullOrEmpty Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要判断的值 | any | — | — |


## isEmpty 判断是否为空

``` js
import Utils from 'yjpl-utils';

Utils.isEmpty();
// true
Utils.isEmpty(null);
// true
Utils.isEmpty(undefined);
// true
Utils.isEmpty('');
// true
Utils.isEmpty([]);
// true
Utils.isEmpty({});
// true
Utils.isEmpty(0);
// true
Utils.isEmpty(false);
// false

```

### isEmpty Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要判断的值 | any | — | — |


## notEmpty 递归判断目标或目标的属性为非空

``` js
import Utils from 'yjpl-utils';

var obj = { "a": "1", "b": "", "c": null, "d": [], "e":{"f": ['a','b',""] } },
    s1 = '  ',
    s2,
    o1 = {},
    a1 = [];

Utils.notEmpty(s1); // false
Utils.notEmpty(s2); // false
Utils.notEmpty(o1); // false
Utils.notEmpty(a1); // false

Utils.notEmpty(obj); // true
Utils.notEmpty(obj, 'a'); // true
Utils.notEmpty(obj, 'b'); // false
Utils.notEmpty(obj, 'c'); // false
Utils.notEmpty(obj, 'd'); // false，[]为空

Utils.notEmpty(obj, 'e.f'); // true，{f:['a','b','']}不空
Utils.notEmpty(obj, 'e.f.1'); // true，'b'不空
Utils.notEmpty(obj, 'e.f.2'); // false，''为空

```

### notEmpty Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要判断的值 | any | — | — |
| attr | 用.分表示的属性 | string | — | — |


## arraySearch 数组中对象的属性值匹配指定值得元素对应的索引位置

``` js
import Utils from 'yjpl-utils';

var aa =[{a:1,b:2},{a:3,b:4}];

Utils.arraySearch(aa, "a", 3); // 1
Utils.arraySearch(aa, "a", 0); // -1

```

### arraySearch Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arr | 被查找的数组 | array | — | — |
| field | 对象的查找字段 | string | — | — |
| value | 查找值，只能是基本类型 | any | — | — |


## clone 深度克隆对象

``` js
import Utils from 'yjpl-utils';

var aa = {a:1,b:2,c:{cc:123},fn:function(){alert(this.c.cc);}};
var bb = Utils.clone(aa);
// bb={a:1,b:2,c:{cc:123},fn:f+-unction(){alert(this.c.cc);},toString:function(){native},valueOf:function(){native}}

```

### clone Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| target | 被克隆的对象 | object | — | — |


## round 四舍五入函数

``` js
import Utils from 'yjpl-utils';

Utils.round(4.56, 1);
// 4.6

Utils.round(4.56, 4);
// 4.56

Utils.round(2.3456, 1);
// 2.3

// 向上取整
Utils.round(2.3456, 1, 1);
// 2.4

```

### round Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| val | 要处理的值 | number / string | — | — |
| scale | 精度 | number | — | 2 |
| scaleExt | 二次精度，向上取整时输入，与精度保持一致 | number | — | — |


## formatNumber 数字转千分位

``` js
import Utils from 'yjpl-utils';

// 默认两位
Utils.formatNumber(123456789);
// 123,456,789.00

(Utils.formatNumber(123456789, 1);
// 123,456,789.0

```

### formatNumber Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| s | 要处理的值 | number | — | — |
| n | 保留小数点后几位 | number | — | — |


## format 格式化字符串

``` js
import Utils from 'yjpl-utils';

Utils.format("{0} is a {1}.", "YWY", "man");
// YWY is a man.

```

### format Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| strtmpl | 字符串模板，用{n}表示参数位置，n从0开始计数 | string | — | — |


## originalAngleBrackets 转换成原始的尖括号

``` js
import Utils from 'yjpl-utils';

Utils.originalAngleBrackets("&lt;script&nbsp;&gt;");
// <script >

```

### originalAngleBrackets Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| s | 要处理的字符串 | string | — | — |


## escapeAngleBrackets 原始的尖括号转换成编码尖括号

``` js
import Utils from 'yjpl-utils';

Utils.escapeAngleBrackets("<script> ");
// &lt;script&gt;&nbsp;

```

### escapeAngleBrackets Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| s | 要处理的字符串 | string | — | — |


## replaceJquerySelecter jquery选择器关键字转义

``` js
import Utils from 'yjpl-utils';

// jquery选择器中".","[","]",":"都是关键字，需要转义

Utils.replaceJquerySelecter("[].:");
// \[\]\.\:;

```

### replaceJquerySelecter Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 要处理的字符串 | string | — | — |


## splitStr 将字段路径字符串分隔成数组

``` js
import Utils from 'yjpl-utils';

var spath = "a.list[0].b.list[1].c";
var apath = Utils.splitStr(spath); // apath = ["a", "list", "0", "b", "list", "1", "c"]

```

### splitStr Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| s | 要处理的字符串 | string | — | — |


## split 用分割符分割字符串，返回字符串数组

``` js
import Utils from 'yjpl-utils';

// 分割全部:
Utils.split("x.y.z.w", ".");        // 返回["x","y","z","w"]

// 分割到第一个分隔符
Utils.split("x.y.z.w", ".", 1);    // 返回["x","y.z.w"]
Utils.split("x.y.z.w", ".", -3);    // 返回["x","y.z.w"]

// 分割到第二个分隔符
Utils.split("x.y.z.w", ".", 2);    // 返回["x.y","z.w"]
Utils.split("x.y.z.w", ".", -2);    // 返回["x.y","z.w"]

// 分割到最后个分隔符
Utils.split("x.y.z.w",".",-1);    // 返回["x.y.z","w"]

// 有回调函数的例子
var aa = Utils.split("x .y.z . w", ".", -1, function(arr) {
    for(var i = 0; i < arr.length; i++){
        arr[i] = arr[i].replace(/^\s+|\s+$/,"");
    }
    return arr;
});    // 返回["x .y.z","w"]

```

### split Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| src | 被分割的目标字符串 | string | — | — |
| sep | 分割符，单或多个字符串 | string | — | — |
| index | 分割到第几个（1表示第1个，-1表示倒数第几个）分隔符，即将src分割成左右两部分；当为0或者没有定义时，表示全部分割 | number | — | — |
| callback | 要处理的字符串 | function | — | — |


## formartToZh 将阿拉伯数字转换成中文数字（为金额，日期，数字共用的方法）

``` js
import Utils from 'yjpl-utils';

Utils.formartToZh("1234567800056509.3","lm") //一千二百三十四万五千六百七十八亿〇五万六千五百〇九元三角
Utils.formartToZh("1234567800056509.3","um") //壹仟贰佰叁拾肆萬伍仟陆佰柒拾捌億零伍萬陆仟伍佰零玖圆叁角
Utils.formartToZh("1234567800056509.3","l") //一千二百三十四万五千六百七十八亿〇五万六千五百〇九点三
Utils.formartToZh("1234567800056509.3","u")  //壹仟贰佰叁拾肆萬伍仟陆佰柒拾捌億零伍萬陆仟伍佰零玖点叁
Utils.formartToZh("-1234567800056509.3","u")  //-壹仟贰佰叁拾肆萬伍仟陆佰柒拾捌億零伍萬陆仟伍佰零玖点叁

Utils.formartToZh("2014","ly");  //二〇一四
Utils.formartToZh("10","ld");  //十
Utils.formartToZh("11","ld");  //十一
Utils.formartToZh("20","ld");  //二十
Utils.formartToZh("2014","uy");  //贰零壹肆
Utils.formartToZh("10","ud");  //拾
Utils.formartToZh("21","ud");  //贰拾壹

```

### formartToZh Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| num | 被分割的目标字符串 | number | — | — |
| u | "u"表示转换成中文大写，否则"l"表示转换成中文小写；第2位(如果有)，"m"表示金额，"y"表示年份（2014拼读“二〇一四”），"d"表示月/日（10拼读“十”） | string | — | — |
| dot | 表示有无小数部分，true表示纯小数，false表示纯整数，undefined表示不确定 | boolean | — | — |


## transferNumToMoney 数字转为金额大写

``` js
import Utils from 'yjpl-utils';

var m = Utils.transferNumToMoney(1001); // 壹仟零壹元整
var m = Utils.transferNumToMoney(1000101); // 壹佰萬零壹佰零壹元整
var m = Utils.transferNumToMoney(1000101.011); // 壹佰萬零壹佰零壹圆零壹分壹厘

```

### transferNumToMoney Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| num | 金额数字 | number | — | — |


## transferNumToChUpper 小写数字转换为大写

``` js
import Utils from 'yjpl-utils';

Utils.transferNumToChUpper(123456000101.011) // 壹千贰百叁十肆亿伍千陆百万零壹百零壹点零壹壹

```

### transferNumToChUpper Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| num | 金额数字 | number | — | — |


## transferNumToChLower 小写数字转换为小写

``` js
import Utils from 'yjpl-utils';

Utils.transferNumToChLower(120345600101.011); // 一千二百〇三亿四千五百六十万〇一百〇一点〇一一

```

### transferNumToChLower Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| num | 金额数字 | number | — | — |


## numberToChinese 数字转中文

``` js
import Utils from 'yjpl-utils';

Utils.numberToChinese(123, 'Lower'); // 一二三
Utils.numberToChinese(123, 'Upper'); // 壹贰叁
Utils.numberToChinese(123, 'MoneyLower'); // 一百二十三元整
Utils.numberToChinese(123, 'Lower'); // 一佰二拾三元整

```

### numberToChinese Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要转换的值 | string | — | — |
| stype | 模式 | string | — | — |


## numberFormatting 数字格式化

``` js
import Utils from 'yjpl-utils';

Utils.numberFormatting(1234.567, 2); // 1234.57
Utils.numberFormatting(1234.567, 2, {numUnit: 'HUNDRED'}); // 12.35
Utils.numberFormatting(1234.567, 2, {separation: true}); // 1,234.57
Utils.numberFormatting(-1234.567, 2, {bracketabled: true}); // (1234.567)

```

### numberFormatting Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，需要格式化的数字，支持数字和数字字符串 | string | — | — |
| decimal | 选传，小数位的位数，null: 小数位全保留 | string | — | — |
| options | 选传，额外的参数，包含numUnit，separation，bracketabled三个可选的key值 | object | — | — |
| numUnit | 选传，折合，对照ecp.const.js中的NumUnit枚举 | boolean | — | — |
| separation | 选传，千位分隔符支持自定义，默认是逗号，也可以自定义字符类型 | boolean | — | — |
| bracketabled | 选传，负数是否用括号表示，value为正数时，此参数不生效 | boolean | — | — |


## numberToPercentage 数字转百分比数值，保留小数位数

``` js
import Utils from 'yjpl-utils';

Utils.numberToPercentage(12); // 12%
Utils.numberToPercentage(12.3456); // 12.3456%
Utils.numberToPercentage(12.3456, 2); // 12.35%

```

### numberToPercentage Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，需要格式化的百分比字符串,如果传入纯数字会自动追加% | string | — | — |
| decimal | 选传，小数位的位数，null: 小数位全保留 | string | — | — |
| options | 选传，参考numberFormatting()方法，可以传入千分位分隔符，折合枚举，括号样式等 | object | — | — |
| options.direct | 选传，options中的一个参数。如0.52默认转换成52% | boolean | — | — |


## numberToCurrency 数字转货币，保留小数位数

``` js
import Utils from 'yjpl-utils';

Utils.numberToCurrency(12.3456, 2, '$'); // $12.35

```

### numberToCurrency Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，需要格式化的货币字符串,如果传入纯数字会自动追加货币符号 | string | — | — |
| decimal | 选传，小数位的位数，null: 小数位全保留 | string | — | — |
| symbol | 可选，货币符号,如果不设置则为纯数字 | string | — | — |
| options | 选传，参考numberFormatting()方法，可以传入千分位分隔符，折合枚举，括号样式等 | object | — | — |


## numberToScientificCount 数字转科学计数法

``` js
import Utils from 'yjpl-utils';

Utils.numberToScientificCount('0.00000001');  // 1e-8

```

### numberToScientificCount Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，需要格式化的字符串 | string | — | — |
| decimal | 选传，小数位数，0 ~ 20 之间的值；如果省略了该参数，将使用尽可能多的数字 | string | — | — |
| options | 选传，参考numberFormatting()方法，可以传入千分位分隔符，折合枚举，括号样式等 | object | — | — |


## transferToNumber 科学计数法转成数字

``` js
import Utils from 'yjpl-utils';

Utils.transferToNumber('1e-8');  // 0.00000001

```

### transferToNumber Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| inputNumber | 要转化的内容 | string | — | — |


## formatDate 把不完成的日期，改成完整的日期格式

``` js
import Utils from 'yjpl-utils';

Utils.formatDate("2010"); // 2010-01-01
Utils.formatDate("2008-08"); // 2008-08-01
Utils.formatDate("abc"); // abc

```

### formatDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 要补完的日期 | string | — | — |


## strToTimeMillis 日期转毫秒

``` js
import Utils from 'yjpl-utils';

Utils.strToTimeMillis("2008"); // 1199116800000
Utils.strToTimeMillis("2021-12-31"); // 1640880000000

```

### strToTimeMillis Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 要转换的日期 | string | — | — |


## invalidDate 日期格式是否非法

``` js
import Utils from 'yjpl-utils';

Utils.invalidDate("2008"); // 2008-01-01
Utils.invalidDate("20211231"); // 2021-12-31
Utils.invalidDate("abc"); // true

```

### invalidDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| dateStr | 要校验的日期格式 | string | — | — |


## parseDate 把字日期符串转换成符点数据格式

``` js
import Utils from 'yjpl-utils';

Utils.parseDate("2008"); // 1640337777146
Utils.parseDate("20211231"); // 1640908800000
Utils.parseDate(); // 返回 new Data()
Utils.parseDate('abc'); // 返回 new Data()

```

### parseDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 要转换的日期 | string | — | — |


## strToDate 字符串转换成日期格式(YYYY-MM-DD)

``` js
import Utils from 'yjpl-utils';

Utils.strToDate('2008', true, null, true); // 2008-01-01 08:00:00
Utils.strToDate('2010', true, null, false); // 2010-01-01 08:00
Utils.strToDate('2012', false, null, true); // 2012-01-01
Utils.strToDate('20211231', null, 'M', null); // 2021-12
Utils.strToDate('202112', null, 'Y', null); // 2021

```

### strToDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 要转换的字符串 | string | — | — |
| showTime | 是否展示时间 | boolean | — | — |
| showType | 展示类型 Y-年 M-月 | string | — | — |
| showSeconds | 是否展示秒钟 | boolean | — | — |


## jsonStrToDate 把JSON格式转成日期

``` js
import Utils from 'yjpl-utils';

const str = '/Date(1328198400000+0800)/';

Utils.jsonStrToDate(str);
// Fri Feb 03 2012 00:00:00 GMT+0800 (中国标准时间)

```

### jsonStrToDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 传入值，把JSON格式转成日期 | string | — | — |


## ecpDateToDate /Date(xxx+0800)/格式 转换成 YYYY-MM-DD格式

``` js
import Utils from 'yjpl-utils';

Utils.ecpDateToDate('2008', true); // 2008-01-01 08:00:00
Utils.ecpDateToDate('2012', false); // 2012-01-01

```

### ecpDateToDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要转换的格式 | string | — | — |
| showTime | 是否展示时间 | boolean | — | — |


## dateToEcpDate 转换成 /Date(xxx+0800)/格式

``` js
import Utils from 'yjpl-utils';

Utils.dateToEcpDate('2021-12-31'); // /Date(1640880000000+0800)/

```

### dateToEcpDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 要转换的格式 | string | — | — |


## isEcpDate 判断校验是否为ECP日期格式

``` js
import Utils from 'yjpl-utils';

Utils.isEcpDate('/Date(1640880000000+0800)/'); // true
Utils.isEcpDate('2021-12-31'); // false

```

### isEcpDate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| date | 要判断的格式 | string | — | — |


## dateToStr 日期转换成字符串

``` js
import Utils from 'yjpl-utils';

Utils.dateToStr(new Data()); // 2021-12-31
Utils.dateToStr(new Data(), 'yyyyMMdd'); // 20211231

```

### dateToStr Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| date | 要转换的日期 | string | — | — |
| pattern | 格式 | string | — | — |


## timeFormatting 时间戳转各种时间格式

``` js
import Utils from 'yjpl-utils';

Utils.timeFormatting(new Date('2021-03-31')); // 2021-3-31
Utils.timeFormatting(1617175433148); // 2021-3-31
Utils.timeFormatting(1617175433148, 'yyyy/MM/dd HH:mm:ss'); // 2021/3/31 15:23:53
Utils.timeFormatting(1617175433148, {isFillZero: true}); // 2021-03-31
Utils.timeFormatting(1617175433148, 'yyyy年MM月dd日', {isCHN: true}); // 二〇二一年三月三十一日
Utils.timeFormatting(1617175433148, 'yyyy年MM月dd日', {isENGMonth: true}); // 2021年Mar月31日

```

### timeFormatting Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| date | 要转的时间对象Date()或者时间戳 | date | — | — |
| format | 要转的格式 | string | — | — |
| options | 选传，额外的参数，包含isCHN，isENGMonth，isFillZero三个可选的key值 | object | — | — |
| options.isCHN | 选传，日期用中文表示 | boolean | — | — |
| options.isENGMonth | 选传，月份用英文表示 | boolean | — | — |
| options.isFillZero | 选传，月份小于10的自动补0 | boolean | — | — |


## isIDCard 身份证

``` js
import Utils from 'yjpl-utils';

Utils.isIDCard('123456196108047890'); // true
Utils.isIDCard('12345619610804789X'); // true
Utils.isIDCard('123456196XXXX47890'); // false

```

### isIDCard Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 检验值 | string | — | — |


## isTele 电话号

``` js
import Utils from 'yjpl-utils';

Utils.isTele('2222222'); // true
Utils.isTele('13333333333'); // true
Utils.isTele('110'); // false

```

### isTele Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 检验值 | string | — | — |


## isMobile 手机号

``` js
import Utils from 'yjpl-utils';

Utils.isMobile('13333333333')); // true
Utils.isMobile('12345678901')); // false

```

### isMobile Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 检验值 | string | — | — |


## isEmail 电子邮箱

``` js
import Utils from 'yjpl-utils';

Utils.isEmail('abc@ygsoft.com'); // true
Utils.isEmail('abc@ygsoft_com'); // false

```

### isEmail Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 检验值 | string | — | — |


## isPostid 邮政编码

``` js
import Utils from 'yjpl-utils';

Utils.isPostid('519000'); // true
Utils.isPostid('000000'); // false

```

### isPostid Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 检验值 | string | — | — |


## validate 校验数据

``` js
import Utils from 'yjpl-utils';

Utils.validate('email', value); // Utils.isEmail(value);
Utils.validate('telePhone', value); // Utils.isTele(value);
Utils.validate('mobileTelePhone', value); // Utils.isMobile(value);
Utils.validate('postboat', value); // Utils.isPostid(value);
Utils.validate('idcard', value); // Utils.isIDCard(value);

```

### validate Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| validate | 检验方式 | string | — | — |
| value | 检验值 | string | — | — |


## strToJson 字符串转JSON

``` js
import Utils from 'yjpl-utils';

const str = '{"a": 1, "b": 2}';

Utils.strToJson(str);
// {a: 1, b: 2}

```

### strToJson Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 要转换的字符串 | str | — | — |


## jsonToStr 字符串转JSON

``` js
import Utils from 'yjpl-utils';

const obj = {a: 1, b: 2};

Utils.jsonToStr(obj);
// {"a":1,"b":2}

```

### jsonToStr Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| obj | 要转换的json对象 | object | — | — |


## getContextPath 获取项目路径

``` js
import Utils from 'yjpl-utils';

Utils.getContextPath(); // /necp/mapp

```

### getContextPath Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| — | — | — | — | — |


## getI18n 获取国际化语言

``` js
import Utils from 'yjpl-utils';

Utils.getI18n('en-us', '欢迎'); // welcome

```

### getI18n Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| key | 主键 | string | — | — |
| defValue | 默认值 | string | — | — |


## getArguments 获取浏览器URL中传入的参数

``` js
import Utils from 'yjpl-utils';

var url = "http://ip:port/example.html?arg1=123&arg2=456#arg3=789#arg4";
var arg1 = Utils.getArgments("arg1"); // arg1 = 123
var arg2 = Utils.getArgments(url, "arg2"); // arg2 = 456
var arg3 = Utils.getArgments(url, "arg3"); // arg3 = 789
var arg4 = Utils.getArgments(url, "arg4"); // arg4 = undefined
var arg5 = Utils.getArgments(url, "arg5"); // arg5 = undefined

```

### getArguments Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | URL串 | string | — | — |
| argName | 参数名称，不区分大小写 | string | — | — |
| decode | 是否解密，默认不解密 | boolean | — | false |


## getAllArgument 获取url的所有参数(Object)

``` js
import Utils from 'yjpl-utils';

var url1 = "abc.com/index.html?A=123&b=456#c=789";
var url2 = "abc.com/index.html?a=123&b=456#c=789#d=000";
var url3 = "abc.com/index.html?a=123&b=456#c=789#d";
Utils.getAllArgument(url1); // {A: "123", b: "456", c: "789", url: "abc.com/index.html"}
Utils.getAllArgument(url1, true); // {a: "123", b: "456", c: "789", url: "abc.com/index.html"}
Utils.getAllArgument(true); // {a: "123", b: "456", c: "789", url: "abc.com/index.html"}，如果location.href=url1
Utils.getAllArgument( ); // {A: "123", b: "456", c: "789", url: "abc.com/index.html"}，如果location.href=url1
Utils.getAllArgument(url2); // {a: "123", b: "456", c: "789", d:"000" ,url: "abc.com/index.html"}
Utils.getAllArgument(url3); // {a: "123", b: "456", c: "789", d:undefined ,url: "abc.com/index.html"}

```

### getAllArgument Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | URL串 | string | — | location.href |
| argName2Lower | 结果对象中参数名是否转化为小写 | boolean | — | false |


## setCookie 设置cookie

``` js
import Utils from 'yjpl-utils';

Utils.setCookie("mc", "XiaoMing", 3000, "ymy");

```

### setCookie Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| name | 名称 | string | — | — |
| value | 值 | string | — | — |
| time | 时长 | number | — | — |
| path | 存放路径 | string | — | — |


## getCookie 获取cookie

``` js
import Utils from 'yjpl-utils';

Utils.getCookie("ecp_locale"); // zh_CN

```

### getCookie Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| name | 名称 | string | — | — |


## getLanguage 获取当前语言

``` js
import Utils from 'yjpl-utils';

Utils.getLanguage(); // zh_CN

```

### getLanguage Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| — | — | — | — | — |


## activeInvoke 通过webSocket调用本地插件控件

``` js
import Utils from 'yjpl-utils';

Utils.activeInvoke('AutoUpdate.WebUpdate','GetMudVersion', [], [], function(state, result){
    //todo
});
Utils.activeInvoke('FT_reg.exe', function(state, result) {
    //todo
});

```

### activeInvoke Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| name | 传处com+的ole字符串，如'AutoUpdate.WebUpdate'(包括对象+接口) 或 应用程序如 FT_reg.exe,当执行exe时，默认只运行与dllAdapter.dll同目录的exe文件 | string | — | — |
| method | 方法名 如果是运行exe文件，可传入空 | string | — | — |
| params | 传入参数数组，可不传 | array | — | — |
| property | 传入属性数组格式[{name:'p1',value:'v1'},{name:'p2', value:'v2'}] 可不传 | array | — | — |
| ret | 是否需要返回值 可不传，默认是true | boolean | — | true |
| type | 可传入类型 type如 dll, exe等，建议不传，目前默认解析dll和exe | string | — | — |
| callBack | 回调函数 两个参数  state:调用状态true/false, result:返回的结果 | function | — | — |


## fromRGBToHex 颜色RGB转换成十六进制

``` js
import Utils from 'yjpl-utils';

Utils.fromRGBToHex('rgb(255, 255, 255)'); // #FFFFFF

```

### fromRGBToHex Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| rgb | RGB格式的颜色表示 | string | — | — |


## getLocaHost 返回网址前缀

``` js
import Utils from 'yjpl-utils';

Utils.getLocaHost(); // window.location.protocol + "//" + window.location.host

```

### getLocaHost Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| — | — | — | — | — |


## getQRCodeUrl 返回二维码服务端实现方式路径

``` js
import Utils from 'yjpl-utils';

Utils.getQRCodeUrl(value, width); // this.getLocaHost() + '/grm/ecp/barcodeServlet?content=' + value + '&barcodeTypeId=QR&width=' + width + '&height=' + width

```

### getQRCodeUrl Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 二维码内容 | string | — | — |
| width | 二维码大小 | string | — | — |


## setTimeZone 设置时区

``` js
import Utils from 'yjpl-utils';

Utils.setTimeZone(); // Utils.setCookie('ecp_timezone', timezone);

```

### setTimeZone Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| — | — | — | — | — |


## strToSecretCode 把字符串转换成掩码

``` js
import Utils from 'yjpl-utils';

Utils.strToSecretCode('ab-cd-ef', '*'); // ******** 无保留字段
Utils.strToSecretCode('ab-cd-ef', '*', '-'); // **-**-** 单个保留字段
Utils.strToSecretCode('abx-cdx-efx', '*', ['a', '-']); // a**-***-*** 多个保留字段，采用数组传入
Utils.strToSecretCode('axybxcxydy', '*', 'xy'); // *xy***xy** 单个连续的保留字段，只保留连续的字段

```

### strToSecretCode Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，被转化的字符串 | string | — | — |
| secret | 必传，掩码字符串 | string | — | — |
| staticValue | 选传，不需要转成掩码的字符串,单个的直接传单个字符串，如：'%'，多个则用数组传递，如：['%', '-'] | string | — | — |


## setQRCode 设置二维码

``` js
import Utils from 'yjpl-utils';

Utils.setQRCode('123', 'qrCodeDiv', {width:300, height:300});

```

### setQRCode Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，二维码内容 | string | — | — |
| selector | 必传，展示二维码的dom元素的id选择器 | string | — | — |
| options.width | 选传，宽度 | number | — | 124 |
| options.height | 选传，高度 | number | — | 124 |
| options.render | 选传，渲染方式（有两种方式 table和canvas，默认是canvas） | string | — | canvas |


## setBarCode 设置条形码

``` js
import Utils from 'yjpl-utils';

Utils.setBarCode('123', '#barcode', {
  format: "CODE39",
  width: 3,
  height: 100,
  displayValue: true,
  text: "456",
  fontOptions: "bold italic",
  font: "fantasy",
  textAlign: "left",
  textPosition: "top",
  textMargin: 5,
  fontSize: 15,
  background: "#eee",
  lineColor: "#2196f3",
  margin: 15
});

```

### setBarCode Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| value | 必传，要转化的内容 | string | — | — |
| selector | 必传，展示条码的dom元素的id选择器 | string | — | — |
| options.format | 选传，要使用的条形码类型 | string | — | — |
| options.background | 选传，条形码背景颜色 | string | — | — |
| options.lineColor | 选传，条码的颜色 | string | — | — |
| options.margin | 选传，设置条形码周围的空白边距 | number | — | — |
| options.width | 选传，条之间的宽度 | number | — | — |
| options.height | 选传，单个条形码的高度 | number | — | — |
| options.displayValue | 选传，是否在条形码下方显示文字 | boolean | — | — |
| options.text | 选传，覆盖显示的文本 | string | — | — |
| options.fontSize | 选传，字体大小 | number | — | — |
| options.fontOptions | 选传，使文字加粗体或变斜体等属性配置 | string | — | — |
| options.font | 选传，文本的字体 | string | — | — |
| options.textAlign | 选传，文本的水平对齐方式 | string | — | — |
| options.textPosition | 选传，文本的垂直位置 | number | — | — |
| options.textMargin | 选传，条形码和文本之间的间距 | number | — | — |


## accAdd 精确加法,返回浮点数

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accAdd(num1, num2));
//6207.5
console.log(Utils.accAdd(num1, num2, num3));
//6207.5
```

### accAdd Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被加数 | number | string | — | — |
| arg2 | 加数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accSub 精确减法，返回浮点数。

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accSub(num1, num2));
//6207.5
console.log(Utils.accSub(num1, num2, num3));
//6207.5
```

### accSub Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被减数 | number | string | — | — |
| arg2 | 减数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accMul 精确乘法，返回浮点数。

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accMul(num1, num2));
//18631.5
console.log(Utils.accMul(num1, num2, num3));
//18631.5
```

### accMul Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被乘数 | number | string | — | — |
| arg2 | 乘数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accDiv 精确除法,返回浮点数

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accDiv(num1, num2));
//2070.17
console.log(Utils.accDiv(num1, num2, num3));
//2070.166667
```

### accDiv Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被除数 | number | string | — | — |
| arg2 | 除数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accMulB 精确乘法,返回BigDecimal类型

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accMulB(num1, num2));
//i {ind: 1, form: 0, mant: [1,8,6,3,1,5], exp: -1}
console.log(Utils.accMulB(num1, num2, num3));
//i {ind: 1, form: 0, mant: [1,8,6,3,1,5], exp: -1}
```

### accMulB Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被乘数 | number | string | — | — |
| arg2 | 乘数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accDivB 精确除法,返回BigDecimal

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accDivB(num1, num2));
//i {ind: 1, form: 0, mant: [2,0,7,0,1,6,6,6,6,7], exp: -1}
console.log(Utils.accDivB(num1, num2, num3));
//i {ind: 1, form: 0, mant: [2,0,7,0,1,6,6,6,6,7], exp: -1}
```

### accDivB Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被除数 | number | string | — | — |
| arg2 | 除数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accMulS 精确减法,返回String

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accMulS(num1, num2));
//"18631.5"
console.log(Utils.accMulS(num1, num2, num3));
//"18631.5"
```

### accMulS Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被减数 | number | string | — | — |
| arg2 | 减数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accSubS 精确减法,返回String

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accSubS(num1, num2));
//"6207.5"
console.log(Utils.accSubS(num1, num2, num3));
//"6207.5"
```

### accSubS Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被减数 | number | string | — | — |
| arg2 | 减数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accAdS 精确加法,返回String

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accAdS(num1, num2));
//"6213.5"
console.log(Utils.accAdS(num1, num2, num3));
//"6213.5"
```

### accAdS Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被加数 | number | string | — | — |
| arg2 | 加数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accAddS 精确加法,返回String

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accAddS(num1, num2));
//"6213.5"
console.log(Utils.accAddS(num1, num2, num3));
//"6213.5"
```

### accAddS Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被加数 | number | string | — | — |
| arg2 | 加数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |


## accAd 精确加法,返回浮点数

``` js
import Utils from 'yjpl-utils';

const num1 = 6210.5;
const num2 = 3.0;
const num3 = 10;

console.log(Utils.accAd(num1, num2));
//6213.5
console.log(Utils.accAd(num1, num2, num3));
//6213.5
```

### accAd Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| arg1 | 被加数 | number | string | — | — |
| arg2 | 加数 | number | string | — | — |
| precision | 精确度,在0-17之间,如果没有提供，取arg1与arg2小数位数比较多者 | number | string | — | — |

## getBusinessOrgCode/setBusinessOrgCode 业务编码传递

```js
<template>
    <page>
        <button @click="getCode">{{msg}}</button><br/>
        <button @click="setCode">{{msgs}}</button><br/>
        <button @click="doLink">{{link}}</button>
        <input type='text' v-model="businessOrgCode">
    </page>
</template>
<script>
import { YJPage } from 'yjpl-core';
import Utils from 'yjpl-utils';
export default class BusinessOrgCode extends YJPage {
    constructor() {
        super();
        this.utils = Utils;
    }
    data() {
        return {
            msg: '获取业务编码',
            msgs: '设置业务编码',
            link: '跳转',
            businessOrgCode: ''
        }
    }
    mounted() {
        
    }
    methods() {
        return {
            getCode() {
                // 从路由中获取。
                this.businessOrgCode = this.utils.getBusinessOrgCode(this.route);
                // 或 
                // this.businessOrgCode = this.utils.getBusinessOrgCode(this);
                // 默认从 url中获取
                // this.businessOrgCode = this.utils.getBusinessOrgCode(); // location.href
                // this.businessOrgCode = this.utils.getBusinessOrgCode(url);
            },
            setCode() {
                var boc = this.businessOrgCode || 10086
                this.utis.setBusinessOrgCode(boc);
            },
            doLink() {
                this.$router.push({ path: '/myrouterpath', query: { businessOrgCode: this.businessOrgCode } });
                // this.$router.push({ name: 'myrouterpath', params: { businessOrgCode: this.businessOrgCode } });
            }
        }
    }
}
</script>
<style>

</style>
```

### UI3.0用法
更新 necp.core.web jar包，js中引入ecp.utils
```javascript
require(['ecp.utils'], function (utils) {
	var businessOrgCode = utils.getBusinessOrgCode(); // location.href
    // var businessOrgCode = utils.getBusinessOrgCode(url);
    window.open("https://www.ygsoft.com?businessOrgCode=" + businessOrgCode);
    // 或者用a标签设置
});
```

### getBusinessOrgCode/setBusinessOrgCode Attributes
| 方法名 | 说明 | 参数 |
| ---- | ---- | ---- |
| getBusinessOrgCode | 获取业务功能编码 | url: string 或 route: any |
| setBusinessOrgCode | 设置业务功能编码 | void |
