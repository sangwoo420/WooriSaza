<template>
    <div style="">
        <div>
            <b-navbar toggleable="lg" type="white" >

                <img src="@/assets/icon.png" style="width:70px">
                <img src="@/assets/name.png" style="width:120px">
                <b-collapse id="nav-collapse" is-nav>

                <b-navbar-nav class="ml-auto">
                    <!-- 로그인-->
                    <div v-if="access_token==null">
                        <b-button pill style="background-color:#F1A501;border:0;" @click="$bvModal.show('signlogin')">로그인</b-button>  &nbsp; &nbsp;
                        <!-- <b-button pill style="background-color:#F1A501;border:0;" @click="$bvModal.show('signlogin')">회원가입</b-button>&nbsp; &nbsp; -->
                        <b-modal id="signlogin" hide-footer size="sm">
                            <template #modal-title>
                            로그인
                            </template>
                            <div class="d-block text-center">
                                <Kakaologin></Kakaologin>
                            </div>
                        </b-modal>
                    </div>
                    
                    <!-- 마이페이지 -->
                    <div v-if="access_token!=null">
                         <b-nav-item-dropdown right>
                        <template #button-content>
                            <em><img src="@/assets/saza.png" style="width:40px"></em>
                        </template>
                        <b-dropdown-item href="#">마이페이지</b-dropdown-item>
                        <b-dropdown-item href="#">로그아웃</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </div>
                   

                </b-navbar-nav>
                </b-collapse>
            </b-navbar>
        </div>
        <div>
            <div>
                <b-nav align="center" >
                    <b-nav-item router-link to="/" :active="homeActive" @click="getHomeActive">홈</b-nav-item>
                    <b-nav-item router-link to="/board" :active="thisSazaActive" @click="getThisSazaActive">이거사자</b-nav-item>
                    <b-nav-item router-link to="/myboard" :active="mySazaActive" @click="getMySazaActive">마이사자</b-nav-item>
                    <b-nav-item router-link to="/guide" :active="guideActive" @click="getGuideActive">가이드</b-nav-item>
                </b-nav>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios"
import Kakaologin from "@/components/layout/kakaoLogin.vue"
export default {
    name: 'Navbar',
    components:{
        Kakaologin,
    },
    data() {
        return {
            homeActive:"active",
            thisSazaActive : null,
            mySazaActive: null,
            guideActive:null,

            data:{
                    grant_type : "authorization_code",
                    client_id : "067178783202c62976d9ac82175e67cd",
                    redirect_uri : "http://localhost:8080/",
                    code : this.$route.query.code,
            },
            queryString : null,
            access_token : null,
        };
    },
    watch:{
    },
    created(){
    },
    mounted() {
        this.getKakaoQuery();
        this.getKakaoToken();
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

        getKakaoQuery(){
            if(this.data.code!=null){
            this.queryString = Object.keys(this.data)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(this.data[k]))
            .join('&');
            }
        },
        getKakaoToken(){
            if(this.queryString!=null){
                axios({
                    method : "post",
                    url : "https://kauth.kakao.com/oauth/token",
                    headers : {
                        "Content-type" : "application/x-www-form-urlencoded;charset=utf-8",
                    },
                    data: this.queryString,
                }).then(({data})=>{
                    console.log(data)
                    this.access_token = data.access_token;
                    console.log(this.access_token)
                    axios({
                        mathod : "get",
                        url : "https://cors-anywhere.herokuapp.com/https://kapi.kakao.com/v1/user/access_token_info",
                        headers : {
                            "Authorization" : "Bearer "+data.access_token,
                            "Content-type" : "application/x-www-form-urlencoded;charset=utf-8",
                        },
                    }).then(({data})=>{
                        console.log(data)
                    })
                })
            }
        }
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
      color:#F1A501;
    }
</style>