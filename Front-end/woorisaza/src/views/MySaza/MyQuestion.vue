<template>
    <div class="mt-3" style="background-color : #F6FBF6; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container >
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                    <h2>1:1 문의</h2>
                                    <div class="my-5">
                                      <b-row>       
                                        <b-col cols="2">
                                            <div>
                                                
                                            </div>
                                        </b-col>
                                        <b-col cols="8">
                                            <div class="my-3">
                                                <p>문의 유형</p>
                                                <b-form-select v-model="category" :options="categories" size="sm" style="width:60%" @change="searchByOption"></b-form-select>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 제목</p>
                                                <b-form-textarea
                                                  id="title"
                                                  size="sm"
                                                  placeholder="제목을 입력하세요"
                                                ></b-form-textarea>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 내용</p>
                                                <b-form-textarea
                                                  id="context"
                                                  placeholder="내용을 입력하세요"
                                                  rows="8"
                                                ></b-form-textarea>
                                            </div>
                                            
                                        </b-col>    
                                        <b-col cols="2">
                                            <div>
                                                
                                            </div>
                                        </b-col>  
                                      </b-row>  
                                      <div class="mt-4">
                                        <p>사진 올리기</p>
                                        <div style="text-align:center">
                                            <div class="photo" style="display:inline-block"> 
                                                <div v-if="image==null">
                                                    <br><br>
                                                    <img  src="@/assets/IDimage.png" alt="">
                                                </div>
                                                <div v-if="image!=null">
                                                    <br>
                                                    <img :src="preImage" alt="" style="width : 140px; height:140px">
                                                </div>
                                            </div>
                                            <div class="mt-3">
                                                <b-form-file v-model="image" plain @change="registerImage" accept=".jpg, .png"></b-form-file>
                                            </div>
                                        </div>
                                      </div>

                                      
                                    </div>
                                    <div class="mt-5" style="text-align:center">
                                        <!-- 취소 완료 버튼 -->
                                        <b-button variant="secondary" class="mr-3" @click="cancelQuestion">작성 취소</b-button>
                                        <b-button variant="warning" class="ml-3" @click="createQuestion">작성 완료</b-button>
                                    </div>
                                </div>
                            </div>
                        </b-col>
                        <b-col>
                          
                        </b-col>
                        
                    </b-row>
                    
                </b-container>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
            </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"

export default {
  name: 'MyQuestion',
  
  data() {
        return {
            preImage : null,
        };
    },

  created() {
        axios_contact({
            method : "get",
            url : "/user/"+this.id,
        }).then(({data})=>{
            console.log(data)
            this.preImage = this.userProfile.pic;
        })
    },
  methods: {
    registerImage(event){
            var input = event.target;
            if(input.files && input.files[0]){
                var reader = new FileReader();
                reader.onload = (e) =>{
                    this.preImage = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
            this.imageChange = true;
        },
  }
}
</script>

<style scoped>
    .box{
        background-color:white; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        border-radius: 0.5em;
        width:100%;
        height:100%;
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
    }
    .box::-webkit-scrollbar {
        display: none; /* Chrome, Safari, Opera*/
    }
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
    .photo{
    background-color: white;
    width:180px;
    height: 180px;
    border: 1px solid black;
}


</style>