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
                                            
                                        </b-col>
                                        <b-col cols="8">
                                            <div class="my-3">
                                                <p>문의 유형</p>
                                                <div class="box mt-3 p-3" style="background-color:#F8FAFC">
                                                물건을 못받았어요
                                                </div>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 제목</p>
                                                <div class="box mt-3 p-3" style="background-color:#F8FAFC">
                                                파티장 관련 문의입니다
                                                </div>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 내용</p>
                                                <div class="box mt-3 p-3" style="background-color:#F8FAFC;height:5rem">
                                                파티장이 연락이 안돼요
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <p>답변 내용</p>
                                                <div class="box mt-3 p-3" style="background-color:#FFF8F8;height:5rem">
                                                저희가 사람을 보냈으니 안심하십시오
                                                </div>
                                            </div>
                                        </b-col>    
                                        <b-col cols="2">
                                            <div>
                                                
                                            </div>
                                        </b-col>  
                                      </b-row>  

                                    </div>
                                    <div class="mt-3" style="text-align:center">
                                        <!-- 취소 완료 버튼 -->
                                        <b-button variant="secondary" class="mr-5" @click="updateQuestion">문의 내용 수정</b-button>
                                        <b-button variant="danger" class="ml-5" @click="deleteQuestion">문의 삭제</b-button>
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
export default {
  name: 'MyAnswer',
}
</script>

<style scoped>
    .box{
        font-size: 0.9em;
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
    .btn-danger{
    width : 12em;
    background-color: #FF0000 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
    }

    .btn-secondary{
    width : 12em;
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