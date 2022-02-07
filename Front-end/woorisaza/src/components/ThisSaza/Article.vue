<template>
    <div>
        <!-- {{articleNo}} -->
        <div  v-if="article != null">
            <b-row>
                <b-col>
                    <div style="cursor:pointer">
                        <b-img thumbnail @click="moveToDetail" :src="article.pic[0]" alt=""></b-img>
                    </div>
                </b-col>
                <b-col>
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">{{article.title}}</div>
                        <div style="font-size:0.9em">{{article.product}}</div>
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">{{article.totalPrice}}원</div>
                        <div>{{article.totalPrice/article.totalRecruitMember}}원</div>
                    </div>
                </b-col>
                <b-col>
                    <div style="text-align:right" class="mt-5">
                        <img src="@/assets/zzimOn.png" style="width:20px;cursor:pointer"><br>
                        <div style="font-size:0.7em" class="mt-2">
                            {{article.currentRecruitMember}}명/{{article.totalRecruitMember}}명<br>
                            모집기간
                        </div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
    name: 'Article',
    props:["articleNo"],

    data() {
        return {
            article : null,
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "article/"+this.articleNo,
        }).then(({data})=>{
            this.article=data.article;
        })
    },
    mounted() {
        
    },

    methods: {
        moveToDetail(){
            this.$router.push("/board/"+this.articleNo).catch(()=>{});
        },
    },
};
</script>

<style lang="scss" scoped>

</style>