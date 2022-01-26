<template>
  <div >
    <b-container>
      <b-row>
        <b-col>

        </b-col>

        <b-col cols="7">
          <div class="card mb-3 flex-row" style="font-size:1.1em;cursor:pointer;align-items:center;background-color:#FFF9EC" @click="moveToMyPage">
            <img src="@/assets/IDimage.png" style="width:100px;height:100px" alt="Card image" class="card-img-left p-3">
            <div class="card-body" style="text-align:center">
              <p class="card-text">
                등급
                <br>
                박상우아님!
                <br>
                광주 북구 용봉동
                <br>
                2022.01.13 가입
              </p>
            </div>
          </div>
        </b-col>

        <b-col>

        </b-col>
      </b-row>
    </b-container>
    
  </div>
</template>

<script>
export default {
  name : "MyProfile",
  components:{
    
  },
  methods: {
        moveToMyPage(){
            this.$router.push("/mypage").catch(()=>{});;
        },
    },
};
</script>

<style >

</style>