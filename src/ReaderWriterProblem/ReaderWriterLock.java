package ReaderWriterProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterLock {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    List<Integer> list = new ArrayList<>();
    public void add(){
        lock.writeLock().lock();
        list.add(1);
        System.out.println("Added Element to List"+list.size());
        lock.writeLock().unlock();
    }
    public void get(int idx) throws InterruptedException {
        lock.readLock().lock();
        if(list.size()>0)
        System.out.println(list.get(idx));
        lock.readLock().unlock();
    }

    public static void main(String[] args) {
        ReaderWriterLock rw = new ReaderWriterLock();
        new Thread(){
            @Override
            public void run(){
                rw.add();
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                try {
                    rw.get(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();


    }
}
