import { createRouter, createWebHistory } from "vue-router";
import MenuNav from "../component/menu.vue";
import BS from "../view/BalnaceSheet.vue";
import AdminDash from "../view/AdminDash.vue";
import Home from "../permitAll/homePage.vue";
import LoginPage from "../permitAll/LoginPage.vue";
import LogoutPage from "../permitAll/LogoutPage.vue";

var appname = " - HAB";

const routes = [
  // Routes


  // Components based Routes
  {
    path: "/component/menu", name: "menuNav", component: MenuNav,
    meta: {title: "MenuNav" + appname }
  },
  {
    path: "/home", name: "Home", component: Home,
    meta: { permitAll : true, title: "home" + appname }, 
    children:[
      {path: "login", name: "LoginPage", component: LoginPage},
      {path: "logout", name: "LogoutPage", component: LogoutPage}
    ]
  },
  {
    path: "/view/BS", name: "BS", component: BS,
    meta: { requiresUser : true, title: "BS" + appname }
  },
  {
    path: "/admin", name: "admin", component: AdminDash,
    meta: { requiresAdmin: true, title: "BS" + appname }
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
  //meta 속성에 정의된 title 값을 사용하여 브라우저의 제목을 동적으로 변경하는 역할을 합니다.
  document.title = to.meta.title;
  if(!to.matched.some(record => record.meta.permitAll) && !isLoging()){
    next({ path: '/home/login'});
  }else{
    next();
  }

    //children으로 권한 상속되는 구조에서 사용
    //상위 라우터부터 현재까지 권한 확인 (하나라도 권한 필요시 상속으로 권한 확인함)
    //true, false 상관없이 메타필드가 있으면 권한 확인 
  if (to.matched.some(record => record.meta.requiresAdmin)) {
    if (!isAdmin()) {
      // ADMIN 권한이 없으면 홈으로 리다이렉트
      next({ path: '/' });
    } else {
      next(); // 권한이 있으면 계속 진행
    }
  } else if (to.matched.some(record => record.meta.requiresUser)) {
    if (!isUser()) {
      // USER 권한이 없으면 홈으로 리다이렉트
      next({ path: '/' });
    } else {
      next(); // 권한이 있으면 계속 진행
    }
  } else {
    next(); // 권한이 필요하지 않은 페이지는 계속 진행
  }

  next();
});
function isLoging(){
  console.log("isLoging?");
  return false;
}
function isAdmin(){
  console.log("isAdmin?");
  return true;
}
function isUser(){
  console.log("isUser?");
  return true;
}

export default router;
