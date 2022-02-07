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
                                    <h2 style="text-align:center">회원가입</h2>
                                    <div class="mt-4">
                                        <b-container>
                                            <div>
                                                닉네임
                                                <b-form-input v-model="userProfile.nickname" placeholder="닉네임을 입력하세요."></b-form-input>
                                            </div>
                                            <div class="mt-4">
                                                주소<br>
                                                <b-form-input v-model="postcode" placeholder="우편번호" style="width:70%;display:inline" disabled></b-form-input>
                                                <b-button variant="warning" @click="execDaumPostcode" class="ml-3" style="width:20%;display:inline">우편번호 찾기</b-button>
                                                <b-form-input v-model="userProfile.address" placeholder="주소" class="mt-1" disabled></b-form-input>
                                            </div>

                                            <div class="mt-4">
                                                프로필사진
                                                <div style="text-align:center">
                                                    <div class="photo" style="display:inline-block"> 
                                                        <div v-if="image==null">
                                                            <br><br>
                                                            <img  src="@/assets/IDimage.png" alt="">
                                                        </div>
                                                        <div>
                                                            <br>
                                                            <img v-if="image!=null" :src="preImage" alt="" style="width : 140px; height:140px">
                                                        </div>
                                                    </div>
                                                    <div class="mt-1">
                                                        <b-form-file v-model="image" plain @change="registerImage" accept=".jpg, .png"></b-form-file>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4" style="text-align:center">
                                                <!-- 탈퇴 수정 버튼 -->
                                                <b-button variant="warning" class="ml-3" @click="register">회원 가입</b-button>
                                            </div>
                                        </b-container>
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
import axios from 'axios';
export default {
    name: 'Register',

    data() {
        return {
            userProfile : {
                address : null,
                cnt : 0,
                id : null,
                joindDate : null,
                nickname : null,
                pic : null,
                score : null, 
            },
            accesstoken : this.$cookie.get("Raccesstoken"),
            postcode:null,
            image : null,
            preImage : null,
        };
    },

    created(){
        axios({
            method : "get",
            url : "https://cors-anywhere.herokuapp.com/https://kapi.kakao.com/v1/user/access_token_info",
            headers : {
                "Authorization" : "Bearer "+this.accesstoken,
                "Content-type" : "application/x-www-form-urlencoded;charset=utf-8",
            },
        }).then(({data})=>{
            // console.log(data)
            this.userProfile.id = data.id;
        })
    },
    mounted() {

    },

    methods: {
        execDaumPostcode() {
            new window.daum.Postcode({
                oncomplete: (data) => {
                if (this.extraAddress !== "") {
                    this.extraAddress = "";
                }
                if (data.userSelectedType === "R") {
                    // 사용자가 도로명 주소를 선택했을 경우
                    this.userProfile.address = data.roadAddress;
                } else {
                    // 사용자가 지번 주소를 선택했을 경우(J)
                    this.userProfile.address = data.jibunAddress;
                }
        
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === "R") {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    this.extraAddress += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== "" && data.apartment === "Y") {
                    this.extraAddress +=
                        this.extraAddress !== ""
                        ? `, ${data.buildingName}`
                        : data.buildingName;
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (this.extraAddress !== "") {
                    this.extraAddress = `(${this.extraAddress})`;
                    }
                } else {
                    this.extraAddress = "";
                }
                // 우편번호를 입력한다.
                this.postcode = data.zonecode;
                },
            }).open();
        },
        register(){
            console.log(this.image)
            axios({
                method : "post",
                url : "http://localhost:8080/user/register",
                data : this.userProfile,
            }).then(({data})=>{
                // console.log(data)
                data;
                this.$router.push("/");
            })
        },

        registerImage(event){
            var input = event.target;
            if(input.files && input.files[0]){
                var reader = new FileReader();
                reader.onload = (e) =>{
                    this.preImage = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }

            console.log(event);
            console.log(this.image);
            const formData = new FormData();
            formData.append('image', this.image);
            console.log(formData);
        },
    },
};
</script>

<style  scoped>
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