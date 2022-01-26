<template>
    <div>
        <!-- 내가 쓴 댓글 -->
        <div v-for="(item, index) in commentNo" :key="index">
            <Comment :commentNo="item"></Comment>
        </div>
    </div>
</template>

<script>
import Comment from "@/components/MySaza/Comment/Comment.vue";
export default {
    name: 'Memberofparty',
    components:{
        Comment,
    },
    data() {
        return {
            commentNo:[1,2,3,4,5,6,7,8,9,10],
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