### QzzQueryGrid - 格式显示：手机号、银行卡、身份证、金额

:::demo QueryGrid31

```html
<template>
  <tl-row>
    <tl-col :span="24" style="height: 300px;margin:8px;">
      <qzz-query-grid :option="gridoption" v-model="griddata" ref="grid"></qzz-query-grid>
    </tl-col>
  </tl-row>
</template>

<script>
  export default {
    data() {
      return {
        gridoption: {
          Align: 'alClient',
          colNames: [
            '用户名称',
            '邮件',
            '电话号加密',
            '电话号',
            '银行卡加密',
            '银行卡',
            '身份证加密',
            '身份证',
            '金额',
            '金额加密'
          ],
          colModels: [
            { name: 'userName', frozen: true },
            { name: 'email' },
            { name: 'mobile', formatType: 'mobile', formatHide: true },
            { name: 'mobile2', formatType: 'mobile', formatHide: false },
            { name: 'bank', formatType: 'bank', formatHide: true },
            { name: 'bank2', formatType: 'bank', formatHide: false },
            { name: 'idcard', formatType: 'idcard', formatHide: true },
            { name: 'idcard2', formatType: 'idcard', formatHide: false },
            { name: 'money', formatType: 'money', scale: 2 },
            { name: 'money', formatType: 'money', scale: 2, formatHide: true }
          ]
        },
        griddata: [
          {
            userName: '张三丰',
            email: 'zhangsan@ygsoft.com',
            mobile: '13570628361',
            mobile2: '13570628361',
            bank: '6228480830080955',
            bank2: '6228480830080955',
            idcard: '440402202305169000',
            idcard2: '440402202305169000',
            money: 10000,
            money: 10000
          },
          {
            userName: '李四',
            email: 'zhangsan@ygsoft.com',
            mobile: '13570628361',
            mobile2: '13570628361',
            bank: '6228480830080955',
            bank2: '6228480830080955',
            idcard: '440402202305169000',
            idcard2: '440402202305169000',
            money: 123.56,
            money: 123.56
          },
          {
            userName: '王五',
            email: 'zhangsan@ygsoft.com',
            mobile: '13570628361',
            mobile2: '13570628361',
            bank: '6228480830080955',
            bank2: '6228480830080955',
            idcard: '440402202305169000',
            idcard2: '440402202305169000',
            money: 405689.23,
            money: 405689.23
          },
          {
            userName: '宋江',
            email: 'zhangsan@ygsoft.com',
            mobile: '13570628361',
            mobile2: '13570628361',
            bank: '6228480830080955',
            bank2: '6228480830080955',
            idcard: '440402202305169000',
            idcard2: '440402202305169000',
            money: 0.23,
            money: 0.23
          },
          {
            userName: '李鬼',
            email: 'zhangsan@ygsoft.com',
            mobile: '13570628361',
            mobile2: '13570628361',
            bank: '6228480830080955',
            bank2: '6228480830080955',
            idcard: '440402202305169000',
            idcard2: '440402202305169000',
            money: 200.4,
            money: 200.4
          },
          {
            userName: '郭靖',
            email: 'zhangsan@ygsoft.com',
            mobile: '13570628361',
            mobile2: '13570628361',
            bank: '6228480830080955',
            bank2: '6228480830080955',
            idcard: '440402202305169000',
            idcard2: '440402202305169000',
            money: 5000000,
            money: 5000000
          }
        ]
      };
    }
  };
</script>
```

:::
