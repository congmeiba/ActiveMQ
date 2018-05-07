package cn.gzsxt.jms;

/**
 * jms模式下队列获取信息
 *
 * 点对点模式下,只能在一方下能去接收这些信息,其他放是获取不到的.
 *
 *
 */
public class TestThread {

    public static void main(String[] args) {
        ThreadMssageReceive receive = new ThreadMssageReceive();
        ThreadMssageReceive receive2 = new ThreadMssageReceive();

        Thread t1 = new Thread(receive);

        Thread t2 = new Thread(receive2);
        t1.start();

        t2.start();

    }

}
