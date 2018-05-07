package cn.gzsxt.topic;

import cn.gzsxt.util.TopicActiveMQUtil;

import javax.jms.*;

/**
 * 订阅者读取,必须订阅者要一直开启
 * 才能获取发布者信息,先SUB 后PUB
 */
public class TopicReceive implements Runnable{


    @Override
    public void run() {
        try {
            TopicSession session = TopicActiveMQUtil.getSession();

            Topic topic = session.createTopic("cn.gzsxt.topic");
            //创建订阅者
            TopicSubscriber subscriber = session.createSubscriber(topic);

            System.out.println("?");
            while (true){
                //获取队列信息
                Message receive = subscriber.receive();

                System.out.println(receive.toString());

                session.commit();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TopicReceive tp = new TopicReceive();
        Thread thread = new Thread(tp);
        thread.start();
    }
}
