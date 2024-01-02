import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import './assets/tailwind.css';
// import vClickOutside from "click-outside-vue3";
import axios from 'axios';

const app = createApp(App);
app.use(router);
// app.use(vClickOutside);
app.mount("#app");

app.config.globalProperties.$axios = axios; //기본 요청
app.config.globalProperties.$serverUrl = '//localhost:7070'; //서버url

    const instance = axios.create({
        baseURL: '/api', // 프록시 경로
        withCredentials: true, // 쿠키를 전송하기 위해 필요한 설정
    });
app.config.globalProperties.$auth = instance; //쿠키포함 요청
