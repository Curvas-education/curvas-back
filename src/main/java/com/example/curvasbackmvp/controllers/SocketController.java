//package com.example.curvasbackmvp.controllers;
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DataListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import lombok.extern.log4j.Log4j2;
//import org.aspectj.bridge.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Log4j2
//public class SocketController {
//    @Autowired
//    private SocketIOServer socketServer;
//
//    void SocketIOController(SocketIOServer socketServer){
//        this.socketServer=socketServer;
//        this.socketServer.addConnectListener(onUserConnectWithSocket);
//        this.socketServer.addDisconnectListener(onUserDisconnectWithSocket);
//        this.socketServer.addEventListener("messageSendToUser", Message.class, onSendMessage);
//
//    }
//
//    public ConnectListener onUserConnectWithSocket = new ConnectListener() {
//        @Override
//        public void onConnect(SocketIOClient socketIOClient) {
//            log.info("Perform operation on user connect in controller");
//        }
//    };
//
//    public DisconnectListener onUserDisconnectWithSocket = new DisconnectListener() {
//        @Override
//        public void onDisconnect(SocketIOClient client) {
//            log.info("Perform operation on user disconnect in controller");
//        }
//    };
//
//    public DataListener<Message> onSendMessage = new DataListener<Message>() {
//        @Override
//        public void onData(SocketIOClient client, Message message, AckRequest acknowledge) throws Exception {
//
//            log.info(message.getMessage()+" user send message to user "+message.getDetails()+" and message is "+message.getMessage());
////            socketServer.getBroadcastOperations().sendEvent(message.GET(),client, message);
//            System.out.println(message.getID());
//
//            acknowledge.sendAckData("Message send to target user successfully");
//        }
//    };
//
//}
