<template>
  <div>
    <div>
      <label for="id">id: </label>
      <input v-model="user.id" type="text" />
    </div>
    <div>
      <label for="password">pw: </label>
      <input v-model="user.password" type="password" />
    </div>
    <button @click="postSignUser">submit</button>
  </div>
  <p style="white-space: pre-line;">
    {{ user.id }}, {{ user.password }}, {{ user.enabled }}
  </p>
  </template>

<script>
// import * as xlsx from 'xlsx'

export default {
  name: "SignIng",
  data() { //변수생성
    return {
      user : {
         id : ""
        ,password : ""
        ,enabled : 'Y'
      }
    }
  },
  watch: {},
  methods: {
    postSignUser() {
      this.$axios
        .post(this.$serverUrl + "/api/auth/sign", this.user)
        .then((res) => {
          console.log(res.staus);
          console.log(res.data);
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          console.log("항상 마지막에 실행");
        });
    },
  }
}
</script>