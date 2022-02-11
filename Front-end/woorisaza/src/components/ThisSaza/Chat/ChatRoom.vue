<template>
    <div class="mb-2">
        <!-- {{room}} -->
        <div class="title">{{roomName}}</div><br><hr>
        <div class="chat">
            
            <div v-for="(item, index) in roomChat" :key="index" >
                유저이름: {{ item.sender }}<br>
                내용: {{ item.content }}<br>
                시간: {{ item.time }}<br>
            </div>
        </div>
        <input v-model="message" type="text" @keyup.enter="sendMessage">
        <button @click="sendMessage">전송</button>
    </div>
</template>


<script>
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';
import {axios_contact} from "@/common.js"

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
        axios_contact({
            method : "get",
            url : "/user/"+this.profileId,
        }).then(({data})=>{
            // console.log(data.profile.nickname)
            this.myName = data.profile.nickname;
            // 소켓 연결
            this.connect();
        })
        
        axios_contact({
            method : "get",
            url : "/chat/room/enter/"+this.roomId,
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
            const serverURL = "http://i6c102.p.ssafy.io:8080/ws-stomp";
            // const serverURL = "http://127.0.0.1:8080/ws-stomp";
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

.chat{
    background-color:white; 
    width:100%;
    height:460px;
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
    overflow-y:scroll;
}
.chat::-webkit-scrollbar{ display:none; }

.title{
    position: fixed;
}

</style>