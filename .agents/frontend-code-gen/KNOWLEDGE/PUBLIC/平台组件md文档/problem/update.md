# 如何在发布库更新依赖

### 本地开发
1. 删除 `node_modules` 文件夹。
2. 删除 `package-lock.json` 文件。
3. 执行 `npm cache clean -f` 命令。
4. 重新执行 `npm install`。

### Jenkins 构建
1. 清除工作空间
2. 删除 `package-lock.json` 文件。
3. 执行 `npm cache clean -f` 命令。
4. 重新拉去仓库，执行 `npm install`。