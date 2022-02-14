<template>
  <div>
    <img src="@/assets/kakaoLogin.png" style="cursor: pointer" @click="loginWithKakao" />
  </div>
</template>

<script>
export default {
  name: "Kakaologin",

  data() {
    return {};
  },

    methods: {
        loginWithKakao() {
            const params = {
                // redirectUri: "http://localhost:8081/",
                redirectUri: "http://i6c102.p.ssafy.io/",
            };
            window.Kakao.Auth.authorize(params);
        },
    },
  },
};
</script>

<style lang="scss" scoped></style>