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
            입력: <input v-model="message" type="text" @keyup.enter="sendMessage">
        </b-container>
    </div>
</template>


<script>
import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';

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

            stompClient:null,
        };
    },

    created(){
        axios({
            method : "get",
            url : "http://localhost:8080/user/"+this.profileId,
        }).then(({data})=>{
            // console.log(data.profile.nickname)
            this.myName = data.profile.nickname;
            // 소켓 연결
            this.connect();
        })
        
        axios({
            method : "get",
            url : "http://localhost:8080/chat/room/enter/"+this.roomId,
        }).then(({data})=>{
            console.log(data)
            this.roomName = data.chatRoom.name;
            this.roomChat = data.chatRoom.msgList;
        })
    },

    mounted() {
    },

    methods: {
        sendMessage: function() {
            this.stompClient.send("/pub/chat/message", {}, JSON.stringify({type:'CHAT', content:this.message, roomId:this.roomId, sender:this.myName}));
            this.message = '';
        },
        recvMessage: function(recv) {
            console.log(recv);
            this.roomChat.push({"sender":recv.sender,"content":recv.content,"time":recv.time})
        },
        connect(){
            const serverURL = "http://i6c102.p.ssafy.io/ws-stomp";
            let socket = new SockJS(serverURL);
            // let socket = new SockJS("/ws-stomp");
            // const that = this;
            this.stompClient = Stomp.over(socket);
            this.reconnect = 0;

            this.stompClient.connect({}, (frame) => {
                console.log("Connected: " + frame);
                this.stompClient.subscribe("/sub/chat/room/"+this.roomId, (message) => {
                    var recv = JSON.parse(message.body);
                    console.log("구독으로 받은 메세지: " +recv.message);
                    this.recvMessage(recv);
                });
            }, (error) => {
                console.log("Fail: " + error);
                if(this.reconnect++ < 5) {
                    setTimeout(function() {
                        // console.log("connection reconnect");
                        let socket = new SockJS(serverURL);
                        this.stompClient = Stomp.over(socket);
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