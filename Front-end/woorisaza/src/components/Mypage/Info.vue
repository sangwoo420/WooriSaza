<template>
    <div>
        <b-container>
            <div>
                닉네임
                <b-form-input v-model="nickname" placeholder="닉네임을 입력하세요."></b-form-input>
            </div>
            <div class="mt-4">
                주소<br>
                <b-form-input v-model="postcode" placeholder="우편번호" style="width:70%;display:inline" disabled></b-form-input>
                <b-button variant="warning" @click="execDaumPostcode" class="ml-3" style="width:20%;display:inline">우편번호 찾기</b-button>
                <b-form-input v-model="address" placeholder="주소" class="mt-1" disabled></b-form-input>
            </div>

            <div class="mt-4">
                프로필사진
                <div style="text-align:center">
                    <div class="photo" style="display:inline-block"> 
                        사진 수정하기
                    </div>
                </div>
            </div>
            <div class="mt-4" style="text-align:center">
                <!-- 탈퇴 수정 버튼 -->
                <b-button variant="secondary" class="mr-3">회원 탈퇴</b-button>
                <b-button variant="warning" class="ml-3">정보 수정</b-button>
            </div>
        </b-container>
        
    </div>
</template>

<script>
export default {
    name: 'Info',

    data() {
        return {
            nickname : null,
            postcode:null,
            address : null,
        };
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
                    this.address = data.roadAddress;
                } else {
                    // 사용자가 지번 주소를 선택했을 경우(J)
                    this.address = data.jibunAddress;
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
.photo{
    background-color: gray;
    width:180px;
    height: 180px;
}
</style>