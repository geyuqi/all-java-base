package createThread;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 4:09 PM
 * @Description: TODO 继承Thread类创建线程
 */
public class Demo01 {
    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "继承Thread类创建线程");
        }
    }
}
