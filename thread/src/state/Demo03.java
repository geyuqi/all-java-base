package state;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: geyuqi
 * @DateTime: 8/10/2020 6:02 PM
 * @Description: 源代码注释可以贴上
 * <p>
 * <p>
 * 二、Obj.wait()与Obj.notify()必须要与synchronized(Obj)一起使用，也就是wait与notify是针对已经获取了Obj锁的对象来进行操作。
 * （1）Obj.wait()、Obj.notify必须在synchronized(Obj){…}语句块内。
 * （2）wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠。直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行。相应的notify()就是对对象锁的唤醒操作。
 * （3）notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。如果是notifyAll()就会释放所有的锁。
 * <p>
 * 注意：如果notify的对象没有wait，会报IllegalMonitorStateException错误
 * <p>
 * wait方法/notify方法必须与synchronized关键字一起使用
 */
public class Demo03 {
    //Thread.sleep(long millis)，一定是当前线程调用此方法，当前线程进入TIMED_WAITING状态，但不释放对象锁，millis后线程自动苏醒进入就绪状态。作用：给其它线程执行机会的最佳方式。
    //Thread.yield()，一定是当前线程调用此方法，当前线程放弃获取的CPU时间片，但不释放锁资源，由运行状态变为就绪状态，让OS再次选择线程。作用：让相同优先级的线程轮流执行，但并不保证一定会轮流执行。实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。Thread.yield()不会导致阻塞。该方法与sleep()类似，只是不能由用户指定暂停多长时间。
    //thread.join()/thread.join(long millis)，当前线程里调用其它线程的join方法，当前线程进入WAITING/TIMED_WAITING状态，当前线程不会释放已经持有的对象锁。线程t执行完毕或者millis时间到，当前线程一般情况下进入RUNNABLE状态，也有可能进入BLOCKED状态（因为join是基于wait实现的）。
    //obj.wait()，当前线程调用对象的wait()方法，当前线程释放对象锁，进入等待队列。依靠notify()/notifyAll()唤醒或者wait(long timeout) timeout时间到自动唤醒。
    //obj.notify()唤醒在此对象监视器上等待的单个线程，选择是任意性的。notifyAll()唤醒在此对象监视器上等待的所有线程。
    //LockSupport.park()/LockSupport.parkNanos(long nanos),LockSupport.parkUntil(long deadlines), 当前线程进入WAITING/TIMED_WAITING状态。对比wait方法,不需要获得锁就可以让线程进入WAITING/TIMED_WAITING状态，需要通过LockSupport.unpark(Thread thread)唤醒。
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread1();
        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(thread.getState());
        LockSupport.unpark(thread);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(thread.getState());
//        String lock = "lock";
//        Thread thread = new Thread(new MyThread2(lock));
//        thread.start();
//        TimeUnit.MILLISECONDS.sleep(100);
//        System.out.println(thread.getState());
//        synchronized (lock) {
//            lock.notify();
//        }
//        Thread.sleep(100);
//        System.out.println(thread.getState());

//        MyThread3 thread = new MyThread3();
//        thread.start();
//        MyThread4 thread4 = new MyThread4(thread);
//        thread4.start();
//        Thread.sleep(100);
//        System.out.println(thread4.getState());
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        LockSupport.park();
    }
}

class MyThread2 implements Runnable {
    public MyThread2(String lock) {
        this.lock = lock;
    }

    private String lock;


    @Override
    public void run() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread3 extends Thread {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyThread4 extends Thread {
    private Thread thread;

    public MyThread4(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
