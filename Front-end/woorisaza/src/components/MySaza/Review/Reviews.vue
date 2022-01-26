<template>
    <div>
        <!-- 내가 쓴 댓글 -->
        <div v-for="(item, index) in reviewNo" :key="index">
            <Review :reviewNo="item"></Review>
        </div>
    </div>
</template>

<script>
import Review from "@/components/MySaza/Review/Review.vue";
export default {
    name: 'Memberofparty',
    components:{
        Review,
    },
    data() {
        return {
            reviewNo:[1,2,3,4,5,6,7,8,9,10],
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