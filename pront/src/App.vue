<template>
  <!-- App -->
  <div class="flex bg-gray-50 font-lexend dark:bg-gray-900">
    <MenuNav></MenuNav>
  </div>
  <div class="body">
      <p>body</p>
      <RouterView></RouterView>
  </div>
  <!-- End app -->
</template>

<script>
  // Vue components
  import MenuNav from "./component/menu.vue";
  export default {
    name: "App",

    data() {
      return {
      };
    },

    components: {
      MenuNav,
      // BS
    },
    methods: {
    },
    watch: {
    },
    mounted() {
      // Scrollbar.init(document.querySelector("#body-scroll"));

      //쿠키에 저장된 토큰을 찾아 로그인용 함수에 대입
      //import Cookies from 'js-cookie'
      // const savedToken = Cookies.get('accessToken')
      // if (savedToken) {
      //   signinByToken(savedToken).then(res => {
      //     console.log('Logined By Token')
      //   })
      // }
    }
  };
</script>

