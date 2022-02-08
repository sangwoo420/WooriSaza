<template>
    <div>
        <div>
            <b-row>
                <b-col cols="8">
                    <div>
                        <!-- <div :class="{statebox:true}">{{article.tag}}</div> -->
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">{{party.title}}</div>
                        <div style="font-size:0.9em">기간: ~{{party.deadline[0]}}.{{party.deadline[1]}}/{{party.deadline[2]}}</div>
                        <div style="font-size:0.9em">모집인원: {{party.currentRecruitMember}}/{{party.totalRecruitMember}}</div>
                        
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">{{party.totalPrice}}원</div>
                        <div style="font-size:1.3em;font-weight:bold;">{{party.myPrice}}원</div>
                    </div>
                </b-col>
            </b-row>
            <hr>
        </div>
    </div>
</template>

<script>
// import {axios_contact} from "@/common.js"

export default {
    name: 'Article',
    props:{
        party:Object,
    },
    data() {
        return {
        };
    },

    created() {
    },
    mounted() {
        
    },

    methods: {
        moveToDetail(){
            // console.log("상세보기 페이지로 이동")
            // console.log(this.party)
            this.$router.push("/partydetail/"+this.party.id).catch(()=>{});
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