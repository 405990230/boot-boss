package com.boss.info.webSocket.config;

/**
 * Created by qxr4383 on 2019/3/14.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 *
 * @author zhengkai
 */
@Configuration
public class WebSocketConfig {
    //@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
