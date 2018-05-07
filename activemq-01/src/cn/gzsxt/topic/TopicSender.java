package cn.gzsxt.topic;

import cn.gzsxt.util.TopicActiveMQUtil;

import javax.jms.*;

/**
 * 发布/订阅
 *
 * 发布者Pub
 *
 */
public class TopicSender {


    public static void main(String[] args) {
        try {
            //获取session会话
            TopicSession session = TopicActiveMQUtil.getSession();
            //主题队列
            Topic topic = session.createTopic("cn.gzsxt.topic");
            //创建发布者
            TopicPublisher publisher = session.createPublisher(topic);
            for (int i = 0;i<5;i++){
                TextMessage message = session.createTextMessage("发送消息第" + (i + 1) + "条");
                publisher.send(message);
            }
            session.commit();
            TopicActiveMQUtil.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
