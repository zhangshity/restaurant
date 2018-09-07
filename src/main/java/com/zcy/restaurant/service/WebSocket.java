package com.zcy.restaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ Author: chunyang.zhang
 * @ Description:       WebScoket 组件
 * @ Date: Created in
 * @ Modified: By:     因为是模糊的层次，所以@Component注解
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    //设置Session容器，用集合存储Set
    private static CopyOnWriteArraySet<WebSocket> webSocketsSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketsSet.add(this);
        log.info("【webSocket消息】有新的连接，总数:{}", webSocketsSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketsSet.remove(this);
        log.info("【webSocket消息】连接断开，总数:{}", webSocketsSet.size());

    }

    @OnMessage
    public void onMessage(String message) {

        log.info("【webSocket消息】收到客户端发来的消息:{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketsSet) {
            log.info("webSocket消息】广播消息,message:{}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
