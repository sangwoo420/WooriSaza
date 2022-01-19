<template>
    <div>
        
        {{articleNo}}
        
    </div>
</template>

<script>
export default {
    name: 'Articledetail',
    data() {
        return {
            articleNo : this.$route.params.articleno,
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