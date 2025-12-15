## Upload 上传

通过点击或者拖拽上传文件

### 点击上传

:::demo 通过 slot 你可以传入自定义的上传按钮类型和文字提示。可通过设置`limit`和`on-exceed`来限制上传文件的个数和定义超出限制时的行为。可通过设置`before-remove`来阻止文件移除操作。

```html
<template>
  <tl-upload
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :file-list="fileList"
    :data="{}"
    :on-remove="onRemove"
  >
    <tl-button type="primary">选择文件</tl-button>
  </tl-upload>
</template>
<script>
  // import { getUploadData, deleteUploadData } from 'yjpl-ui/lib/utils/upload';

  export default {
    data() {
      return {
        fileList: []
      };
    },
    mounted() {
      window._ecp_remote_security_state = true;
      this.getData();
    },
    methods: {
      getData() {
        // getUploadData(
        //   '/jt/mapp/tleadcpdemo',
        //   '67b0b280-4909-11ed-99bb-95558563b1de'
        // ).then((res) => {
        //   if (res && res.length) {
        //     res.forEach((item) => {
        //       item.name = item.title + '.' + item.btype.toLowerCase();
        //     });
        //     this.fileList = res;
        //   }
        // });
      },
      async onRemove(file) {
        let res = await deleteUploadData('/jt/mapp/sample', file.resId);
        if (res && res.errMsg) {
          this.$message({
            message: '删除失败',
            type: 'error',
          });
        } else {
          this.$message({
            message: '删除成功',
            type: 'success',
          });
        }
        return false;
      },
    }
  };
</script>
```
:::

### 用户头像上传

使用 `before-upload` 限制用户上传的图片格式和大小。

:::demo

```html
<template>
  <tl-upload
    class="avatar-uploader"
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :show-file-list="false"
    :data="{}"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload">
    <img v-if="imageUrl" :src="imageUrl" class="avatar">
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
  </tl-upload>
</template>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
  export default {
    data() {
      return {
        imageUrl: ''
      };
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  };
</script>
```
:::

### 照片墙

使用 `list-type` 属性来设置文件列表的样式。

:::demo

```html
<template>
  <tl-upload
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :data="{}"
    list-type="picture-card"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove">
    <i class="el-icon-plus"></i>
  </tl-upload>
  <tl-dialog :visible.sync="dialogVisible" title="图片预览">
    <img width="100%" :src="dialogImageUrl" alt="">
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        dialogImageUrl: '',
        dialogVisible: false
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      }
    }
  };
</script>
```
:::

### 文件缩略图

使用 `scoped-slot` 去设置缩略图模版。

:::demo

```html
<template>
  <tl-upload
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :data="{}"
    list-type="picture-card"
    :auto-upload="false">
      <i slot="default" class="el-icon-plus"></i>
      <div slot="file" slot-scope="{file}">
        <img
          class="el-upload-list__item-thumbnail"
          :src="file.url" alt=""
        >
        <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
          >
            <i class="el-icon-zoom-in"></i>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleDownload(file)"
          >
            <i class="el-icon-download"></i>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
          >
            <i class="el-icon-delete"></i>
          </span>
        </span>
      </div>
  </tl-upload>
  <tl-dialog :visible.sync="dialogVisible">
    <img width="100%" :src="dialogImageUrl" alt="">
  </tl-dialog>
</template>

<script>
  export default {
    data() {
      return {
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false
      };
    },
    methods: {
      handleRemove(file) {
        console.log(file);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handleDownload(file) {
        console.log(file);
      }
    }
  };
</script>
```
:::

### 图片列表缩略图

:::demo

```html
<template>
  <tl-upload
    class="upload-demo"
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :data="{}"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :file-list="fileList"
    list-type="picture">
    <tl-button type="primary">点击上传</tl-button>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
  </tl-upload>
</template>

<script>
  const img = require('../../../assets/images/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg')
  export default {
    data() {
      return {
        fileList: [{name: 'food.jpeg', url: img}, {name: 'food2.jpeg', url: img}]
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      }
    }
  };
</script>
```
:::

### 上传文件列表控制

通过 `on-change` 钩子函数来对列表进行控制

:::demo

```html
<template>
  <tl-upload
    class="upload-demo"
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :data="{}"
    :on-change="handleChange"
    :file-list="fileList">
    <tl-button type="primary">点击上传</tl-button>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
  </tl-upload>
</template>

<script>
  const img = require('../../../assets/images/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg')
  export default {
    data() {
      return {
        fileList: [{
          name: 'food.jpeg',
          url: img
        }, {
          name: 'food2.jpeg',
          url: img
        }]
      };
    },
    methods: {
      handleChange(file, fileList) {
        this.fileList = fileList.slice(-3);
      }
    }
  };
</script>
```
:::

### 拖拽上传

:::demo

```html
<template>
  <tl-upload
    class="upload-demo"
    drag
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :data="{}"
    multiple>
    <i class="el-icon-plus"></i>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
  </tl-upload>
</template>

```
:::

### 手动上传

:::demo

```html
<template>
  <tl-upload
    class="upload-demo"
    ref="upload"
    :vip-address="'/jt/mapp/sample'"
    :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'"
    :data="{}"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :file-list="fileList"
    :auto-upload="false">
    <tl-button slot="trigger" type="primary">选取文件</tl-button>
    <tl-button style="margin-left: 10px;" type="success" @click="submitUpload">上传到服务器</tl-button>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
  </tl-upload>
</template>

<script>
  const img = require('../../../assets/images/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg')
  export default {
    data() {
      return {
        fileList: [{name: 'food.jpeg', url: img}, {name: 'food2.jpeg', url: img}]
      };
    },
    methods: {
      submitUpload() {
        this.$refs.upload.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      }
    }
  }
</script>
```
:::


