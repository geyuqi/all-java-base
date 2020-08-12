package state;

import java.util.concurrent.TimeUnit;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 5:04 PM
 * @Description: 线程状态
 */
public class Demo01 {
    public static void main(String[] args)
            throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("hello thread");
        });
        System.out.println("线程状态=======>"
                + thread.getState().name());//新生 start方法
        thread.start();
        System.out.println("线程状态=======>"
                + thread.getState().name());//执行
        TimeUnit.SECONDS.sleep(1);//休眠一秒保证线程执行结束后再执行主线程
        System.out.println("线程状态=======>"
                + thread.getState().name());//死亡
    }
}
