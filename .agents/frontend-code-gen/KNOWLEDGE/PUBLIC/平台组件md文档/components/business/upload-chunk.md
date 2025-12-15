# UploadChunk 分片上传

分片上传组件是在使用大文件上传时的一个提高性能和减少性能消耗的最好方案。

### 基本使用
基础版本包含全部功能，上传触发按钮，文件列表，进度，网速，下载，删除等

:::demo 

```html
<template>
  <tl-upload-chunk
    ref="uploader"
    :options="options"
    @remove="onRemove"
    @down="onDownload"
    @file-added="onFileAdded"
    @file-success="onFileSuccess"
    @file-progress="onFileProgress"
    @file-error="onFileError"
  >
  </tl-upload-chunk>
</template>

<script>
export default {
  data() {
    return {
      options: {
        target: "/jt/mapp/tleadcpdemo/fileupload/attachment/uploadSinglefile", //上传文件的地址，如涉及到跨域请自行配置前端或后端解决
        testChunks: true, // 是否开启校验
        chunkSize: "2097152", //分片的大小
        maxChunkRetries: 6, //最大重试分片数量，根据服务器承载量决定，越大服务器请求负荷越大
      },
    };
  },
  /*  ****上传组件回调事件参数介绍，适用于全部的事件，请参照参数名进行使用******
   *  rootFile 成功上传的文件所属的根 Uploader.File 对象
   *  file 当前成功的Uploader.File
   *  response 服务端响应内容
   *  chunk Uploader.Chunk 实例
   */
  methods: {
    // 开始上传回调
    onFileAdded(file) {
      console.log("文件开始上传回调");
    },
    // 上传中回调
    onFileProgress(rootFile, file, chunk) {
      console.log("上传中回调");
    },
    // 上传成功回调 通常会在此步骤发送合并文件请求
    onFileSuccess(rootFile, file, response, chunk) {
      console.log("上传成功回调");
    },
    // 上传错误回调
    onFileError(rootFile, file, response, chunk) {
      this.$message({
        message: response || "上传出错",
        type: "error",
      });
    },
    // 删除示例 当文件成功上传后，点击删除将会触发该事件,必要时使用
    onRemove(file, fileList, key) {
      const path = `/api/deleteSummaryAndDetailByResId?resId=${file.uniqueIdentifier}`;
      fetch(path, { method: "POST" }).then(() => {
        this.$message({
          message: "成功上传后将使用API形式删除远程文件",
          type: "success",
        });
        this.$nextTick(() => this.$refs.uploader.removeFileList(fileList, key));
      });
    },
    // 下载示例 当文件成功上传后，点击下载将会触发该事件,必要时使用
    // TODO 优化下载逻辑
    onDownload(file) {
      const path = `/api/downloadFile?resId=${file.uniqueIdentifier}`;
      fetch(path).then((res) => {
        const el = document.createElement("a");
        const url = res.url;
        const filename = file.name;
        el.href = url;
        el.download = filename;
        el.click();
      });
    },
  },
};
</script>
```

:::


### 通过插槽自定义
通过插槽添加自定义内容。

:::demo 可以通过插槽 `panel` 添加自定义列表内容，插槽返回  `fileList` ，是上传中的文件列表。

```html
<template>
  <tl-upload-chunk
    ref="uploader"
    :options="options"
    @remove="onRemove"
    @down="onDownload"
    @file-added="onFileAdded"
    @file-success="onFileSuccess"
    @file-progress="onFileProgress"
    @file-error="onFileError"
  >
    <tl-button type="primary">插槽触发元素</tl-button>
    <template #panel="{ fileList }">
      <ul>
        <li v-for="(item, key) in fileList" :key="key">{{ item.name }}</li>
      </ul>
    </template>
  </tl-upload-chunk>
</template>

<script>
export default {
  data() {
    return {
      options: {
        target: "/api/uploadSinglefile", //上传文件的地址，如涉及到跨域请自行配置前端或后端解决
        testChunks: true, // 是否开启校验
        chunkSize: "2097152", //分片的大小
        maxChunkRetries: 6, //最大重试分片数量，根据服务器承载量决定，越大服务器请求负荷越大
      },
    };
  },
  /*  ****上传组件回调事件参数介绍，适用于全部的事件，请参照参数名进行使用******
   *  rootFile 成功上传的文件所属的根 Uploader.File 对象
   *  file 当前成功的Uploader.File
   *  response 服务端响应内容
   *  chunk Uploader.Chunk 实例
   */
  methods: {
    // 开始上传回调
    onFileAdded(file) {
      console.log("文件开始上传回调");
    },
    // 上传中回调
    onFileProgress(rootFile, file, chunk) {
      console.log("上传中回调");
    },
    // 上传成功回调 通常会在此步骤发送合并文件请求
    onFileSuccess(rootFile, file, response, chunk) {
      console.log("上传成功回调");
    },
    // 上传错误回调
    onFileError(rootFile, file, response, chunk) {
      this.$message({
        message: response || "上传出错",
        type: "error",
      });
    },
    // 删除示例 当文件成功上传后，点击删除将会触发该事件,必要时使用
    onRemove(file, fileList, key) {
      const path = `/api/deleteSummaryAndDetailByResId?resId=${file.uniqueIdentifier}`;
      fetch(path, { method: "POST" }).then(() => {
        this.$message({
          message: "成功上传后将使用API形式删除远程文件",
          type: "success",
        });
        this.$nextTick(() => this.$refs.uploader.removeFileList(fileList, key));
      });
    },
    // 下载示例 当文件成功上传后，点击下载将会触发该事件,必要时使用
    // TODO 优化下载逻辑
    onDownload(file) {
      const path = `/api/downloadFile?resId=${file.uniqueIdentifier}`;
      fetch(path).then((res) => {
        const el = document.createElement("a");
        const url = res.url;
        const filename = file.name;
        el.href = url;
        el.download = filename;
        el.click();
      });
    },
  },
};
</script>
```

