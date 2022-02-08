<template>
    <div v-if="article!=null">
        <span class="title">
             상세 정보
        </span>
        <div class="p-5">
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
        <b-container fluid="sm" v-if="memberInfo != null">
            <b-row>
                <b-col class="list">글제목</b-col>
                <b-col class="ans"><a href="" @click="moveToArticle">{{article.title}}</a></b-col>
            </b-row>
            <b-row>
                <b-col class="list">상품</b-col>
                <b-col class="ans">{{article.product}}</b-col>
            </b-row>
            <b-row>
                <b-col class="list">{{memberInfo.nickname}}님이 선택한 인원 수</b-col>
                <b-col class="ans">{{memberInfo.totalamount}}</b-col>
            </b-row>
            <b-row>
                <b-col class="list">{{memberInfo.nickname}}님이 선택한 수량</b-col>
                <b-col class="ans">{{article.totalProductCount/article.totalRecruitMember*memberInfo.totalamount}}</b-col>
            </b-row>
            <b-row>
                <b-col class="list">진행기간</b-col>
                <b-col class="ans">{{memberInfo.deadline[0]}}.{{memberInfo.deadline[1]}}.{{memberInfo.deadline[2]}}</b-col>
            </b-row>
            <b-row>
                <b-col class="list">결제금액</b-col>
                <b-col class="ans">{{memberInfo.price}}원</b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
import MyProfile from "@/views/MySaza/MyProfile.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Partydetails',
    components : {
        MyProfile,
    },
    data() {
        return {
            partyId : this.$route.params.partyId,
            party : null,
            article : null,
            memberInfo : null,
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/party?partyId="+this.partyId,
        }).then(({data})=>{
            this.party = data;
            this.memberInfo = this.party[0];
            axios_contact({
                method : "get",
                url : "/article/"+this.memberInfo.articleId,
            }).then(({data})=>{
                this.article = data.article;
            })
        })
    },
    mounted() {
    },

    methods: {
        member(index){
            this.memberInfo = this.party[index]
        },
        moveToArticle(){
            this.$router.push("/board/"+this.article.id);
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}

.list{
    margin-top: 13px;
    font-weight: 600;
}
.ans{
    margin-top: 13px;
    text-align: right;
}
</style>