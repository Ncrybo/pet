const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer: {
    host: 'localhost',
    open: true,
    proxy: {
      '/huawei': {
        target: 'https://oauth-login.cloud.huawei.com',
        changeOrigin: true,
        ws: true,
        // secure: false,
        pathRewrite: {
          '^/huawei':"/"
        }
      },
      
    }
  }
});
