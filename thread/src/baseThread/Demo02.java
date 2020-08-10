package baseThread;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 4:19 PM
 * @Description: 启动一个线程的唯一方法就是调用Thread类的start()方法
 * start方法只能调用一次调用多次会出现Expection in thread "main" java.lang.IllegalThreadStateExpection异常
 * 执行线程类的run方法就是简单的执行一个方法并不会开启一个线程
 * run方法可以单独执行么? 可以
 */
public class Demo02 {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> System.out.println("hello thread"));
        thread.run();
        System.out.println("不执行start方法线程的状态==>" + thread.getState().name());
        thread.start();
        System.out.println("执行start方法线程的状态==>" + thread.getState().name());
        thread.start();
    }
}
