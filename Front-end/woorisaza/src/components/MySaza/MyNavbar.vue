<template>
  <div>
    <b-nav align="center" >
      <b-nav-item router-link to="/myboard" :active="homeActive" @click="getHomeActive">참가한 파티</b-nav-item>
      <b-nav-item router-link to="/myboard" :active="thisSazaActive" @click="getThisSazaActive">개설한 파티</b-nav-item>
      <b-nav-item router-link to="/myboard" :active="mySazaActive" @click="getMySazaActive">댓글</b-nav-item>
      <b-nav-item router-link to="/myboard" :active="guideActive" @click="getGuideActive">후기</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
// import {axios_contact} from "@/common.js"
export default {
    name: 'MyNavbar',
    components:{
    },
    data() {
        return {
            homeActive:"active",
            thisSazaActive : null,
            mySazaActive: null,
            guideActive:null,
        };
    },
    watch:{
    },
    created(){
    },
    mounted() {
    },

    methods: {
        getHomeActive(){
            this.homeActive="active";
            this.thisSazaActive=null;
            this.mySazaActive=null;
            this.guideActive=null;
        },
        getThisSazaActive(){
            this.homeActive=null;
            this.thisSazaActive="active";
            this.mySazaActive=null;
            this.guideActive=null;
        },
        getMySazaActive(){
            this.homeActive=null;
            this.thisSazaActive=null;
            this.mySazaActive="active";
            this.guideActive=null;
        },
        getGuideActive(){
            this.homeActive=null;
            this.thisSazaActive=null;
            this.mySazaActive=null;
            this.guideActive="active";
        },
    },
};
</script>

<style scoped>
.nav-link{
        color:black;
        font-size: 20px;
        margin-left: 15px;
        margin-right: 15px;
    }
.nav-link.active{
      color:#F45F60;
    }
</style>