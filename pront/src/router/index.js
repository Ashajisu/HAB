import { createRouter, createWebHistory } from "vue-router";
import MenuNav from "../component/menu.vue";
import BS from "../view/BalnaceSheet.vue";

var appname = " - HAB";

const routes = [
  // Routes


  // Components based Routes
  {
    path: "/component/menu",
    name: "menuNav",
    component: MenuNav,
    meta: { title: "MenuNav" + appname }
  },
  {
    path: "/view/BS",
    name: "BS",
    component: BS,
    meta: { title: "BS" + appname }
  }


];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
//
  linkExactActiveClass: "exact-active"
});

//전역 네비게이션 가드(Global Navigation Guard) : 라우터로 이동하기 전에 실행되는 함수지정, next()는 필수
router.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  next();
});

export default router;
