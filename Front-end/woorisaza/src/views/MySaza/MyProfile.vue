<template>
  <div style="display:inline-block">
    <div class="card mb-3 flex-row" style="font-size:1.1em;align-items:center;background-color:#FFF9EC; width:270px;" >
      <img src="@/assets/IDimage.png" style="width:80px;height:80px;background-color:white" alt="Card image" class="card-img-left p-1 ml-3">
      <div class="card-body" style="text-align:left">
        <div style="font-size:0.8em">
          등급 : A
        </div>
        <div>
          박상우아님
        </div>
        <div style="font-size:0.8em">
          광주 북구 용봉동
        </div>
        <div style="text-align:right;font-size:0.8em">
          2022.01.13 가입
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  name : "MyProfile",
  props : {
        userId : String,
    },
  components:{
    
  },
  methods: {
    },
};
</script>

<style >

</style>