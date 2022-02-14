<template>
    <div>
        <!-- 내가 쓴 후기 -->
        <div v-for="(item, index) in reviewList" :key="index">
            <Review :review="item"></Review>
        </div>
    </div>
</template>

<script>
import Review from "@/components/Mypage/Review/Review.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Reviews',
    components:{
        Review,
    },
    data() {
        return {
            reviewList:[],
            id : this.$cookie.get('id'),
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