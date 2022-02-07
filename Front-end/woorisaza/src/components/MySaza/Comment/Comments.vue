<template>
    <div>
        <!-- 내가 쓴 댓글 -->
        <div v-for="(item, index) in comments" :key="index">
            <Comment :comment="item"></Comment>
        </div>
    </div>
</template>

<script>
import Comment from "@/components/MySaza/Comment/Comment.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Comments',
    components:{
        Comment,
    },
    data() {
        return {
            comments:[],
            id : this.$params.id
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/comment/"+this.id,
        }).then(({data})=>{
            for (let index = 0; index < data.myCommentList.length; index++) {
                this.comments.push(data.myCommentList[index])
            }
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