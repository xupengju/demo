package concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class TestReentrantLock {
    private ReentrantLock lock = new ReentrantLock();

    public void print(int str) {
        try {
            lock.lock();
            System.out.println(str + "ªÒµ√");
            Thread.sleep((int) (Math.random() * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(str + " Õ∑≈");
            lock.unlock();
        }
    }
}