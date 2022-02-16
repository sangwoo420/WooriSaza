<template>
    <div>
        <!-- {{articleNo}} -->
        <div  v-if="article != null">
            <b-row>
                <b-col>
                    <div style="cursor:pointer">
                        <!-- <b-img thumbnail @click="moveToDetail" :src="article.pic[0]" alt="" style="width:150px;height:150px"></b-img> -->
                        <b-card
                            class="text-center"
                            overlay
                            @click="moveToDetail"
                            :img-src="article.pic[0]"
                        >
                        <b-button v-if="finDeal" variant="danger" size="sm" disaled>거래 완료</b-button>
                        </b-card>
                    </div>
                </b-col>
                <b-col>
                    <b-row>
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">
                            {{article.title}}
                        </div>
                    </b-row>
                    <div>
                        <div style="font-size:0.9em">{{article.product}}</div>
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">{{article.totalPrice}}원</div>
                        <div>{{article.myPrice}}원</div>
                    </div>
                </b-col>
                <b-col>
                    <div style="text-align:right" class="mt-5">

                        <div v-if="isZzim"><img src="@/assets/zzimOn.png" style="width:20px;cursor:pointer" @click="deleteZZim"></div>
                        <div v-if="!isZzim"><img src="@/assets/zzimOff.png" style="width:20px;cursor:pointer" @click="addZZim"></div>

                        <div style="font-size:0.7em" class="mt-2">
                            {{article.currentRecruitMember}}명/{{article.totalRecruitMember}}명<br>
                            기간 : ~{{article.deadline[0]}}.{{article.deadline[1]}}.{{article.deadline[2]}}
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
            isZzim : null,
            id : this.$cookie.get("id"),
            finDeal : false,
            party : null,
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "article/"+this.articleNo,
        }).then(({data})=>{
            this.article=data.article;
            // if(this.article.currentRecruitMember == this.article.totalRecruitMember){
            //     this.finDeal=true;
            // }
            axios_contact({
                method : "get",
                url : "/party?partyId="+data.article.partyId,
            }).then(({data})=>{
                console.log(data)
                this.party=data;
                let count = 0;
                let bossCount = 0;
                for (let index = 0; index < data.length; index++) {
                    if(data[index].isConfirmed){
                        count+=data[index].totalamount;
                    }
                    if(data[index].isBoss){
                        bossCount = data[index].totalamount;
                    }
                }
                if(this.article.totalRecruitMember==count-bossCount && data.length > 1){
                    this.finDeal=true;
                }
            })
        })

        axios_contact({
            method : "get",
            url : "/zzim?articleId="+this.articleNo+"&profileId="+this.id,
        }).then((data)=>{
            if(data.data.zzim!=null){
                this.isZzim=true;
            }else{
                this.isZzim=false;
            }
        })

        
    },
    mounted() {
        
    },

    methods: {
        moveToDetail(){
            this.$router.push("/board/"+this.articleNo).catch(()=>{});
        },
        addZZim(){
            this.isZzim = true;
            axios_contact({
                method : "post",
                url : "/zzim",
                data : {
                    "profileid": this.id,
                    "articleid": this.articleNo,
                }
            }).then(({data})=>{
                data
                // console.log(data)
            })
        },
        deleteZZim(){
            this.isZzim = false;
            axios_contact({
                method : "delete",
                url : "/zzim",
                data : {
                    "profileid": this.id,
                    "articleid": this.articleNo,
                }
            }).then(({data})=>{
                data
                // console.log(data)
            })
        },
    },
};
</script>

<style  scoped>
.text-danger{
    color : black;
}
</style>