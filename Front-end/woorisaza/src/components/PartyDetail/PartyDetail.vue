<template>
    <div>
        <span class="title">
             상세 정보
        </span>
        <div class="p-5">
            <carousel 
                :perPage="1" 
                :navigation-enabled="true"
                :navigation-next-label="nextLabel"
                :navigation-prev-label="prevLabel"
                loop
                :paginationPadding="5"
                style="text-align:center"
                @page-change="handleSlideClick"
                >
                <slide v-for="(item, index) in partyIds" :key="index" >
                    파티원<br>
                    <MyProfile :userId="item" style="color:black;"></MyProfile>
                </slide>
            </carousel>
        </div>
        <!-- {{id}}<br> -->
        <b-container fluid="sm">
            <b-row>
                <b-col class="list">글제목</b-col>
                <b-col class="ans"><a href="">물 24개 나누실 분</a></b-col>
            </b-row>
            <b-row>
                <b-col class="list">상품</b-col>
                <b-col class="ans">삼다수</b-col>
            </b-row>
            <b-row>
                <b-col class="list">님이 선택한 인원 수</b-col>
                <b-col class="ans">2</b-col>
            </b-row>
            <b-row>
                <b-col class="list">님이 선택한 수량</b-col>
                <b-col class="ans">12</b-col>
            </b-row>
            <b-row>
                <b-col class="list">진행기간</b-col>
                <b-col class="ans">2022.02.01</b-col>
            </b-row>
            <b-row>
                <b-col class="list">결제금액</b-col>
                <b-col class="ans">5000원</b-col>
            </b-row>
        </b-container>
        
    </div>
</template>

<script>
import MyProfile from "@/views/MySaza/MyProfile.vue";
export default {
    name: 'Partydetail',
    props : {
        partyIds : Array,
    },
    components : {
        MyProfile,
    },
    data() {
        return {
            nextLabel : "<img src='https://cdn-icons-png.flaticon.com/512/271/271228.png' width=30px>",
            prevLabel : "<img src='https://cdn-icons-png.flaticon.com/512/271/271220.png' width=30px>",
            
            index : 0,
            id : this.partyIds[this.index],
        };
    },

    mounted() {
        this.id = this.partyIds[this.index];
    },

    methods: {
        handleSlideClick (index) {
            this.index=index;
            this.id=this.partyIds[this.index]
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}

.list{
    margin-top: 13px;
    font-weight: 600;
}
.ans{
    margin-top: 13px;
    text-align: right;
}
</style>