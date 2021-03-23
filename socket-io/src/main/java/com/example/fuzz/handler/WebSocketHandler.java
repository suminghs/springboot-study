package com.example.fuzz.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  核心业务处理类
 * </p>
 *
 * @author fuzz
 * @since 2021/3/23 10:34
 */
@Component
@Slf4j
public class WebSocketHandler {
    @Autowired
    private SocketIOServer server;

    /**
     * 连接时候出发事件
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            log.info("连接成功,【token】= {},【sessionId】= {}", getToken(client), client.getSessionId());
        }
    }

    /**
     * 断开时触发事件
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        if (client != null) {
            log.info("客户端退出,【token】= {},【sessionId】= {}", getToken(client), client.getSessionId());
        }
    }


    /**
     * 自定义事件  模拟加群
     * @param client  客户端
     * @param request  返回对象
     * @param data 接收参数
     */
    @OnEvent(value = "join")
    public void join(SocketIOClient client, AckRequest request, Map data) {
        String roomId = data.get("roomId").toString();
        String message = "用户：" + getToken(client) +" 加入群聊";
        client.joinRoom(roomId);
        log.info(data.toString());
        log.info(message);

       /*
       前端发送事件到后端
       socket.emit('join', joinRequest, function (data) {
            if (data) {
                layer.msg(data, {icon: 5});
            }
        });

        前端接收后端发送事件
        socket.on('join', function (data) {
            output(`<span class="broadcast">${data}</span>`);
        });*/

        // 获取一组的客户端并发送事件
        server.getRoomOperations(roomId).sendEvent("join", message);

        // 返回前端信息
        request.sendAckData("加入成功");
    }

    /**
     * 自定义事件 模拟私聊
     * @param client  客户端
     * @param request  返回对象
     * @param data 接收参数
     */
    @OnEvent(value = "chat")
    public void chat(SocketIOClient client, AckRequest request, Map data) {
        String userId = data.get("userId").toString();
        String message = data.get("message").toString();
        log.info(data.toString());
        log.info("用户 {} 刚刚私信了用户 {}：{}", client.getSessionId(), userId, message);
        server.getClient(UUID.fromString(userId)).sendEvent("chat", message);
        request.sendAckData("发送成功");
    }


    private String getToken(SocketIOClient client) {
        return client.getHandshakeData().getSingleUrlParam("token");
    }

}
