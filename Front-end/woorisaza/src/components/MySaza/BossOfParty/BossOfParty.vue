<template>
    <div>
        <!-- 내가 참가한 파티 목록 -->
        <div v-for="(item, index) in articleNo" :key="index">
            <Article :articleNo="item"></Article>
        </div>
    </div>
</template>

<script>
import Article from "@/components/MySaza/BossOfParty/Article.vue";
import axios from "axios"

export default {
    name: 'Memberofparty',
    components:{
        Article,
    },
    data() {
        return {
            articleNo:[],
            id : this.$route.params.id,
        };
    },

    created() {
        axios({
            method : "get",
            url : "http://localhost:8080/party/"+this.id,
        }).then(({data})=>{
            // console.log(data)
            for (let index = 0; index < data.length; index++) {
                if(data[index].isBoss){
                    this.articleNo.push(data[index].id)
                }
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