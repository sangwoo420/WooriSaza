<template>
        <div>
            <!-- 카테고리 + 검색 -->
            <div>    
                <b-row>       
                    <b-col cols="8">
                        <div>
                            <b-form-select v-model="category" :options="categories" size="sm" style="width:48%"></b-form-select>&nbsp;
                            <b-form-select v-model="area" :options="areas" size="sm" style="width:48%"></b-form-select>
                        </div>
                    </b-col>
                    <b-col cols="4">
                        <div>
                            <b-form-input v-model="search" placeholder="검색어를 입력하세요." size="sm" style="width:75%;display:inline"></b-form-input>
                            <b-icon icon="search" style="width:25%;cursor:pointer;"></b-icon>
                        </div>
                    </b-col>    
                    </b-row>  
            </div>
            
            <!-- 게시글 목록 -->
            <div class="mt-3">
                <div v-for="(item, index) in printedArticleNo" :key="index">
                    <Article :articleNo="item"></Article>
                    <hr>
                </div>
            </div>
            <infinite-loading @infinite="infiniteHandler" slot="append" force-use-infinite-wrapper=".el-table__body-wrapper"></infinite-loading>
        </div>
</template>

<script>
import Article from "@/components/ThisSaza/Article.vue";
import InfiniteLoading from "vue-infinite-loading";

export default {
    name: 'Board',
    components : {
        Article,
        InfiniteLoading,
    },
    data() {
        return {
            category : null,
            categories : [
                {value:null, text:"카테고리를 선택하세요."},
                {value:"food", text:"식품"},
                {value:"daily", text:"생활 용품"},
                {value:"fasion", text:"패션 잡화"},
                {value:"other", text:"기타"}
            ],
            area : null,
            areas : [
                {value:null, text:"지역범위를 선택하세요."},
                {value:"walk5", text:"걸어서 5분 이내"},
                {value:"walk10", text:"걸어서 10분 이내"},
                {value:"car5", text:"차타고 5분 이내"},
                {value:"car10", text:"차타고 10분 이내"}
            ],
            search:null,
            scrollPostion : 0,
            printedArticleNo:[1,2,3,4,5,6,7,8,9,10],
            articleNo:[
                1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,27,28,29,30,
                31,32,33,34,35,36,37,38,39,40,
                41,42,43,44,45,46,47,48,49,50,
                51,52,53,54,55,56,57,58,59,60,
                61,62,63,64,65,66,67,68,69,70,
                71,72,73,74,75,76,77,78,79,80,
                81,82,83,84,85,86,87,88,89,90,
                91,92,93,94,95,96,97,98,99,100,
            ],
        };
    },

    mounted() {
        
    },

    methods: {
        infiniteHandler($state){
            // console.log($state);
            if(this.printedArticleNo.length < this.articleNo.length){
                let len = this.printedArticleNo.length;
                for (let index = len; index < len+10; index++) {
                    this.printedArticleNo.push(this.articleNo[index])
                }
                // console.log(this.printedArticleNo.length)
                $state.loaded();
            }
            else{
                $state.complete();
            }
        }
    },
};
</script>

<style scoped>
    .custom-select{
        font-size: 0.5em;
        
    }

    .form-control{
        font-size: 0.5em;
    }
</style>