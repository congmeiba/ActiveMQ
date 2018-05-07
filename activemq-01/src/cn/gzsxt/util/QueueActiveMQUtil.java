package cn.gzsxt.util;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

import javax.jms.*;

public class QueueActiveMQUtil {
    //ActiveMQConnection 默认用户
    private static String user = ActiveMQConnection.DEFAULT_USER;
    //ActiveMQConnection 默认密码
    private static String pwd = ActiveMQConnection.DEFAULT_PASSWORD;
    //以tcp协议传输的url
    private static String brokeUrl = "tcp://localhost:61616";

    private static QueueConnection connection;

    private static QueueSession session;


    public static QueueSession getSession() {
        try {
            //创建链接工厂对象
            QueueConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, pwd, brokeUrl);
            //通过工厂创建连接
            connection = connectionFactory.createQueueConnection();
            //启动连接
            connection.start();
            //创建session会话
            //参数1:一般都未true
            //参数2:使用ActiveMQSession自动注册
            session = connection.createQueueSession(Boolean.TRUE, ActiveMQSession.AUTO_ACKNOWLEDGE);
            return session;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    //使用完后关闭
    public static void close() {

        try {
            if (session != null) {
                session.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
