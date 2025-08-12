package cn.iocoder.yudao.framework.websocket.core.sender.rocketmq;

import cn.iocoder.yudao.framework.websocket.core.sender.AbstractWebSocketMessageSender;
import cn.iocoder.yudao.framework.websocket.core.sender.WebSocketMessageSender;
import cn.iocoder.yudao.framework.websocket.core.session.WebSocketSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 基于 RocketMQ 的 {@link WebSocketMessageSender} 实现类
 *
 * @author 芋道源码
 */
@Slf4j
public class RocketMQWebSocketMessageSender extends AbstractWebSocketMessageSender {

    private final RocketMQTemplate rocketMQTemplate;

    private final String topic;

    public RocketMQWebSocketMessageSender(WebSocketSessionManager sessionManager,
                                          RocketMQTemplate rocketMQTemplate,
                                          String topic) {
        super(sessionManager);
        this.rocketMQTemplate = rocketMQTemplate;
        this.topic = topic;
    }

    @Override
    public void send(Long userId, String messageType, String messageContent) {
        sendRocketMQMessage(null, userId, messageType, messageContent);
    }

    @Override
    public void send(String messageType, String messageContent) {
        sendRocketMQMessage(null, null, messageType, messageContent);
    }

    @Override
    public void send(String sessionId, String messageType, String messageContent) {
        sendRocketMQMessage(sessionId, null, messageType, messageContent);
    }

    /**
     * 通过 RocketMQ 广播消息
     *
     * @param sessionId      Session 编号
     * @param userId         用户编号
     * @param messageType    消息类型
     * @param messageContent 消息内容
     */
    private void sendRocketMQMessage(String sessionId, Long userId, String messageType, String messageContent) {
        RocketMQWebSocketMessage mqMessage = new RocketMQWebSocketMessage()
                .setSessionId(sessionId).setUserId(userId)
                .setMessageType(messageType).setMessageContent(messageContent);
        rocketMQTemplate.syncSend(topic, mqMessage);
    }

}
