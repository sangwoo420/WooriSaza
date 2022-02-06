<template>
    <div>
        <!-- {{articleNo}} -->
        <div v-if="article!=null">
            <b-row>
                <b-col cols="8">
                    <div>
                        <!-- <div :class="{statebox:true}">{{article.tag}}</div> -->
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">{{article.title}}</div>
                        <div style="font-size:0.9em">기간: ~데드라인</div>
                        <div style="font-size:0.9em">모집인원: {{article.currentRecruitMember}}/{{article.totalRecruitMember}}</div>
                        
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">{{article.totalPrice}}원</div>
                        <div style="font-size:1.3em;font-weight:bold;">{{article.totalPrice/article.totalRecruitMember}}원</div>
                    </div>
                </b-col>
            </b-row>
            <hr>
        </div>
    </div>
</template>

<script>
import axios from "axios"

export default {
    name: 'Article',
    props:["articleNo"],
    data() {
        return {
            partyId : null,
            article : null,
        };
    },

    created() {
        axios({
            method : "get",
            url : "http://localhost:8080/article/"+this.articleNo,
        }).then(({data})=>{
            this.article = data.article;
        })
    },
    mounted() {
        
    },

    methods: {
        moveToDetail(){
            console.log("상세보기 페이지로 이동")
            this.$router.push("/partydetail/"+this.partyId).catch(()=>{});
        },
    },
};
</script>

<style scoped>
.statebox{
      color: white ;
      font-size: 0.7em;
      background-color: red ;
      border-radius: 0.5em ;
      padding: 0.2em ;
      text-align: center;
      width: 5em;
      margin-bottom: 0.5em;
}
</style>