# CDN

为方便项目更新YJPL相关依赖，并提高前端资源的加载的性能，YJPL内置了CDN功能。

## 开启 CDN

开启 `CDN` 需要在项目中的 `package.json` 配置相关 `scripts` 命令(请根据情况配置，生产环境则配置 `build`)，配置如下：

```json
{
  "scripts": {
    "build": "cross-env CDN=true BASE_URL=/necp/mapp/dqd/yjpl/ yjpl-cli-service build"
  },
}
```

## 部署静态资源

开启 `CDN` 后，YJPL相关的前端静态资源，统一通过静态资源服务获取，所以需要配置静态资源服务，请根据服务情况选择以下方式部署。

### 静态资源获取目录

平台静态资源获取地址（根据版本调整目录）: 

云盘/企业空间/公司产品发布/信创平台部/JT增量版本发布/正式增量/V8.5.0

## 如何对组件库进行 CDN 改造

### CDN 资源打包输出
如需打包出CDN相关资源，在 `webpack` 打包时，以下仅展示必配参数。

``` js
const TerserPlugin = require('terser-webpack-plugin');
// 可以通过引入该配置，获取到当前所有公司内已配置 CDN 的包的 externals 信息
const { externals } = require('@yjpl/cli-service/plugins/cdn/packages');

module.exports = {
  output: {
    // * 建议将这个路径设为下文 cdn-package.xml 中 directory 的路径
    path: path.resolve(__dirname, '../lib'),
    filename: 'yjpl-utils.js',
    // * 以大驼峰命名，表示项目导出后的全局变量， externals 中会用到
    library: 'YjplUtils',
    // * 必须为 umd 格式
    libraryTarget: 'umd',
    // *
    libraryExport: 'default',
    // *
    umdNamedDefine: true
  },
  // * 必须配置参数，请将引用到的包都配置到此处，cdn配置请勿使用 webpack-node-externals
  // 1.公司内部的，请在此处排除，且需要保证你排除的依赖已提供 CDN，并且需要在 package.json 中的 dependencies 中依赖
  // 2.只有该包使用的三方包，请勿进行排除，否则会无法运行，导致白屏，自行引入的三方包体积请自行承担
  externals: {
    ...externals,
    'yjpl-event': 'YjplEvent',
    'yjpl-Des': 'YjplDes'
  },
  // 代码压缩
  optimization: {
    minimizer: [
      new TerserPlugin({
        terserOptions: {
          output: {
            comments: false
          }
        }
      })
    ]
  }
};

```

### 增加 cdnConfig 属性
如需在项目中自动引入cdn，请在相应 `npm` 包中的 `package.json`，配置属性 `cdnConfig`。

`cdnConfig` 具有以下参数：

`library`: 同 `webpack` 配置中 `library` 的配置。

`path`: 为项目引入 `cdn` 的资源路径。
```json
{
  "cdnConfig": {
    "library": "YjplUi",
    "path": "/jt/mapp/tlead/yjpl-ui.js"
  }
}
```

### 登记 CDN 信息
请按部门在以下在线表格中登记组件信息，然后联系 `YJPL` 相关维护人员，申请添加 `CDN` 支持。

[在线登记表格](https://devcloud.ygsoft.com/fmp-bud-estimate/member/gris/mapp/estimate/jump/index/7427d43c-dd25-408d-9637-76b6aa48f297?ecp.mapp=true)

### 通过 jenkins 输出静态资源 zip 包
可以通过以下配置，在 `jenkins` 中将静态资源抽至 `zip` 包中，进行增量。

`cdn-package.xml`
```xml
<assembly xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/assembly-1.0.0.xsd">
    <id>cdn</id>
    <!--这个id会出现在zip包名称的后面，zip的完整名是：pom.xml中的artifactId-version-id.zip -->
    <formats>
        <!--支持的打包格式有zip、tar、tar.gz (or tgz)、tar.bz2 (or tbz2)、jar、dir、war-->
        <format>zip</format>
    </formats>
    <includeBaseDirectory>false</includeBaseDirectory>
    <fileSets>
        <fileSet>
            <!-- zip 内压缩路径 -->
            <outputDirectory>/jt/mapp/tlead</outputDirectory>
            <!-- 需要抽取的资源的路径，可以将 webpack 中 output 的路径设值到这里，方便打包抽取 -->
            <directory>com.ygsoft.jt.tlead.cp.assets/src/main/resources/META-INF/resources/jt/mapp/tlead</directory>
        </fileSet>
    </fileSets>
</assembly>
```

`pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.ygsoft.jt.tlead</groupId>
  <!-- 包名 -->
	<artifactId>jt.tlead.cp.web</artifactId>
	<packaging>pom</packaging>
  <!-- 版本 -->
	<version>8.2.0</version>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	</properties>
	
	<build>
    	<plugins>
	        <plugin>
	        <groupId>org.apache.maven.plugins</groupId>
	        <artifactId>maven-assembly-plugin</artifactId>
	        <version>2.2.1</version>
	        <configuration>
	          <descriptors>
	            <descriptor>cdn-package.xml</descriptor>
	          </descriptors>
	        </configuration>
	        <executions>
	          <execution>
	            <id>make-assembly</id>
	            <phase>package</phase>
	            <goals>
	              <goal>single</goal>
	            </goals>
	          </execution>
	        </executions>
	      </plugin>
	    </plugins>
  	</build>
</project>
```

在 `jenkins` 中，将 `pom.xml` 设为打包脚本，则可生成cdn的静态资源 `zip` 包，后续请将 `zip` 包增量至相应的静态资源增量目录。

### CDN打包后调试

打包静态资源后，将静态资源部署至服务上，并在实际运行的业务项目的 `package.json` 中添加以下配置：

```json
{
  "cdnConfig": {
    "yjpl-ui": {
      "library": "YjplUi",
      "path": "/jt/mapp/tlead/yjpl-ui.js"
    }
  }
}
```

打包后部署测试。


## 静态资源路径规范

所有的静态资源路径建议以下规范进行放置：

#### 平台静态资源路径

`/jt/mapp/${业务名/项目名}/${静态资源}`

#### DAP静态资源路径

`/dap/mapp/${业务名/项目名}/${静态资源}`