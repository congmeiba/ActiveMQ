package cn.gzsxt.jms;

import cn.gzsxt.util.JmsActiveMQUtil;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

/**
 * 测试点对点模式下接收方,多方去获取信息是什么样的?
 */
public class ThreadMssageReceive implements Runnable {

    @Override
    public void run() {
        String queueName = "cn.gzsxt.jms";
        try {
            Session session = JmsActiveMQUtil.getSession();
            //创建消息队列
            Destination destination = session.createQueue(queueName);
            //创建接收方
            MessageConsumer consumer = session.createConsumer(destination);
            while (true) {
                System.out.println(Thread.currentThread());
                Message receive = consumer.receive(10*1000);
                if(receive == null){
                    break;
                }
                System.out.println(receive.toString()+"-----"+Thread.currentThread());
                Thread.sleep(2000);
            }
            session.commit();
            session.close();
            System.out.println(Thread.currentThread());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //JmsActiveMQUtil.close();
        }
    }
}
