<template>
    <div>
        <!-- 내가 쓴 후기 -->
        <div v-for="(item, index) in reviewNo" :key="index">
            <Qna :reviewNo="item"></Qna>
        </div>
    </div>
</template>

<script>
import Qna from "@/components/Mypage/Qna/Qna.vue";
export default {
    name: 'Qnas',
    components:{
        Qna,
    },
    data() {
        return {
            QnaNo:[1,2,3,4,5,6,7,8,9,10],
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>