# Des 加密工具类

## 引入方法

### 使用npm安装

``` shell
npm config set registry=http://ygsoft-npm.ygsoft.com/repository/ygsoft-npm/
npm install yjpl-des
```

### 2.在代码中引入

``` js
import des from 'yjpl-des';
```

## 使用样例

## md5加密

``` js
import des from 'yjpl-des';

console.log(des.md5('test'));
// 3qkj6f4b726238e4edac373d1264dcb6f890e363

```

### md5 Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| str | 传入值，将被用来加密为md5字符串 | String | — | — |

## signData 签名

``` js
import des from 'yjpl-des';

console.log(des.signData(_signURI, _method, _params));
/*{
      AZ: MD5Util.md5(_signString),
      RequestURI: encryptUrl,
      encryptedParam: encryptedData
    }
*/
```

### signData Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| _signURI | 请求签名的地址 | String | — | — |
| method | 请求签名的方法 | Function | — | — |
| params | 待签名的参数 | Object | — | — |

## getCmSign 签名

``` js
import des from 'yjpl-des';

console.log(des.getCmSign());
/* false || cm签名*/
```

## signDatawithoutCheck 签名

``` js
import des from 'yjpl-des';

console.log(des.signDatawithoutCheck(_signURI, _method, _params));
/*{
      AZ: MD5Util.md5(_signString),
      RequestURI: encryptUrl,
      encryptedParam: encryptedData
    }
*/
```

### signDatawithoutCheck Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| _signURI | 请求签名的地址 | String | — | — |
| method | 请求签名的方法 | Function | — | — |
| params | 待签名的参数 | Object | — | — |

## getCmSignwithoutCheck 签名

``` js
import des from 'yjpl-des';

console.log(des.getCmSignwithoutCheck());
/* false || cm签名*/
```

## doReplace 加密文本

``` js
import des from 'yjpl-des';

console.log(des.doReplace("hello", "t e s t"));
/*"lllol@5"*/
```

### doReplace Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| text | 加密文本 | String | — | — |
| key | 密钥 | String | — | — |

## doEncrypt 加密(三个密钥)

必须传入至少一个 密钥(随机组合的字符串，建议使用) 。并且解密时，完全遵循加密时的密钥策略

``` js
import des from 'yjpl-des';

console.log(des.doEncrypt("hello"));
/*8709F8E7A474CA583EDAF32A15E93D8D*/
```

### doEncrypt Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| data | 需要加密的数据 | String | — | — |

## doDecrypt 解密(三个密钥,解密密钥同加密密钥)

必须遵循加密时的密钥策略

``` js
import des from 'yjpl-des';

console.log(des.doDecrypt("8709F8E7A474CA583EDAF32A15E93D8D"));
/*hello*/
```

### doDecrypt Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| data | 需要解密的数据 | String | — | — |

## encodeBase64 字符串编码base64编码

``` js
import des from 'yjpl-des';

console.log(des.encodeBase64("hello",false));
/*aGVsbG8=*/
console.log(des.encodeBase64("你好",true));
/*5L2g5aW9*/
```

### encodeBase64 Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| string | 原始字符串 | String | — | — |
| hasUTF16 | str是否包含中文等utf16字符 | boolean | — | — |

## decodeBase64 base64解码码转成字符串

``` js
import des from 'yjpl-des';

console.log(des.decodeBase64("aGVsbG8=",false));
/*hello*/
console.log(des.decodeBase64("5L2g5aW9",true));
/*你好*/
```

### decodeBase64 Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| string | 原始字符串 | String | — | — |
| hasUTF16 | str是否包含中文等utf16字符 | boolean | — | — |

## utf16to8 utf16转utf8

``` js
import des from 'yjpl-des';

console.log(des.utf16to8("hello"));
/*hello*/

```

### utf16to8 Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| string | 原始字符串 | String | — | — |

## utf8to16 utf8转utf16

``` js
import des from 'yjpl-des';

console.log(des.utf8to16("hello"));
/*hello*/

```

### utf8to16 Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| string | 原始字符串 | String | — | — |

## hashCode 转hashCode

``` js
import des from 'yjpl-des';

console.log(des.hashCode("hello"));
/*99162322*/

```

### hashCode Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| string | 原始字符串 | String | — | — |

## encodeSearch 加密url参数

``` js
import des from 'yjpl-des';

console.log(des.encodeSearch("https://www.baidu.com/info?name=1&id=4"));
/*https://www.baidu.com/info?_d=bmFtZT0xJmlkPTQmX2g9LTE3NDc2NTI4Mzk%3D*/

```

### encodeSearch Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | 有?是完整的url,否则认为是参数部分 | String | — | — |

## decodeSearch 解密url参数

``` js
import des from 'yjpl-des';

console.log(des.decodeSearch("https://www.baidu.com/info?_d=bmFtZT0xJmlkPTQmX2g9LTE3NDc2NTI4Mzk%3D"));
/*https://www.baidu.com/info?name=1&id=4&_h=-1747652839*/

```

### decodeSearch Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| url | 有?是完整的url,否则认为是参数部分 | String | — | — |

## setCookie 设置kookie

``` js
import des from 'yjpl-des';

console.log(des.setCookie("name","8888",new Date(1137075575000)));
/*name=8888*/

```

### setCookie Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| name | key | String | — | — |
| value | cookie值 | String | — | — |
| time | 时间 | Date | — | — |

## getCookie 获取kookie

``` js
import des from 'yjpl-des';

console.log(des.getCookie("name"));
/*8888*/

```

### getCookie Attributes
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------  |-------- |
| name | key | String | — | — |



