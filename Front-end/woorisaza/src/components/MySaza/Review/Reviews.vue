<template>
    <div>
        <!-- 나에게 달린 후기 -->
        <div v-if="reviewList.length == 0" style="text-align:center">
            아직 나에게 달린 후기가 없습니다.
        </div>
        <div v-if="reviewList.length > 0">
            <div v-for="(item, index) in reviewList" :key="index">
                <Review :review="item"></Review>
            </div>
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
            id : this.$cookie.get('id'),
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