package cn.gzsxt.queue;

import cn.gzsxt.util.QueueActiveMQUtil;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;

public class QueueSender {


    public static void main(String[] args) {

        try {
            QueueSession session = QueueActiveMQUtil.getSession();

            Queue queue = session.createQueue("cn.gzsxt.queue");

            javax.jms.QueueSender sender = session.createSender(queue);

            MapMessage message = session.createMapMessage();

            message.setString("queue",Thread.currentThread()+"");

            sender.send(message);

            session.commit();

            QueueActiveMQUtil.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
