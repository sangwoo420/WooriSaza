<template>
    <div class="chat p-2" style="overflow-y:scroll">
            <!-- 채팅창 헤더 -->
            <div>
                <img src="@/assets/saza.png" style="width:3vw">
                사자 채팅
                <b-icon-x-circle @click="offChat" style="cursor:pointer"></b-icon-x-circle>
            </div>
            <!-- 검색창 -->
            <div class="mt-2">
                <b-form-input v-model="party" placeholder="검색"></b-form-input>
            </div>
            <hr>
            <!-- 파티 채팅 리스트 -->
            <div >
                <div v-for="(item, index) in chatList" :key="index" >
                    <Chatpreview :partyNo=item></Chatpreview>
                </div>
            </div>
    </div>
</template>

<script>
import Chatpreview from "@/components/ThisSaza/Chat/ChatPreview.vue"
export default {
    name: 'Chatlist',
    components :{
        Chatpreview,
    },
    data() {
        return {
            party : "",
            chatList : [512,156,4185,1651,1244,1237,845,1034,15762,1265],
        };
    },

    mounted() {
        
    },

    methods: {
        offChat(){
            this.$emit('chatShowFromChild',true)
        }
    },
};
</script>

<style scoped>
.chat{
        background-color:white; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        border-radius: 0.5em;
        width:300px;
        height:500px;
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
    }
</style>