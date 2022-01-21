<template>
    <div class="chat">
        <div class="p-2">
            <!-- 채팅창 헤더 -->
            <div>
                <img src="@/assets/saza.png" style="width:3vw">
                사자 채팅
                <b-icon-x-circle @click="offChat" style="cursor:pointer"></b-icon-x-circle>
            </div>
            <hr>
            <!-- 검색창 -->
            <div>
                <b-form-input v-model="party" placeholder="검색"></b-form-input>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Chatlist',

    data() {
        return {
            party : "",
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
        width:100%;
        height:100%;
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
    }
</style>