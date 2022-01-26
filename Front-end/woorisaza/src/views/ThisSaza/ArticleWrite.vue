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
                        <b-form-input id="input-small" size="sm" placeholder="제목을 입력하세요."></b-form-input>
                    </b-col>
                    <b-col cols="4">
                        카테고리
                        <b-form-select v-model="selectedCategory" :options="category" size="sm"></b-form-select>
                    </b-col>
                </b-row>
                <b-row class="mt-3">
                    <b-col cols="8">
                        상품명
                        <b-form-input id="input-small" size="sm" placeholder="상품명을 입력하세요."></b-form-input>
                    </b-col>
                    <b-col cols="4">
                        총 인원
                        <b-form-input id="input-small" size="sm" placeholder="인원수를 입력하세요." type="number"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mt-3">
                    <b-col cols="8">
                        구매링크
                        <b-form-input id="input-small" size="sm" placeholder="구매링크를 입력하세요."></b-form-input>
                    </b-col>
                    <b-col cols="4">
                        총 금액
                        <b-form-input id="input-small" size="sm" placeholder="총 금액을 입력하세요." type="number"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mt-3">
                    <b-col cols="8">
                        글 내용
                        <b-form-textarea placeholder="내용을 입력하세요." size="sm" rows="8"></b-form-textarea>                    </b-col>
                    <b-col cols="4">
                        <div>
                            위약금
                            <b-form-select v-model="selectedPenalty" size="sm" :options="penalty"></b-form-select>
                        </div>
                        <div class="mt-2">
                            파티 모집 마감일
                            <input type="date" style="font-size : 0.8em">
                        </div>
                    </b-col>
                </b-row>
            </b-container>
        </div>
        <div class="mt-5" style="text-align:center">
            <b-button>&nbsp;&nbsp;&nbsp;작성취소&nbsp;&nbsp;&nbsp;</b-button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <b-button variant="warning">&nbsp;&nbsp;&nbsp;작성완료&nbsp;&nbsp;&nbsp;</b-button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Articlewrite',

    data() {
        return {
            selectedCategory : null,
            category : [
                {value:null, text:"카테고리를 선택하세요."},
                {value:"food", text:"식품"},
                {value:"lifethings", text:"생활용품"},
                {value:"fashion", text:"패션 잡화"},
                {value:"etc", text:"기타"},
            ],
            selectedPenalty : null,
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

        };
    },

    mounted() {
        
    },

    methods: {
        
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