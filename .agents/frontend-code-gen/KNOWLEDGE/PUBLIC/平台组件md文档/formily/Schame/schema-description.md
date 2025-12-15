# Schema

## 描述

formily 协议驱动最核心的部分，Schema 在其中是一个通用 Class，开发可以自行使用，同时在 SchemaField 和 RecursionField 中都有依赖它，它主要有几个核心能力：

- 解析 json-schema 的能力
- 将 json-schema 转换成 Field Model 的能力
- 编译 json-schema 表达式的能力

## 构造器

```ts
class Schema {
  constructor(json: ISchema, parent?: ISchema)
}
```

基于一份 json schema 数据创建一棵 Schema Tree，保证每个 schema 节点都是包含对应方法的

## 属性

| 属性                 | 描述                                              | 类型                                                                               | 字段模型映射                                                             |
| -------------------- | ------------------------------------------------- | ---------------------------------------------------------------------------------- | ------------------------------------------------------------------------ |
| type                 | 类型                                              | SchemaTypes                                                        | GeneralField
| title                | 标题                                              | React.ReactNode                                                                    | `title`                                                                  |
| description          | 描述                                              | React.ReactNode                                                                    | `description`                                                            |
| default              | 默认值                                            | Any                                                                                | `initialValue`                                                           |
| readOnly             | 是否只读                                          | Boolean                                                                            | `readOnly`                                                               |
| writeOnly            | 是否只写                                          | Boolean                                                                            | `editable`                                                               |
| enum                 | 枚举                                              | SchemaEnum                                                          | `dataSource`                                                             |
| const                | 校验字段值是否与 const 的值相等                   | Any                                                                                | `validator`                                                              |
| multipleOf           | 校验字段值是否可被 multipleOf 的值整除            | Number                                                                             | `validator`                                                              |
| maximum              | 校验最大值(大于)                                  | Number                                                                             | `validator`                                                              |
| exclusiveMaximum     | 校验最大值（大于等于                              | Number                                                                             | `validator`                                                              |
| minimum              | 校验最小值(小于)                                  | Number                                                                             | `validator`                                                              |
| exclusiveMinimum     | 最小值（小于等于）                                | Number                                                                             | `validator`                                                              |
| maxLength            | 校验最大长度                                      | Number                                                                             | `validator`                                                              |
| minLength            | 校验最小长度                                      | Number                                                                             | `validator`                                                              |
| pattern              | 正则校验规则                                      | RegExpString                                                                       | `validator`                                                              |
| maxItems             | 最大条目数                                        | Number                                                                             | `validator`                                                              |
| minItems             | 最小条目数                                        | Number                                                                             | `validator`                                                              |
| uniqueItems          | 是否校验重复                                      | Boolean                                                                            | `validator`                                                              |
| maxProperties        | 最大属性数量                                      | Number                                                                             | `validator`                                                              |
| minProperties        | 最小属性数量                                      | Number                                                                             | `validator`                                                              |
| required             | 必填                                              | Boolean                                                                            | `validator`                                                              |
| format               | 正则校验格式                                      | ValidatorFormats     | `validator`                                                              |
| properties           | 属性描述                                          | SchemaProperties                                              | -                                                                        |
| items                | 数组描述                                          | SchemaItems                                                        | -                                                                        |
| additionalItems      | 额外数组元素描述                                  | Schema                                                                             | -                                                                        |
| patternProperties    | 动态匹配对象的某个属性的 Schema                   | SchemaProperties                                              | -                                                                        |
| additionalProperties | 匹配对象额外属性的 Schema                         | Schema                                                                             | -                                                                        |
| x-index              | UI 展示顺序                                       | Number                                                                             | -                                                                        |
| x-pattern            | UI 交互模式                                       | FieldPatternTypes | `pattern`                                                                |
| x-display            | UI 展示                                           | FieldDisplayTypes | `display`                                                                |
| x-validator          | 字段校验器                                        | FieldValidator       | `validator`                                                              |
| x-decorator          | 字段 UI 包装器组件                                | `String \| React.FC`                                                               | `decorator`                                                              |
| x-decorator-props    | 字段 UI 包装器组件属性                            | Any                                                                                | `decorator`                                                              |
| x-component          | 字段 UI 组件                                      | `String \| React.FC`                                                               | `component`                                                              |
| x-component-props    | 字段 UI 组件属性                                  | Any                                                                                | `component`                                                              |
| x-reactions          | 字段联动协议                                      | SchemaReactions                                                | `reactions`                                                              |
| x-content            | 字段内容，用来传入某个组件的子节点                | React.ReactNode                                                                    | ReactChildren                                                            |
| x-visible            | 字段显示隐藏                                      | Boolean                                                                            | `visible`                                                                |
| x-hidden             | 字段 UI 隐藏(保留数据)                            | Boolean                                                                            | `hidden`                                                                 |
| x-disabled           | 字段禁用                                          | Boolean                                                                            | `disabled`                                                               |
| x-editable           | 字段可编辑                                        | Boolean                                                                            | `editable`                                                               |
| x-read-only          | 字段只读                                          | Boolean                                                                            | `readOnly`                                                               |
| x-read-pretty        | 字段阅读态                                        | Boolean                                                                            | `readPretty`                                                             |
| definitions          | Schema 预定义                                     | SchemaProperties                                              | -                                                                        |
| $ref                 | 从 Schema 预定义中读取 Schema 并合并至当前 Schema | String                                                                             | -                                                                        |
| x-data               | 扩展属性                                          | Object                                                                             | data                                                                     |

#### 详细说明

- x-component 的组件标识与 createSchemaField 传入的组件集合的 Key 匹配
- x-decorator 的组件标识与 createSchemaField 传入的组件集合的 Key 匹配
- Schema 的每个属性都能使用字符串表达式<code v-pre>{{expression}}</code>，表达式变量可以从 createSchemaField 中传入，也可以从 SchemaField 组件中传入
- $ref 指定 Schema 预定义的格式必须是<code v-pre>#/definitions/address</code>这种格式，不支持加载远程 JSON Schema

## 内置表达式

内置表达式作用域主要用于在表达式中实现各种联动关系

### $self

代表当前字段实例，可以在普通属性表达式中使用，也能在 x-reactions 中使用

### $values

代表顶层表单数据，可以在普通属性表达式中使用，也能在 x-reactions 中使用

### $form

代表当前 Form 实例，可以在普通属性表达式中使用，也能在 x-reactions 中使用

### $observable

用于创建响应式对象，使用方式与 observable 一致

### $memo

用于创建持久引用数据，使用方式与 autorun.memo 一致

### $effect

用于响应 autorun 第一次执行的下一个微任务时机与响应 autorun 的 dispose，使用方式与 autorun.effect 一致

### $dependencies

只能在 x-reactions 中的表达式消费，与 x-reactions 定义的 dependencies 对应，数组顺序一致

### $deps

只能在 x-reactions 中的表达式消费，与 x-reactions 定义的 dependencies 对应，数组顺序一致

### $target

只能在 x-reactions 中的表达式消费，代表主动模式的 target 字段