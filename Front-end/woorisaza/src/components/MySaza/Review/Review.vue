<template>
    <div>
        <!-- {{reviewNo}} -->
        <div class="box mt-3 p-3" >
            <b-row>
                <b-col cols="8">
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;" >김현수아님</div>
                        <div style="font-size:0.9em">띠꺼워요</div>
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:1em;font-weight:bold;">2022.01.12</div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Review',
    props:["reviewNo"],
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

<style scoped>
.box{
    background-color: rgb(231, 231, 231);
}
</style>