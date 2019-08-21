package com.xzl.mqtt;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2019/5/27 13:50
 * @version: v0.0.1
 * @history:
 */

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *发布端
 */
public class PublishSample {
    public static void main(String[] args) {

        String topic = "mqttIdstrage11";
        int qos = 1;
        String broker = "tcp://192.168.1.23:1883";
        String userName = "admin";
        String password = "Senscape";
        String clientId = "xxxx";
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            String content = "{\"action\":\"ret_track_topn\",\"client_id\":\"mqttIdstrage11\",\"seqno\":7,\"err_code\":0,\"err_msg\":\"success\",\"img_ids\":[\"4ebfa6b6-b457-420a-9abd-b82bcf10b2b8\",\"f03481d2-8f39-43e7-8a05-77d0bcf04a31\",\"22079ff2-c829-4571-839f-e733f8f6b578\",\"76229f7d-d468-464b-af5d-3d4fddb156c3\",\"d261d689-1a73-4463-a48b-b2a869832183\",\"f7261d5d-1855-4757-ba50-e7ea21030edf\",\"2169f704-4366-4098-897a-6f439f8eed50\",\"429e30aa-79d4-43c4-bd08-29f8c4ae2b5e\",\"e0583203-8bd8-4d89-afb4-de4c941f1f23\",\"01d517aa-3f3f-4395-b07f-b460fd02d780\"]}";
            MqttMessage message = new MqttMessage(content.getBytes());
            // 设置消息的服务质量
            message.setQos(qos);
            sampleClient.publish(topic, message);
            // 断开连接
            sampleClient.disconnect();
            System.out.println("发送成功");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
