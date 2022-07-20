const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer: {
    port: 8080,
    proxy: {
      '/huawei': {
        target: 'https://oauth-login.cloud.huawei.com',
        changeOrigin: true,
        ws: true,
        // secure: false,
        pathRewrite: {
          '^/huawei':"/"
        }
      }
      
    }
  }
});
