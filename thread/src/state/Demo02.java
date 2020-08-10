package state;

import java.util.concurrent.TimeUnit;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 5:51 PM
 * @Description: blocked测试
 */
public class Demo02 {
    public static void main(String[] args) throws InterruptedException {
        String lock = "lock";
        Thread thread1 = new Thread(new MyThread(lock));
        Thread thread2 = new Thread(new MyThread(lock));
        thread1.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread2.getState());
    }
}

class MyThread implements Runnable {

    private String lock;

    public MyThread(String lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}