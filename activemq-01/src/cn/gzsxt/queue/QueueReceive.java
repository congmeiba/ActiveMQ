package cn.gzsxt.queue;

import cn.gzsxt.util.QueueActiveMQUtil;

import javax.jms.*;
import java.util.Map;

public class QueueReceive {


    public static void main(String[] args) {

        try {
            QueueSession session = QueueActiveMQUtil.getSession();

            Queue queue = session.createQueue("cn.gzsxt.queue");

            QueueReceiver receiver = session.createReceiver(queue);
            // 设置监听程序
            receiver.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if( message != null){
                        MapMessage msg = (MapMessage) message;
                        try {
                            String queue1 = msg.getString("queue");
                            System.out.println(queue1);
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            session.commit();

            QueueActiveMQUtil.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
