<template>
    <div>
        <!-- 내가 참가한 파티 목록 -->
        <div v-for="(item, index) in articleNo" :key="index">
            <Article :articleNo="item"></Article>
        </div>
    </div>
</template>

<script>
import Article from "@/components/MySaza/MemberOfParty/Article.vue";
export default {
    name: 'Memberofparty',
    components:{
        Article,
    },
    data() {
        return {
            articleNo:[1,2,3,4,5,6,7,8,9,10],
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