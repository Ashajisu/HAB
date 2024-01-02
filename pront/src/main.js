import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import './assets/tailwind.css';
// import vClickOutside from "click-outside-vue3";
// import axios from 'axios';

const app = createApp(App);
app.use(router);
// app.use(vClickOutside);
app.mount("#app");
// app.config.globalProperties.$axios = axios;
// app.config.globalProperties.$serverUrl = '//localhost:7070';
