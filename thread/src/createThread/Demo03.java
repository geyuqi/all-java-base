package createThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 10:39 AM
 * @Description: 实现Runnable接口创建线程
 */
public class Demo03 {
    public static void main(String[] args) {
        Callable<Integer> callable = new MyThread();
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread1 = new Thread(task);
        thread1.start();
        try {
            System.out.println("线程执行结果====>" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyThread implements Callable<Integer> {
        public Integer call() {
            System.out.println(Thread.currentThread().getName() + "实现Callable接口创建线程");
            return 1;
        }
    }
}
