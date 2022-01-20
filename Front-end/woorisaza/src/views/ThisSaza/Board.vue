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
                <div v-for="(item, index) in articleNo" :key="index">
                    <Article :articleNo="item"></Article>
                    <hr>
                </div>
            </div>
            
        </div>
</template>

<script>
import Article from "@/components/ThisSaza/Article.vue";
export default {
    name: 'Board',
    components : {
        Article,
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

            articleNo:[1,2,3,4,5,6,7,8,9,10],
        };
    },

    mounted() {
        
    },

    methods: {
        
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