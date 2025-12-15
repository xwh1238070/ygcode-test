## 用例

**主动联动**

写法一，标准主动联动

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "target": "target",
        "when": "{ {$self.value === '123'} }",
        "fulfill": {
          "state": {
            "visible": false
          }
        },
        "otherwise": {
          "state": {
            "visible": true
          }
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```

写法二，局部表达式分发联动

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "target": "target",
        "fulfill": {
          "state": {
            "visible": "{ {$self.value === '123'} }" //任意层次属性都支持表达式
          }
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```

写法三，相邻元素联动

```json
{
  "type": "array",
  "x-component": "ArrayTable",
  "items": {
    "type": "object",
    "properties": {
      "source": {
        "type": "string",
        "x-component": "Input",
        "x-reactions": {
          "target": ".target",
          "fulfill": {
            "state": {
              "visible": "{ {$self.value === '123'} }" //任意层次属性都支持表达式
            }
          }
        }
      },
      "target": {
        "type": "string",
        "x-component": "Input"
      }
    }
  }
}
```

写法四，基于 Schema 协议联动

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "target": "target",
        "fulfill": {
          "schema": {
            "x-visible": "{ {$self.value === '123'} }" //任意层次属性都支持表达式
          }
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```

写法五，基于 run 语句联动

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "fulfill": {
          "run": "$form.setFieldState('target',state=>{state.visible = $self.value === '123'})"
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```

写法六，基于生命周期钩子联动

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "target": "target",
        "effects": ["onFieldInputValueChange"],
        "fulfill": {
          "state": {
            "visible": "{ {$self.value === '123'} }" //任意层次属性都支持表达式
          }
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```

**被动联动**

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input"
    },
    "target": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "dependencies": ["source"], //依赖路径写法默认是取value，如果依赖的是字段的其他属性，可以使用 source#modified，用#分割取详细属性
        // "dependencies":{ aliasName:"source" }, //别名形式
        "fulfill": {
          "schema": {
            "x-visible": "{ {$deps[0] === '123'} }" //任意层次属性都支持表达式
          }
        }
      }
    }
  }
}
```

**复杂联动**

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input"
    },
    "target": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": "{ {myReaction} }" //外部传入的函数，在函数内可以实现更复杂的联动
    }
  }
}
```

**组件属性联动**

写法一，操作状态

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "target": "target",
        "fulfill": {
          "state": {
            "component[1].style.color": "{ {$self.value === '123' ? 'red' : 'blue'} }" //任意层次属性都支持表达式，同时key是支持路径表达式的，可以实现精确操作属性
          }
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```

写法二，操作 Schema 协议

```json
{
  "type": "object",
  "properties": {
    "source": {
      "type": "string",
      "x-component": "Input",
      "x-reactions": {
        "target": "target",
        "fulfill": {
          "schema": {
            "x-component-props.style.color": "{ {$self.value === '123' ? 'red' : 'blue'} }" //任意层次属性都支持表达式，同时key是支持路径表达式的，可以实现精确操作属性
          }
        }
      }
    },
    "target": {
      "type": "string",
      "x-component": "Input"
    }
  }
}
```