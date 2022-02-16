<template>
    <div>
        <!-- 내가 쓴 댓글 -->
        <div v-for="(item, index) in reviewList" :key="index">
            <Review :review="item"></Review>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
import Review from "@/components/MySaza/Review/Review.vue";
export default {
    name: 'Reviews',
    components:{
        Review,
    },
    data() {
        return {
            id : this.$route.params.id,
            reviewList:[],
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/review/"+this.id,
        }).then(({data})=>{
            console.log(data)
           this.reviewList = data.reviewList;
        })
    },
    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>