:::

### 单个分片使用单文件上传
目前分片上传组件只实现上传，上传成功后文件需要自己处理，下载和删除事件也需自己处理。`upload-success`是单文件上传成功返回信息，里面会有resId和ywkey，是获取文件的参数

:::demo 

```html
<tl-upload-chunk 
  ref="uploader" 
  :options="options" 
  @remove="onRemove" 
  @down="onDownload" 
  :file-added="onFileAdded"
  @file-success="onFileSuccess"
  @file-progress="onFileProgress"
  @file-error="onFileError"
  @upload-success="uploadSuccess"
  @upload-error="uploadError">
<tl-button>分片上传（小文件）判断一个分片走之前的单文件上传接口</tl-button>
</tl-upload-chunk>

<script>
  
export default {
  data() {
    return {
      options: {
        target: "/member/dap/mapp/std-ecm-imagecenter/service/bigfile/v1/uploadChunk", //上传文件的地址，如涉及到跨域请自行配置前端或后端解决
        testChunks: true, // 是否开启校验
        chunkSize: "102400", //分片的大小
        fileParameterName: 'upfile',
        vipAddress: '/jt/mapp/tleadcpdemo',
        ywkey: '67b0b280-4909-11ed-99bb-95558563b1de',
        checkSizeSingle: true, // 检查分片和文件大小，一个分片走之前的单文件上传
        sm3: true // 开启国密3加密
      },
    };
  },
  /*  ****上传组件回调事件参数介绍，适用于全部的事件，请参照参数名进行使用******
   *  rootFile 成功上传的文件所属的根 Uploader.File 对象
   *  file 当前成功的Uploader.File
   *  response 服务端响应内容
   *  chunk Uploader.Chunk 实例
   */
  methods: {
    // 开始上传回调
    onFileAdded(file) {
      // console.log("文件开始上传回调", file);
    },
    onFileAdded1(file) {
      if (file.fileType === 'image/png') {
        this.$message({
          message: "不能上传png图片",
          type: "warning",
        });
        return false;
      }
    },
    // 上传中回调
    onFileProgress(rootFile, file, chunk) {
      // console.log("上传中回调");
    },
    // 上传成功回调 通常会在此步骤发送合并文件请求
    async onFileSuccess(rootFile, file, response, chunk) {
      console.log("上传成功回调，文件名：" + file.name + "，文件标识：", file.uniqueIdentifier);
      // let params = {
      //   filename: file.name, //文件名,必填
      //   totalSize: file.size, //文件总大小,必填
      //   identifier: file.uniqueIdentifier, //文件唯一标识，如MD5,必填
      //   totalChunks: chunk.offset, //总分片数,必填
      //   relativePath: '', //相对路径，非必填
      //   type: '', //文件类型，非必填
      //   location: '', //文件位置，非必填
      // };
      // 调合并文件接口
      // const res = await Request.doPost('member/dap/mapp/std-ecm-imagecenter/service/bigfile/v1/mergeFile', params);
      // // console.log(res);
      // if (res.status === 200 || res.status === 202) {
      //   this.$message({
      //     message: "上传成功",
      //     type: "success",
      //   });
      // }
    },
    // 上传错误回调
    onFileError(rootFile, file, response, chunk) {
      this.$message({
        message: response || "上传出错",
        type: "error",
      });
    },
    // 删除示例 当文件成功上传后，点击删除将会触发该事件,必要时使用
    onRemove(file, fileList, key) {
      const path = `/api/deleteSummaryAndDetailByResId?resId=${file.uniqueIdentifier}`;
      fetch(path, {
        method: "POST"
      }).then(() => {
        this.$message({
          message: "成功上传后将使用API形式删除远程文件",
          type: "success",
        });
        this.$nextTick(() => this.$refs.uploader.removeFileList(fileList, key));
      });
    },
    // 下载示例 当文件成功上传后，点击下载将会触发该事件,必要时使用
    // TODO 优化下载逻辑
    onDownload(file) {
      const path = `/api/downloadFile?resId=${file.uniqueIdentifier}`;
      fetch(path).then((res) => {
        const el = document.createElement("a");
        const url = res.url;
        const filename = file.name;
        el.href = url;
        el.download = filename;
        el.click();
      });
    },
    uploadSuccess(uploader, file) {
      console.log('小文件上传成功， 文件名：', file.name);
    },
    uploadError(uploader, file) {
      console.log('小文件上传失败， 文件名：', file.name);
    }
  },
};
</script>
```

