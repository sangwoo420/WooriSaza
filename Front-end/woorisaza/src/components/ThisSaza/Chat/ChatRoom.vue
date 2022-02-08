<template>
    <div class="mb-2">
        <!-- {{room}} -->
        <b-container class="bv-example-row">
            <b-row>
                <p>{{roomName}}</p>
                <br>
                <div v-for="(item, index) in roomChat" :key="index" >
                    <h6>유저이름: {{ item.sender }}</h6>
                    <h6>내용: {{ item.content }}</h6>
                    <h6>시간: {{ item.time }}</h6>
                </div>
            </b-row>
            내용: <input v-model="message" type="text" @keyup.enter="sendMessage">
        </b-container>
    </div>
</template>


<script>
import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

export default {
    name: 'ChatRoom',
    props:["roomId"],
    data() {
        return {
            profileId : this.$cookie.get("id"),
            roomName:null,
            roomChat:[],
            myName:"",
            message:"",

            sock : null,
            ws : null,
            reconnect : 0,
        };
    },

    created(){
        this.sock = new SockJS("/ws-stomp");
        this.ws = Stomp.over(this.sock);
        this.reconnect = 0;

        axios({
            method : "get",
            url : "http://localhost:8080/user/"+this.profileId,
        }).then(({data})=>{
            // console.log(data.profile.nickname)
            this.myName = data.profile.nickname;
        })
        
        axios({
            method : "get",
            url : "http://localhost:8080/chat/room/enter/"+this.roomId,
        }).then(({data})=>{
            console.log(data)
            this.roomName = data.chatRoom.name;
            this.roomChat = data.chatRoom.msgList;
        })

        this.connect();
    },

    mounted() {
    },

    methods: {
        sendMessage: function() {
            this.ws.send("/pub/chat/message?userNickname="+this.myName, {}, JSON.stringify({type:'CHAT', roomId:this.roomId, sender:this.myName, content:this.message}));
        },
        recvMessage: function(recv) {
            this.messages.unshift({"type":recv.type,"sender":recv.sender,"content":recv.content})
        },
        connect(){
            this.ws.connect({}, function(frame) {
                console.log(frame);
                this.ws.subscribe("/sub/chat/room/"+this.roomId, function(message) {
                    var recv = JSON.parse(message.body);
                    this.recvMessage(recv);
                });
                this.ws.send("/pub/chat/message?userNickname="+this.myName, {}, JSON.stringify({type:'ENTER', roomId:this.roomId, sender:this.myName}));
            }, function(error) {
                console.log(error);
                if(this.reconnect++ < 5) {
                    setTimeout(function() {
                        // console.log("connection reconnect");
                        this.sock = new SockJS("/ws-stomp");
                        this.ws = Stomp.over(this.sock);
                        this.connect();
                    },10*1000);
                }
            });
        },
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