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
                                    <div>
                                        <my-profile/>
                                    </div>
                                    <!-- 마이페이지 네비게이션 바 -->
                                    <div>
                                        <my-navbar/>
                                    </div>
                                    <!-- 게시글 목록 -->
                                    <component :is="selectComponent">
                                      
                                    </component>
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
import MyBoard from "@/views/MySaza/MyBoard.vue";
import MyArticleDetail from "@/views/MySaza/MyArticleDetail.vue";
import MyNavbar from "@/components/MySaza/MyNavbar.vue";
export default {
    name: 'MyPage',
    components : {
        MyBoard,
        MyArticleDetail,
        MyProfile,
        MyNavbar,
    },
    data() {
        return {
            selectComponent : "MyBoard",
            
        };
    },
    created(){
        if(this.$route.params.articleno==null){
            this.selectComponent = "MyBoard";
        }
        else{
            this.selectComponent = "MyArticleDetail";
        }
    },
    computed:{
        getArticlNo(){
            // return this.$store.getters.getArticleNo;
            return this.$route.params.articleno;
        }
    },
    watch:{
        getArticlNo(val){
            if(val==null){
                this.selectComponent = "MyBoard";
            }
            else{
                this.selectComponent = "MyArticleDetail";
            }
        },
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