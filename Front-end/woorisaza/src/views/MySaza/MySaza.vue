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
                                    <!-- 신분증 -->
                                    <div style="text-align:center;" @click="moveToMypage">
                                        <my-profile/>
                                    </div>
                                    <!-- 마이사자 네비게이션 바 -->
                                    <div>
                                        <my-navbar/>
                                    </div>
                                    <!-- 게시글 폼 -->
                                    <div class="mt-4">
                                        <component :is="selectComponent"> </component>
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
import MyNavbar from "@/components/MySaza/MyNavbar.vue";
import MemberOfParty from "@/components/MySaza/MemberOfParty/MemberOfParty.vue";
import BossOfParty from "@/components/MySaza/BossOfParty/BossOfParty.vue";
import Comment from "@/components/MySaza/Comment/Comments.vue";
import Review from "@/components/MySaza/Review/Reviews.vue";
import { EventBus } from "@/event-bus.js"
export default {
    name: 'Mysaza',
    components : {
        MyProfile,
        MyNavbar,
        MemberOfParty,
        BossOfParty,
        Comment,
        Review,
    },
    data() {
        return {
            selectComponent : "MemberOfParty",
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
       moveToMypage(){
           this.$router.push("/mypage").catch(()=>{});;
       }
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