<template>
    <div class="mt-3" style="background-color : #FFFDF2; width:100%;height:87vh">
        <div class="container">
            <div class="pt-5">
                <b-container >
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <Board></Board>
                        </b-col>
                        <b-col></b-col>
                    </b-row>
                </b-container>
            </div>
            
        </div>
    </div>
</template>

<script>
import Board from "@/views/ThisSaza/Board.vue";
export default {
    name: 'Thissaza',
    components : {
        Board,
    },
    data() {
        return {
            
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