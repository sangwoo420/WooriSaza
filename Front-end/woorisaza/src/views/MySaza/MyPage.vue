<template>
    <div class="mt-3" style="background-color : #F6FBF6; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container >
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                    <h2 style="text-align:center">마이페이지</h2>
                                    <!-- 신분증 -->
                                    <div style="text-align:center" class="mt-4">
                                        <MyProfile/>
                                    </div>
                                    <!-- 마이페이지 네비게이션 바 -->
                                    <div>
                                        <my-navbar/>
                                    </div>
                                    <!-- 게시글 목록 -->
                                    <div class="mt-4">
                                        <component :is="selectComponent"></component>
                                    </div>
                                </div>
                            </div>
                        </b-col>
                        <b-col>
                          
                        </b-col>
                    </b-row>
                </b-container>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
            </div>
    </div>
</template>

<script>
import MyProfile from "@/views/MySaza/MyProfile.vue";
import MyNavbar from "@/components/Mypage/MyNavbar.vue";
import Zzim from "@/components/Mypage/Zzim/Zzims.vue";
import Info from "@/components/Mypage/Info.vue"
import Qna from "@/components/Mypage/Qna/Qna.vue"
import Review from "@/components/Mypage/Review/Review.vue"
import { EventBus } from "@/event-bus.js"

export default {
    name: 'MyPage',
    components : {
        MyProfile,
        MyNavbar,
        Zzim,
        Info,
        Qna,
        Review,
    },
    data() {
        return {
            selectComponent : "Info",
            
        };
    },
    created(){
        EventBus.$on("selectComponent",selectComponent=>{
            this.selectComponent=selectComponent;
        })
    },
    computed:{
    },
    watch:{
    },
    mounted() {
        
    },

    methods: {
       
    },
};
</script>

<style scoped>
    .box{
        background-color:white; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        border-radius: 0.5em;
        width:100%;
        height:100%;
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
    }
    .box::-webkit-scrollbar {
        display: none; /* Chrome, Safari, Opera*/
    }

    .btn-secondary{
    width : 8em;
    height: 8em;
    background-color: #F1A501 ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 50%;
    }
    .fixed {
        position: fixed;
        top:75%;
        
    }


</style>