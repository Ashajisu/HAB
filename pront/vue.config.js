const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  // npm run build target dir : backend
  outputDir : "../back_springMVC/src/main/resources/static",

  // npm run server : backend url
devServer: {
  proxy: {
    '/':{
      target: "http://localhost:7070/",
      changeOrigin: true //cros문제 해결
    }
  }
}

})
