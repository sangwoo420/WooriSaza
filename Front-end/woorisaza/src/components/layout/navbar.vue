<template>
    <div style="position:fixed; width:100%">
        <div>
            <b-navbar toggleable="lg" type="white" >

                <img src="@/assets/icon.png" style="width:80px">
                <img src="@/assets/name.png" style="width:140px">
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
                    <b-nav-item  active>홈</b-nav-item>
                    <b-nav-item >이거사자</b-nav-item>
                    <b-nav-item>마이사자</b-nav-item>
                    <b-nav-item >가이드</b-nav-item>
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
        };
    },
    watch:{
    },
    created(){
    },
    mounted() {
    },

    methods: {
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