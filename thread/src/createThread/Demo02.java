package createThread;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 4:09 PM
 * @Description: TODO 实现Runnable接口创建线程
 */
public class Demo02 {
    public static void main(String[] args) {
        Runnable runnable = new MyThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    static class MyThread implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "实现Runnable接口创建线程");
        }
    }
}
