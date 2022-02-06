<template>
  <div style="display:inline-block" v-if="profile != null">
    <div class="card mb-3 flex-row" style="font-size:1.1em;align-items:center;background-color:#FFF9EC; width:270px;" >
      <img v-if="profile.pic==null" src="@/assets/IDimage.png" style="width:80px;height:80px;background-color:white" alt="Card image" class="card-img-left p-1 ml-3">
      
      <div class="card-body" style="text-align:left">
        <div style="font-size:0.8em">
          등급 : A
        </div>
        <div>
          {{profile.nickname}}
        </div>
        <div style="font-size:0.8em">
          {{profile.address}}
        </div>
        <div style="text-align:right;font-size:0.8em">
          {{profile.joinDate[0]}}.{{profile.joinDate[1]}}.{{profile.joinDate[2]}} 가입
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import axios from "axios"
export default {
  name : "MyProfile",
  props : {
        userId : String,
    },
    data(){
      return {
        id : this.$route.params.id!=null ? this.$route.params.id : this.$cookie.get("id"),
        profile : null,
      }
    },
  
  created() {
    axios({
      method : "get",
      url : "http://localhost:8080/user/"+this.id,
    }).then(({data})=>{
      // console.log(data)
      this.profile = data.profile;
    })
  },
  methods: {
    },
};
</script>

<style >

</style>