<template>
    <div>
        <!-- {{articleNo}} -->
        <!-- 글 내용 -->
        <div>
            <!-- 글 제목 -->
            <div style="font-size:1em;font-weight:bold">물 24개 나누실 분</div>
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
                    <b-carousel-slide v-for="(item, index) in imgURL" :key="index" :img-src="item"></b-carousel-slide>
                </b-carousel>
            </div>
        </div>
        <!-- 상품명 -->
        <div class="mt-4" style="font-size:1.3em">
            탐사수
        </div>
        <!-- 상품 링크 + 기간+모집인원+1인당개수 -->
        <div class="mt-2">
            <b-row>
                <b-col >
                    <b-link target='_blank' :href="url" >링크 바로가기</b-link>
                </b-col>
                <b-col>
                    <div style="background-color:#D9E5FF;border-radius:0.3em;text-align:right;">
                        <div class="p-2">
                            기간 : 22.01.13~22.02.01
                            모집인원 : <b style="font-size:1.5em">1</b> /4<br>
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
                <b-col style="text-align:right">10000원</b-col>  
            </b-row>
            <b-row style="font-size:1.1em; font-weight:bold">
                <b-col>사자들과 함께 사기</b-col>
                <b-col style="text-align:right">2500원</b-col>  
            </b-row>
            
        </div>
        <!-- 찜버튼 + 참여하기 버튼 -->
        <div style="text-align:right">
            <img src="@/assets/zzimOn.png" style="width:1.6em;cursor:pointer">&nbsp;
            <b-button variant="secondary" pill>참여하기</b-button>
        </div>
        <!-- 글내용 -->
        <div class="mt-3">
            <b-form-textarea
                id="textarea-auto-height"
                placeholder="내용이 없습니다."
                rows="8"
                max-rows="100"
                value =" 음~맛잇다"
                disabled
                no-resize
            ></b-form-textarea>
        </div>

        <!-- 목록버튼 -->
        <div style="text-align:right" class="mt-2">
            <b-button variant="secondary" pill>목록</b-button>
        </div>
        <!-- 댓글 -->
    </div>
</template>


<script>
// import axios from "axios"
// import ogs from "open-graph-scraper"
// import {ogs} from "@/ogs.js"
import axios from "axios"
export default {
    name: 'MyArticledetail',
    data() {
        return {
            articleNo : this.$route.params.articleno,
            slide: 0,
            sliding: null,
            imgURL : [],
            url : "https://smartstore.naver.com/designersbay/products/6162926005?NaPm=ct%3Dkymr45t4%7Cci%3D0AW0001L9IzvcNwlAfkE%7Ctr%3Dpla%7Chk%3D424bdc46c4996bea7cf305af4b37198d256bd738",

        };
    },
    created() {
        // 이 부분은 글쓰기 부분에서 사진 필드에 바로 썸네일 사진 링크 넣어주기
        axios({
            method : "get",
            url : "https://cors-anywhere.herokuapp.com/https://api.urlmeta.org/?url="+this.url,
            headers:{
                "Authorization" : "Basic dG9teTk3MjlAbmF2ZXIuY29tOjhUYkdka2MxVnE3bnBYTzcyMkpC",
            },
        }).then(({data})=>{
            this.imgURL.push(data.meta.image)
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
    },
};
</script>

<style scoped>
.btn-secondary{
    width : 8em;
    background-color: #F1A501 ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
}
</style>