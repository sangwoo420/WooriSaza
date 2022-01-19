<template>
    <div style="">
        <div>
            <b-navbar toggleable="lg" type="white" >

                <img src="@/assets/icon.png" style="width:70px">
                <img src="@/assets/name.png" style="width:120px">
                <b-collapse id="nav-collapse" is-nav>

                <b-navbar-nav class="ml-auto">
                    <!-- <b-button pill style="background-color:#F1A501;border:0;">로그인</b-button>  &nbsp; &nbsp;
                    <b-button pill style="background-color:#F1A501;border:0">회원가입</b-button>&nbsp; &nbsp; -->
                    <b-nav-item-dropdown right>
                    <template #button-content>
                        <em><img src="@/assets/saza.png" style="width:40px"></em>
                    </template>
                    <b-dropdown-item href="#">마이페이지</b-dropdown-item>
                    <b-dropdown-item href="#">로그아웃</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
                </b-collapse>
            </b-navbar>
        </div>
        <div>
            <div>
                <b-nav align="center">
                    <b-nav-item router-link to="/" :active="homeActive" @click="getHomeActive">홈</b-nav-item>
                    <b-nav-item router-link to="/board" :active="thisSazaActive" @click="getThisSazaActive">이거사자</b-nav-item>
                    <b-nav-item :active="mySazaActive" @click="getMySazaActive">마이사자</b-nav-item>
                    <b-nav-item :active="guideActive" @click="getGuideActive">가이드</b-nav-item>
                </b-nav>
            </div>
        </div>
    </div>
</template>

<script>
// import {axios_contact} from "@/common.js"
export default {
    name: 'Navbar',
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
            this.$store.commit('currentArticle',0);
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
      color:black
    }
.nav-link.active{
      color:#F1A501;
    }
</style>