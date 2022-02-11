<template>
    <div v-if="myInfo!=null">
        <span class="title">
             후기 남기기
        </span>
        <div class="mt-3 pr-5 pl-5">
            <div>
                <carousel 
                    :perPage="1" 
                    :navigation-enabled="true"
                    loop
                    :paginationPadding="5"
                    @page-change = "member"
                    style="text-align:center"
                    >
                    <slide v-for="(item, index) in party" :key="index">
                        <div>
                            <div v-if="item.isBoss==true">파티장</div>
                            <div v-if="item.isBoss==false">파티원</div>
                            <MyProfile :userId="item.profileId" style="color:black;"></MyProfile>
                        </div>
                    </slide>
                </carousel>
            </div>
            <!-- {{id}}<br> -->
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
import MyProfile from "@/views/MySaza/MyProfile.vue";
export default {
    name: 'Review',
    components : {
        MyProfile,
    },
    data() {
        return {
            myId : this.$cookie.get("id"),
            myInfo : null,
            partyId : this.$route.params.partyId,
            party : [],
            article : null,
            memberInfo : null,
        };
    },

    created() {
        this.getInfo();
    },
    mounted() {
        
    },

    methods: {
        getInfo(){
            axios_contact({
            method : "get",
            url : "/party?partyId="+this.partyId,
        }).then(({data})=>{
            for (let index = 0; index < data.length; index++) {
                if(data[index].profileId == this.myId){
                    this.myInfo = data[index]
                }
                else{
                    this.party.push(data[index])
                }
                this.memberInfo = this.party[0];
            }
            axios_contact({
                method : "get",
                url : "/article/"+this.memberInfo.articleId,
            }).then(({data})=>{
                this.article = data.article;
            })
        })
        },

        member(index){
            this.memberInfo = this.party[index]
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}
</style>