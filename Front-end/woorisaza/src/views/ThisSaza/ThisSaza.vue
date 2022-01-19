<template>
    <div class="mt-3" style="background-color : #FFFDF2; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container >
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                    <component :is="selectComponent"></component>
                                </div>
                            </div>
                        </b-col>
                        <b-col></b-col>
                    </b-row>
                </b-container>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
            </div>
    </div>
</template>

<script>
import Board from "@/views/ThisSaza/Board.vue";
import ArticleDetail from "@/views/ThisSaza/ArticleDetail.vue";
export default {
    name: 'Thissaza',
    components : {
        Board,
        ArticleDetail,
    },
    data() {
        return {
            selectComponent : "Board",
        };
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
                this.selectComponent = "Board";
            }
            else{
                this.selectComponent = "ArticleDetail";
            }
        },
    },
    mounted() {
    },

    methods: {
    },
};
</script>

<style >
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

</style>