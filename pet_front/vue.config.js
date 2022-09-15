const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer: {
<<<<<<< HEAD
    host: 'localhost',
=======
    host: '127.0.0.1',
>>>>>>> 0b7e9846aab2c9753fe0279f94b5d759b799f486
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
