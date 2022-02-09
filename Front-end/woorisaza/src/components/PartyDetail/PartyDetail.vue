<template>
    <div v-if="article!=null">
        <span class="title">
             상세 정보
        </span>
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
                <b-col class="list">진행기간</b-col>
                <b-col class="ans">{{article.deadline[0]}}.{{article.deadline[1]}}.{{article.deadline[2]}}</b-col>
            </b-row>
        </b-container>

        <div class="box mt-3 p-5">
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
            <b-container fluid="sm" v-if="memberInfo != null">
                <b-row>
                    <b-col class="list">선택한 인원 수</b-col>
                    <b-col class="ans">{{memberInfo.totalamount}}</b-col>
                </b-row>
                <b-row>
                    <b-col class="list">선택한 수량</b-col>
                    <b-col class="ans">{{article.totalProductCount/article.totalRecruitMember*memberInfo.totalamount}}</b-col>
                </b-row>
                <b-row>
                    <b-col class="list">결제금액</b-col>
                    <b-col class="ans">{{memberInfo.price}}원</b-col>
                </b-row>
            </b-container>
        </div>
        <div style="text-align:center" class="mt-3">
            <!-- 파티장 + 마감 전 -->
            <div v-if="myInfo.isBoss && !myInfo.isClosed">
                <b-button variant="warning" >파티 마감하고 구매 진행하기</b-button>
                <b-button variant="danger" >파티 삭제하기</b-button>
            </div>
            <!-- 파티장 + 마감 후 -->
            <div v-if="myInfo.isBoss && myInfo.isClosed">
                <b-button variant="warning" >구매 인증 폼 등록하기</b-button>
            </div>
            <!-- 파티원 + 마감 전 -->
            <div v-if="!myInfo.isBoss && !myInfo.isClosed">
                <b-button variant="secondary" @click="partyLeave">참가 취소하기</b-button>
            </div>
            <!-- 파티원 + 마감 후 -->
            <div v-if="!myInfo.isBoss && myInfo.isClosed">
                <b-button variant="warning" >구매 인증 폼 확인하기</b-button>
                <b-button variant="danger" >물건을 못 받았어요</b-button>
                <b-button variant="success" >구매 확정하기</b-button>
            </div>
        </div>
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
            myId : this.$cookie.get("id"),
            myInfo : null,
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
            for (let index = 0; index < data.length; index++) {
                if(data[index].profileId == this.myId){
                    this.myInfo = data[index]
                }
            }
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
        partyLeave(){
            console.log("파티 나갈래")
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
    font-size : 15px;
}
.ans{
    margin-top: 13px;
    text-align: right;
    font-size : 15px;
}

.box{
    background-color: #FFF3F3;
    /* box-shadow: 0px 0px 5px 0.1px grey;  */
    border-radius: 0.5em;
}
.btn-warning{
    margin-top: 5px;
    background-color: #F1A501 ;
    width : 300px;
    font-size : 0.8em;
    padding: 0.5em;
    border-color: #F1A501;
    color : white;
    border-radius: 1em;
}
.btn-secondary{
    margin-top: 5px;
    /* background-color: #F1A501 ; */
    width : 300px;
    font-size : 0.8em;
    padding: 0.5em;
    /* border-color: #F1A501; */
    color : white;
    border-radius: 1em;
}
.btn-danger{
    margin-top: 5px;
    /* background-color: #F1A501 ; */
    width : 300px;
    font-size : 0.8em;
    padding: 0.5em;
    /* border-color: #F1A501; */
    color : white;
    border-radius: 1em;
}
.btn-success{
    margin-top: 5px;
    /* background-color: #F1A501 ; */
    width : 300px;
    font-size : 0.8em;
    padding: 0.5em;
    /* border-color: #F1A501; */
    color : white;
    border-radius: 1em;
}
</style>