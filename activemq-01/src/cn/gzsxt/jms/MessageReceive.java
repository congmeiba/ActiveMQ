package cn.gzsxt.jms;

import cn.gzsxt.util.JmsActiveMQUtil;

import javax.jms.*;

/**
 * jms模式下接收方
 */
public class MessageReceive {


    public static void main(String[] args) {
        String queueName = "cn.gzsxt.jms";
        try {
            Session session = JmsActiveMQUtil.getSession();
            //创建消息队列
            Destination destination = session.createQueue(queueName);
            //创建接收方
            MessageConsumer consumer = session.createConsumer(destination);

            while (true){
                //读取队列中的信息
                //receive方法可以参数一个参数,进行等待超时自动关闭功能
                //Message receive = consumer.receive(10*1000); 如果10秒内队列没有信息,那么接收方会自动关闭
                //超时后,Message对象就为null,那么我们就判断为null的时候去关闭退出这个循环
                //如果不传入参数,那么接收方会一直读取,一直等队列内有新的信息
                Message receive = consumer.receive();
                System.out.println(receive.toString());
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JmsActiveMQUtil.close();
        }
    }


}
