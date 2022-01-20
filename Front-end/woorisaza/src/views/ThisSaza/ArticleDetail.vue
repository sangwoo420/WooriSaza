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
        <div>

        </div>
        <!-- 댓글 -->
    </div>
</template>

<script>
// import axios from "axios"
import ogs from "open-graph-scraper"
// import dns from "dns"

export default {
    name: 'Articledetail',
    data() {
        return {
            articleNo : this.$route.params.articleno,
            slide: 0,
            sliding: null,
            imgURL : [
                "https://thumbnail9.coupangcdn.com/thumbnails/remote/230x230ex/image/product/image/vendoritem/2019/03/04/3213757282/456ca43b-ffe6-46ef-b793-d787986a9e52.jpg",
                "https://thumbnail8.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/873394256992-7e874da2-d555-4f7d-b5ff-c098b3775d40.jpg",
            ],
            url : "https://www.coupang.com/vp/products/293308540?itemId=926269831&vendorItemId=5302019285&q=%EB%AC%BC&itemsCount=36&searchId=8a8c3602bd194ab4ad3e221c97f59cba&rank=1&isAddedCart=",
        options : { url: this.url },
        };
    },
    created() {
ogs(this.options, (error, results, response) => {
  console.log('error:', error); // This returns true or false. True if there was an error. The error itself is inside the results object.
  console.log('results:', results); // This contains all of the Open Graph results
  console.log('response:', response); // This contains the HTML of page
});
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

<style>
.img-fluid{
    width: 1024px;
    height: 240px;
    object-fit: contain;
}
.img-fluid.d-block{
    width: 1024px;
    height: 240px;
    object-fit: contain;
}
.img-fluid.d-block.w-100{
    width: 1024px;
    height: 240px;
    object-fit: contain;
}

</style>