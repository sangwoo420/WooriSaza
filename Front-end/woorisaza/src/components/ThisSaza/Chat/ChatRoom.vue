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
        };
    },

    created(){
        // 소켓 연결
        this.connect();

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

    },

    mounted() {
    },

    methods: {
        sendMessage: function() {
            this.stompClient.send("/pub/chat/message?userNickname="+this.myName, {}, JSON.stringify({type:'CHAT', roomId:this.roomId, sender:this.myName, content:this.message}));
            this.message = '';
        },
        recvMessage: function(recv) {
            this.roomChat.unshift({"type":recv.type,"sender":recv.sender,"content":recv.content})
        },
        connect(){
            const serverURL = "http://127.0.0.1:8080/ws-stomp";
            let socket = new SockJS(serverURL);
            // let socket = new SockJS("/ws-stomp");
            this.stompClient = Stomp.over(socket);
            this.reconnect = 0;

            this.stompClient.connect({}, function(frame) {
                console.log("Connected: " + frame);
                this.stompClient.subscribe("/sub/chat/room/"+this.roomId, function(message) {
                    console.log("구독으로 받은 메세지: " + message.body);
                    var recv = JSON.parse(message.body);
                    // this.roomChat.push(recv);
                    this.recvMessage(recv);
                });
                this.stompClient.send("/pub/chat/message?userNickname="+this.myName, {}, JSON.stringify({type:'ENTER', roomId:this.roomId, sender:this.myName}));
            }, function(error) {
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