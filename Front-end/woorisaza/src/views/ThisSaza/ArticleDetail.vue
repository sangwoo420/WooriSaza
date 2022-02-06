<template>
    <div  v-if="article != null">
        <!-- {{articleNo}} -->
        <!-- 글 내용 -->
        <div>
            <!-- 글 제목 -->
            <div style="font-size:1em;font-weight:bold">{{article.title}}</div>
            <!-- 작성자 -->
            <div style="text-align:right;font-size:0.7em">파티장 : 박상우</div>
            <!-- 상품 사진 -->
            <div>
                <b-carousel
                v-model="slide"
                :interval="4000"
                controls
                indicators
                background="#ababab"
                @sliding-start="onSlideStart"
                @sliding-end="onSlideEnd"
                >
                    <b-carousel-slide v-for="(item, index) in article.pic" :key="index" :img-src="item"></b-carousel-slide>
                </b-carousel>
            </div>
        </div>
        <!-- 상품명 -->
        <div class="mt-4" style="font-size:1.3em">
            {{article.product}}
        </div>
        <!-- 상품 링크 + 기간+모집인원+1인당개수 -->
        <div class="mt-2">
            <b-row>
                <b-col >
                    <b-link target='_blank' :href="article.link" >링크 바로가기</b-link>
                </b-col>
                <b-col>
                    <div style="background-color:#D9E5FF;border-radius:0.3em;text-align:right;">
                        <div class="p-2">
                            <!-- 기간이 안나와요 -->
                            기간 : 22.01.13~22.02.01 <br>
                            모집인원 : <b style="font-size:1.5em">{{article.currentRecruitMember}}</b> /{{article.totalRecruitMember}}<br>
                            1인당 : 6개
                        </div>
                    </div>    
                </b-col>  
            </b-row>
        </div>
        <!-- 전체 금액 + 개인 금액 -->
        <div class="mt-4">
            <b-row style="text-decoration:line-through;font-size:0.9em">
                <b-col >전체 구매 시 필요한 금액</b-col>
                <b-col style="text-align:right">{{article.totalPrice}}원</b-col>  
            </b-row>
            <b-row style="font-size:1.1em; font-weight:bold">
                <b-col>사자들과 함께 사기</b-col>
                <b-col style="text-align:right">{{article.totalPrice/article.totalRecruitMember}}원</b-col>  
            </b-row>
            
        </div>
        <!-- 찜버튼 + 참여하기 버튼 -->
        <div style="text-align:right">
            <img src="@/assets/zzimOn.png" style="width:1.6em;cursor:pointer">&nbsp;
            <b-button variant="warning" pill @click="requestParty">참여하기</b-button>
        </div>
        <!-- 글내용 -->
        <div class="mt-3">
            <b-form-textarea
                id="textarea-auto-height"
                placeholder="내용이 없습니다."
                rows="8"
                max-rows="100"
                :value ="article.content"
                disabled
                no-resize
            ></b-form-textarea>
        </div>

        <!-- 목록버튼 -->
        <div style="text-align:right" class="mt-2">
            <b-button variant="warning" pill @click="toBoard">목록</b-button>
        </div>
        <!-- 댓글 -->
        <hr>
        <div class="mt-3">
            <div class="p-2" style="border-radius: 2em;">
                <!-- <img src="@/assets/comment.png" style="display:inline;width:7%" class="ml-1 mr-1"> -->
                <b-form-textarea placeholder="댓글을 입력하세요." size="sm" class="mr-1" style="display:inline;width:90%" v-model="comment"></b-form-textarea>
                <b-button style="display:inline;width:9%;" @click="registerComment">등록</b-button>
                <hr>
            </div>
            <!-- 사람들 댓글 쓴 거 -->
            <!-- 댓글듯 for문 -->
            <div>
                <!-- 각 댓글 -->
                <div v-for="(comment, index) in commentList" :key="index">
                    <!-- {{comment}} -->
                    <div :key="commentRerender">
                        <img v-if="comment.pic==null" src="@/assets/icon.png" style="width:10%" alt="">
                        <!-- 사용자 닉네임 -->
                        <a href="" style="color:black; width:10%;display:inline">{{comment.nickname}}</a>
                        <b-button variant="danger" style="float:right">삭제</b-button>
                        <b-button variant="success" style="float:right" class="mr-1">수정</b-button>
                    </div>
                    <div style="font-size:12px">
                        {{comment.content}}
                    </div>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
// import axios from "axios"
// import ogs from "open-graph-scraper"
// import {ogs} from "@/ogs.js"
import axios from "axios"
export default {
    name: 'Articledetail',
    data() {
        return {
            articleNo : this.$route.params.articleno,
            slide: 0,
            sliding: null,
            article : null,
            comment : null,
            commentList : null,
            commentRerender : 0,
        };
    },
    created() {
        // 이 부분은 글쓰기 부분에서 사진 필드에 바로 썸네일 사진 링크 넣어주기
        axios({
            method : "get",
            url : "http://localhost:8080/article/"+this.articleNo,
        }).then(({data})=>{
            this.article = data.article;
        })

        axios({
            method : "get",
            url : "http://localhost:8080/comment?articleId="+this.articleNo,
        }).then(({data})=>{
            // console.log(data)
            this.commentList=data.commentList;
            for (let index = 0; index < this.commentList.length; index++) {
                // console.log(this.commentList[index])
                let nickname;
                let pic;
                axios({
                    method : "get",
                    url : "http://localhost:8080/user/"+this.commentList[index].profileId,
                }).then(({data})=>{
                    console.log(data)
                    nickname = data.profile.nickname;
                    pic = data.profile.pic;
                    // console.log(nickname)
                    let np = {
                        nickname : nickname,
                        pic : pic
                    };
                    this.commentList[index] = Object.assign(this.commentList[index],np);
                    this.commentRerenderForce();
                    // console.log(this.commentList[index])
                })
            }
        })
    },

    mounted() {
    },

    methods: {
        onSlideStart() {
            this.sliding = true
        },
        onSlideEnd() {
            this.sliding = false
        },
        requestParty(){
            this.$router.push("/request/"+this.articleNo).catch(()=>{});
        },
        toBoard(){
            this.$router.push("/board");
        },

        registerComment(){
            console.log(this.comment+"등록해")
            axios({
                method : "post",
                url : "http://localhost:8080/comment",
                data : {
                    "articleId": this.articleNo,
                    "content": this.comment,
                    "id": "null",
                    "profileId": this.$cookie.get("id"),
                },
            }).then(({data})=>{
                console.log(data)
                this.$router.go();
            })
        },

        commentRerenderForce(){
            this.commentRerender +=1;
        },
    },
};
</script>

<style scoped>
.btn-warning{
    width : 8em;
    background-color: #F1A501 ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
}

.btn-secondary{
    width : 8em;
    background-color: #C4C4C4     ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #C4C4C4 ;
    position: absolute;
}
.btn-success{
    width : 5em;
    background-color: #22C55E      ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #22C55E  ;
}
.btn-danger{
    width : 5em;
    background-color: #FF0000      ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #FF0000  ;
}
</style>