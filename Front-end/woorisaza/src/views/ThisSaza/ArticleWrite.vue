<template>
    <div>
        <span class="title">
            파티 생성하기
        </span>
        <div class="mt-4 form">
            <b-container>
                <b-row>
                    <b-col cols="8">
                        글제목
                        <b-form-input id="input-small" size="sm" placeholder="제목을 입력하세요." :state="titleState" v-model="articleAndParty.title"></b-form-input>
                    </b-col>
                    <b-col cols="4">
                        카테고리
                        <b-form-select :options="category" size="sm" v-model="articleAndParty.category" :state="categoryState"></b-form-select>
                    </b-col>
                </b-row>
                <b-row class="mt-3">
                    <b-col cols="8">
                        상품명
                        <b-form-input id="input-small" size="sm" placeholder="상품명을 입력하세요." v-model="articleAndParty.product" :state="productState"></b-form-input>
                    </b-col>
                    <b-col cols="4">
                        총 인원
                        <b-form-input id="input-small" size="sm" placeholder="인원수를 입력하세요." type="number" v-model="articleAndParty.totalRecruitMember" min=2 :state="totalRecruitMemberState"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mt-3">
                    <b-col cols="8">
                        구매링크
                        <b-form-input id="input-small" size="sm" placeholder="구매링크를 입력하세요." :state="linkState" aria-describedby="linkFalse linkTrue" v-model="articleAndParty.link" @change="checkLink"></b-form-input>
                        <b-form-invalid-feedback id="linkFalse">안전하지 않은 링크입니다.</b-form-invalid-feedback>
                        <b-form-valid-feedback id="linkTrue">안전한 링크입니다.</b-form-valid-feedback>
                    </b-col>
                    <b-col cols="4">
                        총 금액
                        <b-form-input id="input-small" size="sm" placeholder="총 금액을 입력하세요." type="number" v-model="articleAndParty.totalPrice" min=0 :state="totalPriceState"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mt-3">
                    <b-col cols="8">
                        글 내용
                        <b-form-textarea placeholder="내용을 입력하세요." size="sm" rows="12" v-model="articleAndParty.content" :state="contentState"></b-form-textarea>               
                    </b-col>
                    <b-col cols="4">
                        <div>
                            총 물건 개수
                            <b-form-input id="input-small" size="sm" placeholder="총 물건 개수를 입력하세요" type="number" v-model="articleAndParty.totalProductCount" min=1 :state="totalProductCountState"></b-form-input>
                        </div>
                        <div class="mt-2">
                            내 선택 수량(인원 수)
                            <b-form-input id="input-small" size="sm" placeholder="구매 할 수량을 입력하세요" type="number" v-model="articleAndParty.amount" min=1 :state="amountState"></b-form-input>
                        </div>
                        <div class="mt-2">
                            위약금
                            <b-form-select size="sm" :options="penalty" v-model="articleAndParty.penalty" :state="penaltyState"></b-form-select>
                        </div>
                        <div class="mt-2">
                            파티 모집 마감일
                            <input type="date" style="font-size : 0.8em" v-model="articleAndParty.deadline" :state="deadlineState">
                        </div>
                    </b-col>
                </b-row>
            </b-container>
            <div class="mt-5" style="text-align:center">
                <b-button>&nbsp;&nbsp;&nbsp;작성취소&nbsp;&nbsp;&nbsp;</b-button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <b-button variant="warning"  v-if="linkState" @click="articleRegister">&nbsp;&nbsp;&nbsp;작성완료&nbsp;&nbsp;&nbsp;</b-button>
                <b-button variant="warning"  v-if="!linkState" @click="articleRegister" disabled>&nbsp;&nbsp;&nbsp;작성완료&nbsp;&nbsp;&nbsp;</b-button>
            </div>
        </div>
        
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: 'Articlewrite',

    data() {
        return {
            articleAndParty : {
                amount : null,
                category : null,
                content : null,
                deadline : null,
                link : null,
                penalty : null,
                pic : [],
                product : null,
                profileId : this.$cookie.get("id"),
                title : null,
                totalPrice : null,
                totalProductCount : null,
                totalRecruitMember : null
            },
            category : [
                {value:null, text:"카테고리를 선택하세요."},
                {value:"FOOD", text:"식품"},
                {value:"LIFETHINGS", text:"생활용품"},
                {value:"FASHION", text:"패션 잡화"},
                {value:"ELECTRICITY", text:"전자 제품"},
                {value:"DELIVERY", text:"배달"},
                {value:"ETC", text:"기타"},
            ],
            penalty : [
                {value:null, text:"위약금을 선택하세요."},
                {value:0, text:"0%"},
                {value:10, text:"10%"},
                {value:20, text:"20%"},
                {value:30, text:"30%"},
                {value:40, text:"40%"},
                {value:50, text:"50%"},
                {value:60, text:"60%"},
                {value:70, text:"70%"},
                {value:80, text:"80%"},
                {value:90, text:"90%"},
                {value:100, text:"100%"},
            ],

            amountState : null,
            categoryState : null,
            contentState : null,
            deadlineState : null,
            linkState : null,
            penaltyState : null,
            productState : null,
            titleState : null,
            totalPriceState : null,
            totalProductCountState : null,
            totalRecruitMemberState : null
        };
    },

    mounted() {
        
    },

    methods: {
        checkLink(link){
            if(link==""){
                this.linkState=null;
                return;
            }
            const that = this;
            const sublink = link.split("com");
            const options = {
                method: 'GET',
                url: 'https://cors-anywhere.herokuapp.com/https://www.virustotal.com/vtapi/v2/url/report',
                params: {
                    apikey: '68614fc23b4200870247adf446056257420f8c45e525776827cf027c589e0cee',
                    resource: sublink[0]+"com",
                    allinfo: 'false',
                    scan: '0'
                },
                headers: {Accept: 'application/json'}
            };

            axios.request(options).then(function (response) {
                // console.log(response.data)
                if(response.data.response_code!=1){
                    //위험한 링크
                    that.linkState = false;
                    return;
                }
                else{
                    for (const scan in response.data.scans) {
                        if(scan.detected){
                            //위험한 링크
                            that.linkState = false;
                            return;
                        }
                    }
                }
                //여기까지 왔다는 것은 안전한 링크
                that.linkState = true;
                //링크로부터 섬네일 사진 가져오기
                axios({
                    method : "get",
                    url : "https://cors-anywhere.herokuapp.com/https://api.urlmeta.org/?url="+link,
                    headers:{
                        "Authorization" : "Basic dG9teTk3MjlAbmF2ZXIuY29tOjhUYkdka2MxVnE3bnBYTzcyMkpC",
                    },
                }).then(({data})=>{
                    console.log(data)
                    that.articleAndParty.pic.push(data.meta.image)
                })
            })
        },

        articleRegister(){
            console.log("글 쓰즈아~")
            console.log(this.articleAndParty)
            axios({
                method : "post",
                url : "http://localhost:8080/article",
                data : {
                    "amount": this.articleAndParty.amount,
                    "category": this.articleAndParty.category,
                    "content": this.articleAndParty.content,
                    "deadline": this.articleAndParty.deadline+"-23:59:59",
                    "link": this.articleAndParty.link,
                    "penalty": this.articleAndParty.penalty,
                    "pic": this.articleAndParty.pic,
                    "product": this.articleAndParty.product,
                    "profileId": this.articleAndParty.profileId,
                    "title": this.articleAndParty.title,
                    "totalPrice": this.articleAndParty.totalPrice,
                    "totalProductCount": this.articleAndParty.totalProductCount,
                    "totalRecruitMember": this.articleAndParty.totalRecruitMember
                },
            }).then(({data})=>{
                console.log(data)
            }).catch(({err})=>{
                console.log(err)
            })
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}

.form{
    font-size: 0.8em;
}
.form-control{
    font-size: 0.8em;
}
.custom-select{
    font-size: 0.8em;
}

.btn-warning{
    background-color: #F1A501 ;
    font-size : 0.7em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius : 2em;
    color : white;
}
.btn-secondary{
    background-color: #C4C4C4     ;
    font-size : 0.7em;
    padding: 0.5em;
    border-color: #C4C4C4 ;
    border-radius : 2em;
}
</style>