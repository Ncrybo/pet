const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer: {
    host: 'localhost',
    open: true,
    proxy: {
      '/pet-api/*': {
        target: 'http://localhost:18083',
        changeOrigin: true,
        pathRewrite: {
          '^/pet-api': '/'
        }
      },
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
