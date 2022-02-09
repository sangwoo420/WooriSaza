<template>
    <div>
        <!-- 내가 참가한 파티 목록 -->
            <div v-for="(item, index) in zzimList" :key="index">
                <Zzim :zzim="item"></Zzim>
            </div>
    </div>
</template>

<script>
import Zzim from "@/components/Mypage/Zzim/Zzim.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Zzims',
    components:{
        Zzim,
    },
    data() {
        return {
            id : this.$cookie.get("id"),
            zzimList :null,
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/zzim?profileId="+this.id,
        }).then(({data})=>{
            this.zzimList = data.zzimList;
        })
    },
    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>