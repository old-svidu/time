package main;


/**
 * Created by root on 09.02.17.
 */
public class Main {
    public static int time = 1;
    public static Object lock = new Object();


    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("ошибка в 1 потоке");
                    }
                    System.out.println("every  sec "+time);
                    synchronized (lock) {
                        time++;
                        lock.notify();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println("ошибка во 2 потоке");
                        }

                        if (time % 5 == 0) {
                            System.out.println("every 5sec " + time);
                        }
                        lock.notifyAll();
                    }
                }
            }

        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println("ошибка во 3 потоке");
                        }
                        if (time % 7 == 0) {
                            System.out.println("every 7sec " + time);
                        }
                    }
                }
            }

        });
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
