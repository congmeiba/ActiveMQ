package cn.gzsxt.jms;

import cn.gzsxt.util.JmsActiveMQUtil;

import javax.jms.*;

/**
 * jms模式下信息发送者
 */
public class MessageSender {


    //发送信息
    public static void sendMessage(Session session,MessageProducer producer){
        try {
            //向队列中插入5条信息
            for(int i = 0;i<5;i++){
             //通过session会话创建信息
             TextMessage message = session.createTextMessage("向activeMq队列中插入第" + (i + 1) + "条信息");
             //发送者发布信息
             producer.send(message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String queue = "cn.gzsxt.jms";
        try {
            Session session = JmsActiveMQUtil.getSession();
            //创建目标消息队列
            Destination destination = session.createQueue(queue);
            //通过session会话创建发送者.
            //传入参数,使得发送者知道要往哪个队列中存储信息
            MessageProducer producer = session.createProducer(destination);
            //存儲數據
            sendMessage(session,producer);
            //提交会话
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JmsActiveMQUtil.close();
        }


    }

}
