package baseThread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 4:19 PM
 * @Description: 线程的执行顺序是不一定的要看cpu的调度
 */
public class Demo01 {

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            threads.add(new Thread(() -> System.out.println(Thread.currentThread().getName()), "Thread" + i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
