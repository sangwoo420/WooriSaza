<template>
    <div>
        <!-- 내가 참가한 파티 목록 -->
        <div v-for="(item, index) in zzimNo" :key="index">
            <Zzim :zzimNo="item"></Zzim>
        </div>
    </div>
</template>

<script>
import Zzim from "@/components/Mypage/Zzim/Zzim.vue";
export default {
    name: 'Memberofparty',
    components:{
        Zzim,
    },
    data() {
        return {
            zzimNo:[
                [1,1],[2,2],[3,3],[4,4],[5,5],[6,6],[7,7],[8,8],[9,9],
            ],
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>