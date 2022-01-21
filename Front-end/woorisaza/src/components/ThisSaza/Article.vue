<template>
    <div>
        <!-- {{articleNo}} -->
        <div>
            <b-row>
                <b-col>
                    <div style="cursor:pointer">
                        <b-img thumbnail @click="moveToDetail" src="https://thumbnail10.coupangcdn.com/thumbnails/remote/230x230ex/image/rs_quotation_api/38c3fcaa/1c9e059c1bb6469bbb31db4f869ba658.jpg" alt=""></b-img>
                    </div>
                </b-col>
                <b-col>
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">글 제목</div>
                        <div style="font-size:0.9em">상품명</div>
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">총 가격</div>
                        <div>내 가격</div>
                    </div>
                </b-col>
                <b-col>
                    <div style="text-align:right" class="mt-5">
                        <img src="@/assets/zzimOn.png" style="width:20px;cursor:pointer"><br>
                        <div style="font-size:0.7em" class="mt-2">
                            현재인원/모집인원<br>
                            모집기간
                        </div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Article',
    props:["articleNo"],

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        moveToDetail(){
            this.$router.push("/board/"+this.articleNo).catch(()=>{});;
        },
    },
};
</script>

<style lang="scss" scoped>

</style>