const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  // npm run build target dir : backend
  outputDir : "../back_springMVC/src/main/resources/static"

  // npm run server : backend url
  // devServer: {
  //   proxy: {
  //     '/api':{
  //       target: "http://localhost:7070/api", //request만 server에서 처리, 화면은 vue router가 처리
  //       changeOrigin: true, //cros문제 해결
  //       onProxyReq: (proxyReq, req, res) => {
  //         // Spring Security에서 사용하는 쿠키 이름을 변경
  //         const yourCookieName = 'JSESSIONID';

  //         // Vue 개발 서버의 쿠키를 가져와서 프록시 요청에 추가
  //         if (req.headers.cookie) {
  //           const cookies = req.headers.cookie.split(';').map(cookie => cookie.trim());
  //           const targetCookie = cookies.find(cookie => cookie.startsWith(`${JSESSIONID}=`));

  //           if (targetCookie) {
  //             const cookieValue = targetCookie.split('=')[1];
  //             proxyReq.setHeader('cookie', `${JSESSIONID}=${cookieValue}`);
  //           }
  //         }
  //       }
  //     }
  //   }
  // }
  
})