:::
### UploadChunk Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| options   | 基础配置          | object | -         | -     |


### UploadChunk options Attributes

| 参数     | 说明               | 类型   | 可选值               | 默认值 |
| -------- | ------------------ | ------ | -------------------- | ------ |
| auto-start    | 是否自动开始上传          | boolean | -         | true     |
| target    | 目标上传 URL，可以是字符串也可以是函数          | string/function | -         | -     |
| chunk-size    | 分片大小 （字节）        | string/number | -         | -     |
| single-file    | 单文件上传。覆盖式，如果选择了多个会把之前的取消掉          | boolean | -         | false     |
| max-chunk-retries    | 最大自动失败重试上传次数         | number | -         | 0     |
| force-chunk-size    | 是否强制所有的块都是小于等于 chunkSize 的值          | number | -         | 3    |
| query    | 其他额外的参数，这个可以是一个对象或者是一个函数          | object/function | -         | -     |
| headers    | headers的配置          | object | -         | -     |
| process-params    | 上传的参数          | object | -         | -     |
| test-method    | 校验请求的方法          | string | -         | GET    |
| checkSizeSingle    | 校验文件大小和分片大小，一个分片走单文件上传          | boolean | -         | false    |
| vipAddress    | 单文件上传vipAddress          | string | -         | ''    |
| ywkey    | 单文件上传ywkey(没有会重新生成，upload-success监听上传接口，会有信息)          | string | -         | ''    |
| sm3    | 是否开启国密3加密（默认md5）          | boolean | -         | false    |

### UploadChunk Slots

| 名称     | 说明                                              |
| -------- | ------------------------------------------------- |
| default   |触发上传的元素 |
| panle   |文件列表插槽 |

### UploadChunk Slot panle Attributes

| 参数         | 说明                   | 类型   | 可选值               | 默认值 |
| ------------ | ---------------------- | ------ | -------------------- | ------ |
| fileList      | 上传中的文件列表 | Array  | —                    | []     |


### UploadChunk  Events

| 事件名称      | 说明                           | 回调参数     |
| ------------- | ------------------------------ | ------------ |
| remove        |  文件删除事件 | file, fileList, key |
| down        |  文件下载事件 | file |
| file-success        |  文件上传成功事件 | rootFile, file, message, chunk |
| file-complete        | 根文件（文件夹）成功上传完成 | rootFile |
| file-progress        | 文件在上传中 | rootFile, file, chunk |
| file-added        | 这个事件一般用作文件校验，如果说返回了 false，那么这个文件就会被忽略，不会添加到文件上传列表中 | file, event |
| files-added        | 和 fileAdded 一样，但是一般用作多个文件的校验 | files, fileList, event |
| files-submitted        | 和 filesAdded 类似，但是是文件已经加入到上传列表中，一般用来开始整个的上传 | files, fileList, event |
| file-removed        | 文件被移除 | file |
| file-retry        | 文件重试上传事件 | rootFile, file, chunk |
| file-error        | 上传过程中出错了 | rootFile, file, message, chunk |
| upload-start        | 已经开始上传了 | selectedDate |
| complete        | 上传完毕 | - |
| catchAll        | 所有的事件 | event |
| upload-success        | 单文件上传成功事件 | uploadrFile(分片上传文件信息，内含分片上传实例), file |
| upload-error        | 单文件上传失败事件 | uploadrFile, file |
