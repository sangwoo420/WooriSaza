<template>
    <div>
        <!-- 나의 1:1 문의 -->
        <b-container>
          <div style="text-align:center" >
            <b-button @click="toMyQuestion" variant="warning" class="ml-3" style="width:15%;display:inline;font-size:0.8em">문의하기</b-button>
          </div>

          <div class="box mt-3 p-3" style="cursor:pointer" @click="toMyAnswer" >
            <b-button disabled variant="warning" class="ml-3" style="width:20%;display:inline">답변 진행중</b-button>
              <b-row>
                  <b-col cols="8">
                      <div class="mt-2">
                          <div style="font-size:1.1em;font-weight:bold;margin-left:1em" >흥 문희는 포도가 먹고싶어!</div>
                      </div>
                  </b-col>
                  <b-col cols="4">
                      <div style="text-align:right" class="mt-2">
                          <div style="font-size:1.1em;font-weight:bold;">2022.01.12</div>
                      </div>
                  </b-col>
              </b-row>
          </div>

          <div class="box mt-3 p-3" style="cursor:pointer" @click="toMyAnswer" >
            <b-button disabled variant="primary" class="ml-3" style="width:20%;display:inline">답변 완료</b-button>
              <b-row>
                  <b-col cols="8">
                      <div class="mt-2">
                          <div style="font-size:1.1em;font-weight:bold;margin-left:1em" >파티장 관련 문의입니다.</div>
                      </div>
                  </b-col>
                  <b-col cols="4">
                      <div style="text-align:right" class="mt-2">
                          <div style="font-size:1.1em;font-weight:bold;">2022.01.10</div>
                      </div>
                  </b-col>
              </b-row>
          </div>
        </b-container>
        
    </div>
</template>

<script>

export default {
    name: 'Qna',
    components : {

    },
    props:["QnaNo"],
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        toMyQuestion(){
           this.$router.push("/mypage/question").catch(()=>{});;
       },
       toMyAnswer(){
           this.$router.push("/mypage/answer").catch(()=>{});;
       }
    },
};
</script>

<style scoped>
.btn-warning{
    width : 10em;
    background-color: #F1A501 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
}
.btn-secondary{
    width : 10em;
    background-color: #C4C4C4 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #C4C4C4;
    border-radius: 2em;
}
.btn-primary{
    width : 10em;
    background-color: #55A5EE ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #55A5EE;
    border-radius: 2em;
}
.box{
    border-radius:1em;
    background-color: #F6F9FB;
    box-shadow: 0.1em 0.1em gray;
}
</style>