### 附件上传面板

:::demo 通过`tl-upload-panel`引用组件，传递`vip-address`和`ywkey`进行上传文件，附件面板组件内部管理数据（**不支持扩展**）,`base-url`是传递PDF预览（**需要pdfjs**）

```html
<template>
  <tl-upload-panel :ywkey="ywkey" :vip-address="vipAddress" :multiple="multiple" :limit="limit" :accept="'image/*'" :on-error="onError" :base-url="baseUrl" :on-accept-error="handelAcceptError"></tl-upload-panel>
</template>

<script>

export default {
    name: 'UploadPanel',
    data() {
        return {
            ywkey: '67b0b280-4909-11ed-99bb-95558563b1de',
            vipAddress: '/jt/mapp/sample',
            multiple: false,
            limit: 1,
            baseUrl: '/necp/mapp/dqd/yjpl/'
        };
    },
    methods: {
        onError(err){
            console.log('onError', err);
        },
        handelAcceptError(fileList){
          const nameArr = [];
          fileList.forEach((item)=>{
            nameArr.push(item.name);
          });
          this.$message({
            showClose: true,
            message: nameArr.join(',') + '文件格式有误，已取消上传',
            type: 'error',
            duration: 0
          });
        }
    },
};
</script>
```
:::

### 附件上传表单

:::demo 通过`tl-upload-input`引用组件，传递`vip-address`和`ywkey`进行上传文件，附件面板组件内部管理数据（**不支持扩展**）, 继承扩展`tl-upload-panel`支持上传面板参数

```html
<template>
    <tl-upload-input :uploadType="upload" :ywkey="'67b0b280-4909-11ed-99bb-95558563b1de'" :vip-address="'/jt/mapp/sample'" :on-success="success" :on-close="close" :dialog-title="'测试标题'" :accept="'.png, .jpg, .jpeg'" :on-accept-error="handelAcceptError">
    </tl-upload-input>
</template>

<script>

export default {
  name: 'UploadInput',
  data() {
    return {
      fileList: [],
      upload: 'table',
    };
  },
  methods: {
    success(res, file, fileList) {
      console.log(res, file, fileList);
    },
    close(){
      console.log('close');
    },
    handelAcceptError(fileList){
      const nameArr = [];
      fileList.forEach((item)=>{
        nameArr.push(item.name);
      });
      this.$message({
        showClose: true,
        message: nameArr.join(',') + '文件格式有误，已取消上传',
        type: 'error',
        duration: 0
      });
    }
  },
};
</script>
```
:::



## Attribute
| 参数      | 说明          | 类型      | 可选值                           | 默认值  |
|---------- |-------------- |---------- |--------------------------------  |-------- |
| action | 必选参数(如果不传vip-address，传action相当与自定义请求接口地址)，上传的地址 | string | — | — |
| vip-address | 项目的vipAddress | string | — | — |
| ywkey | 项目的业务key，不传会随机生成一个 | string | — | — |
| headers | 设置上传的请求头部 | object | — | — |
| multiple | 是否支持多选文件 | boolean | — | — |
| data | 上传时附带的额外参数 | object | — | — |
| name | 上传的文件字段名 | string | — | file |
| with-credentials | 支持发送 cookie 凭证信息 | boolean | — | false |
| show-file-list | 是否显示已上传文件列表 | boolean | — | true |
| drag | 是否启用拖拽上传 | boolean | — | false |
| accept | 接受上传的[文件类型](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#attr-accept)（thumbnail-mode 模式下此参数无效）| string | — | — |
| on-preview | 点击文件列表中已上传的文件时的钩子 | function(file) | — | — |
| on-remove | 文件列表移除文件时的钩子 | function(file, fileList) | — | — |
| on-success | 文件上传成功时的钩子 | function(response, file, fileList) | — | — |
| on-error | 文件上传失败时的钩子 | function(err, file, fileList) | — | — |
| on-progress | 文件上传时的钩子 | function(event, file, fileList) | — | — |
| on-change | 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用 | function(file, fileList) | — | — |
| before-upload | 上传文件之前的钩子，参数为上传的文件，若返回 false 或者返回 Promise 且被 reject，则停止上传。 | function(file) | — | — |
| before-remove | 删除文件之前的钩子，参数为上传的文件和文件列表，若返回 false 或者返回 Promise 且被 reject，则停止删除。| function(file, fileList) | — | — |
| list-type | 文件列表的类型 | string | text/picture/picture-card | text |
| auto-upload | 是否在选取文件后立即进行上传 | boolean | — | true |
| file-list | 上传的文件列表, 例如: [{name: 'food.jpg', url: 'https://xxx.cdn.com/xxx.jpg'}] | array | — | [] |
| http-request | 覆盖默认的上传行为，可以自定义上传的实现 | function | — | — |
| disabled | 是否禁用 | boolean | — | false |
| limit | 最大允许上传个数 |  number | — | — |
| on-exceed | 文件超出个数限制时的钩子 | function(files, fileList) | — | - |
| on-accept-error | **在拖拽上传时**与accept不相符时返回不匹配文件列表 | function(fileList) | — | - |

## Slot
| name | 说明 |
|------|--------|
| trigger | 触发文件选择框的内容 |
| tip | 提示说明文字 |

## Methods
| 方法名      | 说明          | 参数 |
|----------- |-------------- | -- |
| clearFiles | 清空已上传的文件列表（该方法不支持在 before-upload 中调用） | — |
| abort      | 取消上传请求    | （ file: fileList 中的 file 对象 ） |
| submit     | 手动上传文件列表 |  —                                |
