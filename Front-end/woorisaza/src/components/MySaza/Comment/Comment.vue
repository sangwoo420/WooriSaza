<template>
    <div>
        <!-- {{articleNo}} -->
        <div class="box mt-3 p-3" style="cursor:pointer" @click="moveToArticle" >
            <b-row>
                <b-col cols="8">
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" >물 24개 나누실 분</div>
                        <div style="font-size:0.9em">그럼 아니겠어요?</div>
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:1em;font-weight:bold;">2022.01.12</div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Comment',
    props:["commentNo"],
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        moveToArticle(){
            console.log("게시글 페이지로 이동")
        },
    },
};
</script>

<style scoped>
.box{
    background-color: rgb(231, 231, 231);
}
</style>