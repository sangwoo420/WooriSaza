<template>
    <div class="mb-2">
        <!-- {{partyNo}} -->
        <b-container class="bv-example-row">
            <b-row>
                <b-col cols="3"><b-img  :src="data" v-bind="mainProps" rounded="circle" alt="Circle image" thumbnail ></b-img></b-col>
                <b-col cols="6">
                    <div>
                        물 24개 나누실 분
                    </div>
                    <div style="font-size:10px">
                        운송장 번호도 나중에 등록해..
                    </div>
                </b-col>
                <b-col cols="3">
                    <div style="text-align:right">
                        <div >
                            <b-icon icon="chat-fill" variant="danger"></b-icon>
                        </div>
                        <div style="font-size:10px">
                            2:14 PM
                        </div>
                    </div>
                </b-col>
            </b-row>
        </b-container>
        <hr>
    </div>
</template>

<script>
import Identicon from "identicon.js"
export default {
    name: 'Chatpreview',
    props:["partyNo"],
    data() {
        return {
            mainProps: { blank: false, blankColor: '#777', width: 50, height: 50, class: 'm1' },
            base64 : btoa(this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()+this.partyNo.toString()),
            data : "",
            options : {
                foreground: [this.partyNo%256,(this.partyNo+50)%256,(this.partyNo+120)%256,(this.partyNo+200)%256],               // rgba black
                background: [255, 255, 255, 255],         // rgba white
                margin: 0.2,                              // 20% margin
                size: 50,                                // 420px square
                format: 'png'                             // use SVG instead of PNG
            },
        };
    },

    mounted() {
        this.data="data:image/png;base64,"+new Identicon(this.base64,this.options).toString()
    },

    methods: {
        
    },
};
</script>

<style scoped>
.col-3{
    padding-right: 0;
    padding-left: 0;
}
.col-6{
    padding-right: 0;
    padding-left: 0;
}

</style>